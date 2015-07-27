package org.blended.data.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateJoinTypes implements IParameterValidator {
	 public void validate(String name, String value) throws ParameterException {
		 if (name.split(" ").length != 2)
			 throw new ParameterException("Parameter " + name + " should indicate the name of two goals (found " + value +")");
	 }
}
