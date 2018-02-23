module filesystem/doctorappointment/patientepisode/activity/ActivitySpecThree/SecureActivityModelPattern1SpecThreeExec

open filesystem/doctorappointment/patientepisode/activity/ActivitySpecThree/SecureActivityModelPattern1SpecThree

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

run pattern1Complete for 5 but 4 SecureState, 5 Int

assert CorrectSecureExecution{
	all s, s': SecureState| 
		ACActInvP1 [s] and InvariantsP1 [s]
			=> ACActInvP1 [s'] and InvariantsP1 [s']
}
//Checks
//check CorrectSecureExecution for 5 but 4  SecureState, 5 Int
