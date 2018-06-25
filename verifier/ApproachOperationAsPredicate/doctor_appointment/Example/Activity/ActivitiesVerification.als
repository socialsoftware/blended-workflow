module filesystem/doctorappointment/example/activity/ActivitiesVerification

open filesystem/doctorappointment/example/activity/SecureActivitySpecExec

/**
Register Patient Verifications
**/
assert onlyReceptionistCanRegisterPatient{
	all s: SecureState, p: Patient, pu: Patient_User, u:User| 
			registerPatient[s, s.next, p, pu, u] => R_Receptionist in u.(AccessControlRules.u_roles)  
}
//Checks
//check onlyReceptionistCanRegisterPatient for 5 but 6 Obj, 7 SecureState, 5 Int

assert doctorCantRegisterPatient{
	all p: Patient, pu: Patient_User|
		no s: SecureState | registerPatient[s, s.next, p, pu, Alice] 
}
//Checks
//check doctorCantRegisterPatient for 5 but 6 Obj, 7 SecureState, 5 Int

/**
Check In Verification
**/
assert onlyReceptionistOrPatientCanCheckIn{
	all s: SecureState, e: Episode, u:User| 
		checkin[s, s.next, e, u]  
			=> R_Receptionist in u.(AccessControlRules.u_roles) 
			or some obj: u.usr_obj| obj in reach[s.next, e, DomainEpisodePatient.path] 
}
//Checks
//check onlyReceptionistOrPatientCanCheckIn for 5 but 6 Obj, 7 SecureState, 5 Int

assert doctorCantCheckIn{
	all e: Episode|
		no s: SecureState| checkin[s, s.next, e, Alice]
}

//Checks
//check doctorCantCheckIn for 5 but 6 Obj, 7 SecureState, 5 Int

/**
Check Out Verification
**/
assert onlyReceptionistOrPatientCanCheckOut{
	all s: SecureState, e: Episode, u:User| 
		checkout[s, s.next, e, u]  
			=> R_Receptionist in u.(AccessControlRules.u_roles) 
			or some obj: u.usr_obj| obj in reach[s.next, e, DomainEpisodePatient.path]
}
//Check
//check onlyReceptionistOrPatientCanCheckOut for 5 but 6 Obj, 7 SecureState, 5 Int

assert doctorCantCheckOut{
	all e: Episode|
		no s: SecureState| checkout[s, s.next, e, Alice]
}

//Checks
//check doctorCantCheckOut for 5 but 6 Obj, 7 SecureState, 5 Int


/**
Book Appointment Verification
**/

assert onlyReceptionistCanBookAppointment{
	all s: SecureState, p: Patient, e: Episode, doc: Doctor_User, u: User|
		bookAppointment[s, s.next, p, e, doc, u] 
			=> R_Receptionist in u.(AccessControlRules.u_roles) 
}
//Checks
//check onlyReceptionistCanBookAppointment for 5 but 6 Obj, 7 SecureState, 5 Int

assert doctorCantBookAppointment{
	all p: Patient, e: Episode, doc: Doctor_User |
		no s: SecureState | bookAppointment [s, s.next, p, e, doc, Alice]
}
//checks
//check doctorCantBookAppointment for 5 but 6 Obj, 7 SecureState, 5 Int

/**
Collect Data Verification
**/

assert onlyNurseCanCollectData{
	all s: SecureState, e: Episode, d: Data, u: User|
		collectData[s, s.next, e, d, u] 
			=>  R_Nurse in u.(AccessControlRules.u_roles) 
}
//Checks
//check onlyNurseCanCollectData for 5 but 6 Obj, 7 SecureState, 5 Int

assert doctorCantCollectData{
	all e: Episode, d: Data|
		no s: SecureState| collectData[s, s.next, e, d, Alice]
}
//checks
//check doctorCantCollectData for 5 but 6 Obj, 7 SecureState, 5 Int

assert onlyNurseCanPropagateToDoctor {
	all s:SecureState, e:Episode, d: Data, u1, u2: User|
		collectData[s, s.next, e, d, u1, u2]
		=> R_Nurse in u1.(AccessControlRules.u_roles) and R_Doctor in u2.(AccessControlRules.u_roles)
}
//checks
//check onlyNurseCanPropagateToDoctor for 5 but 6 Obj, 7 SecureState, 5 Int

/**
Write Report Verification
**/

assert onlyDoctorCanWriteReport{
	all s: SecureState, e: Episode, r: Report, u: User|
		writeReport[s, s.next, e, r, u] 
			=> some obj: u.usr_obj| obj in reach[s.next, r, DomainReportDoctor.path] 
}
//checks
//check onlyDoctorCanWriteReport for 5 but 6 Obj, 7 SecureState, 5 Int


assert PatientCantWriteReport{
	all e: Episode, r: Report|
		no s: SecureState| writeReport[s, s.next, e, r, Dan]
}
//checks
//check PatientCantWriteReport for 5 but 6 Obj, 7 SecureState, 5 Int




pred doctorCantRegisterPatient{
	all p: Patient, pu: Patient_User|
		some s: SecureState | registerPatient[s, s.next, p, pu, Carl] 
}
//Checks
run doctorCantRegisterPatient for 5 but 6 Obj, 7 SecureState, 5 Int
