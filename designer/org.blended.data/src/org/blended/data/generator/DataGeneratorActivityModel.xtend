package org.blended.data.generator

import com.google.inject.Guice
import org.blended.activity.ActivityRuntimeModule
import org.blended.activity.activity.ActivityModel
import org.blended.common.common.CommonFactory
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.resource.SaveOptions

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.xtext.EcoreUtil2.*
import org.blended.activity.activity.ActivityFactory
import org.blended.common.common.Specification
import org.blended.common.repository.CommonInterface
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.blended.activity.repository.ReadActivityModelService

class DataGeneratorActivityModel {
	private static Logger logger = LoggerFactory.getLogger("DataGeneratorActivityModel")
	
	CommonInterface ci = CommonInterface.instance
	ReadActivityModelService service = ReadActivityModelService.instance

	Resource resource
	IFileSystemAccess fsa
	CommonFactory factory
	ActivityFactory actFactory
	ActivityModel model

	new(Resource resource, IFileSystemAccess fsa) {
		this.resource = resource
		this.fsa = fsa
		this.factory = CommonFactory.eINSTANCE
		this.actFactory = ActivityFactory.eINSTANCE
		this.model = actFactory.createActivityModel
	}

	def doGenerate() {
		logger.debug("doGenerate");

		var specId = resource.normalizedURI.lastSegment.split("\\.").get(0)
		
		ci.cleanActivityModel(specId)
		
		ci.generateActivityModel(specId)
		
		model.specification = resource.allContents.toIterable.filter(typeof(Specification)).get(0).copy

		service.read(specId, model)

		// TO SERIALIZE THE ACTIVITY MODEL ACCORDING TO THE ACTIVITY FORMATTER
		val injector = Guice.createInjector(new ActivityRuntimeModule)
		var rs = injector.getInstance(ResourceSet)
		var r = rs.createResource(URI.createURI(resource.normalizedURI.toString.replace(".dm", ".am")))
		r.contents.add(model)
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		r.save(builder.options.toOptionsMap)
	}

}
	
