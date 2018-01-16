// a goal model 
module filesystem/doctorappointment/complete/goalcoarsegrained/GoalSpecExec

open filesystem/doctorappointment/complete/goalcoarsegrained/GoalSpec

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode, d: Data, r: Report | 
		processMedicalAppointment [s, s', p, e] or
		reserveDate [s, s', p, e] or
		checkin [s, s', p, e] or
		checkout [s, s', p, e] or
		obtainPatientData [s, s', p, e, d] or
		obtainPhysicalData [s, s', e, d] or
		obtainMedicalData [s, s', e, d] or
		measureBloodPressure [s, s', d] or
		writeMedicalReport [s, s', p, e, r] //or
		//skip [s, s']
}
