/*
 * generated by Xtext 2.12.0
 */
package pt.ist.socialsoftware.blendedworkflow.designer.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import pt.ist.socialsoftware.blendedworkflow.designer.BlendedWorkflowRuntimeModule
import pt.ist.socialsoftware.blendedworkflow.designer.BlendedWorkflowStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class BlendedWorkflowIdeSetup extends BlendedWorkflowStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new BlendedWorkflowRuntimeModule, new BlendedWorkflowIdeModule))
	}
	
}
