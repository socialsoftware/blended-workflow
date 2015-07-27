package org.blended.goal.terminal;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Splitting goals")
class CommandSplit {	
	@Parameter(names = "-n1", validateWith = ValidateName.class, required = true)
	public String name1;	
	
	@Parameter(names = "-n2", validateWith = ValidateName.class, required = true)
	public String name2;
	
	@Parameter(description = "The goals to be splited", required = true)
	public List<String> goals;
}



