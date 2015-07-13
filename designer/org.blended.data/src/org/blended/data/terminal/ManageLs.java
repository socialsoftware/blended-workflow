package org.blended.data.terminal;

import java.util.stream.Collectors;

import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.blended.data.data.Attribute;
import org.blended.data.data.AttributeGroup;
import org.eclipse.emf.ecore.EObject;

public class ManageLs {
	public static void specification(DataModel model, String name, CommandLs cmd) {
		ConsoleManagement.write(name, "Name of the specification:");
		ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, model.getSpecification().getName());
	}
	
	public static void entities(DataModel model, String name, CommandLs cmd) throws ValueException  {
		ConsoleManagement.write(name, "List of entities:");
		for (Entity ent : model.getEntities()){
			String textEnt = ent.getName();
			if (ent.isExists()) textEnt += " exists";
			if (ent.getDependsOn() != null) textEnt += " dependsOn " + ent.getDependsOn().getName();
			ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, textEnt);
			for (EObject oatt : ent.getAttributes()){
				if (oatt instanceof Attribute) {
					Attribute att = (Attribute)oatt;
					String textAtt = att.getName() + " : " + att.getType();
					if (att.isMandatory()) textAtt += " mandatory";
					if (att.getDependsOn().size() > 0) textAtt += " dependsOn " + att.getDependsOn()
							.stream()
							.map(element -> ((Entity)element.eContainer()).getName() + "." + element.getName())					
							.collect(Collectors.joining(", "));							
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t" + textAtt);	
				}
				if (oatt instanceof AttributeGroup) {
					AttributeGroup attG = (AttributeGroup)oatt;
					String textAttG = "group";
					if (attG.getDependsOn().size() > 0) textAttG += " dependsOn " + attG.getDependsOn()
							.stream()
							.map(element -> ((Entity)element.eContainer()).getName() + "." + element.getName())					
							.collect(Collectors.joining(", "));
					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t" + textAttG);	
					for (Attribute attGatt : attG.getAttributes()) {
						Attribute att = (Attribute)attGatt;
						String textAtt = att.getName() + " : " + att.getType();
						if (att.isMandatory()) textAtt += " mandatory";
						if (att.getDependsOn().size() > 0) textAtt += " dependsOn " + att.getDependsOn()
								.stream()
								.map(element -> ((Entity)element.eContainer()).getName() + "." + element.getName())					
								.collect(Collectors.joining(", "));							
						ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t\t" + textAtt);	
					}
				}
			}
		}
	}
	
	public static void associations(DataModel model, String name, CommandLs cmd) throws ValueException {
		
	}
	
	public static void expressions(DataModel model, String name, CommandLs cmd) throws ValueException {
		
	}
}
