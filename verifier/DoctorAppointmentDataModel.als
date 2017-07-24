module DoctorAppointmentDataModel

open DoctorAppointment

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment | 
		defObj [s, s', p] or defObj [s, s', a] or
		defAtt [s, s', p, patient_name] or defAtt [s, s', p, patient_address] or 
		linkObj [s, s', p,  appointment_patient, 1, a, patient_appointment, 100] or
	 	defAtt [s, s', a, appointment_reserve_date] or 
		linkObj [s, s', a, patient_appointment, 100, p, appointment_patient, 1] //or
	//	skip [s, s']
}

// how many instances we are going to use to test the model
fact NumberOfObjects {
	#Patient = 1
	#Appointment = 2
}

// DefObj preserves the operation
assert DefObjPreservesInv {
	all s, s': State | all o: Obj |
		Invariants [s] and defObj [s, s', o] => Invariants [s']
}
//check DefObjPreservesInv for 5

assert DefAttPreservesInv {
	all s, s': State | all o: Obj | all f: FName |
		Invariants [s] and defAtt [s, s', o, f] => Invariants [s']
}
//check DefAttPreservesInv

assert LinkObjPreservesInv {
	all s, s': State | all os, ot: Obj | all rs, rt: FName | all ms, mt: Int |
		Invariants [s] and linkObj [s, s', os, rt, mt, ot, rs, ms] => Invariants [s']
}
check LinkObjPreservesInv for 12

//run complete for 12
