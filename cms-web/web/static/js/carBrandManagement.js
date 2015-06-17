/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
$(function () {
    initCarBrandDatagrid();
    initCarBrandDialog();
});

function initCarBrandDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "name", title: "品牌名称", align: "center", width: 120, fixed: true},
            {field: "englishName", title: "英文名称", align: "center", width: 120, fixed: true},
            {
                field: "logoUrl",
                title: "LOGO",
                align: "center",
                width: 120,
                fixed: true,
                formatter: function (value, row, index) {
                    if (value) {
                        return '<img src="' + value + '/>';
                    }
                    return '';
                }
            },
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
        text: '添加品牌',
        iconCls: 'icon-add',
        handler: function () {
            addCarBrand();
        }
    }, '-', {
        text: '删除品牌',
        iconCls: 'icon-remove',
        handler: function () {
            deleteCars();
        }
    }];

    $("#carBrandDatagrid").datagrid({
        url: "car/queryByPage.json",
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

function initCarBrandDialog() {
    $.parser.parse("#carBrandManagementPage");
    $("#editCarBrandDialog").dialog({
        iconCls: "icon-edit",
        title: "添加品牌",
        width: 580,
        height: 480,
        closed: true,
        cache: false,
        modal: true,
        buttons: "#carBrandDialogButtons"
    });
}

function addCarBrand() {
    clearEditCarBrandForm();
    $("#editCarBrandDialog").dialog({title: "添加汽车"}).dialog("open");
}

function clearEditCarBrandForm() {
    $("#editCarBrandDialog").find("#editCarBrandFrom").form("clear");
}

function deleteCarBrandById(event, carId) {
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

function deleteCarBrands() {
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

function saveCarBrand() {
    var $editCarDialog = $("#editCarDialog");
    var $editCarFrom = $editCarDialog.find("#editCarFrom");
    $editCarFrom.form("submit", {
        url: "car/save.json",
        onSubmit: function () {
            var number = $editCarFrom.find("input[name='number']").val();
            if (!number || number.length != 11) {
                $.messager.alert("错误提示", "请填写11位手机号码", "warning");
                return false;
            }
            var operator = $editCarFrom.find("input[name='operator']").val();
            if (!operator) {
                $.messager.alert("错误提示", "请选择运营商", "warning");
                return false;
            }
            var attribution = $editCarFrom.find("input[name='attribution']").val();
            if (!attribution) {
                $.messager.alert("错误提示", "请填写手机号码归属地" + attribution, "warning");
                return false;
            }
        },
        success: function (result) {
            var jsonResult = $.parseJSON(result);
            if (jsonResult.success) {
                $.messager.alert("添加成功", jsonResult.message);
                $editCarDialog.dialog('close');
                $('#carDatagrid').datagrid('reload');
            } else {
                $.messager.alert("添加失败", jsonResult.message, "error");
            }
        }
    });
}

function editCarBrand(event, carId) {
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