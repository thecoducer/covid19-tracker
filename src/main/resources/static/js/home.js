$(document).ready(
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

                /* console.log(dailyConfirmed);
                console.log(dailyDeceased);
                console.log(dailyRecovered); */

                drawDailyCasesChart(dailyConfirmed, dailyDeceased, dailyRecovered);
                drawTotalCasesChart(totalConfirmed, totalDeceased, totalRecovered);

            }
        });
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
    ]);

    $("#table_id").on('click', 'tr', function () {
        var tdValue = $(this).children(':first').text();
        $("#modaltitle").text(tdValue);
        var id = m.get(tdValue);
        $("#district_table").find("tbody").not("#" + id).hide();
        $("#" + id).show();
    });
});