package org.blended.activity.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Usage help")
class CommandHelp {
	@Parameter(names = "-c", validateWith = ValidateHelpCommands.class, required = true)
	public String command;
}



