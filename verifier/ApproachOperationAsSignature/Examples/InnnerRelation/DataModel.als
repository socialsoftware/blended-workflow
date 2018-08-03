module filesystem/Examples/OneToMany/DataModel

open filesystem/DataModel

sig EntityA extends Entity {}
one sig entityA_entityB extends Attribute {} 
	{
		minMul = 1
		maxMul = 10
		inverse = entityB_entityA
	}

sig EntityB extends Entity {}
one sig entityB_entityA extends Attribute {} 
	{
		minMul = 1
		maxMul = 1
		inverse = entityA_entityB
	}


run {}
