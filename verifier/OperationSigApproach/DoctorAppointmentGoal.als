module filesystem/DoctorAppointmentGoal

open filesystem/GoalModel
open filesystem/DoctorAppointmentState

pred defPatient(s, s': AbstractState, p: Patient) {
	execEntityGoal[s, s', p]
}

pred defPatientName(s, s': AbstractState, p: Patient) {
	execAttributeGoal[s, s', p -> patient_name]
}

pred defPatientAddress(s, s':AbstractState, p: Patient) {
	execAttributeGoal[s, s', p -> patient_address]
}

pred defEpisode(s, s': AbstractState, e: Episode) {
	execEntityGoal[s, s', e]
}

pred defEpisodeReserveDate(s, s': AbstractState, e: Episode) {
	execAttributeGoal[s, s', e -> episode_reserve_date]
}

pred defEpisodeCheckin(s, s': AbstractState, e: Episode) {
	execAttributeGoal[s, s', e -> episode_checkin]
}

pred defEpisodeCheckout(s, s': AbstractState, e: Episode) {
	execAttributeGoal[s, s', e -> episode_checkout]
}

pred linkPatientEpisode(s, s': AbstractState, p: Patient, e: Episode) {
	execMulGoal[s, s', (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred defData(s, s': AbstractState, d: Data) {
	execEntityGoal[s, s', d]
}

pred defDataHeight(s, s': AbstractState, d: Data) {
	execAttributeGoal[s, s', d -> data_height]
}

pred defDataWeight(s, s': AbstractState, d: Data) {
	execAttributeGoal[s, s', d -> data_weight]
}

pred defDataBloodPressure(s, s': AbstractState, d: Data) {
	execAttributeGoal[s, s', d -> data_blood_pressure]
}

pred defDataPhysicalCondition(s, s': AbstractState, d: Data) {
	execAttributeGoal[s, s', d -> data_physical_condition]
}

pred linkEpisodeData(s, s': AbstractState, e: Episode, d: Data) {
	execMulGoal[s, s', d -> data_episode -> e + e -> episode_data -> d]
}

pred defReport(s, s': AbstractState, r: Report) {
	execEntityGoal[s, s', r]
}

pred defReportDescription(s, s': AbstractState, r: Report) {
	execAttributeGoal[s, s', r -> report_description]
}

pred linkEpisodeReport(s, s': AbstractState, e: Episode, r: Report) {
	execMulGoal[s, s', (r -> report_episode -> e) + (e -> episode_report -> r)]
}
