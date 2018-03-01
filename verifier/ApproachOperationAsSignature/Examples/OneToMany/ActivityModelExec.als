module filesystem/Examples/OneToMany/ActivityModelExec

open filesystem/Examples/OneToMany/ActivityModel

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

run complete for 2 but 4 State, 3 Activity, 5 Int

assert PreservesInvariant {
	all s, s': State, a: Activity |
		Invariants[s] and exec[s, s', a] => Invariants[s']
}
check PreservesInvariant for 2 but 4 State, 3 Activity, 5 Int
