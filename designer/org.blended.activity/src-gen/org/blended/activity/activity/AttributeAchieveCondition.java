/**
 */
package org.blended.activity.activity;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Achieve Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.activity.activity.AttributeAchieveCondition#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @see org.blended.activity.activity.ActivityPackage#getAttributeAchieveCondition()
 * @model
 * @generated
 */
public interface AttributeAchieveCondition extends EObject
{
  /**
   * Returns the value of the '<em><b>Conditions</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conditions</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conditions</em>' attribute list.
   * @see org.blended.activity.activity.ActivityPackage#getAttributeAchieveCondition_Conditions()
   * @model unique="false"
   * @generated
   */
  EList<String> getConditions();

} // AttributeAchieveCondition
