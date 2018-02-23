module filesystem/Pattern1/SecurePattern1Spec

open filesystem/BWSecureSpec

abstract sig DomainSubject extends Subject{
	path: seq FName
}

/**
*Pattern 1 Permissions
**/
//Def
pred hasP1DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some dSub: (Def.(AccessControlRules.permissions).o) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s.next, o, dSub.path] 
}

pred hasP1DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some dSub: (Def.(AccessControlRules.permissions).att) <: DomainSubject | some obj: usr.usr_obj| obj in reach[s, o, dSub.path] 
}

pred hasP1LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some dSub: (Def.(AccessControlRules.permissions).(attSource.inverse)) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, objSource, dSub.path] 
	some dSub:  (Def.(AccessControlRules.permissions).attSource) <: DomainSubject  | some obj: usr.usr_obj| obj in reach[s, objTarget, dSub.path] 
}
//Read
pred hasP1ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	some dSub: (Read.(AccessControlRules.permissions).o) <: DomainSubject | some obj: usr.usr_obj| obj in reach[s, o, dSub.path] 
}

pred hasP1ReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some dSub: (Read.(AccessControlRules.permissions).att) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, o, dSub.path] 
}

/**
*Base AC + Pattern 1 Permissions 
**/
pred hasDefObjPermissionP1(s: AbstractSecureState, o: Obj, usr: User){
	hasP1DefObjPermission[s, o, usr] or 
	hasDefObjPermission[s, o, usr]
}

pred hasDefAttPermissionP1(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP1DefAttPermission[s, o, att, usr] or
	hasDefAttPermission[s, o, att, usr]
}

pred hasLinkObjPermissionP1(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr] or
	hasLinkObjPermission[s, objSource, attSource, objTarget, usr]
}

pred hasReadObjPermissionP1(s: AbstractSecureState, o: Obj, usr: User){
	hasP1ReadObjPermission[s, o, usr] or 
	hasReadObjPermission[s, o, usr]
}

pred hasReadAttPermissionP1(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP1ReadAttPermission[s, o, att, usr] or
	hasReadAttPermission[s, att, usr]
}


run{}
