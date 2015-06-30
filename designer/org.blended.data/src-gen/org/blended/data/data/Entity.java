/**
 */
package org.blended.data.data;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.data.data.Entity#getName <em>Name</em>}</li>
 *   <li>{@link org.blended.data.data.Entity#isExists <em>Exists</em>}</li>
 *   <li>{@link org.blended.data.data.Entity#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link org.blended.data.data.Entity#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see org.blended.data.data.DataPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends EObject
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
   * @see org.blended.data.data.DataPackage#getEntity_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.blended.data.data.Entity#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Exists</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exists</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exists</em>' attribute.
   * @see #setExists(boolean)
   * @see org.blended.data.data.DataPackage#getEntity_Exists()
   * @model
   * @generated
   */
  boolean isExists();

  /**
   * Sets the value of the '{@link org.blended.data.data.Entity#isExists <em>Exists</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exists</em>' attribute.
   * @see #isExists()
   * @generated
   */
  void setExists(boolean value);

  /**
   * Returns the value of the '<em><b>Depends On</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Depends On</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Depends On</em>' reference.
   * @see #setDependsOn(Entity)
   * @see org.blended.data.data.DataPackage#getEntity_DependsOn()
   * @model
   * @generated
   */
  Entity getDependsOn();

  /**
   * Sets the value of the '{@link org.blended.data.data.Entity#getDependsOn <em>Depends On</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Depends On</em>' reference.
   * @see #getDependsOn()
   * @generated
   */
  void setDependsOn(Entity value);

  /**
   * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attributes</em>' containment reference list.
   * @see org.blended.data.data.DataPackage#getEntity_Attributes()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getAttributes();

} // Entity
