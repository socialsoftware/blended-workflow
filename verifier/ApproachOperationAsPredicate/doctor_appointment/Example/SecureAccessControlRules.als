module filesystem/doctorappointment/example/SecureAccessControlRules

open filesystem/doctorappointment/example/SecureDoctorAppointment

fact acrules{
	//users
	AccessControlRules.users = {Alice + Bob + Carl + Dan}
	//roles
	AccessControlRules.roles = {R_Doctor + R_Nurse + R_Receptionist + R_Patient}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor + Bob -> R_Nurse + Carl -> R_Receptionist + Dan -> R_Patient} 


	//resources
	AccessControlRules.resources = {
		Patient +  patient_name + patient_address + patient_episode + patient_user +
		Patient_User + user_patient +
		Episode + episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report + episode_doctor +
		Data + data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode +
		Report + report_description + report_episode +
		Doctor_User + doctor_episode +
		registerPatient + bookAppointment + checkin + checkout + collectData + writeReport
	}
	//role permissions
	AccessControlRules.permissions = 
		{Def -> {
				(DomainEpisodePatient -> {episode_checkin + episode_checkout}) +
				(R_Receptionist -> {registerPatient + Episode + episode_reserve_date + patient_episode + episode_patient + doctor_episode + episode_doctor + episode_checkin + episode_checkout }) +
				(R_Nurse -> {Data + data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode + episode_data}) +
				(DomainReportDoctor -> {writeReport})
			}	
		+
		Read -> {
				(R_Patient->{Patient +  patient_name + patient_address + Episode}) +
				(R_Receptionist->{Patient + Episode})	 +
				(R_Nurse -> {Episode})
			}	
		}
	AccessControlRules.delegation = {collectData -> R_Doctor}
}

run{}


