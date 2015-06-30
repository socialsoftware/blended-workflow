/**
 */
package org.blended.data.data.impl;

import org.blended.data.data.Association;
import org.blended.data.data.DataPackage;
import org.blended.data.data.Entity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.blended.data.data.impl.AssociationImpl#getEntity1 <em>Entity1</em>}</li>
 *   <li>{@link org.blended.data.data.impl.AssociationImpl#getName1 <em>Name1</em>}</li>
 *   <li>{@link org.blended.data.data.impl.AssociationImpl#getCardinality1 <em>Cardinality1</em>}</li>
 *   <li>{@link org.blended.data.data.impl.AssociationImpl#getEntity2 <em>Entity2</em>}</li>
 *   <li>{@link org.blended.data.data.impl.AssociationImpl#getName2 <em>Name2</em>}</li>
 *   <li>{@link org.blended.data.data.impl.AssociationImpl#getCardinality2 <em>Cardinality2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssociationImpl extends MinimalEObjectImpl.Container implements Association
{
  /**
   * The cached value of the '{@link #getEntity1() <em>Entity1</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity1()
   * @generated
   * @ordered
   */
  protected Entity entity1;

  /**
   * The default value of the '{@link #getName1() <em>Name1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName1()
   * @generated
   * @ordered
   */
  protected static final String NAME1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName1() <em>Name1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName1()
   * @generated
   * @ordered
   */
  protected String name1 = NAME1_EDEFAULT;

  /**
   * The default value of the '{@link #getCardinality1() <em>Cardinality1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCardinality1()
   * @generated
   * @ordered
   */
  protected static final String CARDINALITY1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCardinality1() <em>Cardinality1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCardinality1()
   * @generated
   * @ordered
   */
  protected String cardinality1 = CARDINALITY1_EDEFAULT;

  /**
   * The cached value of the '{@link #getEntity2() <em>Entity2</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity2()
   * @generated
   * @ordered
   */
  protected Entity entity2;

  /**
   * The default value of the '{@link #getName2() <em>Name2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName2()
   * @generated
   * @ordered
   */
  protected static final String NAME2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName2() <em>Name2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName2()
   * @generated
   * @ordered
   */
  protected String name2 = NAME2_EDEFAULT;

  /**
   * The default value of the '{@link #getCardinality2() <em>Cardinality2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCardinality2()
   * @generated
   * @ordered
   */
  protected static final String CARDINALITY2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCardinality2() <em>Cardinality2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCardinality2()
   * @generated
   * @ordered
   */
  protected String cardinality2 = CARDINALITY2_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AssociationImpl()
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
    return DataPackage.Literals.ASSOCIATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity getEntity1()
  {
    if (entity1 != null && entity1.eIsProxy())
    {
      InternalEObject oldEntity1 = (InternalEObject)entity1;
      entity1 = (Entity)eResolveProxy(oldEntity1);
      if (entity1 != oldEntity1)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataPackage.ASSOCIATION__ENTITY1, oldEntity1, entity1));
      }
    }
    return entity1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity basicGetEntity1()
  {
    return entity1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntity1(Entity newEntity1)
  {
    Entity oldEntity1 = entity1;
    entity1 = newEntity1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.ASSOCIATION__ENTITY1, oldEntity1, entity1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName1()
  {
    return name1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName1(String newName1)
  {
    String oldName1 = name1;
    name1 = newName1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.ASSOCIATION__NAME1, oldName1, name1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCardinality1()
  {
    return cardinality1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCardinality1(String newCardinality1)
  {
    String oldCardinality1 = cardinality1;
    cardinality1 = newCardinality1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.ASSOCIATION__CARDINALITY1, oldCardinality1, cardinality1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity getEntity2()
  {
    if (entity2 != null && entity2.eIsProxy())
    {
      InternalEObject oldEntity2 = (InternalEObject)entity2;
      entity2 = (Entity)eResolveProxy(oldEntity2);
      if (entity2 != oldEntity2)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataPackage.ASSOCIATION__ENTITY2, oldEntity2, entity2));
      }
    }
    return entity2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity basicGetEntity2()
  {
    return entity2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntity2(Entity newEntity2)
  {
    Entity oldEntity2 = entity2;
    entity2 = newEntity2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.ASSOCIATION__ENTITY2, oldEntity2, entity2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName2()
  {
    return name2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName2(String newName2)
  {
    String oldName2 = name2;
    name2 = newName2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.ASSOCIATION__NAME2, oldName2, name2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCardinality2()
  {
    return cardinality2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCardinality2(String newCardinality2)
  {
    String oldCardinality2 = cardinality2;
    cardinality2 = newCardinality2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.ASSOCIATION__CARDINALITY2, oldCardinality2, cardinality2));
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
      case DataPackage.ASSOCIATION__ENTITY1:
        if (resolve) return getEntity1();
        return basicGetEntity1();
      case DataPackage.ASSOCIATION__NAME1:
        return getName1();
      case DataPackage.ASSOCIATION__CARDINALITY1:
        return getCardinality1();
      case DataPackage.ASSOCIATION__ENTITY2:
        if (resolve) return getEntity2();
        return basicGetEntity2();
      case DataPackage.ASSOCIATION__NAME2:
        return getName2();
      case DataPackage.ASSOCIATION__CARDINALITY2:
        return getCardinality2();
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
      case DataPackage.ASSOCIATION__ENTITY1:
        setEntity1((Entity)newValue);
        return;
      case DataPackage.ASSOCIATION__NAME1:
        setName1((String)newValue);
        return;
      case DataPackage.ASSOCIATION__CARDINALITY1:
        setCardinality1((String)newValue);
        return;
      case DataPackage.ASSOCIATION__ENTITY2:
        setEntity2((Entity)newValue);
        return;
      case DataPackage.ASSOCIATION__NAME2:
        setName2((String)newValue);
        return;
      case DataPackage.ASSOCIATION__CARDINALITY2:
        setCardinality2((String)newValue);
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
      case DataPackage.ASSOCIATION__ENTITY1:
        setEntity1((Entity)null);
        return;
      case DataPackage.ASSOCIATION__NAME1:
        setName1(NAME1_EDEFAULT);
        return;
      case DataPackage.ASSOCIATION__CARDINALITY1:
        setCardinality1(CARDINALITY1_EDEFAULT);
        return;
      case DataPackage.ASSOCIATION__ENTITY2:
        setEntity2((Entity)null);
        return;
      case DataPackage.ASSOCIATION__NAME2:
        setName2(NAME2_EDEFAULT);
        return;
      case DataPackage.ASSOCIATION__CARDINALITY2:
        setCardinality2(CARDINALITY2_EDEFAULT);
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
      case DataPackage.ASSOCIATION__ENTITY1:
        return entity1 != null;
      case DataPackage.ASSOCIATION__NAME1:
        return NAME1_EDEFAULT == null ? name1 != null : !NAME1_EDEFAULT.equals(name1);
      case DataPackage.ASSOCIATION__CARDINALITY1:
        return CARDINALITY1_EDEFAULT == null ? cardinality1 != null : !CARDINALITY1_EDEFAULT.equals(cardinality1);
      case DataPackage.ASSOCIATION__ENTITY2:
        return entity2 != null;
      case DataPackage.ASSOCIATION__NAME2:
        return NAME2_EDEFAULT == null ? name2 != null : !NAME2_EDEFAULT.equals(name2);
      case DataPackage.ASSOCIATION__CARDINALITY2:
        return CARDINALITY2_EDEFAULT == null ? cardinality2 != null : !CARDINALITY2_EDEFAULT.equals(cardinality2);
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
    result.append(" (name1: ");
    result.append(name1);
    result.append(", cardinality1: ");
    result.append(cardinality1);
    result.append(", name2: ");
    result.append(name2);
    result.append(", cardinality2: ");
    result.append(cardinality2);
    result.append(')');
    return result.toString();
  }

} //AssociationImpl
