package org.blended.goal.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Splitting goals")
class CommandSplit {	
	@Parameter(description = "Name of the new splitted goal", names = "-n", validateWith = ValidateName.class, required = true)
	public String name;	
	
	@Parameter(description = "Type of the new goal (sibling or child)", names = "-t", validateWith = ValidateSplitType.class, required = true)
	public String type;
	
	@Parameter(description = "Goal to be spplited", names = "-g", validateWith = ValidateName.class, required = true)
	public String goal;
	
	@Parameter(description = "The list of success conditions used for the new spplited goal", names = "-s", required = true)
	public String successConditions;
}



