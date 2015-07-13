package org.blended.data.terminal;

import org.blended.data.data.DataModel;

public class ManageCh {
	public static void specification(DataModel model, String name, CommandCh cmd) throws ValueException  {
		if (cmd.value != null) {
			model.getSpecification().setName(cmd.value);
			ConsoleManagement.write(name, "New name for specification:" + cmd.value);
		} else throw new ValueException("Parameter not valid: The following option is required: -v working with specifications");
	}
}
