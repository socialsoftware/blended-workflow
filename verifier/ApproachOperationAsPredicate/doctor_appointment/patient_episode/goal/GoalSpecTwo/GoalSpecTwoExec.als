// fine grained goals
module filesystem/doctorappointment/patientepisode/goal/GoalSpecTwo/GoalSpecTwoExec

open filesystem/doctorappointment/patientepisode/goal/GoalSpecTwo/GoalSpecTwo

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode | 
		defPatient[s, s', p] or
		defPatientAddress[s, s', p] or
		defPatientName[s, s', p] or 
		defEpisode[s, s', e] or
		defEpisodeReserveDate[s, s', e] or
		associatePatientToEpisode[s, s', p, e] //or
		//skip [s, s']
}

// defPatient preserves the invariant
assert DefPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatient [s, s', p] => Invariants [s']
}
//check DefPatientPreservesInv for 4 but 13 State, 5 Int

// defEpisode preserves the invariant
assert DefEpisodePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisode [s, s', e] => Invariants [s']
}
//check DefEpisodePreservesInv for 4 but 13 State, 5 Int

// defEpisodeReserveDate preserves the invariant
assert DefEpisodeReserveDatePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeReserveDate [s, s', e] => Invariants [s']
}
//check DefEpisodeReserveDatePreservesInv for 4 but 13 State, 5 Int

// associatePatientToEpisode preserves the invariant
assert AssociatePatientToEpisodePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and associatePatientToEpisode [s, s', p, e] => Invariants [s']
}
//check AssociatePatientToEpisodePreservesInv for 4 but 13 State, 5 Int

run complete for 4 but 13 State, 5 Int
