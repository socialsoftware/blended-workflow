module filesystem/DoctorAppointment/StateModel

open filesystem/StateModel
open filesystem/DoctorAppointment/DataModel

pred final {
	one s: State |
    		// Business process goal: define one episode
		defEntity[s, Episode] and

		// Patient
		defAttribute [s, Patient, patient_name + patient_address] and
		noExtraFields [s, Patient, patient_name + patient_address + patient_episode] and

		// Episode
		defAttribute [s, Episode, episode_reserve_date + episode_checkin + episode_checkout] and
		noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient] and

		dependence [s, Episode, episode_checkout_episode_checkin_dependence] and

		multiplicity [s, Patient, patient_episode] and
		multiplicity [s, Episode, episode_patient] and
		bidirectional [s, Patient, patient_episode, Episode]
}


pred invariants(s: State) {
	// only the correct fields are associated to the entities
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode] 
	noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient]	 

	// does not exceeds mutliplicity
	noMultiplicityExceeded [s, Patient, patient_episode] 
	noMultiplicityExceeded [s, Episode, episode_patient] 

	// bidirectional relation
	bidirectional [s, Patient, patient_episode, Episode]

	// dpendences should be verified
	dependence [s, Episode, episode_checkout_episode_checkin_dependence]
}

run {}

