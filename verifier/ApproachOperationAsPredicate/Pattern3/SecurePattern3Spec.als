module filesystem/Pattern3/SecurePattern3Spec

open filesystem/BWSecureSpec

/**
*Pattern 3 Permissions
**/
pred hasP3DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).o) <: User | usr = uSub
}

pred hasP3DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).att) <: User| usr = uSub
}

pred hasP3LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	one uSub: (Def.(AccessControlRules.permissions).(attSource.inverse)) <: User | usr = uSub
	one uSub:  (Def.(AccessControlRules.permissions).attSource) <: User  | usr = uSub
}

pred hasP3ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).o) <: User | usr = uSub
}

pred hasP3ReadAttPermission (s: AbstractSecureState, att: FName, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).att) <: User | usr = uSub
}

run{}
