module filesystem/Examples/OneToMany/GoalModelExec

open filesystem/GoalModelExec
open filesystem/Examples/OneToMany/GoalModel

run final for 2 but 6 State, 5 Goal, 5 Int

assert PreservesInvariant {
	all s, s': State, g: Goal |
		(g in GoalProduce) implies {
			Invariants[s] and execProduce[s, s', g] => Invariants[s']
		} else {
			Invariants[s] and execAssociate[s, s', g] => Invariants[s']
		}
}
check PreservesInvariant for 2 but 6 State, 5 Goal, 5 Int
