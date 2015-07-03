package org.blended.goal.utils

import java.io.IOException
import org.blended.goal.goal.Goal
import org.blended.goal.goal.GoalFactory
import org.blended.goal.goal.GoalModel
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.console.IOConsole
import org.eclipse.ui.console.IOConsoleInputStream
import org.eclipse.xtext.resource.SaveOptions

class GoalListener implements Runnable {
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
		while (!terminate) {
			var IOConsoleInputStream in = myConsole.getInputStream()
			var byte[] input = newByteArrayOfSize(200)
			try {
				ConsoleManagement.write(name, false, ConsoleManagement.TypeOutput.Caret, "GM>> ")
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
				ConsoleManagement.write(name, "List of all the Goals:")
				for (r : resource.allContents.toIterable.filter(typeof(Goal))){
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, 
					'g:' + r.name
					)
				}
			}
			case "2": {
				ConsoleManagement.write(name, "Testing manipulation of model:")
				
				//CHANGING THE MODEL
				var blendedFactory = GoalFactory.eINSTANCE;
				var goal = blendedFactory.createGoal();
				goal.name = "NEWGOAL"
				var gm = resource.getContents().get(0) as GoalModel
				gm.goals.remove(0)	
				gm.goals.remove(1)		
						
				update()
			}
			default: {
				ConsoleManagement.write(name, "Option no valid. Type 0 to see the options")
			}
		}
	}
	
	static def listOfActions() '''
	******************************************************
	*******************GOAL MODEL*************************
	1- List of all the Goals
	2- Testing manipulation of model
	
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
