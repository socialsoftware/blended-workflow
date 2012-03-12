package pt.ist.socialsoftware.blendedworkflow.shared;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Main {  
  
  public static void main(String[] args) throws BlendedWorkflowException {  
    Bootstrap.init();
    Transaction.begin();
	BlendedWorkflow.getInstance().addBwSpecifications(new BWSpecification("run"));
    System.out.println("Run Sucesso: Added " + BlendedWorkflow.getInstance().getBwSpecificationsCount() + 
    		" specification called " + BlendedWorkflow.getInstance().getBWSpecification("run").getName());
    Transaction.commit();
    Bootstrap.clean();
  } 
}