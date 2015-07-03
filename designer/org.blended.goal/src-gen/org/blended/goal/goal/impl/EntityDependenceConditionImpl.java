/**
 */
package org.blended.goal.goal.impl;

import org.blended.goal.goal.EntityDependenceCondition;
import org.blended.goal.goal.GoalPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Dependence Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.goal.goal.impl.EntityDependenceConditionImpl#getEntity1 <em>Entity1</em>}</li>
 *   <li>{@link org.blended.goal.goal.impl.EntityDependenceConditionImpl#getEntity2 <em>Entity2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDependenceConditionImpl extends MinimalEObjectImpl.Container implements EntityDependenceCondition
{
  /**
   * The default value of the '{@link #getEntity1() <em>Entity1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity1()
   * @generated
   * @ordered
   */
  protected static final String ENTITY1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEntity1() <em>Entity1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity1()
   * @generated
   * @ordered
   */
  protected String entity1 = ENTITY1_EDEFAULT;

  /**
   * The default value of the '{@link #getEntity2() <em>Entity2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity2()
   * @generated
   * @ordered
   */
  protected static final String ENTITY2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEntity2() <em>Entity2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity2()
   * @generated
   * @ordered
   */
  protected String entity2 = ENTITY2_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EntityDependenceConditionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return GoalPackage.Literals.ENTITY_DEPENDENCE_CONDITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEntity1()
  {
    return entity1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntity1(String newEntity1)
  {
    String oldEntity1 = entity1;
    entity1 = newEntity1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY1, oldEntity1, entity1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEntity2()
  {
    return entity2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntity2(String newEntity2)
  {
    String oldEntity2 = entity2;
    entity2 = newEntity2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY2, oldEntity2, entity2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY1:
        return getEntity1();
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY2:
        return getEntity2();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY1:
        setEntity1((String)newValue);
        return;
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY2:
        setEntity2((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY1:
        setEntity1(ENTITY1_EDEFAULT);
        return;
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY2:
        setEntity2(ENTITY2_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY1:
        return ENTITY1_EDEFAULT == null ? entity1 != null : !ENTITY1_EDEFAULT.equals(entity1);
      case GoalPackage.ENTITY_DEPENDENCE_CONDITION__ENTITY2:
        return ENTITY2_EDEFAULT == null ? entity2 != null : !ENTITY2_EDEFAULT.equals(entity2);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (entity1: ");
    result.append(entity1);
    result.append(", entity2: ");
    result.append(entity2);
    result.append(')');
    return result.toString();
  }

} //EntityDependenceConditionImpl
