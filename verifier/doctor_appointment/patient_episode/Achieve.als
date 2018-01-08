// generate a complete model
module filesystem/doctorappointment/patientepisode/achieve

open filesystem/doctorappointment/DoctorAppointment

pred complete {
 	one s: State | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 2 and
		#Episode <: s.objects = 2 and
		#s.objects = 4 and
		// model is well defined

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Episode, episode_reserve_date] and

		// associations multiplicity
		multiplicityRule [s, Episode, episode_patient] and
		multiplicityRule [s, Patient, patient_episode] and

		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and

		// dependencies hold
		checkDependence [s, Episode, reserve_date_dependence]
}

run {}
