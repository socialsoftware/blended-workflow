// an activity model where appointments are created first, it violates dependence invariant
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
		registerPatient[s, s', p, e] or
		createEpisode[s, s', e] or
		bookAppointment[s, s', e] //or
	//	skip [s, s']
}

pred registerPatient(s, s': State, p: Patient, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', p, p -> patient_name + p -> patient_address,  (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred createEpisode(s, s': State, e: Episode) {
	preCondition[s, none, none -> none]
	postCondition[s, s', e, none -> none, none -> none -> none]
}

pred bookAppointment(s, s': State, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none, e -> episode_reserve_date, none -> none -> none]
}

// registerPatient preserves the operation
assert RegisterPatientPreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and registerPatient [s, s', p, e] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 7 State, 5 Int

// bookAppointment preserves the operation
assert CreateEpisodePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and createEpisode [s, s', e] => Invariants [s']
}
//check CreateEpisodePreservesInv for 4 but 7 State, 5 Int

// bookAppointment preserves the operation
assert BookAppointmentPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and bookAppointment [s, s', e] => Invariants [s']
}
//check BookAppointmentPreservesInv for 4 but 7 State, 5 Int

// cannot generate a complete model
run complete for 4 but 7 State, 5 Int
