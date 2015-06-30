/**
 */
package org.blended.blended.impl;

import org.blended.blended.*;

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
public class BlendedFactoryImpl extends EFactoryImpl implements BlendedFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BlendedFactory init()
  {
    try
    {
      BlendedFactory theBlendedFactory = (BlendedFactory)EPackage.Registry.INSTANCE.getEFactory(BlendedPackage.eNS_URI);
      if (theBlendedFactory != null)
      {
        return theBlendedFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new BlendedFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlendedFactoryImpl()
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
      case BlendedPackage.BLENDED_MODEL: return createBlendedModel();
      case BlendedPackage.ENTITY_ACHIEVE_CONDITION: return createEntityAchieveCondition();
      case BlendedPackage.ENTITY_ACHIEVE_CONDITION_EXIST: return createEntityAchieveConditionExist();
      case BlendedPackage.ENTITY_INVARIANT_CONDITION: return createEntityInvariantCondition();
      case BlendedPackage.ENTITY_DEPENDENCE_CONDITION: return createEntityDependenceCondition();
      case BlendedPackage.ATTRIBUTE_ACHIEVE_CONDITION: return createAttributeAchieveCondition();
      case BlendedPackage.NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createNotMandatoryAttributeAchieveCondition();
      case BlendedPackage.MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createMandatoryAttributeAchieveCondition();
      case BlendedPackage.ATTRIBUTE_INVARIANT_CONDITION: return createAttributeInvariantCondition();
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION: return createAttributeDependenceCondition();
      case BlendedPackage.NOTHING: return createNothing();
      case BlendedPackage.EXPRESSION: return createExpression();
      case BlendedPackage.CONDITION: return createCondition();
      case BlendedPackage.GOAL: return createGoal();
      case BlendedPackage.ACTIVITY: return createActivity();
      case BlendedPackage.OR: return createOr();
      case BlendedPackage.AND: return createAnd();
      case BlendedPackage.NOT: return createNot();
      case BlendedPackage.ATTRIBUTE_DEFINITION: return createAttributeDefinition();
      case BlendedPackage.ATTRIBUTE_VALUE: return createAttributeValue();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlendedModel createBlendedModel()
  {
    BlendedModelImpl blendedModel = new BlendedModelImpl();
    return blendedModel;
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
  public Condition createCondition()
  {
    ConditionImpl condition = new ConditionImpl();
    return condition;
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
  public Activity createActivity()
  {
    ActivityImpl activity = new ActivityImpl();
    return activity;
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
  public BlendedPackage getBlendedPackage()
  {
    return (BlendedPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static BlendedPackage getPackage()
  {
    return BlendedPackage.eINSTANCE;
  }

} //BlendedFactoryImpl
