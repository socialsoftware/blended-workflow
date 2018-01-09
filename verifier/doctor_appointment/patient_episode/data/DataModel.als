// contains the construction of a doctor appointment model using basic operations based on data, it violates some invariants
module filesystem/doctorappointment/patientepisode/data/DataModel

open filesystem/doctorappointment/patientepisode/DoctorAppointment

pred init (s: State) {
	no s.objects
	no s.fields
}

fact traces {
	first.init
	all s: State - last | let s' = s.next |
	some p: Patient, e: Episode | 
		defObj [s, s', p] or 
		defAtt [s, s', p, patient_name] or 
		defAtt [s, s', p, patient_address] or 
		defObj [s, s', e] or
	 	defAtt [s, s', e, episode_reserve_date] or 
		linkObj [s, s', p,  episode_patient, e, patient_episode] or
		linkObj [s, s', e, patient_episode, p, episode_patient] //or
		//skip [s, s']
}

assert initialState {
	one s: State | init[s] => Invariants [s]
}
//check initialState

// defObj preserves the invariant
assert DefObjPreservesInv {
	all s, s': State | all o: Obj|
		Invariants [s] and defObj [s, s', o] => Invariants [s']
}
//check DefObjPreservesInv for 4 but 15 State, 5 Int

// defAtt preserves the invariant for all except episode_reserve_date 
assert DefAttPreservesInv {
	all s, s': State | all o: Obj | all f: FName | 
		Invariants [s] and defAtt [s, s', o, f] => Invariants [s'] 
}
//check DefAttPreservesInv for 4 but 15 State, 5 Int

// linkObj preserves the invariant
assert LinkObjPreservesInv {
	all s, s': State, os, ot: Obj, rs, rt: FName |
		Invariants [s] and linkObj [s, s', os, rs, ot, rt] => Invariants [s']
}
// fails because does not verify if the there are objects of the same source type which already link to the target
//check LinkObjPreservesInv for 4 but 15 State, 5 Int

run complete for 4 but 15 State, 5 Int
