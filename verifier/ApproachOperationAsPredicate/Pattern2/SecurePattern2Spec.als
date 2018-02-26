module filesystem/Pattern2/SecurePattern2Spec

open filesystem/BWSecureSpec


/**
*Predicates that verifies permissions where subject is a domain association and the resources are Obj or FNames
**/

pred hasP2DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some dSub: (Def.(AccessControlRules.permissions).o) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s.next, o, dSub.path] 
}

pred hasP2DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some dSub: (Def.(AccessControlRules.permissions).att) <: DomainSubject | some obj: usr.usr_obj| obj in reach[s, o, dSub.path] 
}

pred hasP2LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some dSub: (Def.(AccessControlRules.permissions).(attSource.inverse)) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, objSource, dSub.path] 
	some dSub:  (Def.(AccessControlRules.permissions).attSource) <: DomainSubject  | some obj: usr.usr_obj| obj in reach[s, objTarget, dSub.path] 
}

pred hasP2ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	some dSub: (Read.(AccessControlRules.permissions).o) <: DomainSubject | some obj: usr.usr_obj| obj in reach[s, o, dSub.path] 
}

pred hasP2ReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some dSub: (Read.(AccessControlRules.permissions).att) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, o, dSub.path] 
}

run{}
