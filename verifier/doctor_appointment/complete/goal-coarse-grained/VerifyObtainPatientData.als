// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyObtainPatientData
open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel


assert ObtainPatientDataPreservesInv {
	all s, s': State, p: Patient, e: Episode, d: Data |
		Invariants [s] and obtainPatientData [s, s', p, e, d] => Invariants [s']
}
check ObtainPatientDataPreservesInv for 4 but 10 State, 5 Int
