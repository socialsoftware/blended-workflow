module filesystem/doctorappointment/patientepisode/activity/ActivitySpecTwo/SecureActivitySpecTwo

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment
open filesystem/SecureActivityConditions


pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureActivity[s, s', e, none -> none, 
							p, p -> patient_name + p -> patient_address,  (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
}

pred secureCreateEpisode(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureActivity[s, s', none, none -> none, 
							 e, none -> none, none -> none -> none, usr]
}

pred secureBookAppointment(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureActivity[s, s', e, none -> none,  
							none, e -> episode_reserve_date, none -> none -> none, usr]
}
