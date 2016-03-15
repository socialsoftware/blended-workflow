package org.blended.activity.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateHelpCommands implements IParameterValidator {
	@Override
	public void validate(String name, String value) throws ParameterException {
		if (!value.equals("help") && !value.equals("ls") && !value.equals("join") && !value.equals("split")
				&& !value.equals("seq"))
			throw new ParameterException(
					"Command " + name + " should be help, ls, join, split, seq (found " + value + ")");
	}
}
