module filesystem/SecureDataConditions

open filesystem/BWSecureSpec
open filesystem/DataConditions


/**
* OBJECT DEFINITION TRANSITION 
**/
sig defObjTransition extends Transition{
	dO_obj: Obj,
	dO_usr: User
}

pred addObjToLog (s, s': AbstractSecureState, o: Obj, usr: User){
	some d: defObjTransition| d.dO_obj = o and d.dO_usr = usr and s'.log = s.log.add[d]
}

/**
* ATTRIBUTE DEFINITION TRANSITION
**/
sig defAttTransition extends Transition{
	dA_att: FName,
	dA_obj: Obj,
	dA_usr: User
}

pred addAttToLog(s, s': AbstractSecureState, o: Obj, att: FName, usr:User) {
	some d: defAttTransition| d.dA_obj = o and d.dA_att = att and d.dA_usr = usr and s'.log = s.log.add[d]
}

/**
* LINK OBJECTS TRANSITION
**/
sig linkObjTransition extends Transition{
	lO_objSource: Obj,
	lO_attSource: FName,
	lO_objTarget: Obj,
	lO_usr: User
}

pred addLinkToLog(s, s': AbstractSecureState, objSource: Obj, attSource: FName, objTarget: Obj, usr:User){
	some l: linkObjTransition| l.lO_objSource = objSource and l.lO_attSource = attSource and l.lO_objTarget = objTarget and l.lO_usr = usr and	s'.log = s.log.add[l] 
}
