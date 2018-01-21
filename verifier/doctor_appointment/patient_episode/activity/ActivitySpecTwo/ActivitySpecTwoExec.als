// an activity model where appointments are created first, it violates dependence invariant
module filesystem/doctorappointment/patientepisode/activity/ActivitySpecTwo/ActivitySpecTwoExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecTwo/ActivitySpecTwo

sig State extends AbstractState {}

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
