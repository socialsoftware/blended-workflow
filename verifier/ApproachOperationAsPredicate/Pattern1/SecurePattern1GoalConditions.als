module filesystem/Pattern1/SecurePattern1GoalConditions

open filesystem/Pattern1/SecurePattern1Spec
open filesystem/SecureGoalConditions 


/**
*ACTIVATION CONDITION
**/
pred  secureP1ActCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP1ActConditionReadPermissions[s, entDefs, attDefs, usr]
	actCondition[s, entDefs, attDefs]
}

pred hasP1ActConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
(entDefs != none) implies{
		all o: entDefs| hasP1ReadObjPermission[s, o, usr]
	}
(attDefs != none -> none) implies{
		all att: Obj.attDefs | hasP1ReadAttPermission[s, att, usr] 
	}
}

/**
*SUCCESS CONDITION
**/

pred secureP1SucCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP1SucConditionDefPermissions[s, entDefs, attDefs, muls, usr]
	sucCondition[s, s', entDefs, attDefs , muls]
}

pred hasP1SucConditionDefPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP1DefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP1DefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*GOAL
**/
pred secureP1Goal(s, s': AbstractSecureState, act_entDefs: set Obj, act_attDefs: set Obj -> FName,
							suc_entDefs: set Obj, suc_attDefs: set Obj -> FName,  suc_muls: set Obj -> FName -> Obj, usr: User){
	secureP1ActCondition[s, act_entDefs, act_attDefs, usr]
	secureP1SucCondition[s, s', suc_entDefs, suc_attDefs, suc_muls, usr]
	addGoalToLog[s, s', act_entDefs, act_attDefs, 
							suc_entDefs, suc_attDefs, suc_muls, usr]
}



pred ACP1GoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasP1ActConditionReadPermissions[s, g.goal_actDefObj, g.goal_actDefAtt, g.goal_usr] and
		hasP1SucConditionDefPermissions[s, g.goal_sucDefObj, g.goal_sucDefAtt, g.goal_sucLinkObj, g.goal_usr]
}

run{}
