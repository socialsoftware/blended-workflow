/**
 */
package org.blended.data.data.impl;

import java.util.Collection;

import org.blended.data.data.Association;
import org.blended.data.data.Constraint;
import org.blended.data.data.DataModel;
import org.blended.data.data.DataPackage;
import org.blended.data.data.Entity;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.data.data.impl.DataModelImpl#getEntities <em>Entities</em>}</li>
 *   <li>{@link org.blended.data.data.impl.DataModelImpl#getAssociatios <em>Associatios</em>}</li>
 *   <li>{@link org.blended.data.data.impl.DataModelImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataModelImpl extends MinimalEObjectImpl.Container implements DataModel
{
  /**
   * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntities()
   * @generated
   * @ordered
   */
  protected EList<Entity> entities;

  /**
   * The cached value of the '{@link #getAssociatios() <em>Associatios</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssociatios()
   * @generated
   * @ordered
   */
  protected EList<Association> associatios;

  /**
   * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraint()
   * @generated
   * @ordered
   */
  protected EList<Constraint> constraint;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DataModelImpl()
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
    return DataPackage.Literals.DATA_MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Entity> getEntities()
  {
    if (entities == null)
    {
      entities = new EObjectContainmentEList<Entity>(Entity.class, this, DataPackage.DATA_MODEL__ENTITIES);
    }
    return entities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Association> getAssociatios()
  {
    if (associatios == null)
    {
      associatios = new EObjectContainmentEList<Association>(Association.class, this, DataPackage.DATA_MODEL__ASSOCIATIOS);
    }
    return associatios;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Constraint> getConstraint()
  {
    if (constraint == null)
    {
      constraint = new EObjectContainmentEList<Constraint>(Constraint.class, this, DataPackage.DATA_MODEL__CONSTRAINT);
    }
    return constraint;
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
      case DataPackage.DATA_MODEL__ENTITIES:
        return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
      case DataPackage.DATA_MODEL__ASSOCIATIOS:
        return ((InternalEList<?>)getAssociatios()).basicRemove(otherEnd, msgs);
      case DataPackage.DATA_MODEL__CONSTRAINT:
        return ((InternalEList<?>)getConstraint()).basicRemove(otherEnd, msgs);
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
      case DataPackage.DATA_MODEL__ENTITIES:
        return getEntities();
      case DataPackage.DATA_MODEL__ASSOCIATIOS:
        return getAssociatios();
      case DataPackage.DATA_MODEL__CONSTRAINT:
        return getConstraint();
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
      case DataPackage.DATA_MODEL__ENTITIES:
        getEntities().clear();
        getEntities().addAll((Collection<? extends Entity>)newValue);
        return;
      case DataPackage.DATA_MODEL__ASSOCIATIOS:
        getAssociatios().clear();
        getAssociatios().addAll((Collection<? extends Association>)newValue);
        return;
      case DataPackage.DATA_MODEL__CONSTRAINT:
        getConstraint().clear();
        getConstraint().addAll((Collection<? extends Constraint>)newValue);
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
      case DataPackage.DATA_MODEL__ENTITIES:
        getEntities().clear();
        return;
      case DataPackage.DATA_MODEL__ASSOCIATIOS:
        getAssociatios().clear();
        return;
      case DataPackage.DATA_MODEL__CONSTRAINT:
        getConstraint().clear();
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
      case DataPackage.DATA_MODEL__ENTITIES:
        return entities != null && !entities.isEmpty();
      case DataPackage.DATA_MODEL__ASSOCIATIOS:
        return associatios != null && !associatios.isEmpty();
      case DataPackage.DATA_MODEL__CONSTRAINT:
        return constraint != null && !constraint.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //DataModelImpl
