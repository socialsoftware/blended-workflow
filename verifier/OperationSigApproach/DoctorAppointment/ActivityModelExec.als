module filesystem/DoctorAppointment/ActivityModelExec

open filesystem/DoctorAppointment/ActivityModel

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some a: Activity | 
		exec[s, s', a]
}

run complete for 6 but 7 State, 5 Int

assert PreservesInvariant {
	all s, s': State, a: Activity |
		Invariants[s] and exec[s, s', a] => Invariants[s']
}
check PreservesInvariant for 6 but 7 State, 5 Int
