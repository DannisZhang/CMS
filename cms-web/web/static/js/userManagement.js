/**
 * Created by deng.zhang on 2014/11/19.
 */
var deletingDeptId = -1;
$(function () {
    initUserDatagrid();
    initUserDialog();

    $("#deleteUserBtn").click(function () {
        var rows = $('#userDatagrid').datagrid('getChecked');
        if (rows.length == 0) {
            return;
        }
        var ids = [];
        $.each(rows, function (i, row) {
            ids.push(row.id);
        });
        $.post('../user/deleteUsersByIds.json', {ids: ids.join(",")}, function (result) {
            alert(result.message);
            $('#userDatagrid').datagrid('reload');
        });
    });
});

function initUserDatagrid() {
    var columns = [
        [
            {field: 'ck', checkbox: true},
            {field: "code", title: "编码", align: "center", width: 100, fixed: true},
            {field: "name", title: "姓名", align: "center", width: 100, fixed: true},
            {field: "gender", title: "性别", align: "center", width: 50, fixed: true},
            {field: "loginName", title: "登录名", align: "center", width: 100, fixed: true},
            {field: "createdBy", title: "创建者", align: "center", width: 100, fixed: true},
            {field: "createdOn", title: "创建时间", align: "center", width: 120, fixed: true},
            {field: "description", title: "用户描述", align: "center", width: 200},
            {
                field: "id", title: "操作", align: "center", width: 150, fixed: true,
                formatter: function (value, row, index) {
                    var detail = '<a class="datagrid-detail-button" onclick="viewUserDetail(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center" href="javascript:void(0);">详情</a>';
                    var edit = '<a class="datagrid-edit-button" onclick="editUser(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px" href="javascript:void(0);">修改</a>';
                    var del = '<a class="datagrid-delete-button" onclick="deleteUserById(event,' + row.id + ')"'
                        + ' style="height:20px;width:34px;text-align: center;margin-left:5px;" href="javascript:void(0);">删除</a>';
                    return detail + edit + del;
                }
            }
        ]
    ];

    var toolbar = [{
        text:'添加用户',
        iconCls:'icon-add',
        handler:function() {
            addUser();
        }
    },'-',{
        text:'删除用户',
        iconCls:'icon-remove',
        handler:function(){
            alert("删除用户");
        }
    }];

    $("#userDatagrid").datagrid({
        url: "user/queryByPage.json",
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

function initUserDialog() {
    $.parser.parse("#userManagement");
    $("#editUserDialog").dialog({
        title:"添加用户",
        width:500,
        height:500,
        closed:true,
        cache:false,
        modal:true,
        buttons:"#userDialogButtons"
    });

    $("#deleteUserDialog").dialog({
        title: "删除用户",
        width: 320,
        height: 150,
        modal: true,
        closed: true,
        resizable: false,
        buttons: [
            {
                text: '是的', iconCls: 'icon-ok', handler: function () {
                $.ajax({
                    url: "../user/deleteUserById.json",
                    data: {deptId: deletingDeptId},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $("#userDatagrid").datagrid("reload");
                    },
                    error: function (result) {
                        alert("删除失败");
                    }
                });
                $("#deleteUserDialog").dialog("close");
            }
            },
            {
                text: '不是', iconCls: 'icon-no', handler: function () {
                $("#deleteUserDialog").dialog("close");
            }
            }
        ]
    });
}

function addUser() {
    clearEditUserForm();
    $("#editUserDialog").dialog({title:"添加用户"}).dialog("open");
}

function detail(event, deptId) {
    event.stopPropagation();
    alert("详情");
}

function clearEditUserForm() {
    $("#editUserDialog").find("#editUserFrom").form("clear");
}

function saveUser() {
    var $editUserDialog = $("#editUserDialog");
    var params = {};
    params.cnName = $editUserDialog.find("#cnName").textbox("getValue");
    params.enName = $editUserDialog.find("#enName").textbox("getValue");
    params.location = $editUserDialog.find("#location").textbox("getValue");
    params.establishedDate = $editUserDialog.find("#establishedDate").datebox('getValue');
    params.parentId = $editUserDialog.find("#parent").combobox('getValue');
    params.managerId = $editUserDialog.find("#manager").combobox('getValue');
    params.remark = $editUserDialog.find("#remark").textbox('getValue');

    var userId = $editUserDialog.find("input[type='hidden'][id='userId']").val();
    var url = "../user/add.json";
    if (userId != '-1') {
        url = "../user/update.json";
        params.id = userId;
    }
    $.ajax({
        url:url,
        method:'post',
        data:params,
        async:false,
        success: function (result) {
            alert(result.message);
            $editUserDialog.dialog('close');
            $('#userDatagrid').datagrid('reload');
        }
    });
}

function editUser(event, deptId) {
    event.stopPropagation();
    clearEditUserForm();
    var $editUserDialog = $("#editUserDialog").dialog({title:"修改用户"});
    $.ajax({
        url:"user/queryById.json",
        method:"get",
        data:{deptId:deptId},
        dataType:"json",
        success: function (result) {
            if (result && result.data) {
                var user = result.data;
                $editUserDialog.find("input[type='hidden'][id='userId']").val(user.id);
                $editUserDialog.find("#cnName").textbox("setValue",user.cnName);
                $editUserDialog.find("#enName").textbox("setValue",user.enName);
                if (user.parent) {
                    $editUserDialog.find("#parent").combobox("setValue",user.parent.id);
                }
                if (user.manager) {
                    $editUserDialog.find("#manager").combobox("select",user.manager.id);
                }
                $editUserDialog.find("#location").textbox("setValue",user.location);
                $editUserDialog.find("#establishedDate").datebox("setValue",user.establishedDate);
                $editUserDialog.find("#remark").textbox("setValue",user.remark);
            }
        }
    });
    $editUserDialog.dialog("open");
}