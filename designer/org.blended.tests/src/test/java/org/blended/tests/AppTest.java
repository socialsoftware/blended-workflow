package org.blended.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.blended.data.data.DataFactory;
import org.blended.data.data.Entity;
import org.blended.data.validation.DataValidator;

public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	DataFactory df = DataFactory.eINSTANCE;
    	Entity e = df.createEntity();
    	DataValidator a = new DataValidator();
    	//String b = a.checkTest(e);
        assertEquals("helloo", "helloo");
    }
    
}
