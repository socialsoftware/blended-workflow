package org.blended.activity.utils;

import java.io.IOException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;

public class ConsoleManagement {
	public static String CONDITION_CONSOLE = "ConditionConsole";
	public static String GOAL_CONSOLE = "GoalConsole";
	public static String ACTIVITY_CONSOLE = "ActivityConsole";
	
	private static final RGB DEFAULT_COLOR = new RGB(0, 0, 0);
	private static final RGB OUTPUT = new RGB(0, 0, 200);
	private static final RGB OUTPUT_DATA = new RGB(0, 102, 51);
	private static final RGB CARET = new RGB(100, 100, 100);
	
	public static IOConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();

		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (IOConsole) existing[i];
		ImageDescriptor img = null;
		IOConsole myConsole = new IOConsole(name, img);
		conMan.addConsoles(new IConsole[] { myConsole });
		
		/*IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view = (IConsoleView) page.showView(id);
		view.display(myConsole);*/
		
		return myConsole;
	}
	
	public static void write(String name, Boolean rn, TypeOutput type, String message) {
		IOConsole myConsole = findConsole(name);
		IOConsoleOutputStream out = myConsole.newOutputStream();
		out.setActivateOnWrite(true);
		switch (type) {
			case Output: out.setColor(createColor(OUTPUT)); break;
			case OutputData: out.setColor(createColor(OUTPUT_DATA)); break;
			case Caret: out.setColor(createColor(CARET)); break;
			default: out.setColor(createColor(DEFAULT_COLOR)); break;
		}
		try {
			if (rn) message += "\r\n";
			if (type == TypeOutput.OutputData) message = "-> " + message;
			out.write(message);
		} catch (IOException e) {
			//e.printStackTrace();
		}	
	}
	
	public static void write(String name, TypeOutput type, String message) {
		write(name, true, type, message);
	}
	
	public static void write(String name, String message) {
		write(name, TypeOutput.Output, message);
	}
	
	private static Color createColor(RGB rgb) {
		if(rgb == null)
			rgb = DEFAULT_COLOR;
		return new Color(Display.getCurrent(), rgb);
	}
	
	public static enum TypeOutput {
		Default, Output, OutputData, Caret
	}

}
