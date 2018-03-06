// a goal model 
module filesystem/doctorappointment/complete/goalfinegrained/GoalSpecExec

open filesystem/doctorappointment/complete/goalfinegrained/GoalSpec

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode, d: Data, r: Report | 
		defPatient [s, s', p] or
		defPatientName [s, s', p] or
		defPatientAddress [s, s', p] or
		defEpisode [s, s', e] or
		defEpisodeReserveDate [s, s', e] or
		defEpisodeCheckin [s, s', e] or
		defEpisodeCheckout [s, s', e] or
		linkPatientEpisode [s, s', p, e] or
		defData [s, s', d] or
		defDataHeight [s, s', d] or
		defDataWeight [s, s', d] or
		defDataBloodPressure [s, s', d] or
		defDataPhysicalCondition [s, s', d] or
		linkEpisodeData [s, s', e, d] or
		defReport [s, s', r] or
		defReportDescription [s, s', r] or
		linkEpisodeReport [s, s', e, r] //or
	//	skip [s, s']
}
