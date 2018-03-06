module filesystem/Pattern4/SecurePattern4Spec

open filesystem/BWSecureSpec


abstract sig Activity extends Operation {
}

abstract sig Goal extends Operation {
}

/**
*Pattern 4 Permissions
* Activity
**/
//ROLE BASED
pred hasP41OperationPermission(s: AbstractSecureState, op:Operation, usr: User){
		some role: (Def.(AccessControlRules.permissions).op <: RoleSubject)
			| role in usr.(AccessControlRules.u_roles)
}


//DYNAMIC 
pred hasP42OperationPermission(s: AbstractSecureState, op:Operation, usr: User){
//	some dSub: (Def.(AccessControlRules.permissions).op) <: DomainSubject 
	//	|  some obj: usr.usr_obj//| obj in reach[s.next, o, dSub.path]  
}


//USER BASED
pred hasP43OperationPermission(s: AbstractSecureState, op:Operation, usr: User){
	one uSub: (Def.(AccessControlRules.permissions).op) <: User | usr = uSub
}
//////////////////////

pred hasP4DefOperationPermission(s: AbstractSecureState, op:Operation, usr: User){
	hasP41OperationPermission[s, op, usr] or
	hasP42OperationPermission[s, op, usr] or
	hasP43OperationPermission[s, op, usr]
}


run{}
