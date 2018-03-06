module filesystem/Examples/OneToMany/GoalModel

open filesystem/GoalModel
open filesystem/Examples/OneToMany/StateModel

sig DefEntityA extends GoalProduce {}
	{
		one a: EntityA | defEnts = a
		defAtts = none -> none
	}

sig DefEntityAAttOne extends GoalProduce {}
	{
		defEnts = none
		one a: EntityA | defAtts = a -> entityA_attOne
	}

sig DefEntityAAttTwo extends GoalProduce {}
	{
		defEnts = none
		one a: EntityA | defAtts = a -> entityA_attTwo
	}

sig DefEntityB extends GoalProduce {}
	{
		one b: EntityB | defEnts = b
		defAtts = none -> none
	}

sig DefEntityBAttOne extends GoalProduce {}
	{
		defEnts = none
		one b: EntityB | defAtts = b -> entityB_attOne
	}

sig LinkEntityAEntityB extends GoalAssociation {}
	{
		one a: EntityA, b: EntityB | defMuls = a -> entityA_entityB -> b + b -> entityB_entityA -> a
	}

