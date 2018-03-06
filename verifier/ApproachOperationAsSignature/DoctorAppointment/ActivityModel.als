module filesystem/DoctorAppointment/ActivityModel

open filesystem/ActivityModel
open filesystem/DoctorAppointment/StateModel

sig RegisterPatient extends Activity {}
	{
		one p: Patient | defEnts = p
		defAtts = defEnts -> patient_name + defEnts -> patient_address
		defMuls = none -> none -> none
	}

sig BookAppointment extends Activity {}
	{
   		one e: Episode | defEnts = e
		defAtts = defEnts -> episode_reserve_date
		one p: Patient | defMuls = p -> patient_episode -> defEnts + defEnts -> episode_patient -> p
	}

sig Checkin extends Activity {}
	{
	 	defEnts = none
		one e: Episode | defAtts = e -> episode_checkin
		defMuls = none -> none -> none
	}

sig Checkout extends Activity {}
	{
	 	defEnts = none
		one e: Episode | defAtts = e -> episode_checkout 
		defMuls = none -> none -> none
	}

sig CollectData extends Activity {}
	{
		one d: Data | defEnts = d
		defAtts = defEnts -> {data_height + data_weight + data_blood_pressure + data_physical_condition}
		one e: Episode | defMuls = defEnts -> data_episode -> e + e -> episode_data -> defEnts
	}

sig WriteReport extends Activity {}
	{
		one r: Report | defEnts = r
		defAtts = defEnts -> report_description
		one e: Episode | defMuls = defEnts -> report_episode -> e + e -> episode_report -> defEnts
	}
run {}
