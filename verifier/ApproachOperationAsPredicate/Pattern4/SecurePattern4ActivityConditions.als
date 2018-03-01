module filesystem/Pattern4/SecurePattern4ActivityConditions

open filesystem/SecureActivityConditions
open filesystem/Pattern4/SecurePattern4Spec




/**
*ACTIVITY
**/
pred secureP4Activity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj,
										op: Operation, usr: User){
	hasP4DefOperationPermission[s, op, usr]
	preCondition [s, pre_entDefs, pre_attDefs]
	postCondition[s, s', post_entDefs, post_attDefs , post_muls]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, op, usr]
}

pred ACP4ActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasP4DefOperationPermission[s, a.act_op, a.act_usr]
}

run{}



