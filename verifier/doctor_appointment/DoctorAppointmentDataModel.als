// contains the construction of a doctor appointment model using basic operations based on data, it violates some invariants
module filesystem/doctorappointment/DoctorAppointmentDataModel

open filesystem/doctorappointment/DoctorAppointment

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment | 
		defObj [s, s', p] or 
		defAtt [s, s', p, patient_name] or defAtt [s, s', p, patient_address] or 
		defObj [s, s', a] or
	 	defAtt [s, s', a, appointment_reserve_date] or 
		linkObj [s, s', p,  appointment_patient, a, patient_appointment] or
		linkObj [s, s', a, patient_appointment, p, appointment_patient] //or
		//skip [s, s']
}

// how many instances we are going to use to test the model
fact NumberOfObjects {
	#Patient = 2
	#Appointment = 2
}

assert initialState {
	one s: State | init[s] => Invariants [s]
}
//check initialState

// defObj preserves the invariant
assert DefObjPreservesInv {
	all s, s': State | all o: Obj |
		Invariants [s] and defObj [s, s', o] => Invariants [s']
}
// fails for mutliplicity invariant
//check DefObjPreservesInv for 6

// defAtt preserves the invariant
assert DefAttPreservesInv {
	all s, s': State | all o: Obj | all f: FName |
		Invariants [s] and defAtt [s, s', o, f] => Invariants [s'] 
}
// fails for dependency invariant
//check DefAttPreservesInv for 6

// linkObj preserves the invariant
assert LinkObjPreservesInv {
	all s, s': State, os, ot: Obj, rs, rt: FName |
		Invariants [s] and linkObj [s, s', os, rs, ot, rt] => Invariants [s']
}
//check LinkObjPreservesInv for 5 but 5 Int

run complete for 4 but 15 State, 5 Int
