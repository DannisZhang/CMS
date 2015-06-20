/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
$(function () {
    initCarLevelDatagrid();
    initCarLevelDialog();
});

function initCarLevelDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "name", title: "级别名称", align: "center", width: 120, fixed: true},
            {field: "englishName", title: "英文名称", align: "center", width: 100, fixed: true},
            {field: "remark", title: "备注", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var edit = '<a class="datagrid-edit-button" onclick="editCarLevel(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteCarLevelById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text: '添加级别',
        iconCls: 'icon-add',
        handler: function () {
            addCarLevel();
        }
    }, '-', {
        text: '删除级别',
        iconCls: 'icon-remove',
        handler: function () {
            deleteCarLevels();
        }
    }];

    $("#carLevelDatagrid").datagrid({
        url: "carLevel/queryByPage.json",
        pagination: true,
        pageSize: 15,
        pageList: [10, 15, 20],
        loadMsg: '数据加载中...',
        columns: columns,
        fitColumns: true,
        toolbar: toolbar,
        onLoadSuccess: function () {
            var $editButton = $('.datagrid-edit-button');
            $editButton.linkbutton({plain: false});
            $editButton.addClass("c8");
            var $deleteButton = $('.datagrid-delete-button');
            $deleteButton.linkbutton({plain: false});
            $deleteButton.addClass("c5");
        }
    });
}

function initCarLevelDialog() {
    $.parser.parse("#carLevelManagementPage");
    $("#editCarLevelDialog").dialog({
        iconCls: "icon-edit",
        title: "添加汽车级别",
        width: 580,
        height: 480,
        closed: true,
        cache: false,
        modal: true,
        buttons: "#carLevelDialogButtons"
    });
}

function addCarLevel() {
    clearEditCarLevelForm();
    $("#editCarLevelDialog").dialog({title: "添加级别"}).dialog("open");
}

function clearEditCarLevelForm() {
    $("#editCarLevelDialog").find("#editCarLevelFrom").form("clear");
}

function deleteCarLevelById(event, carLevelId) {
    event.stopPropagation();
    $.messager.confirm("确认删除", "请确认是否删除该级别？", function (r) {
        if (r) {
            $.ajax({
                url: "carLevel/deleteById.json",
                method: "post",
                data: {"id": carLevelId},
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("删除成功", result.message);
                        $('#carLevelDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert("删除失败", result.message, "error");
                    }
                }
            })
        }
    });
}

function deleteCarLevels() {
    var rows = $('#carLevelDatagrid').datagrid('getChecked');
    if (rows.length == 0) {
        $.messager.alert("提示信息", "请勾选将要删除的汽车级别", "warning");
        return;
    }
    $.messager.confirm("确认删除", "请确认是否删除汽车级别？", function (r) {
        if (r) {
            var ids = [];
            $.each(rows, function (i, row) {
                ids.push(row.id);
            });
            $.ajax({
                url: "carLevel/deleteByIds.json",
                method: "post",
                data: {"ids": ids.join(",")},
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("删除成功", result.message);
                        $('#carLevelDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert("删除失败", result.message, "error");
                    }
                }
            })
        }
    });
}

function saveCarLevel() {
    var $editCarLevelDialog = $("#editCarLevelDialog");
    var $editCarLevelFrom = $editCarLevelDialog.find("#editCarLevelFrom");
    $editCarLevelFrom.form("submit", {
        url: "carLevel/save.json",
        onSubmit: function () {
            //check
        },
        success: function (result) {
            var jsonResult = $.parseJSON(result);
            if (jsonResult.success) {
                $.messager.alert("添加成功", jsonResult.message);
                $editCarLevelDialog.dialog('close');
                $('#carLevelDatagrid').datagrid('reload');
            } else {
                $.messager.alert("添加失败", jsonResult.message, "error");
            }
        }
    });
}

function editCarLevel(event, carLevelId) {
    event.stopPropagation();
    clearEditCarLevelForm();
    var $editCarLevelDialog = $("#editCarLevelDialog").dialog({title: "修改汽车级别"});
    $.ajax({
        url: "carLevel/queryById.json",
        method: "post",
        data: {id: carLevelId},
        dataLevel: "json",
        success: function (result) {
            if (result && result.success && result.data) {
                var carLevel = result.data;
                $editCarLevelDialog.find("#editCarLevelFrom").form('load', {
                    name: carLevel.name,
                    englishName: carLevel.englishName,
                    remark: carLevel.remark,
                    id: carLevel.id
                });
            }
        }
    });
    $editCarLevelDialog.dialog("open");
}