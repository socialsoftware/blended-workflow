package org.blended.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.blended.services.BlendedGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBlendedParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'DEF'", "'('", "')'", "'EXISTS'", "'MUL'", "','", "'DEP'", "'MAN'", "'RUL'", "'..'", "'*'", "'+'", "'.'", "'nothing'", "'OR'", "'AND'", "'NOT'", "'ENTITY_ACHIEVE_CONDITIONS'", "'ENTITY_INVARIANT_CONDITIONS'", "'ENTITY_DEPENDENCE_CONDITIONS'", "'ATTRIBUTE_ACHIEVE_CONDITIONS'", "'ATTRIBUTE_INVARIANT_CONDITIONS'", "'ATTRIBUTE_DEPENDENCE_CONDITIONS'", "':'", "'SUC'", "'ACT'", "'INV'", "'SUB'", "'PRE'", "'POST'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__39=39;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
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
    public static final int T__40=40;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalBlendedParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBlendedParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalBlendedParser.tokenNames; }
    public String getGrammarFileName() { return "../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g"; }



     	private BlendedGrammarAccess grammarAccess;
     	
        public InternalBlendedParser(TokenStream input, BlendedGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "BlendedModel";	
       	}
       	
       	@Override
       	protected BlendedGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleBlendedModel"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:67:1: entryRuleBlendedModel returns [EObject current=null] : iv_ruleBlendedModel= ruleBlendedModel EOF ;
    public final EObject entryRuleBlendedModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlendedModel = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:68:2: (iv_ruleBlendedModel= ruleBlendedModel EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:69:2: iv_ruleBlendedModel= ruleBlendedModel EOF
            {
             newCompositeNode(grammarAccess.getBlendedModelRule()); 
            pushFollow(FOLLOW_ruleBlendedModel_in_entryRuleBlendedModel75);
            iv_ruleBlendedModel=ruleBlendedModel();

            state._fsp--;

             current =iv_ruleBlendedModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBlendedModel85); 

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
    // $ANTLR end "entryRuleBlendedModel"


    // $ANTLR start "ruleBlendedModel"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:76:1: ruleBlendedModel returns [EObject current=null] : (this_Condition_0= ruleCondition | this_Goal_1= ruleGoal | this_Activity_2= ruleActivity ) ;
    public final EObject ruleBlendedModel() throws RecognitionException {
        EObject current = null;

        EObject this_Condition_0 = null;

        EObject this_Goal_1 = null;

        EObject this_Activity_2 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:79:28: ( (this_Condition_0= ruleCondition | this_Goal_1= ruleGoal | this_Activity_2= ruleActivity ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:80:1: (this_Condition_0= ruleCondition | this_Goal_1= ruleGoal | this_Activity_2= ruleActivity )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:80:1: (this_Condition_0= ruleCondition | this_Goal_1= ruleGoal | this_Activity_2= ruleActivity )
            int alt1=3;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==EOF||(LA1_0>=28 && LA1_0<=33)) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_ID) ) {
                int LA1_2 = input.LA(2);

                if ( (LA1_2==34) ) {
                    int LA1_3 = input.LA(3);

                    if ( (LA1_3==35) ) {
                        alt1=2;
                    }
                    else if ( (LA1_3==RULE_STRING) ) {
                        alt1=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:81:5: this_Condition_0= ruleCondition
                    {
                     
                            newCompositeNode(grammarAccess.getBlendedModelAccess().getConditionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleCondition_in_ruleBlendedModel132);
                    this_Condition_0=ruleCondition();

                    state._fsp--;

                     
                            current = this_Condition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:91:5: this_Goal_1= ruleGoal
                    {
                     
                            newCompositeNode(grammarAccess.getBlendedModelAccess().getGoalParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleGoal_in_ruleBlendedModel159);
                    this_Goal_1=ruleGoal();

                    state._fsp--;

                     
                            current = this_Goal_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:101:5: this_Activity_2= ruleActivity
                    {
                     
                            newCompositeNode(grammarAccess.getBlendedModelAccess().getActivityParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleActivity_in_ruleBlendedModel186);
                    this_Activity_2=ruleActivity();

                    state._fsp--;

                     
                            current = this_Activity_2; 
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
    // $ANTLR end "ruleBlendedModel"


    // $ANTLR start "entryRuleEntityAchieveCondition"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:117:1: entryRuleEntityAchieveCondition returns [EObject current=null] : iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF ;
    public final EObject entryRuleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityAchieveCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:118:2: (iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:119:2: iv_ruleEntityAchieveCondition= ruleEntityAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition221);
            iv_ruleEntityAchieveCondition=ruleEntityAchieveCondition();

            state._fsp--;

             current =iv_ruleEntityAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveCondition231); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:126:1: ruleEntityAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleEntityAchieveCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:129:28: ( (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:130:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:130:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:130:3: otherlv_0= 'DEF' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleEntityAchieveCondition268); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAchieveConditionAccess().getDEFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveCondition280); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:138:1: ( (lv_name_2_0= RULE_ID ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:139:1: (lv_name_2_0= RULE_ID )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:139:1: (lv_name_2_0= RULE_ID )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:140:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityAchieveCondition297); 

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

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveCondition314); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:168:1: entryRuleEntityAchieveConditionExist returns [EObject current=null] : iv_ruleEntityAchieveConditionExist= ruleEntityAchieveConditionExist EOF ;
    public final EObject entryRuleEntityAchieveConditionExist() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityAchieveConditionExist = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:169:2: (iv_ruleEntityAchieveConditionExist= ruleEntityAchieveConditionExist EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:170:2: iv_ruleEntityAchieveConditionExist= ruleEntityAchieveConditionExist EOF
            {
             newCompositeNode(grammarAccess.getEntityAchieveConditionExistRule()); 
            pushFollow(FOLLOW_ruleEntityAchieveConditionExist_in_entryRuleEntityAchieveConditionExist350);
            iv_ruleEntityAchieveConditionExist=ruleEntityAchieveConditionExist();

            state._fsp--;

             current =iv_ruleEntityAchieveConditionExist; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityAchieveConditionExist360); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:177:1: ruleEntityAchieveConditionExist returns [EObject current=null] : (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:180:28: ( (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:181:1: (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:181:1: (otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:181:3: otherlv_0= 'EXISTS' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleEntityAchieveConditionExist397); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAchieveConditionExistAccess().getEXISTSKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveConditionExist409); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleEntityAchieveConditionExist421); 

                	newLeafNode(otherlv_2, grammarAccess.getEntityAchieveConditionExistAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleEntityAchieveConditionExist433); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityAchieveConditionExistAccess().getLeftParenthesisKeyword_3());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:197:1: ( (lv_name_4_0= RULE_ID ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:198:1: (lv_name_4_0= RULE_ID )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:198:1: (lv_name_4_0= RULE_ID )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:199:3: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityAchieveConditionExist450); 

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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveConditionExist467); 

                	newLeafNode(otherlv_5, grammarAccess.getEntityAchieveConditionExistAccess().getRightParenthesisKeyword_5());
                
            otherlv_6=(Token)match(input,13,FOLLOW_13_in_ruleEntityAchieveConditionExist479); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:231:1: entryRuleEntityInvariantCondition returns [EObject current=null] : iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF ;
    public final EObject entryRuleEntityInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityInvariantCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:232:2: (iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:233:2: iv_ruleEntityInvariantCondition= ruleEntityInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition515);
            iv_ruleEntityInvariantCondition=ruleEntityInvariantCondition();

            state._fsp--;

             current =iv_ruleEntityInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityInvariantCondition525); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:240:1: ruleEntityInvariantCondition returns [EObject current=null] : (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:243:28: ( (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:244:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:244:1: (otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:244:3: otherlv_0= 'MUL' otherlv_1= '(' ( (lv_name_2_0= ruleAttribute ) ) otherlv_3= ',' ( (lv_cardinality_4_0= ruleCardinality ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleEntityInvariantCondition562); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityInvariantConditionAccess().getMULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityInvariantCondition574); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:252:1: ( (lv_name_2_0= ruleAttribute ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:253:1: (lv_name_2_0= ruleAttribute )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:253:1: (lv_name_2_0= ruleAttribute )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:254:3: lv_name_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getEntityInvariantConditionAccess().getNameAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleEntityInvariantCondition595);
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

            otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleEntityInvariantCondition607); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityInvariantConditionAccess().getCommaKeyword_3());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:274:1: ( (lv_cardinality_4_0= ruleCardinality ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:275:1: (lv_cardinality_4_0= ruleCardinality )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:275:1: (lv_cardinality_4_0= ruleCardinality )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:276:3: lv_cardinality_4_0= ruleCardinality
            {
             
            	        newCompositeNode(grammarAccess.getEntityInvariantConditionAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleCardinality_in_ruleEntityInvariantCondition628);
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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityInvariantCondition640); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:304:1: entryRuleEntityDependenceCondition returns [EObject current=null] : iv_ruleEntityDependenceCondition= ruleEntityDependenceCondition EOF ;
    public final EObject entryRuleEntityDependenceCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityDependenceCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:305:2: (iv_ruleEntityDependenceCondition= ruleEntityDependenceCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:306:2: iv_ruleEntityDependenceCondition= ruleEntityDependenceCondition EOF
            {
             newCompositeNode(grammarAccess.getEntityDependenceConditionRule()); 
            pushFollow(FOLLOW_ruleEntityDependenceCondition_in_entryRuleEntityDependenceCondition676);
            iv_ruleEntityDependenceCondition=ruleEntityDependenceCondition();

            state._fsp--;

             current =iv_ruleEntityDependenceCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntityDependenceCondition686); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:313:1: ruleEntityDependenceCondition returns [EObject current=null] : (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:316:28: ( (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:317:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:317:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:317:3: otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_entity1_4_0= RULE_ID ) ) otherlv_5= ')' otherlv_6= ',' otherlv_7= 'DEF' otherlv_8= '(' ( (lv_entity2_9_0= RULE_ID ) ) otherlv_10= ')' otherlv_11= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleEntityDependenceCondition723); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityDependenceConditionAccess().getDEPKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleEntityDependenceCondition735); 

                	newLeafNode(otherlv_1, grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleEntityDependenceCondition747); 

                	newLeafNode(otherlv_2, grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleEntityDependenceCondition759); 

                	newLeafNode(otherlv_3, grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_3());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:333:1: ( (lv_entity1_4_0= RULE_ID ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:334:1: (lv_entity1_4_0= RULE_ID )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:334:1: (lv_entity1_4_0= RULE_ID )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:335:3: lv_entity1_4_0= RULE_ID
            {
            lv_entity1_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityDependenceCondition776); 

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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleEntityDependenceCondition793); 

                	newLeafNode(otherlv_5, grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_5());
                
            otherlv_6=(Token)match(input,16,FOLLOW_16_in_ruleEntityDependenceCondition805); 

                	newLeafNode(otherlv_6, grammarAccess.getEntityDependenceConditionAccess().getCommaKeyword_6());
                
            otherlv_7=(Token)match(input,11,FOLLOW_11_in_ruleEntityDependenceCondition817); 

                	newLeafNode(otherlv_7, grammarAccess.getEntityDependenceConditionAccess().getDEFKeyword_7());
                
            otherlv_8=(Token)match(input,12,FOLLOW_12_in_ruleEntityDependenceCondition829); 

                	newLeafNode(otherlv_8, grammarAccess.getEntityDependenceConditionAccess().getLeftParenthesisKeyword_8());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:367:1: ( (lv_entity2_9_0= RULE_ID ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:368:1: (lv_entity2_9_0= RULE_ID )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:368:1: (lv_entity2_9_0= RULE_ID )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:369:3: lv_entity2_9_0= RULE_ID
            {
            lv_entity2_9_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntityDependenceCondition846); 

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

            otherlv_10=(Token)match(input,13,FOLLOW_13_in_ruleEntityDependenceCondition863); 

                	newLeafNode(otherlv_10, grammarAccess.getEntityDependenceConditionAccess().getRightParenthesisKeyword_10());
                
            otherlv_11=(Token)match(input,13,FOLLOW_13_in_ruleEntityDependenceCondition875); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:401:1: entryRuleAttributeAchieveCondition returns [EObject current=null] : iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF ;
    public final EObject entryRuleAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeAchieveCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:402:2: (iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:403:2: iv_ruleAttributeAchieveCondition= ruleAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition911);
            iv_ruleAttributeAchieveCondition=ruleAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeAchieveCondition921); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:410:1: ruleAttributeAchieveCondition returns [EObject current=null] : (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition ) ;
    public final EObject ruleAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject this_MandatoryAttributeAchieveCondition_0 = null;

        EObject this_NotMandatoryAttributeAchieveCondition_1 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:413:28: ( (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:414:1: (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:414:1: (this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition | this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==18) ) {
                alt2=1;
            }
            else if ( (LA2_0==11) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:415:5: this_MandatoryAttributeAchieveCondition_0= ruleMandatoryAttributeAchieveCondition
                    {
                     
                            newCompositeNode(grammarAccess.getAttributeAchieveConditionAccess().getMandatoryAttributeAchieveConditionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition968);
                    this_MandatoryAttributeAchieveCondition_0=ruleMandatoryAttributeAchieveCondition();

                    state._fsp--;

                     
                            current = this_MandatoryAttributeAchieveCondition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:425:5: this_NotMandatoryAttributeAchieveCondition_1= ruleNotMandatoryAttributeAchieveCondition
                    {
                     
                            newCompositeNode(grammarAccess.getAttributeAchieveConditionAccess().getNotMandatoryAttributeAchieveConditionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition995);
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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:441:1: entryRuleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleNotMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNotMandatoryAttributeAchieveCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:442:2: (iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:443:2: iv_ruleNotMandatoryAttributeAchieveCondition= ruleNotMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition1030);
            iv_ruleNotMandatoryAttributeAchieveCondition=ruleNotMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleNotMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition1040); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:450:1: ruleNotMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:453:28: ( (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:454:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:454:1: (otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:454:3: otherlv_0= 'DEF' otherlv_1= '(' ( (lv_conditions_2_0= ruleAttribute ) ) (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )* otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleNotMandatoryAttributeAchieveCondition1077); 

                	newLeafNode(otherlv_0, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getDEFKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition1089); 

                	newLeafNode(otherlv_1, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:462:1: ( (lv_conditions_2_0= ruleAttribute ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:463:1: (lv_conditions_2_0= ruleAttribute )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:463:1: (lv_conditions_2_0= ruleAttribute )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:464:3: lv_conditions_2_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1110);
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

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:480:2: (otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:480:4: otherlv_3= ',' ( (lv_conditions_4_0= ruleAttribute ) )
            	    {
            	    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition1123); 

            	        	newLeafNode(otherlv_3, grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getCommaKeyword_3_0());
            	        
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:484:1: ( (lv_conditions_4_0= ruleAttribute ) )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:485:1: (lv_conditions_4_0= ruleAttribute )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:485:1: (lv_conditions_4_0= ruleAttribute )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:486:3: lv_conditions_4_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNotMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1144);
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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition1158); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:514:1: entryRuleMandatoryAttributeAchieveCondition returns [EObject current=null] : iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF ;
    public final EObject entryRuleMandatoryAttributeAchieveCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatoryAttributeAchieveCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:515:2: (iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:516:2: iv_ruleMandatoryAttributeAchieveCondition= ruleMandatoryAttributeAchieveCondition EOF
            {
             newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionRule()); 
            pushFollow(FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition1194);
            iv_ruleMandatoryAttributeAchieveCondition=ruleMandatoryAttributeAchieveCondition();

            state._fsp--;

             current =iv_ruleMandatoryAttributeAchieveCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition1204); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:523:1: ruleMandatoryAttributeAchieveCondition returns [EObject current=null] : (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:526:28: ( (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:527:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:527:1: (otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:527:3: otherlv_0= 'MAN' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_conditions_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ')'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleMandatoryAttributeAchieveCondition1241); 

                	newLeafNode(otherlv_0, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getMANKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1253); 

                	newLeafNode(otherlv_1, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleMandatoryAttributeAchieveCondition1265); 

                	newLeafNode(otherlv_2, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1277); 

                	newLeafNode(otherlv_3, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getLeftParenthesisKeyword_3());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:543:1: ( (lv_conditions_4_0= ruleAttribute ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:544:1: (lv_conditions_4_0= ruleAttribute )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:544:1: (lv_conditions_4_0= ruleAttribute )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:545:3: lv_conditions_4_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1298);
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

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:561:2: (otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==16) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:561:4: otherlv_5= ',' ( (lv_conditions_6_0= ruleAttribute ) )
            	    {
            	    otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition1311); 

            	        	newLeafNode(otherlv_5, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getCommaKeyword_5_0());
            	        
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:565:1: ( (lv_conditions_6_0= ruleAttribute ) )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:566:1: (lv_conditions_6_0= ruleAttribute )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:566:1: (lv_conditions_6_0= ruleAttribute )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:567:3: lv_conditions_6_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getMandatoryAttributeAchieveConditionAccess().getConditionsAttributeParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1332);
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

            otherlv_7=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1346); 

                	newLeafNode(otherlv_7, grammarAccess.getMandatoryAttributeAchieveConditionAccess().getRightParenthesisKeyword_6());
                
            otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1358); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:599:1: entryRuleAttributeInvariantCondition returns [EObject current=null] : iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF ;
    public final EObject entryRuleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeInvariantCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:600:2: (iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:601:2: iv_ruleAttributeInvariantCondition= ruleAttributeInvariantCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeInvariantConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition1394);
            iv_ruleAttributeInvariantCondition=ruleAttributeInvariantCondition();

            state._fsp--;

             current =iv_ruleAttributeInvariantCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeInvariantCondition1404); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:608:1: ruleAttributeInvariantCondition returns [EObject current=null] : (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) ;
    public final EObject ruleAttributeInvariantCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:611:28: ( (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:612:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:612:1: (otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:612:3: otherlv_0= 'RUL' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleAttributeInvariantCondition1441); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeInvariantConditionAccess().getRULKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAttributeInvariantCondition1453); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeInvariantConditionAccess().getLeftParenthesisKeyword_1());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:620:1: ( (lv_expression_2_0= ruleExpression ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:621:1: (lv_expression_2_0= ruleExpression )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:621:1: (lv_expression_2_0= ruleExpression )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:622:3: lv_expression_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getAttributeInvariantConditionAccess().getExpressionExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition1474);
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

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAttributeInvariantCondition1486); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:650:1: entryRuleAttributeDependenceCondition returns [EObject current=null] : iv_ruleAttributeDependenceCondition= ruleAttributeDependenceCondition EOF ;
    public final EObject entryRuleAttributeDependenceCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeDependenceCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:651:2: (iv_ruleAttributeDependenceCondition= ruleAttributeDependenceCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:652:2: iv_ruleAttributeDependenceCondition= ruleAttributeDependenceCondition EOF
            {
             newCompositeNode(grammarAccess.getAttributeDependenceConditionRule()); 
            pushFollow(FOLLOW_ruleAttributeDependenceCondition_in_entryRuleAttributeDependenceCondition1522);
            iv_ruleAttributeDependenceCondition=ruleAttributeDependenceCondition();

            state._fsp--;

             current =iv_ruleAttributeDependenceCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeDependenceCondition1532); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:659:1: ruleAttributeDependenceCondition returns [EObject current=null] : (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:662:28: ( (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:663:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:663:1: (otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:663:3: otherlv_0= 'DEP' otherlv_1= '(' otherlv_2= 'DEF' otherlv_3= '(' ( (lv_attributes1_4_0= ruleAttribute ) ) (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )* otherlv_7= ')' otherlv_8= ',' otherlv_9= 'DEF' otherlv_10= '(' ( (lv_attribute2_11_0= ruleAttribute ) ) otherlv_12= ')' otherlv_13= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleAttributeDependenceCondition1569); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeDependenceConditionAccess().getDEPKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAttributeDependenceCondition1581); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_1());
                
            otherlv_2=(Token)match(input,11,FOLLOW_11_in_ruleAttributeDependenceCondition1593); 

                	newLeafNode(otherlv_2, grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleAttributeDependenceCondition1605); 

                	newLeafNode(otherlv_3, grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_3());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:679:1: ( (lv_attributes1_4_0= ruleAttribute ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:680:1: (lv_attributes1_4_0= ruleAttribute )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:680:1: (lv_attributes1_4_0= ruleAttribute )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:681:3: lv_attributes1_4_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1626);
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

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:697:2: (otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==16) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:697:4: otherlv_5= ',' ( (lv_attributes1_6_0= ruleAttribute ) )
            	    {
            	    otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleAttributeDependenceCondition1639); 

            	        	newLeafNode(otherlv_5, grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_5_0());
            	        
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:701:1: ( (lv_attributes1_6_0= ruleAttribute ) )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:702:1: (lv_attributes1_6_0= ruleAttribute )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:702:1: (lv_attributes1_6_0= ruleAttribute )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:703:3: lv_attributes1_6_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAttributeDependenceConditionAccess().getAttributes1AttributeParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1660);
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
            	    break loop5;
                }
            } while (true);

            otherlv_7=(Token)match(input,13,FOLLOW_13_in_ruleAttributeDependenceCondition1674); 

                	newLeafNode(otherlv_7, grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_6());
                
            otherlv_8=(Token)match(input,16,FOLLOW_16_in_ruleAttributeDependenceCondition1686); 

                	newLeafNode(otherlv_8, grammarAccess.getAttributeDependenceConditionAccess().getCommaKeyword_7());
                
            otherlv_9=(Token)match(input,11,FOLLOW_11_in_ruleAttributeDependenceCondition1698); 

                	newLeafNode(otherlv_9, grammarAccess.getAttributeDependenceConditionAccess().getDEFKeyword_8());
                
            otherlv_10=(Token)match(input,12,FOLLOW_12_in_ruleAttributeDependenceCondition1710); 

                	newLeafNode(otherlv_10, grammarAccess.getAttributeDependenceConditionAccess().getLeftParenthesisKeyword_9());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:735:1: ( (lv_attribute2_11_0= ruleAttribute ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:736:1: (lv_attribute2_11_0= ruleAttribute )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:736:1: (lv_attribute2_11_0= ruleAttribute )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:737:3: lv_attribute2_11_0= ruleAttribute
            {
             
            	        newCompositeNode(grammarAccess.getAttributeDependenceConditionAccess().getAttribute2AttributeParserRuleCall_10_0()); 
            	    
            pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1731);
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

            otherlv_12=(Token)match(input,13,FOLLOW_13_in_ruleAttributeDependenceCondition1743); 

                	newLeafNode(otherlv_12, grammarAccess.getAttributeDependenceConditionAccess().getRightParenthesisKeyword_11());
                
            otherlv_13=(Token)match(input,13,FOLLOW_13_in_ruleAttributeDependenceCondition1755); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:769:1: entryRuleCardinality returns [String current=null] : iv_ruleCardinality= ruleCardinality EOF ;
    public final String entryRuleCardinality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCardinality = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:770:2: (iv_ruleCardinality= ruleCardinality EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:771:2: iv_ruleCardinality= ruleCardinality EOF
            {
             newCompositeNode(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality1792);
            iv_ruleCardinality=ruleCardinality();

            state._fsp--;

             current =iv_ruleCardinality.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality1803); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:778:1: ruleCardinality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:781:28: ( (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:782:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:782:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            int alt6=4;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_INT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EOF||LA6_1==13) ) {
                    alt6=1;
                }
                else if ( (LA6_1==20) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt6=2;
                        }
                        break;
                    case 21:
                        {
                        alt6=3;
                        }
                        break;
                    case 22:
                        {
                        alt6=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 3, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:782:6: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1843); 

                    		current.merge(this_INT_0);
                        
                     
                        newLeafNode(this_INT_0, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:790:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:790:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:790:11: this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1870); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1888); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1903); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:811:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:811:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:811:11: this_INT_4= RULE_INT kw= '..' kw= '*'
                    {
                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1931); 

                    		current.merge(this_INT_4);
                        
                     
                        newLeafNode(this_INT_4, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality1949); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
                        
                    kw=(Token)match(input,21,FOLLOW_21_in_ruleCardinality1962); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:831:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:831:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:831:11: this_INT_7= RULE_INT kw= '..' kw= '+'
                    {
                    this_INT_7=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1985); 

                    		current.merge(this_INT_7);
                        
                     
                        newLeafNode(this_INT_7, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
                        
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleCardinality2003); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
                        
                    kw=(Token)match(input,22,FOLLOW_22_in_ruleCardinality2016); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:858:1: entryRuleAttribute returns [String current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final String entryRuleAttribute() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAttribute = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:859:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:860:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute2058);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute2069); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:867:1: ruleAttribute returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleAttribute() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:870:28: ( (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:871:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:871:1: (this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )* )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:871:6: this_ID_0= RULE_ID kw= '.' this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2109); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_0()); 
                
            kw=(Token)match(input,23,FOLLOW_23_in_ruleAttribute2127); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_1()); 
                
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2142); 

            		current.merge(this_ID_2);
                
             
                newLeafNode(this_ID_2, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_2()); 
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:891:1: (kw= '.' this_ID_4= RULE_ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:892:2: kw= '.' this_ID_4= RULE_ID
            	    {
            	    kw=(Token)match(input,23,FOLLOW_23_in_ruleAttribute2161); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getAttributeAccess().getFullStopKeyword_3_0()); 
            	        
            	    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2176); 

            	    		current.merge(this_ID_4);
            	        
            	     
            	        newLeafNode(this_ID_4, grammarAccess.getAttributeAccess().getIDTerminalRuleCall_3_1()); 
            	        

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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleNothing"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:912:1: entryRuleNothing returns [EObject current=null] : iv_ruleNothing= ruleNothing EOF ;
    public final EObject entryRuleNothing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNothing = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:913:2: (iv_ruleNothing= ruleNothing EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:914:2: iv_ruleNothing= ruleNothing EOF
            {
             newCompositeNode(grammarAccess.getNothingRule()); 
            pushFollow(FOLLOW_ruleNothing_in_entryRuleNothing2223);
            iv_ruleNothing=ruleNothing();

            state._fsp--;

             current =iv_ruleNothing; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNothing2233); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:921:1: ruleNothing returns [EObject current=null] : ( (lv_name_0_0= 'nothing' ) ) ;
    public final EObject ruleNothing() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;

         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:924:28: ( ( (lv_name_0_0= 'nothing' ) ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:925:1: ( (lv_name_0_0= 'nothing' ) )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:925:1: ( (lv_name_0_0= 'nothing' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:926:1: (lv_name_0_0= 'nothing' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:926:1: (lv_name_0_0= 'nothing' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:927:3: lv_name_0_0= 'nothing'
            {
            lv_name_0_0=(Token)match(input,24,FOLLOW_24_in_ruleNothing2275); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:948:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:949:2: (iv_ruleExpression= ruleExpression EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:950:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2323);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2333); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:957:1: ruleExpression returns [EObject current=null] : this_Or_0= ruleOr ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Or_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:960:28: (this_Or_0= ruleOr )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:962:5: this_Or_0= ruleOr
            {
             
                    newCompositeNode(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleOr_in_ruleExpression2379);
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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:978:1: entryRuleOr returns [EObject current=null] : iv_ruleOr= ruleOr EOF ;
    public final EObject entryRuleOr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:979:2: (iv_ruleOr= ruleOr EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:980:2: iv_ruleOr= ruleOr EOF
            {
             newCompositeNode(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr2413);
            iv_ruleOr=ruleOr();

            state._fsp--;

             current =iv_ruleOr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr2423); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:987:1: ruleOr returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleOr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:990:28: ( (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:991:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:991:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:992:5: this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnd_in_ruleOr2470);
            this_And_0=ruleAnd();

            state._fsp--;

             
                    current = this_And_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1000:1: ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==25) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1000:2: () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1000:2: ()
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1001:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getOrAccess().getOrLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleOr2491); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOrAccess().getORKeyword_1_1());
            	        
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1010:1: ( (lv_right_3_0= ruleAnd ) )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1011:1: (lv_right_3_0= ruleAnd )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1011:1: (lv_right_3_0= ruleAnd )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1012:3: lv_right_3_0= ruleAnd
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnd_in_ruleOr2512);
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
    // $ANTLR end "ruleOr"


    // $ANTLR start "entryRuleAnd"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1036:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1037:2: (iv_ruleAnd= ruleAnd EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1038:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd2550);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd2560); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1045:1: ruleAnd returns [EObject current=null] : (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Primary_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1048:28: ( (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1049:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1049:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1050:5: this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePrimary_in_ruleAnd2607);
            this_Primary_0=rulePrimary();

            state._fsp--;

             
                    current = this_Primary_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1058:1: ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==26) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1058:2: () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1058:2: ()
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1059:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,26,FOLLOW_26_in_ruleAnd2628); 

            	        	newLeafNode(otherlv_2, grammarAccess.getAndAccess().getANDKeyword_1_1());
            	        
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1068:1: ( (lv_right_3_0= rulePrimary ) )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1069:1: (lv_right_3_0= rulePrimary )
            	    {
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1069:1: (lv_right_3_0= rulePrimary )
            	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1070:3: lv_right_3_0= rulePrimary
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePrimary_in_ruleAnd2649);
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
            	    break loop9;
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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1094:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1095:2: (iv_rulePrimary= rulePrimary EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1096:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary2687);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary2697); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1103:1: rulePrimary returns [EObject current=null] : ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1106:28: ( ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1107:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1107:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            int alt10=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt10=1;
                }
                break;
            case 27:
                {
                alt10=2;
                }
                break;
            case RULE_ID:
            case 11:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1107:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1107:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1107:4: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_12_in_rulePrimary2735); 

                        	newLeafNode(otherlv_0, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0());
                        
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleExpression_in_rulePrimary2757);
                    this_Expression_1=ruleExpression();

                    state._fsp--;

                     
                            current = this_Expression_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_rulePrimary2768); 

                        	newLeafNode(otherlv_2, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1125:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1125:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1125:7: () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1125:7: ()
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1126:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getPrimaryAccess().getNotAction_1_0(),
                                current);
                        

                    }

                    otherlv_4=(Token)match(input,27,FOLLOW_27_in_rulePrimary2797); 

                        	newLeafNode(otherlv_4, grammarAccess.getPrimaryAccess().getNOTKeyword_1_1());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1135:1: ( (lv_expression_5_0= rulePrimary ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1136:1: (lv_expression_5_0= rulePrimary )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1136:1: (lv_expression_5_0= rulePrimary )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1137:3: lv_expression_5_0= rulePrimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePrimary_in_rulePrimary2818);
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
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1155:5: this_Atomic_6= ruleAtomic
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleAtomic_in_rulePrimary2847);
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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1171:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1172:2: (iv_ruleAtomic= ruleAtomic EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1173:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic2882);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic2892); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1180:1: ruleAtomic returns [EObject current=null] : ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1183:28: ( ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1184:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1184:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleAttribute ) ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==11) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_ID) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1184:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1184:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')' )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1184:3: () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleAttribute ) ) otherlv_4= ')'
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1184:3: ()
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1185:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,11,FOLLOW_11_in_ruleAtomic2939); 

                        	newLeafNode(otherlv_1, grammarAccess.getAtomicAccess().getDEFKeyword_0_1());
                        
                    otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleAtomic2951); 

                        	newLeafNode(otherlv_2, grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1198:1: ( (lv_name_3_0= ruleAttribute ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1199:1: (lv_name_3_0= ruleAttribute )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1199:1: (lv_name_3_0= ruleAttribute )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1200:3: lv_name_3_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_0_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic2972);
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

                    otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleAtomic2984); 

                        	newLeafNode(otherlv_4, grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1221:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1221:6: ( () ( (lv_name_6_0= ruleAttribute ) ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1221:7: () ( (lv_name_6_0= ruleAttribute ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1221:7: ()
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1222:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeValueAction_1_0(),
                                current);
                        

                    }

                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1227:2: ( (lv_name_6_0= ruleAttribute ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1228:1: (lv_name_6_0= ruleAttribute )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1228:1: (lv_name_6_0= ruleAttribute )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1229:3: lv_name_6_0= ruleAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameAttributeParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAttribute_in_ruleAtomic3022);
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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1253:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1254:2: (iv_ruleCondition= ruleCondition EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1255:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_ruleCondition_in_entryRuleCondition3059);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCondition3069); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1262:1: ruleCondition returns [EObject current=null] : ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1265:28: ( ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1266:1: ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1266:1: ( () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )? )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1266:2: () (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )? (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )? (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )? (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )? (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )? (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )?
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1266:2: ()
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1267:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getConditionAccess().getConditionAction_0(),
                        current);
                

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1272:2: (otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )* )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1272:4: otherlv_1= 'ENTITY_ACHIEVE_CONDITIONS' ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )*
                    {
                    otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleCondition3116); 

                        	newLeafNode(otherlv_1, grammarAccess.getConditionAccess().getENTITY_ACHIEVE_CONDITIONSKeyword_1_0());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1276:1: ( ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==11||LA13_0==14) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1277:1: ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1277:1: ( (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1278:1: (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1278:1: (lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition | lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist )
                    	    int alt12=2;
                    	    int LA12_0 = input.LA(1);

                    	    if ( (LA12_0==11) ) {
                    	        alt12=1;
                    	    }
                    	    else if ( (LA12_0==14) ) {
                    	        alt12=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 12, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt12) {
                    	        case 1 :
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1279:3: lv_entityAchieveConditions_2_1= ruleEntityAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionParserRuleCall_1_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleCondition3139);
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
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1294:8: lv_entityAchieveConditions_2_2= ruleEntityAchieveConditionExist
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getConditionAccess().getEntityAchieveConditionsEntityAchieveConditionExistParserRuleCall_1_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveConditionExist_in_ruleCondition3158);
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
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1312:5: (otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==29) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1312:7: otherlv_3= 'ENTITY_INVARIANT_CONDITIONS' ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )*
                    {
                    otherlv_3=(Token)match(input,29,FOLLOW_29_in_ruleCondition3177); 

                        	newLeafNode(otherlv_3, grammarAccess.getConditionAccess().getENTITY_INVARIANT_CONDITIONSKeyword_2_0());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1316:1: ( (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==15) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1317:1: (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1317:1: (lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1318:3: lv_entityInvariantConditions_4_0= ruleEntityInvariantCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getEntityInvariantConditionsEntityInvariantConditionParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleCondition3198);
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
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1334:5: (otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==30) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1334:7: otherlv_5= 'ENTITY_DEPENDENCE_CONDITIONS' ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )*
                    {
                    otherlv_5=(Token)match(input,30,FOLLOW_30_in_ruleCondition3214); 

                        	newLeafNode(otherlv_5, grammarAccess.getConditionAccess().getENTITY_DEPENDENCE_CONDITIONSKeyword_3_0());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1338:1: ( (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==17) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1339:1: (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1339:1: (lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1340:3: lv_entityDependenceConditions_6_0= ruleEntityDependenceCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getEntityDependenceConditionsEntityDependenceConditionParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEntityDependenceCondition_in_ruleCondition3235);
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
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1356:5: (otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==31) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1356:7: otherlv_7= 'ATTRIBUTE_ACHIEVE_CONDITIONS' ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )*
                    {
                    otherlv_7=(Token)match(input,31,FOLLOW_31_in_ruleCondition3251); 

                        	newLeafNode(otherlv_7, grammarAccess.getConditionAccess().getATTRIBUTE_ACHIEVE_CONDITIONSKeyword_4_0());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1360:1: ( (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==11||LA19_0==18) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1361:1: (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1361:1: (lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1362:3: lv_attributeAchieveConditions_8_0= ruleAttributeAchieveCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getAttributeAchieveConditionsAttributeAchieveConditionParserRuleCall_4_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_ruleCondition3272);
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
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1378:5: (otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==32) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1378:7: otherlv_9= 'ATTRIBUTE_INVARIANT_CONDITIONS' ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )*
                    {
                    otherlv_9=(Token)match(input,32,FOLLOW_32_in_ruleCondition3288); 

                        	newLeafNode(otherlv_9, grammarAccess.getConditionAccess().getATTRIBUTE_INVARIANT_CONDITIONSKeyword_5_0());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1382:1: ( (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==19) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1383:1: (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1383:1: (lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1384:3: lv_attributeInvariantConditions_10_0= ruleAttributeInvariantCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getAttributeInvariantConditionsAttributeInvariantConditionParserRuleCall_5_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleCondition3309);
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
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1400:5: (otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )* )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==33) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1400:7: otherlv_11= 'ATTRIBUTE_DEPENDENCE_CONDITIONS' ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )*
                    {
                    otherlv_11=(Token)match(input,33,FOLLOW_33_in_ruleCondition3325); 

                        	newLeafNode(otherlv_11, grammarAccess.getConditionAccess().getATTRIBUTE_DEPENDENCE_CONDITIONSKeyword_6_0());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1404:1: ( (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==17) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1405:1: (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1405:1: (lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1406:3: lv_attributeDependenceConditions_12_0= ruleAttributeDependenceCondition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getConditionAccess().getAttributeDependenceConditionsAttributeDependenceConditionParserRuleCall_6_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAttributeDependenceCondition_in_ruleCondition3346);
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
                    	    break loop23;
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


    // $ANTLR start "entryRuleGoal"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1430:1: entryRuleGoal returns [EObject current=null] : iv_ruleGoal= ruleGoal EOF ;
    public final EObject entryRuleGoal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoal = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1431:2: (iv_ruleGoal= ruleGoal EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1432:2: iv_ruleGoal= ruleGoal EOF
            {
             newCompositeNode(grammarAccess.getGoalRule()); 
            pushFollow(FOLLOW_ruleGoal_in_entryRuleGoal3385);
            iv_ruleGoal=ruleGoal();

            state._fsp--;

             current =iv_ruleGoal; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGoal3395); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1439:1: ruleGoal returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1442:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1443:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1443:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )? )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1443:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= 'SUC' otherlv_3= '(' ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) ) otherlv_5= ')' (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )? (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )? (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )?
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1443:2: ( (lv_name_0_0= RULE_ID ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1444:1: (lv_name_0_0= RULE_ID )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1444:1: (lv_name_0_0= RULE_ID )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1445:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGoal3437); 

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

            otherlv_1=(Token)match(input,34,FOLLOW_34_in_ruleGoal3454); 

                	newLeafNode(otherlv_1, grammarAccess.getGoalAccess().getColonKeyword_1());
                
            otherlv_2=(Token)match(input,35,FOLLOW_35_in_ruleGoal3466); 

                	newLeafNode(otherlv_2, grammarAccess.getGoalAccess().getSUCKeyword_2());
                
            otherlv_3=(Token)match(input,12,FOLLOW_12_in_ruleGoal3478); 

                	newLeafNode(otherlv_3, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_3());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1473:1: ( ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1474:1: ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1474:1: ( (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1475:1: (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1475:1: (lv_sucessCondition_4_1= ruleEntityAchieveCondition | lv_sucessCondition_4_2= ruleAttributeAchieveCondition | lv_sucessCondition_4_3= ruleNothing )
            int alt25=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==12) ) {
                    int LA25_4 = input.LA(3);

                    if ( (LA25_4==RULE_ID) ) {
                        int LA25_5 = input.LA(4);

                        if ( (LA25_5==23) ) {
                            alt25=2;
                        }
                        else if ( (LA25_5==13) ) {
                            alt25=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 25, 5, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;
                }
                }
                break;
            case 18:
                {
                alt25=2;
                }
                break;
            case 24:
                {
                alt25=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1476:3: lv_sucessCondition_4_1= ruleEntityAchieveCondition
                    {
                     
                    	        newCompositeNode(grammarAccess.getGoalAccess().getSucessConditionEntityAchieveConditionParserRuleCall_4_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleGoal3501);
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
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1491:8: lv_sucessCondition_4_2= ruleAttributeAchieveCondition
                    {
                     
                    	        newCompositeNode(grammarAccess.getGoalAccess().getSucessConditionAttributeAchieveConditionParserRuleCall_4_0_1()); 
                    	    
                    pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal3520);
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
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1506:8: lv_sucessCondition_4_3= ruleNothing
                    {
                     
                    	        newCompositeNode(grammarAccess.getGoalAccess().getSucessConditionNothingParserRuleCall_4_0_2()); 
                    	    
                    pushFollow(FOLLOW_ruleNothing_in_ruleGoal3539);
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

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleGoal3554); 

                	newLeafNode(otherlv_5, grammarAccess.getGoalAccess().getRightParenthesisKeyword_5());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1528:1: (otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==16) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==36) ) {
                    alt27=1;
                }
            }
            switch (alt27) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1528:3: otherlv_6= ',' otherlv_7= 'ACT' otherlv_8= '(' ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) ) otherlv_10= ')'
                    {
                    otherlv_6=(Token)match(input,16,FOLLOW_16_in_ruleGoal3567); 

                        	newLeafNode(otherlv_6, grammarAccess.getGoalAccess().getCommaKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,36,FOLLOW_36_in_ruleGoal3579); 

                        	newLeafNode(otherlv_7, grammarAccess.getGoalAccess().getACTKeyword_6_1());
                        
                    otherlv_8=(Token)match(input,12,FOLLOW_12_in_ruleGoal3591); 

                        	newLeafNode(otherlv_8, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_6_2());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1540:1: ( ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1541:1: ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1541:1: ( (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1542:1: (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1542:1: (lv_activationCondition_9_1= ruleEntityAchieveCondition | lv_activationCondition_9_2= ruleAttributeAchieveCondition )
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==11) ) {
                        int LA26_1 = input.LA(2);

                        if ( (LA26_1==12) ) {
                            int LA26_3 = input.LA(3);

                            if ( (LA26_3==RULE_ID) ) {
                                int LA26_4 = input.LA(4);

                                if ( (LA26_4==23) ) {
                                    alt26=2;
                                }
                                else if ( (LA26_4==13) ) {
                                    alt26=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 26, 4, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 26, 3, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 26, 1, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA26_0==18) ) {
                        alt26=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 0, input);

                        throw nvae;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1543:3: lv_activationCondition_9_1= ruleEntityAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getActivationConditionEntityAchieveConditionParserRuleCall_6_3_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleGoal3614);
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
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1558:8: lv_activationCondition_9_2= ruleAttributeAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getActivationConditionAttributeAchieveConditionParserRuleCall_6_3_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal3633);
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

                    otherlv_10=(Token)match(input,13,FOLLOW_13_in_ruleGoal3648); 

                        	newLeafNode(otherlv_10, grammarAccess.getGoalAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1580:3: (otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==16) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==37) ) {
                    alt31=1;
                }
            }
            switch (alt31) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1580:5: otherlv_11= ',' otherlv_12= 'INV' otherlv_13= '(' ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) ) (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )* otherlv_17= ')'
                    {
                    otherlv_11=(Token)match(input,16,FOLLOW_16_in_ruleGoal3663); 

                        	newLeafNode(otherlv_11, grammarAccess.getGoalAccess().getCommaKeyword_7_0());
                        
                    otherlv_12=(Token)match(input,37,FOLLOW_37_in_ruleGoal3675); 

                        	newLeafNode(otherlv_12, grammarAccess.getGoalAccess().getINVKeyword_7_1());
                        
                    otherlv_13=(Token)match(input,12,FOLLOW_12_in_ruleGoal3687); 

                        	newLeafNode(otherlv_13, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_7_2());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1592:1: ( ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1593:1: ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1593:1: ( (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1594:1: (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1594:1: (lv_invariantConditions_14_1= ruleEntityInvariantCondition | lv_invariantConditions_14_2= ruleAttributeInvariantCondition )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==15) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==19) ) {
                        alt28=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1595:3: lv_invariantConditions_14_1= ruleEntityInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_3_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleGoal3710);
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
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1610:8: lv_invariantConditions_14_2= ruleAttributeInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_3_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal3729);
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

                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1628:2: (otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==16) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1628:4: otherlv_15= ',' ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) )
                    	    {
                    	    otherlv_15=(Token)match(input,16,FOLLOW_16_in_ruleGoal3745); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getGoalAccess().getCommaKeyword_7_4_0());
                    	        
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1632:1: ( ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1633:1: ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1633:1: ( (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1634:1: (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1634:1: (lv_invariantConditions_16_1= ruleEntityInvariantCondition | lv_invariantConditions_16_2= ruleAttributeInvariantCondition )
                    	    int alt29=2;
                    	    int LA29_0 = input.LA(1);

                    	    if ( (LA29_0==15) ) {
                    	        alt29=1;
                    	    }
                    	    else if ( (LA29_0==19) ) {
                    	        alt29=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 29, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt29) {
                    	        case 1 :
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1635:3: lv_invariantConditions_16_1= ruleEntityInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsEntityInvariantConditionParserRuleCall_7_4_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleGoal3768);
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
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1650:8: lv_invariantConditions_16_2= ruleAttributeInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getGoalAccess().getInvariantConditionsAttributeInvariantConditionParserRuleCall_7_4_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal3787);
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
                    	    break loop30;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,13,FOLLOW_13_in_ruleGoal3804); 

                        	newLeafNode(otherlv_17, grammarAccess.getGoalAccess().getRightParenthesisKeyword_7_5());
                        

                    }
                    break;

            }

            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1672:3: (otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==16) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1672:5: otherlv_18= ',' otherlv_19= 'SUB' otherlv_20= '(' ( (otherlv_21= RULE_ID ) ) (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )* otherlv_24= ')'
                    {
                    otherlv_18=(Token)match(input,16,FOLLOW_16_in_ruleGoal3819); 

                        	newLeafNode(otherlv_18, grammarAccess.getGoalAccess().getCommaKeyword_8_0());
                        
                    otherlv_19=(Token)match(input,38,FOLLOW_38_in_ruleGoal3831); 

                        	newLeafNode(otherlv_19, grammarAccess.getGoalAccess().getSUBKeyword_8_1());
                        
                    otherlv_20=(Token)match(input,12,FOLLOW_12_in_ruleGoal3843); 

                        	newLeafNode(otherlv_20, grammarAccess.getGoalAccess().getLeftParenthesisKeyword_8_2());
                        
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1684:1: ( (otherlv_21= RULE_ID ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1685:1: (otherlv_21= RULE_ID )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1685:1: (otherlv_21= RULE_ID )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1686:3: otherlv_21= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getGoalRule());
                    	        }
                            
                    otherlv_21=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGoal3863); 

                    		newLeafNode(otherlv_21, grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_3_0()); 
                    	

                    }


                    }

                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1697:2: (otherlv_22= ',' ( (otherlv_23= RULE_ID ) ) )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==16) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1697:4: otherlv_22= ',' ( (otherlv_23= RULE_ID ) )
                    	    {
                    	    otherlv_22=(Token)match(input,16,FOLLOW_16_in_ruleGoal3876); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getGoalAccess().getCommaKeyword_8_4_0());
                    	        
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1701:1: ( (otherlv_23= RULE_ID ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1702:1: (otherlv_23= RULE_ID )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1702:1: (otherlv_23= RULE_ID )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1703:3: otherlv_23= RULE_ID
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGoalRule());
                    	    	        }
                    	            
                    	    otherlv_23=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGoal3896); 

                    	    		newLeafNode(otherlv_23, grammarAccess.getGoalAccess().getChildrenGoalsGoalCrossReference_8_4_1_0()); 
                    	    	

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop32;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,13,FOLLOW_13_in_ruleGoal3910); 

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


    // $ANTLR start "entryRuleActivity"
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1726:1: entryRuleActivity returns [EObject current=null] : iv_ruleActivity= ruleActivity EOF ;
    public final EObject entryRuleActivity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActivity = null;


        try {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1727:2: (iv_ruleActivity= ruleActivity EOF )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1728:2: iv_ruleActivity= ruleActivity EOF
            {
             newCompositeNode(grammarAccess.getActivityRule()); 
            pushFollow(FOLLOW_ruleActivity_in_entryRuleActivity3948);
            iv_ruleActivity=ruleActivity();

            state._fsp--;

             current =iv_ruleActivity; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleActivity3958); 

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
    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1735:1: ruleActivity returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' ) ;
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
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1738:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1739:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1739:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')' )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1739:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_description_2_0= RULE_STRING ) ) otherlv_3= 'PRE' otherlv_4= '(' ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )? otherlv_8= ')' otherlv_9= ',' otherlv_10= 'POST' otherlv_11= '(' ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )? otherlv_15= ')'
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1739:2: ( (lv_name_0_0= RULE_ID ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1740:1: (lv_name_0_0= RULE_ID )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1740:1: (lv_name_0_0= RULE_ID )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1741:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleActivity4000); 

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

            otherlv_1=(Token)match(input,34,FOLLOW_34_in_ruleActivity4017); 

                	newLeafNode(otherlv_1, grammarAccess.getActivityAccess().getColonKeyword_1());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1761:1: ( (lv_description_2_0= RULE_STRING ) )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1762:1: (lv_description_2_0= RULE_STRING )
            {
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1762:1: (lv_description_2_0= RULE_STRING )
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1763:3: lv_description_2_0= RULE_STRING
            {
            lv_description_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleActivity4034); 

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

            otherlv_3=(Token)match(input,39,FOLLOW_39_in_ruleActivity4051); 

                	newLeafNode(otherlv_3, grammarAccess.getActivityAccess().getPREKeyword_3());
                
            otherlv_4=(Token)match(input,12,FOLLOW_12_in_ruleActivity4063); 

                	newLeafNode(otherlv_4, grammarAccess.getActivityAccess().getLeftParenthesisKeyword_4());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1787:1: ( ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )* )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==11) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1787:2: ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )*
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1787:2: ( ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1788:1: ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1788:1: ( (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1789:1: (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1789:1: (lv_pre_5_1= ruleEntityAchieveCondition | lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition )
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==11) ) {
                        int LA34_1 = input.LA(2);

                        if ( (LA34_1==12) ) {
                            int LA34_2 = input.LA(3);

                            if ( (LA34_2==RULE_ID) ) {
                                int LA34_3 = input.LA(4);

                                if ( (LA34_3==23) ) {
                                    alt34=2;
                                }
                                else if ( (LA34_3==13) ) {
                                    alt34=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 34, 3, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 34, 2, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 34, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 0, input);

                        throw nvae;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1790:3: lv_pre_5_1= ruleEntityAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4087);
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
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1805:8: lv_pre_5_2= ruleNotMandatoryAttributeAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4106);
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

                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1823:2: (otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==16) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1823:4: otherlv_6= ',' ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) )
                    	    {
                    	    otherlv_6=(Token)match(input,16,FOLLOW_16_in_ruleActivity4122); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getActivityAccess().getCommaKeyword_5_1_0());
                    	        
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1827:1: ( ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1828:1: ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1828:1: ( (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1829:1: (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1829:1: (lv_pre_7_1= ruleEntityAchieveCondition | lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition )
                    	    int alt35=2;
                    	    int LA35_0 = input.LA(1);

                    	    if ( (LA35_0==11) ) {
                    	        int LA35_1 = input.LA(2);

                    	        if ( (LA35_1==12) ) {
                    	            int LA35_2 = input.LA(3);

                    	            if ( (LA35_2==RULE_ID) ) {
                    	                int LA35_3 = input.LA(4);

                    	                if ( (LA35_3==13) ) {
                    	                    alt35=1;
                    	                }
                    	                else if ( (LA35_3==23) ) {
                    	                    alt35=2;
                    	                }
                    	                else {
                    	                    NoViableAltException nvae =
                    	                        new NoViableAltException("", 35, 3, input);

                    	                    throw nvae;
                    	                }
                    	            }
                    	            else {
                    	                NoViableAltException nvae =
                    	                    new NoViableAltException("", 35, 2, input);

                    	                throw nvae;
                    	            }
                    	        }
                    	        else {
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 35, 1, input);

                    	            throw nvae;
                    	        }
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 35, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt35) {
                    	        case 1 :
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1830:3: lv_pre_7_1= ruleEntityAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPreEntityAchieveConditionParserRuleCall_5_1_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4145);
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
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1845:8: lv_pre_7_2= ruleNotMandatoryAttributeAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPreNotMandatoryAttributeAchieveConditionParserRuleCall_5_1_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4164);
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
                    	    break loop36;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleActivity4183); 

                	newLeafNode(otherlv_8, grammarAccess.getActivityAccess().getRightParenthesisKeyword_6());
                
            otherlv_9=(Token)match(input,16,FOLLOW_16_in_ruleActivity4195); 

                	newLeafNode(otherlv_9, grammarAccess.getActivityAccess().getCommaKeyword_7());
                
            otherlv_10=(Token)match(input,40,FOLLOW_40_in_ruleActivity4207); 

                	newLeafNode(otherlv_10, grammarAccess.getActivityAccess().getPOSTKeyword_8());
                
            otherlv_11=(Token)match(input,12,FOLLOW_12_in_ruleActivity4219); 

                	newLeafNode(otherlv_11, grammarAccess.getActivityAccess().getLeftParenthesisKeyword_9());
                
            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1879:1: ( ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )* )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==11||LA41_0==15||LA41_0==19) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1879:2: ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) ) (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )*
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1879:2: ( ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1880:1: ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1880:1: ( (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition ) )
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1881:1: (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition )
                    {
                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1881:1: (lv_post_12_1= ruleEntityAchieveCondition | lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_12_3= ruleEntityInvariantCondition | lv_post_12_4= ruleAttributeInvariantCondition )
                    int alt38=4;
                    switch ( input.LA(1) ) {
                    case 11:
                        {
                        int LA38_1 = input.LA(2);

                        if ( (LA38_1==12) ) {
                            int LA38_4 = input.LA(3);

                            if ( (LA38_4==RULE_ID) ) {
                                int LA38_5 = input.LA(4);

                                if ( (LA38_5==23) ) {
                                    alt38=2;
                                }
                                else if ( (LA38_5==13) ) {
                                    alt38=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 38, 5, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 38, 4, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 38, 1, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 15:
                        {
                        alt38=3;
                        }
                        break;
                    case 19:
                        {
                        alt38=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 0, input);

                        throw nvae;
                    }

                    switch (alt38) {
                        case 1 :
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1882:3: lv_post_12_1= ruleEntityAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4243);
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
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1897:8: lv_post_12_2= ruleNotMandatoryAttributeAchieveCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4262);
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
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1912:8: lv_post_12_3= ruleEntityInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleActivity4281);
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
                            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1927:8: lv_post_12_4= ruleAttributeInvariantCondition
                            {
                             
                            	        newCompositeNode(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity4300);
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

                    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1945:2: (otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==16) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1945:4: otherlv_13= ',' ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) )
                    	    {
                    	    otherlv_13=(Token)match(input,16,FOLLOW_16_in_ruleActivity4316); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getActivityAccess().getCommaKeyword_10_1_0());
                    	        
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1949:1: ( ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1950:1: ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1950:1: ( (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition ) )
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1951:1: (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition )
                    	    {
                    	    // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1951:1: (lv_post_14_1= ruleEntityAchieveCondition | lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition | lv_post_14_3= ruleEntityInvariantCondition | lv_post_14_4= ruleAttributeInvariantCondition )
                    	    int alt39=4;
                    	    switch ( input.LA(1) ) {
                    	    case 11:
                    	        {
                    	        int LA39_1 = input.LA(2);

                    	        if ( (LA39_1==12) ) {
                    	            int LA39_4 = input.LA(3);

                    	            if ( (LA39_4==RULE_ID) ) {
                    	                int LA39_5 = input.LA(4);

                    	                if ( (LA39_5==13) ) {
                    	                    alt39=1;
                    	                }
                    	                else if ( (LA39_5==23) ) {
                    	                    alt39=2;
                    	                }
                    	                else {
                    	                    NoViableAltException nvae =
                    	                        new NoViableAltException("", 39, 5, input);

                    	                    throw nvae;
                    	                }
                    	            }
                    	            else {
                    	                NoViableAltException nvae =
                    	                    new NoViableAltException("", 39, 4, input);

                    	                throw nvae;
                    	            }
                    	        }
                    	        else {
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 39, 1, input);

                    	            throw nvae;
                    	        }
                    	        }
                    	        break;
                    	    case 15:
                    	        {
                    	        alt39=3;
                    	        }
                    	        break;
                    	    case 19:
                    	        {
                    	        alt39=4;
                    	        }
                    	        break;
                    	    default:
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 39, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt39) {
                    	        case 1 :
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1952:3: lv_post_14_1= ruleEntityAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityAchieveConditionParserRuleCall_10_1_1_0_0()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4339);
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
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1967:8: lv_post_14_2= ruleNotMandatoryAttributeAchieveCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostNotMandatoryAttributeAchieveConditionParserRuleCall_10_1_1_0_1()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4358);
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
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1982:8: lv_post_14_3= ruleEntityInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostEntityInvariantConditionParserRuleCall_10_1_1_0_2()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleEntityInvariantCondition_in_ruleActivity4377);
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
                    	            // ../org.blended/src-gen/org/blended/parser/antlr/internal/InternalBlended.g:1997:8: lv_post_14_4= ruleAttributeInvariantCondition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getActivityAccess().getPostAttributeInvariantConditionParserRuleCall_10_1_1_0_3()); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity4396);
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
                    	    break loop40;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_15=(Token)match(input,13,FOLLOW_13_in_ruleActivity4415); 

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


 

    public static final BitSet FOLLOW_ruleBlendedModel_in_entryRuleBlendedModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBlendedModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCondition_in_ruleBlendedModel132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGoal_in_ruleBlendedModel159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActivity_in_ruleBlendedModel186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_entryRuleEntityAchieveCondition221 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveCondition231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleEntityAchieveCondition268 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveCondition280 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityAchieveCondition297 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveCondition314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityAchieveConditionExist_in_entryRuleEntityAchieveConditionExist350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityAchieveConditionExist360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleEntityAchieveConditionExist397 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveConditionExist409 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleEntityAchieveConditionExist421 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityAchieveConditionExist433 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityAchieveConditionExist450 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveConditionExist467 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityAchieveConditionExist479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_entryRuleEntityInvariantCondition515 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityInvariantCondition525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleEntityInvariantCondition562 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityInvariantCondition574 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleEntityInvariantCondition595 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleEntityInvariantCondition607 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleCardinality_in_ruleEntityInvariantCondition628 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityInvariantCondition640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntityDependenceCondition_in_entryRuleEntityDependenceCondition676 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntityDependenceCondition686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleEntityDependenceCondition723 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityDependenceCondition735 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleEntityDependenceCondition747 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityDependenceCondition759 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityDependenceCondition776 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityDependenceCondition793 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleEntityDependenceCondition805 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleEntityDependenceCondition817 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEntityDependenceCondition829 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntityDependenceCondition846 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityDependenceCondition863 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntityDependenceCondition875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_entryRuleAttributeAchieveCondition911 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeAchieveCondition921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleAttributeAchieveCondition995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_entryRuleNotMandatoryAttributeAchieveCondition1030 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNotMandatoryAttributeAchieveCondition1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleNotMandatoryAttributeAchieveCondition1077 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNotMandatoryAttributeAchieveCondition1089 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1110 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleNotMandatoryAttributeAchieveCondition1123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleNotMandatoryAttributeAchieveCondition1144 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleNotMandatoryAttributeAchieveCondition1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMandatoryAttributeAchieveCondition_in_entryRuleMandatoryAttributeAchieveCondition1194 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMandatoryAttributeAchieveCondition1204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleMandatoryAttributeAchieveCondition1241 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1253 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleMandatoryAttributeAchieveCondition1265 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMandatoryAttributeAchieveCondition1277 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1298 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleMandatoryAttributeAchieveCondition1311 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleMandatoryAttributeAchieveCondition1332 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1346 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMandatoryAttributeAchieveCondition1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_entryRuleAttributeInvariantCondition1394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeInvariantCondition1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleAttributeInvariantCondition1441 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeInvariantCondition1453 = new BitSet(new long[]{0x0000000008001810L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAttributeInvariantCondition1474 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeInvariantCondition1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeDependenceCondition_in_entryRuleAttributeDependenceCondition1522 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeDependenceCondition1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleAttributeDependenceCondition1569 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeDependenceCondition1581 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAttributeDependenceCondition1593 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeDependenceCondition1605 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1626 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleAttributeDependenceCondition1639 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1660 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeDependenceCondition1674 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAttributeDependenceCondition1686 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAttributeDependenceCondition1698 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAttributeDependenceCondition1710 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeDependenceCondition1731 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeDependenceCondition1743 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeDependenceCondition1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality1792 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality1803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1870 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1888 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1931 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality1949 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleCardinality1962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1985 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCardinality2003 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleCardinality2016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute2058 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2109 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleAttribute2127 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2142 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_ruleAttribute2161 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2176 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_ruleNothing_in_entryRuleNothing2223 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNothing2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleNothing2275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2323 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr2413 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr2423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr2470 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleOr2491 = new BitSet(new long[]{0x0000000008001810L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr2512 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd2550 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd2560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd2607 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_ruleAnd2628 = new BitSet(new long[]{0x0000000008001810L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd2649 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary2687 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary2697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rulePrimary2735 = new BitSet(new long[]{0x0000000008001810L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePrimary2757 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePrimary2768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rulePrimary2797 = new BitSet(new long[]{0x0000000008001810L});
    public static final BitSet FOLLOW_rulePrimary_in_rulePrimary2818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rulePrimary2847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic2882 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic2892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleAtomic2939 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAtomic2951 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic2972 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAtomic2984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAtomic3022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCondition_in_entryRuleCondition3059 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCondition3069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleCondition3116 = new BitSet(new long[]{0x00000003E0004802L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleCondition3139 = new BitSet(new long[]{0x00000003E0004802L});
    public static final BitSet FOLLOW_ruleEntityAchieveConditionExist_in_ruleCondition3158 = new BitSet(new long[]{0x00000003E0004802L});
    public static final BitSet FOLLOW_29_in_ruleCondition3177 = new BitSet(new long[]{0x00000003C0008002L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleCondition3198 = new BitSet(new long[]{0x00000003C0008002L});
    public static final BitSet FOLLOW_30_in_ruleCondition3214 = new BitSet(new long[]{0x0000000380020002L});
    public static final BitSet FOLLOW_ruleEntityDependenceCondition_in_ruleCondition3235 = new BitSet(new long[]{0x0000000380020002L});
    public static final BitSet FOLLOW_31_in_ruleCondition3251 = new BitSet(new long[]{0x0000000300040802L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_ruleCondition3272 = new BitSet(new long[]{0x0000000300040802L});
    public static final BitSet FOLLOW_32_in_ruleCondition3288 = new BitSet(new long[]{0x0000000200080002L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleCondition3309 = new BitSet(new long[]{0x0000000200080002L});
    public static final BitSet FOLLOW_33_in_ruleCondition3325 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleAttributeDependenceCondition_in_ruleCondition3346 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleGoal_in_entryRuleGoal3385 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGoal3395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGoal3437 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleGoal3454 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleGoal3466 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal3478 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleGoal3501 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal3520 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleNothing_in_ruleGoal3539 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGoal3554 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleGoal3567 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleGoal3579 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal3591 = new BitSet(new long[]{0x0000000000040800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleGoal3614 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleAttributeAchieveCondition_in_ruleGoal3633 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGoal3648 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleGoal3663 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleGoal3675 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal3687 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleGoal3710 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal3729 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleGoal3745 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleGoal3768 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleGoal3787 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleGoal3804 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleGoal3819 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleGoal3831 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGoal3843 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGoal3863 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleGoal3876 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGoal3896 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleGoal3910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActivity_in_entryRuleActivity3948 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleActivity3958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleActivity4000 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleActivity4017 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleActivity4034 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleActivity4051 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleActivity4063 = new BitSet(new long[]{0x0000000000042800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4087 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4106 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleActivity4122 = new BitSet(new long[]{0x0000000000040800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4145 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4164 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleActivity4183 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleActivity4195 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleActivity4207 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleActivity4219 = new BitSet(new long[]{0x00000000000CA800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4243 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4262 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleActivity4281 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity4300 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_16_in_ruleActivity4316 = new BitSet(new long[]{0x00000000000C8800L});
    public static final BitSet FOLLOW_ruleEntityAchieveCondition_in_ruleActivity4339 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleNotMandatoryAttributeAchieveCondition_in_ruleActivity4358 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleEntityInvariantCondition_in_ruleActivity4377 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_ruleAttributeInvariantCondition_in_ruleActivity4396 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_13_in_ruleActivity4415 = new BitSet(new long[]{0x0000000000000002L});

}