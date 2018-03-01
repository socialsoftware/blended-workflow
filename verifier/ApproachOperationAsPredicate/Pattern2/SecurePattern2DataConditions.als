module filesystem/Pattern2/SecurePattern2DataConditions

open filesystem/SecureDataConditions
open filesystem/Pattern2/SecurePattern2Spec

open filesystem/Pattern1/SecurePattern1Spec

/**
*This data condition specification can use either the first or the second pattern
**/
pred SecurePattern2DefObj(s, s' : AbstractSecureState, o: Obj, usr:User){
	hasP2DefObjPermission[s, o, usr] or hasP1DefObjPermission[s, o, usr]
	defObj[s, s', o]
//	addObjToLog[s, s', o, usr]
}

pred SecurePattern2DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	hasP2DefAttPermission[s, o, att, usr] or hasP1DefAttPermission[s, o, att, usr]
	defAtt[s, s', o, att]
//	addAttToLog[s, s', o, att, usr]
}

pred SecurePattern2LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	hasP2LinkObjPermission[s, objSource, attSource, objTarget, usr] or hasP1LinkObjPermission[s, objSource, attSource, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
//	addLinkToLog[s, s', objSource, attSource, objTarget, usr]
}


pred secureP2DMInv(s: AbstractSecureState){
	ACP2ObjDefInv[s]
	ACP2AttDefInv[s]
	ACP2LinkDefInv[s]
}

pred ACP2ObjDefInv(s: AbstractSecureState){
	all do: Int.(s.log) <: defObjTransition | hasP2DefObjPermission[s, do.dO_obj, do.dO_usr] or hasP1DefObjPermission[s, do.dO_obj, do.dO_usr] 
}

pred ACP2AttDefInv(s: AbstractSecureState){
	all da: Int.(s.log) <: defAttTransition | hasP2DefAttPermission[s, da.dA_obj, da.dA_att, da.dA_usr] or hasP1DefAttPermission[s, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACP2LinkDefInv(s: AbstractSecureState){
	all l: Int.(s.log) <: linkObjTransition| hasP2LinkObjPermission[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	or hasP1LinkObjPermission[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]
}




run{}
