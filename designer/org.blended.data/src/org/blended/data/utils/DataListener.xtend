package org.blended.data.utils

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.console.IOConsole
import org.eclipse.ui.console.IOConsoleInputStream
import java.io.IOException
import org.eclipse.xtext.resource.SaveOptions

class DataListener implements Runnable {
	package String name
	package Resource resource
	package IOConsole myConsole
	package Boolean terminate
	package Boolean	running
	private static DataListener instance = null;

    def static getInstance(String name, Resource resource) {
        if (instance == null) {
            instance = new DataListener(name, resource);
        }
        return instance;
    }

	new(String name, Resource resource) {
		this.name = name
		this.resource = resource
		this.terminate = false
		this.running = false
		myConsole = ConsoleManagement.findConsole(name)
	}

	override void run() {
		this.running = true
		while (!terminate) {
			var IOConsoleInputStream in = myConsole.getInputStream()
			var byte[] input = newByteArrayOfSize(200)
			try {
				ConsoleManagement.write(name, false, ConsoleManagement.TypeOutput.Caret, "DM>> ")
				in.read(input)
			} catch (IOException e) {
				e.printStackTrace()
			}
			doTask(new String(input).trim())
		}
	}
	
	def terminate() {
		this.terminate = true
	}
	
	def isRunning() {
		this.running
	}
	
	def doTask(String option) {
		switch(option) {
			case "0": {
				ConsoleManagement.write(name, listOfActions().toString)
			}
			case "1": {
				ConsoleManagement.write(name, listOfActionsEntities().toString)
			}
			case "2": {
				ConsoleManagement.write(name, listOfActionsAssociations().toString)
			}
			case "3": {
				ConsoleManagement.write(name, listOfActionsConstraints().toString)
			}
			/*case "1": {
				ConsoleManagement.write(name, "List of Entity Achive Conditions:")
				for (r : resource.allContents.toIterable.filter(typeof(EntityAchieveCondition))){
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
					'DEF(' + r.name + ')')
				}
			}
			case "2": {
				ConsoleManagement.write(name, "List of Entity Invariant Conditions:")
				for (r : resource.allContents.toIterable.filter(typeof(EntityInvariantCondition))){
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
					'MUL(' + r.name + ', ' + r.cardinality + ')')
				}
			}
			case "3": {
				ConsoleManagement.write(name, "List of Entity Dependence Conditions:")
				for (r : resource.allContents.toIterable.filter(typeof(EntityDependenceCondition))){
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
					'DEP(DEF(' + r.entity1 + '), DEF(' + r.entity2 + '))')
				}
			}
			case "4": {
				ConsoleManagement.write(name, "List of Attribute Achive Conditions:")
				for (r : resource.allContents.toIterable.filter(typeof(AttributeAchieveCondition))){
					if (r instanceof MandatoryAttributeAchieveCondition) {
						ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
						'MAN(DEF(' + r.conditions.join(", ") +'))') }
					else {
						ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
						'DEF(' + r.conditions.join(", ") +')')
					}
				}
			}
			case "5": {
				ConsoleManagement.write(name, "List of Attribute Invariant Conditions:")
				for (r : resource.allContents.toIterable.filter(typeof(AttributeInvariantCondition))){
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
						'RUL('+ ConditionGeneratorGoalModel.getCompleteExpression(r.expression) + ')')
				}
			}
			case "6": {
				ConsoleManagement.write(name, "List of Attribute Dependence Conditions:")
				for (r : resource.allContents.toIterable.filter(typeof(AttributeDependenceCondition))){
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
					'DEP(DEF(' + r.attributes1.join(", ") + '), DEF(' + r.attribute2 + '))')
				}
			}
			case "7": {
				ConsoleManagement.write(name, "Testing manipulation of model:")
				
				//CHANGING THE MODEL
				var blendedFactory = ConditionFactory.eINSTANCE;
				var eac = blendedFactory.createEntityAchieveCondition();
				eac.name = "TESTING"
				var cm = resource.getContents().get(0) as ConditionModel
				cm.conditions.entityAchieveConditions.add(eac)		
						
				update()
			}
			case "": {
				
			}*/
			default: {
				ConsoleManagement.write(name, "Option no valid. Type 0 to see the options")
			}
		}
	}
	
	static def listOfActions() '''
	******************************************************
	*********************DATA MODEL***********************
	1- Entities
	2- Associations
	3- Constraints
	
	0- Show main menu again
	******************************************************
	'''
	
	static def listOfActionsEntities() '''
	******************************************************
	*********************DATA MODEL***********************
	---WORKING WITH ENTITIES---
	
	11- Show entities
	12- Add entity
	13- Delete entity
	14- Modify entity
	
	1- Show this menu again
	0- Show main menu again
	******************************************************
	'''
	
	static def listOfActionsAssociations() '''
	******************************************************
	*********************DATA MODEL***********************
	---WORKING WITH ASSOCIATIONS---
	
	21- Show associations
	22- Add association
	23- Delete association
	24- Modify association
	
	1- Show this menu again
	0- Show main menu again
	******************************************************
	'''
	
	static def listOfActionsConstraints() '''
	******************************************************
	*********************DATA MODEL***********************
	---WORKING WITH CONSTRAINTS---
	
	31- Show constraints
	32- Add constraint
	33- Delete constraint
	34- Modify constraint
	
	3- Show this menu again
	0- Show main menu again
	******************************************************
	'''
	
	def update() {
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		resource.save(builder.options.toOptionsMap)
	}
}
