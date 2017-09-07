// verify that operations are consistent
module filesystem/doctorappointment/complete/goal/VerifyDefPatient

open filesystem/doctorappointment/complete/invariants
open filesystem/doctorappointment/complete/goal/GoalModel


assert DefPatientPreservesInv {
	all s, s': State | all p: Patient |
		Invariants [s] and defPatient [s, s', p] => Invariants [s']
}
check DefPatientPreservesInv for 4 but 2 State, 5 Int
