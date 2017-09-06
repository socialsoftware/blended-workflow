// verify that models are consistent
module filesystem/doctorappointment/complete/activity/VerifyRegisterPatient

open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/activity/ActivityModel


assert RegisterPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and registerPatient [s, s', p] => Invariants [s']
}
check RegisterPatientPreservesInv for 4 but 2 State, 5 Int
