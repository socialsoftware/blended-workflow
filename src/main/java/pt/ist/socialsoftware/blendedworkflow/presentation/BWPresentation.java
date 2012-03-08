package pt.ist.socialsoftware.blendedworkflow.presentation;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class BWPresentation extends Application {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("bwServicelogger");
	
	@Override
	public void init() {
		final Window main = new Window("BW Presentation");
		setMainWindow(main);
		log.info("BW Presentation");
		
        // add handle to button
		Button init = new Button("INIT FenixFramework");
        init.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				WorkListManager.getInstance();
			}
		});
        main.addComponent(init);
        
//		Bootstrap.init();
        
//		String test = new ProvideEnabledWorkItemsService().execute();
//		final TextField results = new TextField("Workitems:", test);
//		main.addComponent(results);
		
	}
}

