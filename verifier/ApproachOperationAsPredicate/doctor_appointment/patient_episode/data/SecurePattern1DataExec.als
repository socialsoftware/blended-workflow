module filesystem/doctorappointment/patientepisode/data/SecurePattern1DataModel

open filesystem/doctorappointment/patientepisode/SecurePattern1DoctorAppointment
open filesystem/Pattern1/SecurePattern1DataConditions

sig SecureState extends AbstractSecureState{}

pred secureInit (s: SecureState) {
	//objects
	no s.objects
	//fields
	no s.fields
	//log
	no s.log
}

fact traces {
	first.secureInit
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User|
		secureP1DefObj [s, s', p, u] or 
		secureP1DefAtt [s, s', p, patient_name, u]  or 
		secureP1DefAtt [s, s', p, patient_address, u] or 
		secureP1DefObj [s, s', e, u] or
		secureP1DefAtt[s, s', e, episode_reserve_date, u] or 
		secureP1LinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		secureP1LinkObj [s, s', e, patient_episode, p, episode_patient, u]
}

//É preciso comentar o add to log
run complete for 4 but 8 SecureState, 5 Int

/**
* Specific Assertions 
* This assertions only verify the permissions and do not verify the model consistensy
**/

//The max sequence lenght is 4, the log can only support a scope of a max 4 operations
assert CorrectSecureExecution{
	all s, s': SecureState| 
		secureP1DMInv [s] and Invariants [s]
			=> secureP1DMInv [s'] and Invariants [s']
}
//Checks
check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
