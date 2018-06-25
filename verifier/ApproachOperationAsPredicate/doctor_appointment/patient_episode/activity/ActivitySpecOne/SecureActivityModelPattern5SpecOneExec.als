module filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern5SpecOneExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecOne/SecureActivityModelPattern5SpecOne


sig SecureState extends AbstractSecureState{}

pred init (s: SecureState) {
	//objects
	s.objects = {DoctorAlice}
	//fields
	no s.fields
	//log
	no s.log
}

fact traces {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, d: Doctor| some disj us, ut: User |
		secureRegisterPatient[s, s', p, us, ut] or
		secureRegisterPatient[s, s', p, us, none] or
		secureCreateEpisode[s, s', p, e, d, us, none] or
		secureWriteReport[s, s', e, us, none]
}

//run pattern2Complete for 4 but 4 SecureState, 5 Int

assert CorrectSecureExecution{
	all s: SecureState| 
		ACActInv [s] and pattern2Invariants [s]
		 => 	ACActInv [s.next] and pattern2Invariants [s.next]
}
//Checks
check CorrectSecureExecution for 4 but 4 SecureState, 5 Int
