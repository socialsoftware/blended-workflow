// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyDefEpisode
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefEpisodePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisode [s, s', e] => Invariants [s']
}
check DefEpisodePreservesInv for 4 but 2 State, 5 Int
