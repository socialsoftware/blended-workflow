module filesystem/SecureGoalConditions

open filesystem/BWSecureSpec
open filesystem/GoalConditions

/**
*GOAL TRANSITION
**/
sig goalTransition extends Transition{
	goal_usrSource: set User,
	goal_usrTarget: set User,
	goal_actDefObj: set Obj,
	goal_actDefAtt: set Obj -> FName,
	goal_sucDefObj: set Obj,
	goal_sucDefAtt: set Obj -> FName,
	goal_sucLinkObj: set Obj -> FName -> Obj,
	goal_op: set Operation


}


pred addGoalToLog (s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
									suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj,  op: Operation, usrSource, usrTarget: User){
	some g: goalTransition|
		g.goal_actDefObj = act_entDefs and 
		g.goal_actDefAtt = act_attDefs and	
		g.goal_sucDefObj = suc_entDefs and
		g.goal_sucDefAtt = suc_attDefs and 
		g.goal_sucLinkObj= suc_muls and
		g.goal_usrSource = usrSource and
		g.goal_usrTarget = usrTarget and
		g.goal_op = op and
		s'.log = s.log.add[g]
}


///////////////////////////////////

pred hasGoalPermissions(s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
									suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj,  op: Operation, usrSource, usrTarget: User){
	(usrTarget=none) implies {
		hasGoalResourcesPermissions[s, s', act_entDefs, act_attDefs, suc_entDefs, suc_attDefs, suc_muls, usrSource, usrTarget] or 
		hasExecOperationPermission[s, operationObjects[act_entDefs, act_attDefs, suc_entDefs, suc_attDefs, suc_muls], op, usrSource]
	}
	else {
	some role: op.(AccessControlRules.delegation) | role in usrTarget.(AccessControlRules.u_roles) and
		(hasGoalResourcesPermissions[s, s', act_entDefs, act_attDefs, suc_entDefs, suc_attDefs, suc_muls, usrSource, usrTarget] or 
		hasExecOperationPermission[s, operationObjects[act_entDefs, act_attDefs, suc_entDefs, suc_attDefs, suc_muls], op, usrSource] or
		hasExecOperationPermission[s, operationObjects[act_entDefs, act_attDefs, suc_entDefs, suc_attDefs, suc_muls], op, usrTarget])
	}
	
}


pred hasGoalResourcesPermissions(s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
														suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj, usrSource, usrTarget: User){
	(act_entDefs != none) implies{
		all o: act_entDefs| hasReadObjPermission[s, o, usrSource] or  hasReadObjPermission [s, o, usrTarget]
	}
	(act_attDefs != none -> none) implies{
		all obj: act_attDefs.FName, att : obj.act_attDefs| hasReadAttPermission[s, obj, att, usrSource] or hasReadAttPermission[s, obj, att, usrTarget]
	}
	(suc_entDefs != none) implies{
		all o: suc_entDefs| hasDefObjPermission[s, o, usrSource] or hasDefObjPermission[s, o, usrTarget]
	}
	(suc_attDefs != none -> none) implies{
		all obj: suc_attDefs.FName, att : obj.suc_attDefs| hasDefAttPermission[s, obj, att, usrSource] or hasDefAttPermission[s, obj, att, usrTarget]
	}
	(suc_muls != none -> none -> none) implies{
		all objSource: suc_muls.Obj.FName| all attSource: objSource.suc_muls.Obj| all objTarget: attSource.(objSource.suc_muls) |
			hasLinkObjPermission[s, objSource, objTarget, attSource, usrSource] or hasLinkObjPermission[s, objSource, objTarget, attSource, usrTarget] 
	}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
*ACTIVITY EXECUTION
**/
pred secureActivity(s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
									suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj,  op: Operation, usrSource, usrTarget: User){
	hasGoalPermissions[s, s', act_entDefs, act_attDefs, suc_entDefs, suc_attDefs, suc_muls, op, usrSource, usrTarget]
	actCondition[s, act_entDefs, act_attDefs]
	sucCondition[s, s', suc_entDefs, suc_attDefs, suc_muls]
//	addGoalToLog[s, s', act_entDefs, act_attDefs, 
//									psuc_entDefs, suc_attDefs, suc_muls, op, usrSource, usrTarget]
}

pred ACGoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasGoalPermissions[s, s.next, g.goal_actDefObj, g.goal_actDefAtt, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_op, g.goal_usrSource, g.goal_usrTarget]
}

run{}
