module filesystem/Pattern3/SecurePattern3GoalConditions

open filesystem/Pattern2/SecurePattern2GoalConditions
open filesystem/Pattern3/SecurePattern3Spec

/**
*Activation Condition
**/
pred  secureActConditionP2(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasActConditionReadPermissionsP2[s, entDefs, attDefs, usr]
	actCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasActConditionReadPermissionsP2(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermissionP2[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasReadAttPermissionP2[s, obj, att, usr]
	}
}


/**
*Success CONDITION
**/
pred secureSucConditionP2(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasSucConditionDefPermissionsP2[s, s', entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all success conditions
pred hasSucConditionDefPermissionsP2(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissionP2[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissionP2[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissionP2[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*Goal
**/
pred secureGoalP2(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	secureActConditionP2[s, pre_entDefs, pre_attDefs, usr]
	secureSucConditionP2[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addGoalToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}


pred ACGoalP2Inv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasActConditionReadPermissionsP2[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasSucConditionDefPermissionsP2[s, s.next, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}





run{}
