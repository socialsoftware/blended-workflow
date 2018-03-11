module filesystem/Examples/OneToMany/StateModel

open filesystem/StateModel
open filesystem/Examples/OneToMany/DataModel

pred final {
	one s: State |
		defEntity[s, EntityA] and

		// EntityOne
		defAttribute [s, EntityA, entityA_attOne + entityA_attTwo] and
		noExtraFields [s, EntityA, entityA_attOne + entityA_attTwo + entityA_entityB] and

		// EntityTwo
		defAttribute [s, EntityB, entityB_attOne] and
		noExtraFields [s, EntityB, entityB_attOne + entityB_entityA] and

		dependence [s, EntityA, entityA_attOne_entityB_attOne_dependence] and
		dependence [s, EntityB, entityB_entityA_dependence] and

		multiplicity [s, EntityA, entityA_entityB] and
		multiplicity [s, EntityB, entityB_entityA] and
		bidirectional [s, EntityA, entityA_entityB, EntityB]
}


pred Invariants(s: State) {
	// only the correct fields are associated to the entities
	noExtraFields [s, EntityA, entityA_attOne + entityA_attTwo + entityA_entityB] 
	noExtraFields [s, EntityB, entityB_attOne + entityB_entityA]	 

	// does not exceeds mutliplicity
	noMultiplicityExceeded [s, EntityA, entityA_entityB] 
	noMultiplicityExceeded [s, EntityB, entityB_entityA] 

	// bidirectional relation
	bidirectional [s, EntityA, entityA_entityB, EntityB]
 
	// dpendences should be verified
	dependence [s, EntityA, entityA_attOne_entityB_attOne_dependence]
	dependence [s, EntityB, entityB_entityA_dependence]
}
