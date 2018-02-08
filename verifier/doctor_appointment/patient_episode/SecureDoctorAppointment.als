module filesystem/doctorappointment/patientepisode/SecureDoctorAppointment

open filesystem/doctorappointment/patientepisode/DoctorAppointment
open filesystem/BWSecureSpec

one sig Alice, Bob, Carlos extends User{}

one sig R_Doctor, R_Nurse, R_Receptionist extends RoleSubject{}
/*
fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	AccessControlRules.roles={R_Doctor}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor}
	//resources
	AccessControlRules.resources = {Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode}
	//role permissions
	AccessControlRules.role_permissions = 
		{Def -> {
			R_Doctor->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode}
			}
		+
		Read -> {
			R_Doctor-> {Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode}
			}
		}
}
*/
run{}
