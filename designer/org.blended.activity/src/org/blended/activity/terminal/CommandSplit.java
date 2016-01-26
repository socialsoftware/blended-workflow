package org.blended.activity.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Splitting activities")
class CommandSplit {
	@Parameter(description = "Name of the new splitted activity", names = "-n", validateWith = ValidateName.class, required = true)
	public String name;

	@Parameter(description = "Activity to be splitted", names = "-a", validateWith = ValidateName.class, required = true)
	public String activity;

	@Parameter(description = "The list of post conditions used for the new spplited activity", names = "-p", required = true)
	public String postConditions;
}
