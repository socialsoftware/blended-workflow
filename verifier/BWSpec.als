module filesystem/BWSpec

open util/ordering [AbstractState]

abstract sig Obj {}

abstract sig FName {
	minMul: Int,
	maxMul: Int,
	inverse: FName
}

abstract sig Val {}

one sig DefVal extends Val {}

abstract sig Dependence {
	sourceObj: Obj,
	sourceAtt: FName, 
	sequence: seq FName, 
	targetAtt: FName
}

abstract sig AbstractState {
	objects: set Obj,
	fields: objects -> ( FName ->set { Obj + Val } ) ,
}

fun reach (s: AbstractState, obj: set Obj, path: seq FName): set Obj {
	(#path = 1) implies {
		s.fields[obj, path.first]
	} else {
		reach[s, s.fields[obj, path.first], path.rest]
	}
}

fun atts (s: AbstractState, o: Obj): set FName { s.fields[o].{Obj + Val} }

fun commitedAssociatedObjects (s: AbstractState, objSource: Obj, roleSource: FName): set Obj { 
	{o: Obj | s.fields[o, roleSource] = objSource} + 
	{o: Obj | s.fields[objSource, roleSource.inverse] = o} 
}

pred canLink(s: AbstractState, objSource: Obj, roleSource: FName, objTarget: Obj) {
	// source is not completely commited yet in the number of targets
	let commitedTargetObjects = commitedAssociatedObjects[s, objSource, roleSource] | 
		#commitedTargetObjects < roleSource.inverse.maxMul or objTarget in commitedTargetObjects 
	// target is not completely commited yet in the number of sources
	let commitedSourceObjects = commitedAssociatedObjects[s, objTarget, roleSource.inverse] |
		#commitedSourceObjects < roleSource.maxMul or objSource in commitedSourceObjects
}

pred noFieldChangeExcept(s, s': AbstractState, asg: set Obj ->FName) {
	all obj: s.objects - asg.FName | obj.(s'.fields) = obj.(s.fields)
	all o: asg.FName | all field: atts[s, o] - o.asg | s'.fields[o, field] = s.fields[o, field]
}

pred noExtraFields(s: AbstractState, objs: set Obj, fNames: set FName) {
	
	all obj: objs <: s.objects |no obj.(s.fields)[ FName - fNames ]
}

pred attributesDefined(s: AbstractState, objs: set Obj, atts: set FName) {
	all obj: objs <: s.objects | all att: atts | s.fields[obj, att] = DefVal
}

pred multiplicityRule(s: AbstractState, objs: set Obj, role: FName) {
	all obj: objs <: s.objects | #s.fields[obj, role] >= role.minMul and #s.fields[obj, role] <= role.maxMul
}

pred noMultiplicityExceed(s: AbstractState, objs: set Obj, role: FName) {
	all obj: objs <: s.objects | #s.fields[obj, role] <= role.maxMul
}

pred bidirectionalRule(s: AbstractState, objsOne: set Obj, roleOne: FName, objsTwo: set Obj, roleTwo: FName) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleTwo] | objOne in s.fields[objTwo, roleOne]
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleOne] | objTwo in s.fields[objOne, roleTwo]
}

pred bidirectionalPreservation(s: AbstractState, objsOne: set Obj, roleOne: FName, objsTwo: set Obj, roleTwo: FName) {
	all objOne: objsOne <: s.objects | all objTwo: s.fields[objOne, roleTwo] | objOne in s.fields[objTwo, roleOne] or canLink[s, objTwo, roleTwo, objOne]
	all objTwo: objsTwo <: s.objects | all objOne: s.fields[objTwo, roleOne] | objTwo in s.fields[objOne, roleTwo] or canLink[s, objOne, roleOne, objTwo]
}

pred checkDependence(s: AbstractState, sourceObj: Obj, dependence: Dependence) {
	(dependence.sourceAtt = none and dependence.targetAtt = none) implies {
		all oS: sourceObj <: s.objects | !no reach[s, oS, dependence.sequence]
	} else (dependence.sourceAtt = none) implies {
		all oS: sourceObj <: s.objects | DefVal in s.fields[reach[s, oS, dependence.sequence], dependence.targetAtt] 
	} else (dependence.targetAtt = none) implies {
		all oS: sourceObj <: s.objects | (s.fields[oS, dependence.sourceAtt] = DefVal) implies !no reach[s, oS, dependence.sequence]
	} else (dependence.sequence = none -> none) implies {
		all oS: sourceObj <: s.objects | (s.fields[oS, dependence.sourceAtt] = DefVal) implies DefVal in s.fields[oS, dependence.targetAtt]
	} else {
		all oS: sourceObj <: s.objects | (s.fields[oS, dependence.sourceAtt] = DefVal) implies DefVal in s.fields[reach[s, oS, dependence.sequence], dependence.targetAtt] 
	}	
}
