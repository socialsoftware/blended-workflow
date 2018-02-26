module filesystem/Pattern1/SecurePattern1Spec

open filesystem/BWSecureSpec

/**
*Predicates that verifies permissions where subject is a role and the resources are Obj or FNames
**/
pred hasP1DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some role: (Def.(AccessControlRules.permissions).o <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasP1DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr:User){
	some role: (Def.(AccessControlRules.permissions).att <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasP1LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some role: (Def.(AccessControlRules.permissions).attSource <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
	some role: (Def.(AccessControlRules.permissions).(attSource.inverse) <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasP1ReadObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some role: (Read.(AccessControlRules.permissions).o <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}

pred hasP1ReadAttPermission(s: AbstractSecureState, att: FName, usr: User){
	some role: (Read.(AccessControlRules.permissions).att <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
}


run{}




 
