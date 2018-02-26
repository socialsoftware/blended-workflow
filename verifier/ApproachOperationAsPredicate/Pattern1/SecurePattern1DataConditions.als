module filesystem/Pattern1/SecurePattern1DataConditions

open filesystem/Pattern1/SecurePattern1Spec
open filesystem/SecureDataConditions



/**
* Operations
**/
pred secureP1DefObj(s, s' : AbstractSecureState, o: Obj, usr:User) {
	hasP1DefObjPermission[s, o, usr]
	defObj[s, s', o]
	addObjToLog[s, s', o, usr]
}

pred secureP1DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	hasP1DefAttPermission[s, o, att, usr]
	defAtt[s, s', o, att]
	addAttToLog[s, s', o, att, usr]
}

pred secureP1LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	hasP1LinkObjPermission[s, objSource, attTarget, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
	addLinkToLog[s, s', objSource, attTarget, objTarget, usr]
}


/**
*Invariant
**/
pred secureP1DMInv(s: AbstractSecureState){
	ACObjDefInv[s]
	ACAttDefInv[s]
	ACLinkDefInv[s]
}

pred ACObjDefInv(s: AbstractSecureState){
	all do: Int.(s.log) <: defObjTransition | hasP1DefObjPermission[s, do.dO_obj, do.dO_usr]
}

pred ACAttDefInv(s: AbstractSecureState){
	all da: Int.(s.log) <: defAttTransition | hasP1DefAttPermission[s, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACLinkDefInv(s:AbstractSecureState){
	all l: Int.(s.log) <: linkObjTransition| hasP1LinkObjPermission[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}

run{}
