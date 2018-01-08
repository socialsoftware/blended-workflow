// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefEpisode
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefEpisodePreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisode [s, s', e] => Invariants [s']
}
check DefEpisodePreservesInv for 4 but 18 State, 5 Int
