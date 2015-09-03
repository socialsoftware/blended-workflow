package org.blended.activity.terminal;

import org.blended.activity.ActivityStandaloneSetup;
import org.blended.activity.activity.ActivityModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.google.inject.Injector;

abstract public class ManageSplitTest {
	//protected static ManageSplit manager;
	protected ActivityModel model;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	//manager = new ManageSplit();
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    protected static CommandSplit createParameters(String name, String type, String goal, String sucessConditions) {
    	CommandSplit command = new CommandSplit();
    	command.name = name;
    	command.type = type;
    	command.goal = goal;
    	command.successConditions = sucessConditions;
    	return command;
    }
    
    protected static ActivityModel loadXtextResouce(String name) throws Exception {
    	ActivityStandaloneSetup setup = new ActivityStandaloneSetup();
    	Injector injector = setup.createInjectorAndDoEMFRegistration();
    	ResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    	Resource resource = resourceSet.getResource(URI.createFileURI("test/org/blended/activity/terminal/" + name), true);
    	ActivityModel model = (ActivityModel)resource.getContents().get(0);
    	return model;
 }
    
   
}
