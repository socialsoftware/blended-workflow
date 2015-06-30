/**
 */
package org.blended.blended.impl;

import java.util.Collection;

import org.blended.blended.AttributeDependenceCondition;
import org.blended.blended.BlendedPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Dependence Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.impl.AttributeDependenceConditionImpl#getAttributes1 <em>Attributes1</em>}</li>
 *   <li>{@link org.blended.blended.impl.AttributeDependenceConditionImpl#getAttribute2 <em>Attribute2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeDependenceConditionImpl extends MinimalEObjectImpl.Container implements AttributeDependenceCondition
{
  /**
   * The cached value of the '{@link #getAttributes1() <em>Attributes1</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributes1()
   * @generated
   * @ordered
   */
  protected EList<String> attributes1;

  /**
   * The default value of the '{@link #getAttribute2() <em>Attribute2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttribute2()
   * @generated
   * @ordered
   */
  protected static final String ATTRIBUTE2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAttribute2() <em>Attribute2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttribute2()
   * @generated
   * @ordered
   */
  protected String attribute2 = ATTRIBUTE2_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeDependenceConditionImpl()
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
    return BlendedPackage.Literals.ATTRIBUTE_DEPENDENCE_CONDITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAttributes1()
  {
    if (attributes1 == null)
    {
      attributes1 = new EDataTypeEList<String>(String.class, this, BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1);
    }
    return attributes1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAttribute2()
  {
    return attribute2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttribute2(String newAttribute2)
  {
    String oldAttribute2 = attribute2;
    attribute2 = newAttribute2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2, oldAttribute2, attribute2));
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
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1:
        return getAttributes1();
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2:
        return getAttribute2();
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
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1:
        getAttributes1().clear();
        getAttributes1().addAll((Collection<? extends String>)newValue);
        return;
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2:
        setAttribute2((String)newValue);
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
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1:
        getAttributes1().clear();
        return;
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2:
        setAttribute2(ATTRIBUTE2_EDEFAULT);
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
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTES1:
        return attributes1 != null && !attributes1.isEmpty();
      case BlendedPackage.ATTRIBUTE_DEPENDENCE_CONDITION__ATTRIBUTE2:
        return ATTRIBUTE2_EDEFAULT == null ? attribute2 != null : !ATTRIBUTE2_EDEFAULT.equals(attribute2);
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
    result.append(" (attributes1: ");
    result.append(attributes1);
    result.append(", attribute2: ");
    result.append(attribute2);
    result.append(')');
    return result.toString();
  }

} //AttributeDependenceConditionImpl
