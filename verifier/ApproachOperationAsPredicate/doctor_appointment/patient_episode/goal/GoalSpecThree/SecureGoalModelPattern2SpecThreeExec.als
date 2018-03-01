module filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern2SpecThreeExec

open filesystem/doctorappointment/patientepisode/activity/GoalSpecThree/SecureGoalModelPattern2SpecThree

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
		secureBookAppointmentAndAssignDoctor[s, s', p, e, d, u] or
		secureWriteDescription[s, s', p, e, u] 
}

run pattern2Complete for 5 but 4 SecureState, 5 Int


assert CorrectSecureExecution{
	all s, s': SecureState| 
		ACP2GoalInv [s] and pattern2Invariants [s]
			=> ACP2GoalInv [s'] and pattern2Invariants [s']
}
//Checks
check CorrectSecureExecution for 5 but 4  SecureState, 5 Int
