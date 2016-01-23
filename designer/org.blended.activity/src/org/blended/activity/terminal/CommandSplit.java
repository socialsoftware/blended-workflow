package org.blended.activity.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Splitting activities")
class CommandSplit {	
	@Parameter(description = "Name of the new splitted activity", names = "-n", validateWith = ValidateName.class, required = true)
	public String name;	
	
	@Parameter(description = "Type of the new activity", names = "-t", validateWith = ValidateSplitType.class, required = true)
	public String type;
	
	@Parameter(description = "Activity to be spplited", names = "-g", validateWith = ValidateName.class, required = true)
	public String goal;
	
	@Parameter(description = "The list of success conditions used for the new spplited activity", names = "-s", required = true)
	public String successConditions;
}



