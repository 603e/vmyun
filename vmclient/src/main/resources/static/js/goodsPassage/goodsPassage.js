var rowNumber = 0;
$(document).ready(function(){
    load();
    queryGoods();
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
                    formatter:function(value,row){
                        return row.goods;
                    },
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
                        return "<input type='button' name=\"opera\" value='操作' onClick=\"editRow('" + row + "','" + index + "')\" >";
                    }
				}
	              ]],
	    rownumbers: true,
	    singleSelect: true,
	    rowStyler: function(index, row) {
	        return 'background-color:#FFFFFF';
	    },onEndEdit: function(rowIndex, rowData){
            var ed = $('#myTable').datagrid('getEditor', { index: rowIndex, field: 'goods' });
            if (ed != null) {
                var goods = $(ed.target).combobox('getText');
                $('#myTable').datagrid('getRows')[rowIndex]['goods'] = goods;
            }
        },
	    toolbar: [{
	            text: '添加数据',
	            iconCls: 'icon-add',
	            handler: function () {
	                $("#myTable").datagrid("insertRow", {
	                    index: rowNumber,
	                    row: {}
	                });
	                $("#myTable").datagrid("beginEdit", rowNumber);
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
                        // $("#myTable").datagrid('endEdit',i);
                        var ed = $('#myTable').datagrid('getEditor', { index: i, field: 'goods' });  //editIndex编辑时记录下的行号
                        if (ed != null) {
                            var goods = $(ed.target).combobox('getText');
                            var goodsId = $(ed.target).combobox('getValue');
                            // $('#myTable').datagrid('getRows')[i]['goodsId'] = goodsId;
                            if(goods!=goodsId){
                                rows[i].goodsId=goodsId;
                            }
                            $('#myTable').datagrid('getRows')[i]['goods'] = goods;
                        }
                        $('#myTable').datagrid('endEdit', i);
					}
                    var rows = $("#myTable").datagrid("getRows");
                    var data={};
                    rows=JSON.stringify(rows);
                    data.rows=rows;
                    $.ajax({
                        type:"POST",
						data:data,
                        url :'http://'+base+'/admin/goods/addGoodsPassage',
                        success:function(resultMsg){
                        	if(resultMsg.retCode){
                                $("#myTable").datagrid('reload');
                                $.messager.alert('提示信息','保存成功');
                                $("#myTable").datagrid('reload');
							}else{
                                $("#myTable").datagrid('reload');
                                $.messager.alert('提示信息','保存失败');

							}
                        }
                    });
                }
            },
            {
                text: '全部操作',
                iconCls: 'icon-save',
                handler: function () {
                    var rows = $("#myTable").datagrid("getRows");
                    var data={};
                    rows=JSON.stringify(rows);
                    data.rows=rows;
                    $.ajax({
                        type:"POST",
                        data:data,
                        url :'http://'+base+'/admin/goods/serialOperation',
                        success:function(result){
                            if(result.retCode=="000000"){
                                $.messager.alert('提示信息','出货成功');
                            }else{
                                $.messager.alert('提示信息','出货失败');

                            }
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
                rowNumber=rowNumber+1;
                $("#myTable").datagrid("insertRow", {
                    index: i,
                    row: data
                });
            }
        }
    });
}

function editRow(row,index) {
    var base=window.location.host;
    var rows = $('#myTable').datagrid('getData').rows[index];
    var data={};
    rows=JSON.stringify(rows);
    data.rows=rows;
    $.ajax({
        type:"POST",
        url :'http://'+base+'/admin/goods/serialOperation',
        data:data,
        success:function(result){
            if(result.retCode=="000000"){
                $.messager.alert('提示信息','出货成功');
            }else{
                $.messager.alert('提示信息','出货失败');

            }
        }
    });
}