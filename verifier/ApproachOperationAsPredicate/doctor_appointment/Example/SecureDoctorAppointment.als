module filesystem/doctorappointment/example/SecureDoctorAppointment

open filesystem/doctorappointment/example/DoctorAppointment
open filesystem/BWSecureSpec

one sig Alice, Bob, Carl, Dan extends User{}

one sig R_Doctor, R_Nurse, R_Receptionist, R_Patient extends RoleSubject{}

one sig registerPatient, bookAppointment, checkin, checkout, collectData, writeReport extends Activity{} 

////////////////////////////////////////////////////

sig Doctor_User extends Obj{}
one sig doctor_episode extends FName {} 
one sig episode_doctor extends FName {} 

one sig DoctorAlice extends Doctor_User{}

sig Patient_User extends Obj{}
one sig user_patient extends FName{}
one sig patient_user extends FName{}

one sig PatientDan extends Patient_User{}

fact user_objs{
	Alice.usr_obj = DoctorAlice
	no Bob.usr_obj
	no Carl.usr_obj
	Dan.usr_obj = PatientDan
}

one sig DomainReportDoctor extends DomainSubject {}

fact domain_doctor{
	DomainReportDoctor.path = {0 -> report_episode + 1 -> episode_doctor}
}

one sig DomainEpisodePatient extends DomainSubject {}

fact domain_patient{
	DomainEpisodePatient.path = {0 -> episode_patient + 1 -> patient_user}
}

fact relations {
	// relation Doctor - Episode
	doctor_episode.minMul = 0
	doctor_episode.maxMul = 10
	doctor_episode.inverse = episode_doctor

	episode_doctor.minMul = 1
	episode_doctor.maxMul = 1
	episode_doctor.inverse = doctor_episode

	// relation Patient - Patient_User
	user_patient.minMul = 1
	user_patient.maxMul = 1
	user_patient.inverse = patient_user

	patient_user.minMul = 1
	patient_user.maxMul = 1
	patient_user.inverse = user_patient
}

pred complete {
 	one s: AbstractState | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Patient_User <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#Data<: s.objects = 1 and
		#Report <: s.objects = 1 and
		#Doctor_User <: s.objects = 1 and
		#s.objects = 6 and
		// model is well defined

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Episode, episode_reserve_date + episode_checkin + episode_checkout] and
		attributesDefined [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition] and
		attributesDefined [s, Report, report_description] and

		// associations multiplicity
		multiplicityRule [s, Patient, patient_user] and
		multiplicityRule [s, Patient_User, user_patient] and
		multiplicityRule [s, Patient, patient_episode] and
		multiplicityRule [s, Episode, episode_patient] and
		multiplicityRule [s, Episode, episode_data] and
		multiplicityRule [s, Data, data_episode] and
		multiplicityRule [s, Episode, episode_report] and
		multiplicityRule [s, Report, report_episode] and
		multiplicityRule [s, Episode, episode_doctor] and
		multiplicityRule [s, Doctor_User, doctor_episode] and

		// bidirectional relation
		bidirectionalRule [s, Patient, user_patient, Patient_User, patient_user] and
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and
 		bidirectionalRule [s, Episode, data_episode, Data, episode_data] and
 		bidirectionalRule [s, Episode, report_episode, Report, episode_report] and
		bidirectionalRule [s, Doctor_User, episode_doctor , Episode, doctor_episode] and

		// dependencies hold
		checkDependence [s, Episode, episode_checkout_report_descripton_dependence] and
		checkDependence [s, Episode, episode_checkout_episode_checkin_dependence] and
		checkDependence [s, Report, report_description_data_blood_pressure_dependence]
}

pred Invariants(s: AbstractState) {
	// only the correct fields are associated to the entities
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode + patient_user]
 	noExtraFields [s, Patient_User, user_patient] 
	noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report + episode_doctor]	 
	noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 
	noExtraFields [s, Report, report_description + report_episode]	 
	noExtraFields [s, Doctor_User, doctor_episode] 

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Patient, patient_user] 
	noMultiplicityExceed [s, Patient_User, user_patient] 
	noMultiplicityExceed [s, Patient, patient_episode] 
	noMultiplicityExceed [s, Episode, episode_patient] 
	noMultiplicityExceed [s, Episode, episode_data] 
	noMultiplicityExceed [s, Data, data_episode] 
	noMultiplicityExceed [s, Episode, episode_report] 
	noMultiplicityExceed [s, Report, report_episode] 
	noMultiplicityExceed [s, Episode, episode_doctor] 
	noMultiplicityExceed [s, Doctor_User, doctor_episode] 

	// bidirectional relation
	bidirectionalPreservation [s, Patient, user_patient, Patient_User, patient_user]
	bidirectionalPreservation [s, Patient, episode_patient, Episode, patient_episode]
 	bidirectionalPreservation [s, Episode, data_episode, Data, episode_data]
 	bidirectionalPreservation [s, Episode, report_episode, Report, episode_report]
	bidirectionalPreservation [s, Doctor_User, episode_doctor, Episode, doctor_episode] 

	// if there is a link between two objects, either is unidirectional or bidirectional
	checkDependence [s, Episode, episode_checkout_report_descripton_dependence]
	checkDependence [s, Episode, episode_checkout_episode_checkin_dependence]
	checkDependence [s, Report, report_description_data_blood_pressure_dependence]
}
run {}
