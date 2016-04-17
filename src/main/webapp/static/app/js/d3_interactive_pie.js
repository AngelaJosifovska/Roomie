
  // This example lays out multiple interactive pie charts on a page with multiple HTML layout constructs.
  // Created by Frank Guerino : "http://www.guerino.net"

  // Data Used for this example...
  var dataSet1 = [
    {category: "Legend String 1", num: 51},
    {category: "Legend String 2", num: 21},
    {category: "Legend String 3", num: 31},
    {category: "Legend String 4", num: 14},
    {category: "Legend String 5", num: 19},
    {category: "Legend String 6", num: 47},
    {category: "Legend String 7", num: 27}];


  function drawPie( pieName, dataSet, selectString, colors, margin, outerRadius, innerRadius, sortArcs ) {

    var colorScale = d3.scale.category20c();
    switch (colors)
    {
      case "colorScale10":
        colorScale = d3.scale.category10();
        break;
      case "colorScale20":
        colorScale = d3.scale.category20();
        break;
      case "colorScale20b":
        colorScale = d3.scale.category20b();
        break;
      case "colorScale20c":
        colorScale = d3.scale.category20c();
        break;
      default:
        colorScale = d3.scale.category20c();
    };

var canvasWidth = 700;
var pieWidthTotal = outerRadius * 2;;
var pieCenterX = outerRadius + margin/2;
var pieCenterY = outerRadius + margin/2;
var legendBulletOffset = 0;
var legendVerticalOffset = outerRadius - margin;
var legendTextOffset = 20;
var textVerticalSpace = 20;

var canvasHeight = 0;
    var pieDrivenHeight = outerRadius*2 + margin*2;
    var legendTextDrivenHeight = (dataSet.length * textVerticalSpace) + margin*2;
// Autoadjust Canvas Height
if (pieDrivenHeight >= legendTextDrivenHeight)
{
  canvasHeight = pieDrivenHeight;
}
else
{
  canvasHeight = legendTextDrivenHeight;
}

    var x = d3.scale.linear().domain([0, d3.max(dataSet, function(d) { return d.num; })]).rangeRound([0, pieWidthTotal]);
    var y = d3.scale.linear().domain([0, dataSet.length]).range([0, (dataSet.length * 20)]);


    var synchronizedMouseOver = function() {
      var arc = d3.select(this);
      var indexValue = arc.attr("index_value");

      var arcSelector = "." + "pie-" + pieName + "-arc-" + indexValue;
      var selectedArc = d3.selectAll(arcSelector);
      selectedArc.style("fill", "Maroon");

      var bulletSelector = "." + "pie-" + pieName + "-legendBullet-" + indexValue;
      var selectedLegendBullet = d3.selectAll(bulletSelector);
      selectedLegendBullet.style("fill", "Maroon");

      var textSelector = "." + "pie-" + pieName + "-legendText-" + indexValue;
      var selectedLegendText = d3.selectAll(textSelector);
      selectedLegendText.style("fill", "Maroon");
    };

    var synchronizedMouseOut = function() {
      var arc = d3.select(this);
      var indexValue = arc.attr("index_value");

      var arcSelector = "." + "pie-" + pieName + "-arc-" + indexValue;
      var selectedArc = d3.selectAll(arcSelector);
      var colorValue = selectedArc.attr("color_value");
      selectedArc.style("fill", colorValue);

      var bulletSelector = "." + "pie-" + pieName + "-legendBullet-" + indexValue;
      var selectedLegendBullet = d3.selectAll(bulletSelector);
      var colorValue = selectedLegendBullet.attr("color_value");
      selectedLegendBullet.style("fill", colorValue);

      var textSelector = "." + "pie-" + pieName + "-legendText-" + indexValue;
      var selectedLegendText = d3.selectAll(textSelector);
      selectedLegendText.style("fill", "white");
    };

    var tweenPie = function (b) {
      b.innerRadius = 0;
      var i = d3.interpolate({startAngle: 0, endAngle: 0}, b);
      return function(t) {
        return arc(i(t));
      };
    }

    // Create a drawing canvas...
    var canvas = d3.select(selectString)
      .append("svg:svg") //create the SVG element inside the <body>
        .data([dataSet]) //associate our data with the document
        .attr("width", canvasWidth) //set the width of the canvas
        .attr("height", canvasHeight) //set the height of the canvas
        .append("svg:g") //make a group to hold our pie chart
          .attr("transform", "translate(" + pieCenterX + "," + pieCenterY + ")") // Set center of pie

// Define an arc generator. This will create <path> elements for using arc data.
    var arc = d3.svg.arc()
        .innerRadius(innerRadius) // Causes center of pie to be hollow
        .outerRadius(outerRadius);

// Define a pie layout: the pie angle encodes the value of dataSet.
// Since our data is in the form of a post-parsed CSV string, the
// values are Strings which we coerce to numbers.
    var pie = d3.layout.pie()
    .value(function(d) { return d.num; })
    .sort(function(a, b) {if (sortArcs==1) { return b.num - a.num; } else { return null; } });

    // Select all <g> elements with class slice (there aren't any yet)
    var arcs = canvas.selectAll("g.slice")
      // Associate the generated pie data (an array of arcs, each having startAngle,
      // endAngle and value properties) 
      .data(pie)
      // This will create <g> elements for every "extra" data element that should be associated
      // with a selection. The result is creating a <g> for every object in the data array
      // Create a group to hold each slice (we will have a <path> and a <text>      // element associated with each slice)
  .enter().append("svg:a")
        .attr("xlink:href", function(d) { return d.data.link; })
        .append("svg:g")
          .attr("class", "slice")    //allow us to style things in the slices (like text)
          // Set the color for each slice to be chosen from the color function defined above
          // This creates the actual SVG path using the associated data (pie) with the arc drawing function
          .style("stroke", "White" )
          .attr("d", arc);

    arcs.append("svg:path")
      // Set the color for each slice to be chosen from the color function defined above
      // This creates the actual SVG path using the associated data (pie) with the arc drawing function
      .attr("fill", function(d, i) { return colorScale(i); } )
      .attr("color_value", function(d, i) { return colorScale(i); }) // Bar fill color...
      .attr("index_value", function(d, i) { return "index-" + i; })
      .attr("class", function(d, i) { return "pie-" + pieName + "-arc-index-" + i; })
      .style("stroke", "White" )
      .attr("d", arc)
      .on('mouseover', synchronizedMouseOver)
      .on("mouseout", synchronizedMouseOut)
      .transition()
        .ease("bounce")
        .duration(2000)
        .delay(function(d, i) { return i * 50; })
        .attrTween("d", tweenPie);

    // Add a num value to the larger arcs, translated to the arc centroid and rotated.
    arcs.filter(function(d) { return d.endAngle - d.startAngle > .2; }).append("svg:text")
      .attr("dy", ".35em")
      .attr("text-anchor", "middle")
      //.attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")rotate(" + angle(d) + ")"; })
      .attr("transform", function(d) { //set the label's origin to the center of the arc
        //we have to make sure to set these before calling arc.centroid
        d.outerRadius = outerRadius; // Set Outer Coordinate
        d.innerRadius = innerRadius; // Set Inner Coordinate
        return "translate(" + arc.centroid(d) + ")rotate(" + angle(d) + ")";
      })
      .style("fill", "White")
      .style("font", "normal 12px Arial")
      .text(function(d) { return d.data.num; });

    // Computes the angle of an arc, converting from radians to degrees.
    function angle(d) {
      var a = (d.startAngle + d.endAngle) * 90 / Math.PI - 90;
      return a > 90 ? a - 180 : a;
    }

    // Plot the bullet circles...
    canvas.selectAll("circle")
      .data(dataSet).enter().append("svg:circle") // Append circle elements
      .attr("cx", pieWidthTotal - 50)
      .attr("cy", function(d, i) { return i*textVerticalSpace - legendVerticalOffset; } )
      .attr("stroke-width", ".5")
      .style("fill", function(d, i) { return colorScale(i); }) // Bullet fill color
      .attr("r", 5)
      .attr("color_value", function(d, i) { return colorScale(i); }) // Bar fill color...
      .attr("index_value", function(d, i) { return "index-" + i; })
      .attr("class", function(d, i) { return "pie-" + pieName + "-legendBullet-index-" + i; })
      .on('mouseover', synchronizedMouseOver)
      .on("mouseout", synchronizedMouseOut);

    // Create hyper linked text at right that acts as label key...
    canvas.selectAll("a.legend_link")
      .data(dataSet) // Instruct to bind dataSet to text elements
      .enter().append("svg:a") // Append legend elements
        .attr("xlink:href", "#")
        .append("text")
          .attr("text-anchor", "center")
          .attr("x", pieWidthTotal  - 50 + legendTextOffset)
          .attr("y", function(d, i) { return i*textVerticalSpace - legendVerticalOffset; } )
          .attr("dx", 0)
          .attr("dy", "5px") // Controls padding to place text in alignment with bullets
          .text(function(d) { return d.category;})
          .attr("color_value", function(d, i) { return colorScale(i); }) // Bar fill color...
          .attr("index_value", function(d, i) { return "index-" + i; })
          .attr("class", function(d, i) { return "pie-" + pieName + "-legendText-index-" + i; })
          .style("fill", "white")
          .style("font", "normal 2.5em Arial")
          .on('mouseover', synchronizedMouseOver)
          .on("mouseout", synchronizedMouseOut);

  };
  