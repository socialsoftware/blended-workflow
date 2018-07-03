module filesystem/DoctorAppointment/ActivityModelExec

open filesystem/ActivityModelExec

open filesystem/DoctorAppointment/ActivityModel

run final for 4 but 7 State, 6 Activity, 5 Int

assert preserveInvariants {
	all s, s': State, a: Activity |
		invariants[s] and exec[s, s', a] => invariants[s']
}

check preserveInvariants for 4 but 7 State, 6 Activity, 5 Int
