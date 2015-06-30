package org.blended.data.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.blended.data.services.DataGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDataParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Entity'", "'exists'", "'dependsOn'", "'{'", "'}'", "':'", "'mandatory'", "','", "'group'", "'.'", "'Association'", "'with'", "'('", "')'", "'Constraint'", "'..'", "'*'", "'+'", "'OR'", "'AND'", "'NOT'", "'DEF'"
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


        public InternalDataParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalDataParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalDataParser.tokenNames; }
    public String getGrammarFileName() { return "../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g"; }



     	private DataGrammarAccess grammarAccess;
     	
        public InternalDataParser(TokenStream input, DataGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "DataModel";	
       	}
       	
       	@Override
       	protected DataGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleDataModel"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:67:1: entryRuleDataModel returns [EObject current=null] : iv_ruleDataModel= ruleDataModel EOF ;
    public final EObject entryRuleDataModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataModel = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:68:2: (iv_ruleDataModel= ruleDataModel EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:69:2: iv_ruleDataModel= ruleDataModel EOF
            {
             newCompositeNode(grammarAccess.getDataModelRule()); 
            pushFollow(FOLLOW_ruleDataModel_in_entryRuleDataModel75);
            iv_ruleDataModel=ruleDataModel();

            state._fsp--;

             current =iv_ruleDataModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataModel85); 

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
    // $ANTLR end "entryRuleDataModel"


    // $ANTLR start "ruleDataModel"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:76:1: ruleDataModel returns [EObject current=null] : ( ( (lv_entities_0_0= ruleEntity ) )* ( (lv_associations_1_0= ruleAssociation ) )* ( (lv_constraint_2_0= ruleConstraint ) )* ) ;
    public final EObject ruleDataModel() throws RecognitionException {
        EObject current = null;

        EObject lv_entities_0_0 = null;

        EObject lv_associations_1_0 = null;

        EObject lv_constraint_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:79:28: ( ( ( (lv_entities_0_0= ruleEntity ) )* ( (lv_associations_1_0= ruleAssociation ) )* ( (lv_constraint_2_0= ruleConstraint ) )* ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:80:1: ( ( (lv_entities_0_0= ruleEntity ) )* ( (lv_associations_1_0= ruleAssociation ) )* ( (lv_constraint_2_0= ruleConstraint ) )* )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:80:1: ( ( (lv_entities_0_0= ruleEntity ) )* ( (lv_associations_1_0= ruleAssociation ) )* ( (lv_constraint_2_0= ruleConstraint ) )* )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:80:2: ( (lv_entities_0_0= ruleEntity ) )* ( (lv_associations_1_0= ruleAssociation ) )* ( (lv_constraint_2_0= ruleConstraint ) )*
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:80:2: ( (lv_entities_0_0= ruleEntity ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:81:1: (lv_entities_0_0= ruleEntity )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:81:1: (lv_entities_0_0= ruleEntity )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:82:3: lv_entities_0_0= ruleEntity
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getDataModelAccess().getEntitiesEntityParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleEntity_in_ruleDataModel131);
            	    lv_entities_0_0=ruleEntity();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getDataModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"entities",
            	            		lv_entities_0_0, 
            	            		"Entity");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:98:3: ( (lv_associations_1_0= ruleAssociation ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==21) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:99:1: (lv_associations_1_0= ruleAssociation )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:99:1: (lv_associations_1_0= ruleAssociation )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:100:3: lv_associations_1_0= ruleAssociation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getDataModelAccess().getAssociationsAssociationParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAssociation_in_ruleDataModel153);
            	    lv_associations_1_0=ruleAssociation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getDataModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"associations",
            	            		lv_associations_1_0, 
            	            		"Association");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:116:3: ( (lv_constraint_2_0= ruleConstraint ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==25) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:117:1: (lv_constraint_2_0= ruleConstraint )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:117:1: (lv_constraint_2_0= ruleConstraint )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:118:3: lv_constraint_2_0= ruleConstraint
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getDataModelAccess().getConstraintConstraintParserRuleCall_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleConstraint_in_ruleDataModel175);
            	    lv_constraint_2_0=ruleConstraint();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getDataModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"constraint",
            	            		lv_constraint_2_0, 
            	            		"Constraint");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
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
    // $ANTLR end "ruleDataModel"


    // $ANTLR start "entryRuleEntity"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:142:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:143:2: (iv_ruleEntity= ruleEntity EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:144:2: iv_ruleEntity= ruleEntity EOF
            {
             newCompositeNode(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_ruleEntity_in_entryRuleEntity212);
            iv_ruleEntity=ruleEntity();

            state._fsp--;

             current =iv_ruleEntity; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntity222); 

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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:151:1: ruleEntity returns [EObject current=null] : (otherlv_0= 'Entity' ( (lv_name_1_0= RULE_ID ) ) ( (lv_exists_2_0= 'exists' ) )? (otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) ) )? otherlv_5= '{' ( ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) ) )* otherlv_7= '}' ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_exists_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_attributes_6_1 = null;

        EObject lv_attributes_6_2 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:154:28: ( (otherlv_0= 'Entity' ( (lv_name_1_0= RULE_ID ) ) ( (lv_exists_2_0= 'exists' ) )? (otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) ) )? otherlv_5= '{' ( ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) ) )* otherlv_7= '}' ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:155:1: (otherlv_0= 'Entity' ( (lv_name_1_0= RULE_ID ) ) ( (lv_exists_2_0= 'exists' ) )? (otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) ) )? otherlv_5= '{' ( ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) ) )* otherlv_7= '}' )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:155:1: (otherlv_0= 'Entity' ( (lv_name_1_0= RULE_ID ) ) ( (lv_exists_2_0= 'exists' ) )? (otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) ) )? otherlv_5= '{' ( ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) ) )* otherlv_7= '}' )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:155:3: otherlv_0= 'Entity' ( (lv_name_1_0= RULE_ID ) ) ( (lv_exists_2_0= 'exists' ) )? (otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) ) )? otherlv_5= '{' ( ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) ) )* otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleEntity259); 

                	newLeafNode(otherlv_0, grammarAccess.getEntityAccess().getEntityKeyword_0());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:159:1: ( (lv_name_1_0= RULE_ID ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:160:1: (lv_name_1_0= RULE_ID )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:160:1: (lv_name_1_0= RULE_ID )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:161:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntity276); 

            			newLeafNode(lv_name_1_0, grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getEntityRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:177:2: ( (lv_exists_2_0= 'exists' ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:178:1: (lv_exists_2_0= 'exists' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:178:1: (lv_exists_2_0= 'exists' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:179:3: lv_exists_2_0= 'exists'
                    {
                    lv_exists_2_0=(Token)match(input,12,FOLLOW_12_in_ruleEntity299); 

                            newLeafNode(lv_exists_2_0, grammarAccess.getEntityAccess().getExistsExistsKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getEntityRule());
                    	        }
                           		setWithLastConsumed(current, "exists", true, "exists");
                    	    

                    }


                    }
                    break;

            }

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:192:3: (otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==13) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:192:5: otherlv_3= 'dependsOn' ( (otherlv_4= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleEntity326); 

                        	newLeafNode(otherlv_3, grammarAccess.getEntityAccess().getDependsOnKeyword_3_0());
                        
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:196:1: ( (otherlv_4= RULE_ID ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:197:1: (otherlv_4= RULE_ID )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:197:1: (otherlv_4= RULE_ID )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:198:3: otherlv_4= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getEntityRule());
                    	        }
                            
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntity346); 

                    		newLeafNode(otherlv_4, grammarAccess.getEntityAccess().getDependsOnEntityCrossReference_3_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleEntity360); 

                	newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_4());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:213:1: ( ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID||LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:214:1: ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:214:1: ( (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute ) )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:215:1: (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:215:1: (lv_attributes_6_1= ruleAttributeGroup | lv_attributes_6_2= ruleAttribute )
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==19) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==RULE_ID) ) {
            	        alt6=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:216:3: lv_attributes_6_1= ruleAttributeGroup
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getEntityAccess().getAttributesAttributeGroupParserRuleCall_5_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleAttributeGroup_in_ruleEntity383);
            	            lv_attributes_6_1=ruleAttributeGroup();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getEntityRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"attributes",
            	                    		lv_attributes_6_1, 
            	                    		"AttributeGroup");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:231:8: lv_attributes_6_2= ruleAttribute
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_5_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_ruleAttribute_in_ruleEntity402);
            	            lv_attributes_6_2=ruleAttribute();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getEntityRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"attributes",
            	                    		lv_attributes_6_2, 
            	                    		"Attribute");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_7=(Token)match(input,15,FOLLOW_15_in_ruleEntity418); 

                	newLeafNode(otherlv_7, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6());
                

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
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleAttribute"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:261:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:262:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:263:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute454);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute464); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:270:1: ruleAttribute returns [EObject current=null] : ( ( (lv_name_0_0= ruleQualifiedName ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ( (lv_mandatory_3_0= 'mandatory' ) )? (otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_type_2_0=null;
        Token lv_mandatory_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:273:28: ( ( ( (lv_name_0_0= ruleQualifiedName ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ( (lv_mandatory_3_0= 'mandatory' ) )? (otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:274:1: ( ( (lv_name_0_0= ruleQualifiedName ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ( (lv_mandatory_3_0= 'mandatory' ) )? (otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:274:1: ( ( (lv_name_0_0= ruleQualifiedName ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ( (lv_mandatory_3_0= 'mandatory' ) )? (otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:274:2: ( (lv_name_0_0= ruleQualifiedName ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ( (lv_mandatory_3_0= 'mandatory' ) )? (otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:274:2: ( (lv_name_0_0= ruleQualifiedName ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:275:1: (lv_name_0_0= ruleQualifiedName )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:275:1: (lv_name_0_0= ruleQualifiedName )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:276:3: lv_name_0_0= ruleQualifiedName
            {
             
            	        newCompositeNode(grammarAccess.getAttributeAccess().getNameQualifiedNameParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleAttribute510);
            lv_name_0_0=ruleQualifiedName();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"QualifiedName");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleAttribute522); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeAccess().getColonKeyword_1());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:296:1: ( (lv_type_2_0= RULE_ID ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:297:1: (lv_type_2_0= RULE_ID )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:297:1: (lv_type_2_0= RULE_ID )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:298:3: lv_type_2_0= RULE_ID
            {
            lv_type_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute539); 

            			newLeafNode(lv_type_2_0, grammarAccess.getAttributeAccess().getTypeIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getAttributeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"type",
                    		lv_type_2_0, 
                    		"ID");
            	    

            }


            }

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:314:2: ( (lv_mandatory_3_0= 'mandatory' ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==17) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:315:1: (lv_mandatory_3_0= 'mandatory' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:315:1: (lv_mandatory_3_0= 'mandatory' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:316:3: lv_mandatory_3_0= 'mandatory'
                    {
                    lv_mandatory_3_0=(Token)match(input,17,FOLLOW_17_in_ruleAttribute562); 

                            newLeafNode(lv_mandatory_3_0, grammarAccess.getAttributeAccess().getMandatoryMandatoryKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "mandatory", true, "mandatory");
                    	    

                    }


                    }
                    break;

            }

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:329:3: (otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==13) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:329:5: otherlv_4= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleAttribute589); 

                        	newLeafNode(otherlv_4, grammarAccess.getAttributeAccess().getDependsOnKeyword_4_0());
                        
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:333:1: ( ( ruleQualifiedName ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:334:1: ( ruleQualifiedName )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:334:1: ( ruleQualifiedName )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:335:3: ruleQualifiedName
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAttributeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAttributeAccess().getDependsOnAttributeCrossReference_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleAttribute612);
                    ruleQualifiedName();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:348:2: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==18) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:348:4: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleAttribute625); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getAttributeAccess().getCommaKeyword_4_2_0());
                    	        
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:352:1: ( ( ruleQualifiedName ) )
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:353:1: ( ruleQualifiedName )
                    	    {
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:353:1: ( ruleQualifiedName )
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:354:3: ruleQualifiedName
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAttributeRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getAttributeAccess().getDependsOnAttributeCrossReference_4_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleAttribute648);
                    	    ruleQualifiedName();

                    	    state._fsp--;

                    	     
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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleAttributeGroup"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:375:1: entryRuleAttributeGroup returns [EObject current=null] : iv_ruleAttributeGroup= ruleAttributeGroup EOF ;
    public final EObject entryRuleAttributeGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeGroup = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:376:2: (iv_ruleAttributeGroup= ruleAttributeGroup EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:377:2: iv_ruleAttributeGroup= ruleAttributeGroup EOF
            {
             newCompositeNode(grammarAccess.getAttributeGroupRule()); 
            pushFollow(FOLLOW_ruleAttributeGroup_in_entryRuleAttributeGroup688);
            iv_ruleAttributeGroup=ruleAttributeGroup();

            state._fsp--;

             current =iv_ruleAttributeGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeGroup698); 

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
    // $ANTLR end "entryRuleAttributeGroup"


    // $ANTLR start "ruleAttributeGroup"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:384:1: ruleAttributeGroup returns [EObject current=null] : (otherlv_0= 'group' ( (lv_mandatory_1_0= 'mandatory' ) )? (otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_6= '{' ( (lv_attributes_7_0= ruleAttribute ) )+ otherlv_8= '}' ) ;
    public final EObject ruleAttributeGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_mandatory_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_attributes_7_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:387:28: ( (otherlv_0= 'group' ( (lv_mandatory_1_0= 'mandatory' ) )? (otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_6= '{' ( (lv_attributes_7_0= ruleAttribute ) )+ otherlv_8= '}' ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:388:1: (otherlv_0= 'group' ( (lv_mandatory_1_0= 'mandatory' ) )? (otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_6= '{' ( (lv_attributes_7_0= ruleAttribute ) )+ otherlv_8= '}' )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:388:1: (otherlv_0= 'group' ( (lv_mandatory_1_0= 'mandatory' ) )? (otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_6= '{' ( (lv_attributes_7_0= ruleAttribute ) )+ otherlv_8= '}' )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:388:3: otherlv_0= 'group' ( (lv_mandatory_1_0= 'mandatory' ) )? (otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_6= '{' ( (lv_attributes_7_0= ruleAttribute ) )+ otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleAttributeGroup735); 

                	newLeafNode(otherlv_0, grammarAccess.getAttributeGroupAccess().getGroupKeyword_0());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:392:1: ( (lv_mandatory_1_0= 'mandatory' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==17) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:393:1: (lv_mandatory_1_0= 'mandatory' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:393:1: (lv_mandatory_1_0= 'mandatory' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:394:3: lv_mandatory_1_0= 'mandatory'
                    {
                    lv_mandatory_1_0=(Token)match(input,17,FOLLOW_17_in_ruleAttributeGroup753); 

                            newLeafNode(lv_mandatory_1_0, grammarAccess.getAttributeGroupAccess().getMandatoryMandatoryKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAttributeGroupRule());
                    	        }
                           		setWithLastConsumed(current, "mandatory", true, "mandatory");
                    	    

                    }


                    }
                    break;

            }

            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:407:3: (otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==13) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:407:5: otherlv_2= 'dependsOn' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleAttributeGroup780); 

                        	newLeafNode(otherlv_2, grammarAccess.getAttributeGroupAccess().getDependsOnKeyword_2_0());
                        
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:411:1: ( ( ruleQualifiedName ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:412:1: ( ruleQualifiedName )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:412:1: ( ruleQualifiedName )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:413:3: ruleQualifiedName
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAttributeGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeCrossReference_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleAttributeGroup803);
                    ruleQualifiedName();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:426:2: (otherlv_4= ',' ( ( ruleQualifiedName ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==18) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:426:4: otherlv_4= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_4=(Token)match(input,18,FOLLOW_18_in_ruleAttributeGroup816); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getAttributeGroupAccess().getCommaKeyword_2_2_0());
                    	        
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:430:1: ( ( ruleQualifiedName ) )
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:431:1: ( ruleQualifiedName )
                    	    {
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:431:1: ( ruleQualifiedName )
                    	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:432:3: ruleQualifiedName
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAttributeGroupRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeCrossReference_2_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleAttributeGroup839);
                    	    ruleQualifiedName();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

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

            otherlv_6=(Token)match(input,14,FOLLOW_14_in_ruleAttributeGroup855); 

                	newLeafNode(otherlv_6, grammarAccess.getAttributeGroupAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:449:1: ( (lv_attributes_7_0= ruleAttribute ) )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:450:1: (lv_attributes_7_0= ruleAttribute )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:450:1: (lv_attributes_7_0= ruleAttribute )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:451:3: lv_attributes_7_0= ruleAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAttributeGroupAccess().getAttributesAttributeParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeGroup876);
            	    lv_attributes_7_0=ruleAttribute();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAttributeGroupRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"attributes",
            	            		lv_attributes_7_0, 
            	            		"Attribute");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            otherlv_8=(Token)match(input,15,FOLLOW_15_in_ruleAttributeGroup889); 

                	newLeafNode(otherlv_8, grammarAccess.getAttributeGroupAccess().getRightCurlyBracketKeyword_5());
                

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
    // $ANTLR end "ruleAttributeGroup"


    // $ANTLR start "entryRuleQualifiedName"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:479:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:480:2: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:481:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName926);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedName937); 

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
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:488:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:491:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:492:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:492:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:492:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName977); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:499:1: (kw= '.' this_ID_2= RULE_ID )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==20) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:500:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,20,FOLLOW_20_in_ruleQualifiedName996); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName1011); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop15;
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
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleAssociation"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:520:1: entryRuleAssociation returns [EObject current=null] : iv_ruleAssociation= ruleAssociation EOF ;
    public final EObject entryRuleAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssociation = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:521:2: (iv_ruleAssociation= ruleAssociation EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:522:2: iv_ruleAssociation= ruleAssociation EOF
            {
             newCompositeNode(grammarAccess.getAssociationRule()); 
            pushFollow(FOLLOW_ruleAssociation_in_entryRuleAssociation1058);
            iv_ruleAssociation=ruleAssociation();

            state._fsp--;

             current =iv_ruleAssociation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAssociation1068); 

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
    // $ANTLR end "entryRuleAssociation"


    // $ANTLR start "ruleAssociation"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:529:1: ruleAssociation returns [EObject current=null] : (otherlv_0= 'Association' otherlv_1= '{' ( (otherlv_2= RULE_ID ) ) otherlv_3= 'with' ( (lv_name1_4_0= RULE_ID ) ) otherlv_5= '(' ( (lv_cardinality1_6_0= ruleCardinality ) ) otherlv_7= ')' ( (otherlv_8= RULE_ID ) ) otherlv_9= 'with' ( (lv_name2_10_0= RULE_ID ) ) otherlv_11= '(' ( (lv_cardinality2_12_0= ruleCardinality ) ) otherlv_13= ')' otherlv_14= '}' ) ;
    public final EObject ruleAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name1_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token lv_name2_10_0=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        AntlrDatatypeRuleToken lv_cardinality1_6_0 = null;

        AntlrDatatypeRuleToken lv_cardinality2_12_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:532:28: ( (otherlv_0= 'Association' otherlv_1= '{' ( (otherlv_2= RULE_ID ) ) otherlv_3= 'with' ( (lv_name1_4_0= RULE_ID ) ) otherlv_5= '(' ( (lv_cardinality1_6_0= ruleCardinality ) ) otherlv_7= ')' ( (otherlv_8= RULE_ID ) ) otherlv_9= 'with' ( (lv_name2_10_0= RULE_ID ) ) otherlv_11= '(' ( (lv_cardinality2_12_0= ruleCardinality ) ) otherlv_13= ')' otherlv_14= '}' ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:533:1: (otherlv_0= 'Association' otherlv_1= '{' ( (otherlv_2= RULE_ID ) ) otherlv_3= 'with' ( (lv_name1_4_0= RULE_ID ) ) otherlv_5= '(' ( (lv_cardinality1_6_0= ruleCardinality ) ) otherlv_7= ')' ( (otherlv_8= RULE_ID ) ) otherlv_9= 'with' ( (lv_name2_10_0= RULE_ID ) ) otherlv_11= '(' ( (lv_cardinality2_12_0= ruleCardinality ) ) otherlv_13= ')' otherlv_14= '}' )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:533:1: (otherlv_0= 'Association' otherlv_1= '{' ( (otherlv_2= RULE_ID ) ) otherlv_3= 'with' ( (lv_name1_4_0= RULE_ID ) ) otherlv_5= '(' ( (lv_cardinality1_6_0= ruleCardinality ) ) otherlv_7= ')' ( (otherlv_8= RULE_ID ) ) otherlv_9= 'with' ( (lv_name2_10_0= RULE_ID ) ) otherlv_11= '(' ( (lv_cardinality2_12_0= ruleCardinality ) ) otherlv_13= ')' otherlv_14= '}' )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:533:3: otherlv_0= 'Association' otherlv_1= '{' ( (otherlv_2= RULE_ID ) ) otherlv_3= 'with' ( (lv_name1_4_0= RULE_ID ) ) otherlv_5= '(' ( (lv_cardinality1_6_0= ruleCardinality ) ) otherlv_7= ')' ( (otherlv_8= RULE_ID ) ) otherlv_9= 'with' ( (lv_name2_10_0= RULE_ID ) ) otherlv_11= '(' ( (lv_cardinality2_12_0= ruleCardinality ) ) otherlv_13= ')' otherlv_14= '}'
            {
            otherlv_0=(Token)match(input,21,FOLLOW_21_in_ruleAssociation1105); 

                	newLeafNode(otherlv_0, grammarAccess.getAssociationAccess().getAssociationKeyword_0());
                
            otherlv_1=(Token)match(input,14,FOLLOW_14_in_ruleAssociation1117); 

                	newLeafNode(otherlv_1, grammarAccess.getAssociationAccess().getLeftCurlyBracketKeyword_1());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:541:1: ( (otherlv_2= RULE_ID ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:542:1: (otherlv_2= RULE_ID )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:542:1: (otherlv_2= RULE_ID )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:543:3: otherlv_2= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getAssociationRule());
            	        }
                    
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAssociation1137); 

            		newLeafNode(otherlv_2, grammarAccess.getAssociationAccess().getEntity1EntityCrossReference_2_0()); 
            	

            }


            }

            otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleAssociation1149); 

                	newLeafNode(otherlv_3, grammarAccess.getAssociationAccess().getWithKeyword_3());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:558:1: ( (lv_name1_4_0= RULE_ID ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:559:1: (lv_name1_4_0= RULE_ID )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:559:1: (lv_name1_4_0= RULE_ID )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:560:3: lv_name1_4_0= RULE_ID
            {
            lv_name1_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAssociation1166); 

            			newLeafNode(lv_name1_4_0, grammarAccess.getAssociationAccess().getName1IDTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getAssociationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name1",
                    		lv_name1_4_0, 
                    		"ID");
            	    

            }


            }

            otherlv_5=(Token)match(input,23,FOLLOW_23_in_ruleAssociation1183); 

                	newLeafNode(otherlv_5, grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_5());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:580:1: ( (lv_cardinality1_6_0= ruleCardinality ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:581:1: (lv_cardinality1_6_0= ruleCardinality )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:581:1: (lv_cardinality1_6_0= ruleCardinality )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:582:3: lv_cardinality1_6_0= ruleCardinality
            {
             
            	        newCompositeNode(grammarAccess.getAssociationAccess().getCardinality1CardinalityParserRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleCardinality_in_ruleAssociation1204);
            lv_cardinality1_6_0=ruleCardinality();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAssociationRule());
            	        }
                   		set(
                   			current, 
                   			"cardinality1",
                    		lv_cardinality1_6_0, 
                    		"Cardinality");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,24,FOLLOW_24_in_ruleAssociation1216); 

                	newLeafNode(otherlv_7, grammarAccess.getAssociationAccess().getRightParenthesisKeyword_7());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:602:1: ( (otherlv_8= RULE_ID ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:603:1: (otherlv_8= RULE_ID )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:603:1: (otherlv_8= RULE_ID )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:604:3: otherlv_8= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getAssociationRule());
            	        }
                    
            otherlv_8=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAssociation1236); 

            		newLeafNode(otherlv_8, grammarAccess.getAssociationAccess().getEntity2EntityCrossReference_8_0()); 
            	

            }


            }

            otherlv_9=(Token)match(input,22,FOLLOW_22_in_ruleAssociation1248); 

                	newLeafNode(otherlv_9, grammarAccess.getAssociationAccess().getWithKeyword_9());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:619:1: ( (lv_name2_10_0= RULE_ID ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:620:1: (lv_name2_10_0= RULE_ID )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:620:1: (lv_name2_10_0= RULE_ID )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:621:3: lv_name2_10_0= RULE_ID
            {
            lv_name2_10_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAssociation1265); 

            			newLeafNode(lv_name2_10_0, grammarAccess.getAssociationAccess().getName2IDTerminalRuleCall_10_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getAssociationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name2",
                    		lv_name2_10_0, 
                    		"ID");
            	    

            }


            }

            otherlv_11=(Token)match(input,23,FOLLOW_23_in_ruleAssociation1282); 

                	newLeafNode(otherlv_11, grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_11());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:641:1: ( (lv_cardinality2_12_0= ruleCardinality ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:642:1: (lv_cardinality2_12_0= ruleCardinality )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:642:1: (lv_cardinality2_12_0= ruleCardinality )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:643:3: lv_cardinality2_12_0= ruleCardinality
            {
             
            	        newCompositeNode(grammarAccess.getAssociationAccess().getCardinality2CardinalityParserRuleCall_12_0()); 
            	    
            pushFollow(FOLLOW_ruleCardinality_in_ruleAssociation1303);
            lv_cardinality2_12_0=ruleCardinality();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAssociationRule());
            	        }
                   		set(
                   			current, 
                   			"cardinality2",
                    		lv_cardinality2_12_0, 
                    		"Cardinality");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_13=(Token)match(input,24,FOLLOW_24_in_ruleAssociation1315); 

                	newLeafNode(otherlv_13, grammarAccess.getAssociationAccess().getRightParenthesisKeyword_13());
                
            otherlv_14=(Token)match(input,15,FOLLOW_15_in_ruleAssociation1327); 

                	newLeafNode(otherlv_14, grammarAccess.getAssociationAccess().getRightCurlyBracketKeyword_14());
                

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
    // $ANTLR end "ruleAssociation"


    // $ANTLR start "entryRuleConstraint"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:675:1: entryRuleConstraint returns [EObject current=null] : iv_ruleConstraint= ruleConstraint EOF ;
    public final EObject entryRuleConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraint = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:676:2: (iv_ruleConstraint= ruleConstraint EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:677:2: iv_ruleConstraint= ruleConstraint EOF
            {
             newCompositeNode(grammarAccess.getConstraintRule()); 
            pushFollow(FOLLOW_ruleConstraint_in_entryRuleConstraint1363);
            iv_ruleConstraint=ruleConstraint();

            state._fsp--;

             current =iv_ruleConstraint; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstraint1373); 

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
    // $ANTLR end "entryRuleConstraint"


    // $ANTLR start "ruleConstraint"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:684:1: ruleConstraint returns [EObject current=null] : (otherlv_0= 'Constraint' otherlv_1= ':' ( (lv_constraint_2_0= ruleExpression ) ) ) ;
    public final EObject ruleConstraint() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_constraint_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:687:28: ( (otherlv_0= 'Constraint' otherlv_1= ':' ( (lv_constraint_2_0= ruleExpression ) ) ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:688:1: (otherlv_0= 'Constraint' otherlv_1= ':' ( (lv_constraint_2_0= ruleExpression ) ) )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:688:1: (otherlv_0= 'Constraint' otherlv_1= ':' ( (lv_constraint_2_0= ruleExpression ) ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:688:3: otherlv_0= 'Constraint' otherlv_1= ':' ( (lv_constraint_2_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleConstraint1410); 

                	newLeafNode(otherlv_0, grammarAccess.getConstraintAccess().getConstraintKeyword_0());
                
            otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleConstraint1422); 

                	newLeafNode(otherlv_1, grammarAccess.getConstraintAccess().getColonKeyword_1());
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:696:1: ( (lv_constraint_2_0= ruleExpression ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:697:1: (lv_constraint_2_0= ruleExpression )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:697:1: (lv_constraint_2_0= ruleExpression )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:698:3: lv_constraint_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getConstraintAccess().getConstraintExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleConstraint1443);
            lv_constraint_2_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConstraintRule());
            	        }
                   		set(
                   			current, 
                   			"constraint",
                    		lv_constraint_2_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


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
    // $ANTLR end "ruleConstraint"


    // $ANTLR start "entryRuleCardinality"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:722:1: entryRuleCardinality returns [String current=null] : iv_ruleCardinality= ruleCardinality EOF ;
    public final String entryRuleCardinality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCardinality = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:723:2: (iv_ruleCardinality= ruleCardinality EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:724:2: iv_ruleCardinality= ruleCardinality EOF
            {
             newCompositeNode(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality1480);
            iv_ruleCardinality=ruleCardinality();

            state._fsp--;

             current =iv_ruleCardinality.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality1491); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:731:1: ruleCardinality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) ;
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
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:734:28: ( (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:735:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:735:1: (this_INT_0= RULE_INT | (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT ) | (this_INT_4= RULE_INT kw= '..' kw= '*' ) | (this_INT_7= RULE_INT kw= '..' kw= '+' ) )
            int alt16=4;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==26) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt16=2;
                        }
                        break;
                    case 27:
                        {
                        alt16=3;
                        }
                        break;
                    case 28:
                        {
                        alt16=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 2, input);

                        throw nvae;
                    }

                }
                else if ( (LA16_1==EOF||LA16_1==24) ) {
                    alt16=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:735:6: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1531); 

                    		current.merge(this_INT_0);
                        
                     
                        newLeafNode(this_INT_0, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:743:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:743:6: (this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:743:11: this_INT_1= RULE_INT kw= '..' this_INT_3= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1558); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
                        
                    kw=(Token)match(input,26,FOLLOW_26_in_ruleCardinality1576); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1591); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:764:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:764:6: (this_INT_4= RULE_INT kw= '..' kw= '*' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:764:11: this_INT_4= RULE_INT kw= '..' kw= '*'
                    {
                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1619); 

                    		current.merge(this_INT_4);
                        
                     
                        newLeafNode(this_INT_4, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
                        
                    kw=(Token)match(input,26,FOLLOW_26_in_ruleCardinality1637); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
                        
                    kw=(Token)match(input,27,FOLLOW_27_in_ruleCardinality1650); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:784:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:784:6: (this_INT_7= RULE_INT kw= '..' kw= '+' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:784:11: this_INT_7= RULE_INT kw= '..' kw= '+'
                    {
                    this_INT_7=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleCardinality1673); 

                    		current.merge(this_INT_7);
                        
                     
                        newLeafNode(this_INT_7, grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
                        
                    kw=(Token)match(input,26,FOLLOW_26_in_ruleCardinality1691); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
                        
                    kw=(Token)match(input,28,FOLLOW_28_in_ruleCardinality1704); 

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


    // $ANTLR start "entryRuleExpression"
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:811:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:812:2: (iv_ruleExpression= ruleExpression EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:813:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1745);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1755); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:820:1: ruleExpression returns [EObject current=null] : this_Or_0= ruleOr ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Or_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:823:28: (this_Or_0= ruleOr )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:825:5: this_Or_0= ruleOr
            {
             
                    newCompositeNode(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleOr_in_ruleExpression1801);
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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:841:1: entryRuleOr returns [EObject current=null] : iv_ruleOr= ruleOr EOF ;
    public final EObject entryRuleOr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:842:2: (iv_ruleOr= ruleOr EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:843:2: iv_ruleOr= ruleOr EOF
            {
             newCompositeNode(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr1835);
            iv_ruleOr=ruleOr();

            state._fsp--;

             current =iv_ruleOr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr1845); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:850:1: ruleOr returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleOr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:853:28: ( (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:854:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:854:1: (this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )* )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:855:5: this_And_0= ruleAnd ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleAnd_in_ruleOr1892);
            this_And_0=ruleAnd();

            state._fsp--;

             
                    current = this_And_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:863:1: ( () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==29) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:863:2: () otherlv_2= 'OR' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:863:2: ()
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:864:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getOrAccess().getOrLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,29,FOLLOW_29_in_ruleOr1913); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOrAccess().getORKeyword_1_1());
            	        
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:873:1: ( (lv_right_3_0= ruleAnd ) )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:874:1: (lv_right_3_0= ruleAnd )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:874:1: (lv_right_3_0= ruleAnd )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:875:3: lv_right_3_0= ruleAnd
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnd_in_ruleOr1934);
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
            	    break loop17;
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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:899:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:900:2: (iv_ruleAnd= ruleAnd EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:901:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd1972);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd1982); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:908:1: ruleAnd returns [EObject current=null] : (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Primary_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:911:28: ( (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:912:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:912:1: (this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )* )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:913:5: this_Primary_0= rulePrimary ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePrimary_in_ruleAnd2029);
            this_Primary_0=rulePrimary();

            state._fsp--;

             
                    current = this_Primary_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:921:1: ( () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==30) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:921:2: () otherlv_2= 'AND' ( (lv_right_3_0= rulePrimary ) )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:921:2: ()
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:922:5: 
            	    {

            	            current = forceCreateModelElementAndSet(
            	                grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	                current);
            	        

            	    }

            	    otherlv_2=(Token)match(input,30,FOLLOW_30_in_ruleAnd2050); 

            	        	newLeafNode(otherlv_2, grammarAccess.getAndAccess().getANDKeyword_1_1());
            	        
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:931:1: ( (lv_right_3_0= rulePrimary ) )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:932:1: (lv_right_3_0= rulePrimary )
            	    {
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:932:1: (lv_right_3_0= rulePrimary )
            	    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:933:3: lv_right_3_0= rulePrimary
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePrimary_in_ruleAnd2071);
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
            	    break loop18;
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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:957:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:958:2: (iv_rulePrimary= rulePrimary EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:959:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary2109);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary2119); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:966:1: rulePrimary returns [EObject current=null] : ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) ;
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
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:969:28: ( ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:970:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:970:1: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) ) | this_Atomic_6= ruleAtomic )
            int alt19=3;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt19=1;
                }
                break;
            case 31:
                {
                alt19=2;
                }
                break;
            case RULE_ID:
            case 32:
                {
                alt19=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:970:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:970:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:970:4: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,23,FOLLOW_23_in_rulePrimary2157); 

                        	newLeafNode(otherlv_0, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0());
                        
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleExpression_in_rulePrimary2179);
                    this_Expression_1=ruleExpression();

                    state._fsp--;

                     
                            current = this_Expression_1; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_2=(Token)match(input,24,FOLLOW_24_in_rulePrimary2190); 

                        	newLeafNode(otherlv_2, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:988:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:988:6: ( () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:988:7: () otherlv_4= 'NOT' ( (lv_expression_5_0= rulePrimary ) )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:988:7: ()
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:989:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getPrimaryAccess().getNotAction_1_0(),
                                current);
                        

                    }

                    otherlv_4=(Token)match(input,31,FOLLOW_31_in_rulePrimary2219); 

                        	newLeafNode(otherlv_4, grammarAccess.getPrimaryAccess().getNOTKeyword_1_1());
                        
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:998:1: ( (lv_expression_5_0= rulePrimary ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:999:1: (lv_expression_5_0= rulePrimary )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:999:1: (lv_expression_5_0= rulePrimary )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1000:3: lv_expression_5_0= rulePrimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulePrimary_in_rulePrimary2240);
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
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1018:5: this_Atomic_6= ruleAtomic
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleAtomic_in_rulePrimary2269);
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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1034:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1035:2: (iv_ruleAtomic= ruleAtomic EOF )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1036:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic2304);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic2314); 

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
    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1043:1: ruleAtomic returns [EObject current=null] : ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleQualifiedName ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1046:28: ( ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleQualifiedName ) ) ) ) )
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1047:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleQualifiedName ) ) ) )
            {
            // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1047:1: ( ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')' ) | ( () ( (lv_name_6_0= ruleQualifiedName ) ) ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==32) ) {
                alt20=1;
            }
            else if ( (LA20_0==RULE_ID) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1047:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')' )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1047:2: ( () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')' )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1047:3: () otherlv_1= 'DEF' otherlv_2= '(' ( (lv_name_3_0= ruleQualifiedName ) ) otherlv_4= ')'
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1047:3: ()
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1048:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,32,FOLLOW_32_in_ruleAtomic2361); 

                        	newLeafNode(otherlv_1, grammarAccess.getAtomicAccess().getDEFKeyword_0_1());
                        
                    otherlv_2=(Token)match(input,23,FOLLOW_23_in_ruleAtomic2373); 

                        	newLeafNode(otherlv_2, grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2());
                        
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1061:1: ( (lv_name_3_0= ruleQualifiedName ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1062:1: (lv_name_3_0= ruleQualifiedName )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1062:1: (lv_name_3_0= ruleQualifiedName )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1063:3: lv_name_3_0= ruleQualifiedName
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameQualifiedNameParserRuleCall_0_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleAtomic2394);
                    lv_name_3_0=ruleQualifiedName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAtomicRule());
                    	        }
                           		set(
                           			current, 
                           			"name",
                            		lv_name_3_0, 
                            		"QualifiedName");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_4=(Token)match(input,24,FOLLOW_24_in_ruleAtomic2406); 

                        	newLeafNode(otherlv_4, grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1084:6: ( () ( (lv_name_6_0= ruleQualifiedName ) ) )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1084:6: ( () ( (lv_name_6_0= ruleQualifiedName ) ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1084:7: () ( (lv_name_6_0= ruleQualifiedName ) )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1084:7: ()
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1085:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getAtomicAccess().getAttributeValueAction_1_0(),
                                current);
                        

                    }

                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1090:2: ( (lv_name_6_0= ruleQualifiedName ) )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1091:1: (lv_name_6_0= ruleQualifiedName )
                    {
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1091:1: (lv_name_6_0= ruleQualifiedName )
                    // ../org.blended.data/src-gen/org/blended/data/parser/antlr/internal/InternalData.g:1092:3: lv_name_6_0= ruleQualifiedName
                    {
                     
                    	        newCompositeNode(grammarAccess.getAtomicAccess().getNameQualifiedNameParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleAtomic2444);
                    lv_name_6_0=ruleQualifiedName();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAtomicRule());
                    	        }
                           		set(
                           			current, 
                           			"name",
                            		lv_name_6_0, 
                            		"QualifiedName");
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

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleDataModel_in_entryRuleDataModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntity_in_ruleDataModel131 = new BitSet(new long[]{0x0000000002200802L});
    public static final BitSet FOLLOW_ruleAssociation_in_ruleDataModel153 = new BitSet(new long[]{0x0000000002200002L});
    public static final BitSet FOLLOW_ruleConstraint_in_ruleDataModel175 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity212 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntity222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleEntity259 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity276 = new BitSet(new long[]{0x0000000000007000L});
    public static final BitSet FOLLOW_12_in_ruleEntity299 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleEntity326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity346 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleEntity360 = new BitSet(new long[]{0x0000000000088010L});
    public static final BitSet FOLLOW_ruleAttributeGroup_in_ruleEntity383 = new BitSet(new long[]{0x0000000000088010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleEntity402 = new BitSet(new long[]{0x0000000000088010L});
    public static final BitSet FOLLOW_15_in_ruleEntity418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute454 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAttribute510 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAttribute522 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute539 = new BitSet(new long[]{0x0000000000022002L});
    public static final BitSet FOLLOW_17_in_ruleAttribute562 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_ruleAttribute589 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAttribute612 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleAttribute625 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAttribute648 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ruleAttributeGroup_in_entryRuleAttributeGroup688 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeGroup698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleAttributeGroup735 = new BitSet(new long[]{0x0000000000026000L});
    public static final BitSet FOLLOW_17_in_ruleAttributeGroup753 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleAttributeGroup780 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAttributeGroup803 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_18_in_ruleAttributeGroup816 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAttributeGroup839 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_14_in_ruleAttributeGroup855 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeGroup876 = new BitSet(new long[]{0x0000000000088010L});
    public static final BitSet FOLLOW_15_in_ruleAttributeGroup889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName926 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName977 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_ruleQualifiedName996 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName1011 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_ruleAssociation_in_entryRuleAssociation1058 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAssociation1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleAssociation1105 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleAssociation1117 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAssociation1137 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleAssociation1149 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAssociation1166 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleAssociation1183 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleCardinality_in_ruleAssociation1204 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleAssociation1216 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAssociation1236 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleAssociation1248 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAssociation1265 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleAssociation1282 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleCardinality_in_ruleAssociation1303 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleAssociation1315 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleAssociation1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstraint_in_entryRuleConstraint1363 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstraint1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleConstraint1410 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleConstraint1422 = new BitSet(new long[]{0x0000000180880010L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleConstraint1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality1480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality1491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1558 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCardinality1576 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1619 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCardinality1637 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleCardinality1650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleCardinality1673 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCardinality1691 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleCardinality1704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1745 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr1835 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr1845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr1892 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_ruleOr1913 = new BitSet(new long[]{0x0000000180880010L});
    public static final BitSet FOLLOW_ruleAnd_in_ruleOr1934 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd1972 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd1982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd2029 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleAnd2050 = new BitSet(new long[]{0x0000000180880010L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleAnd2071 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary2109 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary2119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rulePrimary2157 = new BitSet(new long[]{0x0000000180880010L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePrimary2179 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_rulePrimary2190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rulePrimary2219 = new BitSet(new long[]{0x0000000180880010L});
    public static final BitSet FOLLOW_rulePrimary_in_rulePrimary2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rulePrimary2269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic2304 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic2314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleAtomic2361 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleAtomic2373 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAtomic2394 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleAtomic2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAtomic2444 = new BitSet(new long[]{0x0000000000000002L});

}