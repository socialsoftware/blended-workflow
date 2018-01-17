module filesystem/doctorappointment/patientepisode/activity/SecurePattern1ActivityModel

open filesystem/doctorappointment/SecurePattern1DoctorAppointment
open filesystem/SecurePattern1ActivityModel


pred staticDomainActivityInit (s: DomainSecureState) {
	//objects
	s.objects = {Doctor1}
	//fields
	no s.fields
	//log
	no s.log
	//users
	s.users = {Alice + Bob}
	Alice.usr_obj = Doctor1
//	Bob.usr_obj = NoObject
	//roles
	s.roles={R_Doctor}
	//user roles	
	s.u_roles = {Alice -> R_Doctor}
	//resources
	s.resources = {Episode + episode_reserve_date + episode_patient + episode_report + episode_doctor
							+ Patient + patient_name + patient_address + patient_episode 	
							+ Report + report_description + report_episode 
							+ Doctor + doctor_episode }
	//role permissions
	s.role_permissions = 
		{Def -> {
			R_Doctor->{Episode + episode_reserve_date + episode_patient + episode_report + episode_doctor
							+ Patient + patient_name + patient_address + patient_episode 	
							+ Report + report_description + report_episode 
							+ Doctor + doctor_episode}
			}
		+
		Read -> {
			R_Doctor-> {Episode + episode_reserve_date + episode_patient + episode_report + episode_doctor
							+ Patient + patient_name + patient_address + patient_episode 	
							+ Report + report_description + report_episode 
							+ Doctor + doctor_episode}
			}
		}
	//domain dependences
	no s.domain_dependent 
	//domain permissions
	no s.domain_permission
}



fact traces {
	first.staticDomainActivityInit
	all s: DomainSecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User, d: Doctor, r: Report| 
		secureDynamicRegisterPatient[s, s', p, u] or
		secureDynamicBookAppointmentAndAssignDoctor[s, s', p, e, d, u] or
		secureDynamicWriteReport[s, s', p, e, r,  u] 
}



pred secureDynamicRegisterPatient(s, s': DomainSecureState, p: Patient, usr: User) {
	secureDynamicActivity[s, s', none, none -> none, 
										none, none, none -> none, none, 
										p, p -> patient_name + p -> patient_address, none -> none -> none, usr]
}

//run secureDynamicRegisterPatient for 4 but 2 DomainSecureState, 2 SecureState, 2 State, 5 Int

pred secureDynamicBookAppointmentAndAssignDoctor(s, s': DomainSecureState, p: Patient, e: Episode, d: Doctor, usr: User) {
	secureDynamicActivity[s, s', p, none -> none, 
										e, episode_reserve_date, 0 -> episode_patient, patient_address, 
										e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p) + (d -> doctor_episode -> e) + (e -> episode_doctor -> d), usr]
}

pred secureDynamicWriteReport(s, s': DomainSecureState, p: Patient, e: Episode, r: Report, usr: User){
	secureDynamicActivity [s, s', p + e, (e -> episode_reserve_date) + (e -> episode_doctor), 
										none, none, none -> none, none, 
										r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r), usr]
}

run thiscomplete for 5 but 4 DomainSecureState, 4 SecureState, 4 State, 5 Int


pred thiscomplete {
 	one s: State | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#Doctor <: s.objects = 1 and
		#Report <: s.objects = 1 and
		#s.objects = 4 and
		// model is well defined

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Episode, episode_reserve_date] and
		
		attributesDefined [s, Report, report_description] and

		// associations multiplicity
		multiplicityRule [s, Episode, episode_patient] and
		multiplicityRule [s, Patient, patient_episode] and

		multiplicityRule [s, Doctor, doctor_episode] and
		multiplicityRule [s, Episode, episode_doctor] and

		multiplicityRule [s, Report, report_episode] and
		multiplicityRule [s, Episode, episode_report] and

		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and
		bidirectionalRule [s, Report, episode_report, Episode, report_episode] and
		bidirectionalRule [s, Doctor, episode_doctor, Episode, doctor_episode] and

		// dependencies hold
		dependence [s, Episode, episode_reserve_date, 0 -> episode_patient, patient_address]
}

