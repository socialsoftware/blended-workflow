module filesystem/Pattern4/SecurePattern4GoalConditions

open filesystem/SecureGoalConditions
open filesystem/Pattern4/SecurePattern4Spec



/**
*Goal
**/
pred secureP4Goal(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, op: Operation, usr: User){
	hasP4DefOperationPermission[s, op, usr]
	actCondition[s, pre_entDefs, pre_attDefs]
	sucCondition[s, s', post_entDefs, post_attDefs, post_muls]
	addGoalToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, op, usr]
}


pred ACP4GoalInv(s: AbstractSecureState){
	all g: Int.(s.log) <: goalTransition | 
		hasP4DefOperationPermission[s, g.goal_op , g.goal_usr] 
}





run{}
