// A goal model that cannot achive the top goal because of a dependence
module filesystem/doctorappointment/patientepisode/goal/GoalModelOne

open filesystem/doctorappointment/DoctorAppointment
open filesystem/doctorappointment/patientepisode/Achieve
open filesystem/doctorappointment/patientepisode/Invariants
open filesystem/GoalModel

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

pred registerPatient(s, s': State, p: Patient) {
	actCondition[s, none, none -> none]
	
	sucCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': State, e: Episode) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', e, e -> episode_reserve_date, none -> none -> none]

	dependence[s', e, episode_reserve_date, 0 -> episode_patient, patient_address]
}

pred associatePatientToEpisode(s, s': State, p: Patient, e: Episode) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', none, none ->none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

// registerPatient preserves the invariant
assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
//check RegisterPatientPreservesInv for 4 but 2 State, 5 Int

// bookAppointment preserves the invariant
assert BookAppointmentPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and bookAppointment [s, s', e] => Invariants [s']
}
// if the dependence is commented in the operation it will generate a counterexample
//check BookAppointmentPreservesInv for 4 but 2 State, 5 Int

// associatePatientToEpisode preserves the invariant
assert AssociatePatientToEpisodePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and associatePatientToEpisode [s, s', p, e] => Invariants [s']
}
//check AssociatePatientToEpisodePreservesInv for 4 but 2 State, 5 Int

// it is not possible to generate a complete state because book appointment defines appointment and reserve date without associating with a patient
run complete for 4 but 7 State, 5 Int
