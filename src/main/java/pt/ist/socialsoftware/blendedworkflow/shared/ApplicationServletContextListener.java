//package pt.ist.socialsoftware.blendedworkflow.shared;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//import org.apache.log4j.PropertyConfigurator;
//
//public class ApplicationServletContextListener implements ServletContextListener {
//
//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//		ServletContext ctx = event.getServletContext();
//
//		String prefix =  ctx.getRealPath("/config/");    
//		String file = "WEB-INF"+System.getProperty("file.separator")+"classes"+System.getProperty("file.separator")+"log4j.properties";
//
//		if(file != null) {
//			PropertyConfigurator.configure(prefix+file);
//			System.out.println("Log4J Logging started for application: " + prefix+file);
//		}
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent event) {
//	}
//
//
//}