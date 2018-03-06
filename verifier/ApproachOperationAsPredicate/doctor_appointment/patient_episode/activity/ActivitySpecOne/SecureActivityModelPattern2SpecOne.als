module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern2SpecOne

open filesystem/doctorappointment/patientepisode/SecurePattern2DoctorAppointment
open filesystem/Pattern2/SecurePattern2ActivityConditions

pred secureRegisterPatient(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureP2Activity[s, s', none, none -> none, 
							p, p -> patient_name, none -> none -> none, usr]
}

pred secureRegisterPatientAddress(s, s': AbstractSecureState, p: Patient, usr: User) {
	secureP2Activity[s, s', p, none -> none, 
							none, p -> patient_address, none -> none -> none, usr]
}

pred secureCreateEpisode(s, s': AbstractSecureState, p: Patient, e: Episode, d: Doctor, usr: User) {
	secureP2Activity[s, s', p, none -> none, 
							e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), usr]
}

pred secureWriteReport(s, s': AbstractSecureState, e: Episode, usr: User) {
	secureP2Activity[s, s', e, none -> none,  
							none, (e -> episode_report), none -> none -> none, usr]
}

run{}
