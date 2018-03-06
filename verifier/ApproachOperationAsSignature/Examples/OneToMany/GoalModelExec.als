module filesystem/Examples/OneToMany/GoalModelExec

open filesystem/Examples/OneToMany/GoalModel

sig State extends AbstractState {}

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some gp: GoalProduce, ga: GoalAssociation | 
		execProduce[s, s', gp] or
		execAssociation[s, s', ga]
}

run complete for 2 but 7 State, 6 Goal, 5 Int

assert PreservesInvariant {
	all s, s': State, g: Goal |
		(g in GoalProduce) implies {
			Invariants[s] and execProduce[s, s', g] => Invariants[s']
		} else {
			Invariants[s] and execAssociation[s, s', g] => Invariants[s']
		}
}
check PreservesInvariant for 2 but 7 State, 6 Goal, 5 Int
