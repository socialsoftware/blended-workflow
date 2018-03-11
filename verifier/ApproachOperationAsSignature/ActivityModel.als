module filesystem/ActivityModel

open filesystem/StateModel

abstract sig Activity {
	defEnts: set Entity, 
	defAtts: set Entity -> Attribute, 
	defMuls: set Entity -> Attribute -> Entity
}
