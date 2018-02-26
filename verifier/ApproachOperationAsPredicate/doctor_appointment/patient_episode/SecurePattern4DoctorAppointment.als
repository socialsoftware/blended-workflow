module filesystem/doctorappointment/patient_episode/SecurePattern4DoctorAppointment

open filesystem/doctorappointment/patient_episode/SecureDoctorAppointment
open filesystem/Pattern4/SecurePattern4Spec

sig RegisterPatientActivity extends Activity{}
{
	pre_ent = none 
	pre_att = none -> none
	one p: Patient	| post_entDefs = p
	post_attDefs= post_entDefs-> patient_name  + post_entDefs -> patient_address
 	post_links = none -> none -> none
}

sig CreateEpisodeActivity extends Activity{}
{
	
	one p: Patient	| pre_ent = p 
	no pre_att
	one e: Episode| post_entDefs = e 
	post_attDefs = {post_entDefs -> episode_reserve_date} 
	one d:Doctor|  post_links = {(pre_ent -> patient_episode -> post_entDefs) + (post_entDefs -> episode_patient -> pre_ent) + (d -> doctor_episode -> post_entDefs) + (post_entDefs -> episode_doctor -> d)}
}

sig WriteReportActivity extends Activity{}
{
	one e: Episode | pre_ent = e 
	no pre_att
	no post_entDefs
	post_attDefs = pre_ent -> episode_report
	no post_links
}






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


sig RegisterPatientGoal extends Goal{}
{
	no act_ent 
	no act_att 
	one p: Patient	| suc_entDefs = p
	suc_attDefs= suc_entDefs-> patient_name  + suc_entDefs -> patient_address
 	no post_links
}

sig CreateEpisodeGoal extends Goal{}
{
	one p: Patient	| act_ent = p 
	no act_att
	one e: Episode| suc_entDefs = e 
	suc_attDefs = {suc_entDefs -> episode_reserve_date} 
	one d:Doctor|  suc_links = {(act_ent -> patient_episode -> suc_entDefs) + (suc_entDefs -> episode_patient -> act_ent) + (d -> doctor_episode -> suc_entDefs) + (suc_entDefs -> episode_doctor -> d)}
}

sig WriteReportGoal extends Goal{}
{
	one e: Episode | act_ent = e 
	no act_att
	no suc_entDefs
	suc_attDefs = act_ent -> episode_report
	no post_links
}




run{}
