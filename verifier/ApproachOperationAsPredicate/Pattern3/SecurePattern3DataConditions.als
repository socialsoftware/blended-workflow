module filesystem/Pattern3/SecurePattern3DataConditions

open filesystem/SecureDataConditions
open filesystem/Pattern3/SecurePattern3Spec

pred SecurePattern3DefObj(s, s' : AbstractSecureState, o: Obj, usr:User){
	hasP3DefObjPermission[s, o, usr]
	defObj[s, s', o]
	addObjToLog[s, s', o, usr]
}

pred SecurePattern3DefAtt(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	hasP3DefAttPermission[s, o, att, usr]
	defAtt[s, s', o, att]
	addAttToLog[s, s', o, att, usr]
}

pred SecurePattern3LinkObj(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	hasP3LinkObjPermission[s, objSource, attSource, objTarget, usr]
	linkObj[s, s', objSource, attSource, objTarget, attTarget]
	addLinkToLog[s, s', objSource, attSource, objTarget, usr]
}


pred secureP3DMInv(s: AbstractSecureState){
	ACP3ObjDefInv[s]
	ACP3AttDefInv[s]
	ACP3LinkDefInv[s]
}

pred ACP3ObjDefInv(s: AbstractSecureState){
	all do: Int.(s.log) <: defObjTransition | hasP3DefObjPermission[s, do.dO_obj, do.dO_usr]
}

pred ACP3AttDefInv(s: AbstractSecureState){
	all da: Int.(s.log) <: defAttTransition | hasP3DefAttPermission[s, da.dA_obj, da.dA_att, da.dA_usr]
}

pred ACP3LinkDefInv(s: AbstractSecureState){
	all l: Int.(s.log) <: linkObjTransition| hasP3LinkObjPermission[s, l.lO_objSource, l.lO_attSource, l.lO_objTarget, l.lO_usr]	
}




run{}
