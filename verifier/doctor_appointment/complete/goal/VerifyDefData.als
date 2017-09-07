// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefData
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefDataPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defData [s, s', d] => Invariants [s']
}
check DefDataPreservesInv for 4 but 2 State, 5 Int
