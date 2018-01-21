module filesystem/BWSecureSpec

open filesystem/BWSpec


/**
* ACCESS CONTROl SPECIFIC SIGNATURES
**/
abstract sig User{
	usr_obj: set Obj
}

abstract sig Role{}

abstract sig Rights{}

one sig Def, Read extends Rights{}

abstract sig Transition{}

one sig AccessControlRules {
	users: set User,
	roles: set Role,
	u_roles: users -> set roles,
	resources: set {Obj + FName},
	role_permissions: Rights ->  roles ->  resources,
}

abstract sig AbstractSecureState extends AbstractState {
	//Log of the operations made in each transition
	log: seq Transition
}

/**
*RIGHTS OVER RESOURCES
**/

//User has right to create an instance of an object
pred UserHasObjDefRight(s: AbstractSecureState, o: Obj, usr: User){
	!no Def.(AccessControlRules.role_permissions).o
	Def.(AccessControlRules.role_permissions).o in usr.(AccessControlRules.u_roles)
}

//User has right to read an object
pred UserHasObjReadRight(s: AbstractSecureState, o: Obj, usr: User){
	!no Read.(AccessControlRules.role_permissions).o
	Read.(AccessControlRules.role_permissions).o in usr.(AccessControlRules.u_roles)
}


//User has right to define on an attribute
pred UserHasAttDefRight(s: AbstractSecureState, att: FName, usr: User){
	!no Def.(AccessControlRules.role_permissions).att
	Def.(AccessControlRules.role_permissions).att in usr.(AccessControlRules.u_roles)
}

//User has right to read on an attribute
pred UserHasAttReadRight(s: AbstractSecureState, att: FName, usr: User){
	!no Read.(AccessControlRules.role_permissions).att
	Read.(AccessControlRules.role_permissions).att in usr.(AccessControlRules.u_roles)
}

/**
*Predicates that verifies all rights needed to define each resource
**/

//Permissions to define an object
pred hasDefObjPermissions(s: AbstractSecureState, o: Obj, usr: User){
	UserHasObjDefRight[s, o, usr]
}

//Permissions to define an attribute
pred hasDefAttPermissions(s: AbstractSecureState, o: Obj, att: FName, usr:User){
	UserHasAttDefRight[s, att, usr]
	UserHasObjReadRight[s, o, usr]
}

//Permissions to link an object
pred hasLinkObjPermissions(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	UserHasObjReadRight[s, objSource, usr]
	UserHasAttDefRight[s, attSource, usr]
	UserHasAttDefRight[s, attSource.inverse, usr]
	UserHasObjReadRight[s, objTarget, usr]
}





 
