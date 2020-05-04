$(document).ready(function () {
    $('#table_id').dataTable({
        "bInfo": false,
        "paging": false,
        "responsive": true,
        language: {
            searchPlaceholder: "Search state",
            search: ""
        },
        "order": [],
        'columnDefs': [{
            'targets': [1, 3, 4], /* column index */
            'orderable': false, /* true or false */

        }]
    });

    $('#table_id2').dataTable({
        "bInfo": false,
        "paging": true,
        "lengthMenu": [[15, 30, 45, -1], [15, 30, 45, "All"]],
        /* "lengthMenu": [15, 30, 45, ], */
        "responsive": true,
        language: {
            searchPlaceholder: "Search country",
            search: ""
        },
        "order": [],
        'columnDefs': [{
            'targets': [1, 4], /* column index */
            'orderable': false, /* true or false */

        }]
    });

    $('div.dataTables_filter input').addClass('form-control');

    $('#india_casesrate').click();
    $('#india_casesrate').click();


    var m_tbody = new Map([
        ["Andaman and Nicobar Islands", "AN_tbody"],
        ["Andhra Pradesh", "AP_tbody"],
        ["Arunachal Pradesh", "AR_tbody"],
        ["Assam", "AS_tbody"],
        ["Bihar", "BR_tbody"],
        ["Chandigarh", "CH_tbody"],
        ["Chhattisgarh", "CT_tbody"],
        ["Dadra and Nagar Haveli", "DN_tbody"],
        ["Daman and Diu", "DD_tbody"],
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
        ["Lakshadweep", "LD_tbody"],
        ["Madhya Pradesh", "MP_tbody"],
        ["Maharashtra", "MH_tbody"],
        ["Manipur", "MN_tbody"],
        ["Meghalaya", "ML_tbody"],
        ["Mizoram", "MZ_tbody"],
        ["Nagaland", "NL_tbody"],
        ["Odisha", "ORR_tbody"],
        ["Puducherry", "PY_tbody"],
        ["Punjab", "PB_tbody"],
        ["Rajasthan", "RJ_tbody"],
        ["Sikkim", "SK_tbody"],
        ["Tamil Nadu", "TN_tbody"],
        ["Telangana", "TG_tbody"],
        ["Tripura", "TR_tbody"],
        ["Uttar Pradesh", "UP_tbody"],
        ["Uttarakhand", "UT_tbody"],
        ["West Bengal", "WB_tbody"]
    ]);

    $("#table_id").on('click', 'tr', function () {
        var tdValue = $(this).children(':first').text();
        $("#modaltitle").text(tdValue);
        var id = m_tbody.get(tdValue);
        $("#district_table").find("tbody").not("#" + id).hide();
        $("#" + id).show();
    });


    /* Toggle recent updates */

    $("#india-recent-on-btn").click(function () {
        // assumes element with id='button'
        $("#india-recent-on-btn").hide();
        $("#india-recent-off-btn").show();
        $("#updates-div").show();
    });

    $("#india-recent-off-btn").click(function () {
        // assumes element with id='button'
        $("#india-recent-off-btn").hide();
        $("#india-recent-on-btn").show();
        $("#updates-div").hide();
    });


    var m = new Map();

    $.getJSON('https://corona.lmao.ninja/v2/historical/USA,Spain,Italy,Germany,France,China,Iran,UK,Turkey,Belgium,Russia,India?lastdays=30',
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

            drawWorldTotalCasesGraph(m, date);
            drawWorldDeathGraph(m, date);
            drawWorldRecoveredGraph(m, date);

        });



    function drawWorldTotalCasesGraph(m, date) {
        Highcharts.chart('worldtotalcasesgraph', {
            chart: {
                zoomType: 'x'
            },
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
                text: 'Confirmed Cases',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: 'linear scale'
            },
            xAxis: {
                type: 'datetime',
                title: {
                    text: 'Last 30 days'
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
                name: 'Russia',
                data: m.get("Russia")[0],
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


    function drawWorldDeathGraph(m, date) {
        Highcharts.chart('worlddeathsgraph', {
            chart: {
                zoomType: 'x'
            },
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
                text: 'Deaths',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: 'linear scale'
            },
            xAxis: {
                type: 'datetime',
                title: {
                    text: 'Last 30 days'
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
                name: 'Russia',
                data: m.get("Russia")[1],
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



    function drawWorldRecoveredGraph(m, date) {
        Highcharts.chart('worldrecoveredgraph', {
            chart: {
                zoomType: 'x'
            },
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
                text: 'Recovered',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: 'linear scale'
            },
            xAxis: {
                type: 'datetime',
                title: {
                    text: 'Last 30 days'
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
                name: 'Russia',
                data: m.get("Russia")[2],
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


    $.getJSON('https://corona.lmao.ninja/v2/countries/USA,Spain,Italy,Germany,France,Russia,Iran,UK,Turkey,Belgium,S.%20Korea,India',
        function (data) {

            var i
            countries = []
            testsData = []
            updatedOn = []

            for (i = 0; i < data.length; i++) {
                countries.push(data[i].country.trim())
                testsData.push(data[i].testsPerOneMillion)
                updatedOn.push(new Date(data[i].updated))
            }

            drawWorldTestsBarChart(countries, testsData, updatedOn);

        });

    function drawWorldTestsBarChart(countries, testsData, updatedOn) {
        Highcharts.chart('worldtestsbarchart', {
            chart: {
                type: 'column'
            },
            title: {
                text: 'Total tests per one million population',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: countries,
                crosshair: true
            },
            yAxis: {
                title: {
                    text: ''
                }
            },
            credits: {
                enabled: false
            },
            tooltip: {
                shared: true,
                useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '',
                data: testsData,
                color: '#d100b8',
                zIndex: 1,
                events: {
                    legendItemClick: function (e) {
                        e.preventDefault()
                    }
                }
            }]
        });
    }


    $.getJSON('https://corona.lmao.ninja/v2/continents?yesterday=false&sort=cases',
        function (data) {

            var i
            var cm = new Map();

            for (i = 0; i < data.length; i++) {
                continent = data[i].continent.trim()
                totalcases = data[i].cases
                cm.set(continent, totalcases)
            }

            drawContinentsPieChart(cm);

        });

    function drawContinentsPieChart(cm) {
        Highcharts.chart('worldcontinentscasespiechart', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie',
            },
            title: {
                text: 'Total cases per continent/region'
            },
            subtitle: {
                text: ''
            },
            tooltip: {

            },
            accessibility: {
                point: {
                    valueSuffix: ''
                }
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                pie: {
                    size: 220,
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    }
                }
            },
            series: [{
                name: 'Total cases',
                colorByPoint: true,
                data: [{
                    name: "Europe",
                    y: cm.get("Europe"),
                    color: '#ff5757'
                }, {
                    name: "North America",
                    y: cm.get("North America"),
                    color: '#7b5aff'
                }, {
                    name: "Asia",
                    y: cm.get("Asia"),
                    color: '#3aa287'
                }, {
                    name: "South America",
                    y: cm.get("South America"),
                    color: '#d9c630'
                }, {
                    name: "Africa",
                    y: cm.get("Africa"),
                    color: '#d100b8'
                }, {
                    name: "Oceania",
                    y: cm.get("Oceania"),
                    color: 'black'
                }]
            }]
        });
    }


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
     
                    drawDailyCasesGraph(dailyConfirmed, dailyDeceased, dailyRecovered);
                    drawTotalCasesLogGraph(totalConfirmed, totalDeceased, totalRecovered);
     
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
                var date_str = date_i.getFullYear() + '-' + (date_i.getMonth() + 1) + '-' + date_i.getDate();
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

            drawDailyCasesGraph(dailyConfirmed, dailyDeceased, dailyRecovered);
            drawTotalCasesUniformGraph(totalConfirmed, totalDeceased, totalRecovered);
            drawTotalCasesLogGraph(totalConfirmed, totalDeceased, totalRecovered);
        });


    function drawDailyCasesGraph(dailyConfirmed, dailyDeceased, dailyRecovered) {
        Highcharts.chart('dailyseriesgraph', {
            chart: {
                zoomType: 'x'
            },
            plotOptions: {
                series: {
                    pointStart: Date.UTC(2020, 0, 30),
                    pointInterval: 1000 * 3600 * 24
                }
            },
            title: {
                text: 'Daily Cases',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: 'linear scale'
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


    function drawTotalCasesUniformGraph(totalConfirmed, totalDeceased, totalRecovered) {
        Highcharts.chart('totalseriesuniformgraph', {
            chart: {
                zoomType: 'x'
            },
            plotOptions: {
                series: {
                    pointStart: Date.UTC(2020, 0, 30),
                    pointInterval: 1000 * 3600 * 24
                }
            },
            title: {
                text: 'Total Cases',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: 'linear scale'
            },
            xAxis: {
                type: 'datetime',
            },
            yAxis: [{
                className: 'highcharts-color-0',
                type: '',
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

    function drawTotalCasesLogGraph(totalConfirmed, totalDeceased, totalRecovered) {
        Highcharts.chart('totalserieslogarithmicgraph', {
            chart: {
                zoomType: 'x'
            },
            plotOptions: {
                series: {

                    pointStart: Date.UTC(2020, 0, 30),
                    pointInterval: 1000 * 3600 * 24
                }
            },
            title: {
                text: 'Total Cases',
                style: {
                    fontSize: '17px'
                }
            },
            subtitle: {
                text: 'logarithmic scale'
            },
            xAxis: {
                type: 'datetime',
            },
            yAxis: [{
                className: 'highcharts-color-0',
                type: 'logarithmic',
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

});