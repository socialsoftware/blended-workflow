// an activity model where appointments are created first, it violates dependence invariant
module filesystem/doctorappointment/patientepisode/activity/ActivityModelOne

open filesystem/doctorappointment/DoctorAppointment
open filesystem/doctorappointment/patientepisode/Achieve
open filesystem/doctorappointment/patientepisode/Invariants
open filesystem/ActivityModel

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode | 
		registerPatient[s, s', p, e] or
		bookAppointment[s, s', e] or
		skip [s, s']
}

pred registerPatient(s, s': State, p: Patient, e: Episode) {
	preCondition[s, e, none -> none]
	
	postCondition[s, s', p, p -> patient_name + p -> patient_address,  (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred bookAppointment(s, s': State, e: Episode) {
	preCondition[s, none, none -> none]

	postCondition[s, s', e, e -> episode_reserve_date, none -> none -> none]

	dependence[s', e, episode_reserve_date, 0 -> episode_patient, patient_address]
}

// registerPatient preserves the operation
assert RegisterPatientPreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and registerPatient [s, s', p, e] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 2 State, 5 Int

// bookAppointment preserves the operation
assert BookAppointmentPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and bookAppointment [s, s', e] => Invariants [s']
}
// dependence invariant is violated when the dependence verification is removed
//check BookAppointmentPreservesInv for 4 but 2 State, 5 Int

// cannot generate a complete model
run complete for 4 but 6 State, 5 Int
