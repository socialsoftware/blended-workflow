// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyCollectData

open filesystem/doctorappointment/complete/activity/ActivitySpecExec


assert CollectDataPreservesInv {
	all s, s': State, e: Episode, d: Data |
		Invariants [s] and collectData[s, s', e, d] => Invariants [s']
}
check CollectDataPreservesInv for 4 but 7 State, 5 Int
