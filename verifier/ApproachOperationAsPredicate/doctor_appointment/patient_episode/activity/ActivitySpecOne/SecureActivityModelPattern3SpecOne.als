module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern3SpecOne

open filesystem/doctorappointment/patientepisode/SecurePattern3DoctorAppointment
open filesystem/SecureActivityConditions

pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureActivity[s, s', none, none -> none, 
							p, p -> patient_name, none -> none -> none, none, usr, none]
}

pred secureRegisterPatientAddress(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureActivity[s, s', p, none -> none, 
							none, p -> patient_address, none -> none -> none, none, usr, none]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureActivity[s, s', p, none -> none, 
							e, none -> none, (p -> patient_episode -> e) + (e -> episode_patient -> p), none,  usr, none]
}

pred secureBookAppointment(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureActivity[s, s', e, none -> none,  
							none, e -> episode_reserve_date, none -> none -> none, none, usr, none]
}

run{}
