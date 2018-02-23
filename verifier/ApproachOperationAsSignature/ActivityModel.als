module filesystem/ActivityModel

open filesystem/StateModel

abstract sig Activity {
	defEnts: set Obj, 
	defAtts: set Obj -> FName, 
	defMuls: set Obj -> FName -> Obj
}

pred exec(s, s': AbstractState, act: Activity) {
	// objects to be defined are not defined yet
	(act.defEnts != none) implies {
		act.defEnts !in s.objects
	}

	// objects of attributes to be defined eiher are already defined or are going to be defined
	all obj: act.defAtts.FName | obj in s.objects + act.defEnts

	// attributes to be defined are not defined yet
	all obj: act.defAtts.FName, att: obj.(act.defAtts) |  DefVal !in s.fields[obj, att] // no defAttribute [s, obj, att]

	// objects of multiplicity conditions eiher are already defined or are going to be defined
	all objSource: (act.defMuls.Obj).FName | objSource in s.objects + act.defEnts
	all  objTarget: FName.(Obj.(act.defMuls)) |  objTarget in s.objects + act.defEnts

	// check that object can be linked
	all objSource: (act.defMuls.Obj).FName, objTarget: FName.(objSource.(act.defMuls)), roleTarget: objSource.(act.defMuls).objTarget |
		canLink[s, objSource, roleTarget.inverse, objTarget] 

	// dependencies shoud hold in the resulting state
	all o: act.defEnts, dep: Dependence | o in dep.sourceObj implies dependence[s', o, dep]
	all o: (act.defAtts).FName, dep: Dependence | o in dep.sourceObj implies dependence[s', o, dep]

	// change state
	s'.objects = s.objects + act.defEnts
	s'.fields = s.fields + act.defAtts  -> DefVal + act.defMuls
	noFieldChangeExcept[s, s', act.defAtts + (act.defMuls).Obj]
}

