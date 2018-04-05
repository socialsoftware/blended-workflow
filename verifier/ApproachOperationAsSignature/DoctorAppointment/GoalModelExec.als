module filesystem/DoctorAppointment/GoalModelExec

open filesystem/GoalModelExec

open filesystem/DoctorAppointment/GoalModel

run final for 4 but 11 State, 10 Goal, 5 Int

assert PreservesInvariant {
	all s, s': State, g: Goal |
		(g in GoalProduce) implies {
			Invariants[s] and execProduce[s, s', g] => Invariants[s']
		} else {
			Invariants[s] and execAssociate[s, s', g] => Invariants[s']
		}
}
check PreservesInvariant for 4 but 11 State, 10 Goal, 5 Int
