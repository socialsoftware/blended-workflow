module filesystem/DataModel

abstract sig Obj {}

abstract sig FName {
	minMul: Int,
	maxMul: Int,
	inverse: FName
}

abstract sig Val {}

one sig DefVal extends Val {}

abstract sig Dependence {
	sourceObj: Obj,
	sourceAtt: FName, 
	sequence: seq FName, 
	targetAtt: FName
}
