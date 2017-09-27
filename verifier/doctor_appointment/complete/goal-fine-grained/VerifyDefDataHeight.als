// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefDataHeight
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefDataHeightPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataHeight [s, s', d] => Invariants [s']
}
check DefDataHeightPreservesInv for 4 but 2 State, 5 Int
