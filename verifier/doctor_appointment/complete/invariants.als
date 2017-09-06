// invariants for a complete model
module filesystem/doctorappointment/complete/invariants

open filesystem/DataModel
open filesystem/doctorappointment/DoctorAppointment

pred Invariants(s: State) {
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
	bidirectionalPreservation [s, Patient, episode_patient, Episode, patient_episode] and
 	bidirectionalPreservation [s, Episode, data_episode, Data, episode_data] and
 	bidirectionalPreservation [s, Episode, report_episode, Report, episode_report] and

	// if there is a link between two objects, either is unidirectional or bidirectional
	dependence [s, Episode, episode_checkout, 0 -> episode_report, report_description] and
	dependence [s, Episode, episode_checkout,  none -> none, episode_checkin] and
	dependence [s, Report, report_description, 0 -> report_episode + 1 -> episode_data, data_blood_pressure]
}
