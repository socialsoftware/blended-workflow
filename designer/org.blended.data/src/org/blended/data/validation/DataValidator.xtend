/*
 * generated by Xtext
 */
package org.blended.data.validation

import org.blended.data.data.DataModel
import org.blended.data.data.Specification
import org.eclipse.xtext.validation.Check
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface
import pt.ist.socialsoftware.blendedworkflow.service.BWException
import org.blended.data.data.DataPackage

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class DataValidator extends AbstractDataValidator {
  	public static val INVALID_NAME = 'invalidName'
 	
  		@Check
	  	def void checkModel(DataModel model) {
	  	 /*	var instance = DesignInterface.getInstance
	  		//try {
	  				instance.loadDataModel(model)
	  				//instance.createSpecification("Hi")
	  		//} catch (BWException bwe) {
	  		//	return "hello this is an error :-)"
	  		//}
	  		
	  		//if (entity.uid == null) {
	  		//	entity.uid = entity.hashCode().toString
	  		//	System.out.println("UUID for entity " + entity.name + ": " + entity.uid)
	  		//}
	  		//else System.out.println("UUID for entity " + entity.name + "is already assigned with value: " + entity.uid)
	  		//System.out.println("UUID for entity " + entity.name + ": " + entity.hashCode)
	  		
	  		*/
  	}

	@Check
	def check(Specification specification) {
/*		var instance = DesignInterface.getInstance
		
		try {
			instance.createSpecification(specification.name)
			
		}
		catch (BWException ex) {
			if (ex.error.equals(BWException.BlendedWorkflowError.INVALID_SPECIFICATION_NAME)) {
				error('Specification with the same name already exists', DataPackage.Literals.SPECIFICATION__NAME, INVALID_NAME)
			}
		}*/
	}
}