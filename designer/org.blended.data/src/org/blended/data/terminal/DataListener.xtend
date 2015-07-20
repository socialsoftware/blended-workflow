package org.blended.data.terminal

import com.beust.jcommander.JCommander
import com.beust.jcommander.ParameterException
import java.io.IOException
import org.blended.common.utils.ConsoleManagement
import org.blended.data.data.DataModel
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.console.IOConsole
import org.eclipse.ui.console.IOConsoleInputStream
import org.eclipse.xtext.resource.SaveOptions

class DataListener implements Runnable {
	package String name
	package Resource resource
	package DataModel model
	package IOConsole myConsole
	package Boolean terminate
	package Boolean	running
	private static DataListener instance = null
	
    def static getInstance(String name, Resource resource) {
        if (instance == null) {
            instance = new DataListener(name, resource);
        }
        return instance;
    }

	new(String name, Resource resource) {
		this.name = name
		this.resource = resource
		this.model = resource.allContents.toIterable.filter(typeof(DataModel)).get(0)
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
	
	def setModel(Resource resource) {
		this.resource = resource
		this.model = resource.allContents.toIterable.filter(typeof(DataModel)).get(0)
	}
	
	def doTask(String option) {
		var cm = new CommandMain()
		var jc = new JCommander(cm)
		//jc.caseSensitiveOptions = false
		
		var help = new CommandHelp()
		var ls = new CommandLs()
		var mk = new CommandMk()
		var rm = new CommandRm()
		var ch = new CommandCh()
		jc.addCommand("help", help)	
		jc.addCommand("ls", ls)
		jc.addCommand("mk", mk)		
		jc.addCommand("rm", rm)
		jc.addCommand("ch", ch)		
		
		try {
			jc.parse(option.split(" "))
		
			if (cm.help) {
				ConsoleManagement.write(name, listOfActions().toString);
			}
			else if (jc.getParsedCommand().equals("help")) {
				switch(help.command) {
					case "help": {
						ConsoleManagement.write(name, helpHELP().toString);
					}
					case "ls": {
						ConsoleManagement.write(name, helpLS().toString);
					}
					case "mk": {
						ConsoleManagement.write(name, helpMK().toString);
					}
					case "rm": {
						ConsoleManagement.write(name, helpRM().toString);
					}
					case "ch": {
						ConsoleManagement.write(name, helpCH().toString);
					}
				}
			}
			else if (jc.getParsedCommand().equals("ls")) {
				ManageLs.specification(model, name, ls);
			}
			else if (jc.getParsedCommand().equals("mk")) {
				
			}
			else if (jc.getParsedCommand().equals("rm")) {
				switch(rm.type) {
					case "ent": {
						ManageRm.entity(model, name, rm);
						updateResource()
					}
					case "att": {
					}
					case "exp": {
					}
				}
			}
			else if (jc.getParsedCommand().equals("ch")) {
				if (ch.type == null) ch.type = "DEFAULT"
				switch(ch.type) {
					case "spe": {
						ManageCh.specification(model, name, ch)
						updateResource()
					}
					default: {
						ConsoleManagement.write(name, helpLS().toString);
					}
				}
			}
		}
		catch (ParameterException pex) {
			ConsoleManagement.write(name, "Parameter not valid: " + pex.message);
			ConsoleManagement.write(name, "Type -help to know the available commands");
			ConsoleManagement.write(name, "Type help -c=COMMAND for specific help");
		}
		catch (ValueException vex) {
			ConsoleManagement.write(name, vex.message);
			ConsoleManagement.write(name, "Type -help to know the available commands");
			ConsoleManagement.write(name, "Type help -c=COMMAND for specific help");
		}
		catch (Exception e) {
			ConsoleManagement.write(name, "Option not valid: " + e.message);
			ConsoleManagement.write(name, "Type -help to know the available commands");
			ConsoleManagement.write(name, "Type help -c=COMMAND for specific help");
		}


/*/
			case "11": { //SHOW ENTITIES
				ConsoleManagement.write(name, "List of Entities:")
				for (r : resource.allContents.toIterable.filter(typeof(Entity))){
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
				
			}
			default: {
				ConsoleManagement.write(name, "Option no valid. Type 0 to see the options")
			}
		}*/
	}
	
	static def listOfActions() '''
	Data Model Commands:	
		help	shows the help for specific commands
		ls	lists elements of the model
		mk	creates elements in the model
		rm	removes elements from the model
		ch	changes elements from the model
		
		-help	prints this message
	'''
	
	static def helpHELP()'''
	help is a command used to show help to users
	
	Syntax:
	help -c=C
	
	Options:
	-c=C	Command that needs to be explained (C string)
	'''
	
	static def helpLS()'''
	ls is a command used to show elements from the model
	
	Syntax:
	ls -t=TYPE
	
	Options:
	-t=TYPE		Type to be shown, that can be: spe, ent, att, exp
				- spe --> for the specification
				- ent --> for entities
				- att --> for attributes
				- exp --> for expressions
	'''
	
	
	static def helpMK()'''
	mk is a command used to create elements in the model
	'''
	
	static def helpRM()'''
	rm is a command used to remove elements from the model
	'''
	
	static def helpCH()'''
	ch is a command used to change elements from the model
	
	Syntax for the specification:
	up -t=esp -v=NAME
	'''
		
	def updateResource() {
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		resource.save(builder.options.toOptionsMap)
	}
	
	
}
