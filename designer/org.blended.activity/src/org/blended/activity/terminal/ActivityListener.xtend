package org.blended.activity.terminal

import com.beust.jcommander.JCommander
import com.beust.jcommander.ParameterException
import java.io.IOException
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.console.IOConsole
import org.eclipse.ui.console.IOConsoleInputStream
import org.eclipse.xtext.resource.SaveOptions
import org.blended.activity.activity.ActivityModel
import org.blended.common.utils.ConsoleManagement

class ActivityListener implements Runnable {
	package String name
	package String specId
	package Resource resource
	package ActivityModel model
	package IOConsole myConsole
	package Boolean terminate
	package Boolean	running
	private static ActivityListener instance = null
	
    def static getInstance(String name, Resource resource) {
        if (instance == null) {
            instance = new ActivityListener(name, resource);
        }
        return instance;
    }

	new(String name, Resource resource) {
		this.specId = resource.URI.lastSegment.split("\\.").get(0)
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
				ConsoleManagement.write(name, false, ConsoleManagement.TypeOutput.Caret, "AM>> ")
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
		this.model = resource.allContents.toIterable.filter(typeof(ActivityModel)).get(0)
	}
	
	def doTask(String option) {
		var cm = new CommandMain()
		var jc = new JCommander(cm)
		//jc.caseSensitiveOptions = false
		
		var help = new CommandHelp()
		var ls = new CommandLs()
		var join = new CommandJoin()
		var split = new CommandSplit()
		jc.addCommand("help", help)	
		jc.addCommand("ls", ls)
		jc.addCommand("join", join)		
		jc.addCommand("split", split)
		
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
						ConsoleManagement.write(name, helpJoin().toString);
					}
					case "split": {
						ConsoleManagement.write(name, helpSplit().toString);
					}
				}
			}
			else if (jc.getParsedCommand().equals("ls")) {
				ManageLs.activities(model, name, ls);
			}
			else if (jc.getParsedCommand().equals("join")) {
				ManageJoin.execute(model, name, this.specId, join);
				updateResource();
			}
			else if (jc.getParsedCommand().equals("split")) {
				ManageSplit.execute(model, name, this.specId, split);
				updateResource();
			}
		}
		catch (ParameterException pex) {
			ConsoleManagement.write(name, "Parameter not valid: " + pex.message);
			ConsoleManagement.write(name, "Type -help to know the available commands");
			ConsoleManagement.write(name, "Type help -c COMMAND for specific help");
		}
		catch (ValueException vex) {
			ConsoleManagement.write(name, vex.message);
			ConsoleManagement.write(name, "Type -help to know the available commands");
			ConsoleManagement.write(name, "Type help -c COMMAND for specific help");
		}
		catch (Exception e) {
			ConsoleManagement.write(name, "Option not valid: " + e.message);
			ConsoleManagement.write(name, "Type -help to know the available commands");
			ConsoleManagement.write(name, "Type help -c COMMAND for specific help");
		}
	}
	
	static def listOfActions() '''
	Activity Model Commands:	
		help	shows the help for specific commands
		ls	lists elements of the model
		join	joins two activities
		split	splits an activity in two
		
		-help	prints this message
	'''
	
	static def helpHELP()'''
	help is a command used to show help to users
	
	Syntax:
	help -c COMMAND
	
	Options:
	-c COMMAND
	'''
	
	static def helpLS()'''
	ls is a command used to show elements from the model
	
	Syntax:
	ls
	'''
	
	static def helpJoin()'''
	join is a command used to join two activities
	
	Syntax:
	join -n NEW_ACTIVITY_NAME ACTIVITY_TO_JOIN_ONE ACTIVITY_TO_JOIN_TWO
	'''
	
	static def helpSplit()'''
	split is a command used to split an activity in two
	
	Syntax:
	split -n NEW_ACTIVITY_NAME -a SOUCE_ACTIVITY -p POST_ELEMENTS_SEPARATED_BY_SEMICOLON
	'''
	
	def updateResource() {
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		resource.save(builder.options.toOptionsMap)
	}
	
	
}
