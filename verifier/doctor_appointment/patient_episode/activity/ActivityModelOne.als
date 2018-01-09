// an activity model where patients are registered first, it preserves all invariants
module filesystem/doctorappointment/patientepisode/activity/ActivityModelOne

open filesystem/doctorappointment/patientepisode/DoctorAppointment
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
		registerPatientAddress[s, s', p] or
		createEpisode[s, s', p, e] or		
		bookAppointment[s, s', e] // or
		// skip[s, s']
}

pred registerPatient(s, s': State, p: Patient) {
	preCondition[s, none, none -> none]
	postCondition[s, s', p, p -> patient_name, none -> none -> none]
}

pred registerPatientAddress(s, s': State, p: Patient) {
	preCondition[s, p, none -> none]
	postCondition[s, s', none, p -> patient_address, none -> none -> none]
}

pred createEpisode(s, s': State, p: Patient, e: Episode) {
	preCondition[s, p, none -> none]
	postCondition[s, s', e, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred bookAppointment(s, s': State, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none , e -> episode_reserve_date, none -> none -> none]
}

// registerPatient preserves the invariant
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 9 State, 5 Int

// registerPatientAddress preserves the invariant
assert RegisterPatientAddressPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatientAddress [s, s', p] => Invariants [s']
}
//check RegisterPatientAddressPreservesInv for 4 but 9 State, 5 Int

// bookAppointment preserves the invariant
assert CreateEpisodePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and createEpisode [s, s', p, e] => Invariants [s']
}
//check CreateEpisodePreservesInv for 4 but 9 State, 5 Int

// bookAppointment preserves the invariant
assert BookAppointmentPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and bookAppointment [s, s', e] => Invariants [s']
}
//check BookAppointmentPreservesInv for 4 but 9 State, 5 Int

run complete for 4 but 9 State, 5 Int
