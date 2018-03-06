module filesystem/Pattern4/SecurePattern4DataConditions

open filesystem/SecureDataConditions
open filesystem/Pattern4/SecurePattern4Spec

pred SecurePattern4DefObj(s, s' : AbstractSecureState, o: Obj, usr:User){
//	hasP4DataDefObjPermission[s, o, usr]
	defObj[s, s', o]
	addObjToLog[s, s', o, usr]
}

pred SecurePattern4DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
//	hasP4DataDefAttPermission[s, o, att, usr]
	defAtt[s, s', o, att]
	addAttToLog[s, s', o, att, usr]
}

pred SecurePattern4LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
//	hasP4DataLinkObjPermission[s, objSource, attSource, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
	addLinkToLog[s, s', objSource, attSource, objTarget, usr]
}


pred secureP4DMInv(s: AbstractSecureState){
	ACP4ObjDefInv[s]
	ACP4AttDefInv[s]
	ACP4LinkDefInv[s]
}

pred ACP4ObjDefInv(s: AbstractSecureState){
	//all do: Int.(s.log) <: defObjTransition | hasP4DataDefObjPermission[s, do.dO_obj, do.dO_usr]
}

pred ACAttDefInvP2(s: AbstractSecureState){
	//all da: Int.(s.log) <: defAttTransition | hasP4DataDefAttPermission[s, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACLinkDefInvP2(s: AbstractSecureState){
	//all l: Int.(s.log) <: linkObjTransition| hasP4DataLinkObjPermission[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}




run{}
