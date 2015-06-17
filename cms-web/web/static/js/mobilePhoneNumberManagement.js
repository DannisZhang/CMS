/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
var maxFileSize = 2048;//最大文件大小，单位：KB
$(function () {
    initMobilePhoneNumberDatagrid();
    initMobilePhoneNumberDialog();
});

function initMobilePhoneNumberDatagrid() {
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
                    var detail = '<a class="datagrid-detail-button" onclick="viewMobilePhoneNumberDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editMobilePhoneNumber(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteMobilePhoneNumberById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text: '添加号码',
        iconCls: 'icon-add',
        handler: function () {
            addMobilePhoneNumber();
        }
    }, '-', {
        text: '删除号码',
        iconCls: 'icon-remove',
        handler: function () {
            deleteMobilePhoneNumbers();
        }
    }, '-', {
        text: '导入Excel',
        handler: function () {
            importMobilePhoneNumber();
        }
    }, '-', {
        text: '导出Excel',
        handler: function () {
            $.messager.alert("系统提示", "该功能还不能使用");
        }
    }];

    $("#mobilePhoneNumberDatagrid").datagrid({
        url: "mobilePhoneNumber/queryByPage.json",
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

function initMobilePhoneNumberDialog() {
    $.parser.parse("#mobilePhoneNumberManagement");
    $("#editMobilePhoneNumberDialog").dialog({
        iconCls: "icon-edit",
        title: "添加手机号码",
        width: 500,
        height: 480,
        closed: true,
        cache: false,
        modal: true,
        buttons: "#mobilePhoneNumberDialogButtons"
    });

    $("#importMobilePhoneNumberDialog").dialog({
        iconCls: "icon-save",
        title: "导入数据",
        width: 550,
        height: 380,
        closed: true,
        cache: false,
        modal: true,
        buttons: "#importMobilePhoneNumberButtons"
    });
}

function addMobilePhoneNumber() {
    clearEditMobilePhoneNumberForm();
    $("#editMobilePhoneNumberDialog").dialog({title: "添加号码"}).dialog("open");
}

function importMobilePhoneNumber() {
    $("#importMobilePhoneNumberDialog").dialog({title: "导入数据"}).dialog("open");
}

function clearEditMobilePhoneNumberForm() {
    var $editMobilePhoneNumberDialog = $("#editMobilePhoneNumberDialog");
    var $editMobilePhoneNumberFrom = $editMobilePhoneNumberDialog.find("#editMobilePhoneNumberFrom");
    $editMobilePhoneNumberFrom.form("clear");
    $editMobilePhoneNumberFrom.find("input[name='wholesalePrice']").val(0.00);
    $editMobilePhoneNumberFrom.find("input[name='floorPrice']").val(0.00);
    $editMobilePhoneNumberFrom.find("input[name='balance']").val(0.00);
    $editMobilePhoneNumberFrom.find("input[name='priority']").val(9999);
    $("#mobilePhoneNumberDialogButtons").find("a[name='save-btn']").linkbutton("enable");
}

function deleteMobilePhoneNumberById(event, mobilePhoneNumberId) {
    event.stopPropagation();
    $.messager.confirm("确认删除", "请确认是否删除手机号码？", function (r) {
        if (r) {
            $.ajax({
                url: "mobilePhoneNumber/deleteById.json",
                method: "post",
                data: {"id": mobilePhoneNumberId},
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("删除成功", result.message);
                        $('#mobilePhoneNumberDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert("删除失败", result.message, "error");
                    }
                }
            })
        }
    });
}

function deleteMobilePhoneNumbers() {
    var rows = $('#mobilePhoneNumberDatagrid').datagrid('getChecked');
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
                url: "mobilePhoneNumber/deleteByIds.json",
                method: "post",
                data: {"ids": ids.join(",")},
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("删除成功", result.message);
                        $('#mobilePhoneNumberDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert("删除失败", result.message, "error");
                    }
                }
            })
        }
    });
}

function saveMobilePhoneNumber() {
    var $editMobilePhoneNumberDialog = $("#editMobilePhoneNumberDialog");
    var $editMobilePhoneNumberFrom = $editMobilePhoneNumberDialog.find("#editMobilePhoneNumberFrom");
    $editMobilePhoneNumberFrom.form("submit", {
        url: "mobilePhoneNumber/save.json",
        onSubmit: function () {
            var number = $editMobilePhoneNumberFrom.find("input[name='number']").val();
            if (!number || number.length != 11) {
                $.messager.alert("错误提示", "请填写11位手机号码", "warning");
                return false;
            }
            var operator = $editMobilePhoneNumberFrom.find("input[name='operator']").val();
            if (!operator) {
                $.messager.alert("错误提示", "请选择运营商", "warning");
                return false;
            }
            var attribution = $editMobilePhoneNumberFrom.find("input[name='attribution']").val();
            if (!attribution) {
                $.messager.alert("错误提示", "请填写手机号码归属地" + attribution, "warning");
                return false;
            }
        },
        success: function (result) {
            var jsonResult = $.parseJSON(result);
            if (jsonResult.success) {
                $.messager.alert("添加成功", jsonResult.message);
                $editMobilePhoneNumberDialog.dialog('close');
                $('#mobilePhoneNumberDatagrid').datagrid('reload');
            } else {
                $.messager.alert("添加失败", jsonResult.message, "error");
            }
        }
    });
}

/**
 * 导入手机号码Excel数据
 */
function importMobilePhoneNumberExcel() {
    var $importMobilePhoneNumberDialog = $("#importMobilePhoneNumberDialog");
    $importMobilePhoneNumberDialog.find("#importMobilePhoneNumberFrom").form("submit", {
        url: "mobilePhoneNumber/importExcel.json",
        onSubmit: function () {
            var isValid = true;
            var $fileObjectList = $("input[type='file'][name='mobilePhoneNumberExcel']");
            if ($fileObjectList.length > 0 && $fileObjectList[0].files.length > 0) {
                var file = $fileObjectList[0].files[0];
                var fileName = file.name;
                var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
                if (fileExt != "xls" && fileExt != "xlsx") {//非Excel文件
                    $.messager.alert("文件类型错误", "请选择Excel数据文件", "error");
                    isValid = false;
                } else {//Excel文件
                    var fileSize = Math.round(file.size / 1024 * 100) / 100;//单位为：KB
                    if (fileSize > maxFileSize) {
                        $.messager.alert("文件过大", "数据文件过大，请分批进行导入", "warning");
                        isValid = false;
                    }
                }
            } else {
                $.messager.alert("未选择文件", "请选择Excel数据文件", "error");
                isValid = false;
            }
            if (isValid) {
                $.messager.progress({
                    msg: "正在导入数据..."
                });
            }
            return isValid;
        },
        success: function (data) {
            $.messager.progress("close");
            var result = $.parseJSON(data);
            if (result.success) {
                $.messager.alert("导入成功", result.message);
                $("#mobilePhoneNumberDatagrid").datagrid("reload");
                $importMobilePhoneNumberDialog.dialog("close");
            } else {
                $.messager.alert("导入失败", result.message, "error");
            }
        }
    });
}

function viewMobilePhoneNumberDetail(event, mobilePhoneNumberId) {
    event.stopPropagation();
    clearEditMobilePhoneNumberForm();
    var $editMobilePhoneNumberDialog = $("#editMobilePhoneNumberDialog").dialog({title: "查看号码"});
    $.ajax({
        url: "mobilePhoneNumber/queryById.json",
        method: "post",
        data: {id: mobilePhoneNumberId},
        dataType: "json",
        success: function (result) {
            if (result && result.success && result.data) {
                $("#mobilePhoneNumberDialogButtons").find("a[name='save-btn']").linkbutton("disable");
                var mobilePhoneNumber = result.data;
                $editMobilePhoneNumberDialog.find("#editMobilePhoneNumberFrom").form('load', {
                    number: mobilePhoneNumber.number,
                    operator: mobilePhoneNumber.operator,
                    attribution: mobilePhoneNumber.attribution,
                    wholesalePrice: mobilePhoneNumber.wholesalePrice,
                    floorPrice: mobilePhoneNumber.floorPrice,
                    balance: mobilePhoneNumber.balance,
                    priority: mobilePhoneNumber.priority,
                    remark: mobilePhoneNumber.remark,
                    id: mobilePhoneNumber.id
                });
            }
        }
    });
    $editMobilePhoneNumberDialog.dialog("open");
}

function editMobilePhoneNumber(event, mobilePhoneNumberId) {
    event.stopPropagation();
    clearEditMobilePhoneNumberForm();
    var $editMobilePhoneNumberDialog = $("#editMobilePhoneNumberDialog").dialog({title: "修改号码"});
    $.ajax({
        url: "mobilePhoneNumber/queryById.json",
        method: "post",
        data: {id: mobilePhoneNumberId},
        dataType: "json",
        success: function (result) {
            if (result && result.success && result.data) {
                var mobilePhoneNumber = result.data;
                $editMobilePhoneNumberDialog.find("#editMobilePhoneNumberFrom").form('load', {
                    number: mobilePhoneNumber.number,
                    operator: mobilePhoneNumber.operator,
                    attribution: mobilePhoneNumber.attribution,
                    wholesalePrice: mobilePhoneNumber.wholesalePrice,
                    floorPrice: mobilePhoneNumber.floorPrice,
                    balance: mobilePhoneNumber.balance,
                    priority: mobilePhoneNumber.priority,
                    remark: mobilePhoneNumber.remark,
                    id: mobilePhoneNumber.id
                });
            }
        }
    });
    $editMobilePhoneNumberDialog.dialog("open");
}

function queryMobilePhoneNumbers() {
    var $queryMobilePhoneNumberDiv = $("#queryMobilePhoneNumberDiv");
    $('#mobilePhoneNumberDatagrid').datagrid('reload',{
        params:{
            number:$queryMobilePhoneNumberDiv.find("#queryMobilePhoneNumberItemNumber").textbox('getValue'),
            operator:$queryMobilePhoneNumberDiv.find("#queryMobilePhoneNumberItemOperator").combobox('getValue'),
            attribution:$queryMobilePhoneNumberDiv.find("#queryMobilePhoneNumberItemAttribution").textbox('getValue')
        }
    });
}