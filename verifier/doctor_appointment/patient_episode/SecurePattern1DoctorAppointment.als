module filesystem/doctorappointment/patient_episode/SecurePattern1DoctorAppointment

open filesystem/doctorappointment/patient_episode/SecureDoctorAppointment
open filesystem/Pattern1/SecurePattern1Spec

sig Doctor extends Obj{}
one sig doctor_episode extends FName {} 
one sig episode_doctor extends FName {} 
one sig episode_report extends FName {}

one sig DoctorAlice extends Doctor{}

fact Alice_Doctor{
	Alice.usr_obj = DoctorAlice
}

one sig DomainEpisodeDoctor extends DomainSubject {}

fact domain_doctor{
	DomainEpisodeDoctor.path = {0 -> episode_doctor}
}

fact relations {
	// relation Patient - Episode
	doctor_episode.minMul = 0
	doctor_episode.maxMul = 10
	doctor_episode.inverse = episode_doctor

	episode_doctor.minMul = 1
	episode_doctor.maxMul = 10
	episode_doctor.inverse = doctor_episode
}

fact acrules{
	//users
	AccessControlRules.users ={Alice  + Bob}
	//roles
	AccessControlRules.roles={R_Doctor}
	//user roles	
	AccessControlRules.u_roles = {Alice -> R_Doctor}
	//resources
	AccessControlRules.resources = {Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor + episode_report}
	//role permissions
	AccessControlRules.permissions = 
		{Def -> {
				{R_Doctor->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor + episode_report}}
				+ 	{DomainEpisodeDoctor -> episode_report}
			}	
		+
		Read -> {
				{R_Doctor->{Episode + episode_reserve_date + episode_patient + Patient + patient_name + patient_address + patient_episode + Doctor + doctor_episode + episode_doctor + episode_report}}
				+ 	{DomainEpisodeDoctor -> episode_report}
			}	
		}

	//domain dep resources
}

pred pattern1Complete {
 	one s: AbstractState | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#Doctor <: s.objects = 1 and
		#s.objects = 3 and
		// model is well defined

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Episode, episode_reserve_date + episode_report] and

		// associations multiplicity
		multiplicityRule [s, Episode, episode_patient] and
		multiplicityRule [s, Patient, patient_episode] and

		
		multiplicityRule [s, Episode, episode_doctor] and
		multiplicityRule [s, Doctor, doctor_episode] and


		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and
		bidirectionalRule [s, Doctor, episode_doctor , Episode, doctor_episode] and

		// dependencies hold
		checkDependence [s, Episode, reserve_date_dependence]
}

run{}
