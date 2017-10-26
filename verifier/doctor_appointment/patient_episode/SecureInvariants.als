module filesystem/doctorappointment/patientepisode/SecureInvariants

open filesystem/doctorappointment/SecureDoctorAppointment
open filesystem/doctorappointment/patientepisode/Invariants


pred secureInvariants(s: State){
	Invariants[s]
	
	NoObjectDefinitionWithoutPermission[s, Patient]
	NoObjectDefinitionWithoutPermission[s, Episode]

	NoAttributeDefinitionWithoutPermission[s, Patient, patient_name]
	NoAttributeDefinitionWithoutPermission[s, Patient, patient_address]

	NoAttributeDefinitionWithoutPermission[s, Episode, episode_reserve_date]

	NoSecureLinkWithoutPermission[s, Patient, episode_patient, Episode, patient_episode] 
	NoSecureLinkWithoutPermission[s, Episode, patient_episode, Patient, episode_patient] 
}

