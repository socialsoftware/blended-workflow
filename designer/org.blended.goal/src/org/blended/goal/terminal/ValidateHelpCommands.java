package org.blended.goal.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateHelpCommands implements IParameterValidator {
	 public void validate(String name, String value) throws ParameterException {
		 if ((!value.equals("help"))&&(!value.equals("ls"))&&(!value.equals("join"))&&(!value.equals("split")))
			 throw new ParameterException("Command " + name + " should be help, ls, join or split (found " + value +")");
	 }
}
