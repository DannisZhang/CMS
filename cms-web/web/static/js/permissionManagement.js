/**
 * Created by deng.zhang on 2014/11/19.
 */
var deletingPermissionId = -1;
$(function () {
    initPermissionDatagrid();
    initPermissionDialog();

    $("#deletePermissionBtn").click(function () {
        var rows = $('#permissionDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../permission/deletePermissionsByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#permissionDatagrid').datagrid('reload');
        });
    });
});

function initPermissionDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "code", title: "权限编码", align: "center", width: 100, fixed: true},
            {field: "name", title: "权限名称", align: "center", width: 120, fixed: true},
            {field: "createdBy", title: "创建者", align: "center", width: 100, fixed: true},
            {field: "createdOn", title: "创建时间", align: "center", width: 120, fixed: true},
            {field: "description", title: "权限描述", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewPermissionDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editPermission(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deletePermissionById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加权限',
        iconCls:'icon-add',
        handler:function() {
            addPermission();
        }
    },'-',{
        text:'删除权限',
        iconCls:'icon-remove',
        handler:function(){
            alert("删除权限");
        }
    }];

    $("#permissionDatagrid").datagrid({
        url: "permission/queryByPage.json",
        pagination: true,
        pageSize: 15,
        pageList: [10, 15, 20],
        loadMsg: '数据加载中...',
        columns: columns,
        fitColumns: true,
        toolbar: toolbar,
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

function initPermissionDialog() {
    $.parser.parse("#permissionManagement");
    $("#editPermissionDialog").dialog({
        title:"添加权限",
        width:500,
        height:450,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#permissionDialogButtons"
    });

    $("#deletePermissionDialog").dialog({
        title: "删除权限",
        width: 320,
        height: 150,
        modal: true,
        closed: true,
        resizable: false,
        buttons: [
            {
                text: '是的', iconCls: 'icon-ok', handler: function () {
                $.ajax({
                    url: "../permission/deletePermissionById.json",
                    data: {deptId: deletingPermissionId},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $("#permissionDatagrid").datagrid("reload");
                    },
                    error: function (result) {
                        alert("删除失败");
                    }
                });
                $("#deletePermissionDialog").dialog("close");
            }
            },
            {
                text: '不是', iconCls: 'icon-no', handler: function () {
                $("#deletePermissionDialog").dialog("close");
            }
            }
        ]
    });
}

function addPermission() {
    clearEditPermissionForm();
    $("#editPermissionDialog").dialog({title:"添加权限"}).dialog("open");
}

function viewPermissionDetail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function clearEditPermissionForm() {
    $("#editPermissionDialog").find("#editPermissionFrom").form("clear");
}

function deletePermissionById(event, deptId) {
    event.stopPropagation();
    deletingPermissionId = deptId;
    $("#deletePermissionDialog").dialog("open");
}

function savePermission() {
    var $editPermissionDialog = $("#editPermissionDialog");

    var permissionId = $editPermissionDialog.find("input[name='permissionId']").val();
    var url = "permission/add.json";
    if (permissionId != '') {
        url = "permission/update.json";
    }

    $editPermissionDialog.find("#editPermissionFrom").form("submit",{
        url:url,
        success: function (result) {
            try {
                var jsonResult = $.parseJSON(result);
                if (jsonResult.status = 0) {
                    $.messager.alert("提示信息",jsonResult.message);
                    $('#permissionDatagrid').datagrid('reload');
                } else {
                    $.messager.alert("提示信息",jsonResult.message,"warning");
                }
                $.messager.alert("提示信息",jsonResult.message);
            } catch (e) {
                $.messager.alert("系统异常","系统发生异常","warning");
            }
            clearEditPermissionForm();
            $editPermissionDialog.dialog('close');
        }
    });
}

function editPermission(event, deptId) {
    event.stopPropagation();
    clearEditPermissionForm();
    var $editPermissionDialog = $("#editPermissionDialog").dialog({title:"修改权限"});
    $.ajax({
        url:"permission/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var permission = result.data;
                $editPermissionDialog.find("#editPermissionFrom").form('load',{
                    cnName:permission.cnName,
                    enName:permission.enName,
                    parent:permission.parent ? permission.parent.id : '',
                    permissionManager:permission.manager ? permission.manager.id : '',
                    location:permission.location,
                    establishedDate:permission.establishedDate,
                    remark:permission.remark,
                    permissionId:permission.id
                });
            }
        }
    });
    $editPermissionDialog.dialog("open");
}