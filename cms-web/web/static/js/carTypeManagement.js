/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
$(function () {
    initCarTypeDatagrid();
    initCarTypeDialog();
});

function initCarTypeDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "name", title: "车型名称", align: "center", width: 120, fixed: true},
            {field: "englishName", title: "英文名称", align: "center", width: 100, fixed: true},
            {field: "remark", title: "备注", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewCarDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editCar(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteCarById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text: '添加车型',
        iconCls: 'icon-add',
        handler: function () {
            addCarType();
        }
    }, '-', {
        text: '删除车型',
        iconCls: 'icon-remove',
        handler: function () {
            deleteCars();
        }
    }];

    $("#carTypeDatagrid").datagrid({
        url: "carType/queryByPage.json",
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

function initCarTypeDialog() {
    $.parser.parse("#carTypeManagementPage");
    $("#editCarTypeDialog").dialog({
        iconCls: "icon-edit",
        title: "添加车型",
        width: 580,
        height: 480,
        closed: true,
        cache: false,
        modal: true,
        buttons: "#carTypeDialogButtons"
    });
}

function addCarType() {
    clearEditCarTypeForm();
    $("#editCarTypeDialog").dialog({title: "添加车型"}).dialog("open");
}

function clearEditCarTypeForm() {
    $("#editCarTypeDialog").find("#editCarTypeFrom").form("clear");
}

function deleteCarTypeById(event, carId) {
    event.stopPropagation();
    $.messager.confirm("确认删除", "请确认是否删除手机号码？", function (r) {
        if (r) {
            $.ajax({
                url: "car/deleteById.json",
                method: "post",
                data: {"id": carId},
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("删除成功", result.message);
                        $('#carDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert("删除失败", result.message, "error");
                    }
                }
            })
        }
    });
}

function deleteCarTypes() {
    var rows = $('#carDatagrid').datagrid('getChecked');
    if (rows.length == 0) {
        $.messager.alert("提示信息", "请勾选将要删除的手机号码", "warning");
        return;
    }
    $.messager.confirm("确认删除", "请确认是否删除手机号码？", function (r) {
        if (r) {
            var ids = [];
            $.each(rows, function (i, row) {
                ids.push(row.id);
            });
            $.ajax({
                url: "car/deleteByIds.json",
                method: "post",
                data: {"ids": ids.join(",")},
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("删除成功", result.message);
                        $('#carDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert("删除失败", result.message, "error");
                    }
                }
            })
        }
    });
}

function saveCarType() {
    var $editCarTypeDialog = $("#editCarTypeDialog");
    var $editCarTypeFrom = $editCarTypeDialog.find("#editCarTypeFrom");
    $editCarTypeFrom.form("submit", {
        url: "carType/save.json",
        onSubmit: function () {
            //check
        },
        success: function (result) {
            var jsonResult = $.parseJSON(result);
            if (jsonResult.success) {
                $.messager.alert("添加成功", jsonResult.message);
                $editCarTypeDialog.dialog('close');
                $('#carTypeDatagrid').datagrid('reload');
            } else {
                $.messager.alert("添加失败", jsonResult.message, "error");
            }
        }
    });
}

function editCarType(event, carId) {
    event.stopPropagation();
    clearEditCarForm();
    var $editCarDialog = $("#editCarDialog").dialog({title: "修改号码"});
    $.ajax({
        url: "car/queryById.json",
        method: "post",
        data: {id: carId},
        dataType: "json",
        success: function (result) {
            if (result && result.success && result.data) {
                var car = result.data;
                $editCarDialog.find("#editCarFrom").form('load', {
                    number: car.number,
                    operator: car.operator,
                    attribution: car.attribution,
                    wholesalePrice: car.wholesalePrice,
                    floorPrice: car.floorPrice,
                    balance: car.balance,
                    priority: car.priority,
                    remark: car.remark,
                    id: car.id
                });
            }
        }
    });
    $editCarDialog.dialog("open");
}