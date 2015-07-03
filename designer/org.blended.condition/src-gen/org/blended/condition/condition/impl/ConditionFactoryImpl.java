/**
 */
package org.blended.condition.condition.impl;

import org.blended.condition.condition.*;

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
public class ConditionFactoryImpl extends EFactoryImpl implements ConditionFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConditionFactory init()
  {
    try
    {
      ConditionFactory theConditionFactory = (ConditionFactory)EPackage.Registry.INSTANCE.getEFactory(ConditionPackage.eNS_URI);
      if (theConditionFactory != null)
      {
        return theConditionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ConditionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionFactoryImpl()
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
      case ConditionPackage.CONDITION_MODEL: return createConditionModel();
      case ConditionPackage.ENTITY_ACHIEVE_CONDITION: return createEntityAchieveCondition();
      case ConditionPackage.ENTITY_ACHIEVE_CONDITION_EXIST: return createEntityAchieveConditionExist();
      case ConditionPackage.ENTITY_INVARIANT_CONDITION: return createEntityInvariantCondition();
      case ConditionPackage.ENTITY_DEPENDENCE_CONDITION: return createEntityDependenceCondition();
      case ConditionPackage.ATTRIBUTE_ACHIEVE_CONDITION: return createAttributeAchieveCondition();
      case ConditionPackage.NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createNotMandatoryAttributeAchieveCondition();
      case ConditionPackage.MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION: return createMandatoryAttributeAchieveCondition();
      case ConditionPackage.ATTRIBUTE_INVARIANT_CONDITION: return createAttributeInvariantCondition();
      case ConditionPackage.ATTRIBUTE_DEPENDENCE_CONDITION: return createAttributeDependenceCondition();
      case ConditionPackage.NOTHING: return createNothing();
      case ConditionPackage.EXPRESSION: return createExpression();
      case ConditionPackage.CONDITION: return createCondition();
      case ConditionPackage.OR: return createOr();
      case ConditionPackage.AND: return createAnd();
      case ConditionPackage.NOT: return createNot();
      case ConditionPackage.ATTRIBUTE_DEFINITION: return createAttributeDefinition();
      case ConditionPackage.ATTRIBUTE_VALUE: return createAttributeValue();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionModel createConditionModel()
  {
    ConditionModelImpl conditionModel = new ConditionModelImpl();
    return conditionModel;
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
  public ConditionPackage getConditionPackage()
  {
    return (ConditionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ConditionPackage getPackage()
  {
    return ConditionPackage.eINSTANCE;
  }

} //ConditionFactoryImpl
