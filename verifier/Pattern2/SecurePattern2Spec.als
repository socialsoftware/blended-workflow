module filesystem/Pattern2/SecurePattern2Spec

open filesystem/Pattern1/SecurePattern1Spec

/**
*Pattern 2 Permissions
**/
//Def
pred hasP2DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).o) <: User | usr = uSub
}

pred hasP2DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).att) <: User| usr = uSub
}

pred hasP2LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	one uSub: (Def.(AccessControlRules.permissions).(attSource.inverse)) <: User | usr = uSub
	one uSub:  (Def.(AccessControlRules.permissions).attSource) <: User  | usr = uSub
}

//Read
pred hasP2ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).o) <: User | usr = uSub
}

pred hasP2ReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).att) <: User | usr = uSub
}

/**
*Base AC + Pattern 1 + Pattern 2 Permissions 
**/
pred hasDefObjPermissionP2(s: AbstractSecureState, o: Obj, usr: User){
	hasP2DefObjPermission[s, o, usr] or 
	hasP1DefObjPermission[s, o, usr] or 
	hasDefObjPermission[s, o, usr]
}

pred hasDefAttPermissionP2(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP2DefAttPermission[s, o, att, usr] or
	hasP1DefAttPermission[s, o, att, usr] or
	hasDefAttPermission[s, o, att, usr]
}


pred hasLinkObjPermissionP2(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	hasP2LinkObjPermission[s, objSource, attSource, objTarget, usr] or
	hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr] or
	hasLinkObjPermission[s, objSource, attSource, objTarget, usr]
}

pred hasReadObjPermissionP2(s: AbstractSecureState, o: Obj, usr: User){
	hasP2ReadObjPermission[s, o, usr] or 
	hasP1ReadObjPermission[s, o, usr] or 
	hasReadObjPermission[s, o, usr]
}

pred hasReadAttPermissionP2(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP2ReadAttPermission[s, o, att, usr] or
	hasP1ReadAttPermission[s, o, att, usr] or
	hasReadAttPermission[s, att, usr]
}


run{}
