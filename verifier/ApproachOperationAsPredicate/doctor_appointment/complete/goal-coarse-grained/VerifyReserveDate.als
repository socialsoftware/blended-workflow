// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyReserveDate

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalSpecExec


assert ReserveDatePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and reserveDate [s, s', p, e] => Invariants [s']
}
check ReserveDatePreservesInv for 4 but 10 State, 5 Int
