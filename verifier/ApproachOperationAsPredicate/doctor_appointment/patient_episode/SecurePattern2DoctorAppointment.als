module filesystem/doctorappointment/patient_episode/SecurePattern2DoctorAppointment

open filesystem/doctorappointment/patient_episode/SecurePattern1DoctorAppointment
open filesystem/Pattern2/SecurePattern2Spec

fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	AccessControlRules.roles={R_Doctor}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor}
	//resources
	AccessControlRules.resources = {Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor + episode_report}
	//role permissions
	AccessControlRules.permissions = 
		{Def -> {
				{R_Doctor->{Episode + episode_reserve_date + episode_patient + Patient + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor}}
				+ 	{DomainEpisodeDoctor -> episode_report}
				+ {Alice -> patient_name}
			}	
		+
		Read -> {
				{R_Doctor->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor}}
				+ 	{DomainEpisodeDoctor -> episode_report}
			}	
		}
}





run{}
