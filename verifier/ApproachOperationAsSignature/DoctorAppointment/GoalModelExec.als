module filesystem/DoctorAppointment/GoalModelExec

open filesystem/GoalModelExec

open filesystem/DoctorAppointment/GoalModel

run final for 4 but 11 State, 10 Goal, 5 Int

assert preserveInvariants {
	all s, s': State, g: Goal |
		(g in GoalProduce) implies {
			invariants[s] and execProduce[s, s', g] => invariants[s']
		} else {
			invariants[s] and execAssociate[s, s', g] => invariants[s']
		}
}
check preserveInvariants for 4 but 11 State, 10 Goal, 5 Int
