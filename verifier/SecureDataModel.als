module filesystem/SecureDataModel

open filesystem/DataModel
open util/integer

abstract sig User{}

abstract sig Role{}

abstract sig Operation{}

one sig Write, Read extends Operation{}

abstract sig Transition{}

sig defObj extends Transition{
	dO_obj: Obj,
	dO_usr: User
}

sig defAtt extends Transition{
	dA_att:FName,
	dA_obj: Obj,
	dA_usr: User
}

sig linkObj extends Transition{
	lO_objSource: Obj,
	lO_roleSource: FName,
	lO_objTarget: Obj,
	lO_roleTarget: FName,
	lO_usr: User
}

sig SecureState extends State{
	//Log of the operations made in each transition
	log: seq Transition,

	//Access control specifications
	users: set User,
	roles: set Role,
	u_roles: users -> set roles,
	r_permissions: roles -> set({Obj+FName} -> Operation),

	//User owned Objects
	u_owned:users -> objects,
} 

//The DataModel state remains the same
pred noChangeInState(s,s' :SecureState){
	s'.objects = s.objects
	s'.fields = s.fields
}

pred noChangeInAccessControl(s,s' :SecureState){
	s'.users = s.users
	s'.roles = s.roles
	s'.u_roles = s.u_roles
	s'.r_permissions = s.r_permissions
}


pred secureDefObj(s, s' : SecureState, o: Obj, usr:User) {
	usr in s.users
	//User has Write permission over the object
	let usr_perm =  (usr.(s.u_roles)).(s.r_permissions)
		|o in usr_perm.Write

	defObj[s,s',o]

	s'.u_owned = s.u_owned + usr -> o
	noChangeInAccessControl[s, s']

	//add to log
//	s'.log = s.log + (s.step -> defObj)
//	(s.step).(s'.log).dO_obj=o 
	//and	(s.step).(s'.log).dO_usr=usr
	//Increase the step
	//s'.step = 1.add[s.step]
}


pred secureDefAtt(s, s': State, o: Obj, att: FName, usr:User) {
	usr in s.users
	let usr_perm = (usr.(s.u_roles)).(s.r_permissions)|
		//Write or Read permission or own the Object
		(o in usr_perm.Operation or o in usr.(s.u_owned))
		//Write permission on the attribute
		and att in usr_perm.Write
	
	defAtt[s,s',o,att]

	noChangeInAccessControl[s, s']

	//add to log
//	s'.log = s.log + (s.step -> defAtt)
	//(s.step).(s'.log).dA_att=att
	//and(s.step).(s'.log).dA_obj=o
	//and (s.step).(s'.log).dA_usr=usr
	//Increase the step
	//s'.step = 1.add[s.step]
}

pred secureLinkObj(s, s': State, objSource: Obj, roleSource: FName, objTarget: Obj, roleTarget: FName, usr:User) {
	usr in s.users
	let usr_perm = (usr.(s.u_roles)).(s.r_permissions)|
		//Write permission or own the source
		(objSource in usr_perm.Write or objSource in usr.(s.u_owned))
		//Write or Read permission or own the target
		and (objSource in usr_perm.Operation or objSource in usr.(s.u_owned))
	
	linkObj[s, s', objSource, roleSource, objTarget, roleTarget]

	noChangeInAccessControl[s, s']

	//add to log
	//s'.log = s.log + (s.step -> linkObj)
	//(s.step).(s'.log).lO_objSource = objSource
	//and(s.step).(s'.log).lO_roleSource= roleSource
	//and (s.step).(s'.log).lO_objTarget= objTarget
	//and (s.step).(s'.log).lO_roleTarget= roleTarget
//	and (s.step).(s'.log).lO_usr= usr
	//Increase the step
//	s'.step = 1.add[s.step]

}


/*
	Criar um secure complete que verifica: atige o estado completo com os invariantes e adicionalmente
	é correctamente aplicado o controlo de accesso

*/

run {}

