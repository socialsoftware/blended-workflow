module filesystem/doctorappointment/patientepisode/activity/SecureActivityDynamicModel

open filesystem/doctorappointment/SecureDoctorAppointment
open filesystem/SecureActivityModel



pred init (s: SecureState) {
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
	no s.r_ReadPermissions 
}


fact traces {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User | 
		secureDynamicRegisterPatient[s, s', p, u] or
		secureDynamicBookAppointment[s, s', p, e, u] //or
	//	skip [s, s']
}



pred secureDynamicRegisterPatient(s, s': SecureState, p: Patient, usr: User) {
	secureDynamicActivity[s, s', none, none -> none, 
		none, none, none -> none, none, 
		p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}


pred secureDynamicBookAppointment(s, s': SecureState, p: Patient, e: Episode, usr: User) {
	secureDynamicActivity[s, s', p, none -> none, 
		e, episode_reserve_date, 0 -> episode_patient, patient_address, 
		e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
}

