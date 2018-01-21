module filesystem/DataConditions

open filesystem/BWSpec

pred defObj(s, s' : AbstractState, o: Obj) {

	o !in s.objects

	s'.objects = s.objects + o
	s'.fields = s.fields

	all dep: Dependence | o in dep.sourceObj implies checkDependence[ s', o, dep]
}

pred defAtt(s, s': AbstractState, o: Obj, att: FName) {
	o in s.objects
	no s.fields[o, att] 
	s'.objects = s.objects

	s'.fields = s.fields + (o -> att -> DefVal)

	noFieldChangeExcept[s, s', o -> att]

	all dep: Dependence | o in dep.sourceObj and att in dep.sourceAtt implies checkDependence[ s', o, dep]
}

pred linkObj(s, s': AbstractState, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName) {
	objSource in s.objects
	objTarget in s.objects
	objTarget !in s.fields[objSource, roleTarget]

	canLink [s, objSource, roleSource, objTarget]

	s'.objects = s.objects

	s'.fields = s.fields + (objSource -> roleTarget -> objTarget)

	noFieldChangeExcept[s, s', objSource -> roleTarget]
}

pred skip(s, s': AbstractState) {
	s'.objects = s.objects
	s'.fields = s.fields
}

run {}
