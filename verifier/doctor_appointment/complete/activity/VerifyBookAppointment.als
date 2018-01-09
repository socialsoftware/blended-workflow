// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyBookAppointment

open filesystem/doctorappointment/complete/activity/ActivityModel


assert BookAppointmentPreservesInv {
	all s, s': State | all p: Patient | all e: Episode |
		Invariants [s] and bookAppointment[s, s', p, e] => Invariants [s']
}
check BookAppointmentPreservesInv for 4 but 7 State, 5 Int
