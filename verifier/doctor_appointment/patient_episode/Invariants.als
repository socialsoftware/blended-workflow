// invariants for patient and episode
module filesystem/doctorappointment/patientepisode/Invariants

open filesystem/doctorappointment/DoctorAppointment


pred Invariants(s: State) {
	// no extra fields
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode] 	 	
	noExtraFields [s, Episode, episode_reserve_date + episode_patient]	

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Episode, episode_patient] 
	noMultiplicityExceed [s, Patient, patient_episode] 

	// if there is a link between two objects, either is unidirectional or bidirectional
	bidirectionalPreservation [s, Patient, episode_patient, Episode, patient_episode] 

	// dependencies hold
	dependence [s, Episode, episode_reserve_date, 0 -> episode_patient, patient_address]
}
