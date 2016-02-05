package org.blended.common.utils

import java.util.HashSet
import java.util.List
import java.util.Set
import org.blended.common.common.And
import org.blended.common.common.PathDefinition
import org.blended.common.common.AttributeValue
import org.blended.common.common.Div
import org.blended.common.common.Equal
import org.blended.common.common.Expression
import org.blended.common.common.Greater
import org.blended.common.common.GreaterEqual
import org.blended.common.common.Minus
import org.blended.common.common.Mul
import org.blended.common.common.Not
import org.blended.common.common.NotEqual
import org.blended.common.common.Or
import org.blended.common.common.Plus
import org.blended.common.common.Smaller
import org.blended.common.common.SmallerEqual

class Queries {
	static def Object getDecomposedExpression(Expression e, List<String> list) {	
		switch (e) {
			Or: {
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)
			}
			And: {
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)
			}
			Equal: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			NotEqual: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Greater: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			GreaterEqual: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Smaller: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			SmallerEqual: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Plus: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Minus: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Mul: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Div: { 
				e.left.getDecomposedExpression(list)
				e.right.getDecomposedExpression(list)			
			}
			Not: e.expression.getDecomposedExpression(list)
			PathDefinition: list.add(e.path)
			AttributeValue: list.add(e.name)
			//IntConstant: list.add(String.valueOf(e.name))
			//StringConstant: list.add(e.name)
			//BoolConstant: list.add(e.name)
		}		
	}
	
	static def getEntityNameFrom(String name) {
		if (!name.contains(".")) 
			return name
		else 
			return name.substring(0, name.indexOf('.')).toFirstUpper //TO CHANGE FOR THE ENGINE!!
	}
	
	static def getEntityNameTo(String name) {
		var name2 = name.substring(0, name.lastIndexOf('.'));
		return name2.substring(name2.lastIndexOf('.')+1).toFirstUpper //TO CHANGE FOR THE ENGINE!!
	}
	
	static def getAttributeName(String name) {
		return name.substring(name.lastIndexOf('.')+1) //TO CHANGE FOR THE ENGINE!!
	}
	
	static def getDependenciesForElement(Set<String> names) { //TO CHANGE FOR THE ENGINE!!
		var list = new HashSet<String>()
		if (names.size == 1 && names.get(0).equals("Episode")) {
			list.add("Episode.patient")
		}
		else if (names.size == 1 && names.get(0).equals("Prescription.description")) {
			list.add("Prescription.episode.report.description")
		}
		else if (names.contains("Medication.name")&& names.contains("Medication.quantity") && names.contains("Medication.heartImpact")) {
			list.add("Medication.prescription.description")
		}
		return list
	}
	
	static def getAttributesRelatedToRule(Set<String> names) { //TO CHANGE FOR THE ENGINE!!
		var list = new HashSet<String>()
		if (names.contains("Medication.heartImpact")&& names.contains("Medication.quantity")) {
			list.add("Medication.name")
			list.add("Medication.quantity")
			list.add("Medication.quantity")
		}
		return list
	}
}
