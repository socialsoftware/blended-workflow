// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefDataWeight
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefDataWeightPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataWeight [s, s', d] => Invariants [s']
}
check DefDataWeightPreservesInv for 4 but 2 State, 5 Int
