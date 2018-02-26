module filesystem/doctorappointment/patientepisode/data/SecurePattern3DataExec

open filesystem/doctorappointment/patient_episode/SecurePattern3DoctorAppointment
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
		SecurePattern3DefObj [s, s', p, u] or 
		SecurePattern3DefAtt [s, s', p, patient_name, u]  or 
		SecurePattern3DefAtt [s, s', p, patient_address, u] or 
		SecurePattern3DefObj [s, s', e, u] or
		SecurePattern3DefAtt [s, s', e, episode_reserve_date, u] or 
		SecurePattern3LinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		SecurePattern3LinkObj [s, s', e, patient_episode, p, episode_patient, u] 

}

//É preciso comentar o add to log
//run complete for 4 but 8 SecureState, 5 Int


assert CorrectSecureExecution{
	all s, s': SecureState| 
		secureP3DMInv [s]  and Invariants [s]
			=> secureP3DMInv[s'] and Invariants [s']
}
//Checks
check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
