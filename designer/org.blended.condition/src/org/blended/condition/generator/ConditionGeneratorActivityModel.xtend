package org.blended.condition.generator

import com.google.inject.Guice
import java.util.ArrayList
import org.blended.activity.ActivityRuntimeModule
import org.blended.activity.activity.Activity
import org.blended.activity.activity.ActivityModel
import org.blended.common.utils.Queries
import org.blended.common.common.CommonFactory
import org.blended.common.common.AttributeAchieveCondition
import org.blended.common.common.AttributeDependenceCondition
import org.blended.common.common.AttributeInvariantCondition
import org.blended.common.common.EntityAchieveCondition
import org.blended.common.common.EntityDependenceCondition
import org.blended.common.common.EntityInvariantCondition
import org.blended.common.common.MandatoryAttributeAchieveCondition
import org.blended.common.common.NotMandatoryAttributeAchieveCondition
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.resource.SaveOptions

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.xtext.EcoreUtil2.*
import org.blended.activity.activity.ActivityFactory
import org.blended.common.common.Specification
import org.blended.common.repository.CommonInterface
import java.util.HashSet
import org.blended.common.repository.resttemplate.req.AddActivityReq
import org.blended.common.repository.resttemplate.dto.DefEntityConditionDTO
import org.blended.common.repository.resttemplate.dto.DefAttributeConditionDTO
import java.util.Set
import org.blended.common.repository.resttemplate.dto.DefConditionSetDTO
import java.util.stream.Collectors
import org.blended.common.repository.resttemplate.dto.ActivityDTO
import org.blended.common.repository.resttemplate.dto.MulConditionDTO
import java.util.List
import org.blended.common.repository.resttemplate.dto.RuleDTO

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class ConditionGeneratorActivityModel {
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
		model.specification = resource.allContents.toIterable.filter(typeof(Specification)).get(0).copy

		var i = 1 // number of the activity
		for (o : resource.allContents.toIterable.filter(typeof(EntityAchieveCondition)) +
			resource.allContents.toIterable.filter(typeof(AttributeAchieveCondition))) {
			var activity = actFactory.createActivity
			activity.name = 'a' + i
			activity.description = "Activity number " + i++
			model.activities.add(activity)

			var specId = resource.normalizedURI.lastSegment.split("\\.").get(0)

			var ci = CommonInterface.getInstance

			var Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>()
			var Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>()
			if (o instanceof EntityAchieveCondition) {
				defEnts.add(new DefEntityConditionDTO(specId, o.getName()))
			}
			if (o instanceof AttributeAchieveCondition) {							
				defAtts.add(new DefAttributeConditionDTO(specId, o.getConditions().stream().collect(Collectors.toSet())))
			}
			var AddActivityReq request = new AddActivityReq(activity.name, activity.description,
				new DefConditionSetDTO(defEnts, defAtts))
			var ActivityDTO activityDTO = ci.addActivity(specId, request)

			generatePostConditionSet(activity, o)
			
			var DefConditionSetDTO defConditionSet = ci.getActivityPreConditionSet(specId, activityDTO.name)
			
			for (DefEntityConditionDTO defEntity : defConditionSet.defEnts) {
					var apre = factory.createEntityAchieveCondition
					apre.name = defEntity.entityName
					activity.pre.add(apre)
			}
			
			for (DefAttributeConditionDTO defAttribute : defConditionSet.defAtts) {
				if (defAttribute.mandatory) {
					// TODO should be a MandatoryFactory
					var apre = factory.createNotMandatoryAttributeAchieveCondition
					for (String path : defAttribute.paths)
						apre.conditions.add(path)
					activity.pre.add(apre)
				} else {
					var apre = factory.createNotMandatoryAttributeAchieveCondition
					for (String path : defAttribute.paths)
						apre.conditions.add(path)
					activity.pre.add(apre)
				}
			}
			
			var List<MulConditionDTO> mulConditionDTOs = ci.getActivityMulConditions(specId, activityDTO.name)
			
			for (MulConditionDTO mulConditionDTO : mulConditionDTOs) {
				for (mul : resource.allContents.toIterable.filter(typeof(EntityInvariantCondition))) {
			    if (mul.name.equals(mulConditionDTO.rolePath)) {
			    	activity.post.add(mul.copy)
			    }
				}
			}
			
			var List <RuleDTO> ruleDTOs = ci.getActivityRuleConditions(specId, activityDTO.name)
			
			for (RuleDTO ruleDTO : ruleDTOs) {
				for (rule : resource.allContents.toIterable.filter(typeof(AttributeInvariantCondition))) {
					if (rule.name.equals(ruleDTO.name)) {
						activity.post.add(rule.copy)
					}
				}
			}
			
		
			
//			step2(activity, o)
//			step3(activity, o)
		}

//		for (o : resource.allContents.toIterable.filter(typeof(EntityInvariantCondition))) {
//			step4(o)
//		}
//		
//		for (o : resource.allContents.toIterable.filter(typeof(AttributeInvariantCondition))) {
//			step5(o)
//		}
		// TO SERIALIZE THE ACTIVITY MODEL ACCORDING TO THE ACTIVITY FORMATTER
		val injector = Guice.createInjector(new ActivityRuntimeModule)
		var rs = injector.getInstance(ResourceSet)
		var r = rs.createResource(URI.createURI(resource.normalizedURI.toString.replace(".cm", ".am")))
		r.contents.add(model)
		var builder = SaveOptions.newBuilder()
		builder.noValidation
		builder.format
		r.save(builder.options.toOptionsMap)
	}

	def generatePostConditionSet(Activity activity, EObject o) {
		if (o instanceof EntityAchieveCondition || o instanceof NotMandatoryAttributeAchieveCondition) {
			activity.post.add(o.copy)
		} else if (o instanceof MandatoryAttributeAchieveCondition) {
			val post = factory.createNotMandatoryAttributeAchieveCondition
			post.conditions.addAll(o.conditions) // o.conditions.forall[e | post.conditions.add(e)]
			activity.post.add(post)
		}
	}

	def step2(Activity activity, EObject o) {
		if (o instanceof EntityAchieveCondition) {
			for (r : resource.allContents.toIterable.filter(typeof(EntityDependenceCondition))) {
				if (r.entity1.equals(o.name)) { // there is a dependency
					var apre = factory.createEntityAchieveCondition
					apre.name = r.entity2
					activity.pre.add(apre)
				}
			}
		} else if (o instanceof NotMandatoryAttributeAchieveCondition) {
			for (r : resource.allContents.toIterable.filter(typeof(AttributeDependenceCondition))) {
				if (r.attributes1.toString.contains(o.conditions.toString)) { // there is a dependency
					var apre = factory.createNotMandatoryAttributeAchieveCondition
					apre.conditions.addAll(r.attributes2)
					activity.pre.add(apre)
				}
			}
		} else if (o instanceof MandatoryAttributeAchieveCondition) {
			for (r : resource.allContents.toIterable.filter(typeof(AttributeDependenceCondition))) {
				if (r.attributes1.toString.contains(o.conditions.toString)) { // there is a dependency
					var apre = factory.createNotMandatoryAttributeAchieveCondition
					apre.conditions.addAll(r.attributes2)
					activity.pre.add(apre)
				}
			}
		}
	}

	def step3(Activity activity, EObject o) {
		if (o instanceof NotMandatoryAttributeAchieveCondition || o instanceof MandatoryAttributeAchieveCondition) { // only for attributes of the model
			for (EObject p : activity.post) { // we get all the elements in POST
				if (p instanceof NotMandatoryAttributeAchieveCondition) { // only for attributes of the activity
					for (String element : p.conditions) { // for each of the attributes
						var entityName = Queries.getEntityNameFrom(element) // we get the name
						if ((!contains(activity.post, entityName)) && (!contains(activity.pre, entityName))) { // if we don't find it -> we put the entity in PRE
							var apre2 = factory.createEntityAchieveCondition
							apre2.name = entityName
							activity.pre.add(apre2)
						}
					}
				}
			}
		}
	}

	def step4(EntityInvariantCondition o) {
		// we need to know which one of the entities involved in the MUL was defined last in the ACTIVITY MODEL and put the MUL in the POST of that entity
		// call to the engine to know the reference there are pointing to
		var entityName1 = Queries.getEntityNameFrom(o.name)
		var entityName2 = Queries.getEntityNameTo(o.name)

		// get activities related to entity1 and entity2
		var act1 = getFirstActivityWithEntityPost(entityName1)
		var act2 = getFirstActivityWithEntityPost(entityName2)

		if (act1 == null)
			act2.post.add(o.copy)
		else if (act2 == null)
			act1.post.add(o.copy)
		else if(act1.name > act2.name) act1.post.add(o.copy) else act2.post.add(o.copy)
	}

	def step5(AttributeInvariantCondition o) {
		// we need to know which one of the attributes involved in the RUL was defined last in the ACTIVITY MODEL and put the RUL in the POST of that attribute
		// list of all the elements in the RUL
		var listElements = new ArrayList<String>()
		Queries.getDecomposedExpression(o.expression, listElements)

		var listElementsInvolved = new ArrayList<PairOfElements>()
		for (String element : listElements) {
			// call to the engine to know the entity/attribute each of them are pointing to
			var entityName = Queries.getEntityNameTo(element)
			var attributeName = Queries.getAttributeName(element)
			var data = new PairOfElements(entityName, attributeName)
			listElementsInvolved.add(data)
		}

		// getting involved activities
		var listActivitiesInvolved = new ArrayList<Activity>()
		for (PairOfElements pair : listElementsInvolved) {
			var act = getFirstActivityWithAttributePost(pair.entityName + "." + pair.attributeName)
			if (act != null) {
				listActivitiesInvolved.add(act)
			}
		}

		// getting the last one of the activities
		var actForRUL = listActivitiesInvolved.sortBy[Integer.valueOf(name.substring(1))].last
		actForRUL.post.add(o.copy)
	}

	def contains(EList<EObject> list, String name) {
		for (EObject o : list) {
			if (o instanceof EntityAchieveCondition) {
				if(o.name.equals(name)) return true
			} else if (o instanceof NotMandatoryAttributeAchieveCondition) {
				if(o.conditions.contains(name)) return true
			}
		}
		return false
	}

	def getFirstActivityWithEntityPost(String entityName) {
		for (Activity activity : model.activities) {
			for (EObject o : activity.post) {
				if (o instanceof EntityAchieveCondition) {
					if(o.name.equals(entityName)) return activity
				}
			}
		}
	}

	def getFirstActivityWithAttributePost(String attributeName) {
		for (Activity activity : model.activities) {
			for (EObject o : activity.post) {
				if (o instanceof NotMandatoryAttributeAchieveCondition) {
					if(o.conditions.contains(attributeName)) return activity
				}
			}
		}
	}

}

@Data
class PairOfElements {
	String entityName
	String attributeName
}
