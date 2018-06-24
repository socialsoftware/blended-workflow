// an activity model where patients are registered first, it preserves all invariants
module filesystem/doctorappointment/example/activity/SecureActivitySpec

open filesystem/doctorappointment/example/SecureAccessControlRules
open filesystem/SecureActivityConditions

pred registerPatient(s, s':AbstractState, p: Patient, pu: Patient_User, u:User) {
	secureActivity[s, s', none, none->none, p, p->patient_name + p->patient_address, (p->patient_user->pu) + (pu->user_patient->p), registerPatient/*op*/, u, none, Dan]
//	preCondition[s, none, none -> none]
//	postCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': AbstractState, p: Patient, e: Episode, d: Doctor_User, u:User) {
	secureActivity[s, s', p, none->none, e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e ->episode_doctor ->d), bookAppointment/*op*/, u, none, none]
//	preCondition[s, p, none -> none]
//	postCondition[s, s', e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred checkin(s, s': AbstractState, e: Episode, u:User) {
	secureActivity[s, s', e, none->none, none, e -> episode_checkin, none -> none -> none, checkin/*op*/, u, none, none]
	//preCondition[s, e, none -> none]
	//postCondition[s, s', none, e -> episode_checkin, none -> none -> none]
}

pred checkout(s, s': AbstractState, e: Episode, u:User) {
	secureActivity[s, s', e, none->none, none, e -> episode_checkout, none -> none -> none, checkout/*op*/, u, none, none]
//	preCondition[s, e, none -> none]
//	postCondition[s, s', none, e -> episode_checkout, none -> none -> none]
}

pred collectData(s, s': AbstractState, e: Episode, d: Data, u1, u2:User) {
	secureActivity[s, s', e, none->none, d, d -> {data_height + data_weight + data_blood_pressure + data_physical_condition}, d -> data_episode -> e + e -> episode_data -> d, collectData/*op*/, u1, u2, none]
//	preCondition[s, e, none -> none]
//	postCondition[s, s', d, d -> {data_height + data_weight + data_blood_pressure + data_physical_condition}, d -> data_episode -> e + e -> episode_data -> d]
}

pred collectData(s, s': AbstractState, e: Episode, d: Data, u1: User) {
	secureActivity[s, s', e, none->none, d, d -> {data_height + data_weight + data_blood_pressure + data_physical_condition}, d -> data_episode -> e + e -> episode_data -> d, collectData/*op*/, u1, none, none]
//	preCondition[s, e, none -> none]
//	postCondition[s, s', d, d -> {data_height + data_weight + data_blood_pressure + data_physical_condition}, d -> data_episode -> e + e -> episode_data -> d]
}

pred writeReport(s, s': AbstractState, e: Episode, r: Report, u:User) {
	secureActivity[s, s', e, none->none,  r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r), writeReport/*op*/, u, none, none]
//	preCondition[s, e, none -> none]
//	postCondition[s, s', r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r)]
}

run{} 
