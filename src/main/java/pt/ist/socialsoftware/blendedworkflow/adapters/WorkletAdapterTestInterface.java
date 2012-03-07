package pt.ist.socialsoftware.blendedworkflow.adapters;

import org.apache.log4j.Logger;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class WorkletAdapterTestInterface extends Application {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("bwServicelogger");
	
	@Override
	public void init() {
		final Window main = new Window("Worklet Test");
		setMainWindow(main);
		log.info("Worklet Test");
	}
}

