/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
var deletingDeptId = -1;
var maxFileSize = 2048;//最大文件大小，单位：KB
$(function () {
    initPhoneNumberDatagrid();
    initPhoneNumberDialog();

    $("#deleteDeptBtn").click(function () {
        var rows = $('#phoneNumberDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../phoneNumber/deletePhoneNumbersByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#phoneNumberDatagrid').datagrid('reload');
        });
    });
});

function initPhoneNumberDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "number", title: "号码", align: "center", width: 120, fixed: true},
            {field: "operator", title: "运营商", align: "center", width: 100, fixed: true},
            {field: "attribution", title: "归属地", align: "center", width: 80, fixed: true},
            {field: "wholesalePrice", title: "批发价", align: "center", width: 80, fixed: true},
            {field: "floorPrice", title: "底价", align: "center", width: 80, fixed: true},
            {field: "balance", title: "含话费", align: "center", width: 80, fixed: true},
            {field: "priority", title: "优先级", align: "center", width: 80, fixed: true},
            {field: "remark", title: "备注", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewPhoneNumberDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editPhoneNumber(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteDeptById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加号码',
        iconCls:'icon-add',
        handler:function() {
            addPhoneNumber();
        }
    },'-',{
        text:'删除号码',
        iconCls:'icon-remove',
        handler:function(){
            alert("删除号码");
        }
    },'-',{
        text:'导入Excel',
        handler:function(){
            importMobilePhoneNumber();
        }
    },'-',{
        text:'导出Excel',
        handler:function(){
            alert("导出EXCEL");
        }
    }];

    $("#phoneNumberDatagrid").datagrid({
        url: "mobilePhoneNumber/findByPage.json",
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

function initPhoneNumberDialog() {
    $.parser.parse("#phoneNumberManagement");
    $("#editPhoneNumberDialog").dialog({
        title:"添加号码",
        width:500,
        height:450,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#phoneNumberDialogButtons"
    });

    $("#importMobilePhoneNumberDialog").dialog({
        iconCls:"icon-save",
        title:"导入数据",
        width:550,
        height:380,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#importMobilePhoneNumberButtons"
    });

    $("#deletePhoneNumberDialog").dialog({
        title: "删除号码",
        width: 320,
        height: 150,
        modal: true,
        closed: true,
        resizable: false,
        buttons: [
            {
                text: '是的', iconCls: 'icon-ok', handler: function () {
                $.ajax({
                    url: "../mobilePhoneNumber/deletePhoneNumberById.json",
                    data: {deptId: deletingDeptId},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $("#phoneNumberDatagrid").datagrid("reload");
                    },
                    error: function (result) {
                        alert("删除失败");
                    }
                });
                $("#deletePhoneNumberDialog").dialog("close");
            }
            },
            {
                text: '不是', iconCls: 'icon-no', handler: function () {
                $("#deletePhoneNumberDialog").dialog("close");
            }
            }
        ]
    });
}

function addPhoneNumber() {
    clearEditPhoneNumberForm();
    $("#editPhoneNumberDialog").dialog({title:"添加号码"}).dialog("open");
}

function viewPhoneNumberDetail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function importMobilePhoneNumber() {
    $("#importMobilePhoneNumberDialog").dialog({title:"导入数据"}).dialog("open");
}

function clearEditPhoneNumberForm() {
    $("#editPhoneNumberDialog").find("#editPhoneNumberFrom").form("clear");
}

function deleteDeptById(event, deptId) {
    event.stopPropagation();
    deletingDeptId = deptId;
    $("#deletePhoneNumberDialog").dialog("open");
}

function savePhoneNumber() {
    var $editPhoneNumberDialog = $("#editPhoneNumberDialog");

    var phoneNumberId = $editPhoneNumberDialog.find("input[name='phoneNumberId']").val();
    var url = "mobilePhoneNumber/add.json";
    if (phoneNumberId != '') {
        url = "mobilePhoneNumber/update.json";
    }

    $editPhoneNumberDialog.find("#editPhoneNumberFrom").form("submit",{
        url:url,
        success: function (result) {
            try {
                var jsonResult = $.parseJSON(result);
                if (jsonResult.status = 0) {
                    $.messager.alert("提示信息",jsonResult.message);
                    $('#phoneNumberDatagrid').datagrid('reload');
                } else {
                    $.messager.alert("提示信息",jsonResult.message,"warning");
                }
                $.messager.alert("提示信息",jsonResult.message);
            } catch (e) {
                $.messager.alert("系统异常","系统发生异常","warning");
            }
            clearEditPhoneNumberForm();
            $editPhoneNumberDialog.dialog('close');
        }
    });
}

/**
 * 导入手机号码Excel数据
 */
function importMobilePhoneNumberExcel() {
    var $importMobilePhoneNumberDialog = $("#importMobilePhoneNumberDialog");
    $importMobilePhoneNumberDialog.find("#importMobilePhoneNumberFrom").form("submit",{
        url:"mobilePhoneNumber/importExcel.json",
        onSubmit: function() {
            var isValid = true;
            var $fileObjectList = $("input[type='file'][name='mobilePhoneNumberExcel']");
            if ($fileObjectList.length > 0 && $fileObjectList[0].files.length > 0) {
                var file = $fileObjectList[0].files[0];
                var fileName = file.name;
                var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length);
                if (fileExt != 'xls' && fileExt != 'xlsx') {//非Excel文件
                    $.messager.alert("文件类型错误","请选择Excel数据文件","error");
                    isValid = false;
                } else {//Excel文件
                    var fileSize = Math.round(file.size / 1024 * 100) / 100;//单位为：KB
                    if (fileSize > maxFileSize) {
                        $.messager.alert("文件过大","数据文件过大，请分批进行导入","warning");
                        isValid = false;
                    }
                }
            } else {
                $.messager.alert("未选择文件","请选择Excel数据文件","error");
                isValid = false;
            }
            if (isValid) {
                $.messager.progress({
                    msg:'正在导入数据...'
                });
            }
            return isValid;
        },
        success: function (data) {
            $.messager.progress("close");
            var result = $.parseJSON(data);
            if (result.success) {
                $.messager.alert("导入成功",result.message);
            } else {
                $.messager.alert("导入失败",result.message,"error");
            }
            $importMobilePhoneNumberDialog.dialog("close");
        }
    });
}

function checkFile() {
    var isValid = true;
    var $fileObjectList = $("input[type='file'][name='mobilePhoneNumberExcel']");
    if ($fileObjectList.length > 0 && $fileObjectList[0].files.length > 0) {
        var file = $fileObjectList[0].files[0];
        var fileName = file.name;
        var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length);
        if (fileExt != 'xls' && fileExt != 'xlsx') {//非Excel文件
            $.messager.alert("文件类型错误","请选择Excel数据文件","error");
            isValid = false;
        } else {//Excel文件
            var fileSize = Math.round(file.size / 1024 * 100) / 100;//单位为：KB
            if (fileSize > 1) {//文件大小不能超过2MB
                $.messager.alert("文件过大","数据文件过大，请分批进行导入","warning");
                isValid = false;
            }
        }
    } else {
        $.messager.alert("未选择文件","请选择Excel数据文件","error");
        isValid = false;
    }
    alert(isValid);
    return isValid;
}

function editPhoneNumber(event, deptId) {
    event.stopPropagation();
    clearEditPhoneNumberForm();
    var $editPhoneNumberDialog = $("#editPhoneNumberDialog").dialog({title:"修改号码"});
    $.ajax({
        url:"mobilePhoneNumber/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var phoneNumber = result.data;
                $editPhoneNumberDialog.find("#editPhoneNumberFrom").form('load',{
                    cnName:phoneNumber.cnName,
                    enName:phoneNumber.enName,
                    parent:phoneNumber.parent ? phoneNumber.parent.id : '',
                    phoneNumberManager:phoneNumber.manager ? phoneNumber.manager.id : '',
                    location:phoneNumber.location,
                    establishedDate:phoneNumber.establishedDate,
                    remark:phoneNumber.remark,
                    phoneNumberId:phoneNumber.id
                });
            }
        }
    });
    $editPhoneNumberDialog.dialog("open");
}