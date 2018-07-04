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
one sig episode_checkout_episode_checkin_dependence extends Dependence {}
	{
		sourceEntity = Episode
		sourceAttribute = episode_checkout
		path = none -> none
		targetAttribute = episode_checkin
	}

run {}
