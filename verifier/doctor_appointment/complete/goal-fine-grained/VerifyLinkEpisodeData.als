// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyLinkEpisodeData

open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert LinkEpisodeDataPreservesInv {
	all s, s': State, e: Episode, d: Data |
		Invariants [s] and linkEpisodeData [s, s', e, d] => Invariants [s']
}
check LinkEpisodeDataPreservesInv for 4 but 18 State, 5 Int
