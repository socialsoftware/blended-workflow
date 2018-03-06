module filesystem/BWSecureSpec

open filesystem/BWSpec


/**
* ACCESS CONTROl SPECIFIC SIGNATURES
**/
abstract sig User extends Subject{
	usr_obj: set Obj
}

abstract sig RoleSubject extends Subject{}

abstract sig Rights{}

one sig Def, Read extends Rights{}

abstract sig Transition{}

one sig AccessControlRules {
	users: set User,
	roles: set RoleSubject,
	u_roles: users -> set roles,
	resources: set {Obj + FName + Operation},
	permissions: Rights ->  Subject ->  resources,
}

abstract sig Subject{}

abstract sig Operation{}

abstract sig AbstractSecureState extends AbstractState {
	//Log of the operations made in each transition
	log: seq Transition
}


abstract sig DomainSubject extends Subject{
	path: seq FName
}

run{}




 
