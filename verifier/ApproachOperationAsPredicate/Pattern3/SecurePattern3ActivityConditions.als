module filesystem/Pattern3/SecurePattern3ActivityConditions

open filesystem/SecureActivityConditions
open filesystem/Pattern3/SecurePattern3Spec


/**
*Pre Condition
**/
pred  secureP3PreCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP3PreConditionReadPermissions[s, entDefs, attDefs, usr]
	preCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasP3PreConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP3ReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP3ReadAttPermission[s, att, usr]
	}
}

/**
*POST-CONDITION
**/
pred secureP3PostCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP3PostConditionDefPermissions[s, s', entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasP3PostConditionDefPermissions(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP3DefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP3DefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP3LinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*ACTIVITY
**/
pred secureP3Activity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	secureP3PreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP3PostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACP3ActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasP3PreConditionReadPermissions[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasP3PostConditionDefPermissions[s, s.next, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}

run{}




