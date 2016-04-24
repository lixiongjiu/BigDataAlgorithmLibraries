var language = {
    "sProcessing": "处理中...",
    "sLengthMenu": "显示 _MENU_ 项结果",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "搜索:",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页"
    },
    "oAria": {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
};

var aoColumns = [
    {
        "title": "用户id",
        "data": 'userId',
        "sortable": false,
        "visible": false,
    },
    {
        "title": "用户名",
        "data": 'userName',
        "sortable": true,
        /*"fnCreatedCell": filter_Inputs*/
    },
    {
        "title": "密码",
        "data": "password",
        "sortable": true,
        /*"fnCreatedCell": filter_Inputs*/
    },
    {
        "title": "是否为管理员",
        "mData": 'isAdmin',
        "bSortable": false,
        "fnCreatedCell": test
    },
    {
        "title": "编辑",
        "mData": null,
        "bSortable": false,
        "fnCreatedCell": operation
    }
];

var oSettings = {
    "paging": true,
    "searching": true,
    "lengthChange": true,
    "pagingType":"full",
    "language": language,// 国际化语言风格
    "ajax": "/user/getList",// 数据源
   /* "bFilter": true,// 是否允许搜索*/
    "autoWidth": false,// 是否允许datatables自动改变表单宽度
    "bLengthChange": true,// 是否允许用户改变表格每页显示的记录数
    "processing": false,// 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个
    "aoColumns": aoColumns,
    "createdRow": function ( row, data, index) {
        if ( data[2]==true ) {
            $('td', row).css("color","red");
            $('td',row).eq(2).text("是");
        }
        else {
            $('td',row).css("color","green");
            $('td',row).eq(2).text("否");
        }
    }

// 页面显示
};

function test(nTd, sData, oData, iRow, iCol){
    /*var api=this.api();
    var data=api.row(iRow).data();
    alert(data(0));*/
}
function filter_Inputs(nTd, sData, oData, iRow, iCol) {
    /*if (iRow == 0) {
     var api = this.api();
     var column = api.column(iCol);
     var title = $('#datatables thead th').eq(column.index()).text();
     $(
     '<input type="text" class="datatablesInputs" placeholder="'
     + title + '" ></input>').appendTo(
     $(column.header()).empty()).on('keyup change', function() {
     column.search($(this).val()).draw();
     });
     }*/
}

function operation(nTd, data, full, irow) {
    var opers = [];
    opers.push("<a href='/user/modify'>修改</a>");
    opers.push("<a href='/user/delete?id='+data.userId>删除</a>");
    $(nTd).html(opers.join("&nbsp;"));
}

$(function () {
    $('#tb_users').DataTable(oSettings);
    // 隐藏datatables默认搜索框
    $("#tb_users_filter").display(true);

});