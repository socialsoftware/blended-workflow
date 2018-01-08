// a complete state to achieve
module filesystem/doctorappointment/complete/complete

open filesystem/DataModel
open filesystem/doctorappointment/DoctorAppointment

pred complete {
 	one s: State | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#Data<: s.objects = 1 and
		#Report <: s.objects = 1 and
		#s.objects = 4 and
		// model is well defined

		// only the correct fields are associated to the entities
		noExtraFields [s, Patient, patient_name + patient_address + patient_episode] and
		noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report]	 and
		noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 and
		noExtraFields [s, Report, report_description + report_episode]	 and

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Episode, episode_reserve_date + episode_checkin + episode_checkout] and
		attributesDefined [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition] and
		attributesDefined [s, Report, report_description] and

		// associations multiplicity
		multiplicityRule [s, Patient, patient_episode] and
		multiplicityRule [s, Episode, episode_patient] and
		multiplicityRule [s, Episode, episode_data] and
		multiplicityRule [s, Data, data_episode] and
		multiplicityRule [s, Episode, episode_report] and
		multiplicityRule [s, Report, report_episode] and


		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and
 		bidirectionalRule [s, Episode, data_episode, Data, episode_data] and
 		bidirectionalRule [s, Episode, report_episode, Report, episode_report] and

		// dependencies hold
		checkDependence [s, Episode, episode_checkout_report_descripton_dependence] and
		checkDependence [s, Episode, episode_checkout_episode_checkin_dependence] and
		checkDependence [s, Report, report_description_data_blood_pressure_dependence]
}
