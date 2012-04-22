package pt.ist.socialsoftware.blendedworkflow.adapters;

import org.apache.log4j.Logger;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class WorkletAdapterTestInterface extends Application {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger("WorkletAdapterTestInterface");
	private final Window main = new Window("WorkletAdapterTestInterface");

	@Override
	public void init() {
		setMainWindow(main);
		log.info("Worklet Test");
	}
}

