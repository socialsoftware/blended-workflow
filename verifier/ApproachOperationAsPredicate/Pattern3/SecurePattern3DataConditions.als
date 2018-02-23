module filesystem/Pattern3/SecurePattern3DataConditions

open filesystem/Pattern2/SecurePattern2DataConditions
open filesystem/Pattern3/SecurePattern3Spec

pred SecurePattern2DefObj(s, s' : AbstractSecureState, o: Obj, usr:User){
	hasDefObjPermissionP2[s, o, usr]
	defObj[s, s', o]
	addObjToLog[s, s', o, usr]
}

pred SecurePattern2DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	hasDefAttPermissionP2[s, o, att, usr]
	defAtt[s, s', o, att]
	addAttToLog[s, s', o, att, usr]
}

pred SecurePattern2LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	hasLinkObjPermissionP2[s, objSource, attSource, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
	addLinkToLog[s, s', objSource, attSource, objTarget, usr]
}


pred secureDMInvP2(s: AbstractSecureState){
	ACObjDefInvP2[s]
	ACAttDefInvP2[s]
	ACLinkDefInvP2[s]
}

pred ACObjDefInvP2(s: AbstractSecureState){
	all do: Int.(s.log) <: defObjTransition | hasDefObjPermissionP2[s, do.dO_obj, do.dO_usr]
}

pred ACAttDefInvP2(s: AbstractSecureState){
	all da: Int.(s.log) <: defAttTransition | hasDefAttPermissionP2[s, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACLinkDefInvP2(s: AbstractSecureState){
	all l: Int.(s.log) <: linkObjTransition| hasLinkObjPermissionP2[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}




run{}
