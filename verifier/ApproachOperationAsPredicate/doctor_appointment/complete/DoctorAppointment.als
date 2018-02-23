// Contains a doctor appointment model with with four entities
module filesystem/doctorappointment/complete/DoctorAppointment

open filesystem/BWSpec

sig Patient extends Obj {}
one sig patient_name extends FName {} 
one sig patient_address extends FName {} 
one sig patient_episode extends FName {} 

sig Episode extends Obj {}
one sig episode_reserve_date extends FName {} 
one sig episode_checkin extends FName {} 
one sig episode_checkout extends FName {} 
one sig episode_patient extends FName {} 
one sig episode_data extends FName {} 
one sig episode_report extends FName {} 

sig episode_checkout_report_descripton_dependence extends Dependence {}
sig episode_checkout_episode_checkin_dependence extends Dependence {}
sig report_description_data_blood_pressure_dependence extends Dependence {}

sig Data extends Obj {}
one sig data_height extends FName {}
one sig data_weight extends FName {}
one sig data_blood_pressure extends FName {}
one sig data_physical_condition extends FName {}
one sig data_episode extends FName {}

sig Report extends Obj {}
one sig report_description extends FName {}
one sig report_episode extends FName {}

fact relations {
	// relation Patient - Episode
	patient_episode.minMul = 0
	patient_episode.maxMul = 10
	patient_episode.inverse = episode_patient

	episode_patient.minMul = 1
	episode_patient.maxMul = 1
	episode_patient.inverse = patient_episode

	// relation Episode - Data
	episode_data.minMul = 1
	episode_data.maxMul = 1
	episode_data.inverse = data_episode

	data_episode.minMul = 1
	data_episode.maxMul = 1
	data_episode.inverse = episode_data

	// relation Episode - Report
	episode_report.minMul =1
	episode_report.maxMul =1
	episode_report.inverse = report_episode

	report_episode.minMul = 1
	report_episode.maxMul = 1
	report_episode.inverse = episode_report
}

fact dependencies {
	episode_checkout_report_descripton_dependence.sourceObj = Episode
	episode_checkout_report_descripton_dependence.sourceAtt = episode_checkout
	episode_checkout_report_descripton_dependence.sequence = 0 -> episode_report
	episode_checkout_report_descripton_dependence.targetAtt = report_description

	episode_checkout_episode_checkin_dependence.sourceObj = Episode
	episode_checkout_episode_checkin_dependence.sourceAtt = episode_checkout
	episode_checkout_episode_checkin_dependence.sequence = none -> none
	episode_checkout_episode_checkin_dependence.targetAtt = episode_checkin

	report_description_data_blood_pressure_dependence.sourceObj = Report
	report_description_data_blood_pressure_dependence.sourceAtt = report_description
	report_description_data_blood_pressure_dependence.sequence =  0 -> report_episode + 1 -> episode_data
	report_description_data_blood_pressure_dependence.targetAtt = data_blood_pressure
}

pred complete {
 	one s: AbstractState | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#Data<: s.objects = 1 and
		#Report <: s.objects = 1 and
		#s.objects = 4 and
		// model is well defined

		// only the correct fields are associated to the entities
		noExtraFields [s, Patient, patient_name + patient_address + patient_episode] and
		noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report]	 and
		noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 and
		noExtraFields [s, Report, report_description + report_episode]	 and

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Episode, episode_reserve_date + episode_checkin + episode_checkout] and
		attributesDefined [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition] and
		attributesDefined [s, Report, report_description] and

		// associations multiplicity
		multiplicityRule [s, Patient, patient_episode] and
		multiplicityRule [s, Episode, episode_patient] and
		multiplicityRule [s, Episode, episode_data] and
		multiplicityRule [s, Data, data_episode] and
		multiplicityRule [s, Episode, episode_report] and
		multiplicityRule [s, Report, report_episode] and


		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode] and
 		bidirectionalRule [s, Episode, data_episode, Data, episode_data] and
 		bidirectionalRule [s, Episode, report_episode, Report, episode_report] and

		// dependencies hold
		checkDependence [s, Episode, episode_checkout_report_descripton_dependence] and
		checkDependence [s, Episode, episode_checkout_episode_checkin_dependence] and
		checkDependence [s, Report, report_description_data_blood_pressure_dependence]
}

pred Invariants(s: AbstractState) {
	// only the correct fields are associated to the entities
	noExtraFields [s, Patient, patient_name + patient_address + patient_episode] 
	noExtraFields [s, Episode, episode_reserve_date + episode_checkin + episode_checkout + episode_patient + episode_data + episode_report]	 
	noExtraFields [s, Data, data_height + data_weight + data_blood_pressure + data_physical_condition + data_episode]	 
	noExtraFields [s, Report, report_description + report_episode]	 

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Patient, patient_episode] 
	noMultiplicityExceed [s, Episode, episode_patient] 
	noMultiplicityExceed [s, Episode, episode_data] 
	noMultiplicityExceed [s, Data, data_episode] 
	noMultiplicityExceed [s, Episode, episode_report] 
	noMultiplicityExceed [s, Report, report_episode] 

	// bidirectional relation
	bidirectionalPreservation [s, Patient, episode_patient, Episode, patient_episode]
 	bidirectionalPreservation [s, Episode, data_episode, Data, episode_data]
 	bidirectionalPreservation [s, Episode, report_episode, Report, episode_report]

	// if there is a link between two objects, either is unidirectional or bidirectional
	checkDependence [s, Episode, episode_checkout_report_descripton_dependence]
	checkDependence [s, Episode, episode_checkout_episode_checkin_dependence]
	checkDependence [s, Report, report_description_data_blood_pressure_dependence]
}




//run {}
