module filesystem/Examples/OneToMany/ActivityModel

open filesystem/ActivityModel
open filesystem/Examples/OneToMany/StateModel

sig DefEntityA extends Activity {}
	{
		defEnts = none
	//	one a: EntityA | defEnts = a
		defAtts = none -> none
		defMuls = none -> none -> none
	}

sig DefEntityAAttOne extends Activity {}
	{
 //  		defEnts = none
		one a: EntityA | defEnts = a
		one a: EntityA | defAtts = a -> entityA_attOne
		one b: EntityB | defMuls = defEnts -> entityA_entityB -> b + b -> entityB_entityA -> defEnts
	//	defMuls = none -> none -> none
	}

sig DefEntityB extends Activity {}
	{
		one b: EntityB | defEnts = b
		defAtts = defEnts -> entityB_attOne
	//	one a: EntityA | defMuls = defEnts -> entityB_entityA -> a + a -> entityA_entityB -> defEnts
		defMuls = none -> none -> none
}
