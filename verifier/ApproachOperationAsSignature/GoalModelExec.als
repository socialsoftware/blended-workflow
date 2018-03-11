module filesystem/GoalModelExec

open filesystem/GoalModel

pred init (s: State) {
	no s.entities
	no s.attributes
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some gp: GoalProduce, ga: GoalAssociate | 
		execProduce[s, s', gp] or
		execAssociate[s, s', ga]
}

pred execProduce(s, s': State, goal: GoalProduce) {
	(goal.defEnts  != none) implies {
		// entities are not defined
		goal.defEnts !in s.entities
	}

	// entities where attributes are going to be defined are defined
	all ent: goal.defAtts.Attribute | ent in s.entities + goal.defEnts

	// attributes are not defined
	all ent: goal.defAtts.Attribute, att: ent.(goal.defAtts) | DefVal !in s.attributes[ent, att]

	// entities are defined
	s'.entities = s.entities + goal.defEnts
			
	// attributes are defined
	s'.attributes = s.attributes + goal.defAtts  -> DefVal 

	// no further changes
	noAttributesChangedExcept[s, s', goal.defAtts]

	// dependencies hold
	all ent: goal.defEnts, dep: Dependence | ent in dep.sourceEntity implies dependence[s', ent, dep]	
	all ent: (goal.defAtts).Attribute, dep: Dependence | ent in dep.sourceEntity and dep.sourceAttribute in ent.(goal.defAtts) implies dependence[s', ent, dep]
}


pred execAssociate(s, s': State, goal: Goal) {
	// entities in the association to be defined are defined
	all sourceEnt: (goal.defMuls.Entity).Attribute | sourceEnt in s.entities
	all targetEnt: Attribute.(Entity.(goal.defMuls)) | targetEnt in s.entities

	// the association does not exist
	all sourceEnt: (goal.defMuls.Entity).Attribute, targetEnt: Attribute.(sourceEnt.(goal.defMuls)), targetAtt: sourceEnt.(goal.defMuls).targetEnt | targetEnt !in s.attributes[sourceEnt, targetAtt]

	// multiplicity conditions
	all sourceEnt: (goal.defMuls.Entity).Attribute, targetEnt: Attribute.(sourceEnt.(goal.defMuls)), targetAtt: sourceEnt.(goal.defMuls).targetEnt |
		noMultiplicityExceeded[s', sourceEnt, targetAtt] and	noMultiplicityExceeded[s', targetEnt, targetAtt.inverse] and
		bidirectional[s', sourceEnt, targetAtt.inverse, targetEnt]

	// attributes are defined
	s'.attributes = s.attributes + goal.defMuls

	// no further changes
	s'.entities = s.entities
	noAttributesChangedExcept[s, s', goal.defMuls.Entity]
}
