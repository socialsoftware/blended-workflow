package pt.ist.socialsoftware.blendedworkflow.servicelayer;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.domain.*;
import junit.framework.TestCase;

public abstract class TestCaseService extends TestCase {
    static {
    	if(FenixFramework.getConfig()==null) {
    		FenixFramework.initialize(new Config() {{
    			dbAlias="test-db";
    			domainModelPath="src/main/dml/blendedworkflow.dml";
    			repositoryType=RepositoryType.BERKELEYDB;
    			rootClass=BlendedWorkflow.class;
    		}});
    	}
    }
    
    protected TestCaseService(String msg) {
    	super(msg);
    }
    
    protected TestCaseService() {
    	super();
    }
        
    
    protected void cleanBlendedWorkflow() {
    	boolean committed = false;
    	try {
    		Transaction.begin();
    		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
    		Set<BWSpecification> allBWSpecifications = blendedWorkflow.getBwSpecificationSet();
    		allBWSpecifications.clear();
    		Transaction.commit();
    		committed = true;
    	} finally {
    		if (!committed) {
    			Transaction.abort();
    			fail("Could not clean phonebook");
    		}
    	}
    }
    
//    protected boolean checkBWInstance(String BWInstanceID) {
//    	boolean committed = false;
//    	boolean res;
//    	
//    	try {
//    		Transaction.begin();
//    		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//    		res = phonebook.hasContact(contactName);
//    		Transaction.commit();
//    		committed = true;
//    		return res;
//    	} finally {
//    		if (!committed) {
//    			Transaction.abort();
//    			fail("Could not check phonebook");
//    		}
//    	}
//    }
    
    protected BWSpecification addBWSpecification(String bwSpecificationID, String name) {
    	boolean committed = false;
    	try {
    		Transaction.begin();
    		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//    		try {
    			blendedWorkflow.addBwSpecification(new BWSpecification(bwSpecificationID, name));
//    		} catch (NameAlreadyExistsException nae) {
//    		}
    		Transaction.commit();
    		committed = true;
    	} finally {
    		if (!committed) {
    			Transaction.abort();
    			fail("Could not add new bw specification: " + name);
    		}
    	}
    }
    
    protected BWInstance addBWInstance(BWSpecification bwSpecification, String bwInstanceID, String name) {
    	boolean committed = false;
    	try {
    		Transaction.begin();
    		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//    		try {
    			blendedWorkflow.addBwSpecification(new BWSpecification(bwSpecificationID, name));
//    		} catch (NameAlreadyExistsException nae) {
//    		}
    		Transaction.commit();
    		committed = true;
    	} finally {
    		if (!committed) {
    			Transaction.abort();
    			fail("Could not add new bw specification: " + name);
    		}
    	}
    }

}
