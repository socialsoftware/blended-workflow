module filesystem/doctorappointment/patientepisode/data/SecureDataModel



open filesystem/doctorappointment/SecureDoctorAppointment
open filesystem/doctorappointment/patientepisode/Achieve
open filesystem/doctorappointment/patientepisode/Invariants

pred init (s: State) {
	no s.objects
	no s.fields
	//The log is empty
	no s.log
/**
* Hard code here the Access control rules
**/	



	//WRITE HERE THE USERS
	s.users = {Alice + Bruno + Carlos}
	//WRITE HERE THE ROLES
	s.roles={Doctor+ Receptionist + Nurse}
	//WRITE HERE THE ROLES OF EACH USER
	s.u_roles = {
		Alice -> Doctor 
		+ Bruno -> Receptionist 
		+ Carlos -> Nurse}
	//WRITE HERE THE PERMISSIONS OF EACH ROLE
	s.r_permissions = {
		Doctor->{ 
				Patient -> Read
				+ patient_name -> Read + patient_address -> Read + patient_episode->Read
				+Episode -> Write
				+episode_reserve_date -> Write+ episode_data -> Write+episode_report->Write
			}
		+ Receptionist->{ 
				Patient -> Write 
				+ patient_name -> Write + patient_address -> Write + patient_episode->Write
				+ Episode ->Read
				+ episode_reserve_date -> Write + episode_checkin ->Write + episode_checkout ->Write + episode_patient -> Write
			}
		}
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


run complete for  4 but 15 State, 5 Int
