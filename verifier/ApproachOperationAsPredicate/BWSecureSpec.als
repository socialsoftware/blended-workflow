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
	propagation: Operation -> roles
}

abstract sig Subject{}

abstract sig Operation{}
abstract sig Activity, Goal extends Operation{}

abstract sig AbstractSecureState extends AbstractState {
	//Log of the operations made in each transition
	log: seq Transition
}


abstract sig DomainSubject extends Subject{
	path: seq FName
}




/**
* PERMISSION VERIFICATION
**/

pred hasDefObjPermission(s: AbstractSecureState, o: Obj, usr: User){
	userBasedPermission[s, o, none, Def, usr] or
	dynamicResourcePermission[s, o, none, none, Def, usr] or
	roleBasedPermission[s, o, none, Def, usr]
}

pred hasDefAttPermission(s: AbstractSecureState, o: Obj, att: FName, usr: User){
	userBasedPermission[s, att, none, Def, usr] or
	dynamicResourcePermission[s, o, none, att, Def, usr] or
	roleBasedPermission[s, att, none, Def, usr]
}

pred hasLinkObjPermission(s: AbstractSecureState, objSource, objTarget: Obj, attSource: FName, usr:User){
	userBasedPermission[s, attSource, attSource.inverse, Def, usr] or
	dynamicResourcePermission[s, objSource, objTarget, attSource, Def, usr] or
	roleBasedPermission[s, attSource, attSource.inverse, Def, usr] 
}

pred hasReadObjPermission (s: AbstractSecureState, o: Obj, usr: User){
	userBasedPermission[s, o, none, Read, usr] or
	dynamicResourcePermission[s, o, none, none, Read, usr] or
	roleBasedPermission[s, o, none, Read, usr]
}

pred hasReadAttPermission (s: AbstractSecureState, o: Obj, att: FName, usr: User){
	userBasedPermission[s, att, none, Read, usr] or
	dynamicResourcePermission[s, o, none, att, Read, usr] or
	roleBasedPermission[s, att, none, Read, usr]
}

pred hasExecOperationPermission (s: AbstractSecureState, objs: set Obj, op: Operation, usr: User){
	userBasedPermission[s, op, none, Def, usr] or
	dynamicOperationPermission[s, op, objs, Def, usr] or
	roleBasedPermission[s, op, none, Def, usr]
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
pred userBasedPermission(s: AbstractSecureState, res1, res2: (Obj + FName + Operation), right: Rights, usr: User){
	one uSub: (right.(AccessControlRules.permissions).res1) <: User | usr = uSub
	(!no res2) implies {
		one uSub: (right.(AccessControlRules.permissions).res2) <: User | usr = uSub
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
pred dynamicResourcePermission (s: AbstractSecureState, obj1, obj2: (Obj) , att1: FName, right: Rights, usr: User){
	(!no obj2) implies {
		some dSub: (right.(AccessControlRules.permissions).(att1.inverse)) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, obj2, dSub.path] 
	}
	(!no att1) implies {
		some dSub: (right.(AccessControlRules.permissions).att1) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, obj1, dSub.path] 
	}
	else{
		some dSub: (right.(AccessControlRules.permissions).obj1) <: DomainSubject |  some obj: usr.usr_obj| obj in reach[s, obj1, dSub.path] 
	}
}


pred dynamicOperationPermission(s: AbstractSecureState, op: Operation, objs: set Obj, right: Rights, usr: User){
	 some o: objs| some dSub: (right.(AccessControlRules.permissions).op) <: DomainSubject 
		| some uObj: usr.usr_obj| uObj in reach[s, o, dSub.path]  
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
pred roleBasedPermission(s: AbstractSecureState, res1, res2: (Obj + FName + Operation), right: Rights, usr: User){
	some role: (right.(AccessControlRules.permissions).res1 <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
	(!no res2) implies {
		some role: (right.(AccessControlRules.permissions).res2 <: RoleSubject)| role in usr.(AccessControlRules.u_roles)
	}
}


/**
* FUNCTIONS
**/

fun operationObjects(pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj) : set Obj {
	{pre_entDefs + pre_attDefs.FName + post_entDefs + post_attDefs.FName + (post_muls.Obj).FName} 
}


