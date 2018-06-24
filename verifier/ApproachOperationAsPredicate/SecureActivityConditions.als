module filesystem/SecureActivityConditions

open filesystem/BWSecureSpec
open filesystem/ActivityConditions


/**
* ACTIVITY TRANSITION 
**/

sig activityTransition extends Transition{
	act_usrSource: set User,
	act_usrTarget: set User,
	act_PreDefObj: set Obj,
	act_PreDefAtt: set Obj -> FName,
	act_PostDefObj: set Obj,
	act_PostDefAtt: set Obj -> FName,
	act_PostLinkObj: set Obj -> FName -> Obj,
	act_op: set Operation
}

pred addActivityToLog (s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
									post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, op: Operation, usrSource, usrTarget: User) {
	some a: activityTransition|
		a.act_PreDefObj = pre_entDefs and 
		a.act_PreDefAtt = pre_attDefs and
		a.act_PostDefObj = post_entDefs and
		a.act_PostDefAtt = post_attDefs and 
		a.act_PostLinkObj = post_muls and
		a.act_usrSource = usrSource and
		a.act_usrTarget = usrTarget and 
		a.act_op = op and
		s'.log = s.log.add[a]
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

pred hasActivityPermissions(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
											 post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, op: Operation, usrSource, usrTarget: User){
	(usrTarget=none) implies {
		hasActivityResourcesPermissions[s, s', pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls, usrSource, usrTarget] or 
		hasExecOperationPermission[s, operationObjects[pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls], op, usrSource]
	}
	else {
	some role: op.(AccessControlRules.delegation) | role in usrTarget.(AccessControlRules.u_roles) and
		(hasActivityResourcesPermissions[s, s', pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls, usrSource, usrTarget] or 
		hasExecOperationPermission[s, operationObjects[pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls], op, usrSource] or
		hasExecOperationPermission[s, operationObjects[pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls], op, usrTarget])
	}
	
}


pred hasActivityResourcesPermissions(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
											 				post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usrSource, usrTarget: User){
	(pre_entDefs != none) implies{
		all o: pre_entDefs| hasReadObjPermission[s, o, usrSource] or  hasReadObjPermission [s, o, usrTarget]
	}
	(pre_attDefs != none -> none) implies{
		all obj: pre_attDefs.FName, att : obj.pre_attDefs| hasReadAttPermission[s, obj, att, usrSource] or hasReadAttPermission[s, obj, att, usrTarget]
	}
	(post_entDefs != none) implies{
		all o: post_entDefs| hasDefObjPermission[s, o, usrSource] or hasDefObjPermission[s, o, usrTarget]
	}
	(post_attDefs != none -> none) implies{
		all obj: post_attDefs.FName, att : obj.post_attDefs| hasDefAttPermission[s, obj, att, usrSource] or hasDefAttPermission[s, obj, att, usrTarget]
	}
	(post_muls != none -> none -> none) implies{
		all objSource: post_muls.Obj.FName| all attSource: objSource.post_muls.Obj| all objTarget: attSource.(objSource.post_muls) |
			hasLinkObjPermission[s, objSource, objTarget, attSource, usrSource] or hasLinkObjPermission[s, objSource, objTarget, attSource, usrTarget] 
	}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
*ACTIVITY EXECUTION
**/
pred secureActivity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, op: Operation,  usrSource, usrTarget, register: User ){
	hasActivityPermissions[s, s', pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls, op, usrSource, usrTarget]
	preCondition[s, pre_entDefs, pre_attDefs]
	postCondition[s, s', post_entDefs, post_attDefs, post_muls]
//	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
//									post_entDefs, post_attDefs, post_muls, op, usrSource, usrTarget]
}

pred ACActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasActivityPermissions[s, s.next, a.act_PreDefObj, a.act_PreDefAtt, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_op, a.act_usrSource, a.act_usrTarget]
}

run{}
