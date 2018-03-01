module filesystem/Pattern3/SecurePattern3GoalConditions

open filesystem/Pattern3/SecurePattern3Spec
open filesystem/SecureGoalConditions 


/**
*ACTIVATION CONDITION
**/
pred  secureP3ActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP3ActConditionReadPermissions[s, entDefs, attDefs, usr]
	actCondition[s, entDefs, attDefs]
}

pred hasP3ActConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
(entDefs != none) implies{
		all o: entDefs| hasP3ReadObjPermission[s, o, usr]
	}
(attDefs != none -> none) implies{
		all att: Obj.attDefs | hasP3ReadAttPermission[s, att, usr] 
	}
}

/**
*SUCCESS CONDITION
**/

pred secureP3SucCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP3SucConditionDefPermissions[s, entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

pred hasP3SucConditionDefPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP3DefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP3DefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP3LinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*GOAL
**/
pred secureP3Goal(s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
							suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj, usr: User){
	secureP3ActCondition[s, act_entDefs, act_attDefs, usr]
	secureP3SucCondition[s, s', suc_entDefs, suc_attDefs, suc_muls, usr]
	addGoalToLog[s, s', act_entDefs, act_attDefs, 
							suc_entDefs, suc_attDefs, suc_muls, none, usr]
}



pred ACP3GoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasP3ActConditionReadPermissions[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasP3SucConditionDefPermissions[s, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}

run{}
