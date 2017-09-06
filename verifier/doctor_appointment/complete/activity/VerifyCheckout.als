// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyCheckout

open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/activity/ActivityModel


assert CheckoutPreservesInv {
	all s, s': State | all e: Episode |
		Invariants [s] and checkout [s, s', e] => Invariants [s']
}
check CheckoutPreservesInv for 4 but 2 State, 5 Int
