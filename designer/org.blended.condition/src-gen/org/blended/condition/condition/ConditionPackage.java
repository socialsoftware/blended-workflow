/**
 */
package org.blended.condition.condition;

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
 * @see org.blended.condition.condition.ConditionFactory
 * @model kind="package"
 * @generated
 */
public interface ConditionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "condition";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.blended.org/condition/Condition";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "condition";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ConditionPackage eINSTANCE = org.blended.condition.condition.impl.ConditionPackageImpl.init();

  /**
   * The meta object id for the '{@link org.blended.condition.condition.impl.ConditionModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.ConditionModelImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getConditionModel()
   * @generated
   */
  int CONDITION_MODEL = 0;

  /**
   * The feature id for the '<em><b>Conditions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_MODEL__CONDITIONS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.blended.condition.condition.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.EntityAchieveConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityAchieveCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.EntityAchieveConditionExistImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityAchieveConditionExist()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.EntityInvariantConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityInvariantCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.EntityDependenceConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityDependenceCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.AttributeAchieveConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.NotMandatoryAttributeAchieveConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getNotMandatoryAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.MandatoryAttributeAchieveConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getMandatoryAttributeAchieveCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.AttributeInvariantConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeInvariantCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.AttributeDependenceConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeDependenceCondition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.NothingImpl <em>Nothing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.NothingImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getNothing()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.ExpressionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getExpression()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.ConditionImpl <em>Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.ConditionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getCondition()
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
  int CONDITION__ENTITY_ACHIEVE_CONDITIONS = 0;

  /**
   * The feature id for the '<em><b>Entity Invariant Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ENTITY_INVARIANT_CONDITIONS = 1;

  /**
   * The feature id for the '<em><b>Entity Dependence Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ENTITY_DEPENDENCE_CONDITIONS = 2;

  /**
   * The feature id for the '<em><b>Attribute Achieve Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS = 3;

  /**
   * The feature id for the '<em><b>Attribute Invariant Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS = 4;

  /**
   * The feature id for the '<em><b>Attribute Dependence Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS = 5;

  /**
   * The number of structural features of the '<em>Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.blended.condition.condition.impl.OrImpl <em>Or</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.OrImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getOr()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.AndImpl <em>And</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.AndImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAnd()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.NotImpl <em>Not</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.NotImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getNot()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.AttributeDefinitionImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeDefinition()
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
   * The meta object id for the '{@link org.blended.condition.condition.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.blended.condition.condition.impl.AttributeValueImpl
   * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeValue()
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
   * Returns the meta object for class '{@link org.blended.condition.condition.ConditionModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.blended.condition.condition.ConditionModel
   * @generated
   */
  EClass getConditionModel();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.ConditionModel#getConditions <em>Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conditions</em>'.
   * @see org.blended.condition.condition.ConditionModel#getConditions()
   * @see #getConditionModel()
   * @generated
   */
  EReference getConditionModel_Conditions();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.EntityAchieveCondition <em>Entity Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition</em>'.
   * @see org.blended.condition.condition.EntityAchieveCondition
   * @generated
   */
  EClass getEntityAchieveCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.EntityAchieveCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.condition.condition.EntityAchieveCondition#getName()
   * @see #getEntityAchieveCondition()
   * @generated
   */
  EAttribute getEntityAchieveCondition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.EntityAchieveConditionExist <em>Entity Achieve Condition Exist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Achieve Condition Exist</em>'.
   * @see org.blended.condition.condition.EntityAchieveConditionExist
   * @generated
   */
  EClass getEntityAchieveConditionExist();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.EntityAchieveConditionExist#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.condition.condition.EntityAchieveConditionExist#getName()
   * @see #getEntityAchieveConditionExist()
   * @generated
   */
  EAttribute getEntityAchieveConditionExist_Name();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.EntityInvariantCondition <em>Entity Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Invariant Condition</em>'.
   * @see org.blended.condition.condition.EntityInvariantCondition
   * @generated
   */
  EClass getEntityInvariantCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.EntityInvariantCondition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.condition.condition.EntityInvariantCondition#getName()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.EntityInvariantCondition#getCardinality <em>Cardinality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cardinality</em>'.
   * @see org.blended.condition.condition.EntityInvariantCondition#getCardinality()
   * @see #getEntityInvariantCondition()
   * @generated
   */
  EAttribute getEntityInvariantCondition_Cardinality();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.EntityDependenceCondition <em>Entity Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Dependence Condition</em>'.
   * @see org.blended.condition.condition.EntityDependenceCondition
   * @generated
   */
  EClass getEntityDependenceCondition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.EntityDependenceCondition#getEntity1 <em>Entity1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity1</em>'.
   * @see org.blended.condition.condition.EntityDependenceCondition#getEntity1()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.EntityDependenceCondition#getEntity2 <em>Entity2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity2</em>'.
   * @see org.blended.condition.condition.EntityDependenceCondition#getEntity2()
   * @see #getEntityDependenceCondition()
   * @generated
   */
  EAttribute getEntityDependenceCondition_Entity2();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.AttributeAchieveCondition <em>Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Achieve Condition</em>'.
   * @see org.blended.condition.condition.AttributeAchieveCondition
   * @generated
   */
  EClass getAttributeAchieveCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.condition.condition.AttributeAchieveCondition#getConditions <em>Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Conditions</em>'.
   * @see org.blended.condition.condition.AttributeAchieveCondition#getConditions()
   * @see #getAttributeAchieveCondition()
   * @generated
   */
  EAttribute getAttributeAchieveCondition_Conditions();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.NotMandatoryAttributeAchieveCondition <em>Not Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.condition.condition.NotMandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getNotMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.MandatoryAttributeAchieveCondition <em>Mandatory Attribute Achieve Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mandatory Attribute Achieve Condition</em>'.
   * @see org.blended.condition.condition.MandatoryAttributeAchieveCondition
   * @generated
   */
  EClass getMandatoryAttributeAchieveCondition();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.AttributeInvariantCondition <em>Attribute Invariant Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Invariant Condition</em>'.
   * @see org.blended.condition.condition.AttributeInvariantCondition
   * @generated
   */
  EClass getAttributeInvariantCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.AttributeInvariantCondition#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.condition.condition.AttributeInvariantCondition#getExpression()
   * @see #getAttributeInvariantCondition()
   * @generated
   */
  EReference getAttributeInvariantCondition_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.AttributeDependenceCondition <em>Attribute Dependence Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Dependence Condition</em>'.
   * @see org.blended.condition.condition.AttributeDependenceCondition
   * @generated
   */
  EClass getAttributeDependenceCondition();

  /**
   * Returns the meta object for the attribute list '{@link org.blended.condition.condition.AttributeDependenceCondition#getAttributes1 <em>Attributes1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Attributes1</em>'.
   * @see org.blended.condition.condition.AttributeDependenceCondition#getAttributes1()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attributes1();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.AttributeDependenceCondition#getAttribute2 <em>Attribute2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute2</em>'.
   * @see org.blended.condition.condition.AttributeDependenceCondition#getAttribute2()
   * @see #getAttributeDependenceCondition()
   * @generated
   */
  EAttribute getAttributeDependenceCondition_Attribute2();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.Nothing <em>Nothing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Nothing</em>'.
   * @see org.blended.condition.condition.Nothing
   * @generated
   */
  EClass getNothing();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.Nothing#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.condition.condition.Nothing#getName()
   * @see #getNothing()
   * @generated
   */
  EAttribute getNothing_Name();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.blended.condition.condition.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.Condition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Condition</em>'.
   * @see org.blended.condition.condition.Condition
   * @generated
   */
  EClass getCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.condition.condition.Condition#getEntityAchieveConditions <em>Entity Achieve Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Achieve Conditions</em>'.
   * @see org.blended.condition.condition.Condition#getEntityAchieveConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_EntityAchieveConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.condition.condition.Condition#getEntityInvariantConditions <em>Entity Invariant Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Invariant Conditions</em>'.
   * @see org.blended.condition.condition.Condition#getEntityInvariantConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_EntityInvariantConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.condition.condition.Condition#getEntityDependenceConditions <em>Entity Dependence Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Dependence Conditions</em>'.
   * @see org.blended.condition.condition.Condition#getEntityDependenceConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_EntityDependenceConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.condition.condition.Condition#getAttributeAchieveConditions <em>Attribute Achieve Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute Achieve Conditions</em>'.
   * @see org.blended.condition.condition.Condition#getAttributeAchieveConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_AttributeAchieveConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.condition.condition.Condition#getAttributeInvariantConditions <em>Attribute Invariant Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute Invariant Conditions</em>'.
   * @see org.blended.condition.condition.Condition#getAttributeInvariantConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_AttributeInvariantConditions();

  /**
   * Returns the meta object for the containment reference list '{@link org.blended.condition.condition.Condition#getAttributeDependenceConditions <em>Attribute Dependence Conditions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute Dependence Conditions</em>'.
   * @see org.blended.condition.condition.Condition#getAttributeDependenceConditions()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_AttributeDependenceConditions();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.Or <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or</em>'.
   * @see org.blended.condition.condition.Or
   * @generated
   */
  EClass getOr();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.Or#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.condition.condition.Or#getLeft()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.Or#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.condition.condition.Or#getRight()
   * @see #getOr()
   * @generated
   */
  EReference getOr_Right();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.And <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And</em>'.
   * @see org.blended.condition.condition.And
   * @generated
   */
  EClass getAnd();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.And#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.blended.condition.condition.And#getLeft()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.And#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.blended.condition.condition.And#getRight()
   * @see #getAnd()
   * @generated
   */
  EReference getAnd_Right();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.Not <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Not</em>'.
   * @see org.blended.condition.condition.Not
   * @generated
   */
  EClass getNot();

  /**
   * Returns the meta object for the containment reference '{@link org.blended.condition.condition.Not#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.blended.condition.condition.Not#getExpression()
   * @see #getNot()
   * @generated
   */
  EReference getNot_Expression();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.AttributeDefinition <em>Attribute Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Definition</em>'.
   * @see org.blended.condition.condition.AttributeDefinition
   * @generated
   */
  EClass getAttributeDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.AttributeDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.condition.condition.AttributeDefinition#getName()
   * @see #getAttributeDefinition()
   * @generated
   */
  EAttribute getAttributeDefinition_Name();

  /**
   * Returns the meta object for class '{@link org.blended.condition.condition.AttributeValue <em>Attribute Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Value</em>'.
   * @see org.blended.condition.condition.AttributeValue
   * @generated
   */
  EClass getAttributeValue();

  /**
   * Returns the meta object for the attribute '{@link org.blended.condition.condition.AttributeValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.blended.condition.condition.AttributeValue#getName()
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
  ConditionFactory getConditionFactory();

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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.ConditionModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.ConditionModelImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getConditionModel()
     * @generated
     */
    EClass CONDITION_MODEL = eINSTANCE.getConditionModel();

    /**
     * The meta object literal for the '<em><b>Conditions</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION_MODEL__CONDITIONS = eINSTANCE.getConditionModel_Conditions();

    /**
     * The meta object literal for the '{@link org.blended.condition.condition.impl.EntityAchieveConditionImpl <em>Entity Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.EntityAchieveConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityAchieveCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.EntityAchieveConditionExistImpl <em>Entity Achieve Condition Exist</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.EntityAchieveConditionExistImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityAchieveConditionExist()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.EntityInvariantConditionImpl <em>Entity Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.EntityInvariantConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityInvariantCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.EntityDependenceConditionImpl <em>Entity Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.EntityDependenceConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getEntityDependenceCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.AttributeAchieveConditionImpl <em>Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.AttributeAchieveConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeAchieveCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.NotMandatoryAttributeAchieveConditionImpl <em>Not Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.NotMandatoryAttributeAchieveConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getNotMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass NOT_MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getNotMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.condition.condition.impl.MandatoryAttributeAchieveConditionImpl <em>Mandatory Attribute Achieve Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.MandatoryAttributeAchieveConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getMandatoryAttributeAchieveCondition()
     * @generated
     */
    EClass MANDATORY_ATTRIBUTE_ACHIEVE_CONDITION = eINSTANCE.getMandatoryAttributeAchieveCondition();

    /**
     * The meta object literal for the '{@link org.blended.condition.condition.impl.AttributeInvariantConditionImpl <em>Attribute Invariant Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.AttributeInvariantConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeInvariantCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.AttributeDependenceConditionImpl <em>Attribute Dependence Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.AttributeDependenceConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeDependenceCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.NothingImpl <em>Nothing</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.NothingImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getNothing()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.ExpressionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link org.blended.condition.condition.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.ConditionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getCondition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.OrImpl <em>Or</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.OrImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getOr()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.AndImpl <em>And</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.AndImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAnd()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.NotImpl <em>Not</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.NotImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getNot()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.AttributeDefinitionImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeDefinition()
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
     * The meta object literal for the '{@link org.blended.condition.condition.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.blended.condition.condition.impl.AttributeValueImpl
     * @see org.blended.condition.condition.impl.ConditionPackageImpl#getAttributeValue()
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

} //ConditionPackage
