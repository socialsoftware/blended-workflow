module filesystem/Examples/OneToMany/GoalModel

open filesystem/GoalModel
open filesystem/Examples/OneToMany/StateModel

sig DefEntityA extends GoalProduce {}
	{
		one a: EntityA | defEnts = a
		defAtts = none -> none
	}

sig DefEntityB extends GoalProduce {}
	{
		one b: EntityB | defEnts = b
		defAtts = none -> none
	}

sig AssociateEntityAEntityB extends GoalAssociate {}
	{
		one a: EntityA, b: EntityB | defMuls = a -> entityA_entityB -> b + b -> entityB_entityA -> a
	}

