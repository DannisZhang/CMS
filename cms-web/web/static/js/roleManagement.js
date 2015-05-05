/**
 * Created by deng.zhang on 2014/11/19.
 */
var deletingDeptId = -1;
$(function () {
    initRoleDatagrid();
    initRoleDialog();

    $("#deleteDeptBtn").click(function () {
        var rows = $('#roleDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../role/deleteRolesByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#roleDatagrid').datagrid('reload');
        });
    });
});

function initRoleDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "code", title: "角色编码", align: "center", width: 100, fixed: true},
            {field: "name", title: "角色名称", align: "center", width: 120, fixed: true},
            {field: "createdBy", title: "创建者", align: "center", width: 100, fixed: true},
            {field: "createdOn", title: "创建时间", align: "center", width: 120, fixed: true},
            {field: "description", title: "角色描述", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewRoleDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editRole(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteDeptById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加角色',
        iconCls:'icon-add',
        handler:function() {
            addRole();
        }
    },'-',{
        text:'删除角色',
        iconCls:'icon-remove',
        handler:function(){
            alert("删除角色");
        }
    }];

    $("#roleDatagrid").datagrid({
        url: "role/queryByPage.json",
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

function initRoleDialog() {
    $.parser.parse("#roleManagement");
    $("#editRoleDialog").dialog({
        title:"添加角色",
        width:500,
        height:450,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#roleDialogButtons"
    });

    $("#deleteRoleDialog").dialog({
        title: "删除角色",
        width: 320,
        height: 150,
        modal: true,
        closed: true,
        resizable: false,
        buttons: [
            {
                text: '是的', iconCls: 'icon-ok', handler: function () {
                $.ajax({
                    url: "../role/deleteRoleById.json",
                    data: {deptId: deletingDeptId},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $("#roleDatagrid").datagrid("reload");
                    },
                    error: function (result) {
                        alert("删除失败");
                    }
                });
                $("#deleteRoleDialog").dialog("close");
            }
            },
            {
                text: '不是', iconCls: 'icon-no', handler: function () {
                $("#deleteRoleDialog").dialog("close");
            }
            }
        ]
    });
}

function addRole() {
    clearEditRoleForm();
    $("#editRoleDialog").dialog({title:"添加角色"}).dialog("open");
}

function viewRoleDetail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function clearEditRoleForm() {
    $("#editRoleDialog").find("#editRoleFrom").form("clear");
}

function deleteDeptById(event, deptId) {
    event.stopPropagation();
    deletingDeptId = deptId;
    $("#deleteRoleDialog").dialog("open");
}

function saveRole() {
    var $editRoleDialog = $("#editRoleDialog");

    var roleId = $editRoleDialog.find("input[name='roleId']").val();
    var url = "role/add.json";
    if (roleId != '') {
        url = "role/update.json";
    }

    $editRoleDialog.find("#editRoleFrom").form("submit",{
        url:url,
        success: function (result) {
            try {
                var jsonResult = $.parseJSON(result);
                if (jsonResult.status = 0) {
                    $.messager.alert("提示信息",jsonResult.message);
                    $('#roleDatagrid').datagrid('reload');
                } else {
                    $.messager.alert("提示信息",jsonResult.message,"warning");
                }
                $.messager.alert("提示信息",jsonResult.message);
            } catch (e) {
                $.messager.alert("系统异常","系统发生异常","warning");
            }
            clearEditRoleForm();
            $editRoleDialog.dialog('close');
        }
    });
}

function editRole(event, deptId) {
    event.stopPropagation();
    clearEditRoleForm();
    var $editRoleDialog = $("#editRoleDialog").dialog({title:"修改角色"});
    $.ajax({
        url:"role/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var role = result.data;
                $editRoleDialog.find("#editRoleFrom").form('load',{
                    cnName:role.cnName,
                    enName:role.enName,
                    parent:role.parent ? role.parent.id : '',
                    roleManager:role.manager ? role.manager.id : '',
                    location:role.location,
                    establishedDate:role.establishedDate,
                    remark:role.remark,
                    roleId:role.id
                });
            }
        }
    });
    $editRoleDialog.dialog("open");
}