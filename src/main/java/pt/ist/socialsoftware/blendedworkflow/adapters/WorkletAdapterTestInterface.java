package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;

import jvstm.Transaction;

import org.apache.log4j.Logger;
import org.yawlfoundation.yawl.authentication.YExternalClient;
import org.yawlfoundation.yawl.elements.YAWLServiceReference;
import org.yawlfoundation.yawl.engine.YSpecificationID;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class WorkletAdapterTestInterface extends Application {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("bwServicelogger");
	private final Window main = new Window("Worklet Test");
	private WorkletAdapter wa;
	
	@Override
	public void init() {
		setMainWindow(main);
		
//		log.info("Worklet Test");
//		this.wa = new WorkletAdapter(WorkletAdapterTestInterface.this);
//		addlabel("WORKLET TEST:");
		
		// test load OK, create OK , evaluate Failded
//		Bootstrap.init();
//		Bootstrap.populate();
//		Transaction.begin();
//		log.info("WATI: Worklet Adapater" + BlendedWorkflow.getInstance().getWorkletAdapter());
//		Transaction.commit();
		// test load, create, evaluate
		
//		addlabel("Register Listener:");
//		Boolean regresult = this.wa.registerWorkletListener();
//		addlabel(regresult.toString());
		
//		main.addComponent(new Button("GetWorkitem", new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				addlabel("Evaluate Test");
//				Transaction.begin();
//				int workitems = BlendedWorkflow.getInstance().getWorkletAdapter().getWorkItems();
//				addlabel("Existem " + workitems + " workitems.");
//				Transaction.commit();
//			}
//		}));

		
//        main.addComponent(new Button("EvaluateTrue", new Button.ClickListener() {
//        	public void buttonClick(ClickEvent event) {
//        		addlabel("Evaluate Test");
//        		try {
//        			String addresult = wa.evaluateTaskTrue();
//        			addlabel(addresult);
//        		} catch (IOException e) {
//        			addlabel("FAIL: " + e.getMessage());
//        		}
//        	}
//        }));
//        
//        main.addComponent(new Button("EvaluateFalse", new Button.ClickListener() {
//        	public void buttonClick(ClickEvent event) {
//        		addlabel("Evaluate Test");
//        		try {
//        			String addresult = wa.evaluateTaskFalse();
//        			addlabel(addresult);
//        		} catch (IOException e) {
//        			addlabel("FAIL: " + e.getMessage());
//        		}
//        	}
//        }));
	}
	
	public void addlabel(String text) {
		main.addComponent(new Label(text));
	}
}

