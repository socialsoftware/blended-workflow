module filesystem/Pattern1/SecurePattern1DataModel

open filesystem/SecureDataModel


sig Pattern1Rules {
	domain_permission: Rights -> Dependence
} 




pred SecureReq1DefObj(s, s' : SecureState, o: Obj, usr:User){
	secureDefObj[s, s', o, usr]
}

pred SecureReq1DefAtt(s, s': SecureState, o: Obj, att: FName, usr:User) {
	secureDefAtt[s, s', o, att, usr]
}

pred SecureReq1LinkObj(s, s': SecureState, objSource: Obj, attSource: FName, objTarget: Obj, attTarget: FName, usr:User) {
	secureLinkObj[s, s', objSource, attSource, objTarget, attTarget, usr]

}

run{}
