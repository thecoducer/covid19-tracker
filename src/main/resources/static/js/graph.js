$(document).ready(
    function() {
        $.ajax({
            url : "/getseries",
            success : function(result) {
                var yearDtls = [];
                var countDtls = [];
                console.log(result);
                Object.keys(result).forEach(
                        function(key) {
                            yearDtls.push(key);
                            countDtls.push(result[key]);
                        });
                drawChart(yearDtls, countDtls);
            }
        });
    });

function drawChart(year, viewCounts) {
Highcharts.chart('seriesgraph', {
    chart : {
        type : 'column',
        styledMode : true
    },
    title : {
        text : 'Blog Page view count'
    },
    xAxis : [ {
        title : {
            text : 'Year'
        },
        categories : year
    } ],
    yAxis : [ {
        className : 'highcharts-color-0',
        title : {
            text : 'Page View Count'
        }
    } ],
    series : [ {
        data : viewCounts
    } ]
});
}