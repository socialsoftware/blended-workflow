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
