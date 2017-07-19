module StateModel

open util/ordering [State]

abstract sig Obj {}

abstract sig FName {}

abstract sig Val {}

one sig DefVal extends Val {}

sig State {
	objects: set Obj,
	fields: objects -> ( FName ->set { Obj + Val } ) 
	dependencies: (Obj -> FName) -> (Obj -> FName)
}

fun atts (s:State, o:Obj): set FName { s.fields[o].{Obj + Val} }

pred noFieldChangeExcept(s, s': State, o: Obj, f: FName) {
	all obj: s.objects - o | obj.(s'.fields) = obj.(s.fields)
	all field: atts[s, o] - f | s'.fields[o, field] = s.fields[o, field]
}

pred noExtraFields(s: State, objs: set Obj, fNames: set FName) {
	all obj: objs <: s.objects |no obj.(s.fields)[ FName - fNames ]
}

pred attributesDefined(s: State, objs: set Obj, atts: set FName) {
	all obj: objs <: s.objects | all att: atts | s.fields[obj, att] = DefVal
}

pred multiplicityRule(s: State, objs: set Obj, role: FName, min, max: Int) {
	all obj: objs <: s.objects | #s.fields[obj, role] >= min and #s.fields[obj, role] <= max
}

pred bidirectionalRule(s: State, objsOne: set Obj, roleOne: FName, objsTwo: set Obj, roleTwo: FName) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleOne] | s.fields[objTwo, roleTwo] in objsOne
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleTwo] | s.fields[objOne, roleOne] in objsTwo
}

pred defObj(s, s' : State, o: Obj) {
	o !in s.objects

	s'.objects = s.objects + o
	s'.fields = s.fields
}

pred defAtt(s, s': State, o: Obj, att: FName) {
	o in s.objects
	no s.fields[o, att] 

	s'.objects = s.objects

	s'.fields = s.fields + (o -> att -> DefVal)

	noFieldChangeExcept[s, s', o, att]
}

pred linkObj(s, s': State, os: Obj, rel: FName, ot: Obj) {
	os in s.objects
	ot in s.objects
	ot !in s.fields[os, rel]

	s'.objects = s.objects

	s'.fields = s.fields + (os -> rel -> ot)

	noFieldChangeExcept[s, s', os, rel]
}

pred skip(s, s': State) {
	s'.objects = s.objects
	s'.fields = s.fields
}

run {}
