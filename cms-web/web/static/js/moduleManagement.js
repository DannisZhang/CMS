/**
 * Created by deng.zhang on 2014/11/19.
 */
var deletingModuleId = -1;
$(function () {
    initModuleDatagrid();
    initModuleDialog();

    $("#deleteModuleBtn").click(function () {
        var rows = $('#moduleDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../module/deleteModulesByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#moduleDatagrid').datagrid('reload');
        });
    });
});

function initModuleDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "code", title: "模块编码", align: "center", width: 100, fixed: true},
            {field: "name", title: "模块名称", align: "center", width: 120, fixed: true},
            {field: "createdBy", title: "创建者", align: "center", width: 100, fixed: true},
            {field: "createdOn", title: "创建时间", align: "center", width: 120, fixed: true},
            {field: "description", title: "模块描述", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewModuleDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editModule(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteModuleById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加模块',
        iconCls:'icon-add',
        handler:function() {
            addModule();
        }
    },'-',{
        text:'删除模块',
        iconCls:'icon-remove',
        handler:function(){
            alert("删除模块");
        }
    }];

    $("#moduleDatagrid").datagrid({
        url: "module/queryByPage.json",
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

function initModuleDialog() {
    $.parser.parse("#moduleManagement");
    $("#editModuleDialog").dialog({
        title:"添加模块",
        width:500,
        height:450,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#moduleDialogButtons"
    });

    $("#deleteModuleDialog").dialog({
        title: "删除模块",
        width: 320,
        height: 150,
        modal: true,
        closed: true,
        resizable: false,
        buttons: [
            {
                text: '是的', iconCls: 'icon-ok', handler: function () {
                $.ajax({
                    url: "../module/deleteModuleById.json",
                    data: {deptId: deletingModuleId},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $("#moduleDatagrid").datagrid("reload");
                    },
                    error: function (result) {
                        alert("删除失败");
                    }
                });
                $("#deleteModuleDialog").dialog("close");
            }
            },
            {
                text: '不是', iconCls: 'icon-no', handler: function () {
                $("#deleteModuleDialog").dialog("close");
            }
            }
        ]
    });
}

function addModule() {
    clearEditModuleForm();
    $("#editModuleDialog").dialog({title:"添加模块"}).dialog("open");
}

function viewModuleDetail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function clearEditModuleForm() {
    $("#editModuleDialog").find("#editModuleFrom").form("clear");
}

function deleteModuleById(event, deptId) {
    event.stopPropagation();
    deletingModuleId = deptId;
    $("#deleteModuleDialog").dialog("open");
}

function saveModule() {
    var $editModuleDialog = $("#editModuleDialog");

    var moduleId = $editModuleDialog.find("input[name='moduleId']").val();
    var url = "module/add.json";
    if (moduleId != '') {
        url = "module/update.json";
    }

    $editModuleDialog.find("#editModuleFrom").form("submit",{
        url:url,
        success: function (result) {
            try {
                var jsonResult = $.parseJSON(result);
                if (jsonResult.status = 0) {
                    $.messager.alert("提示信息",jsonResult.message);
                    $('#moduleDatagrid').datagrid('reload');
                } else {
                    $.messager.alert("提示信息",jsonResult.message,"warning");
                }
                $.messager.alert("提示信息",jsonResult.message);
            } catch (e) {
                $.messager.alert("系统异常","系统发生异常","warning");
            }
            clearEditModuleForm();
            $editModuleDialog.dialog('close');
        }
    });
}

function editModule(event, deptId) {
    event.stopPropagation();
    clearEditModuleForm();
    var $editModuleDialog = $("#editModuleDialog").dialog({title:"修改模块"});
    $.ajax({
        url:"module/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var module = result.data;
                $editModuleDialog.find("#editModuleFrom").form('load',{
                    cnName:module.cnName,
                    enName:module.enName,
                    parent:module.parent ? module.parent.id : '',
                    moduleManager:module.manager ? module.manager.id : '',
                    location:module.location,
                    establishedDate:module.establishedDate,
                    remark:module.remark,
                    moduleId:module.id
                });
            }
        }
    });
    $editModuleDialog.dialog("open");
}