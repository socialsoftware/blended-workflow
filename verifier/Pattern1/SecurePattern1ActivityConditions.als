module filesystem/Pattern1/SecurePattern1ActivityConditions

open filesystem/SecureActivityConditions
open filesystem/Pattern1/SecurePattern1Spec


/**
*Pre Condition
**/
pred  securePreConditionP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	userHasReadPermissionAllPreConditionP1[s, entDefs, attDefs, usr]
	preCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred userHasReadPermissionAllPreConditionP1(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	userHasReadPermissionAllPreConditionEntitiesP1[s, entDefs, usr]	
	userHasReadPermissionAllPreConditionAttributesP1[s, attDefs, usr]
}

//user has read permission in all entities in the post conditions
pred userHasReadPermissionAllPreConditionEntitiesP1(s: AbstractSecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasReadObjPermissionP1[s, o, usr]
	}
}

//user has read permission in all attributes in the post conditions
pred userHasReadPermissionAllPreConditionAttributesP1(s: AbstractSecureState, attDefs: set Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasReadAttPermissionP1[s, obj, att, usr]
	}
}


/**
*POST-CONDITION
**/
pred securePostConditionP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	userHasDefPermissionAllPostConditionP1[s, s', entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred userHasDefPermissionAllPostConditionP1(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	userHasDefPermissionAllPostConditionEntitiesP1[s, s', entDefs, usr]	
	userHasDefPermissionAllPostConditionAttributesP1[s, s', attDefs, usr]
	userHasDefPermissionAllPostConditionLinksP1[s, s', muls, usr]
}

//user has definition permission in all entities in the post conditions
pred userHasDefPermissionAllPostConditionEntitiesP1(s, s': AbstractSecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissionP1[s, s', o, usr]
	}
}

//user has definition permission in all attributes in the post conditions
pred userHasDefPermissionAllPostConditionAttributesP1(s, s': AbstractSecureState, attDefs: set Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissionP1[s, s', obj, att, usr]
	}
}

//user has def permission in all object link in the post conditions
pred userHasDefPermissionAllPostConditionLinksP1(s, s': AbstractSecureState, muls: set Obj -> FName -> Obj, usr: User){
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissionP1[s, s', objSource, attSource, objTarget, usr] 
	}
}

/**
*ACTIVITY
**/
pred secureActivityP1(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	usr in AccessControlRules.users
	securePreConditionP1[s, pre_entDefs, pre_attDefs, usr]
	securePostConditionP1[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACActP1Inv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		userHasReadPermissionAllPreCondition[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		userHasDefPermissionAllPostConditionP1[s, s.next, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}

run{}




