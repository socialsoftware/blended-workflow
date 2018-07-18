module filesystem/DoctorAppointment/ActivityModelExec

open filesystem/ActivityModelExec

open filesystem/DoctorAppointment/ActivityModel

run final for 2 but 5 State, 4 Activity, 5 Int

assert preserveInvariants {
	all s, s': State, a: Activity |
		invariants[s] and exec[s, s', a] => invariants[s']
}

check preserveInvariants for 2 but 5 State, 4 Activity, 5 Int
