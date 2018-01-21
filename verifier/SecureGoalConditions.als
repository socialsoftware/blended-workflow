module filesystem/SecureGoalConditions

open filesystem/BWSecureSpec
open filesystem/GoalConditions

/**
*GOAL TRANSITION
**/
sig goalTransition extends Transition{
	goal_usr: User,
	goal_actDefObj: set Obj,
	goal_actDefAtt: set Obj -> FName,
	goal_sucDefObj: set Obj,
	goal_sucDefAtt: set Obj -> FName,
	goal_sucLinkObj: set Obj -> FName -> Obj
}

pred addGoalToLog (s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
									suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj, usr: User){
	some g: goalTransition|
		g.goal_actDefObj = act_entDefs and 
		g.goal_actDefAtt = act_attDefs and	
		g.goal_sucDefObj = suc_entDefs and
		g.goal_sucDefAtt = suc_attDefs and 
		g.goal_sucLinkObj= suc_muls and
		g.goal_usr = usr and
		s'.log = s.log.add[g]
}

pred ACGoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		userHasReadPermissionAllActCondition[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		userHasDefPermissionAllSucCondition[s, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}

/**
*ACTIVATION CONDITION
**/
pred  secureActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	userHasReadPermissionAllActCondition[s, entDefs, attDefs, usr]
	actCondition[s, entDefs, attDefs]
}

pred userHasReadPermissionAllActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	userHasReadPermissionAllActConditionEntities[s, entDefs, usr]
	userHasReadPermissionAllActConditionAttributes[s, attDefs, usr]
}

pred userHasReadPermissionAllActConditionEntities(s: AbstractSecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| UserHasObjReadRight[s, o, usr]
	}
}

pred userHasReadPermissionAllActConditionAttributes(s: AbstractSecureState, attDefs: set  Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all att: Obj.attDefs | UserHasAttReadRight[s, att, usr] 
	}
}


/**
*SUCCESS CONDITION
**/

pred secureSucCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	userHasDefPermissionAllSucCondition[s, entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

pred userHasDefPermissionAllSucCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	userHasDefPermissionAllSucConditionEntities[s, entDefs, usr]	
	userHasDefPermissionAllSucConditionAttributes[s, attDefs, usr]
	userHasDefPermissionAllSucConditionLinks[s, muls, usr]
}

pred userHasDefPermissionAllSucConditionEntities(s: AbstractSecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissions[s, o, usr]
	}
}

pred userHasDefPermissionAllSucConditionAttributes(s: AbstractSecureState, attDefs: set Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissions[s, obj, att, usr]
	}
}

pred userHasDefPermissionAllSucConditionLinks(s: AbstractSecureState, muls: set Obj -> FName -> Obj, usr: User){
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissions[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*GOAL
**/
pred secureGoal(s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
							suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	secureActCondition[s, act_entDefs, act_attDefs, usr]
	secureSucCondition[s, s', suc_entDefs, suc_attDefs, suc_muls, usr]
	addGoalToLog[s, s', act_entDefs, act_attDefs, 
							suc_entDefs, suc_attDefs, suc_muls, usr]
}


run{}
