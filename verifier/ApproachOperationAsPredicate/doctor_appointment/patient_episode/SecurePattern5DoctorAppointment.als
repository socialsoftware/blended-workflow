module filesystem/doctorappointment/patient_episode/SecurePattern5DoctorAppointment

open filesystem/doctorappointment/patient_episode/SecureDoctorAppointment

one sig RegisterPatientActivity extends Activity{}

one sig CreateEpisodeActivity extends Activity{}

one sig WriteReportActivity extends Activity{}


/*
fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	AccessControlRules.roles={R_Doctor + R_Bob}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor + Bob -> R_Bob}
	//resources
	AccessControlRules.resources = {WriteReportActivity +CreateEpisodeActivity +RegisterPatientActivity + WriteReportGoal + CreateEpisodeGoal + RegisterPatientGoal
														+Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor + episode_report}
	//role permissions
	 AccessControlRules.permissions = 
		{Def -> {
				{Alice->{Episode + episode_reserve_date + episode_patient + Patient + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor}}
				+ 	{DomainEpisodeDoctor -> episode_report}
				+ {Bob -> patient_name} 
			}	
		+
		Read -> {
				{Alice->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor}}
				+ 	{DomainEpisodeDoctor -> episode_report}
			}	
		}
	AccessControlRules.propagation ={RegisterPatientActivity -> R_Bob}
}
*/

fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	AccessControlRules.roles={R_Doctor + R_Bob}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor + Bob -> R_Bob}
	//resources
	AccessControlRules.resources = {WriteReportActivity +CreateEpisodeActivity +RegisterPatientActivity + WriteReportGoal + CreateEpisodeGoal + RegisterPatientGoal}
	//role permissions
	 AccessControlRules.permissions = 
		{Def -> 
			{Alice->{RegisterPatientActivity + RegisterPatientGoal} +
				{R_Doctor-> {CreateEpisodeActivity + CreateEpisodeGoal}} +
				{DomainEpisodeDoctor -> {WriteReportActivity + WriteReportGoal}}
			}	
		}
	AccessControlRules.propagation ={RegisterPatientActivity -> R_Bob}
}


one sig RegisterPatientGoal extends Goal{}

one sig CreateEpisodeGoal extends Goal{}

one sig WriteReportGoal extends Goal{}





run{}
