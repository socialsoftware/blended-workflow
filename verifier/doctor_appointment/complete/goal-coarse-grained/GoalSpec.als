// a goal model 
module filesystem/doctorappointment/complete/goalcoarsegrained/GoalSpec

open filesystem/GoalConditions
open filesystem/doctorappointment/complete/DoctorAppointment

pred processMedicalAppointment(s, s': AbstractState, p: Patient, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', p + e, (p -> patient_name) + (p -> patient_address), (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred reserveDate(s, s': AbstractState, p: Patient, e: Episode) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', none , e -> episode_reserve_date, none -> none-> none]
}

pred checkin(s, s': AbstractState, p: Patient, e: Episode) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', none, e -> episode_checkin, none -> none-> none]
}

pred checkout(s, s': AbstractState, p: Patient, e: Episode) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', none, e -> episode_checkout, none -> none-> none]
}

pred obtainPatientData(s, s': AbstractState, p: Patient, e: Episode, d: Data) {
	actCondition[s, p + e, (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', d, none -> none,  (d -> data_episode -> e) + (e -> episode_data -> d)]
}

pred obtainPhysicalData(s, s': AbstractState, e: Episode, d: Data) {
	actCondition[s, e + d,  (d -> data_episode) + (e -> episode_data)]

	sucCondition[s, s', none, (d -> data_height) + (d -> data_weight), none -> none-> none]
}

pred obtainMedicalData(s, s': AbstractState, e: Episode, d: Data) {
	actCondition[s, e + d, (d -> data_episode) + (e -> episode_data)]
	sucCondition[s, s', none, d -> data_physical_condition, none -> none-> none]
}

pred measureBloodPressure(s, s': AbstractState, d: Data) {
	actCondition[s, d,  d -> data_physical_condition]
	sucCondition[s, s', none, d -> data_blood_pressure, none -> none-> none]
}

pred writeMedicalReport(s, s': AbstractState, p: Patient, e: Episode, r: Report) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', r,  r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r)]
}

