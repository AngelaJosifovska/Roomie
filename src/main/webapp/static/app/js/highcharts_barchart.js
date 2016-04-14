
function add3DChart(divId,data,category){
    $('#'+divId).highcharts({
        chart: {
            type: 'column',
            backgroundColor:'transparent',
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 15,
                depth: 70
            }
        },
        title: {
            text: 'Properties by '+category,
            style:{
            	color: 'white'
            }
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: $.map(data, function(elem) { return elem.category }),
            labels: {
                style: {
                   color: 'white'
                }
             }
        },
        yAxis: {
            title: {
                text: null
            },
            labels: {
                style: {
                   color: 'white'
                }
             }
        },
        series: [{
            name: 'Class',
            data: $.map(data, function(elem) { return elem.num;})
        }]
    });
}
