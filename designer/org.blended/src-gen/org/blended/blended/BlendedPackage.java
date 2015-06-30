/**
 */
package org.blended.blended;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.blended.blended.BlendedFactory
 * @model kind="package"
 * @generated
 */
public interface BlendedPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "blended";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.blended.org/Blended";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "blended";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  BlendedPackage eINSTANCE = org.blended.blended.impl.BlendedPackageImpl.init();

  /**
   * The meta object id for the '{@link org.blended.blended.impl.BlendedModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.BlendedModelImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getBlendedModel()
   * @generated
   */
  int BLENDED_MODEL = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLENDED_MODEL_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.EntityAchieveConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getEntityAchieveCondition()
   * @generated
   */
  int ENTITY_ACHIEVE_CONDITION = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_ACHIEVE_CONDITION__NAME = 0;

  /**
   * The number of structural features of the '<em>Entity Achieve Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_ACHIEVE_CONDITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.EntityAchieveConditionExistImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getEntityAchieveConditionExist()
   * @generated
   */
  int ENTITY_ACHIEVE_CONDITION_EXIST = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_ACHIEVE_CONDITION_EXIST__NAME = 0;

  /**
   * The number of structural features of the '<em>Entity Achieve Condition Exist</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_ACHIEVE_CONDITION_EXIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.EntityInvariantConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getEntityInvariantCondition()
   * @generated
   */
  int ENTITY_INVARIANT_CONDITION = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_INVARIANT_CONDITION__NAME = 0;

  /**
   * The feature id for the '<em><b>Cardinality</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_INVARIANT_CONDITION__CARDINALITY = 1;

  /**
   * The number of structural features of the '<em>Entity Invariant Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_INVARIANT_CONDITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.EntityDependenceConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getEntityDependenceCondition()
   * @generated
   */
  int ENTITY_DEPENDENCE_CONDITION = 4;

  /**
   * The feature id for the '<em><b>Entity1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DEPENDENCE_CONDITION__ENTITY1 = 0;

  /**
   * The feature id for the '<em><b>Entity2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DEPENDENCE_CONDITION__ENTITY2 = 1;

  /**
   * The number of structural features of the '<em>Entity Dependence Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DEPENDENCE_CONDITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.AttributeAchieveConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeAchieveCondition()
   * @generated
   */
  int ATTRIBUTE_ACHIEVE_CONDITION = 5;

  /**
   * The feature id for the '<em><b>Conditions</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS = 0;

  /**
   * The number of structural features of the '<em>Attribute Achieve Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_ACHIEVE_CONDITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.NotMandatoryAttributeAchieveConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getNotMandatoryAttributeAchieveCondition()
   * @generated
   */
  int NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = 6;

  /**
   * The feature id for the '<em><b>Conditions</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS = ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS;

  /**
   * The number of structural features of the '<em>Not Mandatory Attribute Achieve Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION_FEATURE_COUNT = ATTRIBUTE_ACHIEVE_CONDITION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.MandatoryAttributeAchieveConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getMandatoryAttributeAchieveCondition()
   * @generated
   */
  int MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = 7;

  /**
   * The feature id for the '<em><b>Conditions</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS = ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS;

  /**
   * The number of structural features of the '<em>Mandatory Attribute Achieve Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION_FEATURE_COUNT = ATTRIBUTE_ACHIEVE_CONDITION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.AttributeInvariantConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeInvariantCondition()
   * @generated
   */
  int ATTRIBUTE_INVARIANT_CONDITION = 8;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_INVARIANT_CONDITION__EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>Attribute Invariant Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_INVARIANT_CONDITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.AttributeDependenceConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeDependenceCondition()
   * @generated
   */
  int ATTRIBUTE_DEPENDENCE_CONDITION = 9;

  /**
   * The feature id for the '<em><b>Attributes1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1 = 0;

  /**
   * The feature id for the '<em><b>Attribute2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2 = 1;

  /**
   * The number of structural features of the '<em>Attribute Dependence Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_DEPENDENCE_CONDITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.NothingImpl <em>Nothing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.NothingImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getNothing()
   * @generated
   */
  int NOTHING = 10;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTHING__NAME = 0;

  /**
   * The number of structural features of the '<em>Nothing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTHING_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.ExpressionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 11;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.ConditionImpl <em>Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.ConditionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getCondition()
   * @generated
   */
  int CONDITION = 12;

  /**
   * The feature id for the '<em><b>Entity Achieve Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ENTITY_ACHIEVE_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Entity Invariant Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ENTITY_INVARIANT_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Entity Dependence Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ENTITY_DEPENDENCE_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Attribute Achieve Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Attribute Invariant Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Attribute Dependence Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_FEATURE_COUNT = BLENDED_MODEL_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.GoalImpl <em>Goal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.GoalImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getGoal()
   * @generated
   */
  int GOAL = 13;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__NAME = BLENDED_MODEL_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Sucess Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__SUCESS_CONDITION = BLENDED_MODEL_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Activation Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__ACTIVATION_CONDITION = BLENDED_MODEL_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Invariant Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__INVARIANT_CONDITIONS = BLENDED_MODEL_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Children Goals</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__CHILDREN_GOALS = BLENDED_MODEL_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Goal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL_FEATURE_COUNT = BLENDED_MODEL_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.ActivityImpl <em>Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.ActivityImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getActivity()
   * @generated
   */
  int ACTIVITY = 14;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__NAME = BLENDED_MODEL_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__DESCRIPTION = BLENDED_MODEL_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Pre</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__PRE = BLENDED_MODEL_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Post</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__POST = BLENDED_MODEL_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY_FEATURE_COUNT = BLENDED_MODEL_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.OrImpl <em>Or</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.OrImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getOr()
   * @generated
   */
  int OR = 15;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR__LEFT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR__RIGHT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Or</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.AndImpl <em>And</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.AndImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getAnd()
   * @generated
   */
  int AND = 16;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND__LEFT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND__RIGHT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>And</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.NotImpl <em>Not</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.NotImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getNot()
   * @generated
   */
  int NOT = 17;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOT__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Not</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.AttributeDefinitionImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeDefinition()
   * @generated
   */
  int ATTRIBUTE_DEFINITION = 18;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_DEFINITION__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Attribute Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_DEFINITION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.blended.blended.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.blended.impl.AttributeValueImpl
   * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeValue()
   * @generated
   */
  int ATTRIBUTE_VALUE = 19;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_VALUE__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Attribute Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_VALUE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link org.blended.blended.BlendedModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.blended.blended.BlendedModel
   * @generated
   */
  EClass getBlendedModel();

  /**
   * Returns the meta object for class '{@link org.blended.blended.EntityAchieveCondition <em>Entity Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition</em>'.
   * @see org.blended.blended.EntityAchieveCondition
   * @generated
   */
  EClass getEntityAchieveCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.EntityAchieveCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.EntityAchieveCondition#getName()
   * @see #getEntityAchieveCondition()
   * @generated
   */
  EAttribute getEntityAchieveCondition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.blended.EntityAchieveConditionExist <em>Entity Achieve Condition Exist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition Exist</em>'.
   * @see org.blended.blended.EntityAchieveConditionExist
   * @generated
   */
  EClass getEntityAchieveConditionExist();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.EntityAchieveConditionExist#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.EntityAchieveConditionExist#getName()
   * @see #getEntityAchieveConditionExist()
   * @generated
   */
  EAttribute getEntityAchieveConditionExist_Name();

  /**
   * Returns the meta object for class '{@link org.blended.blended.EntityInvariantCondition <em>Entity Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Invariant Condition</em>'.
   * @see org.blended.blended.EntityInvariantCondition
   * @generated
   */
  EClass getEntityInvariantCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.EntityInvariantCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.EntityInvariantCondition#getName()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.EntityInvariantCondition#getCardinality <em>Cardinality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cardinality</em>'.
   * @see org.blended.blended.EntityInvariantCondition#getCardinality()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Cardinality();

  /**
   * Returns the meta object for class '{@link org.blended.blended.EntityDependenceCondition <em>Entity Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Dependence Condition</em>'.
   * @see org.blended.blended.EntityDependenceCondition
   * @generated
   */
  EClass getEntityDependenceCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.EntityDependenceCondition#getEntity1 <em>Entity1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity1</em>'.
   * @see org.blended.blended.EntityDependenceCondition#getEntity1()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.EntityDependenceCondition#getEntity2 <em>Entity2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity2</em>'.
   * @see org.blended.blended.EntityDependenceCondition#getEntity2()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity2();

  /**
   * Returns the meta object for class '{@link org.blended.blended.AttributeAchieveCondition <em>Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Achieve Condition</em>'.
   * @see org.blended.blended.AttributeAchieveCondition
   * @generated
   */
  EClass getAttributeAchieveCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.blended.AttributeAchieveCondition#getConditions <em>Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Conditions</em>'.
   * @see org.blended.blended.AttributeAchieveCondition#getConditions()
   * @see #getAttributeAchieveCondition()
   * @generated
   */
  EAttribute getAttributeAchieveCondition_Conditions();

  /**
   * Returns the meta object for class '{@link org.blended.blended.NotMandatoryAttributeAchieveCondition <em>Not Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.blended.NotMandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getNotMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.blended.MandatoryAttributeAchieveCondition <em>Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.blended.MandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.blended.AttributeInvariantCondition <em>Attribute Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Invariant Condition</em>'.
   * @see org.blended.blended.AttributeInvariantCondition
   * @generated
   */
  EClass getAttributeInvariantCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.AttributeInvariantCondition#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.blended.AttributeInvariantCondition#getExpression()
   * @see #getAttributeInvariantCondition()
   * @generated
   */
  EReference getAttributeInvariantCondition_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.blended.AttributeDependenceCondition <em>Attribute Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Dependence Condition</em>'.
   * @see org.blended.blended.AttributeDependenceCondition
   * @generated
   */
  EClass getAttributeDependenceCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.blended.AttributeDependenceCondition#getAttributes1 <em>Attributes1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Attributes1</em>'.
   * @see org.blended.blended.AttributeDependenceCondition#getAttributes1()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attributes1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.AttributeDependenceCondition#getAttribute2 <em>Attribute2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute2</em>'.
   * @see org.blended.blended.AttributeDependenceCondition#getAttribute2()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attribute2();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Nothing <em>Nothing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Nothing</em>'.
   * @see org.blended.blended.Nothing
   * @generated
   */
  EClass getNothing();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.Nothing#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.Nothing#getName()
   * @see #getNothing()
   * @generated
   */
  EAttribute getNothing_Name();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.blended.blended.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Condition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Condition</em>'.
   * @see org.blended.blended.Condition
   * @generated
   */
  EClass getCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Condition#getEntityAchieveConditions <em>Entity Achieve Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Achieve Conditions</em>'.
   * @see org.blended.blended.Condition#getEntityAchieveConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_EntityAchieveConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Condition#getEntityInvariantConditions <em>Entity Invariant Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Invariant Conditions</em>'.
   * @see org.blended.blended.Condition#getEntityInvariantConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_EntityInvariantConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Condition#getEntityDependenceConditions <em>Entity Dependence Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Dependence Conditions</em>'.
   * @see org.blended.blended.Condition#getEntityDependenceConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_EntityDependenceConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Condition#getAttributeAchieveConditions <em>Attribute Achieve Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute Achieve Conditions</em>'.
   * @see org.blended.blended.Condition#getAttributeAchieveConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_AttributeAchieveConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Condition#getAttributeInvariantConditions <em>Attribute Invariant Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute Invariant Conditions</em>'.
   * @see org.blended.blended.Condition#getAttributeInvariantConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_AttributeInvariantConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Condition#getAttributeDependenceConditions <em>Attribute Dependence Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute Dependence Conditions</em>'.
   * @see org.blended.blended.Condition#getAttributeDependenceConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_AttributeDependenceConditions();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Goal <em>Goal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Goal</em>'.
   * @see org.blended.blended.Goal
   * @generated
   */
  EClass getGoal();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.Goal#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.Goal#getName()
   * @see #getGoal()
   * @generated
   */
  EAttribute getGoal_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.Goal#getSucessCondition <em>Sucess Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sucess Condition</em>'.
   * @see org.blended.blended.Goal#getSucessCondition()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_SucessCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.Goal#getActivationCondition <em>Activation Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Activation Condition</em>'.
   * @see org.blended.blended.Goal#getActivationCondition()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_ActivationCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Goal#getInvariantConditions <em>Invariant Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Invariant Conditions</em>'.
   * @see org.blended.blended.Goal#getInvariantConditions()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_InvariantConditions();

  /**
   * Returns the meta object for the reference list '{@link org.blended.blended.Goal#getChildrenGoals <em>Children Goals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Children Goals</em>'.
   * @see org.blended.blended.Goal#getChildrenGoals()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_ChildrenGoals();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Activity <em>Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Activity</em>'.
   * @see org.blended.blended.Activity
   * @generated
   */
  EClass getActivity();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.Activity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.Activity#getName()
   * @see #getActivity()
   * @generated
   */
  EAttribute getActivity_Name();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.Activity#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.blended.blended.Activity#getDescription()
   * @see #getActivity()
   * @generated
   */
  EAttribute getActivity_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Activity#getPre <em>Pre</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pre</em>'.
   * @see org.blended.blended.Activity#getPre()
   * @see #getActivity()
   * @generated
   */
  EReference getActivity_Pre();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.blended.Activity#getPost <em>Post</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Post</em>'.
   * @see org.blended.blended.Activity#getPost()
   * @see #getActivity()
   * @generated
   */
  EReference getActivity_Post();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Or <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or</em>'.
   * @see org.blended.blended.Or
   * @generated
   */
  EClass getOr();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.Or#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.blended.Or#getLeft()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.Or#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.blended.Or#getRight()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Right();

  /**
   * Returns the meta object for class '{@link org.blended.blended.And <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And</em>'.
   * @see org.blended.blended.And
   * @generated
   */
  EClass getAnd();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.And#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.blended.And#getLeft()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.And#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.blended.And#getRight()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Right();

  /**
   * Returns the meta object for class '{@link org.blended.blended.Not <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not</em>'.
   * @see org.blended.blended.Not
   * @generated
   */
  EClass getNot();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.blended.Not#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.blended.Not#getExpression()
   * @see #getNot()
   * @generated
   */
  EReference getNot_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.blended.AttributeDefinition <em>Attribute Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Definition</em>'.
   * @see org.blended.blended.AttributeDefinition
   * @generated
   */
  EClass getAttributeDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.AttributeDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.AttributeDefinition#getName()
   * @see #getAttributeDefinition()
   * @generated
   */
  EAttribute getAttributeDefinition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.blended.AttributeValue <em>Attribute Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Value</em>'.
   * @see org.blended.blended.AttributeValue
   * @generated
   */
  EClass getAttributeValue();

  /**
   * Returns the meta object for the attribute '{@link org.blended.blended.AttributeValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.blended.AttributeValue#getName()
   * @see #getAttributeValue()
   * @generated
   */
  EAttribute getAttributeValue_Name();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  BlendedFactory getBlendedFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.blended.blended.impl.BlendedModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.BlendedModelImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getBlendedModel()
     * @generated
     */
    EClass BLENDED_MODEL = eINSTANCE.getBlendedModel();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.EntityAchieveConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getEntityAchieveCondition()
     * @generated
     */
    EClass ENTITY_ACHIEVE_CONDITION = eINSTANCE.getEntityAchieveCondition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_ACHIEVE_CONDITION__NAME = eINSTANCE.getEntityAchieveCondition_Name();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.EntityAchieveConditionExistImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getEntityAchieveConditionExist()
     * @generated
     */
    EClass ENTITY_ACHIEVE_CONDITION_EXIST = eINSTANCE.getEntityAchieveConditionExist();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_ACHIEVE_CONDITION_EXIST__NAME = eINSTANCE.getEntityAchieveConditionExist_Name();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.EntityInvariantConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getEntityInvariantCondition()
     * @generated
     */
    EClass ENTITY_INVARIANT_CONDITION = eINSTANCE.getEntityInvariantCondition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_INVARIANT_CONDITION__NAME = eINSTANCE.getEntityInvariantCondition_Name();

    /**
     * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_INVARIANT_CONDITION__CARDINALITY = eINSTANCE.getEntityInvariantCondition_Cardinality();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.EntityDependenceConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getEntityDependenceCondition()
     * @generated
     */
    EClass ENTITY_DEPENDENCE_CONDITION = eINSTANCE.getEntityDependenceCondition();

    /**
     * The meta object literal for the '<em><b>Entity1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_DEPENDENCE_CONDITION__ENTITY1 = eINSTANCE.getEntityDependenceCondition_Entity1();

    /**
     * The meta object literal for the '<em><b>Entity2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_DEPENDENCE_CONDITION__ENTITY2 = eINSTANCE.getEntityDependenceCondition_Entity2();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.AttributeAchieveConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeAchieveCondition()
     * @generated
     */
    EClass ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getAttributeAchieveCondition();

    /**
     * The meta object literal for the '<em><b>Conditions</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS = eINSTANCE.getAttributeAchieveCondition_Conditions();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.NotMandatoryAttributeAchieveConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getNotMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getNotMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.MandatoryAttributeAchieveConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.AttributeInvariantConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeInvariantCondition()
     * @generated
     */
    EClass ATTRIBUTE_INVARIANT_CONDITION = eINSTANCE.getAttributeInvariantCondition();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_INVARIANT_CONDITION__EXPRESSION = eINSTANCE.getAttributeInvariantCondition_Expression();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.AttributeDependenceConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeDependenceCondition()
     * @generated
     */
    EClass ATTRIBUTE_DEPENDENCE_CONDITION = eINSTANCE.getAttributeDependenceCondition();

    /**
     * The meta object literal for the '<em><b>Attributes1</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1 = eINSTANCE.getAttributeDependenceCondition_Attributes1();

    /**
     * The meta object literal for the '<em><b>Attribute2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2 = eINSTANCE.getAttributeDependenceCondition_Attribute2();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.NothingImpl <em>Nothing</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.NothingImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getNothing()
     * @generated
     */
    EClass NOTHING = eINSTANCE.getNothing();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NOTHING__NAME = eINSTANCE.getNothing_Name();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.ExpressionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.ConditionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getCondition()
     * @generated
     */
    EClass CONDITION = eINSTANCE.getCondition();

    /**
     * The meta object literal for the '<em><b>Entity Achieve Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__ENTITY_ACHIEVE_CONDITIONS = eINSTANCE.getCondition_EntityAchieveConditions();

    /**
     * The meta object literal for the '<em><b>Entity Invariant Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__ENTITY_INVARIANT_CONDITIONS = eINSTANCE.getCondition_EntityInvariantConditions();

    /**
     * The meta object literal for the '<em><b>Entity Dependence Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__ENTITY_DEPENDENCE_CONDITIONS = eINSTANCE.getCondition_EntityDependenceConditions();

    /**
     * The meta object literal for the '<em><b>Attribute Achieve Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS = eINSTANCE.getCondition_AttributeAchieveConditions();

    /**
     * The meta object literal for the '<em><b>Attribute Invariant Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS = eINSTANCE.getCondition_AttributeInvariantConditions();

    /**
     * The meta object literal for the '<em><b>Attribute Dependence Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS = eINSTANCE.getCondition_AttributeDependenceConditions();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.GoalImpl <em>Goal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.GoalImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getGoal()
     * @generated
     */
    EClass GOAL = eINSTANCE.getGoal();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GOAL__NAME = eINSTANCE.getGoal_Name();

    /**
     * The meta object literal for the '<em><b>Sucess Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GOAL__SUCESS_CONDITION = eINSTANCE.getGoal_SucessCondition();

    /**
     * The meta object literal for the '<em><b>Activation Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GOAL__ACTIVATION_CONDITION = eINSTANCE.getGoal_ActivationCondition();

    /**
     * The meta object literal for the '<em><b>Invariant Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GOAL__INVARIANT_CONDITIONS = eINSTANCE.getGoal_InvariantConditions();

    /**
     * The meta object literal for the '<em><b>Children Goals</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GOAL__CHILDREN_GOALS = eINSTANCE.getGoal_ChildrenGoals();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.ActivityImpl <em>Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.ActivityImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getActivity()
     * @generated
     */
    EClass ACTIVITY = eINSTANCE.getActivity();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ACTIVITY__NAME = eINSTANCE.getActivity_Name();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ACTIVITY__DESCRIPTION = eINSTANCE.getActivity_Description();

    /**
     * The meta object literal for the '<em><b>Pre</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTIVITY__PRE = eINSTANCE.getActivity_Pre();

    /**
     * The meta object literal for the '<em><b>Post</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTIVITY__POST = eINSTANCE.getActivity_Post();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.OrImpl <em>Or</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.OrImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getOr()
     * @generated
     */
    EClass OR = eINSTANCE.getOr();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR__LEFT = eINSTANCE.getOr_Left();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR__RIGHT = eINSTANCE.getOr_Right();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.AndImpl <em>And</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.AndImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getAnd()
     * @generated
     */
    EClass AND = eINSTANCE.getAnd();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AND__LEFT = eINSTANCE.getAnd_Left();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AND__RIGHT = eINSTANCE.getAnd_Right();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.NotImpl <em>Not</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.NotImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getNot()
     * @generated
     */
    EClass NOT = eINSTANCE.getNot();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NOT__EXPRESSION = eINSTANCE.getNot_Expression();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.AttributeDefinitionImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeDefinition()
     * @generated
     */
    EClass ATTRIBUTE_DEFINITION = eINSTANCE.getAttributeDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE_DEFINITION__NAME = eINSTANCE.getAttributeDefinition_Name();

    /**
     * The meta object literal for the '{@link org.blended.blended.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.blended.impl.AttributeValueImpl
     * @see org.blended.blended.impl.BlendedPackageImpl#getAttributeValue()
     * @generated
     */
    EClass ATTRIBUTE_VALUE = eINSTANCE.getAttributeValue();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE_VALUE__NAME = eINSTANCE.getAttributeValue_Name();

  }

} //BlendedPackage
