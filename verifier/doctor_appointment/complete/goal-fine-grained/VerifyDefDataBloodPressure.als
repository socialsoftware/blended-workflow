// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefDataBloodPressure
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefDataBloodPressurePreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and defDataBloodPressure [s, s', d] => Invariants [s']
}
check DefDataBloodPressurePreservesInv for 4 but 2 State, 5 Int