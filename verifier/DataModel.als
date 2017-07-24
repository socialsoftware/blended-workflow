module DataModel

open util/ordering [State]

abstract sig Obj {}

abstract sig FName {}

abstract sig Val {}

one sig DefVal extends Val {}

sig State {
	objects: set Obj,
	fields: objects -> ( FName ->set { Obj + Val } ) 
//	dependencies: (Obj -> FName) -> (Obj -> FName)
}

fun atts (s:State, o:Obj): set FName { s.fields[o].{Obj + Val} }

fun commitedAssociatedObjects (s: State, objSource: Obj, roleSource: FName, roleTarget: FName): set Obj { 
	{o: Obj | s.fields[o, roleSource] = objSource} + 
	{o: Obj | s.fields[objSource, roleTarget] = o} 
}

pred noFieldChangeExcept(s, s': State, asg: set Obj ->FName) {
	all obj: s.objects - asg.FName | obj.(s'.fields) = obj.(s.fields)
	all o: asg.FName | all field: atts[s, o] - o.asg | s'.fields[o, field] = s.fields[o, field]
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

pred noMultiplicityExceed(s: State, objs: set Obj, role: FName, max: Int) {
	all obj: objs <: s.objects | #s.fields[obj, role] <= max
}

pred bidirectionalRule(s: State, objsOne: set Obj, roleOne: FName, objsTwo: set Obj, roleTwo: FName) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleOne] | objOne in s.fields[objTwo, roleTwo]
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleTwo] | objTwo in s.fields[objOne, roleOne]
}

pred bidirectionalViolation(s: State, objsOne: set Obj, roleOne: FName, mulOne: Int, objsTwo: set Obj, roleTwo: FName, mulTwo: Int) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleOne] | #s.fields[objOne, roleOne] < mulOne  or objOne in s.fields[objTwo, roleTwo]
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleTwo] | #s.fields[objTwo, roleTwo] < mulTwo or objTwo in s.fields[objOne, roleOne]
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

	noFieldChangeExcept[s, s', o -> att]
}

pred linkObj(s, s': State, objSource: Obj, roleSource: FName, mulSource: Int, objTarget: Obj, roleTarget: FName, mulTarget: Int) {
	objSource in s.objects
	objTarget in s.objects
	objTarget !in s.fields[objSource, roleTarget]
	// source can have more targets
	#s.fields[objSource, roleTarget] < mulTarget
	// target can have more sources
	#s.fields[objTarget, roleSource] < mulSource or objSource in s.fields[objTarget, roleSource]
	// source is not completely commited yet
	let commitedObjects = commitedAssociatedObjects[s, objSource, roleSource, roleTarget] | 
		#commitedObjects < mulSource or objTarget in commitedObjects
	// target is not completely commited yet
	let commitedObjects = commitedAssociatedObjects[s, objTarget, roleTarget, roleSource] |
		#commitedObjects < mulTarget or objSource in commitedObjects

	s'.objects = s.objects

	s'.fields = s.fields + (objSource -> roleTarget -> objTarget)

	noFieldChangeExcept[s, s', objSource -> roleTarget]
}

pred skip(s, s': State) {
	s'.objects = s.objects
	s'.fields = s.fields
}

pred sameState(s, s': State) {
	s.objects = s'.objects
	s.fields = s'.fields
}

run {}
