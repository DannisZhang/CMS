/**
 * @author deng.zhang
 * @date 2014/11/19.
 */
var deletingDeptId = -1;
var allowFileTypes = ['xls','xlsx'];
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
            {field: "type", title: "类型", align: "center", width: 80, fixed: true},
            {field: "operator", title: "运营商", align: "center", width: 100, fixed: true},
            {field: "attribution", title: "归属地", align: "center", width: 80, fixed: true},
            {field: "wholesalePrice", title: "批发价", align: "center", width: 80, fixed: true},
            {field: "floorPrice", title: "底价", align: "center", width: 80, fixed: true},
            {field: "priority", title: "优先级", align: "center", width: 80, fixed: true},
            {field: "description", title: "描述", align: "center", width: 200},
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
        text:'批量导入',
        handler:function(){
            batchUploadPhoneNumber();
        }
    },'-',{
        text:'导出EXCEL',
        handler:function(){
            alert("导出EXCEL");
        }
    }];

    $("#phoneNumberDatagrid").datagrid({
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
        title:"批量导入手机号码",
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

function batchUploadPhoneNumber() {
    $("#importMobilePhoneNumberDialog").dialog({title:"批量导入电话号码"}).dialog("open");
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

function uploadExcelFile() {
    var $importMobilePhoneNumberDialog = $("#importMobilePhoneNumberDialog");
    $importMobilePhoneNumberDialog.find("#importMobilePhoneNumberFrom").form("submit",{
        url:"mobilePhoneNumber/upload.json",
        onSubmit: function(){
            checkFile();
        },
        success: function (data) {
            console.log(data)
            var result = $.parseJSON(data);
            $.messager.alert("提示信息",result.message);
            $importMobilePhoneNumberDialog.dialog("close");
        }
    });
}

function checkFile() {
    var $fileObjectList = $("input[type='file'][name='mobilePhoneNumberExcel']");
    if ($fileObjectList.length > 0 && $fileObjectList[0].files.length > 0) {
        var file = $fileObjectList[0].files[0];
        console.log(file);
        var fileName = file.name;
        var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length);
        if (fileExt != 'xls' && fileExt != 'xlsx') {
            $.messager.alert("文件类型错误","数据文件必须是Excel文件");
            event.returnValue = false;
            return;
        }
        var fileSize = Math.round(file.size / 1024 * 100) / 100;//单位为：KB
        if (fileSize > 1) {//文件大小不能超过2MB
            $.messager.alert("文件过大","数据文件过大，请分批进行导入");
            event.returnValue = false;
            return;
        }
    } else {
        $.messager.alert("未指定文件","未指定数据文件");
        event.returnValue = false;
        return;
    }
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