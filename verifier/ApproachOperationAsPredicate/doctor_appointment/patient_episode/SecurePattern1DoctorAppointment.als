module filesystem/doctorappointment/patientepisode/SecurePattern1DoctorAppointment

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment
open filesystem/Pattern1/SecurePattern1Spec


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
	AccessControlRules.permissions = 
		{Def -> {
			R_Doctor->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode}
			}
		+
		Read -> {
			R_Doctor-> {Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode}
			}
		}
}


run{}
