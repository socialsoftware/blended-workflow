module filesystem/Examples/OneToMany/ActivityModel

open filesystem/ActivityModel
open filesystem/Examples/OneToMany/StateModel

sig DefEntityADefEntityB extends Activity {}
	{
	//	defEnts = none
		some a: EntityA, b: EntityB | defEnts = a + b and #(EntityA <: defEnts) = 1 and #(EntityB <: defEnts) = 1
		defAtts = none -> none
		defMuls = EntityA <: defEnts -> entityA_entityB -> EntityB <: defEnts + EntityB <: defEnts -> entityB_entityA -> EntityA <: defEnts
	}
