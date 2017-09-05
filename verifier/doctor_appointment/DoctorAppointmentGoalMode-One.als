// A goal model that cannot achive the top goal because of a dependence
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

	dependence[s', a, appointment_reserve_date, 0 -> appointment_patient, patient_address]
}

pred associatePatientToAppointment(s, s': State, p, a: Obj) {
	invCondition[s, s',  (p -> patient_appointment -> a) + (a -> appointment_patient -> p)]
}

// registerPatient preserves the invariant
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 7 State, 5 Int

// bookAppointment preserves the invariant
assert BookAppointmentPreservesInv {
	all s, s': State, a: Appointment |
		Invariants [s] and bookAppointment [s, s', a] => Invariants [s']
}
// if the dependence is commented in the operation it will generate a counterexample
check BookAppointmentPreservesInv for 4 but 6 State, 5 Int

// bookAppointment preserves the invariant
assert AssociatePatientToAppointmentPreservesInv {
	all s, s': State, p: Patient, a: Appointment |
		Invariants [s] and associatePatientToAppointment [s, s', p, a] => Invariants [s']
}
//check AssociatePatientToAppointmentPreservesInv for 4 but 6 State, 5 Int

// it is not possible to generate a complete state because book appointment defines appointment and reserve date without associating with a patient
run complete for 4 but 15 State, 5 Int
