module filesystem/Pattern3/SecurePattern3Spec

open filesystem/Pattern2/SecurePattern2Spec



abstract sig DefObj extends Operation{
	entDef: Obj
}
abstract sig DefAtt extends Operation {
	attDef: Obj -> FName
}
abstract sig LinkObj extends Operation {
	link: Obj -> FName -> Obj
}
abstract sig Activity extends Operation {
	pre_ent: Obj,
	pre_att: FName,
	post_entDefs: Obj,
	post_attDefs: Obj -> FName,
	post_links: Obj -> FName -> Obj
}
abstract sig Goal extends Operation {}
/**
*Pattern 3 Permissions
* User Based
**/
//Def
pred hasP3DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and (o in op.entDef or o in op.post_entDefs)
}

pred hasP3DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and (o -> att in op.attDef or o -> att in op.post_attDefs)
}

pred hasP3LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub
	one uSub:  (Def.(AccessControlRules.permissions).Operation) <: User  | usr = uSub
}

//Read
pred hasP3ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).Operation) <: User | usr = uSub
}

pred hasP3ReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	one uSub: (Read.(AccessControlRules.permissions).Operation) <: User | usr = uSub
}


/**
*Base AC + Pattern 1 + Pattern 2 Permissions 
**/
pred hasDefObjPermissionP3(s: AbstractSecureState, o: Obj, usr: User){
	hasP2DefObjPermission[s, o, usr] or 
	hasP1DefObjPermission[s, o, usr] or 
	hasDefObjPermission[s, o, usr]
}

pred hasDefAttPermissionP3(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP2DefAttPermission[s, o, att, usr] or
	hasP1DefAttPermission[s, o, att, usr] or
	hasDefAttPermission[s, o, att, usr]
}


pred hasLinkObjPermissionP3(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	hasP2LinkObjPermission[s, objSource, attSource, objTarget, usr] or
	hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr] or
	hasLinkObjPermission[s, objSource, attSource, objTarget, usr]
}

pred hasReadObjPermissionP3(s: AbstractSecureState, o: Obj, usr: User){
	hasP2ReadObjPermission[s, o, usr] or 
	hasP1ReadObjPermission[s, o, usr] or 
	hasReadObjPermission[s, o, usr]
}

pred hasReadAttPermissionP3(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP2ReadAttPermission[s, o, att, usr] or
	hasP1ReadAttPermission[s, o, att, usr] or
	hasReadAttPermission[s, att, usr]
}


run{}
