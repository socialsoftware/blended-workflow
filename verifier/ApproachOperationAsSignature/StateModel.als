module filesystem/StateModel

open util/ordering [State]

open filesystem/DataModel

// state
sig State {
	entities: set Entity,
	attributes: entities -> ( Attribute ->set { Entity + Val } ) 
}

abstract sig Val {}

one sig DefVal extends Val {}

// auxiliary functions
fun reach (s: State, ent: set Entity, path: seq Attribute): set Entity {
	(#path = 1) implies {
		s.attributes[ent, path.first]
	} else {
		reach[s, s.attributes[ent, path.first], path.rest]
	}
}

fun atts (s: State, ent: Entity): set Attribute { s.attributes[ent].{Entity + Val} }

// def entity
pred defEntity(s: State, ent: set Entity) {
	# ent <: s.entities = 1
}

// def attribute
pred defAttribute(s: State, ent: Entity, atts: set Attribute) {
	all defEnt: ent <: s.entities, att: atts | s.attributes[defEnt, att] = DefVal
}

pred noExtraFields(s: State, ents: set Entity, atts: set Attribute) {
	all entity: ents <: s.entities |no entity.(s.attributes)[ Attribute - atts ]
}

// multiplicity rules
pred multiplicity(s: State, ents: set Entity, att: Attribute) {
	all ent: ents <: s.entities | #s.attributes[ent, att] >= att.minMul and #s.attributes[ent, att] <= att.maxMul
}

pred noMultiplicityExceeded(s: State, ents: set Entity, att: Attribute) {
	all ent: ents <: s.entities | #s.attributes[ent, att] <= att.maxMul
}

pred bidirectional(s: State, sourceEnts: set Entity, sourceAtt: Attribute, targetEnts: set Entity) {
	all sourceEnt: sourceEnts <: s.entities, targetEnt: s.attributes[sourceEnt, sourceAtt] | sourceEnt in s.attributes[targetEnt, sourceAtt.inverse]
	all targetEnt: targetEnts <: s.entities, sourceEnt: s.attributes[targetEnt, sourceAtt.inverse] | targetEnt in s.attributes[sourceEnt, sourceAtt]
}

// dependencies rules
pred dependence(s: State, sourceEnt: Entity, dependence: Dependence) {
	(dependence.path = none -> none) implies {
		all e: sourceEnt <: s.entities | (s.attributes[e, dependence.sourceAttribute] = DefVal) implies DefVal in s.attributes[e, dependence.targetAttribute]
	} else {
		all e: sourceEnt <: s.entities | (s.attributes[e, dependence.sourceAttribute] = DefVal) implies DefVal in s.attributes[reach[s, e, dependence.path], dependence.targetAttribute] 
	}	
}

pred noAttributesChangedExcept(s, s': State, assignment: set Entity -> Attribute) {
	all ent: s.entities - assignment.Attribute | ent.(s'.attributes) = ent.(s.attributes)
	all ent: assignment.Attribute, att: atts[s, ent] - ent.assignment | s'.attributes[ent, att] = s.attributes[ent, att]
}

run {}
