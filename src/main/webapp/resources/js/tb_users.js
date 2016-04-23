var language = {
	"sProcessing" : "处理中...",
	"sLengthMenu" : "显示 _MENU_ 项结果",
	"sZeroRecords" : "没有匹配结果",
	"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
	"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
	"sInfoPostFix" : "",
	"sSearch" : "搜索:",
	"sUrl" : "",
	"sEmptyTable" : "表中数据为空",
	"sLoadingRecords" : "载入中...",
	"sInfoThousands" : ",",
	"oPaginate" : {
		"sFirst" : "首页",
		"sPrevious" : "上页",
		"sNext" : "下页",
		"sLast" : "末页"
	},
	"oAria" : {
		"sSortAscending" : ": 以升序排列此列",
		"sSortDescending" : ": 以降序排列此列"
	}
};

var aoColumns = [ 
{
	"title" : "用户id",
	"mData" : 'userId',
	"bSortable" : false,
	 "visible": false,
	"fnCreatedCell" : filter_Inputs
}, 
{
	"title" : "用户名",
	"mData" : 'userName',
	"bSortable" : false,
	"fnCreatedCell" : filter_Inputs
}, 
{
	"title" : "是否为管理员",
	"mData" : 'isAdmin',
	"bSortable" : false,
	"fnCreatedCell" : filter_Inputs
}, 
{
	"title" : "编辑",
	"mData" : null,
	"bSortable" : false,
	"fnCreatedCell" : operation
}
];

var oSettings = {
	"language" : language,// 国际化语言风格
	"sAjaxSource" :  'ajList' ,// 数据源
	"bFilter" : true,// 是否允许搜索
	"ajax":"/user/list",
	"bAutoWidth" : false,// 是否允许datatables自动改变表单宽度
	"bLengthChange" : false,// 是否允许用户改变表格每页显示的记录数
	"bProcessing" : false,// 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个
	"aoColumns" : aoColumns
// 页面显示
};

function filter_Inputs(nTd, sData, oData, iRow, iCol) {
	if (iRow == 0) {
		var api = this.api();
		var column = api.column(iCol);
		var title = $('#datatables thead th').eq(column.index()).text();
		$(
				'<input type="text" class="datatablesInputs" placeholder="'
						+ title + '" ></input>').appendTo(
				$(column.header()).empty()).on('keyup change', function() {
			column.search($(this).val()).draw();
		});
	}
}

function operation(nTd, data, full, irow) {
	var opers = [];
	opers.push("<a>修改</a>");
	opers.push("<a>删除</a>");
	$(nTd).html(opers.join("&nbsp;"));
}

$(function() {
	$('#tb_users').DataTable(oSettings);
	// 隐藏datatables默认搜索框
	$("#datatables_filter").css("display", "none");
	
});