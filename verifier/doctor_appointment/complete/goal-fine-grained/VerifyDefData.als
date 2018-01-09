// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefData

open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefDataPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defData [s, s', d] => Invariants [s']
}
check DefDataPreservesInv for 4 but 18 State, 5 Int
