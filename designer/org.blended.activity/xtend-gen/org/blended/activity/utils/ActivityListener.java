package org.blended.activity.utils;

import java.io.IOException;
import java.util.Map;
import org.blended.activity.utils.ConsoleManagement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class ActivityListener implements Runnable {
  String name;
  
  Resource resource;
  
  IOConsole myConsole;
  
  Boolean terminate;
  
  Boolean running;
  
  public ActivityListener(final String name, final Resource resource) {
    this.name = name;
    this.resource = resource;
    this.terminate = Boolean.valueOf(false);
    this.running = Boolean.valueOf(false);
    IOConsole _findConsole = ConsoleManagement.findConsole(name);
    this.myConsole = _findConsole;
  }
  
  @Override
  public void run() {
    while ((!(this.terminate).booleanValue())) {
      {
        IOConsoleInputStream in = this.myConsole.getInputStream();
        byte[] input = new byte[200];
        try {
          ConsoleManagement.write(this.name, Boolean.valueOf(false), ConsoleManagement.TypeOutput.Caret, "GM>> ");
          in.read(input);
        } catch (final Throwable _t) {
          if (_t instanceof IOException) {
            final IOException e = (IOException)_t;
            e.printStackTrace();
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        String _string = new String(input);
        String _trim = _string.trim();
        this.doTask(_trim);
      }
    }
  }
  
  public Boolean terminate() {
    return this.terminate = Boolean.valueOf(true);
  }
  
  public Boolean isRunning() {
    return this.running;
  }
  
  public void doTask(final String option) {
    switch (option) {
      case "0":
        CharSequence _listOfActions = ActivityListener.listOfActions();
        String _string = _listOfActions.toString();
        ConsoleManagement.write(this.name, _string);
        break;
      case "1":
        ConsoleManagement.write(this.name, "List of all the Goals:");
        break;
      case "2":
        ConsoleManagement.write(this.name, "Testing manipulation of model:");
        this.update();
        break;
      default:
        ConsoleManagement.write(this.name, "Option no valid. Type 0 to see the options");
        break;
    }
  }
  
  public static CharSequence listOfActions() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("******************************************************");
    _builder.newLine();
    _builder.append("*******************GOAL MODEL*************************");
    _builder.newLine();
    _builder.append("1- List of all the Goals");
    _builder.newLine();
    _builder.append("2- Testing manipulation of model");
    _builder.newLine();
    _builder.newLine();
    _builder.append("0- Show the menu again");
    _builder.newLine();
    _builder.append("******************************************************");
    _builder.newLine();
    return _builder;
  }
  
  public void update() {
    try {
      SaveOptions.Builder builder = SaveOptions.newBuilder();
      builder.noValidation();
      builder.format();
      SaveOptions _options = builder.getOptions();
      Map<Object, Object> _optionsMap = _options.toOptionsMap();
      this.resource.save(_optionsMap);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
