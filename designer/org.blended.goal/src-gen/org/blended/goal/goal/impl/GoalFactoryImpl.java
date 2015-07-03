/**
 */
package org.blended.goal.goal.impl;

import org.blended.goal.goal.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GoalFactoryImpl extends EFactoryImpl implements GoalFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GoalFactory init()
  {
    try
    {
      GoalFactory theGoalFactory = (GoalFactory)EPackage.Registry.INSTANCE.getEFactory(GoalPackage.eNS_URI);
      if (theGoalFactory != null)
      {
        return theGoalFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new GoalFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GoalFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case GoalPackage.GOAL_MODEL: return createGoalModel();
      case GoalPackage.ENTITY_ACHIEVE_CONDITION: return createEntityAchieveCondition();
      case GoalPackage.ENTITY_ACHIEVE_CONDITION_EXIST: return createEntityAchieveConditionExist();
      case GoalPackage.ENTITY_INVARIANT_CONDITION: return createEntityInvariantCondition();
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION: return createEntityDependenceCondition();
      case GoalPackage.ATTRIBUTE_ACHIEVE_CONDITION: return createAttributeAchieveCondition();
      case GoalPackage.NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createNotMandatoryAttributeAchieveCondition();
      case GoalPackage.MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createMandatoryAttributeAchieveCondition();
      case GoalPackage.ATTRIBUTE_INVARIANT_CONDITION: return createAttributeInvariantCondition();
      case GoalPackage.ATTRIBUTE_DEPENDENCE_CONDITION: return createAttributeDependenceCondition();
      case GoalPackage.NOTHING: return createNothing();
      case GoalPackage.EXPRESSION: return createExpression();
      case GoalPackage.GOAL: return createGoal();
      case GoalPackage.OR: return createOr();
      case GoalPackage.AND: return createAnd();
      case GoalPackage.NOT: return createNot();
      case GoalPackage.ATTRIBUTE_DEFINITION: return createAttributeDefinition();
      case GoalPackage.ATTRIBUTE_VALUE: return createAttributeValue();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GoalModel createGoalModel()
  {
    GoalModelImpl goalModel = new GoalModelImpl();
    return goalModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityAchieveCondition createEntityAchieveCondition()
  {
    EntityAchieveConditionImpl entityAchieveCondition = new EntityAchieveConditionImpl();
    return entityAchieveCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityAchieveConditionExist createEntityAchieveConditionExist()
  {
    EntityAchieveConditionExistImpl entityAchieveConditionExist = new EntityAchieveConditionExistImpl();
    return entityAchieveConditionExist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityInvariantCondition createEntityInvariantCondition()
  {
    EntityInvariantConditionImpl entityInvariantCondition = new EntityInvariantConditionImpl();
    return entityInvariantCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDependenceCondition createEntityDependenceCondition()
  {
    EntityDependenceConditionImpl entityDependenceCondition = new EntityDependenceConditionImpl();
    return entityDependenceCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AttributeAchieveCondition createAttributeAchieveCondition()
  {
    AttributeAchieveConditionImpl attributeAchieveCondition = new AttributeAchieveConditionImpl();
    return attributeAchieveCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotMandatoryAttributeAchieveCondition createNotMandatoryAttributeAchieveCondition()
  {
    NotMandatoryAttributeAchieveConditionImpl notMandatoryAttributeAchieveCondition = new NotMandatoryAttributeAchieveConditionImpl();
    return notMandatoryAttributeAchieveCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MandatoryAttributeAchieveCondition createMandatoryAttributeAchieveCondition()
  {
    MandatoryAttributeAchieveConditionImpl mandatoryAttributeAchieveCondition = new MandatoryAttributeAchieveConditionImpl();
    return mandatoryAttributeAchieveCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AttributeInvariantCondition createAttributeInvariantCondition()
  {
    AttributeInvariantConditionImpl attributeInvariantCondition = new AttributeInvariantConditionImpl();
    return attributeInvariantCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AttributeDependenceCondition createAttributeDependenceCondition()
  {
    AttributeDependenceConditionImpl attributeDependenceCondition = new AttributeDependenceConditionImpl();
    return attributeDependenceCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Nothing createNothing()
  {
    NothingImpl nothing = new NothingImpl();
    return nothing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Goal createGoal()
  {
    GoalImpl goal = new GoalImpl();
    return goal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Or createOr()
  {
    OrImpl or = new OrImpl();
    return or;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public And createAnd()
  {
    AndImpl and = new AndImpl();
    return and;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Not createNot()
  {
    NotImpl not = new NotImpl();
    return not;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AttributeDefinition createAttributeDefinition()
  {
    AttributeDefinitionImpl attributeDefinition = new AttributeDefinitionImpl();
    return attributeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AttributeValue createAttributeValue()
  {
    AttributeValueImpl attributeValue = new AttributeValueImpl();
    return attributeValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GoalPackage getGoalPackage()
  {
    return (GoalPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static GoalPackage getPackage()
  {
    return GoalPackage.eINSTANCE;
  }

} //GoalFactoryImpl
