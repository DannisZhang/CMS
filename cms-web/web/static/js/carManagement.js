/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
$(function () {
    initCarDatagrid();
    initCarDialog();
    $('#addCarImage').click(function () {
        $('#carImageFile').trigger('click');
    });
});

function initCarDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "number", title: "号码", align: "center", width: 120, fixed: true},
            {field: "operator", title: "运营商", align: "center", width: 100, fixed: true},
            {field: "attribution", title: "归属地", align: "center", width: 80, fixed: true},
            {
                field: "wholesalePrice",
                title: "批发价",
                align: "center",
                width: 80,
                fixed: true,
                formatter: function (value, row, index) {
                    return "￥" + row.wholesalePrice;
                }
            },
            {
                field: "floorPrice",
                title: "底价",
                align: "center",
                width: 80,
                fixed: true,
                formatter: function (value, row, index) {
                    return "￥" + row.floorPrice;
                }
            },
            {
                field: "balance",
                title: "含话费",
                align: "center",
                width: 80,
                fixed: true,
                formatter: function (value, row, index) {
                    return "￥" + row.balance;
                }
            },
            {field: "priority", title: "优先级", align: "center", width: 80, fixed: true},
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
        text: '添加汽车',
        iconCls: 'icon-add',
        handler: function () {
            addCar();
        }
    }, '-', {
        text: '删除汽车',
        iconCls: 'icon-remove',
        handler: function () {
            deleteCars();
        }
    }];

    $("#carDatagrid").datagrid({
        url: "car/queryByPage.ajax",
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

function initCarDialog() {
    $.parser.parse("#carManagementPage");
    $("#editCarDialog").dialog({
        iconCls: "icon-edit",
        title: "添加汽车",
        width: 650,
        height: 600,
        closed: true,
        cache: false,
        modal: true,
        buttons: "#carDialogButtons"
    });
}

function addCar() {
    clearEditCarForm();
    $("#editCarDialog").dialog({title: "添加汽车"}).dialog("open");
}

function clearEditCarForm() {
    var $editCarFrom = $("#editCarDialog").find("#editCarFrom");
}

function deleteCarById(event, carId) {
    event.stopPropagation();
    $.messager.confirm("确认删除", "请确认是否删除手机号码？", function (r) {
        if (r) {
            $.ajax({
                url: "car/deleteById.ajax",
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

function deleteCars() {
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
                url: "car/deleteByIds.ajax",
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

function saveCar() {
    var $editCarDialog = $("#editCarDialog");
    var $editCarFrom = $editCarDialog.find("#editCarFrom");
    $editCarFrom.form("submit", {
        url: "car/save.ajax",
        onSubmit: function () {
            //check code
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

function exitEditCar() {
    var $editCarDialog = $('#editCarDialog');
    var $editCarFrom = $editCarDialog.find('#editCarFrom');
    $editCarFrom.form("clear");
    var imageUrls = [];
    $.each($editCarFrom.find('#addCarImagePanel').find('.uploaded-image').find('img'), function () {
        imageUrls.push($(this).attr('src'));
    });
    if (imageUrls.length > 0) {
        $.ajax({
            url: "car/deleteImages.ajax",
            method: "post",
            data: {"imageUrls": imageUrls.join(",")}
        });

        $editCarFrom.find('#addCarImagePanel').find('.uploaded-image').remove();
    }
    $editCarDialog.dialog('close');
}

function viewCarDetail(event, carId) {
    event.stopPropagation();
    clearEditCarForm();
    var $editCarDialog = $("#editCarDialog").dialog({title: "查看号码"});
    $.ajax({
        url: "car/queryById.ajax",
        method: "post",
        data: {id: carId},
        dataType: "json",
        success: function (result) {
            if (result && result.success && result.data) {
                $("#carDialogButtons").find("a[name='save-btn']").linkbutton("disable");
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

function editCar(event, carId) {
    event.stopPropagation();
    clearEditCarForm();
    var $editCarDialog = $("#editCarDialog").dialog({title: "修改号码"});
    $.ajax({
        url: "car/queryById.ajax",
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

function queryCars() {
    var $queryCarDiv = $("#queryCarDiv");
    $('#carDatagrid').datagrid('reload', {
        params: {
            number: $queryCarDiv.find("#queryCarItemNumber").textbox('getValue'),
            operator: $queryCarDiv.find("#queryCarItemOperator").combobox('getValue'),
            attribution: $queryCarDiv.find("#queryCarItemAttribution").textbox('getValue')
        }
    });
}

function uploadCarImage() {
    $('#uploadCarImageForm').form('submit', {
        url: "car/uploadImage.ajax",
        onSubmit: function () {
            var $fileObjectList = $("#carImageFile");
            if ($fileObjectList.length > 0 && $fileObjectList[0].files.length > 0) {
                var file = $fileObjectList[0].files[0];
                var fileName = file.name;
            }
        },
        success: function (result) {
            var jsonResult = $.parseJSON(result);
            if (jsonResult.success) {
                var uploadedImageDivHtml = '<div class="uploaded-image">';
                uploadedImageDivHtml += '<img src="' + jsonResult.message + '" />';
                uploadedImageDivHtml += '<span>×</span>';
                uploadedImageDivHtml += '</div>';
                $('#addCarImage').before(uploadedImageDivHtml);
                $('.uploaded-image').find('span').on('click', function (e) {
                    var $uploadedImageDiv = $(this).parent();
                    $.ajax({
                        url: "car/deleteImage.ajax",
                        method: "post",
                        data: {imageUrl: $uploadedImageDiv.find('img').attr('src')},
                        success: function (result) {
                            if (result && result.success) {
                                $uploadedImageDiv.remove();
                            }
                        }
                    });
                });
            }
        }
    });
}

function previewCarImage(e, imageUrl) {
    var pointX = e.pageX;
    var pointY = e.pageY;
    var $previewCarImageDiv = $('#previewCarImageDiv');
    $previewCarImageDiv.css('top', pointY);
    $previewCarImageDiv.css('left', pointX);
}