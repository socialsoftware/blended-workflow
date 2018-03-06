module filesystem/Pattern1/SecurePattern1Spec

open filesystem/BWSecureSpec

/**
*Pattern 1 Permissions
**/
pred hasP1DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).o) <: User | usr = uSub
}

pred hasP1DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).att) <: User| usr = uSub
}

pred hasP1LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	one uSub: (Def.(AccessControlRules.permissions).(attSource.inverse)) <: User | usr = uSub
	one uSub:  (Def.(AccessControlRules.permissions).attSource) <: User  | usr = uSub
}

pred hasP1ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).o) <: User | usr = uSub
}

pred hasP1ReadAttPermission (s: AbstractSecureState, att: FName, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).att) <: User | usr = uSub
}

run{}
