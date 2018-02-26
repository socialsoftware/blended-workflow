module filesystem/Pattern1/SecurePattern1ActivityConditions

open filesystem/Pattern1/SecurePattern1Spec
open filesystem/SecureActivityConditions


/**
*PRE-CONDITION
**/

pred  secureP1PreCondition(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	hasP1PreConditionReadPermissions[s, entDefs, attDefs, usr]
	preCondition[s, entDefs, attDefs]
}

//user has read permission in all pre conditions
pred hasP1PreConditionReadPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP1ReadObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all att: Obj.attDefs | hasP1ReadAttPermission[s, att, usr] 
	}
}


/**
*POST-CONDITION
**/
pred secureP1PostCondition(s, s': AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	hasP1PostConditionDefPermissions[s, entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs , muls]
}

//user has definition permission in all post conditions
pred hasP1PostConditionDefPermissions(s: AbstractSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasP1DefObjPermission[s, o, usr]
	}
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasP1DefAttPermission[s, obj, att, usr]
	}
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr] 
	}
}


/**
*ACTIVITY
**/
pred secureP1Activity(s, s': AbstractSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName, 
										post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){
	secureP1PreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureP1PostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addActivityToLog[s, s', pre_entDefs, pre_attDefs, 
									post_entDefs, post_attDefs, post_muls, usr]
}

pred ACP1ActInv(s: AbstractSecureState){
	all a: Int.(s.log) <: activityTransition | 
		hasP1PreConditionReadPermissions[s, a.act_PreDefObj, a.act_PreDefAtt, a.act_usr] and
		hasP1PostConditionDefPermissions[s, a.act_PostDefObj, a.act_PostDefAtt, a.act_PostLinkObj, a.act_usr] 
}

run{}

////////////////////////////
