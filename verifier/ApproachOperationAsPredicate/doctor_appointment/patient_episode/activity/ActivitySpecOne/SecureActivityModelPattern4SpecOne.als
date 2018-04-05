module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern4SpecOne

open filesystem/doctorappointment/patientepisode/SecurePattern4DoctorAppointment
open filesystem/SecureActivityConditions


pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureActivity[s, s', none, none -> none, 
							p, (p -> patient_name) +  (p -> patient_address), none -> none -> none, RegisterPatientActivity, usr, none]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, d: Doctor, usr: User) {
	secureActivity[s, s', p, none -> none, 
							e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), CreateEpisodeActivity, usr, none]
}

pred secureWriteReport(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureActivity[s, s', e , none -> none,  
							none, (e -> episode_report), none -> none -> none, WriteReportActivity, usr, none]
}

run{}
