module DoctorAppointment

open StateModel

sig Patient extends Obj {}
one sig patient_name extends FName {}
one sig patient_address extends FName {}
one sig patient_appointment extends FName {}

sig Appointment extends Obj {}
one sig appointment_reserve_date extends FName {}
one sig appointment_patient extends FName {}

pred Invariants(s: State) {
	// no extra fields
	noExtraFields [s, Patient, patient_name + patient_address + patient_appointment] 	and 	
	noExtraFields [s, Appointment, appointment_reserve_date + appointment_patient]	and

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Appointment, appointment_patient, 1] and
	noMultiplicityExceed [s, Patient, patient_appointment, 100]

	// if there is a link between two objects, either is unidirectional or bidirectional
	bidirectionalViolation [s, Patient, patient_appointment, 1, Appointment, appointment_patient, 100] 
}

