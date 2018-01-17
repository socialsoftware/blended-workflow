module filesystem/doctorappointment/SecurePattern1DoctorAppointment

open filesystem/doctorappointment/SecureDoctorAppointment

sig Doctor extends Obj{}
one sig doctor_episode extends FName {} 
one sig episode_doctor extends FName {} 

one sig Doctor1 extends Doctor{}

fact relations {
	// relation Patient - Episode
	doctor_episode.minMul = 0
	doctor_episode.maxMul = 10
	doctor_episode.inverse = episode_doctor

	episode_doctor.minMul = 0
	episode_doctor.maxMul = 10
	episode_doctor.inverse = doctor_episode
}
