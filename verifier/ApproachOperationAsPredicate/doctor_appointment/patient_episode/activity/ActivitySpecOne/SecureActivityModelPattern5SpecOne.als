module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern5SpecOne

open filesystem/doctorappointment/patientepisode/SecurePattern5DoctorAppointment
open filesystem/SecureActivityConditions



pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usrSource, usrTarget: User) {
	secureActivity[s, s', none, none -> none, 
							p, (p -> patient_name) +  (p -> patient_address), none -> none -> none, RegisterPatientActivity, usrSource, usrTarget]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, d: Doctor,  usrSource, usrTarget: User){
	secureActivity[s, s', p, none -> none, 
							e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), CreateEpisodeActivity, usrSource, usrTarget]
}

pred secureWriteReport(s, s': AbstractSecureState, e: Episode, usrSource, usrTarget: User) {
	secureActivity[s, s', e , none -> none,  
							none, (e -> episode_report), none -> none -> none, WriteReportActivity, usrSource, usrTarget]
}
