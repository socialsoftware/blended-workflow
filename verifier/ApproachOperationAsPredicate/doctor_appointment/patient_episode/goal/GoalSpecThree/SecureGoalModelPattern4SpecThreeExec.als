module filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern4SpecThreeExec

open filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern4SpecThree


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
	some p: Patient, e: Episode, u: User, d: Doctor| 
		secureRegisterPatient[s, s', p, u] or
		secureCreateEpisode[s, s', p, e, d, u] or
		secureWriteReport[s, s', e, u]
}


//run pattern2Complete for 5 but 4 SecureState, 5 Int


assert CorrectSecureExecution{
	all s, s': SecureState| 
		ACP4GoalInv [s] and pattern2Invariants [s]
			=> ACP4GoalInv [s'] and pattern2Invariants [s]
}
//Checks
check CorrectSecureExecution for 4 but 4  SecureState, 5 Int
