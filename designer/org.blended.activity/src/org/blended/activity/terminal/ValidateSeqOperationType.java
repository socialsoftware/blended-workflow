package org.blended.activity.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateSeqOperationType implements IParameterValidator {
	@Override
	public void validate(String name, String value) throws ParameterException {
		// XTEXT ID: '^'?('a'..'z'|'A'..'Z'|'_')
		// ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
		if (!value.equals("add") && !value.equals("rem"))
			throw new ParameterException("Parameter " + name + " should be 'add' or 'rem' (found " + value + ")");
	}

}
