// Contains a simple doctor appointment model with a dependence appointment.reserve_date -> appointment.appointment_patient.patient_address
module filesystem/doctorappointment/DoctorAppointmentTwoEntities

open filesystem/DataModel

sig Patient extends Obj {}
one sig patient_name extends FName {} 
one sig patient_address extends FName {} 
one sig patient_appointment extends FName {} 

sig Appointment extends Obj {}
one sig appointment_reserve_date extends FName {} 
one sig appointment_patient extends FName {} 

fact relations {
	patient_appointment.minMul = 0
	patient_appointment.maxMul = 10
	patient_appointment.inverse = appointment_patient

	appointment_patient.minMul = 1
	appointment_patient.maxMul = 1
	appointment_patient.inverse = patient_appointment
}

pred Invariants(s: State) {
	// no extra fields
	noExtraFields [s, Patient, patient_name + patient_address + patient_appointment] 	 	
	noExtraFields [s, Appointment, appointment_reserve_date + appointment_patient]	

	// does not exceeds mutliplicity
	noMultiplicityExceed [s, Appointment, appointment_patient] 
	noMultiplicityExceed [s, Patient, patient_appointment] 

	// if there is a link between two objects, either is unidirectional or bidirectional
	bidirectionalPreservation [s, Patient, appointment_patient, Appointment, patient_appointment] 

	// dependencies hold
	dependence [s, Appointment, appointment_reserve_date, 0 -> appointment_patient, patient_address]
}

pred complete {
 	one s: State | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 2 and
		#Appointment <: s.objects = 2 and
		#s.objects = 4 and
		// model is well defined

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Appointment, appointment_reserve_date] and

		// associations multiplicity
		multiplicityRule [s, Appointment, appointment_patient] and
		multiplicityRule [s, Patient, patient_appointment] and

		// bidirectional relation
		bidirectionalRule [s, Patient, appointment_patient, Appointment, patient_appointment] and

		// dependencies hold
		dependence [s, Appointment, appointment_reserve_date, 0 -> appointment_patient, patient_address]
}

run {}
