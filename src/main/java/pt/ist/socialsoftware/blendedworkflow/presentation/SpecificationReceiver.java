package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class SpecificationReceiver implements Receiver, FailedListener, SucceededListener {

	protected BWPresentation bwPresentation = null;
	protected ByteArrayOutputStream buffer;
	private String specInString = null;
	
	public SpecificationReceiver(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}
	
	public String getSpecInString() {
		return this.specInString;
	}
	
	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		this.buffer = new ByteArrayOutputStream();
		
		return buffer;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		this.specInString = StringUtils.bufferToString(buffer);
		
		if(specInString == null) {
			this.bwPresentation.getMainWindow().showNotification("Submition of " + event.getFilename() + " failed", Notification.TYPE_ERROR_MESSAGE);
			return;
		}
		else {
			this.bwPresentation.getMainWindow().showNotification("Submition of " + event.getFilename() + " successful");
			return;
		}
			
	}
	
	@Override
	public void uploadFailed(FailedEvent event) {
		this.bwPresentation.getMainWindow().showNotification("Upload of " + event.getFilename() + " failed", Notification.TYPE_ERROR_MESSAGE);
	}
}
