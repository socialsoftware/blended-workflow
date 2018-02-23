module filesystem/Pattern2/SecurePattern2ActivityConditions

open filesystem/Pattern1/SecurePattern1ActivityConditions
open filesystem/Pattern2/SecurePattern2Spec


/**
*Pre Condition
**/
pred  securePreConditionP2(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasPreConditionReadPermissionsP2[s, entDefs, attDefs, usr]
	preCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasPreConditionReadPermissionsP2(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermissionP2[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasReadAttPermissionP2[s, obj, att, usr]
	}
}

/**
*POST-CONDITION
**/
pred securePostConditionP2(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasPostConditionDefPermissionsP2[s, s', entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasPostConditionDefPermissionsP2(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissionP2[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissionP2[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissionP2[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*ACTIVITY
**/
pred secureActivityP2(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	securePreConditionP2[s, pre_entDefs, pre_attDefs, usr]
	securePostConditionP2[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACActInvP2(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasPreConditionReadPermissionsP2[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasPostConditionDefPermissionsP2[s, s.next, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}

run{}




