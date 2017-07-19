module DoctorAppointmentExec

open util/ordering [State]

abstract sig Val {}
one sig DefVal extends Val {}
one sig UndefVal extends Val {}

sig Patient {}
sig Appointment {}

sig State {
	patients: set Patient,
	patient_name: Patient -> Val,
	patient_appointments: Patient -> set Appointment,

	appointments: set Appointment,
	appointment_patient: Appointment ->Patient
}

pred init (s: State) {
	no s.patients
	no s.patient_name
	no s.patient_appointments

	no s.appointments
	no s.appointment_patient
}

fact Traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, a: Appointment
		| addPatient [s, s', p] or addAppointment [s, s', p, a] or setPatientName [s, s', p]
}

pred addPatient(s, s' : State, p: Patient) {
	p !in s.patients
	s'.patients = s.patients + p
	s'.patient_name =s.patient_name + p -> UndefVal
	s'.patient_appointments = s.patient_appointments

	s'.appointments = s.appointments 
	s'.appointment_patient = s.appointment_patient
}

pred addAppointment(s, s' : State, p: Patient, a: Appointment) {
	p in s.patients
	a !in s.appointments

	s'.patients = s.patients
	s'.patient_name =s.patient_name
	s'.patient_appointments = s.patient_appointments + p -> (s.patient_appointments[p] + a)

	s'.appointments = s.appointments + a
	s'.appointment_patient = s.appointment_patient + a -> p
}

pred setPatientName(s, s': State, p: Patient) {
	p in s.patients
	s.patient_name[p] = UndefVal

	s'.patients = s.patients
	s'.patient_name =s.patient_name ++ p ->DefVal
	s'.patient_appointments = s.patient_appointments

	s'.appointments = s.appointments 
	s'.appointment_patient = s.appointment_patient
}

pred oneApointment(s: State) {
	#s.appointments = 1
}

pred twoPatient(s: State, i: Int) {
	#s.patients > i
}

pred complete {

	one s: State | 

	twoPatient[s, 1] and

	all p: s.patients | s.patient_name[p] = DefVal
	and
	// there is at least an appointment
//	#s.appointments = 1
	oneApointment[s] and twoPatient[s, 1] and #s.patients > 1
	// appointment of patient
	and
	all a: s.appointments | #s.appointment_patient[a] = 1 
//#s.appointments.patient = #s.appointments and

	// Patient - Appointment is bidirectional
	and
    all p: s.patients | all a: s.patient_appointments[p] | p = s.appointment_patient[a]
	and
    all a: s.appointments | a in s.patient_appointments[s.appointment_patient[a]]
}

run complete for 7
