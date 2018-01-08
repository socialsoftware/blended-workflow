// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefReport
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefReportPreservesInv {
	all s, s': State, r: Report |
		Invariants [s] and defReport [s, s', r] => Invariants [s']
}
check DefReportPreservesInv for 4 but 18 State, 5 Int
