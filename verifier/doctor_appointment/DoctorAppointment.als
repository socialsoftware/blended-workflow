// Contains a doctor appointment model with with four entities
module filesystem/doctorappointment/DoctorAppointment

open filesystem/DataModel

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

//run {}
