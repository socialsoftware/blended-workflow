module filesystem/SecureDataModel

open filesystem/DataModel


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
	dA_att: FName,
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

pred noChangeInAccessControlandOwned(s,s' :SecureState){
	noChangeInAccessControl[s, s']
	s'.u_owned = s.u_owned
}

/**
* Secure Object Definition
**/

pred secureDefObj(s, s' : SecureState, o: Obj, usr:User) {
	usr in s.users
	
	hasDefObjPermissions[s, o, usr]
	defObj[s,s',o]
	addObjToLog[s,s',o,usr]

	s'.u_owned = s.u_owned + usr -> o

	noChangeInAccessControl[s, s']
}

//Permissions to define an object
pred hasDefObjPermissions(s:SecureState,o:Obj, usr:User){
	let usr_perm =  (usr.(s.u_roles)).(s.r_permissions)
		{o in usr_perm.Write}
}

//add Object to log
pred addObjToLog(s,s': SecureState, o:Obj, usr:User){
	s'.log= s.log.add[defObj] 
	s'.log.last.dO_obj = o
	s'.log.last.dO_usr = usr
}

/**
* Secure Attribute Definition
**/
pred secureDefAtt(s, s': State, o: Obj, att: FName, usr:User) {}

//Permissions to define an attribute
pred hasDefAttPermissions(s: State, o: Obj, att: FName, usr:User){
	let usr_perm = (usr.(s.u_roles)).(s.r_permissions){
		//Write or Read permission or own the Object
		(o in usr_perm.Operation or o in usr.(s.u_owned))
		//Write permission on the attribute
		att in usr_perm.Write
	}
}

//Add attribute to Log
pred addAttToLog(s, s': State, o: Obj, att: FName, usr:User) {
	s'.log= s.log.add[defAtt] 
	s'.log.last.dA_att = att
	s'.log.last.dA_obj = o
	s'.log.last.dA_usr = usr
}

/**
* Secure Link Objects Definition
**/

pred secureLinkObj(s, s': State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User) {
	usr in s.users

	hasLinkObjPermissions[s, objSource, roleSource, objTarget, roleTarget,usr]
	linkObj[s, s', objSource, roleSource, objTarget, roleTarget]
	addLinkToLog[s, s', objSource, roleSource, objTarget, roleTarget,usr]

	noChangeInAccessControlandOwned[s, s']
}

//Permissions to link an object
pred hasLinkObjPermissions(s: State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User){
		let usr_perm = (usr.(s.u_roles)).(s.r_permissions){
		//Write permission or own the source
		(objSource in usr_perm.Write or objSource in usr.(s.u_owned))
		//Write or Read permission or own the target
		(objSource in usr_perm.Operation or objSource in usr.(s.u_owned))
		}
}

pred addLinkToLog(s, s': State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User) {
	s'.log = s.log.add[linkObj] 
	s'.log.last.lO_objSource = objSource
	s'.log.last.lO_roleSource = roleSource
	s'.log.last.lO_objTarget = objTarget
	s'.log.last.lO_roleTarget = roleTarget
	s'.log.last.lO_usr = usr
}

pred hasPathObjPermissions(s: SecureState, obj: set Obj, path: seq FName, usr: User){
	all o: reach[s, obj, path] |	
		let usr_perm = (usr.(s.u_roles)).(s.r_permissions){
			//Read permission or own the Object
			(o in usr_perm.Read or o in usr.(s.u_owned))
		}
}

pred NoObjectDefinitionWithoutPermission(s: SecureState, obj: Obj){
	all do: (defObj <: Int.(s.log)) <: dO_obj.obj 
		| hasDefObjPermissions[s, obj, do.dO_usr]

}

pred NoAttributeDefinitionWithoutPermission(s: SecureState, obj: Obj, att: FName){
	all dA1: (defAtt <: Int.(s.log)) <: dA_obj.obj, dA2: dA1 <: dA_att.att 
			| hasDefAttPermissions[s, obj, att, dA2.dA_usr]
}

pred NoSecureLinkWithoutPermission(s:SecureState,  objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName){
	all lO1: (linkObj <: Int.(s.log)) <: lO_objSource.objSource, lO2: lO1 <: lO_roleSource.roleSource, lO3: lO2 <: lO_objTarget.objTarget, lO4: lO3 <: lO_roleTarget.roleTarget
					| hasLinkObjPermissions[s, objSource, roleSource, objTarget, roleTarget, lO4.lO_usr]
		
}









run {}

