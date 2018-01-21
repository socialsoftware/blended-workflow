// fine grained goals
module filesystem/doctorappointment/patientepisode/goal/GoalSpecTwo/GoalSpecTwo

open filesystem/GoalConditions
open filesystem/doctorappointment/patientepisode/DoctorAppointment

pred defPatient(s, s': AbstractState, p: Patient) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', p, none -> none, none -> none -> none]
}

pred defPatientAddress(s, s': AbstractState, p: Patient) {
	actCondition[s, p, none -> none]
	sucCondition[s, s', none,  p -> patient_address, none -> none -> none]
}

pred defPatientName(s, s': AbstractState, p: Patient) {
	actCondition[s, p, none -> none]
	sucCondition[s, s', none,  p -> patient_name, none -> none -> none]
}

pred defEpisode(s, s': AbstractState, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', e, none -> none, none -> none -> none]
}

pred defEpisodeReserveDate(s, s': AbstractState, e: Episode) {
	actCondition[s, e, none -> none]
	sucCondition[s, s', none, e -> episode_reserve_date, none -> none -> none]
}

pred associatePatientToEpisode(s, s': AbstractState, p: Patient, e: Episode) {
	sucCondition[s, s', none, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}
