package pt.ist.socialsoftware.blendedworkflow.shared;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

public class BWExecutorService {

	private static Logger log = Logger.getLogger("BWExecutorService");

	public ExecutorService executorService;

	public BWExecutorService() {
		log.info("BWExecutorService Initialized");
		executorService = Executors.newSingleThreadScheduledExecutor();
	}

	/**
	 * Add a Task to the BWExecutorService.
	 * 
	 * @param task
	 *            a task.
	 */
	public void runTask(Callable<String> task) {
		log.info("Task: " + task.getClass().getSimpleName()
				+ " added to BWExecutorService.");
		Future<String> f = executorService.submit(task);

		// Print the exceptions
		try {
			log.info(f.get());
		} catch (InterruptedException e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream stream = new PrintStream(baos);
			e.printStackTrace(stream);
			stream.flush();
			log.info("InterruptedException: " + new String(baos.toByteArray()));
		} catch (ExecutionException e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream stream = new PrintStream(baos);
			e.printStackTrace(stream);
			stream.flush();
			log.info("ExecutionException: " + new String(baos.toByteArray()));
		}
	}

	/**
	 * Shutdown the BWExecutorService.
	 */
	public void shutDown() {
		executorService.shutdown();
	}

}
