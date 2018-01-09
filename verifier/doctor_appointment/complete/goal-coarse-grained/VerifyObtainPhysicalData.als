// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyObtainPhysicalData

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel


assert ObtainPhysicalDataPreservesInv {
	all s, s': State, e: Episode, d: Data |
		Invariants [s] and obtainPhysicalData [s, s', e, d] => Invariants [s']
}
check ObtainPhysicalDataPreservesInv for 4 but 10 State, 5 Int
