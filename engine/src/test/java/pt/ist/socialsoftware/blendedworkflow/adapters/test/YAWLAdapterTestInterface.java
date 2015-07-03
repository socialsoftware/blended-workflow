package pt.ist.socialsoftware.blendedworkflow.adapters.test;

import org.apache.log4j.Logger;
import org.yawlfoundation.yawl.authentication.YExternalClient;
import org.yawlfoundation.yawl.elements.YAWLServiceReference;
import org.yawlfoundation.yawl.engine.interfce.SpecificationData;

import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class YAWLAdapterTestInterface extends Application {

	private static Logger log = Logger.getLogger("YAWLAdapterTestInterface");

	// Inputs
	private static String YAWL_SPEC_FILENAME = "C:/Users/User/Desktop/MedicalEpisode.yawl.xml";
	private static String specID ="";
	private String caseInstanceID ="";
	YAWLAdapter yawlAdapter = null;

	@Override
	public void init() {
		try {
			yawlAdapter= new YAWLAdapter();
		} catch(BWException e) {
			log.info(e.getMessage());
		}
		final Window main = new Window("YAWLAdapterTestInterface");
		setMainWindow(main);

		HorizontalLayout registrationButtons = new HorizontalLayout();
		HorizontalLayout specButtons = new HorizontalLayout();
		HorizontalLayout caseButtons = new HorizontalLayout();

		main.addComponent(registrationButtons);
		main.addComponent(specButtons);
		main.addComponent(caseButtons);

		final TextField results = new TextField("Results:", "...");
		results.setWidth("100%");
		main.addComponent(results);

		// Registration Buttons
		registrationButtons.addComponent(new Button("Connect to Yawl", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					BlendedWorkflow.getInstance().getYawlAdapter().connectYAWL();
					results.setValue("Connect to Yawl - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Connect to Yawl - Fail!");
				}
			}
		}));

		registrationButtons.addComponent(new Button("Disconnect to Yawl", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					BlendedWorkflow.getInstance().getYawlAdapter().disconnectYAWL();
					results.setValue("Disconnect to Yawl - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Disconnect to Yawl - Fail!");
				}
			}
		}));

		registrationButtons.addComponent(new Button("Get Connection State", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {

					log.debug("Clients Registered:");
					for (YExternalClient client : yawlAdapter.getClientAccounts()) {
						log.debug(client.getUserName());
					}
					log.debug("Services Registered:");
					for (YAWLServiceReference service : yawlAdapter.getRegisteredServices()) {
						log.debug(service.getURI());
					}

					results.setValue("Get Connection State - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Get Connection State - Fail!");
				}
			}
		}));

		// Specification Buttons
		specButtons.addComponent(new Button("Load Specification", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					String spec = StringUtils.fileToString(YAWL_SPEC_FILENAME);
					yawlAdapter.loadSpecification(spec);
					results.setValue("Load Specification - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Load Specification - Fail!");
				}
			}
		}));

		specButtons.addComponent(new Button("Unload Specification", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					BlendedWorkflow.getInstance().getYawlAdapter().unloadSpecification(specID);
					results.setValue(" Unload Specification - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Unload Specification - Fail!");
				}
			}
		}));

		specButtons.addComponent(new Button("Get Loaded Specifications", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {

					log.debug("Loaded Specifications: ");
					for (SpecificationData specification : yawlAdapter.getLoadedSpecs()) {
						log.debug(specification.getName());
					}

					results.setValue("Get Loaded Specifications - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Get Loaded Specifications - Fail!");
				}
			}
		}));

		// Case Buttons
		caseButtons.addComponent(new Button("Launch Specification", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					String spec = StringUtils.fileToString(YAWL_SPEC_FILENAME);
					specID = SpecUtils.getYAWLSpecificationIDFromSpec(spec).getIdentifier();
					caseInstanceID = yawlAdapter.launchCase(specID);
					results.setValue("Launch Specification - Sucesso!" + caseInstanceID);
				}
				catch (Exception e) {
					results.setValue("Launch Specification - Fail!");
				}
			}
		}));

		caseButtons.addComponent(new Button("Cancel Case", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					BlendedWorkflow.getInstance().getYawlAdapter().cancelCase(caseInstanceID);
					results.setValue("Cancel Case - Sucesso!");
				}
				catch (Exception e) {
					results.setValue("Cancel Case - Fail!");
				}
			}
		}));
	}
}
