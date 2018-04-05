module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern2SpecOneExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern2SpecOne


sig SecureState extends AbstractSecureState{}

pred secureInit (s: SecureState) {
	//objects
	s.objects = {DoctorAlice}
	//fields
	no s.fields
	//log
	no s.log
}

fact traces {
	first.secureInit
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User, d: Doctor | 
		secureRegisterPatient[s, s', p, u] or
		secureRegisterPatientAddress[s, s', p, u] or
		secureCreateEpisode[s, s', p, e, d, u] or
		secureWriteReport[s, s', e, u]
		
}

run pattern2Complete for 4 but 5 SecureState, 5 Int

assert CorrectSecureExecution{
	all s: SecureState| 
		ACActInv [s] and pattern2Invariants [s]
}
//Checks
check CorrectSecureExecution for 4 but 5 SecureState, 5 Int
