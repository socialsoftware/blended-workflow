package org.blended.data.terminal;

import org.blended.common.common.Entity;
import org.blended.common.utils.ConsoleManagement;
import org.blended.data.data.DataModel;

public class ManageRm {
	public static void entity(DataModel model, String name, CommandRm cmd) throws ValueException {
		if (cmd.value != null) {
			Entity ent = model.getEntities().stream()
				.filter(e -> e.getName().equals(cmd.value))
				.findFirst().get();
				//.forEach(e -> model.getEntities().remove(e));
			if (ent != null) {
				if (model.getEntities().remove(ent))
					ConsoleManagement.write(name, "Operation performed");
				else ConsoleManagement.write(name, "Operation NOT performed");	
			} 
			else ConsoleManagement.write(name, "Operation NOT performed. Entity not found");	
		} else throw new ValueException("Parameter not valid: The following option is required: -v working with specifications");
	}

}
