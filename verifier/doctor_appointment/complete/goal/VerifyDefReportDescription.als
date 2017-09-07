// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefReportDescription
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefReportDescriptionPreservesInv {
	all s, s': State, r: Report |
		Invariants [s] and defReportDescription [s, s', r] => Invariants [s']
}
check DefReportDescriptionPreservesInv for 4 but 2 State, 5 Int
