module filesystem/SecurePattern1ActivityModel

open filesystem/SecureActivityModel
open filesystem/SecurePattern1DataModel




/*
fun directReachObjs(s: DomainSecureState, o: Obj): set Obj{
	atts[s, o].(o.(s.fields)) <: Obj
}
*/


/*
//Domain Verifications
pred hasDomainRelation(s: DomainSecureState, entDefs: set Obj, attDefs:set Obj -> FName, usr:User, op: Operation){
		objectHasDomainRelation[s, entDefs, usr, op]
		attributeHasDomainRelation[s, attDefs, usr, op]
		
}

pred objectHasDomainRelation(s: DomainSecureState, entDefs: set Obj, usr:User, op: Operation ){
	all obj: entDefs
		|isDomainDependent[s, obj] implies{
			objIsRelatedInDomain[s, obj, usr, op]
		}
}

pred attributeHasDomainRelation(s: DomainSecureState, attDefs:set Obj -> FName, usr:User, op: Operation){
	all obj: attDefs.FName, att : obj.attDefs
		|isDomainDependent[s, att] implies{
			attIsRelatedInDomain[s, obj, att, usr, op]
		}
}


pred isDomainDependent(s: DomainSecureState, dep: {FName+Obj}){
	dep in s.domain_dependent
}

pred objIsRelatedInDomain(s: DomainSecureState, obj:Obj, usr: User, op: Operation){
	all o: reach [s, obj, domainPermissionPath[s, obj, none, op]]| usr.usr_obj = o
}

pred attIsRelatedInDomain(s: DomainSecureState, attObject:Obj, att:FName, usr: User, op: Operation){
	all o: reach [s, attObject, domainPermissionPath[s, attObject, att, op]]| usr.usr_obj = o
}

fun domainPermissionPath(s: DomainSecureState, obj: Obj, att: FName, op: Operation) : set (seq FName){
	(att = none) implies{
		(op.(s.domain_permission).obj).path
	}
	else {
		(op.(s.domain_permission).att).path
	}
}
*/

/*

//Log Domain verifications

pred NoDynamicActivityExecutionWithoutPermissions(s: DomainSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
																		post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj){
	
	NoActivityExecutionWithoutPermissions[s, pre_entDefs, pre_attDefs, post_entDefs, post_attDefs, post_muls]
	
}

pred NoActivityPostConditionWithoutDomainRelation(s: DomainSecureState, post_entDefs:set Obj, post_attDefs: set Obj -> FName){
	(post_attDefs != none -> none) implies{
		all obj: post_attDefs.FName, att : obj.post_attDefs
			| all da: (activityTransition <: Int.(s.log)) <: act_PostDefAtt.att.obj 
				| hasDomainRelation[s, post_entDefs, post_attDefs, da.act_usr, Def]
	}
}

*/

/**
* Enhanced model 
**/

pred secureDynamicPreCondition(s: DomainSecureState, entDefs: set Obj, attDefs: set Obj -> FName, usr: User){
	secureBasePreCondition[s, entDefs, attDefs, usr]
}

pred secureDynamicDependence(s, s': DomainSecureState, sourceObj: Obj, sourceAtt: FName, path: seq FName, targetAtt: FName, usr: User){
	secureBaseDependence[s', sourceObj, sourceAtt, path, targetAtt, usr]
}

pred secureDynamicPostCondition(s, s': DomainSecureState, entDefs: set Obj, attDefs: set Obj -> FName,  muls: set Obj -> FName -> Obj, usr: User){
	secureBasePostCondition[s, s', entDefs, attDefs, muls, usr]
//	hasDomainRelation[s, entDefs, attDefs, usr, Def]
}

pred secureDynamicActivity(s, s': DomainSecureState, pre_entDefs: set Obj, pre_attDefs: set Obj -> FName,
											dep_sourceObj: Obj, dep_sourceAtt: FName, dep_path: seq FName, dep_targetAtt: FName,  
											post_entDefs: set Obj, post_attDefs: set Obj -> FName,  post_muls: set Obj -> FName -> Obj, usr: User){

	secureDynamicPreCondition[s, pre_entDefs, pre_attDefs, usr]
	secureDynamicDependence[s, s', dep_sourceObj, dep_sourceAtt, dep_path, dep_targetAtt, usr]
	secureDynamicPostCondition[s, s', post_entDefs, post_attDefs, post_muls, usr]
	addPreDepPostToLog[s, s', pre_entDefs, pre_attDefs, dep_sourceObj, dep_sourceAtt, dep_path, dep_targetAtt, post_entDefs, post_attDefs, post_muls, usr]
	noChangeInDomainSecureState[s, s']

}


run{}

