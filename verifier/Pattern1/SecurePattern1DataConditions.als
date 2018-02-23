module filesystem/Pattern1/SecurePattern1DataConditions

open filesystem/SecureDataConditions
open filesystem/Pattern1/SecurePattern1Spec

pred SecurePattern1DefObj(s, s' : AbstractSecureState, o: Obj, usr:User){
	hasDefObjPermissionP1[s, o, usr]
	defObj[s, s', o]
	addObjToLog[s, s', o, usr]
}

pred SecurePattern1DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	hasDefAttPermissionP1[s, o, att, usr]
	defAtt[s, s', o, att]
	addAttToLog[s, s', o, att, usr]
}

pred SecurePattern1LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	hasLinkObjPermissionP1[s, objSource, attSource, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
	addLinkToLog[s, s', objSource, attSource, objTarget, usr]
}


pred secureDMInvP1(s: AbstractSecureState){
	ACObjDefInvP1[s]
	ACAttDefInvP1[s]
	ACLinkDefInvP1[s]
}

pred ACObjDefInvP1(s: AbstractSecureState){
	all do: Int.(s.log) <: defObjTransition | hasDefObjPermissionP1[s, do.dO_obj, do.dO_usr]
}

pred ACAttDefInvP1(s: AbstractSecureState){
	all da: Int.(s.log) <: defAttTransition | hasDefAttPermissionP1[s, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACLinkDefInvP1(s: AbstractSecureState){
	all l: Int.(s.log) <: linkObjTransition| hasLinkObjPermissionP1[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}




run{}
