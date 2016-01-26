package org.blended.goal.terminal;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Joining goals")
class CommandJoin {
	@Parameter(names = "-n", validateWith = ValidateName.class, required = true)
	public String name;

	@Parameter(description = "The two goals to be joined", required = true)
	public List<String> goals;
}
