// a goal model 
module filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel

open filesystem/GoalModel
open filesystem/doctorappointment/complete/DoctorAppointment

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode, d: Data, r: Report | 
		processMedicalAppointment [s, s', p, e] or
		reserveDate [s, s', p, e] or
		checkin [s, s', p, e] or
		checkout [s, s', p, e] or
		obtainPatientData [s, s', p, e, d] or
		obtainPhysicalData [s, s', e, d] or
		obtainMedicalData [s, s', e, d] or
		measureBloodPressure [s, s', d] or
		writeMedicalReport [s, s', p, e, r] //or
		//skip [s, s']
}

pred processMedicalAppointment(s, s': State, p: Patient, e: Episode) {
	actCondition[s, none, none -> none]
	sucCondition[s, s', p + e, (p -> patient_name) + (p -> patient_address), (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred reserveDate(s, s': State, p: Patient, e: Episode) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', none , e -> episode_reserve_date, none -> none-> none]
}

pred checkin(s, s': State, p: Patient, e: Episode) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', none, e -> episode_checkin, none -> none-> none]
}

pred checkout(s, s': State, p: Patient, e: Episode) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', none, e -> episode_checkout, none -> none-> none]
}

pred obtainPatientData(s, s': State, p: Patient, e: Episode, d: Data) {
	actCondition[s, p + e, (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', d, none -> none,  (d -> data_episode -> e) + (e -> episode_data -> d)]
}

pred obtainPhysicalData(s, s': State, e: Episode, d: Data) {
	actCondition[s, e + d,  (d -> data_episode) + (e -> episode_data)]

	sucCondition[s, s', none, (d -> data_height) + (d -> data_weight), none -> none-> none]
}

pred obtainMedicalData(s, s': State, e: Episode, d: Data) {
	actCondition[s, e + d, (d -> data_episode) + (e -> episode_data)]
	sucCondition[s, s', none, d -> data_physical_condition, none -> none-> none]
}

pred measureBloodPressure(s, s': State, d: Data) {
	actCondition[s, d,  d -> data_physical_condition]
	sucCondition[s, s', none, d -> data_blood_pressure, none -> none-> none]
}

pred writeMedicalReport(s, s': State, p: Patient, e: Episode, r: Report) {
	actCondition[s, p + e,  (p -> patient_name) + (p -> patient_address) +  (p -> patient_episode) + (e -> episode_patient)]
	sucCondition[s, s', r,  r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r)]
}

