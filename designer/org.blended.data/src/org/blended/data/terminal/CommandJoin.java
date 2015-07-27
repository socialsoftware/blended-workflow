package org.blended.data.terminal;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Updating elements")
class CommandJoin {	
	@Parameter(description = "The list of goals to be joined", validateWith = ValidateJoinTypes.class, required = true)
	public List<String> goals;
}



