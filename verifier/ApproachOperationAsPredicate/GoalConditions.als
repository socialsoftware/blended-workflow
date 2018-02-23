module filesystem/GoalConditions

open filesystem/BWSpec

pred actCondition(s: AbstractState, entDefs: set Obj, attDefs: set Obj -> FName) {
	entDefs in s.objects

	attDefs.FName in s.objects
	all obj: attDefs.FName, field: obj.attDefs | s.fields[obj, field] = DefVal or s.fields[obj, field] in s.objects
}

pred sucCondition(s, s': AbstractState, entDefs: set Obj, attDefs: set Obj -> FName, muls: set  Obj -> FName -> Obj) {
	(entDefs != none) implies {
		entDefs !in s.objects
		all obj: attDefs.FName | obj in s.objects + entDefs
		all obj: attDefs.FName, role: obj.attDefs | no s.fields[obj, role]
	}

	all objSource: (muls.Obj).FName, objTarget: FName.(objSource.muls), roleTarget: objSource.muls.objTarget |
		canLink[s, objSource, roleTarget.inverse, objTarget] 

	s'.objects = s.objects + entDefs

	s'.fields = s.fields + attDefs  -> DefVal + muls

	noFieldChangeExcept[s, s', attDefs + muls.Obj]

	all o: entDefs, dep: Dependence | o in dep.sourceObj implies checkDependence[ s', o, dep]

	all o: attDefs.FName, dep: Dependence | o in dep.sourceObj implies checkDependence[ s', o, dep]
}
