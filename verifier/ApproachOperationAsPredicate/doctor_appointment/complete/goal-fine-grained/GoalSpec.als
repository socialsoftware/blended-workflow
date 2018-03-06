// a goal model 
module filesystem/doctorappointment/complete/goalfinegrained/GoalSpec

open filesystem/GoalConditions
open filesystem/doctorappointment/complete/DoctorAppointment

pred defPatient(s, s': AbstractState, p: Patient) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', p, none -> none, none -> none -> none]
}

pred defPatientName(s, s': AbstractState, p: Patient) {
	actCondition[s, p, none -> none]
	sucCondition[s, s', none, p -> patient_name, none -> none -> none]
}

pred defPatientAddress(s, s':AbstractState, p: Patient) {
	actCondition[s, p, none -> none]
	sucCondition[s, s', none, p -> patient_address, none -> none -> none]
}

pred defEpisode(s, s': AbstractState, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', e, none -> none, none -> none -> none]
}

pred defEpisodeReserveDate(s, s': AbstractState, e: Episode) {
	actCondition[s, e, none -> none]
	sucCondition[s, s', none , e -> episode_reserve_date, none -> none -> none]
}

pred defEpisodeCheckin(s, s': AbstractState, e: Episode) {
	actCondition[s, e, none -> none]
	sucCondition[s, s', none, e -> episode_checkin, none -> none -> none]
}

pred defEpisodeCheckout(s, s': AbstractState, e: Episode) {
	actCondition[s, e, none -> none]
	sucCondition[s, s', none, e -> episode_checkout, none -> none -> none]
}

pred linkPatientEpisode(s, s': AbstractState, p: Patient, e: Episode) {
	sucCondition[s, s', none, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred defData(s, s': AbstractState, d: Data) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', d, none -> none, none -> none -> none]
}

pred defDataHeight(s, s': AbstractState, d: Data) {
	actCondition[s, d, none -> none]
	sucCondition[s, s', none, d -> data_height, none -> none -> none]
}

pred defDataWeight(s, s': AbstractState, d: Data) {
	actCondition[s, d, none -> none]
	sucCondition[s, s', none, d -> data_weight, none -> none -> none]
}

pred defDataBloodPressure(s, s': AbstractState, d: Data) {
	actCondition[s, d, none -> none]
	sucCondition[s, s', none, d -> data_blood_pressure, none -> none -> none]
}

pred defDataPhysicalCondition(s, s': AbstractState, d: Data) {
	actCondition[s, d, none -> none]
	sucCondition[s, s', none, d -> data_physical_condition, none -> none -> none]
}

pred linkEpisodeData(s, s': AbstractState, e: Episode, d: Data) {
	sucCondition[s, s',  none, none -> none, d -> data_episode -> e + e -> episode_data -> d]
}

pred defReport(s, s': AbstractState, r: Report) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', r, none -> none, none -> none -> none]
}

pred defReportDescription(s, s': AbstractState, r: Report) {
	actCondition[s, r, none -> none]
	sucCondition[s, s', none , r -> report_description, none -> none -> none]
}

pred linkEpisodeReport(s, s': AbstractState, e: Episode, r: Report) {
	sucCondition[s, s', none, none -> none, (r -> report_episode -> e) + (e -> episode_report -> r)]
}
