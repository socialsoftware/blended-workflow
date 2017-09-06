// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyCheckin

open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/activity/ActivityModel


assert CheckinPreservesInv {
	all s, s': State | all e: Episode |
		Invariants [s] and checkin [s, s', e] => Invariants [s']
}
check CheckinPreservesInv for 4 but 2 State, 5 Int
