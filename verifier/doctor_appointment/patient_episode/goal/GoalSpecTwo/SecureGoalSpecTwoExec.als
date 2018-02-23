module filesystem/doctorappointment/patientepisode/goal/GoalSpecTwo/SecureGoalSpecTwoExec

open filesystem/doctorappointment/patientepisode/goal/GoalSpecTwo/SecureGoalSpecTwo

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
		secureCreateAppointment[s, s', p, e, u] or
		secureBookAppointment[s, s', e, u] 
}

//execution that leads to a complete state
//run complete for 4 but 4 SecureState, 5 Int



assert CorrectExecution{
	all s, s': SecureState|
			ACGoalInv[s] and Invariants[s]  
				=> ACGoalInv [s'] and Invariants[s']
}
//Check
check CorrectExecution for 4 but 3 SecureState, 5 Int

