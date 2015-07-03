/**
 */
package org.blended.condition.condition.impl;

import java.util.Collection;

import org.blended.condition.condition.AttributeAchieveCondition;
import org.blended.condition.condition.ConditionPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Achieve Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.condition.condition.impl.AttributeAchieveConditionImpl#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeAchieveConditionImpl extends MinimalEObjectImpl.Container implements AttributeAchieveCondition
{
  /**
   * The cached value of the '{@link #getConditions() <em>Conditions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConditions()
   * @generated
   * @ordered
   */
  protected EList<String> conditions;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeAchieveConditionImpl()
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
    return ConditionPackage.Literals.ATTRIBUTE_ACHIEVE_CONDITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getConditions()
  {
    if (conditions == null)
    {
      conditions = new EDataTypeEList<String>(String.class, this, ConditionPackage.ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS);
    }
    return conditions;
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
      case ConditionPackage.ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS:
        return getConditions();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ConditionPackage.ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS:
        getConditions().clear();
        getConditions().addAll((Collection<? extends String>)newValue);
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
      case ConditionPackage.ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS:
        getConditions().clear();
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
      case ConditionPackage.ATTRIBUTE_ACHIEVE_CONDITION__CONDITIONS:
        return conditions != null && !conditions.isEmpty();
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
    result.append(" (conditions: ");
    result.append(conditions);
    result.append(')');
    return result.toString();
  }

} //AttributeAchieveConditionImpl
