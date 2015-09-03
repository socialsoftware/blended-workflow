package org.blended.activity.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateName implements IParameterValidator {
	 public void validate(String name, String value) throws ParameterException {
		 //XTEXT ID: '^'?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
		 String pattern = "[_a-zA-Z]\\w*";
		 if (!value.matches(pattern))
			 throw new ParameterException("Command " + name + " should be a valid ID (found " + value +")");
	 }
}
