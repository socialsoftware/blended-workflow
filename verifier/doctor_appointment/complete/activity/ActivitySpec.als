// an activity model where patients are registered first, it preserves all invariants
module filesystem/doctorappointment/complete/activity/ActivitySpec

open filesystem/ActivityConditions
open filesystem/doctorappointment/complete/DoctorAppointment

pred registerPatient(s, s':AbstractState, p: Patient) {
	preCondition[s, none, none -> none]
	postCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': AbstractState, p: Patient, e: Episode) {
	preCondition[s, p, none -> none]
	postCondition[s, s', e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred checkin(s, s': AbstractState, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none, e -> episode_checkin, none -> none -> none]
}

pred checkout(s, s': AbstractState, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none, e -> episode_checkout, none -> none -> none]
}

pred collectData(s, s': AbstractState, e: Episode, d: Data) {
	preCondition[s, e, none -> none]
	postCondition[s, s', d, d -> {data_height + data_weight + data_blood_pressure + data_physical_condition}, d -> data_episode -> e + e -> episode_data -> d]
}

pred writeReport(s, s': AbstractState, e: Episode, r: Report) {
	preCondition[s, e, none -> none]
	postCondition[s, s', r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r)]
}
