package org.blended.data.terminal;

import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.blended.data.data.Attribute;
import org.eclipse.emf.ecore.EObject;

public class ManageHelp {
	public static void specification(DataModel model, String name, CommandLs cmd) {
		ConsoleManagement.write(name, "Name of the specification:");
		ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, model.getSpecification().getName());
	}
	
	public static void entities(DataModel model, String name, CommandLs cmd) throws ValueException  {
		ConsoleManagement.write(name, "List of entities:");
		for (Entity ent : model.getEntities()){
			ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, ent.getName());
			for (EObject oatt : ent.getAttributes()){
				if (oatt instanceof Attribute) {
					Attribute att = (Attribute)oatt;
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t" + att.getName() + " : " + att.getType());	
				}
			}
		}
	}
	
	public static void associations(DataModel model, String name, CommandLs cmd) throws ValueException {
		
	}
	
	public static void expressions(DataModel model, String name, CommandLs cmd) throws ValueException {
		
	}
}
