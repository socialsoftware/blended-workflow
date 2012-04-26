package pt.ist.socialsoftware.blendedworkflow.shared;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class ThreadPool {
	
	private static Logger log = Logger.getLogger("ThreadPool");
	private static ExecutorService threadExecutor = Executors.newSingleThreadScheduledExecutor();

	private ThreadPool() {}

	public static ExecutorService getThreadExecutor() {
		log.info("GetThreadExecutor");		
		return threadExecutor;
	}

}
