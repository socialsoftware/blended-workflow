package org.blended.goal.utils;

import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.Map;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalFactory;
import org.blended.goal.goal.GoalModel;
import org.blended.goal.utils.ConsoleManagement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class GoalListener implements Runnable {
  String name;
  
  Resource resource;
  
  IOConsole myConsole;
  
  Boolean terminate;
  
  Boolean running;
  
  public GoalListener(final String name, final Resource resource) {
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
        CharSequence _listOfActions = GoalListener.listOfActions();
        String _string = _listOfActions.toString();
        ConsoleManagement.write(this.name, _string);
        break;
      case "1":
        ConsoleManagement.write(this.name, "List of all the Goals:");
        TreeIterator<EObject> _allContents = this.resource.getAllContents();
        Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
        Iterable<Goal> _filter = Iterables.<Goal>filter(_iterable, Goal.class);
        for (final Goal r : _filter) {
          String _name = r.getName();
          String _plus = ("g:" + _name);
          ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus);
        }
        break;
      case "2":
        ConsoleManagement.write(this.name, "Testing manipulation of model:");
        GoalFactory blendedFactory = GoalFactory.eINSTANCE;
        Goal goal = blendedFactory.createGoal();
        goal.setName("NEWGOAL");
        EList<EObject> _contents = this.resource.getContents();
        EObject _get = _contents.get(0);
        GoalModel gm = ((GoalModel) _get);
        EList<Goal> _goals = gm.getGoals();
        _goals.remove(0);
        EList<Goal> _goals_1 = gm.getGoals();
        _goals_1.remove(1);
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
