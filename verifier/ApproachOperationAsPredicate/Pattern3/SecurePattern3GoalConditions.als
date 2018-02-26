module filesystem/Pattern3/SecurePattern3GoalConditions

open filesystem/SecureGoalConditions
open filesystem/Pattern3/SecurePattern3Spec

/**
*Activation Condition
**/
pred  secureP3ActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP3ActConditionReadPermissions[s, entDefs, attDefs, usr]
	actCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasP3ActConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP3ReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP3ReadAttPermission[s, att, usr]
	}
}


/**
*Success CONDITION
**/
pred secureP3SucCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP3SucConditionDefPermissions[s, s', entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all success conditions
pred hasP3SucConditionDefPermissions(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
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
*Goal
**/
pred secureP3Goal(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	secureP3ActCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP3SucCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addGoalToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}


pred ACP3GoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasP3ActConditionReadPermissions[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasP3SucConditionDefPermissions[s, s.next, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}





run{}
