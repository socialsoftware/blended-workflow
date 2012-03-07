package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import org.apache.log4j.Logger;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class BWPresentation extends Application {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("bwServicelogger");
	
	@Override
	public void init() {
		final Window main = new Window("BW Presentation");
		setMainWindow(main);
		log.info("BW Presentation");
	}
}

