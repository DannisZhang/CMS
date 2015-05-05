/**
 * Created by deng.zhang on 2014/11/19.
 */
var deletingDeptId = -1;
$(function () {
    initDepartmentDatagrid();
    initDepartmentDialog();

    $("#deleteDeptBtn").click(function () {
        var rows = $('#departmentDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../department/deleteDepartmentsByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#departmentDatagrid').datagrid('reload');
        });
    });
});

function initDepartmentDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "cnName", title: "中文名称", align: "center", width: 120, fixed: true},
            {field: "enName", title: "英文名称", align: "center", width: 120, fixed: true},
            {field: "code", title: "编号", align: "center", width: 100, fixed: true},
            {field: "location", title: "部门地址", align: "center", width: 130, fixed: true},
            {field: "establishedDate", title: "成立日期", align: "center", width: 120, fixed: true},
            {field: "remark", title: "备注", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewDepartmentDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editDepartment(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteDeptById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加部门',
        iconCls:'icon-add',
        handler:function() {
            addDepartment();
        }
    },'-',{
        text:'删除部门',
        iconCls:'icon-remove',
        handler:function(){
            alert("删除部门");
        }
    },'-',{
        text:'批量导入',
        handler:function(){
            alert("批量导入");
        }
    },'-',{
        text:'导出EXCEL',
        handler:function(){
            alert("导出EXCEL");
        }
    }];

    $("#departmentDatagrid").datagrid({
        url: "department/queryByPage.json",
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

function initDepartmentDialog() {
    $.parser.parse("#departmentManagement");
    $("#editDepartmentDialog").dialog({
        title:"添加部门",
        width:500,
        height:450,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#departmentDialogButtons"
    });

    $("#deleteDepartmentDialog").dialog({
        title: "删除部门",
        width: 320,
        height: 150,
        modal: true,
        closed: true,
        resizable: false,
        buttons: [
            {
                text: '是的', iconCls: 'icon-ok', handler: function () {
                $.ajax({
                    url: "../department/deleteDepartmentById.json",
                    data: {deptId: deletingDeptId},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $("#departmentDatagrid").datagrid("reload");
                    },
                    error: function (result) {
                        alert("删除失败");
                    }
                });
                $("#deleteDepartmentDialog").dialog("close");
            }
            },
            {
                text: '不是', iconCls: 'icon-no', handler: function () {
                $("#deleteDepartmentDialog").dialog("close");
            }
            }
        ]
    });
}

function addDepartment() {
    clearEditDepartmentForm();
    $("#editDepartmentDialog").dialog({title:"添加部门"}).dialog("open");
}

function viewDepartmentDetail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function clearEditDepartmentForm() {
    $("#editDepartmentDialog").find("#editDepartmentFrom").form("clear");
}

function deleteDeptById(event, deptId) {
    event.stopPropagation();
    deletingDeptId = deptId;
    $("#deleteDepartmentDialog").dialog("open");
}

function saveDepartment() {
    var $editDepartmentDialog = $("#editDepartmentDialog");

    var departmentId = $editDepartmentDialog.find("input[name='departmentId']").val();
    var url = "department/add.json";
    if (departmentId != '') {
        url = "department/update.json";
    }

    $editDepartmentDialog.find("#editDepartmentFrom").form("submit",{
        url:url,
        success: function (result) {
            try {
                var jsonResult = $.parseJSON(result);
                if (jsonResult.status = 0) {
                    $.messager.alert("提示信息",jsonResult.message);
                    $('#departmentDatagrid').datagrid('reload');
                } else {
                    $.messager.alert("提示信息",jsonResult.message,"warning");
                }
                $.messager.alert("提示信息",jsonResult.message);
            } catch (e) {
                $.messager.alert("系统异常","系统发生异常","warning");
            }
            clearEditDepartmentForm();
            $editDepartmentDialog.dialog('close');
        }
    });
}

function editDepartment(event, deptId) {
    event.stopPropagation();
    clearEditDepartmentForm();
    var $editDepartmentDialog = $("#editDepartmentDialog").dialog({title:"修改部门"});
    $.ajax({
        url:"department/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var department = result.data;
                $editDepartmentDialog.find("#editDepartmentFrom").form('load',{
                    cnName:department.cnName,
                    enName:department.enName,
                    parent:department.parent ? department.parent.id : '',
                    departmentManager:department.manager ? department.manager.id : '',
                    location:department.location,
                    establishedDate:department.establishedDate,
                    remark:department.remark,
                    departmentId:department.id
                });
            }
        }
    });
    $editDepartmentDialog.dialog("open");
}