module filesystem/doctorappointment/patient_episode/SecurePattern4DoctorAppointment

open filesystem/doctorappointment/patient_episode/SecureDoctorAppointment
open filesystem/Pattern4/SecurePattern4Spec

one sig RegisterPatientActivity extends Activity{}

one sig CreateEpisodeActivity extends Activity{}

one sig WriteReportActivity extends Activity{}







fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	AccessControlRules.roles={R_Doctor}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor}
	//resources
	AccessControlRules.resources = {Patient+ WriteReportActivity +CreateEpisodeActivity +RegisterPatientActivity + WriteReportGoal + CreateEpisodeGoal + RegisterPatientGoal}
	//role permissions
	 AccessControlRules.permissions = 
		{Def -> 
			{Alice->{RegisterPatientActivity + RegisterPatientGoal} +
				{R_Doctor-> {CreateEpisodeActivity + CreateEpisodeGoal}} +
				{DomainEpisodeDoctor -> {WriteReportActivity + WriteReportGoal}}
			}	
		}
}


one sig RegisterPatientGoal extends Goal{}

one sig CreateEpisodeGoal extends Goal{}

one sig WriteReportGoal extends Goal{}





run{}
