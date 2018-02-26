module filesystem/doctorappointment/patient_episode/SecurePattern3DoctorAppointment

open filesystem/doctorappointment/patient_episode/SecureDoctorAppointment
open filesystem/Pattern3/SecurePattern3Spec

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
				Alice->{Episode + episode_reserve_date + episode_patient + Patient + patient_address + patient_name + patient_episode}	
			}	
		+
		Read -> {
				Alice->{Episode + episode_reserve_date + episode_patient + Patient + patient_address + patient_name + patient_episode}
			}	
		}
}





run{}
