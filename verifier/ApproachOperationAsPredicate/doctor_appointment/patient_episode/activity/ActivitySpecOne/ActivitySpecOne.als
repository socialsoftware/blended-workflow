// an activity model where patients are registered first, it preserves all invariants
module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/ActivitySpecOne

open filesystem/ActivityConditions
open filesystem/doctorappointment/patientepisode/DoctorAppointment

pred registerPatient(s, s':AbstractState, p: Patient) {
	preCondition[s, none, none -> none]
	postCondition[s, s', p, p -> patient_name, none -> none -> none]
}

pred registerPatientAddress(s, s':AbstractState, p: Patient) {
	preCondition[s, p, none -> none]
	postCondition[s, s', none, p -> patient_address, none -> none -> none]
}

pred createEpisode(s, s': AbstractState, p: Patient, e: Episode) {
	preCondition[s, p, none -> none]
	postCondition[s, s', e, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred bookAppointment(s, s': AbstractState, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none , e -> episode_reserve_date, none -> none -> none]
}
