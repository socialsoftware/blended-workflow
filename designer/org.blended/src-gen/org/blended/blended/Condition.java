/**
 */
package org.blended.blended;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.Condition#getEntityAchieveConditions <em>Entity Achieve Conditions</em>}</li>
 *   <li>{@link org.blended.blended.Condition#getEntityInvariantConditions <em>Entity Invariant Conditions</em>}</li>
 *   <li>{@link org.blended.blended.Condition#getEntityDependenceConditions <em>Entity Dependence Conditions</em>}</li>
 *   <li>{@link org.blended.blended.Condition#getAttributeAchieveConditions <em>Attribute Achieve Conditions</em>}</li>
 *   <li>{@link org.blended.blended.Condition#getAttributeInvariantConditions <em>Attribute Invariant Conditions</em>}</li>
 *   <li>{@link org.blended.blended.Condition#getAttributeDependenceConditions <em>Attribute Dependence Conditions</em>}</li>
 * </ul>
 *
 * @see org.blended.blended.BlendedPackage#getCondition()
 * @model
 * @generated
 */
public interface Condition extends BlendedModel
{
  /**
   * Returns the value of the '<em><b>Entity Achieve Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Achieve Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Achieve Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getCondition_EntityAchieveConditions()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getEntityAchieveConditions();

  /**
   * Returns the value of the '<em><b>Entity Invariant Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.blended.EntityInvariantCondition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Invariant Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Invariant Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getCondition_EntityInvariantConditions()
   * @model containment="true"
   * @generated
   */
  EList<EntityInvariantCondition> getEntityInvariantConditions();

  /**
   * Returns the value of the '<em><b>Entity Dependence Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.blended.EntityDependenceCondition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Dependence Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Dependence Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getCondition_EntityDependenceConditions()
   * @model containment="true"
   * @generated
   */
  EList<EntityDependenceCondition> getEntityDependenceConditions();

  /**
   * Returns the value of the '<em><b>Attribute Achieve Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.blended.AttributeAchieveCondition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute Achieve Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Achieve Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getCondition_AttributeAchieveConditions()
   * @model containment="true"
   * @generated
   */
  EList<AttributeAchieveCondition> getAttributeAchieveConditions();

  /**
   * Returns the value of the '<em><b>Attribute Invariant Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.blended.AttributeInvariantCondition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute Invariant Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Invariant Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getCondition_AttributeInvariantConditions()
   * @model containment="true"
   * @generated
   */
  EList<AttributeInvariantCondition> getAttributeInvariantConditions();

  /**
   * Returns the value of the '<em><b>Attribute Dependence Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.blended.AttributeDependenceCondition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute Dependence Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Dependence Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getCondition_AttributeDependenceConditions()
   * @model containment="true"
   * @generated
   */
  EList<AttributeDependenceCondition> getAttributeDependenceConditions();

} // Condition
