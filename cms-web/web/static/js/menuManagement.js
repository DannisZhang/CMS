/**
 * Created by deng.zhang on 2014/11/19.
 */
$(function () {
    initMenuDatagrid();
    initMenuDialog();

    $("#deleteMenuBtn").click(function () {
        var rows = $('#menuDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../menu/deleteMenusByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#menuDatagrid').datagrid('reload');
        });
    });
});

function initMenuDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "code", title: "菜单编码", align: "center", width: 100, fixed: true},
            {field: "name", title: "菜单名称", align: "center", width: 120, fixed: true},
            {field: "remark", title: "备注信息", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewMenuDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editMenu(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteMenuByCode(event,\'' + row.code + '\')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加菜单',
        iconCls:'icon-add',
        handler:function() {
            addMenu();
        }
    },'-',{
        text:'批量删除',
        iconCls:'icon-remove',
        handler:function(){
            deleteMenuByCodeList();
        }
    }];

    $("#menuDatagrid").datagrid({
        title:'菜单列表',
        url: "menu/findByPage.json",
        pagination: true,
        pageSize: 15,
        pageList: [10, 15, 20],
        loadMsg: '数据加载中...',
        columns: columns,
        fitColumns: true,
        toolbar: '#menuDatagridToolbar',
        onLoadSuccess: function () {
            var $detailButton = $('.datagrid-detail-button');
            $detailButton.linkbutton({plain: false});
            $detailButton.addClass("c1");
            var $editButton = $('.datagrid-edit-button');
            $editButton.linkbutton({plain: false});
            $editButton.addClass("c8");
            var $deleteButton = $('.datagrid-delete-button');
            $deleteButton.linkbutton({plain: false});
            $deleteButton.addClass("c5");
        }
    });
}

function initMenuDialog() {
    $.parser.parse("#menuManagement");
    $("#editMenuDialog").dialog({
        title:"添加菜单",
        width:500,
        height:450,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#menuDialogButtons"
    });
}

function addMenu() {
    clearEditMenuForm();
    $("#editMenuDialog").dialog({title:"添加菜单"}).dialog("open");
}

function deleteMenuByCodeList() {
    var checkedRows = $("#menuDatagrid").datagrid("getChecked");
    if (checkedRows.length == 0) {
        $.messager.alert("提示","请至少选择一个菜单");
    } else {
        $.messager.confirm("确认删除","请确认是否删除已选菜单？", function (r) {
            if (r) {
                var codeList = [];
                $.each(checkedRows, function (i, row) {
                    codeList.push(row.code);
                });
                $.post("menu/deleteByCodes.json",{codes:codeList.join(",")}, function (result) {
                    if (result.code == 0) {
                        $.messager.alert("提示","删除成功！");
                        $("#menuDatagrid").datagrid("reload");
                    } else {
                        $.messager.alert("提示","删除失败！","warning");
                    }
                });
            }
        });
    }
}

function viewMenuDetail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function clearEditMenuForm() {
    $("#editMenuDialog").find("#editMenuFrom").form("clear");
}

function deleteMenuByCode(event, menuCode) {
    event.stopPropagation();
    $.messager.confirm("确认删除","请确认是否删除？", function (r) {
        if (r) {
            $.ajax({
                url:"menu/delete.json",
                method:"post",
                data:{"code" : menuCode},
                success: function (result) {
                    if (result.code == 0) {
                        $.messager.alert("删除成功","删除菜单成功！");
                        $("#menuDatagrid").datagrid("reload");
                    } else {
                        $.messager.alert("删除失败","抱歉，删除菜单失败！","error");
                    }
                }
            })
        }
    });
}

function saveMenu() {
    var $editMenuDialog = $("#editMenuDialog");

    var menuId = $editMenuDialog.find("input[name='menuId']").val();
    var url = "menu/add.json";
    if (menuId != '') {
        url = "menu/update.json";
    }

    $editMenuDialog.find("#editMenuFrom").form("submit",{
        url:url,
        success: function (result) {
            try {
                var jsonResult = $.parseJSON(result);
                if (jsonResult.code == 0) {
                    $.messager.alert("提示信息",jsonResult.message);
                    $('#menuDatagrid').datagrid('reload');
                } else {
                    $.messager.alert("提示信息",jsonResult.message,"warning");
                }
            } catch (e) {
                $.messager.alert("系统异常","系统发生异常","warning");
            }
            clearEditMenuForm();
            $editMenuDialog.dialog('close');
        }
    });
}

function editMenu(event, deptId) {
    event.stopPropagation();
    clearEditMenuForm();
    var $editMenuDialog = $("#editMenuDialog").dialog({title:"修改菜单"});
    $.ajax({
        url:"menu/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var menu = result.data;
                $editMenuDialog.find("#editMenuFrom").form('load',{
                    cnName:menu.cnName,
                    enName:menu.enName,
                    parent:menu.parent ? menu.parent.id : '',
                    menuManager:menu.manager ? menu.manager.id : '',
                    location:menu.location,
                    establishedDate:menu.establishedDate,
                    remark:menu.remark,
                    menuId:menu.id
                });
            }
        }
    });
    $editMenuDialog.dialog("open");
}

function findMenuByName(menuName) {
    $.messager.alert("提示","查询菜单");
}