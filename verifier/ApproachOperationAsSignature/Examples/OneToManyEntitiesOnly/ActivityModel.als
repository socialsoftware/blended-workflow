module filesystem/Examples/OneToMany/ActivityModel

open filesystem/ActivityModel
open filesystem/Examples/OneToMany/StateModel

sig DefEntityA extends Activity {}
	{
		one a: EntityA | defEnts = a
		defAtts = none -> none
		Entity.defMuls.Entity in {entityA_entityB + entityB_entityA}
	//	defMuls = none -> none -> none
	}

sig DefEntityB extends Activity {}
	{
		one b: EntityB | defEnts = b
		defAtts = none -> none
	//	Entity.defMuls.Entity in {entityA_entityB + entityB_entityA}
		defMuls = none -> none -> none
	}
