$(document).ready(function(){
    load();
    queryGoods();
	var rowNumber = 0;
    var base=window.location.host;
	$("#myTable").datagrid({
	    columns: [[
                {field:'id',hidden:'true'},
	            {
	                title: "上货道编号",
	                field: "number",
	                editor: {
	                    type: 'validatebox',
	                    options: {}
	                }
	            },
	            {
	                title: "行",
	                field: "vmRow",
	                editor: {
	                    type: 'validatebox',
	                    options: {}
	                }
	            },
	            {
	                title: "列",
	                field: "vmColumn",
	                editor: {
	                    type: 'validatebox',
	                    options: {}
	                }
	            },
	            {
	            	id:'dd',
	                title: "商品",
	                field: "goods",
	                editor: {
	                    type: 'combobox',
	                    options: {
							url:'http://'+base+'/admin/goods/queryGoods',
                            valueField: 'id',
                            textField: 'name',
                            editable: false
						}
	                }
	            },
				{
					title: "价格",
					field: "price",
					editor: {
						type: 'validatebox',
						options: {}
					}
				},
            	{
                title: "数量",
                field: "qty",
                editor: {
                    type: 'validatebox',
                    options: {}
                	}
            	},
				{
					title: "操作",
					field: "amount",
					formatter:function(value, row, index){
                        var str = '<a href="#" rel="external nofollow" name="opera" class="easyui-linkbutton" >操作</a>';
                        return str;
                    }
				}
	              ]],
	    rownumbers: true,
	    singleSelect: true,
	    rowStyler: function(index, row) {
	        return 'background-color:#FFFFFF';
	    },
	    toolbar: [{
	            text: '添加数据',
	            iconCls: 'icon-add',
	            handler: function () {
	                $("#myTable").datagrid("insertRow", {
	                    index: rowNumber,
	                    row: {}
	                });
	                $("#myTable").datagrid("beginEdit", 0);
                    rowNumber+1;
	            }
	         },
	        {
	            text: '编辑数据',
	            iconCls: 'icon-edit',
	            handler: function () {
	                var hasSelect = $("#myTable").datagrid("getSelections");
	                if (hasSelect.length == 1) {
	                    var row = hasSelect[0];
	                    rowNumber = $("#myTable").datagrid("getRowIndex", row);
	                    $("#myTable").datagrid("beginEdit", rowNumber);
	                }
	            }
	         },
	         {
	            text: '删除行',
	            iconCls: 'icon-save',
	            handler: function () {
                    var hasSelect = $("#myTable").datagrid("getSelections");
                    if (hasSelect.length == 1) {
                        var row = hasSelect[0];
                        rowNumber = $("#myTable").datagrid("getRowIndex", row);
                        $("#myTable").datagrid("deleteRow", rowNumber);
                    }
	            }
	       },
            {
                text: '保存数据',
                iconCls: 'icon-save',
                handler: function () {
                    var rows = $("#myTable").datagrid("getRows");
                    for(var i=0;i<rows.length;i++){
                        $("#myTable").datagrid('endEdit',i);
					}
                    var rows = $("#myTable").datagrid("getRows");
                    var data={};
                    rows=JSON.stringify(rows);
                    data.rows=rows;
                    $.ajax({
                        type:"POST",
						data:data,
                        url :'http://'+base+'/admin/goods/addGoodsPassage',
                        success:function(data){
                        	debugger;
                        }
                    });
                }
            },
            {
                text: '全部操作',
                iconCls: 'icon-save',
                handler: function () {
                    var rows = $("#myTable").datagrid("getRows");
                    getAjax('../customerController/deletCredit', rows[rowNumber], '', function(result, status) {
                        if(result.errorCode == '0000'){
                        }
                    });
                    $("#myTable").datagrid('deleteRow',rowNumber);
                }
            }]
	});
});


function queryGoods() {
    var base=window.location.host;
    $.ajax({
        type:"POST",
        url :'http://'+base+'/admin/goods/queryGoods',
        dataType :'json',
        success:function(data){
        }
    });
}
function load(){
	 var base=window.location.host;
    $.ajax({
        type:"POST",
        url :'http://'+base+'/admin/goods/list',
        dataType :'json',
        success:function(result){
            result=result.data;
            for(i=0;i<result.length;i++){
                var data=result[i];
                data.goods=data.goods.name;
                $("#myTable").datagrid("insertRow", {
                    index: i,
                    row: data
                });
            }
        }
    });
}