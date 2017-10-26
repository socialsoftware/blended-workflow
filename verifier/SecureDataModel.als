module filesystem/SecureDataModel

open filesystem/DataModel

abstract sig User{

}

abstract sig Role{}

abstract sig Operation{}

one sig Def, Read extends Operation{}

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



sig activityTransition extends Transition{
	act_usr: User,
	act_PreDefObj: set Obj,
	act_PreDefAtt: set Obj -> FName,
	
	act_DepSourceObj: Obj,
	act_DepSourceAtt: FName,
	act_DepPath: seq FName,
	act_DepTargetAtt: FName,

	act_PostDefObj: set Obj,
	act_PostDefAtt: set Obj -> FName,
	act_PostLinkObj: set Obj -> FName -> Obj
}
sig SecureState extends State{
	//Log of the operations made in each transition
	log: seq Transition,

	//Access control specifications
	users: set User,
	roles: set Role,
	u_roles: users -> set roles,
	r_DefPermissions: roles -> set({Obj + FName}),
	r_ReadPermissions: roles -> set({Obj + FName}),
	
	
} 

//The DataModel state remains the same
pred noChangeInState(s,s' :SecureState){
	s'.objects = s.objects
	s'.fields = s.fields
}

pred noChangeInAccessControl(s, s' :SecureState){
	s'.users = s.users
	s'.roles = s.roles
	s'.u_roles = s.u_roles
	s'.r_DefPermissions = s.r_DefPermissions
	s'.r_ReadPermissions = s.r_ReadPermissions
}

pred noChangeInAccessControlExceptRolePermissions(s, s' :SecureState){
	s'.users = s.users
	s'.roles = s.roles
	s'.u_roles = s.u_roles
}

// gives all roles of a user
fun userRoles(s: SecureState, usr: User): set Role{
	(usr.(s.u_roles))
}

// gives all the tuples (Role, Object) of the def Permissions
fun RoleObjectWithDefPermission(s: SecureState): set Role -> Obj {
	 ( s.r_DefPermissions) :> Obj
}

// gives all the tuples (Role, FName) of the def Permissions
fun RoleAttributeWithDefPermission(s: SecureState): set Role -> FName {
	( s.r_DefPermissions) :> FName
}

// gives all the tuples (Role, Object) of the read Permissions
fun RoleObjectWithReadPermission(s: SecureState): set Role -> Obj {
	( s.r_ReadPermissions) :> Obj 
}

// gives all the tuples (Role, FName) of the read Permissions
fun RoleAttributeWithReadPermission(s: SecureState): set Role -> FName {
	( s.r_ReadPermissions) :> FName
}

//User has permission to create an instance of an object
pred UserHasObjDefPermissions(s: SecureState, o: Obj, usr: User){
	o in (userRoles[s, usr]).(RoleObjectWithDefPermission[s])
}

//User has permission to read an object
pred UserHasObjReadPermissions(s: SecureState, o: Obj, usr: User){
	o in (userRoles[s, usr]).(RoleObjectWithReadPermission[s])
}


//User has permission to define on an attribute
pred UserHasAttDefPermissions(s: SecureState, att: FName, usr: User){
	att in (userRoles[s, usr]).(RoleAttributeWithDefPermission[s])
}


//User has permission to read on an attribute
pred UserHasAttReadPermissions(s: SecureState, att: FName, usr: User){
		att in (userRoles[s, usr]).(RoleAttributeWithReadPermission[s])
}

/**
* Secure Object Definition
**/

pred secureDefObj(s, s' : SecureState, o: Obj, usr:User) {
	usr in s.users
	hasDefObjPermissions[s, o, usr]
	defObj[s, s', o]
	addObjToLog[s, s', o, usr]
}

//Permissions to define an object
pred hasDefObjPermissions(s: SecureState, o: Obj, usr: User){
	UserHasObjDefPermissions[s, o, usr]
}

//add Object to log
pred addObjToLog (s, s': SecureState, o: Obj, usr: User){
	s'.log = s.log.add[defObj] 
	s'.log.last.dO_obj = o
	s'.log.last.dO_usr = usr
}

/**
* Secure Attribute Definition
**/

pred secureDefAtt(s, s': SecureState, o: Obj, att: FName, usr:User) {
	usr in s.users
	hasDefAttPermissions[s, o, att, usr]
	defAtt[s, s', o, att]
	addAttToLog[s, s', o, att, usr]
}

//Permissions to define an attribute
pred hasDefAttPermissions(s: SecureState, o: Obj, att: FName, usr:User){
	UserHasAttDefPermissions[s, att, usr]
	UserHasObjReadPermissions[s, o, usr]
}

//Add attribute to Log
pred addAttToLog(s, s': SecureState, o: Obj, att: FName, usr:User) {
	s'.log = s.log.add[defAtt] 
	s'.log.last.dA_obj = o
	s'.log.last.dA_att = att
	s'.log.last.dA_usr = usr
}



/**
* Secure Link Objects Definition
**/

pred secureLinkObj(s, s': SecureState, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User) {
	usr in s.users
	hasLinkObjPermissions[s, objSource, roleSource, objTarget,usr]
	linkObj[s, s', objSource, roleSource, objTarget, roleTarget]
	addLinkToLog[s, s', objSource, roleSource, objTarget, roleTarget,usr]
}

//Permissions to link an object
pred hasLinkObjPermissions(s: SecureState, objSource: Obj, roleSource: FName, objTarget: Obj, usr:User){
	UserHasObjReadPermissions[s, objSource, usr]
	UserHasAttDefPermissions[s, roleSource, usr]
	UserHasObjReadPermissions[s, objTarget, usr]
}

pred addLinkToLog(s, s': SecureState, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User){
	s'.log = s.log.add[linkObj] 
	s'.log.last.lO_objSource = objSource
	s'.log.last.lO_roleSource = roleSource
	s'.log.last.lO_objTarget = objTarget
	s'.log.last.lO_roleTarget = roleTarget
}

pred hasPathObjReadPermissions(s: SecureState, obj: set Obj, path: seq FName, usr: User){
	all o: reach[s, obj, path] | UserHasObjReadPermissions[s, o, usr] 
}

pred NoObjectDefinitionWithoutPermission(s: SecureState, obj: Obj){
	all do: (defObj <: Int.(s.log)) <: dO_obj.obj 
		| hasDefObjPermissions[s, obj, do.dO_usr]
}

pred NoAttributeDefinitionWithoutPermission(s: SecureState, obj: Obj, att: FName){
	all dA1: (defAtt <: Int.(s.log)) <: dA_obj.obj, 
		dA2: dA1 <: dA_att.att 
			| hasDefAttPermissions[s, obj, att, dA2.dA_usr]
}

pred NoSecureLinkWithoutPermission(s:SecureState,  objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName){
	all lO1: (linkObj <: Int.(s.log)) <: lO_objSource.objSource,
		lO2: lO1 <: lO_roleSource.roleSource, 
			lO3: lO2 <: lO_objTarget.objTarget, 
				lO4: lO3 <: lO_roleTarget.roleTarget
					| hasLinkObjPermissions[s, objSource, roleSource, objTarget, lO4.lO_usr]
		
}

/**
*	BASE MODEL
*	
*	In this model the definition of the objects and attributes is made based on the AC defined in the design time,
*	 and the operations does not modify the AC
**/ 

pred baseSecureDefObj(s, s' : SecureState, o: Obj, usr:User){
	secureDefObj[s, s', o, usr]
	noChangeInAccessControl[s, s']
}

pred baseSecureDefAtt(s, s': SecureState, o: Obj, att: FName, usr:User) {
	secureDefAtt[s, s', o, att, usr]
	noChangeInAccessControl[s, s']
}

pred baseSecureLinkObj(s, s': SecureState, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User) {
	secureLinkObj[s, s', objSource, roleSource, objTarget, roleTarget, usr]
	noChangeInAccessControl[s, s']	
}
/**
*	Model enhancement #1
*
*	in this enhancement the user that executes an operation, automatically recieve the permission to read the instance that he creates 
**/


//Give read permission to all roles of a user that have def permission to that object
pred giveObjectReadPermission(s, s': SecureState, o: Obj, usr: User){
	all role: userRoles[s, usr] <: (RoleObjectWithDefPermission[s]).o |
		 role.(s'.r_ReadPermissions) = role.(s.r_ReadPermissions) + {o}
}

//Give read permission to all roles of a user that have def permission to that attribute
pred giveAttributeReadPermission(s, s': SecureState, att: FName, usr: User){
	all role: userRoles[s, usr] <: (RoleAttributeWithDefPermission[s]).att |
		role.(s'.r_ReadPermissions) = role.(s.r_ReadPermissions) + {att}
}

// The user that defines the object gains read permission over that object
pred secureDefObjGivesReadPermission(s, s' : SecureState, o: Obj, usr:User){
	secureDefObj[s, s', o, usr]
	giveObjectReadPermission[s, s', o, usr]
	noChangeInAccessControlExceptRolePermissions[s, s']
}

// The user that defines the Attribute gains read permission over that attribute
pred secureDefAttributeGivesReadPermission(s, s': SecureState, o: Obj, att: FName, usr:User){
	secureDefAtt[s, s', o, att, usr]
	giveAttributeReadPermission[s, s', att, usr]
	noChangeInAccessControlExceptRolePermissions[s, s']
}

// The user that links objects gains read permission over the attribute of the source obj
pred secureLinkObjGivesReadPermission(s, s': SecureState, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User){
	secureLinkObj[s, s', objSource, roleSource, objTarget, roleTarget, usr]
	giveAttributeReadPermission[s, s', roleSource, usr]
	noChangeInAccessControlExceptRolePermissions[s, s']
}



run {}

