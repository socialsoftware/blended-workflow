module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern3SpecOne

open filesystem/doctorappointment/patient_episode/SecurePattern3DoctorAppointment
open filesystem/Pattern3/SecurePattern3ActivityConditions


pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureP3Activity [s, s', none, none -> none, 
										p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureP3Activity [s, s', p, none -> none, 
										e, none -> none , (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
}

pred secureBookAppointment(s, s': AbstractSecureState, e: Episode, usr: User){
	secureP3Activity [s, s', e,   none -> none, 
										none,  e -> episode_reserve_date, none -> none -> none, usr]
}

