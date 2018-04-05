module filesystem/Examples/OneToMany/DataModel

open filesystem/DataModel

sig EntityA extends Entity {}
one sig entityA_attOne extends Attribute {} 
one sig entityA_entityB extends Attribute {} 
	{
		minMul = 0
		maxMul = 10
		inverse = entityB_entityA
	}

sig EntityB extends Entity {}
one sig entityB_attOne extends Attribute {} 
one sig entityB_entityA extends Attribute {} 
	{
		minMul = 1
		maxMul = 1
		inverse = entityA_entityB
	}

one sig entityA_attOne_entityB_attOne_dependence extends Dependence {}
	{
		sourceEntity = EntityA
		sourceAttribute = entityA_attOne
		path =0 -> entityA_entityB
		targetAttribute = entityB_attOne
	}


run {}
