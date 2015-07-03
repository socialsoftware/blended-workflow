/**
 */
package org.blended.data.data;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link org.blended.data.data.DataModel#getEntities <em>Entities</em>}</li>
 *   <li>{@link org.blended.data.data.DataModel#getAssociatios <em>Associatios</em>}</li>
 *   <li>{@link org.blended.data.data.DataModel#getConstraint <em>Constraint</em>}</li>
 * </ul>
 *
 * @see org.blended.data.data.DataPackage#getDataModel()
 * @model
 * @generated
 */
public interface DataModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.data.data.Entity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entities</em>' containment reference list.
   * @see org.blended.data.data.DataPackage#getDataModel_Entities()
   * @model containment="true"
   * @generated
   */
  EList<Entity> getEntities();

  /**
   * Returns the value of the '<em><b>Associatios</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.data.data.Association}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Associatios</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Associatios</em>' containment reference list.
   * @see org.blended.data.data.DataPackage#getDataModel_Associatios()
   * @model containment="true"
   * @generated
   */
  EList<Association> getAssociatios();

  /**
   * Returns the value of the '<em><b>Constraint</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.data.data.Constraint}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constraint</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraint</em>' containment reference list.
   * @see org.blended.data.data.DataPackage#getDataModel_Constraint()
   * @model containment="true"
   * @generated
   */
  EList<Constraint> getConstraint();

} // DataModel
