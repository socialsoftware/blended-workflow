// A goal model that cannot achive the top goal because of a dependence
module filesystem/doctorappointment/patientepisode/goal/GoalSpecOne/GoalSpecOneExec

open filesystem/doctorappointment/patientepisode/goal/GoalSpecOne/GoalSpecOne

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode | 
		registerPatient[s, s', p] or
		bookAppointment[s, s', e] or
		associatePatientToEpisode[s, s', p, e] //or
		//skip [s, s']
}

// registerPatient preserves the invariant
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 7 State, 5 Int

// bookAppointment preserves the invariant
assert BookAppointmentPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and bookAppointment [s, s', e] => Invariants [s']
}
//check BookAppointmentPreservesInv for 4 but 7 State, 5 Int

// associatePatientToEpisode preserves the invariant
assert AssociatePatientToEpisodePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and associatePatientToEpisode [s, s', p, e] => Invariants [s']
}
//check AssociatePatientToEpisodePreservesInv for 4 but 7 State, 5 Int

// it is not possible to generate a complete state because book appointment defines appointment and reserve date without associating with a patient
run complete for 4 but 7 State, 5 Int
