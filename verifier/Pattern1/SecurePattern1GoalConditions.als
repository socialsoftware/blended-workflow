module filesystem/Pattern1/SecurePattern1GoalConditions

open filesystem/SecureGoalConditions
open filesystem/Pattern1/SecurePattern1Spec

/**
*Activation Condition
**/
pred  secureActConditionP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	userHasReadPermissionAllActConditionP1[s, entDefs, attDefs, usr]
	actCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred userHasReadPermissionAllActConditionP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	userHasReadPermissionAllActConditionEntitiesP1[s, entDefs, usr]	
	userHasReadPermissionAllActConditionAttributesP1[s, attDefs, usr]
}

//user has read permission in all entities in the post conditions
pred userHasReadPermissionAllActConditionEntitiesP1(s: AbstractSecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermissionP1[s, o, usr]
	}
}

//user has read permission in all attributes in the post conditions
pred userHasReadPermissionAllActConditionAttributesP1(s: AbstractSecureState, attDefs: set Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasReadAttPermissionP1[s, obj, att, usr]
	}
}


/**
*Success CONDITION
**/
pred secureSucConditionP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	userHasDefPermissionAllSucConditionP1[s, s', entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all success conditions
pred userHasDefPermissionAllSucConditionP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	userHasDefPermissionAllSucConditionEntitiesP1[s, s', entDefs, usr]	
	userHasDefPermissionAllSucConditionAttributesP1[s, s', attDefs, usr]
	userHasDefPermissionAllSucConditionLinksP1[s, s', muls, usr]
}

//user has definition permission in all entities in the success conditions
pred userHasDefPermissionAllSucConditionEntitiesP1(s, s': AbstractSecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissionP1[s, s', o, usr]
	}
}

//user has definition permission in all attributes in the success conditions
pred userHasDefPermissionAllSucConditionAttributesP1(s, s': AbstractSecureState, attDefs: set Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissionP1[s, s', obj, att, usr]
	}
}

//user has def permission in all object link in the success conditions
pred userHasDefPermissionAllSucConditionLinksP1(s, s': AbstractSecureState, muls: set Obj -> FName -> Obj, usr: User){
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissionP1[s, s', objSource, attSource, objTarget, usr] 
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
		userHasReadPermissionAllActCondition[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		userHasDefPermissionAllSucConditionP1[s, s.next, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}





run{}
