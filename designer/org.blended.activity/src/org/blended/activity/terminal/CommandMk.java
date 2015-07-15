package org.blended.activity.terminal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Record changes to the repository")
class CommandMk {
	//@Parameter(description = "The list of files to commit")
	//public List<String> files;
 
	@Parameter(names = "-aver", description = "Amend")
	public Boolean amend = false;
 
	//@Parameter(names = "--author")
	//public String author;
}

