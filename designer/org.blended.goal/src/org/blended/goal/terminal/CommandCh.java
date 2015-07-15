package org.blended.goal.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Updating elements")
class CommandCh {	
	@Parameter(names = "t", validateWith = ValidateChTypes.class, required = true)
	public String type;
	
	@Parameter(names = "v")
	public String value;
}



