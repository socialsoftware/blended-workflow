module filesystem/doctorappointment/patientepisode/data/SecurePattern3DataModel

open filesystem/doctorappointment/patientepisode/SecurePattern3DoctorAppointment
open filesystem/Pattern3/SecurePattern3DataConditions

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
		secureP3DefObj [s, s', p, u] or 
		secureP3DefAtt [s, s', p, patient_name, u]  or 
		secureP3DefAtt [s, s', p, patient_address, u] or 
		secureP3DefObj [s, s', e, u] or
		secureP3DefAtt[s, s', e, episode_reserve_date, u] or 
		secureP3LinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		secureP3LinkObj [s, s', e, patient_episode, p, episode_patient, u]
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
		secureP3DMInv [s] and Invariants [s]
			=> secureP3DMInv [s'] and Invariants [s']
}
//Checks
check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
