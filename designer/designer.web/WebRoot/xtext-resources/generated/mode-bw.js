define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		var keywords = "ABOUT|AND|ANY|ANYONE|Association|BY|CAN|CAPABILITY|Capability|Constraint|DATA|DELEGATE|DELEGATED|DIRECTLY|Entity|FIELD|FOR|HAS|HAVE|IN|INFORMED|IS|NOT|OF|OR|PERSON|POSITION|Person|Position|REPORTED|REPORTS|RESPONSIBLE|ROLE|ResourceRules|Role|Specification|TO|UNIT|Unit|WORK|canDelegateWorkTo|dependsOn|description|exists|false|has|hasCapability|informs|isMemberOf|mandatory|occupies|participatesIn|reportsTo|responsible|true|with";
		this.$rules = {
			"start": [
				{token: "comment", regex: "\\/\\/.*$"},
				{token: "comment", regex: "\\/\\*", next : "comment"},
				{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
				{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
				{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
				{token: "lparen", regex: "[({]"},
				{token: "rparen", regex: "[)}]"},
				{token: "keyword", regex: "\\b(?:" + keywords + ")\\b"}
			],
			"comment": [
				{token: "comment", regex: ".*?\\*\\/", next : "start"},
				{token: "comment", regex: ".+"}
			]
		};
	};
	oop.inherits(HighlightRules, mTextHighlightRules.TextHighlightRules);
	
	var Mode = function() {
		this.HighlightRules = HighlightRules;
	};
	oop.inherits(Mode, mText.Mode);
	Mode.prototype.$id = "xtext/bw";
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	return {
		Mode: Mode
	};
});
