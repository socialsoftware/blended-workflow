// verify that operations are consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefPatientName

open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefPatientNamePreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatientName [s, s', p] => Invariants [s']
}
check DefPatientNamePreservesInv for 4 but 2 State, 5 Int
