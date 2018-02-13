module filesystem/doctorappointment/patientepisode/activity/ActivitySpecTwo/SecureActivitySpecTwoExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecTwo/SecureActivitySpecTwo

sig SecureState extends AbstractSecureState{}

pred secureInit (s: SecureState){
	//objects
	no s.objects
	//fields
	no s.fields
	//log
	no s.log
}

fact traces {
	first.secureInit
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User | 
		secureRegisterPatient[s, s', p, e, u] or
		secureCreateEpisode[s, s', e, u] or
		secureBookAppointment[s, s', e, u]
}


//run complete for 4 but 4 SecureState, 5 Int

assert CorrectRegisterPatient{
	all s, s': SecureState, p: Patient, e: Episode, u: User |
			ACActInv[s] and Invariants[s] and secureRegisterPatient [s, s', p, e, u] 
				=> ACActInv [s'] and Invariants[s']
}
//Check
//check CorrectRegisterPatient for 4 but 2 SecureState, 5 Int

assert CorrectSecureExecution{
	all s, s': SecureState| 
		ACActInv [s] and Invariants [s]
			=> ACActInv [s'] and Invariants [s']
}
//Checks
check CorrectSecureExecution for 4 but 4 SecureState, 5 Int
