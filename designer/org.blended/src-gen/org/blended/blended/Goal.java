/**
 */
package org.blended.blended;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.Goal#getName <em>Name</em>}</li>
 *   <li>{@link org.blended.blended.Goal#getSucessCondition <em>Sucess Condition</em>}</li>
 *   <li>{@link org.blended.blended.Goal#getActivationCondition <em>Activation Condition</em>}</li>
 *   <li>{@link org.blended.blended.Goal#getInvariantConditions <em>Invariant Conditions</em>}</li>
 *   <li>{@link org.blended.blended.Goal#getChildrenGoals <em>Children Goals</em>}</li>
 * </ul>
 *
 * @see org.blended.blended.BlendedPackage#getGoal()
 * @model
 * @generated
 */
public interface Goal extends BlendedModel
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.blended.blended.BlendedPackage#getGoal_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.blended.blended.Goal#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Sucess Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sucess Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sucess Condition</em>' containment reference.
   * @see #setSucessCondition(EObject)
   * @see org.blended.blended.BlendedPackage#getGoal_SucessCondition()
   * @model containment="true"
   * @generated
   */
  EObject getSucessCondition();

  /**
   * Sets the value of the '{@link org.blended.blended.Goal#getSucessCondition <em>Sucess Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sucess Condition</em>' containment reference.
   * @see #getSucessCondition()
   * @generated
   */
  void setSucessCondition(EObject value);

  /**
   * Returns the value of the '<em><b>Activation Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Activation Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Activation Condition</em>' containment reference.
   * @see #setActivationCondition(EObject)
   * @see org.blended.blended.BlendedPackage#getGoal_ActivationCondition()
   * @model containment="true"
   * @generated
   */
  EObject getActivationCondition();

  /**
   * Sets the value of the '{@link org.blended.blended.Goal#getActivationCondition <em>Activation Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Activation Condition</em>' containment reference.
   * @see #getActivationCondition()
   * @generated
   */
  void setActivationCondition(EObject value);

  /**
   * Returns the value of the '<em><b>Invariant Conditions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Invariant Conditions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Invariant Conditions</em>' containment reference list.
   * @see org.blended.blended.BlendedPackage#getGoal_InvariantConditions()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getInvariantConditions();

  /**
   * Returns the value of the '<em><b>Children Goals</b></em>' reference list.
   * The list contents are of type {@link org.blended.blended.Goal}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Children Goals</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children Goals</em>' reference list.
   * @see org.blended.blended.BlendedPackage#getGoal_ChildrenGoals()
   * @model
   * @generated
   */
  EList<Goal> getChildrenGoals();

} // Goal
