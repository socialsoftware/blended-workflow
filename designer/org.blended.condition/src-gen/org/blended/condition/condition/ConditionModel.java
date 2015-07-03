/**
 */
package org.blended.condition.condition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.condition.condition.ConditionModel#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @see org.blended.condition.condition.ConditionPackage#getConditionModel()
 * @model
 * @generated
 */
public interface ConditionModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Conditions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conditions</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conditions</em>' containment reference.
   * @see #setConditions(Condition)
   * @see org.blended.condition.condition.ConditionPackage#getConditionModel_Conditions()
   * @model containment="true"
   * @generated
   */
  Condition getConditions();

  /**
   * Sets the value of the '{@link org.blended.condition.condition.ConditionModel#getConditions <em>Conditions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conditions</em>' containment reference.
   * @see #getConditions()
   * @generated
   */
  void setConditions(Condition value);

} // ConditionModel
