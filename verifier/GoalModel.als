module filesystem/GoalModel

open filesystem/DataModel

pred actCondition(s: State, entDefs: set Obj, attDefs: set Obj -> FName) {
	entDefs in s.objects

	attDefs.FName in s.objects
	all obj: attDefs.FName, field: obj.attDefs | s.fields[obj, field] = DefVal or s.fields[obj, field] in s.objects
}

pred sucCondition(s, s': State, entDefs: set Obj, attDefs: set Obj -> FName) {
	entDefs !in s.objects
	all obj: attDefs.FName | obj in s.objects + entDefs
	all obj: attDefs.FName, role: obj.attDefs | no s.fields[obj, role]

	s'.objects = s.objects + entDefs

	s'.fields = s.fields + attDefs  -> DefVal

	noFieldChangeExcept[s, s', attDefs]
}

pred invCondition(s, s': State, muls: set  Obj -> FName -> Obj) {
	all objSource: (muls.Obj).FName, objTarget: FName.(objSource.muls), roleTarget: objSource.muls.objTarget |
		canLink[s, objSource, roleTarget.inverse, objTarget] 
		//and canLink[s, objTarget, roleTarget, objSource] it is redundant because canLink verifies in both directions

	s'.objects = s.objects

	s'.fields = s.fields + muls

	noFieldChangeExcept[s, s',  muls.Obj]	
}
