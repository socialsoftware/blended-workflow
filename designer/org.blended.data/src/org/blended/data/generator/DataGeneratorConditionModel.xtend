package org.blended.data.generator

import com.google.inject.Guice
import org.blended.common.common.Association
import org.blended.common.common.Attribute
import org.blended.common.common.AttributeGroup
import org.blended.common.common.CommonFactory
import org.blended.common.common.Constraint
import org.blended.common.common.Entity
import org.blended.condition.ConditionRuntimeModule
import org.blended.condition.condition.ConditionFactory
import org.blended.condition.condition.ConditionModel
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.resource.SaveOptions

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.xtext.EcoreUtil2.*

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class DataGeneratorConditionModel {
	Resource resource
	IFileSystemAccess fsa
	CommonFactory factory
	ConditionFactory conFactory
	ConditionModel model
	
	new (Resource resource, IFileSystemAccess fsa) {
		this.resource = resource
		this.fsa = fsa
		this.factory = CommonFactory.eINSTANCE
		this.conFactory = ConditionFactory.eINSTANCE
		this.model = conFactory.createConditionModel
	}
	
	def doGenerate() {
		for (r : resource.allContents.toIterable.filter(typeof(Entity))) {
			entityAchieveCondition(r)
		}
		
		for (r : resource.allContents.toIterable.filter(typeof(Association))) {
			entityInvariantCondition(r)
		}
		
		for (r : resource.allContents.toIterable.filter(typeof(Entity))) {
			entityDependenceCondition(r)
		}	
		
		for (r : resource.allContents.toIterable.filter(typeof(Entity))) {
			attributeAchieveCondition(r)
		}
		
		for (r : resource.allContents.toIterable.filter(typeof(Constraint))) {
			attributeInvariantCondition(r)		
		}
		
		for (r : resource.allContents.toIterable.filter(typeof(Entity))) {
			attributeDependenceCondition(r)
		}		
		
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
	
	def entityAchieveCondition(Entity e) {
		if (!e.exists) {
			var eac = factory.createEntityAchieveCondition
			eac.name = e.name
			model.entityAchieveConditions.add(eac)
		}
		else {
			var eace = factory.createEntityAchieveConditionExist
			eace.name = e.name
			model.entityAchieveConditions.add(eace)
		}
	}
	
	def entityInvariantCondition(Association a) {
		if (!a.entity1.exists) {
			var inv = factory.createEntityInvariantCondition
			inv.name = a.entity1.name + "." + a.name2
			inv.cardinality = a.cardinality2
			model.entityInvariantConditions.add(inv)	
		}
		if (!a.entity2.exists) {
			var inv = factory.createEntityInvariantCondition
			inv.name = a.entity2.name + "." + a.name1
			inv.cardinality = a.cardinality1
			model.entityInvariantConditions.add(inv)	
		}
	}
	
	def entityDependenceCondition(Entity e) {
		if ((!e.exists) && (e.dependsOn.size > 0)) {
			for (d : e.dependsOn) {
				var dep = factory.createEntityDependenceCondition
				dep.entity1 = e.name
				dep.entity2 = d
				model.entityDependenceConditions.add(dep)
			}		
		}
	}
	
	def attributeAchieveCondition(Entity e) {
		if (!e.exists) {
			e.attributes.forEach[a | 
				if (a instanceof Attribute) {
					if (a.mandatory) {
						var attM = factory.createMandatoryAttributeAchieveCondition
						attM.conditions.add(e.name + '.' + a.name)
						model.attributeAchieveConditions.add(attM)
					} 
					else {
						var attNM = factory.createNotMandatoryAttributeAchieveCondition
						attNM.conditions.add(e.name + '.' + a.name)
						model.attributeAchieveConditions.add(attNM)
					}			
				}
				else if (a instanceof AttributeGroup) {
					if (a.mandatory) {
						var attM = factory.createMandatoryAttributeAchieveCondition
						attM.conditions.addAll(a.attributes.map[e.name + '.' + it.name])
						model.attributeAchieveConditions.add(attM)
					}
					else {
						var attNM = factory.createNotMandatoryAttributeAchieveCondition
						attNM.conditions.addAll(a.attributes.map[e.name + '.' + it.name])
						model.attributeAchieveConditions.add(attNM)
					}
				}
			]
		}
	}
	
	def attributeInvariantCondition(Constraint c) {
		var inv = factory.createAttributeInvariantCondition
		inv.expression = c.constraint.copy()
		model.attributeInvariantConditions.add(inv)
	}
	
	def attributeDependenceCondition(Entity e) {
		if (!e.exists) {
			e.attributes.forEach[a | 
				if (a instanceof Attribute) {
					if (a.dependsOn.size > 0) {
						var dep = factory.createAttributeDependenceCondition
						dep.attributes1.add(e.name + '.' + a.name)
						dep.attributes2.addAll(a.dependsOn)
						model.attributeDependenceConditions.add(dep)
					} 		
				}
				else if (a instanceof AttributeGroup) {
					if (a.dependsOn.size > 0) {
						var dep = factory.createAttributeDependenceCondition
						dep.attributes1.addAll(a.attributes.map[e.name + '.' + name])
						dep.attributes2.addAll(a.dependsOn)
						model.attributeDependenceConditions.add(dep)
					}
				}
			]
		}
	}

}
