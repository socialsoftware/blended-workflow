// an activity model where patients are registered first, it preserves all invariants
module filesystem/doctorappointment/complete/activity/ActivitySpecExec

open filesystem/doctorappointment/complete/activity/ActivitySpec

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode, d: Data, r: Report | 
		registerPatient[s, s', p] or
		bookAppointment[s, s', p, e] or
		checkin[s, s', e] or
		checkout[s, s', e] or
		collectData[s, s', e, d] or
		writeReport[s, s', e, r] //or
	//	skip [s, s']
}
