// an activity model where patients are registered first, it preserves all invariants
module filesystem/doctorappointment/complete/activity/ActivityModel

open filesystem/ActivityModel
open filesystem/doctorappointment/complete/DoctorAppointment

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

pred registerPatient(s, s': State, p: Patient) {
	preCondition[s, none, none -> none]
	postCondition[s, s', p, p -> patient_name + p -> patient_address, none -> none -> none]
}

pred bookAppointment(s, s': State, p: Patient, e: Episode) {
	preCondition[s, p, none -> none]
	postCondition[s, s', e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p)]
}

pred checkin(s, s': State, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none, e -> episode_checkin, none -> none -> none]
}

pred checkout(s, s': State, e: Episode) {
	preCondition[s, e, none -> none]
	postCondition[s, s', none, e -> episode_checkout, none -> none -> none]
}

pred collectData(s, s': State, e: Episode, d: Data) {
	preCondition[s, e, none -> none]
	postCondition[s, s', d, d -> {data_height + data_weight + data_blood_pressure + data_physical_condition}, d -> data_episode -> e + e -> episode_data -> d]
}

pred writeReport(s, s': State, e: Episode, r: Report) {
	preCondition[s, e, none -> none]
	postCondition[s, s', r, r -> report_description, (r -> report_episode -> e) + (e -> episode_report -> r)]
}
