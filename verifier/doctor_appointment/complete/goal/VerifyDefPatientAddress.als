// verify that operations are consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefPatientAddress

open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefPatientAddressPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatientAddress [s, s', p] => Invariants [s']
}
check DefPatientAddressPreservesInv for 4 but 2 State, 5 Int
