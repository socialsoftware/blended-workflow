package pt.ist.socialsoftware.blendedworkflow.adapters.test;

public class WorkletAdapterConditionsTest {
	
//	public void evaluate() throws BlendedWorkflowException {
//		String name;
//		RuleType ruleType = RuleType.ItemConstraintViolation;
//		Element eData;
//		String conclusion;
//		String cs;
//
//		name = "Check-in_Patient";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>" + 
//				"<Patient_Name_State>UNDEFINED</Patient_Name_State>"+ 
//				"<Patient_Name>$UNDEFINED$</Patient_Name>"+
//				"<Episode_Date_State>UNDEFINED</Episode_Date_State>"+
//				"<Episode_Date>$UNDEFINED$</Episode_Date>"+
//				"<MedicalReport_Report_State>UNDEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$UNDEFINED$</MedicalReport_Report>"+
//				"<Episode_Closed_State>UNDEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>$UNDEFINED$</Episode_Closed>"+
//				"<Patient_PhoneNumber_State>UNDEFINED</Patient_PhoneNumber_State>"+"" +
//				"<Patient_PhoneNumber>$UNDEFINED$</Patient_PhoneNumber>"+"" +
//				"<Patient_Address_State>UNDEFINED</Patient_Address_State>"+
//				"<Patient_Address>$UNDEFINED$</Patient_Address>"+
//				"<Patient_Gender_State>UNDEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$UNDEFINED$</Patient_Gender>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE=" + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>" + 
//				"<Patient_Name_State>DEFINED</Patient_Name_State>"+ 
//				"<Patient_Name>$DEFINED$</Patient_Name>"+
//				"<Episode_Date_State>DEFINED</Episode_Date_State>"+
//				"<Episode_Date>$DEFINED$</Episode_Date>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"<Episode_Closed_State>DEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>$DEFINED$</Episode_Closed>"+
//				"<Patient_PhoneNumber_State>DEFINED</Patient_PhoneNumber_State>"+"" +
//				"<Patient_PhoneNumber>$DEFINED$</Patient_PhoneNumber>"+"" +
//				"<Patient_Address_State>DEFINED</Patient_Address_State>"+
//				"<Patient_Address>$DEFINED$</Patient_Address>"+
//				"<Patient_Gender_State>DEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$DEFINED$</Patient_Gender>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE=" + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>" + 
//				"<Patient_Name_State>SKIPPED</Patient_Name_State>"+ 
//				"<Patient_Name>$SKIPPED$</Patient_Name>"+
//				"<Episode_Date_State>SKIPPED</Episode_Date_State>"+
//				"<Episode_Date>$SKIPPED$</Episode_Date>"+
//				"<MedicalReport_Report_State>SKIPPED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$SKIPPED$</MedicalReport_Report>"+
//				"<Episode_Closed_State>SKIPPED</Episode_Closed_State>"+
//				"<Episode_Closed>$SKIPPED$</Episode_Closed>"+
//				"<Patient_PhoneNumber_State>SKIPPED</Patient_PhoneNumber_State>"+"" +
//				"<Patient_PhoneNumber>$SKIPPED$</Patient_PhoneNumber>"+"" +
//				"<Patient_Address_State>SKIPPED</Patient_Address_State>"+
//				"<Patient_Address>$SKIPPED$</Patient_Address>"+
//				"<Patient_Gender_State>SKIPPED</Patient_Gender_State>"+
//				"<Patient_Gender>$SKIPPED$</Patient_Gender>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP=" + parseConclusion(conclusion));
//
//		name = "Physical_Examination";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<PatientData_PhysicalReport_State>UNDEFINED</PatientData_PhysicalReport_State>"+
//				"<PatientData_PhysicalReport>$UNDEFINED$</PatientData_PhysicalReport>"+
//				"<PatientData_PhysicalExamination_State>UNDEFINED</PatientData_PhysicalExamination_State>"+
//				"<PatientData_PhysicalExamination>$UNDEFINED$</PatientData_PhysicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<PatientData_PhysicalReport_State>DEFINED</PatientData_PhysicalReport_State>"+
//				"<PatientData_PhysicalReport>$DEFINED$</PatientData_PhysicalReport>"+
//				"<PatientData_PhysicalExamination_State>DEFINED</PatientData_PhysicalExamination_State>"+
//				"<PatientData_PhysicalExamination>$DEFINED$</PatientData_PhysicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE=" + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<PatientData_PhysicalReport_State>SKIPPED</PatientData_PhysicalReport_State>"+
//				"<PatientData_PhysicalReport>$SKIPPED$</PatientData_PhysicalReport>"+
//				"<PatientData_PhysicalExamination_State>SKIPPED</PatientData_PhysicalExamination_State>"+
//				"<PatientData_PhysicalExamination>$SKIPPED$</PatientData_PhysicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP=" + parseConclusion(conclusion));
//
//		name = "Collect_Data";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<PatientData_Height_State>UNDEFINED</PatientData_Height_State>"+
//				"<PatientData_Height>$UNDEFINED$</PatientData_Height>"+
//				"<PatientData_Weight_State>UNDEFINED</PatientData_Weight_State>"+
//				"<PatientData_Weight>$UNDEFINED$</PatientData_Weight>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE=" + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<PatientData_Height_State>DEFINED</PatientData_Height_State>"+
//				"<PatientData_Height>$UNDEFINED$</PatientData_Height>"+
//				"<PatientData_Weight_State>DEFINED</PatientData_Weight_State>"+
//				"<PatientData_Weight>$UNDEFINED$</PatientData_Weight>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE=" + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<PatientData_Height_State>SKIPPED</PatientData_Height_State>"+
//				"<PatientData_Height>$SKIPPED$</PatientData_Height>"+
//				"<PatientData_Weight_State>SKIPPED</PatientData_Weight_State>"+
//				"<PatientData_Weight>$SKIPPED$</PatientData_Weight>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP=" + parseConclusion(conclusion));
//
//		name = "Doctor_Appointment";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>UNDEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$UNDEFINED$</MedicalReport_Report>"+
//				"<Prescription_Recipe_State>UNDEFINED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$UNDEFINED$</Prescription_Recipe>"+
//				"<MedicalReport_Closed_State>UNDEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$UNDEFINED$</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>UNDEFINED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$UNDEFINED$</PatientData_MedicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE=" + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"<Prescription_Recipe_State>DEFINED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$DEFINED$</Prescription_Recipe>"+
//				"<MedicalReport_Closed_State>DEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>true</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>DEFINED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$DEFINED$</PatientData_MedicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE=" + parseConclusion(conclusion));
//
//		// DEF False
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"<Prescription_Recipe_State>DEFINED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$DEFINED$</Prescription_Recipe>"+
//				"<MedicalReport_Closed_State>DEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>false</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>DEFINED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$DEFINED$</PatientData_MedicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("DEF FALSE=" + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>SKIPPED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$SKIPPED$</MedicalReport_Report>"+
//				"<Prescription_Recipe_State>SKIPPED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$SKIPPED$</Prescription_Recipe>"+
//				"<MedicalReport_Closed_State>SKIPPED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$SKIPPED$</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>SKIPPED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$SKIPPED$</PatientData_MedicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP=" + parseConclusion(conclusion));
//
//		name = "Check-out_Patient";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<Episode_Closed_State>UNDEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>$UNDEFINED$</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<Episode_Closed_State>DEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>true</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// DEF False
//		cs = "<cornerstone>"+
//				"<Episode_Closed_State>DEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>false</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("DEF FALSE=" + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<Episode_Closed_State>SKIPPED</Episode_Closed_State>"+
//				"<Episode_Closed>$SKIPPED$</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//
//		///goals
//		name = "GOAL_Collect_Data";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<PatientData_Height_State>UNDEFINED</PatientData_Height_State>"+
//				"<PatientData_Height>$UNDEFINED$</PatientData_Height>"+
//				"<PatientData_Weight_State>UNDEFINED</PatientData_Weight_State>"+
//				"<PatientData_Weight>$UNDEFINED$</PatientData_Weight>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<PatientData_Height_State>DEFINED</PatientData_Height_State>"+
//				"<PatientData_Height>$DEFINED$</PatientData_Height>"+
//				"<PatientData_Weight_State>DEFINED</PatientData_Weight_State>"+
//				"<PatientData_Weight>$DEFINED$</PatientData_Weight>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<PatientData_Height_State>SKIPPED</PatientData_Height_State>"+
//				"<PatientData_Height>$SKIPPED$</PatientData_Height>"+
//				"<PatientData_Weight_State>SKIPPED</PatientData_Weight_State>"+
//				"<PatientData_Weight>$SKIPPED$</PatientData_Weight>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//
//		//////////
//		name = "GOAL_Diagnose_Patient";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>UNDEFINED</Patient_Name_State>"+
//				"<Patient_Name>$UNDEFINED$</Patient_Name>"+
//				"<MedicalReport_Report_State>UNDEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$UNDEFINED$</MedicalReport_Report>"+
//				"<Patient_PhoneNumber_State>UNDEFINED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$UNDEFINED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>UNDEFINED</Patient_Address_State>"+
//				"<Patient_Address>$UNDEFINED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>UNDEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$UNDEFINED$</MedicalReport_Closed>"+
//				"<Patient_Gender_State>UNDEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$UNDEFINED$</Patient_Gender>"+
//				"<Episode_Closed_State>UNDEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>$UNDEFINED$</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>DEFINED</Patient_Name_State>"+
//				"<Patient_Name>$DEFINED$</Patient_Name>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"<Patient_PhoneNumber_State>DEFINED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$DEFINED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>DEFINED</Patient_Address_State>"+
//				"<Patient_Address>$DEFINED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>DEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>true</MedicalReport_Closed>"+
//				"<Patient_Gender_State>DEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$DEFINED$</Patient_Gender>"+
//				"<Episode_Closed_State>DEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>true</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// DEF FALSE
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>DEFINED</Patient_Name_State>"+
//				"<Patient_Name>$DEFINED$</Patient_Name>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"<Patient_PhoneNumber_State>DEFINED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$DEFINED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>DEFINED</Patient_Address_State>"+
//				"<Patient_Address>$DEFINED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>DEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>false</MedicalReport_Closed>"+
//				"<Patient_Gender_State>DEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$DEFINED$</Patient_Gender>"+
//				"<Episode_Closed_State>DEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>false</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE=" + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>SKIPPED</Patient_Name_State>"+
//				"<Patient_Name>$SKIPPED$</Patient_Name>"+
//				"<MedicalReport_Report_State>SKIPPED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$SKIPPED$</MedicalReport_Report>"+
//				"<Patient_PhoneNumber_State>SKIPPED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$SKIPPED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>SKIPPED</Patient_Address_State>"+
//				"<Patient_Address>$SKIPPED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>SKIPPED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$SKIPPED$</MedicalReport_Closed>"+
//				"<Patient_Gender_State>SKIPPED</Patient_Gender_State>"+
//				"<Patient_Gender>$SKIPPED$</Patient_Gender>"+
//				"<Episode_Closed_State>SKIPPED</Episode_Closed_State>"+
//				"<Episode_Closed>$SKIPPED$</Episode_Closed>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//
//		name = "GOAL_Observe_Patient";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>UNDEFINED</Patient_Name_State>"+
//				"<Patient_Name>$UNDEFINED$</Patient_Name>"+
//				"<Episode_Date_State>UNDEFINED</Episode_Date_State>"+
//				"<Episode_Date>$UNDEFINED$</Episode_Date>"+
//				"<MedicalReport_Report_State>UNDEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$UNDEFINED$</MedicalReport_Report>"+
//				"<Episode_Closed_State>UNDEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>$UNDEFINED$</Episode_Closed>"+
//				"<Patient_PhoneNumber_State>UNDEFINED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$UNDEFINED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>UNDEFINED</Patient_Address_State>"+
//				"<Patient_Address>$UNDEFINED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>UNDEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$UNDEFINED$</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>UNDEFINED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$UNDEFINED$</PatientData_MedicalExamination>"+
//				"<Patient_Gender_State>UNDEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$UNDEFINED$</Patient_Gender>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>DEFINED</Patient_Name_State>"+
//				"<Patient_Name>$DEFINED$</Patient_Name>"+
//				"<Episode_Date_State>DEFINED</Episode_Date_State>"+
//				"<Episode_Date>$DEFINED$</Episode_Date>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"<Episode_Closed_State>DEFINED</Episode_Closed_State>"+
//				"<Episode_Closed>$DEFINED$</Episode_Closed>"+
//				"<Patient_PhoneNumber_State>DEFINED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$DEFINED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>DEFINED</Patient_Address_State>"+
//				"<Patient_Address>$DEFINED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>DEFINED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$DEFINED$</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>DEFINED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$DEFINED$</PatientData_MedicalExamination>"+
//				"<Patient_Gender_State>DEFINED</Patient_Gender_State>"+
//				"<Patient_Gender>$DEFINED$</Patient_Gender>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<Patient_Name_State>SKIPPED</Patient_Name_State>"+
//				"<Patient_Name>$SKIPPED$</Patient_Name>"+
//				"<Episode_Date_State>SKIPPED</Episode_Date_State>"+
//				"<Episode_Date>$SKIPPED$</Episode_Date>"+
//				"<MedicalReport_Report_State>SKIPPED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$SKIPPED$</MedicalReport_Report>"+
//				"<Episode_Closed_State>SKIPPED</Episode_Closed_State>"+
//				"<Episode_Closed>$SKIPPED$</Episode_Closed>"+
//				"<Patient_PhoneNumber_State>SKIPPED</Patient_PhoneNumber_State>"+
//				"<Patient_PhoneNumber>$SKIPPED$</Patient_PhoneNumber>"+
//				"<Patient_Address_State>SKIPPED</Patient_Address_State>"+
//				"<Patient_Address>$SKIPPED$</Patient_Address>"+
//				"<MedicalReport_Closed_State>SKIPPED</MedicalReport_Closed_State>"+
//				"<MedicalReport_Closed>$SKIPPED$</MedicalReport_Closed>"+
//				"<PatientData_MedicalExamination_State>SKIPPED</PatientData_MedicalExamination_State>"+
//				"<PatientData_MedicalExamination>$SKIPPED$</PatientData_MedicalExamination>"+
//				"<Patient_Gender_State>SKIPPED</Patient_Gender_State>"+
//				"<Patient_Gender>$SKIPPED$</Patient_Gender>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//
//		name = "GOAL_Write_Medical_Report";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>UNDEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$UNDEFINED$</MedicalReport_Report>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>DEFINED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$DEFINED$</MedicalReport_Report>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<MedicalReport_Report_State>SKIPPED</MedicalReport_Report_State>"+
//				"<MedicalReport_Report>$SKIPPED$</MedicalReport_Report>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//
//		name = "GOAL_Physical_Examination";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<PatientData_PhysicalReport_State>UNDEFINED</PatientData_PhysicalReport_State>"+
//				"<PatientData_PhysicalReport>$UNDEFINED$</PatientData_PhysicalReport>"+
//				"<PatientData_PhysicalExamination_State>UNDEFINED</PatientData_PhysicalExamination_State>"+
//				"<PatientData_PhysicalExamination>$UNDEFINED$</PatientData_PhysicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<PatientData_PhysicalReport_State>DEFINED</PatientData_PhysicalReport_State>"+
//				"<PatientData_PhysicalReport>$DEFINED$</PatientData_PhysicalReport>"+
//				"<PatientData_PhysicalExamination_State>DEFINED</PatientData_PhysicalExamination_State>"+
//				"<PatientData_PhysicalExamination>$DEFINED$</PatientData_PhysicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<PatientData_PhysicalReport_State>SKIPPED</PatientData_PhysicalReport_State>"+
//				"<PatientData_PhysicalReport>$SKIPPED$</PatientData_PhysicalReport>"+
//				"<PatientData_PhysicalExamination_State>SKIPPED</PatientData_PhysicalExamination_State>"+
//				"<PatientData_PhysicalExamination>$SKIPPED$</PatientData_PhysicalExamination>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//
//		name = "GOAL_Prescribe";
//		log.info(name);
//		// UND
//		cs = "<cornerstone>"+
//				"<Prescription_Recipe_State>UNDEFINED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$UNDEFINED$</Prescription_Recipe>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("FALSE = " + parseConclusion(conclusion));
//
//		// DEF
//		cs = "<cornerstone>"+
//				"<Prescription_Recipe_State>DEFINED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$DEFINED$</Prescription_Recipe>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("TRUE = " + parseConclusion(conclusion));
//
//		// SKIP
//		cs = "<cornerstone>"+
//				"<Prescription_Recipe_State>SKIPPED</Prescription_Recipe_State>"+
//				"<Prescription_Recipe>$SKIPPED$</Prescription_Recipe>"+
//				"</cornerstone>";
//		eData = JDOMUtil.stringToElement(cs);
//
//		try {
//			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
//		} catch (IOException e) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
//		}
//		log.info("SKIP = " + parseConclusion(conclusion));
//	}

}
