/**
 */
package org.blended.blended.impl;

import java.util.Collection;

import org.blended.blended.BlendedPackage;
import org.blended.blended.Goal;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.impl.GoalImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.blended.blended.impl.GoalImpl#getSucessCondition <em>Sucess Condition</em>}</li>
 *   <li>{@link org.blended.blended.impl.GoalImpl#getActivationCondition <em>Activation Condition</em>}</li>
 *   <li>{@link org.blended.blended.impl.GoalImpl#getInvariantConditions <em>Invariant Conditions</em>}</li>
 *   <li>{@link org.blended.blended.impl.GoalImpl#getChildrenGoals <em>Children Goals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GoalImpl extends BlendedModelImpl implements Goal
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getSucessCondition() <em>Sucess Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSucessCondition()
   * @generated
   * @ordered
   */
  protected EObject sucessCondition;

  /**
   * The cached value of the '{@link #getActivationCondition() <em>Activation Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActivationCondition()
   * @generated
   * @ordered
   */
  protected EObject activationCondition;

  /**
   * The cached value of the '{@link #getInvariantConditions() <em>Invariant Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInvariantConditions()
   * @generated
   * @ordered
   */
  protected EList<EObject> invariantConditions;

  /**
   * The cached value of the '{@link #getChildrenGoals() <em>Children Goals</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildrenGoals()
   * @generated
   * @ordered
   */
  protected EList<Goal> childrenGoals;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GoalImpl()
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
    return BlendedPackage.Literals.GOAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BlendedPackage.GOAL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getSucessCondition()
  {
    return sucessCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSucessCondition(EObject newSucessCondition, NotificationChain msgs)
  {
    EObject oldSucessCondition = sucessCondition;
    sucessCondition = newSucessCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BlendedPackage.GOAL__SUCESS_CONDITION, oldSucessCondition, newSucessCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSucessCondition(EObject newSucessCondition)
  {
    if (newSucessCondition != sucessCondition)
    {
      NotificationChain msgs = null;
      if (sucessCondition != null)
        msgs = ((InternalEObject)sucessCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BlendedPackage.GOAL__SUCESS_CONDITION, null, msgs);
      if (newSucessCondition != null)
        msgs = ((InternalEObject)newSucessCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BlendedPackage.GOAL__SUCESS_CONDITION, null, msgs);
      msgs = basicSetSucessCondition(newSucessCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BlendedPackage.GOAL__SUCESS_CONDITION, newSucessCondition, newSucessCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getActivationCondition()
  {
    return activationCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetActivationCondition(EObject newActivationCondition, NotificationChain msgs)
  {
    EObject oldActivationCondition = activationCondition;
    activationCondition = newActivationCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BlendedPackage.GOAL__ACTIVATION_CONDITION, oldActivationCondition, newActivationCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActivationCondition(EObject newActivationCondition)
  {
    if (newActivationCondition != activationCondition)
    {
      NotificationChain msgs = null;
      if (activationCondition != null)
        msgs = ((InternalEObject)activationCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BlendedPackage.GOAL__ACTIVATION_CONDITION, null, msgs);
      if (newActivationCondition != null)
        msgs = ((InternalEObject)newActivationCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BlendedPackage.GOAL__ACTIVATION_CONDITION, null, msgs);
      msgs = basicSetActivationCondition(newActivationCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BlendedPackage.GOAL__ACTIVATION_CONDITION, newActivationCondition, newActivationCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getInvariantConditions()
  {
    if (invariantConditions == null)
    {
      invariantConditions = new EObjectContainmentEList<EObject>(EObject.class, this, BlendedPackage.GOAL__INVARIANT_CONDITIONS);
    }
    return invariantConditions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Goal> getChildrenGoals()
  {
    if (childrenGoals == null)
    {
      childrenGoals = new EObjectResolvingEList<Goal>(Goal.class, this, BlendedPackage.GOAL__CHILDREN_GOALS);
    }
    return childrenGoals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case BlendedPackage.GOAL__SUCESS_CONDITION:
        return basicSetSucessCondition(null, msgs);
      case BlendedPackage.GOAL__ACTIVATION_CONDITION:
        return basicSetActivationCondition(null, msgs);
      case BlendedPackage.GOAL__INVARIANT_CONDITIONS:
        return ((InternalEList<?>)getInvariantConditions()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case BlendedPackage.GOAL__NAME:
        return getName();
      case BlendedPackage.GOAL__SUCESS_CONDITION:
        return getSucessCondition();
      case BlendedPackage.GOAL__ACTIVATION_CONDITION:
        return getActivationCondition();
      case BlendedPackage.GOAL__INVARIANT_CONDITIONS:
        return getInvariantConditions();
      case BlendedPackage.GOAL__CHILDREN_GOALS:
        return getChildrenGoals();
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
      case BlendedPackage.GOAL__NAME:
        setName((String)newValue);
        return;
      case BlendedPackage.GOAL__SUCESS_CONDITION:
        setSucessCondition((EObject)newValue);
        return;
      case BlendedPackage.GOAL__ACTIVATION_CONDITION:
        setActivationCondition((EObject)newValue);
        return;
      case BlendedPackage.GOAL__INVARIANT_CONDITIONS:
        getInvariantConditions().clear();
        getInvariantConditions().addAll((Collection<? extends EObject>)newValue);
        return;
      case BlendedPackage.GOAL__CHILDREN_GOALS:
        getChildrenGoals().clear();
        getChildrenGoals().addAll((Collection<? extends Goal>)newValue);
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
      case BlendedPackage.GOAL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BlendedPackage.GOAL__SUCESS_CONDITION:
        setSucessCondition((EObject)null);
        return;
      case BlendedPackage.GOAL__ACTIVATION_CONDITION:
        setActivationCondition((EObject)null);
        return;
      case BlendedPackage.GOAL__INVARIANT_CONDITIONS:
        getInvariantConditions().clear();
        return;
      case BlendedPackage.GOAL__CHILDREN_GOALS:
        getChildrenGoals().clear();
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
      case BlendedPackage.GOAL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BlendedPackage.GOAL__SUCESS_CONDITION:
        return sucessCondition != null;
      case BlendedPackage.GOAL__ACTIVATION_CONDITION:
        return activationCondition != null;
      case BlendedPackage.GOAL__INVARIANT_CONDITIONS:
        return invariantConditions != null && !invariantConditions.isEmpty();
      case BlendedPackage.GOAL__CHILDREN_GOALS:
        return childrenGoals != null && !childrenGoals.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //GoalImpl
