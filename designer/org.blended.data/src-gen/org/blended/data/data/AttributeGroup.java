/**
 */
package org.blended.data.data;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.data.data.AttributeGroup#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link org.blended.data.data.AttributeGroup#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link org.blended.data.data.AttributeGroup#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see org.blended.data.data.DataPackage#getAttributeGroup()
 * @model
 * @generated
 */
public interface AttributeGroup extends EObject
{
  /**
   * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mandatory</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mandatory</em>' attribute.
   * @see #setMandatory(boolean)
   * @see org.blended.data.data.DataPackage#getAttributeGroup_Mandatory()
   * @model
   * @generated
   */
  boolean isMandatory();

  /**
   * Sets the value of the '{@link org.blended.data.data.AttributeGroup#isMandatory <em>Mandatory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mandatory</em>' attribute.
   * @see #isMandatory()
   * @generated
   */
  void setMandatory(boolean value);

  /**
   * Returns the value of the '<em><b>Depends On</b></em>' reference list.
   * The list contents are of type {@link org.blended.data.data.Attribute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Depends On</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Depends On</em>' reference list.
   * @see org.blended.data.data.DataPackage#getAttributeGroup_DependsOn()
   * @model
   * @generated
   */
  EList<Attribute> getDependsOn();

  /**
   * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.data.data.Attribute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attributes</em>' containment reference list.
   * @see org.blended.data.data.DataPackage#getAttributeGroup_Attributes()
   * @model containment="true"
   * @generated
   */
  EList<Attribute> getAttributes();

} // AttributeGroup
