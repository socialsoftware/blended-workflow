package org.blended.activity.ui.contentassist.antlr.internal; 

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
import org.blended.activity.services.ActivityGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalActivityParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEFF'", "'('", "')'", "'MUL'", "','", "'DEF'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'OR'", "'AND'", "'NOT'", "':'", "'PRE'", "'POST'"
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
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalActivityParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalActivityParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalActivityParser.tokenNames; }
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g"; }


     
     	private ActivityGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(ActivityGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleActivityModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:60:1: entryRuleActivityModel : ruleActivityModel EOF ;
    public final void entryRuleActivityModel() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:61:1: ( ruleActivityModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:62:1: ruleActivityModel EOF
            {
             before(grammarAccess.getActivityModelRule()); 
            pushFollow(FOLLOW_ruleActivityModel_in_entryRuleActivityModel61);
            ruleActivityModel();

            state._fsp--;

             after(grammarAccess.getActivityModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleActivityModel68); 

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
    // $ANTLR end "entryRuleActivityModel"


    // $ANTLR start "ruleActivityModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:69:1: ruleActivityModel : ( ( ( rule__ActivityModel__ActivitiesAssignment ) ) ( ( rule__ActivityModel__ActivitiesAssignment )* ) ) ;
    public final void ruleActivityModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:73:2: ( ( ( ( rule__ActivityModel__ActivitiesAssignment ) ) ( ( rule__ActivityModel__ActivitiesAssignment )* ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:74:1: ( ( ( rule__ActivityModel__ActivitiesAssignment ) ) ( ( rule__ActivityModel__ActivitiesAssignment )* ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:74:1: ( ( ( rule__ActivityModel__ActivitiesAssignment ) ) ( ( rule__ActivityModel__ActivitiesAssignment )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:75:1: ( ( rule__ActivityModel__ActivitiesAssignment ) ) ( ( rule__ActivityModel__ActivitiesAssignment )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:75:1: ( ( rule__ActivityModel__ActivitiesAssignment ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:76:1: ( rule__ActivityModel__ActivitiesAssignment )
            {
             before(grammarAccess.getActivityModelAccess().getActivitiesAssignment()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:77:1: ( rule__ActivityModel__ActivitiesAssignment )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:77:2: rule__ActivityModel__ActivitiesAssignment
            {
            pushFollow(FOLLOW_rule__ActivityModel__ActivitiesAssignment_in_ruleActivityModel96);
            rule__ActivityModel__ActivitiesAssignment();

            state._fsp--;


            }

             after(grammarAccess.getActivityModelAccess().getActivitiesAssignment()); 

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:80:1: ( ( rule__ActivityModel__ActivitiesAssignment )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:81:1: ( rule__ActivityModel__ActivitiesAssignment )*
            {
             before(grammarAccess.getActivityModelAccess().getActivitiesAssignment()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:82:1: ( rule__ActivityModel__ActivitiesAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:82:2: rule__ActivityModel__ActivitiesAssignment
            	    {
            	    pushFollow(FOLLOW_rule__ActivityModel__ActivitiesAssignment_in_ruleActivityModel108);
            	    rule__ActivityModel__ActivitiesAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getActivityModelAccess().getActivitiesAssignment()); 

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
    // $ANTLR end "ruleActivityModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:95:1: entryRuleEntityAchieveCondition : ruleEntityAchieveCondition EOF ;
    public final void entryRuleEntityAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:96:1: ( ruleEntityAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:97:1: ruleEntityAchieveCondition EOF
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:104:1: ruleEntityAchieveCondition : ( ( rule__EntityAchieveCondition__Group__0 ) ) ;
    public final void ruleEntityAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:108:2: ( ( ( rule__EntityAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:109:1: ( ( rule__EntityAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:109:1: ( ( rule__EntityAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:110:1: ( rule__EntityAchieveCondition__Group__0 )
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:111:1: ( rule__EntityAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:111:2: rule__EntityAchieveCondition__Group__0
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:125:1: entryRuleEntityInvariantCondition : ruleEntityInvariantCondition EOF ;
    public final void entryRuleEntityInvariantCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:126:1: ( ruleEntityInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:127:1: ruleEntityInvariantCondition EOF
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:134:1: ruleEntityInvariantCondition : ( ( rule__EntityInvariantCondition__Group__0 ) ) ;
    public final void ruleEntityInvariantCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:138:2: ( ( ( rule__EntityInvariantCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:139:1: ( ( rule__EntityInvariantCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:139:1: ( ( rule__EntityInvariantCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:140:1: ( rule__EntityInvariantCondition__Group__0 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:141:1: ( rule__EntityInvariantCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:141:2: rule__EntityInvariantCondition__Group__0
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


    // $ANTLR start "entryRuleNotMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:157:1: entryRuleNotMandatoryAttributeAchieveCondition : ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final void entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:158:1: ( ruleNotMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:159:1: ruleNotMandatoryAttributeAchieveCondition EOF
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition264);
            ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition271); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:166:1: ruleNotMandatoryAttributeAchieveCondition : ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) ) ;
    public final void ruleNotMandatoryAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:170:2: ( ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:171:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:171:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:172:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:173:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:173:2: rule__NotMandatoryAttributeAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0_in_ruleNotMandatoryAttributeAchieveCondition297);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:185:1: entryRuleMandatoryAttributeAchieveCondition : ruleMandatoryAttributeAchieveCondition EOF ;
    public final void entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:186:1: ( ruleMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:187:1: ruleMandatoryAttributeAchieveCondition EOF
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition324);
            ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             after(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition331); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:194:1: ruleMandatoryAttributeAchieveCondition : ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) ) ;
    public final void ruleMandatoryAttributeAchieveCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:198:2: ( ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:199:1: ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:199:1: ( ( rule__MandatoryAttributeAchieveCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:200:1: ( rule__MandatoryAttributeAchieveCondition__Group__0 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:201:1: ( rule__MandatoryAttributeAchieveCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:201:2: rule__MandatoryAttributeAchieveCondition__Group__0
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0_in_ruleMandatoryAttributeAchieveCondition357);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:213:1: entryRuleAttributeInvariantCondition : ruleAttributeInvariantCondition EOF ;
    public final void entryRuleAttributeInvariantCondition() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:214:1: ( ruleAttributeInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:215:1: ruleAttributeInvariantCondition EOF
            {
             before(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition384);
            ruleAttributeInvariantCondition();

            state._fsp--;

             after(grammarAccess.getAttributeInvariantConditionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition391); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:222:1: ruleAttributeInvariantCondition : ( ( rule__AttributeInvariantCondition__Group__0 ) ) ;
    public final void ruleAttributeInvariantCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:226:2: ( ( ( rule__AttributeInvariantCondition__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:227:1: ( ( rule__AttributeInvariantCondition__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:227:1: ( ( rule__AttributeInvariantCondition__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:228:1: ( rule__AttributeInvariantCondition__Group__0 )
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:229:1: ( rule__AttributeInvariantCondition__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:229:2: rule__AttributeInvariantCondition__Group__0
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__0_in_ruleAttributeInvariantCondition417);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:243:1: entryRuleCardinality : ruleCardinality EOF ;
    public final void entryRuleCardinality() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:244:1: ( ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:245:1: ruleCardinality EOF
            {
             before(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality446);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getCardinalityRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality453); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:252:1: ruleCardinality : ( ( rule__Cardinality__Alternatives ) ) ;
    public final void ruleCardinality() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:256:2: ( ( ( rule__Cardinality__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:257:1: ( ( rule__Cardinality__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:257:1: ( ( rule__Cardinality__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:258:1: ( rule__Cardinality__Alternatives )
            {
             before(grammarAccess.getCardinalityAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:259:1: ( rule__Cardinality__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:259:2: rule__Cardinality__Alternatives
            {
            pushFollow(FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality479);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:271:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:272:1: ( ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:273:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute506);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute513); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:280:1: ruleAttribute : ( ( rule__Attribute__Group__0 ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:284:2: ( ( ( rule__Attribute__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:285:1: ( ( rule__Attribute__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:285:1: ( ( rule__Attribute__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:286:1: ( rule__Attribute__Group__0 )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:287:1: ( rule__Attribute__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:287:2: rule__Attribute__Group__0
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0_in_ruleAttribute539);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:301:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:302:1: ( ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:303:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression568);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression575); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:310:1: ruleExpression : ( ruleOr ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:314:2: ( ( ruleOr ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:315:1: ( ruleOr )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:315:1: ( ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:316:1: ruleOr
            {
             before(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
            pushFollow(FOLLOW_ruleOr_in_ruleExpression601);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:329:1: entryRuleOr : ruleOr EOF ;
    public final void entryRuleOr() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:330:1: ( ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:331:1: ruleOr EOF
            {
             before(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr627);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getOrRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr634); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:338:1: ruleOr : ( ( rule__Or__Group__0 ) ) ;
    public final void ruleOr() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:342:2: ( ( ( rule__Or__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:343:1: ( ( rule__Or__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:343:1: ( ( rule__Or__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:344:1: ( rule__Or__Group__0 )
            {
             before(grammarAccess.getOrAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:345:1: ( rule__Or__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:345:2: rule__Or__Group__0
            {
            pushFollow(FOLLOW_rule__Or__Group__0_in_ruleOr660);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:357:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:358:1: ( ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:359:1: ruleAnd EOF
            {
             before(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd687);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getAndRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd694); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:366:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:370:2: ( ( ( rule__And__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:371:1: ( ( rule__And__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:371:1: ( ( rule__And__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:372:1: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:373:1: ( rule__And__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:373:2: rule__And__Group__0
            {
            pushFollow(FOLLOW_rule__And__Group__0_in_ruleAnd720);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:385:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:386:1: ( rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:387:1: rulePrimary EOF
            {
             before(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary747);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary754); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:394:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:398:2: ( ( ( rule__Primary__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:399:1: ( ( rule__Primary__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:399:1: ( ( rule__Primary__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:400:1: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:401:1: ( rule__Primary__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:401:2: rule__Primary__Alternatives
            {
            pushFollow(FOLLOW_rule__Primary__Alternatives_in_rulePrimary780);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:413:1: entryRuleAtomic : ruleAtomic EOF ;
    public final void entryRuleAtomic() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:414:1: ( ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:415:1: ruleAtomic EOF
            {
             before(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic807);
            ruleAtomic();

            state._fsp--;

             after(grammarAccess.getAtomicRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic814); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:422:1: ruleAtomic : ( ( rule__Atomic__Alternatives ) ) ;
    public final void ruleAtomic() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:426:2: ( ( ( rule__Atomic__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:427:1: ( ( rule__Atomic__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:427:1: ( ( rule__Atomic__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:428:1: ( rule__Atomic__Alternatives )
            {
             before(grammarAccess.getAtomicAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:429:1: ( rule__Atomic__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:429:2: rule__Atomic__Alternatives
            {
            pushFollow(FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic840);
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


    // $ANTLR start "entryRuleActivity"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:441:1: entryRuleActivity : ruleActivity EOF ;
    public final void entryRuleActivity() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:442:1: ( ruleActivity EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:443:1: ruleActivity EOF
            {
             before(grammarAccess.getActivityRule()); 
            pushFollow(FOLLOW_ruleActivity_in_entryRuleActivity867);
            ruleActivity();

            state._fsp--;

             after(grammarAccess.getActivityRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleActivity874); 

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
    // $ANTLR end "entryRuleActivity"


    // $ANTLR start "ruleActivity"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:450:1: ruleActivity : ( ( rule__Activity__Group__0 ) ) ;
    public final void ruleActivity() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:454:2: ( ( ( rule__Activity__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:455:1: ( ( rule__Activity__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:455:1: ( ( rule__Activity__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:456:1: ( rule__Activity__Group__0 )
            {
             before(grammarAccess.getActivityAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:457:1: ( rule__Activity__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:457:2: rule__Activity__Group__0
            {
            pushFollow(FOLLOW_rule__Activity__Group__0_in_ruleActivity900);
            rule__Activity__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleActivity"


    // $ANTLR start "rule__Cardinality__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:470:1: rule__Cardinality__Alternatives : ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) );
    public final void rule__Cardinality__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:474:1: ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) )
            int alt2=4;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_INT) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==19) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt2=2;
                        }
                        break;
                    case 21:
                        {
                        alt2=4;
                        }
                        break;
                    case 20:
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:475:1: ( RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:475:1: ( RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:476:1: RULE_INT
                    {
                     before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives937); 
                     after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:481:6: ( ( rule__Cardinality__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:481:6: ( ( rule__Cardinality__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:482:1: ( rule__Cardinality__Group_1__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:483:1: ( rule__Cardinality__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:483:2: rule__Cardinality__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives954);
                    rule__Cardinality__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:487:6: ( ( rule__Cardinality__Group_2__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:487:6: ( ( rule__Cardinality__Group_2__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:488:1: ( rule__Cardinality__Group_2__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_2()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:489:1: ( rule__Cardinality__Group_2__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:489:2: rule__Cardinality__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives972);
                    rule__Cardinality__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:493:6: ( ( rule__Cardinality__Group_3__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:493:6: ( ( rule__Cardinality__Group_3__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:494:1: ( rule__Cardinality__Group_3__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_3()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:495:1: ( rule__Cardinality__Group_3__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:495:2: rule__Cardinality__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives990);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:504:1: rule__Primary__Alternatives : ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:508:1: ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt3=1;
                }
                break;
            case 25:
                {
                alt3=2;
                }
                break;
            case RULE_ID:
            case 16:
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:509:1: ( ( rule__Primary__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:509:1: ( ( rule__Primary__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:510:1: ( rule__Primary__Group_0__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:511:1: ( rule__Primary__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:511:2: rule__Primary__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives1023);
                    rule__Primary__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:515:6: ( ( rule__Primary__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:515:6: ( ( rule__Primary__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:516:1: ( rule__Primary__Group_1__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:517:1: ( rule__Primary__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:517:2: rule__Primary__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1041);
                    rule__Primary__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:521:6: ( ruleAtomic )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:521:6: ( ruleAtomic )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:522:1: ruleAtomic
                    {
                     before(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1059);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:532:1: rule__Atomic__Alternatives : ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) );
    public final void rule__Atomic__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:536:1: ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:537:1: ( ( rule__Atomic__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:537:1: ( ( rule__Atomic__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:538:1: ( rule__Atomic__Group_0__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:539:1: ( rule__Atomic__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:539:2: rule__Atomic__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1091);
                    rule__Atomic__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAtomicAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:543:6: ( ( rule__Atomic__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:543:6: ( ( rule__Atomic__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:544:1: ( rule__Atomic__Group_1__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:545:1: ( rule__Atomic__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:545:2: rule__Atomic__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1109);
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


    // $ANTLR start "rule__Activity__PreAlternatives_5_0_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:554:1: rule__Activity__PreAlternatives_5_0_0 : ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) );
    public final void rule__Activity__PreAlternatives_5_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:558:1: ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==11) ) {
                alt5=1;
            }
            else if ( (LA5_0==16) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:559:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:559:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:560:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_0_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PreAlternatives_5_0_01142);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:565:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:565:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:566:1: ruleNotMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_0_0_1()); 
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PreAlternatives_5_0_01159);
                    ruleNotMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_0_0_1()); 

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
    // $ANTLR end "rule__Activity__PreAlternatives_5_0_0"


    // $ANTLR start "rule__Activity__PreAlternatives_5_1_1_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:576:1: rule__Activity__PreAlternatives_5_1_1_0 : ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) );
    public final void rule__Activity__PreAlternatives_5_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:580:1: ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==11) ) {
                alt6=1;
            }
            else if ( (LA6_0==16) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:581:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:581:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:582:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_1_1_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PreAlternatives_5_1_1_01191);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:587:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:587:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:588:1: ruleNotMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_1_1_0_1()); 
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PreAlternatives_5_1_1_01208);
                    ruleNotMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_1_1_0_1()); 

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
    // $ANTLR end "rule__Activity__PreAlternatives_5_1_1_0"


    // $ANTLR start "rule__Activity__PostAlternatives_10_0_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:598:1: rule__Activity__PostAlternatives_10_0_0 : ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) | ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) );
    public final void rule__Activity__PostAlternatives_10_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:602:1: ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) | ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) )
            int alt7=4;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt7=1;
                }
                break;
            case 16:
                {
                alt7=2;
                }
                break;
            case 14:
                {
                alt7=3;
                }
                break;
            case 18:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:603:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:603:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:604:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_0_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PostAlternatives_10_0_01240);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:609:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:609:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:610:1: ruleNotMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_0_0_1()); 
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PostAlternatives_10_0_01257);
                    ruleNotMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_0_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:615:6: ( ruleEntityInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:615:6: ( ruleEntityInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:616:1: ruleEntityInvariantCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_0_0_2()); 
                    pushFollow(FOLLOW_ruleEntityInvariantCondition_in_rule__Activity__PostAlternatives_10_0_01274);
                    ruleEntityInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_0_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:621:6: ( ruleAttributeInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:621:6: ( ruleAttributeInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:622:1: ruleAttributeInvariantCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_0_0_3()); 
                    pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_rule__Activity__PostAlternatives_10_0_01291);
                    ruleAttributeInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_0_0_3()); 

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
    // $ANTLR end "rule__Activity__PostAlternatives_10_0_0"


    // $ANTLR start "rule__Activity__PostAlternatives_10_1_1_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:632:1: rule__Activity__PostAlternatives_10_1_1_0 : ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) | ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) );
    public final void rule__Activity__PostAlternatives_10_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:636:1: ( ( ruleEntityAchieveCondition ) | ( ruleNotMandatoryAttributeAchieveCondition ) | ( ruleEntityInvariantCondition ) | ( ruleAttributeInvariantCondition ) )
            int alt8=4;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt8=1;
                }
                break;
            case 16:
                {
                alt8=2;
                }
                break;
            case 14:
                {
                alt8=3;
                }
                break;
            case 18:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:637:1: ( ruleEntityAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:637:1: ( ruleEntityAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:638:1: ruleEntityAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_1_1_0_0()); 
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PostAlternatives_10_1_1_01323);
                    ruleEntityAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:643:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:643:6: ( ruleNotMandatoryAttributeAchieveCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:644:1: ruleNotMandatoryAttributeAchieveCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_1_1_0_1()); 
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PostAlternatives_10_1_1_01340);
                    ruleNotMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:649:6: ( ruleEntityInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:649:6: ( ruleEntityInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:650:1: ruleEntityInvariantCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_1_1_0_2()); 
                    pushFollow(FOLLOW_ruleEntityInvariantCondition_in_rule__Activity__PostAlternatives_10_1_1_01357);
                    ruleEntityInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:655:6: ( ruleAttributeInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:655:6: ( ruleAttributeInvariantCondition )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:656:1: ruleAttributeInvariantCondition
                    {
                     before(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_1_1_0_3()); 
                    pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_rule__Activity__PostAlternatives_10_1_1_01374);
                    ruleAttributeInvariantCondition();

                    state._fsp--;

                     after(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_1_1_0_3()); 

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
    // $ANTLR end "rule__Activity__PostAlternatives_10_1_1_0"


    // $ANTLR start "rule__EntityAchieveCondition__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:668:1: rule__EntityAchieveCondition__Group__0 : rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1 ;
    public final void rule__EntityAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:672:1: ( rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:673:2: rule__EntityAchieveCondition__Group__0__Impl rule__EntityAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__0__Impl_in_rule__EntityAchieveCondition__Group__01404);
            rule__EntityAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__1_in_rule__EntityAchieveCondition__Group__01407);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:680:1: rule__EntityAchieveCondition__Group__0__Impl : ( 'DEFF' ) ;
    public final void rule__EntityAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:684:1: ( ( 'DEFF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:685:1: ( 'DEFF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:685:1: ( 'DEFF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:686:1: 'DEFF'
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getDEFFKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__EntityAchieveCondition__Group__0__Impl1435); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:699:1: rule__EntityAchieveCondition__Group__1 : rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2 ;
    public final void rule__EntityAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:703:1: ( rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:704:2: rule__EntityAchieveCondition__Group__1__Impl rule__EntityAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__1__Impl_in_rule__EntityAchieveCondition__Group__11466);
            rule__EntityAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__2_in_rule__EntityAchieveCondition__Group__11469);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:711:1: rule__EntityAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:715:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:716:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:716:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:717:1: '('
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityAchieveCondition__Group__1__Impl1497); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:730:1: rule__EntityAchieveCondition__Group__2 : rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3 ;
    public final void rule__EntityAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:734:1: ( rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:735:2: rule__EntityAchieveCondition__Group__2__Impl rule__EntityAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__2__Impl_in_rule__EntityAchieveCondition__Group__21528);
            rule__EntityAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__3_in_rule__EntityAchieveCondition__Group__21531);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:742:1: rule__EntityAchieveCondition__Group__2__Impl : ( ( rule__EntityAchieveCondition__NameAssignment_2 ) ) ;
    public final void rule__EntityAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:746:1: ( ( ( rule__EntityAchieveCondition__NameAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:747:1: ( ( rule__EntityAchieveCondition__NameAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:747:1: ( ( rule__EntityAchieveCondition__NameAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:748:1: ( rule__EntityAchieveCondition__NameAssignment_2 )
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getNameAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:749:1: ( rule__EntityAchieveCondition__NameAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:749:2: rule__EntityAchieveCondition__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__NameAssignment_2_in_rule__EntityAchieveCondition__Group__2__Impl1558);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:759:1: rule__EntityAchieveCondition__Group__3 : rule__EntityAchieveCondition__Group__3__Impl ;
    public final void rule__EntityAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:763:1: ( rule__EntityAchieveCondition__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:764:2: rule__EntityAchieveCondition__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__EntityAchieveCondition__Group__3__Impl_in_rule__EntityAchieveCondition__Group__31588);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:770:1: rule__EntityAchieveCondition__Group__3__Impl : ( ')' ) ;
    public final void rule__EntityAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:774:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:775:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:775:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:776:1: ')'
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getRightParenthesisKeyword_3()); 
            match(input,13,FOLLOW_13_in_rule__EntityAchieveCondition__Group__3__Impl1616); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:798:1: rule__EntityInvariantCondition__Group__0 : rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1 ;
    public final void rule__EntityInvariantCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:802:1: ( rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:803:2: rule__EntityInvariantCondition__Group__0__Impl rule__EntityInvariantCondition__Group__1
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__0__Impl_in_rule__EntityInvariantCondition__Group__01656);
            rule__EntityInvariantCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__1_in_rule__EntityInvariantCondition__Group__01659);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:810:1: rule__EntityInvariantCondition__Group__0__Impl : ( 'MUL' ) ;
    public final void rule__EntityInvariantCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:814:1: ( ( 'MUL' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:815:1: ( 'MUL' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:815:1: ( 'MUL' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:816:1: 'MUL'
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0()); 
            match(input,14,FOLLOW_14_in_rule__EntityInvariantCondition__Group__0__Impl1687); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:829:1: rule__EntityInvariantCondition__Group__1 : rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2 ;
    public final void rule__EntityInvariantCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:833:1: ( rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:834:2: rule__EntityInvariantCondition__Group__1__Impl rule__EntityInvariantCondition__Group__2
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__1__Impl_in_rule__EntityInvariantCondition__Group__11718);
            rule__EntityInvariantCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__2_in_rule__EntityInvariantCondition__Group__11721);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:841:1: rule__EntityInvariantCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__EntityInvariantCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:845:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:846:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:846:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:847:1: '('
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__EntityInvariantCondition__Group__1__Impl1749); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:860:1: rule__EntityInvariantCondition__Group__2 : rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3 ;
    public final void rule__EntityInvariantCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:864:1: ( rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:865:2: rule__EntityInvariantCondition__Group__2__Impl rule__EntityInvariantCondition__Group__3
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__2__Impl_in_rule__EntityInvariantCondition__Group__21780);
            rule__EntityInvariantCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__3_in_rule__EntityInvariantCondition__Group__21783);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:872:1: rule__EntityInvariantCondition__Group__2__Impl : ( ( rule__EntityInvariantCondition__NameAssignment_2 ) ) ;
    public final void rule__EntityInvariantCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:876:1: ( ( ( rule__EntityInvariantCondition__NameAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:877:1: ( ( rule__EntityInvariantCondition__NameAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:877:1: ( ( rule__EntityInvariantCondition__NameAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:878:1: ( rule__EntityInvariantCondition__NameAssignment_2 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getNameAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:879:1: ( rule__EntityInvariantCondition__NameAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:879:2: rule__EntityInvariantCondition__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__NameAssignment_2_in_rule__EntityInvariantCondition__Group__2__Impl1810);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:889:1: rule__EntityInvariantCondition__Group__3 : rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4 ;
    public final void rule__EntityInvariantCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:893:1: ( rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:894:2: rule__EntityInvariantCondition__Group__3__Impl rule__EntityInvariantCondition__Group__4
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__3__Impl_in_rule__EntityInvariantCondition__Group__31840);
            rule__EntityInvariantCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__4_in_rule__EntityInvariantCondition__Group__31843);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:901:1: rule__EntityInvariantCondition__Group__3__Impl : ( ',' ) ;
    public final void rule__EntityInvariantCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:905:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:906:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:906:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:907:1: ','
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3()); 
            match(input,15,FOLLOW_15_in_rule__EntityInvariantCondition__Group__3__Impl1871); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:920:1: rule__EntityInvariantCondition__Group__4 : rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5 ;
    public final void rule__EntityInvariantCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:924:1: ( rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:925:2: rule__EntityInvariantCondition__Group__4__Impl rule__EntityInvariantCondition__Group__5
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__4__Impl_in_rule__EntityInvariantCondition__Group__41902);
            rule__EntityInvariantCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__5_in_rule__EntityInvariantCondition__Group__41905);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:932:1: rule__EntityInvariantCondition__Group__4__Impl : ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) ) ;
    public final void rule__EntityInvariantCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:936:1: ( ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:937:1: ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:937:1: ( ( rule__EntityInvariantCondition__CardinalityAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:938:1: ( rule__EntityInvariantCondition__CardinalityAssignment_4 )
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCardinalityAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:939:1: ( rule__EntityInvariantCondition__CardinalityAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:939:2: rule__EntityInvariantCondition__CardinalityAssignment_4
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__CardinalityAssignment_4_in_rule__EntityInvariantCondition__Group__4__Impl1932);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:949:1: rule__EntityInvariantCondition__Group__5 : rule__EntityInvariantCondition__Group__5__Impl ;
    public final void rule__EntityInvariantCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:953:1: ( rule__EntityInvariantCondition__Group__5__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:954:2: rule__EntityInvariantCondition__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__EntityInvariantCondition__Group__5__Impl_in_rule__EntityInvariantCondition__Group__51962);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:960:1: rule__EntityInvariantCondition__Group__5__Impl : ( ')' ) ;
    public final void rule__EntityInvariantCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:964:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:965:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:965:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:966:1: ')'
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getRightParenthesisKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__EntityInvariantCondition__Group__5__Impl1990); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:992:1: rule__NotMandatoryAttributeAchieveCondition__Group__0 : rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:996:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:997:2: rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__02034);
            rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1_in_rule__NotMandatoryAttributeAchieveCondition__Group__02037);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1004:1: rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl : ( 'DEF' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1008:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1009:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1009:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1010:1: 'DEF'
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0()); 
            match(input,16,FOLLOW_16_in_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl2065); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1023:1: rule__NotMandatoryAttributeAchieveCondition__Group__1 : rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1027:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1028:2: rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl rule__NotMandatoryAttributeAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__12096);
            rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2_in_rule__NotMandatoryAttributeAchieveCondition__Group__12099);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1035:1: rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1039:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1040:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1040:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1041:1: '('
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl2127); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1054:1: rule__NotMandatoryAttributeAchieveCondition__Group__2 : rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1058:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1059:2: rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl rule__NotMandatoryAttributeAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__22158);
            rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3_in_rule__NotMandatoryAttributeAchieveCondition__Group__22161);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1066:1: rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1070:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1071:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1071:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1072:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1073:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1073:2: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2_in_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl2188);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1083:1: rule__NotMandatoryAttributeAchieveCondition__Group__3 : rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1087:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1088:2: rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl rule__NotMandatoryAttributeAchieveCondition__Group__4
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__32218);
            rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4_in_rule__NotMandatoryAttributeAchieveCondition__Group__32221);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1095:1: rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1099:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1100:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1100:1: ( ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1101:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )*
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1102:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==15) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1102:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0_in_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl2248);
            	    rule__NotMandatoryAttributeAchieveCondition__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1112:1: rule__NotMandatoryAttributeAchieveCondition__Group__4 : rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1116:1: ( rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1117:2: rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__42279);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1123:1: rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl : ( ')' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1127:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1128:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1128:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1129:1: ')'
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_4()); 
            match(input,13,FOLLOW_13_in_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl2307); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1152:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__0 : rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1 ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1156:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1157:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl rule__NotMandatoryAttributeAchieveCondition__Group_3__1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02348);
            rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02351);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1164:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl : ( ',' ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1168:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1169:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1169:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1170:1: ','
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0()); 
            match(input,15,FOLLOW_15_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl2379); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1183:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__1 : rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1187:1: ( rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1188:2: rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__12410);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1194:1: rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl : ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1198:1: ( ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1199:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1199:1: ( ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1200:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 )
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_3_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1201:1: ( rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1201:2: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1
            {
            pushFollow(FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl2437);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1215:1: rule__MandatoryAttributeAchieveCondition__Group__0 : rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1219:1: ( rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1220:2: rule__MandatoryAttributeAchieveCondition__Group__0__Impl rule__MandatoryAttributeAchieveCondition__Group__1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__02471);
            rule__MandatoryAttributeAchieveCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1_in_rule__MandatoryAttributeAchieveCondition__Group__02474);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1227:1: rule__MandatoryAttributeAchieveCondition__Group__0__Impl : ( 'MAN' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1231:1: ( ( 'MAN' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1232:1: ( 'MAN' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1232:1: ( 'MAN' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1233:1: 'MAN'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__MandatoryAttributeAchieveCondition__Group__0__Impl2502); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1246:1: rule__MandatoryAttributeAchieveCondition__Group__1 : rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1250:1: ( rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1251:2: rule__MandatoryAttributeAchieveCondition__Group__1__Impl rule__MandatoryAttributeAchieveCondition__Group__2
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__12533);
            rule__MandatoryAttributeAchieveCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2_in_rule__MandatoryAttributeAchieveCondition__Group__12536);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1258:1: rule__MandatoryAttributeAchieveCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1262:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1263:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1263:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1264:1: '('
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__1__Impl2564); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1277:1: rule__MandatoryAttributeAchieveCondition__Group__2 : rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1281:1: ( rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1282:2: rule__MandatoryAttributeAchieveCondition__Group__2__Impl rule__MandatoryAttributeAchieveCondition__Group__3
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__22595);
            rule__MandatoryAttributeAchieveCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3_in_rule__MandatoryAttributeAchieveCondition__Group__22598);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1289:1: rule__MandatoryAttributeAchieveCondition__Group__2__Impl : ( 'DEF' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1293:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1294:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1294:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1295:1: 'DEF'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2()); 
            match(input,16,FOLLOW_16_in_rule__MandatoryAttributeAchieveCondition__Group__2__Impl2626); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1308:1: rule__MandatoryAttributeAchieveCondition__Group__3 : rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1312:1: ( rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1313:2: rule__MandatoryAttributeAchieveCondition__Group__3__Impl rule__MandatoryAttributeAchieveCondition__Group__4
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__32657);
            rule__MandatoryAttributeAchieveCondition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4_in_rule__MandatoryAttributeAchieveCondition__Group__32660);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1320:1: rule__MandatoryAttributeAchieveCondition__Group__3__Impl : ( '(' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1324:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1325:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1325:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1326:1: '('
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__3__Impl2688); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1339:1: rule__MandatoryAttributeAchieveCondition__Group__4 : rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1343:1: ( rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1344:2: rule__MandatoryAttributeAchieveCondition__Group__4__Impl rule__MandatoryAttributeAchieveCondition__Group__5
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__42719);
            rule__MandatoryAttributeAchieveCondition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5_in_rule__MandatoryAttributeAchieveCondition__Group__42722);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1351:1: rule__MandatoryAttributeAchieveCondition__Group__4__Impl : ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1355:1: ( ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1356:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1356:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1357:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1358:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1358:2: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4_in_rule__MandatoryAttributeAchieveCondition__Group__4__Impl2749);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1368:1: rule__MandatoryAttributeAchieveCondition__Group__5 : rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1372:1: ( rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1373:2: rule__MandatoryAttributeAchieveCondition__Group__5__Impl rule__MandatoryAttributeAchieveCondition__Group__6
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__52779);
            rule__MandatoryAttributeAchieveCondition__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6_in_rule__MandatoryAttributeAchieveCondition__Group__52782);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1380:1: rule__MandatoryAttributeAchieveCondition__Group__5__Impl : ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1384:1: ( ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1385:1: ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1385:1: ( ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1386:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )*
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getGroup_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1387:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==15) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1387:2: rule__MandatoryAttributeAchieveCondition__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0_in_rule__MandatoryAttributeAchieveCondition__Group__5__Impl2809);
            	    rule__MandatoryAttributeAchieveCondition__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1397:1: rule__MandatoryAttributeAchieveCondition__Group__6 : rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1401:1: ( rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1402:2: rule__MandatoryAttributeAchieveCondition__Group__6__Impl rule__MandatoryAttributeAchieveCondition__Group__7
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__62840);
            rule__MandatoryAttributeAchieveCondition__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7_in_rule__MandatoryAttributeAchieveCondition__Group__62843);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1409:1: rule__MandatoryAttributeAchieveCondition__Group__6__Impl : ( ')' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1413:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1414:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1414:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1415:1: ')'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__6__Impl2871); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1428:1: rule__MandatoryAttributeAchieveCondition__Group__7 : rule__MandatoryAttributeAchieveCondition__Group__7__Impl ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1432:1: ( rule__MandatoryAttributeAchieveCondition__Group__7__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1433:2: rule__MandatoryAttributeAchieveCondition__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__72902);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1439:1: rule__MandatoryAttributeAchieveCondition__Group__7__Impl : ( ')' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1443:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1444:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1444:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1445:1: ')'
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_7()); 
            match(input,13,FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__7__Impl2930); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1474:1: rule__MandatoryAttributeAchieveCondition__Group_5__0 : rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1 ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1478:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1479:2: rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl rule__MandatoryAttributeAchieveCondition__Group_5__1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__02977);
            rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1_in_rule__MandatoryAttributeAchieveCondition__Group_5__02980);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1486:1: rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl : ( ',' ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1490:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1491:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1491:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1492:1: ','
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0()); 
            match(input,15,FOLLOW_15_in_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl3008); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1505:1: rule__MandatoryAttributeAchieveCondition__Group_5__1 : rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1509:1: ( rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1510:2: rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__13039);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1516:1: rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl : ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) ) ;
    public final void rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1520:1: ( ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1521:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1521:1: ( ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1522:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 )
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAssignment_5_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1523:1: ( rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1523:2: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1
            {
            pushFollow(FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1_in_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl3066);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1537:1: rule__AttributeInvariantCondition__Group__0 : rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1 ;
    public final void rule__AttributeInvariantCondition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1541:1: ( rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1542:2: rule__AttributeInvariantCondition__Group__0__Impl rule__AttributeInvariantCondition__Group__1
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__0__Impl_in_rule__AttributeInvariantCondition__Group__03100);
            rule__AttributeInvariantCondition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__1_in_rule__AttributeInvariantCondition__Group__03103);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1549:1: rule__AttributeInvariantCondition__Group__0__Impl : ( 'RUL' ) ;
    public final void rule__AttributeInvariantCondition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1553:1: ( ( 'RUL' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1554:1: ( 'RUL' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1554:1: ( 'RUL' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1555:1: 'RUL'
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0()); 
            match(input,18,FOLLOW_18_in_rule__AttributeInvariantCondition__Group__0__Impl3131); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1568:1: rule__AttributeInvariantCondition__Group__1 : rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2 ;
    public final void rule__AttributeInvariantCondition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1572:1: ( rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1573:2: rule__AttributeInvariantCondition__Group__1__Impl rule__AttributeInvariantCondition__Group__2
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__1__Impl_in_rule__AttributeInvariantCondition__Group__13162);
            rule__AttributeInvariantCondition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__2_in_rule__AttributeInvariantCondition__Group__13165);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1580:1: rule__AttributeInvariantCondition__Group__1__Impl : ( '(' ) ;
    public final void rule__AttributeInvariantCondition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1584:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1585:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1585:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1586:1: '('
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__AttributeInvariantCondition__Group__1__Impl3193); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1599:1: rule__AttributeInvariantCondition__Group__2 : rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3 ;
    public final void rule__AttributeInvariantCondition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1603:1: ( rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1604:2: rule__AttributeInvariantCondition__Group__2__Impl rule__AttributeInvariantCondition__Group__3
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__2__Impl_in_rule__AttributeInvariantCondition__Group__23224);
            rule__AttributeInvariantCondition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__3_in_rule__AttributeInvariantCondition__Group__23227);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1611:1: rule__AttributeInvariantCondition__Group__2__Impl : ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) ) ;
    public final void rule__AttributeInvariantCondition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1615:1: ( ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1616:1: ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1616:1: ( ( rule__AttributeInvariantCondition__ExpressionAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1617:1: ( rule__AttributeInvariantCondition__ExpressionAssignment_2 )
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getExpressionAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1618:1: ( rule__AttributeInvariantCondition__ExpressionAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1618:2: rule__AttributeInvariantCondition__ExpressionAssignment_2
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__ExpressionAssignment_2_in_rule__AttributeInvariantCondition__Group__2__Impl3254);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1628:1: rule__AttributeInvariantCondition__Group__3 : rule__AttributeInvariantCondition__Group__3__Impl ;
    public final void rule__AttributeInvariantCondition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1632:1: ( rule__AttributeInvariantCondition__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1633:2: rule__AttributeInvariantCondition__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__AttributeInvariantCondition__Group__3__Impl_in_rule__AttributeInvariantCondition__Group__33284);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1639:1: rule__AttributeInvariantCondition__Group__3__Impl : ( ')' ) ;
    public final void rule__AttributeInvariantCondition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1643:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1644:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1644:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1645:1: ')'
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getRightParenthesisKeyword_3()); 
            match(input,13,FOLLOW_13_in_rule__AttributeInvariantCondition__Group__3__Impl3312); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1668:1: rule__Cardinality__Group_1__0 : rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 ;
    public final void rule__Cardinality__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1672:1: ( rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1673:2: rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__03353);
            rule__Cardinality__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__03356);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1680:1: rule__Cardinality__Group_1__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1684:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1685:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1685:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1686:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl3383); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1697:1: rule__Cardinality__Group_1__1 : rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 ;
    public final void rule__Cardinality__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1701:1: ( rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1702:2: rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__13412);
            rule__Cardinality__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__13415);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1709:1: rule__Cardinality__Group_1__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1713:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1714:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1714:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1715:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
            match(input,19,FOLLOW_19_in_rule__Cardinality__Group_1__1__Impl3443); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1728:1: rule__Cardinality__Group_1__2 : rule__Cardinality__Group_1__2__Impl ;
    public final void rule__Cardinality__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1732:1: ( rule__Cardinality__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1733:2: rule__Cardinality__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__23474);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1739:1: rule__Cardinality__Group_1__2__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1743:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1744:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1744:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1745:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl3501); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1762:1: rule__Cardinality__Group_2__0 : rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 ;
    public final void rule__Cardinality__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1766:1: ( rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1767:2: rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__03536);
            rule__Cardinality__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__03539);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1774:1: rule__Cardinality__Group_2__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1778:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1779:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1779:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1780:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl3566); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1791:1: rule__Cardinality__Group_2__1 : rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 ;
    public final void rule__Cardinality__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1795:1: ( rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1796:2: rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__13595);
            rule__Cardinality__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__13598);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1803:1: rule__Cardinality__Group_2__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1807:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1808:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1808:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1809:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
            match(input,19,FOLLOW_19_in_rule__Cardinality__Group_2__1__Impl3626); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1822:1: rule__Cardinality__Group_2__2 : rule__Cardinality__Group_2__2__Impl ;
    public final void rule__Cardinality__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1826:1: ( rule__Cardinality__Group_2__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1827:2: rule__Cardinality__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__23657);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1833:1: rule__Cardinality__Group_2__2__Impl : ( '*' ) ;
    public final void rule__Cardinality__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1837:1: ( ( '*' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1838:1: ( '*' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1838:1: ( '*' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1839:1: '*'
            {
             before(grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
            match(input,20,FOLLOW_20_in_rule__Cardinality__Group_2__2__Impl3685); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1858:1: rule__Cardinality__Group_3__0 : rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 ;
    public final void rule__Cardinality__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1862:1: ( rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1863:2: rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__03722);
            rule__Cardinality__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__03725);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1870:1: rule__Cardinality__Group_3__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1874:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1875:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1875:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1876:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl3752); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1887:1: rule__Cardinality__Group_3__1 : rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 ;
    public final void rule__Cardinality__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1891:1: ( rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1892:2: rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__13781);
            rule__Cardinality__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__13784);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1899:1: rule__Cardinality__Group_3__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1903:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1904:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1904:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1905:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
            match(input,19,FOLLOW_19_in_rule__Cardinality__Group_3__1__Impl3812); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1918:1: rule__Cardinality__Group_3__2 : rule__Cardinality__Group_3__2__Impl ;
    public final void rule__Cardinality__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1922:1: ( rule__Cardinality__Group_3__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1923:2: rule__Cardinality__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__23843);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1929:1: rule__Cardinality__Group_3__2__Impl : ( '+' ) ;
    public final void rule__Cardinality__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1933:1: ( ( '+' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1934:1: ( '+' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1934:1: ( '+' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1935:1: '+'
            {
             before(grammarAccess.getCardinalityAccess().getPlusSignKeyword_3_2()); 
            match(input,21,FOLLOW_21_in_rule__Cardinality__Group_3__2__Impl3871); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1954:1: rule__Attribute__Group__0 : rule__Attribute__Group__0__Impl rule__Attribute__Group__1 ;
    public final void rule__Attribute__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1958:1: ( rule__Attribute__Group__0__Impl rule__Attribute__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1959:2: rule__Attribute__Group__0__Impl rule__Attribute__Group__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__03908);
            rule__Attribute__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__03911);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1966:1: rule__Attribute__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1970:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1971:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1971:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1972:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group__0__Impl3938); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1983:1: rule__Attribute__Group__1 : rule__Attribute__Group__1__Impl rule__Attribute__Group__2 ;
    public final void rule__Attribute__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1987:1: ( rule__Attribute__Group__1__Impl rule__Attribute__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1988:2: rule__Attribute__Group__1__Impl rule__Attribute__Group__2
            {
            pushFollow(FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__13967);
            rule__Attribute__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__13970);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1995:1: rule__Attribute__Group__1__Impl : ( '.' ) ;
    public final void rule__Attribute__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:1999:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2000:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2000:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2001:1: '.'
            {
             before(grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
            match(input,22,FOLLOW_22_in_rule__Attribute__Group__1__Impl3998); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2014:1: rule__Attribute__Group__2 : rule__Attribute__Group__2__Impl rule__Attribute__Group__3 ;
    public final void rule__Attribute__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2018:1: ( rule__Attribute__Group__2__Impl rule__Attribute__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2019:2: rule__Attribute__Group__2__Impl rule__Attribute__Group__3
            {
            pushFollow(FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__24029);
            rule__Attribute__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__24032);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2026:1: rule__Attribute__Group__2__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2030:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2031:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2031:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2032:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group__2__Impl4059); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2043:1: rule__Attribute__Group__3 : rule__Attribute__Group__3__Impl ;
    public final void rule__Attribute__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2047:1: ( rule__Attribute__Group__3__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2048:2: rule__Attribute__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__34088);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2054:1: rule__Attribute__Group__3__Impl : ( ( rule__Attribute__Group_3__0 )* ) ;
    public final void rule__Attribute__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2058:1: ( ( ( rule__Attribute__Group_3__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2059:1: ( ( rule__Attribute__Group_3__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2059:1: ( ( rule__Attribute__Group_3__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2060:1: ( rule__Attribute__Group_3__0 )*
            {
             before(grammarAccess.getAttributeAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2061:1: ( rule__Attribute__Group_3__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2061:2: rule__Attribute__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__Attribute__Group_3__0_in_rule__Attribute__Group__3__Impl4115);
            	    rule__Attribute__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2079:1: rule__Attribute__Group_3__0 : rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1 ;
    public final void rule__Attribute__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2083:1: ( rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2084:2: rule__Attribute__Group_3__0__Impl rule__Attribute__Group_3__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group_3__0__Impl_in_rule__Attribute__Group_3__04154);
            rule__Attribute__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group_3__1_in_rule__Attribute__Group_3__04157);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2091:1: rule__Attribute__Group_3__0__Impl : ( '.' ) ;
    public final void rule__Attribute__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2095:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2096:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2096:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2097:1: '.'
            {
             before(grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            match(input,22,FOLLOW_22_in_rule__Attribute__Group_3__0__Impl4185); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2110:1: rule__Attribute__Group_3__1 : rule__Attribute__Group_3__1__Impl ;
    public final void rule__Attribute__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2114:1: ( rule__Attribute__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2115:2: rule__Attribute__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group_3__1__Impl_in_rule__Attribute__Group_3__14216);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2121:1: rule__Attribute__Group_3__1__Impl : ( RULE_ID ) ;
    public final void rule__Attribute__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2125:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2126:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2126:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2127:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__Group_3__1__Impl4243); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2142:1: rule__Or__Group__0 : rule__Or__Group__0__Impl rule__Or__Group__1 ;
    public final void rule__Or__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2146:1: ( rule__Or__Group__0__Impl rule__Or__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2147:2: rule__Or__Group__0__Impl rule__Or__Group__1
            {
            pushFollow(FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__04276);
            rule__Or__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group__1_in_rule__Or__Group__04279);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2154:1: rule__Or__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Or__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2158:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2159:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2159:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2160:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl4306);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2171:1: rule__Or__Group__1 : rule__Or__Group__1__Impl ;
    public final void rule__Or__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2175:1: ( rule__Or__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2176:2: rule__Or__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__14335);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2182:1: rule__Or__Group__1__Impl : ( ( rule__Or__Group_1__0 )* ) ;
    public final void rule__Or__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2186:1: ( ( ( rule__Or__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2187:1: ( ( rule__Or__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2187:1: ( ( rule__Or__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2188:1: ( rule__Or__Group_1__0 )*
            {
             before(grammarAccess.getOrAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2189:1: ( rule__Or__Group_1__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==23) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2189:2: rule__Or__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl4362);
            	    rule__Or__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2203:1: rule__Or__Group_1__0 : rule__Or__Group_1__0__Impl rule__Or__Group_1__1 ;
    public final void rule__Or__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2207:1: ( rule__Or__Group_1__0__Impl rule__Or__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2208:2: rule__Or__Group_1__0__Impl rule__Or__Group_1__1
            {
            pushFollow(FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__04397);
            rule__Or__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__04400);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2215:1: rule__Or__Group_1__0__Impl : ( () ) ;
    public final void rule__Or__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2219:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2220:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2220:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2221:1: ()
            {
             before(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2222:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2224:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2234:1: rule__Or__Group_1__1 : rule__Or__Group_1__1__Impl rule__Or__Group_1__2 ;
    public final void rule__Or__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2238:1: ( rule__Or__Group_1__1__Impl rule__Or__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2239:2: rule__Or__Group_1__1__Impl rule__Or__Group_1__2
            {
            pushFollow(FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__14458);
            rule__Or__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__14461);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2246:1: rule__Or__Group_1__1__Impl : ( 'OR' ) ;
    public final void rule__Or__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2250:1: ( ( 'OR' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2251:1: ( 'OR' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2251:1: ( 'OR' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2252:1: 'OR'
            {
             before(grammarAccess.getOrAccess().getORKeyword_1_1()); 
            match(input,23,FOLLOW_23_in_rule__Or__Group_1__1__Impl4489); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2265:1: rule__Or__Group_1__2 : rule__Or__Group_1__2__Impl ;
    public final void rule__Or__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2269:1: ( rule__Or__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2270:2: rule__Or__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__24520);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2276:1: rule__Or__Group_1__2__Impl : ( ( rule__Or__RightAssignment_1_2 ) ) ;
    public final void rule__Or__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2280:1: ( ( ( rule__Or__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2281:1: ( ( rule__Or__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2281:1: ( ( rule__Or__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2282:1: ( rule__Or__RightAssignment_1_2 )
            {
             before(grammarAccess.getOrAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2283:1: ( rule__Or__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2283:2: rule__Or__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl4547);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2299:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2303:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2304:2: rule__And__Group__0__Impl rule__And__Group__1
            {
            pushFollow(FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__04583);
            rule__And__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group__1_in_rule__And__Group__04586);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2311:1: rule__And__Group__0__Impl : ( rulePrimary ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2315:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2316:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2316:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2317:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__Group__0__Impl4613);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2328:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2332:1: ( rule__And__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2333:2: rule__And__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__14642);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2339:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2343:1: ( ( ( rule__And__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2344:1: ( ( rule__And__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2344:1: ( ( rule__And__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2345:1: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2346:1: ( rule__And__Group_1__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==24) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2346:2: rule__And__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl4669);
            	    rule__And__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2360:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2364:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2365:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
            {
            pushFollow(FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__04704);
            rule__And__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__04707);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2372:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2376:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2377:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2377:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2378:1: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2379:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2381:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2391:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2395:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2396:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__14765);
            rule__And__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__14768);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2403:1: rule__And__Group_1__1__Impl : ( 'AND' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2407:1: ( ( 'AND' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2408:1: ( 'AND' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2408:1: ( 'AND' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2409:1: 'AND'
            {
             before(grammarAccess.getAndAccess().getANDKeyword_1_1()); 
            match(input,24,FOLLOW_24_in_rule__And__Group_1__1__Impl4796); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2422:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2426:1: ( rule__And__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2427:2: rule__And__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__24827);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2433:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2437:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2438:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2438:1: ( ( rule__And__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2439:1: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2440:1: ( rule__And__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2440:2: rule__And__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl4854);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2456:1: rule__Primary__Group_0__0 : rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 ;
    public final void rule__Primary__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2460:1: ( rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2461:2: rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__04890);
            rule__Primary__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__04893);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2468:1: rule__Primary__Group_0__0__Impl : ( '(' ) ;
    public final void rule__Primary__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2472:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2473:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2473:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2474:1: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0()); 
            match(input,12,FOLLOW_12_in_rule__Primary__Group_0__0__Impl4921); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2487:1: rule__Primary__Group_0__1 : rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 ;
    public final void rule__Primary__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2491:1: ( rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2492:2: rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__14952);
            rule__Primary__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__14955);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2499:1: rule__Primary__Group_0__1__Impl : ( ruleExpression ) ;
    public final void rule__Primary__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2503:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2504:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2504:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2505:1: ruleExpression
            {
             before(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl4982);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2516:1: rule__Primary__Group_0__2 : rule__Primary__Group_0__2__Impl ;
    public final void rule__Primary__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2520:1: ( rule__Primary__Group_0__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2521:2: rule__Primary__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__25011);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2527:1: rule__Primary__Group_0__2__Impl : ( ')' ) ;
    public final void rule__Primary__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2531:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2532:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2532:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2533:1: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2()); 
            match(input,13,FOLLOW_13_in_rule__Primary__Group_0__2__Impl5039); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2552:1: rule__Primary__Group_1__0 : rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 ;
    public final void rule__Primary__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2556:1: ( rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2557:2: rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__05076);
            rule__Primary__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__05079);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2564:1: rule__Primary__Group_1__0__Impl : ( () ) ;
    public final void rule__Primary__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2568:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2569:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2569:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2570:1: ()
            {
             before(grammarAccess.getPrimaryAccess().getNotAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2571:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2573:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2583:1: rule__Primary__Group_1__1 : rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 ;
    public final void rule__Primary__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2587:1: ( rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2588:2: rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__15137);
            rule__Primary__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__15140);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2595:1: rule__Primary__Group_1__1__Impl : ( 'NOT' ) ;
    public final void rule__Primary__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2599:1: ( ( 'NOT' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2600:1: ( 'NOT' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2600:1: ( 'NOT' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2601:1: 'NOT'
            {
             before(grammarAccess.getPrimaryAccess().getNOTKeyword_1_1()); 
            match(input,25,FOLLOW_25_in_rule__Primary__Group_1__1__Impl5168); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2614:1: rule__Primary__Group_1__2 : rule__Primary__Group_1__2__Impl ;
    public final void rule__Primary__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2618:1: ( rule__Primary__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2619:2: rule__Primary__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__25199);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2625:1: rule__Primary__Group_1__2__Impl : ( ( rule__Primary__ExpressionAssignment_1_2 ) ) ;
    public final void rule__Primary__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2629:1: ( ( ( rule__Primary__ExpressionAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2630:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2630:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2631:1: ( rule__Primary__ExpressionAssignment_1_2 )
            {
             before(grammarAccess.getPrimaryAccess().getExpressionAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2632:1: ( rule__Primary__ExpressionAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2632:2: rule__Primary__ExpressionAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl5226);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2648:1: rule__Atomic__Group_0__0 : rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 ;
    public final void rule__Atomic__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2652:1: ( rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2653:2: rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__05262);
            rule__Atomic__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__05265);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2660:1: rule__Atomic__Group_0__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2664:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2665:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2665:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2666:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2667:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2669:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2679:1: rule__Atomic__Group_0__1 : rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 ;
    public final void rule__Atomic__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2683:1: ( rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2684:2: rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__15323);
            rule__Atomic__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__15326);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2691:1: rule__Atomic__Group_0__1__Impl : ( 'DEF' ) ;
    public final void rule__Atomic__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2695:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2696:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2696:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2697:1: 'DEF'
            {
             before(grammarAccess.getAtomicAccess().getDEFKeyword_0_1()); 
            match(input,16,FOLLOW_16_in_rule__Atomic__Group_0__1__Impl5354); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2710:1: rule__Atomic__Group_0__2 : rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 ;
    public final void rule__Atomic__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2714:1: ( rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2715:2: rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__25385);
            rule__Atomic__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__25388);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2722:1: rule__Atomic__Group_0__2__Impl : ( '(' ) ;
    public final void rule__Atomic__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2726:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2727:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2727:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2728:1: '('
            {
             before(grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2()); 
            match(input,12,FOLLOW_12_in_rule__Atomic__Group_0__2__Impl5416); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2741:1: rule__Atomic__Group_0__3 : rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 ;
    public final void rule__Atomic__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2745:1: ( rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2746:2: rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__35447);
            rule__Atomic__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__35450);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2753:1: rule__Atomic__Group_0__3__Impl : ( ( rule__Atomic__NameAssignment_0_3 ) ) ;
    public final void rule__Atomic__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2757:1: ( ( ( rule__Atomic__NameAssignment_0_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2758:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2758:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2759:1: ( rule__Atomic__NameAssignment_0_3 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_0_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2760:1: ( rule__Atomic__NameAssignment_0_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2760:2: rule__Atomic__NameAssignment_0_3
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl5477);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2770:1: rule__Atomic__Group_0__4 : rule__Atomic__Group_0__4__Impl ;
    public final void rule__Atomic__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2774:1: ( rule__Atomic__Group_0__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2775:2: rule__Atomic__Group_0__4__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__45507);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2781:1: rule__Atomic__Group_0__4__Impl : ( ')' ) ;
    public final void rule__Atomic__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2785:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2786:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2786:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2787:1: ')'
            {
             before(grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4()); 
            match(input,13,FOLLOW_13_in_rule__Atomic__Group_0__4__Impl5535); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2810:1: rule__Atomic__Group_1__0 : rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 ;
    public final void rule__Atomic__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2814:1: ( rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2815:2: rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__05576);
            rule__Atomic__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__05579);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2822:1: rule__Atomic__Group_1__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2826:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2827:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2827:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2828:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeValueAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2829:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2831:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2841:1: rule__Atomic__Group_1__1 : rule__Atomic__Group_1__1__Impl ;
    public final void rule__Atomic__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2845:1: ( rule__Atomic__Group_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2846:2: rule__Atomic__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__15637);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2852:1: rule__Atomic__Group_1__1__Impl : ( ( rule__Atomic__NameAssignment_1_1 ) ) ;
    public final void rule__Atomic__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2856:1: ( ( ( rule__Atomic__NameAssignment_1_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2857:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2857:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2858:1: ( rule__Atomic__NameAssignment_1_1 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2859:1: ( rule__Atomic__NameAssignment_1_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2859:2: rule__Atomic__NameAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl5664);
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


    // $ANTLR start "rule__Activity__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2873:1: rule__Activity__Group__0 : rule__Activity__Group__0__Impl rule__Activity__Group__1 ;
    public final void rule__Activity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2877:1: ( rule__Activity__Group__0__Impl rule__Activity__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2878:2: rule__Activity__Group__0__Impl rule__Activity__Group__1
            {
            pushFollow(FOLLOW_rule__Activity__Group__0__Impl_in_rule__Activity__Group__05698);
            rule__Activity__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__1_in_rule__Activity__Group__05701);
            rule__Activity__Group__1();

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
    // $ANTLR end "rule__Activity__Group__0"


    // $ANTLR start "rule__Activity__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2885:1: rule__Activity__Group__0__Impl : ( ( rule__Activity__NameAssignment_0 ) ) ;
    public final void rule__Activity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2889:1: ( ( ( rule__Activity__NameAssignment_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2890:1: ( ( rule__Activity__NameAssignment_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2890:1: ( ( rule__Activity__NameAssignment_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2891:1: ( rule__Activity__NameAssignment_0 )
            {
             before(grammarAccess.getActivityAccess().getNameAssignment_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2892:1: ( rule__Activity__NameAssignment_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2892:2: rule__Activity__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__Activity__NameAssignment_0_in_rule__Activity__Group__0__Impl5728);
            rule__Activity__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__0__Impl"


    // $ANTLR start "rule__Activity__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2902:1: rule__Activity__Group__1 : rule__Activity__Group__1__Impl rule__Activity__Group__2 ;
    public final void rule__Activity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2906:1: ( rule__Activity__Group__1__Impl rule__Activity__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2907:2: rule__Activity__Group__1__Impl rule__Activity__Group__2
            {
            pushFollow(FOLLOW_rule__Activity__Group__1__Impl_in_rule__Activity__Group__15758);
            rule__Activity__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__2_in_rule__Activity__Group__15761);
            rule__Activity__Group__2();

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
    // $ANTLR end "rule__Activity__Group__1"


    // $ANTLR start "rule__Activity__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2914:1: rule__Activity__Group__1__Impl : ( ':' ) ;
    public final void rule__Activity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2918:1: ( ( ':' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2919:1: ( ':' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2919:1: ( ':' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2920:1: ':'
            {
             before(grammarAccess.getActivityAccess().getColonKeyword_1()); 
            match(input,26,FOLLOW_26_in_rule__Activity__Group__1__Impl5789); 
             after(grammarAccess.getActivityAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__1__Impl"


    // $ANTLR start "rule__Activity__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2933:1: rule__Activity__Group__2 : rule__Activity__Group__2__Impl rule__Activity__Group__3 ;
    public final void rule__Activity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2937:1: ( rule__Activity__Group__2__Impl rule__Activity__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2938:2: rule__Activity__Group__2__Impl rule__Activity__Group__3
            {
            pushFollow(FOLLOW_rule__Activity__Group__2__Impl_in_rule__Activity__Group__25820);
            rule__Activity__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__3_in_rule__Activity__Group__25823);
            rule__Activity__Group__3();

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
    // $ANTLR end "rule__Activity__Group__2"


    // $ANTLR start "rule__Activity__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2945:1: rule__Activity__Group__2__Impl : ( ( rule__Activity__DescriptionAssignment_2 ) ) ;
    public final void rule__Activity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2949:1: ( ( ( rule__Activity__DescriptionAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2950:1: ( ( rule__Activity__DescriptionAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2950:1: ( ( rule__Activity__DescriptionAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2951:1: ( rule__Activity__DescriptionAssignment_2 )
            {
             before(grammarAccess.getActivityAccess().getDescriptionAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2952:1: ( rule__Activity__DescriptionAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2952:2: rule__Activity__DescriptionAssignment_2
            {
            pushFollow(FOLLOW_rule__Activity__DescriptionAssignment_2_in_rule__Activity__Group__2__Impl5850);
            rule__Activity__DescriptionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getDescriptionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__2__Impl"


    // $ANTLR start "rule__Activity__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2962:1: rule__Activity__Group__3 : rule__Activity__Group__3__Impl rule__Activity__Group__4 ;
    public final void rule__Activity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2966:1: ( rule__Activity__Group__3__Impl rule__Activity__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2967:2: rule__Activity__Group__3__Impl rule__Activity__Group__4
            {
            pushFollow(FOLLOW_rule__Activity__Group__3__Impl_in_rule__Activity__Group__35880);
            rule__Activity__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__4_in_rule__Activity__Group__35883);
            rule__Activity__Group__4();

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
    // $ANTLR end "rule__Activity__Group__3"


    // $ANTLR start "rule__Activity__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2974:1: rule__Activity__Group__3__Impl : ( 'PRE' ) ;
    public final void rule__Activity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2978:1: ( ( 'PRE' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2979:1: ( 'PRE' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2979:1: ( 'PRE' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2980:1: 'PRE'
            {
             before(grammarAccess.getActivityAccess().getPREKeyword_3()); 
            match(input,27,FOLLOW_27_in_rule__Activity__Group__3__Impl5911); 
             after(grammarAccess.getActivityAccess().getPREKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__3__Impl"


    // $ANTLR start "rule__Activity__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2993:1: rule__Activity__Group__4 : rule__Activity__Group__4__Impl rule__Activity__Group__5 ;
    public final void rule__Activity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2997:1: ( rule__Activity__Group__4__Impl rule__Activity__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:2998:2: rule__Activity__Group__4__Impl rule__Activity__Group__5
            {
            pushFollow(FOLLOW_rule__Activity__Group__4__Impl_in_rule__Activity__Group__45942);
            rule__Activity__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__5_in_rule__Activity__Group__45945);
            rule__Activity__Group__5();

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
    // $ANTLR end "rule__Activity__Group__4"


    // $ANTLR start "rule__Activity__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3005:1: rule__Activity__Group__4__Impl : ( '(' ) ;
    public final void rule__Activity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3009:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3010:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3010:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3011:1: '('
            {
             before(grammarAccess.getActivityAccess().getLeftParenthesisKeyword_4()); 
            match(input,12,FOLLOW_12_in_rule__Activity__Group__4__Impl5973); 
             after(grammarAccess.getActivityAccess().getLeftParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__4__Impl"


    // $ANTLR start "rule__Activity__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3024:1: rule__Activity__Group__5 : rule__Activity__Group__5__Impl rule__Activity__Group__6 ;
    public final void rule__Activity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3028:1: ( rule__Activity__Group__5__Impl rule__Activity__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3029:2: rule__Activity__Group__5__Impl rule__Activity__Group__6
            {
            pushFollow(FOLLOW_rule__Activity__Group__5__Impl_in_rule__Activity__Group__56004);
            rule__Activity__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__6_in_rule__Activity__Group__56007);
            rule__Activity__Group__6();

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
    // $ANTLR end "rule__Activity__Group__5"


    // $ANTLR start "rule__Activity__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3036:1: rule__Activity__Group__5__Impl : ( ( rule__Activity__Group_5__0 )? ) ;
    public final void rule__Activity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3040:1: ( ( ( rule__Activity__Group_5__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3041:1: ( ( rule__Activity__Group_5__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3041:1: ( ( rule__Activity__Group_5__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3042:1: ( rule__Activity__Group_5__0 )?
            {
             before(grammarAccess.getActivityAccess().getGroup_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3043:1: ( rule__Activity__Group_5__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==11||LA14_0==16) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3043:2: rule__Activity__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__Activity__Group_5__0_in_rule__Activity__Group__5__Impl6034);
                    rule__Activity__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getActivityAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__5__Impl"


    // $ANTLR start "rule__Activity__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3053:1: rule__Activity__Group__6 : rule__Activity__Group__6__Impl rule__Activity__Group__7 ;
    public final void rule__Activity__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3057:1: ( rule__Activity__Group__6__Impl rule__Activity__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3058:2: rule__Activity__Group__6__Impl rule__Activity__Group__7
            {
            pushFollow(FOLLOW_rule__Activity__Group__6__Impl_in_rule__Activity__Group__66065);
            rule__Activity__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__7_in_rule__Activity__Group__66068);
            rule__Activity__Group__7();

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
    // $ANTLR end "rule__Activity__Group__6"


    // $ANTLR start "rule__Activity__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3065:1: rule__Activity__Group__6__Impl : ( ')' ) ;
    public final void rule__Activity__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3069:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3070:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3070:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3071:1: ')'
            {
             before(grammarAccess.getActivityAccess().getRightParenthesisKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__Activity__Group__6__Impl6096); 
             after(grammarAccess.getActivityAccess().getRightParenthesisKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__6__Impl"


    // $ANTLR start "rule__Activity__Group__7"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3084:1: rule__Activity__Group__7 : rule__Activity__Group__7__Impl rule__Activity__Group__8 ;
    public final void rule__Activity__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3088:1: ( rule__Activity__Group__7__Impl rule__Activity__Group__8 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3089:2: rule__Activity__Group__7__Impl rule__Activity__Group__8
            {
            pushFollow(FOLLOW_rule__Activity__Group__7__Impl_in_rule__Activity__Group__76127);
            rule__Activity__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__8_in_rule__Activity__Group__76130);
            rule__Activity__Group__8();

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
    // $ANTLR end "rule__Activity__Group__7"


    // $ANTLR start "rule__Activity__Group__7__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3096:1: rule__Activity__Group__7__Impl : ( ',' ) ;
    public final void rule__Activity__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3100:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3101:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3101:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3102:1: ','
            {
             before(grammarAccess.getActivityAccess().getCommaKeyword_7()); 
            match(input,15,FOLLOW_15_in_rule__Activity__Group__7__Impl6158); 
             after(grammarAccess.getActivityAccess().getCommaKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__7__Impl"


    // $ANTLR start "rule__Activity__Group__8"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3115:1: rule__Activity__Group__8 : rule__Activity__Group__8__Impl rule__Activity__Group__9 ;
    public final void rule__Activity__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3119:1: ( rule__Activity__Group__8__Impl rule__Activity__Group__9 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3120:2: rule__Activity__Group__8__Impl rule__Activity__Group__9
            {
            pushFollow(FOLLOW_rule__Activity__Group__8__Impl_in_rule__Activity__Group__86189);
            rule__Activity__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__9_in_rule__Activity__Group__86192);
            rule__Activity__Group__9();

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
    // $ANTLR end "rule__Activity__Group__8"


    // $ANTLR start "rule__Activity__Group__8__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3127:1: rule__Activity__Group__8__Impl : ( 'POST' ) ;
    public final void rule__Activity__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3131:1: ( ( 'POST' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3132:1: ( 'POST' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3132:1: ( 'POST' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3133:1: 'POST'
            {
             before(grammarAccess.getActivityAccess().getPOSTKeyword_8()); 
            match(input,28,FOLLOW_28_in_rule__Activity__Group__8__Impl6220); 
             after(grammarAccess.getActivityAccess().getPOSTKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__8__Impl"


    // $ANTLR start "rule__Activity__Group__9"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3146:1: rule__Activity__Group__9 : rule__Activity__Group__9__Impl rule__Activity__Group__10 ;
    public final void rule__Activity__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3150:1: ( rule__Activity__Group__9__Impl rule__Activity__Group__10 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3151:2: rule__Activity__Group__9__Impl rule__Activity__Group__10
            {
            pushFollow(FOLLOW_rule__Activity__Group__9__Impl_in_rule__Activity__Group__96251);
            rule__Activity__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__10_in_rule__Activity__Group__96254);
            rule__Activity__Group__10();

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
    // $ANTLR end "rule__Activity__Group__9"


    // $ANTLR start "rule__Activity__Group__9__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3158:1: rule__Activity__Group__9__Impl : ( '(' ) ;
    public final void rule__Activity__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3162:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3163:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3163:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3164:1: '('
            {
             before(grammarAccess.getActivityAccess().getLeftParenthesisKeyword_9()); 
            match(input,12,FOLLOW_12_in_rule__Activity__Group__9__Impl6282); 
             after(grammarAccess.getActivityAccess().getLeftParenthesisKeyword_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__9__Impl"


    // $ANTLR start "rule__Activity__Group__10"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3177:1: rule__Activity__Group__10 : rule__Activity__Group__10__Impl rule__Activity__Group__11 ;
    public final void rule__Activity__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3181:1: ( rule__Activity__Group__10__Impl rule__Activity__Group__11 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3182:2: rule__Activity__Group__10__Impl rule__Activity__Group__11
            {
            pushFollow(FOLLOW_rule__Activity__Group__10__Impl_in_rule__Activity__Group__106313);
            rule__Activity__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group__11_in_rule__Activity__Group__106316);
            rule__Activity__Group__11();

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
    // $ANTLR end "rule__Activity__Group__10"


    // $ANTLR start "rule__Activity__Group__10__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3189:1: rule__Activity__Group__10__Impl : ( ( rule__Activity__Group_10__0 )? ) ;
    public final void rule__Activity__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3193:1: ( ( ( rule__Activity__Group_10__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3194:1: ( ( rule__Activity__Group_10__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3194:1: ( ( rule__Activity__Group_10__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3195:1: ( rule__Activity__Group_10__0 )?
            {
             before(grammarAccess.getActivityAccess().getGroup_10()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3196:1: ( rule__Activity__Group_10__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==11||LA15_0==14||LA15_0==16||LA15_0==18) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3196:2: rule__Activity__Group_10__0
                    {
                    pushFollow(FOLLOW_rule__Activity__Group_10__0_in_rule__Activity__Group__10__Impl6343);
                    rule__Activity__Group_10__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getActivityAccess().getGroup_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__10__Impl"


    // $ANTLR start "rule__Activity__Group__11"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3206:1: rule__Activity__Group__11 : rule__Activity__Group__11__Impl ;
    public final void rule__Activity__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3210:1: ( rule__Activity__Group__11__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3211:2: rule__Activity__Group__11__Impl
            {
            pushFollow(FOLLOW_rule__Activity__Group__11__Impl_in_rule__Activity__Group__116374);
            rule__Activity__Group__11__Impl();

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
    // $ANTLR end "rule__Activity__Group__11"


    // $ANTLR start "rule__Activity__Group__11__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3217:1: rule__Activity__Group__11__Impl : ( ')' ) ;
    public final void rule__Activity__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3221:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3222:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3222:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3223:1: ')'
            {
             before(grammarAccess.getActivityAccess().getRightParenthesisKeyword_11()); 
            match(input,13,FOLLOW_13_in_rule__Activity__Group__11__Impl6402); 
             after(grammarAccess.getActivityAccess().getRightParenthesisKeyword_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group__11__Impl"


    // $ANTLR start "rule__Activity__Group_5__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3260:1: rule__Activity__Group_5__0 : rule__Activity__Group_5__0__Impl rule__Activity__Group_5__1 ;
    public final void rule__Activity__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3264:1: ( rule__Activity__Group_5__0__Impl rule__Activity__Group_5__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3265:2: rule__Activity__Group_5__0__Impl rule__Activity__Group_5__1
            {
            pushFollow(FOLLOW_rule__Activity__Group_5__0__Impl_in_rule__Activity__Group_5__06457);
            rule__Activity__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group_5__1_in_rule__Activity__Group_5__06460);
            rule__Activity__Group_5__1();

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
    // $ANTLR end "rule__Activity__Group_5__0"


    // $ANTLR start "rule__Activity__Group_5__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3272:1: rule__Activity__Group_5__0__Impl : ( ( rule__Activity__PreAssignment_5_0 ) ) ;
    public final void rule__Activity__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3276:1: ( ( ( rule__Activity__PreAssignment_5_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3277:1: ( ( rule__Activity__PreAssignment_5_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3277:1: ( ( rule__Activity__PreAssignment_5_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3278:1: ( rule__Activity__PreAssignment_5_0 )
            {
             before(grammarAccess.getActivityAccess().getPreAssignment_5_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3279:1: ( rule__Activity__PreAssignment_5_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3279:2: rule__Activity__PreAssignment_5_0
            {
            pushFollow(FOLLOW_rule__Activity__PreAssignment_5_0_in_rule__Activity__Group_5__0__Impl6487);
            rule__Activity__PreAssignment_5_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPreAssignment_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_5__0__Impl"


    // $ANTLR start "rule__Activity__Group_5__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3289:1: rule__Activity__Group_5__1 : rule__Activity__Group_5__1__Impl ;
    public final void rule__Activity__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3293:1: ( rule__Activity__Group_5__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3294:2: rule__Activity__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Activity__Group_5__1__Impl_in_rule__Activity__Group_5__16517);
            rule__Activity__Group_5__1__Impl();

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
    // $ANTLR end "rule__Activity__Group_5__1"


    // $ANTLR start "rule__Activity__Group_5__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3300:1: rule__Activity__Group_5__1__Impl : ( ( rule__Activity__Group_5_1__0 )* ) ;
    public final void rule__Activity__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3304:1: ( ( ( rule__Activity__Group_5_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3305:1: ( ( rule__Activity__Group_5_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3305:1: ( ( rule__Activity__Group_5_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3306:1: ( rule__Activity__Group_5_1__0 )*
            {
             before(grammarAccess.getActivityAccess().getGroup_5_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3307:1: ( rule__Activity__Group_5_1__0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==15) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3307:2: rule__Activity__Group_5_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Activity__Group_5_1__0_in_rule__Activity__Group_5__1__Impl6544);
            	    rule__Activity__Group_5_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getActivityAccess().getGroup_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_5__1__Impl"


    // $ANTLR start "rule__Activity__Group_5_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3321:1: rule__Activity__Group_5_1__0 : rule__Activity__Group_5_1__0__Impl rule__Activity__Group_5_1__1 ;
    public final void rule__Activity__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3325:1: ( rule__Activity__Group_5_1__0__Impl rule__Activity__Group_5_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3326:2: rule__Activity__Group_5_1__0__Impl rule__Activity__Group_5_1__1
            {
            pushFollow(FOLLOW_rule__Activity__Group_5_1__0__Impl_in_rule__Activity__Group_5_1__06579);
            rule__Activity__Group_5_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group_5_1__1_in_rule__Activity__Group_5_1__06582);
            rule__Activity__Group_5_1__1();

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
    // $ANTLR end "rule__Activity__Group_5_1__0"


    // $ANTLR start "rule__Activity__Group_5_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3333:1: rule__Activity__Group_5_1__0__Impl : ( ',' ) ;
    public final void rule__Activity__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3337:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3338:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3338:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3339:1: ','
            {
             before(grammarAccess.getActivityAccess().getCommaKeyword_5_1_0()); 
            match(input,15,FOLLOW_15_in_rule__Activity__Group_5_1__0__Impl6610); 
             after(grammarAccess.getActivityAccess().getCommaKeyword_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_5_1__0__Impl"


    // $ANTLR start "rule__Activity__Group_5_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3352:1: rule__Activity__Group_5_1__1 : rule__Activity__Group_5_1__1__Impl ;
    public final void rule__Activity__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3356:1: ( rule__Activity__Group_5_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3357:2: rule__Activity__Group_5_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Activity__Group_5_1__1__Impl_in_rule__Activity__Group_5_1__16641);
            rule__Activity__Group_5_1__1__Impl();

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
    // $ANTLR end "rule__Activity__Group_5_1__1"


    // $ANTLR start "rule__Activity__Group_5_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3363:1: rule__Activity__Group_5_1__1__Impl : ( ( rule__Activity__PreAssignment_5_1_1 ) ) ;
    public final void rule__Activity__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3367:1: ( ( ( rule__Activity__PreAssignment_5_1_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3368:1: ( ( rule__Activity__PreAssignment_5_1_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3368:1: ( ( rule__Activity__PreAssignment_5_1_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3369:1: ( rule__Activity__PreAssignment_5_1_1 )
            {
             before(grammarAccess.getActivityAccess().getPreAssignment_5_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3370:1: ( rule__Activity__PreAssignment_5_1_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3370:2: rule__Activity__PreAssignment_5_1_1
            {
            pushFollow(FOLLOW_rule__Activity__PreAssignment_5_1_1_in_rule__Activity__Group_5_1__1__Impl6668);
            rule__Activity__PreAssignment_5_1_1();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPreAssignment_5_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_5_1__1__Impl"


    // $ANTLR start "rule__Activity__Group_10__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3384:1: rule__Activity__Group_10__0 : rule__Activity__Group_10__0__Impl rule__Activity__Group_10__1 ;
    public final void rule__Activity__Group_10__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3388:1: ( rule__Activity__Group_10__0__Impl rule__Activity__Group_10__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3389:2: rule__Activity__Group_10__0__Impl rule__Activity__Group_10__1
            {
            pushFollow(FOLLOW_rule__Activity__Group_10__0__Impl_in_rule__Activity__Group_10__06702);
            rule__Activity__Group_10__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group_10__1_in_rule__Activity__Group_10__06705);
            rule__Activity__Group_10__1();

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
    // $ANTLR end "rule__Activity__Group_10__0"


    // $ANTLR start "rule__Activity__Group_10__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3396:1: rule__Activity__Group_10__0__Impl : ( ( rule__Activity__PostAssignment_10_0 ) ) ;
    public final void rule__Activity__Group_10__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3400:1: ( ( ( rule__Activity__PostAssignment_10_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3401:1: ( ( rule__Activity__PostAssignment_10_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3401:1: ( ( rule__Activity__PostAssignment_10_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3402:1: ( rule__Activity__PostAssignment_10_0 )
            {
             before(grammarAccess.getActivityAccess().getPostAssignment_10_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3403:1: ( rule__Activity__PostAssignment_10_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3403:2: rule__Activity__PostAssignment_10_0
            {
            pushFollow(FOLLOW_rule__Activity__PostAssignment_10_0_in_rule__Activity__Group_10__0__Impl6732);
            rule__Activity__PostAssignment_10_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPostAssignment_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_10__0__Impl"


    // $ANTLR start "rule__Activity__Group_10__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3413:1: rule__Activity__Group_10__1 : rule__Activity__Group_10__1__Impl ;
    public final void rule__Activity__Group_10__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3417:1: ( rule__Activity__Group_10__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3418:2: rule__Activity__Group_10__1__Impl
            {
            pushFollow(FOLLOW_rule__Activity__Group_10__1__Impl_in_rule__Activity__Group_10__16762);
            rule__Activity__Group_10__1__Impl();

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
    // $ANTLR end "rule__Activity__Group_10__1"


    // $ANTLR start "rule__Activity__Group_10__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3424:1: rule__Activity__Group_10__1__Impl : ( ( rule__Activity__Group_10_1__0 )* ) ;
    public final void rule__Activity__Group_10__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3428:1: ( ( ( rule__Activity__Group_10_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3429:1: ( ( rule__Activity__Group_10_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3429:1: ( ( rule__Activity__Group_10_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3430:1: ( rule__Activity__Group_10_1__0 )*
            {
             before(grammarAccess.getActivityAccess().getGroup_10_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3431:1: ( rule__Activity__Group_10_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==15) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3431:2: rule__Activity__Group_10_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Activity__Group_10_1__0_in_rule__Activity__Group_10__1__Impl6789);
            	    rule__Activity__Group_10_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getActivityAccess().getGroup_10_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_10__1__Impl"


    // $ANTLR start "rule__Activity__Group_10_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3445:1: rule__Activity__Group_10_1__0 : rule__Activity__Group_10_1__0__Impl rule__Activity__Group_10_1__1 ;
    public final void rule__Activity__Group_10_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3449:1: ( rule__Activity__Group_10_1__0__Impl rule__Activity__Group_10_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3450:2: rule__Activity__Group_10_1__0__Impl rule__Activity__Group_10_1__1
            {
            pushFollow(FOLLOW_rule__Activity__Group_10_1__0__Impl_in_rule__Activity__Group_10_1__06824);
            rule__Activity__Group_10_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Activity__Group_10_1__1_in_rule__Activity__Group_10_1__06827);
            rule__Activity__Group_10_1__1();

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
    // $ANTLR end "rule__Activity__Group_10_1__0"


    // $ANTLR start "rule__Activity__Group_10_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3457:1: rule__Activity__Group_10_1__0__Impl : ( ',' ) ;
    public final void rule__Activity__Group_10_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3461:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3462:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3462:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3463:1: ','
            {
             before(grammarAccess.getActivityAccess().getCommaKeyword_10_1_0()); 
            match(input,15,FOLLOW_15_in_rule__Activity__Group_10_1__0__Impl6855); 
             after(grammarAccess.getActivityAccess().getCommaKeyword_10_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_10_1__0__Impl"


    // $ANTLR start "rule__Activity__Group_10_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3476:1: rule__Activity__Group_10_1__1 : rule__Activity__Group_10_1__1__Impl ;
    public final void rule__Activity__Group_10_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3480:1: ( rule__Activity__Group_10_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3481:2: rule__Activity__Group_10_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Activity__Group_10_1__1__Impl_in_rule__Activity__Group_10_1__16886);
            rule__Activity__Group_10_1__1__Impl();

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
    // $ANTLR end "rule__Activity__Group_10_1__1"


    // $ANTLR start "rule__Activity__Group_10_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3487:1: rule__Activity__Group_10_1__1__Impl : ( ( rule__Activity__PostAssignment_10_1_1 ) ) ;
    public final void rule__Activity__Group_10_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3491:1: ( ( ( rule__Activity__PostAssignment_10_1_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3492:1: ( ( rule__Activity__PostAssignment_10_1_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3492:1: ( ( rule__Activity__PostAssignment_10_1_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3493:1: ( rule__Activity__PostAssignment_10_1_1 )
            {
             before(grammarAccess.getActivityAccess().getPostAssignment_10_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3494:1: ( rule__Activity__PostAssignment_10_1_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3494:2: rule__Activity__PostAssignment_10_1_1
            {
            pushFollow(FOLLOW_rule__Activity__PostAssignment_10_1_1_in_rule__Activity__Group_10_1__1__Impl6913);
            rule__Activity__PostAssignment_10_1_1();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPostAssignment_10_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__Group_10_1__1__Impl"


    // $ANTLR start "rule__ActivityModel__ActivitiesAssignment"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3509:1: rule__ActivityModel__ActivitiesAssignment : ( ruleActivity ) ;
    public final void rule__ActivityModel__ActivitiesAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3513:1: ( ( ruleActivity ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3514:1: ( ruleActivity )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3514:1: ( ruleActivity )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3515:1: ruleActivity
            {
             before(grammarAccess.getActivityModelAccess().getActivitiesActivityParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleActivity_in_rule__ActivityModel__ActivitiesAssignment6952);
            ruleActivity();

            state._fsp--;

             after(grammarAccess.getActivityModelAccess().getActivitiesActivityParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ActivityModel__ActivitiesAssignment"


    // $ANTLR start "rule__EntityAchieveCondition__NameAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3524:1: rule__EntityAchieveCondition__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__EntityAchieveCondition__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3528:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3529:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3529:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3530:1: RULE_ID
            {
             before(grammarAccess.getEntityAchieveConditionAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__EntityAchieveCondition__NameAssignment_26983); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3540:1: rule__EntityInvariantCondition__NameAssignment_2 : ( ruleAttribute ) ;
    public final void rule__EntityInvariantCondition__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3544:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3545:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3545:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3546:1: ruleAttribute
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__EntityInvariantCondition__NameAssignment_27015);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3555:1: rule__EntityInvariantCondition__CardinalityAssignment_4 : ( ruleCardinality ) ;
    public final void rule__EntityInvariantCondition__CardinalityAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3559:1: ( ( ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3560:1: ( ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3560:1: ( ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3561:1: ruleCardinality
            {
             before(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleCardinality_in_rule__EntityInvariantCondition__CardinalityAssignment_47046);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3572:1: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2 : ( ruleAttribute ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3576:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3577:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3577:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3578:1: ruleAttribute
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_27079);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3587:1: rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1 : ( ruleAttribute ) ;
    public final void rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3591:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3592:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3592:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3593:1: ruleAttribute
            {
             before(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_17110);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3602:1: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4 : ( ruleAttribute ) ;
    public final void rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3606:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3607:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3607:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3608:1: ruleAttribute
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_47141);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3617:1: rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1 : ( ruleAttribute ) ;
    public final void rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3621:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3622:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3622:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3623:1: ruleAttribute
            {
             before(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_17172);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3632:1: rule__AttributeInvariantCondition__ExpressionAssignment_2 : ( ruleExpression ) ;
    public final void rule__AttributeInvariantCondition__ExpressionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3636:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3637:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3637:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3638:1: ruleExpression
            {
             before(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__AttributeInvariantCondition__ExpressionAssignment_27203);
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


    // $ANTLR start "rule__Or__RightAssignment_1_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3651:1: rule__Or__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Or__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3655:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3656:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3656:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3657:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_27238);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3666:1: rule__And__RightAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3670:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3671:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3671:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3672:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_27269);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3681:1: rule__Primary__ExpressionAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__Primary__ExpressionAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3685:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3686:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3686:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3687:1: rulePrimary
            {
             before(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_27300);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3696:1: rule__Atomic__NameAssignment_0_3 : ( ruleAttribute ) ;
    public final void rule__Atomic__NameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3700:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3701:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3701:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3702:1: ruleAttribute
            {
             before(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_0_37331);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3711:1: rule__Atomic__NameAssignment_1_1 : ( ruleAttribute ) ;
    public final void rule__Atomic__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3715:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3716:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3716:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3717:1: ruleAttribute
            {
             before(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_1_17362);
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


    // $ANTLR start "rule__Activity__NameAssignment_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3726:1: rule__Activity__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Activity__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3730:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3731:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3731:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3732:1: RULE_ID
            {
             before(grammarAccess.getActivityAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Activity__NameAssignment_07393); 
             after(grammarAccess.getActivityAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__NameAssignment_0"


    // $ANTLR start "rule__Activity__DescriptionAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3741:1: rule__Activity__DescriptionAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Activity__DescriptionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3745:1: ( ( RULE_STRING ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3746:1: ( RULE_STRING )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3746:1: ( RULE_STRING )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3747:1: RULE_STRING
            {
             before(grammarAccess.getActivityAccess().getDescriptionSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Activity__DescriptionAssignment_27424); 
             after(grammarAccess.getActivityAccess().getDescriptionSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__DescriptionAssignment_2"


    // $ANTLR start "rule__Activity__PreAssignment_5_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3756:1: rule__Activity__PreAssignment_5_0 : ( ( rule__Activity__PreAlternatives_5_0_0 ) ) ;
    public final void rule__Activity__PreAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3760:1: ( ( ( rule__Activity__PreAlternatives_5_0_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3761:1: ( ( rule__Activity__PreAlternatives_5_0_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3761:1: ( ( rule__Activity__PreAlternatives_5_0_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3762:1: ( rule__Activity__PreAlternatives_5_0_0 )
            {
             before(grammarAccess.getActivityAccess().getPreAlternatives_5_0_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3763:1: ( rule__Activity__PreAlternatives_5_0_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3763:2: rule__Activity__PreAlternatives_5_0_0
            {
            pushFollow(FOLLOW_rule__Activity__PreAlternatives_5_0_0_in_rule__Activity__PreAssignment_5_07455);
            rule__Activity__PreAlternatives_5_0_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPreAlternatives_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__PreAssignment_5_0"


    // $ANTLR start "rule__Activity__PreAssignment_5_1_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3772:1: rule__Activity__PreAssignment_5_1_1 : ( ( rule__Activity__PreAlternatives_5_1_1_0 ) ) ;
    public final void rule__Activity__PreAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3776:1: ( ( ( rule__Activity__PreAlternatives_5_1_1_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3777:1: ( ( rule__Activity__PreAlternatives_5_1_1_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3777:1: ( ( rule__Activity__PreAlternatives_5_1_1_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3778:1: ( rule__Activity__PreAlternatives_5_1_1_0 )
            {
             before(grammarAccess.getActivityAccess().getPreAlternatives_5_1_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3779:1: ( rule__Activity__PreAlternatives_5_1_1_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3779:2: rule__Activity__PreAlternatives_5_1_1_0
            {
            pushFollow(FOLLOW_rule__Activity__PreAlternatives_5_1_1_0_in_rule__Activity__PreAssignment_5_1_17488);
            rule__Activity__PreAlternatives_5_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPreAlternatives_5_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__PreAssignment_5_1_1"


    // $ANTLR start "rule__Activity__PostAssignment_10_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3788:1: rule__Activity__PostAssignment_10_0 : ( ( rule__Activity__PostAlternatives_10_0_0 ) ) ;
    public final void rule__Activity__PostAssignment_10_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3792:1: ( ( ( rule__Activity__PostAlternatives_10_0_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3793:1: ( ( rule__Activity__PostAlternatives_10_0_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3793:1: ( ( rule__Activity__PostAlternatives_10_0_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3794:1: ( rule__Activity__PostAlternatives_10_0_0 )
            {
             before(grammarAccess.getActivityAccess().getPostAlternatives_10_0_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3795:1: ( rule__Activity__PostAlternatives_10_0_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3795:2: rule__Activity__PostAlternatives_10_0_0
            {
            pushFollow(FOLLOW_rule__Activity__PostAlternatives_10_0_0_in_rule__Activity__PostAssignment_10_07521);
            rule__Activity__PostAlternatives_10_0_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPostAlternatives_10_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__PostAssignment_10_0"


    // $ANTLR start "rule__Activity__PostAssignment_10_1_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3804:1: rule__Activity__PostAssignment_10_1_1 : ( ( rule__Activity__PostAlternatives_10_1_1_0 ) ) ;
    public final void rule__Activity__PostAssignment_10_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3808:1: ( ( ( rule__Activity__PostAlternatives_10_1_1_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3809:1: ( ( rule__Activity__PostAlternatives_10_1_1_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3809:1: ( ( rule__Activity__PostAlternatives_10_1_1_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3810:1: ( rule__Activity__PostAlternatives_10_1_1_0 )
            {
             before(grammarAccess.getActivityAccess().getPostAlternatives_10_1_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3811:1: ( rule__Activity__PostAlternatives_10_1_1_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity.ui/src-gen/org/blended/activity/ui/contentassist/antlr/internal/InternalActivity.g:3811:2: rule__Activity__PostAlternatives_10_1_1_0
            {
            pushFollow(FOLLOW_rule__Activity__PostAlternatives_10_1_1_0_in_rule__Activity__PostAssignment_10_1_17554);
            rule__Activity__PostAlternatives_10_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getActivityAccess().getPostAlternatives_10_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Activity__PostAssignment_10_1_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleActivityModel_in_entryRuleActivityModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleActivityModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ActivityModel__ActivitiesAssignment_in_ruleActivityModel96 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__ActivityModel__ActivitiesAssignment_in_ruleActivityModel108 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition138 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveCondition145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__0_in_ruleEntityAchieveCondition171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityInvariantCondition207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__0_in_ruleEntityInvariantCondition233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition264 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0_in_ruleNotMandatoryAttributeAchieveCondition297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition324 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0_in_ruleMandatoryAttributeAchieveCondition357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition384 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__0_in_ruleAttributeInvariantCondition417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality446 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute506 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0_in_ruleAttribute539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression568 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr627 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0_in_ruleOr660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd687 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0_in_ruleAnd720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary747 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Alternatives_in_rulePrimary780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic807 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActivity_in_entryRuleActivity867 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleActivity874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__0_in_ruleActivity900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PreAlternatives_5_0_01142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PreAlternatives_5_0_01159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PreAlternatives_5_1_1_01191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PreAlternatives_5_1_1_01208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PostAlternatives_10_0_01240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PostAlternatives_10_0_01257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_rule__Activity__PostAlternatives_10_0_01274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_rule__Activity__PostAlternatives_10_0_01291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_rule__Activity__PostAlternatives_10_1_1_01323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_rule__Activity__PostAlternatives_10_1_1_01340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_rule__Activity__PostAlternatives_10_1_1_01357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_rule__Activity__PostAlternatives_10_1_1_01374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__0__Impl_in_rule__EntityAchieveCondition__Group__01404 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__1_in_rule__EntityAchieveCondition__Group__01407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__EntityAchieveCondition__Group__0__Impl1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__1__Impl_in_rule__EntityAchieveCondition__Group__11466 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__2_in_rule__EntityAchieveCondition__Group__11469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityAchieveCondition__Group__1__Impl1497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__2__Impl_in_rule__EntityAchieveCondition__Group__21528 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__3_in_rule__EntityAchieveCondition__Group__21531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__NameAssignment_2_in_rule__EntityAchieveCondition__Group__2__Impl1558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityAchieveCondition__Group__3__Impl_in_rule__EntityAchieveCondition__Group__31588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityAchieveCondition__Group__3__Impl1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__0__Impl_in_rule__EntityInvariantCondition__Group__01656 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__1_in_rule__EntityInvariantCondition__Group__01659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__EntityInvariantCondition__Group__0__Impl1687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__1__Impl_in_rule__EntityInvariantCondition__Group__11718 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__2_in_rule__EntityInvariantCondition__Group__11721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__EntityInvariantCondition__Group__1__Impl1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__2__Impl_in_rule__EntityInvariantCondition__Group__21780 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__3_in_rule__EntityInvariantCondition__Group__21783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__NameAssignment_2_in_rule__EntityInvariantCondition__Group__2__Impl1810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__3__Impl_in_rule__EntityInvariantCondition__Group__31840 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__4_in_rule__EntityInvariantCondition__Group__31843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__EntityInvariantCondition__Group__3__Impl1871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__4__Impl_in_rule__EntityInvariantCondition__Group__41902 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__5_in_rule__EntityInvariantCondition__Group__41905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__CardinalityAssignment_4_in_rule__EntityInvariantCondition__Group__4__Impl1932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__EntityInvariantCondition__Group__5__Impl_in_rule__EntityInvariantCondition__Group__51962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__EntityInvariantCondition__Group__5__Impl1990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__02034 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1_in_rule__NotMandatoryAttributeAchieveCondition__Group__02037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__NotMandatoryAttributeAchieveCondition__Group__0__Impl2065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__12096 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2_in_rule__NotMandatoryAttributeAchieveCondition__Group__12099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__NotMandatoryAttributeAchieveCondition__Group__1__Impl2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__22158 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3_in_rule__NotMandatoryAttributeAchieveCondition__Group__22161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_2_in_rule__NotMandatoryAttributeAchieveCondition__Group__2__Impl2188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__32218 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4_in_rule__NotMandatoryAttributeAchieveCondition__Group__32221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0_in_rule__NotMandatoryAttributeAchieveCondition__Group__3__Impl2248 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group__42279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__NotMandatoryAttributeAchieveCondition__Group__4__Impl2307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02348 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__02351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__0__Impl2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__12410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_1_in_rule__NotMandatoryAttributeAchieveCondition__Group_3__1__Impl2437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__02471 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1_in_rule__MandatoryAttributeAchieveCondition__Group__02474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__MandatoryAttributeAchieveCondition__Group__0__Impl2502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__12533 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2_in_rule__MandatoryAttributeAchieveCondition__Group__12536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__1__Impl2564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__2__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__22595 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3_in_rule__MandatoryAttributeAchieveCondition__Group__22598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__MandatoryAttributeAchieveCondition__Group__2__Impl2626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__3__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__32657 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4_in_rule__MandatoryAttributeAchieveCondition__Group__32660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__MandatoryAttributeAchieveCondition__Group__3__Impl2688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__4__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__42719 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5_in_rule__MandatoryAttributeAchieveCondition__Group__42722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_4_in_rule__MandatoryAttributeAchieveCondition__Group__4__Impl2749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__5__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__52779 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6_in_rule__MandatoryAttributeAchieveCondition__Group__52782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0_in_rule__MandatoryAttributeAchieveCondition__Group__5__Impl2809 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__6__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__62840 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7_in_rule__MandatoryAttributeAchieveCondition__Group__62843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__6__Impl2871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group__7__Impl_in_rule__MandatoryAttributeAchieveCondition__Group__72902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__MandatoryAttributeAchieveCondition__Group__7__Impl2930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__02977 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1_in_rule__MandatoryAttributeAchieveCondition__Group_5__02980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__MandatoryAttributeAchieveCondition__Group_5__0__Impl3008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl_in_rule__MandatoryAttributeAchieveCondition__Group_5__13039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_1_in_rule__MandatoryAttributeAchieveCondition__Group_5__1__Impl3066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__0__Impl_in_rule__AttributeInvariantCondition__Group__03100 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__1_in_rule__AttributeInvariantCondition__Group__03103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__AttributeInvariantCondition__Group__0__Impl3131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__1__Impl_in_rule__AttributeInvariantCondition__Group__13162 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__2_in_rule__AttributeInvariantCondition__Group__13165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeInvariantCondition__Group__1__Impl3193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__2__Impl_in_rule__AttributeInvariantCondition__Group__23224 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__3_in_rule__AttributeInvariantCondition__Group__23227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__ExpressionAssignment_2_in_rule__AttributeInvariantCondition__Group__2__Impl3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeInvariantCondition__Group__3__Impl_in_rule__AttributeInvariantCondition__Group__33284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeInvariantCondition__Group__3__Impl3312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__03353 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__03356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl3383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__13412 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__13415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Cardinality__Group_1__1__Impl3443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__23474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__03536 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__03539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl3566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__13595 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__13598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Cardinality__Group_2__1__Impl3626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__23657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Cardinality__Group_2__2__Impl3685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__03722 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__03725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl3752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__13781 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__13784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Cardinality__Group_3__1__Impl3812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__23843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Cardinality__Group_3__2__Impl3871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__03908 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__03911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group__0__Impl3938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__13967 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__13970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Attribute__Group__1__Impl3998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__24029 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__24032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group__2__Impl4059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__34088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__0_in_rule__Attribute__Group__3__Impl4115 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__0__Impl_in_rule__Attribute__Group_3__04154 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__1_in_rule__Attribute__Group_3__04157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Attribute__Group_3__0__Impl4185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_3__1__Impl_in_rule__Attribute__Group_3__14216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__Group_3__1__Impl4243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__04276 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Or__Group__1_in_rule__Or__Group__04279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl4306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__14335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl4362 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__04397 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__04400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__14458 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__14461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Or__Group_1__1__Impl4489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__24520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl4547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__04583 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__And__Group__1_in_rule__And__Group__04586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__Group__0__Impl4613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__14642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl4669 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__04704 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__04707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__14765 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__14768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__And__Group_1__1__Impl4796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__24827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl4854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__04890 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__04893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Primary__Group_0__0__Impl4921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__14952 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__14955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl4982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__25011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Primary__Group_0__2__Impl5039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__05076 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__05079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__15137 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__15140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Primary__Group_1__1__Impl5168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__25199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl5226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__05262 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__05265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__15323 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__15326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Atomic__Group_0__1__Impl5354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__25385 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__25388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Atomic__Group_0__2__Impl5416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__35447 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__35450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl5477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__45507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Atomic__Group_0__4__Impl5535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__05576 = new BitSet(new long[]{0x0000000002011020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__05579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__15637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl5664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__0__Impl_in_rule__Activity__Group__05698 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__Activity__Group__1_in_rule__Activity__Group__05701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__NameAssignment_0_in_rule__Activity__Group__0__Impl5728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__1__Impl_in_rule__Activity__Group__15758 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__Activity__Group__2_in_rule__Activity__Group__15761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Activity__Group__1__Impl5789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__2__Impl_in_rule__Activity__Group__25820 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Activity__Group__3_in_rule__Activity__Group__25823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__DescriptionAssignment_2_in_rule__Activity__Group__2__Impl5850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__3__Impl_in_rule__Activity__Group__35880 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Activity__Group__4_in_rule__Activity__Group__35883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Activity__Group__3__Impl5911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__4__Impl_in_rule__Activity__Group__45942 = new BitSet(new long[]{0x0000000000012800L});
    public static final BitSet FOLLOW_rule__Activity__Group__5_in_rule__Activity__Group__45945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Activity__Group__4__Impl5973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__5__Impl_in_rule__Activity__Group__56004 = new BitSet(new long[]{0x0000000000012800L});
    public static final BitSet FOLLOW_rule__Activity__Group__6_in_rule__Activity__Group__56007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_5__0_in_rule__Activity__Group__5__Impl6034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__6__Impl_in_rule__Activity__Group__66065 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Activity__Group__7_in_rule__Activity__Group__66068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Activity__Group__6__Impl6096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__7__Impl_in_rule__Activity__Group__76127 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Activity__Group__8_in_rule__Activity__Group__76130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Activity__Group__7__Impl6158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__8__Impl_in_rule__Activity__Group__86189 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Activity__Group__9_in_rule__Activity__Group__86192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Activity__Group__8__Impl6220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__9__Impl_in_rule__Activity__Group__96251 = new BitSet(new long[]{0x0000000000056800L});
    public static final BitSet FOLLOW_rule__Activity__Group__10_in_rule__Activity__Group__96254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Activity__Group__9__Impl6282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__10__Impl_in_rule__Activity__Group__106313 = new BitSet(new long[]{0x0000000000056800L});
    public static final BitSet FOLLOW_rule__Activity__Group__11_in_rule__Activity__Group__106316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_10__0_in_rule__Activity__Group__10__Impl6343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group__11__Impl_in_rule__Activity__Group__116374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Activity__Group__11__Impl6402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_5__0__Impl_in_rule__Activity__Group_5__06457 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Activity__Group_5__1_in_rule__Activity__Group_5__06460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PreAssignment_5_0_in_rule__Activity__Group_5__0__Impl6487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_5__1__Impl_in_rule__Activity__Group_5__16517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_5_1__0_in_rule__Activity__Group_5__1__Impl6544 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Activity__Group_5_1__0__Impl_in_rule__Activity__Group_5_1__06579 = new BitSet(new long[]{0x0000000000010800L});
    public static final BitSet FOLLOW_rule__Activity__Group_5_1__1_in_rule__Activity__Group_5_1__06582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Activity__Group_5_1__0__Impl6610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_5_1__1__Impl_in_rule__Activity__Group_5_1__16641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PreAssignment_5_1_1_in_rule__Activity__Group_5_1__1__Impl6668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_10__0__Impl_in_rule__Activity__Group_10__06702 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Activity__Group_10__1_in_rule__Activity__Group_10__06705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PostAssignment_10_0_in_rule__Activity__Group_10__0__Impl6732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_10__1__Impl_in_rule__Activity__Group_10__16762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_10_1__0_in_rule__Activity__Group_10__1__Impl6789 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Activity__Group_10_1__0__Impl_in_rule__Activity__Group_10_1__06824 = new BitSet(new long[]{0x0000000000054800L});
    public static final BitSet FOLLOW_rule__Activity__Group_10_1__1_in_rule__Activity__Group_10_1__06827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Activity__Group_10_1__0__Impl6855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__Group_10_1__1__Impl_in_rule__Activity__Group_10_1__16886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PostAssignment_10_1_1_in_rule__Activity__Group_10_1__1__Impl6913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActivity_in_rule__ActivityModel__ActivitiesAssignment6952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__EntityAchieveCondition__NameAssignment_26983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__EntityInvariantCondition__NameAssignment_27015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_rule__EntityInvariantCondition__CardinalityAssignment_47046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_27079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__NotMandatoryAttributeAchieveCondition__ConditionsAssignment_3_17110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_47141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__MandatoryAttributeAchieveCondition__ConditionsAssignment_5_17172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__AttributeInvariantCondition__ExpressionAssignment_27203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_27238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_27269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_27300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_0_37331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Atomic__NameAssignment_1_17362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Activity__NameAssignment_07393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Activity__DescriptionAssignment_27424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PreAlternatives_5_0_0_in_rule__Activity__PreAssignment_5_07455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PreAlternatives_5_1_1_0_in_rule__Activity__PreAssignment_5_1_17488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PostAlternatives_10_0_0_in_rule__Activity__PostAssignment_10_07521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Activity__PostAlternatives_10_1_1_0_in_rule__Activity__PostAssignment_10_1_17554 = new BitSet(new long[]{0x0000000000000002L});

}