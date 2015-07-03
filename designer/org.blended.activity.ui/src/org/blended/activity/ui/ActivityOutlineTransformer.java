package org.blended.activity.ui;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;

public class ActivityOutlineTransformer extends DefaultOutlineTreeProvider {

	@Override
	protected void createNode(IOutlineNode parentNode, EObject modelElement) {
		if (modelElement instanceof ActivityModel) {
			createEObjectNode(parentNode, modelElement);
		}
		else if (modelElement instanceof Activity) {
			createEObjectNode(parentNode, modelElement); 
		}
	}

}
