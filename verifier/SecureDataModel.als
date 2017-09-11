module filesystem/SecureDataModel

open filesystem/DataModel
open util/integer

abstract sig User{}

abstract sig Role{}

abstract sig Operation{}

one sig Write, Read extends Operation{}

abstract sig Transition{}

sig defObj extends Transition{
	dO_obj: Obj,
	dO_usr: User
}

sig defAtt extends Transition{
	dA_att:FName,
	dA_obj: Obj,
	dA_usr: User
}

sig linkObj extends Transition{
	lO_objSource: Obj,
	lO_roleSource: FName,
	lO_objTarget: Obj,
	lO_roleTarget: FName,
	lO_usr: User
}

sig SecureState extends State{
	//Log of the operations made in each transition
	log: seq Transition,

	//Access control specifications
	users: set User,
	roles: set Role,
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
	
	hasDefObjPermissions[s,s',o,usr]
	defObj[s,s',o]

	s'.u_owned = s.u_owned + usr -> o
	noChangeInAccessControl[s, s']
}

//Permissions to define an object
pred hasDefObjPermissions(s, s':SecureState,o:Obj, usr:User){
	let usr_perm =  (usr.(s.u_roles)).(s.r_permissions)
		{o in usr_perm.Write}
}

pred secureDefAtt(s, s': State, o: Obj, att: FName, usr:User) {
	usr in s.users
	
	hasDefAttPermissions[s,s',o,att,usr]
	defAtt[s,s',o,att, none]

	noChangeInAccessControl[s, s']
	s'.u_owned = s.u_owned 
}

//Permissions to define an attribute
pred hasDefAttPermissions(s, s': State, o: Obj, att: FName, usr:User){
	let usr_perm = (usr.(s.u_roles)).(s.r_permissions){
		//Write or Read permission or own the Object
		(o in usr_perm.Operation or o in usr.(s.u_owned))
		//Write permission on the attribute
		att in usr_perm.Write
	}
}

pred secureLinkObj(s, s': State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User) {
	usr in s.users

	hasLinkObjPermissions[s, s', objSource, roleSource, objTarget, roleTarget,usr]
	linkObj[s, s', objSource, roleSource, objTarget, roleTarget]

	noChangeInAccessControl[s, s']
}

//Permissions to link an object
pred hasLinkObjPermissions(s, s': State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User){
		let usr_perm = (usr.(s.u_roles)).(s.r_permissions){
		//Write permission or own the source
		(objSource in usr_perm.Write or objSource in usr.(s.u_owned))
		//Write or Read permission or own the target
		(objSource in usr_perm.Operation or objSource in usr.(s.u_owned))
		}
}


run {}

