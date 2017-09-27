// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyLinkEpisodeReport
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert LinkEpisodeReportPreservesInv {
	all s, s': State, e: Episode, r: Report |
		Invariants [s] and linkEpisodeReport [s, s', e, r] => Invariants [s']
}
check LinkEpisodeReportPreservesInv for 4 but 2 State, 5 Int
