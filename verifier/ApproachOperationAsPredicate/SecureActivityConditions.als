module filesystem/SecureActivityConditions

open filesystem/BWSecureSpec
open filesystem/ActivityConditions


/**
* ACTIVITY TRANSITION 
**/

sig activityTransition extends Transition{
	act_usr: User,
	act_PreDefObj: set Obj,
	act_PreDefAtt: set Obj -> FName,
	act_PostDefObj: set Obj,
	act_PostDefAtt: set Obj -> FName,
	act_PostLinkObj: set Obj -> FName -> Obj,
	act_op: set Operation
}

pred addActivityToLog (s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
									post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, op: Operation, usr: User) {
	some a: activityTransition|
		a.act_PreDefObj = pre_entDefs and 
		a.act_PreDefAtt = pre_attDefs and
		a.act_PostDefObj = post_entDefs and
		a.act_PostDefAtt = post_attDefs and 
		a.act_PostLinkObj = post_muls and
		a.act_usr = usr and
		a.act_op = op and
		s'.log = s.log.add[a]
}
