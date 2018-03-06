module filesystem/doctorappointment/patientepisode/SecurePattern2DoctorAppointment

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment
open filesystem/Pattern2/SecurePattern2Spec


fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	no AccessControlRules.roles
	//user roles	
	no AccessControlRules.u_roles 
	//resources
	AccessControlRules.resources = {Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor + episode_report}
	//role permissions
	AccessControlRules.permissions = 
		{Def -> {
				{Alice->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor}}
				+ 	{DomainEpisodeDoctor -> episode_report}
			}	
		+
		Read -> {
				{Alice->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor}}
				+ 	{DomainEpisodeDoctor -> episode_report}
			}	
		}
}

run{}
