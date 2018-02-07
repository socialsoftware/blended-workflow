// Contains a doctor appointment model with with four entities
module filesystem/doctorappointment/patientepisode/DoctorAppointment

open filesystem/BWSpec

sig Patient extends Obj {}
one sig patient_name extends FName {} 
one sig patient_address extends FName {} 
one sig patient_episode extends FName {} 

sig Episode extends Obj {}
one sig episode_reserve_date extends FName {} 
one sig episode_patient extends FName {} 

sig reserve_date_dependence extends Dependence {}

fact relations {
	// relation Patient - Episode
	patient_episode.minMul = 0
	patient_episode.maxMul = 10
	patient_episode.inverse = episode_patient

	episode_patient.minMul = 1
	episode_patient.maxMul = 1
	episode_patient.inverse = patient_episode
}

fact dependencies {
	// dependence episode_reserve_date from patient_address
	reserve_date_dependence.sourceObj = Episode
	reserve_date_dependence.sourceAtt = episode_reserve_date
	reserve_date_dependence.sequence =  0 -> episode_patient
	reserve_date_dependence.targetAtt = patient_address
}

// achieve
pred complete {
 	one s: AbstractState | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#s.objects = 2 and
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

// invariants
pred Invariants(s: AbstractState) {
	// no extra fields
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode] 	 	
	noExtraFields [s, Episode, episode_reserve_date + episode_patient]	

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Episode, episode_patient] 
	noMultiplicityExceed [s, Patient, patient_episode] 

	// if there is a link between two objects, either is unidirectional or bidirectional
	bidirectionalPreservation [s, Patient, episode_patient, Episode, patient_episode] 

	// dependencies hold
	checkDependence [s, Episode, reserve_date_dependence]
}


//run {}
