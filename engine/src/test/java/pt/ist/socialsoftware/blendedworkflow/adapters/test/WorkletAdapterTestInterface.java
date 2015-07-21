package pt.ist.socialsoftware.blendedworkflow.adapters.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class WorkletAdapterTestInterface extends Application {

    private static final long serialVersionUID = 1L;

    private static Logger log = LoggerFactory
            .getLogger("WorkletAdapterTestInterface");
    private final Window main = new Window("WorkletAdapterTestInterface");

    @Override
    public void init() {
        setMainWindow(main);
        log.info("Worklet Test");
    }
}
