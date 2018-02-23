module filesystem/doctorappointment/patientepisode/goal/GoalSpecOne/SecureGoalSpecOne

open filesystem/doctorappointment/patientepisode/SecureDoctorAppointment
open filesystem/SecureGoalConditions


pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureGoal[s, s', none, none -> none,  
						p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}

pred secureBookAppointment(s, s': AbstractSecureState, e: Episode,  usr: User) {
	secureGoal[s, s', none, none -> none,
					 e, e -> episode_reserve_date, none -> none -> none, usr]
}


pred secureAssociatePatientToEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User) {
	secureGoal[s, s', none, none -> none,
						none, none ->none, (p -> patient_episode -> e) + (e -> episode_patient -> p), usr ] 
}
 
