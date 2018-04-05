module filesystem/Examples/OneToMany/ActivityModelExec

open filesystem/ActivityModelExec
open filesystem/Examples/OneToMany/ActivityModel

run final for 3 but 4 State, 3 Activity, 1 DefEntityA, 2 DefEntityB, 5 Int

assert PreservesInvariant {
	all s, s': State, a: Activity |
		Invariants[s] and exec[s, s', a] => Invariants[s']
}
check PreservesInvariant for 3 but 4 State, 3 Activity, 1 DefEntityA, 2 DefEntityB, 5 Int
