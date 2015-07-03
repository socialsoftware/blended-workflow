/**
 */
package org.blended.activity.activity.impl;

import org.blended.activity.activity.*;

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
public class ActivityFactoryImpl extends EFactoryImpl implements ActivityFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ActivityFactory init()
  {
    try
    {
      ActivityFactory theActivityFactory = (ActivityFactory)EPackage.Registry.INSTANCE.getEFactory(ActivityPackage.eNS_URI);
      if (theActivityFactory != null)
      {
        return theActivityFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ActivityFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActivityFactoryImpl()
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
      case ActivityPackage.ACTIVITY_MODEL: return createActivityModel();
      case ActivityPackage.ENTITY_ACHIEVE_CONDITION: return createEntityAchieveCondition();
      case ActivityPackage.ENTITY_ACHIEVE_CONDITION_EXIST: return createEntityAchieveConditionExist();
      case ActivityPackage.ENTITY_INVARIANT_CONDITION: return createEntityInvariantCondition();
      case ActivityPackage.ENTITY_DEPENDENCE_CONDITION: return createEntityDependenceCondition();
      case ActivityPackage.ATTRIBUTE_ACHIEVE_CONDITION: return createAttributeAchieveCondition();
      case ActivityPackage.NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createNotMandatoryAttributeAchieveCondition();
      case ActivityPackage.MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createMandatoryAttributeAchieveCondition();
      case ActivityPackage.ATTRIBUTE_INVARIANT_CONDITION: return createAttributeInvariantCondition();
      case ActivityPackage.ATTRIBUTE_DEPENDENCE_CONDITION: return createAttributeDependenceCondition();
      case ActivityPackage.NOTHING: return createNothing();
      case ActivityPackage.EXPRESSION: return createExpression();
      case ActivityPackage.ACTIVITY: return createActivity();
      case ActivityPackage.OR: return createOr();
      case ActivityPackage.AND: return createAnd();
      case ActivityPackage.NOT: return createNot();
      case ActivityPackage.ATTRIBUTE_DEFINITION: return createAttributeDefinition();
      case ActivityPackage.ATTRIBUTE_VALUE: return createAttributeValue();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActivityModel createActivityModel()
  {
    ActivityModelImpl activityModel = new ActivityModelImpl();
    return activityModel;
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
  public ActivityPackage getActivityPackage()
  {
    return (ActivityPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ActivityPackage getPackage()
  {
    return ActivityPackage.eINSTANCE;
  }

} //ActivityFactoryImpl
