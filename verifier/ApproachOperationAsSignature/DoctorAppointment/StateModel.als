module filesystem/DoctorAppointment/StateModel

open filesystem/StateModel
open filesystem/DoctorAppointment/DataModel

pred final {
	one s: State |
		defEntity[s, Episode] and

		// Patient
		defAttribute [s, Patient, patient_name + patient_address] and
		noExtraFields [s, Patient, patient_name + patient_address + patient_episode] and

		// Episode
		defAttribute [s, Episode, episode_reserve_date + episode_checkin + episode_checkout] and
		noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report] and

		dependence [s, Episode, episode_checkout_episode_checkin_dependence] and

		multiplicity [s, Patient, patient_episode] and
		multiplicity [s, Episode, episode_patient] and
		bidirectional [s, Patient, patient_episode, Episode] and

		// Data
		defAttribute [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition] and
		noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	and

		multiplicity [s, Episode, episode_data] and
		multiplicity [s, Data, data_episode] and
		bidirectional [s, Episode, episode_data, Data] and

		// Report
		defAttribute [s, Report, report_description] and
		noExtraFields [s, Report, report_description + report_episode]	 and

		multiplicity [s, Episode, episode_report] and
		multiplicity [s, Report, report_episode] and
 		bidirectional [s, Episode, episode_report, Report] and

		dependence [s, Episode, episode_checkout_report_descripton_dependence] and
		dependence [s, Report, report_description_data_blood_pressure_dependence]
}


pred invariants(s: State) {
	// only the correct fields are associated to the entities
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode] 
	noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report]	 
	noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 
	noExtraFields [s, Report, report_description + report_episode]	 

	// does not exceeds mutliplicity
	noMultiplicityExceeded [s, Patient, patient_episode] 
	noMultiplicityExceeded [s, Episode, episode_patient] 
	noMultiplicityExceeded [s, Episode, episode_data] 
	noMultiplicityExceeded [s, Data, data_episode] 
	noMultiplicityExceeded [s, Episode, episode_report] 
	noMultiplicityExceeded [s, Report, report_episode] 

	// bidirectional relation
	bidirectional [s, Patient, patient_episode, Episode]
 	bidirectional [s, Episode, episode_data, Data]
 	bidirectional [s, Episode, episode_report, Report]

	// dpendences should be verified
	dependence [s, Episode, episode_checkout_report_descripton_dependence]
	dependence [s, Episode, episode_checkout_episode_checkin_dependence]
	dependence [s, Report, report_description_data_blood_pressure_dependence]
}

run {}

