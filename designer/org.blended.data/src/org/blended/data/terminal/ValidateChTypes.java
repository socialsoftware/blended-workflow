package org.blended.data.terminal;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateChTypes implements IParameterValidator {
	 public void validate(String name, String value) throws ParameterException {
		 if ((!value.equals("spe"))&&(!value.equals("ent"))&&(!value.equals("att"))&&(!value.equals("exp")))
			 throw new ParameterException("Parameter " + name + " should be spe, ent, att, or exp (found " + value +")");
	 }
}
