package pt.ist.socialsoftware.blendedworkflow.engines.domain.test;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.junit.After;
import org.junit.Before;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.core.WriteOnReadError;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public abstract class AbstractDomainTest {

    @Before
    public void setUp() throws Exception {
        try {
            FenixFramework.getTransactionManager().begin(false);
            new BlendedWorkflow();
            populate4DomainTest();
        } catch (WriteOnReadError | NotSupportedException | SystemException e1) {
            e1.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            FenixFramework.getTransactionManager().rollback();
        } catch (IllegalStateException | SecurityException | SystemException e) {
            e.printStackTrace();
        }
    }

    protected abstract void populate4DomainTest()
            throws BWException;

}
