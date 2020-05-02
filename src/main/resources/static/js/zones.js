$(document).ready(function () {
    $('#zoneslist').dataTable({
        "bInfo": false,
        "paging": true,
        "lengthMenu": [[15, 30, 45, -1], [15, 30, 45, "All"]],
        /* "lengthMenu": [15, 30, 45, ], */
        "responsive": true,
        language: {
            searchPlaceholder: "Search area",
            search: ""
        },
        "order": [],
        'columnDefs': [{
            'targets': [3], /* column index */
            'orderable': false, /* true or false */

        }]
    });

    $('div.dataTables_filter input').addClass('form-control');
});