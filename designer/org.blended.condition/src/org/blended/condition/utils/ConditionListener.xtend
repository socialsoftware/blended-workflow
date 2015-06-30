package org.blended.condition.utils

import java.io.IOException
import org.blended.blended.AttributeAchieveCondition
import org.blended.blended.AttributeDependenceCondition
import org.blended.blended.AttributeInvariantCondition
import org.blended.blended.BlendedFactory
import org.blended.blended.EntityAchieveCondition
import org.blended.blended.EntityDependenceCondition
import org.blended.blended.EntityInvariantCondition
import org.blended.blended.MandatoryAttributeAchieveCondition
import org.blended.condition.condition.ConditionModel
import org.blended.condition.generator.ConditionGeneratorGoalModel
import org.blended.utils.ConsoleManagement
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.console.IOConsole
import org.eclipse.ui.console.IOConsoleInputStream
import org.eclipse.xtext.resource.SaveOptions

class ConditionListener implements Runnable {
	package String name
	package Resource resource
	package IOConsole myConsole
	package Boolean terminate
	package Boolean	running

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
				ConsoleManagement.write(name, false, ConsoleManagement.TypeOutput.Caret, "CM>> ")
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
				var blendedFactory = BlendedFactory.eINSTANCE;
				var eac = blendedFactory.createEntityAchieveCondition();
				eac.name = "TESTING"
				var cm = resource.getContents().get(0) as ConditionModel
				cm.conditions.entityAchieveConditions.add(eac)		
						
				update()
			}
			case "": {
				
			}
			default: {
				ConsoleManagement.write(name, "Option no valid. Type 0 to see the options")
			}
		}
	}
	
	static def listOfActions() '''
	******************************************************
	*******************CONDITION MODEL********************
	1- List of Entity Achive Conditions
	2- List of Entity Invariant Conditions
	3- List of Entity Dependence Conditions
	4- List of Attribute Achive Conditions
	5- List of Attribute Invariant Conditions
	6- List of Attribute Dependence Conditions
	7- Testing manipulation of model
	
	0- Show the menu again
	******************************************************
	'''
	
	def update() {
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		resource.save(builder.options.toOptionsMap)
	}
}
