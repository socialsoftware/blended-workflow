module filesystem/doctorappointment/patientepisode/data/SecureDataModel

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment
open filesystem/SecureDataConditions

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
		secureDefObj [s, s', p, u] or 
		secureDefAtt [s, s', p, patient_name, u]  or 
		secureDefAtt [s, s', p, patient_address, u] or 
		secureDefObj [s, s', e, u] or
		secureDefAtt[s, s', e, episode_reserve_date, u] or 
		secureLinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		secureLinkObj [s, s', e, patient_episode, p, episode_patient, u]
}

//É preciso comentar o add to log
run complete for 4 but 8 SecureState, 5 Int

/**
* Specific Assertions 
* This assertions only verify the permissions and do not verify the model consistensy
**/

//Case where the permission to define Patient is verified
assert CorrectSecureDefPatient{
	all s, s': SecureState, u: User| 
		secureDMInv [s] and Invariants [s] and secureDefObj[s, s', Patient, u]
			=> secureDMInv [s'] and Invariants [s']
}
//Checks
//check CorrectSecureDefPatient for 4 but 2 SecureState, 5 Int

//Case where the permission to define patient_name is verified
assert CorrectSecureDefPatientName{
	all s, s': SecureState, p: Patient, u: User| 
		secureDMInv [s]  and Invariants [s] and  secureDefAtt [s, s', p, patient_name, u] 
			=> secureDMInv [s']  and Invariants [s']
}
//Checks
//scheck CorrectSecureDefPatientName for 4 but 3 SecureState, 5 Int

//Case where the permission to link a Patient and an Episode is verified
assert CorrectSecureLinkPatientEpisode{
	all s, s': SecureState| all p: Patient, e: Episode, u: User|
		secureDMInv [s]  and Invariants [s] and  secureLinkObj [s, s', p,  episode_patient, e, patient_episode, u] 
			=> secureDMInv [s'] and Invariants [s]
}
//Checks
//check CorrectSecureLinkPatientEpisode for 4 but 4 SecureState, 5 Int

//The max sequence lenght is 4, the log can only support a scope of a max 4 operations
assert CorrectSecureExecution{
	all s, s': SecureState| 
		secureDMInv [s] and Invariants [s]
			=> secureDMInv [s'] and Invariants [s']
}
//Checks
//check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
