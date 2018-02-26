module filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern4SpecThree

open filesystem/doctorappointment/patientepisode/SecurePattern4DoctorAppointment
open filesystem/Pattern4/SecurePattern4GoalConditions



pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureP4Goal[s, s', none, none -> none, 
							p, (p -> patient_name) +  (p -> patient_address), none -> none -> none, usr]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, d: Doctor, usr: User) {
	secureP4Goal[s, s', p, none -> none, 
							e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), usr]
}

pred secureWriteReport(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureP4Goal[s, s', e, none -> none,  
							none, (e -> episode_report), none -> none -> none, usr]
}

run{}

