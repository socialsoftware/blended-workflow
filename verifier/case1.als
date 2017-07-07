open DataModelRules
open util/integer

open util/ordering [DataModel]

sig DataModel{
	//entity A instances
	DMentA: set entityA,
	//entity A instances attributes
	EnvA: DMentA -> FName -> lone Val,
	//entity B instances
	DMentB: set entityB,
	//entity B instances attributes
	EnvB: DMentB -> FName -> lone Val
}

pred init (m: DataModel) {
	no m.DMentA
	no m.DMentB
	no m.EnvA
	no m.EnvB
}
fact traces {
	init [first]
	no a:entityA| a !in last.DMentA
	no b:entityB| b !in last.DMentB
	all m: DataModel - last | let m' = next [m] |
	some ea: entityA/*, eb: entityB */,i:type_Int//, //str:type_String
		|addA [m, m' ,ea] 
	//	or addB [m, m',  eb] 
	//	or setValueEA1[m, m', ea, str])
		or setValueEA2[m, m', ea, i]
}

pred addA (m, m': DataModel, eA: entityA) {
	//Entity not in the datamodel
	eA not in m.DMentA
	//Entity attributes definition
	ObjFNames [ eA , attributeTwo+ attributeOne+none]
	ObjAttrib [ eA , attributeOne , type_String ]
	ObjAttrib [ eA , attributeTwo , type_Int]
	//Entity A instance is added to the Datamodel
	m'.DMentA = m.DMentA + {eA}
	//The attribute has no value
	no eA.(m'.EnvA)[attributeOne]
	no eA.(m'.EnvA)[attributeTwo]
	//No changes in the rest of the model
	m.DMentB = m'.DMentB
	m.EnvA = m'.EnvA
	m.EnvB = m'.EnvB
}

pred addB (m, m': DataModel, eB: entityB) {
	//Entity not in the datamodel
	eB not in m.DMentB
	//Entity attributes definition
	ObjFNames [ eB , attributeThree + none]
	ObjAttrib [ eB , attributeThree , type_String ]
	//Entity A instance is added to the Datamodel
	m'.DMentB = m.DMentB + {eB}
	//The attribute has no value
	no eB.(m'.EnvB)[attributeThree]
	//No changes in the rest of the model
	m.DMentA = m'.DMentA
	m.EnvA = m'.EnvA
	m.EnvB = m'.EnvB
	
}

pred setValueEA1(m, m': DataModel, eA: entityA, str:type_String){
	//The instance of the entity must exist in the model
	eA in m.DMentA
	//Add a value to the attribute
	m'.EnvA = m.EnvA + {eA -> attributeOne-> str}
	//No changes in the rest of the model
	m.DMentA = m'.DMentA
	m.DMentB= m'.DMentB
	m.EnvB = m'.EnvB
}

pred setValueEA2(m, m': DataModel, eA: entityA, i:type_Int){
	//The instance of the entity must exist in the model
	eA in m.DMentA
	//Add a value to the attribute
	m'.EnvA = m.EnvA + {eA -> attributeOne-> i}
	//No changes in the rest of the model
	m.DMentA = m'.DMentA
	m.DMentB= m'.DMentB
	m.EnvB = m'.EnvB
}



sig entityA extends Obj {}
one sig attributeOne extends FName{}
one sig attributeTwo extends FName{}

sig entityB extends Obj {}
one sig attributeThree extends FName{}


// Concrete value types in model cd
lone sig type_String extends Val {}
lone sig type_Int extends Val {}

/*
//one sig entA extends FName{}
//one sig entB extends FName{}

 fun entityACD : set Obj {
	 entityA
 }
 fun entityBCD : set Obj {
	 entityB
 }
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
