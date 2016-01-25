package org.blended.activity.terminal;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Joining activities")
public class CommandJoin {
	@Parameter(description = "Name of the new joined activity", names = "-n", validateWith = ValidateName.class, required = true)
	public String name;

	@Parameter(description = "The two activities to be joined", required = true)
	public List<String> activities;
}