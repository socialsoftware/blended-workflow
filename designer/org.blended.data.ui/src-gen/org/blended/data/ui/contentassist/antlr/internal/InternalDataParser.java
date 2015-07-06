package org.blended.data.ui.contentassist.antlr.internal; 

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
import org.blended.data.services.DataGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDataParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Entityy'", "'{'", "'}'", "'dependsOn'", "':'", "','", "'group'", "'.'", "'Association'", "'with'", "'('", "')'", "'Constraint'", "'..'", "'*'", "'+'", "'OR'", "'AND'", "'NOT'", "'DEF'", "'exists'", "'mandatory'"
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


        public InternalDataParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalDataParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalDataParser.tokenNames; }
    public String getGrammarFileName() { return "/C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g"; }


     
     	private DataGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(DataGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleDataModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:60:1: entryRuleDataModel : ruleDataModel EOF ;
    public final void entryRuleDataModel() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:61:1: ( ruleDataModel EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:62:1: ruleDataModel EOF
            {
             before(grammarAccess.getDataModelRule()); 
            pushFollow(FOLLOW_ruleDataModel_in_entryRuleDataModel61);
            ruleDataModel();

            state._fsp--;

             after(grammarAccess.getDataModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataModel68); 

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
    // $ANTLR end "entryRuleDataModel"


    // $ANTLR start "ruleDataModel"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:69:1: ruleDataModel : ( ( rule__DataModel__Group__0 ) ) ;
    public final void ruleDataModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:73:2: ( ( ( rule__DataModel__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:74:1: ( ( rule__DataModel__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:74:1: ( ( rule__DataModel__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:75:1: ( rule__DataModel__Group__0 )
            {
             before(grammarAccess.getDataModelAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:76:1: ( rule__DataModel__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:76:2: rule__DataModel__Group__0
            {
            pushFollow(FOLLOW_rule__DataModel__Group__0_in_ruleDataModel94);
            rule__DataModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDataModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDataModel"


    // $ANTLR start "entryRuleEntity"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:88:1: entryRuleEntity : ruleEntity EOF ;
    public final void entryRuleEntity() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:89:1: ( ruleEntity EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:90:1: ruleEntity EOF
            {
             before(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_ruleEntity_in_entryRuleEntity121);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getEntityRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntity128); 

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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:97:1: ruleEntity : ( ( rule__Entity__Group__0 ) ) ;
    public final void ruleEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:101:2: ( ( ( rule__Entity__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:102:1: ( ( rule__Entity__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:102:1: ( ( rule__Entity__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:103:1: ( rule__Entity__Group__0 )
            {
             before(grammarAccess.getEntityAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:104:1: ( rule__Entity__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:104:2: rule__Entity__Group__0
            {
            pushFollow(FOLLOW_rule__Entity__Group__0_in_ruleEntity154);
            rule__Entity__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleAttribute"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:116:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:117:1: ( ruleAttribute EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:118:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute181);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute188); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:125:1: ruleAttribute : ( ( rule__Attribute__Group__0 ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:129:2: ( ( ( rule__Attribute__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:130:1: ( ( rule__Attribute__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:130:1: ( ( rule__Attribute__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:131:1: ( rule__Attribute__Group__0 )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:132:1: ( rule__Attribute__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:132:2: rule__Attribute__Group__0
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0_in_ruleAttribute214);
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


    // $ANTLR start "entryRuleAttributeGroup"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:144:1: entryRuleAttributeGroup : ruleAttributeGroup EOF ;
    public final void entryRuleAttributeGroup() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:145:1: ( ruleAttributeGroup EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:146:1: ruleAttributeGroup EOF
            {
             before(grammarAccess.getAttributeGroupRule()); 
            pushFollow(FOLLOW_ruleAttributeGroup_in_entryRuleAttributeGroup241);
            ruleAttributeGroup();

            state._fsp--;

             after(grammarAccess.getAttributeGroupRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeGroup248); 

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
    // $ANTLR end "entryRuleAttributeGroup"


    // $ANTLR start "ruleAttributeGroup"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:153:1: ruleAttributeGroup : ( ( rule__AttributeGroup__Group__0 ) ) ;
    public final void ruleAttributeGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:157:2: ( ( ( rule__AttributeGroup__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:158:1: ( ( rule__AttributeGroup__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:158:1: ( ( rule__AttributeGroup__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:159:1: ( rule__AttributeGroup__Group__0 )
            {
             before(grammarAccess.getAttributeGroupAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:160:1: ( rule__AttributeGroup__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:160:2: rule__AttributeGroup__Group__0
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__0_in_ruleAttributeGroup274);
            rule__AttributeGroup__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeGroupAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttributeGroup"


    // $ANTLR start "entryRuleQualifiedName"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:172:1: entryRuleQualifiedName : ruleQualifiedName EOF ;
    public final void entryRuleQualifiedName() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:173:1: ( ruleQualifiedName EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:174:1: ruleQualifiedName EOF
            {
             before(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName301);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getQualifiedNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedName308); 

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
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:181:1: ruleQualifiedName : ( ( rule__QualifiedName__Group__0 ) ) ;
    public final void ruleQualifiedName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:185:2: ( ( ( rule__QualifiedName__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:186:1: ( ( rule__QualifiedName__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:186:1: ( ( rule__QualifiedName__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:187:1: ( rule__QualifiedName__Group__0 )
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:188:1: ( rule__QualifiedName__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:188:2: rule__QualifiedName__Group__0
            {
            pushFollow(FOLLOW_rule__QualifiedName__Group__0_in_ruleQualifiedName334);
            rule__QualifiedName__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedNameAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleAssociation"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:200:1: entryRuleAssociation : ruleAssociation EOF ;
    public final void entryRuleAssociation() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:201:1: ( ruleAssociation EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:202:1: ruleAssociation EOF
            {
             before(grammarAccess.getAssociationRule()); 
            pushFollow(FOLLOW_ruleAssociation_in_entryRuleAssociation361);
            ruleAssociation();

            state._fsp--;

             after(grammarAccess.getAssociationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAssociation368); 

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
    // $ANTLR end "entryRuleAssociation"


    // $ANTLR start "ruleAssociation"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:209:1: ruleAssociation : ( ( rule__Association__Group__0 ) ) ;
    public final void ruleAssociation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:213:2: ( ( ( rule__Association__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:214:1: ( ( rule__Association__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:214:1: ( ( rule__Association__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:215:1: ( rule__Association__Group__0 )
            {
             before(grammarAccess.getAssociationAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:216:1: ( rule__Association__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:216:2: rule__Association__Group__0
            {
            pushFollow(FOLLOW_rule__Association__Group__0_in_ruleAssociation394);
            rule__Association__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAssociation"


    // $ANTLR start "entryRuleConstraint"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:228:1: entryRuleConstraint : ruleConstraint EOF ;
    public final void entryRuleConstraint() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:229:1: ( ruleConstraint EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:230:1: ruleConstraint EOF
            {
             before(grammarAccess.getConstraintRule()); 
            pushFollow(FOLLOW_ruleConstraint_in_entryRuleConstraint421);
            ruleConstraint();

            state._fsp--;

             after(grammarAccess.getConstraintRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstraint428); 

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
    // $ANTLR end "entryRuleConstraint"


    // $ANTLR start "ruleConstraint"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:237:1: ruleConstraint : ( ( rule__Constraint__Group__0 ) ) ;
    public final void ruleConstraint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:241:2: ( ( ( rule__Constraint__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:242:1: ( ( rule__Constraint__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:242:1: ( ( rule__Constraint__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:243:1: ( rule__Constraint__Group__0 )
            {
             before(grammarAccess.getConstraintAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:244:1: ( rule__Constraint__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:244:2: rule__Constraint__Group__0
            {
            pushFollow(FOLLOW_rule__Constraint__Group__0_in_ruleConstraint454);
            rule__Constraint__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConstraintAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConstraint"


    // $ANTLR start "entryRuleCardinality"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:256:1: entryRuleCardinality : ruleCardinality EOF ;
    public final void entryRuleCardinality() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:257:1: ( ruleCardinality EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:258:1: ruleCardinality EOF
            {
             before(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_ruleCardinality_in_entryRuleCardinality481);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getCardinalityRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCardinality488); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:265:1: ruleCardinality : ( ( rule__Cardinality__Alternatives ) ) ;
    public final void ruleCardinality() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:269:2: ( ( ( rule__Cardinality__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:270:1: ( ( rule__Cardinality__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:270:1: ( ( rule__Cardinality__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:271:1: ( rule__Cardinality__Alternatives )
            {
             before(grammarAccess.getCardinalityAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:272:1: ( rule__Cardinality__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:272:2: rule__Cardinality__Alternatives
            {
            pushFollow(FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality514);
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


    // $ANTLR start "entryRuleExpression"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:284:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:285:1: ( ruleExpression EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:286:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression541);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression548); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:293:1: ruleExpression : ( ruleOr ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:297:2: ( ( ruleOr ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:298:1: ( ruleOr )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:298:1: ( ruleOr )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:299:1: ruleOr
            {
             before(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
            pushFollow(FOLLOW_ruleOr_in_ruleExpression574);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:312:1: entryRuleOr : ruleOr EOF ;
    public final void entryRuleOr() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:313:1: ( ruleOr EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:314:1: ruleOr EOF
            {
             before(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_ruleOr_in_entryRuleOr600);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getOrRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOr607); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:321:1: ruleOr : ( ( rule__Or__Group__0 ) ) ;
    public final void ruleOr() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:325:2: ( ( ( rule__Or__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:326:1: ( ( rule__Or__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:326:1: ( ( rule__Or__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:327:1: ( rule__Or__Group__0 )
            {
             before(grammarAccess.getOrAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:328:1: ( rule__Or__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:328:2: rule__Or__Group__0
            {
            pushFollow(FOLLOW_rule__Or__Group__0_in_ruleOr633);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:340:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:341:1: ( ruleAnd EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:342:1: ruleAnd EOF
            {
             before(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_ruleAnd_in_entryRuleAnd660);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getAndRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnd667); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:349:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:353:2: ( ( ( rule__And__Group__0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:354:1: ( ( rule__And__Group__0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:354:1: ( ( rule__And__Group__0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:355:1: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:356:1: ( rule__And__Group__0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:356:2: rule__And__Group__0
            {
            pushFollow(FOLLOW_rule__And__Group__0_in_ruleAnd693);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:368:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:369:1: ( rulePrimary EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:370:1: rulePrimary EOF
            {
             before(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary720);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary727); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:377:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:381:2: ( ( ( rule__Primary__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:382:1: ( ( rule__Primary__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:382:1: ( ( rule__Primary__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:383:1: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:384:1: ( rule__Primary__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:384:2: rule__Primary__Alternatives
            {
            pushFollow(FOLLOW_rule__Primary__Alternatives_in_rulePrimary753);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:396:1: entryRuleAtomic : ruleAtomic EOF ;
    public final void entryRuleAtomic() throws RecognitionException {
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:397:1: ( ruleAtomic EOF )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:398:1: ruleAtomic EOF
            {
             before(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_ruleAtomic_in_entryRuleAtomic780);
            ruleAtomic();

            state._fsp--;

             after(grammarAccess.getAtomicRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomic787); 

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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:405:1: ruleAtomic : ( ( rule__Atomic__Alternatives ) ) ;
    public final void ruleAtomic() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:409:2: ( ( ( rule__Atomic__Alternatives ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:410:1: ( ( rule__Atomic__Alternatives ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:410:1: ( ( rule__Atomic__Alternatives ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:411:1: ( rule__Atomic__Alternatives )
            {
             before(grammarAccess.getAtomicAccess().getAlternatives()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:412:1: ( rule__Atomic__Alternatives )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:412:2: rule__Atomic__Alternatives
            {
            pushFollow(FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic813);
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


    // $ANTLR start "rule__Entity__AttributesAlternatives_5_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:424:1: rule__Entity__AttributesAlternatives_5_0 : ( ( ruleAttributeGroup ) | ( ruleAttribute ) );
    public final void rule__Entity__AttributesAlternatives_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:428:1: ( ( ruleAttributeGroup ) | ( ruleAttribute ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==17) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_ID) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:429:1: ( ruleAttributeGroup )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:429:1: ( ruleAttributeGroup )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:430:1: ruleAttributeGroup
                    {
                     before(grammarAccess.getEntityAccess().getAttributesAttributeGroupParserRuleCall_5_0_0()); 
                    pushFollow(FOLLOW_ruleAttributeGroup_in_rule__Entity__AttributesAlternatives_5_0849);
                    ruleAttributeGroup();

                    state._fsp--;

                     after(grammarAccess.getEntityAccess().getAttributesAttributeGroupParserRuleCall_5_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:435:6: ( ruleAttribute )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:435:6: ( ruleAttribute )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:436:1: ruleAttribute
                    {
                     before(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_5_0_1()); 
                    pushFollow(FOLLOW_ruleAttribute_in_rule__Entity__AttributesAlternatives_5_0866);
                    ruleAttribute();

                    state._fsp--;

                     after(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_5_0_1()); 

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
    // $ANTLR end "rule__Entity__AttributesAlternatives_5_0"


    // $ANTLR start "rule__Cardinality__Alternatives"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:446:1: rule__Cardinality__Alternatives : ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) );
    public final void rule__Cardinality__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:450:1: ( ( RULE_INT ) | ( ( rule__Cardinality__Group_1__0 ) ) | ( ( rule__Cardinality__Group_2__0 ) ) | ( ( rule__Cardinality__Group_3__0 ) ) )
            int alt2=4;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_INT) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==EOF||LA2_1==22) ) {
                    alt2=1;
                }
                else if ( (LA2_1==24) ) {
                    switch ( input.LA(3) ) {
                    case 25:
                        {
                        alt2=3;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt2=2;
                        }
                        break;
                    case 26:
                        {
                        alt2=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 3, input);

                        throw nvae;
                    }

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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:451:1: ( RULE_INT )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:451:1: ( RULE_INT )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:452:1: RULE_INT
                    {
                     before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives898); 
                     after(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:457:6: ( ( rule__Cardinality__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:457:6: ( ( rule__Cardinality__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:458:1: ( rule__Cardinality__Group_1__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:459:1: ( rule__Cardinality__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:459:2: rule__Cardinality__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives915);
                    rule__Cardinality__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:463:6: ( ( rule__Cardinality__Group_2__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:463:6: ( ( rule__Cardinality__Group_2__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:464:1: ( rule__Cardinality__Group_2__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_2()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:465:1: ( rule__Cardinality__Group_2__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:465:2: rule__Cardinality__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives933);
                    rule__Cardinality__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCardinalityAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:469:6: ( ( rule__Cardinality__Group_3__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:469:6: ( ( rule__Cardinality__Group_3__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:470:1: ( rule__Cardinality__Group_3__0 )
                    {
                     before(grammarAccess.getCardinalityAccess().getGroup_3()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:471:1: ( rule__Cardinality__Group_3__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:471:2: rule__Cardinality__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives951);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:480:1: rule__Primary__Alternatives : ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:484:1: ( ( ( rule__Primary__Group_0__0 ) ) | ( ( rule__Primary__Group_1__0 ) ) | ( ruleAtomic ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt3=1;
                }
                break;
            case 29:
                {
                alt3=2;
                }
                break;
            case RULE_ID:
            case 30:
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:485:1: ( ( rule__Primary__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:485:1: ( ( rule__Primary__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:486:1: ( rule__Primary__Group_0__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:487:1: ( rule__Primary__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:487:2: rule__Primary__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives984);
                    rule__Primary__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:491:6: ( ( rule__Primary__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:491:6: ( ( rule__Primary__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:492:1: ( rule__Primary__Group_1__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:493:1: ( rule__Primary__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:493:2: rule__Primary__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1002);
                    rule__Primary__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:497:6: ( ruleAtomic )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:497:6: ( ruleAtomic )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:498:1: ruleAtomic
                    {
                     before(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1020);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:508:1: rule__Atomic__Alternatives : ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) );
    public final void rule__Atomic__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:512:1: ( ( ( rule__Atomic__Group_0__0 ) ) | ( ( rule__Atomic__Group_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==30) ) {
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
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:513:1: ( ( rule__Atomic__Group_0__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:513:1: ( ( rule__Atomic__Group_0__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:514:1: ( rule__Atomic__Group_0__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_0()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:515:1: ( rule__Atomic__Group_0__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:515:2: rule__Atomic__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1052);
                    rule__Atomic__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAtomicAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:519:6: ( ( rule__Atomic__Group_1__0 ) )
                    {
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:519:6: ( ( rule__Atomic__Group_1__0 ) )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:520:1: ( rule__Atomic__Group_1__0 )
                    {
                     before(grammarAccess.getAtomicAccess().getGroup_1()); 
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:521:1: ( rule__Atomic__Group_1__0 )
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:521:2: rule__Atomic__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1070);
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


    // $ANTLR start "rule__DataModel__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:532:1: rule__DataModel__Group__0 : rule__DataModel__Group__0__Impl rule__DataModel__Group__1 ;
    public final void rule__DataModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:536:1: ( rule__DataModel__Group__0__Impl rule__DataModel__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:537:2: rule__DataModel__Group__0__Impl rule__DataModel__Group__1
            {
            pushFollow(FOLLOW_rule__DataModel__Group__0__Impl_in_rule__DataModel__Group__01101);
            rule__DataModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DataModel__Group__1_in_rule__DataModel__Group__01104);
            rule__DataModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__Group__0"


    // $ANTLR start "rule__DataModel__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:544:1: rule__DataModel__Group__0__Impl : ( ( rule__DataModel__EntitiesAssignment_0 )* ) ;
    public final void rule__DataModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:548:1: ( ( ( rule__DataModel__EntitiesAssignment_0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:549:1: ( ( rule__DataModel__EntitiesAssignment_0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:549:1: ( ( rule__DataModel__EntitiesAssignment_0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:550:1: ( rule__DataModel__EntitiesAssignment_0 )*
            {
             before(grammarAccess.getDataModelAccess().getEntitiesAssignment_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:551:1: ( rule__DataModel__EntitiesAssignment_0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==11) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:551:2: rule__DataModel__EntitiesAssignment_0
            	    {
            	    pushFollow(FOLLOW_rule__DataModel__EntitiesAssignment_0_in_rule__DataModel__Group__0__Impl1131);
            	    rule__DataModel__EntitiesAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getDataModelAccess().getEntitiesAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__Group__0__Impl"


    // $ANTLR start "rule__DataModel__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:561:1: rule__DataModel__Group__1 : rule__DataModel__Group__1__Impl rule__DataModel__Group__2 ;
    public final void rule__DataModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:565:1: ( rule__DataModel__Group__1__Impl rule__DataModel__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:566:2: rule__DataModel__Group__1__Impl rule__DataModel__Group__2
            {
            pushFollow(FOLLOW_rule__DataModel__Group__1__Impl_in_rule__DataModel__Group__11162);
            rule__DataModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DataModel__Group__2_in_rule__DataModel__Group__11165);
            rule__DataModel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__Group__1"


    // $ANTLR start "rule__DataModel__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:573:1: rule__DataModel__Group__1__Impl : ( ( rule__DataModel__AssociatiosAssignment_1 )* ) ;
    public final void rule__DataModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:577:1: ( ( ( rule__DataModel__AssociatiosAssignment_1 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:578:1: ( ( rule__DataModel__AssociatiosAssignment_1 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:578:1: ( ( rule__DataModel__AssociatiosAssignment_1 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:579:1: ( rule__DataModel__AssociatiosAssignment_1 )*
            {
             before(grammarAccess.getDataModelAccess().getAssociatiosAssignment_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:580:1: ( rule__DataModel__AssociatiosAssignment_1 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==19) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:580:2: rule__DataModel__AssociatiosAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__DataModel__AssociatiosAssignment_1_in_rule__DataModel__Group__1__Impl1192);
            	    rule__DataModel__AssociatiosAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getDataModelAccess().getAssociatiosAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__Group__1__Impl"


    // $ANTLR start "rule__DataModel__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:590:1: rule__DataModel__Group__2 : rule__DataModel__Group__2__Impl ;
    public final void rule__DataModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:594:1: ( rule__DataModel__Group__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:595:2: rule__DataModel__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__DataModel__Group__2__Impl_in_rule__DataModel__Group__21223);
            rule__DataModel__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__Group__2"


    // $ANTLR start "rule__DataModel__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:601:1: rule__DataModel__Group__2__Impl : ( ( rule__DataModel__ConstraintAssignment_2 )* ) ;
    public final void rule__DataModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:605:1: ( ( ( rule__DataModel__ConstraintAssignment_2 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:606:1: ( ( rule__DataModel__ConstraintAssignment_2 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:606:1: ( ( rule__DataModel__ConstraintAssignment_2 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:607:1: ( rule__DataModel__ConstraintAssignment_2 )*
            {
             before(grammarAccess.getDataModelAccess().getConstraintAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:608:1: ( rule__DataModel__ConstraintAssignment_2 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:608:2: rule__DataModel__ConstraintAssignment_2
            	    {
            	    pushFollow(FOLLOW_rule__DataModel__ConstraintAssignment_2_in_rule__DataModel__Group__2__Impl1250);
            	    rule__DataModel__ConstraintAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getDataModelAccess().getConstraintAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__Group__2__Impl"


    // $ANTLR start "rule__Entity__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:624:1: rule__Entity__Group__0 : rule__Entity__Group__0__Impl rule__Entity__Group__1 ;
    public final void rule__Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:628:1: ( rule__Entity__Group__0__Impl rule__Entity__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:629:2: rule__Entity__Group__0__Impl rule__Entity__Group__1
            {
            pushFollow(FOLLOW_rule__Entity__Group__0__Impl_in_rule__Entity__Group__01287);
            rule__Entity__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group__1_in_rule__Entity__Group__01290);
            rule__Entity__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0"


    // $ANTLR start "rule__Entity__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:636:1: rule__Entity__Group__0__Impl : ( 'Entityy' ) ;
    public final void rule__Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:640:1: ( ( 'Entityy' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:641:1: ( 'Entityy' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:641:1: ( 'Entityy' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:642:1: 'Entityy'
            {
             before(grammarAccess.getEntityAccess().getEntityyKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__Entity__Group__0__Impl1318); 
             after(grammarAccess.getEntityAccess().getEntityyKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0__Impl"


    // $ANTLR start "rule__Entity__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:655:1: rule__Entity__Group__1 : rule__Entity__Group__1__Impl rule__Entity__Group__2 ;
    public final void rule__Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:659:1: ( rule__Entity__Group__1__Impl rule__Entity__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:660:2: rule__Entity__Group__1__Impl rule__Entity__Group__2
            {
            pushFollow(FOLLOW_rule__Entity__Group__1__Impl_in_rule__Entity__Group__11349);
            rule__Entity__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group__2_in_rule__Entity__Group__11352);
            rule__Entity__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__1"


    // $ANTLR start "rule__Entity__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:667:1: rule__Entity__Group__1__Impl : ( ( rule__Entity__NameAssignment_1 ) ) ;
    public final void rule__Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:671:1: ( ( ( rule__Entity__NameAssignment_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:672:1: ( ( rule__Entity__NameAssignment_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:672:1: ( ( rule__Entity__NameAssignment_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:673:1: ( rule__Entity__NameAssignment_1 )
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:674:1: ( rule__Entity__NameAssignment_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:674:2: rule__Entity__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Entity__NameAssignment_1_in_rule__Entity__Group__1__Impl1379);
            rule__Entity__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__1__Impl"


    // $ANTLR start "rule__Entity__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:684:1: rule__Entity__Group__2 : rule__Entity__Group__2__Impl rule__Entity__Group__3 ;
    public final void rule__Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:688:1: ( rule__Entity__Group__2__Impl rule__Entity__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:689:2: rule__Entity__Group__2__Impl rule__Entity__Group__3
            {
            pushFollow(FOLLOW_rule__Entity__Group__2__Impl_in_rule__Entity__Group__21409);
            rule__Entity__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group__3_in_rule__Entity__Group__21412);
            rule__Entity__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__2"


    // $ANTLR start "rule__Entity__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:696:1: rule__Entity__Group__2__Impl : ( ( rule__Entity__ExistsAssignment_2 )? ) ;
    public final void rule__Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:700:1: ( ( ( rule__Entity__ExistsAssignment_2 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:701:1: ( ( rule__Entity__ExistsAssignment_2 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:701:1: ( ( rule__Entity__ExistsAssignment_2 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:702:1: ( rule__Entity__ExistsAssignment_2 )?
            {
             before(grammarAccess.getEntityAccess().getExistsAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:703:1: ( rule__Entity__ExistsAssignment_2 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==31) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:703:2: rule__Entity__ExistsAssignment_2
                    {
                    pushFollow(FOLLOW_rule__Entity__ExistsAssignment_2_in_rule__Entity__Group__2__Impl1439);
                    rule__Entity__ExistsAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEntityAccess().getExistsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__2__Impl"


    // $ANTLR start "rule__Entity__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:713:1: rule__Entity__Group__3 : rule__Entity__Group__3__Impl rule__Entity__Group__4 ;
    public final void rule__Entity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:717:1: ( rule__Entity__Group__3__Impl rule__Entity__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:718:2: rule__Entity__Group__3__Impl rule__Entity__Group__4
            {
            pushFollow(FOLLOW_rule__Entity__Group__3__Impl_in_rule__Entity__Group__31470);
            rule__Entity__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group__4_in_rule__Entity__Group__31473);
            rule__Entity__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__3"


    // $ANTLR start "rule__Entity__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:725:1: rule__Entity__Group__3__Impl : ( ( rule__Entity__Group_3__0 )? ) ;
    public final void rule__Entity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:729:1: ( ( ( rule__Entity__Group_3__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:730:1: ( ( rule__Entity__Group_3__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:730:1: ( ( rule__Entity__Group_3__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:731:1: ( rule__Entity__Group_3__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:732:1: ( rule__Entity__Group_3__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==14) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:732:2: rule__Entity__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__Entity__Group_3__0_in_rule__Entity__Group__3__Impl1500);
                    rule__Entity__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEntityAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__3__Impl"


    // $ANTLR start "rule__Entity__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:742:1: rule__Entity__Group__4 : rule__Entity__Group__4__Impl rule__Entity__Group__5 ;
    public final void rule__Entity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:746:1: ( rule__Entity__Group__4__Impl rule__Entity__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:747:2: rule__Entity__Group__4__Impl rule__Entity__Group__5
            {
            pushFollow(FOLLOW_rule__Entity__Group__4__Impl_in_rule__Entity__Group__41531);
            rule__Entity__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group__5_in_rule__Entity__Group__41534);
            rule__Entity__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__4"


    // $ANTLR start "rule__Entity__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:754:1: rule__Entity__Group__4__Impl : ( '{' ) ;
    public final void rule__Entity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:758:1: ( ( '{' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:759:1: ( '{' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:759:1: ( '{' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:760:1: '{'
            {
             before(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,12,FOLLOW_12_in_rule__Entity__Group__4__Impl1562); 
             after(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__4__Impl"


    // $ANTLR start "rule__Entity__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:773:1: rule__Entity__Group__5 : rule__Entity__Group__5__Impl rule__Entity__Group__6 ;
    public final void rule__Entity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:777:1: ( rule__Entity__Group__5__Impl rule__Entity__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:778:2: rule__Entity__Group__5__Impl rule__Entity__Group__6
            {
            pushFollow(FOLLOW_rule__Entity__Group__5__Impl_in_rule__Entity__Group__51593);
            rule__Entity__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group__6_in_rule__Entity__Group__51596);
            rule__Entity__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__5"


    // $ANTLR start "rule__Entity__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:785:1: rule__Entity__Group__5__Impl : ( ( rule__Entity__AttributesAssignment_5 )* ) ;
    public final void rule__Entity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:789:1: ( ( ( rule__Entity__AttributesAssignment_5 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:790:1: ( ( rule__Entity__AttributesAssignment_5 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:790:1: ( ( rule__Entity__AttributesAssignment_5 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:791:1: ( rule__Entity__AttributesAssignment_5 )*
            {
             before(grammarAccess.getEntityAccess().getAttributesAssignment_5()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:792:1: ( rule__Entity__AttributesAssignment_5 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_ID||LA10_0==17) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:792:2: rule__Entity__AttributesAssignment_5
            	    {
            	    pushFollow(FOLLOW_rule__Entity__AttributesAssignment_5_in_rule__Entity__Group__5__Impl1623);
            	    rule__Entity__AttributesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getAttributesAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__5__Impl"


    // $ANTLR start "rule__Entity__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:802:1: rule__Entity__Group__6 : rule__Entity__Group__6__Impl ;
    public final void rule__Entity__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:806:1: ( rule__Entity__Group__6__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:807:2: rule__Entity__Group__6__Impl
            {
            pushFollow(FOLLOW_rule__Entity__Group__6__Impl_in_rule__Entity__Group__61654);
            rule__Entity__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__6"


    // $ANTLR start "rule__Entity__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:813:1: rule__Entity__Group__6__Impl : ( '}' ) ;
    public final void rule__Entity__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:817:1: ( ( '}' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:818:1: ( '}' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:818:1: ( '}' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:819:1: '}'
            {
             before(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6()); 
            match(input,13,FOLLOW_13_in_rule__Entity__Group__6__Impl1682); 
             after(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__6__Impl"


    // $ANTLR start "rule__Entity__Group_3__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:846:1: rule__Entity__Group_3__0 : rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1 ;
    public final void rule__Entity__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:850:1: ( rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:851:2: rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1
            {
            pushFollow(FOLLOW_rule__Entity__Group_3__0__Impl_in_rule__Entity__Group_3__01727);
            rule__Entity__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Entity__Group_3__1_in_rule__Entity__Group_3__01730);
            rule__Entity__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__0"


    // $ANTLR start "rule__Entity__Group_3__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:858:1: rule__Entity__Group_3__0__Impl : ( 'dependsOn' ) ;
    public final void rule__Entity__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:862:1: ( ( 'dependsOn' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:863:1: ( 'dependsOn' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:863:1: ( 'dependsOn' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:864:1: 'dependsOn'
            {
             before(grammarAccess.getEntityAccess().getDependsOnKeyword_3_0()); 
            match(input,14,FOLLOW_14_in_rule__Entity__Group_3__0__Impl1758); 
             after(grammarAccess.getEntityAccess().getDependsOnKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__0__Impl"


    // $ANTLR start "rule__Entity__Group_3__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:877:1: rule__Entity__Group_3__1 : rule__Entity__Group_3__1__Impl ;
    public final void rule__Entity__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:881:1: ( rule__Entity__Group_3__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:882:2: rule__Entity__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__Entity__Group_3__1__Impl_in_rule__Entity__Group_3__11789);
            rule__Entity__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__1"


    // $ANTLR start "rule__Entity__Group_3__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:888:1: rule__Entity__Group_3__1__Impl : ( ( rule__Entity__DependsOnAssignment_3_1 ) ) ;
    public final void rule__Entity__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:892:1: ( ( ( rule__Entity__DependsOnAssignment_3_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:893:1: ( ( rule__Entity__DependsOnAssignment_3_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:893:1: ( ( rule__Entity__DependsOnAssignment_3_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:894:1: ( rule__Entity__DependsOnAssignment_3_1 )
            {
             before(grammarAccess.getEntityAccess().getDependsOnAssignment_3_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:895:1: ( rule__Entity__DependsOnAssignment_3_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:895:2: rule__Entity__DependsOnAssignment_3_1
            {
            pushFollow(FOLLOW_rule__Entity__DependsOnAssignment_3_1_in_rule__Entity__Group_3__1__Impl1816);
            rule__Entity__DependsOnAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getDependsOnAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__1__Impl"


    // $ANTLR start "rule__Attribute__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:909:1: rule__Attribute__Group__0 : rule__Attribute__Group__0__Impl rule__Attribute__Group__1 ;
    public final void rule__Attribute__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:913:1: ( rule__Attribute__Group__0__Impl rule__Attribute__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:914:2: rule__Attribute__Group__0__Impl rule__Attribute__Group__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__01850);
            rule__Attribute__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__01853);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:921:1: rule__Attribute__Group__0__Impl : ( ( rule__Attribute__NameAssignment_0 ) ) ;
    public final void rule__Attribute__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:925:1: ( ( ( rule__Attribute__NameAssignment_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:926:1: ( ( rule__Attribute__NameAssignment_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:926:1: ( ( rule__Attribute__NameAssignment_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:927:1: ( rule__Attribute__NameAssignment_0 )
            {
             before(grammarAccess.getAttributeAccess().getNameAssignment_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:928:1: ( rule__Attribute__NameAssignment_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:928:2: rule__Attribute__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__Attribute__NameAssignment_0_in_rule__Attribute__Group__0__Impl1880);
            rule__Attribute__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getNameAssignment_0()); 

            }


            }

        }
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:938:1: rule__Attribute__Group__1 : rule__Attribute__Group__1__Impl rule__Attribute__Group__2 ;
    public final void rule__Attribute__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:942:1: ( rule__Attribute__Group__1__Impl rule__Attribute__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:943:2: rule__Attribute__Group__1__Impl rule__Attribute__Group__2
            {
            pushFollow(FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__11910);
            rule__Attribute__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__11913);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:950:1: rule__Attribute__Group__1__Impl : ( ':' ) ;
    public final void rule__Attribute__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:954:1: ( ( ':' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:955:1: ( ':' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:955:1: ( ':' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:956:1: ':'
            {
             before(grammarAccess.getAttributeAccess().getColonKeyword_1()); 
            match(input,15,FOLLOW_15_in_rule__Attribute__Group__1__Impl1941); 
             after(grammarAccess.getAttributeAccess().getColonKeyword_1()); 

            }


            }

        }
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:969:1: rule__Attribute__Group__2 : rule__Attribute__Group__2__Impl rule__Attribute__Group__3 ;
    public final void rule__Attribute__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:973:1: ( rule__Attribute__Group__2__Impl rule__Attribute__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:974:2: rule__Attribute__Group__2__Impl rule__Attribute__Group__3
            {
            pushFollow(FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__21972);
            rule__Attribute__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__21975);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:981:1: rule__Attribute__Group__2__Impl : ( ( rule__Attribute__TypeAssignment_2 ) ) ;
    public final void rule__Attribute__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:985:1: ( ( ( rule__Attribute__TypeAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:986:1: ( ( rule__Attribute__TypeAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:986:1: ( ( rule__Attribute__TypeAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:987:1: ( rule__Attribute__TypeAssignment_2 )
            {
             before(grammarAccess.getAttributeAccess().getTypeAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:988:1: ( rule__Attribute__TypeAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:988:2: rule__Attribute__TypeAssignment_2
            {
            pushFollow(FOLLOW_rule__Attribute__TypeAssignment_2_in_rule__Attribute__Group__2__Impl2002);
            rule__Attribute__TypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getTypeAssignment_2()); 

            }


            }

        }
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:998:1: rule__Attribute__Group__3 : rule__Attribute__Group__3__Impl rule__Attribute__Group__4 ;
    public final void rule__Attribute__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1002:1: ( rule__Attribute__Group__3__Impl rule__Attribute__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1003:2: rule__Attribute__Group__3__Impl rule__Attribute__Group__4
            {
            pushFollow(FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__32032);
            rule__Attribute__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group__4_in_rule__Attribute__Group__32035);
            rule__Attribute__Group__4();

            state._fsp--;


            }

        }
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1010:1: rule__Attribute__Group__3__Impl : ( ( rule__Attribute__MandatoryAssignment_3 )? ) ;
    public final void rule__Attribute__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1014:1: ( ( ( rule__Attribute__MandatoryAssignment_3 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1015:1: ( ( rule__Attribute__MandatoryAssignment_3 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1015:1: ( ( rule__Attribute__MandatoryAssignment_3 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1016:1: ( rule__Attribute__MandatoryAssignment_3 )?
            {
             before(grammarAccess.getAttributeAccess().getMandatoryAssignment_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1017:1: ( rule__Attribute__MandatoryAssignment_3 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==32) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1017:2: rule__Attribute__MandatoryAssignment_3
                    {
                    pushFollow(FOLLOW_rule__Attribute__MandatoryAssignment_3_in_rule__Attribute__Group__3__Impl2062);
                    rule__Attribute__MandatoryAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getMandatoryAssignment_3()); 

            }


            }

        }
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


    // $ANTLR start "rule__Attribute__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1027:1: rule__Attribute__Group__4 : rule__Attribute__Group__4__Impl ;
    public final void rule__Attribute__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1031:1: ( rule__Attribute__Group__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1032:2: rule__Attribute__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group__4__Impl_in_rule__Attribute__Group__42093);
            rule__Attribute__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__4"


    // $ANTLR start "rule__Attribute__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1038:1: rule__Attribute__Group__4__Impl : ( ( rule__Attribute__Group_4__0 )? ) ;
    public final void rule__Attribute__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1042:1: ( ( ( rule__Attribute__Group_4__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1043:1: ( ( rule__Attribute__Group_4__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1043:1: ( ( rule__Attribute__Group_4__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1044:1: ( rule__Attribute__Group_4__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1045:1: ( rule__Attribute__Group_4__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==14) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1045:2: rule__Attribute__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__Attribute__Group_4__0_in_rule__Attribute__Group__4__Impl2120);
                    rule__Attribute__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__4__Impl"


    // $ANTLR start "rule__Attribute__Group_4__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1065:1: rule__Attribute__Group_4__0 : rule__Attribute__Group_4__0__Impl rule__Attribute__Group_4__1 ;
    public final void rule__Attribute__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1069:1: ( rule__Attribute__Group_4__0__Impl rule__Attribute__Group_4__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1070:2: rule__Attribute__Group_4__0__Impl rule__Attribute__Group_4__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group_4__0__Impl_in_rule__Attribute__Group_4__02161);
            rule__Attribute__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group_4__1_in_rule__Attribute__Group_4__02164);
            rule__Attribute__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4__0"


    // $ANTLR start "rule__Attribute__Group_4__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1077:1: rule__Attribute__Group_4__0__Impl : ( 'dependsOn' ) ;
    public final void rule__Attribute__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1081:1: ( ( 'dependsOn' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1082:1: ( 'dependsOn' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1082:1: ( 'dependsOn' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1083:1: 'dependsOn'
            {
             before(grammarAccess.getAttributeAccess().getDependsOnKeyword_4_0()); 
            match(input,14,FOLLOW_14_in_rule__Attribute__Group_4__0__Impl2192); 
             after(grammarAccess.getAttributeAccess().getDependsOnKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4__0__Impl"


    // $ANTLR start "rule__Attribute__Group_4__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1096:1: rule__Attribute__Group_4__1 : rule__Attribute__Group_4__1__Impl rule__Attribute__Group_4__2 ;
    public final void rule__Attribute__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1100:1: ( rule__Attribute__Group_4__1__Impl rule__Attribute__Group_4__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1101:2: rule__Attribute__Group_4__1__Impl rule__Attribute__Group_4__2
            {
            pushFollow(FOLLOW_rule__Attribute__Group_4__1__Impl_in_rule__Attribute__Group_4__12223);
            rule__Attribute__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group_4__2_in_rule__Attribute__Group_4__12226);
            rule__Attribute__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4__1"


    // $ANTLR start "rule__Attribute__Group_4__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1108:1: rule__Attribute__Group_4__1__Impl : ( ( rule__Attribute__DependsOnAssignment_4_1 ) ) ;
    public final void rule__Attribute__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1112:1: ( ( ( rule__Attribute__DependsOnAssignment_4_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1113:1: ( ( rule__Attribute__DependsOnAssignment_4_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1113:1: ( ( rule__Attribute__DependsOnAssignment_4_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1114:1: ( rule__Attribute__DependsOnAssignment_4_1 )
            {
             before(grammarAccess.getAttributeAccess().getDependsOnAssignment_4_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1115:1: ( rule__Attribute__DependsOnAssignment_4_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1115:2: rule__Attribute__DependsOnAssignment_4_1
            {
            pushFollow(FOLLOW_rule__Attribute__DependsOnAssignment_4_1_in_rule__Attribute__Group_4__1__Impl2253);
            rule__Attribute__DependsOnAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getDependsOnAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4__1__Impl"


    // $ANTLR start "rule__Attribute__Group_4__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1125:1: rule__Attribute__Group_4__2 : rule__Attribute__Group_4__2__Impl ;
    public final void rule__Attribute__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1129:1: ( rule__Attribute__Group_4__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1130:2: rule__Attribute__Group_4__2__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group_4__2__Impl_in_rule__Attribute__Group_4__22283);
            rule__Attribute__Group_4__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4__2"


    // $ANTLR start "rule__Attribute__Group_4__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1136:1: rule__Attribute__Group_4__2__Impl : ( ( rule__Attribute__Group_4_2__0 )* ) ;
    public final void rule__Attribute__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1140:1: ( ( ( rule__Attribute__Group_4_2__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1141:1: ( ( rule__Attribute__Group_4_2__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1141:1: ( ( rule__Attribute__Group_4_2__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1142:1: ( rule__Attribute__Group_4_2__0 )*
            {
             before(grammarAccess.getAttributeAccess().getGroup_4_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1143:1: ( rule__Attribute__Group_4_2__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==16) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1143:2: rule__Attribute__Group_4_2__0
            	    {
            	    pushFollow(FOLLOW_rule__Attribute__Group_4_2__0_in_rule__Attribute__Group_4__2__Impl2310);
            	    rule__Attribute__Group_4_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getAttributeAccess().getGroup_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4__2__Impl"


    // $ANTLR start "rule__Attribute__Group_4_2__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1159:1: rule__Attribute__Group_4_2__0 : rule__Attribute__Group_4_2__0__Impl rule__Attribute__Group_4_2__1 ;
    public final void rule__Attribute__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1163:1: ( rule__Attribute__Group_4_2__0__Impl rule__Attribute__Group_4_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1164:2: rule__Attribute__Group_4_2__0__Impl rule__Attribute__Group_4_2__1
            {
            pushFollow(FOLLOW_rule__Attribute__Group_4_2__0__Impl_in_rule__Attribute__Group_4_2__02347);
            rule__Attribute__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Attribute__Group_4_2__1_in_rule__Attribute__Group_4_2__02350);
            rule__Attribute__Group_4_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4_2__0"


    // $ANTLR start "rule__Attribute__Group_4_2__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1171:1: rule__Attribute__Group_4_2__0__Impl : ( ',' ) ;
    public final void rule__Attribute__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1175:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1176:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1176:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1177:1: ','
            {
             before(grammarAccess.getAttributeAccess().getCommaKeyword_4_2_0()); 
            match(input,16,FOLLOW_16_in_rule__Attribute__Group_4_2__0__Impl2378); 
             after(grammarAccess.getAttributeAccess().getCommaKeyword_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4_2__0__Impl"


    // $ANTLR start "rule__Attribute__Group_4_2__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1190:1: rule__Attribute__Group_4_2__1 : rule__Attribute__Group_4_2__1__Impl ;
    public final void rule__Attribute__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1194:1: ( rule__Attribute__Group_4_2__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1195:2: rule__Attribute__Group_4_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Attribute__Group_4_2__1__Impl_in_rule__Attribute__Group_4_2__12409);
            rule__Attribute__Group_4_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4_2__1"


    // $ANTLR start "rule__Attribute__Group_4_2__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1201:1: rule__Attribute__Group_4_2__1__Impl : ( ( rule__Attribute__DependsOnAssignment_4_2_1 ) ) ;
    public final void rule__Attribute__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1205:1: ( ( ( rule__Attribute__DependsOnAssignment_4_2_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1206:1: ( ( rule__Attribute__DependsOnAssignment_4_2_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1206:1: ( ( rule__Attribute__DependsOnAssignment_4_2_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1207:1: ( rule__Attribute__DependsOnAssignment_4_2_1 )
            {
             before(grammarAccess.getAttributeAccess().getDependsOnAssignment_4_2_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1208:1: ( rule__Attribute__DependsOnAssignment_4_2_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1208:2: rule__Attribute__DependsOnAssignment_4_2_1
            {
            pushFollow(FOLLOW_rule__Attribute__DependsOnAssignment_4_2_1_in_rule__Attribute__Group_4_2__1__Impl2436);
            rule__Attribute__DependsOnAssignment_4_2_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getDependsOnAssignment_4_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_4_2__1__Impl"


    // $ANTLR start "rule__AttributeGroup__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1222:1: rule__AttributeGroup__Group__0 : rule__AttributeGroup__Group__0__Impl rule__AttributeGroup__Group__1 ;
    public final void rule__AttributeGroup__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1226:1: ( rule__AttributeGroup__Group__0__Impl rule__AttributeGroup__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1227:2: rule__AttributeGroup__Group__0__Impl rule__AttributeGroup__Group__1
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__0__Impl_in_rule__AttributeGroup__Group__02470);
            rule__AttributeGroup__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group__1_in_rule__AttributeGroup__Group__02473);
            rule__AttributeGroup__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__0"


    // $ANTLR start "rule__AttributeGroup__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1234:1: rule__AttributeGroup__Group__0__Impl : ( 'group' ) ;
    public final void rule__AttributeGroup__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1238:1: ( ( 'group' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1239:1: ( 'group' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1239:1: ( 'group' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1240:1: 'group'
            {
             before(grammarAccess.getAttributeGroupAccess().getGroupKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__AttributeGroup__Group__0__Impl2501); 
             after(grammarAccess.getAttributeGroupAccess().getGroupKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__0__Impl"


    // $ANTLR start "rule__AttributeGroup__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1253:1: rule__AttributeGroup__Group__1 : rule__AttributeGroup__Group__1__Impl rule__AttributeGroup__Group__2 ;
    public final void rule__AttributeGroup__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1257:1: ( rule__AttributeGroup__Group__1__Impl rule__AttributeGroup__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1258:2: rule__AttributeGroup__Group__1__Impl rule__AttributeGroup__Group__2
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__1__Impl_in_rule__AttributeGroup__Group__12532);
            rule__AttributeGroup__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group__2_in_rule__AttributeGroup__Group__12535);
            rule__AttributeGroup__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__1"


    // $ANTLR start "rule__AttributeGroup__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1265:1: rule__AttributeGroup__Group__1__Impl : ( ( rule__AttributeGroup__MandatoryAssignment_1 )? ) ;
    public final void rule__AttributeGroup__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1269:1: ( ( ( rule__AttributeGroup__MandatoryAssignment_1 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1270:1: ( ( rule__AttributeGroup__MandatoryAssignment_1 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1270:1: ( ( rule__AttributeGroup__MandatoryAssignment_1 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1271:1: ( rule__AttributeGroup__MandatoryAssignment_1 )?
            {
             before(grammarAccess.getAttributeGroupAccess().getMandatoryAssignment_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1272:1: ( rule__AttributeGroup__MandatoryAssignment_1 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==32) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1272:2: rule__AttributeGroup__MandatoryAssignment_1
                    {
                    pushFollow(FOLLOW_rule__AttributeGroup__MandatoryAssignment_1_in_rule__AttributeGroup__Group__1__Impl2562);
                    rule__AttributeGroup__MandatoryAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeGroupAccess().getMandatoryAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__1__Impl"


    // $ANTLR start "rule__AttributeGroup__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1282:1: rule__AttributeGroup__Group__2 : rule__AttributeGroup__Group__2__Impl rule__AttributeGroup__Group__3 ;
    public final void rule__AttributeGroup__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1286:1: ( rule__AttributeGroup__Group__2__Impl rule__AttributeGroup__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1287:2: rule__AttributeGroup__Group__2__Impl rule__AttributeGroup__Group__3
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__2__Impl_in_rule__AttributeGroup__Group__22593);
            rule__AttributeGroup__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group__3_in_rule__AttributeGroup__Group__22596);
            rule__AttributeGroup__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__2"


    // $ANTLR start "rule__AttributeGroup__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1294:1: rule__AttributeGroup__Group__2__Impl : ( ( rule__AttributeGroup__Group_2__0 )? ) ;
    public final void rule__AttributeGroup__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1298:1: ( ( ( rule__AttributeGroup__Group_2__0 )? ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1299:1: ( ( rule__AttributeGroup__Group_2__0 )? )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1299:1: ( ( rule__AttributeGroup__Group_2__0 )? )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1300:1: ( rule__AttributeGroup__Group_2__0 )?
            {
             before(grammarAccess.getAttributeGroupAccess().getGroup_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1301:1: ( rule__AttributeGroup__Group_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==14) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1301:2: rule__AttributeGroup__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__AttributeGroup__Group_2__0_in_rule__AttributeGroup__Group__2__Impl2623);
                    rule__AttributeGroup__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeGroupAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__2__Impl"


    // $ANTLR start "rule__AttributeGroup__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1311:1: rule__AttributeGroup__Group__3 : rule__AttributeGroup__Group__3__Impl rule__AttributeGroup__Group__4 ;
    public final void rule__AttributeGroup__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1315:1: ( rule__AttributeGroup__Group__3__Impl rule__AttributeGroup__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1316:2: rule__AttributeGroup__Group__3__Impl rule__AttributeGroup__Group__4
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__3__Impl_in_rule__AttributeGroup__Group__32654);
            rule__AttributeGroup__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group__4_in_rule__AttributeGroup__Group__32657);
            rule__AttributeGroup__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__3"


    // $ANTLR start "rule__AttributeGroup__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1323:1: rule__AttributeGroup__Group__3__Impl : ( '{' ) ;
    public final void rule__AttributeGroup__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1327:1: ( ( '{' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1328:1: ( '{' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1328:1: ( '{' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1329:1: '{'
            {
             before(grammarAccess.getAttributeGroupAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,12,FOLLOW_12_in_rule__AttributeGroup__Group__3__Impl2685); 
             after(grammarAccess.getAttributeGroupAccess().getLeftCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__3__Impl"


    // $ANTLR start "rule__AttributeGroup__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1342:1: rule__AttributeGroup__Group__4 : rule__AttributeGroup__Group__4__Impl rule__AttributeGroup__Group__5 ;
    public final void rule__AttributeGroup__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1346:1: ( rule__AttributeGroup__Group__4__Impl rule__AttributeGroup__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1347:2: rule__AttributeGroup__Group__4__Impl rule__AttributeGroup__Group__5
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__4__Impl_in_rule__AttributeGroup__Group__42716);
            rule__AttributeGroup__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group__5_in_rule__AttributeGroup__Group__42719);
            rule__AttributeGroup__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__4"


    // $ANTLR start "rule__AttributeGroup__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1354:1: rule__AttributeGroup__Group__4__Impl : ( ( ( rule__AttributeGroup__AttributesAssignment_4 ) ) ( ( rule__AttributeGroup__AttributesAssignment_4 )* ) ) ;
    public final void rule__AttributeGroup__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1358:1: ( ( ( ( rule__AttributeGroup__AttributesAssignment_4 ) ) ( ( rule__AttributeGroup__AttributesAssignment_4 )* ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1359:1: ( ( ( rule__AttributeGroup__AttributesAssignment_4 ) ) ( ( rule__AttributeGroup__AttributesAssignment_4 )* ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1359:1: ( ( ( rule__AttributeGroup__AttributesAssignment_4 ) ) ( ( rule__AttributeGroup__AttributesAssignment_4 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1360:1: ( ( rule__AttributeGroup__AttributesAssignment_4 ) ) ( ( rule__AttributeGroup__AttributesAssignment_4 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1360:1: ( ( rule__AttributeGroup__AttributesAssignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1361:1: ( rule__AttributeGroup__AttributesAssignment_4 )
            {
             before(grammarAccess.getAttributeGroupAccess().getAttributesAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1362:1: ( rule__AttributeGroup__AttributesAssignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1362:2: rule__AttributeGroup__AttributesAssignment_4
            {
            pushFollow(FOLLOW_rule__AttributeGroup__AttributesAssignment_4_in_rule__AttributeGroup__Group__4__Impl2748);
            rule__AttributeGroup__AttributesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getAttributeGroupAccess().getAttributesAssignment_4()); 

            }

            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1365:1: ( ( rule__AttributeGroup__AttributesAssignment_4 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1366:1: ( rule__AttributeGroup__AttributesAssignment_4 )*
            {
             before(grammarAccess.getAttributeGroupAccess().getAttributesAssignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1367:1: ( rule__AttributeGroup__AttributesAssignment_4 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1367:2: rule__AttributeGroup__AttributesAssignment_4
            	    {
            	    pushFollow(FOLLOW_rule__AttributeGroup__AttributesAssignment_4_in_rule__AttributeGroup__Group__4__Impl2760);
            	    rule__AttributeGroup__AttributesAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getAttributeGroupAccess().getAttributesAssignment_4()); 

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
    // $ANTLR end "rule__AttributeGroup__Group__4__Impl"


    // $ANTLR start "rule__AttributeGroup__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1378:1: rule__AttributeGroup__Group__5 : rule__AttributeGroup__Group__5__Impl ;
    public final void rule__AttributeGroup__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1382:1: ( rule__AttributeGroup__Group__5__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1383:2: rule__AttributeGroup__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group__5__Impl_in_rule__AttributeGroup__Group__52793);
            rule__AttributeGroup__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__5"


    // $ANTLR start "rule__AttributeGroup__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1389:1: rule__AttributeGroup__Group__5__Impl : ( '}' ) ;
    public final void rule__AttributeGroup__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1393:1: ( ( '}' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1394:1: ( '}' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1394:1: ( '}' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1395:1: '}'
            {
             before(grammarAccess.getAttributeGroupAccess().getRightCurlyBracketKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__AttributeGroup__Group__5__Impl2821); 
             after(grammarAccess.getAttributeGroupAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group__5__Impl"


    // $ANTLR start "rule__AttributeGroup__Group_2__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1420:1: rule__AttributeGroup__Group_2__0 : rule__AttributeGroup__Group_2__0__Impl rule__AttributeGroup__Group_2__1 ;
    public final void rule__AttributeGroup__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1424:1: ( rule__AttributeGroup__Group_2__0__Impl rule__AttributeGroup__Group_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1425:2: rule__AttributeGroup__Group_2__0__Impl rule__AttributeGroup__Group_2__1
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group_2__0__Impl_in_rule__AttributeGroup__Group_2__02864);
            rule__AttributeGroup__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group_2__1_in_rule__AttributeGroup__Group_2__02867);
            rule__AttributeGroup__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2__0"


    // $ANTLR start "rule__AttributeGroup__Group_2__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1432:1: rule__AttributeGroup__Group_2__0__Impl : ( 'dependsOn' ) ;
    public final void rule__AttributeGroup__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1436:1: ( ( 'dependsOn' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1437:1: ( 'dependsOn' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1437:1: ( 'dependsOn' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1438:1: 'dependsOn'
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnKeyword_2_0()); 
            match(input,14,FOLLOW_14_in_rule__AttributeGroup__Group_2__0__Impl2895); 
             after(grammarAccess.getAttributeGroupAccess().getDependsOnKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2__0__Impl"


    // $ANTLR start "rule__AttributeGroup__Group_2__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1451:1: rule__AttributeGroup__Group_2__1 : rule__AttributeGroup__Group_2__1__Impl rule__AttributeGroup__Group_2__2 ;
    public final void rule__AttributeGroup__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1455:1: ( rule__AttributeGroup__Group_2__1__Impl rule__AttributeGroup__Group_2__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1456:2: rule__AttributeGroup__Group_2__1__Impl rule__AttributeGroup__Group_2__2
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group_2__1__Impl_in_rule__AttributeGroup__Group_2__12926);
            rule__AttributeGroup__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group_2__2_in_rule__AttributeGroup__Group_2__12929);
            rule__AttributeGroup__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2__1"


    // $ANTLR start "rule__AttributeGroup__Group_2__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1463:1: rule__AttributeGroup__Group_2__1__Impl : ( ( rule__AttributeGroup__DependsOnAssignment_2_1 ) ) ;
    public final void rule__AttributeGroup__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1467:1: ( ( ( rule__AttributeGroup__DependsOnAssignment_2_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1468:1: ( ( rule__AttributeGroup__DependsOnAssignment_2_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1468:1: ( ( rule__AttributeGroup__DependsOnAssignment_2_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1469:1: ( rule__AttributeGroup__DependsOnAssignment_2_1 )
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnAssignment_2_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1470:1: ( rule__AttributeGroup__DependsOnAssignment_2_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1470:2: rule__AttributeGroup__DependsOnAssignment_2_1
            {
            pushFollow(FOLLOW_rule__AttributeGroup__DependsOnAssignment_2_1_in_rule__AttributeGroup__Group_2__1__Impl2956);
            rule__AttributeGroup__DependsOnAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeGroupAccess().getDependsOnAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2__1__Impl"


    // $ANTLR start "rule__AttributeGroup__Group_2__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1480:1: rule__AttributeGroup__Group_2__2 : rule__AttributeGroup__Group_2__2__Impl ;
    public final void rule__AttributeGroup__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1484:1: ( rule__AttributeGroup__Group_2__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1485:2: rule__AttributeGroup__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group_2__2__Impl_in_rule__AttributeGroup__Group_2__22986);
            rule__AttributeGroup__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2__2"


    // $ANTLR start "rule__AttributeGroup__Group_2__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1491:1: rule__AttributeGroup__Group_2__2__Impl : ( ( rule__AttributeGroup__Group_2_2__0 )* ) ;
    public final void rule__AttributeGroup__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1495:1: ( ( ( rule__AttributeGroup__Group_2_2__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1496:1: ( ( rule__AttributeGroup__Group_2_2__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1496:1: ( ( rule__AttributeGroup__Group_2_2__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1497:1: ( rule__AttributeGroup__Group_2_2__0 )*
            {
             before(grammarAccess.getAttributeGroupAccess().getGroup_2_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1498:1: ( rule__AttributeGroup__Group_2_2__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==16) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1498:2: rule__AttributeGroup__Group_2_2__0
            	    {
            	    pushFollow(FOLLOW_rule__AttributeGroup__Group_2_2__0_in_rule__AttributeGroup__Group_2__2__Impl3013);
            	    rule__AttributeGroup__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getAttributeGroupAccess().getGroup_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2__2__Impl"


    // $ANTLR start "rule__AttributeGroup__Group_2_2__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1514:1: rule__AttributeGroup__Group_2_2__0 : rule__AttributeGroup__Group_2_2__0__Impl rule__AttributeGroup__Group_2_2__1 ;
    public final void rule__AttributeGroup__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1518:1: ( rule__AttributeGroup__Group_2_2__0__Impl rule__AttributeGroup__Group_2_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1519:2: rule__AttributeGroup__Group_2_2__0__Impl rule__AttributeGroup__Group_2_2__1
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group_2_2__0__Impl_in_rule__AttributeGroup__Group_2_2__03050);
            rule__AttributeGroup__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AttributeGroup__Group_2_2__1_in_rule__AttributeGroup__Group_2_2__03053);
            rule__AttributeGroup__Group_2_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2_2__0"


    // $ANTLR start "rule__AttributeGroup__Group_2_2__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1526:1: rule__AttributeGroup__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__AttributeGroup__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1530:1: ( ( ',' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1531:1: ( ',' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1531:1: ( ',' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1532:1: ','
            {
             before(grammarAccess.getAttributeGroupAccess().getCommaKeyword_2_2_0()); 
            match(input,16,FOLLOW_16_in_rule__AttributeGroup__Group_2_2__0__Impl3081); 
             after(grammarAccess.getAttributeGroupAccess().getCommaKeyword_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2_2__0__Impl"


    // $ANTLR start "rule__AttributeGroup__Group_2_2__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1545:1: rule__AttributeGroup__Group_2_2__1 : rule__AttributeGroup__Group_2_2__1__Impl ;
    public final void rule__AttributeGroup__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1549:1: ( rule__AttributeGroup__Group_2_2__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1550:2: rule__AttributeGroup__Group_2_2__1__Impl
            {
            pushFollow(FOLLOW_rule__AttributeGroup__Group_2_2__1__Impl_in_rule__AttributeGroup__Group_2_2__13112);
            rule__AttributeGroup__Group_2_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2_2__1"


    // $ANTLR start "rule__AttributeGroup__Group_2_2__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1556:1: rule__AttributeGroup__Group_2_2__1__Impl : ( ( rule__AttributeGroup__DependsOnAssignment_2_2_1 ) ) ;
    public final void rule__AttributeGroup__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1560:1: ( ( ( rule__AttributeGroup__DependsOnAssignment_2_2_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1561:1: ( ( rule__AttributeGroup__DependsOnAssignment_2_2_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1561:1: ( ( rule__AttributeGroup__DependsOnAssignment_2_2_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1562:1: ( rule__AttributeGroup__DependsOnAssignment_2_2_1 )
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnAssignment_2_2_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1563:1: ( rule__AttributeGroup__DependsOnAssignment_2_2_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1563:2: rule__AttributeGroup__DependsOnAssignment_2_2_1
            {
            pushFollow(FOLLOW_rule__AttributeGroup__DependsOnAssignment_2_2_1_in_rule__AttributeGroup__Group_2_2__1__Impl3139);
            rule__AttributeGroup__DependsOnAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeGroupAccess().getDependsOnAssignment_2_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__Group_2_2__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1577:1: rule__QualifiedName__Group__0 : rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 ;
    public final void rule__QualifiedName__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1581:1: ( rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1582:2: rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1
            {
            pushFollow(FOLLOW_rule__QualifiedName__Group__0__Impl_in_rule__QualifiedName__Group__03173);
            rule__QualifiedName__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedName__Group__1_in_rule__QualifiedName__Group__03176);
            rule__QualifiedName__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0"


    // $ANTLR start "rule__QualifiedName__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1589:1: rule__QualifiedName__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1593:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1594:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1594:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1595:1: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedName__Group__0__Impl3203); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1606:1: rule__QualifiedName__Group__1 : rule__QualifiedName__Group__1__Impl ;
    public final void rule__QualifiedName__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1610:1: ( rule__QualifiedName__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1611:2: rule__QualifiedName__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedName__Group__1__Impl_in_rule__QualifiedName__Group__13232);
            rule__QualifiedName__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1"


    // $ANTLR start "rule__QualifiedName__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1617:1: rule__QualifiedName__Group__1__Impl : ( ( rule__QualifiedName__Group_1__0 )* ) ;
    public final void rule__QualifiedName__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1621:1: ( ( ( rule__QualifiedName__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1622:1: ( ( rule__QualifiedName__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1622:1: ( ( rule__QualifiedName__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1623:1: ( rule__QualifiedName__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1624:1: ( rule__QualifiedName__Group_1__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==18) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1624:2: rule__QualifiedName__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__QualifiedName__Group_1__0_in_rule__QualifiedName__Group__1__Impl3259);
            	    rule__QualifiedName__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getQualifiedNameAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1638:1: rule__QualifiedName__Group_1__0 : rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 ;
    public final void rule__QualifiedName__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1642:1: ( rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1643:2: rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1
            {
            pushFollow(FOLLOW_rule__QualifiedName__Group_1__0__Impl_in_rule__QualifiedName__Group_1__03294);
            rule__QualifiedName__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedName__Group_1__1_in_rule__QualifiedName__Group_1__03297);
            rule__QualifiedName__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0"


    // $ANTLR start "rule__QualifiedName__Group_1__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1650:1: rule__QualifiedName__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedName__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1654:1: ( ( '.' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1655:1: ( '.' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1655:1: ( '.' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1656:1: '.'
            {
             before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            match(input,18,FOLLOW_18_in_rule__QualifiedName__Group_1__0__Impl3325); 
             after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1669:1: rule__QualifiedName__Group_1__1 : rule__QualifiedName__Group_1__1__Impl ;
    public final void rule__QualifiedName__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1673:1: ( rule__QualifiedName__Group_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1674:2: rule__QualifiedName__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedName__Group_1__1__Impl_in_rule__QualifiedName__Group_1__13356);
            rule__QualifiedName__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1"


    // $ANTLR start "rule__QualifiedName__Group_1__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1680:1: rule__QualifiedName__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1684:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1685:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1685:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1686:1: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedName__Group_1__1__Impl3383); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1__Impl"


    // $ANTLR start "rule__Association__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1701:1: rule__Association__Group__0 : rule__Association__Group__0__Impl rule__Association__Group__1 ;
    public final void rule__Association__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1705:1: ( rule__Association__Group__0__Impl rule__Association__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1706:2: rule__Association__Group__0__Impl rule__Association__Group__1
            {
            pushFollow(FOLLOW_rule__Association__Group__0__Impl_in_rule__Association__Group__03416);
            rule__Association__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__1_in_rule__Association__Group__03419);
            rule__Association__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__0"


    // $ANTLR start "rule__Association__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1713:1: rule__Association__Group__0__Impl : ( 'Association' ) ;
    public final void rule__Association__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1717:1: ( ( 'Association' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1718:1: ( 'Association' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1718:1: ( 'Association' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1719:1: 'Association'
            {
             before(grammarAccess.getAssociationAccess().getAssociationKeyword_0()); 
            match(input,19,FOLLOW_19_in_rule__Association__Group__0__Impl3447); 
             after(grammarAccess.getAssociationAccess().getAssociationKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__0__Impl"


    // $ANTLR start "rule__Association__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1732:1: rule__Association__Group__1 : rule__Association__Group__1__Impl rule__Association__Group__2 ;
    public final void rule__Association__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1736:1: ( rule__Association__Group__1__Impl rule__Association__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1737:2: rule__Association__Group__1__Impl rule__Association__Group__2
            {
            pushFollow(FOLLOW_rule__Association__Group__1__Impl_in_rule__Association__Group__13478);
            rule__Association__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__2_in_rule__Association__Group__13481);
            rule__Association__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__1"


    // $ANTLR start "rule__Association__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1744:1: rule__Association__Group__1__Impl : ( '{' ) ;
    public final void rule__Association__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1748:1: ( ( '{' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1749:1: ( '{' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1749:1: ( '{' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1750:1: '{'
            {
             before(grammarAccess.getAssociationAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__Association__Group__1__Impl3509); 
             after(grammarAccess.getAssociationAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__1__Impl"


    // $ANTLR start "rule__Association__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1763:1: rule__Association__Group__2 : rule__Association__Group__2__Impl rule__Association__Group__3 ;
    public final void rule__Association__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1767:1: ( rule__Association__Group__2__Impl rule__Association__Group__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1768:2: rule__Association__Group__2__Impl rule__Association__Group__3
            {
            pushFollow(FOLLOW_rule__Association__Group__2__Impl_in_rule__Association__Group__23540);
            rule__Association__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__3_in_rule__Association__Group__23543);
            rule__Association__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__2"


    // $ANTLR start "rule__Association__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1775:1: rule__Association__Group__2__Impl : ( ( rule__Association__Entity1Assignment_2 ) ) ;
    public final void rule__Association__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1779:1: ( ( ( rule__Association__Entity1Assignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1780:1: ( ( rule__Association__Entity1Assignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1780:1: ( ( rule__Association__Entity1Assignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1781:1: ( rule__Association__Entity1Assignment_2 )
            {
             before(grammarAccess.getAssociationAccess().getEntity1Assignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1782:1: ( rule__Association__Entity1Assignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1782:2: rule__Association__Entity1Assignment_2
            {
            pushFollow(FOLLOW_rule__Association__Entity1Assignment_2_in_rule__Association__Group__2__Impl3570);
            rule__Association__Entity1Assignment_2();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getEntity1Assignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__2__Impl"


    // $ANTLR start "rule__Association__Group__3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1792:1: rule__Association__Group__3 : rule__Association__Group__3__Impl rule__Association__Group__4 ;
    public final void rule__Association__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1796:1: ( rule__Association__Group__3__Impl rule__Association__Group__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1797:2: rule__Association__Group__3__Impl rule__Association__Group__4
            {
            pushFollow(FOLLOW_rule__Association__Group__3__Impl_in_rule__Association__Group__33600);
            rule__Association__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__4_in_rule__Association__Group__33603);
            rule__Association__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__3"


    // $ANTLR start "rule__Association__Group__3__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1804:1: rule__Association__Group__3__Impl : ( 'with' ) ;
    public final void rule__Association__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1808:1: ( ( 'with' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1809:1: ( 'with' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1809:1: ( 'with' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1810:1: 'with'
            {
             before(grammarAccess.getAssociationAccess().getWithKeyword_3()); 
            match(input,20,FOLLOW_20_in_rule__Association__Group__3__Impl3631); 
             after(grammarAccess.getAssociationAccess().getWithKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__3__Impl"


    // $ANTLR start "rule__Association__Group__4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1823:1: rule__Association__Group__4 : rule__Association__Group__4__Impl rule__Association__Group__5 ;
    public final void rule__Association__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1827:1: ( rule__Association__Group__4__Impl rule__Association__Group__5 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1828:2: rule__Association__Group__4__Impl rule__Association__Group__5
            {
            pushFollow(FOLLOW_rule__Association__Group__4__Impl_in_rule__Association__Group__43662);
            rule__Association__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__5_in_rule__Association__Group__43665);
            rule__Association__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__4"


    // $ANTLR start "rule__Association__Group__4__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1835:1: rule__Association__Group__4__Impl : ( ( rule__Association__Name1Assignment_4 ) ) ;
    public final void rule__Association__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1839:1: ( ( ( rule__Association__Name1Assignment_4 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1840:1: ( ( rule__Association__Name1Assignment_4 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1840:1: ( ( rule__Association__Name1Assignment_4 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1841:1: ( rule__Association__Name1Assignment_4 )
            {
             before(grammarAccess.getAssociationAccess().getName1Assignment_4()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1842:1: ( rule__Association__Name1Assignment_4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1842:2: rule__Association__Name1Assignment_4
            {
            pushFollow(FOLLOW_rule__Association__Name1Assignment_4_in_rule__Association__Group__4__Impl3692);
            rule__Association__Name1Assignment_4();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getName1Assignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__4__Impl"


    // $ANTLR start "rule__Association__Group__5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1852:1: rule__Association__Group__5 : rule__Association__Group__5__Impl rule__Association__Group__6 ;
    public final void rule__Association__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1856:1: ( rule__Association__Group__5__Impl rule__Association__Group__6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1857:2: rule__Association__Group__5__Impl rule__Association__Group__6
            {
            pushFollow(FOLLOW_rule__Association__Group__5__Impl_in_rule__Association__Group__53722);
            rule__Association__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__6_in_rule__Association__Group__53725);
            rule__Association__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__5"


    // $ANTLR start "rule__Association__Group__5__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1864:1: rule__Association__Group__5__Impl : ( '(' ) ;
    public final void rule__Association__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1868:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1869:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1869:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1870:1: '('
            {
             before(grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_5()); 
            match(input,21,FOLLOW_21_in_rule__Association__Group__5__Impl3753); 
             after(grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__5__Impl"


    // $ANTLR start "rule__Association__Group__6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1883:1: rule__Association__Group__6 : rule__Association__Group__6__Impl rule__Association__Group__7 ;
    public final void rule__Association__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1887:1: ( rule__Association__Group__6__Impl rule__Association__Group__7 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1888:2: rule__Association__Group__6__Impl rule__Association__Group__7
            {
            pushFollow(FOLLOW_rule__Association__Group__6__Impl_in_rule__Association__Group__63784);
            rule__Association__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__7_in_rule__Association__Group__63787);
            rule__Association__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__6"


    // $ANTLR start "rule__Association__Group__6__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1895:1: rule__Association__Group__6__Impl : ( ( rule__Association__Cardinality1Assignment_6 ) ) ;
    public final void rule__Association__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1899:1: ( ( ( rule__Association__Cardinality1Assignment_6 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1900:1: ( ( rule__Association__Cardinality1Assignment_6 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1900:1: ( ( rule__Association__Cardinality1Assignment_6 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1901:1: ( rule__Association__Cardinality1Assignment_6 )
            {
             before(grammarAccess.getAssociationAccess().getCardinality1Assignment_6()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1902:1: ( rule__Association__Cardinality1Assignment_6 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1902:2: rule__Association__Cardinality1Assignment_6
            {
            pushFollow(FOLLOW_rule__Association__Cardinality1Assignment_6_in_rule__Association__Group__6__Impl3814);
            rule__Association__Cardinality1Assignment_6();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getCardinality1Assignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__6__Impl"


    // $ANTLR start "rule__Association__Group__7"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1912:1: rule__Association__Group__7 : rule__Association__Group__7__Impl rule__Association__Group__8 ;
    public final void rule__Association__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1916:1: ( rule__Association__Group__7__Impl rule__Association__Group__8 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1917:2: rule__Association__Group__7__Impl rule__Association__Group__8
            {
            pushFollow(FOLLOW_rule__Association__Group__7__Impl_in_rule__Association__Group__73844);
            rule__Association__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__8_in_rule__Association__Group__73847);
            rule__Association__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__7"


    // $ANTLR start "rule__Association__Group__7__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1924:1: rule__Association__Group__7__Impl : ( ')' ) ;
    public final void rule__Association__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1928:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1929:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1929:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1930:1: ')'
            {
             before(grammarAccess.getAssociationAccess().getRightParenthesisKeyword_7()); 
            match(input,22,FOLLOW_22_in_rule__Association__Group__7__Impl3875); 
             after(grammarAccess.getAssociationAccess().getRightParenthesisKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__7__Impl"


    // $ANTLR start "rule__Association__Group__8"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1943:1: rule__Association__Group__8 : rule__Association__Group__8__Impl rule__Association__Group__9 ;
    public final void rule__Association__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1947:1: ( rule__Association__Group__8__Impl rule__Association__Group__9 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1948:2: rule__Association__Group__8__Impl rule__Association__Group__9
            {
            pushFollow(FOLLOW_rule__Association__Group__8__Impl_in_rule__Association__Group__83906);
            rule__Association__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__9_in_rule__Association__Group__83909);
            rule__Association__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__8"


    // $ANTLR start "rule__Association__Group__8__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1955:1: rule__Association__Group__8__Impl : ( ( rule__Association__Entity2Assignment_8 ) ) ;
    public final void rule__Association__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1959:1: ( ( ( rule__Association__Entity2Assignment_8 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1960:1: ( ( rule__Association__Entity2Assignment_8 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1960:1: ( ( rule__Association__Entity2Assignment_8 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1961:1: ( rule__Association__Entity2Assignment_8 )
            {
             before(grammarAccess.getAssociationAccess().getEntity2Assignment_8()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1962:1: ( rule__Association__Entity2Assignment_8 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1962:2: rule__Association__Entity2Assignment_8
            {
            pushFollow(FOLLOW_rule__Association__Entity2Assignment_8_in_rule__Association__Group__8__Impl3936);
            rule__Association__Entity2Assignment_8();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getEntity2Assignment_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__8__Impl"


    // $ANTLR start "rule__Association__Group__9"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1972:1: rule__Association__Group__9 : rule__Association__Group__9__Impl rule__Association__Group__10 ;
    public final void rule__Association__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1976:1: ( rule__Association__Group__9__Impl rule__Association__Group__10 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1977:2: rule__Association__Group__9__Impl rule__Association__Group__10
            {
            pushFollow(FOLLOW_rule__Association__Group__9__Impl_in_rule__Association__Group__93966);
            rule__Association__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__10_in_rule__Association__Group__93969);
            rule__Association__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__9"


    // $ANTLR start "rule__Association__Group__9__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1984:1: rule__Association__Group__9__Impl : ( 'with' ) ;
    public final void rule__Association__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1988:1: ( ( 'with' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1989:1: ( 'with' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1989:1: ( 'with' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:1990:1: 'with'
            {
             before(grammarAccess.getAssociationAccess().getWithKeyword_9()); 
            match(input,20,FOLLOW_20_in_rule__Association__Group__9__Impl3997); 
             after(grammarAccess.getAssociationAccess().getWithKeyword_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__9__Impl"


    // $ANTLR start "rule__Association__Group__10"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2003:1: rule__Association__Group__10 : rule__Association__Group__10__Impl rule__Association__Group__11 ;
    public final void rule__Association__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2007:1: ( rule__Association__Group__10__Impl rule__Association__Group__11 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2008:2: rule__Association__Group__10__Impl rule__Association__Group__11
            {
            pushFollow(FOLLOW_rule__Association__Group__10__Impl_in_rule__Association__Group__104028);
            rule__Association__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__11_in_rule__Association__Group__104031);
            rule__Association__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__10"


    // $ANTLR start "rule__Association__Group__10__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2015:1: rule__Association__Group__10__Impl : ( ( rule__Association__Name2Assignment_10 ) ) ;
    public final void rule__Association__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2019:1: ( ( ( rule__Association__Name2Assignment_10 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2020:1: ( ( rule__Association__Name2Assignment_10 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2020:1: ( ( rule__Association__Name2Assignment_10 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2021:1: ( rule__Association__Name2Assignment_10 )
            {
             before(grammarAccess.getAssociationAccess().getName2Assignment_10()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2022:1: ( rule__Association__Name2Assignment_10 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2022:2: rule__Association__Name2Assignment_10
            {
            pushFollow(FOLLOW_rule__Association__Name2Assignment_10_in_rule__Association__Group__10__Impl4058);
            rule__Association__Name2Assignment_10();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getName2Assignment_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__10__Impl"


    // $ANTLR start "rule__Association__Group__11"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2032:1: rule__Association__Group__11 : rule__Association__Group__11__Impl rule__Association__Group__12 ;
    public final void rule__Association__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2036:1: ( rule__Association__Group__11__Impl rule__Association__Group__12 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2037:2: rule__Association__Group__11__Impl rule__Association__Group__12
            {
            pushFollow(FOLLOW_rule__Association__Group__11__Impl_in_rule__Association__Group__114088);
            rule__Association__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__12_in_rule__Association__Group__114091);
            rule__Association__Group__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__11"


    // $ANTLR start "rule__Association__Group__11__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2044:1: rule__Association__Group__11__Impl : ( '(' ) ;
    public final void rule__Association__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2048:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2049:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2049:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2050:1: '('
            {
             before(grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_11()); 
            match(input,21,FOLLOW_21_in_rule__Association__Group__11__Impl4119); 
             after(grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__11__Impl"


    // $ANTLR start "rule__Association__Group__12"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2063:1: rule__Association__Group__12 : rule__Association__Group__12__Impl rule__Association__Group__13 ;
    public final void rule__Association__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2067:1: ( rule__Association__Group__12__Impl rule__Association__Group__13 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2068:2: rule__Association__Group__12__Impl rule__Association__Group__13
            {
            pushFollow(FOLLOW_rule__Association__Group__12__Impl_in_rule__Association__Group__124150);
            rule__Association__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__13_in_rule__Association__Group__124153);
            rule__Association__Group__13();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__12"


    // $ANTLR start "rule__Association__Group__12__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2075:1: rule__Association__Group__12__Impl : ( ( rule__Association__Cardinality2Assignment_12 ) ) ;
    public final void rule__Association__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2079:1: ( ( ( rule__Association__Cardinality2Assignment_12 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2080:1: ( ( rule__Association__Cardinality2Assignment_12 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2080:1: ( ( rule__Association__Cardinality2Assignment_12 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2081:1: ( rule__Association__Cardinality2Assignment_12 )
            {
             before(grammarAccess.getAssociationAccess().getCardinality2Assignment_12()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2082:1: ( rule__Association__Cardinality2Assignment_12 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2082:2: rule__Association__Cardinality2Assignment_12
            {
            pushFollow(FOLLOW_rule__Association__Cardinality2Assignment_12_in_rule__Association__Group__12__Impl4180);
            rule__Association__Cardinality2Assignment_12();

            state._fsp--;


            }

             after(grammarAccess.getAssociationAccess().getCardinality2Assignment_12()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__12__Impl"


    // $ANTLR start "rule__Association__Group__13"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2092:1: rule__Association__Group__13 : rule__Association__Group__13__Impl rule__Association__Group__14 ;
    public final void rule__Association__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2096:1: ( rule__Association__Group__13__Impl rule__Association__Group__14 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2097:2: rule__Association__Group__13__Impl rule__Association__Group__14
            {
            pushFollow(FOLLOW_rule__Association__Group__13__Impl_in_rule__Association__Group__134210);
            rule__Association__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Association__Group__14_in_rule__Association__Group__134213);
            rule__Association__Group__14();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__13"


    // $ANTLR start "rule__Association__Group__13__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2104:1: rule__Association__Group__13__Impl : ( ')' ) ;
    public final void rule__Association__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2108:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2109:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2109:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2110:1: ')'
            {
             before(grammarAccess.getAssociationAccess().getRightParenthesisKeyword_13()); 
            match(input,22,FOLLOW_22_in_rule__Association__Group__13__Impl4241); 
             after(grammarAccess.getAssociationAccess().getRightParenthesisKeyword_13()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__13__Impl"


    // $ANTLR start "rule__Association__Group__14"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2123:1: rule__Association__Group__14 : rule__Association__Group__14__Impl ;
    public final void rule__Association__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2127:1: ( rule__Association__Group__14__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2128:2: rule__Association__Group__14__Impl
            {
            pushFollow(FOLLOW_rule__Association__Group__14__Impl_in_rule__Association__Group__144272);
            rule__Association__Group__14__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__14"


    // $ANTLR start "rule__Association__Group__14__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2134:1: rule__Association__Group__14__Impl : ( '}' ) ;
    public final void rule__Association__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2138:1: ( ( '}' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2139:1: ( '}' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2139:1: ( '}' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2140:1: '}'
            {
             before(grammarAccess.getAssociationAccess().getRightCurlyBracketKeyword_14()); 
            match(input,13,FOLLOW_13_in_rule__Association__Group__14__Impl4300); 
             after(grammarAccess.getAssociationAccess().getRightCurlyBracketKeyword_14()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Group__14__Impl"


    // $ANTLR start "rule__Constraint__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2183:1: rule__Constraint__Group__0 : rule__Constraint__Group__0__Impl rule__Constraint__Group__1 ;
    public final void rule__Constraint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2187:1: ( rule__Constraint__Group__0__Impl rule__Constraint__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2188:2: rule__Constraint__Group__0__Impl rule__Constraint__Group__1
            {
            pushFollow(FOLLOW_rule__Constraint__Group__0__Impl_in_rule__Constraint__Group__04361);
            rule__Constraint__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Constraint__Group__1_in_rule__Constraint__Group__04364);
            rule__Constraint__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__Group__0"


    // $ANTLR start "rule__Constraint__Group__0__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2195:1: rule__Constraint__Group__0__Impl : ( 'Constraint' ) ;
    public final void rule__Constraint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2199:1: ( ( 'Constraint' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2200:1: ( 'Constraint' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2200:1: ( 'Constraint' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2201:1: 'Constraint'
            {
             before(grammarAccess.getConstraintAccess().getConstraintKeyword_0()); 
            match(input,23,FOLLOW_23_in_rule__Constraint__Group__0__Impl4392); 
             after(grammarAccess.getConstraintAccess().getConstraintKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__Group__0__Impl"


    // $ANTLR start "rule__Constraint__Group__1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2214:1: rule__Constraint__Group__1 : rule__Constraint__Group__1__Impl rule__Constraint__Group__2 ;
    public final void rule__Constraint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2218:1: ( rule__Constraint__Group__1__Impl rule__Constraint__Group__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2219:2: rule__Constraint__Group__1__Impl rule__Constraint__Group__2
            {
            pushFollow(FOLLOW_rule__Constraint__Group__1__Impl_in_rule__Constraint__Group__14423);
            rule__Constraint__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Constraint__Group__2_in_rule__Constraint__Group__14426);
            rule__Constraint__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__Group__1"


    // $ANTLR start "rule__Constraint__Group__1__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2226:1: rule__Constraint__Group__1__Impl : ( ':' ) ;
    public final void rule__Constraint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2230:1: ( ( ':' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2231:1: ( ':' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2231:1: ( ':' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2232:1: ':'
            {
             before(grammarAccess.getConstraintAccess().getColonKeyword_1()); 
            match(input,15,FOLLOW_15_in_rule__Constraint__Group__1__Impl4454); 
             after(grammarAccess.getConstraintAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__Group__1__Impl"


    // $ANTLR start "rule__Constraint__Group__2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2245:1: rule__Constraint__Group__2 : rule__Constraint__Group__2__Impl ;
    public final void rule__Constraint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2249:1: ( rule__Constraint__Group__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2250:2: rule__Constraint__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Constraint__Group__2__Impl_in_rule__Constraint__Group__24485);
            rule__Constraint__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__Group__2"


    // $ANTLR start "rule__Constraint__Group__2__Impl"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2256:1: rule__Constraint__Group__2__Impl : ( ( rule__Constraint__ConstraintAssignment_2 ) ) ;
    public final void rule__Constraint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2260:1: ( ( ( rule__Constraint__ConstraintAssignment_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2261:1: ( ( rule__Constraint__ConstraintAssignment_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2261:1: ( ( rule__Constraint__ConstraintAssignment_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2262:1: ( rule__Constraint__ConstraintAssignment_2 )
            {
             before(grammarAccess.getConstraintAccess().getConstraintAssignment_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2263:1: ( rule__Constraint__ConstraintAssignment_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2263:2: rule__Constraint__ConstraintAssignment_2
            {
            pushFollow(FOLLOW_rule__Constraint__ConstraintAssignment_2_in_rule__Constraint__Group__2__Impl4512);
            rule__Constraint__ConstraintAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getConstraintAccess().getConstraintAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__Group__2__Impl"


    // $ANTLR start "rule__Cardinality__Group_1__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2279:1: rule__Cardinality__Group_1__0 : rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 ;
    public final void rule__Cardinality__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2283:1: ( rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2284:2: rule__Cardinality__Group_1__0__Impl rule__Cardinality__Group_1__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__04548);
            rule__Cardinality__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__04551);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2291:1: rule__Cardinality__Group_1__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2295:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2296:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2296:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2297:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl4578); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2308:1: rule__Cardinality__Group_1__1 : rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 ;
    public final void rule__Cardinality__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2312:1: ( rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2313:2: rule__Cardinality__Group_1__1__Impl rule__Cardinality__Group_1__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__14607);
            rule__Cardinality__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__14610);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2320:1: rule__Cardinality__Group_1__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2324:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2325:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2325:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2326:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_1_1()); 
            match(input,24,FOLLOW_24_in_rule__Cardinality__Group_1__1__Impl4638); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2339:1: rule__Cardinality__Group_1__2 : rule__Cardinality__Group_1__2__Impl ;
    public final void rule__Cardinality__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2343:1: ( rule__Cardinality__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2344:2: rule__Cardinality__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__24669);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2350:1: rule__Cardinality__Group_1__2__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2354:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2355:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2355:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2356:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_1_2()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl4696); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2373:1: rule__Cardinality__Group_2__0 : rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 ;
    public final void rule__Cardinality__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2377:1: ( rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2378:2: rule__Cardinality__Group_2__0__Impl rule__Cardinality__Group_2__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__04731);
            rule__Cardinality__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__04734);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2385:1: rule__Cardinality__Group_2__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2389:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2390:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2390:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2391:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl4761); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2402:1: rule__Cardinality__Group_2__1 : rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 ;
    public final void rule__Cardinality__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2406:1: ( rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2407:2: rule__Cardinality__Group_2__1__Impl rule__Cardinality__Group_2__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__14790);
            rule__Cardinality__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__14793);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2414:1: rule__Cardinality__Group_2__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2418:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2419:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2419:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2420:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2_1()); 
            match(input,24,FOLLOW_24_in_rule__Cardinality__Group_2__1__Impl4821); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2433:1: rule__Cardinality__Group_2__2 : rule__Cardinality__Group_2__2__Impl ;
    public final void rule__Cardinality__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2437:1: ( rule__Cardinality__Group_2__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2438:2: rule__Cardinality__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__24852);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2444:1: rule__Cardinality__Group_2__2__Impl : ( '*' ) ;
    public final void rule__Cardinality__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2448:1: ( ( '*' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2449:1: ( '*' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2449:1: ( '*' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2450:1: '*'
            {
             before(grammarAccess.getCardinalityAccess().getAsteriskKeyword_2_2()); 
            match(input,25,FOLLOW_25_in_rule__Cardinality__Group_2__2__Impl4880); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2469:1: rule__Cardinality__Group_3__0 : rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 ;
    public final void rule__Cardinality__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2473:1: ( rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2474:2: rule__Cardinality__Group_3__0__Impl rule__Cardinality__Group_3__1
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__04917);
            rule__Cardinality__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__04920);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2481:1: rule__Cardinality__Group_3__0__Impl : ( RULE_INT ) ;
    public final void rule__Cardinality__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2485:1: ( ( RULE_INT ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2486:1: ( RULE_INT )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2486:1: ( RULE_INT )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2487:1: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl4947); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2498:1: rule__Cardinality__Group_3__1 : rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 ;
    public final void rule__Cardinality__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2502:1: ( rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2503:2: rule__Cardinality__Group_3__1__Impl rule__Cardinality__Group_3__2
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__14976);
            rule__Cardinality__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__14979);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2510:1: rule__Cardinality__Group_3__1__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2514:1: ( ( '..' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2515:1: ( '..' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2515:1: ( '..' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2516:1: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_3_1()); 
            match(input,24,FOLLOW_24_in_rule__Cardinality__Group_3__1__Impl5007); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2529:1: rule__Cardinality__Group_3__2 : rule__Cardinality__Group_3__2__Impl ;
    public final void rule__Cardinality__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2533:1: ( rule__Cardinality__Group_3__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2534:2: rule__Cardinality__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__25038);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2540:1: rule__Cardinality__Group_3__2__Impl : ( '+' ) ;
    public final void rule__Cardinality__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2544:1: ( ( '+' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2545:1: ( '+' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2545:1: ( '+' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2546:1: '+'
            {
             before(grammarAccess.getCardinalityAccess().getPlusSignKeyword_3_2()); 
            match(input,26,FOLLOW_26_in_rule__Cardinality__Group_3__2__Impl5066); 
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


    // $ANTLR start "rule__Or__Group__0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2565:1: rule__Or__Group__0 : rule__Or__Group__0__Impl rule__Or__Group__1 ;
    public final void rule__Or__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2569:1: ( rule__Or__Group__0__Impl rule__Or__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2570:2: rule__Or__Group__0__Impl rule__Or__Group__1
            {
            pushFollow(FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__05103);
            rule__Or__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group__1_in_rule__Or__Group__05106);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2577:1: rule__Or__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Or__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2581:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2582:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2582:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2583:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getAndParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl5133);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2594:1: rule__Or__Group__1 : rule__Or__Group__1__Impl ;
    public final void rule__Or__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2598:1: ( rule__Or__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2599:2: rule__Or__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__15162);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2605:1: rule__Or__Group__1__Impl : ( ( rule__Or__Group_1__0 )* ) ;
    public final void rule__Or__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2609:1: ( ( ( rule__Or__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2610:1: ( ( rule__Or__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2610:1: ( ( rule__Or__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2611:1: ( rule__Or__Group_1__0 )*
            {
             before(grammarAccess.getOrAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2612:1: ( rule__Or__Group_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==27) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2612:2: rule__Or__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl5189);
            	    rule__Or__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2626:1: rule__Or__Group_1__0 : rule__Or__Group_1__0__Impl rule__Or__Group_1__1 ;
    public final void rule__Or__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2630:1: ( rule__Or__Group_1__0__Impl rule__Or__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2631:2: rule__Or__Group_1__0__Impl rule__Or__Group_1__1
            {
            pushFollow(FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__05224);
            rule__Or__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__05227);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2638:1: rule__Or__Group_1__0__Impl : ( () ) ;
    public final void rule__Or__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2642:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2643:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2643:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2644:1: ()
            {
             before(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2645:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2647:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2657:1: rule__Or__Group_1__1 : rule__Or__Group_1__1__Impl rule__Or__Group_1__2 ;
    public final void rule__Or__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2661:1: ( rule__Or__Group_1__1__Impl rule__Or__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2662:2: rule__Or__Group_1__1__Impl rule__Or__Group_1__2
            {
            pushFollow(FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__15285);
            rule__Or__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__15288);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2669:1: rule__Or__Group_1__1__Impl : ( 'OR' ) ;
    public final void rule__Or__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2673:1: ( ( 'OR' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2674:1: ( 'OR' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2674:1: ( 'OR' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2675:1: 'OR'
            {
             before(grammarAccess.getOrAccess().getORKeyword_1_1()); 
            match(input,27,FOLLOW_27_in_rule__Or__Group_1__1__Impl5316); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2688:1: rule__Or__Group_1__2 : rule__Or__Group_1__2__Impl ;
    public final void rule__Or__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2692:1: ( rule__Or__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2693:2: rule__Or__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__25347);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2699:1: rule__Or__Group_1__2__Impl : ( ( rule__Or__RightAssignment_1_2 ) ) ;
    public final void rule__Or__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2703:1: ( ( ( rule__Or__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2704:1: ( ( rule__Or__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2704:1: ( ( rule__Or__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2705:1: ( rule__Or__RightAssignment_1_2 )
            {
             before(grammarAccess.getOrAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2706:1: ( rule__Or__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2706:2: rule__Or__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl5374);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2722:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2726:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2727:2: rule__And__Group__0__Impl rule__And__Group__1
            {
            pushFollow(FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__05410);
            rule__And__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group__1_in_rule__And__Group__05413);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2734:1: rule__And__Group__0__Impl : ( rulePrimary ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2738:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2739:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2739:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2740:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getPrimaryParserRuleCall_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__Group__0__Impl5440);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2751:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2755:1: ( rule__And__Group__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2756:2: rule__And__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__15469);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2762:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2766:1: ( ( ( rule__And__Group_1__0 )* ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2767:1: ( ( rule__And__Group_1__0 )* )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2767:1: ( ( rule__And__Group_1__0 )* )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2768:1: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2769:1: ( rule__And__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==28) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2769:2: rule__And__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl5496);
            	    rule__And__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2783:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2787:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2788:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
            {
            pushFollow(FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__05531);
            rule__And__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__05534);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2795:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2799:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2800:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2800:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2801:1: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2802:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2804:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2814:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2818:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2819:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__15592);
            rule__And__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__15595);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2826:1: rule__And__Group_1__1__Impl : ( 'AND' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2830:1: ( ( 'AND' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2831:1: ( 'AND' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2831:1: ( 'AND' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2832:1: 'AND'
            {
             before(grammarAccess.getAndAccess().getANDKeyword_1_1()); 
            match(input,28,FOLLOW_28_in_rule__And__Group_1__1__Impl5623); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2845:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2849:1: ( rule__And__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2850:2: rule__And__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__25654);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2856:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2860:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2861:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2861:1: ( ( rule__And__RightAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2862:1: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2863:1: ( rule__And__RightAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2863:2: rule__And__RightAssignment_1_2
            {
            pushFollow(FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl5681);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2879:1: rule__Primary__Group_0__0 : rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 ;
    public final void rule__Primary__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2883:1: ( rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2884:2: rule__Primary__Group_0__0__Impl rule__Primary__Group_0__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__05717);
            rule__Primary__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__05720);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2891:1: rule__Primary__Group_0__0__Impl : ( '(' ) ;
    public final void rule__Primary__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2895:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2896:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2896:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2897:1: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_0_0()); 
            match(input,21,FOLLOW_21_in_rule__Primary__Group_0__0__Impl5748); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2910:1: rule__Primary__Group_0__1 : rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 ;
    public final void rule__Primary__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2914:1: ( rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2915:2: rule__Primary__Group_0__1__Impl rule__Primary__Group_0__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__15779);
            rule__Primary__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__15782);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2922:1: rule__Primary__Group_0__1__Impl : ( ruleExpression ) ;
    public final void rule__Primary__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2926:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2927:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2927:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2928:1: ruleExpression
            {
             before(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_0_1()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl5809);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2939:1: rule__Primary__Group_0__2 : rule__Primary__Group_0__2__Impl ;
    public final void rule__Primary__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2943:1: ( rule__Primary__Group_0__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2944:2: rule__Primary__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__25838);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2950:1: rule__Primary__Group_0__2__Impl : ( ')' ) ;
    public final void rule__Primary__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2954:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2955:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2955:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2956:1: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_0_2()); 
            match(input,22,FOLLOW_22_in_rule__Primary__Group_0__2__Impl5866); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2975:1: rule__Primary__Group_1__0 : rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 ;
    public final void rule__Primary__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2979:1: ( rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2980:2: rule__Primary__Group_1__0__Impl rule__Primary__Group_1__1
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__05903);
            rule__Primary__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__05906);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2987:1: rule__Primary__Group_1__0__Impl : ( () ) ;
    public final void rule__Primary__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2991:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2992:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2992:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2993:1: ()
            {
             before(grammarAccess.getPrimaryAccess().getNotAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2994:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:2996:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3006:1: rule__Primary__Group_1__1 : rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 ;
    public final void rule__Primary__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3010:1: ( rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3011:2: rule__Primary__Group_1__1__Impl rule__Primary__Group_1__2
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__15964);
            rule__Primary__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__15967);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3018:1: rule__Primary__Group_1__1__Impl : ( 'NOT' ) ;
    public final void rule__Primary__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3022:1: ( ( 'NOT' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3023:1: ( 'NOT' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3023:1: ( 'NOT' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3024:1: 'NOT'
            {
             before(grammarAccess.getPrimaryAccess().getNOTKeyword_1_1()); 
            match(input,29,FOLLOW_29_in_rule__Primary__Group_1__1__Impl5995); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3037:1: rule__Primary__Group_1__2 : rule__Primary__Group_1__2__Impl ;
    public final void rule__Primary__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3041:1: ( rule__Primary__Group_1__2__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3042:2: rule__Primary__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__26026);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3048:1: rule__Primary__Group_1__2__Impl : ( ( rule__Primary__ExpressionAssignment_1_2 ) ) ;
    public final void rule__Primary__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3052:1: ( ( ( rule__Primary__ExpressionAssignment_1_2 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3053:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3053:1: ( ( rule__Primary__ExpressionAssignment_1_2 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3054:1: ( rule__Primary__ExpressionAssignment_1_2 )
            {
             before(grammarAccess.getPrimaryAccess().getExpressionAssignment_1_2()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3055:1: ( rule__Primary__ExpressionAssignment_1_2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3055:2: rule__Primary__ExpressionAssignment_1_2
            {
            pushFollow(FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl6053);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3071:1: rule__Atomic__Group_0__0 : rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 ;
    public final void rule__Atomic__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3075:1: ( rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3076:2: rule__Atomic__Group_0__0__Impl rule__Atomic__Group_0__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__06089);
            rule__Atomic__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__06092);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3083:1: rule__Atomic__Group_0__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3087:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3088:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3088:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3089:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeDefinitionAction_0_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3090:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3092:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3102:1: rule__Atomic__Group_0__1 : rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 ;
    public final void rule__Atomic__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3106:1: ( rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3107:2: rule__Atomic__Group_0__1__Impl rule__Atomic__Group_0__2
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__16150);
            rule__Atomic__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__16153);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3114:1: rule__Atomic__Group_0__1__Impl : ( 'DEF' ) ;
    public final void rule__Atomic__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3118:1: ( ( 'DEF' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3119:1: ( 'DEF' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3119:1: ( 'DEF' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3120:1: 'DEF'
            {
             before(grammarAccess.getAtomicAccess().getDEFKeyword_0_1()); 
            match(input,30,FOLLOW_30_in_rule__Atomic__Group_0__1__Impl6181); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3133:1: rule__Atomic__Group_0__2 : rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 ;
    public final void rule__Atomic__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3137:1: ( rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3138:2: rule__Atomic__Group_0__2__Impl rule__Atomic__Group_0__3
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__26212);
            rule__Atomic__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__26215);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3145:1: rule__Atomic__Group_0__2__Impl : ( '(' ) ;
    public final void rule__Atomic__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3149:1: ( ( '(' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3150:1: ( '(' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3150:1: ( '(' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3151:1: '('
            {
             before(grammarAccess.getAtomicAccess().getLeftParenthesisKeyword_0_2()); 
            match(input,21,FOLLOW_21_in_rule__Atomic__Group_0__2__Impl6243); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3164:1: rule__Atomic__Group_0__3 : rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 ;
    public final void rule__Atomic__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3168:1: ( rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3169:2: rule__Atomic__Group_0__3__Impl rule__Atomic__Group_0__4
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__36274);
            rule__Atomic__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__36277);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3176:1: rule__Atomic__Group_0__3__Impl : ( ( rule__Atomic__NameAssignment_0_3 ) ) ;
    public final void rule__Atomic__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3180:1: ( ( ( rule__Atomic__NameAssignment_0_3 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3181:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3181:1: ( ( rule__Atomic__NameAssignment_0_3 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3182:1: ( rule__Atomic__NameAssignment_0_3 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_0_3()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3183:1: ( rule__Atomic__NameAssignment_0_3 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3183:2: rule__Atomic__NameAssignment_0_3
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl6304);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3193:1: rule__Atomic__Group_0__4 : rule__Atomic__Group_0__4__Impl ;
    public final void rule__Atomic__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3197:1: ( rule__Atomic__Group_0__4__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3198:2: rule__Atomic__Group_0__4__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__46334);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3204:1: rule__Atomic__Group_0__4__Impl : ( ')' ) ;
    public final void rule__Atomic__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3208:1: ( ( ')' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3209:1: ( ')' )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3209:1: ( ')' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3210:1: ')'
            {
             before(grammarAccess.getAtomicAccess().getRightParenthesisKeyword_0_4()); 
            match(input,22,FOLLOW_22_in_rule__Atomic__Group_0__4__Impl6362); 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3233:1: rule__Atomic__Group_1__0 : rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 ;
    public final void rule__Atomic__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3237:1: ( rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3238:2: rule__Atomic__Group_1__0__Impl rule__Atomic__Group_1__1
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__06403);
            rule__Atomic__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__06406);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3245:1: rule__Atomic__Group_1__0__Impl : ( () ) ;
    public final void rule__Atomic__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3249:1: ( ( () ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3250:1: ( () )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3250:1: ( () )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3251:1: ()
            {
             before(grammarAccess.getAtomicAccess().getAttributeValueAction_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3252:1: ()
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3254:1: 
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3264:1: rule__Atomic__Group_1__1 : rule__Atomic__Group_1__1__Impl ;
    public final void rule__Atomic__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3268:1: ( rule__Atomic__Group_1__1__Impl )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3269:2: rule__Atomic__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__16464);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3275:1: rule__Atomic__Group_1__1__Impl : ( ( rule__Atomic__NameAssignment_1_1 ) ) ;
    public final void rule__Atomic__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3279:1: ( ( ( rule__Atomic__NameAssignment_1_1 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3280:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3280:1: ( ( rule__Atomic__NameAssignment_1_1 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3281:1: ( rule__Atomic__NameAssignment_1_1 )
            {
             before(grammarAccess.getAtomicAccess().getNameAssignment_1_1()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3282:1: ( rule__Atomic__NameAssignment_1_1 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3282:2: rule__Atomic__NameAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl6491);
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


    // $ANTLR start "rule__DataModel__EntitiesAssignment_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3297:1: rule__DataModel__EntitiesAssignment_0 : ( ruleEntity ) ;
    public final void rule__DataModel__EntitiesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3301:1: ( ( ruleEntity ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3302:1: ( ruleEntity )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3302:1: ( ruleEntity )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3303:1: ruleEntity
            {
             before(grammarAccess.getDataModelAccess().getEntitiesEntityParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleEntity_in_rule__DataModel__EntitiesAssignment_06530);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getDataModelAccess().getEntitiesEntityParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__EntitiesAssignment_0"


    // $ANTLR start "rule__DataModel__AssociatiosAssignment_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3312:1: rule__DataModel__AssociatiosAssignment_1 : ( ruleAssociation ) ;
    public final void rule__DataModel__AssociatiosAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3316:1: ( ( ruleAssociation ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3317:1: ( ruleAssociation )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3317:1: ( ruleAssociation )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3318:1: ruleAssociation
            {
             before(grammarAccess.getDataModelAccess().getAssociatiosAssociationParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleAssociation_in_rule__DataModel__AssociatiosAssignment_16561);
            ruleAssociation();

            state._fsp--;

             after(grammarAccess.getDataModelAccess().getAssociatiosAssociationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__AssociatiosAssignment_1"


    // $ANTLR start "rule__DataModel__ConstraintAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3327:1: rule__DataModel__ConstraintAssignment_2 : ( ruleConstraint ) ;
    public final void rule__DataModel__ConstraintAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3331:1: ( ( ruleConstraint ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3332:1: ( ruleConstraint )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3332:1: ( ruleConstraint )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3333:1: ruleConstraint
            {
             before(grammarAccess.getDataModelAccess().getConstraintConstraintParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleConstraint_in_rule__DataModel__ConstraintAssignment_26592);
            ruleConstraint();

            state._fsp--;

             after(grammarAccess.getDataModelAccess().getConstraintConstraintParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DataModel__ConstraintAssignment_2"


    // $ANTLR start "rule__Entity__NameAssignment_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3342:1: rule__Entity__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Entity__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3346:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3347:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3347:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3348:1: RULE_ID
            {
             before(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Entity__NameAssignment_16623); 
             after(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__NameAssignment_1"


    // $ANTLR start "rule__Entity__ExistsAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3357:1: rule__Entity__ExistsAssignment_2 : ( ( 'exists' ) ) ;
    public final void rule__Entity__ExistsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3361:1: ( ( ( 'exists' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3362:1: ( ( 'exists' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3362:1: ( ( 'exists' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3363:1: ( 'exists' )
            {
             before(grammarAccess.getEntityAccess().getExistsExistsKeyword_2_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3364:1: ( 'exists' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3365:1: 'exists'
            {
             before(grammarAccess.getEntityAccess().getExistsExistsKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__Entity__ExistsAssignment_26659); 
             after(grammarAccess.getEntityAccess().getExistsExistsKeyword_2_0()); 

            }

             after(grammarAccess.getEntityAccess().getExistsExistsKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__ExistsAssignment_2"


    // $ANTLR start "rule__Entity__DependsOnAssignment_3_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3380:1: rule__Entity__DependsOnAssignment_3_1 : ( ( RULE_ID ) ) ;
    public final void rule__Entity__DependsOnAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3384:1: ( ( ( RULE_ID ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3385:1: ( ( RULE_ID ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3385:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3386:1: ( RULE_ID )
            {
             before(grammarAccess.getEntityAccess().getDependsOnEntityCrossReference_3_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3387:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3388:1: RULE_ID
            {
             before(grammarAccess.getEntityAccess().getDependsOnEntityIDTerminalRuleCall_3_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Entity__DependsOnAssignment_3_16702); 
             after(grammarAccess.getEntityAccess().getDependsOnEntityIDTerminalRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getEntityAccess().getDependsOnEntityCrossReference_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__DependsOnAssignment_3_1"


    // $ANTLR start "rule__Entity__AttributesAssignment_5"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3399:1: rule__Entity__AttributesAssignment_5 : ( ( rule__Entity__AttributesAlternatives_5_0 ) ) ;
    public final void rule__Entity__AttributesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3403:1: ( ( ( rule__Entity__AttributesAlternatives_5_0 ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3404:1: ( ( rule__Entity__AttributesAlternatives_5_0 ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3404:1: ( ( rule__Entity__AttributesAlternatives_5_0 ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3405:1: ( rule__Entity__AttributesAlternatives_5_0 )
            {
             before(grammarAccess.getEntityAccess().getAttributesAlternatives_5_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3406:1: ( rule__Entity__AttributesAlternatives_5_0 )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3406:2: rule__Entity__AttributesAlternatives_5_0
            {
            pushFollow(FOLLOW_rule__Entity__AttributesAlternatives_5_0_in_rule__Entity__AttributesAssignment_56737);
            rule__Entity__AttributesAlternatives_5_0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getAttributesAlternatives_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__AttributesAssignment_5"


    // $ANTLR start "rule__Attribute__NameAssignment_0"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3415:1: rule__Attribute__NameAssignment_0 : ( ruleQualifiedName ) ;
    public final void rule__Attribute__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3419:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3420:1: ( ruleQualifiedName )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3420:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3421:1: ruleQualifiedName
            {
             before(grammarAccess.getAttributeAccess().getNameQualifiedNameParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__Attribute__NameAssignment_06770);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAttributeAccess().getNameQualifiedNameParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__NameAssignment_0"


    // $ANTLR start "rule__Attribute__TypeAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3430:1: rule__Attribute__TypeAssignment_2 : ( RULE_ID ) ;
    public final void rule__Attribute__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3434:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3435:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3435:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3436:1: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getTypeIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Attribute__TypeAssignment_26801); 
             after(grammarAccess.getAttributeAccess().getTypeIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__TypeAssignment_2"


    // $ANTLR start "rule__Attribute__MandatoryAssignment_3"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3445:1: rule__Attribute__MandatoryAssignment_3 : ( ( 'mandatory' ) ) ;
    public final void rule__Attribute__MandatoryAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3449:1: ( ( ( 'mandatory' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3450:1: ( ( 'mandatory' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3450:1: ( ( 'mandatory' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3451:1: ( 'mandatory' )
            {
             before(grammarAccess.getAttributeAccess().getMandatoryMandatoryKeyword_3_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3452:1: ( 'mandatory' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3453:1: 'mandatory'
            {
             before(grammarAccess.getAttributeAccess().getMandatoryMandatoryKeyword_3_0()); 
            match(input,32,FOLLOW_32_in_rule__Attribute__MandatoryAssignment_36837); 
             after(grammarAccess.getAttributeAccess().getMandatoryMandatoryKeyword_3_0()); 

            }

             after(grammarAccess.getAttributeAccess().getMandatoryMandatoryKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__MandatoryAssignment_3"


    // $ANTLR start "rule__Attribute__DependsOnAssignment_4_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3468:1: rule__Attribute__DependsOnAssignment_4_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Attribute__DependsOnAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3472:1: ( ( ( ruleQualifiedName ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3473:1: ( ( ruleQualifiedName ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3473:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3474:1: ( ruleQualifiedName )
            {
             before(grammarAccess.getAttributeAccess().getDependsOnAttributeCrossReference_4_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3475:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3476:1: ruleQualifiedName
            {
             before(grammarAccess.getAttributeAccess().getDependsOnAttributeQualifiedNameParserRuleCall_4_1_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__Attribute__DependsOnAssignment_4_16880);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAttributeAccess().getDependsOnAttributeQualifiedNameParserRuleCall_4_1_0_1()); 

            }

             after(grammarAccess.getAttributeAccess().getDependsOnAttributeCrossReference_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__DependsOnAssignment_4_1"


    // $ANTLR start "rule__Attribute__DependsOnAssignment_4_2_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3487:1: rule__Attribute__DependsOnAssignment_4_2_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Attribute__DependsOnAssignment_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3491:1: ( ( ( ruleQualifiedName ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3492:1: ( ( ruleQualifiedName ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3492:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3493:1: ( ruleQualifiedName )
            {
             before(grammarAccess.getAttributeAccess().getDependsOnAttributeCrossReference_4_2_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3494:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3495:1: ruleQualifiedName
            {
             before(grammarAccess.getAttributeAccess().getDependsOnAttributeQualifiedNameParserRuleCall_4_2_1_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__Attribute__DependsOnAssignment_4_2_16919);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAttributeAccess().getDependsOnAttributeQualifiedNameParserRuleCall_4_2_1_0_1()); 

            }

             after(grammarAccess.getAttributeAccess().getDependsOnAttributeCrossReference_4_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__DependsOnAssignment_4_2_1"


    // $ANTLR start "rule__AttributeGroup__MandatoryAssignment_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3506:1: rule__AttributeGroup__MandatoryAssignment_1 : ( ( 'mandatory' ) ) ;
    public final void rule__AttributeGroup__MandatoryAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3510:1: ( ( ( 'mandatory' ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3511:1: ( ( 'mandatory' ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3511:1: ( ( 'mandatory' ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3512:1: ( 'mandatory' )
            {
             before(grammarAccess.getAttributeGroupAccess().getMandatoryMandatoryKeyword_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3513:1: ( 'mandatory' )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3514:1: 'mandatory'
            {
             before(grammarAccess.getAttributeGroupAccess().getMandatoryMandatoryKeyword_1_0()); 
            match(input,32,FOLLOW_32_in_rule__AttributeGroup__MandatoryAssignment_16959); 
             after(grammarAccess.getAttributeGroupAccess().getMandatoryMandatoryKeyword_1_0()); 

            }

             after(grammarAccess.getAttributeGroupAccess().getMandatoryMandatoryKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__MandatoryAssignment_1"


    // $ANTLR start "rule__AttributeGroup__DependsOnAssignment_2_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3529:1: rule__AttributeGroup__DependsOnAssignment_2_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__AttributeGroup__DependsOnAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3533:1: ( ( ( ruleQualifiedName ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3534:1: ( ( ruleQualifiedName ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3534:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3535:1: ( ruleQualifiedName )
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeCrossReference_2_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3536:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3537:1: ruleQualifiedName
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeQualifiedNameParserRuleCall_2_1_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__AttributeGroup__DependsOnAssignment_2_17002);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeQualifiedNameParserRuleCall_2_1_0_1()); 

            }

             after(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeCrossReference_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__DependsOnAssignment_2_1"


    // $ANTLR start "rule__AttributeGroup__DependsOnAssignment_2_2_1"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3548:1: rule__AttributeGroup__DependsOnAssignment_2_2_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__AttributeGroup__DependsOnAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3552:1: ( ( ( ruleQualifiedName ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3553:1: ( ( ruleQualifiedName ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3553:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3554:1: ( ruleQualifiedName )
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeCrossReference_2_2_1_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3555:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3556:1: ruleQualifiedName
            {
             before(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeQualifiedNameParserRuleCall_2_2_1_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__AttributeGroup__DependsOnAssignment_2_2_17041);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeQualifiedNameParserRuleCall_2_2_1_0_1()); 

            }

             after(grammarAccess.getAttributeGroupAccess().getDependsOnAttributeCrossReference_2_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__DependsOnAssignment_2_2_1"


    // $ANTLR start "rule__AttributeGroup__AttributesAssignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3567:1: rule__AttributeGroup__AttributesAssignment_4 : ( ruleAttribute ) ;
    public final void rule__AttributeGroup__AttributesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3571:1: ( ( ruleAttribute ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3572:1: ( ruleAttribute )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3572:1: ( ruleAttribute )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3573:1: ruleAttribute
            {
             before(grammarAccess.getAttributeGroupAccess().getAttributesAttributeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleAttribute_in_rule__AttributeGroup__AttributesAssignment_47076);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeGroupAccess().getAttributesAttributeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeGroup__AttributesAssignment_4"


    // $ANTLR start "rule__Association__Entity1Assignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3582:1: rule__Association__Entity1Assignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__Association__Entity1Assignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3586:1: ( ( ( RULE_ID ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3587:1: ( ( RULE_ID ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3587:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3588:1: ( RULE_ID )
            {
             before(grammarAccess.getAssociationAccess().getEntity1EntityCrossReference_2_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3589:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3590:1: RULE_ID
            {
             before(grammarAccess.getAssociationAccess().getEntity1EntityIDTerminalRuleCall_2_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Association__Entity1Assignment_27111); 
             after(grammarAccess.getAssociationAccess().getEntity1EntityIDTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getAssociationAccess().getEntity1EntityCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Entity1Assignment_2"


    // $ANTLR start "rule__Association__Name1Assignment_4"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3601:1: rule__Association__Name1Assignment_4 : ( RULE_ID ) ;
    public final void rule__Association__Name1Assignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3605:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3606:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3606:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3607:1: RULE_ID
            {
             before(grammarAccess.getAssociationAccess().getName1IDTerminalRuleCall_4_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Association__Name1Assignment_47146); 
             after(grammarAccess.getAssociationAccess().getName1IDTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Name1Assignment_4"


    // $ANTLR start "rule__Association__Cardinality1Assignment_6"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3616:1: rule__Association__Cardinality1Assignment_6 : ( ruleCardinality ) ;
    public final void rule__Association__Cardinality1Assignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3620:1: ( ( ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3621:1: ( ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3621:1: ( ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3622:1: ruleCardinality
            {
             before(grammarAccess.getAssociationAccess().getCardinality1CardinalityParserRuleCall_6_0()); 
            pushFollow(FOLLOW_ruleCardinality_in_rule__Association__Cardinality1Assignment_67177);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getAssociationAccess().getCardinality1CardinalityParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Cardinality1Assignment_6"


    // $ANTLR start "rule__Association__Entity2Assignment_8"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3631:1: rule__Association__Entity2Assignment_8 : ( ( RULE_ID ) ) ;
    public final void rule__Association__Entity2Assignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3635:1: ( ( ( RULE_ID ) ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3636:1: ( ( RULE_ID ) )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3636:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3637:1: ( RULE_ID )
            {
             before(grammarAccess.getAssociationAccess().getEntity2EntityCrossReference_8_0()); 
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3638:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3639:1: RULE_ID
            {
             before(grammarAccess.getAssociationAccess().getEntity2EntityIDTerminalRuleCall_8_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Association__Entity2Assignment_87212); 
             after(grammarAccess.getAssociationAccess().getEntity2EntityIDTerminalRuleCall_8_0_1()); 

            }

             after(grammarAccess.getAssociationAccess().getEntity2EntityCrossReference_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Entity2Assignment_8"


    // $ANTLR start "rule__Association__Name2Assignment_10"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3650:1: rule__Association__Name2Assignment_10 : ( RULE_ID ) ;
    public final void rule__Association__Name2Assignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3654:1: ( ( RULE_ID ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3655:1: ( RULE_ID )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3655:1: ( RULE_ID )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3656:1: RULE_ID
            {
             before(grammarAccess.getAssociationAccess().getName2IDTerminalRuleCall_10_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Association__Name2Assignment_107247); 
             after(grammarAccess.getAssociationAccess().getName2IDTerminalRuleCall_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Name2Assignment_10"


    // $ANTLR start "rule__Association__Cardinality2Assignment_12"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3665:1: rule__Association__Cardinality2Assignment_12 : ( ruleCardinality ) ;
    public final void rule__Association__Cardinality2Assignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3669:1: ( ( ruleCardinality ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3670:1: ( ruleCardinality )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3670:1: ( ruleCardinality )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3671:1: ruleCardinality
            {
             before(grammarAccess.getAssociationAccess().getCardinality2CardinalityParserRuleCall_12_0()); 
            pushFollow(FOLLOW_ruleCardinality_in_rule__Association__Cardinality2Assignment_127278);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getAssociationAccess().getCardinality2CardinalityParserRuleCall_12_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Association__Cardinality2Assignment_12"


    // $ANTLR start "rule__Constraint__ConstraintAssignment_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3680:1: rule__Constraint__ConstraintAssignment_2 : ( ruleExpression ) ;
    public final void rule__Constraint__ConstraintAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3684:1: ( ( ruleExpression ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3685:1: ( ruleExpression )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3685:1: ( ruleExpression )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3686:1: ruleExpression
            {
             before(grammarAccess.getConstraintAccess().getConstraintExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__Constraint__ConstraintAssignment_27309);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getConstraintAccess().getConstraintExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constraint__ConstraintAssignment_2"


    // $ANTLR start "rule__Or__RightAssignment_1_2"
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3695:1: rule__Or__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Or__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3699:1: ( ( ruleAnd ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3700:1: ( ruleAnd )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3700:1: ( ruleAnd )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3701:1: ruleAnd
            {
             before(grammarAccess.getOrAccess().getRightAndParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_27340);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3710:1: rule__And__RightAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3714:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3715:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3715:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3716:1: rulePrimary
            {
             before(grammarAccess.getAndAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_27371);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3725:1: rule__Primary__ExpressionAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__Primary__ExpressionAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3729:1: ( ( rulePrimary ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3730:1: ( rulePrimary )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3730:1: ( rulePrimary )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3731:1: rulePrimary
            {
             before(grammarAccess.getPrimaryAccess().getExpressionPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_27402);
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3740:1: rule__Atomic__NameAssignment_0_3 : ( ruleQualifiedName ) ;
    public final void rule__Atomic__NameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3744:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3745:1: ( ruleQualifiedName )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3745:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3746:1: ruleQualifiedName
            {
             before(grammarAccess.getAtomicAccess().getNameQualifiedNameParserRuleCall_0_3_0()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__Atomic__NameAssignment_0_37433);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAtomicAccess().getNameQualifiedNameParserRuleCall_0_3_0()); 

            }


            }

        }
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
    // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3755:1: rule__Atomic__NameAssignment_1_1 : ( ruleQualifiedName ) ;
    public final void rule__Atomic__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3759:1: ( ( ruleQualifiedName ) )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3760:1: ( ruleQualifiedName )
            {
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3760:1: ( ruleQualifiedName )
            // /C:\\Users\\Vicente\\Documents\\GitHub\\bw\\designer\\org.blended.data.ui/src-gen/org/blended/data/ui/contentassist/antlr/internal/InternalData.g:3761:1: ruleQualifiedName
            {
             before(grammarAccess.getAtomicAccess().getNameQualifiedNameParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_rule__Atomic__NameAssignment_1_17464);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getAtomicAccess().getNameQualifiedNameParserRuleCall_1_1_0()); 

            }


            }

        }
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

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleDataModel_in_entryRuleDataModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataModel__Group__0_in_ruleDataModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntity128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group__0_in_ruleEntity154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0_in_ruleAttribute214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeGroup_in_entryRuleAttributeGroup241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeGroup248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__0_in_ruleAttributeGroup274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group__0_in_ruleQualifiedName334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssociation_in_entryRuleAssociation361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAssociation368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__0_in_ruleAssociation394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstraint_in_entryRuleConstraint421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstraint428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Constraint__Group__0_in_ruleConstraint454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_entryRuleCardinality481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCardinality488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Alternatives_in_ruleCardinality514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_ruleExpression574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOr_in_entryRuleOr600 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOr607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0_in_ruleOr633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_entryRuleAnd660 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnd667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0_in_ruleAnd693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary720 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Alternatives_in_rulePrimary753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_entryRuleAtomic780 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomic787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Alternatives_in_ruleAtomic813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeGroup_in_rule__Entity__AttributesAlternatives_5_0849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Entity__AttributesAlternatives_5_0866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Alternatives898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0_in_rule__Cardinality__Alternatives915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0_in_rule__Cardinality__Alternatives933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0_in_rule__Cardinality__Alternatives951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0_in_rule__Primary__Alternatives984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0_in_rule__Primary__Alternatives1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomic_in_rule__Primary__Alternatives1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0_in_rule__Atomic__Alternatives1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0_in_rule__Atomic__Alternatives1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataModel__Group__0__Impl_in_rule__DataModel__Group__01101 = new BitSet(new long[]{0x0000000000880000L});
    public static final BitSet FOLLOW_rule__DataModel__Group__1_in_rule__DataModel__Group__01104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataModel__EntitiesAssignment_0_in_rule__DataModel__Group__0__Impl1131 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rule__DataModel__Group__1__Impl_in_rule__DataModel__Group__11162 = new BitSet(new long[]{0x0000000000880000L});
    public static final BitSet FOLLOW_rule__DataModel__Group__2_in_rule__DataModel__Group__11165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataModel__AssociatiosAssignment_1_in_rule__DataModel__Group__1__Impl1192 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__DataModel__Group__2__Impl_in_rule__DataModel__Group__21223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataModel__ConstraintAssignment_2_in_rule__DataModel__Group__2__Impl1250 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_rule__Entity__Group__0__Impl_in_rule__Entity__Group__01287 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Entity__Group__1_in_rule__Entity__Group__01290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__Entity__Group__0__Impl1318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group__1__Impl_in_rule__Entity__Group__11349 = new BitSet(new long[]{0x0000000080005000L});
    public static final BitSet FOLLOW_rule__Entity__Group__2_in_rule__Entity__Group__11352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__NameAssignment_1_in_rule__Entity__Group__1__Impl1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group__2__Impl_in_rule__Entity__Group__21409 = new BitSet(new long[]{0x0000000080005000L});
    public static final BitSet FOLLOW_rule__Entity__Group__3_in_rule__Entity__Group__21412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__ExistsAssignment_2_in_rule__Entity__Group__2__Impl1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group__3__Impl_in_rule__Entity__Group__31470 = new BitSet(new long[]{0x0000000080005000L});
    public static final BitSet FOLLOW_rule__Entity__Group__4_in_rule__Entity__Group__31473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group_3__0_in_rule__Entity__Group__3__Impl1500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group__4__Impl_in_rule__Entity__Group__41531 = new BitSet(new long[]{0x0000000000022020L});
    public static final BitSet FOLLOW_rule__Entity__Group__5_in_rule__Entity__Group__41534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Entity__Group__4__Impl1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group__5__Impl_in_rule__Entity__Group__51593 = new BitSet(new long[]{0x0000000000022020L});
    public static final BitSet FOLLOW_rule__Entity__Group__6_in_rule__Entity__Group__51596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__AttributesAssignment_5_in_rule__Entity__Group__5__Impl1623 = new BitSet(new long[]{0x0000000000020022L});
    public static final BitSet FOLLOW_rule__Entity__Group__6__Impl_in_rule__Entity__Group__61654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Entity__Group__6__Impl1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group_3__0__Impl_in_rule__Entity__Group_3__01727 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Entity__Group_3__1_in_rule__Entity__Group_3__01730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Entity__Group_3__0__Impl1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__Group_3__1__Impl_in_rule__Entity__Group_3__11789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__DependsOnAssignment_3_1_in_rule__Entity__Group_3__1__Impl1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__0__Impl_in_rule__Attribute__Group__01850 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1_in_rule__Attribute__Group__01853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__NameAssignment_0_in_rule__Attribute__Group__0__Impl1880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__1__Impl_in_rule__Attribute__Group__11910 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2_in_rule__Attribute__Group__11913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Attribute__Group__1__Impl1941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__2__Impl_in_rule__Attribute__Group__21972 = new BitSet(new long[]{0x0000000100004000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3_in_rule__Attribute__Group__21975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__TypeAssignment_2_in_rule__Attribute__Group__2__Impl2002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__3__Impl_in_rule__Attribute__Group__32032 = new BitSet(new long[]{0x0000000100004000L});
    public static final BitSet FOLLOW_rule__Attribute__Group__4_in_rule__Attribute__Group__32035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__MandatoryAssignment_3_in_rule__Attribute__Group__3__Impl2062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group__4__Impl_in_rule__Attribute__Group__42093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4__0_in_rule__Attribute__Group__4__Impl2120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4__0__Impl_in_rule__Attribute__Group_4__02161 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4__1_in_rule__Attribute__Group_4__02164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Attribute__Group_4__0__Impl2192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4__1__Impl_in_rule__Attribute__Group_4__12223 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4__2_in_rule__Attribute__Group_4__12226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__DependsOnAssignment_4_1_in_rule__Attribute__Group_4__1__Impl2253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4__2__Impl_in_rule__Attribute__Group_4__22283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4_2__0_in_rule__Attribute__Group_4__2__Impl2310 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4_2__0__Impl_in_rule__Attribute__Group_4_2__02347 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4_2__1_in_rule__Attribute__Group_4_2__02350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Attribute__Group_4_2__0__Impl2378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_4_2__1__Impl_in_rule__Attribute__Group_4_2__12409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__DependsOnAssignment_4_2_1_in_rule__Attribute__Group_4_2__1__Impl2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__0__Impl_in_rule__AttributeGroup__Group__02470 = new BitSet(new long[]{0x0000000100005000L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__1_in_rule__AttributeGroup__Group__02473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__AttributeGroup__Group__0__Impl2501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__1__Impl_in_rule__AttributeGroup__Group__12532 = new BitSet(new long[]{0x0000000100005000L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__2_in_rule__AttributeGroup__Group__12535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__MandatoryAssignment_1_in_rule__AttributeGroup__Group__1__Impl2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__2__Impl_in_rule__AttributeGroup__Group__22593 = new BitSet(new long[]{0x0000000100005000L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__3_in_rule__AttributeGroup__Group__22596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2__0_in_rule__AttributeGroup__Group__2__Impl2623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__3__Impl_in_rule__AttributeGroup__Group__32654 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__4_in_rule__AttributeGroup__Group__32657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__AttributeGroup__Group__3__Impl2685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__4__Impl_in_rule__AttributeGroup__Group__42716 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__5_in_rule__AttributeGroup__Group__42719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__AttributesAssignment_4_in_rule__AttributeGroup__Group__4__Impl2748 = new BitSet(new long[]{0x0000000000020022L});
    public static final BitSet FOLLOW_rule__AttributeGroup__AttributesAssignment_4_in_rule__AttributeGroup__Group__4__Impl2760 = new BitSet(new long[]{0x0000000000020022L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group__5__Impl_in_rule__AttributeGroup__Group__52793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__AttributeGroup__Group__5__Impl2821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2__0__Impl_in_rule__AttributeGroup__Group_2__02864 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2__1_in_rule__AttributeGroup__Group_2__02867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__AttributeGroup__Group_2__0__Impl2895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2__1__Impl_in_rule__AttributeGroup__Group_2__12926 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2__2_in_rule__AttributeGroup__Group_2__12929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__DependsOnAssignment_2_1_in_rule__AttributeGroup__Group_2__1__Impl2956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2__2__Impl_in_rule__AttributeGroup__Group_2__22986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2_2__0_in_rule__AttributeGroup__Group_2__2__Impl3013 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2_2__0__Impl_in_rule__AttributeGroup__Group_2_2__03050 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2_2__1_in_rule__AttributeGroup__Group_2_2__03053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__AttributeGroup__Group_2_2__0__Impl3081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__Group_2_2__1__Impl_in_rule__AttributeGroup__Group_2_2__13112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AttributeGroup__DependsOnAssignment_2_2_1_in_rule__AttributeGroup__Group_2_2__1__Impl3139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group__0__Impl_in_rule__QualifiedName__Group__03173 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group__1_in_rule__QualifiedName__Group__03176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedName__Group__0__Impl3203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group__1__Impl_in_rule__QualifiedName__Group__13232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group_1__0_in_rule__QualifiedName__Group__1__Impl3259 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group_1__0__Impl_in_rule__QualifiedName__Group_1__03294 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group_1__1_in_rule__QualifiedName__Group_1__03297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__QualifiedName__Group_1__0__Impl3325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedName__Group_1__1__Impl_in_rule__QualifiedName__Group_1__13356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedName__Group_1__1__Impl3383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__0__Impl_in_rule__Association__Group__03416 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Association__Group__1_in_rule__Association__Group__03419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Association__Group__0__Impl3447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__1__Impl_in_rule__Association__Group__13478 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Association__Group__2_in_rule__Association__Group__13481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Association__Group__1__Impl3509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__2__Impl_in_rule__Association__Group__23540 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Association__Group__3_in_rule__Association__Group__23543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Entity1Assignment_2_in_rule__Association__Group__2__Impl3570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__3__Impl_in_rule__Association__Group__33600 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Association__Group__4_in_rule__Association__Group__33603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Association__Group__3__Impl3631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__4__Impl_in_rule__Association__Group__43662 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Association__Group__5_in_rule__Association__Group__43665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Name1Assignment_4_in_rule__Association__Group__4__Impl3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__5__Impl_in_rule__Association__Group__53722 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Association__Group__6_in_rule__Association__Group__53725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Association__Group__5__Impl3753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__6__Impl_in_rule__Association__Group__63784 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Association__Group__7_in_rule__Association__Group__63787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Cardinality1Assignment_6_in_rule__Association__Group__6__Impl3814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__7__Impl_in_rule__Association__Group__73844 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Association__Group__8_in_rule__Association__Group__73847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Association__Group__7__Impl3875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__8__Impl_in_rule__Association__Group__83906 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Association__Group__9_in_rule__Association__Group__83909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Entity2Assignment_8_in_rule__Association__Group__8__Impl3936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__9__Impl_in_rule__Association__Group__93966 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Association__Group__10_in_rule__Association__Group__93969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Association__Group__9__Impl3997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__10__Impl_in_rule__Association__Group__104028 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Association__Group__11_in_rule__Association__Group__104031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Name2Assignment_10_in_rule__Association__Group__10__Impl4058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__11__Impl_in_rule__Association__Group__114088 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Association__Group__12_in_rule__Association__Group__114091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Association__Group__11__Impl4119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__12__Impl_in_rule__Association__Group__124150 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Association__Group__13_in_rule__Association__Group__124153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Cardinality2Assignment_12_in_rule__Association__Group__12__Impl4180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__13__Impl_in_rule__Association__Group__134210 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Association__Group__14_in_rule__Association__Group__134213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Association__Group__13__Impl4241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Association__Group__14__Impl_in_rule__Association__Group__144272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Association__Group__14__Impl4300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Constraint__Group__0__Impl_in_rule__Constraint__Group__04361 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Constraint__Group__1_in_rule__Constraint__Group__04364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Constraint__Group__0__Impl4392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Constraint__Group__1__Impl_in_rule__Constraint__Group__14423 = new BitSet(new long[]{0x0000000060220020L});
    public static final BitSet FOLLOW_rule__Constraint__Group__2_in_rule__Constraint__Group__14426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Constraint__Group__1__Impl4454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Constraint__Group__2__Impl_in_rule__Constraint__Group__24485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Constraint__ConstraintAssignment_2_in_rule__Constraint__Group__2__Impl4512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__0__Impl_in_rule__Cardinality__Group_1__04548 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1_in_rule__Cardinality__Group_1__04551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__0__Impl4578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__1__Impl_in_rule__Cardinality__Group_1__14607 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2_in_rule__Cardinality__Group_1__14610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Cardinality__Group_1__1__Impl4638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_1__2__Impl_in_rule__Cardinality__Group_1__24669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_1__2__Impl4696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__0__Impl_in_rule__Cardinality__Group_2__04731 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1_in_rule__Cardinality__Group_2__04734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_2__0__Impl4761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__1__Impl_in_rule__Cardinality__Group_2__14790 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2_in_rule__Cardinality__Group_2__14793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Cardinality__Group_2__1__Impl4821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_2__2__Impl_in_rule__Cardinality__Group_2__24852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Cardinality__Group_2__2__Impl4880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__0__Impl_in_rule__Cardinality__Group_3__04917 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1_in_rule__Cardinality__Group_3__04920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__Cardinality__Group_3__0__Impl4947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__1__Impl_in_rule__Cardinality__Group_3__14976 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2_in_rule__Cardinality__Group_3__14979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Cardinality__Group_3__1__Impl5007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cardinality__Group_3__2__Impl_in_rule__Cardinality__Group_3__25038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Cardinality__Group_3__2__Impl5066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__0__Impl_in_rule__Or__Group__05103 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Or__Group__1_in_rule__Or__Group__05106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__Group__0__Impl5133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group__1__Impl_in_rule__Or__Group__15162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0_in_rule__Or__Group__1__Impl5189 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__0__Impl_in_rule__Or__Group_1__05224 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1_in_rule__Or__Group_1__05227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__1__Impl_in_rule__Or__Group_1__15285 = new BitSet(new long[]{0x0000000060220020L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2_in_rule__Or__Group_1__15288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Or__Group_1__1__Impl5316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__Group_1__2__Impl_in_rule__Or__Group_1__25347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Or__RightAssignment_1_2_in_rule__Or__Group_1__2__Impl5374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__0__Impl_in_rule__And__Group__05410 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__And__Group__1_in_rule__And__Group__05413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__Group__0__Impl5440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group__1__Impl_in_rule__And__Group__15469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0_in_rule__And__Group__1__Impl5496 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__0__Impl_in_rule__And__Group_1__05531 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__And__Group_1__1_in_rule__And__Group_1__05534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__1__Impl_in_rule__And__Group_1__15592 = new BitSet(new long[]{0x0000000060220020L});
    public static final BitSet FOLLOW_rule__And__Group_1__2_in_rule__And__Group_1__15595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__And__Group_1__1__Impl5623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__Group_1__2__Impl_in_rule__And__Group_1__25654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__And__RightAssignment_1_2_in_rule__And__Group_1__2__Impl5681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__0__Impl_in_rule__Primary__Group_0__05717 = new BitSet(new long[]{0x0000000060220020L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1_in_rule__Primary__Group_0__05720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Primary__Group_0__0__Impl5748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__1__Impl_in_rule__Primary__Group_0__15779 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2_in_rule__Primary__Group_0__15782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__Primary__Group_0__1__Impl5809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_0__2__Impl_in_rule__Primary__Group_0__25838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Primary__Group_0__2__Impl5866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__0__Impl_in_rule__Primary__Group_1__05903 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1_in_rule__Primary__Group_1__05906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__1__Impl_in_rule__Primary__Group_1__15964 = new BitSet(new long[]{0x0000000060220020L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2_in_rule__Primary__Group_1__15967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Primary__Group_1__1__Impl5995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__Group_1__2__Impl_in_rule__Primary__Group_1__26026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Primary__ExpressionAssignment_1_2_in_rule__Primary__Group_1__2__Impl6053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__0__Impl_in_rule__Atomic__Group_0__06089 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1_in_rule__Atomic__Group_0__06092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__1__Impl_in_rule__Atomic__Group_0__16150 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2_in_rule__Atomic__Group_0__16153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Atomic__Group_0__1__Impl6181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__2__Impl_in_rule__Atomic__Group_0__26212 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3_in_rule__Atomic__Group_0__26215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Atomic__Group_0__2__Impl6243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__3__Impl_in_rule__Atomic__Group_0__36274 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4_in_rule__Atomic__Group_0__36277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_0_3_in_rule__Atomic__Group_0__3__Impl6304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_0__4__Impl_in_rule__Atomic__Group_0__46334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Atomic__Group_0__4__Impl6362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__0__Impl_in_rule__Atomic__Group_1__06403 = new BitSet(new long[]{0x0000000060220020L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1_in_rule__Atomic__Group_1__06406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__Group_1__1__Impl_in_rule__Atomic__Group_1__16464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Atomic__NameAssignment_1_1_in_rule__Atomic__Group_1__1__Impl6491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntity_in_rule__DataModel__EntitiesAssignment_06530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssociation_in_rule__DataModel__AssociatiosAssignment_16561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstraint_in_rule__DataModel__ConstraintAssignment_26592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Entity__NameAssignment_16623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Entity__ExistsAssignment_26659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Entity__DependsOnAssignment_3_16702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Entity__AttributesAlternatives_5_0_in_rule__Entity__AttributesAssignment_56737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__Attribute__NameAssignment_06770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Attribute__TypeAssignment_26801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__Attribute__MandatoryAssignment_36837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__Attribute__DependsOnAssignment_4_16880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__Attribute__DependsOnAssignment_4_2_16919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__AttributeGroup__MandatoryAssignment_16959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__AttributeGroup__DependsOnAssignment_2_17002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__AttributeGroup__DependsOnAssignment_2_2_17041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__AttributeGroup__AttributesAssignment_47076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Association__Entity1Assignment_27111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Association__Name1Assignment_47146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_rule__Association__Cardinality1Assignment_67177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Association__Entity2Assignment_87212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Association__Name2Assignment_107247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCardinality_in_rule__Association__Cardinality2Assignment_127278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__Constraint__ConstraintAssignment_27309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnd_in_rule__Or__RightAssignment_1_27340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__And__RightAssignment_1_27371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_rule__Primary__ExpressionAssignment_1_27402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__Atomic__NameAssignment_0_37433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rule__Atomic__NameAssignment_1_17464 = new BitSet(new long[]{0x0000000000000002L});

}