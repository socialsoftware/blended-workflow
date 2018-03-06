module filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern3SpecThree

open filesystem/doctorappointment/patientepisode/SecurePattern3DoctorAppointment
open filesystem/Pattern3/SecurePattern3GoalConditions

pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureP3Goal[s, s', none, none -> none,  
						p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}

pred secureCreateAppointment(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureP3Goal[s, s', none, none -> none,
					 e, none -> none,(p -> patient_episode -> e) + (e -> episode_patient -> p), usr]
}


pred secureBookAppointment(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureP3Goal[s, s', none, none -> none,
						none, e -> episode_reserve_date, none -> none -> none, usr]
}

