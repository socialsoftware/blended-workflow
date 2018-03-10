module filesystem/doctorappointment/patientepisode/SecurePattern1DoctorAppointment

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment


fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	no AccessControlRules.roles 
	//user roles	
	no AccessControlRules.u_roles 
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
