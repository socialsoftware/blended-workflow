// Contains a doctor appointment model with with four entities
module filesystem/DoctorAppointment/DataModel

open filesystem/DataModel

sig Patient extends Entity {}
one sig patient_name extends Attribute {} 
one sig patient_address extends Attribute {} 
one sig patient_episode extends Attribute {} 
	{
		minMul = 0
		maxMul = 10
		inverse = episode_patient
	}

sig Episode extends Entity {}
one sig episode_reserve_date extends Attribute {} 
one sig episode_checkin extends Attribute {} 
one sig episode_checkout extends Attribute {} 
one sig episode_patient extends Attribute {} 
	{
		minMul = 1
		maxMul = 1
		inverse = patient_episode
	}
one sig episode_data extends Attribute {} 
	{
		minMul = 1
		maxMul = 1
		inverse = data_episode
	}
one sig episode_report extends Attribute {} 
	{
		minMul =1
		maxMul =1
		inverse = report_episode
	}
one sig episode_checkout_episode_checkin_dependence extends Dependence {}
	{
		sourceEntity = Episode
		sourceAttribute = episode_checkout
		path = none -> none
		targetAttribute = episode_checkin
	}
one sig episode_checkout_report_descripton_dependence extends Dependence {}
	{
		sourceEntity = Episode
		sourceAttribute = episode_checkout
		path = 0 -> episode_report
		targetAttribute = report_description
	}


sig Data extends Entity {}
one sig data_height extends Attribute {}
one sig data_weight extends Attribute {}
one sig data_blood_pressure extends Attribute {}
one sig data_physical_condition extends Attribute {}
one sig data_episode extends Attribute {}
	{
		minMul = 1
		maxMul = 1
		inverse = episode_data
	}

sig Report extends Entity {}
one sig report_description extends Attribute {}
one sig report_episode extends Attribute {}
	{
		minMul = 1
		maxMul = 1
		inverse = episode_report
	}
one sig report_description_data_blood_pressure_dependence extends Dependence {}
	{
		sourceEntity = Report
		sourceAttribute = report_description
		path =  0 -> report_episode + 1 -> episode_data
		targetAttribute = data_blood_pressure
	}

run {}
