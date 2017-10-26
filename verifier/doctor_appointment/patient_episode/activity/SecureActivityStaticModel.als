module filesystem/doctorappointment/patientepisode/activity/SecureActivityStaticModel

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
	s.r_ReadPermissions = {
		R_Doctor-> {Patient + patient_name + patient_address + patient_episode + Episode + episode_reserve_date  + episode_patient}
		+ R_Receptionist->{ Patient + patient_name + patient_address + patient_episode + Episode + episode_reserve_date  + episode_patient}
		}

}


fact traces {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User | 
		secureStaticRegisterPatient[s, s', p, u] or
		secureStaticBookAppointment[s, s', p, e, u] //or
	//	skip [s, s']
}


pred secureStaticRegisterPatient(s, s': SecureState, p: Patient, usr: User) {
	secureBaseActivity[s, s', none, none -> none, 
		none, none, none -> none, none, 
		p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
	noChangeInAccessControl[s, s']
/*
	secureBasePreCondition[s, none, none -> none, usr]
	secureBasePostCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
*/
/*	
	usr in s.users
	preCondition[s, none, none ->none]
	hasDefObjPermissions[s, p, usr]
	hasDefAttPermissions[s, p, patient_name, usr]
	hasDefAttPermissions[s, p, patient_address, usr]
	postCondition[s, s', p, p -> patient_name + p -> patient_address , none -> none -> none]
*/
}

pred secureStaticBookAppointment(s, s': SecureState, p: Patient, e: Episode, usr: User) {
	secureBaseActivity[s, s', p, none -> none, 
		e, episode_reserve_date, 0 -> episode_patient, patient_address, 
		e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
	noChangeInAccessControl[s, s']
/*
	secureBasePreCondition[s, p, none -> none, usr]
	secureBaseDependence[s', e, episode_reserve_date, 0 -> episode_patient, patient_address, usr]
	secureBasePostCondition[s, s', e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
*/
/*	
	usr in s.users
	UserHasObjReadPermissions[s, p, usr]
	preCondition[s, p, none -> none]

	secureBaseDependence[s', e, episode_reserve_date, 0 -> episode_patient, patient_address, usr]

	hasDefObjPermissions[s, e, usr]
	hasDefAttPermissions[s, e, episode_reserve_date, usr]
	hasLinkObjPermissions[s, p, patient_episode, e, usr]
	hasLinkObjPermissions[s, e, episode_patient, p, usr]
	postCondition[s, s', e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
	
	noChangeInAccessControl[s, s']
*/
}


assert secureRegisterPatient {
	all s, s': SecureState, p: Patient, u : User |
		secureStaticRegisterPatient [s, s', p, u] 
			=> NoActivityExecutionWithoutPermissions[s', none, none -> none, p, p -> patient_name + p -> patient_address, none -> none -> none]
}
//CHECK
//check secureRegisterPatient for 4 but 6 State, 5 Int


assert secureBookAppointment{
	all s, s': SecureState, p: Patient, e: Episode, u: User |
	NoActivityExecutionWithoutPermissions[s, p, none -> none, e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) ]
	and NoActivityDepenceWithoutPermissions[s, e, episode_reserve_date, 0 -> episode_patient, patient_address]
	and secureStaticBookAppointment[s, s', p, e, u]
			=> 	NoActivityExecutionWithoutPermissions[s', p, none -> none, e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) ]
			and NoActivityDepenceWithoutPermissions[s', e, episode_reserve_date, 0 -> episode_patient, patient_address]
}
//CHECK
check secureBookAppointment for 4 but 6 State, 5 Int

pred complete{}
//run complete for 2
