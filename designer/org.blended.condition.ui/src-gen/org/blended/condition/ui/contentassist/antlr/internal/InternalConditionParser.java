package org.blended.condition.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.blended.condition.services.ConditionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalConditionParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEF'", "'('", "')'", "'EXISTS'", "'MUL'", "','", "'DEP'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'OR'", "'AND'", "'NOT'", "'ENTITY_ACHIEVE_CONDITIONS'", "'ENTITY_INVARIANT_CONDITIONS'", "'ENTITY_DEPENDENCE_CONDITIONS'", "'ATTRIBUTE_ACHIEVE_CONDITIONS'", "'ATTRIBUTE_INVARIANT_CONDITIONS'", "'ATTRIBUTE_DEPENDENCE_CONDITIONS'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalConditionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalConditionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalConditionParser.tokenNames; }
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g"; }


     
     	private ConditionGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(ConditionGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleConditionModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:60:1: entryRuleConditionModel : ruleConditionModel EOF ;
    public final void entryRuleConditionModel() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:61:1: ( ruleConditionModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:62:1: ruleConditionModel EOF
            {
             before(grammarAccess.getConditionModelRule()); 
            pushFollow(FOLLOW_ruleConditionModel_in_entryRuleConditionModel61);
            ruleConditionModel();

            state._fsp--;

             after(grammarAccess.getConditionModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionModel68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConditionModel"


    // $ANTLR start "ruleConditionModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:69:1: ruleConditionModel : ( ( rule__ConditionModel__ConditionsAssignment ) ) ;
    public final void ruleConditionModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:73:2: ( ( ( rule__ConditionModel__ConditionsAssignment ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:74:1: ( ( rule__ConditionModel__ConditionsAssignment ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:74:1: ( ( rule__ConditionModel__ConditionsAssignment ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:75:1: ( rule__ConditionModel__ConditionsAssignment )
            {
             before(grammarAccess.getConditionModelAccess().getConditionsAssignment()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:76:1: ( rule__ConditionModel__ConditionsAssignment )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:76:2: rule__ConditionModel__ConditionsAssignment
            {
            pushFollow(FOLLOW_rule__ConditionModel__ConditionsAssignment_in_ruleConditionModel94);
            rule__ConditionModel__ConditionsAssignment();

            state._fsp--;


            }

             after(grammarAccess.getConditionModelAccess().getConditionsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConditionModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:88:1: entryRuleEntityAchieveCondition : ruleEntityAchieveCondition EOF ;
    public final void entryRuleEntityAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:89:1: ( ruleEntityAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:90:1: ruleEntityAchieveCondition EOF
            {
             before(grammarAccess.getEntityAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition121);
            ruleEntityAchieveCondition();

            state._fsp--;

             after(grammarAccess.getEntityAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveCondition128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEntityAchieveCondition"


    // $ANTLR start "ruleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:97:1: ruleEntityAchieveCondition : ( ( rule__EntityAchieveCondition__Group__0 ) ) ;
    public final void ruleEntityAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:101:2: ( ( ( rule__EntityAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:102:1: ( ( rule__EntityAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:102:1: ( ( rule__EntityAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:103:1: ( rule__EntityAchieveCondition__Group__0 )
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:104:1: ( rule__EntityAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:104:2: rule__EntityAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__0_in_ruleEntityAchieveCondition154);
            rule__EntityAchieveCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAchieveConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntityAchieveCondition"


    // $ANTLR start "entryRuleEntityAchieveConditionExist"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:116:1: entryRuleEntityAchieveConditionExist : ruleEntityAchieveConditionExist EOF ;
    public final void entryRuleEntityAchieveConditionExist() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:117:1: ( ruleEntityAchieveConditionExist EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:118:1: ruleEntityAchieveConditionExist EOF
            {
             before(grammarAccess.getEntityAchieveConditionExistRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveConditionExist_in_entryRuleEntityAchieveConditionExist181);
            ruleEntityAchieveConditionExist();

            state._fsp--;

             after(grammarAccess.getEntityAchieveConditionExistRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveConditionExist188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEntityAchieveConditionExist"


    // $ANTLR start "ruleEntityAchieveConditionExist"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:125:1: ruleEntityAchieveConditionExist : ( ( rule__EntityAchieveConditionExist__Group__0 ) ) ;
    public final void ruleEntityAchieveConditionExist() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:129:2: ( ( ( rule__EntityAchieveConditionExist__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:130:1: ( ( rule__EntityAchieveConditionExist__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:130:1: ( ( rule__EntityAchieveConditionExist__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:131:1: ( rule__EntityAchieveConditionExist__Group__0 )
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:132:1: ( rule__EntityAchieveConditionExist__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:132:2: rule__EntityAchieveConditionExist__Group__0
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__0_in_ruleEntityAchieveConditionExist214);
            rule__EntityAchieveConditionExist__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAchieveConditionExistAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntityAchieveConditionExist"


    // $ANTLR start "entryRuleEntityInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:144:1: entryRuleEntityInvariantCondition : ruleEntityInvariantCondition EOF ;
    public final void entryRuleEntityInvariantCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:145:1: ( ruleEntityInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:146:1: ruleEntityInvariantCondition EOF
            {
             before(grammarAccess.getEntityInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition241);
            ruleEntityInvariantCondition();

            state._fsp--;

             after(grammarAccess.getEntityInvariantConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityInvariantCondition248); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEntityInvariantCondition"


    // $ANTLR start "ruleEntityInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:153:1: ruleEntityInvariantCondition : ( ( rule__EntityInvariantCondition__Group__0 ) ) ;
    public final void ruleEntityInvariantCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:157:2: ( ( ( rule__EntityInvariantCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:158:1: ( ( rule__EntityInvariantCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:158:1: ( ( rule__EntityInvariantCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:159:1: ( rule__EntityInvariantCondition__Group__0 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:160:1: ( rule__EntityInvariantCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:160:2: rule__EntityInvariantCondition__Group__0
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__0_in_ruleEntityInvariantCondition274);
            rule__EntityInvariantCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityInvariantConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntityInvariantCondition"


    // $ANTLR start "entryRuleEntityDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:172:1: entryRuleEntityDependenceCondition : ruleEntityDependenceCondition EOF ;
    public final void entryRuleEntityDependenceCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:173:1: ( ruleEntityDependenceCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:174:1: ruleEntityDependenceCondition EOF
            {
             before(grammarAccess.getEntityDependenceConditionRule()); 
            pushFollow(FOLLOW_ruleEntityDependenceCondition_in_entryRuleEntityDependenceCondition301);
            ruleEntityDependenceCondition();

            state._fsp--;

             after(grammarAccess.getEntityDependenceConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityDependenceCondition308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEntityDependenceCondition"


    // $ANTLR start "ruleEntityDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:181:1: ruleEntityDependenceCondition : ( ( rule__EntityDependenceCondition__Group__0 ) ) ;
    public final void ruleEntityDependenceCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:185:2: ( ( ( rule__EntityDependenceCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:186:1: ( ( rule__EntityDependenceCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:186:1: ( ( rule__EntityDependenceCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:187:1: ( rule__EntityDependenceCondition__Group__0 )
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:188:1: ( rule__EntityDependenceCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:188:2: rule__EntityDependenceCondition__Group__0
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__0_in_ruleEntityDependenceCondition334);
            rule__EntityDependenceCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityDependenceConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntityDependenceCondition"


    // $ANTLR start "entryRuleAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:200:1: entryRuleAttributeAchieveCondition : ruleAttributeAchieveCondition EOF ;
    public final void entryRuleAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:201:1: ( ruleAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:202:1: ruleAttributeAchieveCondition EOF
            {
             before(grammarAccess.getAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition361);
            ruleAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeAchieveCondition368); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAttributeAchieveCondition"


    // $ANTLR start "ruleAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:209:1: ruleAttributeAchieveCondition : ( ( rule__AttributeAchieveCondition__Alternatives ) ) ;
    public final void ruleAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:213:2: ( ( ( rule__AttributeAchieveCondition__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:214:1: ( ( rule__AttributeAchieveCondition__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:214:1: ( ( rule__AttributeAchieveCondition__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:215:1: ( rule__AttributeAchieveCondition__Alternatives )
            {
             before(grammarAccess.getAttributeAchieveConditionAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:216:1: ( rule__AttributeAchieveCondition__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:216:2: rule__AttributeAchieveCondition__Alternatives
            {
            pushFollow(FOLLOW_rule__AttributeAchieveCondition__Alternatives_in_ruleAttributeAchieveCondition394);
            rule__AttributeAchieveCondition__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAchieveConditionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttributeAchieveCondition"


    // $ANTLR start "entryRuleNotMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:228:1: entryRuleNotMandatoryAttributeAchieveCondition : ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final void entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:229:1: ( ruleNotMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:230:1: ruleNotMandatoryAttributeAchieveCondition EOF
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition421);
            ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition428); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNotMandatoryAttributeAchieveCondition"


    // $ANTLR start "ruleNotMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:237:1: ruleNotMandatoryAttributeAchieveCondition : ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) ) ;
    public final void ruleNotMandatoryAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:241:2: ( ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:242:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:242:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:243:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:244:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:244:2: rule__NotMandatoryAttributeAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0_in_ruleNotMandatoryAttributeAchieveCondition454);
            rule__NotMandatoryAttributeAchieveCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNotMandatoryAttributeAchieveCondition"


    // $ANTLR start "entryRuleMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:256:1: entryRuleMandatoryAttributeAchieveCondition : ruleMandatoryAttributeAchieveCondition EOF ;
    public final void entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:257:1: ( ruleMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:258:1: ruleMandatoryAttributeAchieveCondition EOF
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition481);
            ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition488); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMandatoryAttributeAchieveCondition"


    // $ANTLR start "ruleMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:265:1: ruleMandatoryAttributeAchieveCondition : ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) ) ;
    public final void ruleMandatoryAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:269:2: ( ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:270:1: ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:270:1: ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:271:1: ( rule__MandatoryAttributeAchieveCondition__Group__0 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:272:1: ( rule__MandatoryAttributeAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:272:2: rule__MandatoryAttributeAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0_in_ruleMandatoryAttributeAchieveCondition514);
            rule__MandatoryAttributeAchieveCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMandatoryAttributeAchieveCondition"


    // $ANTLR start "entryRuleAttributeInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:284:1: entryRuleAttributeInvariantCondition : ruleAttributeInvariantCondition EOF ;
    public final void entryRuleAttributeInvariantCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:285:1: ( ruleAttributeInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:286:1: ruleAttributeInvariantCondition EOF
            {
             before(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition541);
            ruleAttributeInvariantCondition();

            state._fsp--;

             after(grammarAccess.getAttributeInvariantConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition548); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAttributeInvariantCondition"


    // $ANTLR start "ruleAttributeInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:293:1: ruleAttributeInvariantCondition : ( ( rule__AttributeInvariantCondition__Group__0 ) ) ;
    public final void ruleAttributeInvariantCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:297:2: ( ( ( rule__AttributeInvariantCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:298:1: ( ( rule__AttributeInvariantCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:298:1: ( ( rule__AttributeInvariantCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:299:1: ( rule__AttributeInvariantCondition__Group__0 )
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:300:1: ( rule__AttributeInvariantCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:300:2: rule__AttributeInvariantCondition__Group__0
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__0_in_ruleAttributeInvariantCondition574);
            rule__AttributeInvariantCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeInvariantConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttributeInvariantCondition"


    // $ANTLR start "entryRuleAttributeDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:312:1: entryRuleAttributeDependenceCondition : ruleAttributeDependenceCondition EOF ;
    public final void entryRuleAttributeDependenceCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:313:1: ( ruleAttributeDependenceCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:314:1: ruleAttributeDependenceCondition EOF
            {
             before(grammarAccess.getAttributeDependenceConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeDependenceCondition_in_entryRuleAttributeDependenceCondition601);
            ruleAttributeDependenceCondition();

            state._fsp--;

             after(grammarAccess.getAttributeDependenceConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeDependenceCondition608); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAttributeDependenceCondition"


    // $ANTLR start "ruleAttributeDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:321:1: ruleAttributeDependenceCondition : ( ( rule__AttributeDependenceCondition__Group__0 ) ) ;
    public final void ruleAttributeDependenceCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:325:2: ( ( ( rule__AttributeDependenceCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:326:1: ( ( rule__AttributeDependenceCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:326:1: ( ( rule__AttributeDependenceCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:327:1: ( rule__AttributeDependenceCondition__Group__0 )
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:328:1: ( rule__AttributeDependenceCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:328:2: rule__AttributeDependenceCondition__Group__0
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__0_in_ruleAttributeDependenceCondition634);
            rule__AttributeDependenceCondition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeDependenceConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttributeDependenceCondition"


    // $ANTLR start "entryRuleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:340:1: entryRuleCardinality : ruleCardinality EOF ;
    public final void entryRuleCardinality() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:341:1: ( ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:342:1: ruleCardinality EOF
            {
             before(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality661);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getCardinalityRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality668); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCardinality"


    // $ANTLR start "ruleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:349:1: ruleCardinality : ( ( rule__Cardinality__Alternatives ) ) ;
    public final void ruleCardinality() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:353:2: ( ( ( rule__Cardinality__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:354:1: ( ( rule__Cardinality__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:354:1: ( ( rule__Cardinality__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:355:1: ( rule__Cardinality__Alternatives )
            {
             before(grammarAccess.getCardinalityAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:356:1: ( rule__Cardinality__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:356:2: rule__Cardinality__Alternatives
            {
            pushFollow(FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality694);
            rule__Cardinality__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getCardinalityAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCardinality"


    // $ANTLR start "entryRuleAttribute"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:368:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:369:1: ( ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:370:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute721);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute728); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:377:1: ruleAttribute : ( ( rule__Attribute__Group__0 ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:381:2: ( ( ( rule__Attribute__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:382:1: ( ( rule__Attribute__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:382:1: ( ( rule__Attribute__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:383:1: ( rule__Attribute__Group__0 )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:384:1: ( rule__Attribute__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:384:2: rule__Attribute__Group__0
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0_in_ruleAttribute754);
            rule__Attribute__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:398:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:399:1: ( ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:400:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression783);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression790); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:407:1: ruleExpression : ( ruleOr ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:411:2: ( ( ruleOr ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:412:1: ( ruleOr )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:412:1: ( ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:413:1: ruleOr
            {
             before(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
            pushFollow(FOLLOW_ruleOr_in_ruleExpression816);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleOr"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:426:1: entryRuleOr : ruleOr EOF ;
    public final void entryRuleOr() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:427:1: ( ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:428:1: ruleOr EOF
            {
             before(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr842);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getOrRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr849); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOr"


    // $ANTLR start "ruleOr"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:435:1: ruleOr : ( ( rule__Or__Group__0 ) ) ;
    public final void ruleOr() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:439:2: ( ( ( rule__Or__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:440:1: ( ( rule__Or__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:440:1: ( ( rule__Or__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:441:1: ( rule__Or__Group__0 )
            {
             before(grammarAccess.getOrAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:442:1: ( rule__Or__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:442:2: rule__Or__Group__0
            {
            pushFollow(FOLLOW_rule__Or__Group__0_in_ruleOr875);
            rule__Or__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOrAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOr"


    // $ANTLR start "entryRuleAnd"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:454:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:455:1: ( ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:456:1: ruleAnd EOF
            {
             before(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd902);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getAndRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd909); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAnd"


    // $ANTLR start "ruleAnd"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:463:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:467:2: ( ( ( rule__And__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:468:1: ( ( rule__And__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:468:1: ( ( rule__And__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:469:1: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:470:1: ( rule__And__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:470:2: rule__And__Group__0
            {
            pushFollow(FOLLOW_rule__And__Group__0_in_ruleAnd935);
            rule__And__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAndAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAnd"


    // $ANTLR start "entryRulePrimary"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:482:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:483:1: ( rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:484:1: rulePrimary EOF
            {
             before(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary962);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary969); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:491:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:495:2: ( ( ( rule__Primary__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:496:1: ( ( rule__Primary__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:496:1: ( ( rule__Primary__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:497:1: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:498:1: ( rule__Primary__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:498:2: rule__Primary__Alternatives
            {
            pushFollow(FOLLOW_rule__Primary__Alternatives_in_rulePrimary995);
            rule__Primary__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "entryRuleAtomic"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:510:1: entryRuleAtomic : ruleAtomic EOF ;
    public final void entryRuleAtomic() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:511:1: ( ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:512:1: ruleAtomic EOF
            {
             before(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic1022);
            ruleAtomic();

            state._fsp--;

             after(grammarAccess.getAtomicRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic1029); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAtomic"


    // $ANTLR start "ruleAtomic"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:519:1: ruleAtomic : ( ( rule__Atomic__Alternatives ) ) ;
    public final void ruleAtomic() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:523:2: ( ( ( rule__Atomic__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:524:1: ( ( rule__Atomic__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:524:1: ( ( rule__Atomic__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:525:1: ( rule__Atomic__Alternatives )
            {
             before(grammarAccess.getAtomicAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:526:1: ( rule__Atomic__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:526:2: rule__Atomic__Alternatives
            {
            pushFollow(FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic1055);
            rule__Atomic__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAtomicAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAtomic"


    // $ANTLR start "entryRuleCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:538:1: entryRuleCondition : ruleCondition EOF ;
    public final void entryRuleCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:539:1: ( ruleCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:540:1: ruleCondition EOF
            {
             before(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_ruleCondition_in_entryRuleCondition1082);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCondition1089); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:547:1: ruleCondition : ( ( rule__Condition__Group__0 ) ) ;
    public final void ruleCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:551:2: ( ( ( rule__Condition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:552:1: ( ( rule__Condition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:552:1: ( ( rule__Condition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:553:1: ( rule__Condition__Group__0 )
            {
             before(grammarAccess.getConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:554:1: ( rule__Condition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:554:2: rule__Condition__Group__0
            {
            pushFollow(FOLLOW_rule__Condition__Group__0_in_ruleCondition1115);
            rule__Condition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCondition"


    // $ANTLR start "rule__AttributeAchieveCondition__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:566:1: rule__AttributeAchieveCondition__Alternatives : ( ( ruleMandatoryAttributeAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) );
    public final void rule__AttributeAchieveCondition__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:570:1: ( ( ruleMandatoryAttributeAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==18) ) {
                alt1=1;
            }
            else if ( (LA1_0==11) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:571:1: ( ruleMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:571:1: ( ruleMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:572:1: ruleMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1151);
                    ruleMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:577:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:577:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:578:1: ruleNotMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getAttributeAchieveConditionAccess().getNotMandatoryAttributeAchieveConditionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1168);
                    ruleNotMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getAttributeAchieveConditionAccess().getNotMandatoryAttributeAchieveConditionParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeAchieveCondition__Alternatives"


    // $ANTLR start "rule__Cardinality__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:588:1: rule__Cardinality__Alternatives : ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) );
    public final void rule__Cardinality__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:592:1: ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) )
            int alt2=4;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_INT) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==20) ) {
                    switch ( input.LA(3) ) {
                    case 22:
                        {
                        alt2=4;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt2=2;
                        }
                        break;
                    case 21:
                        {
                        alt2=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;
                    }

                }
                else if ( (LA2_1==EOF||LA2_1==13) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:593:1: ( RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:593:1: ( RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:594:1: RULE_INT
                    {
                     before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives1200); 
                     after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:599:6: ( ( rule__Cardinality__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:599:6: ( ( rule__Cardinality__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:600:1: ( rule__Cardinality__Group_1__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:601:1: ( rule__Cardinality__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:601:2: rule__Cardinality__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives1217);
                    rule__Cardinality__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:605:6: ( ( rule__Cardinality__Group_2__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:605:6: ( ( rule__Cardinality__Group_2__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:606:1: ( rule__Cardinality__Group_2__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_2()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:607:1: ( rule__Cardinality__Group_2__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:607:2: rule__Cardinality__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives1235);
                    rule__Cardinality__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:611:6: ( ( rule__Cardinality__Group_3__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:611:6: ( ( rule__Cardinality__Group_3__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:612:1: ( rule__Cardinality__Group_3__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_3()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:613:1: ( rule__Cardinality__Group_3__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:613:2: rule__Cardinality__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives1253);
                    rule__Cardinality__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Alternatives"


    // $ANTLR start "rule__Primary__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:622:1: rule__Primary__Alternatives : ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:626:1: ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt3=1;
                }
                break;
            case 26:
                {
                alt3=2;
                }
                break;
            case RULE_ID:
            case 11:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:627:1: ( ( rule__Primary__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:627:1: ( ( rule__Primary__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:628:1: ( rule__Primary__Group_0__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:629:1: ( rule__Primary__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:629:2: rule__Primary__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives1286);
                    rule__Primary__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:633:6: ( ( rule__Primary__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:633:6: ( ( rule__Primary__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:634:1: ( rule__Primary__Group_1__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:635:1: ( rule__Primary__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:635:2: rule__Primary__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1304);
                    rule__Primary__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:639:6: ( ruleAtomic )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:639:6: ( ruleAtomic )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:640:1: ruleAtomic
                    {
                     before(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1322);
                    ruleAtomic();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Alternatives"


    // $ANTLR start "rule__Atomic__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:650:1: rule__Atomic__Alternatives : ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) );
    public final void rule__Atomic__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:654:1: ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==11) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:655:1: ( ( rule__Atomic__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:655:1: ( ( rule__Atomic__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:656:1: ( rule__Atomic__Group_0__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:657:1: ( rule__Atomic__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:657:2: rule__Atomic__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1354);
                    rule__Atomic__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAtomicAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:661:6: ( ( rule__Atomic__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:661:6: ( ( rule__Atomic__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:662:1: ( rule__Atomic__Group_1__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:663:1: ( rule__Atomic__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:663:2: rule__Atomic__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1372);
                    rule__Atomic__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAtomicAccess().getGroup_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Alternatives"


    // $ANTLR start "rule__Condition__EntityAchieveConditionsAlternatives_1_1_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:672:1: rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 : ( ( ruleEntityAchieveCondition ) | ( ruleEntityAchieveConditionExist ) );
    public final void rule__Condition__EntityAchieveConditionsAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:676:1: ( ( ruleEntityAchieveCondition ) | ( ruleEntityAchieveConditionExist ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==11) ) {
                alt5=1;
            }
            else if ( (LA5_0==14) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:677:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:677:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:678:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionParserRuleCall_1_1_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Condition__EntityAchieveConditionsAlternatives_1_1_01405);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionParserRuleCall_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:683:6: ( ruleEntityAchieveConditionExist )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:683:6: ( ruleEntityAchieveConditionExist )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:684:1: ruleEntityAchieveConditionExist
                    {
                     before(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionExistParserRuleCall_1_1_0_1()); 
                    pushFollow(FOLLOW_ruleEntityAchieveConditionExist_in_rule__Condition__EntityAchieveConditionsAlternatives_1_1_01422);
                    ruleEntityAchieveConditionExist();

                    state._fsp--;

                     after(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionExistParserRuleCall_1_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__EntityAchieveConditionsAlternatives_1_1_0"


    // $ANTLR start "rule__EntityAchieveCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:696:1: rule__EntityAchieveCondition__Group__0 : rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1 ;
    public final void rule__EntityAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:700:1: ( rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:701:2: rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__0__Impl_in_rule__EntityAchieveCondition__Group__01452);
            rule__EntityAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__1_in_rule__EntityAchieveCondition__Group__01455);
            rule__EntityAchieveCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__0"


    // $ANTLR start "rule__EntityAchieveCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:708:1: rule__EntityAchieveCondition__Group__0__Impl : ( 'DEF' ) ;
    public final void rule__EntityAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:712:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:713:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:713:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:714:1: 'DEF'
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getDEFKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__EntityAchieveCondition__Group__0__Impl1483); 
             after(grammarAccess.getEntityAchieveConditionAccess().getDEFKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__0__Impl"


    // $ANTLR start "rule__EntityAchieveCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:727:1: rule__EntityAchieveCondition__Group__1 : rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2 ;
    public final void rule__EntityAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:731:1: ( rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:732:2: rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__1__Impl_in_rule__EntityAchieveCondition__Group__11514);
            rule__EntityAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__2_in_rule__EntityAchieveCondition__Group__11517);
            rule__EntityAchieveCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__1"


    // $ANTLR start "rule__EntityAchieveCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:739:1: rule__EntityAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:743:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:744:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:744:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:745:1: '('
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityAchieveCondition__Group__1__Impl1545); 
             after(grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__1__Impl"


    // $ANTLR start "rule__EntityAchieveCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:758:1: rule__EntityAchieveCondition__Group__2 : rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3 ;
    public final void rule__EntityAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:762:1: ( rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:763:2: rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__2__Impl_in_rule__EntityAchieveCondition__Group__21576);
            rule__EntityAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__3_in_rule__EntityAchieveCondition__Group__21579);
            rule__EntityAchieveCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__2"


    // $ANTLR start "rule__EntityAchieveCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:770:1: rule__EntityAchieveCondition__Group__2__Impl : ( ( rule__EntityAchieveCondition__NameAssignment_2 ) ) ;
    public final void rule__EntityAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:774:1: ( ( ( rule__EntityAchieveCondition__NameAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:775:1: ( ( rule__EntityAchieveCondition__NameAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:775:1: ( ( rule__EntityAchieveCondition__NameAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:776:1: ( rule__EntityAchieveCondition__NameAssignment_2 )
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getNameAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:777:1: ( rule__EntityAchieveCondition__NameAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:777:2: rule__EntityAchieveCondition__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__NameAssignment_2_in_rule__EntityAchieveCondition__Group__2__Impl1606);
            rule__EntityAchieveCondition__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEntityAchieveConditionAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__2__Impl"


    // $ANTLR start "rule__EntityAchieveCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:787:1: rule__EntityAchieveCondition__Group__3 : rule__EntityAchieveCondition__Group__3__Impl ;
    public final void rule__EntityAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:791:1: ( rule__EntityAchieveCondition__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:792:2: rule__EntityAchieveCondition__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__3__Impl_in_rule__EntityAchieveCondition__Group__31636);
            rule__EntityAchieveCondition__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__3"


    // $ANTLR start "rule__EntityAchieveCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:798:1: rule__EntityAchieveCondition__Group__3__Impl : ( ')' ) ;
    public final void rule__EntityAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:802:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:803:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:803:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:804:1: ')'
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getRightParenthesisKeyword_3()); 
            match(input,13,FOLLOW_13_in_rule__EntityAchieveCondition__Group__3__Impl1664); 
             after(grammarAccess.getEntityAchieveConditionAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__Group__3__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:825:1: rule__EntityAchieveConditionExist__Group__0 : rule__EntityAchieveConditionExist__Group__0__Impl rule__EntityAchieveConditionExist__Group__1 ;
    public final void rule__EntityAchieveConditionExist__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:829:1: ( rule__EntityAchieveConditionExist__Group__0__Impl rule__EntityAchieveConditionExist__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:830:2: rule__EntityAchieveConditionExist__Group__0__Impl rule__EntityAchieveConditionExist__Group__1
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__0__Impl_in_rule__EntityAchieveConditionExist__Group__01703);
            rule__EntityAchieveConditionExist__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__1_in_rule__EntityAchieveConditionExist__Group__01706);
            rule__EntityAchieveConditionExist__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__0"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:837:1: rule__EntityAchieveConditionExist__Group__0__Impl : ( 'EXISTS' ) ;
    public final void rule__EntityAchieveConditionExist__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:841:1: ( ( 'EXISTS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:842:1: ( 'EXISTS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:842:1: ( 'EXISTS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:843:1: 'EXISTS'
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getEXISTSKeyword_0()); 
            match(input,14,FOLLOW_14_in_rule__EntityAchieveConditionExist__Group__0__Impl1734); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getEXISTSKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__0__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:856:1: rule__EntityAchieveConditionExist__Group__1 : rule__EntityAchieveConditionExist__Group__1__Impl rule__EntityAchieveConditionExist__Group__2 ;
    public final void rule__EntityAchieveConditionExist__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:860:1: ( rule__EntityAchieveConditionExist__Group__1__Impl rule__EntityAchieveConditionExist__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:861:2: rule__EntityAchieveConditionExist__Group__1__Impl rule__EntityAchieveConditionExist__Group__2
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__1__Impl_in_rule__EntityAchieveConditionExist__Group__11765);
            rule__EntityAchieveConditionExist__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__2_in_rule__EntityAchieveConditionExist__Group__11768);
            rule__EntityAchieveConditionExist__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__1"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:868:1: rule__EntityAchieveConditionExist__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityAchieveConditionExist__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:872:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:873:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:873:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:874:1: '('
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityAchieveConditionExist__Group__1__Impl1796); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__1__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:887:1: rule__EntityAchieveConditionExist__Group__2 : rule__EntityAchieveConditionExist__Group__2__Impl rule__EntityAchieveConditionExist__Group__3 ;
    public final void rule__EntityAchieveConditionExist__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:891:1: ( rule__EntityAchieveConditionExist__Group__2__Impl rule__EntityAchieveConditionExist__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:892:2: rule__EntityAchieveConditionExist__Group__2__Impl rule__EntityAchieveConditionExist__Group__3
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__2__Impl_in_rule__EntityAchieveConditionExist__Group__21827);
            rule__EntityAchieveConditionExist__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__3_in_rule__EntityAchieveConditionExist__Group__21830);
            rule__EntityAchieveConditionExist__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__2"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:899:1: rule__EntityAchieveConditionExist__Group__2__Impl : ( 'DEF' ) ;
    public final void rule__EntityAchieveConditionExist__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:903:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:904:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:904:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:905:1: 'DEF'
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getDEFKeyword_2()); 
            match(input,11,FOLLOW_11_in_rule__EntityAchieveConditionExist__Group__2__Impl1858); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getDEFKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__2__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:918:1: rule__EntityAchieveConditionExist__Group__3 : rule__EntityAchieveConditionExist__Group__3__Impl rule__EntityAchieveConditionExist__Group__4 ;
    public final void rule__EntityAchieveConditionExist__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:922:1: ( rule__EntityAchieveConditionExist__Group__3__Impl rule__EntityAchieveConditionExist__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:923:2: rule__EntityAchieveConditionExist__Group__3__Impl rule__EntityAchieveConditionExist__Group__4
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__3__Impl_in_rule__EntityAchieveConditionExist__Group__31889);
            rule__EntityAchieveConditionExist__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__4_in_rule__EntityAchieveConditionExist__Group__31892);
            rule__EntityAchieveConditionExist__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__3"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:930:1: rule__EntityAchieveConditionExist__Group__3__Impl : ( '(' ) ;
    public final void rule__EntityAchieveConditionExist__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:934:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:935:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:935:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:936:1: '('
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__EntityAchieveConditionExist__Group__3__Impl1920); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__3__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:949:1: rule__EntityAchieveConditionExist__Group__4 : rule__EntityAchieveConditionExist__Group__4__Impl rule__EntityAchieveConditionExist__Group__5 ;
    public final void rule__EntityAchieveConditionExist__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:953:1: ( rule__EntityAchieveConditionExist__Group__4__Impl rule__EntityAchieveConditionExist__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:954:2: rule__EntityAchieveConditionExist__Group__4__Impl rule__EntityAchieveConditionExist__Group__5
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__4__Impl_in_rule__EntityAchieveConditionExist__Group__41951);
            rule__EntityAchieveConditionExist__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__5_in_rule__EntityAchieveConditionExist__Group__41954);
            rule__EntityAchieveConditionExist__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__4"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:961:1: rule__EntityAchieveConditionExist__Group__4__Impl : ( ( rule__EntityAchieveConditionExist__NameAssignment_4 ) ) ;
    public final void rule__EntityAchieveConditionExist__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:965:1: ( ( ( rule__EntityAchieveConditionExist__NameAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:966:1: ( ( rule__EntityAchieveConditionExist__NameAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:966:1: ( ( rule__EntityAchieveConditionExist__NameAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:967:1: ( rule__EntityAchieveConditionExist__NameAssignment_4 )
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getNameAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:968:1: ( rule__EntityAchieveConditionExist__NameAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:968:2: rule__EntityAchieveConditionExist__NameAssignment_4
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__NameAssignment_4_in_rule__EntityAchieveConditionExist__Group__4__Impl1981);
            rule__EntityAchieveConditionExist__NameAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getEntityAchieveConditionExistAccess().getNameAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__4__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:978:1: rule__EntityAchieveConditionExist__Group__5 : rule__EntityAchieveConditionExist__Group__5__Impl rule__EntityAchieveConditionExist__Group__6 ;
    public final void rule__EntityAchieveConditionExist__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:982:1: ( rule__EntityAchieveConditionExist__Group__5__Impl rule__EntityAchieveConditionExist__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:983:2: rule__EntityAchieveConditionExist__Group__5__Impl rule__EntityAchieveConditionExist__Group__6
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__5__Impl_in_rule__EntityAchieveConditionExist__Group__52011);
            rule__EntityAchieveConditionExist__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__6_in_rule__EntityAchieveConditionExist__Group__52014);
            rule__EntityAchieveConditionExist__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__5"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:990:1: rule__EntityAchieveConditionExist__Group__5__Impl : ( ')' ) ;
    public final void rule__EntityAchieveConditionExist__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:994:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:995:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:995:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:996:1: ')'
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__EntityAchieveConditionExist__Group__5__Impl2042); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__5__Impl"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1009:1: rule__EntityAchieveConditionExist__Group__6 : rule__EntityAchieveConditionExist__Group__6__Impl ;
    public final void rule__EntityAchieveConditionExist__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1013:1: ( rule__EntityAchieveConditionExist__Group__6__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1014:2: rule__EntityAchieveConditionExist__Group__6__Impl
            {
            pushFollow(FOLLOW_rule__EntityAchieveConditionExist__Group__6__Impl_in_rule__EntityAchieveConditionExist__Group__62073);
            rule__EntityAchieveConditionExist__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__6"


    // $ANTLR start "rule__EntityAchieveConditionExist__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1020:1: rule__EntityAchieveConditionExist__Group__6__Impl : ( ')' ) ;
    public final void rule__EntityAchieveConditionExist__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1024:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1025:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1025:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1026:1: ')'
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__EntityAchieveConditionExist__Group__6__Impl2101); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__Group__6__Impl"


    // $ANTLR start "rule__EntityInvariantCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1053:1: rule__EntityInvariantCondition__Group__0 : rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1 ;
    public final void rule__EntityInvariantCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1057:1: ( rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1058:2: rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__0__Impl_in_rule__EntityInvariantCondition__Group__02146);
            rule__EntityInvariantCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__1_in_rule__EntityInvariantCondition__Group__02149);
            rule__EntityInvariantCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__0"


    // $ANTLR start "rule__EntityInvariantCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1065:1: rule__EntityInvariantCondition__Group__0__Impl : ( 'MUL' ) ;
    public final void rule__EntityInvariantCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1069:1: ( ( 'MUL' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1070:1: ( 'MUL' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1070:1: ( 'MUL' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1071:1: 'MUL'
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0()); 
            match(input,15,FOLLOW_15_in_rule__EntityInvariantCondition__Group__0__Impl2177); 
             after(grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__0__Impl"


    // $ANTLR start "rule__EntityInvariantCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1084:1: rule__EntityInvariantCondition__Group__1 : rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2 ;
    public final void rule__EntityInvariantCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1088:1: ( rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1089:2: rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__1__Impl_in_rule__EntityInvariantCondition__Group__12208);
            rule__EntityInvariantCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__2_in_rule__EntityInvariantCondition__Group__12211);
            rule__EntityInvariantCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__1"


    // $ANTLR start "rule__EntityInvariantCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1096:1: rule__EntityInvariantCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityInvariantCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1100:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1101:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1101:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1102:1: '('
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityInvariantCondition__Group__1__Impl2239); 
             after(grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__1__Impl"


    // $ANTLR start "rule__EntityInvariantCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1115:1: rule__EntityInvariantCondition__Group__2 : rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3 ;
    public final void rule__EntityInvariantCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1119:1: ( rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1120:2: rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__2__Impl_in_rule__EntityInvariantCondition__Group__22270);
            rule__EntityInvariantCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__3_in_rule__EntityInvariantCondition__Group__22273);
            rule__EntityInvariantCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__2"


    // $ANTLR start "rule__EntityInvariantCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1127:1: rule__EntityInvariantCondition__Group__2__Impl : ( ( rule__EntityInvariantCondition__NameAssignment_2 ) ) ;
    public final void rule__EntityInvariantCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1131:1: ( ( ( rule__EntityInvariantCondition__NameAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1132:1: ( ( rule__EntityInvariantCondition__NameAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1132:1: ( ( rule__EntityInvariantCondition__NameAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1133:1: ( rule__EntityInvariantCondition__NameAssignment_2 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getNameAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1134:1: ( rule__EntityInvariantCondition__NameAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1134:2: rule__EntityInvariantCondition__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__NameAssignment_2_in_rule__EntityInvariantCondition__Group__2__Impl2300);
            rule__EntityInvariantCondition__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEntityInvariantConditionAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__2__Impl"


    // $ANTLR start "rule__EntityInvariantCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1144:1: rule__EntityInvariantCondition__Group__3 : rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4 ;
    public final void rule__EntityInvariantCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1148:1: ( rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1149:2: rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__3__Impl_in_rule__EntityInvariantCondition__Group__32330);
            rule__EntityInvariantCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__4_in_rule__EntityInvariantCondition__Group__32333);
            rule__EntityInvariantCondition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__3"


    // $ANTLR start "rule__EntityInvariantCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1156:1: rule__EntityInvariantCondition__Group__3__Impl : ( ',' ) ;
    public final void rule__EntityInvariantCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1160:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1161:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1161:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1162:1: ','
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3()); 
            match(input,16,FOLLOW_16_in_rule__EntityInvariantCondition__Group__3__Impl2361); 
             after(grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__3__Impl"


    // $ANTLR start "rule__EntityInvariantCondition__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1175:1: rule__EntityInvariantCondition__Group__4 : rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5 ;
    public final void rule__EntityInvariantCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1179:1: ( rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1180:2: rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__4__Impl_in_rule__EntityInvariantCondition__Group__42392);
            rule__EntityInvariantCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__5_in_rule__EntityInvariantCondition__Group__42395);
            rule__EntityInvariantCondition__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__4"


    // $ANTLR start "rule__EntityInvariantCondition__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1187:1: rule__EntityInvariantCondition__Group__4__Impl : ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) ) ;
    public final void rule__EntityInvariantCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1191:1: ( ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1192:1: ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1192:1: ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1193:1: ( rule__EntityInvariantCondition__CardinalityAssignment_4 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCardinalityAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1194:1: ( rule__EntityInvariantCondition__CardinalityAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1194:2: rule__EntityInvariantCondition__CardinalityAssignment_4
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__CardinalityAssignment_4_in_rule__EntityInvariantCondition__Group__4__Impl2422);
            rule__EntityInvariantCondition__CardinalityAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getEntityInvariantConditionAccess().getCardinalityAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__4__Impl"


    // $ANTLR start "rule__EntityInvariantCondition__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1204:1: rule__EntityInvariantCondition__Group__5 : rule__EntityInvariantCondition__Group__5__Impl ;
    public final void rule__EntityInvariantCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1208:1: ( rule__EntityInvariantCondition__Group__5__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1209:2: rule__EntityInvariantCondition__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__5__Impl_in_rule__EntityInvariantCondition__Group__52452);
            rule__EntityInvariantCondition__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__5"


    // $ANTLR start "rule__EntityInvariantCondition__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1215:1: rule__EntityInvariantCondition__Group__5__Impl : ( ')' ) ;
    public final void rule__EntityInvariantCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1219:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1220:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1220:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1221:1: ')'
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getRightParenthesisKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__EntityInvariantCondition__Group__5__Impl2480); 
             after(grammarAccess.getEntityInvariantConditionAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__Group__5__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1246:1: rule__EntityDependenceCondition__Group__0 : rule__EntityDependenceCondition__Group__0__Impl rule__EntityDependenceCondition__Group__1 ;
    public final void rule__EntityDependenceCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1250:1: ( rule__EntityDependenceCondition__Group__0__Impl rule__EntityDependenceCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1251:2: rule__EntityDependenceCondition__Group__0__Impl rule__EntityDependenceCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__0__Impl_in_rule__EntityDependenceCondition__Group__02523);
            rule__EntityDependenceCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__1_in_rule__EntityDependenceCondition__Group__02526);
            rule__EntityDependenceCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__0"


    // $ANTLR start "rule__EntityDependenceCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1258:1: rule__EntityDependenceCondition__Group__0__Impl : ( 'DEP' ) ;
    public final void rule__EntityDependenceCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1262:1: ( ( 'DEP' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1263:1: ( 'DEP' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1263:1: ( 'DEP' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1264:1: 'DEP'
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getDEPKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__EntityDependenceCondition__Group__0__Impl2554); 
             after(grammarAccess.getEntityDependenceConditionAccess().getDEPKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__0__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1277:1: rule__EntityDependenceCondition__Group__1 : rule__EntityDependenceCondition__Group__1__Impl rule__EntityDependenceCondition__Group__2 ;
    public final void rule__EntityDependenceCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1281:1: ( rule__EntityDependenceCondition__Group__1__Impl rule__EntityDependenceCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1282:2: rule__EntityDependenceCondition__Group__1__Impl rule__EntityDependenceCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__1__Impl_in_rule__EntityDependenceCondition__Group__12585);
            rule__EntityDependenceCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__2_in_rule__EntityDependenceCondition__Group__12588);
            rule__EntityDependenceCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__1"


    // $ANTLR start "rule__EntityDependenceCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1289:1: rule__EntityDependenceCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityDependenceCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1293:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1294:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1294:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1295:1: '('
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityDependenceCondition__Group__1__Impl2616); 
             after(grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__1__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1308:1: rule__EntityDependenceCondition__Group__2 : rule__EntityDependenceCondition__Group__2__Impl rule__EntityDependenceCondition__Group__3 ;
    public final void rule__EntityDependenceCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1312:1: ( rule__EntityDependenceCondition__Group__2__Impl rule__EntityDependenceCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1313:2: rule__EntityDependenceCondition__Group__2__Impl rule__EntityDependenceCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__2__Impl_in_rule__EntityDependenceCondition__Group__22647);
            rule__EntityDependenceCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__3_in_rule__EntityDependenceCondition__Group__22650);
            rule__EntityDependenceCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__2"


    // $ANTLR start "rule__EntityDependenceCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1320:1: rule__EntityDependenceCondition__Group__2__Impl : ( 'DEF' ) ;
    public final void rule__EntityDependenceCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1324:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1325:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1325:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1326:1: 'DEF'
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_2()); 
            match(input,11,FOLLOW_11_in_rule__EntityDependenceCondition__Group__2__Impl2678); 
             after(grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__2__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1339:1: rule__EntityDependenceCondition__Group__3 : rule__EntityDependenceCondition__Group__3__Impl rule__EntityDependenceCondition__Group__4 ;
    public final void rule__EntityDependenceCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1343:1: ( rule__EntityDependenceCondition__Group__3__Impl rule__EntityDependenceCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1344:2: rule__EntityDependenceCondition__Group__3__Impl rule__EntityDependenceCondition__Group__4
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__3__Impl_in_rule__EntityDependenceCondition__Group__32709);
            rule__EntityDependenceCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__4_in_rule__EntityDependenceCondition__Group__32712);
            rule__EntityDependenceCondition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__3"


    // $ANTLR start "rule__EntityDependenceCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1351:1: rule__EntityDependenceCondition__Group__3__Impl : ( '(' ) ;
    public final void rule__EntityDependenceCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1355:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1356:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1356:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1357:1: '('
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__EntityDependenceCondition__Group__3__Impl2740); 
             after(grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__3__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1370:1: rule__EntityDependenceCondition__Group__4 : rule__EntityDependenceCondition__Group__4__Impl rule__EntityDependenceCondition__Group__5 ;
    public final void rule__EntityDependenceCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1374:1: ( rule__EntityDependenceCondition__Group__4__Impl rule__EntityDependenceCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1375:2: rule__EntityDependenceCondition__Group__4__Impl rule__EntityDependenceCondition__Group__5
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__4__Impl_in_rule__EntityDependenceCondition__Group__42771);
            rule__EntityDependenceCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__5_in_rule__EntityDependenceCondition__Group__42774);
            rule__EntityDependenceCondition__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__4"


    // $ANTLR start "rule__EntityDependenceCondition__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1382:1: rule__EntityDependenceCondition__Group__4__Impl : ( ( rule__EntityDependenceCondition__Entity1Assignment_4 ) ) ;
    public final void rule__EntityDependenceCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1386:1: ( ( ( rule__EntityDependenceCondition__Entity1Assignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1387:1: ( ( rule__EntityDependenceCondition__Entity1Assignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1387:1: ( ( rule__EntityDependenceCondition__Entity1Assignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1388:1: ( rule__EntityDependenceCondition__Entity1Assignment_4 )
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getEntity1Assignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1389:1: ( rule__EntityDependenceCondition__Entity1Assignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1389:2: rule__EntityDependenceCondition__Entity1Assignment_4
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Entity1Assignment_4_in_rule__EntityDependenceCondition__Group__4__Impl2801);
            rule__EntityDependenceCondition__Entity1Assignment_4();

            state._fsp--;


            }

             after(grammarAccess.getEntityDependenceConditionAccess().getEntity1Assignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__4__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1399:1: rule__EntityDependenceCondition__Group__5 : rule__EntityDependenceCondition__Group__5__Impl rule__EntityDependenceCondition__Group__6 ;
    public final void rule__EntityDependenceCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1403:1: ( rule__EntityDependenceCondition__Group__5__Impl rule__EntityDependenceCondition__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1404:2: rule__EntityDependenceCondition__Group__5__Impl rule__EntityDependenceCondition__Group__6
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__5__Impl_in_rule__EntityDependenceCondition__Group__52831);
            rule__EntityDependenceCondition__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__6_in_rule__EntityDependenceCondition__Group__52834);
            rule__EntityDependenceCondition__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__5"


    // $ANTLR start "rule__EntityDependenceCondition__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1411:1: rule__EntityDependenceCondition__Group__5__Impl : ( ')' ) ;
    public final void rule__EntityDependenceCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1415:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1416:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1416:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1417:1: ')'
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__EntityDependenceCondition__Group__5__Impl2862); 
             after(grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__5__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1430:1: rule__EntityDependenceCondition__Group__6 : rule__EntityDependenceCondition__Group__6__Impl rule__EntityDependenceCondition__Group__7 ;
    public final void rule__EntityDependenceCondition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1434:1: ( rule__EntityDependenceCondition__Group__6__Impl rule__EntityDependenceCondition__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1435:2: rule__EntityDependenceCondition__Group__6__Impl rule__EntityDependenceCondition__Group__7
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__6__Impl_in_rule__EntityDependenceCondition__Group__62893);
            rule__EntityDependenceCondition__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__7_in_rule__EntityDependenceCondition__Group__62896);
            rule__EntityDependenceCondition__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__6"


    // $ANTLR start "rule__EntityDependenceCondition__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1442:1: rule__EntityDependenceCondition__Group__6__Impl : ( ',' ) ;
    public final void rule__EntityDependenceCondition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1446:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1447:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1447:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1448:1: ','
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getCommaKeyword_6()); 
            match(input,16,FOLLOW_16_in_rule__EntityDependenceCondition__Group__6__Impl2924); 
             after(grammarAccess.getEntityDependenceConditionAccess().getCommaKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__6__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__7"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1461:1: rule__EntityDependenceCondition__Group__7 : rule__EntityDependenceCondition__Group__7__Impl rule__EntityDependenceCondition__Group__8 ;
    public final void rule__EntityDependenceCondition__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1465:1: ( rule__EntityDependenceCondition__Group__7__Impl rule__EntityDependenceCondition__Group__8 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1466:2: rule__EntityDependenceCondition__Group__7__Impl rule__EntityDependenceCondition__Group__8
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__7__Impl_in_rule__EntityDependenceCondition__Group__72955);
            rule__EntityDependenceCondition__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__8_in_rule__EntityDependenceCondition__Group__72958);
            rule__EntityDependenceCondition__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__7"


    // $ANTLR start "rule__EntityDependenceCondition__Group__7__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1473:1: rule__EntityDependenceCondition__Group__7__Impl : ( 'DEF' ) ;
    public final void rule__EntityDependenceCondition__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1477:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1478:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1478:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1479:1: 'DEF'
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_7()); 
            match(input,11,FOLLOW_11_in_rule__EntityDependenceCondition__Group__7__Impl2986); 
             after(grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__7__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__8"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1492:1: rule__EntityDependenceCondition__Group__8 : rule__EntityDependenceCondition__Group__8__Impl rule__EntityDependenceCondition__Group__9 ;
    public final void rule__EntityDependenceCondition__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1496:1: ( rule__EntityDependenceCondition__Group__8__Impl rule__EntityDependenceCondition__Group__9 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1497:2: rule__EntityDependenceCondition__Group__8__Impl rule__EntityDependenceCondition__Group__9
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__8__Impl_in_rule__EntityDependenceCondition__Group__83017);
            rule__EntityDependenceCondition__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__9_in_rule__EntityDependenceCondition__Group__83020);
            rule__EntityDependenceCondition__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__8"


    // $ANTLR start "rule__EntityDependenceCondition__Group__8__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1504:1: rule__EntityDependenceCondition__Group__8__Impl : ( '(' ) ;
    public final void rule__EntityDependenceCondition__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1508:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1509:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1509:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1510:1: '('
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_8()); 
            match(input,12,FOLLOW_12_in_rule__EntityDependenceCondition__Group__8__Impl3048); 
             after(grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__8__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__9"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1523:1: rule__EntityDependenceCondition__Group__9 : rule__EntityDependenceCondition__Group__9__Impl rule__EntityDependenceCondition__Group__10 ;
    public final void rule__EntityDependenceCondition__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1527:1: ( rule__EntityDependenceCondition__Group__9__Impl rule__EntityDependenceCondition__Group__10 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1528:2: rule__EntityDependenceCondition__Group__9__Impl rule__EntityDependenceCondition__Group__10
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__9__Impl_in_rule__EntityDependenceCondition__Group__93079);
            rule__EntityDependenceCondition__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__10_in_rule__EntityDependenceCondition__Group__93082);
            rule__EntityDependenceCondition__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__9"


    // $ANTLR start "rule__EntityDependenceCondition__Group__9__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1535:1: rule__EntityDependenceCondition__Group__9__Impl : ( ( rule__EntityDependenceCondition__Entity2Assignment_9 ) ) ;
    public final void rule__EntityDependenceCondition__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1539:1: ( ( ( rule__EntityDependenceCondition__Entity2Assignment_9 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1540:1: ( ( rule__EntityDependenceCondition__Entity2Assignment_9 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1540:1: ( ( rule__EntityDependenceCondition__Entity2Assignment_9 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1541:1: ( rule__EntityDependenceCondition__Entity2Assignment_9 )
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getEntity2Assignment_9()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1542:1: ( rule__EntityDependenceCondition__Entity2Assignment_9 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1542:2: rule__EntityDependenceCondition__Entity2Assignment_9
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Entity2Assignment_9_in_rule__EntityDependenceCondition__Group__9__Impl3109);
            rule__EntityDependenceCondition__Entity2Assignment_9();

            state._fsp--;


            }

             after(grammarAccess.getEntityDependenceConditionAccess().getEntity2Assignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__9__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__10"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1552:1: rule__EntityDependenceCondition__Group__10 : rule__EntityDependenceCondition__Group__10__Impl rule__EntityDependenceCondition__Group__11 ;
    public final void rule__EntityDependenceCondition__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1556:1: ( rule__EntityDependenceCondition__Group__10__Impl rule__EntityDependenceCondition__Group__11 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1557:2: rule__EntityDependenceCondition__Group__10__Impl rule__EntityDependenceCondition__Group__11
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__10__Impl_in_rule__EntityDependenceCondition__Group__103139);
            rule__EntityDependenceCondition__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__11_in_rule__EntityDependenceCondition__Group__103142);
            rule__EntityDependenceCondition__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__10"


    // $ANTLR start "rule__EntityDependenceCondition__Group__10__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1564:1: rule__EntityDependenceCondition__Group__10__Impl : ( ')' ) ;
    public final void rule__EntityDependenceCondition__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1568:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1569:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1569:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1570:1: ')'
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_10()); 
            match(input,13,FOLLOW_13_in_rule__EntityDependenceCondition__Group__10__Impl3170); 
             after(grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__10__Impl"


    // $ANTLR start "rule__EntityDependenceCondition__Group__11"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1583:1: rule__EntityDependenceCondition__Group__11 : rule__EntityDependenceCondition__Group__11__Impl ;
    public final void rule__EntityDependenceCondition__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1587:1: ( rule__EntityDependenceCondition__Group__11__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1588:2: rule__EntityDependenceCondition__Group__11__Impl
            {
            pushFollow(FOLLOW_rule__EntityDependenceCondition__Group__11__Impl_in_rule__EntityDependenceCondition__Group__113201);
            rule__EntityDependenceCondition__Group__11__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__11"


    // $ANTLR start "rule__EntityDependenceCondition__Group__11__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1594:1: rule__EntityDependenceCondition__Group__11__Impl : ( ')' ) ;
    public final void rule__EntityDependenceCondition__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1598:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1599:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1599:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1600:1: ')'
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_11()); 
            match(input,13,FOLLOW_13_in_rule__EntityDependenceCondition__Group__11__Impl3229); 
             after(grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Group__11__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1637:1: rule__NotMandatoryAttributeAchieveCondition__Group__0 : rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1641:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1642:2: rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__03284);
            rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1_in_rule__NotMandatoryAttributeAchieveCondition__Group__03287);
            rule__NotMandatoryAttributeAchieveCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__0"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1649:1: rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl : ( 'DEF' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1653:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1654:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1654:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1655:1: 'DEF'
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl3315); 
             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1668:1: rule__NotMandatoryAttributeAchieveCondition__Group__1 : rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1672:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1673:2: rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__13346);
            rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2_in_rule__NotMandatoryAttributeAchieveCondition__Group__13349);
            rule__NotMandatoryAttributeAchieveCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__1"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1680:1: rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1684:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1685:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1685:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1686:1: '('
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl3377); 
             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1699:1: rule__NotMandatoryAttributeAchieveCondition__Group__2 : rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1703:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1704:2: rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__23408);
            rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3_in_rule__NotMandatoryAttributeAchieveCondition__Group__23411);
            rule__NotMandatoryAttributeAchieveCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__2"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1711:1: rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1715:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1716:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1716:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1717:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1718:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1718:2: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2_in_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl3438);
            rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1728:1: rule__NotMandatoryAttributeAchieveCondition__Group__3 : rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1732:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1733:2: rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__33468);
            rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4_in_rule__NotMandatoryAttributeAchieveCondition__Group__33471);
            rule__NotMandatoryAttributeAchieveCondition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__3"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1740:1: rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1744:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1745:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1745:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1746:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )*
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1747:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==16) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1747:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0_in_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl3498);
            	    rule__NotMandatoryAttributeAchieveCondition__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1757:1: rule__NotMandatoryAttributeAchieveCondition__Group__4 : rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1761:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1762:2: rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__43529);
            rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__4"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1768:1: rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl : ( ')' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1772:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1773:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1773:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1774:1: ')'
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_4()); 
            match(input,13,FOLLOW_13_in_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl3557); 
             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group_3__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1797:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__0 : rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1801:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1802:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__03598);
            rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__03601);
            rule__NotMandatoryAttributeAchieveCondition__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group_3__0"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1809:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl : ( ',' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1813:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1814:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1814:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1815:1: ','
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0()); 
            match(input,16,FOLLOW_16_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl3629); 
             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group_3__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1828:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__1 : rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1832:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1833:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__13660);
            rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group_3__1"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1839:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1843:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1844:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1844:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1845:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_3_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1846:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1846:2: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl3687);
            rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1860:1: rule__MandatoryAttributeAchieveCondition__Group__0 : rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1864:1: ( rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1865:2: rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__03721);
            rule__MandatoryAttributeAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1_in_rule__MandatoryAttributeAchieveCondition__Group__03724);
            rule__MandatoryAttributeAchieveCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__0"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1872:1: rule__MandatoryAttributeAchieveCondition__Group__0__Impl : ( 'MAN' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1876:1: ( ( 'MAN' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1877:1: ( 'MAN' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1877:1: ( 'MAN' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1878:1: 'MAN'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0()); 
            match(input,18,FOLLOW_18_in_rule__MandatoryAttributeAchieveCondition__Group__0__Impl3752); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__0__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1891:1: rule__MandatoryAttributeAchieveCondition__Group__1 : rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1895:1: ( rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1896:2: rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__13783);
            rule__MandatoryAttributeAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2_in_rule__MandatoryAttributeAchieveCondition__Group__13786);
            rule__MandatoryAttributeAchieveCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__1"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1903:1: rule__MandatoryAttributeAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1907:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1908:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1908:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1909:1: '('
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__1__Impl3814); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__1__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1922:1: rule__MandatoryAttributeAchieveCondition__Group__2 : rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1926:1: ( rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1927:2: rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__23845);
            rule__MandatoryAttributeAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3_in_rule__MandatoryAttributeAchieveCondition__Group__23848);
            rule__MandatoryAttributeAchieveCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__2"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1934:1: rule__MandatoryAttributeAchieveCondition__Group__2__Impl : ( 'DEF' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1938:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1939:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1939:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1940:1: 'DEF'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2()); 
            match(input,11,FOLLOW_11_in_rule__MandatoryAttributeAchieveCondition__Group__2__Impl3876); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__2__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1953:1: rule__MandatoryAttributeAchieveCondition__Group__3 : rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1957:1: ( rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1958:2: rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__33907);
            rule__MandatoryAttributeAchieveCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4_in_rule__MandatoryAttributeAchieveCondition__Group__33910);
            rule__MandatoryAttributeAchieveCondition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__3"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1965:1: rule__MandatoryAttributeAchieveCondition__Group__3__Impl : ( '(' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1969:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1970:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1970:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1971:1: '('
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__3__Impl3938); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__3__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1984:1: rule__MandatoryAttributeAchieveCondition__Group__4 : rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1988:1: ( rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1989:2: rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__43969);
            rule__MandatoryAttributeAchieveCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5_in_rule__MandatoryAttributeAchieveCondition__Group__43972);
            rule__MandatoryAttributeAchieveCondition__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__4"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:1996:1: rule__MandatoryAttributeAchieveCondition__Group__4__Impl : ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2000:1: ( ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2001:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2001:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2002:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2003:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2003:2: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4_in_rule__MandatoryAttributeAchieveCondition__Group__4__Impl3999);
            rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__4__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2013:1: rule__MandatoryAttributeAchieveCondition__Group__5 : rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2017:1: ( rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2018:2: rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__54029);
            rule__MandatoryAttributeAchieveCondition__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6_in_rule__MandatoryAttributeAchieveCondition__Group__54032);
            rule__MandatoryAttributeAchieveCondition__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__5"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2025:1: rule__MandatoryAttributeAchieveCondition__Group__5__Impl : ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2029:1: ( ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2030:1: ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2030:1: ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2031:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )*
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2032:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2032:2: rule__MandatoryAttributeAchieveCondition__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0_in_rule__MandatoryAttributeAchieveCondition__Group__5__Impl4059);
            	    rule__MandatoryAttributeAchieveCondition__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__5__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2042:1: rule__MandatoryAttributeAchieveCondition__Group__6 : rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2046:1: ( rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2047:2: rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__64090);
            rule__MandatoryAttributeAchieveCondition__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7_in_rule__MandatoryAttributeAchieveCondition__Group__64093);
            rule__MandatoryAttributeAchieveCondition__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__6"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2054:1: rule__MandatoryAttributeAchieveCondition__Group__6__Impl : ( ')' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2058:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2059:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2059:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2060:1: ')'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__6__Impl4121); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__6__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__7"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2073:1: rule__MandatoryAttributeAchieveCondition__Group__7 : rule__MandatoryAttributeAchieveCondition__Group__7__Impl ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2077:1: ( rule__MandatoryAttributeAchieveCondition__Group__7__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2078:2: rule__MandatoryAttributeAchieveCondition__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__74152);
            rule__MandatoryAttributeAchieveCondition__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__7"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group__7__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2084:1: rule__MandatoryAttributeAchieveCondition__Group__7__Impl : ( ')' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2088:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2089:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2089:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2090:1: ')'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_7()); 
            match(input,13,FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__7__Impl4180); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group__7__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group_5__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2119:1: rule__MandatoryAttributeAchieveCondition__Group_5__0 : rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2123:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2124:2: rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__04227);
            rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1_in_rule__MandatoryAttributeAchieveCondition__Group_5__04230);
            rule__MandatoryAttributeAchieveCondition__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group_5__0"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2131:1: rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl : ( ',' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2135:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2136:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2136:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2137:1: ','
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0()); 
            match(input,16,FOLLOW_16_in_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl4258); 
             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group_5__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2150:1: rule__MandatoryAttributeAchieveCondition__Group_5__1 : rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2154:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2155:2: rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__14289);
            rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group_5__1"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2161:1: rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl : ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2165:1: ( ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2166:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2166:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2167:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_5_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2168:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2168:2: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1_in_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl4316);
            rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2182:1: rule__AttributeInvariantCondition__Group__0 : rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1 ;
    public final void rule__AttributeInvariantCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2186:1: ( rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2187:2: rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__0__Impl_in_rule__AttributeInvariantCondition__Group__04350);
            rule__AttributeInvariantCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__1_in_rule__AttributeInvariantCondition__Group__04353);
            rule__AttributeInvariantCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__0"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2194:1: rule__AttributeInvariantCondition__Group__0__Impl : ( 'RUL' ) ;
    public final void rule__AttributeInvariantCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2198:1: ( ( 'RUL' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2199:1: ( 'RUL' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2199:1: ( 'RUL' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2200:1: 'RUL'
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0()); 
            match(input,19,FOLLOW_19_in_rule__AttributeInvariantCondition__Group__0__Impl4381); 
             after(grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__0__Impl"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2213:1: rule__AttributeInvariantCondition__Group__1 : rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2 ;
    public final void rule__AttributeInvariantCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2217:1: ( rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2218:2: rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__1__Impl_in_rule__AttributeInvariantCondition__Group__14412);
            rule__AttributeInvariantCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__2_in_rule__AttributeInvariantCondition__Group__14415);
            rule__AttributeInvariantCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__1"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2225:1: rule__AttributeInvariantCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__AttributeInvariantCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2229:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2230:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2230:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2231:1: '('
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__AttributeInvariantCondition__Group__1__Impl4443); 
             after(grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__1__Impl"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2244:1: rule__AttributeInvariantCondition__Group__2 : rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3 ;
    public final void rule__AttributeInvariantCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2248:1: ( rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2249:2: rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__2__Impl_in_rule__AttributeInvariantCondition__Group__24474);
            rule__AttributeInvariantCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__3_in_rule__AttributeInvariantCondition__Group__24477);
            rule__AttributeInvariantCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__2"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2256:1: rule__AttributeInvariantCondition__Group__2__Impl : ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) ) ;
    public final void rule__AttributeInvariantCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2260:1: ( ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2261:1: ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2261:1: ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2262:1: ( rule__AttributeInvariantCondition__ExpressionAssignment_2 )
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getExpressionAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2263:1: ( rule__AttributeInvariantCondition__ExpressionAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2263:2: rule__AttributeInvariantCondition__ExpressionAssignment_2
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__ExpressionAssignment_2_in_rule__AttributeInvariantCondition__Group__2__Impl4504);
            rule__AttributeInvariantCondition__ExpressionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getAttributeInvariantConditionAccess().getExpressionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__2__Impl"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2273:1: rule__AttributeInvariantCondition__Group__3 : rule__AttributeInvariantCondition__Group__3__Impl ;
    public final void rule__AttributeInvariantCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2277:1: ( rule__AttributeInvariantCondition__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2278:2: rule__AttributeInvariantCondition__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__3__Impl_in_rule__AttributeInvariantCondition__Group__34534);
            rule__AttributeInvariantCondition__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__3"


    // $ANTLR start "rule__AttributeInvariantCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2284:1: rule__AttributeInvariantCondition__Group__3__Impl : ( ')' ) ;
    public final void rule__AttributeInvariantCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2288:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2289:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2289:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2290:1: ')'
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getRightParenthesisKeyword_3()); 
            match(input,13,FOLLOW_13_in_rule__AttributeInvariantCondition__Group__3__Impl4562); 
             after(grammarAccess.getAttributeInvariantConditionAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__Group__3__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2311:1: rule__AttributeDependenceCondition__Group__0 : rule__AttributeDependenceCondition__Group__0__Impl rule__AttributeDependenceCondition__Group__1 ;
    public final void rule__AttributeDependenceCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2315:1: ( rule__AttributeDependenceCondition__Group__0__Impl rule__AttributeDependenceCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2316:2: rule__AttributeDependenceCondition__Group__0__Impl rule__AttributeDependenceCondition__Group__1
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__0__Impl_in_rule__AttributeDependenceCondition__Group__04601);
            rule__AttributeDependenceCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__1_in_rule__AttributeDependenceCondition__Group__04604);
            rule__AttributeDependenceCondition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__0"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2323:1: rule__AttributeDependenceCondition__Group__0__Impl : ( 'DEP' ) ;
    public final void rule__AttributeDependenceCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2327:1: ( ( 'DEP' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2328:1: ( 'DEP' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2328:1: ( 'DEP' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2329:1: 'DEP'
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getDEPKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__AttributeDependenceCondition__Group__0__Impl4632); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getDEPKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__0__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2342:1: rule__AttributeDependenceCondition__Group__1 : rule__AttributeDependenceCondition__Group__1__Impl rule__AttributeDependenceCondition__Group__2 ;
    public final void rule__AttributeDependenceCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2346:1: ( rule__AttributeDependenceCondition__Group__1__Impl rule__AttributeDependenceCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2347:2: rule__AttributeDependenceCondition__Group__1__Impl rule__AttributeDependenceCondition__Group__2
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__1__Impl_in_rule__AttributeDependenceCondition__Group__14663);
            rule__AttributeDependenceCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__2_in_rule__AttributeDependenceCondition__Group__14666);
            rule__AttributeDependenceCondition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__1"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2354:1: rule__AttributeDependenceCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__AttributeDependenceCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2358:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2359:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2359:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2360:1: '('
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__AttributeDependenceCondition__Group__1__Impl4694); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__1__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2373:1: rule__AttributeDependenceCondition__Group__2 : rule__AttributeDependenceCondition__Group__2__Impl rule__AttributeDependenceCondition__Group__3 ;
    public final void rule__AttributeDependenceCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2377:1: ( rule__AttributeDependenceCondition__Group__2__Impl rule__AttributeDependenceCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2378:2: rule__AttributeDependenceCondition__Group__2__Impl rule__AttributeDependenceCondition__Group__3
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__2__Impl_in_rule__AttributeDependenceCondition__Group__24725);
            rule__AttributeDependenceCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__3_in_rule__AttributeDependenceCondition__Group__24728);
            rule__AttributeDependenceCondition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__2"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2385:1: rule__AttributeDependenceCondition__Group__2__Impl : ( 'DEF' ) ;
    public final void rule__AttributeDependenceCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2389:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2390:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2390:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2391:1: 'DEF'
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_2()); 
            match(input,11,FOLLOW_11_in_rule__AttributeDependenceCondition__Group__2__Impl4756); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__2__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2404:1: rule__AttributeDependenceCondition__Group__3 : rule__AttributeDependenceCondition__Group__3__Impl rule__AttributeDependenceCondition__Group__4 ;
    public final void rule__AttributeDependenceCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2408:1: ( rule__AttributeDependenceCondition__Group__3__Impl rule__AttributeDependenceCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2409:2: rule__AttributeDependenceCondition__Group__3__Impl rule__AttributeDependenceCondition__Group__4
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__3__Impl_in_rule__AttributeDependenceCondition__Group__34787);
            rule__AttributeDependenceCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__4_in_rule__AttributeDependenceCondition__Group__34790);
            rule__AttributeDependenceCondition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__3"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2416:1: rule__AttributeDependenceCondition__Group__3__Impl : ( '(' ) ;
    public final void rule__AttributeDependenceCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2420:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2421:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2421:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2422:1: '('
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__AttributeDependenceCondition__Group__3__Impl4818); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__3__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2435:1: rule__AttributeDependenceCondition__Group__4 : rule__AttributeDependenceCondition__Group__4__Impl rule__AttributeDependenceCondition__Group__5 ;
    public final void rule__AttributeDependenceCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2439:1: ( rule__AttributeDependenceCondition__Group__4__Impl rule__AttributeDependenceCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2440:2: rule__AttributeDependenceCondition__Group__4__Impl rule__AttributeDependenceCondition__Group__5
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__4__Impl_in_rule__AttributeDependenceCondition__Group__44849);
            rule__AttributeDependenceCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__5_in_rule__AttributeDependenceCondition__Group__44852);
            rule__AttributeDependenceCondition__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__4"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2447:1: rule__AttributeDependenceCondition__Group__4__Impl : ( ( rule__AttributeDependenceCondition__Attributes1Assignment_4 ) ) ;
    public final void rule__AttributeDependenceCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2451:1: ( ( ( rule__AttributeDependenceCondition__Attributes1Assignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2452:1: ( ( rule__AttributeDependenceCondition__Attributes1Assignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2452:1: ( ( rule__AttributeDependenceCondition__Attributes1Assignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2453:1: ( rule__AttributeDependenceCondition__Attributes1Assignment_4 )
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1Assignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2454:1: ( rule__AttributeDependenceCondition__Attributes1Assignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2454:2: rule__AttributeDependenceCondition__Attributes1Assignment_4
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Attributes1Assignment_4_in_rule__AttributeDependenceCondition__Group__4__Impl4879);
            rule__AttributeDependenceCondition__Attributes1Assignment_4();

            state._fsp--;


            }

             after(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1Assignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__4__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2464:1: rule__AttributeDependenceCondition__Group__5 : rule__AttributeDependenceCondition__Group__5__Impl rule__AttributeDependenceCondition__Group__6 ;
    public final void rule__AttributeDependenceCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2468:1: ( rule__AttributeDependenceCondition__Group__5__Impl rule__AttributeDependenceCondition__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2469:2: rule__AttributeDependenceCondition__Group__5__Impl rule__AttributeDependenceCondition__Group__6
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__5__Impl_in_rule__AttributeDependenceCondition__Group__54909);
            rule__AttributeDependenceCondition__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__6_in_rule__AttributeDependenceCondition__Group__54912);
            rule__AttributeDependenceCondition__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__5"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2476:1: rule__AttributeDependenceCondition__Group__5__Impl : ( ( rule__AttributeDependenceCondition__Group_5__0 )* ) ;
    public final void rule__AttributeDependenceCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2480:1: ( ( ( rule__AttributeDependenceCondition__Group_5__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2481:1: ( ( rule__AttributeDependenceCondition__Group_5__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2481:1: ( ( rule__AttributeDependenceCondition__Group_5__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2482:1: ( rule__AttributeDependenceCondition__Group_5__0 )*
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getGroup_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2483:1: ( rule__AttributeDependenceCondition__Group_5__0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==16) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2483:2: rule__AttributeDependenceCondition__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group_5__0_in_rule__AttributeDependenceCondition__Group__5__Impl4939);
            	    rule__AttributeDependenceCondition__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getAttributeDependenceConditionAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__5__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2493:1: rule__AttributeDependenceCondition__Group__6 : rule__AttributeDependenceCondition__Group__6__Impl rule__AttributeDependenceCondition__Group__7 ;
    public final void rule__AttributeDependenceCondition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2497:1: ( rule__AttributeDependenceCondition__Group__6__Impl rule__AttributeDependenceCondition__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2498:2: rule__AttributeDependenceCondition__Group__6__Impl rule__AttributeDependenceCondition__Group__7
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__6__Impl_in_rule__AttributeDependenceCondition__Group__64970);
            rule__AttributeDependenceCondition__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__7_in_rule__AttributeDependenceCondition__Group__64973);
            rule__AttributeDependenceCondition__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__6"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2505:1: rule__AttributeDependenceCondition__Group__6__Impl : ( ')' ) ;
    public final void rule__AttributeDependenceCondition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2509:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2510:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2510:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2511:1: ')'
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__AttributeDependenceCondition__Group__6__Impl5001); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__6__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__7"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2524:1: rule__AttributeDependenceCondition__Group__7 : rule__AttributeDependenceCondition__Group__7__Impl rule__AttributeDependenceCondition__Group__8 ;
    public final void rule__AttributeDependenceCondition__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2528:1: ( rule__AttributeDependenceCondition__Group__7__Impl rule__AttributeDependenceCondition__Group__8 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2529:2: rule__AttributeDependenceCondition__Group__7__Impl rule__AttributeDependenceCondition__Group__8
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__7__Impl_in_rule__AttributeDependenceCondition__Group__75032);
            rule__AttributeDependenceCondition__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__8_in_rule__AttributeDependenceCondition__Group__75035);
            rule__AttributeDependenceCondition__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__7"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__7__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2536:1: rule__AttributeDependenceCondition__Group__7__Impl : ( ',' ) ;
    public final void rule__AttributeDependenceCondition__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2540:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2541:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2541:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2542:1: ','
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_7()); 
            match(input,16,FOLLOW_16_in_rule__AttributeDependenceCondition__Group__7__Impl5063); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__7__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__8"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2555:1: rule__AttributeDependenceCondition__Group__8 : rule__AttributeDependenceCondition__Group__8__Impl rule__AttributeDependenceCondition__Group__9 ;
    public final void rule__AttributeDependenceCondition__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2559:1: ( rule__AttributeDependenceCondition__Group__8__Impl rule__AttributeDependenceCondition__Group__9 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2560:2: rule__AttributeDependenceCondition__Group__8__Impl rule__AttributeDependenceCondition__Group__9
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__8__Impl_in_rule__AttributeDependenceCondition__Group__85094);
            rule__AttributeDependenceCondition__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__9_in_rule__AttributeDependenceCondition__Group__85097);
            rule__AttributeDependenceCondition__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__8"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__8__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2567:1: rule__AttributeDependenceCondition__Group__8__Impl : ( 'DEF' ) ;
    public final void rule__AttributeDependenceCondition__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2571:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2572:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2572:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2573:1: 'DEF'
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_8()); 
            match(input,11,FOLLOW_11_in_rule__AttributeDependenceCondition__Group__8__Impl5125); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__8__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__9"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2586:1: rule__AttributeDependenceCondition__Group__9 : rule__AttributeDependenceCondition__Group__9__Impl rule__AttributeDependenceCondition__Group__10 ;
    public final void rule__AttributeDependenceCondition__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2590:1: ( rule__AttributeDependenceCondition__Group__9__Impl rule__AttributeDependenceCondition__Group__10 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2591:2: rule__AttributeDependenceCondition__Group__9__Impl rule__AttributeDependenceCondition__Group__10
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__9__Impl_in_rule__AttributeDependenceCondition__Group__95156);
            rule__AttributeDependenceCondition__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__10_in_rule__AttributeDependenceCondition__Group__95159);
            rule__AttributeDependenceCondition__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__9"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__9__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2598:1: rule__AttributeDependenceCondition__Group__9__Impl : ( '(' ) ;
    public final void rule__AttributeDependenceCondition__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2602:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2603:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2603:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2604:1: '('
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_9()); 
            match(input,12,FOLLOW_12_in_rule__AttributeDependenceCondition__Group__9__Impl5187); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__9__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__10"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2617:1: rule__AttributeDependenceCondition__Group__10 : rule__AttributeDependenceCondition__Group__10__Impl rule__AttributeDependenceCondition__Group__11 ;
    public final void rule__AttributeDependenceCondition__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2621:1: ( rule__AttributeDependenceCondition__Group__10__Impl rule__AttributeDependenceCondition__Group__11 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2622:2: rule__AttributeDependenceCondition__Group__10__Impl rule__AttributeDependenceCondition__Group__11
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__10__Impl_in_rule__AttributeDependenceCondition__Group__105218);
            rule__AttributeDependenceCondition__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__11_in_rule__AttributeDependenceCondition__Group__105221);
            rule__AttributeDependenceCondition__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__10"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__10__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2629:1: rule__AttributeDependenceCondition__Group__10__Impl : ( ( rule__AttributeDependenceCondition__Attribute2Assignment_10 ) ) ;
    public final void rule__AttributeDependenceCondition__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2633:1: ( ( ( rule__AttributeDependenceCondition__Attribute2Assignment_10 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2634:1: ( ( rule__AttributeDependenceCondition__Attribute2Assignment_10 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2634:1: ( ( rule__AttributeDependenceCondition__Attribute2Assignment_10 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2635:1: ( rule__AttributeDependenceCondition__Attribute2Assignment_10 )
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getAttribute2Assignment_10()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2636:1: ( rule__AttributeDependenceCondition__Attribute2Assignment_10 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2636:2: rule__AttributeDependenceCondition__Attribute2Assignment_10
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Attribute2Assignment_10_in_rule__AttributeDependenceCondition__Group__10__Impl5248);
            rule__AttributeDependenceCondition__Attribute2Assignment_10();

            state._fsp--;


            }

             after(grammarAccess.getAttributeDependenceConditionAccess().getAttribute2Assignment_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__10__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__11"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2646:1: rule__AttributeDependenceCondition__Group__11 : rule__AttributeDependenceCondition__Group__11__Impl rule__AttributeDependenceCondition__Group__12 ;
    public final void rule__AttributeDependenceCondition__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2650:1: ( rule__AttributeDependenceCondition__Group__11__Impl rule__AttributeDependenceCondition__Group__12 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2651:2: rule__AttributeDependenceCondition__Group__11__Impl rule__AttributeDependenceCondition__Group__12
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__11__Impl_in_rule__AttributeDependenceCondition__Group__115278);
            rule__AttributeDependenceCondition__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__12_in_rule__AttributeDependenceCondition__Group__115281);
            rule__AttributeDependenceCondition__Group__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__11"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__11__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2658:1: rule__AttributeDependenceCondition__Group__11__Impl : ( ')' ) ;
    public final void rule__AttributeDependenceCondition__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2662:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2663:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2663:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2664:1: ')'
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_11()); 
            match(input,13,FOLLOW_13_in_rule__AttributeDependenceCondition__Group__11__Impl5309); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__11__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__12"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2677:1: rule__AttributeDependenceCondition__Group__12 : rule__AttributeDependenceCondition__Group__12__Impl ;
    public final void rule__AttributeDependenceCondition__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2681:1: ( rule__AttributeDependenceCondition__Group__12__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2682:2: rule__AttributeDependenceCondition__Group__12__Impl
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group__12__Impl_in_rule__AttributeDependenceCondition__Group__125340);
            rule__AttributeDependenceCondition__Group__12__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__12"


    // $ANTLR start "rule__AttributeDependenceCondition__Group__12__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2688:1: rule__AttributeDependenceCondition__Group__12__Impl : ( ')' ) ;
    public final void rule__AttributeDependenceCondition__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2692:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2693:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2693:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2694:1: ')'
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_12()); 
            match(input,13,FOLLOW_13_in_rule__AttributeDependenceCondition__Group__12__Impl5368); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_12()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group__12__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group_5__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2733:1: rule__AttributeDependenceCondition__Group_5__0 : rule__AttributeDependenceCondition__Group_5__0__Impl rule__AttributeDependenceCondition__Group_5__1 ;
    public final void rule__AttributeDependenceCondition__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2737:1: ( rule__AttributeDependenceCondition__Group_5__0__Impl rule__AttributeDependenceCondition__Group_5__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2738:2: rule__AttributeDependenceCondition__Group_5__0__Impl rule__AttributeDependenceCondition__Group_5__1
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group_5__0__Impl_in_rule__AttributeDependenceCondition__Group_5__05425);
            rule__AttributeDependenceCondition__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group_5__1_in_rule__AttributeDependenceCondition__Group_5__05428);
            rule__AttributeDependenceCondition__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group_5__0"


    // $ANTLR start "rule__AttributeDependenceCondition__Group_5__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2745:1: rule__AttributeDependenceCondition__Group_5__0__Impl : ( ',' ) ;
    public final void rule__AttributeDependenceCondition__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2749:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2750:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2750:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2751:1: ','
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_5_0()); 
            match(input,16,FOLLOW_16_in_rule__AttributeDependenceCondition__Group_5__0__Impl5456); 
             after(grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group_5__0__Impl"


    // $ANTLR start "rule__AttributeDependenceCondition__Group_5__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2764:1: rule__AttributeDependenceCondition__Group_5__1 : rule__AttributeDependenceCondition__Group_5__1__Impl ;
    public final void rule__AttributeDependenceCondition__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2768:1: ( rule__AttributeDependenceCondition__Group_5__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2769:2: rule__AttributeDependenceCondition__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Group_5__1__Impl_in_rule__AttributeDependenceCondition__Group_5__15487);
            rule__AttributeDependenceCondition__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group_5__1"


    // $ANTLR start "rule__AttributeDependenceCondition__Group_5__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2775:1: rule__AttributeDependenceCondition__Group_5__1__Impl : ( ( rule__AttributeDependenceCondition__Attributes1Assignment_5_1 ) ) ;
    public final void rule__AttributeDependenceCondition__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2779:1: ( ( ( rule__AttributeDependenceCondition__Attributes1Assignment_5_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2780:1: ( ( rule__AttributeDependenceCondition__Attributes1Assignment_5_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2780:1: ( ( rule__AttributeDependenceCondition__Attributes1Assignment_5_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2781:1: ( rule__AttributeDependenceCondition__Attributes1Assignment_5_1 )
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1Assignment_5_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2782:1: ( rule__AttributeDependenceCondition__Attributes1Assignment_5_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2782:2: rule__AttributeDependenceCondition__Attributes1Assignment_5_1
            {
            pushFollow(FOLLOW_rule__AttributeDependenceCondition__Attributes1Assignment_5_1_in_rule__AttributeDependenceCondition__Group_5__1__Impl5514);
            rule__AttributeDependenceCondition__Attributes1Assignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1Assignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Group_5__1__Impl"


    // $ANTLR start "rule__Cardinality__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2796:1: rule__Cardinality__Group_1__0 : rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 ;
    public final void rule__Cardinality__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2800:1: ( rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2801:2: rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__05548);
            rule__Cardinality__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__05551);
            rule__Cardinality__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_1__0"


    // $ANTLR start "rule__Cardinality__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2808:1: rule__Cardinality__Group_1__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2812:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2813:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2813:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2814:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl5578); 
             after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_1__0__Impl"


    // $ANTLR start "rule__Cardinality__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2825:1: rule__Cardinality__Group_1__1 : rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 ;
    public final void rule__Cardinality__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2829:1: ( rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2830:2: rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__15607);
            rule__Cardinality__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__15610);
            rule__Cardinality__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_1__1"


    // $ANTLR start "rule__Cardinality__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2837:1: rule__Cardinality__Group_1__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2841:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2842:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2842:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2843:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
            match(input,20,FOLLOW_20_in_rule__Cardinality__Group_1__1__Impl5638); 
             after(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_1__1__Impl"


    // $ANTLR start "rule__Cardinality__Group_1__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2856:1: rule__Cardinality__Group_1__2 : rule__Cardinality__Group_1__2__Impl ;
    public final void rule__Cardinality__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2860:1: ( rule__Cardinality__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2861:2: rule__Cardinality__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__25669);
            rule__Cardinality__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_1__2"


    // $ANTLR start "rule__Cardinality__Group_1__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2867:1: rule__Cardinality__Group_1__2__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2871:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2872:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2872:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2873:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl5696); 
             after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_1__2__Impl"


    // $ANTLR start "rule__Cardinality__Group_2__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2890:1: rule__Cardinality__Group_2__0 : rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 ;
    public final void rule__Cardinality__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2894:1: ( rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2895:2: rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__05731);
            rule__Cardinality__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__05734);
            rule__Cardinality__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_2__0"


    // $ANTLR start "rule__Cardinality__Group_2__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2902:1: rule__Cardinality__Group_2__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2906:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2907:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2907:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2908:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl5761); 
             after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_2__0__Impl"


    // $ANTLR start "rule__Cardinality__Group_2__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2919:1: rule__Cardinality__Group_2__1 : rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 ;
    public final void rule__Cardinality__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2923:1: ( rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2924:2: rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__15790);
            rule__Cardinality__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__15793);
            rule__Cardinality__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_2__1"


    // $ANTLR start "rule__Cardinality__Group_2__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2931:1: rule__Cardinality__Group_2__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2935:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2936:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2936:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2937:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
            match(input,20,FOLLOW_20_in_rule__Cardinality__Group_2__1__Impl5821); 
             after(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_2__1__Impl"


    // $ANTLR start "rule__Cardinality__Group_2__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2950:1: rule__Cardinality__Group_2__2 : rule__Cardinality__Group_2__2__Impl ;
    public final void rule__Cardinality__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2954:1: ( rule__Cardinality__Group_2__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2955:2: rule__Cardinality__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__25852);
            rule__Cardinality__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_2__2"


    // $ANTLR start "rule__Cardinality__Group_2__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2961:1: rule__Cardinality__Group_2__2__Impl : ( '*' ) ;
    public final void rule__Cardinality__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2965:1: ( ( '*' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2966:1: ( '*' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2966:1: ( '*' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2967:1: '*'
            {
             before(grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
            match(input,21,FOLLOW_21_in_rule__Cardinality__Group_2__2__Impl5880); 
             after(grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_2__2__Impl"


    // $ANTLR start "rule__Cardinality__Group_3__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2986:1: rule__Cardinality__Group_3__0 : rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 ;
    public final void rule__Cardinality__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2990:1: ( rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2991:2: rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__05917);
            rule__Cardinality__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__05920);
            rule__Cardinality__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_3__0"


    // $ANTLR start "rule__Cardinality__Group_3__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:2998:1: rule__Cardinality__Group_3__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3002:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3003:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3003:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3004:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl5947); 
             after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_3__0__Impl"


    // $ANTLR start "rule__Cardinality__Group_3__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3015:1: rule__Cardinality__Group_3__1 : rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 ;
    public final void rule__Cardinality__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3019:1: ( rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3020:2: rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__15976);
            rule__Cardinality__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__15979);
            rule__Cardinality__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_3__1"


    // $ANTLR start "rule__Cardinality__Group_3__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3027:1: rule__Cardinality__Group_3__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3031:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3032:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3032:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3033:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
            match(input,20,FOLLOW_20_in_rule__Cardinality__Group_3__1__Impl6007); 
             after(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_3__1__Impl"


    // $ANTLR start "rule__Cardinality__Group_3__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3046:1: rule__Cardinality__Group_3__2 : rule__Cardinality__Group_3__2__Impl ;
    public final void rule__Cardinality__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3050:1: ( rule__Cardinality__Group_3__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3051:2: rule__Cardinality__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__26038);
            rule__Cardinality__Group_3__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_3__2"


    // $ANTLR start "rule__Cardinality__Group_3__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3057:1: rule__Cardinality__Group_3__2__Impl : ( '+' ) ;
    public final void rule__Cardinality__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3061:1: ( ( '+' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3062:1: ( '+' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3062:1: ( '+' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3063:1: '+'
            {
             before(grammarAccess.getCardinalityAccess().getPlusSignKeyword_3_2()); 
            match(input,22,FOLLOW_22_in_rule__Cardinality__Group_3__2__Impl6066); 
             after(grammarAccess.getCardinalityAccess().getPlusSignKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group_3__2__Impl"


    // $ANTLR start "rule__Attribute__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3082:1: rule__Attribute__Group__0 : rule__Attribute__Group__0__Impl rule__Attribute__Group__1 ;
    public final void rule__Attribute__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3086:1: ( rule__Attribute__Group__0__Impl rule__Attribute__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3087:2: rule__Attribute__Group__0__Impl rule__Attribute__Group__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__06103);
            rule__Attribute__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__06106);
            rule__Attribute__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__0"


    // $ANTLR start "rule__Attribute__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3094:1: rule__Attribute__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3098:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3099:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3099:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3100:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group__0__Impl6133); 
             after(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__0__Impl"


    // $ANTLR start "rule__Attribute__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3111:1: rule__Attribute__Group__1 : rule__Attribute__Group__1__Impl rule__Attribute__Group__2 ;
    public final void rule__Attribute__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3115:1: ( rule__Attribute__Group__1__Impl rule__Attribute__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3116:2: rule__Attribute__Group__1__Impl rule__Attribute__Group__2
            {
            pushFollow(FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__16162);
            rule__Attribute__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__16165);
            rule__Attribute__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__1"


    // $ANTLR start "rule__Attribute__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3123:1: rule__Attribute__Group__1__Impl : ( '.' ) ;
    public final void rule__Attribute__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3127:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3128:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3128:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3129:1: '.'
            {
             before(grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
            match(input,23,FOLLOW_23_in_rule__Attribute__Group__1__Impl6193); 
             after(grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__1__Impl"


    // $ANTLR start "rule__Attribute__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3142:1: rule__Attribute__Group__2 : rule__Attribute__Group__2__Impl rule__Attribute__Group__3 ;
    public final void rule__Attribute__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3146:1: ( rule__Attribute__Group__2__Impl rule__Attribute__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3147:2: rule__Attribute__Group__2__Impl rule__Attribute__Group__3
            {
            pushFollow(FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__26224);
            rule__Attribute__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__26227);
            rule__Attribute__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__2"


    // $ANTLR start "rule__Attribute__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3154:1: rule__Attribute__Group__2__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3158:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3159:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3159:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3160:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group__2__Impl6254); 
             after(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__2__Impl"


    // $ANTLR start "rule__Attribute__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3171:1: rule__Attribute__Group__3 : rule__Attribute__Group__3__Impl ;
    public final void rule__Attribute__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3175:1: ( rule__Attribute__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3176:2: rule__Attribute__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__36283);
            rule__Attribute__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__3"


    // $ANTLR start "rule__Attribute__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3182:1: rule__Attribute__Group__3__Impl : ( ( rule__Attribute__Group_3__0 )* ) ;
    public final void rule__Attribute__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3186:1: ( ( ( rule__Attribute__Group_3__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3187:1: ( ( rule__Attribute__Group_3__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3187:1: ( ( rule__Attribute__Group_3__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3188:1: ( rule__Attribute__Group_3__0 )*
            {
             before(grammarAccess.getAttributeAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3189:1: ( rule__Attribute__Group_3__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==23) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3189:2: rule__Attribute__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__Attribute__Group_3__0_in_rule__Attribute__Group__3__Impl6310);
            	    rule__Attribute__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getAttributeAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__3__Impl"


    // $ANTLR start "rule__Attribute__Group_3__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3207:1: rule__Attribute__Group_3__0 : rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1 ;
    public final void rule__Attribute__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3211:1: ( rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3212:2: rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group_3__0__Impl_in_rule__Attribute__Group_3__06349);
            rule__Attribute__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group_3__1_in_rule__Attribute__Group_3__06352);
            rule__Attribute__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_3__0"


    // $ANTLR start "rule__Attribute__Group_3__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3219:1: rule__Attribute__Group_3__0__Impl : ( '.' ) ;
    public final void rule__Attribute__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3223:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3224:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3224:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3225:1: '.'
            {
             before(grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            match(input,23,FOLLOW_23_in_rule__Attribute__Group_3__0__Impl6380); 
             after(grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_3__0__Impl"


    // $ANTLR start "rule__Attribute__Group_3__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3238:1: rule__Attribute__Group_3__1 : rule__Attribute__Group_3__1__Impl ;
    public final void rule__Attribute__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3242:1: ( rule__Attribute__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3243:2: rule__Attribute__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group_3__1__Impl_in_rule__Attribute__Group_3__16411);
            rule__Attribute__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_3__1"


    // $ANTLR start "rule__Attribute__Group_3__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3249:1: rule__Attribute__Group_3__1__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3253:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3254:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3254:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3255:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group_3__1__Impl6438); 
             after(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_3__1__Impl"


    // $ANTLR start "rule__Or__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3270:1: rule__Or__Group__0 : rule__Or__Group__0__Impl rule__Or__Group__1 ;
    public final void rule__Or__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3274:1: ( rule__Or__Group__0__Impl rule__Or__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3275:2: rule__Or__Group__0__Impl rule__Or__Group__1
            {
            pushFollow(FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__06471);
            rule__Or__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group__1_in_rule__Or__Group__06474);
            rule__Or__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__0"


    // $ANTLR start "rule__Or__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3282:1: rule__Or__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Or__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3286:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3287:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3287:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3288:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl6501);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__0__Impl"


    // $ANTLR start "rule__Or__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3299:1: rule__Or__Group__1 : rule__Or__Group__1__Impl ;
    public final void rule__Or__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3303:1: ( rule__Or__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3304:2: rule__Or__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__16530);
            rule__Or__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__1"


    // $ANTLR start "rule__Or__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3310:1: rule__Or__Group__1__Impl : ( ( rule__Or__Group_1__0 )* ) ;
    public final void rule__Or__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3314:1: ( ( ( rule__Or__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3315:1: ( ( rule__Or__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3315:1: ( ( rule__Or__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3316:1: ( rule__Or__Group_1__0 )*
            {
             before(grammarAccess.getOrAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3317:1: ( rule__Or__Group_1__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==24) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3317:2: rule__Or__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl6557);
            	    rule__Or__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getOrAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__1__Impl"


    // $ANTLR start "rule__Or__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3331:1: rule__Or__Group_1__0 : rule__Or__Group_1__0__Impl rule__Or__Group_1__1 ;
    public final void rule__Or__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3335:1: ( rule__Or__Group_1__0__Impl rule__Or__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3336:2: rule__Or__Group_1__0__Impl rule__Or__Group_1__1
            {
            pushFollow(FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__06592);
            rule__Or__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__06595);
            rule__Or__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__0"


    // $ANTLR start "rule__Or__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3343:1: rule__Or__Group_1__0__Impl : ( () ) ;
    public final void rule__Or__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3347:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3348:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3348:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3349:1: ()
            {
             before(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3350:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3352:1: 
            {
            }

             after(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__0__Impl"


    // $ANTLR start "rule__Or__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3362:1: rule__Or__Group_1__1 : rule__Or__Group_1__1__Impl rule__Or__Group_1__2 ;
    public final void rule__Or__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3366:1: ( rule__Or__Group_1__1__Impl rule__Or__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3367:2: rule__Or__Group_1__1__Impl rule__Or__Group_1__2
            {
            pushFollow(FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__16653);
            rule__Or__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__16656);
            rule__Or__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__1"


    // $ANTLR start "rule__Or__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3374:1: rule__Or__Group_1__1__Impl : ( 'OR' ) ;
    public final void rule__Or__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3378:1: ( ( 'OR' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3379:1: ( 'OR' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3379:1: ( 'OR' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3380:1: 'OR'
            {
             before(grammarAccess.getOrAccess().getORKeyword_1_1()); 
            match(input,24,FOLLOW_24_in_rule__Or__Group_1__1__Impl6684); 
             after(grammarAccess.getOrAccess().getORKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__1__Impl"


    // $ANTLR start "rule__Or__Group_1__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3393:1: rule__Or__Group_1__2 : rule__Or__Group_1__2__Impl ;
    public final void rule__Or__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3397:1: ( rule__Or__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3398:2: rule__Or__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__26715);
            rule__Or__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__2"


    // $ANTLR start "rule__Or__Group_1__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3404:1: rule__Or__Group_1__2__Impl : ( ( rule__Or__RightAssignment_1_2 ) ) ;
    public final void rule__Or__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3408:1: ( ( ( rule__Or__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3409:1: ( ( rule__Or__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3409:1: ( ( rule__Or__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3410:1: ( rule__Or__RightAssignment_1_2 )
            {
             before(grammarAccess.getOrAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3411:1: ( rule__Or__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3411:2: rule__Or__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl6742);
            rule__Or__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getOrAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__2__Impl"


    // $ANTLR start "rule__And__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3427:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3431:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3432:2: rule__And__Group__0__Impl rule__And__Group__1
            {
            pushFollow(FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__06778);
            rule__And__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group__1_in_rule__And__Group__06781);
            rule__And__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__0"


    // $ANTLR start "rule__And__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3439:1: rule__And__Group__0__Impl : ( rulePrimary ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3443:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3444:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3444:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3445:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__Group__0__Impl6808);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__0__Impl"


    // $ANTLR start "rule__And__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3456:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3460:1: ( rule__And__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3461:2: rule__And__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__16837);
            rule__And__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__1"


    // $ANTLR start "rule__And__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3467:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3471:1: ( ( ( rule__And__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3472:1: ( ( rule__And__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3472:1: ( ( rule__And__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3473:1: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3474:1: ( rule__And__Group_1__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==25) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3474:2: rule__And__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl6864);
            	    rule__And__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getAndAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__1__Impl"


    // $ANTLR start "rule__And__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3488:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3492:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3493:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
            {
            pushFollow(FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__06899);
            rule__And__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__06902);
            rule__And__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__0"


    // $ANTLR start "rule__And__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3500:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3504:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3505:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3505:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3506:1: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3507:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3509:1: 
            {
            }

             after(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__0__Impl"


    // $ANTLR start "rule__And__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3519:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3523:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3524:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__16960);
            rule__And__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__16963);
            rule__And__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__1"


    // $ANTLR start "rule__And__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3531:1: rule__And__Group_1__1__Impl : ( 'AND' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3535:1: ( ( 'AND' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3536:1: ( 'AND' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3536:1: ( 'AND' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3537:1: 'AND'
            {
             before(grammarAccess.getAndAccess().getANDKeyword_1_1()); 
            match(input,25,FOLLOW_25_in_rule__And__Group_1__1__Impl6991); 
             after(grammarAccess.getAndAccess().getANDKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__1__Impl"


    // $ANTLR start "rule__And__Group_1__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3550:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3554:1: ( rule__And__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3555:2: rule__And__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__27022);
            rule__And__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__2"


    // $ANTLR start "rule__And__Group_1__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3561:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3565:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3566:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3566:1: ( ( rule__And__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3567:1: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3568:1: ( rule__And__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3568:2: rule__And__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl7049);
            rule__And__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getAndAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__2__Impl"


    // $ANTLR start "rule__Primary__Group_0__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3584:1: rule__Primary__Group_0__0 : rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 ;
    public final void rule__Primary__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3588:1: ( rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3589:2: rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__07085);
            rule__Primary__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__07088);
            rule__Primary__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_0__0"


    // $ANTLR start "rule__Primary__Group_0__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3596:1: rule__Primary__Group_0__0__Impl : ( '(' ) ;
    public final void rule__Primary__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3600:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3601:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3601:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3602:1: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0()); 
            match(input,12,FOLLOW_12_in_rule__Primary__Group_0__0__Impl7116); 
             after(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_0__0__Impl"


    // $ANTLR start "rule__Primary__Group_0__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3615:1: rule__Primary__Group_0__1 : rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 ;
    public final void rule__Primary__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3619:1: ( rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3620:2: rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__17147);
            rule__Primary__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__17150);
            rule__Primary__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_0__1"


    // $ANTLR start "rule__Primary__Group_0__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3627:1: rule__Primary__Group_0__1__Impl : ( ruleExpression ) ;
    public final void rule__Primary__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3631:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3632:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3632:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3633:1: ruleExpression
            {
             before(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl7177);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_0__1__Impl"


    // $ANTLR start "rule__Primary__Group_0__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3644:1: rule__Primary__Group_0__2 : rule__Primary__Group_0__2__Impl ;
    public final void rule__Primary__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3648:1: ( rule__Primary__Group_0__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3649:2: rule__Primary__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__27206);
            rule__Primary__Group_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_0__2"


    // $ANTLR start "rule__Primary__Group_0__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3655:1: rule__Primary__Group_0__2__Impl : ( ')' ) ;
    public final void rule__Primary__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3659:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3660:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3660:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3661:1: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2()); 
            match(input,13,FOLLOW_13_in_rule__Primary__Group_0__2__Impl7234); 
             after(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_0__2__Impl"


    // $ANTLR start "rule__Primary__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3680:1: rule__Primary__Group_1__0 : rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 ;
    public final void rule__Primary__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3684:1: ( rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3685:2: rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__07271);
            rule__Primary__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__07274);
            rule__Primary__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_1__0"


    // $ANTLR start "rule__Primary__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3692:1: rule__Primary__Group_1__0__Impl : ( () ) ;
    public final void rule__Primary__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3696:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3697:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3697:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3698:1: ()
            {
             before(grammarAccess.getPrimaryAccess().getNotAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3699:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3701:1: 
            {
            }

             after(grammarAccess.getPrimaryAccess().getNotAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_1__0__Impl"


    // $ANTLR start "rule__Primary__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3711:1: rule__Primary__Group_1__1 : rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 ;
    public final void rule__Primary__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3715:1: ( rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3716:2: rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__17332);
            rule__Primary__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__17335);
            rule__Primary__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_1__1"


    // $ANTLR start "rule__Primary__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3723:1: rule__Primary__Group_1__1__Impl : ( 'NOT' ) ;
    public final void rule__Primary__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3727:1: ( ( 'NOT' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3728:1: ( 'NOT' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3728:1: ( 'NOT' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3729:1: 'NOT'
            {
             before(grammarAccess.getPrimaryAccess().getNOTKeyword_1_1()); 
            match(input,26,FOLLOW_26_in_rule__Primary__Group_1__1__Impl7363); 
             after(grammarAccess.getPrimaryAccess().getNOTKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_1__1__Impl"


    // $ANTLR start "rule__Primary__Group_1__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3742:1: rule__Primary__Group_1__2 : rule__Primary__Group_1__2__Impl ;
    public final void rule__Primary__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3746:1: ( rule__Primary__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3747:2: rule__Primary__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__27394);
            rule__Primary__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_1__2"


    // $ANTLR start "rule__Primary__Group_1__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3753:1: rule__Primary__Group_1__2__Impl : ( ( rule__Primary__ExpressionAssignment_1_2 ) ) ;
    public final void rule__Primary__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3757:1: ( ( ( rule__Primary__ExpressionAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3758:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3758:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3759:1: ( rule__Primary__ExpressionAssignment_1_2 )
            {
             before(grammarAccess.getPrimaryAccess().getExpressionAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3760:1: ( rule__Primary__ExpressionAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3760:2: rule__Primary__ExpressionAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl7421);
            rule__Primary__ExpressionAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryAccess().getExpressionAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_1__2__Impl"


    // $ANTLR start "rule__Atomic__Group_0__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3776:1: rule__Atomic__Group_0__0 : rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 ;
    public final void rule__Atomic__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3780:1: ( rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3781:2: rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__07457);
            rule__Atomic__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__07460);
            rule__Atomic__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__0"


    // $ANTLR start "rule__Atomic__Group_0__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3788:1: rule__Atomic__Group_0__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3792:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3793:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3793:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3794:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3795:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3797:1: 
            {
            }

             after(grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__0__Impl"


    // $ANTLR start "rule__Atomic__Group_0__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3807:1: rule__Atomic__Group_0__1 : rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 ;
    public final void rule__Atomic__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3811:1: ( rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3812:2: rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__17518);
            rule__Atomic__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__17521);
            rule__Atomic__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__1"


    // $ANTLR start "rule__Atomic__Group_0__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3819:1: rule__Atomic__Group_0__1__Impl : ( 'DEF' ) ;
    public final void rule__Atomic__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3823:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3824:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3824:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3825:1: 'DEF'
            {
             before(grammarAccess.getAtomicAccess().getDEFKeyword_0_1()); 
            match(input,11,FOLLOW_11_in_rule__Atomic__Group_0__1__Impl7549); 
             after(grammarAccess.getAtomicAccess().getDEFKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__1__Impl"


    // $ANTLR start "rule__Atomic__Group_0__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3838:1: rule__Atomic__Group_0__2 : rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 ;
    public final void rule__Atomic__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3842:1: ( rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3843:2: rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__27580);
            rule__Atomic__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__27583);
            rule__Atomic__Group_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__2"


    // $ANTLR start "rule__Atomic__Group_0__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3850:1: rule__Atomic__Group_0__2__Impl : ( '(' ) ;
    public final void rule__Atomic__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3854:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3855:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3855:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3856:1: '('
            {
             before(grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2()); 
            match(input,12,FOLLOW_12_in_rule__Atomic__Group_0__2__Impl7611); 
             after(grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__2__Impl"


    // $ANTLR start "rule__Atomic__Group_0__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3869:1: rule__Atomic__Group_0__3 : rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 ;
    public final void rule__Atomic__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3873:1: ( rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3874:2: rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__37642);
            rule__Atomic__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__37645);
            rule__Atomic__Group_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__3"


    // $ANTLR start "rule__Atomic__Group_0__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3881:1: rule__Atomic__Group_0__3__Impl : ( ( rule__Atomic__NameAssignment_0_3 ) ) ;
    public final void rule__Atomic__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3885:1: ( ( ( rule__Atomic__NameAssignment_0_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3886:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3886:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3887:1: ( rule__Atomic__NameAssignment_0_3 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_0_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3888:1: ( rule__Atomic__NameAssignment_0_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3888:2: rule__Atomic__NameAssignment_0_3
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl7672);
            rule__Atomic__NameAssignment_0_3();

            state._fsp--;


            }

             after(grammarAccess.getAtomicAccess().getNameAssignment_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__3__Impl"


    // $ANTLR start "rule__Atomic__Group_0__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3898:1: rule__Atomic__Group_0__4 : rule__Atomic__Group_0__4__Impl ;
    public final void rule__Atomic__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3902:1: ( rule__Atomic__Group_0__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3903:2: rule__Atomic__Group_0__4__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__47702);
            rule__Atomic__Group_0__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__4"


    // $ANTLR start "rule__Atomic__Group_0__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3909:1: rule__Atomic__Group_0__4__Impl : ( ')' ) ;
    public final void rule__Atomic__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3913:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3914:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3914:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3915:1: ')'
            {
             before(grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4()); 
            match(input,13,FOLLOW_13_in_rule__Atomic__Group_0__4__Impl7730); 
             after(grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_0__4__Impl"


    // $ANTLR start "rule__Atomic__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3938:1: rule__Atomic__Group_1__0 : rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 ;
    public final void rule__Atomic__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3942:1: ( rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3943:2: rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__07771);
            rule__Atomic__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__07774);
            rule__Atomic__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_1__0"


    // $ANTLR start "rule__Atomic__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3950:1: rule__Atomic__Group_1__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3954:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3955:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3955:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3956:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeValueAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3957:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3959:1: 
            {
            }

             after(grammarAccess.getAtomicAccess().getAttributeValueAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_1__0__Impl"


    // $ANTLR start "rule__Atomic__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3969:1: rule__Atomic__Group_1__1 : rule__Atomic__Group_1__1__Impl ;
    public final void rule__Atomic__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3973:1: ( rule__Atomic__Group_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3974:2: rule__Atomic__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__17832);
            rule__Atomic__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_1__1"


    // $ANTLR start "rule__Atomic__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3980:1: rule__Atomic__Group_1__1__Impl : ( ( rule__Atomic__NameAssignment_1_1 ) ) ;
    public final void rule__Atomic__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3984:1: ( ( ( rule__Atomic__NameAssignment_1_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3985:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3985:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3986:1: ( rule__Atomic__NameAssignment_1_1 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3987:1: ( rule__Atomic__NameAssignment_1_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:3987:2: rule__Atomic__NameAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl7859);
            rule__Atomic__NameAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAtomicAccess().getNameAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__Group_1__1__Impl"


    // $ANTLR start "rule__Condition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4001:1: rule__Condition__Group__0 : rule__Condition__Group__0__Impl rule__Condition__Group__1 ;
    public final void rule__Condition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4005:1: ( rule__Condition__Group__0__Impl rule__Condition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4006:2: rule__Condition__Group__0__Impl rule__Condition__Group__1
            {
            pushFollow(FOLLOW_rule__Condition__Group__0__Impl_in_rule__Condition__Group__07893);
            rule__Condition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group__1_in_rule__Condition__Group__07896);
            rule__Condition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__0"


    // $ANTLR start "rule__Condition__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4013:1: rule__Condition__Group__0__Impl : ( () ) ;
    public final void rule__Condition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4017:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4018:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4018:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4019:1: ()
            {
             before(grammarAccess.getConditionAccess().getConditionAction_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4020:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4022:1: 
            {
            }

             after(grammarAccess.getConditionAccess().getConditionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__0__Impl"


    // $ANTLR start "rule__Condition__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4032:1: rule__Condition__Group__1 : rule__Condition__Group__1__Impl rule__Condition__Group__2 ;
    public final void rule__Condition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4036:1: ( rule__Condition__Group__1__Impl rule__Condition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4037:2: rule__Condition__Group__1__Impl rule__Condition__Group__2
            {
            pushFollow(FOLLOW_rule__Condition__Group__1__Impl_in_rule__Condition__Group__17954);
            rule__Condition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group__2_in_rule__Condition__Group__17957);
            rule__Condition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__1"


    // $ANTLR start "rule__Condition__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4044:1: rule__Condition__Group__1__Impl : ( ( rule__Condition__Group_1__0 )? ) ;
    public final void rule__Condition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4048:1: ( ( ( rule__Condition__Group_1__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4049:1: ( ( rule__Condition__Group_1__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4049:1: ( ( rule__Condition__Group_1__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4050:1: ( rule__Condition__Group_1__0 )?
            {
             before(grammarAccess.getConditionAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4051:1: ( rule__Condition__Group_1__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==27) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4051:2: rule__Condition__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Condition__Group_1__0_in_rule__Condition__Group__1__Impl7984);
                    rule__Condition__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConditionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__1__Impl"


    // $ANTLR start "rule__Condition__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4061:1: rule__Condition__Group__2 : rule__Condition__Group__2__Impl rule__Condition__Group__3 ;
    public final void rule__Condition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4065:1: ( rule__Condition__Group__2__Impl rule__Condition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4066:2: rule__Condition__Group__2__Impl rule__Condition__Group__3
            {
            pushFollow(FOLLOW_rule__Condition__Group__2__Impl_in_rule__Condition__Group__28015);
            rule__Condition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group__3_in_rule__Condition__Group__28018);
            rule__Condition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__2"


    // $ANTLR start "rule__Condition__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4073:1: rule__Condition__Group__2__Impl : ( ( rule__Condition__Group_2__0 )? ) ;
    public final void rule__Condition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4077:1: ( ( ( rule__Condition__Group_2__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4078:1: ( ( rule__Condition__Group_2__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4078:1: ( ( rule__Condition__Group_2__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4079:1: ( rule__Condition__Group_2__0 )?
            {
             before(grammarAccess.getConditionAccess().getGroup_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4080:1: ( rule__Condition__Group_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4080:2: rule__Condition__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Condition__Group_2__0_in_rule__Condition__Group__2__Impl8045);
                    rule__Condition__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConditionAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__2__Impl"


    // $ANTLR start "rule__Condition__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4090:1: rule__Condition__Group__3 : rule__Condition__Group__3__Impl rule__Condition__Group__4 ;
    public final void rule__Condition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4094:1: ( rule__Condition__Group__3__Impl rule__Condition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4095:2: rule__Condition__Group__3__Impl rule__Condition__Group__4
            {
            pushFollow(FOLLOW_rule__Condition__Group__3__Impl_in_rule__Condition__Group__38076);
            rule__Condition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group__4_in_rule__Condition__Group__38079);
            rule__Condition__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__3"


    // $ANTLR start "rule__Condition__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4102:1: rule__Condition__Group__3__Impl : ( ( rule__Condition__Group_3__0 )? ) ;
    public final void rule__Condition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4106:1: ( ( ( rule__Condition__Group_3__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4107:1: ( ( rule__Condition__Group_3__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4107:1: ( ( rule__Condition__Group_3__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4108:1: ( rule__Condition__Group_3__0 )?
            {
             before(grammarAccess.getConditionAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4109:1: ( rule__Condition__Group_3__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==29) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4109:2: rule__Condition__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__Condition__Group_3__0_in_rule__Condition__Group__3__Impl8106);
                    rule__Condition__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConditionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__3__Impl"


    // $ANTLR start "rule__Condition__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4119:1: rule__Condition__Group__4 : rule__Condition__Group__4__Impl rule__Condition__Group__5 ;
    public final void rule__Condition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4123:1: ( rule__Condition__Group__4__Impl rule__Condition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4124:2: rule__Condition__Group__4__Impl rule__Condition__Group__5
            {
            pushFollow(FOLLOW_rule__Condition__Group__4__Impl_in_rule__Condition__Group__48137);
            rule__Condition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group__5_in_rule__Condition__Group__48140);
            rule__Condition__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__4"


    // $ANTLR start "rule__Condition__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4131:1: rule__Condition__Group__4__Impl : ( ( rule__Condition__Group_4__0 )? ) ;
    public final void rule__Condition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4135:1: ( ( ( rule__Condition__Group_4__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4136:1: ( ( rule__Condition__Group_4__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4136:1: ( ( rule__Condition__Group_4__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4137:1: ( rule__Condition__Group_4__0 )?
            {
             before(grammarAccess.getConditionAccess().getGroup_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4138:1: ( rule__Condition__Group_4__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==30) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4138:2: rule__Condition__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__Condition__Group_4__0_in_rule__Condition__Group__4__Impl8167);
                    rule__Condition__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConditionAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__4__Impl"


    // $ANTLR start "rule__Condition__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4148:1: rule__Condition__Group__5 : rule__Condition__Group__5__Impl rule__Condition__Group__6 ;
    public final void rule__Condition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4152:1: ( rule__Condition__Group__5__Impl rule__Condition__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4153:2: rule__Condition__Group__5__Impl rule__Condition__Group__6
            {
            pushFollow(FOLLOW_rule__Condition__Group__5__Impl_in_rule__Condition__Group__58198);
            rule__Condition__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group__6_in_rule__Condition__Group__58201);
            rule__Condition__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__5"


    // $ANTLR start "rule__Condition__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4160:1: rule__Condition__Group__5__Impl : ( ( rule__Condition__Group_5__0 )? ) ;
    public final void rule__Condition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4164:1: ( ( ( rule__Condition__Group_5__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4165:1: ( ( rule__Condition__Group_5__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4165:1: ( ( rule__Condition__Group_5__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4166:1: ( rule__Condition__Group_5__0 )?
            {
             before(grammarAccess.getConditionAccess().getGroup_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4167:1: ( rule__Condition__Group_5__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==31) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4167:2: rule__Condition__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__Condition__Group_5__0_in_rule__Condition__Group__5__Impl8228);
                    rule__Condition__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConditionAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__5__Impl"


    // $ANTLR start "rule__Condition__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4177:1: rule__Condition__Group__6 : rule__Condition__Group__6__Impl ;
    public final void rule__Condition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4181:1: ( rule__Condition__Group__6__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4182:2: rule__Condition__Group__6__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group__6__Impl_in_rule__Condition__Group__68259);
            rule__Condition__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__6"


    // $ANTLR start "rule__Condition__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4188:1: rule__Condition__Group__6__Impl : ( ( rule__Condition__Group_6__0 )? ) ;
    public final void rule__Condition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4192:1: ( ( ( rule__Condition__Group_6__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4193:1: ( ( rule__Condition__Group_6__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4193:1: ( ( rule__Condition__Group_6__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4194:1: ( rule__Condition__Group_6__0 )?
            {
             before(grammarAccess.getConditionAccess().getGroup_6()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4195:1: ( rule__Condition__Group_6__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==32) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4195:2: rule__Condition__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__Condition__Group_6__0_in_rule__Condition__Group__6__Impl8286);
                    rule__Condition__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConditionAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__6__Impl"


    // $ANTLR start "rule__Condition__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4219:1: rule__Condition__Group_1__0 : rule__Condition__Group_1__0__Impl rule__Condition__Group_1__1 ;
    public final void rule__Condition__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4223:1: ( rule__Condition__Group_1__0__Impl rule__Condition__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4224:2: rule__Condition__Group_1__0__Impl rule__Condition__Group_1__1
            {
            pushFollow(FOLLOW_rule__Condition__Group_1__0__Impl_in_rule__Condition__Group_1__08331);
            rule__Condition__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group_1__1_in_rule__Condition__Group_1__08334);
            rule__Condition__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_1__0"


    // $ANTLR start "rule__Condition__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4231:1: rule__Condition__Group_1__0__Impl : ( 'ENTITY_ACHIEVE_CONDITIONS' ) ;
    public final void rule__Condition__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4235:1: ( ( 'ENTITY_ACHIEVE_CONDITIONS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4236:1: ( 'ENTITY_ACHIEVE_CONDITIONS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4236:1: ( 'ENTITY_ACHIEVE_CONDITIONS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4237:1: 'ENTITY_ACHIEVE_CONDITIONS'
            {
             before(grammarAccess.getConditionAccess().getENTITY_ACHIEVE_CONDITIONSKeyword_1_0()); 
            match(input,27,FOLLOW_27_in_rule__Condition__Group_1__0__Impl8362); 
             after(grammarAccess.getConditionAccess().getENTITY_ACHIEVE_CONDITIONSKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_1__0__Impl"


    // $ANTLR start "rule__Condition__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4250:1: rule__Condition__Group_1__1 : rule__Condition__Group_1__1__Impl ;
    public final void rule__Condition__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4254:1: ( rule__Condition__Group_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4255:2: rule__Condition__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group_1__1__Impl_in_rule__Condition__Group_1__18393);
            rule__Condition__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_1__1"


    // $ANTLR start "rule__Condition__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4261:1: rule__Condition__Group_1__1__Impl : ( ( rule__Condition__EntityAchieveConditionsAssignment_1_1 )* ) ;
    public final void rule__Condition__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4265:1: ( ( ( rule__Condition__EntityAchieveConditionsAssignment_1_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4266:1: ( ( rule__Condition__EntityAchieveConditionsAssignment_1_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4266:1: ( ( rule__Condition__EntityAchieveConditionsAssignment_1_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4267:1: ( rule__Condition__EntityAchieveConditionsAssignment_1_1 )*
            {
             before(grammarAccess.getConditionAccess().getEntityAchieveConditionsAssignment_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4268:1: ( rule__Condition__EntityAchieveConditionsAssignment_1_1 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==11||LA18_0==14) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4268:2: rule__Condition__EntityAchieveConditionsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_rule__Condition__EntityAchieveConditionsAssignment_1_1_in_rule__Condition__Group_1__1__Impl8420);
            	    rule__Condition__EntityAchieveConditionsAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getEntityAchieveConditionsAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_1__1__Impl"


    // $ANTLR start "rule__Condition__Group_2__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4282:1: rule__Condition__Group_2__0 : rule__Condition__Group_2__0__Impl rule__Condition__Group_2__1 ;
    public final void rule__Condition__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4286:1: ( rule__Condition__Group_2__0__Impl rule__Condition__Group_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4287:2: rule__Condition__Group_2__0__Impl rule__Condition__Group_2__1
            {
            pushFollow(FOLLOW_rule__Condition__Group_2__0__Impl_in_rule__Condition__Group_2__08455);
            rule__Condition__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group_2__1_in_rule__Condition__Group_2__08458);
            rule__Condition__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_2__0"


    // $ANTLR start "rule__Condition__Group_2__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4294:1: rule__Condition__Group_2__0__Impl : ( 'ENTITY_INVARIANT_CONDITIONS' ) ;
    public final void rule__Condition__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4298:1: ( ( 'ENTITY_INVARIANT_CONDITIONS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4299:1: ( 'ENTITY_INVARIANT_CONDITIONS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4299:1: ( 'ENTITY_INVARIANT_CONDITIONS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4300:1: 'ENTITY_INVARIANT_CONDITIONS'
            {
             before(grammarAccess.getConditionAccess().getENTITY_INVARIANT_CONDITIONSKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__Condition__Group_2__0__Impl8486); 
             after(grammarAccess.getConditionAccess().getENTITY_INVARIANT_CONDITIONSKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_2__0__Impl"


    // $ANTLR start "rule__Condition__Group_2__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4313:1: rule__Condition__Group_2__1 : rule__Condition__Group_2__1__Impl ;
    public final void rule__Condition__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4317:1: ( rule__Condition__Group_2__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4318:2: rule__Condition__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group_2__1__Impl_in_rule__Condition__Group_2__18517);
            rule__Condition__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_2__1"


    // $ANTLR start "rule__Condition__Group_2__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4324:1: rule__Condition__Group_2__1__Impl : ( ( rule__Condition__EntityInvariantConditionsAssignment_2_1 )* ) ;
    public final void rule__Condition__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4328:1: ( ( ( rule__Condition__EntityInvariantConditionsAssignment_2_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4329:1: ( ( rule__Condition__EntityInvariantConditionsAssignment_2_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4329:1: ( ( rule__Condition__EntityInvariantConditionsAssignment_2_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4330:1: ( rule__Condition__EntityInvariantConditionsAssignment_2_1 )*
            {
             before(grammarAccess.getConditionAccess().getEntityInvariantConditionsAssignment_2_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4331:1: ( rule__Condition__EntityInvariantConditionsAssignment_2_1 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==15) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4331:2: rule__Condition__EntityInvariantConditionsAssignment_2_1
            	    {
            	    pushFollow(FOLLOW_rule__Condition__EntityInvariantConditionsAssignment_2_1_in_rule__Condition__Group_2__1__Impl8544);
            	    rule__Condition__EntityInvariantConditionsAssignment_2_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getEntityInvariantConditionsAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_2__1__Impl"


    // $ANTLR start "rule__Condition__Group_3__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4345:1: rule__Condition__Group_3__0 : rule__Condition__Group_3__0__Impl rule__Condition__Group_3__1 ;
    public final void rule__Condition__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4349:1: ( rule__Condition__Group_3__0__Impl rule__Condition__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4350:2: rule__Condition__Group_3__0__Impl rule__Condition__Group_3__1
            {
            pushFollow(FOLLOW_rule__Condition__Group_3__0__Impl_in_rule__Condition__Group_3__08579);
            rule__Condition__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group_3__1_in_rule__Condition__Group_3__08582);
            rule__Condition__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_3__0"


    // $ANTLR start "rule__Condition__Group_3__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4357:1: rule__Condition__Group_3__0__Impl : ( 'ENTITY_DEPENDENCE_CONDITIONS' ) ;
    public final void rule__Condition__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4361:1: ( ( 'ENTITY_DEPENDENCE_CONDITIONS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4362:1: ( 'ENTITY_DEPENDENCE_CONDITIONS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4362:1: ( 'ENTITY_DEPENDENCE_CONDITIONS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4363:1: 'ENTITY_DEPENDENCE_CONDITIONS'
            {
             before(grammarAccess.getConditionAccess().getENTITY_DEPENDENCE_CONDITIONSKeyword_3_0()); 
            match(input,29,FOLLOW_29_in_rule__Condition__Group_3__0__Impl8610); 
             after(grammarAccess.getConditionAccess().getENTITY_DEPENDENCE_CONDITIONSKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_3__0__Impl"


    // $ANTLR start "rule__Condition__Group_3__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4376:1: rule__Condition__Group_3__1 : rule__Condition__Group_3__1__Impl ;
    public final void rule__Condition__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4380:1: ( rule__Condition__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4381:2: rule__Condition__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group_3__1__Impl_in_rule__Condition__Group_3__18641);
            rule__Condition__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_3__1"


    // $ANTLR start "rule__Condition__Group_3__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4387:1: rule__Condition__Group_3__1__Impl : ( ( rule__Condition__EntityDependenceConditionsAssignment_3_1 )* ) ;
    public final void rule__Condition__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4391:1: ( ( ( rule__Condition__EntityDependenceConditionsAssignment_3_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4392:1: ( ( rule__Condition__EntityDependenceConditionsAssignment_3_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4392:1: ( ( rule__Condition__EntityDependenceConditionsAssignment_3_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4393:1: ( rule__Condition__EntityDependenceConditionsAssignment_3_1 )*
            {
             before(grammarAccess.getConditionAccess().getEntityDependenceConditionsAssignment_3_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4394:1: ( rule__Condition__EntityDependenceConditionsAssignment_3_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==17) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4394:2: rule__Condition__EntityDependenceConditionsAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_rule__Condition__EntityDependenceConditionsAssignment_3_1_in_rule__Condition__Group_3__1__Impl8668);
            	    rule__Condition__EntityDependenceConditionsAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getEntityDependenceConditionsAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_3__1__Impl"


    // $ANTLR start "rule__Condition__Group_4__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4408:1: rule__Condition__Group_4__0 : rule__Condition__Group_4__0__Impl rule__Condition__Group_4__1 ;
    public final void rule__Condition__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4412:1: ( rule__Condition__Group_4__0__Impl rule__Condition__Group_4__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4413:2: rule__Condition__Group_4__0__Impl rule__Condition__Group_4__1
            {
            pushFollow(FOLLOW_rule__Condition__Group_4__0__Impl_in_rule__Condition__Group_4__08703);
            rule__Condition__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group_4__1_in_rule__Condition__Group_4__08706);
            rule__Condition__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_4__0"


    // $ANTLR start "rule__Condition__Group_4__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4420:1: rule__Condition__Group_4__0__Impl : ( 'ATTRIBUTE_ACHIEVE_CONDITIONS' ) ;
    public final void rule__Condition__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4424:1: ( ( 'ATTRIBUTE_ACHIEVE_CONDITIONS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4425:1: ( 'ATTRIBUTE_ACHIEVE_CONDITIONS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4425:1: ( 'ATTRIBUTE_ACHIEVE_CONDITIONS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4426:1: 'ATTRIBUTE_ACHIEVE_CONDITIONS'
            {
             before(grammarAccess.getConditionAccess().getATTRIBUTE_ACHIEVE_CONDITIONSKeyword_4_0()); 
            match(input,30,FOLLOW_30_in_rule__Condition__Group_4__0__Impl8734); 
             after(grammarAccess.getConditionAccess().getATTRIBUTE_ACHIEVE_CONDITIONSKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_4__0__Impl"


    // $ANTLR start "rule__Condition__Group_4__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4439:1: rule__Condition__Group_4__1 : rule__Condition__Group_4__1__Impl ;
    public final void rule__Condition__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4443:1: ( rule__Condition__Group_4__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4444:2: rule__Condition__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group_4__1__Impl_in_rule__Condition__Group_4__18765);
            rule__Condition__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_4__1"


    // $ANTLR start "rule__Condition__Group_4__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4450:1: rule__Condition__Group_4__1__Impl : ( ( rule__Condition__AttributeAchieveConditionsAssignment_4_1 )* ) ;
    public final void rule__Condition__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4454:1: ( ( ( rule__Condition__AttributeAchieveConditionsAssignment_4_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4455:1: ( ( rule__Condition__AttributeAchieveConditionsAssignment_4_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4455:1: ( ( rule__Condition__AttributeAchieveConditionsAssignment_4_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4456:1: ( rule__Condition__AttributeAchieveConditionsAssignment_4_1 )*
            {
             before(grammarAccess.getConditionAccess().getAttributeAchieveConditionsAssignment_4_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4457:1: ( rule__Condition__AttributeAchieveConditionsAssignment_4_1 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==11||LA21_0==18) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4457:2: rule__Condition__AttributeAchieveConditionsAssignment_4_1
            	    {
            	    pushFollow(FOLLOW_rule__Condition__AttributeAchieveConditionsAssignment_4_1_in_rule__Condition__Group_4__1__Impl8792);
            	    rule__Condition__AttributeAchieveConditionsAssignment_4_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getAttributeAchieveConditionsAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_4__1__Impl"


    // $ANTLR start "rule__Condition__Group_5__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4471:1: rule__Condition__Group_5__0 : rule__Condition__Group_5__0__Impl rule__Condition__Group_5__1 ;
    public final void rule__Condition__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4475:1: ( rule__Condition__Group_5__0__Impl rule__Condition__Group_5__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4476:2: rule__Condition__Group_5__0__Impl rule__Condition__Group_5__1
            {
            pushFollow(FOLLOW_rule__Condition__Group_5__0__Impl_in_rule__Condition__Group_5__08827);
            rule__Condition__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group_5__1_in_rule__Condition__Group_5__08830);
            rule__Condition__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_5__0"


    // $ANTLR start "rule__Condition__Group_5__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4483:1: rule__Condition__Group_5__0__Impl : ( 'ATTRIBUTE_INVARIANT_CONDITIONS' ) ;
    public final void rule__Condition__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4487:1: ( ( 'ATTRIBUTE_INVARIANT_CONDITIONS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4488:1: ( 'ATTRIBUTE_INVARIANT_CONDITIONS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4488:1: ( 'ATTRIBUTE_INVARIANT_CONDITIONS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4489:1: 'ATTRIBUTE_INVARIANT_CONDITIONS'
            {
             before(grammarAccess.getConditionAccess().getATTRIBUTE_INVARIANT_CONDITIONSKeyword_5_0()); 
            match(input,31,FOLLOW_31_in_rule__Condition__Group_5__0__Impl8858); 
             after(grammarAccess.getConditionAccess().getATTRIBUTE_INVARIANT_CONDITIONSKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_5__0__Impl"


    // $ANTLR start "rule__Condition__Group_5__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4502:1: rule__Condition__Group_5__1 : rule__Condition__Group_5__1__Impl ;
    public final void rule__Condition__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4506:1: ( rule__Condition__Group_5__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4507:2: rule__Condition__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group_5__1__Impl_in_rule__Condition__Group_5__18889);
            rule__Condition__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_5__1"


    // $ANTLR start "rule__Condition__Group_5__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4513:1: rule__Condition__Group_5__1__Impl : ( ( rule__Condition__AttributeInvariantConditionsAssignment_5_1 )* ) ;
    public final void rule__Condition__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4517:1: ( ( ( rule__Condition__AttributeInvariantConditionsAssignment_5_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4518:1: ( ( rule__Condition__AttributeInvariantConditionsAssignment_5_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4518:1: ( ( rule__Condition__AttributeInvariantConditionsAssignment_5_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4519:1: ( rule__Condition__AttributeInvariantConditionsAssignment_5_1 )*
            {
             before(grammarAccess.getConditionAccess().getAttributeInvariantConditionsAssignment_5_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4520:1: ( rule__Condition__AttributeInvariantConditionsAssignment_5_1 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==19) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4520:2: rule__Condition__AttributeInvariantConditionsAssignment_5_1
            	    {
            	    pushFollow(FOLLOW_rule__Condition__AttributeInvariantConditionsAssignment_5_1_in_rule__Condition__Group_5__1__Impl8916);
            	    rule__Condition__AttributeInvariantConditionsAssignment_5_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getAttributeInvariantConditionsAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_5__1__Impl"


    // $ANTLR start "rule__Condition__Group_6__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4534:1: rule__Condition__Group_6__0 : rule__Condition__Group_6__0__Impl rule__Condition__Group_6__1 ;
    public final void rule__Condition__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4538:1: ( rule__Condition__Group_6__0__Impl rule__Condition__Group_6__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4539:2: rule__Condition__Group_6__0__Impl rule__Condition__Group_6__1
            {
            pushFollow(FOLLOW_rule__Condition__Group_6__0__Impl_in_rule__Condition__Group_6__08951);
            rule__Condition__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Condition__Group_6__1_in_rule__Condition__Group_6__08954);
            rule__Condition__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_6__0"


    // $ANTLR start "rule__Condition__Group_6__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4546:1: rule__Condition__Group_6__0__Impl : ( 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ) ;
    public final void rule__Condition__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4550:1: ( ( 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4551:1: ( 'ATTRIBUTE_DEPENDENCE_CONDITIONS' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4551:1: ( 'ATTRIBUTE_DEPENDENCE_CONDITIONS' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4552:1: 'ATTRIBUTE_DEPENDENCE_CONDITIONS'
            {
             before(grammarAccess.getConditionAccess().getATTRIBUTE_DEPENDENCE_CONDITIONSKeyword_6_0()); 
            match(input,32,FOLLOW_32_in_rule__Condition__Group_6__0__Impl8982); 
             after(grammarAccess.getConditionAccess().getATTRIBUTE_DEPENDENCE_CONDITIONSKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_6__0__Impl"


    // $ANTLR start "rule__Condition__Group_6__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4565:1: rule__Condition__Group_6__1 : rule__Condition__Group_6__1__Impl ;
    public final void rule__Condition__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4569:1: ( rule__Condition__Group_6__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4570:2: rule__Condition__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__Condition__Group_6__1__Impl_in_rule__Condition__Group_6__19013);
            rule__Condition__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_6__1"


    // $ANTLR start "rule__Condition__Group_6__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4576:1: rule__Condition__Group_6__1__Impl : ( ( rule__Condition__AttributeDependenceConditionsAssignment_6_1 )* ) ;
    public final void rule__Condition__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4580:1: ( ( ( rule__Condition__AttributeDependenceConditionsAssignment_6_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4581:1: ( ( rule__Condition__AttributeDependenceConditionsAssignment_6_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4581:1: ( ( rule__Condition__AttributeDependenceConditionsAssignment_6_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4582:1: ( rule__Condition__AttributeDependenceConditionsAssignment_6_1 )*
            {
             before(grammarAccess.getConditionAccess().getAttributeDependenceConditionsAssignment_6_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4583:1: ( rule__Condition__AttributeDependenceConditionsAssignment_6_1 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==17) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4583:2: rule__Condition__AttributeDependenceConditionsAssignment_6_1
            	    {
            	    pushFollow(FOLLOW_rule__Condition__AttributeDependenceConditionsAssignment_6_1_in_rule__Condition__Group_6__1__Impl9040);
            	    rule__Condition__AttributeDependenceConditionsAssignment_6_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getAttributeDependenceConditionsAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group_6__1__Impl"


    // $ANTLR start "rule__ConditionModel__ConditionsAssignment"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4598:1: rule__ConditionModel__ConditionsAssignment : ( ruleCondition ) ;
    public final void rule__ConditionModel__ConditionsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4602:1: ( ( ruleCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4603:1: ( ruleCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4603:1: ( ruleCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4604:1: ruleCondition
            {
             before(grammarAccess.getConditionModelAccess().getConditionsConditionParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleCondition_in_rule__ConditionModel__ConditionsAssignment9080);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getConditionModelAccess().getConditionsConditionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConditionModel__ConditionsAssignment"


    // $ANTLR start "rule__EntityAchieveCondition__NameAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4613:1: rule__EntityAchieveCondition__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__EntityAchieveCondition__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4617:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4618:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4618:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4619:1: RULE_ID
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__EntityAchieveCondition__NameAssignment_29111); 
             after(grammarAccess.getEntityAchieveConditionAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveCondition__NameAssignment_2"


    // $ANTLR start "rule__EntityAchieveConditionExist__NameAssignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4628:1: rule__EntityAchieveConditionExist__NameAssignment_4 : ( RULE_ID ) ;
    public final void rule__EntityAchieveConditionExist__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4632:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4633:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4633:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4634:1: RULE_ID
            {
             before(grammarAccess.getEntityAchieveConditionExistAccess().getNameIDTerminalRuleCall_4_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__EntityAchieveConditionExist__NameAssignment_49142); 
             after(grammarAccess.getEntityAchieveConditionExistAccess().getNameIDTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityAchieveConditionExist__NameAssignment_4"


    // $ANTLR start "rule__EntityInvariantCondition__NameAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4643:1: rule__EntityInvariantCondition__NameAssignment_2 : ( ruleAttribute ) ;
    public final void rule__EntityInvariantCondition__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4647:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4648:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4648:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4649:1: ruleAttribute
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__EntityInvariantCondition__NameAssignment_29173);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__NameAssignment_2"


    // $ANTLR start "rule__EntityInvariantCondition__CardinalityAssignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4658:1: rule__EntityInvariantCondition__CardinalityAssignment_4 : ( ruleCardinality ) ;
    public final void rule__EntityInvariantCondition__CardinalityAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4662:1: ( ( ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4663:1: ( ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4663:1: ( ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4664:1: ruleCardinality
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleCardinality_in_rule__EntityInvariantCondition__CardinalityAssignment_49204);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityInvariantCondition__CardinalityAssignment_4"


    // $ANTLR start "rule__EntityDependenceCondition__Entity1Assignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4673:1: rule__EntityDependenceCondition__Entity1Assignment_4 : ( RULE_ID ) ;
    public final void rule__EntityDependenceCondition__Entity1Assignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4677:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4678:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4678:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4679:1: RULE_ID
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getEntity1IDTerminalRuleCall_4_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__EntityDependenceCondition__Entity1Assignment_49235); 
             after(grammarAccess.getEntityDependenceConditionAccess().getEntity1IDTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Entity1Assignment_4"


    // $ANTLR start "rule__EntityDependenceCondition__Entity2Assignment_9"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4688:1: rule__EntityDependenceCondition__Entity2Assignment_9 : ( RULE_ID ) ;
    public final void rule__EntityDependenceCondition__Entity2Assignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4692:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4693:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4693:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4694:1: RULE_ID
            {
             before(grammarAccess.getEntityDependenceConditionAccess().getEntity2IDTerminalRuleCall_9_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__EntityDependenceCondition__Entity2Assignment_99266); 
             after(grammarAccess.getEntityDependenceConditionAccess().getEntity2IDTerminalRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EntityDependenceCondition__Entity2Assignment_9"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4703:1: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 : ( ruleAttribute ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4707:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4708:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4708:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4709:1: ruleAttribute
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_29297);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2"


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4718:1: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 : ( ruleAttribute ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4722:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4723:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4723:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4724:1: ruleAttribute
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_19328);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4733:1: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 : ( ruleAttribute ) ;
    public final void rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4737:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4738:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4738:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4739:1: ruleAttribute
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_49359);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4"


    // $ANTLR start "rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4748:1: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 : ( ruleAttribute ) ;
    public final void rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4752:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4753:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4753:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4754:1: ruleAttribute
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_19390);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1"


    // $ANTLR start "rule__AttributeInvariantCondition__ExpressionAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4763:1: rule__AttributeInvariantCondition__ExpressionAssignment_2 : ( ruleExpression ) ;
    public final void rule__AttributeInvariantCondition__ExpressionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4767:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4768:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4768:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4769:1: ruleExpression
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__AttributeInvariantCondition__ExpressionAssignment_29421);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeInvariantCondition__ExpressionAssignment_2"


    // $ANTLR start "rule__AttributeDependenceCondition__Attributes1Assignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4778:1: rule__AttributeDependenceCondition__Attributes1Assignment_4 : ( ruleAttribute ) ;
    public final void rule__AttributeDependenceCondition__Attributes1Assignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4782:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4783:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4783:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4784:1: ruleAttribute
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__AttributeDependenceCondition__Attributes1Assignment_49452);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Attributes1Assignment_4"


    // $ANTLR start "rule__AttributeDependenceCondition__Attributes1Assignment_5_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4793:1: rule__AttributeDependenceCondition__Attributes1Assignment_5_1 : ( ruleAttribute ) ;
    public final void rule__AttributeDependenceCondition__Attributes1Assignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4797:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4798:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4798:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4799:1: ruleAttribute
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__AttributeDependenceCondition__Attributes1Assignment_5_19483);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Attributes1Assignment_5_1"


    // $ANTLR start "rule__AttributeDependenceCondition__Attribute2Assignment_10"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4808:1: rule__AttributeDependenceCondition__Attribute2Assignment_10 : ( ruleAttribute ) ;
    public final void rule__AttributeDependenceCondition__Attribute2Assignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4812:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4813:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4813:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4814:1: ruleAttribute
            {
             before(grammarAccess.getAttributeDependenceConditionAccess().getAttribute2AttributeParserRuleCall_10_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__AttributeDependenceCondition__Attribute2Assignment_109514);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeDependenceConditionAccess().getAttribute2AttributeParserRuleCall_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeDependenceCondition__Attribute2Assignment_10"


    // $ANTLR start "rule__Or__RightAssignment_1_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4824:1: rule__Or__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Or__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4828:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4829:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4829:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4830:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_29546);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__RightAssignment_1_2"


    // $ANTLR start "rule__And__RightAssignment_1_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4839:1: rule__And__RightAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4843:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4844:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4844:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4845:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_29577);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__RightAssignment_1_2"


    // $ANTLR start "rule__Primary__ExpressionAssignment_1_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4854:1: rule__Primary__ExpressionAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__Primary__ExpressionAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4858:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4859:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4859:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4860:1: rulePrimary
            {
             before(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_29608);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__ExpressionAssignment_1_2"


    // $ANTLR start "rule__Atomic__NameAssignment_0_3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4869:1: rule__Atomic__NameAssignment_0_3 : ( ruleAttribute ) ;
    public final void rule__Atomic__NameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4873:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4874:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4874:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4875:1: ruleAttribute
            {
             before(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_0_39639);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__NameAssignment_0_3"


    // $ANTLR start "rule__Atomic__NameAssignment_1_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4884:1: rule__Atomic__NameAssignment_1_1 : ( ruleAttribute ) ;
    public final void rule__Atomic__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4888:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4889:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4889:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4890:1: ruleAttribute
            {
             before(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_1_19670);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atomic__NameAssignment_1_1"


    // $ANTLR start "rule__Condition__EntityAchieveConditionsAssignment_1_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4899:1: rule__Condition__EntityAchieveConditionsAssignment_1_1 : ( ( rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 ) ) ;
    public final void rule__Condition__EntityAchieveConditionsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4903:1: ( ( ( rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4904:1: ( ( rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4904:1: ( ( rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4905:1: ( rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 )
            {
             before(grammarAccess.getConditionAccess().getEntityAchieveConditionsAlternatives_1_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4906:1: ( rule__Condition__EntityAchieveConditionsAlternatives_1_1_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4906:2: rule__Condition__EntityAchieveConditionsAlternatives_1_1_0
            {
            pushFollow(FOLLOW_rule__Condition__EntityAchieveConditionsAlternatives_1_1_0_in_rule__Condition__EntityAchieveConditionsAssignment_1_19701);
            rule__Condition__EntityAchieveConditionsAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getConditionAccess().getEntityAchieveConditionsAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__EntityAchieveConditionsAssignment_1_1"


    // $ANTLR start "rule__Condition__EntityInvariantConditionsAssignment_2_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4915:1: rule__Condition__EntityInvariantConditionsAssignment_2_1 : ( ruleEntityInvariantCondition ) ;
    public final void rule__Condition__EntityInvariantConditionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4919:1: ( ( ruleEntityInvariantCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4920:1: ( ruleEntityInvariantCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4920:1: ( ruleEntityInvariantCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4921:1: ruleEntityInvariantCondition
            {
             before(grammarAccess.getConditionAccess().getEntityInvariantConditionsEntityInvariantConditionParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_rule__Condition__EntityInvariantConditionsAssignment_2_19734);
            ruleEntityInvariantCondition();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getEntityInvariantConditionsEntityInvariantConditionParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__EntityInvariantConditionsAssignment_2_1"


    // $ANTLR start "rule__Condition__EntityDependenceConditionsAssignment_3_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4930:1: rule__Condition__EntityDependenceConditionsAssignment_3_1 : ( ruleEntityDependenceCondition ) ;
    public final void rule__Condition__EntityDependenceConditionsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4934:1: ( ( ruleEntityDependenceCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4935:1: ( ruleEntityDependenceCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4935:1: ( ruleEntityDependenceCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4936:1: ruleEntityDependenceCondition
            {
             before(grammarAccess.getConditionAccess().getEntityDependenceConditionsEntityDependenceConditionParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleEntityDependenceCondition_in_rule__Condition__EntityDependenceConditionsAssignment_3_19765);
            ruleEntityDependenceCondition();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getEntityDependenceConditionsEntityDependenceConditionParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__EntityDependenceConditionsAssignment_3_1"


    // $ANTLR start "rule__Condition__AttributeAchieveConditionsAssignment_4_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4945:1: rule__Condition__AttributeAchieveConditionsAssignment_4_1 : ( ruleAttributeAchieveCondition ) ;
    public final void rule__Condition__AttributeAchieveConditionsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4949:1: ( ( ruleAttributeAchieveCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4950:1: ( ruleAttributeAchieveCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4950:1: ( ruleAttributeAchieveCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4951:1: ruleAttributeAchieveCondition
            {
             before(grammarAccess.getConditionAccess().getAttributeAchieveConditionsAttributeAchieveConditionParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_rule__Condition__AttributeAchieveConditionsAssignment_4_19796);
            ruleAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getAttributeAchieveConditionsAttributeAchieveConditionParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__AttributeAchieveConditionsAssignment_4_1"


    // $ANTLR start "rule__Condition__AttributeInvariantConditionsAssignment_5_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4960:1: rule__Condition__AttributeInvariantConditionsAssignment_5_1 : ( ruleAttributeInvariantCondition ) ;
    public final void rule__Condition__AttributeInvariantConditionsAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4964:1: ( ( ruleAttributeInvariantCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4965:1: ( ruleAttributeInvariantCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4965:1: ( ruleAttributeInvariantCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4966:1: ruleAttributeInvariantCondition
            {
             before(grammarAccess.getConditionAccess().getAttributeInvariantConditionsAttributeInvariantConditionParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_rule__Condition__AttributeInvariantConditionsAssignment_5_19827);
            ruleAttributeInvariantCondition();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getAttributeInvariantConditionsAttributeInvariantConditionParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__AttributeInvariantConditionsAssignment_5_1"


    // $ANTLR start "rule__Condition__AttributeDependenceConditionsAssignment_6_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4975:1: rule__Condition__AttributeDependenceConditionsAssignment_6_1 : ( ruleAttributeDependenceCondition ) ;
    public final void rule__Condition__AttributeDependenceConditionsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4979:1: ( ( ruleAttributeDependenceCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4980:1: ( ruleAttributeDependenceCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4980:1: ( ruleAttributeDependenceCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition.ui/src-gen/org/blended/condition/ui/contentassist/antlr/internal/InternalCondition.g:4981:1: ruleAttributeDependenceCondition
            {
             before(grammarAccess.getConditionAccess().getAttributeDependenceConditionsAttributeDependenceConditionParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleAttributeDependenceCondition_in_rule__Condition__AttributeDependenceConditionsAssignment_6_19858);
            ruleAttributeDependenceCondition();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getAttributeDependenceConditionsAttributeDependenceConditionParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__AttributeDependenceConditionsAssignment_6_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleConditionModel_in_entryRuleConditionModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ConditionModel__ConditionsAssignment_in_ruleConditionModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveCondition128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__0_in_ruleEntityAchieveCondition154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveConditionExist_in_entryRuleEntityAchieveConditionExist181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveConditionExist188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__0_in_ruleEntityAchieveConditionExist214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityInvariantCondition248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__0_in_ruleEntityInvariantCondition274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityDependenceCondition_in_entryRuleEntityDependenceCondition301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityDependenceCondition308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__0_in_ruleEntityDependenceCondition334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeAchieveCondition368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeAchieveCondition__Alternatives_in_ruleAttributeAchieveCondition394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0_in_ruleNotMandatoryAttributeAchieveCondition454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0_in_ruleMandatoryAttributeAchieveCondition514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__0_in_ruleAttributeInvariantCondition574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeDependenceCondition_in_entryRuleAttributeDependenceCondition601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeDependenceCondition608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__0_in_ruleAttributeDependenceCondition634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0_in_ruleAttribute754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression783 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr842 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0_in_ruleOr875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd902 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0_in_ruleAnd935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary962 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Alternatives_in_rulePrimary995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic1022 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCondition_in_entryRuleCondition1082 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCondition1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__0_in_ruleCondition1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives1253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Condition__EntityAchieveConditionsAlternatives_1_1_01405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveConditionExist_in_rule__Condition__EntityAchieveConditionsAlternatives_1_1_01422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__0__Impl_in_rule__EntityAchieveCondition__Group__01452 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__1_in_rule__EntityAchieveCondition__Group__01455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__EntityAchieveCondition__Group__0__Impl1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__1__Impl_in_rule__EntityAchieveCondition__Group__11514 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__2_in_rule__EntityAchieveCondition__Group__11517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityAchieveCondition__Group__1__Impl1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__2__Impl_in_rule__EntityAchieveCondition__Group__21576 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__3_in_rule__EntityAchieveCondition__Group__21579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__NameAssignment_2_in_rule__EntityAchieveCondition__Group__2__Impl1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__3__Impl_in_rule__EntityAchieveCondition__Group__31636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityAchieveCondition__Group__3__Impl1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__0__Impl_in_rule__EntityAchieveConditionExist__Group__01703 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__1_in_rule__EntityAchieveConditionExist__Group__01706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__EntityAchieveConditionExist__Group__0__Impl1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__1__Impl_in_rule__EntityAchieveConditionExist__Group__11765 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__2_in_rule__EntityAchieveConditionExist__Group__11768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityAchieveConditionExist__Group__1__Impl1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__2__Impl_in_rule__EntityAchieveConditionExist__Group__21827 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__3_in_rule__EntityAchieveConditionExist__Group__21830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__EntityAchieveConditionExist__Group__2__Impl1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__3__Impl_in_rule__EntityAchieveConditionExist__Group__31889 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__4_in_rule__EntityAchieveConditionExist__Group__31892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityAchieveConditionExist__Group__3__Impl1920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__4__Impl_in_rule__EntityAchieveConditionExist__Group__41951 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__5_in_rule__EntityAchieveConditionExist__Group__41954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__NameAssignment_4_in_rule__EntityAchieveConditionExist__Group__4__Impl1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__5__Impl_in_rule__EntityAchieveConditionExist__Group__52011 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__6_in_rule__EntityAchieveConditionExist__Group__52014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityAchieveConditionExist__Group__5__Impl2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveConditionExist__Group__6__Impl_in_rule__EntityAchieveConditionExist__Group__62073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityAchieveConditionExist__Group__6__Impl2101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__0__Impl_in_rule__EntityInvariantCondition__Group__02146 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__1_in_rule__EntityInvariantCondition__Group__02149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__EntityInvariantCondition__Group__0__Impl2177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__1__Impl_in_rule__EntityInvariantCondition__Group__12208 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__2_in_rule__EntityInvariantCondition__Group__12211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityInvariantCondition__Group__1__Impl2239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__2__Impl_in_rule__EntityInvariantCondition__Group__22270 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__3_in_rule__EntityInvariantCondition__Group__22273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__NameAssignment_2_in_rule__EntityInvariantCondition__Group__2__Impl2300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__3__Impl_in_rule__EntityInvariantCondition__Group__32330 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__4_in_rule__EntityInvariantCondition__Group__32333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__EntityInvariantCondition__Group__3__Impl2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__4__Impl_in_rule__EntityInvariantCondition__Group__42392 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__5_in_rule__EntityInvariantCondition__Group__42395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__CardinalityAssignment_4_in_rule__EntityInvariantCondition__Group__4__Impl2422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__5__Impl_in_rule__EntityInvariantCondition__Group__52452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityInvariantCondition__Group__5__Impl2480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__0__Impl_in_rule__EntityDependenceCondition__Group__02523 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__1_in_rule__EntityDependenceCondition__Group__02526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__EntityDependenceCondition__Group__0__Impl2554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__1__Impl_in_rule__EntityDependenceCondition__Group__12585 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__2_in_rule__EntityDependenceCondition__Group__12588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityDependenceCondition__Group__1__Impl2616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__2__Impl_in_rule__EntityDependenceCondition__Group__22647 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__3_in_rule__EntityDependenceCondition__Group__22650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__EntityDependenceCondition__Group__2__Impl2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__3__Impl_in_rule__EntityDependenceCondition__Group__32709 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__4_in_rule__EntityDependenceCondition__Group__32712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityDependenceCondition__Group__3__Impl2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__4__Impl_in_rule__EntityDependenceCondition__Group__42771 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__5_in_rule__EntityDependenceCondition__Group__42774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Entity1Assignment_4_in_rule__EntityDependenceCondition__Group__4__Impl2801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__5__Impl_in_rule__EntityDependenceCondition__Group__52831 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__6_in_rule__EntityDependenceCondition__Group__52834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityDependenceCondition__Group__5__Impl2862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__6__Impl_in_rule__EntityDependenceCondition__Group__62893 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__7_in_rule__EntityDependenceCondition__Group__62896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__EntityDependenceCondition__Group__6__Impl2924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__7__Impl_in_rule__EntityDependenceCondition__Group__72955 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__8_in_rule__EntityDependenceCondition__Group__72958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__EntityDependenceCondition__Group__7__Impl2986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__8__Impl_in_rule__EntityDependenceCondition__Group__83017 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__9_in_rule__EntityDependenceCondition__Group__83020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityDependenceCondition__Group__8__Impl3048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__9__Impl_in_rule__EntityDependenceCondition__Group__93079 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__10_in_rule__EntityDependenceCondition__Group__93082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Entity2Assignment_9_in_rule__EntityDependenceCondition__Group__9__Impl3109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__10__Impl_in_rule__EntityDependenceCondition__Group__103139 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__11_in_rule__EntityDependenceCondition__Group__103142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityDependenceCondition__Group__10__Impl3170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityDependenceCondition__Group__11__Impl_in_rule__EntityDependenceCondition__Group__113201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityDependenceCondition__Group__11__Impl3229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__03284 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1_in_rule__NotMandatoryAttributeAchieveCondition__Group__03287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl3315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__13346 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2_in_rule__NotMandatoryAttributeAchieveCondition__Group__13349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl3377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__23408 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3_in_rule__NotMandatoryAttributeAchieveCondition__Group__23411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2_in_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl3438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__33468 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4_in_rule__NotMandatoryAttributeAchieveCondition__Group__33471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0_in_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl3498 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__43529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl3557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__03598 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__03601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl3629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__13660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl3687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__03721 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1_in_rule__MandatoryAttributeAchieveCondition__Group__03724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__MandatoryAttributeAchieveCondition__Group__0__Impl3752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__13783 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2_in_rule__MandatoryAttributeAchieveCondition__Group__13786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__1__Impl3814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__23845 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3_in_rule__MandatoryAttributeAchieveCondition__Group__23848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__MandatoryAttributeAchieveCondition__Group__2__Impl3876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__33907 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4_in_rule__MandatoryAttributeAchieveCondition__Group__33910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__3__Impl3938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__43969 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5_in_rule__MandatoryAttributeAchieveCondition__Group__43972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4_in_rule__MandatoryAttributeAchieveCondition__Group__4__Impl3999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__54029 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6_in_rule__MandatoryAttributeAchieveCondition__Group__54032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0_in_rule__MandatoryAttributeAchieveCondition__Group__5__Impl4059 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__64090 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7_in_rule__MandatoryAttributeAchieveCondition__Group__64093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__6__Impl4121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__74152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__7__Impl4180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__04227 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1_in_rule__MandatoryAttributeAchieveCondition__Group_5__04230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl4258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__14289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1_in_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl4316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__0__Impl_in_rule__AttributeInvariantCondition__Group__04350 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__1_in_rule__AttributeInvariantCondition__Group__04353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__AttributeInvariantCondition__Group__0__Impl4381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__1__Impl_in_rule__AttributeInvariantCondition__Group__14412 = new BitSet(new long[]{0x0000000004001820L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__2_in_rule__AttributeInvariantCondition__Group__14415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeInvariantCondition__Group__1__Impl4443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__2__Impl_in_rule__AttributeInvariantCondition__Group__24474 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__3_in_rule__AttributeInvariantCondition__Group__24477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__ExpressionAssignment_2_in_rule__AttributeInvariantCondition__Group__2__Impl4504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__3__Impl_in_rule__AttributeInvariantCondition__Group__34534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeInvariantCondition__Group__3__Impl4562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__0__Impl_in_rule__AttributeDependenceCondition__Group__04601 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__1_in_rule__AttributeDependenceCondition__Group__04604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__AttributeDependenceCondition__Group__0__Impl4632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__1__Impl_in_rule__AttributeDependenceCondition__Group__14663 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__2_in_rule__AttributeDependenceCondition__Group__14666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeDependenceCondition__Group__1__Impl4694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__2__Impl_in_rule__AttributeDependenceCondition__Group__24725 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__3_in_rule__AttributeDependenceCondition__Group__24728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__AttributeDependenceCondition__Group__2__Impl4756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__3__Impl_in_rule__AttributeDependenceCondition__Group__34787 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__4_in_rule__AttributeDependenceCondition__Group__34790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeDependenceCondition__Group__3__Impl4818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__4__Impl_in_rule__AttributeDependenceCondition__Group__44849 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__5_in_rule__AttributeDependenceCondition__Group__44852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Attributes1Assignment_4_in_rule__AttributeDependenceCondition__Group__4__Impl4879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__5__Impl_in_rule__AttributeDependenceCondition__Group__54909 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__6_in_rule__AttributeDependenceCondition__Group__54912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group_5__0_in_rule__AttributeDependenceCondition__Group__5__Impl4939 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__6__Impl_in_rule__AttributeDependenceCondition__Group__64970 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__7_in_rule__AttributeDependenceCondition__Group__64973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeDependenceCondition__Group__6__Impl5001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__7__Impl_in_rule__AttributeDependenceCondition__Group__75032 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__8_in_rule__AttributeDependenceCondition__Group__75035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__AttributeDependenceCondition__Group__7__Impl5063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__8__Impl_in_rule__AttributeDependenceCondition__Group__85094 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__9_in_rule__AttributeDependenceCondition__Group__85097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__AttributeDependenceCondition__Group__8__Impl5125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__9__Impl_in_rule__AttributeDependenceCondition__Group__95156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__10_in_rule__AttributeDependenceCondition__Group__95159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeDependenceCondition__Group__9__Impl5187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__10__Impl_in_rule__AttributeDependenceCondition__Group__105218 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__11_in_rule__AttributeDependenceCondition__Group__105221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Attribute2Assignment_10_in_rule__AttributeDependenceCondition__Group__10__Impl5248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__11__Impl_in_rule__AttributeDependenceCondition__Group__115278 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__12_in_rule__AttributeDependenceCondition__Group__115281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeDependenceCondition__Group__11__Impl5309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group__12__Impl_in_rule__AttributeDependenceCondition__Group__125340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeDependenceCondition__Group__12__Impl5368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group_5__0__Impl_in_rule__AttributeDependenceCondition__Group_5__05425 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group_5__1_in_rule__AttributeDependenceCondition__Group_5__05428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__AttributeDependenceCondition__Group_5__0__Impl5456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Group_5__1__Impl_in_rule__AttributeDependenceCondition__Group_5__15487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeDependenceCondition__Attributes1Assignment_5_1_in_rule__AttributeDependenceCondition__Group_5__1__Impl5514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__05548 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__05551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl5578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__15607 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__15610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Cardinality__Group_1__1__Impl5638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__25669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl5696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__05731 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__05734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl5761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__15790 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__15793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Cardinality__Group_2__1__Impl5821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__25852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Cardinality__Group_2__2__Impl5880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__05917 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__05920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl5947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__15976 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__15979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Cardinality__Group_3__1__Impl6007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__26038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Cardinality__Group_3__2__Impl6066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__06103 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__06106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group__0__Impl6133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__16162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__16165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Attribute__Group__1__Impl6193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__26224 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__26227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group__2__Impl6254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__36283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__0_in_rule__Attribute__Group__3__Impl6310 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__0__Impl_in_rule__Attribute__Group_3__06349 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__1_in_rule__Attribute__Group_3__06352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Attribute__Group_3__0__Impl6380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__1__Impl_in_rule__Attribute__Group_3__16411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group_3__1__Impl6438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__06471 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Or__Group__1_in_rule__Or__Group__06474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl6501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__16530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl6557 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__06592 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__06595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__16653 = new BitSet(new long[]{0x0000000004001820L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__16656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Or__Group_1__1__Impl6684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__26715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl6742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__06778 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__And__Group__1_in_rule__And__Group__06781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__Group__0__Impl6808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__16837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl6864 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__06899 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__06902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__16960 = new BitSet(new long[]{0x0000000004001820L});
    public static final BitSet FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__16963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__And__Group_1__1__Impl6991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__27022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl7049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__07085 = new BitSet(new long[]{0x0000000004001820L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__07088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Primary__Group_0__0__Impl7116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__17147 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__17150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl7177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__27206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Primary__Group_0__2__Impl7234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__07271 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__07274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__17332 = new BitSet(new long[]{0x0000000004001820L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__17335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Primary__Group_1__1__Impl7363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__27394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl7421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__07457 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__07460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__17518 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__17521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__Atomic__Group_0__1__Impl7549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__27580 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__27583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Atomic__Group_0__2__Impl7611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__37642 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__37645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl7672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__47702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Atomic__Group_0__4__Impl7730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__07771 = new BitSet(new long[]{0x0000000004001820L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__07774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__17832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl7859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__0__Impl_in_rule__Condition__Group__07893 = new BitSet(new long[]{0x00000001F8000000L});
    public static final BitSet FOLLOW_rule__Condition__Group__1_in_rule__Condition__Group__07896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__1__Impl_in_rule__Condition__Group__17954 = new BitSet(new long[]{0x00000001F8000000L});
    public static final BitSet FOLLOW_rule__Condition__Group__2_in_rule__Condition__Group__17957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_1__0_in_rule__Condition__Group__1__Impl7984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__2__Impl_in_rule__Condition__Group__28015 = new BitSet(new long[]{0x00000001F8000000L});
    public static final BitSet FOLLOW_rule__Condition__Group__3_in_rule__Condition__Group__28018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_2__0_in_rule__Condition__Group__2__Impl8045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__3__Impl_in_rule__Condition__Group__38076 = new BitSet(new long[]{0x00000001F8000000L});
    public static final BitSet FOLLOW_rule__Condition__Group__4_in_rule__Condition__Group__38079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_3__0_in_rule__Condition__Group__3__Impl8106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__4__Impl_in_rule__Condition__Group__48137 = new BitSet(new long[]{0x00000001F8000000L});
    public static final BitSet FOLLOW_rule__Condition__Group__5_in_rule__Condition__Group__48140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_4__0_in_rule__Condition__Group__4__Impl8167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__5__Impl_in_rule__Condition__Group__58198 = new BitSet(new long[]{0x00000001F8000000L});
    public static final BitSet FOLLOW_rule__Condition__Group__6_in_rule__Condition__Group__58201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_5__0_in_rule__Condition__Group__5__Impl8228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group__6__Impl_in_rule__Condition__Group__68259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_6__0_in_rule__Condition__Group__6__Impl8286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_1__0__Impl_in_rule__Condition__Group_1__08331 = new BitSet(new long[]{0x0000000000004800L});
    public static final BitSet FOLLOW_rule__Condition__Group_1__1_in_rule__Condition__Group_1__08334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Condition__Group_1__0__Impl8362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_1__1__Impl_in_rule__Condition__Group_1__18393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__EntityAchieveConditionsAssignment_1_1_in_rule__Condition__Group_1__1__Impl8420 = new BitSet(new long[]{0x0000000000004802L});
    public static final BitSet FOLLOW_rule__Condition__Group_2__0__Impl_in_rule__Condition__Group_2__08455 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Condition__Group_2__1_in_rule__Condition__Group_2__08458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Condition__Group_2__0__Impl8486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_2__1__Impl_in_rule__Condition__Group_2__18517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__EntityInvariantConditionsAssignment_2_1_in_rule__Condition__Group_2__1__Impl8544 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Condition__Group_3__0__Impl_in_rule__Condition__Group_3__08579 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__Condition__Group_3__1_in_rule__Condition__Group_3__08582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Condition__Group_3__0__Impl8610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_3__1__Impl_in_rule__Condition__Group_3__18641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__EntityDependenceConditionsAssignment_3_1_in_rule__Condition__Group_3__1__Impl8668 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_rule__Condition__Group_4__0__Impl_in_rule__Condition__Group_4__08703 = new BitSet(new long[]{0x0000000000040800L});
    public static final BitSet FOLLOW_rule__Condition__Group_4__1_in_rule__Condition__Group_4__08706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Condition__Group_4__0__Impl8734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_4__1__Impl_in_rule__Condition__Group_4__18765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__AttributeAchieveConditionsAssignment_4_1_in_rule__Condition__Group_4__1__Impl8792 = new BitSet(new long[]{0x0000000000040802L});
    public static final BitSet FOLLOW_rule__Condition__Group_5__0__Impl_in_rule__Condition__Group_5__08827 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Condition__Group_5__1_in_rule__Condition__Group_5__08830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Condition__Group_5__0__Impl8858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_5__1__Impl_in_rule__Condition__Group_5__18889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__AttributeInvariantConditionsAssignment_5_1_in_rule__Condition__Group_5__1__Impl8916 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__Condition__Group_6__0__Impl_in_rule__Condition__Group_6__08951 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__Condition__Group_6__1_in_rule__Condition__Group_6__08954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__Condition__Group_6__0__Impl8982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__Group_6__1__Impl_in_rule__Condition__Group_6__19013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__AttributeDependenceConditionsAssignment_6_1_in_rule__Condition__Group_6__1__Impl9040 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleCondition_in_rule__ConditionModel__ConditionsAssignment9080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__EntityAchieveCondition__NameAssignment_29111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__EntityAchieveConditionExist__NameAssignment_49142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__EntityInvariantCondition__NameAssignment_29173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_rule__EntityInvariantCondition__CardinalityAssignment_49204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__EntityDependenceCondition__Entity1Assignment_49235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__EntityDependenceCondition__Entity2Assignment_99266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_29297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_19328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_49359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_19390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__AttributeInvariantCondition__ExpressionAssignment_29421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__AttributeDependenceCondition__Attributes1Assignment_49452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__AttributeDependenceCondition__Attributes1Assignment_5_19483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__AttributeDependenceCondition__Attribute2Assignment_109514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_29546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_29577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_29608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_0_39639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_1_19670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Condition__EntityAchieveConditionsAlternatives_1_1_0_in_rule__Condition__EntityAchieveConditionsAssignment_1_19701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_rule__Condition__EntityInvariantConditionsAssignment_2_19734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityDependenceCondition_in_rule__Condition__EntityDependenceConditionsAssignment_3_19765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_rule__Condition__AttributeAchieveConditionsAssignment_4_19796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_rule__Condition__AttributeInvariantConditionsAssignment_5_19827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeDependenceCondition_in_rule__Condition__AttributeDependenceConditionsAssignment_6_19858 = new BitSet(new long[]{0x0000000000000002L});

}