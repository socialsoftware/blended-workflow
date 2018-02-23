module filesystem/SecureActivityConditions

open filesystem/BWSecureSpec
open filesystem/ActivityConditions

sig activityTransition extends Transition{
	act_usr: User,
	act_PreDefObj: set Obj,
	act_PreDefAtt: set Obj -> FName,
	act_PostDefObj: set Obj,
	act_PostDefAtt: set Obj -> FName,
	act_PostLinkObj: set Obj -> FName -> Obj
}

pred addActivityToLog (s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
									post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User) {
	some a: activityTransition|
		a.act_PreDefObj = pre_entDefs and 
		a.act_PreDefAtt = pre_attDefs and
		a.act_PostDefObj = post_entDefs and
		a.act_PostDefAtt = post_attDefs and 
		a.act_PostLinkObj = post_muls and
		a.act_usr = usr and
		s'.log = s.log.add[a]
}

pred ACActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasPreConditionReadPermissions[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasPostConditionDefPermissions[s, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}


/**
*PRE-CONDITION
**/

pred  securePreCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasPreConditionReadPermissions[s, entDefs, attDefs, usr]
	preCondition[s, entDefs, attDefs]
}

//user has read permission in all pre conditions
pred hasPreConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all att: Obj.attDefs | hasReadAttPermission[s, att, usr] 
	}
}


/**
*POST-CONDITION
**/
pred securePostCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasPostConditionDefPermissions[s, entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasPostConditionDefPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}


/**
*ACTIVITY
**/
pred secureActivity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	securePreCondition[s, pre_entDefs, pre_attDefs, usr]
	securePostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

run{}

////////////////////////////
