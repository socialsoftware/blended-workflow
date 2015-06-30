/**
 */
package org.blended.blended;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Dependence Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.AttributeDependenceCondition#getAttributes1 <em>Attributes1</em>}</li>
 *   <li>{@link org.blended.blended.AttributeDependenceCondition#getAttribute2 <em>Attribute2</em>}</li>
 * </ul>
 *
 * @see org.blended.blended.BlendedPackage#getAttributeDependenceCondition()
 * @model
 * @generated
 */
public interface AttributeDependenceCondition extends EObject
{
  /**
   * Returns the value of the '<em><b>Attributes1</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attributes1</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attributes1</em>' attribute list.
   * @see org.blended.blended.BlendedPackage#getAttributeDependenceCondition_Attributes1()
   * @model unique="false"
   * @generated
   */
  EList<String> getAttributes1();

  /**
   * Returns the value of the '<em><b>Attribute2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute2</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute2</em>' attribute.
   * @see #setAttribute2(String)
   * @see org.blended.blended.BlendedPackage#getAttributeDependenceCondition_Attribute2()
   * @model
   * @generated
   */
  String getAttribute2();

  /**
   * Sets the value of the '{@link org.blended.blended.AttributeDependenceCondition#getAttribute2 <em>Attribute2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute2</em>' attribute.
   * @see #getAttribute2()
   * @generated
   */
  void setAttribute2(String value);

} // AttributeDependenceCondition
