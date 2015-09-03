package org.blended.activity.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateSplitType implements IParameterValidator {
	 public void validate(String name, String value) throws ParameterException {
		 String pattern = "sibling|child|s|c";
		 if (!value.matches(pattern))
			 throw new ParameterException("Command " + name + " should be s (sibling) or c (child) (found " + value +")");
	 }
}
