// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefEpisodeReserveDate

open filesystem/doctorappointment/complete/goalfinegrained/GoalSpecExec


assert DefEpisodeReserveDatePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeReserveDate [s, s', e] => Invariants [s']
}
check DefEpisodeReserveDatePreservesInv for 4 but 18 State, 5 Int
