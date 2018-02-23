// Contains a doctor appointment model with with four entities
module filesystem/DoctorAppointment/DataModel

open filesystem/DataModel

sig Patient extends Obj {}
one sig patient_name extends FName {} 
one sig patient_address extends FName {} 
one sig patient_episode extends FName {} 
	{
		minMul = 0
		maxMul = 10
		inverse = episode_patient
	}

sig Episode extends Obj {}
one sig episode_reserve_date extends FName {} 
one sig episode_checkin extends FName {} 
one sig episode_checkout extends FName {} 
one sig episode_patient extends FName {} 
	{
		minMul = 1
		maxMul = 1
		inverse = patient_episode
	}
one sig episode_data extends FName {} 
	{
		minMul = 1
		maxMul = 1
		inverse = data_episode
	}
one sig episode_report extends FName {} 
	{
		minMul =1
		maxMul =1
		inverse = report_episode
	}
one sig episode_checkout_episode_checkin_dependence extends Dependence {}
	{
		sourceObj = Episode
		sourceAtt = episode_checkout
		sequence = none -> none
		targetAtt = episode_checkin
	}
one sig episode_checkout_report_descripton_dependence extends Dependence {}
	{
		sourceObj = Episode
		sourceAtt = episode_checkout
		sequence = 0 -> episode_report
		targetAtt = report_description
	}


sig Data extends Obj {}
one sig data_height extends FName {}
one sig data_weight extends FName {}
one sig data_blood_pressure extends FName {}
one sig data_physical_condition extends FName {}
one sig data_episode extends FName {}
	{
		minMul = 1
		maxMul = 1
		inverse = episode_data
	}

sig Report extends Obj {}
one sig report_description extends FName {}
one sig report_episode extends FName {}
	{
		minMul = 1
		maxMul = 1
		inverse = episode_report
	}
one sig report_description_data_blood_pressure_dependence extends Dependence {}
	{
		sourceObj = Report
		sourceAtt = report_description
		sequence =  0 -> report_episode + 1 -> episode_data
		targetAtt = data_blood_pressure
	}

