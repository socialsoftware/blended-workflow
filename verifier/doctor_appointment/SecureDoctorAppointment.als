module filesystem/doctorappointment/SecureDoctorAppointment

open filesystem/SecureDataModel
open  filesystem/doctorappointment/DoctorAppointment


one sig Doctor, Receptionist, Nurse extends Role {}

one sig Person1, Person2 extends User{}

pred AccessControlInvariants{
	
}

pred SecureComplete{
//Assegurar que que atinge o complete com o AC
}

