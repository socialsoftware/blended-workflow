/**
 */
package org.blended.goal.goal.impl;

import org.blended.blended.BlendedPackage;

import org.blended.goal.goal.GoalFactory;
import org.blended.goal.goal.GoalModel;
import org.blended.goal.goal.GoalPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GoalPackageImpl extends EPackageImpl implements GoalPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass goalModelEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.blended.goal.goal.GoalPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private GoalPackageImpl()
  {
    super(eNS_URI, GoalFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link GoalPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static GoalPackage init()
  {
    if (isInited) return (GoalPackage)EPackage.Registry.INSTANCE.getEPackage(GoalPackage.eNS_URI);

    // Obtain or create and register package
    GoalPackageImpl theGoalPackage = (GoalPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GoalPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GoalPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    BlendedPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theGoalPackage.createPackageContents();

    // Initialize created meta-data
    theGoalPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theGoalPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(GoalPackage.eNS_URI, theGoalPackage);
    return theGoalPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGoalModel()
  {
    return goalModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGoalModel_Goals()
  {
    return (EReference)goalModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GoalFactory getGoalFactory()
  {
    return (GoalFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    goalModelEClass = createEClass(GOAL_MODEL);
    createEReference(goalModelEClass, GOAL_MODEL__GOALS);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    BlendedPackage theBlendedPackage = (BlendedPackage)EPackage.Registry.INSTANCE.getEPackage(BlendedPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(goalModelEClass, GoalModel.class, "GoalModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGoalModel_Goals(), theBlendedPackage.getGoal(), null, "goals", null, 0, -1, GoalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //GoalPackageImpl
