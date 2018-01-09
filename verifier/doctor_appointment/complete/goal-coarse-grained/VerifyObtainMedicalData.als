// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyObtainMedicalData

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel


assert ObtainMedicalDataPreservesInv {
	all s, s': State, e: Episode, d: Data |
		Invariants [s] and obtainMedicalData [s, s', e, d] => Invariants [s']
}
check ObtainMedicalDataPreservesInv for 4 but 10 State, 5 Int
