module filesystem/SecureActivityModel

open filesystem/SecureDataModel
open filesystem/ActivityModel



//user has read permission in all pre conditions
pred userHasReadPermissionAllPreCondition(s: SecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	userHasReadPermissionAllPreConditionEntities[s, entDefs, usr]
	userHasReadPermissionAllPreConditionAttributes[s, attDefs, usr]
}

//user has read permission in all entities in the pre conditions
pred userHasReadPermissionAllPreConditionEntities(s: SecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| UserHasObjReadPermissions[s, o, usr]
	}
}

//user has read permission in all attributes in the pre conditions
pred userHasReadPermissionAllPreConditionAttributes(s: SecureState, attDefs: set  Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all att: Obj.attDefs | UserHasAttReadPermissions[s, att, usr] 
	}
}

//user has definition permission in all post conditions
pred userHasDefPermissionAllPostCondition(s: SecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	userHasDefPermissionAllPostConditionEntities[s, entDefs, usr]	
	userHasDefPermissionAllPostConditionAttributes[s, attDefs, usr]
	userHasDefPermissionAllPostConditionLinks[s, muls, usr]
}

//user has definition permission in all entities in the post conditions
pred userHasDefPermissionAllPostConditionEntities(s: SecureState, entDefs: set Obj, usr: User){
	(entDefs != none) implies{
		all o: entDefs| hasDefObjPermissions[s, o, usr]
	}
}

//user has definition permission in all attributes in the post conditions
pred userHasDefPermissionAllPostConditionAttributes(s: SecureState, attDefs: set Obj -> FName, usr: User){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs| hasDefAttPermissions[s, obj, att, usr]
	}
}

//user has def permission in all object link in the post conditions
pred userHasDefPermissionAllPostConditionLinks(s: SecureState, muls: set Obj -> FName -> Obj, usr: User){
	(muls != none -> none -> none) implies{
		all objSource: muls.Obj.FName| all attSource: objSource.muls.Obj| all objTarget: attSource.(objSource.muls) |
			hasLinkObjPermissions[s, objSource, attSource, objTarget, usr] 
			and hasLinkObjPermissions[s, objTarget, attSource.inverse, objSource, usr]
	}
}

//Add activity to log
pred addPreDepPostToLog (s, s': SecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
dep_sourceObj: Obj, dep_sourceAtt: FName, dep_path: seq FName, dep_targetAtt: FName,  
post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User) {
	s'.log = s.log.add[activityTransition]
	//Pre Condition
	s'.log.last.act_PreDefObj = pre_entDefs
	s'.log.last.act_PreDefAtt = pre_attDefs
	//Dependence
	s'.log.last.act_DepSourceObj = dep_sourceObj
	s'.log.last.act_DepSourceAtt = dep_sourceAtt
	s'.log.last.act_DepPath = dep_path
	s'.log.last.act_DepTargetAtt = dep_targetAtt
	//Post Condition
	s'.log.last.act_PostDefObj = post_entDefs
	s'.log.last.act_PostDefAtt = post_attDefs
	s'.log.last.act_PostLinkObj = post_muls
	s'.log.last.act_usr = usr
}

/**
*Log Verifications
**/

//POST CONDITION
pred NoActivityPostConditionWithoutPermissions(s: SecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj){
	NoActivityObjectDefinedWithoutPermission[s, entDefs]
	NoActivityAttributesDefinedWithoutPermission[s, attDefs]
	NoActivityObjLinksDefinedWithoutPermission[s, muls]
}

pred NoActivityObjectDefinedWithoutPermission(s: SecureState, entDefs: set Obj){
	(entDefs != none) implies{
		all do: (activityTransition <: Int.(s.log)) <: act_PostDefObj.entDefs 
			| userHasDefPermissionAllPostConditionEntities [s, entDefs, do.act_usr]
	}
}

pred NoActivityAttributesDefinedWithoutPermission(s: SecureState, attDefs: set Obj -> FName){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs
			| all da: (activityTransition <: Int.(s.log)) <: act_PostDefAtt.att.obj 
				| userHasDefPermissionAllPostConditionAttributes [s, attDefs, da.act_usr]
	}
}

pred NoActivityObjLinksDefinedWithoutPermission(s: SecureState, muls:set Obj -> FName -> Obj ){
	(muls != none -> none -> none) implies{
			all objSource: muls.Obj.FName, attSource: objSource.muls.Obj, objTarget: attSource.(objSource.muls)
				| all lo: (activityTransition <: Int.(s.log)) <: act_PostLinkObj.objTarget.attSource.objSource
					| userHasDefPermissionAllPostConditionLinks[s, muls, lo.act_usr]
	}
}

//PRE CONDITION
pred NoActivityPreConditionsWithoutPermissions(s: SecureState, entDefs: set Obj, attDefs: set Obj -> FName){
	NoActivityPreConditionsEntityWithoutReadPermission[s, entDefs]
	NoActivityPreConditionsAttributeWithoutReadPermission[s, attDefs]
}

pred NoActivityPreConditionsEntityWithoutReadPermission(s: SecureState, entDefs: set Obj){
	(entDefs != none) implies{
		all do: (activityTransition <: Int.(s.log)) <: act_PreDefObj.entDefs 
			| userHasReadPermissionAllPreConditionEntities [s, entDefs, do.act_usr]
	}
}

pred NoActivityPreConditionsAttributeWithoutReadPermission(s: SecureState, attDefs: set Obj -> FName){
	(attDefs != none -> none) implies{
		all obj: attDefs.FName, att : obj.attDefs
			| all da: (activityTransition <: Int.(s.log)) <: act_PreDefAtt.att.obj 
				| userHasReadPermissionAllPreConditionAttributes [s, attDefs, da.act_usr]
	}
} 

//DEPENDENCE
pred NoActivityDepenceWithoutPermissions(s: SecureState, sourceObj: Obj, sourceAtt: FName, path: seq FName, targetAtt: FName){
	all dep1: (activityTransition <: Int.(s.log)) <: act_DepSourceObj. sourceObj, 
		dep2: dep1 <: act_DepSourceAtt.sourceAtt, 
		dep3: dep2 <: (act_DepPath.(Int.path)).Int,
		dep4: dep3 <: act_DepTargetAtt.targetAtt
			| userHasDependencePermissions [s, sourceObj, path, targetAtt, dep4.act_usr] 
}

pred userHasDependencePermissions[s: SecureState, sourceObj: Obj, path: seq FName, targetAtt: FName, usr: User]{
	hasPathObjReadPermissions [s, sourceObj, path, usr] 
	UserHasAttReadPermissions[s, targetAtt, usr] 
}

//All Log Verifications in one pred
pred NoActivityExecutionWithoutPermissions(s: SecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj){

	NoActivityPreConditionsWithoutPermissions[s, pre_entDefs, pre_attDefs]
	NoActivityPostConditionWithoutPermissions[s, post_entDefs, post_attDefs, post_muls]
}

/**
* Base case where the AC is static 
**/
pred  secureBasePreCondition(s: SecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User) {
	usr in s.users
	userHasReadPermissionAllPreCondition[s, entDefs, attDefs, usr]
	preCondition[s, entDefs, attDefs]
}


pred secureBasePostCondition(s, s': SecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User) {
	usr in s.users
	userHasDefPermissionAllPostCondition[s, entDefs, attDefs, muls, usr]
	postCondition[s, s', entDefs, attDefs, muls]
}

pred secureBaseDependence(s: SecureState, sourceObj: Obj, sourceAtt: FName, path: seq FName, targetAtt: FName, usr: User ){
	usr in s.users
	userHasDependencePermissions[s, sourceObj, path, targetAtt, usr]
	dependence[s, sourceObj, sourceAtt, path, targetAtt] 
}

pred secureBaseActivity(s, s': SecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
dep_sourceObj: Obj, dep_sourceAtt: FName, dep_path: seq FName, dep_targetAtt: FName,  
post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){

	secureBasePreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureBaseDependence[s', dep_sourceObj, dep_sourceAtt, dep_path, dep_targetAtt, usr]
	secureBasePostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addPreDepPostToLog[s, s', pre_entDefs, pre_attDefs, dep_sourceObj, dep_sourceAtt, dep_path, dep_targetAtt, post_entDefs, post_attDefs, post_muls, usr]
}


/**
* Enhanced model where the definition of an object grants read permission to that object
**/

pred secureDynamicPreCondition(s: SecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	secureBasePreCondition[s, entDefs, attDefs, usr]
}

pred secureDynamicDependence(s, s': SecureState, sourceObj: Obj, sourceAtt: FName, path: seq FName, targetAtt: FName, usr: User){
	usr in s.users
	userHasDependencePermissions[s, sourceObj, path, targetAtt, usr]
	dependence[s, sourceObj, sourceAtt, path, targetAtt] 
}

pred secureDynamicPostCondition(s, s': SecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	secureBasePostCondition[s, s', entDefs, attDefs, muls, usr]
	giveUserPermissionOverDefinedEntitiesAndAttributes[s, s', entDefs, attDefs, muls, usr]
}

pred secureDynamicActivity(s, s': SecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
											dep_sourceObj: Obj, dep_sourceAtt: FName, dep_path: seq FName, dep_targetAtt: FName,  
											post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){

	secureDynamicPreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureDynamicDependence[s, s', dep_sourceObj, dep_sourceAtt, dep_path, dep_targetAtt, usr]
	secureDynamicPostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addPreDepPostToLog[s, s', pre_entDefs, pre_attDefs, dep_sourceObj, dep_sourceAtt, dep_path, dep_targetAtt, post_entDefs, post_attDefs, post_muls, usr]
}


pred giveUserPermissionOverDefinedEntitiesAndAttributes(s, s': SecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){

}


run{}


