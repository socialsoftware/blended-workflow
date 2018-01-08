// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefDataPhysicalCondition
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefDataPhysicalConditionPreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataPhysicalCondition [s, s', d] => Invariants [s']
}
check DefDataPhysicalConditionPreservesInv for 4 but 18 State, 5 Int
