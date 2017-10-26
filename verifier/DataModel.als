module filesystem/DataModel

open util/ordering [State]

abstract sig Obj {}

abstract sig FName {
	minMul: Int,
	maxMul: Int,
	inverse: FName
}

abstract sig Val {}

one sig DefVal extends Val {}

sig State {
	objects: set Obj,
	fields: objects -> ( FName ->set { Obj + Val } ) ,
}

fun reach (s: State, obj: set Obj, path: seq FName): set Obj {
	(#path = 1) implies {
		s.fields[obj, path.first]
	} else {
		reach[s, s.fields[obj, path.first], path.rest]
	}
}

fun atts (s: State, o: Obj): set FName { s.fields[o].{Obj + Val} }

fun commitedAssociatedObjects (s: State, objSource: Obj, roleSource: FName): set Obj { 
	{o: Obj | s.fields[o, roleSource] = objSource} + 
	{o: Obj | s.fields[objSource, roleSource.inverse] = o} 
}

pred canLink(s: State, objSource: Obj, roleSource: FName, objTarget: Obj) {
	// source is not completely commited yet in the number of targets
	let commitedTargetObjects = commitedAssociatedObjects[s, objSource, roleSource] | 
		#commitedTargetObjects < roleSource.inverse.maxMul or objTarget in commitedTargetObjects 
	// target is not completely commited yet in the number of sources
	let commitedSourceObjects = commitedAssociatedObjects[s, objTarget, roleSource.inverse] |
		#commitedSourceObjects < roleSource.maxMul or objSource in commitedSourceObjects
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

pred multiplicityRule(s: State, objs: set Obj, role: FName) {
	all obj: objs <: s.objects | #s.fields[obj, role] >= role.minMul and #s.fields[obj, role] <= role.maxMul
}

pred noMultiplicityExceed(s: State, objs: set Obj, role: FName) {
	all obj: objs <: s.objects | #s.fields[obj, role] <= role.maxMul
}

pred bidirectionalRule(s: State, objsOne: set Obj, roleOne: FName, objsTwo: set Obj, roleTwo: FName) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleTwo] | objOne in s.fields[objTwo, roleOne]
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleOne] | objTwo in s.fields[objOne, roleTwo]
}

pred bidirectionalPreservation(s: State, objsOne: set Obj, roleOne: FName, objsTwo: set Obj, roleTwo: FName) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleTwo] | objOne in s.fields[objTwo, roleOne] or canLink[s, objTwo, roleTwo, objOne]
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleOne] | objTwo in s.fields[objOne, roleTwo] or canLink[s, objOne, roleOne, objTwo]
}

pred dependence(s: State, sourceObj: Obj, sourceAtt: FName, p: seq FName, targetAtt: FName) {
	(sourceAtt = none and targetAtt = none) implies {
		all oS: sourceObj <: s.objects | !no reach[s, oS, p]
	} else (sourceAtt = none) implies {
		all oS: sourceObj <: s.objects | DefVal in s.fields[reach[s, oS, p], targetAtt] 
	} else (targetAtt = none) implies {
		all oS: sourceObj <: s.objects | (s.fields[oS, sourceAtt] = DefVal) implies !no reach[s, oS, p]
	} else (p = none -> none) implies {
		all oS: sourceObj <: s.objects | (s.fields[oS, sourceAtt] = DefVal) implies DefVal in s.fields[oS, targetAtt]
	} else {
		all oS: sourceObj <: s.objects | (s.fields[oS, sourceAtt] = DefVal) implies DefVal in s.fields[reach[s, oS, p], targetAtt] 
	}	
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

pred linkObj(s, s': State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName) {
	objSource in s.objects
	objTarget in s.objects
	objTarget !in s.fields[objSource, roleTarget]

	canLink [s, objSource, roleSource, objTarget]

	s'.objects = s.objects

	s'.fields = s.fields + (objSource -> roleTarget -> objTarget)

	noFieldChangeExcept[s, s', objSource -> roleTarget]
}

pred skip(s, s': State) {
	s'.objects = s.objects
	s'.fields = s.fields
}

run {}
