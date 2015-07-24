package org.blended.goal.terminal

import com.beust.jcommander.JCommander
import com.beust.jcommander.ParameterException
import java.io.IOException
import org.blended.common.utils.ConsoleManagement
import org.blended.goal.goal.GoalModel
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.console.IOConsole
import org.eclipse.ui.console.IOConsoleInputStream
import org.eclipse.xtext.resource.SaveOptions
import org.blended.common.utils.ValueException

class DataListener implements Runnable {
	package String name
	package Resource resource
	package GoalModel model
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
		setModel(resource)
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
	
	def setModel(Resource resource) {
		this.resource = resource
		this.model = resource.allContents.toIterable.filter(typeof(GoalModel)).get(0)
	}
	
	def doTask(String option) {
		var cm = new CommandMain()
		var jc = new JCommander(cm)		
		var help = new CommandHelp()
		var ls = new CommandLs()
		var join = new CommandJoin()
		jc.addCommand("help", help)	
		jc.addCommand("ls", ls)	
		jc.addCommand("join", join)
		
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
					case "join": {
						ConsoleManagement.write(name, helpJOIN().toString);
					}
					case "split": {
						ConsoleManagement.write(name, helpSPLIT().toString);
					}
				}
			}
			else if (jc.getParsedCommand().equals("ls")) {
				ManageLs.goals(model, name, ls);
			}
			else if (jc.getParsedCommand().equals("join")) {
				ManageJoin.goals(model, name, join);
				updateResource();
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
	}
	
	static def listOfActions() '''
	Data Model Commands:	
		help	shows the help for specific commands
		ls	lists elements of the model
		join	joins together two goals in the model
		split	splits two goals in the model
		
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
	ls
	'''
	
	static def helpJOIN()'''
	join is a command used to join together two goals in the model
	
	Syntax for the specification:
	join GOAL1 GOAL2
	'''
	
	static def helpSPLIT()'''
	splits is a command used to splits two goals in the model
	
	Syntax for the specification:
	splits XXXX
	'''
		
	def updateResource() {
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		resource.save(builder.options.toOptionsMap)
	}
	
	
}
