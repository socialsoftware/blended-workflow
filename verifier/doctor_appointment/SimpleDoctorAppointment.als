module filesystem/doctorappointment/SimpleDoctorAppointment

open filesystem/DataModel

sig Patient extends Obj {}
one sig patient_name extends FName {} 
one sig patient_address extends FName {} 
one sig patient_episode extends FName {} 

sig Episode extends Obj {}
one sig episode_reserve_date extends FName {} 
one sig episode_patient extends FName {} 

fact relations {
	// relation Patient - Episode
	patient_episode.minMul = 0
	patient_episode.maxMul = 10
	patient_episode.inverse = episode_patient

	episode_patient.minMul = 1
	episode_patient.maxMul = 1
	episode_patient.inverse = patient_episode
}
