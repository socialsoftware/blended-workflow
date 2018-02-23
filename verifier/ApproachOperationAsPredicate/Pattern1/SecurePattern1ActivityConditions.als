module filesystem/Pattern1/SecurePattern1ActivityConditions

open filesystem/SecureActivityConditions
open filesystem/Pattern1/SecurePattern1Spec


/**
*Pre Condition
**/
pred  securePreConditionP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasPreConditionReadPermissionsP1[s, entDefs, attDefs, usr]
	preCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasPreConditionReadPermissionsP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermissionP1[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasReadAttPermissionP1[s, obj, att, usr]
	}
}

/**
*POST-CONDITION
**/
pred securePostConditionP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasPostConditionDefPermissionsP1[s, s', entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasPostConditionDefPermissionsP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissionP1[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissionP1[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissionP1[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*ACTIVITY
**/
pred secureActivityP1(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	securePreConditionP1[s, pre_entDefs, pre_attDefs, usr]
	securePostConditionP1[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACActInvP1(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasPreConditionReadPermissionsP1[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasPostConditionDefPermissionsP1[s, s.next, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}

run{}




