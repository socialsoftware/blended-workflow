module filesystem/doctorappointmenttwoentities/DoctorAppointmentGoalModelTwo

open filesystem/doctorappointmenttwoentities/DoctorAppointment
open filesystem/GoalModel

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment | 
		defPatient[s, s', p] or
		defPatientAddress[s, s', p] or
		defPatientName[s, s', p] or 
		defAppointment[s, s', a] or
		defAppointmentReserveDate[s, s', a] or
		associatePatientToAppointment[s, s', p, a] //or
		//skip [s, s']
}

pred defPatient(s, s': State, p: Patient) {
	actCondition[s, none, none -> none]
	
	sucCondition[s, s', p, none -> none]
}

pred defPatientAddress(s, s': State, p: Patient) {
	actCondition[s, p, none -> none]

	sucCondition[s, s', none,  p -> patient_address]
}

pred defPatientName(s, s': State, p: Patient) {
	actCondition[s, p, none -> none]

	sucCondition[s, s', none,  p -> patient_name]
}

pred defAppointment(s, s': State, a: Appointment) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', a, none -> none]
}

pred defAppointmentReserveDate(s, s': State, a: Appointment) {
	actCondition[s, a, none -> none]

	sucCondition[s, s', none, a -> appointment_reserve_date]

	dependence[s', a, appointment_reserve_date, 0 -> appointment_patient, patient_address]
}

pred associatePatientToAppointment(s, s': State, p, a: Obj) {
	invCondition[s, s',  (p -> patient_appointment -> a) + (a -> appointment_patient -> p)]
}

// defPatient preserves the invariant
assert DefPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatient [s, s', p] => Invariants [s']
}
//check DefPatientPreservesInv for 4 but 7 State, 5 Int

// defAppointment preserves the invariant
assert DefAppointmentPreservesInv {
	all s, s': State, a: Appointment |
		Invariants [s] and defAppointment [s, s', a] => Invariants [s']
}
//check DefAppointmentPreservesInv for 4 but 6 State, 5 Int

// defAppointmentReserveDate preserves the invariant
assert DefAppointmentReserveDatePreservesInv {
	all s, s': State, a: Appointment |
		Invariants [s] and defAppointmentReserveDate [s, s', a] => Invariants [s']
}
check DefAppointmentReserveDatePreservesInv for 4 but 4 State, 5 Int

// associatePatientToAppointment preserves the invariant
assert AssociatePatientToAppointmentPreservesInv {
	all s, s': State, p: Patient, a: Appointment |
		Invariants [s] and associatePatientToAppointment [s, s', p, a] => Invariants [s']
}
//check AssociatePatientToAppointmentPreservesInv for 4 but 6 State, 5 Int

//run complete for 4 but 15 State, 5 Int
