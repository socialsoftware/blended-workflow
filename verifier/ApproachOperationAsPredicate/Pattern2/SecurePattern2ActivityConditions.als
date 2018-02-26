module filesystem/Pattern2/SecurePattern2ActivityConditions

open filesystem/SecureActivityConditions
open filesystem/Pattern2/SecurePattern2Spec

open filesystem/Pattern1/SecurePattern1Spec


/**
*Pre Condition
**/
pred  secureP2PreCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP2PreConditionReadPermissions[s, entDefs, attDefs, usr]
	preCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasP2PreConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP2ReadObjPermission[s, o, usr] or  hasP1ReadObjPermission[s, o, usr] 
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP2ReadAttPermission[s, obj, att, usr] or  hasP1ReadAttPermission[s, att, usr]
	}
}

/**
*POST-CONDITION
**/
pred secureP2PostCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP2PostConditionDefPermissions[s, s', entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasP2PostConditionDefPermissions(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP2DefObjPermission[s, o, usr] or hasP1DefObjPermission[s, o, usr] 
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP2DefAttPermission[s, obj, att, usr] or hasP1DefAttPermission[s, obj, att, usr] 
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP2LinkObjPermission[s, objSource, attSource, objTarget, usr] or hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr]
	}
}

/**
*ACTIVITY
**/
pred secureP2Activity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	secureP2PreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP2PostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACP2ActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasP2PreConditionReadPermissions[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasP2PostConditionDefPermissions[s, s.next, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}

run{}




