module filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern1SpecThree

open filesystem/doctorappointment/patient_episode/SecurePattern1DoctorAppointment
open filesystem/Pattern1/SecurePattern1GoalConditions


pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureGoalP1 [s, s', none, none -> none, 
										p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}

pred secureBookAppointmentAndAssignDoctor(s, s': AbstractSecureState, p: Patient, e: Episode, d: Doctor, usr: User) {
	secureGoalP1 [s, s', p, none -> none, 
										e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), usr]
}

pred secureWriteDescription(s, s': AbstractSecureState, p: Patient, e: Episode, usr: User){
	secureGoalP1 [s, s', none,  (e -> episode_doctor), 
										none, e -> episode_report, none -> none -> none, usr]
}
