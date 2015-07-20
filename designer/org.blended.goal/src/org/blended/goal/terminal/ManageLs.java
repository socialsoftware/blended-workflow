package org.blended.goal.terminal;

import org.blended.common.utils.ConsoleManagement;
import org.blended.goal.goal.GoalModel;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class ManageLs {
	public static void goals(GoalModel model, String name, CommandLs cmd) {
		ConsoleManagement.write(name, "Goals:");
		ICompositeNode node = NodeModelUtils.getNode(model.eResource().getContents().get(0));
		ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, node.getText());
	}
	
//	public static void entities(DataModel model, String name, CommandLs cmd) throws ValueException  {
//		ConsoleManagement.write(name, "List of entities:");
//		for (Entity ent : model.getEntities()){
//			String textEnt = ent.getName();
//			if (ent.isExists()) textEnt += " exists";
//			if (ent.getDependsOn() != null) textEnt += " dependsOn " + ent.getDependsOn().getName();
//			ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, textEnt);
//			for (EObject oatt : ent.getAttributes()){
//				if (oatt instanceof Attribute) {
//					Attribute att = (Attribute)oatt;
//					String textAtt = att.getName() + " : " + att.getType();
//					if (att.isMandatory()) textAtt += " mandatory";
//					if (att.getDependsOn().size() > 0) textAtt += " dependsOn " + att.getDependsOn()
//							.stream()
//							.map(element -> ((Entity)element.eContainer()).getName() + "." + element.getName())					
//							.collect(Collectors.joining(", "));							
//					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t" + textAtt);	
//				}
//				if (oatt instanceof AttributeGroup) {
//					AttributeGroup attG = (AttributeGroup)oatt;
//					String textAttG = "group";
//					if (attG.getDependsOn().size() > 0) textAttG += " dependsOn " + attG.getDependsOn()
//							.stream()
//							.map(element -> ((Entity)element.eContainer()).getName() + "." + element.getName())					
//							.collect(Collectors.joining(", "));
//					ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t" + textAttG);	
//					for (Attribute attGatt : attG.getAttributes()) {
//						Attribute att = (Attribute)attGatt;
//						String textAtt = att.getName() + " : " + att.getType();
//						if (att.isMandatory()) textAtt += " mandatory";
//						if (att.getDependsOn().size() > 0) textAtt += " dependsOn " + att.getDependsOn()
//								.stream()
//								.map(element -> ((Entity)element.eContainer()).getName() + "." + element.getName())					
//								.collect(Collectors.joining(", "));							
//						ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, "\t\t" + textAtt);	
//					}
//				}
//			}
//		}
//	}
	
}
