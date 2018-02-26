module filesystem/Pattern4/SecurePattern4ActivityConditions

open filesystem/SecureActivityConditions
open filesystem/Pattern4/SecurePattern4Spec




/**
*Pre Condition
**/
pred  secureP4PreCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP4PreConditionReadPermissions[s, entDefs, attDefs, usr]
	preCondition [s, entDefs, attDefs]
}

//user has read permission in all post conditions
pred hasP4PreConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP4ReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP4ReadAttPermission[s,obj , att, usr]
	}
}

/**
*POST-CONDITION
**/
pred secureP4PostCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP4PostConditionDefPermissions[s, s', entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasP4PostConditionDefPermissions(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP4DefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP4DefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP4LinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}

/**
*ACTIVITY
**/
pred secureP4Activity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	secureP4PreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP4PostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACP4ActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasP4PreConditionReadPermissions[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasP4PostConditionDefPermissions[s, s.next, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}


run{}



