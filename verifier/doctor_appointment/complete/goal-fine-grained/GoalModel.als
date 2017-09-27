// a goal model 
module filesystem/doctorappointment/complete/goalfinegrained/GoalModel

open filesystem/GoalModel
open filesystem/doctorappointment/DoctorAppointment

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode, d: Data, r: Report | 
		defPatient [s, s', p] or
		defPatientName [s, s', p] or
		defPatientAddress [s, s', p] or
		defEpisode [s, s', e] or
		defEpisodeReserveDate [s, s', e] or
		defEpisodeCheckin [s, s', e] or
		defEpisodeCheckout [s, s', e] or
		linkPatientEpisode [s, s', p, e] or
		defData [s, s', d] or
		defDataHeight [s, s', d] or
		defDataWeight [s, s', d] or
		defDataBloodPressure [s, s', d] or
		defDataPhysicalCondition [s, s', d] or
		linkEpisodeData [s, s', e, d] or
		defReport [s, s', r] or
		defReportDescription [s, s', r] or
		linkEpisodeReport [s, s', e, r] //or
	//	skip [s, s']
}

pred defPatient(s, s': State, p: Patient) {
	actCondition[s, none, none -> none]
	
	sucCondition[s, s', p, none -> none, none -> none -> none]
}

pred defPatientName(s, s': State, p: Patient) {
	actCondition[s, p, none -> none]
	
	sucCondition[s, s', none, p -> patient_name, none -> none -> none]
}

pred defPatientAddress(s, s': State, p: Patient) {
	actCondition[s, p, none -> none]
	
	sucCondition[s, s', none, p -> patient_address, none -> none -> none]
}

pred defEpisode(s, s': State, e: Episode) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', e, none -> none, none -> none -> none]
}

pred defEpisodeReserveDate(s, s': State, e: Episode) {
	actCondition[s, e, none -> none]

	sucCondition[s, s', none , e -> episode_reserve_date, none -> none -> none]
}

pred defEpisodeCheckin(s, s': State, e: Episode) {
	actCondition[s, e, none -> none]

	sucCondition[s, s', none, e -> episode_checkin, none -> none -> none]
}

pred defEpisodeCheckout(s, s': State, e: Episode) {
	actCondition[s, e, none -> none]

	sucCondition[s, s', none, e -> episode_checkout, none -> none -> none]

	dependence [s', e, episode_checkout, 0 -> episode_report, report_description]
	dependence [s', e, episode_checkout,  none -> none, episode_checkin]
}

pred linkPatientEpisode(s, s': State, p: Patient, e: Episode) {
	sucCondition[s, s', none, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred defData(s, s': State, d: Data) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', d, none -> none, none -> none -> none]
}

pred defDataHeight(s, s': State, d: Data) {
	actCondition[s, d, none -> none]

	sucCondition[s, s', none, d -> data_height, none -> none -> none]
}

pred defDataWeight(s, s': State, d: Data) {
	actCondition[s, d, none -> none]

	sucCondition[s, s', none, d -> data_weight, none -> none -> none]
}

pred defDataBloodPressure(s, s': State, d: Data) {
	actCondition[s, d, none -> none]

	sucCondition[s, s', none, d -> data_blood_pressure, none -> none -> none]
}

pred defDataPhysicalCondition(s, s': State, d: Data) {
	actCondition[s, d, none -> none]

	sucCondition[s, s', none, d -> data_physical_condition, none -> none -> none]
}

pred linkEpisodeData(s, s': State, e: Episode, d: Data) {
	sucCondition[s, s',  none, none -> none, d -> data_episode -> e + e -> episode_data -> d]
}

pred defReport(s, s': State, r: Report) {
	actCondition[s, none, none -> none]

	sucCondition[s, s', r, none -> none, none -> none -> none]
}

pred defReportDescription(s, s': State, r: Report) {
	actCondition[s, r, none -> none]

	sucCondition[s, s', none , r -> report_description, none -> none -> none]

	dependence [s', r, report_description, 0 -> report_episode + 1 -> episode_data, data_blood_pressure]
}

pred linkEpisodeReport(s, s': State, e: Episode, r: Report) {
	sucCondition[s, s', none, none -> none, (r -> report_episode -> e) + (e -> episode_report -> r)]
}
