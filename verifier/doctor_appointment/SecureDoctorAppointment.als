module filesystem/doctorappointment/SecureDoctorAppointment

open filesystem/SecureDataModel
open filesystem/doctorappointment/DoctorAppointment


one sig R_Doctor, R_Receptionist, R_Nurse extends Role {}

one sig Alice, Bruno, Carlos, David extends User{}


sig Doctor extends Obj{}
one sig doctor_episode extends FName{}
one sig episode_doctor extends FName{}


fact{
	doctor_episode.minMul = 0
	doctor_episode.maxMul = 10
	doctor_episode.inverse = episode_doctor

	episode_doctor.minMul = 0
	episode_doctor.maxMul = 10
	episode_doctor.inverse = doctor_episode
}
