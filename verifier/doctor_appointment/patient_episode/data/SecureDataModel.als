module filesystem/doctorappointment/patientepisode/data/SecureDataModel



open filesystem/doctorappointment/SecureDoctorAppointment
open filesystem/doctorappointment/patientepisode/Achieve
open filesystem/doctorappointment/patientepisode/Invariants

pred init (s: State) {
	no s.objects
	no s.fields
	
	//The log is empty
	no s.log
	//This is the first state
	//s.step=1

/**
* Hard code here the Access control rules
**/	
//DIZER EXPLICITAMENTE QUE O CONJUNTO É = Person1+person2

	//WRITE HERE THE USERS
	Person1 in s.users
	Person2 in s.users
	//WRITE HERE THE ROLES
	Doctor in s.roles
	Receptionist in s.roles
	//WRITE HERE THE ROLES OF EACH USER
	
	//WRITE HERE THE PERMISSIONS OF EACH ROLE

	//No user owned Objects
	no s.u_owned


}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
		some p: Patient, e: Episode, u: User| 
		secureDefObj [s, s', p, u]or 
		secureDefAtt [s, s', p, patient_name, u] or secureDefAtt [s, s', p, patient_address, u] or 
		secureDefObj [s, s', e, u] or
	 	secureDefAtt [s, s', e, episode_reserve_date, u] or 
		secureLinkObj [s, s', p,  episode_patient, e, patient_episode, u] or
		secureLinkObj [s, s', e, patient_episode, p, episode_patient, u]
}


run complete for   4 but 15 State, 5 Int
