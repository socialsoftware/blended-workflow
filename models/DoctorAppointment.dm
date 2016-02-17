Specification: "Doctor appointment"
        
Entity Patient exists {
	name : String
	age : Number
	heartProblems : Boolean
}         
                      
Entity Episode {
	reserveDate : Date
	checkin : Boolean
	checkout : Boolean               
} 
  
Entity Prescription {
	description : String dependsOn Prescription.episode.report.description
}

Entity Report dependsOn Report.episode.data.bloodPressure {
	description : String mandatory 
}
 
Entity Data { 
	height : Number
	weight : Number
	bloodPressure : Number
	physicalCondition : String
}

Entity Medication {
	group med dependsOn Medication.prescription.description {
		name : String
		quantity : Number
		heartImpact : Boolean
	}
	
	Constraint HeartAttackContraint: NOT (DEF (Medication.heartImpact))
	OR NOT (Medication.heartImpact)
	OR NOT (Medication.prescription.episode.patient.heartProblems)
	OR (Medication.quantity<100)
}
 
Association PatientHasEpisode {
	Patient with patient (1)
	Episode with episode (1..*)
}

Association EpisodeHasPrescription{
	Episode with episode (1)
	Prescription with prescription (0..1)
}

Association EpisodeHasData {
	Episode with episode (1)
	Data with data (1)
}

Association EpisodehasReport {
	Episode with episode (1)
	Report with report (1)
}
  
Association PrescriptionHasMedication {
	Prescription with prescription (1)
	Medication with medication (1..*)
}  
