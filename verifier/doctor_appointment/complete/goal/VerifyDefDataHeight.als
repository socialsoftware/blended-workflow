// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefDataHeight
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefDataHeightPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataHeight [s, s', d] => Invariants [s']
}
check DefDataHeightPreservesInv for 4 but 2 State, 5 Int
