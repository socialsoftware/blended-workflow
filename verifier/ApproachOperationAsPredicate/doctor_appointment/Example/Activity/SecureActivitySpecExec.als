module filesystem/doctorappointment/example/activity/SecureActivitySpecExec

open filesystem/doctorappointment/example/activity/SecureActivitySpec

sig SecureState extends AbstractSecureState{}

pred init (s: SecureState) {
	//objects
	s.objects = {DoctorAlice + PatientDan}
	//fields
	no s.fields
	//log
	no s.log
}



fact traces {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, pu: Patient_User, e: Episode, d: Data, r: Report, doc: Doctor_User, u1, u2: User| 
		registerPatient[s, s', p, pu, u1] or  
		bookAppointment[s, s', p, e, doc, u1] or
		checkin[s, s', e, u1] or
		checkout[s, s', e, u1] or
		collectData[s, s', e, d, u1, u2] or
		collectData[s, s', e, d, u1] or
		writeReport[s, s', e, r, u1] 
}

run complete for 5 but 6 Obj, 7 SecureState, 5 Int

