package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.yawlfoundation.yawl.engine.YSpecificationID;

public class SpecUtils {
	
	//so that anyone instantiate the class
	private SpecUtils() {}
	
	public static YSpecificationID getYAWLSpecificationIDFromSpec(String spec) {		
		Document doc = StringUtils.stringToDoc(spec);
		
		if(doc == null) {
			return null;
		}
		
		Element root = doc.getRootElement();
		
		// get the identifier child
		Element specification = root.getChild("specification", root.getNamespace());
		Element identifier = specification.getChild("metaData", specification.getNamespace()).getChild("identifier", specification.getNamespace());
		
		Attribute uri = specification.getAttribute("uri");
		Element version = specification.getChild("metaData", specification.getNamespace()).getChild("version", specification.getNamespace());
		
		YSpecificationID specId = new YSpecificationID(identifier.getValue(), version.getValue(), uri.getValue());
		return specId;
	}
	
	public static String getYAWLSpecVersion(String spec) {
		Document doc = StringUtils.stringToDoc(spec);
		Element root = doc.getRootElement();
		
		Namespace yawlNS = root.getNamespace();
		
		Element specification = root.getChild("specification", yawlNS);
		Element version = specification.getChild("metaData", yawlNS).getChild("version", yawlNS);
		
		return version.getText();
	}
	
	public static String getBWSpecificationIDFromSpec(String spec) {
		int start = spec.indexOf("<bw:identifier>") + "<bw:identifier>".length() + 1;
		int stop = spec.indexOf("</bw:identifier>");
		if(start > stop) {
			return null;
		}
		String id = spec.substring(start, stop);
		return id;
	}
	
	public static String getGoalSpecificationIDFromSpec(String spec) {
		Document doc = StringUtils.stringToDoc(spec);
		Element root = doc.getRootElement();
		
		Namespace bwNs = root.getNamespace();
		String goalSpecID = root.getChild("goalSpec", bwNs).getAttributeValue("id");
		
		return goalSpecID;
	}
	
	public static String getConditionsspecificationIDFromSpec(String spec) {
		Document doc = StringUtils.stringToDoc(spec);
		Element root = doc.getRootElement();
		
		Namespace bwNs = root.getNamespace();
		String condSpecID = root.getChild("conditionsSpec", bwNs).getAttributeValue("id");
		
		return condSpecID;
	}
	
	public static String getDataModelURIFromBWSpec(String spec) {
		Document doc = StringUtils.stringToDoc(spec);
		Element root = doc.getRootElement();
		
		Namespace bwNs = root.getNamespace();
	
		return root.getChildText("dataModelURI", bwNs);
	}
	
	public static String getBWSpecNameFromSpec(String spec) {
		Document doc = StringUtils.stringToDoc(spec);
		Element root = doc.getRootElement();
		
		return root.getAttributeValue("uri");
	}
}
