/**
 */
package org.blended.activity.activity;

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
 *   <li>{@link org.blended.activity.activity.ActivityModel#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @see org.blended.activity.activity.ActivityPackage#getActivityModel()
 * @model
 * @generated
 */
public interface ActivityModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Activities</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.activity.activity.Activity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Activities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Activities</em>' containment reference list.
   * @see org.blended.activity.activity.ActivityPackage#getActivityModel_Activities()
   * @model containment="true"
   * @generated
   */
  EList<Activity> getActivities();

} // ActivityModel
