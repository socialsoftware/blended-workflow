// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyCheckin

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel

assert CheckinPreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and checkin [s, s', p, e] => Invariants [s']
}
check CheckinPreservesInv for 4 but 10 State, 5 Int
