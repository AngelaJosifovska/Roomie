function addDonut(divId,allData,category,colors_array){
	
    $('#'+divId).highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            },
            backgroundColor:'transparent'
        },
        title: {
            text: 'Properties by '+category,
            style:{
            	color:'white'
            }
        },
        plotOptions: {
            pie: {
                innerSize: 100,
                depth: 45
            }
        },
        series: [{
            name: 'Number od properties',
            data: allData
        }]
    });
}