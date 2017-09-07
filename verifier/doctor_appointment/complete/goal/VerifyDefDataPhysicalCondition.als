// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefDataPhysicalCondition
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefDataPhysicalConditionPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataPhysicalCondition [s, s', d] => Invariants [s']
}
check DefDataPhysicalConditionPreservesInv for 4 but 2 State, 5 Int
