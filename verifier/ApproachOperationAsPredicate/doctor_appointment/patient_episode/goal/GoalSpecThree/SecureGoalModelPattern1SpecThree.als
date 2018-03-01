module filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern1SpecThree

open filesystem/doctorappointment/patientepisode/SecurePattern1DoctorAppointment
open filesystem/Pattern1/SecurePattern1GoalConditions


pred secureRegisterPatient (s, s': AbstractSecureState, p: Patient, usr: User) {
	secureP1Goal [s, s', none, none -> none, 
										p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}

pred secureCreateEpisode (s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureP1Goal [s, s', p, none -> none, 
										e, none -> none,  (p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
}

pred secureBookAppointment (s, s': AbstractSecureState, e: Episode, usr: User){
	secureP1Goal [s, s', e,  none -> none,
										none,e -> episode_reserve_date, none -> none -> none, usr]
}
