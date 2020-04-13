$(document).ready(function () {
    $('#india_casesrate').click();
    $('#india_casesrate').click();
});


$(document).ready(function () {
    var m = new Map([
        ["Andaman and Nicobar Islands", "AN_tbody"],
        ["Andhra Pradesh", "AP_tbody"],
        ["Arunachal Pradesh", "AR_tbody"],
        ["Assam", "AS_tbody"],
        ["Bihar", "BR_tbody"],
        ["Chandigarh", "CH_tbody"],
        ["Chhattisgarh", "CT_tbody"],
        ["Dadra and Nagar Haveli", "DN_tbody"],
        ["Delhi", "DL_tbody"],
        ["Goa", "GA_tbody"],
        ["Gujarat", "GJ_tbody"],
        ["Haryana", "HR_tbody"],
        ["Himachal Pradesh", "HP_tbody"],
        ["Jammu and Kashmir", "JK_tbody"],
        ["Jharkhand", "JH_tbody"],
        ["Karnataka", "KA_tbody"],
        ["Kerala", "KL_tbody"],
        ["Ladakh", "LA_tbody"],
        ["Madhya Pradesh", "MP_tbody"],
        ["Maharashtra", "MH_tbody"],
        ["Manipur", "MN_tbody"],
        ["Mizoram", "MZ_tbody"],
        ["Odisha", "ORR_tbody"],
        ["Puducherry", "PY_tbody"],
        ["Punjab", "PB_tbody"],
        ["Rajasthan", "RJ_tbody"],
        ["Tamil Nadu", "TN_tbody"],
        ["Telangana", "TG_tbody"],
        ["Tripura", "TR_tbody"],
        ["Uttar Pradesh", "UP_tbody"],
        ["Uttarakhand", "UT_tbody"],
        ["West Bengal", "WB_tbody"],
        ["Nagaland", "NL_tbody"],
        ["Meghalaya", "ML_tbody"],
        ["Daman and Diu", "DD_tbody"],
        ["Lakshadweep", "LD_tbody"],
        ["Sikkim", "SK_tbody"]
    ]);

    $("#table_id").on('click', 'tr', function () {
        var tdValue = $(this).children(':first').text();
        $("#modaltitle").text(tdValue);
        var id = m.get(tdValue);
        $("#district_table").find("tbody").not("#" + id).hide();
        $("#" + id).show();
    });
});


$(document).ready(function () {

    var m = new Map();

    $.getJSON('https://corona.lmao.ninja/v2/historical/USA,Spain,Italy,Germany,France,China,Iran,UK,Turkey,Belgium,S.%20Korea,India?lastdays=40',
        function (data) {

            var i;

            for (i = 0; i < data.length; i++) {

                var cases = data[i].timeline.cases;
                var deaths = data[i].timeline.deaths;
                var recovered = data[i].timeline.recovered;
                var countryName = data[i].country;

                for (x in cases) {
                    date = x;
                    break;
                }

                var countryList = [];
                var casesList = [];
                var deathsList = [];
                var recoveredList = [];

                for (x in cases) {
                    casesList.push([new Date(x), cases[x]]);
                }

                for (x in deaths) {
                    deathsList.push([new Date(x), deaths[x]]);
                }

                for (x in recovered) {
                    recoveredList.push([new Date(x), recovered[x]]);
                }

                countryList.push(casesList, deathsList, recoveredList);

                var casesList = [];
                var deathsList = [];
                var recoveredList = [];

                m.set(countryName, countryList);
            }

            var dateArray = date.split("/");

            /* console.log(dateArray); */

            var date = Date.UTC(dateArray[2] + "20", dateArray[0] - 1, dateArray[1]);

            /* console.log(date); */
            /* console.log(Date.UTC(2020, 2, 12)); */

            drawWorldTotalCasesChart(m, date);
            drawWorldDeathChart(m, date);
            drawWorldRecoveredChart(m, date);

        });


    /* $("#nav-profile-tab").click(function () {
        console.log("Initiate world charts");
        drawWorldTotalCasesChart(m, date);
        drawWorldDeathChart(m, date);
        drawWorldRecoveredChart(m, date);
    }); */


    function drawWorldTotalCasesChart(m, date) {
        Highcharts.chart('worldtotalcasesgraph', {
            plotOptions: {
                series: {
                    pointStart: date,
                    pointInterval: 1000 * 3600 * 24,
                    marker: {
                        enabled: false
                    }
                }
            },
            title: {
                text: 'Confirmed Cases'
            },
            xAxis: {
                type: 'datetime',
                title: {
                    text: 'Last 40 days'
                }
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
                name: 'USA',
                data: m.get("USA")[0],
                type: 'line',
                color: '#4830ff'
            },
            {
                name: 'Spain',
                data: m.get("Spain")[0],
                type: 'line',
                color: '#ff0000'
            },
            {
                name: 'Italy',
                data: m.get("Italy")[0],
                type: 'line',
                color: '#29ff00'
            },
            {
                name: 'Germany',
                data: m.get("Germany")[0],
                type: 'line',
                color: '#d9c630'
            },
            {
                name: 'France',
                data: m.get("France")[0],
                type: 'line',
                color: '#969aff'
            },
            {
                name: 'China',
                data: m.get("China")[0],
                type: 'line',
                color: '#000000'
            },
            {
                name: 'Iran',
                data: m.get("Iran")[0],
                type: 'line',
                color: '#b698d3'
            },
            {
                name: 'UK',
                data: m.get("UK")[0],
                type: 'line',
                color: '#FF00F3'
            },
            {
                name: 'Turkey',
                data: m.get("Turkey")[0],
                type: 'line',
                color: '#FE6F00'
            },
            {
                name: 'Belgium',
                data: m.get("Belgium")[0],
                type: 'line',
                color: '#950000'
            },
            {
                name: 'South Korea',
                data: m.get("S. Korea")[0],
                type: 'line',
                color: '#3aa287'
            },
            {
                name: 'India',
                data: m.get("India")[0],
                type: 'line',
                color: '#a75c5c'
            }
            ]
        });
    }


    function drawWorldDeathChart(m, date) {
        Highcharts.chart('worlddeathsgraph', {
            plotOptions: {
                series: {
                    pointStart: date,
                    pointInterval: 1000 * 3600 * 24,
                    marker: {
                        enabled: false
                    }
                }
            },
            title: {
                text: 'Deaths'
            },
            xAxis: {
                type: 'datetime',
                title: {
                    text: 'Last 40 days'
                }
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
                name: 'USA',
                data: m.get("USA")[1],
                type: 'line',
                color: '#4830ff'
            },
            {
                name: 'Spain',
                data: m.get("Spain")[1],
                type: 'line',
                color: '#ff0000'
            },
            {
                name: 'Italy',
                data: m.get("Italy")[1],
                type: 'line',
                color: '#29ff00'
            },
            {
                name: 'Germany',
                data: m.get("Germany")[1],
                type: 'line',
                color: '#d9c630'
            },
            {
                name: 'France',
                data: m.get("France")[1],
                type: 'line',
                color: '#969aff'
            },
            {
                name: 'China',
                data: m.get("China")[1],
                type: 'line',
                color: '#000000'
            },
            {
                name: 'Iran',
                data: m.get("Iran")[1],
                type: 'line',
                color: '#b698d3'
            },
            {
                name: 'UK',
                data: m.get("UK")[1],
                type: 'line',
                color: '#FF00F3'
            },
            {
                name: 'Turkey',
                data: m.get("Turkey")[1],
                type: 'line',
                color: '#FE6F00'
            },
            {
                name: 'Belgium',
                data: m.get("Belgium")[1],
                type: 'line',
                color: '#950000'
            },
            {
                name: 'South Korea',
                data: m.get("S. Korea")[1],
                type: 'line',
                color: '#3aa287'
            },
            {
                name: 'India',
                data: m.get("India")[1],
                type: 'line',
                color: '#a75c5c'
            }
            ]
        });
    }



    function drawWorldRecoveredChart(m, date) {
        Highcharts.chart('worldrecoveredgraph', {
            plotOptions: {
                series: {
                    pointStart: date,
                    pointInterval: 1000 * 3600 * 24,
                    marker: {
                        enabled: false
                    }
                }
            },
            title: {
                text: 'Recovered'
            },
            xAxis: {
                type: 'datetime',
                title: {
                    text: 'Last 40 days'
                }
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
                name: 'USA',
                data: m.get("USA")[2],
                type: 'line',
                color: '#4830ff'
            },
            {
                name: 'Spain',
                data: m.get("Spain")[2],
                type: 'line',
                color: '#ff0000'
            },
            {
                name: 'Italy',
                data: m.get("Italy")[2],
                type: 'line',
                color: '#29ff00'
            },
            {
                name: 'Germany',
                data: m.get("Germany")[2],
                type: 'line',
                color: '#d9c630'
            },
            {
                name: 'France',
                data: m.get("France")[2],
                type: 'line',
                color: '#969aff'
            },
            {
                name: 'China',
                data: m.get("China")[2],
                type: 'line',
                color: '#000000'
            },
            {
                name: 'Iran',
                data: m.get("Iran")[2],
                type: 'line',
                color: '#b698d3'
            },
            {
                name: 'UK',
                data: m.get("UK")[2],
                type: 'line',
                color: '#FF00F3'
            },
            {
                name: 'Turkey',
                data: m.get("Turkey")[2],
                type: 'line',
                color: '#FE6F00'
            },
            {
                name: 'Belgium',
                data: m.get("Belgium")[2],
                type: 'line',
                color: '#950000'
            },
            {
                name: 'South Korea',
                data: m.get("S. Korea")[2],
                type: 'line',
                color: '#3aa287'
            },
            {
                name: 'India',
                data: m.get("India")[2],
                type: 'line',
                color: '#a75c5c'
            }
            ]
        });
    }
});




/* $(document).ready(
    function () {
        $.ajax({
            url: "/getseries",
            success: function (result) {

                var dailyConfirmed = [];
                var dailyDeceased = [];
                var dailyRecovered = [];

                var totalConfirmed = [];
                var totalDeceased = [];
                var totalRecovered = [];


                Object.keys(result).forEach(
                    function (key) {

                        dailyConfirmed.push([new Date(result[key].date + '2020'), parseInt(result[key].dailyConfirmed)]);

                        dailyDeceased.push([new Date(result[key].date + '2020'), parseInt(result[key].dailyDeceased)]);

                        dailyRecovered.push([new Date(result[key].date + '2020'), parseInt(result[key].dailyRecovered)]);

                        totalConfirmed.push([new Date(result[key].date + '2020'), parseInt(result[key].totalConfirmed)]);

                        totalDeceased.push([new Date(result[key].date + '2020'), parseInt(result[key].totalDeceased)]);

                        totalRecovered.push([new Date(result[key].date + '2020'), parseInt(result[key].totalRecovered)]);
                    });

                drawDailyCasesChart(dailyConfirmed, dailyDeceased, dailyRecovered);
                drawTotalCasesChart(totalConfirmed, totalDeceased, totalRecovered);

            }
        });
    }); */


$.getJSON('https://api.covid19india.org/data.json',
    function (result) {

        var dailyConfirmed = [];
        var dailyDeceased = [];
        var dailyRecovered = [];

        var totalConfirmed = [];
        var totalDeceased = [];
        var totalRecovered = [];

        var cts = result.cases_time_series;


        for (x in cts) {

            var date_i = new Date(cts[x].date + '2020');
            var date_str = date_i.getFullYear() + '-' + (date_i.getMonth() + 1) + '-' + date_i.getDate()
            var today = new Date();
            var today_str = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();

            if (date_str != today_str) {

                dailyConfirmed.push([date_i, parseInt(cts[x].dailyconfirmed)]);

                dailyDeceased.push([date_i, parseInt(cts[x].dailydeceased)]);

                dailyRecovered.push([date_i, parseInt(cts[x].dailyrecovered)]);

                totalConfirmed.push([date_i, parseInt(cts[x].totalconfirmed)]);

                totalDeceased.push([date_i, parseInt(cts[x].totaldeceased)]);

                totalRecovered.push([date_i, parseInt(cts[x].totalrecovered)]);
            }
        }

        drawDailyCasesChart(dailyConfirmed, dailyDeceased, dailyRecovered);
        drawTotalCasesChart(totalConfirmed, totalDeceased, totalRecovered);
        /* drawpie1();
        drawpie2();
        drawpie3(); */
    });



function drawDailyCasesChart(dailyConfirmed, dailyDeceased, dailyRecovered) {
    Highcharts.chart('dailyseriesgraph', {
        plotOptions: {
            series: {

                pointStart: Date.UTC(2020, 0, 30),
                pointInterval: 1000 * 3600 * 24
            }
        },
        title: {
            text: 'Daily Cases'
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




function drawTotalCasesChart(totalConfirmed, totalDeceased, totalRecovered) {
    Highcharts.chart('totalseriesgraph', {
        plotOptions: {
            series: {

                pointStart: Date.UTC(2020, 0, 30),
                pointInterval: 1000 * 3600 * 24
            }
        },
        title: {
            text: 'Total Cases'
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
            data: totalConfirmed,
            type: 'line',
            color: '#4830ff'
        },
        {
            name: 'Deceased',
            data: totalDeceased,
            type: 'line',
            color: '#ff0000'
        },
        {
            name: 'Recovered',
            data: totalRecovered,
            type: 'line',
            color: '#29ff00'
        }]
    });
}

/* function drawpie1() {
    Highcharts.chart('pie1', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Browser market shares in January, 2018'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Chrome',
                y: 61.41,
                sliced: true,
                selected: true
            }, {
                name: 'Internet Explorer',
                y: 11.84
            }, {
                name: 'Firefox',
                y: 10.85
            }, {
                name: 'Edge',
                y: 4.67
            }, {
                name: 'Safari',
                y: 4.18
            }, {
                name: 'Sogou Explorer',
                y: 1.64
            }, {
                name: 'Opera',
                y: 1.6
            }, {
                name: 'QQ',
                y: 1.2
            }, {
                name: 'Other',
                y: 2.61
            }]
        }]
    });
}

function drawpie2() {
    Highcharts.chart('pie2', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Browser market shares in January, 2018'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Chrome',
                y: 61.41,
                sliced: true,
                selected: true
            }, {
                name: 'Internet Explorer',
                y: 11.84
            }, {
                name: 'Firefox',
                y: 10.85
            }, {
                name: 'Edge',
                y: 4.67
            }, {
                name: 'Safari',
                y: 4.18
            }, {
                name: 'Sogou Explorer',
                y: 1.64
            }, {
                name: 'Opera',
                y: 1.6
            }, {
                name: 'QQ',
                y: 1.2
            }, {
                name: 'Other',
                y: 2.61
            }]
        }]
    });
}

function drawpie3() {
    Highcharts.chart('pie3', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Browser market shares in January, 2018'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Chrome',
                y: 61.41,
                sliced: true,
                selected: true
            }, {
                name: 'Internet Explorer',
                y: 11.84
            }, {
                name: 'Firefox',
                y: 10.85
            }, {
                name: 'Edge',
                y: 4.67
            }, {
                name: 'Safari',
                y: 4.18
            }, {
                name: 'Sogou Explorer',
                y: 1.64
            }, {
                name: 'Opera',
                y: 1.6
            }, {
                name: 'QQ',
                y: 1.2
            }, {
                name: 'Other',
                y: 2.61
            }]
        }]
    });
} */