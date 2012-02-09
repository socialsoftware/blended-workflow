package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.CompareAttributeToValueCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.ExistsAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class ConditionParser {
	
	private final int STR_LENGHT;
	
	private static String _cond;
//	private String dataModelURI;
//	private String instanceID;
	
	private int _token;
	
	public ConditionParser(String condition, String dataModelURI, String instanceID) throws BlendedWorkflowException {
		if(condition == null || condition.equals("")) {
			throw new BlendedWorkflowException("@ConditionParser.ConditionParser");
		}
		_cond = condition;
		STR_LENGHT = condition.length();
//		this.dataModelURI = dataModelURI;
//		this.instanceID = instanceID;
		_token = 0;
	}
	
	public Condition parseCondition() throws BlendedWorkflowException {
		Condition finalCondition = null;
		if(_cond.startsWith("exists(") || _cond.startsWith("equalTo(")) {
			finalCondition = parseConditionType();
		} else if(_cond.startsWith("true")){
			return new TrueCondition();
		} else {
			throw new BlendedWorkflowException("@ConditionParser.parseCondition");
		}
		
		return continueParseCondition(finalCondition);
	}
	
	protected Condition continueParseCondition(Condition parsedCondition) throws BlendedWorkflowException {
		while(_token < STR_LENGHT) {
			if(_cond.startsWith(" and ", _token) || _cond.startsWith(" or ", _token)) {
				parsedCondition = parseConditionJoiner(parsedCondition);
			} else {
				throw new BlendedWorkflowException("@ConditionParser.continueParseCondition");
			}
		}
		return parsedCondition;
	}
	
	////// PARSE RULES /////
	protected Condition parseConditionType() throws BlendedWorkflowException {
		Condition parsedCondition;
		if(_cond.startsWith("exists(", _token)) {
			parsedCondition = parseExistsCondition();
		} else if(_cond.startsWith("equalTo(", _token)) {
			parsedCondition = parseEqualToCondition();
		} else {
			return null;
		}
		
		// see if there is a .not() next
		if(_cond.startsWith(".not()", _token)) {
			parsedCondition = parseNotCondition(parsedCondition);
		}
		return parsedCondition;
	}
	
	protected Condition parseExistsCondition() throws BlendedWorkflowException {
		int endOfCondition = _cond.indexOf(')', _token);
		if(endOfCondition < _token) {
			throw new BlendedWorkflowException("@ConditionParser.parseExistsCondition");
		}
		
		String existsString = _cond.substring(_token, endOfCondition+1);
		System.out.println("1: Condicao: "+existsString); // FIXME APAGAR
		StringBuilder elementName  = new StringBuilder();
		int startArgs = "exists(".length();
		parseExistsConditionArgs(existsString, startArgs, existsString.length()-1, elementName);
		System.out.println("2: ARGS: "+elementName.toString()); // FIXME APAGAR
		argsPrint(elementName.toString());

		Condition existsCondition = new ExistsAttributeCondition();//dataModelURI, new DataNameURI(elementName.toString()), instanceID);
		_token = endOfCondition+1;
		System.out.println("@ConditionParser.EXISTS: "+existsCondition.getOID()); // FIXME APAGAR
		return existsCondition;
	}
	
	// DATAMODELURI METHOD
	private void argsPrint (String elementURI){
		String[] elementArr = elementURI.split("\\.");
		String ent;
		String att = null;
		ent = elementArr[0];
		if(elementArr.length > 1) {
			att = elementArr[1];
		}
		System.out.println("3: entity: "+ent);
		System.out.println("4: attribute: "+att); 
	}
	
	protected void parseExistsConditionArgs(String existsCondition, int startArgs, int endArgs, StringBuilder elementName) {
		if(startArgs > endArgs) return;
		
		elementName.append(existsCondition.substring(startArgs, endArgs));
	}
	
	protected Condition parseEqualToCondition() throws BlendedWorkflowException {
		int endOfCondition = _cond.indexOf(')', _token);
		
		if(endOfCondition < _token) {
			throw new BlendedWorkflowException("@ConditionParser.parseEqualToCondition");
		}
		
		String equaltoString = _cond.substring(_token, endOfCondition+1);
		StringBuilder elementName = new StringBuilder();
		StringBuilder elementValue = new StringBuilder();
		int startArgs = "equalTo(".length();
		parseEqualToConditionArgs(equaltoString, startArgs, equaltoString.length()-1, elementName, elementValue);
		
		Condition equalToCondition = new CompareAttributeToValueCondition(); //(dataModelURI, new DataNameURI(elementName.toString()), instanceID, elementValue.toString());
		_token = endOfCondition+1;
		
		return equalToCondition;
	}
	
	protected void parseEqualToConditionArgs(String conditionString, int startArgs, int endArgs, StringBuilder elementName, StringBuilder elementValue) {
		if(startArgs > endArgs) return;
		
		int subToken = conditionString.indexOf(',', startArgs);
		
		elementName.append(conditionString.substring(startArgs, subToken));
		elementValue.append(conditionString.substring(subToken+1, endArgs).trim());
	}
	
	protected Condition parseNotCondition(Condition typeCondition) {
		_token += ".not()".length();
		return new NotCondition(typeCondition);
	}
	
	protected Condition parseConditionJoiner(Condition parsedCondition) throws BlendedWorkflowException {
		if(_cond.startsWith(" and ", _token)) {
			return parseAndCondition(parsedCondition);
		} else if(_cond.startsWith(" or ", _token)) {
			return parseOrCondition(parsedCondition);
		} else {
			throw new BlendedWorkflowException("@ConditionParser.parseConditionJoiner");
		}
	}
	
	protected Condition parseAndCondition(Condition parsedCondition) throws BlendedWorkflowException {
		_token += " and ".length();
		AndCondition and = new AndCondition(parsedCondition, parseConditionType());
		System.out.println("@ConditionParser.AND: "+and.getOID()); // FIXME APAGAR
		return and;
	}
	
	protected Condition parseOrCondition(Condition parsedCondition) throws BlendedWorkflowException {
		_token += " or ".length();
		return new AndCondition(parsedCondition, parseConditionType());
	}

}
