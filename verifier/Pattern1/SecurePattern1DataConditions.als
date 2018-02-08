module filesystem/Pattern1/SecurePattern1DataConditions

open filesystem/SecureDataConditions
open filesystem/Pattern1/SecurePattern1Spec

pred SecurePattern1DefObj(s, s' : AbstractSecureState, o: Obj, usr:User){
	usr in AccessControlRules.users
	hasDefObjPermissionP1[s, s', o, usr]
	defObj[s, s', o]
	//addObjToLog[s, s', o, usr]
}

pred SecurePattern1DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	usr in AccessControlRules.users
	hasDefAttPermissionP1[s, s', o, att, usr]
	defAtt[s, s', o, att]
	//addAttToLog[s, s', o, att, usr]
}

pred SecurePattern1LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	usr in AccessControlRules.users
	hasLinkObjPermissionP1[s, s', objSource, attSource, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
	//addLinkToLog[s, s', objSource, attSource, objTarget, usr]
}


pred secureDMP1Inv(s: AbstractSecureState){
	ACObjDefP1Inv[s]
	ACAttDefP1Inv[s]
	ACLinkDefP1Inv[s]
}

pred ACObjDefP1Inv(s: AbstractSecureState){
	all do: Int.(s.log) <: def_Obj | hasDefObjPermissionP1[s, s.next, do.dO_obj, do.dO_usr]
}

pred ACAttDefP1Inv(s: AbstractSecureState){
	all da: Int.(s.log) <: def_Att | hasDefAttPermissionP1[s, s.next, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACLinkDefP1Inv(s: AbstractSecureState){
	all l: Int.(s.log) <: link_Obj| hasLinkObjPermissionP1[s, s.next, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}




run{}
