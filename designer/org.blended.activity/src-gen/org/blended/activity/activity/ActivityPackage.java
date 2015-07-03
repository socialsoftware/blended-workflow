/**
 */
package org.blended.activity.activity;

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
 * @see org.blended.activity.activity.ActivityFactory
 * @model kind="package"
 * @generated
 */
public interface ActivityPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "activity";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.blended.org/activity/Activity";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "activity";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ActivityPackage eINSTANCE = org.blended.activity.activity.impl.ActivityPackageImpl.init();

  /**
   * The meta object id for the '{@link org.blended.activity.activity.impl.ActivityModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.ActivityModelImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getActivityModel()
   * @generated
   */
  int ACTIVITY_MODEL = 0;

  /**
   * The feature id for the '<em><b>Activities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY_MODEL__ACTIVITIES = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY_MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.activity.activity.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.EntityAchieveConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityAchieveCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.EntityAchieveConditionExistImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityAchieveConditionExist()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.EntityInvariantConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityInvariantCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.EntityDependenceConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityDependenceCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.AttributeAchieveConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.NotMandatoryAttributeAchieveConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getNotMandatoryAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.MandatoryAttributeAchieveConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getMandatoryAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.AttributeInvariantConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeInvariantCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.AttributeDependenceConditionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeDependenceCondition()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.NothingImpl <em>Nothing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.NothingImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getNothing()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.ExpressionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getExpression()
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
   * The meta object id for the '{@link org.blended.activity.activity.impl.ActivityImpl <em>Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.ActivityImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getActivity()
   * @generated
   */
  int ACTIVITY = 12;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__NAME = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__DESCRIPTION = 1;

  /**
   * The feature id for the '<em><b>Pre</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__PRE = 2;

  /**
   * The feature id for the '<em><b>Post</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY__POST = 3;

  /**
   * The number of structural features of the '<em>Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTIVITY_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.blended.activity.activity.impl.OrImpl <em>Or</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.OrImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getOr()
   * @generated
   */
  int OR = 13;

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
   * The meta object id for the '{@link org.blended.activity.activity.impl.AndImpl <em>And</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.AndImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAnd()
   * @generated
   */
  int AND = 14;

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
   * The meta object id for the '{@link org.blended.activity.activity.impl.NotImpl <em>Not</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.NotImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getNot()
   * @generated
   */
  int NOT = 15;

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
   * The meta object id for the '{@link org.blended.activity.activity.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.AttributeDefinitionImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeDefinition()
   * @generated
   */
  int ATTRIBUTE_DEFINITION = 16;

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
   * The meta object id for the '{@link org.blended.activity.activity.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.activity.activity.impl.AttributeValueImpl
   * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeValue()
   * @generated
   */
  int ATTRIBUTE_VALUE = 17;

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
   * Returns the meta object for class '{@link org.blended.activity.activity.ActivityModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.blended.activity.activity.ActivityModel
   * @generated
   */
  EClass getActivityModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.activity.activity.ActivityModel#getActivities <em>Activities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Activities</em>'.
   * @see org.blended.activity.activity.ActivityModel#getActivities()
   * @see #getActivityModel()
   * @generated
   */
  EReference getActivityModel_Activities();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.EntityAchieveCondition <em>Entity Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition</em>'.
   * @see org.blended.activity.activity.EntityAchieveCondition
   * @generated
   */
  EClass getEntityAchieveCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.EntityAchieveCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.EntityAchieveCondition#getName()
   * @see #getEntityAchieveCondition()
   * @generated
   */
  EAttribute getEntityAchieveCondition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.EntityAchieveConditionExist <em>Entity Achieve Condition Exist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition Exist</em>'.
   * @see org.blended.activity.activity.EntityAchieveConditionExist
   * @generated
   */
  EClass getEntityAchieveConditionExist();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.EntityAchieveConditionExist#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.EntityAchieveConditionExist#getName()
   * @see #getEntityAchieveConditionExist()
   * @generated
   */
  EAttribute getEntityAchieveConditionExist_Name();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.EntityInvariantCondition <em>Entity Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Invariant Condition</em>'.
   * @see org.blended.activity.activity.EntityInvariantCondition
   * @generated
   */
  EClass getEntityInvariantCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.EntityInvariantCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.EntityInvariantCondition#getName()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.EntityInvariantCondition#getCardinality <em>Cardinality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cardinality</em>'.
   * @see org.blended.activity.activity.EntityInvariantCondition#getCardinality()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Cardinality();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.EntityDependenceCondition <em>Entity Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Dependence Condition</em>'.
   * @see org.blended.activity.activity.EntityDependenceCondition
   * @generated
   */
  EClass getEntityDependenceCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.EntityDependenceCondition#getEntity1 <em>Entity1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity1</em>'.
   * @see org.blended.activity.activity.EntityDependenceCondition#getEntity1()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.EntityDependenceCondition#getEntity2 <em>Entity2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity2</em>'.
   * @see org.blended.activity.activity.EntityDependenceCondition#getEntity2()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity2();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.AttributeAchieveCondition <em>Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Achieve Condition</em>'.
   * @see org.blended.activity.activity.AttributeAchieveCondition
   * @generated
   */
  EClass getAttributeAchieveCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.activity.activity.AttributeAchieveCondition#getConditions <em>Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Conditions</em>'.
   * @see org.blended.activity.activity.AttributeAchieveCondition#getConditions()
   * @see #getAttributeAchieveCondition()
   * @generated
   */
  EAttribute getAttributeAchieveCondition_Conditions();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.NotMandatoryAttributeAchieveCondition <em>Not Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.activity.activity.NotMandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getNotMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.MandatoryAttributeAchieveCondition <em>Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.activity.activity.MandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.AttributeInvariantCondition <em>Attribute Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Invariant Condition</em>'.
   * @see org.blended.activity.activity.AttributeInvariantCondition
   * @generated
   */
  EClass getAttributeInvariantCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.activity.activity.AttributeInvariantCondition#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.activity.activity.AttributeInvariantCondition#getExpression()
   * @see #getAttributeInvariantCondition()
   * @generated
   */
  EReference getAttributeInvariantCondition_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.AttributeDependenceCondition <em>Attribute Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Dependence Condition</em>'.
   * @see org.blended.activity.activity.AttributeDependenceCondition
   * @generated
   */
  EClass getAttributeDependenceCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.activity.activity.AttributeDependenceCondition#getAttributes1 <em>Attributes1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Attributes1</em>'.
   * @see org.blended.activity.activity.AttributeDependenceCondition#getAttributes1()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attributes1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.AttributeDependenceCondition#getAttribute2 <em>Attribute2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute2</em>'.
   * @see org.blended.activity.activity.AttributeDependenceCondition#getAttribute2()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attribute2();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.Nothing <em>Nothing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Nothing</em>'.
   * @see org.blended.activity.activity.Nothing
   * @generated
   */
  EClass getNothing();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.Nothing#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.Nothing#getName()
   * @see #getNothing()
   * @generated
   */
  EAttribute getNothing_Name();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.blended.activity.activity.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.Activity <em>Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Activity</em>'.
   * @see org.blended.activity.activity.Activity
   * @generated
   */
  EClass getActivity();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.Activity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.Activity#getName()
   * @see #getActivity()
   * @generated
   */
  EAttribute getActivity_Name();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.Activity#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.blended.activity.activity.Activity#getDescription()
   * @see #getActivity()
   * @generated
   */
  EAttribute getActivity_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.activity.activity.Activity#getPre <em>Pre</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pre</em>'.
   * @see org.blended.activity.activity.Activity#getPre()
   * @see #getActivity()
   * @generated
   */
  EReference getActivity_Pre();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.activity.activity.Activity#getPost <em>Post</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Post</em>'.
   * @see org.blended.activity.activity.Activity#getPost()
   * @see #getActivity()
   * @generated
   */
  EReference getActivity_Post();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.Or <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or</em>'.
   * @see org.blended.activity.activity.Or
   * @generated
   */
  EClass getOr();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.activity.activity.Or#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.activity.activity.Or#getLeft()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.activity.activity.Or#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.activity.activity.Or#getRight()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Right();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.And <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And</em>'.
   * @see org.blended.activity.activity.And
   * @generated
   */
  EClass getAnd();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.activity.activity.And#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.activity.activity.And#getLeft()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.activity.activity.And#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.activity.activity.And#getRight()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Right();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.Not <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not</em>'.
   * @see org.blended.activity.activity.Not
   * @generated
   */
  EClass getNot();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.activity.activity.Not#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.activity.activity.Not#getExpression()
   * @see #getNot()
   * @generated
   */
  EReference getNot_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.AttributeDefinition <em>Attribute Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Definition</em>'.
   * @see org.blended.activity.activity.AttributeDefinition
   * @generated
   */
  EClass getAttributeDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.AttributeDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.AttributeDefinition#getName()
   * @see #getAttributeDefinition()
   * @generated
   */
  EAttribute getAttributeDefinition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.activity.activity.AttributeValue <em>Attribute Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Value</em>'.
   * @see org.blended.activity.activity.AttributeValue
   * @generated
   */
  EClass getAttributeValue();

  /**
   * Returns the meta object for the attribute '{@link org.blended.activity.activity.AttributeValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.activity.activity.AttributeValue#getName()
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
  ActivityFactory getActivityFactory();

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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.ActivityModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.ActivityModelImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getActivityModel()
     * @generated
     */
    EClass ACTIVITY_MODEL = eINSTANCE.getActivityModel();

    /**
     * The meta object literal for the '<em><b>Activities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTIVITY_MODEL__ACTIVITIES = eINSTANCE.getActivityModel_Activities();

    /**
     * The meta object literal for the '{@link org.blended.activity.activity.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.EntityAchieveConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityAchieveCondition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.EntityAchieveConditionExistImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityAchieveConditionExist()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.EntityInvariantConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityInvariantCondition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.EntityDependenceConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getEntityDependenceCondition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.AttributeAchieveConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeAchieveCondition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.NotMandatoryAttributeAchieveConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getNotMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getNotMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.activity.activity.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.MandatoryAttributeAchieveConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.activity.activity.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.AttributeInvariantConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeInvariantCondition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.AttributeDependenceConditionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeDependenceCondition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.NothingImpl <em>Nothing</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.NothingImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getNothing()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.ExpressionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link org.blended.activity.activity.impl.ActivityImpl <em>Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.ActivityImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getActivity()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.OrImpl <em>Or</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.OrImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getOr()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.AndImpl <em>And</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.AndImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAnd()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.NotImpl <em>Not</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.NotImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getNot()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.AttributeDefinitionImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeDefinition()
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
     * The meta object literal for the '{@link org.blended.activity.activity.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.activity.activity.impl.AttributeValueImpl
     * @see org.blended.activity.activity.impl.ActivityPackageImpl#getAttributeValue()
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

} //ActivityPackage
