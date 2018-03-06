module filesystem/Examples/OneToMany/DataModel

open filesystem/DataModel

sig EntityA extends Obj {}
one sig entityA_attOne extends FName {} 
one sig entityA_attTwo extends FName {} 
one sig entityA_entityB extends FName {} 
	{
		minMul = 0
		maxMul = 10
		inverse = entityB_entityA
	}

sig EntityB extends Obj {}
one sig entityB_attOne extends FName {} 
one sig entityB_entityA extends FName {} 
	{
		minMul = 1
		maxMul = 1
		inverse = entityA_entityB
	}

one sig entityA_attOne_entityB_attOne_dependence extends Dependence {}
	{
		sourceObj = EntityA
		sourceAtt = entityA_attOne
		sequence =0 -> entityA_entityB
		targetAtt = entityB_attOne
	}

one sig entityB_entityA_dependence extends Dependence {}
	{
		sourceObj = EntityB
	//	sourceAtt = none
		sequence = 0 -> entityB_entityA
		targetAtt = entityA_attTwo
	}
