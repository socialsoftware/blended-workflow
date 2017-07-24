module filesystem/doctorappointment/DoctorAppointment

open filesystem/DataModel

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

pred complete {
 	one s: State | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Appointment <: s.objects = 2 and
		#s.objects = 3 and
		// model is well defined

		// all attributes are defined
		attributesDefined [s, Patient, patient_name + patient_address]	and
		attributesDefined [s, Appointment, appointment_reserve_date] and

		// associations multiplicity
		multiplicityRule [s, Appointment, appointment_patient, 1, 1] and
		multiplicityRule [s, Patient, patient_appointment, 0, 100] and

		// bidirectional relation
		bidirectionalRule [s, Patient, patient_appointment, Appointment, appointment_patient] 
}
