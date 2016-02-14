package org.blended.data.generator

import com.google.inject.Guice
import org.blended.common.common.CommonFactory
import org.blended.common.common.Specification
import org.blended.condition.ConditionRuntimeModule
import org.blended.condition.condition.ConditionFactory
import org.blended.condition.condition.ConditionModel
import org.blended.condition.repository.ReadConditionModelService
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

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class DataGeneratorConditionModel {
	private static Logger logger = LoggerFactory.getLogger("DataGeneratorConditionModel")
	
	CommonInterface ci = CommonInterface.instance
	ReadConditionModelService service = ReadConditionModelService.instance
	
	Resource resource
	IFileSystemAccess fsa
	CommonFactory factory
	ConditionFactory conFactory
	ConditionModel model

	new(Resource resource, IFileSystemAccess fsa) {
		this.resource = resource
		this.fsa = fsa
		this.factory = CommonFactory.eINSTANCE
		this.conFactory = ConditionFactory.eINSTANCE
		this.model = conFactory.createConditionModel
	}

	def doGenerate() {
		logger.debug("doGenerate");

		var specId = resource.normalizedURI.lastSegment.split("\\.").get(0)
		
		ci.generateConditionModel(specId)
		
		model.specification = resource.allContents.toIterable.filter(typeof(Specification)).get(0).copy

		service.read(specId, model)
		
		// TO SERIALIZE THE DATA MODEL ACCORDING TO THE ACTIVITY FORMATTER
		val injector = Guice.createInjector(new ConditionRuntimeModule)
		var rs = injector.getInstance(ResourceSet)
		var r = rs.createResource(URI.createURI(resource.normalizedURI.toString.replace(".dm", ".cm")))
		r.contents.add(model)
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		r.save(builder.options.toOptionsMap)
	}
 
 }