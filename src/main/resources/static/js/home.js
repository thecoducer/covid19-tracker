$(document).ready(
    function () {
        $.ajax({
            url: "/getseries",
            success: function (result) {

                var dailyConfirmed = [];
                var dailyDeceased = [];
                var dailyRecovered = [];

                Object.keys(result).forEach(
                    function (key) {

                        dailyConfirmed.push([new Date(result[key].date + '2020'), parseInt(result[key].dailyConfirmed)]);

                        dailyDeceased.push([new Date(result[key].date + '2020'), parseInt(result[key].dailyDeceased)]);

                        dailyRecovered.push([new Date(result[key].date + '2020'), parseInt(result[key].dailyRecovered)]);
                    });

                /* console.log(dailyConfirmed);
                console.log(dailyDeceased);
                console.log(dailyRecovered); */

                drawChart(dailyConfirmed, dailyDeceased, dailyRecovered);

            }
        });
    });

function drawChart(dailyConfirmed, dailyDeceased, dailyRecovered) {
    Highcharts.chart('seriesgraph', {
        plotOptions: {
            series: {

                pointStart: Date.UTC(2020, 0, 30),
                pointInterval: 1000 * 3600 * 24
            }
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
        },
        yAxis: [{
            className: 'highcharts-color-0',
            title: {
                text: ''
            }
        }],
        credits: {
            enabled: false
        },
        tooltip: {
            crosshairs: true,
            shared: true,
            valueSuffix: '',
            xDateFormat: '%A, %b %e, %Y'
        },
        series: [{
            name: 'Confirmed',
            data: dailyConfirmed,
            type: 'line',
            color: '#4830ff'
        },
        {
            name: 'Deceased',
            data: dailyDeceased,
            type: 'line',
            color: '#ff0000'
        },
        {
            name: 'Recovered',
            data: dailyRecovered,
            type: 'line',
            color: '#29ff00'
        }]
    });
}

