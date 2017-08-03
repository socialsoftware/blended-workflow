module filesystem/SecureDataModel

open filesystem/DataModel

abstract sig User{}

abstract sig Role{}

abstract sig Operation{}

one sig Write, Read extends Operation{}


sig SecureState extends State{
	//Log of users, roles and permissions
//	log: one Log,

	users: set User,
	roles:set Role,
	u_roles: users -> set roles,
	r_permissions: roles -> set({Obj+FName} -> Operation),
	//User owned Objects
	u_owned:users -> objects,
}

//The DataModel state remains the same
pred noChangeInState(s,s' :SecureState){
	s'.objects = s.objects
	s'.fields = s.fields
}
pred noChangeInAccessControl(s,s' :SecureState){
	s'.users = s.users
	s'.roles = s.roles
	s'.u_roles = s.u_roles
	s'.r_permissions = s.r_permissions
}

pred secureDefObj(s, s' : SecureState, o: Obj, usr:User) {
	usr in s.users
	//User has Write permission over the object
	let usr_perm =  (usr.(s.u_roles)).(s.r_permissions)
		|o in usr_perm.Write
	o !in s.objects
	
	
	s'.objects = s.objects + o
	s'.u_owned = s.u_owned + usr -> o
	s'.fields = s.fields
	noChangeInAccessControl[s, s']
}

pred secureDefAtt(s, s': State, o: Obj, att: FName, usr:User) {
	usr in s.users
	o in s.objects
	no s.fields[o, att] 
	let usr_perm = (usr.(s.u_roles)).(s.r_permissions)|
		(o in usr_perm.Operation or o in usr.(s.u_owned))
		and att in usr_perm.Write
	s'.objects = s.objects
	s'.fields = s.fields + (o -> att -> DefVal)
	noFieldChangeExcept[s, s', o -> att]
}



run {}

