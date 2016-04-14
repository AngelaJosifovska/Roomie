function addBarchart(data, divId,num){
    console.log(data);
	console.log(divId);
	var margin = {top: 40, right: 20, bottom: 30, left: 40},
		width = 500 - margin.left - margin.right,
		height = 300 - margin.top - margin.bottom;
	
	console.log(height);

	var formatPercent = d3.format("");

	var x = d3.scale.ordinal()
		.rangeRoundBands([0, width], .1);

	var y = d3.scale.linear()
		.range([height, 0]);

	var xAxis = d3.svg.axis()
		.scale(x)
		.orient("bottom");

	var yAxis = d3.svg.axis()
		.scale(y)
		.orient("left")
		.tickFormat(formatPercent);

	var tip = d3.tip()
	  .attr('class', 'd3-tip')
	  .offset([-10, 0])
	  .html(function(d) {
		return "<strong>Frequency:</strong> <span style='color:red'>" + d.num + "</span>";
	  })

	var svg = d3.select("#"+divId).append("svg")
		.attr("width", width + margin.left + margin.right)
		.attr("height", height + margin.top + margin.bottom)
	  .append("g")
		.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	svg.call(tip);

	  x.domain(data.map(function(d) { return d.category; }));
	  y.domain([0, d3.max(data, function(d) { return d.num; })]);

	  svg.append("g")
		  .attr("class", "x axis")
		  .attr("transform", "translate(0," + height + ")")
		  .call(xAxis);

	  svg.append("g")
		  .attr("class", "y axis")
		  .call(yAxis)
		.append("text")
		  .attr("transform", "rotate(-90)")
		  .attr("y", 6)
		  .attr("dy", ".71em")
		  .style("text-anchor", "end")
		  .text("Frequency");

	  svg.selectAll(".bar")
		  .data(data)
		.enter().append("rect")
		  .attr("class", "bar")
		  .attr("class", num)
		  .attr("x", function(d) { return x(d.category); })
		  .attr("width", x.rangeBand())
		  .attr("y", function(d) { return y(d.num); })
		  .attr("height", function(d) { return height - y(d.num); })
		  .on('mouseover', tip.show)
		  .on('mouseout', tip.hide);



	function type(d) {
	  d.num= +d.num;
	  return d;
	}
}
