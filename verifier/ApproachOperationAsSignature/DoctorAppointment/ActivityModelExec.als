module filesystem/DoctorAppointment/ActivityModelExec

open filesystem/ActivityModelExec

open filesystem/DoctorAppointment/ActivityModel

run final for 4 but 7 State, 6 Activity, 5 Int

assert PreservesInvariant {
	all s, s': State, a: Activity |
		Invariants[s] and exec[s, s', a] => Invariants[s']
}

check PreservesInvariant for 4 but 7 State, 6 Activity, 5 Int
