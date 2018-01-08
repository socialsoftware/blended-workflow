// fine grained goals
module filesystem/doctorappointment/patientepisode/goal/GoalModelTwo

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
		defPatient[s, s', p] or
		defPatientAddress[s, s', p] or
		defPatientName[s, s', p] or 
		defEpisode[s, s', e] or
		defEpisodeReserveDate[s, s', e] or
		associatePatientToEpisode[s, s', p, e] //or
		//skip [s, s']
}

pred defPatient(s, s': State, p: Patient) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', p, none -> none, none -> none -> none]
}

pred defPatientAddress(s, s': State, p: Patient) {
	actCondition[s, p, none -> none]
	sucCondition[s, s', none,  p -> patient_address, none -> none -> none]
}

pred defPatientName(s, s': State, p: Patient) {
	actCondition[s, p, none -> none]
	sucCondition[s, s', none,  p -> patient_name, none -> none -> none]
}

pred defEpisode(s, s': State, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', e, none -> none, none -> none -> none]
}

pred defEpisodeReserveDate(s, s': State, e: Episode) {
	actCondition[s, e, none -> none]
	sucCondition[s, s', none, e -> episode_reserve_date, none -> none -> none]
}

pred associatePatientToEpisode(s, s': State, p: Patient, e: Episode) {
	sucCondition[s, s', none, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

// defPatient preserves the invariant
assert DefPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatient [s, s', p] => Invariants [s']
}
//check DefPatientPreservesInv for 4 but 15 State, 5 Int

// defEpisode preserves the invariant
assert DefEpisodePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisode [s, s', e] => Invariants [s']
}
//check DefEpisodePreservesInv for 4 but 15 State, 5 Int

// defEpisodeReserveDate preserves the invariant
assert DefEpisodeReserveDatePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeReserveDate [s, s', e] => Invariants [s']
}
check DefEpisodeReserveDatePreservesInv for 4 but 15 State, 5 Int

// associatePatientToEpisode preserves the invariant
assert AssociatePatientToEpisodePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and associatePatientToEpisode [s, s', p, e] => Invariants [s']
}
//check AssociatePatientToEpisodePreservesInv for 4 but 15 State, 5 Int

run complete for 4 but 15 State, 5 Int
