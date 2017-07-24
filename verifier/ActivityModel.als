module filesystem/ActivityModel

open filesystem/DataModel

pred preCondition(s: State, entDefs: set Obj, attDefs: set Obj -> FName) {
	entDefs in s.objects
	attDefs.FName in s.objects
	all obj: attDefs.FName | all field: obj.attDefs | s.fields[obj, field] = DefVal or s.fields[obj, field] in s.objects
}

pred postCondition(s, s': State, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj) {
	entDefs !in s.objects

	s'.objects = s.objects + entDefs

	s'.fields = s.fields + attDefs  -> DefVal + muls

	noFieldChangeExcept[s, s', attDefs + muls.Obj]
}
