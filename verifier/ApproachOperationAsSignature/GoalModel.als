module filesystem/GoalModel

open filesystem/StateModel

abstract sig Goal {}

abstract sig GoalProduce extends Goal {
	defEnts: set Entity, 
	defAtts: set Entity -> Attribute
}

abstract sig GoalAssociate extends Goal {
	defMuls: set Entity -> Attribute -> Entity
}

run {}

