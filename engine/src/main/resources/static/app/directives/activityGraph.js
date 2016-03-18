app.directive('activityGraph', function($window) {
	return {
		restrict : 'EA',
		template : "<svg width='960' height='600'></svg>",
		link : function(scope, elem, attrs) {
			var dataToPlot = scope[attrs.activityData];

			// Create the input graph
			var g = new dagreD3.graphlib.Graph().setGraph({})
					.setDefaultEdgeLabel(function() {
						return {};
					});

			g.graph().rankdir = "LR";

			// Here we"re setting nodeclass, which is used by our custom
			// drawNodes function
			// below.

			for (i = 0; i < dataToPlot.nodes.length; i++)
				g.setNode(dataToPlot.nodes[i].id, {
					label : dataToPlot.nodes[i].label,
					description : dataToPlot.nodes[i].description
				});

			g.nodes().forEach(function(v) {
				var node = g.node(v);
				// Round the corners of the nodes
				node.rx = node.ry = 5;
			});

			// Set up edges, no special attributes.
			for (i = 0; i < dataToPlot.edges.length; i++)
				g.setEdge(dataToPlot.edges[i].source,
						dataToPlot.edges[i].target);

			// Create the renderer
			var render = new dagreD3.render();

			// Set up an SVG group so that we can translate the final graph.
			var svg = d3.select("svg"), inner = svg.append("g");

			// Set up zoom support
			var zoom = d3.behavior.zoom().on(
					"zoom",
					function() {
						inner.attr("transform", "translate("
								+ d3.event.translate + ")" + "scale("
								+ d3.event.scale + ")");
					});
			svg.call(zoom);

			// Simple function to style the tooltip for the given node.
			var styleTooltip = function(description) {
				return description;
			};

			// Run the renderer. This is what draws the final graph.
			render(inner, g);

			inner.selectAll("g.node").append("title").text(function(v) {
				return g.node(v).description
			});


			// Center the graph
			// var initialScale = 0.75;
			// zoom.translate(
			// [ (svg.attr("width") - g.graph().width * initialScale) / 2,
			// 20 ]).scale(initialScale).event(svg);
			// svg.attr('height', g.graph().height * initialScale + 40);
		}
	};
});