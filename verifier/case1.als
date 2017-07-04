open DataModelRules


open util/ordering [DataModel]

sig DataModel{
	DMentA: set entityA,
	DMentB: set entityB,
	EnvA: DMentA -> FName -> lone Val





}

pred init (m: DataModel) {
	no m.DMentA
	no m.DMentB
}
fact traces {
	init [first]
	all m: DataModel - last | let m' = next [m] |
	some ea: entityA, eb: entityB| addA [m, m' ,ea] or addB [m, m',  eb]
}

pred addA (m, m': DataModel, eA: entityA) {
	eA not in m.DMentA
	ObjFNames [ eA , attributeTwo+ attributeOne+none]
	ObjAttrib [ eA , attributeOne , type_String ]
	

	ObjAttrib [ eA , attributeTwo , type_String ]
	
	m'.DMentA = m.DMentA + {eA}
	m.DMentB = m'.DMentB
	no eA.(m'.EnvA)[attributeOne]
	no eA.(m'.EnvA)[attributeTwo]
}

pred addB (m, m': DataModel, eB: entityB) {
	eB not in m.DMentB
	ObjFNames [ eB , none]
	m'.DMentB = m.DMentB + {eB}
	m.DMentA = m'.DMentA
}





 sig entityA extends Obj {}
 sig entityB extends Obj {}

one sig attributeOne extends FName{}
one sig attributeTwo extends FName{}

lone sig type_String extends Val {}
/*

//one sig attributeThree extends FName{}
//one sig entA extends FName{}
//one sig entB extends FName{}

 fun entityACD : set Obj {
	 entityA
 }
 fun entityBCD : set Obj {
	 entityB
 }

// Concrete value types in model cd
lone sig type_String extends Val {}
lone sig type_Date extends Val {}
*/
pred cd {
//	ObjFNames [ entityA , attributeOne+ attributeTwo + none]
//	ObjAttrib [ entityA , attributeOne , type_String ]
	//ObjAttrib [ entityA , attributeTwo , type_Date ]
	
//	ObjFNames [ entityB ,  none ]
//	ObjAttrib [ entityB , attributeThree , type_String ]
	
//	BidiAssoc [ DataModel.DMentA , entA , DataModel.DMentB , entB ]
//	ObjLAttrib [ DataModel.DMentA , entA , DataModel.DMentB , 0]
//	ObjLUAttrib [ DataModel.DMentB , entB , DataModel.DMentA , 1 , 1]
}


run cd for 7
