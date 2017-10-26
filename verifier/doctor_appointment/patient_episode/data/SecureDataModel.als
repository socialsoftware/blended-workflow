module filesystem/doctorappointment/patientepisode/data/SecureDataModel



open filesystem/doctorappointment/SecureDoctorAppointment
open filesystem/doctorappointment/patientepisode/SecureInvariants
open filesystem/doctorappointment/patientepisode/data/DataModel

pred securityInit (s: SecureState) {
	no s.objects
	no s.fields
	//The log is empty
	no s.log
/**
* Hard code here the Access control rules
**/	



	//WRITE HERE THE USERS
	s.users = {Alice + Bruno + Carlos}
	//WRITE HERE THE ROLES
	s.roles={R_Doctor+ R_Receptionist + R_Nurse}
	//WRITE HERE THE ROLES OF EACH USER
	s.u_roles = {
		Alice -> R_Doctor 
		+ Bruno -> R_Receptionist 
		+ Carlos -> R_Nurse}
	//WRITE HERE THE PERMISSIONS OF EACH ROLE
	s.r_DefPermissions = {
		R_Doctor->{ Episode + episode_reserve_date}
		+ R_Receptionist->{ Patient + patient_name + patient_address + patient_episode + episode_patient }
		}
	s.r_ReadPermissions = {
		R_Doctor-> {Patient + patient_name + patient_address + patient_episode + Episode + episode_reserve_date  + episode_patient}
		+ R_Receptionist->{ Patient + patient_name + patient_address + patient_episode + Episode + episode_reserve_date  + episode_patient}
		}

}

fact traces {
	first.securityInit
	all s: State - last | let s' = s.next |
		some p: sPatient, e: Episode, u: User| 
		baseSecureDefObj [s, s', p, u]or 
		baseSecureDefAtt [s, s', p, patient_name, u] or baseSecureDefAtt [s, s', p, patient_address, u] or 
		baseSecureDefObj [s, s', e, u] or
	 	baseSecureDefAtt [s, s', e, episode_reserve_date, u] or 
		baseSecureLinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		baseSecureLinkObj [s, s', e, patient_episode, p, episode_patient, u] 
}

/**
* Specific Assertions 
* This assertions only verify the permissions and do not verify the model consistensy
**/
assert SecureDefPatient{
	all s, s': SecureState| all u: User | 
		baseSecureDefObj[s, s', Patient, u] and  NoObjectDefinitionWithoutPermission[s, Patient]
			=> NoObjectDefinitionWithoutPermission[s', Patient] 
}
//Checks
//check SecureDefPatient for 4

assert SecureDefPatientName{
	all s, s': SecureState| all u:User | all p: Patient |
		baseSecureDefAtt[s, s', p, patient_name, u] and NoAttributeDefinitionWithoutPermission[s, p, patient_name]
			=> NoAttributeDefinitionWithoutPermission[s', p, patient_name]
}
//Checks
//check SecureDefPatientName for 4

assert SecureDefPatientAddress{
	all s, s': SecureState| all u:User| all p: Patient |
		baseSecureDefAtt[s, s', p, patient_address, u]  and NoAttributeDefinitionWithoutPermission[s, p, patient_address]
			=> NoAttributeDefinitionWithoutPermission[s', p, patient_address]
}
//Checks
//check SecureDefPatientAddress for 4

assert SecureDefEpisode{
	all s, s': SecureState| all u: User | 
		baseSecureDefObj[s, s', Episode, u] and NoObjectDefinitionWithoutPermission[s, Episode] 
			=> NoObjectDefinitionWithoutPermission[s', Episode]
}
//Checks
//check SecureDefEpisode for 4

assert SecureDefEpisodeReserveDate{
	all s, s': SecureState| all u: User| all e: Episode |
		baseSecureDefAtt[s, s', e, episode_reserve_date, u] and NoAttributeDefinitionWithoutPermission[s, e, episode_reserve_date]
			=> NoAttributeDefinitionWithoutPermission[s', e, episode_reserve_date]
}
//Checks
//check SecureDefEpisodeReserveDate for 4

assert SecureLinkPatientEpisode{
	all s, s': SecureState | all u: User| all p: Patient | all e: Episode |
		baseSecureLinkObj[s, s', p,  episode_patient, e, patient_episode, u] and  NoSecureLinkWithoutPermission[s, p,  episode_patient, e, patient_episode]
			=> NoSecureLinkWithoutPermission[s', p,  episode_patient, e, patient_episode] 
}
//Checks
//check SecureLinkPatientEpisode for 4

assert SecureLinkEpisodePatient{
	all s, s': SecureState | all u: User| all p: Patient | all e: Episode |
		baseSecureLinkObj[s, s', e,  patient_episode, p, episode_patient, u] and NoSecureLinkWithoutPermission[s, e,  patient_episode, p, episode_patient]
			=> NoSecureLinkWithoutPermission[s', e,  patient_episode, p, episode_patient]
}
//Checks
//check SecureLinkEpisodePatient for 4


//run complete for  4 but 15 State, 5 Int
