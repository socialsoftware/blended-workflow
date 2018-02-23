module filesystem/BWSecureSpec

open filesystem/BWSpec


/**
* ACCESS CONTROl SPECIFIC SIGNATURES
**/
abstract sig User extends Subject{
	usr_obj: set Obj
}

abstract sig RoleSubject extends Subject{}

abstract sig Rights{}

one sig Def, Read extends Rights{}

abstract sig Transition{}

one sig AccessControlRules {
	users: set User,
	roles: set RoleSubject,
	u_roles: users -> set roles,
	resources: set {Obj + FName + Operation},
	permissions: Rights ->  Subject ->  resources,
}

abstract sig Subject{}

abstract sig Operation{}

abstract sig AbstractSecureState extends AbstractState {
	//Log of the operations made in each transition
	log: seq Transition
}

/**
*Predicates that verifies all rights needed to define each resource
**/

//Permissions to define an object
pred hasDefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some role: (Def.(AccessControlRules.permissions).o <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

//Permissions to define an attribute
pred hasDefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr:User){
	some role: (Def.(AccessControlRules.permissions).att <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

//Permissions to link an object
pred hasLinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some role: (Def.(AccessControlRules.permissions).attSource <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
	some role: (Def.(AccessControlRules.permissions).(attSource.inverse) <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasReadObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some role: (Read.(AccessControlRules.permissions).o <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasReadAttPermission(s: AbstractSecureState, att: FName, usr: User){
	some role: (Read.(AccessControlRules.permissions).att <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasReadLinkPermission{}

run{}




 
