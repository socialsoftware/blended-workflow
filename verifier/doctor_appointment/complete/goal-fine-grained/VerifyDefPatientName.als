// verify that operations are consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefPatientName

open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefPatientNamePreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatientName [s, s', p] => Invariants [s']
}
check DefPatientNamePreservesInv for 4 but 18 State, 5 Int
