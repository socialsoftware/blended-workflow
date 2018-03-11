module filesystem/doctorappointment/patientepisode/SecurePattern4DoctorAppointment

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment


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
	AccessControlRules.resources = {Patient+ WriteReportActivity +CreateEpisodeActivity +RegisterPatientActivity}
	//role permissions
	 AccessControlRules.permissions = 
		{Def -> 
			{Alice->{RegisterPatientActivity} +
				{R_Doctor-> {CreateEpisodeActivity}} +
				{DomainEpisodeDoctor -> {WriteReportActivity}}
			}	
		}
}


//one sig RegisterPatientGoal extends Goal{}

//one sig CreateEpisodeGoal extends Goal{}

//one sig WriteReportGoal extends Goal{}





run{}
