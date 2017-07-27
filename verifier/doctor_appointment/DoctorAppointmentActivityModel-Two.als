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
		registerPatient[s, s', p, a] or
		bookAppointment[s, s', a] or
		skip [s, s']
}

pred registerPatient(s, s': State, p: Patient, a: Appointment) {
	preCondition[s, a, none -> none]
	
	postCondition[s, s', p, p -> patient_name + p -> patient_address,  (p -> patient_appointment -> a) + (a -> appointment_patient -> p)]
}

pred bookAppointment(s, s': State, a: Appointment) {
	preCondition[s, none, none -> none]

	postCondition[s, s', a, a -> appointment_reserve_date, none -> none -> none]
}

// registerPatient preserves the operation
assert RegisterPatientPreservesInv {
	all s, s': State, p: Patient, a: Appointment |
		Invariants [s] and registerPatient [s, s', p, a] => Invariants [s']
}
check RegisterPatientPreservesInv for 4 but 6 State, 5 Int

// bookAppointment preserves the operation
assert BookAppointmentPreservesInv {
	all s, s': State, a: Appointment |
		Invariants [s] and bookAppointment [s, s', a] => Invariants [s']
}
//check BookAppointmentPreservesInv for 4 but 6 State, 5 Int

//run complete for 4 but 6 State, 5 Int
