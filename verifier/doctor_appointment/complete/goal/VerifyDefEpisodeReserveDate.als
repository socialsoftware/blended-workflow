// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefEpisodeReserveDate
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefEpisodeReserveDatePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeReserveDate [s, s', e] => Invariants [s']
}
check DefEpisodeReserveDatePreservesInv for 4 but 2 State, 5 Int
