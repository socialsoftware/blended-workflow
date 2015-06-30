/**
 */
package org.blended.goal.goal;

import org.blended.blended.Goal;

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
 *   <li>{@link org.blended.goal.goal.GoalModel#getGoals <em>Goals</em>}</li>
 * </ul>
 *
 * @see org.blended.goal.goal.GoalPackage#getGoalModel()
 * @model
 * @generated
 */
public interface GoalModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Goals</b></em>' containment reference list.
   * The list contents are of type {@link org.blended.blended.Goal}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Goals</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Goals</em>' containment reference list.
   * @see org.blended.goal.goal.GoalPackage#getGoalModel_Goals()
   * @model containment="true"
   * @generated
   */
  EList<Goal> getGoals();

} // GoalModel
