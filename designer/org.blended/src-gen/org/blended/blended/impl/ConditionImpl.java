/**
 */
package org.blended.blended.impl;

import java.util.Collection;

import org.blended.blended.AttributeAchieveCondition;
import org.blended.blended.AttributeDependenceCondition;
import org.blended.blended.AttributeInvariantCondition;
import org.blended.blended.BlendedPackage;
import org.blended.blended.Condition;
import org.blended.blended.EntityDependenceCondition;
import org.blended.blended.EntityInvariantCondition;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.blended.impl.ConditionImpl#getEntityAchieveConditions <em>Entity Achieve Conditions</em>}</li>
 *   <li>{@link org.blended.blended.impl.ConditionImpl#getEntityInvariantConditions <em>Entity Invariant Conditions</em>}</li>
 *   <li>{@link org.blended.blended.impl.ConditionImpl#getEntityDependenceConditions <em>Entity Dependence Conditions</em>}</li>
 *   <li>{@link org.blended.blended.impl.ConditionImpl#getAttributeAchieveConditions <em>Attribute Achieve Conditions</em>}</li>
 *   <li>{@link org.blended.blended.impl.ConditionImpl#getAttributeInvariantConditions <em>Attribute Invariant Conditions</em>}</li>
 *   <li>{@link org.blended.blended.impl.ConditionImpl#getAttributeDependenceConditions <em>Attribute Dependence Conditions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConditionImpl extends BlendedModelImpl implements Condition
{
  /**
   * The cached value of the '{@link #getEntityAchieveConditions() <em>Entity Achieve Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityAchieveConditions()
   * @generated
   * @ordered
   */
  protected EList<EObject> entityAchieveConditions;

  /**
   * The cached value of the '{@link #getEntityInvariantConditions() <em>Entity Invariant Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityInvariantConditions()
   * @generated
   * @ordered
   */
  protected EList<EntityInvariantCondition> entityInvariantConditions;

  /**
   * The cached value of the '{@link #getEntityDependenceConditions() <em>Entity Dependence Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityDependenceConditions()
   * @generated
   * @ordered
   */
  protected EList<EntityDependenceCondition> entityDependenceConditions;

  /**
   * The cached value of the '{@link #getAttributeAchieveConditions() <em>Attribute Achieve Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeAchieveConditions()
   * @generated
   * @ordered
   */
  protected EList<AttributeAchieveCondition> attributeAchieveConditions;

  /**
   * The cached value of the '{@link #getAttributeInvariantConditions() <em>Attribute Invariant Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeInvariantConditions()
   * @generated
   * @ordered
   */
  protected EList<AttributeInvariantCondition> attributeInvariantConditions;

  /**
   * The cached value of the '{@link #getAttributeDependenceConditions() <em>Attribute Dependence Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeDependenceConditions()
   * @generated
   * @ordered
   */
  protected EList<AttributeDependenceCondition> attributeDependenceConditions;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConditionImpl()
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
    return BlendedPackage.Literals.CONDITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getEntityAchieveConditions()
  {
    if (entityAchieveConditions == null)
    {
      entityAchieveConditions = new EObjectContainmentEList<EObject>(EObject.class, this, BlendedPackage.CONDITION__ENTITY_ACHIEVE_CONDITIONS);
    }
    return entityAchieveConditions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EntityInvariantCondition> getEntityInvariantConditions()
  {
    if (entityInvariantConditions == null)
    {
      entityInvariantConditions = new EObjectContainmentEList<EntityInvariantCondition>(EntityInvariantCondition.class, this, BlendedPackage.CONDITION__ENTITY_INVARIANT_CONDITIONS);
    }
    return entityInvariantConditions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EntityDependenceCondition> getEntityDependenceConditions()
  {
    if (entityDependenceConditions == null)
    {
      entityDependenceConditions = new EObjectContainmentEList<EntityDependenceCondition>(EntityDependenceCondition.class, this, BlendedPackage.CONDITION__ENTITY_DEPENDENCE_CONDITIONS);
    }
    return entityDependenceConditions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AttributeAchieveCondition> getAttributeAchieveConditions()
  {
    if (attributeAchieveConditions == null)
    {
      attributeAchieveConditions = new EObjectContainmentEList<AttributeAchieveCondition>(AttributeAchieveCondition.class, this, BlendedPackage.CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS);
    }
    return attributeAchieveConditions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AttributeInvariantCondition> getAttributeInvariantConditions()
  {
    if (attributeInvariantConditions == null)
    {
      attributeInvariantConditions = new EObjectContainmentEList<AttributeInvariantCondition>(AttributeInvariantCondition.class, this, BlendedPackage.CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS);
    }
    return attributeInvariantConditions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AttributeDependenceCondition> getAttributeDependenceConditions()
  {
    if (attributeDependenceConditions == null)
    {
      attributeDependenceConditions = new EObjectContainmentEList<AttributeDependenceCondition>(AttributeDependenceCondition.class, this, BlendedPackage.CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS);
    }
    return attributeDependenceConditions;
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
      case BlendedPackage.CONDITION__ENTITY_ACHIEVE_CONDITIONS:
        return ((InternalEList<?>)getEntityAchieveConditions()).basicRemove(otherEnd, msgs);
      case BlendedPackage.CONDITION__ENTITY_INVARIANT_CONDITIONS:
        return ((InternalEList<?>)getEntityInvariantConditions()).basicRemove(otherEnd, msgs);
      case BlendedPackage.CONDITION__ENTITY_DEPENDENCE_CONDITIONS:
        return ((InternalEList<?>)getEntityDependenceConditions()).basicRemove(otherEnd, msgs);
      case BlendedPackage.CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS:
        return ((InternalEList<?>)getAttributeAchieveConditions()).basicRemove(otherEnd, msgs);
      case BlendedPackage.CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS:
        return ((InternalEList<?>)getAttributeInvariantConditions()).basicRemove(otherEnd, msgs);
      case BlendedPackage.CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS:
        return ((InternalEList<?>)getAttributeDependenceConditions()).basicRemove(otherEnd, msgs);
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
      case BlendedPackage.CONDITION__ENTITY_ACHIEVE_CONDITIONS:
        return getEntityAchieveConditions();
      case BlendedPackage.CONDITION__ENTITY_INVARIANT_CONDITIONS:
        return getEntityInvariantConditions();
      case BlendedPackage.CONDITION__ENTITY_DEPENDENCE_CONDITIONS:
        return getEntityDependenceConditions();
      case BlendedPackage.CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS:
        return getAttributeAchieveConditions();
      case BlendedPackage.CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS:
        return getAttributeInvariantConditions();
      case BlendedPackage.CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS:
        return getAttributeDependenceConditions();
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
      case BlendedPackage.CONDITION__ENTITY_ACHIEVE_CONDITIONS:
        getEntityAchieveConditions().clear();
        getEntityAchieveConditions().addAll((Collection<? extends EObject>)newValue);
        return;
      case BlendedPackage.CONDITION__ENTITY_INVARIANT_CONDITIONS:
        getEntityInvariantConditions().clear();
        getEntityInvariantConditions().addAll((Collection<? extends EntityInvariantCondition>)newValue);
        return;
      case BlendedPackage.CONDITION__ENTITY_DEPENDENCE_CONDITIONS:
        getEntityDependenceConditions().clear();
        getEntityDependenceConditions().addAll((Collection<? extends EntityDependenceCondition>)newValue);
        return;
      case BlendedPackage.CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS:
        getAttributeAchieveConditions().clear();
        getAttributeAchieveConditions().addAll((Collection<? extends AttributeAchieveCondition>)newValue);
        return;
      case BlendedPackage.CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS:
        getAttributeInvariantConditions().clear();
        getAttributeInvariantConditions().addAll((Collection<? extends AttributeInvariantCondition>)newValue);
        return;
      case BlendedPackage.CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS:
        getAttributeDependenceConditions().clear();
        getAttributeDependenceConditions().addAll((Collection<? extends AttributeDependenceCondition>)newValue);
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
      case BlendedPackage.CONDITION__ENTITY_ACHIEVE_CONDITIONS:
        getEntityAchieveConditions().clear();
        return;
      case BlendedPackage.CONDITION__ENTITY_INVARIANT_CONDITIONS:
        getEntityInvariantConditions().clear();
        return;
      case BlendedPackage.CONDITION__ENTITY_DEPENDENCE_CONDITIONS:
        getEntityDependenceConditions().clear();
        return;
      case BlendedPackage.CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS:
        getAttributeAchieveConditions().clear();
        return;
      case BlendedPackage.CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS:
        getAttributeInvariantConditions().clear();
        return;
      case BlendedPackage.CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS:
        getAttributeDependenceConditions().clear();
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
      case BlendedPackage.CONDITION__ENTITY_ACHIEVE_CONDITIONS:
        return entityAchieveConditions != null && !entityAchieveConditions.isEmpty();
      case BlendedPackage.CONDITION__ENTITY_INVARIANT_CONDITIONS:
        return entityInvariantConditions != null && !entityInvariantConditions.isEmpty();
      case BlendedPackage.CONDITION__ENTITY_DEPENDENCE_CONDITIONS:
        return entityDependenceConditions != null && !entityDependenceConditions.isEmpty();
      case BlendedPackage.CONDITION__ATTRIBUTE_ACHIEVE_CONDITIONS:
        return attributeAchieveConditions != null && !attributeAchieveConditions.isEmpty();
      case BlendedPackage.CONDITION__ATTRIBUTE_INVARIANT_CONDITIONS:
        return attributeInvariantConditions != null && !attributeInvariantConditions.isEmpty();
      case BlendedPackage.CONDITION__ATTRIBUTE_DEPENDENCE_CONDITIONS:
        return attributeDependenceConditions != null && !attributeDependenceConditions.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ConditionImpl
