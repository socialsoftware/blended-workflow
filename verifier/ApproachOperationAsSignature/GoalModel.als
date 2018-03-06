module filesystem/GoalModel

open filesystem/StateModel

abstract sig Goal {}

abstract sig GoalProduce extends Goal {
	defEnts: set Obj, 
	defAtts: set Obj -> FName
}

abstract sig GoalAssociation extends Goal {
	defMuls: set Obj -> FName -> Obj
}

pred execProduce(s, s': AbstractState, goal: GoalProduce) {
	(goal.defEnts  != none) implies {
		// entities are not defined
		goal.defEnts !in s.objects
	}

	// entities where attributes are going to be defined are defined
	all obj: goal.defAtts.FName | obj in s.objects + goal.defEnts

	// attributes are not defined
	all obj: goal.defAtts.FName, att: obj.(goal.defAtts) | DefVal !in s.fields[obj, att]

	// entities are defined
	s'.objects = s.objects + goal.defEnts
			
	// attributes are defined
	s'.fields = s.fields + goal.defAtts  -> DefVal 

	// no further changes
	noFieldChangeExcept[s, s', goal.defAtts]

	// dependencies hold
	all o: goal.defEnts, dep: Dependence | o in dep.sourceObj implies dependence[s', o, dep]	
	all o: (goal.defAtts).FName, dep: Dependence | o in dep.sourceObj and dep.sourceAtt in o.(goal.defAtts) implies dependence[s', o, dep]
}


pred execAssociation(s, s': AbstractState, goal: Goal) {
	// entities in the link to be defined are defined
	all entSource: (goal.defMuls.Obj).FName | entSource in s.objects
	all entTarget: FName.(Obj.(goal.defMuls)) | entTarget in s.objects

	// the association does not exist
	all objSource: (goal.defMuls.Obj).FName, objTarget: FName.(objSource.(goal.defMuls)), roleTarget: objSource.(goal.defMuls).objTarget | objTarget !in s.fields[objSource, roleTarget]

	// objects can be linked
	all objSource: (goal.defMuls.Obj).FName, objTarget: FName.(objSource.(goal.defMuls)), roleTarget: objSource.(goal.defMuls).objTarget |
		canLink[s, objSource, roleTarget.inverse, objTarget] 
		//and canLink[s, objTarget, roleTarget, objSource] it is redundant because canLink verifies in both directions

	// attributes are defined
	s'.fields = s.fields + goal.defMuls

	// no further changes
	s'.objects = s.objects
	noFieldChangeExcept[s, s', goal.defMuls.Obj]
}

