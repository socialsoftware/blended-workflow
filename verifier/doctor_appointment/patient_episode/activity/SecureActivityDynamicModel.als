module filesystem/doctorappointment/patientepisode/activity/SecureActivityDynamicModel

open filesystem/doctorappointment/SecureDoctorAppointment
open filesystem/SecureActivityModel


pred init (s: DomainSecureState) {
	s.objects = {Doctor1}
	no s.fields
	//The log is empty
	no s.log
/**
* Hard code here the Access control rules
**/	
	//WRITE HERE THE USERS
	s.users = {Alice + Bruno + Carlos}
	Alice.usr_obj = Doctor1
	no Bruno.usr_obj
	no Carlos.usr_obj
	//WRITE HERE THE ROLES
	s.roles={R_Doctor+ R_Receptionist + R_Nurse}
	//WRITE HERE THE ROLES OF EACH USER
	s.u_roles = {
		Alice -> R_Doctor 
		+ Bruno -> R_Receptionist 
		+ Carlos -> R_Nurse}
	//WRITE HERE THE PERMISSIONS OF EACH ROLE
	s.role_permissions = 
		{Def -> ({
			R_Doctor->{ Episode + Report + episode_reserve_date + episode_report + report_episode + report_description}
			+ R_Receptionist->{ Patient + patient_name + patient_address + patient_episode + episode_patient + doctor_episode + episode_doctor }
			})
		}
		+
		{Read -> ({
			R_Doctor-> {Patient + patient_name + patient_address + patient_episode + Episode + episode_reserve_date  + episode_patient}
			+ R_Receptionist->{ Patient + patient_name + patient_address + patient_episode + Episode + episode_reserve_date  + episode_patient}
			})
		}
	//ATRIBUTE THAT HAVE DOMAIN DEPENDENCE
	s.domain_dependent = report_description
	//DOMAIN AC RULES
	s.domain_permission = { 
		Def -> ReportDescription_Doctor -> report_description
	} 

}


fact traces {
	first.init
	all s: DomainSecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User, d: Doctor, r: Report| 
		secureDynamicRegisterPatient[s, s', p, u] or
		secureDynamicBookAppointmentAndAssignDoctor[s, s', p, e, d, u] or
		secureDynamicWriteReport[s, s', p, e, r,  u]// or
	//	skip [s, s']
}



pred secureDynamicRegisterPatient(s, s': DomainSecureState, p: Patient, usr: User) {
	secureDynamicActivity[s, s', none, none -> none, 
										none, none, none -> none, none, 
										p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}


pred secureDynamicBookAppointmentAndAssignDoctor(s, s': DomainSecureState, p: Patient, e: Episode, d: Doctor, usr: User) {
	secureDynamicActivity[s, s', p, none -> none, 
										e, episode_reserve_date, 0 -> episode_patient, patient_address, 
										e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), usr]
}

pred secureDynamicWriteReport(s, s': DomainSecureState, p: Patient, e: Episode, r: Report, usr: User){
	secureDynamicActivity [s, s', p + e, (e -> episode_reserve_date) + (e -> episode_doctor), 
										none, none, none -> none, none, 
										r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r), usr]
}


/////////////////////////////////
assert secureDynamicRegisterPatient {
	all s, s': DomainSecureState, p: Patient, u : User |
		secureDynamicRegisterPatient [s, s', p, u] 
			=> NoDynamicActivityExecutionWithoutPermissions[s', none, none -> none, p, p -> patient_name + p -> patient_address, none -> none -> none]
}
//CHECK
check secureDynamicRegisterPatient for 8


assert secureDynamicBookAppointment{
	all s, s': DomainSecureState, p: Patient, e: Episode, u: User, d: Doctor |
		secureDynamicBookAppointmentAndAssignDoctor[s, s', p, e, d, u]
			=> 	NoDynamicActivityExecutionWithoutPermissions[s', p, none -> none, e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p)+ (d -> doctor_episode -> e) + (e -> episode_doctor -> d) ]
			and NoActivityDepenceWithoutPermissions[s', e, episode_reserve_date, 0 -> episode_patient, patient_address]
}
//CHECK
//check secureDynamicBookAppointment for 8

assert secureDynamicWriteReport{
	all s, s': DomainSecureState, p: Patient, e: Episode, u: User, r: Report |
		secureDynamicWriteReport[s, s', p, e, r, u]
			=> NoDynamicActivityExecutionWithoutPermissions[s', p + e, (e -> episode_reserve_date) + (e -> episode_doctor), r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r)]
}
//Check
//check secureDynamicWriteReport for 4 but 10 FName

pred show{}
//run show 
