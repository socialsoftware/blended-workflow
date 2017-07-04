module DataModelRules



// Adapted alloy model from http://www.se-rwth.de/publications/SM_JOR_BR_cd2alloy_MODELS_11.pdf

// Some markers not belonging to the model
one sig auxilary {}

// Names of fields / associations in classes of the model
abstract sig FName {
	is : one auxilary
}

// Names of enum values in enums of the model
abstract sig EnumVal {}

fact enums {
	// no enum values can exist on their own
	all v : EnumVal | some f : FName | v in Obj . get [ f ]
}

// Parent of all classes relating fields and values
abstract sig Obj {
	get : FName -> { Obj + Val + EnumVal }
}

// Values of fields
abstract sig Val {}

fact values {
	// no values can exist on their own
	all v : Val | some f : FName | v in Obj.get [ f ]
}

// An object has only the fields stated in this predicate and no others
pred ObjFNames [ objs : set Obj , fNames :set FName ] {
	no objs.get [ FName - fNames ]
}

// adds the value of an attribute
pred ObjAttrib [ objs : set Obj , fName :one FName , fType : set {Obj + Val + EnumVal }] {
	objs . get [ fName ] in fType
	all o : objs | one o . get [ fName ]
}


pred ObjLUAttrib [ objs : set Obj , fName :one FName , fType : set Obj , low : Int , up : Int ] {
	ObjLAttrib [ objs , fName , fType , low ]
	ObjUAttrib [ objs , fName , fType , up ]
}

//used to define lower multiplicity
pred ObjLAttrib [ objs : set Obj , fName :one FName , fType : set Obj , low : Int ] {
	objs . get [ fName ] in fType
	all o : objs | (# o . get [ fName ] >= low )
}

//used to define upper multiplicity
pred ObjUAttrib [ objs : set Obj , fName :one FName , fType : set Obj , up : Int ] {
	objs . get [ fName ] in fType
	all o : objs | (# o . get [ fName ] =< up )
}

pred ObjLU [ objs : set Obj , fName :one FName , fType : set Obj , low : Int , up : Int] {
	ObjL [ objs , fName , fType , low ]
	ObjU [ objs , fName , fType , up ]
}

pred ObjL [ objs : set Obj , fName :one FName , fType : set Obj , low : Int] {
	all r : objs | # { l : fType | r in l . get [ fName ]} >= low
}

pred ObjU [ objs : set Obj , fName :one FName , fType : set Obj , up : Int ] {
	all r : objs | # { l : fType | r in l . get [ fName ]} =< up
}

pred BidiAssoc [ left : set Obj , lFName :one FName , right : set Obj , rFName :one FName ] {
	all l : left | all r : l . get [ lFName ] | l in r . get [ rFName ]
	all r : right | all l : r . get [ rFName ] | r in l . get [ lFName ]
}

pred Composition [ left : set Obj , lFName : some FName , right : set Obj ] {
	all r : right | # { l : left , lF : lFName | r in l . get [ lF ]}= 1
}



//Enrichment

sig User{
	hasRole: set Role,
	owns: set Obj
}
sig Role{
	perm: {Obj + FName} -> Operation 
}

abstract sig Operation{}
one sig Write, Read extends Operation{}

pred rolePermissions[role: one Role, opRes:set {Obj + FName} -> Operation ]{
	opRes in role.perm
	no role.perm - opRes
}

pred userRoles[user: one User, roles: set Role]{
	roles in user.hasRole
	no user.hasRole - roles
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



