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
            {
                field: "level",
                title: "级别",
                align: "center",
                width: 100,
                fixed: true,
                formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                    return '';
                }
            },
            {
                field: "brand",
                title: "品牌",
                align: "center",
                width: 100,
                fixed: true,
                formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                    return '';
                }
            },
            {field: "series", title: "车系", align: "center", width: 80, fixed: true},
            {field: "structure", title: "车身结构", align: "center", width: 80, fixed: true},
            {field: "displacement", title: "排量", align: "center", width: 50, fixed: true},
            {
                field: "price",
                title: "价格",
                align: "center",
                width: 80,
                fixed: true,
                formatter: function (value, row, index) {
                    return "￥" + row.price + '万';
                }
            },
            {
                field: "lowestPrice",
                title: "最低价格",
                align: "center",
                width: 80,
                fixed: true,
                formatter: function (value, row, index) {
                    return "￥" + row.lowestPrice + '万';
                }
            },
            {field: "priority", title: "优先级", align: "center", width: 80, fixed: true},
            {field: "remark", title: "备注", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var edit = '<a class="datagrid-edit-button" onclick="editCar(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteCarById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return edit + del;
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
        url: "car/queryByPage.json",
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
    var $editCarFrom = $('#editCarDialog').find('#editCarFrom');
    $editCarFrom.form("clear");
    $editCarFrom.find('#addCarImagePanel').find('.uploaded-image').remove();
}

function closeEditCarDialog() {
    var $editCarDialog = $('#editCarDialog');
    var $editCarFrom = $editCarDialog.find('#editCarFrom');
    var carId = $editCarFrom.find('input[type="hidden"][name="id"]').val();
    if (!carId) {
        var imageUrls = [];
        $.each($editCarFrom.find('#addCarImagePanel').find('.uploaded-image').find('img'), function () {
            imageUrls.push($(this).attr('src'));
        });
        if (imageUrls.length > 0) {
            $.ajax({
                url: "car/deleteImages.json",
                method: "post",
                data: {"imageUrls": imageUrls.join(",")}
            });

            $editCarFrom.find('#addCarImagePanel').find('.uploaded-image').remove();
        }
    }
    $editCarFrom.form("clear");
    $editCarDialog.dialog('close');
}

function deleteCarById(event, carId) {
    event.stopPropagation();
    $.messager.confirm("确认删除", "请确认是否删除汽车信息？", function (r) {
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

function deleteCars() {
    var rows = $('#carDatagrid').datagrid('getChecked');
    if (rows.length == 0) {
        $.messager.alert("提示信息", "请勾选将要删除的汽车信息", "warning");
        return;
    }
    $.messager.confirm("确认删除", "请确认是否删除汽车信息？", function (r) {
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

function saveCar() {
    var $editCarDialog = $("#editCarDialog");
    var $editCarFrom = $editCarDialog.find("#editCarFrom");
    $editCarFrom.form("submit", {
        url: "car/save.json",
        onSubmit: function (param) {
            var imageUrls = [];
            $.each($editCarFrom.find('#addCarImagePanel').find('.uploaded-image').find('img'), function () {
                imageUrls.push($(this).attr('src'));
            });
            param.imageUrls = imageUrls.join(',');
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

function editCar(event, carId) {
    event.stopPropagation();
    clearEditCarForm();
    var $editCarDialog = $("#editCarDialog").dialog({title: "修改汽车信息"});
    $.ajax({
        url: "car/queryById.json",
        method: "post",
        data: {id: carId},
        dataType: "json",
        success: function (result) {
            if (result && result.success && result.data) {
                var car = result.data;
                $editCarDialog.find("#editCarFrom").form('load', {
                    type: car.type,
                    brand: car.brand,
                    series: car.series,
                    structure: car.structure,
                    displacement: car.displacement,
                    emissionStandard: car.emissionStandard,
                    gearbox: car.gearbox,
                    mileage: car.mileage,
                    price: car.price,
                    lowestPrice: car.lowestPrice,
                    priority: car.priority,
                    remark: car.remark,
                    id: car.id
                });

                showCarImages(car.imageUrls);
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
        url: "car/uploadImage.json",
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
                    if (!$('#editCarForm').find('input[type="hidden"][name="id"]').val()) {
                        var $uploadedImageDiv = $(this).parent();
                        $.ajax({
                            url: "car/deleteImage.json",
                            method: "post",
                            data: {imageUrl: $uploadedImageDiv.find('img').attr('src')},
                            success: function (result) {
                                if (result && result.success) {
                                    $uploadedImageDiv.remove();
                                }
                            }
                        });
                    }
                });
            }
        }
    });
}

function showCarImages(imageUrls) {
    if (imageUrls) {
        var uploadedImageDivs = '';
        $.each(imageUrls, function () {
            var uploadedImageDiv = '<div class="uploaded-image">';
            uploadedImageDiv += '<img src="' + this + '" />';
            uploadedImageDiv += '<span>×</span>';
            uploadedImageDiv += '</div>';

            uploadedImageDivs += uploadedImageDiv;
        });

        $('#addCarImage').before(uploadedImageDivs);
        $('.uploaded-image').find('span').on('click', function (e) {
            $(this).parent().remove();
        });
    }
}