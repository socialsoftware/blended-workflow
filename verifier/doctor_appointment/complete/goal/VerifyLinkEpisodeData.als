// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goal/VerifyLinkEpisodeData
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert LinkEpisodeDataPreservesInv {
	all s, s': State, e: Episode, d: Data |
		Invariants [s] and linkEpisodeData [s, s', e, d] => Invariants [s']
}
check LinkEpisodeDataPreservesInv for 4 but 2 State, 5 Int
