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

sig LinkPatientEpisode extends GoalAssociation {}
	{
		one p: Patient, e: Episode | defMuls = p -> patient_episode -> e + e -> episode_patient -> p
	}

sig DefData extends GoalProduce {}
	{
		one d: Data | defEnts = d
			one d: Data | defAtts = d -> { data_height + data_weight + data_blood_pressure + data_physical_condition }
	}

sig LinkEpisodeData extends GoalAssociation {}
	{
		one d: Data, e: Episode | defMuls = d -> data_episode -> e + e -> episode_data -> d
	}

sig DefReport extends GoalProduce {}
	{
		one r: Report | defEnts = r
		defAtts = none -> none
	}

sig DefReportDescription extends GoalProduce {}
	{
		defEnts = none
		one r: Report | defAtts = r -> report_description
	}

sig LinkEpisodeReport extends GoalAssociation {}
	{
		one r: Report, e: Episode | defMuls = r -> report_episode -> e + e -> episode_report -> r
	}

