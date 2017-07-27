module filesystem/doctorappointment/DoctorAppointmentActivityModel

open filesystem/doctorappointment/DoctorAppointment
open filesystem/ActivityModel

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment | 
		registerPatient[s, s', p] or
		bookAppointment[s, s', p, a] or
		skip [s, s']
}

pred registerPatient(s, s': State, p: Patient) {
	preCondition[s, none, none -> none]
	
	postCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': State, p: Patient, a: Appointment) {
	preCondition[s, p, none -> none]

	postCondition[s, s', a, a -> appointment_reserve_date, (p -> patient_appointment -> a) + (a -> appointment_patient -> p)]
}

// registerPatient preserves the operation
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
check RegisterPatientPreservesInv for 4 but 6 State, 5 Int

// bookAppointment preserves the operation
assert BookAppointmentPreservesInv {
	all s, s': State, p: Patient, a: Appointment |
		Invariants [s] and bookAppointment [s, s', p, a] => Invariants [s']
}
//check BookAppointmentPreservesInv for 4 but 6 State, 5 Int

//run complete for 4 but 6 State, 5 Int
