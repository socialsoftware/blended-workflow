package org.blended.common.utils

import java.util.List
import org.blended.common.common.And
import org.blended.common.common.AttributeDefinition
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
			AttributeDefinition: list.add(e.name)
			AttributeValue: list.add(e.name)
			//IntConstant: list.add(String.valueOf(e.name))
			//StringConstant: list.add(e.name)
			//BoolConstant: list.add(e.name)
		}		
	}
}
