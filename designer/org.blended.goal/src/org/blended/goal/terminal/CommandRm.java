package org.blended.goal.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Record changes to the repository")
class CommandRm {
	@Parameter(names = "-t", validateWith = ValidateRmTypes.class, required = true)
	public String type;
	
	@Parameter(names = "-v")
	public String value;
}

