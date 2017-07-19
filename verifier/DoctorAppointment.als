module DoctorAppointment

open StateModel

sig Patient extends Obj {}
one sig patient_name extends FName {}
one sig patient_address extends FName {}
one sig patient_appointment extends FName {}

sig Appointment extends Obj {}
one sig appointment_reserve_date extends FName {}
one sig appointment_patient extends FName {}

pred init (s: State) {
	no s.objects
	no s.fields
	s.dependencies = (Appointment -> appointment_reserve_date) -> (Appointment -> appointment_patient -> )
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment 
		| defObj [s, s', p] or defObj [s, s', a] or
		  defAtt [s, s', p, patient_name] or defAtt [s, s', p, patient_address] or linkObj [s, s', p, patient_appointment, a] or
		  defAtt [s, s', a, appointment_reserve_date] or linkObj [s, s', a, appointment_patient, p] or
		  skip [s, s']
}

// how many instances we are going to use to test the model
fact NumberOfObjects {
	#Patient = 1
	#Appointment = 2
}

pred complete {
	one s: State | 
	// cannot be the initial state to find one meaningful state
	#Patient <: s.objects = 1 and
	#Appointment <: s.objects = 2 and
	#s.objects = 3 and
	// model is well defined

	// all attributes are defined
	noExtraFields [s, Patient, patient_name + patient_address + patient_appointment] 	and 	
	noExtraFields [s, Appointment, appointment_reserve_date + appointment_patient]	and

	attributesDefined [s, Patient, patient_name + patient_address]	and
	attributesDefined [s, Appointment, appointment_reserve_date] and

	// associations multiplicity
	multiplicityRule [s, Appointment, appointment_patient, 1, 1] and
	multiplicityRule [s, Patient, patient_appointment, 0, 100] and

	// bidirectional relation
	bidirectionalRule [s, Patient, patient_appointment, Appointment, appointment_patient] 
}

run complete for 13
