// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefEpisodeCheckin
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefEpisodeCheckinPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeCheckin [s, s', e] => Invariants [s']
}
check DefEpisodeCheckinPreservesInv for 4 but 2 State, 5 Int
