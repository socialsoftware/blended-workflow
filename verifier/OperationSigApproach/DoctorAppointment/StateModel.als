module filesystem/DoctorAppointment/StateModel

open filesystem/StateModel
open filesystem/DoctorAppointment/DataModel

pred complete {
	one s: AbstractState |
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
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and

		// Data
		defAttribute [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition] and
		noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	and

		multiplicity [s, Episode, episode_data] and
		multiplicity [s, Data, data_episode] and
		bidirectionalRule [s, Episode, data_episode, Data, episode_data] and

		// Report
		defAttribute [s, Report, report_description] and
		noExtraFields [s, Report, report_description + report_episode]	 and

		multiplicity [s, Episode, episode_report] and
		multiplicity [s, Report, report_episode] and
 		bidirectionalRule [s, Episode, report_episode, Report, episode_report] and

		dependence [s, Episode, episode_checkout_report_descripton_dependence] and
		dependence [s, Report, report_description_data_blood_pressure_dependence]
}


pred Invariants(s: AbstractState) {
	// only the correct fields are associated to the entities
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode] 
	noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report]	 
	noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 
	noExtraFields [s, Report, report_description + report_episode]	 

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Patient, patient_episode] 
	noMultiplicityExceed [s, Episode, episode_patient] 
	noMultiplicityExceed [s, Episode, episode_data] 
	noMultiplicityExceed [s, Data, data_episode] 
	noMultiplicityExceed [s, Episode, episode_report] 
	noMultiplicityExceed [s, Report, report_episode] 

	// bidirectional relation
	bidirectionalPreservation [s, Patient, episode_patient, Episode, patient_episode]
 	bidirectionalPreservation [s, Episode, data_episode, Data, episode_data]
 	bidirectionalPreservation [s, Episode, report_episode, Report, episode_report]

	// dpendences should be verified
	dependence [s, Episode, episode_checkout_report_descripton_dependence]
	dependence [s, Episode, episode_checkout_episode_checkin_dependence]
	dependence [s, Report, report_description_data_blood_pressure_dependence]
}


