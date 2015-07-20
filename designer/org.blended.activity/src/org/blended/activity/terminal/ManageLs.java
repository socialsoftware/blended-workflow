package org.blended.activity.terminal;

import org.blended.activity.activity.ActivityModel;
import org.blended.common.utils.ConsoleManagement;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class ManageLs {
	public static void activities(ActivityModel model, String name, CommandLs cmd) {
		ConsoleManagement.write(name, "Activities:");
		ICompositeNode node = NodeModelUtils.getNode(model.eResource().getContents().get(0));
		ConsoleManagement.write(name, ConsoleManagement.TypeOutput.OutputData, node.getText());
	}

}
