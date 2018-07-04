module filesystem/DoctorAppointment/GoalModel

open filesystem/GoalModel
open filesystem/DoctorAppointment/StateModel

sig DefPatient extends GoalProduce {}
	{
		one p: Patient | defEnts = p
		defAtts = defEnts -> { patient_name + patient_address }
	}

sig DefEpisode extends GoalProduce {}
	{
		one e: Episode | defEnts = e
		defAtts = defEnts -> episode_reserve_date
	}

sig DefEpisodeCheckin extends GoalProduce {}
	{
		defEnts = none
		one e: Episode | defAtts = e -> episode_checkin
	}

sig DefEpisodeCheckout extends GoalProduce {}
	{
		defEnts = none
		one e: Episode | defAtts = e -> episode_checkout 
	}

sig AssociatePatientEpisode extends GoalAssociate {}
	{
		one p: Patient, e: Episode | defMuls = p -> patient_episode -> e + e -> episode_patient -> p
	}
