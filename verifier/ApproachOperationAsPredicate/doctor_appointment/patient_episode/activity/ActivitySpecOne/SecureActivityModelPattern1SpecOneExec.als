module filesystem/doctorappointment/patientepisode/activity/SecureActivityModelPattern1SpecOneExec

open filesystem/doctorappointment/patientepisode/activity/SecureActivityModelPattern1SpecOne

sig SecureState extends AbstractSecureState{}

pred init (s: SecureState) {
	//objects
	no s.objects
	//fields
	no s.fields
	//log
	no s.log
}



fact traces {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User| 
		secureRegisterPatient[s, s', p, u] or
		secureCreateEpisode[s, s', p, e, u] or
		secureBookAppointment[s, s', e, u] 
}

run complete for 4 but 4 SecureState, 5 Int

assert CorrectSecureExecution{
	all s: SecureState| 
		ACActInv [s] and Invariants [s]
}
//Checks
check CorrectSecureExecution for 4 but 4  SecureState, 5 Int
