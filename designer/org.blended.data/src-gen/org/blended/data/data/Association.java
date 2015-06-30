/**
 */
package org.blended.data.data;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.blended.data.data.Association#getEntity1 <em>Entity1</em>}</li>
 *   <li>{@link org.blended.data.data.Association#getName1 <em>Name1</em>}</li>
 *   <li>{@link org.blended.data.data.Association#getCardinality1 <em>Cardinality1</em>}</li>
 *   <li>{@link org.blended.data.data.Association#getEntity2 <em>Entity2</em>}</li>
 *   <li>{@link org.blended.data.data.Association#getName2 <em>Name2</em>}</li>
 *   <li>{@link org.blended.data.data.Association#getCardinality2 <em>Cardinality2</em>}</li>
 * </ul>
 *
 * @see org.blended.data.data.DataPackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends EObject
{
  /**
   * Returns the value of the '<em><b>Entity1</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity1</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity1</em>' reference.
   * @see #setEntity1(Entity)
   * @see org.blended.data.data.DataPackage#getAssociation_Entity1()
   * @model
   * @generated
   */
  Entity getEntity1();

  /**
   * Sets the value of the '{@link org.blended.data.data.Association#getEntity1 <em>Entity1</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity1</em>' reference.
   * @see #getEntity1()
   * @generated
   */
  void setEntity1(Entity value);

  /**
   * Returns the value of the '<em><b>Name1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name1</em>' attribute.
   * @see #setName1(String)
   * @see org.blended.data.data.DataPackage#getAssociation_Name1()
   * @model
   * @generated
   */
  String getName1();

  /**
   * Sets the value of the '{@link org.blended.data.data.Association#getName1 <em>Name1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name1</em>' attribute.
   * @see #getName1()
   * @generated
   */
  void setName1(String value);

  /**
   * Returns the value of the '<em><b>Cardinality1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cardinality1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cardinality1</em>' attribute.
   * @see #setCardinality1(String)
   * @see org.blended.data.data.DataPackage#getAssociation_Cardinality1()
   * @model
   * @generated
   */
  String getCardinality1();

  /**
   * Sets the value of the '{@link org.blended.data.data.Association#getCardinality1 <em>Cardinality1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cardinality1</em>' attribute.
   * @see #getCardinality1()
   * @generated
   */
  void setCardinality1(String value);

  /**
   * Returns the value of the '<em><b>Entity2</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity2</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity2</em>' reference.
   * @see #setEntity2(Entity)
   * @see org.blended.data.data.DataPackage#getAssociation_Entity2()
   * @model
   * @generated
   */
  Entity getEntity2();

  /**
   * Sets the value of the '{@link org.blended.data.data.Association#getEntity2 <em>Entity2</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity2</em>' reference.
   * @see #getEntity2()
   * @generated
   */
  void setEntity2(Entity value);

  /**
   * Returns the value of the '<em><b>Name2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name2</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name2</em>' attribute.
   * @see #setName2(String)
   * @see org.blended.data.data.DataPackage#getAssociation_Name2()
   * @model
   * @generated
   */
  String getName2();

  /**
   * Sets the value of the '{@link org.blended.data.data.Association#getName2 <em>Name2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name2</em>' attribute.
   * @see #getName2()
   * @generated
   */
  void setName2(String value);

  /**
   * Returns the value of the '<em><b>Cardinality2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cardinality2</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cardinality2</em>' attribute.
   * @see #setCardinality2(String)
   * @see org.blended.data.data.DataPackage#getAssociation_Cardinality2()
   * @model
   * @generated
   */
  String getCardinality2();

  /**
   * Sets the value of the '{@link org.blended.data.data.Association#getCardinality2 <em>Cardinality2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cardinality2</em>' attribute.
   * @see #getCardinality2()
   * @generated
   */
  void setCardinality2(String value);

} // Association
