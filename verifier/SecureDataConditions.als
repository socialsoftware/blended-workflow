module filesystem/SecureDataConditions

open filesystem/BWSecureSpec
open filesystem/DataConditions

/**
* OBJECT DEFINITION TRANSITION 
**/
sig def_Obj extends Transition{
	dO_obj: Obj,
	dO_usr: User
}

pred addObjToLog (s, s': AbstractSecureState, o: Obj, usr: User){
	some d: def_Obj| d.dO_obj = o and d.dO_usr = usr and s'.log = s.log.add[d]
}

pred ACObjDefInv(s: AbstractSecureState){
	all do: Int.(s.log) <: def_Obj | hasDefObjPermissions[s, do.dO_obj, do.dO_usr]
}

/**
* ATTRIBUTE DEFINITION TRANSITION
**/
sig def_Att extends Transition{
	dA_att: FName,
	dA_obj: Obj,
	dA_usr: User
}

pred addAttToLog(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	some d: def_Att| d.dA_obj = o and d.dA_att = att and d.dA_usr = usr and s'.log = s.log.add[d]
}

pred ACAttDefInv(s: AbstractSecureState){
	all da: Int.(s.log) <: def_Att | hasDefAttPermissions[s, da.dA_obj, da.dA_att, da.dA_usr]
}

/**
* LINK OBJECTS TRANSITION
**/
sig link_Obj extends Transition{
	lO_objSource: Obj,
	lO_attSource: FName,
	lO_objTarget: Obj,
	lO_usr: User
}

pred addLinkToLog(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some l: link_Obj| l.lO_objSource = objSource and l.lO_attSource = attSource and l.lO_objTarget = objTarget and l.lO_usr = usr and	s'.log = s.log.add[l] 
}

pred ACLinkDefInv(s:AbstractSecureState){
	all l: Int.(s.log) <: link_Obj| hasLinkObjPermissions[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}


/**
* Secure Object 
**/
pred secureDefObj(s, s' : AbstractSecureState, o: Obj, usr:User) {
	usr in AccessControlRules.users
	hasDefObjPermissions[s, o, usr]
	defObj[s, s', o]
//	addObjToLog[s, s', o, usr]
}

/**
* Secure Attribute Predicates
**/
pred secureDefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	usr in AccessControlRules.users
	hasDefAttPermissions[s, o, att, usr]
	defAtt[s, s', o, att]
//	addAttToLog[s, s', o, att, usr]
}

/**
* Secure Link
**/
pred secureLinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	usr in AccessControlRules.users
	hasLinkObjPermissions[s, objSource, attTarget, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
//	addLinkToLog[s, s', objSource, attTarget, objTarget, usr]
}


/**
*INVARIANT FOR ALL OPERATIONS
**/
pred secureDMInv(s: AbstractSecureState){
	ACObjDefInv[s]
	ACAttDefInv[s]
	ACLinkDefInv[s]
}

run{}
