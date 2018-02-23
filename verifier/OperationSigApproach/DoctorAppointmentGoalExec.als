module filesystem/DoctorAppointmentGoalExec

open filesystem/DoctorAppointmentGoal

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


// run complete for 4 but 18 State, 5 Int

pred reportGoal {
 	one s: AbstractState | 
		// An episode is achieved
		defEntity [s, Report] 
/*
		// only the correct fields are associated to the entities
			noExtraFields [s, Report, report_description + report_episode]	 and

		// all attributes are defined
			defAttribute [s, Report, report_description] and

		// associations multiplicity
			multiplicity [s, Report, report_episode] and

		// bidirectional relation
	 		bidirectionalRule [s, Episode, report_episode, Report, episode_report] and

		// dependencies hold
			dependence [s, Report, report_description_data_blood_pressure_dependence]
*/
}

//run reportGoal for 4 but 10 State, 5 int

pred dataGoal {
 	one s: AbstractState | 
		// An episode is achieved
		defEntity [s, Data] 
}

// run dataGoal for 4 but 2 State, 5 int

// def attribute
pred defAtt(s: AbstractState,  atts: FName) {
	some obj: s.objects | all att: atts | s.fields[obj, att] = DefVal
}


pred bloodPressureGoal {
 	one s: AbstractState | 
		// An episode is achieved
		defAtt [s, data_blood_pressure] 
}

run bloodPressureGoal for 4 but 3 State, 5 int

pred completeX {
 	one s: AbstractState | 
		// An episode is achieved
		defEntity [s, Episode] and

		// only the correct fields are associated to the entities
		noExtraFields [s, Patient, patient_name + patient_address + patient_episode] and
		noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report]	 and
		noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 and
		noExtraFields [s, Report, report_description + report_episode]	 and

		// all attributes are defined
		defAttribute [s, Patient, patient_name + patient_address]	and
		defAttribute [s, Episode, episode_reserve_date + episode_checkin + episode_checkout] and
		defAttribute [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition] and
		defAttribute [s, Report, report_description] and

		// associations multiplicity
		multiplicity [s, Patient, patient_episode] and
		multiplicity [s, Episode, episode_patient] and
		multiplicity [s, Episode, episode_data] and
		multiplicity [s, Data, data_episode] and
		multiplicity [s, Episode, episode_report] and
		multiplicity [s, Report, report_episode] and

		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and
 		bidirectionalRule [s, Episode, data_episode, Data, episode_data] and
 		bidirectionalRule [s, Episode, report_episode, Report, episode_report] and

		// dependencies hold
		dependence [s, Episode, episode_checkout_report_descripton_dependence] and
		dependence [s, Episode, episode_checkout_episode_checkin_dependence] and
		dependence [s, Report, report_description_data_blood_pressure_dependence]
}
