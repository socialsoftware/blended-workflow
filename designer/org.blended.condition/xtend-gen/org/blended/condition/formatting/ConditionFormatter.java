package org.blended.condition.formatting;

import java.util.List;
import org.blended.condition.services.ConditionGrammarAccess;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

/**
 * This class contains custom formatting declarations.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
 * on how and when to use it.
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
@SuppressWarnings("all")
public class ConditionFormatter extends AbstractDeclarativeFormatter {
  @Override
  protected void configureFormatting(final FormattingConfig c) {
    IGrammarAccess _grammarAccess = this.getGrammarAccess();
    ConditionGrammarAccess f = ((ConditionGrammarAccess) _grammarAccess);
    c.setAutoLinewrap(130);
    List<Keyword> _findKeywords = f.findKeywords("ENTITY_ACHIEVE_CONDITIONS");
    for (final Keyword key : _findKeywords) {
      FormattingConfig.NoSpaceLocator _setNoSpace = c.setNoSpace();
      _setNoSpace.before(key);
    }
    List<Keyword> _findKeywords_1 = f.findKeywords("(");
    for (final Keyword key_1 : _findKeywords_1) {
      {
        FormattingConfig.NoSpaceLocator _setNoSpace_1 = c.setNoSpace();
        _setNoSpace_1.before(key_1);
        FormattingConfig.NoSpaceLocator _setNoSpace_2 = c.setNoSpace();
        _setNoSpace_2.after(key_1);
      }
    }
    List<Keyword> _findKeywords_2 = f.findKeywords(")");
    for (final Keyword key_2 : _findKeywords_2) {
      FormattingConfig.NoSpaceLocator _setNoSpace_1 = c.setNoSpace();
      _setNoSpace_1.before(key_2);
    }
    List<Keyword> _findKeywords_3 = f.findKeywords(",");
    for (final Keyword key_3 : _findKeywords_3) {
      FormattingConfig.NoSpaceLocator _setNoSpace_2 = c.setNoSpace();
      _setNoSpace_2.before(key_3);
    }
    List<Keyword> _findKeywords_4 = f.findKeywords("ENTITY_INVARIANT_CONDITIONS", 
      "ENTITY_DEPENDENCE_CONDITIONS", "ATTRIBUTE_ACHIEVE_CONDITIONS", 
      "ATTRIBUTE_INVARIANT_CONDITIONS", "ATTRIBUTE_DEPENDENCE_CONDITIONS");
    for (final Keyword key_4 : _findKeywords_4) {
      FormattingConfig.LinewrapLocator _setLinewrap = c.setLinewrap(2);
      _setLinewrap.before(key_4);
    }
    FormattingConfig.IndentationLocatorStart _setIndentationIncrement = c.setIndentationIncrement();
    ParserRule _entityAchieveConditionRule = f.getEntityAchieveConditionRule();
    _setIndentationIncrement.before(_entityAchieveConditionRule);
    FormattingConfig.IndentationLocatorEnd _setIndentationDecrement = c.setIndentationDecrement();
    ParserRule _entityAchieveConditionRule_1 = f.getEntityAchieveConditionRule();
    _setIndentationDecrement.after(_entityAchieveConditionRule_1);
    FormattingConfig.LinewrapLocator _setLinewrap_1 = c.setLinewrap();
    ParserRule _entityAchieveConditionRule_2 = f.getEntityAchieveConditionRule();
    _setLinewrap_1.before(_entityAchieveConditionRule_2);
    FormattingConfig.IndentationLocatorStart _setIndentationIncrement_1 = c.setIndentationIncrement();
    ParserRule _entityInvariantConditionRule = f.getEntityInvariantConditionRule();
    _setIndentationIncrement_1.before(_entityInvariantConditionRule);
    FormattingConfig.IndentationLocatorEnd _setIndentationDecrement_1 = c.setIndentationDecrement();
    ParserRule _entityInvariantConditionRule_1 = f.getEntityInvariantConditionRule();
    _setIndentationDecrement_1.after(_entityInvariantConditionRule_1);
    FormattingConfig.LinewrapLocator _setLinewrap_2 = c.setLinewrap();
    ParserRule _entityInvariantConditionRule_2 = f.getEntityInvariantConditionRule();
    _setLinewrap_2.before(_entityInvariantConditionRule_2);
    FormattingConfig.IndentationLocatorStart _setIndentationIncrement_2 = c.setIndentationIncrement();
    ParserRule _entityDependenceConditionRule = f.getEntityDependenceConditionRule();
    _setIndentationIncrement_2.before(_entityDependenceConditionRule);
    FormattingConfig.IndentationLocatorEnd _setIndentationDecrement_2 = c.setIndentationDecrement();
    ParserRule _entityDependenceConditionRule_1 = f.getEntityDependenceConditionRule();
    _setIndentationDecrement_2.after(_entityDependenceConditionRule_1);
    FormattingConfig.LinewrapLocator _setLinewrap_3 = c.setLinewrap();
    ParserRule _entityDependenceConditionRule_2 = f.getEntityDependenceConditionRule();
    _setLinewrap_3.before(_entityDependenceConditionRule_2);
    FormattingConfig.IndentationLocatorStart _setIndentationIncrement_3 = c.setIndentationIncrement();
    ParserRule _attributeAchieveConditionRule = f.getAttributeAchieveConditionRule();
    _setIndentationIncrement_3.before(_attributeAchieveConditionRule);
    FormattingConfig.IndentationLocatorEnd _setIndentationDecrement_3 = c.setIndentationDecrement();
    ParserRule _attributeAchieveConditionRule_1 = f.getAttributeAchieveConditionRule();
    _setIndentationDecrement_3.after(_attributeAchieveConditionRule_1);
    FormattingConfig.LinewrapLocator _setLinewrap_4 = c.setLinewrap();
    ParserRule _attributeAchieveConditionRule_2 = f.getAttributeAchieveConditionRule();
    _setLinewrap_4.before(_attributeAchieveConditionRule_2);
    FormattingConfig.IndentationLocatorStart _setIndentationIncrement_4 = c.setIndentationIncrement();
    ParserRule _attributeInvariantConditionRule = f.getAttributeInvariantConditionRule();
    _setIndentationIncrement_4.before(_attributeInvariantConditionRule);
    FormattingConfig.IndentationLocatorEnd _setIndentationDecrement_4 = c.setIndentationDecrement();
    ParserRule _attributeInvariantConditionRule_1 = f.getAttributeInvariantConditionRule();
    _setIndentationDecrement_4.after(_attributeInvariantConditionRule_1);
    FormattingConfig.LinewrapLocator _setLinewrap_5 = c.setLinewrap();
    ParserRule _attributeInvariantConditionRule_2 = f.getAttributeInvariantConditionRule();
    _setLinewrap_5.before(_attributeInvariantConditionRule_2);
    FormattingConfig.IndentationLocatorStart _setIndentationIncrement_5 = c.setIndentationIncrement();
    ParserRule _attributeDependenceConditionRule = f.getAttributeDependenceConditionRule();
    _setIndentationIncrement_5.before(_attributeDependenceConditionRule);
    FormattingConfig.IndentationLocatorEnd _setIndentationDecrement_5 = c.setIndentationDecrement();
    ParserRule _attributeDependenceConditionRule_1 = f.getAttributeDependenceConditionRule();
    _setIndentationDecrement_5.after(_attributeDependenceConditionRule_1);
    FormattingConfig.LinewrapLocator _setLinewrap_6 = c.setLinewrap();
    ParserRule _attributeDependenceConditionRule_2 = f.getAttributeDependenceConditionRule();
    _setLinewrap_6.before(_attributeDependenceConditionRule_2);
  }
}
