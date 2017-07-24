module ActivityModel

open DataModel

pred preCondition(s: State, entDefs: set Obj, attDefs: set Obj -> FName) {
	entDefs in s.objects
	attDefs.FName in s.objects
//	all def: attDefs | s.fields[def.FName, Obj.def] = DefVal or s.fields[def.FName, Obj.def] in s.objects
}

pred postCondition(s, s': State, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj) {
	entDefs !in s.objects

	s'.objects = s.objects + entDefs

	s'.fields = s.fields + attDefs  -> DefVal + muls

	noFieldChangeExcept[s, s', attDefs + muls.Obj]
}
