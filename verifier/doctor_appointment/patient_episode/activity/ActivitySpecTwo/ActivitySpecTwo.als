// an activity model where appointments are created first, it violates dependence invariant
module filesystem/doctorappointment/patientepisode/activity/ActivitySpecTwo/ActivitySpecTwo

open filesystem/ActivityConditions
open filesystem/doctorappointment/patientepisode/DoctorAppointment

pred registerPatient(s, s': AbstractState, p: Patient, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', p, p -> patient_name + p -> patient_address,  (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred createEpisode(s, s': AbstractState, e: Episode) {
	preCondition[s, none, none -> none]
	postCondition[s, s', e, none -> none, none -> none -> none]
}

pred bookAppointment(s, s': AbstractState, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none, e -> episode_reserve_date, none -> none -> none]
}
