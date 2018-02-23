module filesystem/Pattern1/SecurePattern1GoalConditions

open filesystem/SecureGoalConditions
open filesystem/Pattern1/SecurePattern1Spec

/**
*Activation Condition
**/
pred  secureActConditionP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasActConditionReadPermissionsP1[s, entDefs, attDefs, usr]
	actCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasActConditionReadPermissionsP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermissionP1[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasReadAttPermissionP1[s, obj, att, usr]
	}
}


/**
*Success CONDITION
**/
pred secureSucConditionP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasSucConditionDefPermissionsP1[s, s', entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all success conditions
pred hasSucConditionDefPermissionsP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissionP1[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissionP1[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissionP1[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*Goal
**/
pred secureGoalP1(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	secureActConditionP1[s, pre_entDefs, pre_attDefs, usr]
	secureSucConditionP1[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addGoalToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}


pred ACGoalP1Inv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasActConditionReadPermissionsP1[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasSucConditionDefPermissionsP1[s, s.next, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}





run{}
