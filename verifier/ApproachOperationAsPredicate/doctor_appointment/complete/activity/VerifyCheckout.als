// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyCheckout

open filesystem/doctorappointment/complete/activity/ActivitySpecExec


assert CheckoutPreservesInv {
	all s, s': State | all e: Episode |
		Invariants [s] and checkout [s, s', e] => Invariants [s']
}
check CheckoutPreservesInv for 6 but 7 State, 5 Int
