module filesystem/doctorappointment/patientepisode/data/SecurePattern2DataExec

open filesystem/doctorappointment/patient_episode/SecurePattern2DoctorAppointment
open filesystem/Pattern2/SecurePattern2DataConditions

sig SecureState extends AbstractSecureState{}

pred secureInit (s: SecureState) {
	//objects
	s.objects = DoctorAlice
	//fields
	no s.fields
	//log
	no s.log
}

fact traces {
	first.secureInit
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, d: Doctor, u: User|
		SecurePattern2DefObj [s, s', p, u] or 
		SecurePattern2DefAtt [s, s', p, patient_name, u]  or 
		SecurePattern2DefAtt [s, s', p, patient_address, u] or 
		SecurePattern2DefObj [s, s', e, u] or
		SecurePattern2DefAtt [s, s', e, episode_reserve_date, u] or 
		SecurePattern2LinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		SecurePattern2LinkObj [s, s', e, patient_episode, p, episode_patient, u] or
		SecurePattern2LinkObj [s, s', d,  episode_doctor, e, doctor_episode, u] or
		SecurePattern2LinkObj [s, s', e, doctor_episode, d, episode_doctor, u] or
		SecurePattern2DefAtt [s, s', e, episode_report, u]

}

//É preciso comentar o add to log
run pattern2Complete for 4 but 11 SecureState, 5 Int


assert CorrectSecureExecution{
	all s, s': SecureState| 
		secureP2DMInv [s]  and pattern2Invariants [s]
			=> secureP2DMInv[s'] and pattern2Invariants [s']
}
//Checks
check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
