// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefDataBloodPressure
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefDataBloodPressurePreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataBloodPressure [s, s', d] => Invariants [s']
}
check DefDataBloodPressurePreservesInv for 4 but 2 State, 5 Int
