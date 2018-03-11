module filesystem/DataModel

abstract sig Entity {}

abstract sig Attribute {
	minMul: Int,
	maxMul: Int,
	inverse: Attribute
}

abstract sig Dependence {
	sourceEntity: Entity,
	sourceAttribute: Attribute, 
	path: seq Attribute, 
	targetAttribute: Attribute
}
