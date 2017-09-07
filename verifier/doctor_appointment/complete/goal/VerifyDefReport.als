// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefReport
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefReportPreservesInv {
	all s, s': State, r: Report |
		Invariants [s] and defReport [s, s', r] => Invariants [s']
}
check DefReportPreservesInv for 4 but 2 State, 5 Int
