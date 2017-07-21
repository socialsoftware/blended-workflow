module DoctorAppointment

open StateModel

sig Patient extends Obj {}
one sig patient_name extends FName {}
one sig patient_address extends FName {}
one sig patient_appointment extends FName {}

sig Appointment extends Obj {}
one sig appointment_reserve_date extends FName {}
one sig appointment_patient extends FName {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment 
		| defObj [s, s', p] or defObj [s, s', a] or
		  defAtt [s, s', p, patient_name] or defAtt [s, s', p, patient_address] or linkObj [s, s', p, patient_appointment, a] or
		  defAtt [s, s', a, appointment_reserve_date] or linkObj [s, s', a, appointment_patient, p] //or
		  //skip [s, s']
}

// how many instances we are going to use to test the model
fact NumberOfObjects {
	#Patient = 2
	#Appointment = 2
}

pred Invariants(s: State) {
	// no extra fields
	noExtraFields [s, Patient, patient_name + patient_address + patient_appointment] 	and 	
	noExtraFields [s, Appointment, appointment_reserve_date + appointment_patient]	and

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Appointment, appointment_patient, 1] and
	noMultiplicityExceed [s, Patient, patient_appointment, 100]

	// 
	bidirectionalViolation [s, Patient, patient_appointment, Appointment, appointment_patient] 
}

// DefObj preserves the operation
assert DefObjPreservesInv {
	all s, s': State | all o: Obj |
		Invariants [s] and defObj [s, s', o] => Invariants [s']
}
//check DefObjPreservesInv 

assert DefAttPreservesInv {
	all s, s': State | all o: Obj | all f: FName |
		Invariants [s] and defAtt [s, s', o, f] => Invariants [s']
}
//check DefAttPreservesInv

assert LinkObjPreservesInv {
	all s, s': State | all o1, o2: Obj | all f: FName |
		Invariants [s] and linkObj [s, s', o1, f, o2] => Invariants [s']
}
check LinkObjPreservesInv for 10

assert InvariantsAllStates {
	all s: State | Invariants [s]
}
//check InvariantsAllStates for 10

pred complete {
	one s: State | 
	// cannot be the initial state to find one meaningful state
	#Patient <: s.objects = 1 and
	#Appointment <: s.objects = 2 and
	#s.objects = 3 and
	// model is well defined

	// all attributes are defined
	attributesDefined [s, Patient, patient_name + patient_address]	and
	attributesDefined [s, Appointment, appointment_reserve_date] and

	// associations multiplicity
	multiplicityRule [s, Appointment, appointment_patient, 1, 1] and
	multiplicityRule [s, Patient, patient_appointment, 0, 100] and

	// bidirectional relation
	bidirectionalRule [s, Patient, patient_appointment, Appointment, appointment_patient] 

	and
	sameState[s, s.next]
}
//run complete for 14
