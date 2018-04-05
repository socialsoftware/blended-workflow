module filesystem/ActivityModelExec

open filesystem/ActivityModel

pred init (s: State) {
	no s.entities
	no s.attributes
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some a: Activity | 
		exec[s, s', a]
}

pred exec(s, s': State, act: Activity) {
	// entities to be defined are not defined yet
	(act.defEnts != none) implies {
		act.defEnts !in s.entities
	}

	// entities of attributes to be defined eiher are already defined or are going to be defined
	all ent: act.defAtts.Attribute | ent in s.entities + act.defEnts

	// attributes to be defined are not defined yet
	all ent: act.defAtts.Attribute, att: ent.(act.defAtts) | DefVal !in s.attributes[ent, att] // no defAttribute [s, ent, att]

	// entities of multiplicity conditions eiher are already defined or are going to be defined
	all sourceEnt: (act.defMuls.Entity).Attribute | sourceEnt in s.entities + act.defEnts
	all targetEnt: Attribute.(Entity.(act.defMuls)) | targetEnt in s.entities + act.defEnts

	// multiplicity conditions
	all sourceEnt: (act.defMuls.Entity).Attribute, targetEnt: Attribute.(sourceEnt.(act.defMuls)), targetAtt: sourceEnt.(act.defMuls).targetEnt |
		noMultiplicityExceeded[s', sourceEnt, targetAtt] and noMultiplicityExceeded[s', targetEnt, targetAtt.inverse] and
		bidirectional[s', sourceEnt, targetAtt.inverse, targetEnt]

	// dependencies shoud hold in the resulting state
	all ent: act.defEnts, dep: Dependence | ent in dep.sourceEntity implies dependence[s', ent, dep]
	all ent: (act.defAtts).Attribute, dep: Dependence | ent in dep.sourceEntity and dep.sourceAttribute in ent.(act.defAtts) implies dependence[s', ent, dep]

	// change state
	s'.entities = s.entities + act.defEnts
	s'.attributes = s.attributes + act.defAtts  -> DefVal + act.defMuls
	noAttributesChangedExcept[s, s', act.defAtts + (act.defMuls).Entity]
}
