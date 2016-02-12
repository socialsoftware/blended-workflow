package org.blended.data.generator

import com.google.inject.Guice
import org.blended.common.common.CommonFactory
import org.blended.common.common.Specification
import org.blended.goal.GoalRuntimeModule
import org.blended.goal.goal.GoalFactory
import org.blended.goal.goal.GoalModel
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.resource.SaveOptions

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.xtext.EcoreUtil2.*
import org.blended.common.repository.CommonInterface
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.blended.goal.repository.ReadGoalModelService

class DataGeneratorGoalModel {
	private static Logger logger = LoggerFactory.getLogger("DataGeneratorGoalModel")
	
	CommonInterface ci = CommonInterface.instance
	ReadGoalModelService service = ReadGoalModelService.instance

	Resource resource
	IFileSystemAccess fsa
	CommonFactory factory
	GoalFactory goalFactory
	GoalModel model

	new(Resource resource, IFileSystemAccess fsa) {
		this.resource = resource
		this.fsa = fsa
		this.factory = CommonFactory.eINSTANCE
		this.goalFactory = GoalFactory.eINSTANCE
		this.model = goalFactory.createGoalModel
	}

	def doGenerate() {
		logger.debug("doGenerate");
		
		var specId = resource.normalizedURI.lastSegment.split("\\.").get(0)
		
		ci.cleanGoalModel(specId)
		
		ci.generateGoalModel(specId)
		
		model.specification = resource.allContents.toIterable.filter(typeof(Specification)).get(0).copy

		service.read(specId, model)
		
		// TO SERIALIZE THE GOAL MODEL ACCORDING TO THE GOAL FORMATTER
		val injector = Guice.createInjector(new GoalRuntimeModule)
		var rs = injector.getInstance(ResourceSet)
		var r = rs.createResource(URI.createURI(resource.normalizedURI.toString.replace(".dm", ".gm")))
		r.contents.add(model)
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		r.save(builder.options.toOptionsMap)
	}
	
}