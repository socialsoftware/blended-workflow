module filesystem/Pattern2/SecurePattern2GoalConditions

open filesystem/SecureGoalConditions
open filesystem/Pattern2/SecurePattern2Spec

open filesystem/Pattern1/SecurePattern1Spec

/**
*Activation Condition
**/
pred  secureP2ActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP2ActConditionReadPermissions[s, entDefs, attDefs, usr]
	actCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasP2ActConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP2ReadObjPermission[s, o, usr] or hasP1ReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP2ReadAttPermission[s, obj, att, usr] or hasP1ReadAttPermission[s, att, usr]
	}
}


/**
*Success CONDITION
**/
pred secureP2SucCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP2SucConditionDefPermissions[s, s', entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all success conditions
pred hasP2SucConditionDefPermissions(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
(entDefs != none) implies{
		all o: entDefs| hasP2DefObjPermission[s, o, usr] or hasP1DefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP2DefAttPermission[s, obj, att, usr] or  hasP1DefAttPermission[s, obj, att, usr] 
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP2LinkObjPermission[s, objSource, attSource, objTarget, usr] or hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr]
	}
}

/**
*Goal
**/
pred secureP2Goal(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	secureP2ActCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP2SucCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addGoalToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, none, usr]
}


pred ACP2GoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasP2ActConditionReadPermissions[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasP2SucConditionDefPermissions[s, s.next, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}





run{}
