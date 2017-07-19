open DataModelRules



sig entityA extends Obj {}
one sig attributeOne extends FName{}
one sig attributeTwo extends FName{}


sig entityB extends Obj {}
one sig attributeThree extends FName{}

sig entityC extends Obj {}

// Concrete value types in model cd
// lone sig type_String extends Val {}
// lone sig type_Int extends Val {}
// lone sig type_Date extends Val {}
one sig Def extends Val {}
one sig Undef extends Val {}

one sig entA extends FName{}
one sig entB extends FName{}
one sig entC extends FName{}


pred cd {
    ObjFNames [ entityA , attributeOne+ attributeTwo + entB + entC + none ]
	ObjAttrib [ entityA , attributeOne , Val ]
	ObjAttrib [ entityA , attributeTwo , Val ]	
	ObjAttrib [ entityA , entB , entityB]
	ObjAttrib [ entityA , entC , entityC]

	ObjFNames [ entityB , attributeThree + entA + entC + none ]
	ObjAttrib [ entityB , attributeThree , Val ]
	ObjAttrib [ entityB , entA, entityA ]
	ObjAttrib [ entityB , entC, entityC ]

	ObjFNames [ entityC , entA + entB + none ]
	ObjAttrib [ entityC , entA, entityA ]
	ObjAttrib [ entityC , entB, entityB ]

	BidiAssoc [ entityA , entB , entityB , entA ]
	ObjLUAttrib [ entityA , entB , entityB , 1, 1]
	ObjLUAttrib [ entityB , entA , entityA , 1, 1]

	BidiAssoc [ entityA , entC , entityC , entA ]
	ObjLUAttrib [ entityA , entC , entityC , 1, 1]
	ObjLUAttrib [ entityC , entA , entityA , 1, 1]

	BidiAssoc [ entityB , entC , entityC , entB ]
	ObjLUAttrib [ entityB , entC , entityC , 1, 2]
	ObjLUAttrib [ entityC , entB , entityB , 1, 2]
}


run cd
