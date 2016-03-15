package org.blended.activity.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Add sequence constraint")
public class CommandSeq {
	@Parameter(description = "Name of the activity", names = "-a", validateWith = ValidateName.class, required = true)
	public String activity;

	@Parameter(description = "Type of operation", names = "-t", validateWith = ValidateSeqOperationType.class, required = true)
	public String operation;

	@Parameter(description = "Dependence path", names = "-p", required = true)
	public String path;
}
