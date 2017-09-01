module filesystem/doctorappointment/DoctorAppointmentGoalModel

open filesystem/doctorappointment/DoctorAppointment
open filesystem/GoalModel

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment | 
		registerPatient[s, s', p] or
		bookAppointment[s, s', a] or
		associatePatientToAppointment[s, s', p, a] //or
		//skip [s, s']
}

pred registerPatient(s, s': State, p: Patient) {
	actCondition[s, none, none -> none]
	
	sucCondition[s, s', p, p -> patient_name + p -> patient_address]
}

pred bookAppointment(s, s': State, a: Appointment) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', a, a -> appointment_reserve_date]
}

pred associatePatientToAppointment(s, s': State, p, a: Obj) {
	invCondition[s, s',  (p -> patient_appointment -> a) + (a -> appointment_patient -> p)]
}

// registerPatient preserves the operation
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 7 State, 5 Int

// bookAppointment preserves the operation
assert BookAppointmentPreservesInv {
	all s, s': State, a: Appointment |
		Invariants [s] and bookAppointment [s, s', a] => Invariants [s']
}
check BookAppointmentPreservesInv for 4 but 6 State, 5 Int

// bookAppointment preserves the operation
assert AssociatePatientToAppointmentPreservesInv {
	all s, s': State, p: Patient, a: Appointment |
		Invariants [s] and associatePatientToAppointment [s, s', p, a] => Invariants [s']
}
//check AssociatePatientToAppointmentPreservesInv for 4 but 6 State, 5 Int

//run complete for 4 but 7 State, 5 Int
