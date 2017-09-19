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
	s.roles={Doctor+ Receptionist + Nurse}
	//WRITE HERE THE ROLES OF EACH USER
	s.u_roles = {
		Alice -> Doctor 
		+ Bruno -> Receptionist 
		+ Carlos -> Nurse}
	//WRITE HERE THE PERMISSIONS OF EACH ROLE
	s.r_permissions = {
		Doctor->{ 
				Patient -> Read
				+ patient_name -> Read + patient_address -> Read + patient_episode->Read
				+Episode -> Write
				+episode_reserve_date -> Write
			}
		+ Receptionist->{ 
				Patient -> Write 
				+ patient_name -> Write + patient_address -> Write + patient_episode->Write
				+ Episode ->Read
				+ episode_reserve_date -> Write + episode_patient -> Write
			}
		}
	//No user owned Objects
	no s.u_owned


}

fact traces {
	first.securityInit
	all s: State - last | let s' = s.next |
		some p: Patient, e: Episode, u: User| 
		secureDefObj [s, s', p, u]or 
		secureDefAtt [s, s', p, patient_name, u] or secureDefAtt [s, s', p, patient_address, u] or 
		secureDefObj [s, s', e, u] or
		secureDefEpisodeReserveDate[s, s', e, u] or
	// 	secureDefAtt [s, s', e, episode_reserve_date, u] or 
		secureLinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		secureLinkObj [s, s', e, patient_episode, p, episode_patient, u]
}



pred secureDefEpisodeReserveDate(s, s': State, e: Episode, usr: User){
	hasDefAttPermissions[s, e, episode_reserve_date, usr]
	hasPathObjPermissions[s, e, 0 -> episode_patient, usr]
	defEpisodeReserveDate[s, s', e]
}

/**
* Specific Assertions 
ADicIONAR AO ESTADO ANTERIOR VERIFICAção

**/
assert SecureDefPatient{
	all s, s': SecureState| all u: User | 
		secureDefObj[s, s', Patient, u] and Invariants[s] and  NoObjectDefinitionWithoutPermission[s, Patient]
			=> NoObjectDefinitionWithoutPermission[s', Patient] and Invariants[s'] 
}
//Checks
check SecureDefPatient for 4 but 2 SecureState

assert SecureDefPatientName{
	all s, s': SecureState| all u:User | all p: Patient |
		secureDefAtt[s, s', p, patient_name, u]  and Invariants[s] 
			=> NoAttributeDefinitionWithoutPermission[s', p, patient_name] and Invariants[s']
}
//Checks
//check SecureDefPatientName for 2

assert SecureDefPatientAddress{
	all s, s': SecureState| all u:User| all p: Patient |
		secureDefAtt[s, s', p, patient_address, u]  and Invariants[s] 
			=> NoAttributeDefinitionWithoutPermission[s', p, patient_address] and Invariants[s']
}
//Checks
//check SecureDefPatientAddress for 2

assert SecureDefEpisode{
	all s, s': SecureState| all u: User | 
		secureDefObj[s, s', Episode, u] and Invariants[s] 
			=> NoObjectDefinitionWithoutPermission[s', Episode] and Invariants[s'] 
}
//Checks
//check SecureDefEpisode for 2

assert SecureDefEpisodeReserveDate{
	all s, s': SecureState| all u: User| all e: Episode |
		secureDefEpisodeReserveDate[s, s', e, u]  and Invariants[s] 
			=> NoAttributeDefinitionWithoutPermission[s', e, episode_reserve_date] and Invariants[s']
}
//Checks
//check SecureDefEpisodeReserveDate for 4

assert SecureLinkPatientEpisode{
	all s, s': SecureState | all u: User| all p: Patient | all e: Episode |
		secureLinkObj[s, s', p,  episode_patient, e, patient_episode, u] and Invariants[s] 
			=> NoSecureLinkWithoutPermission[s', p,  episode_patient, e, patient_episode]
}
//Checks
//check SecureLinkPatientEpisode for 4

assert SecureLinkEpisodePatient{
	all s, s': SecureState | all u: User| all p: Patient | all e: Episode |
		secureLinkObj[s, s', e,  patient_episode, p, episode_patient, u] and Invariants[s] 
			=> NoSecureLinkWithoutPermission[s', e,  patient_episode, p, episode_patient]
}
//Checks
//check SecureLinkEpisodePatient for 4

/**
*Generic Assertions
**/
assert SecureDefObjPreservesInv {
	all s, s': SecureState | all o: Obj | all u: User |
		secureInvariants [s] and secureDefObj [s, s', o, u] => secureInvariants [s']
}
//FAILS
//check SecureDefObjPreservesInv for 2

assert SecureDefAttPreservesInv {
	all s, s': SecureState | all o: Obj | all f: FName - episode_reserve_date| all u: User |
		secureInvariants [s] and secureDefAtt [s, s', o, f, u] => secureInvariants [s'] 
}
//Checks
//check SecureDefAttPreservesInv for 2

assert SecureLinkObjPreservesInv {
	all s, s': SecureState| all os, ot: Obj| all rs, rt: FName | all u: User |
		secureInvariants [s] and secureLinkObj [s, s', os, rs, ot, rt, u] => secureInvariants [s']
}
//Checks
//check SecureLinkObjPreservesInv for 2

//run complete for  4 but 15 State, 5 Int
