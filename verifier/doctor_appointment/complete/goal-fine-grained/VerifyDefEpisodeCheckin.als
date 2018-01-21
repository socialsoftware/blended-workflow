// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefEpisodeCheckin

open filesystem/doctorappointment/complete/goalfinegrained/GoalSpecExec


assert DefEpisodeCheckinPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeCheckin [s, s', e] => Invariants [s']
}
check DefEpisodeCheckinPreservesInv for 4 but 18 State, 5 Int
