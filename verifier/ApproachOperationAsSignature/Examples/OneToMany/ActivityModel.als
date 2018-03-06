module filesystem/Examples/OneToMany/ActivityModel

open filesystem/ActivityModel
open filesystem/Examples/OneToMany/StateModel

sig DefEntityA extends Activity {}
	{
		one a: EntityA | defEnts = a
		defAtts = defEnts -> entityA_attTwo
		defMuls = none -> none -> none
	}

sig DefEntityAAttOne extends Activity {}
	{
   		defEnts = none
		one a: EntityA | defAtts = a -> entityA_attOne
		defMuls = none -> none -> none
	}

sig DefEntityB extends Activity {}
	{
		one b: EntityB | defEnts = b
		defAtts = defEnts -> entityB_attOne
		one a: EntityA | defMuls = defEnts -> entityB_entityA -> a + a -> entityA_entityB -> defEnts
	}
