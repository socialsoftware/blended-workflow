package org.blended.activity.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.blended.activity.services.ActivityGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalActivityParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEFF'", "'('", "')'", "'MUL'", "','", "'DEF'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'OR'", "'AND'", "'NOT'", "':'", "'PRE'", "'POST'"
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
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
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
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g"; }



     	private ActivityGrammarAccess grammarAccess;
     	
        public InternalActivityParser(TokenStream input, ActivityGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "ActivityModel";	
       	}
       	
       	@Override
       	protected ActivityGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleActivityModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:67:1: entryRuleActivityModel returns [EObject current=null] : iv_ruleActivityModel= ruleActivityModel EOF ;
    public final EObject entryRuleActivityModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActivityModel = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:68:2: (iv_ruleActivityModel= ruleActivityModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:69:2: iv_ruleActivityModel= ruleActivityModel EOF
            {
             newCompositeNode(grammarAccess.getActivityModelRule()); 
            pushFollow(FOLLOW_ruleActivityModel_in_entryRuleActivityModel75);
            iv_ruleActivityModel=ruleActivityModel();

            state._fsp--;

             current =iv_ruleActivityModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleActivityModel85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleActivityModel"


    // $ANTLR start "ruleActivityModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:76:1: ruleActivityModel returns [EObject current=null] : ( (lv_activities_0_0= ruleActivity ) )+ ;
    public final EObject ruleActivityModel() throws RecognitionException {
        EObject current = null;

        EObject lv_activities_0_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:79:28: ( ( (lv_activities_0_0= ruleActivity ) )+ )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:80:1: ( (lv_activities_0_0= ruleActivity ) )+
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:80:1: ( (lv_activities_0_0= ruleActivity ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:81:1: (lv_activities_0_0= ruleActivity )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:81:1: (lv_activities_0_0= ruleActivity )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:82:3: lv_activities_0_0= ruleActivity
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getActivityModelAccess().getActivitiesActivityParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleActivity_in_ruleActivityModel130);
            	    lv_activities_0_0=ruleActivity();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getActivityModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"activities",
            	            		lv_activities_0_0, 
            	            		"Activity");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleActivityModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:106:1: entryRuleEntityAchieveCondition returns [EObject current=null] : iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF ;
    public final EObject entryRuleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:107:2: (iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:108:2: iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition166);
            iv_ruleEntityAchieveCondition=ruleEntityAchieveCondition();

            state._fsp--;

             current =iv_ruleEntityAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveCondition176); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntityAchieveCondition"


    // $ANTLR start "ruleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:115:1: ruleEntityAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:118:28: ( (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:119:1: (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:119:1: (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:119:3: otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleEntityAchieveCondition213); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAchieveConditionAccess().getDEFFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveCondition225); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:127:1: ( (lv_name_2_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:128:1: (lv_name_2_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:128:1: (lv_name_2_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:129:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityAchieveCondition242); 

            			newLeafNode(lv_name_2_0, grammarAccess.getEntityAchieveConditionAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getEntityAchieveConditionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveCondition259); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityAchieveConditionAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntityAchieveCondition"


    // $ANTLR start "entryRuleEntityInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:159:1: entryRuleEntityInvariantCondition returns [EObject current=null] : iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF ;
    public final EObject entryRuleEntityInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityInvariantCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:160:2: (iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:161:2: iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition297);
            iv_ruleEntityInvariantCondition=ruleEntityInvariantCondition();

            state._fsp--;

             current =iv_ruleEntityInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityInvariantCondition307); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntityInvariantCondition"


    // $ANTLR start "ruleEntityInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:168:1: ruleEntityInvariantCondition returns [EObject current=null] : (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) ;
    public final EObject ruleEntityInvariantCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_cardinality_4_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:171:28: ( (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:172:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:172:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:172:3: otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleEntityInvariantCondition344); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityInvariantCondition356); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:180:1: ( (lv_name_2_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:181:1: (lv_name_2_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:181:1: (lv_name_2_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:182:3: lv_name_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleEntityInvariantCondition377);
            lv_name_2_0=ruleAttribute();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEntityInvariantConditionRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"Attribute");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_15_in_ruleEntityInvariantCondition389); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:202:1: ( (lv_cardinality_4_0= ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:203:1: (lv_cardinality_4_0= ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:203:1: (lv_cardinality_4_0= ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:204:3: lv_cardinality_4_0= ruleCardinality
            {
             
            	        newCompositeNode(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleCardinality_in_ruleEntityInvariantCondition410);
            lv_cardinality_4_0=ruleCardinality();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEntityInvariantConditionRule());
            	        }
                   		set(
                   			current, 
                   			"cardinality",
                    		lv_cardinality_4_0, 
                    		"Cardinality");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityInvariantCondition422); 

                	newLeafNode(otherlv_5, grammarAccess.getEntityInvariantConditionAccess().getRightParenthesisKeyword_5());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntityInvariantCondition"


    // $ANTLR start "entryRuleNotMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:236:1: entryRuleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNotMandatoryAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:237:2: (iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:238:2: iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition462);
            iv_ruleNotMandatoryAttributeAchieveCondition=ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleNotMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition472); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNotMandatoryAttributeAchieveCondition"


    // $ANTLR start "ruleNotMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:245:1: ruleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) ;
    public final EObject ruleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_conditions_2_0 = null;

        AntlrDatatypeRuleToken lv_conditions_4_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:248:28: ( (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:249:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:249:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:249:3: otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition509); 

                	newLeafNode(otherlv_0, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition521); 

                	newLeafNode(otherlv_1, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:257:1: ( (lv_conditions_2_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:258:1: (lv_conditions_2_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:258:1: (lv_conditions_2_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:259:3: lv_conditions_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition542);
            lv_conditions_2_0=ruleAttribute();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNotMandatoryAttributeAchieveConditionRule());
            	        }
                   		add(
                   			current, 
                   			"conditions",
                    		lv_conditions_2_0, 
                    		"Attribute");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:275:2: (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==15) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:275:4: otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) )
            	    {
            	    otherlv_3=(Token)match(input,15,FOLLOW_15_in_ruleNotMandatoryAttributeAchieveCondition555); 

            	        	newLeafNode(otherlv_3, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:279:1: ( (lv_conditions_4_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:280:1: (lv_conditions_4_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:280:1: (lv_conditions_4_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:281:3: lv_conditions_4_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition576);
            	    lv_conditions_4_0=ruleAttribute();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getNotMandatoryAttributeAchieveConditionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"conditions",
            	            		lv_conditions_4_0, 
            	            		"Attribute");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition590); 

                	newLeafNode(otherlv_5, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNotMandatoryAttributeAchieveCondition"


    // $ANTLR start "entryRuleMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:309:1: entryRuleMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatoryAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:310:2: (iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:311:2: iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition626);
            iv_ruleMandatoryAttributeAchieveCondition=ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition636); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMandatoryAttributeAchieveCondition"


    // $ANTLR start "ruleMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:318:1: ruleMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) ;
    public final EObject ruleMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_conditions_4_0 = null;

        AntlrDatatypeRuleToken lv_conditions_6_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:321:28: ( (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:322:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:322:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:322:3: otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleMandatoryAttributeAchieveCondition673); 

                	newLeafNode(otherlv_0, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition685); 

                	newLeafNode(otherlv_1, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition697); 

                	newLeafNode(otherlv_2, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition709); 

                	newLeafNode(otherlv_3, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:338:1: ( (lv_conditions_4_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:339:1: (lv_conditions_4_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:339:1: (lv_conditions_4_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:340:3: lv_conditions_4_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition730);
            lv_conditions_4_0=ruleAttribute();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMandatoryAttributeAchieveConditionRule());
            	        }
                   		add(
                   			current, 
                   			"conditions",
                    		lv_conditions_4_0, 
                    		"Attribute");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:356:2: (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==15) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:356:4: otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) )
            	    {
            	    otherlv_5=(Token)match(input,15,FOLLOW_15_in_ruleMandatoryAttributeAchieveCondition743); 

            	        	newLeafNode(otherlv_5, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:360:1: ( (lv_conditions_6_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:361:1: (lv_conditions_6_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:361:1: (lv_conditions_6_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:362:3: lv_conditions_6_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition764);
            	    lv_conditions_6_0=ruleAttribute();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getMandatoryAttributeAchieveConditionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"conditions",
            	            		lv_conditions_6_0, 
            	            		"Attribute");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_7=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition778); 

                	newLeafNode(otherlv_7, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6());
                
            otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition790); 

                	newLeafNode(otherlv_8, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMandatoryAttributeAchieveCondition"


    // $ANTLR start "entryRuleAttributeInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:394:1: entryRuleAttributeInvariantCondition returns [EObject current=null] : iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF ;
    public final EObject entryRuleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeInvariantCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:395:2: (iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:396:2: iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition826);
            iv_ruleAttributeInvariantCondition=ruleAttributeInvariantCondition();

            state._fsp--;

             current =iv_ruleAttributeInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition836); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttributeInvariantCondition"


    // $ANTLR start "ruleAttributeInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:403:1: ruleAttributeInvariantCondition returns [EObject current=null] : (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) ;
    public final EObject ruleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:406:28: ( (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:407:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:407:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:407:3: otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleAttributeInvariantCondition873); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAttributeInvariantCondition885); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:415:1: ( (lv_expression_2_0= ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:416:1: (lv_expression_2_0= ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:416:1: (lv_expression_2_0= ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:417:3: lv_expression_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition906);
            lv_expression_2_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeInvariantConditionRule());
            	        }
                   		set(
                   			current, 
                   			"expression",
                    		lv_expression_2_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAttributeInvariantCondition918); 

                	newLeafNode(otherlv_3, grammarAccess.getAttributeInvariantConditionAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttributeInvariantCondition"


    // $ANTLR start "entryRuleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:447:1: entryRuleCardinality returns [String current=null] : iv_ruleCardinality= ruleCardinality EOF ;
    public final String entryRuleCardinality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCardinality = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:448:2: (iv_ruleCardinality= ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:449:2: iv_ruleCardinality= ruleCardinality EOF
            {
             newCompositeNode(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality957);
            iv_ruleCardinality=ruleCardinality();

            state._fsp--;

             current =iv_ruleCardinality.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality968); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCardinality"


    // $ANTLR start "ruleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:456:1: ruleCardinality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) ;
    public final AntlrDatatypeRuleToken ruleCardinality() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token this_INT_1=null;
        Token kw=null;
        Token this_INT_3=null;
        Token this_INT_4=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:459:28: ( (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:460:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:460:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            int alt4=4;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_INT) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==19) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt4=2;
                        }
                        break;
                    case 20:
                        {
                        alt4=3;
                        }
                        break;
                    case 21:
                        {
                        alt4=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 2, input);

                        throw nvae;
                    }

                }
                else if ( (LA4_1==EOF||LA4_1==13) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:460:6: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1008); 

                    		current.merge(this_INT_0);
                        
                     
                        newLeafNode(this_INT_0, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:468:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:468:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:468:11: this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1035); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
                        
                    kw=(Token)match(input,19,FOLLOW_19_in_ruleCardinality1053); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1068); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:489:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:489:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:489:11: this_INT_4= RULE_INT kw= '..' kw= '*'
                    {
                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1096); 

                    		current.merge(this_INT_4);
                        
                     
                        newLeafNode(this_INT_4, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
                        
                    kw=(Token)match(input,19,FOLLOW_19_in_ruleCardinality1114); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1127); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:509:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:509:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:509:11: this_INT_7= RULE_INT kw= '..' kw= '+'
                    {
                    this_INT_7=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1150); 

                    		current.merge(this_INT_7);
                        
                     
                        newLeafNode(this_INT_7, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
                        
                    kw=(Token)match(input,19,FOLLOW_19_in_ruleCardinality1168); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
                        
                    kw=(Token)match(input,21,FOLLOW_21_in_ruleCardinality1181); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getPlusSignKeyword_3_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCardinality"


    // $ANTLR start "entryRuleAttribute"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:536:1: entryRuleAttribute returns [String current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final String entryRuleAttribute() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAttribute = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:537:2: (iv_ruleAttribute= ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:538:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute1223);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute1234); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:545:1: ruleAttribute returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleAttribute() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:548:28: ( (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:549:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:549:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:549:6: this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute1274); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,22,FOLLOW_22_in_ruleAttribute1292); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
                
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute1307); 

            		current.merge(this_ID_2);
                
             
                newLeafNode(this_ID_2, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:569:1: (kw= '.' this_ID_4= RULE_ID )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==22) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:570:2: kw= '.' this_ID_4= RULE_ID
            	    {
            	    kw=(Token)match(input,22,FOLLOW_22_in_ruleAttribute1326); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            	        
            	    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute1341); 

            	    		current.merge(this_ID_4);
            	        
            	     
            	        newLeafNode(this_ID_4, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:592:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:593:2: (iv_ruleExpression= ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:594:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1390);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1400); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:601:1: ruleExpression returns [EObject current=null] : this_Or_0= ruleOr ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Or_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:604:28: (this_Or_0= ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:606:5: this_Or_0= ruleOr
            {
             
                    newCompositeNode(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleOr_in_ruleExpression1446);
            this_Or_0=ruleOr();

            state._fsp--;

             
                    current = this_Or_0; 
                    afterParserOrEnumRuleCall();
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleOr"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:622:1: entryRuleOr returns [EObject current=null] : iv_ruleOr= ruleOr EOF ;
    public final EObject entryRuleOr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:623:2: (iv_ruleOr= ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:624:2: iv_ruleOr= ruleOr EOF
            {
             newCompositeNode(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr1480);
            iv_ruleOr=ruleOr();

            state._fsp--;

             current =iv_ruleOr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr1490); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOr"


    // $ANTLR start "ruleOr"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:631:1: ruleOr returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleOr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:634:28: ( (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:635:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:635:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:636:5: this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnd_in_ruleOr1537);
            this_And_0=ruleAnd();

            state._fsp--;

             
                    current = this_And_0; 
                    afterParserOrEnumRuleCall();
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:644:1: ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==23) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:644:2: () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:644:2: ()
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:645:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getOrAccess().getOrLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,23,FOLLOW_23_in_ruleOr1558); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOrAccess().getORKeyword_1_1());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:654:1: ( (lv_right_3_0= ruleAnd ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:655:1: (lv_right_3_0= ruleAnd )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:655:1: (lv_right_3_0= ruleAnd )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:656:3: lv_right_3_0= ruleAnd
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnd_in_ruleOr1579);
            	    lv_right_3_0=ruleAnd();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getOrRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"right",
            	            		lv_right_3_0, 
            	            		"And");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOr"


    // $ANTLR start "entryRuleAnd"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:680:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:681:2: (iv_ruleAnd= ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:682:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd1617);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd1627); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnd"


    // $ANTLR start "ruleAnd"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:689:1: ruleAnd returns [EObject current=null] : (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Primary_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:692:28: ( (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:693:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:693:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:694:5: this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePrimary_in_ruleAnd1674);
            this_Primary_0=rulePrimary();

            state._fsp--;

             
                    current = this_Primary_0; 
                    afterParserOrEnumRuleCall();
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:702:1: ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==24) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:702:2: () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:702:2: ()
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:703:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleAnd1695); 

            	        	newLeafNode(otherlv_2, grammarAccess.getAndAccess().getANDKeyword_1_1());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:712:1: ( (lv_right_3_0= rulePrimary ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:713:1: (lv_right_3_0= rulePrimary )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:713:1: (lv_right_3_0= rulePrimary )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:714:3: lv_right_3_0= rulePrimary
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePrimary_in_ruleAnd1716);
            	    lv_right_3_0=rulePrimary();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAndRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"right",
            	            		lv_right_3_0, 
            	            		"Primary");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnd"


    // $ANTLR start "entryRulePrimary"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:738:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:739:2: (iv_rulePrimary= rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:740:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary1754);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary1764); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:747:1: rulePrimary returns [EObject current=null] : ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Expression_1 = null;

        EObject lv_expression_5_0 = null;

        EObject this_Atomic_6 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:750:28: ( ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:751:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:751:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt8=1;
                }
                break;
            case 25:
                {
                alt8=2;
                }
                break;
            case RULE_ID:
            case 16:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:751:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:751:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:751:4: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_12_in_rulePrimary1802); 

                        	newLeafNode(otherlv_0, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0());
                        
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleExpression_in_rulePrimary1824);
                    this_Expression_1=ruleExpression();

                    state._fsp--;

                     
                            current = this_Expression_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_rulePrimary1835); 

                        	newLeafNode(otherlv_2, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:769:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:769:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:769:7: () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:769:7: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:770:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getPrimaryAccess().getNotAction_1_0(),
                                current);
                        

                    }

                    otherlv_4=(Token)match(input,25,FOLLOW_25_in_rulePrimary1864); 

                        	newLeafNode(otherlv_4, grammarAccess.getPrimaryAccess().getNOTKeyword_1_1());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:779:1: ( (lv_expression_5_0= rulePrimary ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:780:1: (lv_expression_5_0= rulePrimary )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:780:1: (lv_expression_5_0= rulePrimary )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:781:3: lv_expression_5_0= rulePrimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePrimary_in_rulePrimary1885);
                    lv_expression_5_0=rulePrimary();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    	        }
                           		set(
                           			current, 
                           			"expression",
                            		lv_expression_5_0, 
                            		"Primary");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:799:5: this_Atomic_6= ruleAtomic
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleAtomic_in_rulePrimary1914);
                    this_Atomic_6=ruleAtomic();

                    state._fsp--;

                     
                            current = this_Atomic_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "entryRuleAtomic"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:815:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:816:2: (iv_ruleAtomic= ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:817:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic1949);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic1959); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtomic"


    // $ANTLR start "ruleAtomic"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:824:1: ruleAtomic returns [EObject current=null] : ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:827:28: ( ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:828:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:828:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==16) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_ID) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:828:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:828:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:828:3: () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')'
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:828:3: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:829:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleAtomic2006); 

                        	newLeafNode(otherlv_1, grammarAccess.getAtomicAccess().getDEFKeyword_0_1());
                        
                    otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleAtomic2018); 

                        	newLeafNode(otherlv_2, grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:842:1: ( (lv_name_3_0= ruleAttribute ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:843:1: (lv_name_3_0= ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:843:1: (lv_name_3_0= ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:844:3: lv_name_3_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2039);
                    lv_name_3_0=ruleAttribute();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAtomicRule());
                    	        }
                           		set(
                           			current, 
                           			"name",
                            		lv_name_3_0, 
                            		"Attribute");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleAtomic2051); 

                        	newLeafNode(otherlv_4, grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4());
                        

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:865:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:865:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:865:7: () ( (lv_name_6_0= ruleAttribute ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:865:7: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:866:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeValueAction_1_0(),
                                current);
                        

                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:871:2: ( (lv_name_6_0= ruleAttribute ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:872:1: (lv_name_6_0= ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:872:1: (lv_name_6_0= ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:873:3: lv_name_6_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2089);
                    lv_name_6_0=ruleAttribute();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAtomicRule());
                    	        }
                           		set(
                           			current, 
                           			"name",
                            		lv_name_6_0, 
                            		"Attribute");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtomic"


    // $ANTLR start "entryRuleActivity"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:897:1: entryRuleActivity returns [EObject current=null] : iv_ruleActivity= ruleActivity EOF ;
    public final EObject entryRuleActivity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActivity = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:898:2: (iv_ruleActivity= ruleActivity EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:899:2: iv_ruleActivity= ruleActivity EOF
            {
             newCompositeNode(grammarAccess.getActivityRule()); 
            pushFollow(FOLLOW_ruleActivity_in_entryRuleActivity2126);
            iv_ruleActivity=ruleActivity();

            state._fsp--;

             current =iv_ruleActivity; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleActivity2136); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleActivity"


    // $ANTLR start "ruleActivity"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:906:1: ruleActivity returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' ) ;
    public final EObject ruleActivity() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_description_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        EObject lv_pre_5_1 = null;

        EObject lv_pre_5_2 = null;

        EObject lv_pre_7_1 = null;

        EObject lv_pre_7_2 = null;

        EObject lv_post_12_1 = null;

        EObject lv_post_12_2 = null;

        EObject lv_post_12_3 = null;

        EObject lv_post_12_4 = null;

        EObject lv_post_14_1 = null;

        EObject lv_post_14_2 = null;

        EObject lv_post_14_3 = null;

        EObject lv_post_14_4 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:909:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:910:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:910:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:910:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')'
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:910:2: ( (lv_name_0_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:911:1: (lv_name_0_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:911:1: (lv_name_0_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:912:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleActivity2178); 

            			newLeafNode(lv_name_0_0, grammarAccess.getActivityAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getActivityRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleActivity2195); 

                	newLeafNode(otherlv_1, grammarAccess.getActivityAccess().getColonKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:932:1: ( (lv_description_2_0= RULE_STRING ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:933:1: (lv_description_2_0= RULE_STRING )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:933:1: (lv_description_2_0= RULE_STRING )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:934:3: lv_description_2_0= RULE_STRING
            {
            lv_description_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleActivity2212); 

            			newLeafNode(lv_description_2_0, grammarAccess.getActivityAccess().getDescriptionSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getActivityRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"description",
                    		lv_description_2_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleActivity2229); 

                	newLeafNode(otherlv_3, grammarAccess.getActivityAccess().getPREKeyword_3());
                
            otherlv_4=(Token)match(input,12,FOLLOW_12_in_ruleActivity2241); 

                	newLeafNode(otherlv_4, grammarAccess.getActivityAccess().getLeftParenthesisKeyword_4());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:958:1: ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==11||LA13_0==16) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:958:2: ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )*
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:958:2: ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:959:1: ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:959:1: ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:960:1: (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:960:1: (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition )
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==11) ) {
                        alt10=1;
                    }
                    else if ( (LA10_0==16) ) {
                        alt10=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 0, input);

                        throw nvae;
                    }
                    switch (alt10) {
                        case 1 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:961:3: lv_pre_5_1= ruleEntityAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2265);
                            lv_pre_5_1=ruleEntityAchieveCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"pre",
                                    		lv_pre_5_1, 
                                    		"EntityAchieveCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:976:8: lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2284);
                            lv_pre_5_2=ruleNotMandatoryAttributeAchieveCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"pre",
                                    		lv_pre_5_2, 
                                    		"NotMandatoryAttributeAchieveCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:994:2: (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==15) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:994:4: otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FOLLOW_15_in_ruleActivity2300); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getActivityAccess().getCommaKeyword_5_1_0());
                    	        
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:998:1: ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:999:1: ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:999:1: ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1000:1: (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1000:1: (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition )
                    	    int alt11=2;
                    	    int LA11_0 = input.LA(1);

                    	    if ( (LA11_0==11) ) {
                    	        alt11=1;
                    	    }
                    	    else if ( (LA11_0==16) ) {
                    	        alt11=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 11, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt11) {
                    	        case 1 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1001:3: lv_pre_7_1= ruleEntityAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_1_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2323);
                    	            lv_pre_7_1=ruleEntityAchieveCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"pre",
                    	                    		lv_pre_7_1, 
                    	                    		"EntityAchieveCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1016:8: lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_1_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2342);
                    	            lv_pre_7_2=ruleNotMandatoryAttributeAchieveCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"pre",
                    	                    		lv_pre_7_2, 
                    	                    		"NotMandatoryAttributeAchieveCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;

                    	    }


                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleActivity2361); 

                	newLeafNode(otherlv_8, grammarAccess.getActivityAccess().getRightParenthesisKeyword_6());
                
            otherlv_9=(Token)match(input,15,FOLLOW_15_in_ruleActivity2373); 

                	newLeafNode(otherlv_9, grammarAccess.getActivityAccess().getCommaKeyword_7());
                
            otherlv_10=(Token)match(input,28,FOLLOW_28_in_ruleActivity2385); 

                	newLeafNode(otherlv_10, grammarAccess.getActivityAccess().getPOSTKeyword_8());
                
            otherlv_11=(Token)match(input,12,FOLLOW_12_in_ruleActivity2397); 

                	newLeafNode(otherlv_11, grammarAccess.getActivityAccess().getLeftParenthesisKeyword_9());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1050:1: ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==11||LA17_0==14||LA17_0==16||LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1050:2: ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )*
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1050:2: ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1051:1: ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1051:1: ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1052:1: (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1052:1: (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition )
                    int alt14=4;
                    switch ( input.LA(1) ) {
                    case 11:
                        {
                        alt14=1;
                        }
                        break;
                    case 16:
                        {
                        alt14=2;
                        }
                        break;
                    case 14:
                        {
                        alt14=3;
                        }
                        break;
                    case 18:
                        {
                        alt14=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;
                    }

                    switch (alt14) {
                        case 1 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1053:3: lv_post_12_1= ruleEntityAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2421);
                            lv_post_12_1=ruleEntityAchieveCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"post",
                                    		lv_post_12_1, 
                                    		"EntityAchieveCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1068:8: lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2440);
                            lv_post_12_2=ruleNotMandatoryAttributeAchieveCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"post",
                                    		lv_post_12_2, 
                                    		"NotMandatoryAttributeAchieveCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 3 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1083:8: lv_post_12_3= ruleEntityInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleActivity2459);
                            lv_post_12_3=ruleEntityInvariantCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"post",
                                    		lv_post_12_3, 
                                    		"EntityInvariantCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 4 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1098:8: lv_post_12_4= ruleAttributeInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity2478);
                            lv_post_12_4=ruleAttributeInvariantCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"post",
                                    		lv_post_12_4, 
                                    		"AttributeInvariantCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1116:2: (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==15) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1116:4: otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) )
                    	    {
                    	    otherlv_13=(Token)match(input,15,FOLLOW_15_in_ruleActivity2494); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getActivityAccess().getCommaKeyword_10_1_0());
                    	        
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1120:1: ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1121:1: ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1121:1: ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1122:1: (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1122:1: (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition )
                    	    int alt15=4;
                    	    switch ( input.LA(1) ) {
                    	    case 11:
                    	        {
                    	        alt15=1;
                    	        }
                    	        break;
                    	    case 16:
                    	        {
                    	        alt15=2;
                    	        }
                    	        break;
                    	    case 14:
                    	        {
                    	        alt15=3;
                    	        }
                    	        break;
                    	    case 18:
                    	        {
                    	        alt15=4;
                    	        }
                    	        break;
                    	    default:
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 15, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt15) {
                    	        case 1 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1123:3: lv_post_14_1= ruleEntityAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_1_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2517);
                    	            lv_post_14_1=ruleEntityAchieveCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"post",
                    	                    		lv_post_14_1, 
                    	                    		"EntityAchieveCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1138:8: lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_1_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2536);
                    	            lv_post_14_2=ruleNotMandatoryAttributeAchieveCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"post",
                    	                    		lv_post_14_2, 
                    	                    		"NotMandatoryAttributeAchieveCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;
                    	        case 3 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1153:8: lv_post_14_3= ruleEntityInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_1_1_0_2()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleActivity2555);
                    	            lv_post_14_3=ruleEntityInvariantCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"post",
                    	                    		lv_post_14_3, 
                    	                    		"EntityInvariantCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;
                    	        case 4 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.activity/src-gen/org/blended/activity/parser/antlr/internal/InternalActivity.g:1168:8: lv_post_14_4= ruleAttributeInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_1_1_0_3()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity2574);
                    	            lv_post_14_4=ruleAttributeInvariantCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getActivityRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"post",
                    	                    		lv_post_14_4, 
                    	                    		"AttributeInvariantCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;

                    	    }


                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_15=(Token)match(input,13,FOLLOW_13_in_ruleActivity2593); 

                	newLeafNode(otherlv_15, grammarAccess.getActivityAccess().getRightParenthesisKeyword_11());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleActivity"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleActivityModel_in_entryRuleActivityModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleActivityModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActivity_in_ruleActivityModel130 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveCondition176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleEntityAchieveCondition213 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveCondition225 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityAchieveCondition242 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveCondition259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition297 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityInvariantCondition307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleEntityInvariantCondition344 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityInvariantCondition356 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleEntityInvariantCondition377 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleEntityInvariantCondition389 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleCardinality_in_ruleEntityInvariantCondition410 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityInvariantCondition422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition509 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition521 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition542 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleNotMandatoryAttributeAchieveCondition555 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition576 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition626 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleMandatoryAttributeAchieveCondition673 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition685 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition697 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition709 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition730 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleMandatoryAttributeAchieveCondition743 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition764 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition778 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition826 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleAttributeInvariantCondition873 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeInvariantCondition885 = new BitSet(new long[]{0x0000000002011010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition906 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeInvariantCondition918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality957 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1035 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCardinality1053 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1096 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCardinality1114 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1150 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCardinality1168 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleCardinality1181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute1223 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute1274 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleAttribute1292 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute1307 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_ruleAttribute1326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute1341 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1390 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr1480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr1537 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_ruleOr1558 = new BitSet(new long[]{0x0000000002011010L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr1579 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd1617 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd1674 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleAnd1695 = new BitSet(new long[]{0x0000000002011010L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd1716 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary1754 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary1764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rulePrimary1802 = new BitSet(new long[]{0x0000000002011010L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePrimary1824 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePrimary1835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rulePrimary1864 = new BitSet(new long[]{0x0000000002011010L});
    public static final BitSet FOLLOW_rulePrimary_in_rulePrimary1885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rulePrimary1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic1949 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic1959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleAtomic2006 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAtomic2018 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2039 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAtomic2051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActivity_in_entryRuleActivity2126 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleActivity2136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleActivity2178 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleActivity2195 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleActivity2212 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleActivity2229 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleActivity2241 = new BitSet(new long[]{0x0000000000012800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2265 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2284 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleActivity2300 = new BitSet(new long[]{0x0000000000010800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2323 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2342 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleActivity2361 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleActivity2373 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleActivity2385 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleActivity2397 = new BitSet(new long[]{0x0000000000056800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2421 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2440 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleActivity2459 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity2478 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleActivity2494 = new BitSet(new long[]{0x0000000000054800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity2517 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity2536 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleActivity2555 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity2574 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleActivity2593 = new BitSet(new long[]{0x0000000000000002L});

}