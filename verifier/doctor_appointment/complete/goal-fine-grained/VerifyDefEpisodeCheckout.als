// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalfinegrained/VerifyDefEpisodeCheckout

open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert DefEpisodeCheckoutPreservesInv {
	all s, s': State, e: Episode |
		Invariants [s] and defEpisodeCheckout [s, s', e] => Invariants [s']
}
check DefEpisodeCheckoutPreservesInv for 4 but 18 State, 5 Int
