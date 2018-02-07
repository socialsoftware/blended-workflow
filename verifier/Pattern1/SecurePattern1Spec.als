module filesystem/Pattern1/SecurePattern1Spec

open filesystem/BWSecureSpec

abstract sig DomainSubject extends Subject{
	path: seq FName
}


fun domSubject(res: {Obj + FName}, right: Rights): set DomainSubject {
	(right.(AccessControlRules.permissions).res) <: DomainSubject
}

/**
*DEF
**/
pred hasDefObjPermissionP1(s, s': AbstractSecureState, o: Obj, usr: User){
	hasDefObjDomainPermission[s, s', o, usr] or 
	hasDefObjPermissions[s, o, usr]
}

pred hasDefAttPermissionP1(s, s': AbstractSecureState, o: Obj, att: FName, usr: User){
	hasDefAttDomainPermission[s, s', o, att, usr] or
	hasDefAttPermissions[s, o, att, usr]
}

pred hasLinkObjPermissionP1(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	hasLinkObjPermissions[s, objSource, attSource, objTarget, usr]
	
}
//Usar separado
pred hasDefObjDomainPermission(s, s': AbstractSecureState, o: Obj, usr: User){
	let dSub= domSubject[o, Def] | usr.usr_obj in reach[s', o, dSub.path] and !no reach[s', o, dSub.path]
}

pred hasDefAttDomainPermission(s, s': AbstractSecureState, o: Obj, att: FName, usr: User){
	let dSub= domSubject[att, Def] | usr.usr_obj in reach[s', o, dSub.path] and !no reach[s', o, dSub.path]
}
/**
*READ
**/
pred hasReadObjPermissionP1(s: AbstractSecureState, o: Obj, usr: User){
	hasReadObjDomainPermission[s, o, usr] or 
	UserHasObjReadRight[s, o, usr]
}

pred hasReadAttPermissionP1(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasReadAttDomainPermission[s, o, att, usr] or
	UserHasAttReadRight[s, att, usr]
}

pred hasReadObjDomainPermission (s: AbstractSecureState, o: Obj, usr: User){
	let dSub= domSubject[o, Read] | usr.usr_obj in reach[s, o, dSub.path] and !no reach[s, o, dSub.path]
}

pred hasReadAttDomainPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	let dSub = domSubject[att, Read] | usr.usr_obj in reach[s, o, dSub.path] and !no reach[s, o, dSub.path]	
}

run{}
