package pt.ist.socialsoftware.blendedworkflow.engines.domain.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jvstm.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

public class DataModelTest {

	private DataModelInstance dataModelInstance;


	@Before
	public void setUp() throws BlendedWorkflowException {
		Bootstrap.initTestDB();
	}
	
	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void test2() throws BlendedWorkflowException {
		//setUpData
		Transaction.begin();
		dataModelInstance = new DataModelInstance();
		
		//Types
		Entity A = new Entity(dataModelInstance, "A");
		Entity B = new Entity(dataModelInstance, "B");
		Entity C = new Entity(dataModelInstance, "C");
		Entity D = new Entity(dataModelInstance, "D");
		Entity E = new Entity(dataModelInstance, "E");
		Entity F = new Entity(dataModelInstance, "F");
		Entity X = new Entity(dataModelInstance, "X");
		Relation AB = new Relation(dataModelInstance, "AB", A, B, Cardinality.ONE, Cardinality.MANY, false, true);
		Relation AC = new Relation(dataModelInstance, "AC", A, C, Cardinality.ONE, Cardinality.ONE, true, false);
		Relation AD = new Relation(dataModelInstance, "AD", A, D, Cardinality.ONE, Cardinality.ONE, true, false);
		Relation DE = new Relation(dataModelInstance, "DE", D, E, Cardinality.ONE, Cardinality.ONE, true, false);
		Relation EF = new Relation(dataModelInstance, "EF", E, F, Cardinality.ONE, Cardinality.ONE, true, false);
		Relation EC = new Relation(dataModelInstance, "EC", E, C, Cardinality.ONE, Cardinality.ONE, true, false);

		//Instances
		EntityInstance A1 = new EntityInstance(dataModelInstance, A);
		EntityInstance B1 = new EntityInstance(dataModelInstance, B);
		EntityInstance C1 = new EntityInstance(dataModelInstance, C);
		EntityInstance D1 = new EntityInstance(dataModelInstance, D);
		EntityInstance E1 = new EntityInstance(dataModelInstance, E);
		EntityInstance F1 = new EntityInstance(dataModelInstance, F);
		EntityInstance F2 = new EntityInstance(dataModelInstance, F);
		EntityInstance X1 = new EntityInstance(dataModelInstance, X);
		new RelationInstance(AB, A1, B1, A1.getNewRelationInstanceID());
		new RelationInstance(AC, A1, C1, A1.getNewRelationInstanceID());
		new RelationInstance(AD, A1, D1, A1.getNewRelationInstanceID());
		new RelationInstance(DE, D1, E1, D1.getNewRelationInstanceID());
		new RelationInstance(EF, E1, F1, E1.getNewRelationInstanceID());
		new RelationInstance(EF, E1, F2, E1.getNewRelationInstanceID());
		new RelationInstance(EC, E1, C1, E1.getNewRelationInstanceID());
		Transaction.commit();

		Transaction.begin();
		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(A1, F1, new ArrayList<EntityInstance>());
		assertEquals(F1, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=A1 & target=F1");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());
		
		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(A1, F2, new ArrayList<EntityInstance>());
		assertEquals(F2, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=A1 & target=F2");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());
		
		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(B1, F1, new ArrayList<EntityInstance>());
		assertEquals(F1, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=B1 & target=F");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());

		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(C1, F1, new ArrayList<EntityInstance>());
		assertEquals(F1, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=C1 & target=F1");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());
		
		
		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(D1, F1, new ArrayList<EntityInstance>());
		assertEquals(F1, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=D1 & target=F1");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());
		
		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(E1, F1, new ArrayList<EntityInstance>());
		assertEquals(F1, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=E1 & target=F1");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());
		
		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(F1, F1, new ArrayList<EntityInstance>());
		assertEquals(F1, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=F1 & target=F1");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());

		dataModelInstance.clearSearchEntityInstanceVariables();
		dataModelInstance.searchEntityInstance(A1, X1, new ArrayList<EntityInstance>());
		assertEquals(null, dataModelInstance.getFoundEntityInstance());
//		System.out.println("SearchEntityInstance: init=A1 & target=X");
//		for (EntityInstance e1 : dataModelInstance.getFoundEntityInstancePath())
//			System.out.println(e1.getID());

		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(A, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(F, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=A & target=F");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());
//		for (Relation r : dataModelInstance.getFoundRelationPath())
//			System.out.println(r.getName());
		
		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(B, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(F, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=B & target=F");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());
		
		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(C, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(F, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=C & target=F");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());
		
		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(D, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(F, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=D & target=F");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());
		
		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(E, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(F, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=E & target=F");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());
		
		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(F, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(F, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=F & target=F");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());
		
		dataModelInstance.clearSearchEntityVariables();
		dataModelInstance.searchEntity(X, F, null, new ArrayList<Entity>(), new ArrayList<Relation>());
		assertEquals(null, dataModelInstance.getFoundEntity());
//		System.out.println("SearchEntity: init=A & target=X");
//		for (Entity e : dataModelInstance.getFoundEntityPath())
//			System.out.println(e.getName());

		Transaction.commit();
	}
	
}
