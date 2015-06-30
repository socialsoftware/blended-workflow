package org.blended.condition.utils;

import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.Map;
import org.blended.blended.AttributeAchieveCondition;
import org.blended.blended.AttributeDependenceCondition;
import org.blended.blended.AttributeInvariantCondition;
import org.blended.blended.BlendedFactory;
import org.blended.blended.Condition;
import org.blended.blended.EntityAchieveCondition;
import org.blended.blended.EntityDependenceCondition;
import org.blended.blended.EntityInvariantCondition;
import org.blended.blended.Expression;
import org.blended.blended.MandatoryAttributeAchieveCondition;
import org.blended.condition.condition.ConditionModel;
import org.blended.condition.generator.ConditionGeneratorGoalModel;
import org.blended.utils.ConsoleManagement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class ConditionListener implements Runnable {
  String name;
  
  Resource resource;
  
  IOConsole myConsole;
  
  Boolean terminate;
  
  Boolean running;
  
  public ConditionListener(final String name, final Resource resource) {
    this.name = name;
    this.resource = resource;
    this.terminate = Boolean.valueOf(false);
    this.running = Boolean.valueOf(false);
    IOConsole _findConsole = ConsoleManagement.findConsole(name);
    this.myConsole = _findConsole;
  }
  
  @Override
  public void run() {
    this.running = Boolean.valueOf(true);
    while ((!(this.terminate).booleanValue())) {
      {
        IOConsoleInputStream in = this.myConsole.getInputStream();
        byte[] input = new byte[200];
        try {
          ConsoleManagement.write(this.name, Boolean.valueOf(false), ConsoleManagement.TypeOutput.Caret, "CM>> ");
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
  
  public Object doTask(final String option) {
    Object _switchResult = null;
    switch (option) {
      case "0":
        CharSequence _listOfActions = ConditionListener.listOfActions();
        String _string = _listOfActions.toString();
        ConsoleManagement.write(this.name, _string);
        break;
      case "1":
        ConsoleManagement.write(this.name, "List of Entity Achive Conditions:");
        TreeIterator<EObject> _allContents = this.resource.getAllContents();
        Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
        Iterable<EntityAchieveCondition> _filter = Iterables.<EntityAchieveCondition>filter(_iterable, EntityAchieveCondition.class);
        for (final EntityAchieveCondition r : _filter) {
          String _name = r.getName();
          String _plus = ("DEF(" + _name);
          String _plus_1 = (_plus + ")");
          ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_1);
        }
        break;
      case "2":
        ConsoleManagement.write(this.name, "List of Entity Invariant Conditions:");
        TreeIterator<EObject> _allContents_1 = this.resource.getAllContents();
        Iterable<EObject> _iterable_1 = IteratorExtensions.<EObject>toIterable(_allContents_1);
        Iterable<EntityInvariantCondition> _filter_1 = Iterables.<EntityInvariantCondition>filter(_iterable_1, EntityInvariantCondition.class);
        for (final EntityInvariantCondition r_1 : _filter_1) {
          String _name_1 = r_1.getName();
          String _plus_2 = ("MUL(" + _name_1);
          String _plus_3 = (_plus_2 + ", ");
          String _cardinality = r_1.getCardinality();
          String _plus_4 = (_plus_3 + _cardinality);
          String _plus_5 = (_plus_4 + ")");
          ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_5);
        }
        break;
      case "3":
        ConsoleManagement.write(this.name, "List of Entity Dependence Conditions:");
        TreeIterator<EObject> _allContents_2 = this.resource.getAllContents();
        Iterable<EObject> _iterable_2 = IteratorExtensions.<EObject>toIterable(_allContents_2);
        Iterable<EntityDependenceCondition> _filter_2 = Iterables.<EntityDependenceCondition>filter(_iterable_2, EntityDependenceCondition.class);
        for (final EntityDependenceCondition r_2 : _filter_2) {
          String _entity1 = r_2.getEntity1();
          String _plus_6 = ("DEP(DEF(" + _entity1);
          String _plus_7 = (_plus_6 + "), DEF(");
          String _entity2 = r_2.getEntity2();
          String _plus_8 = (_plus_7 + _entity2);
          String _plus_9 = (_plus_8 + "))");
          ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_9);
        }
        break;
      case "4":
        ConsoleManagement.write(this.name, "List of Attribute Achive Conditions:");
        TreeIterator<EObject> _allContents_3 = this.resource.getAllContents();
        Iterable<EObject> _iterable_3 = IteratorExtensions.<EObject>toIterable(_allContents_3);
        Iterable<AttributeAchieveCondition> _filter_3 = Iterables.<AttributeAchieveCondition>filter(_iterable_3, AttributeAchieveCondition.class);
        for (final AttributeAchieveCondition r_3 : _filter_3) {
          if ((r_3 instanceof MandatoryAttributeAchieveCondition)) {
            EList<String> _conditions = ((MandatoryAttributeAchieveCondition)r_3).getConditions();
            String _join = IterableExtensions.join(_conditions, ", ");
            String _plus_10 = ("MAN(DEF(" + _join);
            String _plus_11 = (_plus_10 + "))");
            ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_11);
          } else {
            EList<String> _conditions_1 = r_3.getConditions();
            String _join_1 = IterableExtensions.join(_conditions_1, ", ");
            String _plus_12 = ("DEF(" + _join_1);
            String _plus_13 = (_plus_12 + ")");
            ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_13);
          }
        }
        break;
      case "5":
        ConsoleManagement.write(this.name, "List of Attribute Invariant Conditions:");
        TreeIterator<EObject> _allContents_4 = this.resource.getAllContents();
        Iterable<EObject> _iterable_4 = IteratorExtensions.<EObject>toIterable(_allContents_4);
        Iterable<AttributeInvariantCondition> _filter_4 = Iterables.<AttributeInvariantCondition>filter(_iterable_4, AttributeInvariantCondition.class);
        for (final AttributeInvariantCondition r_4 : _filter_4) {
          Expression _expression = r_4.getExpression();
          String _completeExpression = ConditionGeneratorGoalModel.getCompleteExpression(_expression);
          String _plus_14 = ("RUL(" + _completeExpression);
          String _plus_15 = (_plus_14 + ")");
          ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_15);
        }
        break;
      case "6":
        ConsoleManagement.write(this.name, "List of Attribute Dependence Conditions:");
        TreeIterator<EObject> _allContents_5 = this.resource.getAllContents();
        Iterable<EObject> _iterable_5 = IteratorExtensions.<EObject>toIterable(_allContents_5);
        Iterable<AttributeDependenceCondition> _filter_5 = Iterables.<AttributeDependenceCondition>filter(_iterable_5, AttributeDependenceCondition.class);
        for (final AttributeDependenceCondition r_5 : _filter_5) {
          EList<String> _attributes1 = r_5.getAttributes1();
          String _join_2 = IterableExtensions.join(_attributes1, ", ");
          String _plus_16 = ("DEP(DEF(" + _join_2);
          String _plus_17 = (_plus_16 + "), DEF(");
          String _attribute2 = r_5.getAttribute2();
          String _plus_18 = (_plus_17 + _attribute2);
          String _plus_19 = (_plus_18 + "))");
          ConsoleManagement.write(this.name, ConsoleManagement.TypeOutput.OutputData, _plus_19);
        }
        break;
      case "7":
        ConsoleManagement.write(this.name, "Testing manipulation of model:");
        BlendedFactory blendedFactory = BlendedFactory.eINSTANCE;
        EntityAchieveCondition eac = blendedFactory.createEntityAchieveCondition();
        eac.setName("TESTING");
        EList<EObject> _contents = this.resource.getContents();
        EObject _get = _contents.get(0);
        ConditionModel cm = ((ConditionModel) _get);
        Condition _conditions_2 = cm.getConditions();
        EList<EObject> _entityAchieveConditions = _conditions_2.getEntityAchieveConditions();
        _entityAchieveConditions.add(eac);
        this.update();
        break;
      case "":
        _switchResult = null;
        break;
      default:
        ConsoleManagement.write(this.name, "Option no valid. Type 0 to see the options");
        break;
    }
    return _switchResult;
  }
  
  public static CharSequence listOfActions() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("******************************************************");
    _builder.newLine();
    _builder.append("*******************CONDITION MODEL********************");
    _builder.newLine();
    _builder.append("1- List of Entity Achive Conditions");
    _builder.newLine();
    _builder.append("2- List of Entity Invariant Conditions");
    _builder.newLine();
    _builder.append("3- List of Entity Dependence Conditions");
    _builder.newLine();
    _builder.append("4- List of Attribute Achive Conditions");
    _builder.newLine();
    _builder.append("5- List of Attribute Invariant Conditions");
    _builder.newLine();
    _builder.append("6- List of Attribute Dependence Conditions");
    _builder.newLine();
    _builder.append("7- Testing manipulation of model");
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
