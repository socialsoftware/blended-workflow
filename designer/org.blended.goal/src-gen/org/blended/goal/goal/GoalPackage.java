/**
 */
package org.blended.goal.goal;

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
 * @see org.blended.goal.goal.GoalFactory
 * @model kind="package"
 * @generated
 */
public interface GoalPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "goal";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.blended.org/goal/Goal";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "goal";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GoalPackage eINSTANCE = org.blended.goal.goal.impl.GoalPackageImpl.init();

  /**
   * The meta object id for the '{@link org.blended.goal.goal.impl.GoalModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.GoalModelImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getGoalModel()
   * @generated
   */
  int GOAL_MODEL = 0;

  /**
   * The feature id for the '<em><b>Goals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL_MODEL__GOALS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL_MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.goal.goal.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.EntityAchieveConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityAchieveCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.EntityAchieveConditionExistImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityAchieveConditionExist()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.EntityInvariantConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityInvariantCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.EntityDependenceConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityDependenceCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.AttributeAchieveConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.NotMandatoryAttributeAchieveConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getNotMandatoryAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.MandatoryAttributeAchieveConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getMandatoryAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.AttributeInvariantConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeInvariantCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.AttributeDependenceConditionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeDependenceCondition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.NothingImpl <em>Nothing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.NothingImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getNothing()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.ExpressionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getExpression()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.GoalImpl <em>Goal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.GoalImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getGoal()
   * @generated
   */
  int GOAL = 12;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__NAME = 0;

  /**
   * The feature id for the '<em><b>Sucess Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__SUCESS_CONDITION = 1;

  /**
   * The feature id for the '<em><b>Activation Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__ACTIVATION_CONDITION = 2;

  /**
   * The feature id for the '<em><b>Invariant Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__INVARIANT_CONDITIONS = 3;

  /**
   * The feature id for the '<em><b>Children Goals</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL__CHILDREN_GOALS = 4;

  /**
   * The number of structural features of the '<em>Goal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GOAL_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.blended.goal.goal.impl.OrImpl <em>Or</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.OrImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getOr()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.AndImpl <em>And</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.AndImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getAnd()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.NotImpl <em>Not</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.NotImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getNot()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.AttributeDefinitionImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeDefinition()
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
   * The meta object id for the '{@link org.blended.goal.goal.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.goal.goal.impl.AttributeValueImpl
   * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeValue()
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
   * Returns the meta object for class '{@link org.blended.goal.goal.GoalModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.blended.goal.goal.GoalModel
   * @generated
   */
  EClass getGoalModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.goal.goal.GoalModel#getGoals <em>Goals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Goals</em>'.
   * @see org.blended.goal.goal.GoalModel#getGoals()
   * @see #getGoalModel()
   * @generated
   */
  EReference getGoalModel_Goals();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.EntityAchieveCondition <em>Entity Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition</em>'.
   * @see org.blended.goal.goal.EntityAchieveCondition
   * @generated
   */
  EClass getEntityAchieveCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.EntityAchieveCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.EntityAchieveCondition#getName()
   * @see #getEntityAchieveCondition()
   * @generated
   */
  EAttribute getEntityAchieveCondition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.EntityAchieveConditionExist <em>Entity Achieve Condition Exist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition Exist</em>'.
   * @see org.blended.goal.goal.EntityAchieveConditionExist
   * @generated
   */
  EClass getEntityAchieveConditionExist();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.EntityAchieveConditionExist#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.EntityAchieveConditionExist#getName()
   * @see #getEntityAchieveConditionExist()
   * @generated
   */
  EAttribute getEntityAchieveConditionExist_Name();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.EntityInvariantCondition <em>Entity Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Invariant Condition</em>'.
   * @see org.blended.goal.goal.EntityInvariantCondition
   * @generated
   */
  EClass getEntityInvariantCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.EntityInvariantCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.EntityInvariantCondition#getName()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.EntityInvariantCondition#getCardinality <em>Cardinality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cardinality</em>'.
   * @see org.blended.goal.goal.EntityInvariantCondition#getCardinality()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Cardinality();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.EntityDependenceCondition <em>Entity Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Dependence Condition</em>'.
   * @see org.blended.goal.goal.EntityDependenceCondition
   * @generated
   */
  EClass getEntityDependenceCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.EntityDependenceCondition#getEntity1 <em>Entity1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity1</em>'.
   * @see org.blended.goal.goal.EntityDependenceCondition#getEntity1()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.EntityDependenceCondition#getEntity2 <em>Entity2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity2</em>'.
   * @see org.blended.goal.goal.EntityDependenceCondition#getEntity2()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity2();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.AttributeAchieveCondition <em>Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Achieve Condition</em>'.
   * @see org.blended.goal.goal.AttributeAchieveCondition
   * @generated
   */
  EClass getAttributeAchieveCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.goal.goal.AttributeAchieveCondition#getConditions <em>Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Conditions</em>'.
   * @see org.blended.goal.goal.AttributeAchieveCondition#getConditions()
   * @see #getAttributeAchieveCondition()
   * @generated
   */
  EAttribute getAttributeAchieveCondition_Conditions();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.NotMandatoryAttributeAchieveCondition <em>Not Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.goal.goal.NotMandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getNotMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.MandatoryAttributeAchieveCondition <em>Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.goal.goal.MandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.AttributeInvariantCondition <em>Attribute Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Invariant Condition</em>'.
   * @see org.blended.goal.goal.AttributeInvariantCondition
   * @generated
   */
  EClass getAttributeInvariantCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.AttributeInvariantCondition#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.goal.goal.AttributeInvariantCondition#getExpression()
   * @see #getAttributeInvariantCondition()
   * @generated
   */
  EReference getAttributeInvariantCondition_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.AttributeDependenceCondition <em>Attribute Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Dependence Condition</em>'.
   * @see org.blended.goal.goal.AttributeDependenceCondition
   * @generated
   */
  EClass getAttributeDependenceCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.goal.goal.AttributeDependenceCondition#getAttributes1 <em>Attributes1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Attributes1</em>'.
   * @see org.blended.goal.goal.AttributeDependenceCondition#getAttributes1()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attributes1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.AttributeDependenceCondition#getAttribute2 <em>Attribute2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute2</em>'.
   * @see org.blended.goal.goal.AttributeDependenceCondition#getAttribute2()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attribute2();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.Nothing <em>Nothing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Nothing</em>'.
   * @see org.blended.goal.goal.Nothing
   * @generated
   */
  EClass getNothing();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.Nothing#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.Nothing#getName()
   * @see #getNothing()
   * @generated
   */
  EAttribute getNothing_Name();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.blended.goal.goal.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.Goal <em>Goal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Goal</em>'.
   * @see org.blended.goal.goal.Goal
   * @generated
   */
  EClass getGoal();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.Goal#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.Goal#getName()
   * @see #getGoal()
   * @generated
   */
  EAttribute getGoal_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.Goal#getSucessCondition <em>Sucess Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sucess Condition</em>'.
   * @see org.blended.goal.goal.Goal#getSucessCondition()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_SucessCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.Goal#getActivationCondition <em>Activation Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Activation Condition</em>'.
   * @see org.blended.goal.goal.Goal#getActivationCondition()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_ActivationCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.goal.goal.Goal#getInvariantConditions <em>Invariant Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Invariant Conditions</em>'.
   * @see org.blended.goal.goal.Goal#getInvariantConditions()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_InvariantConditions();

  /**
   * Returns the meta object for the reference list '{@link org.blended.goal.goal.Goal#getChildrenGoals <em>Children Goals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Children Goals</em>'.
   * @see org.blended.goal.goal.Goal#getChildrenGoals()
   * @see #getGoal()
   * @generated
   */
  EReference getGoal_ChildrenGoals();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.Or <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or</em>'.
   * @see org.blended.goal.goal.Or
   * @generated
   */
  EClass getOr();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.Or#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.goal.goal.Or#getLeft()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.Or#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.goal.goal.Or#getRight()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Right();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.And <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And</em>'.
   * @see org.blended.goal.goal.And
   * @generated
   */
  EClass getAnd();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.And#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.goal.goal.And#getLeft()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.And#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.goal.goal.And#getRight()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Right();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.Not <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not</em>'.
   * @see org.blended.goal.goal.Not
   * @generated
   */
  EClass getNot();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.goal.goal.Not#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.goal.goal.Not#getExpression()
   * @see #getNot()
   * @generated
   */
  EReference getNot_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.AttributeDefinition <em>Attribute Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Definition</em>'.
   * @see org.blended.goal.goal.AttributeDefinition
   * @generated
   */
  EClass getAttributeDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.AttributeDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.AttributeDefinition#getName()
   * @see #getAttributeDefinition()
   * @generated
   */
  EAttribute getAttributeDefinition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.goal.goal.AttributeValue <em>Attribute Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Value</em>'.
   * @see org.blended.goal.goal.AttributeValue
   * @generated
   */
  EClass getAttributeValue();

  /**
   * Returns the meta object for the attribute '{@link org.blended.goal.goal.AttributeValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.goal.goal.AttributeValue#getName()
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
  GoalFactory getGoalFactory();

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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.GoalModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.GoalModelImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getGoalModel()
     * @generated
     */
    EClass GOAL_MODEL = eINSTANCE.getGoalModel();

    /**
     * The meta object literal for the '<em><b>Goals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GOAL_MODEL__GOALS = eINSTANCE.getGoalModel_Goals();

    /**
     * The meta object literal for the '{@link org.blended.goal.goal.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.EntityAchieveConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityAchieveCondition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.EntityAchieveConditionExistImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityAchieveConditionExist()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.EntityInvariantConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityInvariantCondition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.EntityDependenceConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getEntityDependenceCondition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.AttributeAchieveConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeAchieveCondition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.NotMandatoryAttributeAchieveConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getNotMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getNotMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.goal.goal.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.MandatoryAttributeAchieveConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.goal.goal.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.AttributeInvariantConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeInvariantCondition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.AttributeDependenceConditionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeDependenceCondition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.NothingImpl <em>Nothing</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.NothingImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getNothing()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.ExpressionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link org.blended.goal.goal.impl.GoalImpl <em>Goal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.GoalImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getGoal()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.OrImpl <em>Or</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.OrImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getOr()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.AndImpl <em>And</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.AndImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getAnd()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.NotImpl <em>Not</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.NotImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getNot()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.AttributeDefinitionImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeDefinition()
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
     * The meta object literal for the '{@link org.blended.goal.goal.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.goal.goal.impl.AttributeValueImpl
     * @see org.blended.goal.goal.impl.GoalPackageImpl#getAttributeValue()
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

} //GoalPackage
