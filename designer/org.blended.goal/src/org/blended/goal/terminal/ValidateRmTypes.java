package org.blended.goal.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateRmTypes implements IParameterValidator {
	 public void validate(String name, String value) throws ParameterException {
		 if ((!value.equals("ent"))&&(!value.equals("ent"))&&(!value.equals("ent"))&&(!value.equals("ent")))
			 throw new ParameterException("Parameter " + name + " should be ent (found " + value +")");
	 }
}
