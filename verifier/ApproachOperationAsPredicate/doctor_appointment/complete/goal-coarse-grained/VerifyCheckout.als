// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyCheckout

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalSpecExec


assert CheckoutPreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and checkout [s, s', p, e] => Invariants [s']
}
check CheckoutPreservesInv for 4 but 10 State, 5 Int
