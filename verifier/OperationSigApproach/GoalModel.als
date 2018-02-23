module filesystem/GoalModel

open filesystem/StateModel

pred execAttributeGoal(s, s': AbstractState, attDefs: set Obj -> FName) {
	// entities where attributes are going to be defined are defined
	all obj: attDefs.FName | obj in s.objects

	// attributes are not defined
	all obj: attDefs.FName, role: obj.attDefs | no s.fields[obj, role]

	// attributes are defined
	s'.fields = s.fields + attDefs  -> DefVal 

	// no further changes
	s'.objects = s.objects
	noFieldChangeExcept[s, s', attDefs]

	// dependencies hold
	all o: attDefs.FName, dep: Dependence | o in dep.sourceObj implies dependence[s', o, dep]
}

pred execEntityGoal(s, s': AbstractState, entDefs: set Obj) {
	// entities are not defined
	(entDefs != none) implies {
		entDefs !in s.objects
	}

	// entities are defined
	s'.objects = s.objects + entDefs

	// no further changes
	s'.fields = s.fields

	// dependencies hold
	all o: entDefs, dep: Dependence | o in dep.sourceObj implies dependence[s', o, dep]
}

pred execMulGoal(s, s': AbstractState, muls: set Obj -> FName -> Obj) {
	// entities in the link to be defined are defined
	all entSource: (muls.Obj).FName | entSource in s.objects
	all entTarget: FName.(Obj.muls) | entTarget in s.objects

	// objects can be linked
	all objSource: (muls.Obj).FName, objTarget: FName.(objSource.muls), roleTarget: objSource.muls.objTarget |
		canLink[s, objSource, roleTarget.inverse, objTarget] 
		//and canLink[s, objTarget, roleTarget, objSource] it is redundant because canLink verifies in both directions

	// attributes are defined
	s'.fields = s.fields + muls

	// no further changes
	s'.objects = s.objects
	noFieldChangeExcept[s, s', muls.Obj]
}

