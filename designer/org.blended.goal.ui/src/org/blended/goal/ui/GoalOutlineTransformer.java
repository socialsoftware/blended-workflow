package org.blended.goal.ui;

import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;

public class GoalOutlineTransformer extends DefaultOutlineTreeProvider {

	@Override
	protected void createNode(IOutlineNode parentNode, EObject modelElement) {
		if (modelElement instanceof GoalModel) {
			createEObjectNode(parentNode, modelElement);
		}
		else if (modelElement instanceof Goal) {
			createEObjectNode(parentNode, modelElement); 
		}
	}

}
