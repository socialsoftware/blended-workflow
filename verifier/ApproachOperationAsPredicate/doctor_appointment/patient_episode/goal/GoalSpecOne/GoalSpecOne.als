// A goal model that cannot achive the top goal because of a dependence
module filesystem/doctorappointment/patientepisode/goal/GoalSpecOne/GoalSpecOne

open filesystem/GoalConditions
open filesystem/doctorappointment/patientepisode/DoctorAppointment

pred registerPatient(s, s': AbstractState, p: Patient) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': AbstractState, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', e, e -> episode_reserve_date, none -> none -> none]
}

pred associatePatientToEpisode(s, s': AbstractState, p: Patient, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', none, none ->none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}
