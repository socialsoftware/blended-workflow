/**
 */
package org.blended.blended;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Dependence Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.EntityDependenceCondition#getEntity1 <em>Entity1</em>}</li>
 *   <li>{@link org.blended.blended.EntityDependenceCondition#getEntity2 <em>Entity2</em>}</li>
 * </ul>
 *
 * @see org.blended.blended.BlendedPackage#getEntityDependenceCondition()
 * @model
 * @generated
 */
public interface EntityDependenceCondition extends EObject
{
  /**
   * Returns the value of the '<em><b>Entity1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity1</em>' attribute.
   * @see #setEntity1(String)
   * @see org.blended.blended.BlendedPackage#getEntityDependenceCondition_Entity1()
   * @model
   * @generated
   */
  String getEntity1();

  /**
   * Sets the value of the '{@link org.blended.blended.EntityDependenceCondition#getEntity1 <em>Entity1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity1</em>' attribute.
   * @see #getEntity1()
   * @generated
   */
  void setEntity1(String value);

  /**
   * Returns the value of the '<em><b>Entity2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity2</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity2</em>' attribute.
   * @see #setEntity2(String)
   * @see org.blended.blended.BlendedPackage#getEntityDependenceCondition_Entity2()
   * @model
   * @generated
   */
  String getEntity2();

  /**
   * Sets the value of the '{@link org.blended.blended.EntityDependenceCondition#getEntity2 <em>Entity2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity2</em>' attribute.
   * @see #getEntity2()
   * @generated
   */
  void setEntity2(String value);

} // EntityDependenceCondition
