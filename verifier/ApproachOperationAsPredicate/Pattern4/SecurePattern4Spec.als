module filesystem/Pattern4/SecurePattern4Spec

open filesystem/BWSecureSpec


abstract sig Activity extends Operation {
	pre_ent: set Obj,
	pre_att: set Obj -> FName,
	post_entDefs: set Obj,
	post_attDefs: set Obj -> FName,
	post_links: set Obj -> FName -> Obj
}


/**
*Pattern 4 Permissions
* Activity
**/
//ROLE BASED
pred hasP41DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
		some op: Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
			| role in usr.(AccessControlRules.u_roles) and o in op.post_entDefs
}

pred hasP41DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr:User){
	some op: Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)	
		| role in usr.(AccessControlRules.u_roles) and o -> att in op.post_attDefs
}

pred hasP41LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op:Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
		| role in usr.(AccessControlRules.u_roles) and objSource -> attSource -> objTarget in op.post_links 
}

pred hasP41ReadObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
		| role in usr.(AccessControlRules.u_roles) and  o in op.pre_ent 
}

pred hasP41ReadAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op:Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
		| role in usr.(AccessControlRules.u_roles) and  o -> att in op.pre_att
}

//DYNAMIC 
pred hasP42DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		|  some obj: usr.usr_obj| obj in reach[s.next, o, dSub.path]  and o in op.post_entDefs
}

pred hasP42DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		| some obj: usr.usr_obj| obj in reach[s, o, dSub.path] and o -> att in op.post_attDefs
}

pred hasP42LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		|  some obj: usr.usr_obj| obj in reach[s, objSource, dSub.path] and objSource -> attSource -> objTarget in op.post_links 
}

pred hasP42ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject
		| some obj: usr.usr_obj| obj in reach[s, o, dSub.path] and o in op.pre_ent 
}

pred hasP42ReadAttPermission (s: AbstractSecureState, o:Obj, att: FName, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		|  some obj: usr.usr_obj| obj in reach[s, o, dSub.path] and  o -> att in op.pre_att
}

//USER BASED
pred hasP43DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and  o in op.post_entDefs
}

pred hasP43DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and  o -> att in op.post_attDefs
}

pred hasP43LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and objSource -> attSource -> objTarget in op.post_links
}

pred hasP43ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){	
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and  o in op.pre_ent
}

pred hasP43ReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and   o -> att in op.pre_att
}
//////////////////////

pred hasP4DefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	hasP41DefObjPermission[s, o, usr] or
	hasP42DefObjPermission[s, o, usr] or
	hasP43DefObjPermission[s, o, usr]
}

pred hasP4DefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP41DefAttPermission [s, o, att, usr] or
	hasP42DefAttPermission [s, o, att, usr] or
	hasP43DefAttPermission [s, o, att, usr]
}

pred hasP4LinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	hasP41LinkObjPermission [s, objSource, attSource, objTarget, usr] or 
	hasP42LinkObjPermission [s, objSource, attSource, objTarget, usr] or 
	hasP43LinkObjPermission [s, objSource, attSource, objTarget, usr]	

}

pred hasP4ReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){	
	hasP41ReadObjPermission [s, o, usr] or
	hasP42ReadObjPermission [s, o, usr] or
	hasP43ReadObjPermission [s, o, usr]
}

pred hasP4ReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP41ReadAttPermission[s, o, att, usr] or
	hasP42ReadAttPermission[s, o, att, usr] or
	hasP43ReadAttPermission[s, o, att, usr]
}




abstract sig Goal extends Operation {
	act_ent: set Obj,
	act_att: set Obj -> FName,
	suc_entDefs: set Obj,
	suc_attDefs: set Obj -> FName,
	suc_links: set Obj -> FName -> Obj
}

/**
*Pattern 4 Permissions
* Goal
**/
//ROLE BASED
pred hasP41GoalDefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
		some op: Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
			| role in usr.(AccessControlRules.u_roles) and o in op.suc_entDefs
}

pred hasP41GoalDefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr:User){
	some op: Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)	
		| role in usr.(AccessControlRules.u_roles) and o -> att in op.suc_attDefs
}

pred hasP41GoalLinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op:Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
		| role in usr.(AccessControlRules.u_roles) and objSource -> attSource -> objTarget in op.suc_links 
}

pred hasP41GoalReadObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
		| role in usr.(AccessControlRules.u_roles) and  o in op.act_ent 
}

pred hasP41GoalReadAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op:Operation| some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
		| role in usr.(AccessControlRules.u_roles) and  o -> att in op.act_att
}

//DYNAMIC 
pred hasP42GoalDefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		|  some obj: usr.usr_obj| obj in reach[s.next, o, dSub.path]  and o in op.suc_entDefs
}

pred hasP42GoalDefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		| some obj: usr.usr_obj| obj in reach[s, o, dSub.path] and o -> att in op.suc_attDefs
}

pred hasP42GoalLinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		|  some obj: usr.usr_obj| obj in reach[s, objSource, dSub.path] and objSource -> attSource -> objTarget in op.suc_links 
}

pred hasP42GoalReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject
		| some obj: usr.usr_obj| obj in reach[s, o, dSub.path] and o in op.act_ent 
}

pred hasP42GoalReadAttPermission (s: AbstractSecureState, o:Obj, att: FName, usr: User){
	some op: Operation| some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
		|  some obj: usr.usr_obj| obj in reach[s, o, dSub.path] and  o -> att in op.act_att
}

//USER BASED
pred hasP43GoalDefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and  o in op.suc_entDefs
}

pred hasP43GoalDefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and  o -> att in op.suc_attDefs
}

pred hasP43GoalLinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and objSource -> attSource -> objTarget in op.suc_links
}

pred hasP43GoalReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){	
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and  o in op.act_ent
}

pred hasP43GoalReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	some op: Operation| one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub and   o -> att in op.act_att
}
//////////////////////

pred hasP4GoalDefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	hasP41GoalDefObjPermission[s, o, usr] or
	hasP42GoalDefObjPermission[s, o, usr] or
	hasP43GoalDefObjPermission[s, o, usr]
}

pred hasP4GoalDefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP41GoalDefAttPermission [s, o, att, usr] or
	hasP42GoalDefAttPermission [s, o, att, usr] or
	hasP43GoalDefAttPermission [s, o, att, usr]
}

pred hasP4GoalLinkObjPermission(s: AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	hasP41GoalLinkObjPermission [s, objSource, attSource, objTarget, usr] or 
	hasP42GoalLinkObjPermission [s, objSource, attSource, objTarget, usr] or 
	hasP43GoalLinkObjPermission [s, objSource, attSource, objTarget, usr]	

}

pred hasP4GoalReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){	
	hasP41GoalReadObjPermission [s, o, usr] or
	hasP42GoalReadObjPermission [s, o, usr] or
	hasP43GoalReadObjPermission [s, o, usr]
}

pred hasP4GoalReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	hasP41GoalReadAttPermission[s, o, att, usr] or
	hasP42GoalReadAttPermission[s, o, att, usr] or
	hasP43GoalReadAttPermission[s, o, att, usr]
}

run{}
