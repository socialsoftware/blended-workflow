// an activity model where patients are registered first, it preserves all invariants
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
		registerPatient[s, s', p] or
		bookAppointment[s, s', p, e] or
		skip [s, s']
}

pred registerPatient(s, s': State, p: Patient) {
	preCondition[s, none, none -> none]
	
	postCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': State, p: Patient, e: Episode) {
	preCondition[s, p, none -> none]

	dependence[s', e, episode_reserve_date, 0 -> episode_patient, patient_address]

	postCondition[s, s', e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

// registerPatient preserves the invariant
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 6 State, 5 Int

// bookAppointment preserves the invariant
assert BookAppointmentPreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and bookAppointment [s, s', p, e] => Invariants [s']
}
//check BookAppointmentPreservesInv for 4 but 6 State, 5 Int

run complete for 4 but 6 State, 5 Int
