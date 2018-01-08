// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyMeasureBloodPressure
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel


assert MeasureBloodPressurePreservesInv {
	all s, s': State, d: Data |
		Invariants [s] and measureBloodPressure [s, s', d] => Invariants [s']
}
check MeasureBloodPressurePreservesInv for 4 but 10 State, 5 Int
