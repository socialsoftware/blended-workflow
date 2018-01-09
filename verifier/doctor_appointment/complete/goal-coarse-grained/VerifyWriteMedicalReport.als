// verify that the operation is consistently defined
module filesystem/doctorappointment/complete/goalcoarsegrained/VerifyWriteMedicalReport

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalModel


assert WriteMedicalReportPreservesInv {
	all s, s': State, p: Patient, e: Episode, r: Report |
		Invariants [s] and writeMedicalReport [s, s', p, e, r] => Invariants [s']
}
check WriteMedicalReportPreservesInv for 4 but 10 State, 5 Int
