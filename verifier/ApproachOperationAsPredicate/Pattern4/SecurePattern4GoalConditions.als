module filesystem/Pattern4/SecurePattern4GoalConditions

open filesystem/SecureGoalConditions
open filesystem/Pattern4/SecurePattern4Spec

/**
*Activation Condition
**/
pred  secureP4ActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP4ActConditionReadPermissions[s, entDefs, attDefs, usr]
	actCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasP4ActConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP4GoalReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP4GoalReadAttPermission[s, obj, att, usr]
	}
}


/**
*Success CONDITION
**/
pred secureP4SucCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP4SucConditionDefPermissions[s, s', entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all success conditions
pred hasP4SucConditionDefPermissions(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
(entDefs != none) implies{
		all o: entDefs| hasP4GoalDefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP4GoalDefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP4GoalLinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*Goal
**/
pred secureP4Goal(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	secureP4ActCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP4SucCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addGoalToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}


pred ACP4GoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasP4ActConditionReadPermissions[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasP4SucConditionDefPermissions[s, s.next, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}





run{}
