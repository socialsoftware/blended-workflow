package org.blended.goal.ui.contentassist.antlr.internal; 

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
import org.blended.goal.services.GoalGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGoalParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEFF'", "'('", "')'", "'MUL'", "','", "'DEF'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'OR'", "'AND'", "'NOT'", "':'", "'SUC'", "'ACT'", "'INV'", "'SUB'", "'nothing'"
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


        public InternalGoalParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalGoalParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalGoalParser.tokenNames; }
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g"; }


     
     	private GoalGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(GoalGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleGoalModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:60:1: entryRuleGoalModel : ruleGoalModel EOF ;
    public final void entryRuleGoalModel() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:61:1: ( ruleGoalModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:62:1: ruleGoalModel EOF
            {
             before(grammarAccess.getGoalModelRule()); 
            pushFollow(FOLLOW_ruleGoalModel_in_entryRuleGoalModel61);
            ruleGoalModel();

            state._fsp--;

             after(grammarAccess.getGoalModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGoalModel68); 

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
    // $ANTLR end "entryRuleGoalModel"


    // $ANTLR start "ruleGoalModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:69:1: ruleGoalModel : ( ( ( rule__GoalModel__GoalsAssignment ) ) ( ( rule__GoalModel__GoalsAssignment )* ) ) ;
    public final void ruleGoalModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:73:2: ( ( ( ( rule__GoalModel__GoalsAssignment ) ) ( ( rule__GoalModel__GoalsAssignment )* ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:74:1: ( ( ( rule__GoalModel__GoalsAssignment ) ) ( ( rule__GoalModel__GoalsAssignment )* ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:74:1: ( ( ( rule__GoalModel__GoalsAssignment ) ) ( ( rule__GoalModel__GoalsAssignment )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:75:1: ( ( rule__GoalModel__GoalsAssignment ) ) ( ( rule__GoalModel__GoalsAssignment )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:75:1: ( ( rule__GoalModel__GoalsAssignment ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:76:1: ( rule__GoalModel__GoalsAssignment )
            {
             before(grammarAccess.getGoalModelAccess().getGoalsAssignment()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:77:1: ( rule__GoalModel__GoalsAssignment )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:77:2: rule__GoalModel__GoalsAssignment
            {
            pushFollow(FOLLOW_rule__GoalModel__GoalsAssignment_in_ruleGoalModel96);
            rule__GoalModel__GoalsAssignment();

            state._fsp--;


            }

             after(grammarAccess.getGoalModelAccess().getGoalsAssignment()); 

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:80:1: ( ( rule__GoalModel__GoalsAssignment )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:81:1: ( rule__GoalModel__GoalsAssignment )*
            {
             before(grammarAccess.getGoalModelAccess().getGoalsAssignment()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:82:1: ( rule__GoalModel__GoalsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:82:2: rule__GoalModel__GoalsAssignment
            	    {
            	    pushFollow(FOLLOW_rule__GoalModel__GoalsAssignment_in_ruleGoalModel108);
            	    rule__GoalModel__GoalsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getGoalModelAccess().getGoalsAssignment()); 

            }


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
    // $ANTLR end "ruleGoalModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:95:1: entryRuleEntityAchieveCondition : ruleEntityAchieveCondition EOF ;
    public final void entryRuleEntityAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:96:1: ( ruleEntityAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:97:1: ruleEntityAchieveCondition EOF
            {
             before(grammarAccess.getEntityAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition138);
            ruleEntityAchieveCondition();

            state._fsp--;

             after(grammarAccess.getEntityAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveCondition145); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:104:1: ruleEntityAchieveCondition : ( ( rule__EntityAchieveCondition__Group__0 ) ) ;
    public final void ruleEntityAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:108:2: ( ( ( rule__EntityAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:109:1: ( ( rule__EntityAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:109:1: ( ( rule__EntityAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:110:1: ( rule__EntityAchieveCondition__Group__0 )
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:111:1: ( rule__EntityAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:111:2: rule__EntityAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__0_in_ruleEntityAchieveCondition171);
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


    // $ANTLR start "entryRuleEntityInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:125:1: entryRuleEntityInvariantCondition : ruleEntityInvariantCondition EOF ;
    public final void entryRuleEntityInvariantCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:126:1: ( ruleEntityInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:127:1: ruleEntityInvariantCondition EOF
            {
             before(grammarAccess.getEntityInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition200);
            ruleEntityInvariantCondition();

            state._fsp--;

             after(grammarAccess.getEntityInvariantConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityInvariantCondition207); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:134:1: ruleEntityInvariantCondition : ( ( rule__EntityInvariantCondition__Group__0 ) ) ;
    public final void ruleEntityInvariantCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:138:2: ( ( ( rule__EntityInvariantCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:139:1: ( ( rule__EntityInvariantCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:139:1: ( ( rule__EntityInvariantCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:140:1: ( rule__EntityInvariantCondition__Group__0 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:141:1: ( rule__EntityInvariantCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:141:2: rule__EntityInvariantCondition__Group__0
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__0_in_ruleEntityInvariantCondition233);
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


    // $ANTLR start "entryRuleAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:155:1: entryRuleAttributeAchieveCondition : ruleAttributeAchieveCondition EOF ;
    public final void entryRuleAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:156:1: ( ruleAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:157:1: ruleAttributeAchieveCondition EOF
            {
             before(grammarAccess.getAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition262);
            ruleAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeAchieveCondition269); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:164:1: ruleAttributeAchieveCondition : ( ( rule__AttributeAchieveCondition__Alternatives ) ) ;
    public final void ruleAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:168:2: ( ( ( rule__AttributeAchieveCondition__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:169:1: ( ( rule__AttributeAchieveCondition__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:169:1: ( ( rule__AttributeAchieveCondition__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:170:1: ( rule__AttributeAchieveCondition__Alternatives )
            {
             before(grammarAccess.getAttributeAchieveConditionAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:171:1: ( rule__AttributeAchieveCondition__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:171:2: rule__AttributeAchieveCondition__Alternatives
            {
            pushFollow(FOLLOW_rule__AttributeAchieveCondition__Alternatives_in_ruleAttributeAchieveCondition295);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:183:1: entryRuleNotMandatoryAttributeAchieveCondition : ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final void entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:184:1: ( ruleNotMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:185:1: ruleNotMandatoryAttributeAchieveCondition EOF
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition322);
            ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition329); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:192:1: ruleNotMandatoryAttributeAchieveCondition : ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) ) ;
    public final void ruleNotMandatoryAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:196:2: ( ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:197:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:197:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:198:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:199:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:199:2: rule__NotMandatoryAttributeAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0_in_ruleNotMandatoryAttributeAchieveCondition355);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:211:1: entryRuleMandatoryAttributeAchieveCondition : ruleMandatoryAttributeAchieveCondition EOF ;
    public final void entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:212:1: ( ruleMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:213:1: ruleMandatoryAttributeAchieveCondition EOF
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition382);
            ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition389); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:220:1: ruleMandatoryAttributeAchieveCondition : ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) ) ;
    public final void ruleMandatoryAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:224:2: ( ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:225:1: ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:225:1: ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:226:1: ( rule__MandatoryAttributeAchieveCondition__Group__0 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:227:1: ( rule__MandatoryAttributeAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:227:2: rule__MandatoryAttributeAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0_in_ruleMandatoryAttributeAchieveCondition415);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:239:1: entryRuleAttributeInvariantCondition : ruleAttributeInvariantCondition EOF ;
    public final void entryRuleAttributeInvariantCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:240:1: ( ruleAttributeInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:241:1: ruleAttributeInvariantCondition EOF
            {
             before(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition442);
            ruleAttributeInvariantCondition();

            state._fsp--;

             after(grammarAccess.getAttributeInvariantConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition449); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:248:1: ruleAttributeInvariantCondition : ( ( rule__AttributeInvariantCondition__Group__0 ) ) ;
    public final void ruleAttributeInvariantCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:252:2: ( ( ( rule__AttributeInvariantCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:253:1: ( ( rule__AttributeInvariantCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:253:1: ( ( rule__AttributeInvariantCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:254:1: ( rule__AttributeInvariantCondition__Group__0 )
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:255:1: ( rule__AttributeInvariantCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:255:2: rule__AttributeInvariantCondition__Group__0
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__0_in_ruleAttributeInvariantCondition475);
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


    // $ANTLR start "entryRuleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:269:1: entryRuleCardinality : ruleCardinality EOF ;
    public final void entryRuleCardinality() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:270:1: ( ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:271:1: ruleCardinality EOF
            {
             before(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality504);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getCardinalityRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality511); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:278:1: ruleCardinality : ( ( rule__Cardinality__Alternatives ) ) ;
    public final void ruleCardinality() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:282:2: ( ( ( rule__Cardinality__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:283:1: ( ( rule__Cardinality__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:283:1: ( ( rule__Cardinality__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:284:1: ( rule__Cardinality__Alternatives )
            {
             before(grammarAccess.getCardinalityAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:285:1: ( rule__Cardinality__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:285:2: rule__Cardinality__Alternatives
            {
            pushFollow(FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality537);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:297:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:298:1: ( ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:299:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute564);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute571); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:306:1: ruleAttribute : ( ( rule__Attribute__Group__0 ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:310:2: ( ( ( rule__Attribute__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:311:1: ( ( rule__Attribute__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:311:1: ( ( rule__Attribute__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:312:1: ( rule__Attribute__Group__0 )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:313:1: ( rule__Attribute__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:313:2: rule__Attribute__Group__0
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0_in_ruleAttribute597);
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


    // $ANTLR start "entryRuleNothing"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:325:1: entryRuleNothing : ruleNothing EOF ;
    public final void entryRuleNothing() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:326:1: ( ruleNothing EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:327:1: ruleNothing EOF
            {
             before(grammarAccess.getNothingRule()); 
            pushFollow(FOLLOW_ruleNothing_in_entryRuleNothing624);
            ruleNothing();

            state._fsp--;

             after(grammarAccess.getNothingRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNothing631); 

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
    // $ANTLR end "entryRuleNothing"


    // $ANTLR start "ruleNothing"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:334:1: ruleNothing : ( ( rule__Nothing__NameAssignment ) ) ;
    public final void ruleNothing() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:338:2: ( ( ( rule__Nothing__NameAssignment ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:339:1: ( ( rule__Nothing__NameAssignment ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:339:1: ( ( rule__Nothing__NameAssignment ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:340:1: ( rule__Nothing__NameAssignment )
            {
             before(grammarAccess.getNothingAccess().getNameAssignment()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:341:1: ( rule__Nothing__NameAssignment )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:341:2: rule__Nothing__NameAssignment
            {
            pushFollow(FOLLOW_rule__Nothing__NameAssignment_in_ruleNothing657);
            rule__Nothing__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getNothingAccess().getNameAssignment()); 

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
    // $ANTLR end "ruleNothing"


    // $ANTLR start "entryRuleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:353:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:354:1: ( ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:355:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression684);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression691); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:362:1: ruleExpression : ( ruleOr ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:366:2: ( ( ruleOr ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:367:1: ( ruleOr )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:367:1: ( ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:368:1: ruleOr
            {
             before(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
            pushFollow(FOLLOW_ruleOr_in_ruleExpression717);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:381:1: entryRuleOr : ruleOr EOF ;
    public final void entryRuleOr() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:382:1: ( ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:383:1: ruleOr EOF
            {
             before(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr743);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getOrRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr750); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:390:1: ruleOr : ( ( rule__Or__Group__0 ) ) ;
    public final void ruleOr() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:394:2: ( ( ( rule__Or__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:395:1: ( ( rule__Or__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:395:1: ( ( rule__Or__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:396:1: ( rule__Or__Group__0 )
            {
             before(grammarAccess.getOrAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:397:1: ( rule__Or__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:397:2: rule__Or__Group__0
            {
            pushFollow(FOLLOW_rule__Or__Group__0_in_ruleOr776);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:409:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:410:1: ( ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:411:1: ruleAnd EOF
            {
             before(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd803);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getAndRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd810); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:418:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:422:2: ( ( ( rule__And__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:423:1: ( ( rule__And__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:423:1: ( ( rule__And__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:424:1: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:425:1: ( rule__And__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:425:2: rule__And__Group__0
            {
            pushFollow(FOLLOW_rule__And__Group__0_in_ruleAnd836);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:437:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:438:1: ( rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:439:1: rulePrimary EOF
            {
             before(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary863);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary870); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:446:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:450:2: ( ( ( rule__Primary__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:451:1: ( ( rule__Primary__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:451:1: ( ( rule__Primary__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:452:1: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:453:1: ( rule__Primary__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:453:2: rule__Primary__Alternatives
            {
            pushFollow(FOLLOW_rule__Primary__Alternatives_in_rulePrimary896);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:465:1: entryRuleAtomic : ruleAtomic EOF ;
    public final void entryRuleAtomic() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:466:1: ( ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:467:1: ruleAtomic EOF
            {
             before(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic923);
            ruleAtomic();

            state._fsp--;

             after(grammarAccess.getAtomicRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic930); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:474:1: ruleAtomic : ( ( rule__Atomic__Alternatives ) ) ;
    public final void ruleAtomic() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:478:2: ( ( ( rule__Atomic__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:479:1: ( ( rule__Atomic__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:479:1: ( ( rule__Atomic__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:480:1: ( rule__Atomic__Alternatives )
            {
             before(grammarAccess.getAtomicAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:481:1: ( rule__Atomic__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:481:2: rule__Atomic__Alternatives
            {
            pushFollow(FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic956);
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


    // $ANTLR start "entryRuleGoal"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:493:1: entryRuleGoal : ruleGoal EOF ;
    public final void entryRuleGoal() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:494:1: ( ruleGoal EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:495:1: ruleGoal EOF
            {
             before(grammarAccess.getGoalRule()); 
            pushFollow(FOLLOW_ruleGoal_in_entryRuleGoal983);
            ruleGoal();

            state._fsp--;

             after(grammarAccess.getGoalRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGoal990); 

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
    // $ANTLR end "entryRuleGoal"


    // $ANTLR start "ruleGoal"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:502:1: ruleGoal : ( ( rule__Goal__Group__0 ) ) ;
    public final void ruleGoal() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:506:2: ( ( ( rule__Goal__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:507:1: ( ( rule__Goal__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:507:1: ( ( rule__Goal__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:508:1: ( rule__Goal__Group__0 )
            {
             before(grammarAccess.getGoalAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:509:1: ( rule__Goal__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:509:2: rule__Goal__Group__0
            {
            pushFollow(FOLLOW_rule__Goal__Group__0_in_ruleGoal1016);
            rule__Goal__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getGroup()); 

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
    // $ANTLR end "ruleGoal"


    // $ANTLR start "rule__AttributeAchieveCondition__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:521:1: rule__AttributeAchieveCondition__Alternatives : ( ( ruleMandatoryAttributeAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) );
    public final void rule__AttributeAchieveCondition__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:525:1: ( ( ruleMandatoryAttributeAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==17) ) {
                alt2=1;
            }
            else if ( (LA2_0==16) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:526:1: ( ruleMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:526:1: ( ruleMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:527:1: ruleMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1052);
                    ruleMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:532:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:532:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:533:1: ruleNotMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getAttributeAchieveConditionAccess().getNotMandatoryAttributeAchieveConditionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1069);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:543:1: rule__Cardinality__Alternatives : ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) );
    public final void rule__Cardinality__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:547:1: ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) )
            int alt3=4;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_INT) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==19) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt3=2;
                        }
                        break;
                    case 21:
                        {
                        alt3=4;
                        }
                        break;
                    case 20:
                        {
                        alt3=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 2, input);

                        throw nvae;
                    }

                }
                else if ( (LA3_1==EOF||LA3_1==13) ) {
                    alt3=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:548:1: ( RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:548:1: ( RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:549:1: RULE_INT
                    {
                     before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives1101); 
                     after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:554:6: ( ( rule__Cardinality__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:554:6: ( ( rule__Cardinality__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:555:1: ( rule__Cardinality__Group_1__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:556:1: ( rule__Cardinality__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:556:2: rule__Cardinality__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives1118);
                    rule__Cardinality__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:560:6: ( ( rule__Cardinality__Group_2__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:560:6: ( ( rule__Cardinality__Group_2__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:561:1: ( rule__Cardinality__Group_2__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_2()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:562:1: ( rule__Cardinality__Group_2__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:562:2: rule__Cardinality__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives1136);
                    rule__Cardinality__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:566:6: ( ( rule__Cardinality__Group_3__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:566:6: ( ( rule__Cardinality__Group_3__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:567:1: ( rule__Cardinality__Group_3__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_3()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:568:1: ( rule__Cardinality__Group_3__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:568:2: rule__Cardinality__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives1154);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:577:1: rule__Primary__Alternatives : ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:581:1: ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt4=1;
                }
                break;
            case 25:
                {
                alt4=2;
                }
                break;
            case RULE_ID:
            case 16:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:582:1: ( ( rule__Primary__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:582:1: ( ( rule__Primary__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:583:1: ( rule__Primary__Group_0__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:584:1: ( rule__Primary__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:584:2: rule__Primary__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives1187);
                    rule__Primary__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:588:6: ( ( rule__Primary__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:588:6: ( ( rule__Primary__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:589:1: ( rule__Primary__Group_1__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:590:1: ( rule__Primary__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:590:2: rule__Primary__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1205);
                    rule__Primary__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:594:6: ( ruleAtomic )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:594:6: ( ruleAtomic )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:595:1: ruleAtomic
                    {
                     before(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1223);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:605:1: rule__Atomic__Alternatives : ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) );
    public final void rule__Atomic__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:609:1: ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==16) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_ID) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:610:1: ( ( rule__Atomic__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:610:1: ( ( rule__Atomic__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:611:1: ( rule__Atomic__Group_0__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:612:1: ( rule__Atomic__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:612:2: rule__Atomic__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1255);
                    rule__Atomic__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAtomicAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:616:6: ( ( rule__Atomic__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:616:6: ( ( rule__Atomic__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:617:1: ( rule__Atomic__Group_1__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:618:1: ( rule__Atomic__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:618:2: rule__Atomic__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1273);
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


    // $ANTLR start "rule__Goal__SucessConditionAlternatives_4_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:627:1: rule__Goal__SucessConditionAlternatives_4_0 : ( ( ruleEntityAchieveCondition ) | ( ruleAttributeAchieveCondition ) | ( ruleNothing ) );
    public final void rule__Goal__SucessConditionAlternatives_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:631:1: ( ( ruleEntityAchieveCondition ) | ( ruleAttributeAchieveCondition ) | ( ruleNothing ) )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt6=1;
                }
                break;
            case 16:
            case 17:
                {
                alt6=2;
                }
                break;
            case 31:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:632:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:632:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:633:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getGoalAccess().getSucessConditionEntityAchieveConditionParserRuleCall_4_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Goal__SucessConditionAlternatives_4_01306);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getSucessConditionEntityAchieveConditionParserRuleCall_4_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:638:6: ( ruleAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:638:6: ( ruleAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:639:1: ruleAttributeAchieveCondition
                    {
                     before(grammarAccess.getGoalAccess().getSucessConditionAttributeAchieveConditionParserRuleCall_4_0_1()); 
                    pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_rule__Goal__SucessConditionAlternatives_4_01323);
                    ruleAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getSucessConditionAttributeAchieveConditionParserRuleCall_4_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:644:6: ( ruleNothing )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:644:6: ( ruleNothing )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:645:1: ruleNothing
                    {
                     before(grammarAccess.getGoalAccess().getSucessConditionNothingParserRuleCall_4_0_2()); 
                    pushFollow(FOLLOW_ruleNothing_in_rule__Goal__SucessConditionAlternatives_4_01340);
                    ruleNothing();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getSucessConditionNothingParserRuleCall_4_0_2()); 

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
    // $ANTLR end "rule__Goal__SucessConditionAlternatives_4_0"


    // $ANTLR start "rule__Goal__ActivationConditionAlternatives_6_3_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:655:1: rule__Goal__ActivationConditionAlternatives_6_3_0 : ( ( ruleEntityAchieveCondition ) | ( ruleAttributeAchieveCondition ) );
    public final void rule__Goal__ActivationConditionAlternatives_6_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:659:1: ( ( ruleEntityAchieveCondition ) | ( ruleAttributeAchieveCondition ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==11) ) {
                alt7=1;
            }
            else if ( ((LA7_0>=16 && LA7_0<=17)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:660:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:660:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:661:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getGoalAccess().getActivationConditionEntityAchieveConditionParserRuleCall_6_3_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Goal__ActivationConditionAlternatives_6_3_01372);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getActivationConditionEntityAchieveConditionParserRuleCall_6_3_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:666:6: ( ruleAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:666:6: ( ruleAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:667:1: ruleAttributeAchieveCondition
                    {
                     before(grammarAccess.getGoalAccess().getActivationConditionAttributeAchieveConditionParserRuleCall_6_3_0_1()); 
                    pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_rule__Goal__ActivationConditionAlternatives_6_3_01389);
                    ruleAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getActivationConditionAttributeAchieveConditionParserRuleCall_6_3_0_1()); 

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
    // $ANTLR end "rule__Goal__ActivationConditionAlternatives_6_3_0"


    // $ANTLR start "rule__Goal__InvariantConditionsAlternatives_7_3_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:677:1: rule__Goal__InvariantConditionsAlternatives_7_3_0 : ( ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) );
    public final void rule__Goal__InvariantConditionsAlternatives_7_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:681:1: ( ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==14) ) {
                alt8=1;
            }
            else if ( (LA8_0==18) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:682:1: ( ruleEntityInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:682:1: ( ruleEntityInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:683:1: ruleEntityInvariantCondition
                    {
                     before(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_3_0_0()); 
                    pushFollow(FOLLOW_ruleEntityInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_3_01421);
                    ruleEntityInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_3_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:688:6: ( ruleAttributeInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:688:6: ( ruleAttributeInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:689:1: ruleAttributeInvariantCondition
                    {
                     before(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_3_0_1()); 
                    pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_3_01438);
                    ruleAttributeInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_3_0_1()); 

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
    // $ANTLR end "rule__Goal__InvariantConditionsAlternatives_7_3_0"


    // $ANTLR start "rule__Goal__InvariantConditionsAlternatives_7_4_1_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:699:1: rule__Goal__InvariantConditionsAlternatives_7_4_1_0 : ( ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) );
    public final void rule__Goal__InvariantConditionsAlternatives_7_4_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:703:1: ( ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==14) ) {
                alt9=1;
            }
            else if ( (LA9_0==18) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:704:1: ( ruleEntityInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:704:1: ( ruleEntityInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:705:1: ruleEntityInvariantCondition
                    {
                     before(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_4_1_0_0()); 
                    pushFollow(FOLLOW_ruleEntityInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_4_1_01470);
                    ruleEntityInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_4_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:710:6: ( ruleAttributeInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:710:6: ( ruleAttributeInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:711:1: ruleAttributeInvariantCondition
                    {
                     before(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_4_1_0_1()); 
                    pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_4_1_01487);
                    ruleAttributeInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_4_1_0_1()); 

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
    // $ANTLR end "rule__Goal__InvariantConditionsAlternatives_7_4_1_0"


    // $ANTLR start "rule__EntityAchieveCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:723:1: rule__EntityAchieveCondition__Group__0 : rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1 ;
    public final void rule__EntityAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:727:1: ( rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:728:2: rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__0__Impl_in_rule__EntityAchieveCondition__Group__01517);
            rule__EntityAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__1_in_rule__EntityAchieveCondition__Group__01520);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:735:1: rule__EntityAchieveCondition__Group__0__Impl : ( 'DEFF' ) ;
    public final void rule__EntityAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:739:1: ( ( 'DEFF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:740:1: ( 'DEFF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:740:1: ( 'DEFF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:741:1: 'DEFF'
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getDEFFKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__EntityAchieveCondition__Group__0__Impl1548); 
             after(grammarAccess.getEntityAchieveConditionAccess().getDEFFKeyword_0()); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:754:1: rule__EntityAchieveCondition__Group__1 : rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2 ;
    public final void rule__EntityAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:758:1: ( rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:759:2: rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__1__Impl_in_rule__EntityAchieveCondition__Group__11579);
            rule__EntityAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__2_in_rule__EntityAchieveCondition__Group__11582);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:766:1: rule__EntityAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:770:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:771:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:771:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:772:1: '('
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityAchieveCondition__Group__1__Impl1610); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:785:1: rule__EntityAchieveCondition__Group__2 : rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3 ;
    public final void rule__EntityAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:789:1: ( rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:790:2: rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__2__Impl_in_rule__EntityAchieveCondition__Group__21641);
            rule__EntityAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__3_in_rule__EntityAchieveCondition__Group__21644);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:797:1: rule__EntityAchieveCondition__Group__2__Impl : ( ( rule__EntityAchieveCondition__NameAssignment_2 ) ) ;
    public final void rule__EntityAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:801:1: ( ( ( rule__EntityAchieveCondition__NameAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:802:1: ( ( rule__EntityAchieveCondition__NameAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:802:1: ( ( rule__EntityAchieveCondition__NameAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:803:1: ( rule__EntityAchieveCondition__NameAssignment_2 )
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getNameAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:804:1: ( rule__EntityAchieveCondition__NameAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:804:2: rule__EntityAchieveCondition__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__NameAssignment_2_in_rule__EntityAchieveCondition__Group__2__Impl1671);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:814:1: rule__EntityAchieveCondition__Group__3 : rule__EntityAchieveCondition__Group__3__Impl ;
    public final void rule__EntityAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:818:1: ( rule__EntityAchieveCondition__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:819:2: rule__EntityAchieveCondition__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__3__Impl_in_rule__EntityAchieveCondition__Group__31701);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:825:1: rule__EntityAchieveCondition__Group__3__Impl : ( ')' ) ;
    public final void rule__EntityAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:829:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:830:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:830:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:831:1: ')'
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getRightParenthesisKeyword_3()); 
            match(input,13,FOLLOW_13_in_rule__EntityAchieveCondition__Group__3__Impl1729); 
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


    // $ANTLR start "rule__EntityInvariantCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:853:1: rule__EntityInvariantCondition__Group__0 : rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1 ;
    public final void rule__EntityInvariantCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:857:1: ( rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:858:2: rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__0__Impl_in_rule__EntityInvariantCondition__Group__01769);
            rule__EntityInvariantCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__1_in_rule__EntityInvariantCondition__Group__01772);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:865:1: rule__EntityInvariantCondition__Group__0__Impl : ( 'MUL' ) ;
    public final void rule__EntityInvariantCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:869:1: ( ( 'MUL' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:870:1: ( 'MUL' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:870:1: ( 'MUL' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:871:1: 'MUL'
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0()); 
            match(input,14,FOLLOW_14_in_rule__EntityInvariantCondition__Group__0__Impl1800); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:884:1: rule__EntityInvariantCondition__Group__1 : rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2 ;
    public final void rule__EntityInvariantCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:888:1: ( rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:889:2: rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__1__Impl_in_rule__EntityInvariantCondition__Group__11831);
            rule__EntityInvariantCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__2_in_rule__EntityInvariantCondition__Group__11834);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:896:1: rule__EntityInvariantCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityInvariantCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:900:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:901:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:901:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:902:1: '('
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityInvariantCondition__Group__1__Impl1862); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:915:1: rule__EntityInvariantCondition__Group__2 : rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3 ;
    public final void rule__EntityInvariantCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:919:1: ( rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:920:2: rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__2__Impl_in_rule__EntityInvariantCondition__Group__21893);
            rule__EntityInvariantCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__3_in_rule__EntityInvariantCondition__Group__21896);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:927:1: rule__EntityInvariantCondition__Group__2__Impl : ( ( rule__EntityInvariantCondition__NameAssignment_2 ) ) ;
    public final void rule__EntityInvariantCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:931:1: ( ( ( rule__EntityInvariantCondition__NameAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:932:1: ( ( rule__EntityInvariantCondition__NameAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:932:1: ( ( rule__EntityInvariantCondition__NameAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:933:1: ( rule__EntityInvariantCondition__NameAssignment_2 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getNameAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:934:1: ( rule__EntityInvariantCondition__NameAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:934:2: rule__EntityInvariantCondition__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__NameAssignment_2_in_rule__EntityInvariantCondition__Group__2__Impl1923);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:944:1: rule__EntityInvariantCondition__Group__3 : rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4 ;
    public final void rule__EntityInvariantCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:948:1: ( rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:949:2: rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__3__Impl_in_rule__EntityInvariantCondition__Group__31953);
            rule__EntityInvariantCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__4_in_rule__EntityInvariantCondition__Group__31956);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:956:1: rule__EntityInvariantCondition__Group__3__Impl : ( ',' ) ;
    public final void rule__EntityInvariantCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:960:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:961:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:961:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:962:1: ','
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3()); 
            match(input,15,FOLLOW_15_in_rule__EntityInvariantCondition__Group__3__Impl1984); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:975:1: rule__EntityInvariantCondition__Group__4 : rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5 ;
    public final void rule__EntityInvariantCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:979:1: ( rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:980:2: rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__4__Impl_in_rule__EntityInvariantCondition__Group__42015);
            rule__EntityInvariantCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__5_in_rule__EntityInvariantCondition__Group__42018);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:987:1: rule__EntityInvariantCondition__Group__4__Impl : ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) ) ;
    public final void rule__EntityInvariantCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:991:1: ( ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:992:1: ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:992:1: ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:993:1: ( rule__EntityInvariantCondition__CardinalityAssignment_4 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCardinalityAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:994:1: ( rule__EntityInvariantCondition__CardinalityAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:994:2: rule__EntityInvariantCondition__CardinalityAssignment_4
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__CardinalityAssignment_4_in_rule__EntityInvariantCondition__Group__4__Impl2045);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1004:1: rule__EntityInvariantCondition__Group__5 : rule__EntityInvariantCondition__Group__5__Impl ;
    public final void rule__EntityInvariantCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1008:1: ( rule__EntityInvariantCondition__Group__5__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1009:2: rule__EntityInvariantCondition__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__5__Impl_in_rule__EntityInvariantCondition__Group__52075);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1015:1: rule__EntityInvariantCondition__Group__5__Impl : ( ')' ) ;
    public final void rule__EntityInvariantCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1019:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1020:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1020:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1021:1: ')'
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getRightParenthesisKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__EntityInvariantCondition__Group__5__Impl2103); 
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


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1047:1: rule__NotMandatoryAttributeAchieveCondition__Group__0 : rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1051:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1052:2: rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__02147);
            rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1_in_rule__NotMandatoryAttributeAchieveCondition__Group__02150);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1059:1: rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl : ( 'DEF' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1063:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1064:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1064:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1065:1: 'DEF'
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0()); 
            match(input,16,FOLLOW_16_in_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl2178); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1078:1: rule__NotMandatoryAttributeAchieveCondition__Group__1 : rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1082:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1083:2: rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__12209);
            rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2_in_rule__NotMandatoryAttributeAchieveCondition__Group__12212);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1090:1: rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1094:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1095:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1095:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1096:1: '('
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl2240); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1109:1: rule__NotMandatoryAttributeAchieveCondition__Group__2 : rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1113:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1114:2: rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__22271);
            rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3_in_rule__NotMandatoryAttributeAchieveCondition__Group__22274);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1121:1: rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1125:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1126:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1126:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1127:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1128:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1128:2: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2_in_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl2301);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1138:1: rule__NotMandatoryAttributeAchieveCondition__Group__3 : rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1142:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1143:2: rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__32331);
            rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4_in_rule__NotMandatoryAttributeAchieveCondition__Group__32334);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1150:1: rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1154:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1155:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1155:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1156:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )*
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1157:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==15) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1157:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0_in_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl2361);
            	    rule__NotMandatoryAttributeAchieveCondition__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1167:1: rule__NotMandatoryAttributeAchieveCondition__Group__4 : rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1171:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1172:2: rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__42392);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1178:1: rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl : ( ')' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1182:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1183:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1183:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1184:1: ')'
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_4()); 
            match(input,13,FOLLOW_13_in_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl2420); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1207:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__0 : rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1211:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1212:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02461);
            rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02464);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1219:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl : ( ',' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1223:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1224:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1224:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1225:1: ','
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0()); 
            match(input,15,FOLLOW_15_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl2492); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1238:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__1 : rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1242:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1243:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__12523);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1249:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1253:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1254:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1254:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1255:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_3_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1256:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1256:2: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl2550);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1270:1: rule__MandatoryAttributeAchieveCondition__Group__0 : rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1274:1: ( rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1275:2: rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__02584);
            rule__MandatoryAttributeAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1_in_rule__MandatoryAttributeAchieveCondition__Group__02587);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1282:1: rule__MandatoryAttributeAchieveCondition__Group__0__Impl : ( 'MAN' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1286:1: ( ( 'MAN' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1287:1: ( 'MAN' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1287:1: ( 'MAN' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1288:1: 'MAN'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__MandatoryAttributeAchieveCondition__Group__0__Impl2615); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1301:1: rule__MandatoryAttributeAchieveCondition__Group__1 : rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1305:1: ( rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1306:2: rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__12646);
            rule__MandatoryAttributeAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2_in_rule__MandatoryAttributeAchieveCondition__Group__12649);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1313:1: rule__MandatoryAttributeAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1317:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1318:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1318:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1319:1: '('
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__1__Impl2677); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1332:1: rule__MandatoryAttributeAchieveCondition__Group__2 : rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1336:1: ( rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1337:2: rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__22708);
            rule__MandatoryAttributeAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3_in_rule__MandatoryAttributeAchieveCondition__Group__22711);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1344:1: rule__MandatoryAttributeAchieveCondition__Group__2__Impl : ( 'DEF' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1348:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1349:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1349:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1350:1: 'DEF'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2()); 
            match(input,16,FOLLOW_16_in_rule__MandatoryAttributeAchieveCondition__Group__2__Impl2739); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1363:1: rule__MandatoryAttributeAchieveCondition__Group__3 : rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1367:1: ( rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1368:2: rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__32770);
            rule__MandatoryAttributeAchieveCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4_in_rule__MandatoryAttributeAchieveCondition__Group__32773);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1375:1: rule__MandatoryAttributeAchieveCondition__Group__3__Impl : ( '(' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1379:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1380:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1380:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1381:1: '('
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__3__Impl2801); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1394:1: rule__MandatoryAttributeAchieveCondition__Group__4 : rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1398:1: ( rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1399:2: rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__42832);
            rule__MandatoryAttributeAchieveCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5_in_rule__MandatoryAttributeAchieveCondition__Group__42835);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1406:1: rule__MandatoryAttributeAchieveCondition__Group__4__Impl : ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1410:1: ( ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1411:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1411:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1412:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1413:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1413:2: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4_in_rule__MandatoryAttributeAchieveCondition__Group__4__Impl2862);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1423:1: rule__MandatoryAttributeAchieveCondition__Group__5 : rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1427:1: ( rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1428:2: rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__52892);
            rule__MandatoryAttributeAchieveCondition__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6_in_rule__MandatoryAttributeAchieveCondition__Group__52895);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1435:1: rule__MandatoryAttributeAchieveCondition__Group__5__Impl : ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1439:1: ( ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1440:1: ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1440:1: ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1441:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )*
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1442:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==15) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1442:2: rule__MandatoryAttributeAchieveCondition__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0_in_rule__MandatoryAttributeAchieveCondition__Group__5__Impl2922);
            	    rule__MandatoryAttributeAchieveCondition__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1452:1: rule__MandatoryAttributeAchieveCondition__Group__6 : rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1456:1: ( rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1457:2: rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__62953);
            rule__MandatoryAttributeAchieveCondition__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7_in_rule__MandatoryAttributeAchieveCondition__Group__62956);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1464:1: rule__MandatoryAttributeAchieveCondition__Group__6__Impl : ( ')' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1468:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1469:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1469:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1470:1: ')'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__6__Impl2984); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1483:1: rule__MandatoryAttributeAchieveCondition__Group__7 : rule__MandatoryAttributeAchieveCondition__Group__7__Impl ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1487:1: ( rule__MandatoryAttributeAchieveCondition__Group__7__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1488:2: rule__MandatoryAttributeAchieveCondition__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__73015);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1494:1: rule__MandatoryAttributeAchieveCondition__Group__7__Impl : ( ')' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1498:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1499:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1499:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1500:1: ')'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_7()); 
            match(input,13,FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__7__Impl3043); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1529:1: rule__MandatoryAttributeAchieveCondition__Group_5__0 : rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1533:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1534:2: rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__03090);
            rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1_in_rule__MandatoryAttributeAchieveCondition__Group_5__03093);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1541:1: rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl : ( ',' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1545:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1546:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1546:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1547:1: ','
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0()); 
            match(input,15,FOLLOW_15_in_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl3121); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1560:1: rule__MandatoryAttributeAchieveCondition__Group_5__1 : rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1564:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1565:2: rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__13152);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1571:1: rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl : ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1575:1: ( ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1576:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1576:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1577:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_5_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1578:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1578:2: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1_in_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl3179);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1592:1: rule__AttributeInvariantCondition__Group__0 : rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1 ;
    public final void rule__AttributeInvariantCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1596:1: ( rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1597:2: rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__0__Impl_in_rule__AttributeInvariantCondition__Group__03213);
            rule__AttributeInvariantCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__1_in_rule__AttributeInvariantCondition__Group__03216);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1604:1: rule__AttributeInvariantCondition__Group__0__Impl : ( 'RUL' ) ;
    public final void rule__AttributeInvariantCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1608:1: ( ( 'RUL' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1609:1: ( 'RUL' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1609:1: ( 'RUL' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1610:1: 'RUL'
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0()); 
            match(input,18,FOLLOW_18_in_rule__AttributeInvariantCondition__Group__0__Impl3244); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1623:1: rule__AttributeInvariantCondition__Group__1 : rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2 ;
    public final void rule__AttributeInvariantCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1627:1: ( rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1628:2: rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__1__Impl_in_rule__AttributeInvariantCondition__Group__13275);
            rule__AttributeInvariantCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__2_in_rule__AttributeInvariantCondition__Group__13278);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1635:1: rule__AttributeInvariantCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__AttributeInvariantCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1639:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1640:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1640:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1641:1: '('
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__AttributeInvariantCondition__Group__1__Impl3306); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1654:1: rule__AttributeInvariantCondition__Group__2 : rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3 ;
    public final void rule__AttributeInvariantCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1658:1: ( rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1659:2: rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__2__Impl_in_rule__AttributeInvariantCondition__Group__23337);
            rule__AttributeInvariantCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__3_in_rule__AttributeInvariantCondition__Group__23340);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1666:1: rule__AttributeInvariantCondition__Group__2__Impl : ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) ) ;
    public final void rule__AttributeInvariantCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1670:1: ( ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1671:1: ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1671:1: ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1672:1: ( rule__AttributeInvariantCondition__ExpressionAssignment_2 )
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getExpressionAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1673:1: ( rule__AttributeInvariantCondition__ExpressionAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1673:2: rule__AttributeInvariantCondition__ExpressionAssignment_2
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__ExpressionAssignment_2_in_rule__AttributeInvariantCondition__Group__2__Impl3367);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1683:1: rule__AttributeInvariantCondition__Group__3 : rule__AttributeInvariantCondition__Group__3__Impl ;
    public final void rule__AttributeInvariantCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1687:1: ( rule__AttributeInvariantCondition__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1688:2: rule__AttributeInvariantCondition__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__3__Impl_in_rule__AttributeInvariantCondition__Group__33397);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1694:1: rule__AttributeInvariantCondition__Group__3__Impl : ( ')' ) ;
    public final void rule__AttributeInvariantCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1698:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1699:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1699:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1700:1: ')'
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getRightParenthesisKeyword_3()); 
            match(input,13,FOLLOW_13_in_rule__AttributeInvariantCondition__Group__3__Impl3425); 
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


    // $ANTLR start "rule__Cardinality__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1723:1: rule__Cardinality__Group_1__0 : rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 ;
    public final void rule__Cardinality__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1727:1: ( rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1728:2: rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__03466);
            rule__Cardinality__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__03469);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1735:1: rule__Cardinality__Group_1__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1739:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1740:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1740:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1741:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl3496); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1752:1: rule__Cardinality__Group_1__1 : rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 ;
    public final void rule__Cardinality__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1756:1: ( rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1757:2: rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__13525);
            rule__Cardinality__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__13528);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1764:1: rule__Cardinality__Group_1__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1768:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1769:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1769:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1770:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
            match(input,19,FOLLOW_19_in_rule__Cardinality__Group_1__1__Impl3556); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1783:1: rule__Cardinality__Group_1__2 : rule__Cardinality__Group_1__2__Impl ;
    public final void rule__Cardinality__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1787:1: ( rule__Cardinality__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1788:2: rule__Cardinality__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__23587);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1794:1: rule__Cardinality__Group_1__2__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1798:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1799:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1799:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1800:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl3614); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1817:1: rule__Cardinality__Group_2__0 : rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 ;
    public final void rule__Cardinality__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1821:1: ( rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1822:2: rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__03649);
            rule__Cardinality__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__03652);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1829:1: rule__Cardinality__Group_2__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1833:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1834:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1834:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1835:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl3679); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1846:1: rule__Cardinality__Group_2__1 : rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 ;
    public final void rule__Cardinality__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1850:1: ( rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1851:2: rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__13708);
            rule__Cardinality__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__13711);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1858:1: rule__Cardinality__Group_2__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1862:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1863:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1863:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1864:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
            match(input,19,FOLLOW_19_in_rule__Cardinality__Group_2__1__Impl3739); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1877:1: rule__Cardinality__Group_2__2 : rule__Cardinality__Group_2__2__Impl ;
    public final void rule__Cardinality__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1881:1: ( rule__Cardinality__Group_2__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1882:2: rule__Cardinality__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__23770);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1888:1: rule__Cardinality__Group_2__2__Impl : ( '*' ) ;
    public final void rule__Cardinality__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1892:1: ( ( '*' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1893:1: ( '*' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1893:1: ( '*' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1894:1: '*'
            {
             before(grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
            match(input,20,FOLLOW_20_in_rule__Cardinality__Group_2__2__Impl3798); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1913:1: rule__Cardinality__Group_3__0 : rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 ;
    public final void rule__Cardinality__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1917:1: ( rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1918:2: rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__03835);
            rule__Cardinality__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__03838);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1925:1: rule__Cardinality__Group_3__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1929:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1930:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1930:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1931:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl3865); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1942:1: rule__Cardinality__Group_3__1 : rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 ;
    public final void rule__Cardinality__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1946:1: ( rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1947:2: rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__13894);
            rule__Cardinality__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__13897);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1954:1: rule__Cardinality__Group_3__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1958:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1959:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1959:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1960:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
            match(input,19,FOLLOW_19_in_rule__Cardinality__Group_3__1__Impl3925); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1973:1: rule__Cardinality__Group_3__2 : rule__Cardinality__Group_3__2__Impl ;
    public final void rule__Cardinality__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1977:1: ( rule__Cardinality__Group_3__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1978:2: rule__Cardinality__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__23956);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1984:1: rule__Cardinality__Group_3__2__Impl : ( '+' ) ;
    public final void rule__Cardinality__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1988:1: ( ( '+' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1989:1: ( '+' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1989:1: ( '+' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:1990:1: '+'
            {
             before(grammarAccess.getCardinalityAccess().getPlusSignKeyword_3_2()); 
            match(input,21,FOLLOW_21_in_rule__Cardinality__Group_3__2__Impl3984); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2009:1: rule__Attribute__Group__0 : rule__Attribute__Group__0__Impl rule__Attribute__Group__1 ;
    public final void rule__Attribute__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2013:1: ( rule__Attribute__Group__0__Impl rule__Attribute__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2014:2: rule__Attribute__Group__0__Impl rule__Attribute__Group__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__04021);
            rule__Attribute__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__04024);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2021:1: rule__Attribute__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2025:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2026:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2026:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2027:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group__0__Impl4051); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2038:1: rule__Attribute__Group__1 : rule__Attribute__Group__1__Impl rule__Attribute__Group__2 ;
    public final void rule__Attribute__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2042:1: ( rule__Attribute__Group__1__Impl rule__Attribute__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2043:2: rule__Attribute__Group__1__Impl rule__Attribute__Group__2
            {
            pushFollow(FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__14080);
            rule__Attribute__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__14083);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2050:1: rule__Attribute__Group__1__Impl : ( '.' ) ;
    public final void rule__Attribute__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2054:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2055:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2055:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2056:1: '.'
            {
             before(grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
            match(input,22,FOLLOW_22_in_rule__Attribute__Group__1__Impl4111); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2069:1: rule__Attribute__Group__2 : rule__Attribute__Group__2__Impl rule__Attribute__Group__3 ;
    public final void rule__Attribute__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2073:1: ( rule__Attribute__Group__2__Impl rule__Attribute__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2074:2: rule__Attribute__Group__2__Impl rule__Attribute__Group__3
            {
            pushFollow(FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__24142);
            rule__Attribute__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__24145);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2081:1: rule__Attribute__Group__2__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2085:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2086:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2086:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2087:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group__2__Impl4172); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2098:1: rule__Attribute__Group__3 : rule__Attribute__Group__3__Impl ;
    public final void rule__Attribute__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2102:1: ( rule__Attribute__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2103:2: rule__Attribute__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__34201);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2109:1: rule__Attribute__Group__3__Impl : ( ( rule__Attribute__Group_3__0 )* ) ;
    public final void rule__Attribute__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2113:1: ( ( ( rule__Attribute__Group_3__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2114:1: ( ( rule__Attribute__Group_3__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2114:1: ( ( rule__Attribute__Group_3__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2115:1: ( rule__Attribute__Group_3__0 )*
            {
             before(grammarAccess.getAttributeAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2116:1: ( rule__Attribute__Group_3__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==22) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2116:2: rule__Attribute__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__Attribute__Group_3__0_in_rule__Attribute__Group__3__Impl4228);
            	    rule__Attribute__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2134:1: rule__Attribute__Group_3__0 : rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1 ;
    public final void rule__Attribute__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2138:1: ( rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2139:2: rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group_3__0__Impl_in_rule__Attribute__Group_3__04267);
            rule__Attribute__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group_3__1_in_rule__Attribute__Group_3__04270);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2146:1: rule__Attribute__Group_3__0__Impl : ( '.' ) ;
    public final void rule__Attribute__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2150:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2151:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2151:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2152:1: '.'
            {
             before(grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            match(input,22,FOLLOW_22_in_rule__Attribute__Group_3__0__Impl4298); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2165:1: rule__Attribute__Group_3__1 : rule__Attribute__Group_3__1__Impl ;
    public final void rule__Attribute__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2169:1: ( rule__Attribute__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2170:2: rule__Attribute__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group_3__1__Impl_in_rule__Attribute__Group_3__14329);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2176:1: rule__Attribute__Group_3__1__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2180:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2181:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2181:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2182:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group_3__1__Impl4356); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2197:1: rule__Or__Group__0 : rule__Or__Group__0__Impl rule__Or__Group__1 ;
    public final void rule__Or__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2201:1: ( rule__Or__Group__0__Impl rule__Or__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2202:2: rule__Or__Group__0__Impl rule__Or__Group__1
            {
            pushFollow(FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__04389);
            rule__Or__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group__1_in_rule__Or__Group__04392);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2209:1: rule__Or__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Or__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2213:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2214:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2214:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2215:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl4419);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2226:1: rule__Or__Group__1 : rule__Or__Group__1__Impl ;
    public final void rule__Or__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2230:1: ( rule__Or__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2231:2: rule__Or__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__14448);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2237:1: rule__Or__Group__1__Impl : ( ( rule__Or__Group_1__0 )* ) ;
    public final void rule__Or__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2241:1: ( ( ( rule__Or__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2242:1: ( ( rule__Or__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2242:1: ( ( rule__Or__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2243:1: ( rule__Or__Group_1__0 )*
            {
             before(grammarAccess.getOrAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2244:1: ( rule__Or__Group_1__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==23) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2244:2: rule__Or__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl4475);
            	    rule__Or__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2258:1: rule__Or__Group_1__0 : rule__Or__Group_1__0__Impl rule__Or__Group_1__1 ;
    public final void rule__Or__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2262:1: ( rule__Or__Group_1__0__Impl rule__Or__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2263:2: rule__Or__Group_1__0__Impl rule__Or__Group_1__1
            {
            pushFollow(FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__04510);
            rule__Or__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__04513);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2270:1: rule__Or__Group_1__0__Impl : ( () ) ;
    public final void rule__Or__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2274:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2275:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2275:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2276:1: ()
            {
             before(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2277:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2279:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2289:1: rule__Or__Group_1__1 : rule__Or__Group_1__1__Impl rule__Or__Group_1__2 ;
    public final void rule__Or__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2293:1: ( rule__Or__Group_1__1__Impl rule__Or__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2294:2: rule__Or__Group_1__1__Impl rule__Or__Group_1__2
            {
            pushFollow(FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__14571);
            rule__Or__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__14574);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2301:1: rule__Or__Group_1__1__Impl : ( 'OR' ) ;
    public final void rule__Or__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2305:1: ( ( 'OR' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2306:1: ( 'OR' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2306:1: ( 'OR' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2307:1: 'OR'
            {
             before(grammarAccess.getOrAccess().getORKeyword_1_1()); 
            match(input,23,FOLLOW_23_in_rule__Or__Group_1__1__Impl4602); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2320:1: rule__Or__Group_1__2 : rule__Or__Group_1__2__Impl ;
    public final void rule__Or__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2324:1: ( rule__Or__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2325:2: rule__Or__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__24633);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2331:1: rule__Or__Group_1__2__Impl : ( ( rule__Or__RightAssignment_1_2 ) ) ;
    public final void rule__Or__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2335:1: ( ( ( rule__Or__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2336:1: ( ( rule__Or__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2336:1: ( ( rule__Or__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2337:1: ( rule__Or__RightAssignment_1_2 )
            {
             before(grammarAccess.getOrAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2338:1: ( rule__Or__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2338:2: rule__Or__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl4660);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2354:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2358:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2359:2: rule__And__Group__0__Impl rule__And__Group__1
            {
            pushFollow(FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__04696);
            rule__And__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group__1_in_rule__And__Group__04699);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2366:1: rule__And__Group__0__Impl : ( rulePrimary ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2370:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2371:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2371:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2372:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__Group__0__Impl4726);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2383:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2387:1: ( rule__And__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2388:2: rule__And__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__14755);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2394:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2398:1: ( ( ( rule__And__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2399:1: ( ( rule__And__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2399:1: ( ( rule__And__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2400:1: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2401:1: ( rule__And__Group_1__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==24) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2401:2: rule__And__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl4782);
            	    rule__And__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2415:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2419:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2420:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
            {
            pushFollow(FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__04817);
            rule__And__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__04820);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2427:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2431:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2432:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2432:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2433:1: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2434:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2436:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2446:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2450:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2451:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__14878);
            rule__And__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__14881);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2458:1: rule__And__Group_1__1__Impl : ( 'AND' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2462:1: ( ( 'AND' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2463:1: ( 'AND' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2463:1: ( 'AND' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2464:1: 'AND'
            {
             before(grammarAccess.getAndAccess().getANDKeyword_1_1()); 
            match(input,24,FOLLOW_24_in_rule__And__Group_1__1__Impl4909); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2477:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2481:1: ( rule__And__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2482:2: rule__And__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__24940);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2488:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2492:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2493:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2493:1: ( ( rule__And__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2494:1: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2495:1: ( rule__And__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2495:2: rule__And__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl4967);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2511:1: rule__Primary__Group_0__0 : rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 ;
    public final void rule__Primary__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2515:1: ( rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2516:2: rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__05003);
            rule__Primary__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__05006);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2523:1: rule__Primary__Group_0__0__Impl : ( '(' ) ;
    public final void rule__Primary__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2527:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2528:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2528:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2529:1: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0()); 
            match(input,12,FOLLOW_12_in_rule__Primary__Group_0__0__Impl5034); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2542:1: rule__Primary__Group_0__1 : rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 ;
    public final void rule__Primary__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2546:1: ( rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2547:2: rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__15065);
            rule__Primary__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__15068);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2554:1: rule__Primary__Group_0__1__Impl : ( ruleExpression ) ;
    public final void rule__Primary__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2558:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2559:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2559:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2560:1: ruleExpression
            {
             before(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl5095);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2571:1: rule__Primary__Group_0__2 : rule__Primary__Group_0__2__Impl ;
    public final void rule__Primary__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2575:1: ( rule__Primary__Group_0__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2576:2: rule__Primary__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__25124);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2582:1: rule__Primary__Group_0__2__Impl : ( ')' ) ;
    public final void rule__Primary__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2586:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2587:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2587:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2588:1: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2()); 
            match(input,13,FOLLOW_13_in_rule__Primary__Group_0__2__Impl5152); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2607:1: rule__Primary__Group_1__0 : rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 ;
    public final void rule__Primary__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2611:1: ( rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2612:2: rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__05189);
            rule__Primary__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__05192);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2619:1: rule__Primary__Group_1__0__Impl : ( () ) ;
    public final void rule__Primary__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2623:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2624:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2624:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2625:1: ()
            {
             before(grammarAccess.getPrimaryAccess().getNotAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2626:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2628:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2638:1: rule__Primary__Group_1__1 : rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 ;
    public final void rule__Primary__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2642:1: ( rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2643:2: rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__15250);
            rule__Primary__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__15253);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2650:1: rule__Primary__Group_1__1__Impl : ( 'NOT' ) ;
    public final void rule__Primary__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2654:1: ( ( 'NOT' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2655:1: ( 'NOT' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2655:1: ( 'NOT' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2656:1: 'NOT'
            {
             before(grammarAccess.getPrimaryAccess().getNOTKeyword_1_1()); 
            match(input,25,FOLLOW_25_in_rule__Primary__Group_1__1__Impl5281); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2669:1: rule__Primary__Group_1__2 : rule__Primary__Group_1__2__Impl ;
    public final void rule__Primary__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2673:1: ( rule__Primary__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2674:2: rule__Primary__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__25312);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2680:1: rule__Primary__Group_1__2__Impl : ( ( rule__Primary__ExpressionAssignment_1_2 ) ) ;
    public final void rule__Primary__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2684:1: ( ( ( rule__Primary__ExpressionAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2685:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2685:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2686:1: ( rule__Primary__ExpressionAssignment_1_2 )
            {
             before(grammarAccess.getPrimaryAccess().getExpressionAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2687:1: ( rule__Primary__ExpressionAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2687:2: rule__Primary__ExpressionAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl5339);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2703:1: rule__Atomic__Group_0__0 : rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 ;
    public final void rule__Atomic__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2707:1: ( rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2708:2: rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__05375);
            rule__Atomic__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__05378);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2715:1: rule__Atomic__Group_0__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2719:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2720:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2720:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2721:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2722:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2724:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2734:1: rule__Atomic__Group_0__1 : rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 ;
    public final void rule__Atomic__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2738:1: ( rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2739:2: rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__15436);
            rule__Atomic__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__15439);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2746:1: rule__Atomic__Group_0__1__Impl : ( 'DEF' ) ;
    public final void rule__Atomic__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2750:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2751:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2751:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2752:1: 'DEF'
            {
             before(grammarAccess.getAtomicAccess().getDEFKeyword_0_1()); 
            match(input,16,FOLLOW_16_in_rule__Atomic__Group_0__1__Impl5467); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2765:1: rule__Atomic__Group_0__2 : rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 ;
    public final void rule__Atomic__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2769:1: ( rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2770:2: rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__25498);
            rule__Atomic__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__25501);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2777:1: rule__Atomic__Group_0__2__Impl : ( '(' ) ;
    public final void rule__Atomic__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2781:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2782:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2782:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2783:1: '('
            {
             before(grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2()); 
            match(input,12,FOLLOW_12_in_rule__Atomic__Group_0__2__Impl5529); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2796:1: rule__Atomic__Group_0__3 : rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 ;
    public final void rule__Atomic__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2800:1: ( rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2801:2: rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__35560);
            rule__Atomic__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__35563);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2808:1: rule__Atomic__Group_0__3__Impl : ( ( rule__Atomic__NameAssignment_0_3 ) ) ;
    public final void rule__Atomic__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2812:1: ( ( ( rule__Atomic__NameAssignment_0_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2813:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2813:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2814:1: ( rule__Atomic__NameAssignment_0_3 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_0_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2815:1: ( rule__Atomic__NameAssignment_0_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2815:2: rule__Atomic__NameAssignment_0_3
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl5590);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2825:1: rule__Atomic__Group_0__4 : rule__Atomic__Group_0__4__Impl ;
    public final void rule__Atomic__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2829:1: ( rule__Atomic__Group_0__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2830:2: rule__Atomic__Group_0__4__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__45620);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2836:1: rule__Atomic__Group_0__4__Impl : ( ')' ) ;
    public final void rule__Atomic__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2840:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2841:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2841:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2842:1: ')'
            {
             before(grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4()); 
            match(input,13,FOLLOW_13_in_rule__Atomic__Group_0__4__Impl5648); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2865:1: rule__Atomic__Group_1__0 : rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 ;
    public final void rule__Atomic__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2869:1: ( rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2870:2: rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__05689);
            rule__Atomic__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__05692);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2877:1: rule__Atomic__Group_1__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2881:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2882:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2882:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2883:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeValueAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2884:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2886:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2896:1: rule__Atomic__Group_1__1 : rule__Atomic__Group_1__1__Impl ;
    public final void rule__Atomic__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2900:1: ( rule__Atomic__Group_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2901:2: rule__Atomic__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__15750);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2907:1: rule__Atomic__Group_1__1__Impl : ( ( rule__Atomic__NameAssignment_1_1 ) ) ;
    public final void rule__Atomic__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2911:1: ( ( ( rule__Atomic__NameAssignment_1_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2912:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2912:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2913:1: ( rule__Atomic__NameAssignment_1_1 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2914:1: ( rule__Atomic__NameAssignment_1_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2914:2: rule__Atomic__NameAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl5777);
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


    // $ANTLR start "rule__Goal__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2928:1: rule__Goal__Group__0 : rule__Goal__Group__0__Impl rule__Goal__Group__1 ;
    public final void rule__Goal__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2932:1: ( rule__Goal__Group__0__Impl rule__Goal__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2933:2: rule__Goal__Group__0__Impl rule__Goal__Group__1
            {
            pushFollow(FOLLOW_rule__Goal__Group__0__Impl_in_rule__Goal__Group__05811);
            rule__Goal__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__1_in_rule__Goal__Group__05814);
            rule__Goal__Group__1();

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
    // $ANTLR end "rule__Goal__Group__0"


    // $ANTLR start "rule__Goal__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2940:1: rule__Goal__Group__0__Impl : ( ( rule__Goal__NameAssignment_0 ) ) ;
    public final void rule__Goal__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2944:1: ( ( ( rule__Goal__NameAssignment_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2945:1: ( ( rule__Goal__NameAssignment_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2945:1: ( ( rule__Goal__NameAssignment_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2946:1: ( rule__Goal__NameAssignment_0 )
            {
             before(grammarAccess.getGoalAccess().getNameAssignment_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2947:1: ( rule__Goal__NameAssignment_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2947:2: rule__Goal__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__Goal__NameAssignment_0_in_rule__Goal__Group__0__Impl5841);
            rule__Goal__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getNameAssignment_0()); 

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
    // $ANTLR end "rule__Goal__Group__0__Impl"


    // $ANTLR start "rule__Goal__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2957:1: rule__Goal__Group__1 : rule__Goal__Group__1__Impl rule__Goal__Group__2 ;
    public final void rule__Goal__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2961:1: ( rule__Goal__Group__1__Impl rule__Goal__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2962:2: rule__Goal__Group__1__Impl rule__Goal__Group__2
            {
            pushFollow(FOLLOW_rule__Goal__Group__1__Impl_in_rule__Goal__Group__15871);
            rule__Goal__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__2_in_rule__Goal__Group__15874);
            rule__Goal__Group__2();

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
    // $ANTLR end "rule__Goal__Group__1"


    // $ANTLR start "rule__Goal__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2969:1: rule__Goal__Group__1__Impl : ( ':' ) ;
    public final void rule__Goal__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2973:1: ( ( ':' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2974:1: ( ':' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2974:1: ( ':' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2975:1: ':'
            {
             before(grammarAccess.getGoalAccess().getColonKeyword_1()); 
            match(input,26,FOLLOW_26_in_rule__Goal__Group__1__Impl5902); 
             after(grammarAccess.getGoalAccess().getColonKeyword_1()); 

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
    // $ANTLR end "rule__Goal__Group__1__Impl"


    // $ANTLR start "rule__Goal__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2988:1: rule__Goal__Group__2 : rule__Goal__Group__2__Impl rule__Goal__Group__3 ;
    public final void rule__Goal__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2992:1: ( rule__Goal__Group__2__Impl rule__Goal__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:2993:2: rule__Goal__Group__2__Impl rule__Goal__Group__3
            {
            pushFollow(FOLLOW_rule__Goal__Group__2__Impl_in_rule__Goal__Group__25933);
            rule__Goal__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__3_in_rule__Goal__Group__25936);
            rule__Goal__Group__3();

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
    // $ANTLR end "rule__Goal__Group__2"


    // $ANTLR start "rule__Goal__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3000:1: rule__Goal__Group__2__Impl : ( 'SUC' ) ;
    public final void rule__Goal__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3004:1: ( ( 'SUC' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3005:1: ( 'SUC' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3005:1: ( 'SUC' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3006:1: 'SUC'
            {
             before(grammarAccess.getGoalAccess().getSUCKeyword_2()); 
            match(input,27,FOLLOW_27_in_rule__Goal__Group__2__Impl5964); 
             after(grammarAccess.getGoalAccess().getSUCKeyword_2()); 

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
    // $ANTLR end "rule__Goal__Group__2__Impl"


    // $ANTLR start "rule__Goal__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3019:1: rule__Goal__Group__3 : rule__Goal__Group__3__Impl rule__Goal__Group__4 ;
    public final void rule__Goal__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3023:1: ( rule__Goal__Group__3__Impl rule__Goal__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3024:2: rule__Goal__Group__3__Impl rule__Goal__Group__4
            {
            pushFollow(FOLLOW_rule__Goal__Group__3__Impl_in_rule__Goal__Group__35995);
            rule__Goal__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__4_in_rule__Goal__Group__35998);
            rule__Goal__Group__4();

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
    // $ANTLR end "rule__Goal__Group__3"


    // $ANTLR start "rule__Goal__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3031:1: rule__Goal__Group__3__Impl : ( '(' ) ;
    public final void rule__Goal__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3035:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3036:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3036:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3037:1: '('
            {
             before(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__Goal__Group__3__Impl6026); 
             after(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_3()); 

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
    // $ANTLR end "rule__Goal__Group__3__Impl"


    // $ANTLR start "rule__Goal__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3050:1: rule__Goal__Group__4 : rule__Goal__Group__4__Impl rule__Goal__Group__5 ;
    public final void rule__Goal__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3054:1: ( rule__Goal__Group__4__Impl rule__Goal__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3055:2: rule__Goal__Group__4__Impl rule__Goal__Group__5
            {
            pushFollow(FOLLOW_rule__Goal__Group__4__Impl_in_rule__Goal__Group__46057);
            rule__Goal__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__5_in_rule__Goal__Group__46060);
            rule__Goal__Group__5();

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
    // $ANTLR end "rule__Goal__Group__4"


    // $ANTLR start "rule__Goal__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3062:1: rule__Goal__Group__4__Impl : ( ( rule__Goal__SucessConditionAssignment_4 ) ) ;
    public final void rule__Goal__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3066:1: ( ( ( rule__Goal__SucessConditionAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3067:1: ( ( rule__Goal__SucessConditionAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3067:1: ( ( rule__Goal__SucessConditionAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3068:1: ( rule__Goal__SucessConditionAssignment_4 )
            {
             before(grammarAccess.getGoalAccess().getSucessConditionAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3069:1: ( rule__Goal__SucessConditionAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3069:2: rule__Goal__SucessConditionAssignment_4
            {
            pushFollow(FOLLOW_rule__Goal__SucessConditionAssignment_4_in_rule__Goal__Group__4__Impl6087);
            rule__Goal__SucessConditionAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getSucessConditionAssignment_4()); 

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
    // $ANTLR end "rule__Goal__Group__4__Impl"


    // $ANTLR start "rule__Goal__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3079:1: rule__Goal__Group__5 : rule__Goal__Group__5__Impl rule__Goal__Group__6 ;
    public final void rule__Goal__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3083:1: ( rule__Goal__Group__5__Impl rule__Goal__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3084:2: rule__Goal__Group__5__Impl rule__Goal__Group__6
            {
            pushFollow(FOLLOW_rule__Goal__Group__5__Impl_in_rule__Goal__Group__56117);
            rule__Goal__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__6_in_rule__Goal__Group__56120);
            rule__Goal__Group__6();

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
    // $ANTLR end "rule__Goal__Group__5"


    // $ANTLR start "rule__Goal__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3091:1: rule__Goal__Group__5__Impl : ( ')' ) ;
    public final void rule__Goal__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3095:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3096:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3096:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3097:1: ')'
            {
             before(grammarAccess.getGoalAccess().getRightParenthesisKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__Goal__Group__5__Impl6148); 
             after(grammarAccess.getGoalAccess().getRightParenthesisKeyword_5()); 

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
    // $ANTLR end "rule__Goal__Group__5__Impl"


    // $ANTLR start "rule__Goal__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3110:1: rule__Goal__Group__6 : rule__Goal__Group__6__Impl rule__Goal__Group__7 ;
    public final void rule__Goal__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3114:1: ( rule__Goal__Group__6__Impl rule__Goal__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3115:2: rule__Goal__Group__6__Impl rule__Goal__Group__7
            {
            pushFollow(FOLLOW_rule__Goal__Group__6__Impl_in_rule__Goal__Group__66179);
            rule__Goal__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__7_in_rule__Goal__Group__66182);
            rule__Goal__Group__7();

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
    // $ANTLR end "rule__Goal__Group__6"


    // $ANTLR start "rule__Goal__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3122:1: rule__Goal__Group__6__Impl : ( ( rule__Goal__Group_6__0 )? ) ;
    public final void rule__Goal__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3126:1: ( ( ( rule__Goal__Group_6__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3127:1: ( ( rule__Goal__Group_6__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3127:1: ( ( rule__Goal__Group_6__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3128:1: ( rule__Goal__Group_6__0 )?
            {
             before(grammarAccess.getGoalAccess().getGroup_6()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3129:1: ( rule__Goal__Group_6__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==15) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==28) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3129:2: rule__Goal__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__Goal__Group_6__0_in_rule__Goal__Group__6__Impl6209);
                    rule__Goal__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGoalAccess().getGroup_6()); 

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
    // $ANTLR end "rule__Goal__Group__6__Impl"


    // $ANTLR start "rule__Goal__Group__7"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3139:1: rule__Goal__Group__7 : rule__Goal__Group__7__Impl rule__Goal__Group__8 ;
    public final void rule__Goal__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3143:1: ( rule__Goal__Group__7__Impl rule__Goal__Group__8 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3144:2: rule__Goal__Group__7__Impl rule__Goal__Group__8
            {
            pushFollow(FOLLOW_rule__Goal__Group__7__Impl_in_rule__Goal__Group__76240);
            rule__Goal__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group__8_in_rule__Goal__Group__76243);
            rule__Goal__Group__8();

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
    // $ANTLR end "rule__Goal__Group__7"


    // $ANTLR start "rule__Goal__Group__7__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3151:1: rule__Goal__Group__7__Impl : ( ( rule__Goal__Group_7__0 )? ) ;
    public final void rule__Goal__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3155:1: ( ( ( rule__Goal__Group_7__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3156:1: ( ( rule__Goal__Group_7__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3156:1: ( ( rule__Goal__Group_7__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3157:1: ( rule__Goal__Group_7__0 )?
            {
             before(grammarAccess.getGoalAccess().getGroup_7()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3158:1: ( rule__Goal__Group_7__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==15) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==29) ) {
                    alt16=1;
                }
            }
            switch (alt16) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3158:2: rule__Goal__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__Goal__Group_7__0_in_rule__Goal__Group__7__Impl6270);
                    rule__Goal__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGoalAccess().getGroup_7()); 

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
    // $ANTLR end "rule__Goal__Group__7__Impl"


    // $ANTLR start "rule__Goal__Group__8"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3168:1: rule__Goal__Group__8 : rule__Goal__Group__8__Impl ;
    public final void rule__Goal__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3172:1: ( rule__Goal__Group__8__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3173:2: rule__Goal__Group__8__Impl
            {
            pushFollow(FOLLOW_rule__Goal__Group__8__Impl_in_rule__Goal__Group__86301);
            rule__Goal__Group__8__Impl();

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
    // $ANTLR end "rule__Goal__Group__8"


    // $ANTLR start "rule__Goal__Group__8__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3179:1: rule__Goal__Group__8__Impl : ( ( rule__Goal__Group_8__0 )? ) ;
    public final void rule__Goal__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3183:1: ( ( ( rule__Goal__Group_8__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3184:1: ( ( rule__Goal__Group_8__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3184:1: ( ( rule__Goal__Group_8__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3185:1: ( rule__Goal__Group_8__0 )?
            {
             before(grammarAccess.getGoalAccess().getGroup_8()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3186:1: ( rule__Goal__Group_8__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==15) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3186:2: rule__Goal__Group_8__0
                    {
                    pushFollow(FOLLOW_rule__Goal__Group_8__0_in_rule__Goal__Group__8__Impl6328);
                    rule__Goal__Group_8__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGoalAccess().getGroup_8()); 

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
    // $ANTLR end "rule__Goal__Group__8__Impl"


    // $ANTLR start "rule__Goal__Group_6__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3214:1: rule__Goal__Group_6__0 : rule__Goal__Group_6__0__Impl rule__Goal__Group_6__1 ;
    public final void rule__Goal__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3218:1: ( rule__Goal__Group_6__0__Impl rule__Goal__Group_6__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3219:2: rule__Goal__Group_6__0__Impl rule__Goal__Group_6__1
            {
            pushFollow(FOLLOW_rule__Goal__Group_6__0__Impl_in_rule__Goal__Group_6__06377);
            rule__Goal__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_6__1_in_rule__Goal__Group_6__06380);
            rule__Goal__Group_6__1();

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
    // $ANTLR end "rule__Goal__Group_6__0"


    // $ANTLR start "rule__Goal__Group_6__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3226:1: rule__Goal__Group_6__0__Impl : ( ',' ) ;
    public final void rule__Goal__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3230:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3231:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3231:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3232:1: ','
            {
             before(grammarAccess.getGoalAccess().getCommaKeyword_6_0()); 
            match(input,15,FOLLOW_15_in_rule__Goal__Group_6__0__Impl6408); 
             after(grammarAccess.getGoalAccess().getCommaKeyword_6_0()); 

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
    // $ANTLR end "rule__Goal__Group_6__0__Impl"


    // $ANTLR start "rule__Goal__Group_6__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3245:1: rule__Goal__Group_6__1 : rule__Goal__Group_6__1__Impl rule__Goal__Group_6__2 ;
    public final void rule__Goal__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3249:1: ( rule__Goal__Group_6__1__Impl rule__Goal__Group_6__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3250:2: rule__Goal__Group_6__1__Impl rule__Goal__Group_6__2
            {
            pushFollow(FOLLOW_rule__Goal__Group_6__1__Impl_in_rule__Goal__Group_6__16439);
            rule__Goal__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_6__2_in_rule__Goal__Group_6__16442);
            rule__Goal__Group_6__2();

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
    // $ANTLR end "rule__Goal__Group_6__1"


    // $ANTLR start "rule__Goal__Group_6__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3257:1: rule__Goal__Group_6__1__Impl : ( 'ACT' ) ;
    public final void rule__Goal__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3261:1: ( ( 'ACT' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3262:1: ( 'ACT' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3262:1: ( 'ACT' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3263:1: 'ACT'
            {
             before(grammarAccess.getGoalAccess().getACTKeyword_6_1()); 
            match(input,28,FOLLOW_28_in_rule__Goal__Group_6__1__Impl6470); 
             after(grammarAccess.getGoalAccess().getACTKeyword_6_1()); 

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
    // $ANTLR end "rule__Goal__Group_6__1__Impl"


    // $ANTLR start "rule__Goal__Group_6__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3276:1: rule__Goal__Group_6__2 : rule__Goal__Group_6__2__Impl rule__Goal__Group_6__3 ;
    public final void rule__Goal__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3280:1: ( rule__Goal__Group_6__2__Impl rule__Goal__Group_6__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3281:2: rule__Goal__Group_6__2__Impl rule__Goal__Group_6__3
            {
            pushFollow(FOLLOW_rule__Goal__Group_6__2__Impl_in_rule__Goal__Group_6__26501);
            rule__Goal__Group_6__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_6__3_in_rule__Goal__Group_6__26504);
            rule__Goal__Group_6__3();

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
    // $ANTLR end "rule__Goal__Group_6__2"


    // $ANTLR start "rule__Goal__Group_6__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3288:1: rule__Goal__Group_6__2__Impl : ( '(' ) ;
    public final void rule__Goal__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3292:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3293:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3293:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3294:1: '('
            {
             before(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_6_2()); 
            match(input,12,FOLLOW_12_in_rule__Goal__Group_6__2__Impl6532); 
             after(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_6_2()); 

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
    // $ANTLR end "rule__Goal__Group_6__2__Impl"


    // $ANTLR start "rule__Goal__Group_6__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3307:1: rule__Goal__Group_6__3 : rule__Goal__Group_6__3__Impl rule__Goal__Group_6__4 ;
    public final void rule__Goal__Group_6__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3311:1: ( rule__Goal__Group_6__3__Impl rule__Goal__Group_6__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3312:2: rule__Goal__Group_6__3__Impl rule__Goal__Group_6__4
            {
            pushFollow(FOLLOW_rule__Goal__Group_6__3__Impl_in_rule__Goal__Group_6__36563);
            rule__Goal__Group_6__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_6__4_in_rule__Goal__Group_6__36566);
            rule__Goal__Group_6__4();

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
    // $ANTLR end "rule__Goal__Group_6__3"


    // $ANTLR start "rule__Goal__Group_6__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3319:1: rule__Goal__Group_6__3__Impl : ( ( rule__Goal__ActivationConditionAssignment_6_3 ) ) ;
    public final void rule__Goal__Group_6__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3323:1: ( ( ( rule__Goal__ActivationConditionAssignment_6_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3324:1: ( ( rule__Goal__ActivationConditionAssignment_6_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3324:1: ( ( rule__Goal__ActivationConditionAssignment_6_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3325:1: ( rule__Goal__ActivationConditionAssignment_6_3 )
            {
             before(grammarAccess.getGoalAccess().getActivationConditionAssignment_6_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3326:1: ( rule__Goal__ActivationConditionAssignment_6_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3326:2: rule__Goal__ActivationConditionAssignment_6_3
            {
            pushFollow(FOLLOW_rule__Goal__ActivationConditionAssignment_6_3_in_rule__Goal__Group_6__3__Impl6593);
            rule__Goal__ActivationConditionAssignment_6_3();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getActivationConditionAssignment_6_3()); 

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
    // $ANTLR end "rule__Goal__Group_6__3__Impl"


    // $ANTLR start "rule__Goal__Group_6__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3336:1: rule__Goal__Group_6__4 : rule__Goal__Group_6__4__Impl ;
    public final void rule__Goal__Group_6__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3340:1: ( rule__Goal__Group_6__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3341:2: rule__Goal__Group_6__4__Impl
            {
            pushFollow(FOLLOW_rule__Goal__Group_6__4__Impl_in_rule__Goal__Group_6__46623);
            rule__Goal__Group_6__4__Impl();

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
    // $ANTLR end "rule__Goal__Group_6__4"


    // $ANTLR start "rule__Goal__Group_6__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3347:1: rule__Goal__Group_6__4__Impl : ( ')' ) ;
    public final void rule__Goal__Group_6__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3351:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3352:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3352:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3353:1: ')'
            {
             before(grammarAccess.getGoalAccess().getRightParenthesisKeyword_6_4()); 
            match(input,13,FOLLOW_13_in_rule__Goal__Group_6__4__Impl6651); 
             after(grammarAccess.getGoalAccess().getRightParenthesisKeyword_6_4()); 

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
    // $ANTLR end "rule__Goal__Group_6__4__Impl"


    // $ANTLR start "rule__Goal__Group_7__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3376:1: rule__Goal__Group_7__0 : rule__Goal__Group_7__0__Impl rule__Goal__Group_7__1 ;
    public final void rule__Goal__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3380:1: ( rule__Goal__Group_7__0__Impl rule__Goal__Group_7__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3381:2: rule__Goal__Group_7__0__Impl rule__Goal__Group_7__1
            {
            pushFollow(FOLLOW_rule__Goal__Group_7__0__Impl_in_rule__Goal__Group_7__06692);
            rule__Goal__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_7__1_in_rule__Goal__Group_7__06695);
            rule__Goal__Group_7__1();

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
    // $ANTLR end "rule__Goal__Group_7__0"


    // $ANTLR start "rule__Goal__Group_7__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3388:1: rule__Goal__Group_7__0__Impl : ( ',' ) ;
    public final void rule__Goal__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3392:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3393:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3393:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3394:1: ','
            {
             before(grammarAccess.getGoalAccess().getCommaKeyword_7_0()); 
            match(input,15,FOLLOW_15_in_rule__Goal__Group_7__0__Impl6723); 
             after(grammarAccess.getGoalAccess().getCommaKeyword_7_0()); 

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
    // $ANTLR end "rule__Goal__Group_7__0__Impl"


    // $ANTLR start "rule__Goal__Group_7__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3407:1: rule__Goal__Group_7__1 : rule__Goal__Group_7__1__Impl rule__Goal__Group_7__2 ;
    public final void rule__Goal__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3411:1: ( rule__Goal__Group_7__1__Impl rule__Goal__Group_7__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3412:2: rule__Goal__Group_7__1__Impl rule__Goal__Group_7__2
            {
            pushFollow(FOLLOW_rule__Goal__Group_7__1__Impl_in_rule__Goal__Group_7__16754);
            rule__Goal__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_7__2_in_rule__Goal__Group_7__16757);
            rule__Goal__Group_7__2();

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
    // $ANTLR end "rule__Goal__Group_7__1"


    // $ANTLR start "rule__Goal__Group_7__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3419:1: rule__Goal__Group_7__1__Impl : ( 'INV' ) ;
    public final void rule__Goal__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3423:1: ( ( 'INV' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3424:1: ( 'INV' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3424:1: ( 'INV' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3425:1: 'INV'
            {
             before(grammarAccess.getGoalAccess().getINVKeyword_7_1()); 
            match(input,29,FOLLOW_29_in_rule__Goal__Group_7__1__Impl6785); 
             after(grammarAccess.getGoalAccess().getINVKeyword_7_1()); 

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
    // $ANTLR end "rule__Goal__Group_7__1__Impl"


    // $ANTLR start "rule__Goal__Group_7__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3438:1: rule__Goal__Group_7__2 : rule__Goal__Group_7__2__Impl rule__Goal__Group_7__3 ;
    public final void rule__Goal__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3442:1: ( rule__Goal__Group_7__2__Impl rule__Goal__Group_7__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3443:2: rule__Goal__Group_7__2__Impl rule__Goal__Group_7__3
            {
            pushFollow(FOLLOW_rule__Goal__Group_7__2__Impl_in_rule__Goal__Group_7__26816);
            rule__Goal__Group_7__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_7__3_in_rule__Goal__Group_7__26819);
            rule__Goal__Group_7__3();

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
    // $ANTLR end "rule__Goal__Group_7__2"


    // $ANTLR start "rule__Goal__Group_7__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3450:1: rule__Goal__Group_7__2__Impl : ( '(' ) ;
    public final void rule__Goal__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3454:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3455:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3455:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3456:1: '('
            {
             before(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_7_2()); 
            match(input,12,FOLLOW_12_in_rule__Goal__Group_7__2__Impl6847); 
             after(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_7_2()); 

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
    // $ANTLR end "rule__Goal__Group_7__2__Impl"


    // $ANTLR start "rule__Goal__Group_7__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3469:1: rule__Goal__Group_7__3 : rule__Goal__Group_7__3__Impl rule__Goal__Group_7__4 ;
    public final void rule__Goal__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3473:1: ( rule__Goal__Group_7__3__Impl rule__Goal__Group_7__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3474:2: rule__Goal__Group_7__3__Impl rule__Goal__Group_7__4
            {
            pushFollow(FOLLOW_rule__Goal__Group_7__3__Impl_in_rule__Goal__Group_7__36878);
            rule__Goal__Group_7__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_7__4_in_rule__Goal__Group_7__36881);
            rule__Goal__Group_7__4();

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
    // $ANTLR end "rule__Goal__Group_7__3"


    // $ANTLR start "rule__Goal__Group_7__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3481:1: rule__Goal__Group_7__3__Impl : ( ( rule__Goal__InvariantConditionsAssignment_7_3 ) ) ;
    public final void rule__Goal__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3485:1: ( ( ( rule__Goal__InvariantConditionsAssignment_7_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3486:1: ( ( rule__Goal__InvariantConditionsAssignment_7_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3486:1: ( ( rule__Goal__InvariantConditionsAssignment_7_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3487:1: ( rule__Goal__InvariantConditionsAssignment_7_3 )
            {
             before(grammarAccess.getGoalAccess().getInvariantConditionsAssignment_7_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3488:1: ( rule__Goal__InvariantConditionsAssignment_7_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3488:2: rule__Goal__InvariantConditionsAssignment_7_3
            {
            pushFollow(FOLLOW_rule__Goal__InvariantConditionsAssignment_7_3_in_rule__Goal__Group_7__3__Impl6908);
            rule__Goal__InvariantConditionsAssignment_7_3();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getInvariantConditionsAssignment_7_3()); 

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
    // $ANTLR end "rule__Goal__Group_7__3__Impl"


    // $ANTLR start "rule__Goal__Group_7__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3498:1: rule__Goal__Group_7__4 : rule__Goal__Group_7__4__Impl rule__Goal__Group_7__5 ;
    public final void rule__Goal__Group_7__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3502:1: ( rule__Goal__Group_7__4__Impl rule__Goal__Group_7__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3503:2: rule__Goal__Group_7__4__Impl rule__Goal__Group_7__5
            {
            pushFollow(FOLLOW_rule__Goal__Group_7__4__Impl_in_rule__Goal__Group_7__46938);
            rule__Goal__Group_7__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_7__5_in_rule__Goal__Group_7__46941);
            rule__Goal__Group_7__5();

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
    // $ANTLR end "rule__Goal__Group_7__4"


    // $ANTLR start "rule__Goal__Group_7__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3510:1: rule__Goal__Group_7__4__Impl : ( ( rule__Goal__Group_7_4__0 )* ) ;
    public final void rule__Goal__Group_7__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3514:1: ( ( ( rule__Goal__Group_7_4__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3515:1: ( ( rule__Goal__Group_7_4__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3515:1: ( ( rule__Goal__Group_7_4__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3516:1: ( rule__Goal__Group_7_4__0 )*
            {
             before(grammarAccess.getGoalAccess().getGroup_7_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3517:1: ( rule__Goal__Group_7_4__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==15) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3517:2: rule__Goal__Group_7_4__0
            	    {
            	    pushFollow(FOLLOW_rule__Goal__Group_7_4__0_in_rule__Goal__Group_7__4__Impl6968);
            	    rule__Goal__Group_7_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getGoalAccess().getGroup_7_4()); 

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
    // $ANTLR end "rule__Goal__Group_7__4__Impl"


    // $ANTLR start "rule__Goal__Group_7__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3527:1: rule__Goal__Group_7__5 : rule__Goal__Group_7__5__Impl ;
    public final void rule__Goal__Group_7__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3531:1: ( rule__Goal__Group_7__5__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3532:2: rule__Goal__Group_7__5__Impl
            {
            pushFollow(FOLLOW_rule__Goal__Group_7__5__Impl_in_rule__Goal__Group_7__56999);
            rule__Goal__Group_7__5__Impl();

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
    // $ANTLR end "rule__Goal__Group_7__5"


    // $ANTLR start "rule__Goal__Group_7__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3538:1: rule__Goal__Group_7__5__Impl : ( ')' ) ;
    public final void rule__Goal__Group_7__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3542:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3543:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3543:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3544:1: ')'
            {
             before(grammarAccess.getGoalAccess().getRightParenthesisKeyword_7_5()); 
            match(input,13,FOLLOW_13_in_rule__Goal__Group_7__5__Impl7027); 
             after(grammarAccess.getGoalAccess().getRightParenthesisKeyword_7_5()); 

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
    // $ANTLR end "rule__Goal__Group_7__5__Impl"


    // $ANTLR start "rule__Goal__Group_7_4__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3569:1: rule__Goal__Group_7_4__0 : rule__Goal__Group_7_4__0__Impl rule__Goal__Group_7_4__1 ;
    public final void rule__Goal__Group_7_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3573:1: ( rule__Goal__Group_7_4__0__Impl rule__Goal__Group_7_4__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3574:2: rule__Goal__Group_7_4__0__Impl rule__Goal__Group_7_4__1
            {
            pushFollow(FOLLOW_rule__Goal__Group_7_4__0__Impl_in_rule__Goal__Group_7_4__07070);
            rule__Goal__Group_7_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_7_4__1_in_rule__Goal__Group_7_4__07073);
            rule__Goal__Group_7_4__1();

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
    // $ANTLR end "rule__Goal__Group_7_4__0"


    // $ANTLR start "rule__Goal__Group_7_4__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3581:1: rule__Goal__Group_7_4__0__Impl : ( ',' ) ;
    public final void rule__Goal__Group_7_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3585:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3586:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3586:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3587:1: ','
            {
             before(grammarAccess.getGoalAccess().getCommaKeyword_7_4_0()); 
            match(input,15,FOLLOW_15_in_rule__Goal__Group_7_4__0__Impl7101); 
             after(grammarAccess.getGoalAccess().getCommaKeyword_7_4_0()); 

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
    // $ANTLR end "rule__Goal__Group_7_4__0__Impl"


    // $ANTLR start "rule__Goal__Group_7_4__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3600:1: rule__Goal__Group_7_4__1 : rule__Goal__Group_7_4__1__Impl ;
    public final void rule__Goal__Group_7_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3604:1: ( rule__Goal__Group_7_4__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3605:2: rule__Goal__Group_7_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Goal__Group_7_4__1__Impl_in_rule__Goal__Group_7_4__17132);
            rule__Goal__Group_7_4__1__Impl();

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
    // $ANTLR end "rule__Goal__Group_7_4__1"


    // $ANTLR start "rule__Goal__Group_7_4__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3611:1: rule__Goal__Group_7_4__1__Impl : ( ( rule__Goal__InvariantConditionsAssignment_7_4_1 ) ) ;
    public final void rule__Goal__Group_7_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3615:1: ( ( ( rule__Goal__InvariantConditionsAssignment_7_4_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3616:1: ( ( rule__Goal__InvariantConditionsAssignment_7_4_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3616:1: ( ( rule__Goal__InvariantConditionsAssignment_7_4_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3617:1: ( rule__Goal__InvariantConditionsAssignment_7_4_1 )
            {
             before(grammarAccess.getGoalAccess().getInvariantConditionsAssignment_7_4_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3618:1: ( rule__Goal__InvariantConditionsAssignment_7_4_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3618:2: rule__Goal__InvariantConditionsAssignment_7_4_1
            {
            pushFollow(FOLLOW_rule__Goal__InvariantConditionsAssignment_7_4_1_in_rule__Goal__Group_7_4__1__Impl7159);
            rule__Goal__InvariantConditionsAssignment_7_4_1();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getInvariantConditionsAssignment_7_4_1()); 

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
    // $ANTLR end "rule__Goal__Group_7_4__1__Impl"


    // $ANTLR start "rule__Goal__Group_8__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3632:1: rule__Goal__Group_8__0 : rule__Goal__Group_8__0__Impl rule__Goal__Group_8__1 ;
    public final void rule__Goal__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3636:1: ( rule__Goal__Group_8__0__Impl rule__Goal__Group_8__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3637:2: rule__Goal__Group_8__0__Impl rule__Goal__Group_8__1
            {
            pushFollow(FOLLOW_rule__Goal__Group_8__0__Impl_in_rule__Goal__Group_8__07193);
            rule__Goal__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_8__1_in_rule__Goal__Group_8__07196);
            rule__Goal__Group_8__1();

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
    // $ANTLR end "rule__Goal__Group_8__0"


    // $ANTLR start "rule__Goal__Group_8__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3644:1: rule__Goal__Group_8__0__Impl : ( ',' ) ;
    public final void rule__Goal__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3648:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3649:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3649:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3650:1: ','
            {
             before(grammarAccess.getGoalAccess().getCommaKeyword_8_0()); 
            match(input,15,FOLLOW_15_in_rule__Goal__Group_8__0__Impl7224); 
             after(grammarAccess.getGoalAccess().getCommaKeyword_8_0()); 

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
    // $ANTLR end "rule__Goal__Group_8__0__Impl"


    // $ANTLR start "rule__Goal__Group_8__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3663:1: rule__Goal__Group_8__1 : rule__Goal__Group_8__1__Impl rule__Goal__Group_8__2 ;
    public final void rule__Goal__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3667:1: ( rule__Goal__Group_8__1__Impl rule__Goal__Group_8__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3668:2: rule__Goal__Group_8__1__Impl rule__Goal__Group_8__2
            {
            pushFollow(FOLLOW_rule__Goal__Group_8__1__Impl_in_rule__Goal__Group_8__17255);
            rule__Goal__Group_8__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_8__2_in_rule__Goal__Group_8__17258);
            rule__Goal__Group_8__2();

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
    // $ANTLR end "rule__Goal__Group_8__1"


    // $ANTLR start "rule__Goal__Group_8__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3675:1: rule__Goal__Group_8__1__Impl : ( 'SUB' ) ;
    public final void rule__Goal__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3679:1: ( ( 'SUB' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3680:1: ( 'SUB' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3680:1: ( 'SUB' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3681:1: 'SUB'
            {
             before(grammarAccess.getGoalAccess().getSUBKeyword_8_1()); 
            match(input,30,FOLLOW_30_in_rule__Goal__Group_8__1__Impl7286); 
             after(grammarAccess.getGoalAccess().getSUBKeyword_8_1()); 

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
    // $ANTLR end "rule__Goal__Group_8__1__Impl"


    // $ANTLR start "rule__Goal__Group_8__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3694:1: rule__Goal__Group_8__2 : rule__Goal__Group_8__2__Impl rule__Goal__Group_8__3 ;
    public final void rule__Goal__Group_8__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3698:1: ( rule__Goal__Group_8__2__Impl rule__Goal__Group_8__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3699:2: rule__Goal__Group_8__2__Impl rule__Goal__Group_8__3
            {
            pushFollow(FOLLOW_rule__Goal__Group_8__2__Impl_in_rule__Goal__Group_8__27317);
            rule__Goal__Group_8__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_8__3_in_rule__Goal__Group_8__27320);
            rule__Goal__Group_8__3();

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
    // $ANTLR end "rule__Goal__Group_8__2"


    // $ANTLR start "rule__Goal__Group_8__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3706:1: rule__Goal__Group_8__2__Impl : ( '(' ) ;
    public final void rule__Goal__Group_8__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3710:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3711:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3711:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3712:1: '('
            {
             before(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_8_2()); 
            match(input,12,FOLLOW_12_in_rule__Goal__Group_8__2__Impl7348); 
             after(grammarAccess.getGoalAccess().getLeftParenthesisKeyword_8_2()); 

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
    // $ANTLR end "rule__Goal__Group_8__2__Impl"


    // $ANTLR start "rule__Goal__Group_8__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3725:1: rule__Goal__Group_8__3 : rule__Goal__Group_8__3__Impl rule__Goal__Group_8__4 ;
    public final void rule__Goal__Group_8__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3729:1: ( rule__Goal__Group_8__3__Impl rule__Goal__Group_8__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3730:2: rule__Goal__Group_8__3__Impl rule__Goal__Group_8__4
            {
            pushFollow(FOLLOW_rule__Goal__Group_8__3__Impl_in_rule__Goal__Group_8__37379);
            rule__Goal__Group_8__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_8__4_in_rule__Goal__Group_8__37382);
            rule__Goal__Group_8__4();

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
    // $ANTLR end "rule__Goal__Group_8__3"


    // $ANTLR start "rule__Goal__Group_8__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3737:1: rule__Goal__Group_8__3__Impl : ( ( rule__Goal__ChildrenGoalsAssignment_8_3 ) ) ;
    public final void rule__Goal__Group_8__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3741:1: ( ( ( rule__Goal__ChildrenGoalsAssignment_8_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3742:1: ( ( rule__Goal__ChildrenGoalsAssignment_8_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3742:1: ( ( rule__Goal__ChildrenGoalsAssignment_8_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3743:1: ( rule__Goal__ChildrenGoalsAssignment_8_3 )
            {
             before(grammarAccess.getGoalAccess().getChildrenGoalsAssignment_8_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3744:1: ( rule__Goal__ChildrenGoalsAssignment_8_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3744:2: rule__Goal__ChildrenGoalsAssignment_8_3
            {
            pushFollow(FOLLOW_rule__Goal__ChildrenGoalsAssignment_8_3_in_rule__Goal__Group_8__3__Impl7409);
            rule__Goal__ChildrenGoalsAssignment_8_3();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getChildrenGoalsAssignment_8_3()); 

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
    // $ANTLR end "rule__Goal__Group_8__3__Impl"


    // $ANTLR start "rule__Goal__Group_8__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3754:1: rule__Goal__Group_8__4 : rule__Goal__Group_8__4__Impl rule__Goal__Group_8__5 ;
    public final void rule__Goal__Group_8__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3758:1: ( rule__Goal__Group_8__4__Impl rule__Goal__Group_8__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3759:2: rule__Goal__Group_8__4__Impl rule__Goal__Group_8__5
            {
            pushFollow(FOLLOW_rule__Goal__Group_8__4__Impl_in_rule__Goal__Group_8__47439);
            rule__Goal__Group_8__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_8__5_in_rule__Goal__Group_8__47442);
            rule__Goal__Group_8__5();

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
    // $ANTLR end "rule__Goal__Group_8__4"


    // $ANTLR start "rule__Goal__Group_8__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3766:1: rule__Goal__Group_8__4__Impl : ( ( rule__Goal__Group_8_4__0 )* ) ;
    public final void rule__Goal__Group_8__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3770:1: ( ( ( rule__Goal__Group_8_4__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3771:1: ( ( rule__Goal__Group_8_4__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3771:1: ( ( rule__Goal__Group_8_4__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3772:1: ( rule__Goal__Group_8_4__0 )*
            {
             before(grammarAccess.getGoalAccess().getGroup_8_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3773:1: ( rule__Goal__Group_8_4__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==15) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3773:2: rule__Goal__Group_8_4__0
            	    {
            	    pushFollow(FOLLOW_rule__Goal__Group_8_4__0_in_rule__Goal__Group_8__4__Impl7469);
            	    rule__Goal__Group_8_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getGoalAccess().getGroup_8_4()); 

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
    // $ANTLR end "rule__Goal__Group_8__4__Impl"


    // $ANTLR start "rule__Goal__Group_8__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3783:1: rule__Goal__Group_8__5 : rule__Goal__Group_8__5__Impl ;
    public final void rule__Goal__Group_8__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3787:1: ( rule__Goal__Group_8__5__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3788:2: rule__Goal__Group_8__5__Impl
            {
            pushFollow(FOLLOW_rule__Goal__Group_8__5__Impl_in_rule__Goal__Group_8__57500);
            rule__Goal__Group_8__5__Impl();

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
    // $ANTLR end "rule__Goal__Group_8__5"


    // $ANTLR start "rule__Goal__Group_8__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3794:1: rule__Goal__Group_8__5__Impl : ( ')' ) ;
    public final void rule__Goal__Group_8__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3798:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3799:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3799:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3800:1: ')'
            {
             before(grammarAccess.getGoalAccess().getRightParenthesisKeyword_8_5()); 
            match(input,13,FOLLOW_13_in_rule__Goal__Group_8__5__Impl7528); 
             after(grammarAccess.getGoalAccess().getRightParenthesisKeyword_8_5()); 

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
    // $ANTLR end "rule__Goal__Group_8__5__Impl"


    // $ANTLR start "rule__Goal__Group_8_4__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3825:1: rule__Goal__Group_8_4__0 : rule__Goal__Group_8_4__0__Impl rule__Goal__Group_8_4__1 ;
    public final void rule__Goal__Group_8_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3829:1: ( rule__Goal__Group_8_4__0__Impl rule__Goal__Group_8_4__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3830:2: rule__Goal__Group_8_4__0__Impl rule__Goal__Group_8_4__1
            {
            pushFollow(FOLLOW_rule__Goal__Group_8_4__0__Impl_in_rule__Goal__Group_8_4__07571);
            rule__Goal__Group_8_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Goal__Group_8_4__1_in_rule__Goal__Group_8_4__07574);
            rule__Goal__Group_8_4__1();

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
    // $ANTLR end "rule__Goal__Group_8_4__0"


    // $ANTLR start "rule__Goal__Group_8_4__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3837:1: rule__Goal__Group_8_4__0__Impl : ( ',' ) ;
    public final void rule__Goal__Group_8_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3841:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3842:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3842:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3843:1: ','
            {
             before(grammarAccess.getGoalAccess().getCommaKeyword_8_4_0()); 
            match(input,15,FOLLOW_15_in_rule__Goal__Group_8_4__0__Impl7602); 
             after(grammarAccess.getGoalAccess().getCommaKeyword_8_4_0()); 

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
    // $ANTLR end "rule__Goal__Group_8_4__0__Impl"


    // $ANTLR start "rule__Goal__Group_8_4__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3856:1: rule__Goal__Group_8_4__1 : rule__Goal__Group_8_4__1__Impl ;
    public final void rule__Goal__Group_8_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3860:1: ( rule__Goal__Group_8_4__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3861:2: rule__Goal__Group_8_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Goal__Group_8_4__1__Impl_in_rule__Goal__Group_8_4__17633);
            rule__Goal__Group_8_4__1__Impl();

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
    // $ANTLR end "rule__Goal__Group_8_4__1"


    // $ANTLR start "rule__Goal__Group_8_4__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3867:1: rule__Goal__Group_8_4__1__Impl : ( ( rule__Goal__ChildrenGoalsAssignment_8_4_1 ) ) ;
    public final void rule__Goal__Group_8_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3871:1: ( ( ( rule__Goal__ChildrenGoalsAssignment_8_4_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3872:1: ( ( rule__Goal__ChildrenGoalsAssignment_8_4_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3872:1: ( ( rule__Goal__ChildrenGoalsAssignment_8_4_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3873:1: ( rule__Goal__ChildrenGoalsAssignment_8_4_1 )
            {
             before(grammarAccess.getGoalAccess().getChildrenGoalsAssignment_8_4_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3874:1: ( rule__Goal__ChildrenGoalsAssignment_8_4_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3874:2: rule__Goal__ChildrenGoalsAssignment_8_4_1
            {
            pushFollow(FOLLOW_rule__Goal__ChildrenGoalsAssignment_8_4_1_in_rule__Goal__Group_8_4__1__Impl7660);
            rule__Goal__ChildrenGoalsAssignment_8_4_1();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getChildrenGoalsAssignment_8_4_1()); 

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
    // $ANTLR end "rule__Goal__Group_8_4__1__Impl"


    // $ANTLR start "rule__GoalModel__GoalsAssignment"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3889:1: rule__GoalModel__GoalsAssignment : ( ruleGoal ) ;
    public final void rule__GoalModel__GoalsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3893:1: ( ( ruleGoal ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3894:1: ( ruleGoal )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3894:1: ( ruleGoal )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3895:1: ruleGoal
            {
             before(grammarAccess.getGoalModelAccess().getGoalsGoalParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleGoal_in_rule__GoalModel__GoalsAssignment7699);
            ruleGoal();

            state._fsp--;

             after(grammarAccess.getGoalModelAccess().getGoalsGoalParserRuleCall_0()); 

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
    // $ANTLR end "rule__GoalModel__GoalsAssignment"


    // $ANTLR start "rule__EntityAchieveCondition__NameAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3904:1: rule__EntityAchieveCondition__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__EntityAchieveCondition__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3908:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3909:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3909:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3910:1: RULE_ID
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__EntityAchieveCondition__NameAssignment_27730); 
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


    // $ANTLR start "rule__EntityInvariantCondition__NameAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3920:1: rule__EntityInvariantCondition__NameAssignment_2 : ( ruleAttribute ) ;
    public final void rule__EntityInvariantCondition__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3924:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3925:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3925:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3926:1: ruleAttribute
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__EntityInvariantCondition__NameAssignment_27762);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3935:1: rule__EntityInvariantCondition__CardinalityAssignment_4 : ( ruleCardinality ) ;
    public final void rule__EntityInvariantCondition__CardinalityAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3939:1: ( ( ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3940:1: ( ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3940:1: ( ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3941:1: ruleCardinality
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleCardinality_in_rule__EntityInvariantCondition__CardinalityAssignment_47793);
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


    // $ANTLR start "rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3952:1: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 : ( ruleAttribute ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3956:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3957:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3957:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3958:1: ruleAttribute
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_27826);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3967:1: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 : ( ruleAttribute ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3971:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3972:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3972:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3973:1: ruleAttribute
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_17857);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3982:1: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 : ( ruleAttribute ) ;
    public final void rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3986:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3987:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3987:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3988:1: ruleAttribute
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_47888);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:3997:1: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 : ( ruleAttribute ) ;
    public final void rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4001:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4002:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4002:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4003:1: ruleAttribute
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_17919);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4012:1: rule__AttributeInvariantCondition__ExpressionAssignment_2 : ( ruleExpression ) ;
    public final void rule__AttributeInvariantCondition__ExpressionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4016:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4017:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4017:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4018:1: ruleExpression
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__AttributeInvariantCondition__ExpressionAssignment_27950);
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


    // $ANTLR start "rule__Nothing__NameAssignment"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4030:1: rule__Nothing__NameAssignment : ( ( 'nothing' ) ) ;
    public final void rule__Nothing__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4034:1: ( ( ( 'nothing' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4035:1: ( ( 'nothing' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4035:1: ( ( 'nothing' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4036:1: ( 'nothing' )
            {
             before(grammarAccess.getNothingAccess().getNameNothingKeyword_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4037:1: ( 'nothing' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4038:1: 'nothing'
            {
             before(grammarAccess.getNothingAccess().getNameNothingKeyword_0()); 
            match(input,31,FOLLOW_31_in_rule__Nothing__NameAssignment7989); 
             after(grammarAccess.getNothingAccess().getNameNothingKeyword_0()); 

            }

             after(grammarAccess.getNothingAccess().getNameNothingKeyword_0()); 

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
    // $ANTLR end "rule__Nothing__NameAssignment"


    // $ANTLR start "rule__Or__RightAssignment_1_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4053:1: rule__Or__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Or__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4057:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4058:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4058:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4059:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_28028);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4068:1: rule__And__RightAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4072:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4073:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4073:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4074:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_28059);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4083:1: rule__Primary__ExpressionAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__Primary__ExpressionAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4087:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4088:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4088:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4089:1: rulePrimary
            {
             before(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_28090);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4098:1: rule__Atomic__NameAssignment_0_3 : ( ruleAttribute ) ;
    public final void rule__Atomic__NameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4102:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4103:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4103:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4104:1: ruleAttribute
            {
             before(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_0_38121);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4113:1: rule__Atomic__NameAssignment_1_1 : ( ruleAttribute ) ;
    public final void rule__Atomic__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4117:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4118:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4118:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4119:1: ruleAttribute
            {
             before(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_1_18152);
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


    // $ANTLR start "rule__Goal__NameAssignment_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4128:1: rule__Goal__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Goal__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4132:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4133:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4133:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4134:1: RULE_ID
            {
             before(grammarAccess.getGoalAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Goal__NameAssignment_08183); 
             after(grammarAccess.getGoalAccess().getNameIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__Goal__NameAssignment_0"


    // $ANTLR start "rule__Goal__SucessConditionAssignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4143:1: rule__Goal__SucessConditionAssignment_4 : ( ( rule__Goal__SucessConditionAlternatives_4_0 ) ) ;
    public final void rule__Goal__SucessConditionAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4147:1: ( ( ( rule__Goal__SucessConditionAlternatives_4_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4148:1: ( ( rule__Goal__SucessConditionAlternatives_4_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4148:1: ( ( rule__Goal__SucessConditionAlternatives_4_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4149:1: ( rule__Goal__SucessConditionAlternatives_4_0 )
            {
             before(grammarAccess.getGoalAccess().getSucessConditionAlternatives_4_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4150:1: ( rule__Goal__SucessConditionAlternatives_4_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4150:2: rule__Goal__SucessConditionAlternatives_4_0
            {
            pushFollow(FOLLOW_rule__Goal__SucessConditionAlternatives_4_0_in_rule__Goal__SucessConditionAssignment_48214);
            rule__Goal__SucessConditionAlternatives_4_0();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getSucessConditionAlternatives_4_0()); 

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
    // $ANTLR end "rule__Goal__SucessConditionAssignment_4"


    // $ANTLR start "rule__Goal__ActivationConditionAssignment_6_3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4159:1: rule__Goal__ActivationConditionAssignment_6_3 : ( ( rule__Goal__ActivationConditionAlternatives_6_3_0 ) ) ;
    public final void rule__Goal__ActivationConditionAssignment_6_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4163:1: ( ( ( rule__Goal__ActivationConditionAlternatives_6_3_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4164:1: ( ( rule__Goal__ActivationConditionAlternatives_6_3_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4164:1: ( ( rule__Goal__ActivationConditionAlternatives_6_3_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4165:1: ( rule__Goal__ActivationConditionAlternatives_6_3_0 )
            {
             before(grammarAccess.getGoalAccess().getActivationConditionAlternatives_6_3_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4166:1: ( rule__Goal__ActivationConditionAlternatives_6_3_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4166:2: rule__Goal__ActivationConditionAlternatives_6_3_0
            {
            pushFollow(FOLLOW_rule__Goal__ActivationConditionAlternatives_6_3_0_in_rule__Goal__ActivationConditionAssignment_6_38247);
            rule__Goal__ActivationConditionAlternatives_6_3_0();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getActivationConditionAlternatives_6_3_0()); 

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
    // $ANTLR end "rule__Goal__ActivationConditionAssignment_6_3"


    // $ANTLR start "rule__Goal__InvariantConditionsAssignment_7_3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4175:1: rule__Goal__InvariantConditionsAssignment_7_3 : ( ( rule__Goal__InvariantConditionsAlternatives_7_3_0 ) ) ;
    public final void rule__Goal__InvariantConditionsAssignment_7_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4179:1: ( ( ( rule__Goal__InvariantConditionsAlternatives_7_3_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4180:1: ( ( rule__Goal__InvariantConditionsAlternatives_7_3_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4180:1: ( ( rule__Goal__InvariantConditionsAlternatives_7_3_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4181:1: ( rule__Goal__InvariantConditionsAlternatives_7_3_0 )
            {
             before(grammarAccess.getGoalAccess().getInvariantConditionsAlternatives_7_3_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4182:1: ( rule__Goal__InvariantConditionsAlternatives_7_3_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4182:2: rule__Goal__InvariantConditionsAlternatives_7_3_0
            {
            pushFollow(FOLLOW_rule__Goal__InvariantConditionsAlternatives_7_3_0_in_rule__Goal__InvariantConditionsAssignment_7_38280);
            rule__Goal__InvariantConditionsAlternatives_7_3_0();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getInvariantConditionsAlternatives_7_3_0()); 

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
    // $ANTLR end "rule__Goal__InvariantConditionsAssignment_7_3"


    // $ANTLR start "rule__Goal__InvariantConditionsAssignment_7_4_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4191:1: rule__Goal__InvariantConditionsAssignment_7_4_1 : ( ( rule__Goal__InvariantConditionsAlternatives_7_4_1_0 ) ) ;
    public final void rule__Goal__InvariantConditionsAssignment_7_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4195:1: ( ( ( rule__Goal__InvariantConditionsAlternatives_7_4_1_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4196:1: ( ( rule__Goal__InvariantConditionsAlternatives_7_4_1_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4196:1: ( ( rule__Goal__InvariantConditionsAlternatives_7_4_1_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4197:1: ( rule__Goal__InvariantConditionsAlternatives_7_4_1_0 )
            {
             before(grammarAccess.getGoalAccess().getInvariantConditionsAlternatives_7_4_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4198:1: ( rule__Goal__InvariantConditionsAlternatives_7_4_1_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4198:2: rule__Goal__InvariantConditionsAlternatives_7_4_1_0
            {
            pushFollow(FOLLOW_rule__Goal__InvariantConditionsAlternatives_7_4_1_0_in_rule__Goal__InvariantConditionsAssignment_7_4_18313);
            rule__Goal__InvariantConditionsAlternatives_7_4_1_0();

            state._fsp--;


            }

             after(grammarAccess.getGoalAccess().getInvariantConditionsAlternatives_7_4_1_0()); 

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
    // $ANTLR end "rule__Goal__InvariantConditionsAssignment_7_4_1"


    // $ANTLR start "rule__Goal__ChildrenGoalsAssignment_8_3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4207:1: rule__Goal__ChildrenGoalsAssignment_8_3 : ( ( RULE_ID ) ) ;
    public final void rule__Goal__ChildrenGoalsAssignment_8_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4211:1: ( ( ( RULE_ID ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4212:1: ( ( RULE_ID ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4212:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4213:1: ( RULE_ID )
            {
             before(grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_3_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4214:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4215:1: RULE_ID
            {
             before(grammarAccess.getGoalAccess().getChildrenGoalsGoalIDTerminalRuleCall_8_3_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Goal__ChildrenGoalsAssignment_8_38350); 
             after(grammarAccess.getGoalAccess().getChildrenGoalsGoalIDTerminalRuleCall_8_3_0_1()); 

            }

             after(grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_3_0()); 

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
    // $ANTLR end "rule__Goal__ChildrenGoalsAssignment_8_3"


    // $ANTLR start "rule__Goal__ChildrenGoalsAssignment_8_4_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4226:1: rule__Goal__ChildrenGoalsAssignment_8_4_1 : ( ( RULE_ID ) ) ;
    public final void rule__Goal__ChildrenGoalsAssignment_8_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4230:1: ( ( ( RULE_ID ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4231:1: ( ( RULE_ID ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4231:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4232:1: ( RULE_ID )
            {
             before(grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_4_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4233:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal.ui/src-gen/org/blended/goal/ui/contentassist/antlr/internal/InternalGoal.g:4234:1: RULE_ID
            {
             before(grammarAccess.getGoalAccess().getChildrenGoalsGoalIDTerminalRuleCall_8_4_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Goal__ChildrenGoalsAssignment_8_4_18389); 
             after(grammarAccess.getGoalAccess().getChildrenGoalsGoalIDTerminalRuleCall_8_4_1_0_1()); 

            }

             after(grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_4_1_0()); 

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
    // $ANTLR end "rule__Goal__ChildrenGoalsAssignment_8_4_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleGoalModel_in_entryRuleGoalModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGoalModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GoalModel__GoalsAssignment_in_ruleGoalModel96 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__GoalModel__GoalsAssignment_in_ruleGoalModel108 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition138 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveCondition145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__0_in_ruleEntityAchieveCondition171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityInvariantCondition207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__0_in_ruleEntityInvariantCondition233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition262 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeAchieveCondition269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeAchieveCondition__Alternatives_in_ruleAttributeAchieveCondition295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition322 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0_in_ruleNotMandatoryAttributeAchieveCondition355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition382 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0_in_ruleMandatoryAttributeAchieveCondition415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition442 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__0_in_ruleAttributeInvariantCondition475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality504 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute564 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0_in_ruleAttribute597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNothing_in_entryRuleNothing624 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNothing631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Nothing__NameAssignment_in_ruleNothing657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression684 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr743 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0_in_ruleOr776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd803 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0_in_ruleAnd836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary863 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Alternatives_in_rulePrimary896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic923 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGoal_in_entryRuleGoal983 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGoal990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__0_in_ruleGoal1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__AttributeAchieveCondition__Alternatives1069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Goal__SucessConditionAlternatives_4_01306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_rule__Goal__SucessConditionAlternatives_4_01323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNothing_in_rule__Goal__SucessConditionAlternatives_4_01340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Goal__ActivationConditionAlternatives_6_3_01372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_rule__Goal__ActivationConditionAlternatives_6_3_01389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_3_01421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_3_01438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_4_1_01470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_rule__Goal__InvariantConditionsAlternatives_7_4_1_01487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__0__Impl_in_rule__EntityAchieveCondition__Group__01517 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__1_in_rule__EntityAchieveCondition__Group__01520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__EntityAchieveCondition__Group__0__Impl1548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__1__Impl_in_rule__EntityAchieveCondition__Group__11579 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__2_in_rule__EntityAchieveCondition__Group__11582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityAchieveCondition__Group__1__Impl1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__2__Impl_in_rule__EntityAchieveCondition__Group__21641 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__3_in_rule__EntityAchieveCondition__Group__21644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__NameAssignment_2_in_rule__EntityAchieveCondition__Group__2__Impl1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__3__Impl_in_rule__EntityAchieveCondition__Group__31701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityAchieveCondition__Group__3__Impl1729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__0__Impl_in_rule__EntityInvariantCondition__Group__01769 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__1_in_rule__EntityInvariantCondition__Group__01772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__EntityInvariantCondition__Group__0__Impl1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__1__Impl_in_rule__EntityInvariantCondition__Group__11831 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__2_in_rule__EntityInvariantCondition__Group__11834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityInvariantCondition__Group__1__Impl1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__2__Impl_in_rule__EntityInvariantCondition__Group__21893 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__3_in_rule__EntityInvariantCondition__Group__21896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__NameAssignment_2_in_rule__EntityInvariantCondition__Group__2__Impl1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__3__Impl_in_rule__EntityInvariantCondition__Group__31953 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__4_in_rule__EntityInvariantCondition__Group__31956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__EntityInvariantCondition__Group__3__Impl1984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__4__Impl_in_rule__EntityInvariantCondition__Group__42015 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__5_in_rule__EntityInvariantCondition__Group__42018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__CardinalityAssignment_4_in_rule__EntityInvariantCondition__Group__4__Impl2045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__5__Impl_in_rule__EntityInvariantCondition__Group__52075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityInvariantCondition__Group__5__Impl2103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__02147 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1_in_rule__NotMandatoryAttributeAchieveCondition__Group__02150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__12209 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2_in_rule__NotMandatoryAttributeAchieveCondition__Group__12212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__22271 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3_in_rule__NotMandatoryAttributeAchieveCondition__Group__22274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2_in_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl2301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__32331 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4_in_rule__NotMandatoryAttributeAchieveCondition__Group__32334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0_in_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl2361 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__42392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02461 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl2492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__12523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl2550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__02584 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1_in_rule__MandatoryAttributeAchieveCondition__Group__02587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__MandatoryAttributeAchieveCondition__Group__0__Impl2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__12646 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2_in_rule__MandatoryAttributeAchieveCondition__Group__12649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__1__Impl2677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__22708 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3_in_rule__MandatoryAttributeAchieveCondition__Group__22711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__MandatoryAttributeAchieveCondition__Group__2__Impl2739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__32770 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4_in_rule__MandatoryAttributeAchieveCondition__Group__32773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__3__Impl2801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__42832 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5_in_rule__MandatoryAttributeAchieveCondition__Group__42835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4_in_rule__MandatoryAttributeAchieveCondition__Group__4__Impl2862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__52892 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6_in_rule__MandatoryAttributeAchieveCondition__Group__52895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0_in_rule__MandatoryAttributeAchieveCondition__Group__5__Impl2922 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__62953 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7_in_rule__MandatoryAttributeAchieveCondition__Group__62956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__6__Impl2984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__73015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__7__Impl3043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__03090 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1_in_rule__MandatoryAttributeAchieveCondition__Group_5__03093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl3121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__13152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1_in_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__0__Impl_in_rule__AttributeInvariantCondition__Group__03213 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__1_in_rule__AttributeInvariantCondition__Group__03216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__AttributeInvariantCondition__Group__0__Impl3244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__1__Impl_in_rule__AttributeInvariantCondition__Group__13275 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__2_in_rule__AttributeInvariantCondition__Group__13278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeInvariantCondition__Group__1__Impl3306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__2__Impl_in_rule__AttributeInvariantCondition__Group__23337 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__3_in_rule__AttributeInvariantCondition__Group__23340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__ExpressionAssignment_2_in_rule__AttributeInvariantCondition__Group__2__Impl3367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__3__Impl_in_rule__AttributeInvariantCondition__Group__33397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeInvariantCondition__Group__3__Impl3425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__03466 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__03469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl3496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__13525 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__13528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Cardinality__Group_1__1__Impl3556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__23587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl3614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__03649 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__03652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl3679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__13708 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__13711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Cardinality__Group_2__1__Impl3739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__23770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Cardinality__Group_2__2__Impl3798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__03835 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__03838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl3865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__13894 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__13897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Cardinality__Group_3__1__Impl3925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__23956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Cardinality__Group_3__2__Impl3984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__04021 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__04024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group__0__Impl4051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__14080 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__14083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Attribute__Group__1__Impl4111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__24142 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__24145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group__2__Impl4172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__34201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__0_in_rule__Attribute__Group__3__Impl4228 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__0__Impl_in_rule__Attribute__Group_3__04267 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__1_in_rule__Attribute__Group_3__04270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Attribute__Group_3__0__Impl4298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__1__Impl_in_rule__Attribute__Group_3__14329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group_3__1__Impl4356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__04389 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Or__Group__1_in_rule__Or__Group__04392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl4419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__14448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl4475 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__04510 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__04513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__14571 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__14574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Or__Group_1__1__Impl4602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__24633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl4660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__04696 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__And__Group__1_in_rule__And__Group__04699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__Group__0__Impl4726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__14755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl4782 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__04817 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__04820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__14878 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__14881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__And__Group_1__1__Impl4909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__24940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl4967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__05003 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__05006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Primary__Group_0__0__Impl5034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__15065 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__15068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl5095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__25124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Primary__Group_0__2__Impl5152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__05189 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__05192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__15250 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__15253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Primary__Group_1__1__Impl5281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__25312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl5339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__05375 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__05378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__15436 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__15439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Atomic__Group_0__1__Impl5467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__25498 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__25501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Atomic__Group_0__2__Impl5529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__35560 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__35563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl5590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__45620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Atomic__Group_0__4__Impl5648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__05689 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__05692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__15750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl5777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__0__Impl_in_rule__Goal__Group__05811 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__Goal__Group__1_in_rule__Goal__Group__05814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__NameAssignment_0_in_rule__Goal__Group__0__Impl5841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__1__Impl_in_rule__Goal__Group__15871 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Goal__Group__2_in_rule__Goal__Group__15874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Goal__Group__1__Impl5902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__2__Impl_in_rule__Goal__Group__25933 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Goal__Group__3_in_rule__Goal__Group__25936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Goal__Group__2__Impl5964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__3__Impl_in_rule__Goal__Group__35995 = new BitSet(new long[]{0x0000000080030800L});
    public static final BitSet FOLLOW_rule__Goal__Group__4_in_rule__Goal__Group__35998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Goal__Group__3__Impl6026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__4__Impl_in_rule__Goal__Group__46057 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Goal__Group__5_in_rule__Goal__Group__46060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__SucessConditionAssignment_4_in_rule__Goal__Group__4__Impl6087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__5__Impl_in_rule__Goal__Group__56117 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Goal__Group__6_in_rule__Goal__Group__56120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Goal__Group__5__Impl6148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__6__Impl_in_rule__Goal__Group__66179 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Goal__Group__7_in_rule__Goal__Group__66182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__0_in_rule__Goal__Group__6__Impl6209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__7__Impl_in_rule__Goal__Group__76240 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Goal__Group__8_in_rule__Goal__Group__76243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__0_in_rule__Goal__Group__7__Impl6270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group__8__Impl_in_rule__Goal__Group__86301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__0_in_rule__Goal__Group__8__Impl6328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__0__Impl_in_rule__Goal__Group_6__06377 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__1_in_rule__Goal__Group_6__06380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Goal__Group_6__0__Impl6408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__1__Impl_in_rule__Goal__Group_6__16439 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__2_in_rule__Goal__Group_6__16442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Goal__Group_6__1__Impl6470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__2__Impl_in_rule__Goal__Group_6__26501 = new BitSet(new long[]{0x0000000000030800L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__3_in_rule__Goal__Group_6__26504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Goal__Group_6__2__Impl6532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__3__Impl_in_rule__Goal__Group_6__36563 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__4_in_rule__Goal__Group_6__36566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__ActivationConditionAssignment_6_3_in_rule__Goal__Group_6__3__Impl6593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_6__4__Impl_in_rule__Goal__Group_6__46623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Goal__Group_6__4__Impl6651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__0__Impl_in_rule__Goal__Group_7__06692 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__1_in_rule__Goal__Group_7__06695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Goal__Group_7__0__Impl6723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__1__Impl_in_rule__Goal__Group_7__16754 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__2_in_rule__Goal__Group_7__16757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Goal__Group_7__1__Impl6785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__2__Impl_in_rule__Goal__Group_7__26816 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__3_in_rule__Goal__Group_7__26819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Goal__Group_7__2__Impl6847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__3__Impl_in_rule__Goal__Group_7__36878 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__4_in_rule__Goal__Group_7__36881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__InvariantConditionsAssignment_7_3_in_rule__Goal__Group_7__3__Impl6908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__4__Impl_in_rule__Goal__Group_7__46938 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__5_in_rule__Goal__Group_7__46941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7_4__0_in_rule__Goal__Group_7__4__Impl6968 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7__5__Impl_in_rule__Goal__Group_7__56999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Goal__Group_7__5__Impl7027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7_4__0__Impl_in_rule__Goal__Group_7_4__07070 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_rule__Goal__Group_7_4__1_in_rule__Goal__Group_7_4__07073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Goal__Group_7_4__0__Impl7101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_7_4__1__Impl_in_rule__Goal__Group_7_4__17132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__InvariantConditionsAssignment_7_4_1_in_rule__Goal__Group_7_4__1__Impl7159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__0__Impl_in_rule__Goal__Group_8__07193 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__1_in_rule__Goal__Group_8__07196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Goal__Group_8__0__Impl7224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__1__Impl_in_rule__Goal__Group_8__17255 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__2_in_rule__Goal__Group_8__17258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Goal__Group_8__1__Impl7286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__2__Impl_in_rule__Goal__Group_8__27317 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__3_in_rule__Goal__Group_8__27320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Goal__Group_8__2__Impl7348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__3__Impl_in_rule__Goal__Group_8__37379 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__4_in_rule__Goal__Group_8__37382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__ChildrenGoalsAssignment_8_3_in_rule__Goal__Group_8__3__Impl7409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__4__Impl_in_rule__Goal__Group_8__47439 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__5_in_rule__Goal__Group_8__47442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8_4__0_in_rule__Goal__Group_8__4__Impl7469 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8__5__Impl_in_rule__Goal__Group_8__57500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Goal__Group_8__5__Impl7528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8_4__0__Impl_in_rule__Goal__Group_8_4__07571 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Goal__Group_8_4__1_in_rule__Goal__Group_8_4__07574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Goal__Group_8_4__0__Impl7602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__Group_8_4__1__Impl_in_rule__Goal__Group_8_4__17633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__ChildrenGoalsAssignment_8_4_1_in_rule__Goal__Group_8_4__1__Impl7660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGoal_in_rule__GoalModel__GoalsAssignment7699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__EntityAchieveCondition__NameAssignment_27730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__EntityInvariantCondition__NameAssignment_27762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_rule__EntityInvariantCondition__CardinalityAssignment_47793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_27826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_17857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_47888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_17919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__AttributeInvariantCondition__ExpressionAssignment_27950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Nothing__NameAssignment7989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_28028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_28059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_28090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_0_38121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_1_18152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Goal__NameAssignment_08183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__SucessConditionAlternatives_4_0_in_rule__Goal__SucessConditionAssignment_48214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__ActivationConditionAlternatives_6_3_0_in_rule__Goal__ActivationConditionAssignment_6_38247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__InvariantConditionsAlternatives_7_3_0_in_rule__Goal__InvariantConditionsAssignment_7_38280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Goal__InvariantConditionsAlternatives_7_4_1_0_in_rule__Goal__InvariantConditionsAssignment_7_4_18313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Goal__ChildrenGoalsAssignment_8_38350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Goal__ChildrenGoalsAssignment_8_4_18389 = new BitSet(new long[]{0x0000000000000002L});

}