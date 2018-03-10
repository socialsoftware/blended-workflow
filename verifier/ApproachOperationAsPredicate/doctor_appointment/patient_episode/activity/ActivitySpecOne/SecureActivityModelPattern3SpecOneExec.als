module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern3SpecOneExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern3SpecOne


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

assert CorrectSecureExecution{
	all s: SecureState| 
		ACActInv [s] and Invariants [s]
			
}
//Checks
check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
