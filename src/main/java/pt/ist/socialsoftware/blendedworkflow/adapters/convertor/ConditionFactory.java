package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class ConditionFactory {

	public static Condition createCondition(DataModel dataModel, String condInString) throws BlendedWorkflowException {
		return new ConditionParser(dataModel, condInString).parseCondition();
	}
	
	/**
	 * Legacy: Add missing Relation dependencies to conditions.
	 */
	/*
	public static String getRelationDependencies(DataModel dataModel, String originalCondition) {
		String resultingCondition = originalCondition;
		ArrayList<Entity> relationEntities = new ArrayList<Entity>();
		String parsedCondition = originalCondition;
		
		parsedCondition = parsedCondition.replaceAll(" and ", "%");
		parsedCondition = parsedCondition.replaceAll(" or ", "%");
		String[] relationEntitiesNames = parsedCondition.split("%", 0);
		
		for (int i=0; i<relationEntitiesNames.length;i++) {
			if (relationEntitiesNames[i].contains("existsEntity")) {
				int startArgs = "existsEntity(".length();
				int endArgs = relationEntitiesNames[i].length()-1;
				String entityName = relationEntitiesNames[i].substring(startArgs, endArgs);
				Entity entity = dataModel.getEntity(entityName);

				for (Relation relation : entity.getRelations()) {
					Entity entityOne = relation.getEntityOne();
					if (!entityOne.equals(entity) & relation.getIsOneKeyEntity() &
							!originalCondition.contains("existsEntity(" + entityOne.getName() + ")") && !relationEntities.contains(entityOne)) {
						relationEntities.add(entityOne);
					}
					Entity entityTwo = relation.getEntityTwo();
					if (!entityTwo.equals(entity) & relation.getIsTwoKeyEntity() &
							!originalCondition.contains("existsEntity(" + entityTwo.getName() + ")") && !relationEntities.contains(entityTwo)) {
						relationEntities.add(entityTwo);
					}
				}
			}
		}
		
		boolean notFinish = false;
		int size;
		while (!notFinish) {
			size = relationEntities.size();

			for (Entity entity : relationEntities) {
				for (Relation relation : entity.getRelations()) {
					Entity entityOne = relation.getEntityOne();
					if (!entityOne.equals(entity) & relation.getIsOneKeyEntity() &
							!originalCondition.contains("existsEntity(" + entityOne.getName() + ")") && !relationEntities.contains(entityOne)) {
						relationEntities.add(entityOne);
					}
					Entity entityTwo = relation.getEntityTwo();
					if (!entityTwo.equals(entity) & relation.getIsTwoKeyEntity() &
							!originalCondition.contains("existsEntity(" + entityTwo.getName() + ")") && !relationEntities.contains(entityTwo)) {
						relationEntities.add(entityTwo);
					}
				}
			}

			if (size == relationEntities.size()) {
				notFinish = true;
			}
		}
		
//		resultingCondition = "";
		for (Entity entity : relationEntities) {
			resultingCondition += " and existsEntity(" + entity.getName() + ")";
		}
//		System.out.println("-----------------------------");
//		System.out.println(originalCondition);
//		System.out.println(resultingCondition);
		return resultingCondition;
		
	}
	*/

}