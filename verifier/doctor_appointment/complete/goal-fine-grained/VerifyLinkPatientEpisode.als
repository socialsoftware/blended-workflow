// verify that operations are consistent
module filesystem/doctorappointment/complete/goalfinegrained/VerifyLinkPatientEpisode

open filesystem/doctorappointment/complete/goalfinegrained/GoalModel


assert LinkPatientEpisodePreservesInv {
	all s, s': State, p: Patient, e: Episode |
		Invariants [s] and linkPatientEpisode [s, s', p, e] => Invariants [s']
}
check LinkPatientEpisodePreservesInv for 4 but 18 State, 5 Int
