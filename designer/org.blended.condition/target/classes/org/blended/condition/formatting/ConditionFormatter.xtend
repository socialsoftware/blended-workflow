package org.blended.condition.formatting

import org.blended.condition.services.ConditionGrammarAccess
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig

/**
 * This class contains custom formatting declarations.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
 * on how and when to use it.
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
class ConditionFormatter extends AbstractDeclarativeFormatter {

//	@Inject extension ConditionGrammarAccess
	
	override protected void configureFormatting(FormattingConfig c) {
		var ConditionGrammarAccess f = getGrammarAccess() as ConditionGrammarAccess;
		c.autoLinewrap = 130
		
		for (Keyword key : f.findKeywords("ENTITY_ACHIEVE_CONDITIONS")) {
			c.setNoSpace().before(key)
		}
			
		for (Keyword key : f.findKeywords("(")) {
			c.setNoSpace().before(key)
			c.setNoSpace().after(key)
		}
		
		for (Keyword key : f.findKeywords(")")) {
			c.setNoSpace().before(key)
		}
			
		for (Keyword key : f.findKeywords(",")) {
			c.setNoSpace().before(key)
		}
		
		for (Keyword key : f.findKeywords("ENTITY_INVARIANT_CONDITIONS",
			"ENTITY_DEPENDENCE_CONDITIONS", "ATTRIBUTE_ACHIEVE_CONDITIONS", 
			"ATTRIBUTE_INVARIANT_CONDITIONS", "ATTRIBUTE_DEPENDENCE_CONDITIONS"
		)) {
			c.setLinewrap(2).before(key)
		}
		
		c.setIndentationIncrement().before(f.entityAchieveConditionRule)
		c.setIndentationDecrement().after(f.entityAchieveConditionRule)
		c.setLinewrap().before(f.entityAchieveConditionRule)
		
		c.setIndentationIncrement().before(f.entityInvariantConditionRule)
		c.setIndentationDecrement().after(f.entityInvariantConditionRule)
		c.setLinewrap().before(f.entityInvariantConditionRule)
		
		c.setIndentationIncrement().before(f.entityDependenceConditionRule)
		c.setIndentationDecrement().after(f.entityDependenceConditionRule)
		c.setLinewrap().before(f.entityDependenceConditionRule)
		
		c.setIndentationIncrement().before(f.attributeAchieveConditionRule)
		c.setIndentationDecrement().after(f.attributeAchieveConditionRule)
		c.setLinewrap().before(f.attributeAchieveConditionRule)
		
		c.setIndentationIncrement().before(f.attributeInvariantConditionRule)
		c.setIndentationDecrement().after(f.attributeInvariantConditionRule)
		c.setLinewrap().before(f.attributeInvariantConditionRule)
		
		c.setIndentationIncrement().before(f.attributeDependenceConditionRule)
		c.setIndentationDecrement().after(f.attributeDependenceConditionRule)
		c.setLinewrap().before(f.attributeDependenceConditionRule)
	}
}
