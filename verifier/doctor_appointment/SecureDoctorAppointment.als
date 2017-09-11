module filesystem/doctorappointment/SecureDoctorAppointment

open filesystem/SecureDataModel
open  filesystem/doctorappointment/DoctorAppointment


one sig Doctor, Receptionist, Nurse extends Role {}

one sig Alice, Bruno, Carlos, David extends User{}
