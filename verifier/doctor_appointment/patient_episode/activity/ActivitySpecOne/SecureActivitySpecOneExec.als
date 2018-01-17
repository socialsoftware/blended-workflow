module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivitySpecOneExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivitySpecOne


sig SecureState extends AbstractSecureState{}

pred secureInit (s: SecureState) {
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
		secureRegisterPatient[s, s', p, u] or
		secureRegisterPatientAddress[s, s', p, u] or
		secureCreateEpisode[s, s', p, e, u] or
		secureBookAppointment[s, s', e, u]
		
}

run complete for 4 but 5 SecureState, 5 Int

assert CorrectRegisterPatient{
	all s, s': SecureState, p: Patient, u: User |
			ACActInv[s] and Invariants[s] and secureRegisterPatient [s, s', p, u] 
				=> ACActInv [s'] and Invariants[s']
}
//Check
check CorrectRegisterPatient for 4 but 2 SecureState, 5 Int

assert CorrectSecureExecution{
	all s, s': SecureState| 
		ACActInv [s] and Invariants [s]
			=> ACActInv [s'] and Invariants [s]
}
//Checks
check CorrectSecureExecution for 4 but 3 SecureState, 5 Int
