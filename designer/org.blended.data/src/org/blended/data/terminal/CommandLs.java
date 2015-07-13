package org.blended.data.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Listing elements")
class CommandLs {
	@Parameter(names = "-t", validateWith = ValidateLsTypes.class, required = true)
	public String type;
}



