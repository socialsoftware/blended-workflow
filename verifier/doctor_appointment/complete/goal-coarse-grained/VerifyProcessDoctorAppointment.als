// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyProcessMedicalAppointment
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel


assert ProcessMedicalAppointmentPreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and processMedicalAppointment [s, s', p, e] => Invariants [s']
}
check ProcessMedicalAppointmentPreservesInv for 4 but 2 State, 5 Int
