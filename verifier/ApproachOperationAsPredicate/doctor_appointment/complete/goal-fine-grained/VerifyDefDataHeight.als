// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefDataHeight

open filesystem/doctorappointment/complete/goalfinegrained/GoalSpecExec


assert DefDataHeightPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataHeight [s, s', d] => Invariants [s']
}
check DefDataHeightPreservesInv for 4 but 18 State, 5 Int
