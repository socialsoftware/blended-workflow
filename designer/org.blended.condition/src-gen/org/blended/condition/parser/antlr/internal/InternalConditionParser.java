package org.blended.condition.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.blended.condition.services.ConditionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalConditionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEF'", "'('", "')'", "'EXISTS'", "'MUL'", "','", "'DEP'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'OR'", "'AND'", "'NOT'", "'ENTITY_ACHIEVE_CONDITIONS'", "'ENTITY_INVARIANT_CONDITIONS'", "'ENTITY_DEPENDENCE_CONDITIONS'", "'ATTRIBUTE_ACHIEVE_CONDITIONS'", "'ATTRIBUTE_INVARIANT_CONDITIONS'", "'ATTRIBUTE_DEPENDENCE_CONDITIONS'"
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


        public InternalConditionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalConditionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalConditionParser.tokenNames; }
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g"; }



     	private ConditionGrammarAccess grammarAccess;
     	
        public InternalConditionParser(TokenStream input, ConditionGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "ConditionModel";	
       	}
       	
       	@Override
       	protected ConditionGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleConditionModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:67:1: entryRuleConditionModel returns [EObject current=null] : iv_ruleConditionModel= ruleConditionModel EOF ;
    public final EObject entryRuleConditionModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionModel = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:68:2: (iv_ruleConditionModel= ruleConditionModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:69:2: iv_ruleConditionModel= ruleConditionModel EOF
            {
             newCompositeNode(grammarAccess.getConditionModelRule()); 
            pushFollow(FOLLOW_ruleConditionModel_in_entryRuleConditionModel75);
            iv_ruleConditionModel=ruleConditionModel();

            state._fsp--;

             current =iv_ruleConditionModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionModel85); 

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
    // $ANTLR end "entryRuleConditionModel"


    // $ANTLR start "ruleConditionModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:76:1: ruleConditionModel returns [EObject current=null] : ( (lv_conditions_0_0= ruleCondition ) ) ;
    public final EObject ruleConditionModel() throws RecognitionException {
        EObject current = null;

        EObject lv_conditions_0_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:79:28: ( ( (lv_conditions_0_0= ruleCondition ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:80:1: ( (lv_conditions_0_0= ruleCondition ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:80:1: ( (lv_conditions_0_0= ruleCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:81:1: (lv_conditions_0_0= ruleCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:81:1: (lv_conditions_0_0= ruleCondition )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:82:3: lv_conditions_0_0= ruleCondition
            {
             
            	        newCompositeNode(grammarAccess.getConditionModelAccess().getConditionsConditionParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleCondition_in_ruleConditionModel130);
            lv_conditions_0_0=ruleCondition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditionModelRule());
            	        }
                   		set(
                   			current, 
                   			"conditions",
                    		lv_conditions_0_0, 
                    		"Condition");
            	        afterParserOrEnumRuleCall();
            	    

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
    // $ANTLR end "ruleConditionModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:106:1: entryRuleEntityAchieveCondition returns [EObject current=null] : iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF ;
    public final EObject entryRuleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:107:2: (iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:108:2: iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition165);
            iv_ruleEntityAchieveCondition=ruleEntityAchieveCondition();

            state._fsp--;

             current =iv_ruleEntityAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveCondition175); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:115:1: ruleEntityAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:118:28: ( (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:119:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:119:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:119:3: otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleEntityAchieveCondition212); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAchieveConditionAccess().getDEFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveCondition224); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:127:1: ( (lv_name_2_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:128:1: (lv_name_2_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:128:1: (lv_name_2_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:129:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityAchieveCondition241); 

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

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveCondition258); 

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


    // $ANTLR start "entryRuleEntityAchieveConditionExist"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:157:1: entryRuleEntityAchieveConditionExist returns [EObject current=null] : iv_ruleEntityAchieveConditionExist= ruleEntityAchieveConditionExist EOF ;
    public final EObject entryRuleEntityAchieveConditionExist() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityAchieveConditionExist = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:158:2: (iv_ruleEntityAchieveConditionExist= ruleEntityAchieveConditionExist EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:159:2: iv_ruleEntityAchieveConditionExist= ruleEntityAchieveConditionExist EOF
            {
             newCompositeNode(grammarAccess.getEntityAchieveConditionExistRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveConditionExist_in_entryRuleEntityAchieveConditionExist294);
            iv_ruleEntityAchieveConditionExist=ruleEntityAchieveConditionExist();

            state._fsp--;

             current =iv_ruleEntityAchieveConditionExist; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveConditionExist304); 

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
    // $ANTLR end "entryRuleEntityAchieveConditionExist"


    // $ANTLR start "ruleEntityAchieveConditionExist"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:166:1: ruleEntityAchieveConditionExist returns [EObject current=null] : (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' ) ;
    public final EObject ruleEntityAchieveConditionExist() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:169:28: ( (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:170:1: (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:170:1: (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:170:3: otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleEntityAchieveConditionExist341); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAchieveConditionExistAccess().getEXISTSKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveConditionExist353); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleEntityAchieveConditionExist365); 

                	newLeafNode(otherlv_2, grammarAccess.getEntityAchieveConditionExistAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveConditionExist377); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:186:1: ( (lv_name_4_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:187:1: (lv_name_4_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:187:1: (lv_name_4_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:188:3: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityAchieveConditionExist394); 

            			newLeafNode(lv_name_4_0, grammarAccess.getEntityAchieveConditionExistAccess().getNameIDTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getEntityAchieveConditionExistRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"ID");
            	    

            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveConditionExist411); 

                	newLeafNode(otherlv_5, grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_5());
                
            otherlv_6=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveConditionExist423); 

                	newLeafNode(otherlv_6, grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_6());
                

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
    // $ANTLR end "ruleEntityAchieveConditionExist"


    // $ANTLR start "entryRuleEntityInvariantCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:220:1: entryRuleEntityInvariantCondition returns [EObject current=null] : iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF ;
    public final EObject entryRuleEntityInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityInvariantCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:221:2: (iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:222:2: iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition459);
            iv_ruleEntityInvariantCondition=ruleEntityInvariantCondition();

            state._fsp--;

             current =iv_ruleEntityInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityInvariantCondition469); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:229:1: ruleEntityInvariantCondition returns [EObject current=null] : (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:232:28: ( (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:233:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:233:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:233:3: otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleEntityInvariantCondition506); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityInvariantCondition518); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:241:1: ( (lv_name_2_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:242:1: (lv_name_2_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:242:1: (lv_name_2_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:243:3: lv_name_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleEntityInvariantCondition539);
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

            otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleEntityInvariantCondition551); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:263:1: ( (lv_cardinality_4_0= ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:264:1: (lv_cardinality_4_0= ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:264:1: (lv_cardinality_4_0= ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:265:3: lv_cardinality_4_0= ruleCardinality
            {
             
            	        newCompositeNode(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleCardinality_in_ruleEntityInvariantCondition572);
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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityInvariantCondition584); 

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


    // $ANTLR start "entryRuleEntityDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:293:1: entryRuleEntityDependenceCondition returns [EObject current=null] : iv_ruleEntityDependenceCondition= ruleEntityDependenceCondition EOF ;
    public final EObject entryRuleEntityDependenceCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityDependenceCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:294:2: (iv_ruleEntityDependenceCondition= ruleEntityDependenceCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:295:2: iv_ruleEntityDependenceCondition= ruleEntityDependenceCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityDependenceConditionRule()); 
            pushFollow(FOLLOW_ruleEntityDependenceCondition_in_entryRuleEntityDependenceCondition620);
            iv_ruleEntityDependenceCondition=ruleEntityDependenceCondition();

            state._fsp--;

             current =iv_ruleEntityDependenceCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityDependenceCondition630); 

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
    // $ANTLR end "entryRuleEntityDependenceCondition"


    // $ANTLR start "ruleEntityDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:302:1: ruleEntityDependenceCondition returns [EObject current=null] : (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' ) ;
    public final EObject ruleEntityDependenceCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_entity1_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token lv_entity2_9_0=null;
        Token otherlv_10=null;
        Token otherlv_11=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:305:28: ( (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:306:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:306:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:306:3: otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleEntityDependenceCondition667); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityDependenceConditionAccess().getDEPKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityDependenceCondition679); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleEntityDependenceCondition691); 

                	newLeafNode(otherlv_2, grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleEntityDependenceCondition703); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:322:1: ( (lv_entity1_4_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:323:1: (lv_entity1_4_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:323:1: (lv_entity1_4_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:324:3: lv_entity1_4_0= RULE_ID
            {
            lv_entity1_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityDependenceCondition720); 

            			newLeafNode(lv_entity1_4_0, grammarAccess.getEntityDependenceConditionAccess().getEntity1IDTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getEntityDependenceConditionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"entity1",
                    		lv_entity1_4_0, 
                    		"ID");
            	    

            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityDependenceCondition737); 

                	newLeafNode(otherlv_5, grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_5());
                
            otherlv_6=(Token)match(input,16,FOLLOW_16_in_ruleEntityDependenceCondition749); 

                	newLeafNode(otherlv_6, grammarAccess.getEntityDependenceConditionAccess().getCommaKeyword_6());
                
            otherlv_7=(Token)match(input,11,FOLLOW_11_in_ruleEntityDependenceCondition761); 

                	newLeafNode(otherlv_7, grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_7());
                
            otherlv_8=(Token)match(input,12,FOLLOW_12_in_ruleEntityDependenceCondition773); 

                	newLeafNode(otherlv_8, grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_8());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:356:1: ( (lv_entity2_9_0= RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:357:1: (lv_entity2_9_0= RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:357:1: (lv_entity2_9_0= RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:358:3: lv_entity2_9_0= RULE_ID
            {
            lv_entity2_9_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityDependenceCondition790); 

            			newLeafNode(lv_entity2_9_0, grammarAccess.getEntityDependenceConditionAccess().getEntity2IDTerminalRuleCall_9_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getEntityDependenceConditionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"entity2",
                    		lv_entity2_9_0, 
                    		"ID");
            	    

            }


            }

            otherlv_10=(Token)match(input,13,FOLLOW_13_in_ruleEntityDependenceCondition807); 

                	newLeafNode(otherlv_10, grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_10());
                
            otherlv_11=(Token)match(input,13,FOLLOW_13_in_ruleEntityDependenceCondition819); 

                	newLeafNode(otherlv_11, grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_11());
                

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
    // $ANTLR end "ruleEntityDependenceCondition"


    // $ANTLR start "entryRuleAttributeAchieveCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:390:1: entryRuleAttributeAchieveCondition returns [EObject current=null] : iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF ;
    public final EObject entryRuleAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:391:2: (iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:392:2: iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition855);
            iv_ruleAttributeAchieveCondition=ruleAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeAchieveCondition865); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:399:1: ruleAttributeAchieveCondition returns [EObject current=null] : (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition ) ;
    public final EObject ruleAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject this_MandatoryAttributeAchieveCondition_0 = null;

        EObject this_NotMandatoryAttributeAchieveCondition_1 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:402:28: ( (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:403:1: (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:403:1: (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition )
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:404:5: this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition
                    {
                     
                            newCompositeNode(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition912);
                    this_MandatoryAttributeAchieveCondition_0=ruleMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     
                            current = this_MandatoryAttributeAchieveCondition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:414:5: this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition
                    {
                     
                            newCompositeNode(grammarAccess.getAttributeAchieveConditionAccess().getNotMandatoryAttributeAchieveConditionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition939);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:430:1: entryRuleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNotMandatoryAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:431:2: (iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:432:2: iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition974);
            iv_ruleNotMandatoryAttributeAchieveCondition=ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleNotMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition984); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:439:1: ruleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:442:28: ( (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:443:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:443:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:443:3: otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleNotMandatoryAttributeAchieveCondition1021); 

                	newLeafNode(otherlv_0, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition1033); 

                	newLeafNode(otherlv_1, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:451:1: ( (lv_conditions_2_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:452:1: (lv_conditions_2_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:452:1: (lv_conditions_2_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:453:3: lv_conditions_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1054);
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

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:469:2: (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==16) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:469:4: otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) )
            	    {
            	    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition1067); 

            	        	newLeafNode(otherlv_3, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:473:1: ( (lv_conditions_4_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:474:1: (lv_conditions_4_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:474:1: (lv_conditions_4_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:475:3: lv_conditions_4_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1088);
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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition1102); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:503:1: entryRuleMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatoryAttributeAchieveCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:504:2: (iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:505:2: iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition1138);
            iv_ruleMandatoryAttributeAchieveCondition=ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition1148); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:512:1: ruleMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:515:28: ( (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:516:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:516:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:516:3: otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleMandatoryAttributeAchieveCondition1185); 

                	newLeafNode(otherlv_0, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1197); 

                	newLeafNode(otherlv_1, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleMandatoryAttributeAchieveCondition1209); 

                	newLeafNode(otherlv_2, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1221); 

                	newLeafNode(otherlv_3, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:532:1: ( (lv_conditions_4_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:533:1: (lv_conditions_4_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:533:1: (lv_conditions_4_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:534:3: lv_conditions_4_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1242);
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

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:550:2: (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:550:4: otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) )
            	    {
            	    otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition1255); 

            	        	newLeafNode(otherlv_5, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:554:1: ( (lv_conditions_6_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:555:1: (lv_conditions_6_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:555:1: (lv_conditions_6_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:556:3: lv_conditions_6_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1276);
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

            otherlv_7=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1290); 

                	newLeafNode(otherlv_7, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6());
                
            otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1302); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:588:1: entryRuleAttributeInvariantCondition returns [EObject current=null] : iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF ;
    public final EObject entryRuleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeInvariantCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:589:2: (iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:590:2: iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition1338);
            iv_ruleAttributeInvariantCondition=ruleAttributeInvariantCondition();

            state._fsp--;

             current =iv_ruleAttributeInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition1348); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:597:1: ruleAttributeInvariantCondition returns [EObject current=null] : (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) ;
    public final EObject ruleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:600:28: ( (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:601:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:601:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:601:3: otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleAttributeInvariantCondition1385); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAttributeInvariantCondition1397); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:609:1: ( (lv_expression_2_0= ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:610:1: (lv_expression_2_0= ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:610:1: (lv_expression_2_0= ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:611:3: lv_expression_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition1418);
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

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAttributeInvariantCondition1430); 

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


    // $ANTLR start "entryRuleAttributeDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:639:1: entryRuleAttributeDependenceCondition returns [EObject current=null] : iv_ruleAttributeDependenceCondition= ruleAttributeDependenceCondition EOF ;
    public final EObject entryRuleAttributeDependenceCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeDependenceCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:640:2: (iv_ruleAttributeDependenceCondition= ruleAttributeDependenceCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:641:2: iv_ruleAttributeDependenceCondition= ruleAttributeDependenceCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeDependenceConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeDependenceCondition_in_entryRuleAttributeDependenceCondition1466);
            iv_ruleAttributeDependenceCondition=ruleAttributeDependenceCondition();

            state._fsp--;

             current =iv_ruleAttributeDependenceCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeDependenceCondition1476); 

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
    // $ANTLR end "entryRuleAttributeDependenceCondition"


    // $ANTLR start "ruleAttributeDependenceCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:648:1: ruleAttributeDependenceCondition returns [EObject current=null] : (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' ) ;
    public final EObject ruleAttributeDependenceCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_attributes1_4_0 = null;

        AntlrDatatypeRuleToken lv_attributes1_6_0 = null;

        AntlrDatatypeRuleToken lv_attribute2_11_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:651:28: ( (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:652:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:652:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:652:3: otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleAttributeDependenceCondition1513); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeDependenceConditionAccess().getDEPKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAttributeDependenceCondition1525); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleAttributeDependenceCondition1537); 

                	newLeafNode(otherlv_2, grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleAttributeDependenceCondition1549); 

                	newLeafNode(otherlv_3, grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_3());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:668:1: ( (lv_attributes1_4_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:669:1: (lv_attributes1_4_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:669:1: (lv_attributes1_4_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:670:3: lv_attributes1_4_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1570);
            lv_attributes1_4_0=ruleAttribute();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeDependenceConditionRule());
            	        }
                   		add(
                   			current, 
                   			"attributes1",
                    		lv_attributes1_4_0, 
                    		"Attribute");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:686:2: (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==16) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:686:4: otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) )
            	    {
            	    otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleAttributeDependenceCondition1583); 

            	        	newLeafNode(otherlv_5, grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_5_0());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:690:1: ( (lv_attributes1_6_0= ruleAttribute ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:691:1: (lv_attributes1_6_0= ruleAttribute )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:691:1: (lv_attributes1_6_0= ruleAttribute )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:692:3: lv_attributes1_6_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1604);
            	    lv_attributes1_6_0=ruleAttribute();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAttributeDependenceConditionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"attributes1",
            	            		lv_attributes1_6_0, 
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

            otherlv_7=(Token)match(input,13,FOLLOW_13_in_ruleAttributeDependenceCondition1618); 

                	newLeafNode(otherlv_7, grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_6());
                
            otherlv_8=(Token)match(input,16,FOLLOW_16_in_ruleAttributeDependenceCondition1630); 

                	newLeafNode(otherlv_8, grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_7());
                
            otherlv_9=(Token)match(input,11,FOLLOW_11_in_ruleAttributeDependenceCondition1642); 

                	newLeafNode(otherlv_9, grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_8());
                
            otherlv_10=(Token)match(input,12,FOLLOW_12_in_ruleAttributeDependenceCondition1654); 

                	newLeafNode(otherlv_10, grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_9());
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:724:1: ( (lv_attribute2_11_0= ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:725:1: (lv_attribute2_11_0= ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:725:1: (lv_attribute2_11_0= ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:726:3: lv_attribute2_11_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getAttributeDependenceConditionAccess().getAttribute2AttributeParserRuleCall_10_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1675);
            lv_attribute2_11_0=ruleAttribute();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeDependenceConditionRule());
            	        }
                   		set(
                   			current, 
                   			"attribute2",
                    		lv_attribute2_11_0, 
                    		"Attribute");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_13_in_ruleAttributeDependenceCondition1687); 

                	newLeafNode(otherlv_12, grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_11());
                
            otherlv_13=(Token)match(input,13,FOLLOW_13_in_ruleAttributeDependenceCondition1699); 

                	newLeafNode(otherlv_13, grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_12());
                

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
    // $ANTLR end "ruleAttributeDependenceCondition"


    // $ANTLR start "entryRuleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:758:1: entryRuleCardinality returns [String current=null] : iv_ruleCardinality= ruleCardinality EOF ;
    public final String entryRuleCardinality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCardinality = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:759:2: (iv_ruleCardinality= ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:760:2: iv_ruleCardinality= ruleCardinality EOF
            {
             newCompositeNode(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality1736);
            iv_ruleCardinality=ruleCardinality();

            state._fsp--;

             current =iv_ruleCardinality.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality1747); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:767:1: ruleCardinality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:770:28: ( (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:771:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:771:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            int alt5=4;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_INT) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==20) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt5=2;
                        }
                        break;
                    case 21:
                        {
                        alt5=3;
                        }
                        break;
                    case 22:
                        {
                        alt5=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;
                    }

                }
                else if ( (LA5_1==EOF||LA5_1==13) ) {
                    alt5=1;
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:771:6: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1787); 

                    		current.merge(this_INT_0);
                        
                     
                        newLeafNode(this_INT_0, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:779:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:779:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:779:11: this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1814); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1832); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1847); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:800:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:800:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:800:11: this_INT_4= RULE_INT kw= '..' kw= '*'
                    {
                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1875); 

                    		current.merge(this_INT_4);
                        
                     
                        newLeafNode(this_INT_4, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1893); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
                        
                    kw=(Token)match(input,21,FOLLOW_21_in_ruleCardinality1906); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:820:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:820:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:820:11: this_INT_7= RULE_INT kw= '..' kw= '+'
                    {
                    this_INT_7=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1929); 

                    		current.merge(this_INT_7);
                        
                     
                        newLeafNode(this_INT_7, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1947); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
                        
                    kw=(Token)match(input,22,FOLLOW_22_in_ruleCardinality1960); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:847:1: entryRuleAttribute returns [String current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final String entryRuleAttribute() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAttribute = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:848:2: (iv_ruleAttribute= ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:849:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute2002);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute2013); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:856:1: ruleAttribute returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleAttribute() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:859:28: ( (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:860:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:860:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:860:6: this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2053); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,23,FOLLOW_23_in_ruleAttribute2071); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
                
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2086); 

            		current.merge(this_ID_2);
                
             
                newLeafNode(this_ID_2, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:880:1: (kw= '.' this_ID_4= RULE_ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==23) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:881:2: kw= '.' this_ID_4= RULE_ID
            	    {
            	    kw=(Token)match(input,23,FOLLOW_23_in_ruleAttribute2105); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            	        
            	    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2120); 

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


    // $ANTLR start "entryRuleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:903:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:904:2: (iv_ruleExpression= ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:905:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2169);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2179); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:912:1: ruleExpression returns [EObject current=null] : this_Or_0= ruleOr ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Or_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:915:28: (this_Or_0= ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:917:5: this_Or_0= ruleOr
            {
             
                    newCompositeNode(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleOr_in_ruleExpression2225);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:933:1: entryRuleOr returns [EObject current=null] : iv_ruleOr= ruleOr EOF ;
    public final EObject entryRuleOr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:934:2: (iv_ruleOr= ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:935:2: iv_ruleOr= ruleOr EOF
            {
             newCompositeNode(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr2259);
            iv_ruleOr=ruleOr();

            state._fsp--;

             current =iv_ruleOr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr2269); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:942:1: ruleOr returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleOr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:945:28: ( (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:946:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:946:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:947:5: this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnd_in_ruleOr2316);
            this_And_0=ruleAnd();

            state._fsp--;

             
                    current = this_And_0; 
                    afterParserOrEnumRuleCall();
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:955:1: ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==24) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:955:2: () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:955:2: ()
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:956:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getOrAccess().getOrLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleOr2337); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOrAccess().getORKeyword_1_1());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:965:1: ( (lv_right_3_0= ruleAnd ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:966:1: (lv_right_3_0= ruleAnd )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:966:1: (lv_right_3_0= ruleAnd )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:967:3: lv_right_3_0= ruleAnd
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnd_in_ruleOr2358);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:991:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:992:2: (iv_ruleAnd= ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:993:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd2396);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd2406); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1000:1: ruleAnd returns [EObject current=null] : (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Primary_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1003:28: ( (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1004:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1004:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1005:5: this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePrimary_in_ruleAnd2453);
            this_Primary_0=rulePrimary();

            state._fsp--;

             
                    current = this_Primary_0; 
                    afterParserOrEnumRuleCall();
                
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1013:1: ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==25) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1013:2: () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1013:2: ()
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1014:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleAnd2474); 

            	        	newLeafNode(otherlv_2, grammarAccess.getAndAccess().getANDKeyword_1_1());
            	        
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1023:1: ( (lv_right_3_0= rulePrimary ) )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1024:1: (lv_right_3_0= rulePrimary )
            	    {
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1024:1: (lv_right_3_0= rulePrimary )
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1025:3: lv_right_3_0= rulePrimary
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePrimary_in_ruleAnd2495);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1049:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1050:2: (iv_rulePrimary= rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1051:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary2533);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary2543); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1058:1: rulePrimary returns [EObject current=null] : ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) ;
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
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1061:28: ( ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1062:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1062:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
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
            case 11:
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1062:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1062:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1062:4: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_12_in_rulePrimary2581); 

                        	newLeafNode(otherlv_0, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0());
                        
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleExpression_in_rulePrimary2603);
                    this_Expression_1=ruleExpression();

                    state._fsp--;

                     
                            current = this_Expression_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_rulePrimary2614); 

                        	newLeafNode(otherlv_2, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1080:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1080:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1080:7: () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1080:7: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1081:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getPrimaryAccess().getNotAction_1_0(),
                                current);
                        

                    }

                    otherlv_4=(Token)match(input,26,FOLLOW_26_in_rulePrimary2643); 

                        	newLeafNode(otherlv_4, grammarAccess.getPrimaryAccess().getNOTKeyword_1_1());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1090:1: ( (lv_expression_5_0= rulePrimary ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1091:1: (lv_expression_5_0= rulePrimary )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1091:1: (lv_expression_5_0= rulePrimary )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1092:3: lv_expression_5_0= rulePrimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePrimary_in_rulePrimary2664);
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1110:5: this_Atomic_6= ruleAtomic
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleAtomic_in_rulePrimary2693);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1126:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1127:2: (iv_ruleAtomic= ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1128:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic2728);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic2738); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1135:1: ruleAtomic returns [EObject current=null] : ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1138:28: ( ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1139:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1139:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==11) ) {
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1139:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1139:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1139:3: () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')'
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1139:3: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1140:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,11,FOLLOW_11_in_ruleAtomic2785); 

                        	newLeafNode(otherlv_1, grammarAccess.getAtomicAccess().getDEFKeyword_0_1());
                        
                    otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleAtomic2797); 

                        	newLeafNode(otherlv_2, grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1153:1: ( (lv_name_3_0= ruleAttribute ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1154:1: (lv_name_3_0= ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1154:1: (lv_name_3_0= ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1155:3: lv_name_3_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2818);
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

                    otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleAtomic2830); 

                        	newLeafNode(otherlv_4, grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4());
                        

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1176:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1176:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1176:7: () ( (lv_name_6_0= ruleAttribute ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1176:7: ()
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1177:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeValueAction_1_0(),
                                current);
                        

                    }

                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1182:2: ( (lv_name_6_0= ruleAttribute ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1183:1: (lv_name_6_0= ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1183:1: (lv_name_6_0= ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1184:3: lv_name_6_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2868);
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


    // $ANTLR start "entryRuleCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1208:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1209:2: (iv_ruleCondition= ruleCondition EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1210:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_ruleCondition_in_entryRuleCondition2905);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCondition2915); 

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
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1217:1: ruleCondition returns [EObject current=null] : ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? ) ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_entityAchieveConditions_2_1 = null;

        EObject lv_entityAchieveConditions_2_2 = null;

        EObject lv_entityInvariantConditions_4_0 = null;

        EObject lv_entityDependenceConditions_6_0 = null;

        EObject lv_attributeAchieveConditions_8_0 = null;

        EObject lv_attributeInvariantConditions_10_0 = null;

        EObject lv_attributeDependenceConditions_12_0 = null;


         enterRule(); 
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1220:28: ( ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1221:1: ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1221:1: ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1221:2: () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )?
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1221:2: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1222:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getConditionAccess().getConditionAction_0(),
                        current);
                

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1227:2: (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==27) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1227:4: otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )*
                    {
                    otherlv_1=(Token)match(input,27,FOLLOW_27_in_ruleCondition2962); 

                        	newLeafNode(otherlv_1, grammarAccess.getConditionAccess().getENTITY_ACHIEVE_CONDITIONSKeyword_1_0());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1231:1: ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==11||LA12_0==14) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1232:1: ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1232:1: ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1233:1: (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1233:1: (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist )
                    	    int alt11=2;
                    	    int LA11_0 = input.LA(1);

                    	    if ( (LA11_0==11) ) {
                    	        alt11=1;
                    	    }
                    	    else if ( (LA11_0==14) ) {
                    	        alt11=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 11, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt11) {
                    	        case 1 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1234:3: lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionParserRuleCall_1_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleCondition2985);
                    	            lv_entityAchieveConditions_2_1=ruleEntityAchieveCondition();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"entityAchieveConditions",
                    	                    		lv_entityAchieveConditions_2_1, 
                    	                    		"EntityAchieveCondition");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1249:8: lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionExistParserRuleCall_1_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveConditionExist_in_ruleCondition3004);
                    	            lv_entityAchieveConditions_2_2=ruleEntityAchieveConditionExist();

                    	            state._fsp--;


                    	            	        if (current==null) {
                    	            	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	            	        }
                    	                   		add(
                    	                   			current, 
                    	                   			"entityAchieveConditions",
                    	                    		lv_entityAchieveConditions_2_2, 
                    	                    		"EntityAchieveConditionExist");
                    	            	        afterParserOrEnumRuleCall();
                    	            	    

                    	            }
                    	            break;

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

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1267:5: (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==28) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1267:7: otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )*
                    {
                    otherlv_3=(Token)match(input,28,FOLLOW_28_in_ruleCondition3023); 

                        	newLeafNode(otherlv_3, grammarAccess.getConditionAccess().getENTITY_INVARIANT_CONDITIONSKeyword_2_0());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1271:1: ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==15) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1272:1: (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1272:1: (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1273:3: lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getEntityInvariantConditionsEntityInvariantConditionParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleCondition3044);
                    	    lv_entityInvariantConditions_4_0=ruleEntityInvariantCondition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entityInvariantConditions",
                    	            		lv_entityInvariantConditions_4_0, 
                    	            		"EntityInvariantCondition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1289:5: (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==29) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1289:7: otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )*
                    {
                    otherlv_5=(Token)match(input,29,FOLLOW_29_in_ruleCondition3060); 

                        	newLeafNode(otherlv_5, grammarAccess.getConditionAccess().getENTITY_DEPENDENCE_CONDITIONSKeyword_3_0());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1293:1: ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==17) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1294:1: (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1294:1: (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1295:3: lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getEntityDependenceConditionsEntityDependenceConditionParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEntityDependenceCondition_in_ruleCondition3081);
                    	    lv_entityDependenceConditions_6_0=ruleEntityDependenceCondition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"entityDependenceConditions",
                    	            		lv_entityDependenceConditions_6_0, 
                    	            		"EntityDependenceCondition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

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

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1311:5: (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==30) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1311:7: otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )*
                    {
                    otherlv_7=(Token)match(input,30,FOLLOW_30_in_ruleCondition3097); 

                        	newLeafNode(otherlv_7, grammarAccess.getConditionAccess().getATTRIBUTE_ACHIEVE_CONDITIONSKeyword_4_0());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1315:1: ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==11||LA18_0==18) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1316:1: (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1316:1: (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1317:3: lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getAttributeAchieveConditionsAttributeAchieveConditionParserRuleCall_4_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_ruleCondition3118);
                    	    lv_attributeAchieveConditions_8_0=ruleAttributeAchieveCondition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"attributeAchieveConditions",
                    	            		lv_attributeAchieveConditions_8_0, 
                    	            		"AttributeAchieveCondition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1333:5: (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==31) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1333:7: otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )*
                    {
                    otherlv_9=(Token)match(input,31,FOLLOW_31_in_ruleCondition3134); 

                        	newLeafNode(otherlv_9, grammarAccess.getConditionAccess().getATTRIBUTE_INVARIANT_CONDITIONSKeyword_5_0());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1337:1: ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==19) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1338:1: (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1338:1: (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1339:3: lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getAttributeInvariantConditionsAttributeInvariantConditionParserRuleCall_5_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleCondition3155);
                    	    lv_attributeInvariantConditions_10_0=ruleAttributeInvariantCondition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"attributeInvariantConditions",
                    	            		lv_attributeInvariantConditions_10_0, 
                    	            		"AttributeInvariantCondition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1355:5: (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==32) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1355:7: otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )*
                    {
                    otherlv_11=(Token)match(input,32,FOLLOW_32_in_ruleCondition3171); 

                        	newLeafNode(otherlv_11, grammarAccess.getConditionAccess().getATTRIBUTE_DEPENDENCE_CONDITIONSKeyword_6_0());
                        
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1359:1: ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==17) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1360:1: (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition )
                    	    {
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1360:1: (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition )
                    	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.condition/src-gen/org/blended/condition/parser/antlr/internal/InternalCondition.g:1361:3: lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getAttributeDependenceConditionsAttributeDependenceConditionParserRuleCall_6_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAttributeDependenceCondition_in_ruleCondition3192);
                    	    lv_attributeDependenceConditions_12_0=ruleAttributeDependenceCondition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"attributeDependenceConditions",
                    	            		lv_attributeDependenceConditions_12_0, 
                    	            		"AttributeDependenceCondition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


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
    // $ANTLR end "ruleCondition"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleConditionModel_in_entryRuleConditionModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCondition_in_ruleConditionModel130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition165 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveCondition175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleEntityAchieveCondition212 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveCondition224 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityAchieveCondition241 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveCondition258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveConditionExist_in_entryRuleEntityAchieveConditionExist294 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveConditionExist304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleEntityAchieveConditionExist341 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveConditionExist353 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleEntityAchieveConditionExist365 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveConditionExist377 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityAchieveConditionExist394 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveConditionExist411 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveConditionExist423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition459 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityInvariantCondition469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleEntityInvariantCondition506 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityInvariantCondition518 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleEntityInvariantCondition539 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleEntityInvariantCondition551 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleCardinality_in_ruleEntityInvariantCondition572 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityInvariantCondition584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityDependenceCondition_in_entryRuleEntityDependenceCondition620 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityDependenceCondition630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleEntityDependenceCondition667 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityDependenceCondition679 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleEntityDependenceCondition691 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityDependenceCondition703 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityDependenceCondition720 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityDependenceCondition737 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleEntityDependenceCondition749 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleEntityDependenceCondition761 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityDependenceCondition773 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityDependenceCondition790 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityDependenceCondition807 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityDependenceCondition819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition855 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeAchieveCondition865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition974 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleNotMandatoryAttributeAchieveCondition1021 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition1033 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1054 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition1067 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1088 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition1138 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleMandatoryAttributeAchieveCondition1185 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1197 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleMandatoryAttributeAchieveCondition1209 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1221 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1242 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition1255 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1276 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1290 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition1338 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleAttributeInvariantCondition1385 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeInvariantCondition1397 = new BitSet(new long[]{0x0000000004001810L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition1418 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeInvariantCondition1430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeDependenceCondition_in_entryRuleAttributeDependenceCondition1466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeDependenceCondition1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleAttributeDependenceCondition1513 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeDependenceCondition1525 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAttributeDependenceCondition1537 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeDependenceCondition1549 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1570 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleAttributeDependenceCondition1583 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1604 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeDependenceCondition1618 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAttributeDependenceCondition1630 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAttributeDependenceCondition1642 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeDependenceCondition1654 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1675 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeDependenceCondition1687 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeDependenceCondition1699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality1736 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1814 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1832 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1875 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1893 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleCardinality1906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1929 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1947 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleCardinality1960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute2002 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute2013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2053 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleAttribute2071 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2086 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_ruleAttribute2105 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2120 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2169 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression2225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr2259 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr2269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr2316 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleOr2337 = new BitSet(new long[]{0x0000000004001810L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr2358 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd2396 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd2453 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleAnd2474 = new BitSet(new long[]{0x0000000004001810L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd2495 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary2533 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary2543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rulePrimary2581 = new BitSet(new long[]{0x0000000004001810L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePrimary2603 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePrimary2614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulePrimary2643 = new BitSet(new long[]{0x0000000004001810L});
    public static final BitSet FOLLOW_rulePrimary_in_rulePrimary2664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rulePrimary2693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic2728 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic2738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleAtomic2785 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAtomic2797 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2818 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAtomic2830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCondition_in_entryRuleCondition2905 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCondition2915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleCondition2962 = new BitSet(new long[]{0x00000001F0004802L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleCondition2985 = new BitSet(new long[]{0x00000001F0004802L});
    public static final BitSet FOLLOW_ruleEntityAchieveConditionExist_in_ruleCondition3004 = new BitSet(new long[]{0x00000001F0004802L});
    public static final BitSet FOLLOW_28_in_ruleCondition3023 = new BitSet(new long[]{0x00000001E0008002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleCondition3044 = new BitSet(new long[]{0x00000001E0008002L});
    public static final BitSet FOLLOW_29_in_ruleCondition3060 = new BitSet(new long[]{0x00000001C0020002L});
    public static final BitSet FOLLOW_ruleEntityDependenceCondition_in_ruleCondition3081 = new BitSet(new long[]{0x00000001C0020002L});
    public static final BitSet FOLLOW_30_in_ruleCondition3097 = new BitSet(new long[]{0x0000000180040802L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_ruleCondition3118 = new BitSet(new long[]{0x0000000180040802L});
    public static final BitSet FOLLOW_31_in_ruleCondition3134 = new BitSet(new long[]{0x0000000100080002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleCondition3155 = new BitSet(new long[]{0x0000000100080002L});
    public static final BitSet FOLLOW_32_in_ruleCondition3171 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleAttributeDependenceCondition_in_ruleCondition3192 = new BitSet(new long[]{0x0000000000020002L});

}