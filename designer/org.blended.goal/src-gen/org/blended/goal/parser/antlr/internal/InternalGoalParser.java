package org.blended.goal.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.blended.goal.services.GoalGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGoalParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEFF'", "'('", "')'", "'MUL'", "','", "'DEF'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'nothing'", "'OR'", "'AND'", "'NOT'", "':'", "'SUC'", "'ACT'", "'INV'", "'SUB'"
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
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
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
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g"; }



     	private GoalGrammarAccess grammarAccess;
     	
        public InternalGoalParser(TokenStream input, GoalGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "GoalModel";	
       	}
       	
       	@Override
       	protected GoalGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleGoalModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:67:1: entryRuleGoalModel returns [EObject current=null] : iv_ruleGoalModel= ruleGoalModel EOF ;
    public final EObject entryRuleGoalModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoalModel = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:68:2: (iv_ruleGoalModel= ruleGoalModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:69:2: iv_ruleGoalModel= ruleGoalModel EOF
            {
             newCompositeNode(grammarAccess.getGoalModelRule()); 
            pushFollow(FOLLOW_ruleGoalModel_in_entryRuleGoalModel75);
            iv_ruleGoalModel=ruleGoalModel();

            state._fsp--;

             current =iv_ruleGoalModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGoalModel85); 

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
    // $ANTLR end "entryRuleGoalModel"


    // $ANTLR start "ruleGoalModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:76:1: ruleGoalModel returns [EObject current=null] : ( (lv_goals_0_0= ruleGoal ) )+ ;
    public final EObject ruleGoalModel() throws RecognitionException {
        EObject current = null;

        EObject lv_goals_0_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:79:28: ( ( (lv_goals_0_0= ruleGoal ) )+ )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:80:1: ( (lv_goals_0_0= ruleGoal ) )+
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:80:1: ( (lv_goals_0_0= ruleGoal ) )+
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
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:81:1: (lv_goals_0_0= ruleGoal )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:81:1: (lv_goals_0_0= ruleGoal )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:82:3: lv_goals_0_0= ruleGoal
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGoalModelAccess().getGoalsGoalParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleGoal_in_ruleGoalModel130);
            	    lv_goals_0_0=ruleGoal();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGoalModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"goals",
            	            		lv_goals_0_0, 
            	            		"Goal");
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
    // $ANTLR end "ruleGoalModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:106:1: entryRuleEntityAchieveCondition returns [EObject current=null] : iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF ;
    public final EObject entryRuleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:107:2: (iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:108:2: iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:115:1: ruleEntityAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:118:28: ( (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:119:1: (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:119:1: (otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:119:3: otherlv_0= 'DEFF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleEntityAchieveCondition213); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAchieveConditionAccess().getDEFFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveCondition225); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:127:1: ( (lv_name_2_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:128:1: (lv_name_2_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:128:1: (lv_name_2_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:129:3: lv_name_2_0= RULE_ID
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:159:1: entryRuleEntityInvariantCondition returns [EObject current=null] : iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF ;
    public final EObject entryRuleEntityInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityInvariantCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:160:2: (iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:161:2: iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:168:1: ruleEntityInvariantCondition returns [EObject current=null] : (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:171:28: ( (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:172:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:172:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:172:3: otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleEntityInvariantCondition344); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityInvariantCondition356); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:180:1: ( (lv_name_2_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:181:1: (lv_name_2_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:181:1: (lv_name_2_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:182:3: lv_name_2_0= ruleAttribute
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
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:202:1: ( (lv_cardinality_4_0= ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:203:1: (lv_cardinality_4_0= ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:203:1: (lv_cardinality_4_0= ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:204:3: lv_cardinality_4_0= ruleCardinality
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


    // $ANTLR start "entryRuleAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:234:1: entryRuleAttributeAchieveCondition returns [EObject current=null] : iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF ;
    public final EObject entryRuleAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:235:2: (iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:236:2: iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition460);
            iv_ruleAttributeAchieveCondition=ruleAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeAchieveCondition470); 

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
    // $ANTLR end "entryRuleAttributeAchieveCondition"


    // $ANTLR start "ruleAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:243:1: ruleAttributeAchieveCondition returns [EObject current=null] : (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition ) ;
    public final EObject ruleAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject this_MandatoryAttributeAchieveCondition_0 = null;

        EObject this_NotMandatoryAttributeAchieveCondition_1 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:246:28: ( (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:247:1: (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:247:1: (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition )
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:248:5: this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition
                    {
                     
                            newCompositeNode(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition517);
                    this_MandatoryAttributeAchieveCondition_0=ruleMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     
                            current = this_MandatoryAttributeAchieveCondition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:258:5: this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition
                    {
                     
                            newCompositeNode(grammarAccess.getAttributeAchieveConditionAccess().getNotMandatoryAttributeAchieveConditionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition544);
                    this_NotMandatoryAttributeAchieveCondition_1=ruleNotMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     
                            current = this_NotMandatoryAttributeAchieveCondition_1; 
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
    // $ANTLR end "ruleAttributeAchieveCondition"


    // $ANTLR start "entryRuleNotMandatoryAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:274:1: entryRuleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNotMandatoryAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:275:2: (iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:276:2: iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition579);
            iv_ruleNotMandatoryAttributeAchieveCondition=ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleNotMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition589); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:283:1: ruleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:286:28: ( (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:287:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:287:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:287:3: otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition626); 

                	newLeafNode(otherlv_0, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition638); 

                	newLeafNode(otherlv_1, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:295:1: ( (lv_conditions_2_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:296:1: (lv_conditions_2_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:296:1: (lv_conditions_2_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:297:3: lv_conditions_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition659);
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

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:313:2: (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==15) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:313:4: otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) )
            	    {
            	    otherlv_3=(Token)match(input,15,FOLLOW_15_in_ruleNotMandatoryAttributeAchieveCondition672); 

            	        	newLeafNode(otherlv_3, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:317:1: ( (lv_conditions_4_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:318:1: (lv_conditions_4_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:318:1: (lv_conditions_4_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:319:3: lv_conditions_4_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition693);
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
            	    break loop3;
                }
            } while (true);

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition707); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:347:1: entryRuleMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatoryAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:348:2: (iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:349:2: iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition743);
            iv_ruleMandatoryAttributeAchieveCondition=ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition753); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:356:1: ruleMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:359:28: ( (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:360:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:360:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:360:3: otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleMandatoryAttributeAchieveCondition790); 

                	newLeafNode(otherlv_0, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition802); 

                	newLeafNode(otherlv_1, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition814); 

                	newLeafNode(otherlv_2, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition826); 

                	newLeafNode(otherlv_3, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:376:1: ( (lv_conditions_4_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:377:1: (lv_conditions_4_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:377:1: (lv_conditions_4_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:378:3: lv_conditions_4_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition847);
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

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:394:2: (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==15) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:394:4: otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) )
            	    {
            	    otherlv_5=(Token)match(input,15,FOLLOW_15_in_ruleMandatoryAttributeAchieveCondition860); 

            	        	newLeafNode(otherlv_5, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:398:1: ( (lv_conditions_6_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:399:1: (lv_conditions_6_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:399:1: (lv_conditions_6_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:400:3: lv_conditions_6_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition881);
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
            	    break loop4;
                }
            } while (true);

            otherlv_7=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition895); 

                	newLeafNode(otherlv_7, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6());
                
            otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition907); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:432:1: entryRuleAttributeInvariantCondition returns [EObject current=null] : iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF ;
    public final EObject entryRuleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeInvariantCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:433:2: (iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:434:2: iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition943);
            iv_ruleAttributeInvariantCondition=ruleAttributeInvariantCondition();

            state._fsp--;

             current =iv_ruleAttributeInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition953); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:441:1: ruleAttributeInvariantCondition returns [EObject current=null] : (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) ;
    public final EObject ruleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:444:28: ( (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:445:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:445:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:445:3: otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleAttributeInvariantCondition990); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAttributeInvariantCondition1002); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:453:1: ( (lv_expression_2_0= ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:454:1: (lv_expression_2_0= ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:454:1: (lv_expression_2_0= ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:455:3: lv_expression_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition1023);
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

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAttributeInvariantCondition1035); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:485:1: entryRuleCardinality returns [String current=null] : iv_ruleCardinality= ruleCardinality EOF ;
    public final String entryRuleCardinality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCardinality = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:486:2: (iv_ruleCardinality= ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:487:2: iv_ruleCardinality= ruleCardinality EOF
            {
             newCompositeNode(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality1074);
            iv_ruleCardinality=ruleCardinality();

            state._fsp--;

             current =iv_ruleCardinality.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality1085); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:494:1: ruleCardinality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:497:28: ( (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:498:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:498:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            int alt5=4;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_INT) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==EOF||LA5_1==13) ) {
                    alt5=1;
                }
                else if ( (LA5_1==19) ) {
                    switch ( input.LA(3) ) {
                    case 20:
                        {
                        alt5=3;
                        }
                        break;
                    case 21:
                        {
                        alt5=4;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt5=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 3, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:498:6: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1125); 

                    		current.merge(this_INT_0);
                        
                     
                        newLeafNode(this_INT_0, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:506:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:506:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:506:11: this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1152); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
                        
                    kw=(Token)match(input,19,FOLLOW_19_in_ruleCardinality1170); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1185); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:527:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:527:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:527:11: this_INT_4= RULE_INT kw= '..' kw= '*'
                    {
                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1213); 

                    		current.merge(this_INT_4);
                        
                     
                        newLeafNode(this_INT_4, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
                        
                    kw=(Token)match(input,19,FOLLOW_19_in_ruleCardinality1231); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1244); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:547:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:547:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:547:11: this_INT_7= RULE_INT kw= '..' kw= '+'
                    {
                    this_INT_7=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1267); 

                    		current.merge(this_INT_7);
                        
                     
                        newLeafNode(this_INT_7, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
                        
                    kw=(Token)match(input,19,FOLLOW_19_in_ruleCardinality1285); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
                        
                    kw=(Token)match(input,21,FOLLOW_21_in_ruleCardinality1298); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:574:1: entryRuleAttribute returns [String current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final String entryRuleAttribute() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAttribute = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:575:2: (iv_ruleAttribute= ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:576:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute1340);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute1351); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:583:1: ruleAttribute returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleAttribute() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:586:28: ( (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:587:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:587:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:587:6: this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute1391); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,22,FOLLOW_22_in_ruleAttribute1409); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
                
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute1424); 

            		current.merge(this_ID_2);
                
             
                newLeafNode(this_ID_2, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:607:1: (kw= '.' this_ID_4= RULE_ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==22) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:608:2: kw= '.' this_ID_4= RULE_ID
            	    {
            	    kw=(Token)match(input,22,FOLLOW_22_in_ruleAttribute1443); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            	        
            	    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute1458); 

            	    		current.merge(this_ID_4);
            	        
            	     
            	        newLeafNode(this_ID_4, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 
            	        

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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleNothing"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:628:1: entryRuleNothing returns [EObject current=null] : iv_ruleNothing= ruleNothing EOF ;
    public final EObject entryRuleNothing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNothing = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:629:2: (iv_ruleNothing= ruleNothing EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:630:2: iv_ruleNothing= ruleNothing EOF
            {
             newCompositeNode(grammarAccess.getNothingRule()); 
            pushFollow(FOLLOW_ruleNothing_in_entryRuleNothing1505);
            iv_ruleNothing=ruleNothing();

            state._fsp--;

             current =iv_ruleNothing; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNothing1515); 

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
    // $ANTLR end "entryRuleNothing"


    // $ANTLR start "ruleNothing"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:637:1: ruleNothing returns [EObject current=null] : ( (lv_name_0_0= 'nothing' ) ) ;
    public final EObject ruleNothing() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:640:28: ( ( (lv_name_0_0= 'nothing' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:641:1: ( (lv_name_0_0= 'nothing' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:641:1: ( (lv_name_0_0= 'nothing' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:642:1: (lv_name_0_0= 'nothing' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:642:1: (lv_name_0_0= 'nothing' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:643:3: lv_name_0_0= 'nothing'
            {
            lv_name_0_0=(Token)match(input,23,FOLLOW_23_in_ruleNothing1557); 

                    newLeafNode(lv_name_0_0, grammarAccess.getNothingAccess().getNameNothingKeyword_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNothingRule());
            	        }
                   		setWithLastConsumed(current, "name", lv_name_0_0, "nothing");
            	    

            }


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
    // $ANTLR end "ruleNothing"


    // $ANTLR start "entryRuleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:664:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:665:2: (iv_ruleExpression= ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:666:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1605);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1615); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:673:1: ruleExpression returns [EObject current=null] : this_Or_0= ruleOr ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Or_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:676:28: (this_Or_0= ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:678:5: this_Or_0= ruleOr
            {
             
                    newCompositeNode(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleOr_in_ruleExpression1661);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:694:1: entryRuleOr returns [EObject current=null] : iv_ruleOr= ruleOr EOF ;
    public final EObject entryRuleOr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:695:2: (iv_ruleOr= ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:696:2: iv_ruleOr= ruleOr EOF
            {
             newCompositeNode(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr1695);
            iv_ruleOr=ruleOr();

            state._fsp--;

             current =iv_ruleOr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr1705); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:703:1: ruleOr returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleOr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:706:28: ( (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:707:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:707:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:708:5: this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnd_in_ruleOr1752);
            this_And_0=ruleAnd();

            state._fsp--;

             
                    current = this_And_0; 
                    afterParserOrEnumRuleCall();
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:716:1: ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==24) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:716:2: () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:716:2: ()
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:717:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getOrAccess().getOrLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleOr1773); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOrAccess().getORKeyword_1_1());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:726:1: ( (lv_right_3_0= ruleAnd ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:727:1: (lv_right_3_0= ruleAnd )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:727:1: (lv_right_3_0= ruleAnd )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:728:3: lv_right_3_0= ruleAnd
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnd_in_ruleOr1794);
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
    // $ANTLR end "ruleOr"


    // $ANTLR start "entryRuleAnd"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:752:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:753:2: (iv_ruleAnd= ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:754:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd1832);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd1842); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:761:1: ruleAnd returns [EObject current=null] : (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Primary_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:764:28: ( (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:765:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:765:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:766:5: this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePrimary_in_ruleAnd1889);
            this_Primary_0=rulePrimary();

            state._fsp--;

             
                    current = this_Primary_0; 
                    afterParserOrEnumRuleCall();
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:774:1: ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==25) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:774:2: () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:774:2: ()
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:775:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleAnd1910); 

            	        	newLeafNode(otherlv_2, grammarAccess.getAndAccess().getANDKeyword_1_1());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:784:1: ( (lv_right_3_0= rulePrimary ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:785:1: (lv_right_3_0= rulePrimary )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:785:1: (lv_right_3_0= rulePrimary )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:786:3: lv_right_3_0= rulePrimary
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePrimary_in_ruleAnd1931);
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
            	    break loop8;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:810:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:811:2: (iv_rulePrimary= rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:812:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary1969);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary1979); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:819:1: rulePrimary returns [EObject current=null] : ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:822:28: ( ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:823:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:823:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt9=1;
                }
                break;
            case 26:
                {
                alt9=2;
                }
                break;
            case RULE_ID:
            case 16:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:823:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:823:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:823:4: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_12_in_rulePrimary2017); 

                        	newLeafNode(otherlv_0, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0());
                        
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleExpression_in_rulePrimary2039);
                    this_Expression_1=ruleExpression();

                    state._fsp--;

                     
                            current = this_Expression_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_rulePrimary2050); 

                        	newLeafNode(otherlv_2, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:841:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:841:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:841:7: () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:841:7: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:842:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getPrimaryAccess().getNotAction_1_0(),
                                current);
                        

                    }

                    otherlv_4=(Token)match(input,26,FOLLOW_26_in_rulePrimary2079); 

                        	newLeafNode(otherlv_4, grammarAccess.getPrimaryAccess().getNOTKeyword_1_1());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:851:1: ( (lv_expression_5_0= rulePrimary ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:852:1: (lv_expression_5_0= rulePrimary )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:852:1: (lv_expression_5_0= rulePrimary )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:853:3: lv_expression_5_0= rulePrimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePrimary_in_rulePrimary2100);
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:871:5: this_Atomic_6= ruleAtomic
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleAtomic_in_rulePrimary2129);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:887:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:888:2: (iv_ruleAtomic= ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:889:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic2164);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic2174); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:896:1: ruleAtomic returns [EObject current=null] : ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:899:28: ( ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:900:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:900:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==16) ) {
                alt10=1;
            }
            else if ( (LA10_0==RULE_ID) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:900:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:900:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:900:3: () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')'
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:900:3: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:901:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleAtomic2221); 

                        	newLeafNode(otherlv_1, grammarAccess.getAtomicAccess().getDEFKeyword_0_1());
                        
                    otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleAtomic2233); 

                        	newLeafNode(otherlv_2, grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:914:1: ( (lv_name_3_0= ruleAttribute ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:915:1: (lv_name_3_0= ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:915:1: (lv_name_3_0= ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:916:3: lv_name_3_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2254);
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

                    otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleAtomic2266); 

                        	newLeafNode(otherlv_4, grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4());
                        

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:937:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:937:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:937:7: () ( (lv_name_6_0= ruleAttribute ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:937:7: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:938:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeValueAction_1_0(),
                                current);
                        

                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:943:2: ( (lv_name_6_0= ruleAttribute ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:944:1: (lv_name_6_0= ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:944:1: (lv_name_6_0= ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:945:3: lv_name_6_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2304);
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


    // $ANTLR start "entryRuleGoal"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:969:1: entryRuleGoal returns [EObject current=null] : iv_ruleGoal= ruleGoal EOF ;
    public final EObject entryRuleGoal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoal = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:970:2: (iv_ruleGoal= ruleGoal EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:971:2: iv_ruleGoal= ruleGoal EOF
            {
             newCompositeNode(grammarAccess.getGoalRule()); 
            pushFollow(FOLLOW_ruleGoal_in_entryRuleGoal2341);
            iv_ruleGoal=ruleGoal();

            state._fsp--;

             current =iv_ruleGoal; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGoal2351); 

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
    // $ANTLR end "entryRuleGoal"


    // $ANTLR start "ruleGoal"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:978:1: ruleGoal returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? ) ;
    public final EObject ruleGoal() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        EObject lv_sucessCondition_4_1 = null;

        EObject lv_sucessCondition_4_2 = null;

        EObject lv_sucessCondition_4_3 = null;

        EObject lv_activationCondition_9_1 = null;

        EObject lv_activationCondition_9_2 = null;

        EObject lv_invariantConditions_14_1 = null;

        EObject lv_invariantConditions_14_2 = null;

        EObject lv_invariantConditions_16_1 = null;

        EObject lv_invariantConditions_16_2 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:981:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:982:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:982:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:982:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )?
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:982:2: ( (lv_name_0_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:983:1: (lv_name_0_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:983:1: (lv_name_0_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:984:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGoal2393); 

            			newLeafNode(lv_name_0_0, grammarAccess.getGoalAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getGoalRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,27,FOLLOW_27_in_ruleGoal2410); 

                	newLeafNode(otherlv_1, grammarAccess.getGoalAccess().getColonKeyword_1());
                
            otherlv_2=(Token)match(input,28,FOLLOW_28_in_ruleGoal2422); 

                	newLeafNode(otherlv_2, grammarAccess.getGoalAccess().getSUCKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleGoal2434); 

                	newLeafNode(otherlv_3, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1012:1: ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1013:1: ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1013:1: ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1014:1: (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1014:1: (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing )
            int alt11=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt11=1;
                }
                break;
            case 16:
            case 17:
                {
                alt11=2;
                }
                break;
            case 23:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1015:3: lv_sucessCondition_4_1= ruleEntityAchieveCondition
                    {
                     
                    	        newCompositeNode(grammarAccess.getGoalAccess().getSucessConditionEntityAchieveConditionParserRuleCall_4_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleGoal2457);
                    lv_sucessCondition_4_1=ruleEntityAchieveCondition();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getGoalRule());
                    	        }
                           		set(
                           			current, 
                           			"sucessCondition",
                            		lv_sucessCondition_4_1, 
                            		"EntityAchieveCondition");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1030:8: lv_sucessCondition_4_2= ruleAttributeAchieveCondition
                    {
                     
                    	        newCompositeNode(grammarAccess.getGoalAccess().getSucessConditionAttributeAchieveConditionParserRuleCall_4_0_1()); 
                    	    
                    pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal2476);
                    lv_sucessCondition_4_2=ruleAttributeAchieveCondition();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getGoalRule());
                    	        }
                           		set(
                           			current, 
                           			"sucessCondition",
                            		lv_sucessCondition_4_2, 
                            		"AttributeAchieveCondition");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1045:8: lv_sucessCondition_4_3= ruleNothing
                    {
                     
                    	        newCompositeNode(grammarAccess.getGoalAccess().getSucessConditionNothingParserRuleCall_4_0_2()); 
                    	    
                    pushFollow(FOLLOW_ruleNothing_in_ruleGoal2495);
                    lv_sucessCondition_4_3=ruleNothing();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getGoalRule());
                    	        }
                           		set(
                           			current, 
                           			"sucessCondition",
                            		lv_sucessCondition_4_3, 
                            		"Nothing");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }
                    break;

            }


            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleGoal2510); 

                	newLeafNode(otherlv_5, grammarAccess.getGoalAccess().getRightParenthesisKeyword_5());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1067:1: (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==15) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==29) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1067:3: otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')'
                    {
                    otherlv_6=(Token)match(input,15,FOLLOW_15_in_ruleGoal2523); 

                        	newLeafNode(otherlv_6, grammarAccess.getGoalAccess().getCommaKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,29,FOLLOW_29_in_ruleGoal2535); 

                        	newLeafNode(otherlv_7, grammarAccess.getGoalAccess().getACTKeyword_6_1());
                        
                    otherlv_8=(Token)match(input,12,FOLLOW_12_in_ruleGoal2547); 

                        	newLeafNode(otherlv_8, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_6_2());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1079:1: ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1080:1: ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1080:1: ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1081:1: (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1081:1: (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition )
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==11) ) {
                        alt12=1;
                    }
                    else if ( ((LA12_0>=16 && LA12_0<=17)) ) {
                        alt12=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;
                    }
                    switch (alt12) {
                        case 1 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1082:3: lv_activationCondition_9_1= ruleEntityAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getActivationConditionEntityAchieveConditionParserRuleCall_6_3_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleGoal2570);
                            lv_activationCondition_9_1=ruleEntityAchieveCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getGoalRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"activationCondition",
                                    		lv_activationCondition_9_1, 
                                    		"EntityAchieveCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1097:8: lv_activationCondition_9_2= ruleAttributeAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getActivationConditionAttributeAchieveConditionParserRuleCall_6_3_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal2589);
                            lv_activationCondition_9_2=ruleAttributeAchieveCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getGoalRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"activationCondition",
                                    		lv_activationCondition_9_2, 
                                    		"AttributeAchieveCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }

                    otherlv_10=(Token)match(input,13,FOLLOW_13_in_ruleGoal2604); 

                        	newLeafNode(otherlv_10, grammarAccess.getGoalAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1119:3: (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==15) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==30) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1119:5: otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')'
                    {
                    otherlv_11=(Token)match(input,15,FOLLOW_15_in_ruleGoal2619); 

                        	newLeafNode(otherlv_11, grammarAccess.getGoalAccess().getCommaKeyword_7_0());
                        
                    otherlv_12=(Token)match(input,30,FOLLOW_30_in_ruleGoal2631); 

                        	newLeafNode(otherlv_12, grammarAccess.getGoalAccess().getINVKeyword_7_1());
                        
                    otherlv_13=(Token)match(input,12,FOLLOW_12_in_ruleGoal2643); 

                        	newLeafNode(otherlv_13, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_7_2());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1131:1: ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1132:1: ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1132:1: ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1133:1: (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1133:1: (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition )
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==14) ) {
                        alt14=1;
                    }
                    else if ( (LA14_0==18) ) {
                        alt14=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;
                    }
                    switch (alt14) {
                        case 1 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1134:3: lv_invariantConditions_14_1= ruleEntityInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_3_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleGoal2666);
                            lv_invariantConditions_14_1=ruleEntityInvariantCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getGoalRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"invariantConditions",
                                    		lv_invariantConditions_14_1, 
                                    		"EntityInvariantCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1149:8: lv_invariantConditions_14_2= ruleAttributeInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_3_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal2685);
                            lv_invariantConditions_14_2=ruleAttributeInvariantCondition();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getGoalRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"invariantConditions",
                                    		lv_invariantConditions_14_2, 
                                    		"AttributeInvariantCondition");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1167:2: (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==15) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1167:4: otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) )
                    	    {
                    	    otherlv_15=(Token)match(input,15,FOLLOW_15_in_ruleGoal2701); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getGoalAccess().getCommaKeyword_7_4_0());
                    	        
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1171:1: ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1172:1: ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1172:1: ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1173:1: (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1173:1: (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition )
                    	    int alt15=2;
                    	    int LA15_0 = input.LA(1);

                    	    if ( (LA15_0==14) ) {
                    	        alt15=1;
                    	    }
                    	    else if ( (LA15_0==18) ) {
                    	        alt15=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 15, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt15) {
                    	        case 1 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1174:3: lv_invariantConditions_16_1= ruleEntityInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_4_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleGoal2724);
                    	            lv_invariantConditions_16_1=ruleEntityInvariantCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getGoalRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"invariantConditions",
                    	                    		lv_invariantConditions_16_1, 
                    	                    		"EntityInvariantCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1189:8: lv_invariantConditions_16_2= ruleAttributeInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_4_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal2743);
                    	            lv_invariantConditions_16_2=ruleAttributeInvariantCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getGoalRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"invariantConditions",
                    	                    		lv_invariantConditions_16_2, 
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

                    otherlv_17=(Token)match(input,13,FOLLOW_13_in_ruleGoal2760); 

                        	newLeafNode(otherlv_17, grammarAccess.getGoalAccess().getRightParenthesisKeyword_7_5());
                        

                    }
                    break;

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1211:3: (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==15) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1211:5: otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')'
                    {
                    otherlv_18=(Token)match(input,15,FOLLOW_15_in_ruleGoal2775); 

                        	newLeafNode(otherlv_18, grammarAccess.getGoalAccess().getCommaKeyword_8_0());
                        
                    otherlv_19=(Token)match(input,31,FOLLOW_31_in_ruleGoal2787); 

                        	newLeafNode(otherlv_19, grammarAccess.getGoalAccess().getSUBKeyword_8_1());
                        
                    otherlv_20=(Token)match(input,12,FOLLOW_12_in_ruleGoal2799); 

                        	newLeafNode(otherlv_20, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_8_2());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1223:1: ( (otherlv_21= RULE_ID ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1224:1: (otherlv_21= RULE_ID )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1224:1: (otherlv_21= RULE_ID )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1225:3: otherlv_21= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getGoalRule());
                    	        }
                            
                    otherlv_21=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGoal2819); 

                    		newLeafNode(otherlv_21, grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_3_0()); 
                    	

                    }


                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1236:2: (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==15) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1236:4: otherlv_22= ',' ( (otherlv_23= RULE_ID ) )
                    	    {
                    	    otherlv_22=(Token)match(input,15,FOLLOW_15_in_ruleGoal2832); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getGoalAccess().getCommaKeyword_8_4_0());
                    	        
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1240:1: ( (otherlv_23= RULE_ID ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1241:1: (otherlv_23= RULE_ID )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1241:1: (otherlv_23= RULE_ID )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.goal/src-gen/org/blended/goal/parser/antlr/internal/InternalGoal.g:1242:3: otherlv_23= RULE_ID
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGoalRule());
                    	    	        }
                    	            
                    	    otherlv_23=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGoal2852); 

                    	    		newLeafNode(otherlv_23, grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_4_1_0()); 
                    	    	

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,13,FOLLOW_13_in_ruleGoal2866); 

                        	newLeafNode(otherlv_24, grammarAccess.getGoalAccess().getRightParenthesisKeyword_8_5());
                        

                    }
                    break;

            }


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
    // $ANTLR end "ruleGoal"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleGoalModel_in_entryRuleGoalModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGoalModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGoal_in_ruleGoalModel130 = new BitSet(new long[]{0x0000000000000012L});
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
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition460 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeAchieveCondition470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition579 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition626 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition638 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition659 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleNotMandatoryAttributeAchieveCondition672 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition693 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition743 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleMandatoryAttributeAchieveCondition790 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition802 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition814 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition826 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition847 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleMandatoryAttributeAchieveCondition860 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition881 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition895 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition943 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleAttributeInvariantCondition990 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeInvariantCondition1002 = new BitSet(new long[]{0x0000000004011010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition1023 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeInvariantCondition1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality1074 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1152 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCardinality1170 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1213 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCardinality1231 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1267 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCardinality1285 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleCardinality1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute1340 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute1391 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleAttribute1409 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute1424 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_ruleAttribute1443 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute1458 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_ruleNothing_in_entryRuleNothing1505 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNothing1515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleNothing1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1605 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr1695 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr1705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr1752 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleOr1773 = new BitSet(new long[]{0x0000000004011010L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr1794 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd1832 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd1842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd1889 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleAnd1910 = new BitSet(new long[]{0x0000000004011010L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd1931 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary1969 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary1979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rulePrimary2017 = new BitSet(new long[]{0x0000000004011010L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePrimary2039 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePrimary2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulePrimary2079 = new BitSet(new long[]{0x0000000004011010L});
    public static final BitSet FOLLOW_rulePrimary_in_rulePrimary2100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rulePrimary2129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic2164 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic2174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleAtomic2221 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAtomic2233 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2254 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAtomic2266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGoal_in_entryRuleGoal2341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGoal2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGoal2393 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleGoal2410 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleGoal2422 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal2434 = new BitSet(new long[]{0x0000000000830800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleGoal2457 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal2476 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleNothing_in_ruleGoal2495 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGoal2510 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleGoal2523 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleGoal2535 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal2547 = new BitSet(new long[]{0x0000000000030800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleGoal2570 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal2589 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGoal2604 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleGoal2619 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleGoal2631 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal2643 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleGoal2666 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal2685 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleGoal2701 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleGoal2724 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal2743 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleGoal2760 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleGoal2775 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleGoal2787 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal2799 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGoal2819 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_ruleGoal2832 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGoal2852 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_13_in_ruleGoal2866 = new BitSet(new long[]{0x0000000000000002L});

}