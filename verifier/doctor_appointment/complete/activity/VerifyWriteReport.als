// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyWriteReport
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/activity/ActivityModel


assert WriteReportPreservesInv {
	all s, s': State, e: Episode, r: Report |
		Invariants [s] and writeReport [s, s', e, r] => Invariants [s']
}
check WriteReportPreservesInv for 4 but 7 State, 5 Int
