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
		secureCreateAppointment[s, s', e, u] or
		secureReserveDate[s, s', e, u] or
		secureAssociatePatientToEpisode[s, s', p, e, u]
}

 //execution that leads to a complete state
 run complete for 4 but 5 SecureState, 5 Int



assert CorrectRegisterPatient{
	all s, s': SecureState, p: Patient, u: User |
			ACGoalInv[s] and Invariants[s] and secureRegisterPatient [s, s', p, u] 
				=> ACGoalInv [s'] and Invariants[s']
}
//Check
check CorrectRegisterPatient for 4 but 2 SecureState, 5 Int

