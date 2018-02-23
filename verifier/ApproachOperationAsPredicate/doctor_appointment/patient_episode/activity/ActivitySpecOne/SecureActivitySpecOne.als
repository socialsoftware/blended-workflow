module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivitySpecOne

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment
open filesystem/SecureActivityConditions

pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureActivity[s, s', none, none -> none, 
							p, p -> patient_name, none -> none -> none, usr]
}

pred secureRegisterPatientAddress(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureActivity[s, s', p, none -> none, 
							none, p -> patient_address, none -> none -> none, usr]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureActivity[s, s', p, none -> none, 
							e, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
}

pred secureBookAppointment(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureActivity[s, s', e, none -> none,  
							none, e -> episode_reserve_date, none -> none -> none, usr]
}
