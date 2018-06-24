module filesystem/doctorappointment/example/activity/chagesinspecifcationvalidation

open filesystem/SecureActivityConditions

/**
Model Spec
**/
sig Patient extends Obj {}
one sig patient_name extends FName {} 
one sig patient_episode extends FName {} 

sig Episode extends Obj {}
one sig episode_reserve_date extends FName {}
one sig episode_patient extends FName{}

fact relations {
	// relation Patient - Episode
	patient_episode.minMul = 0
	patient_episode.maxMul = 10
	patient_episode.inverse = episode_patient

	episode_patient.minMul = 1
	episode_patient.maxMul = 1
	episode_patient.inverse = patient_episode
}
one sig Carl, Alice, John extends User{}

one sig R_Receptionist, R_Doctor, R_Patient, R_Receptionist2 extends RoleSubject{}

/**
Complete State
**/
pred complete {
 	one s: AbstractState | 
		// cannot be the initial state to find one meaningful state
		#Patient <: s.objects = 1 and
		#Episode <: s.objects = 1 and
		#s.objects = 2 and
		// model is well defined
		// only the correct fields are associated to the entities
		noExtraFields [s, Patient, patient_name + patient_episode] and
		noExtraFields [s, Episode, episode_reserve_date + episode_patient]	 and
		// all attributes are defined
		attributesDefined [s, Patient, patient_name]	and
		attributesDefined [s, Episode, episode_reserve_date] and
		// associations multiplicity
		multiplicityRule [s, Patient, patient_episode] and
		multiplicityRule [s, Episode, episode_patient] and
		// bidirectional relation
		bidirectionalRule [s, Patient, episode_patient, Episode, patient_episode]
}

/**
Activity Specification
**/

one sig registerPatient, createPatient, namePatient, bookAppointment, createPatientAndAppointment extends Operation{}

pred registerPatient(s, s':AbstractState, p: Patient, u:User) {
	secureActivity[s, s', none, none->none, p, p->patient_name, none->none->none , registerPatient, u, none, none]
}

pred createPatient(s, s':AbstractState, p: Patient, u:User) {
	secureActivity[s, s', none, none->none, p,none->none, none->none->none , createPatient, u, none, none]
}
pred namePatient(s, s':AbstractState, p: Patient, u:User) {
	secureActivity[s, s', p, none->none, none, p->patient_name, none->none->none , namePatient, u, none, none]
}

pred bookAppointment(s, s': AbstractState, p: Patient, e: Episode, u:User) {
	secureActivity[s, s', p, none->none, e, e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p), bookAppointment, u, none, none]
}

pred createPatientAndAppointment(s, s': AbstractState, p: Patient, e: Episode, u:User) {
	secureActivity[s, s', none, none->none, e+p, p->patient_name + e -> episode_reserve_date, (p -> patient_episode -> e) + (e -> episode_patient -> p), createPatientAndAppointment, u, none, none]
}



/**
User Model
**/
pred userModel{
	AccessControlRules.users = {Carl}
	AccessControlRules.roles = {R_Receptionist}
	AccessControlRules.u_roles = {Carl -> R_Receptionist} 
}

/**
Rule Model
**/
pred ruleModel{
	AccessControlRules.resources = {
		Patient +  patient_name + patient_episode +
		Episode + episode_reserve_date + episode_patient +
		registerPatient + createPatient + namePatient + bookAppointment + createPatientAndAppointment
	}
	AccessControlRules.permissions = 
		{Def -> {
				(R_Receptionist -> {Patient +  patient_name + patient_episode + Episode + episode_reserve_date + episode_patient}) 
			}	
		+
		Read -> {
				(R_Receptionist->{Patient})	
			}	
		}
	no AccessControlRules.delegation
}
/**
Blended Workflow
**/

sig SecureState extends AbstractSecureState{}

pred init (s: SecureState) {
	no s.objects
	no s.fields
	no s.log
}

pred execution {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User| 
		registerPatient[s, s', p, u] or  
		bookAppointment[s, s', p, e, u] 
}

pred achieveComplete{
	userModel
	ruleModel
	execution
	complete
}

//run achieveComplete for 5 but 3 SecureState, 5 Int


/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/**
Alternative User Models
**/

pred userModelNewUserAdded{
	AccessControlRules.users = {Carl + Alice }
	AccessControlRules.roles = {R_Receptionist + R_Doctor}
	AccessControlRules.u_roles = {Carl -> R_Receptionist + Alice -> R_Doctor} 
}

assert differentUserModelCantAchieveComplete1{
	userModelNewUserAdded and ruleModel and execution 
		implies !complete
}
//generates counterexample on purpose
//check differentUserModelCantAchieveComplete1 for 5 but 3 SecureState, 5 Int



pred userModelNewRoleAdded{
	AccessControlRules.users = {Carl}
	AccessControlRules.roles = {R_Receptionist + R_Patient}
	AccessControlRules.u_roles = {Carl -> {R_Receptionist + R_Patient}} 
}

assert differentUserModelCantAchieveComplete2{
	userModelNewRoleAdded and ruleModel and execution 
		implies !complete
}
//generates counterexample on purpose
//check differentUserModelCantAchieveComplete2 for 5 but 3 SecureState, 5 Int



pred userModelRemovedUser{
	no AccessControlRules.users
	AccessControlRules.roles = {R_Receptionist}
	no AccessControlRules.u_roles 
}

assert differentUserModelCantAchieveComplete3{
	userModelRemovedUser and ruleModel and execution 
		implies !complete
}
//Check
//check differentUserModelCantAchieveComplete3 for 5 but 3 SecureState, 5 Int



pred userModelRemovedRole{
	AccessControlRules.users = {Carl}
	AccessControlRules.roles = {R_Receptionist} 
	no AccessControlRules.u_roles
}

assert differentUserModelCantAchieveComplete4{
	userModelRemovedRole and ruleModel and execution 
		implies !complete
}
//Check
//check differentUserModelCantAchieveComplete4 for 5 but 3 SecureState, 5 Int



pred userModelReplacedUser{
	AccessControlRules.users = {John}
	AccessControlRules.roles = {R_Receptionist}
	AccessControlRules.u_roles = {John -> {R_Receptionist}} 
}

assert differentUserModelCantAchieveComplete5{
	userModelReplacedUser and ruleModel and execution 
		implies !complete
}
//generates counterexample on purpose
//check differentUserModelCantAchieveComplete5 for 5 but 3 SecureState, 5 Int

/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/**
Alternative Rule Models
**/
pred ruleModelWithoutADef{
	AccessControlRules.resources = {
		Patient +  patient_name + patient_episode +
		Episode + episode_reserve_date + episode_patient +
		registerPatient + createPatient + namePatient + bookAppointment + createPatientAndAppointment
	}
	AccessControlRules.permissions = 
		{Def -> {
				(R_Receptionist -> {Patient + patient_episode + Episode + episode_reserve_date + episode_patient}) 
			}	
		+
		Read -> {
				(R_Receptionist->{Patient})	
			}	
		}
	no AccessControlRules.delegation
}

assert differentRuleModelCantAchieveComplete1{
	userModel and ruleModelWithoutADef and execution 
		implies !complete
}
//Checks
//check differentRuleModelCantAchieveComplete1 for 5 but 3 SecureState, 5 Int



pred ruleModelWithoutARead{
	AccessControlRules.resources = {
		Patient +  patient_name + patient_episode +
		Episode + episode_reserve_date + episode_patient +
		registerPatient + createPatient + namePatient + bookAppointment + createPatientAndAppointment
	}
	AccessControlRules.permissions = 
		{Def -> {
				(R_Receptionist -> {Patient +  patient_name + patient_episode + Episode + episode_reserve_date + episode_patient}) 
			}	
		}
	no AccessControlRules.delegation
}

assert differentRuleModelCantAchieveComplete2{
	userModel and ruleModelWithoutARead and execution 
		implies !complete
}
//Checks
//check differentRuleModelCantAchieveComplete2 for 5 but 3 SecureState, 5 Int



pred ruleModelOperations{
	AccessControlRules.resources = {
		Patient +  patient_name + patient_episode +
		Episode + episode_reserve_date + episode_patient +
		registerPatient + createPatient + namePatient + bookAppointment + createPatientAndAppointment
	}
	AccessControlRules.permissions = 
		{Def -> {
				(R_Receptionist -> {registerPatient +bookAppointment}) 
			}	
		}	
	no AccessControlRules.delegation
}

assert differentRuleModelCantAchieveComplete3{
	userModel and ruleModelOperations and execution 
		implies !complete
}
//generates counterexample on purpose
//check differentRuleModelCantAchieveComplete3 for 5 but 3 SecureState, 5 Int



pred ruleModelHalfOperationHalfResource{
	AccessControlRules.resources = {
		Patient +  patient_name + patient_episode +
		Episode + episode_reserve_date + episode_patient +
		registerPatient + createPatient + namePatient + bookAppointment + createPatientAndAppointment
	}
	AccessControlRules.permissions = 
		{Def -> {
				(R_Receptionist -> {Patient +  patient_name + bookAppointment}) 
			}	
		}
	no AccessControlRules.delegation
}

assert differentRuleModelCantAchieveComplete4{
	userModel and ruleModelHalfOperationHalfResource and execution 
		implies !complete
}
//generates counterexample on purpose
//check differentRuleModelCantAchieveComplete4 for 5 but 3 SecureState, 5 Int

/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/**
Alternative Executions
**/


//Execution with registerPatient divided in createPatient and namePatient
pred executionWithOperationDivision {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User| 
		createPatient[s, s', p, u] or 
		namePatient[s, s', p, u] or 
		bookAppointment[s, s', p, e, u] 
}

assert differentExecutiomCantAchieveComplete1{
	userModel and ruleModel and executionWithOperationDivision 
		implies !complete
}
//generates counterexample on purpose
//check differentExecutiomCantAchieveComplete1 for 5 but 4 SecureState, 5 Int





//Execution with registerPatient and bookAppointment merged in createPatientAndAppointment
pred executionWithOperationMerge {
	first.init
	all s: SecureState - last | let s' = s.next |
	some p: Patient, e: Episode, u: User| 
		createPatientAndAppointment[s, s', p, e, u] 
}

assert differentExecutionCantAchieveComplete2{
	userModel and ruleModel and executionWithOperationMerge 
		implies !complete
}
//generates counterexample on purpose
//check differentExecutiomCantAchieveComplete2 for 5 but 2 SecureState, 5 Int



/**
Execution with division for diferente Rule Models
**/

//Permissions over operations
assert differentExecutionCantAchieveComplete3{
	userModel and ruleModelOperations and executionWithOperationDivision
		=> !complete
}
//checks
//check differentExecutionCantAchieveComplete3 for 5 but 4 SecureState, 5 Int

//Permissions half resources half operations
assert differentExecutionCantAchieveComplete4{
	userModel and ruleModelHalfOperationHalfResource and executionWithOperationDivision
		=> !complete
}
//checks
//check differentExecutionCantAchieveComplete4 for 5 but 4 SecureState, 5 Int


/**
Execution with merge for diferente Rule Models
**/

//Permissions over operations
assert differentExecutionCantAchieveComplete5{
	userModel and ruleModelOperations and executionWithOperationMerge
		=> !complete
}
//checks
//check differentExecutionCantAchieveComplete5 for 5 but 2 SecureState, 5 Int

//Permissions half resources half operations
assert differentExecutionCantAchieveComplete6{
	userModel and ruleModelHalfOperationHalfResource and executionWithOperationMerge
		=> !complete
}
//checks
//check differentExecutionCantAchieveComplete6 for 5 but 2 SecureState, 5 Int

/////////////////////////////////////////////////////////


// new User Model

pred newUserModel{
	AccessControlRules.users = {Carl + John}
	AccessControlRules.roles = {R_Receptionist + R_Receptionist2}
	AccessControlRules.u_roles = {Carl -> R_Receptionist + John -> R_Receptionist2} 
}


// new Rule Model
pred newRuleModel{
	AccessControlRules.resources = {
		Patient +  patient_name + patient_episode +
		Episode + episode_reserve_date + episode_patient +
		registerPatient + createPatient + namePatient + bookAppointment + createPatientAndAppointment
	}
	AccessControlRules.permissions = 
		{Def -> {
				(R_Receptionist -> {Patient +  patient_name }) 
				+ (R_Receptionist2 -> {patient_episode + Episode + episode_reserve_date + episode_patient})
			}	
		+
		Read -> {
				(R_Receptionist2->{Patient})	
			}	
		}
	no AccessControlRules.delegation
}


//Permissions over operations
assert differentExecutionCantAchieveComplete7{
	newUserModel and newRuleModel and executionWithOperationDivision
		=> !complete
}
//checks
check differentExecutionCantAchieveComplete7 for 5 but 4 SecureState, 5 Int

//Permissions half resources half operations
assert differentExecutionCantAchieveComplete8{
	newUserModel and newRuleModel and executionWithOperationMerge
		=> !complete
}
//checks
//check differentExecutionCantAchieveComplete8 for 5 but 2 SecureState, 5 Int





