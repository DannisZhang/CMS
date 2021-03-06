/**
 * @author Dannis
 * @date 2015-05-14
 */
$(function () {
    initWorkbench();
});

/**
 * 初始化工作台
 */
function initWorkbench() {
    initWorkbenchTopMenu();
    //initWorkbenchLeftMenu();
    initWorkbenchLeftMenuTree();
    initWorkspaceTabs();
    $('body').layout();
    initCalendar();
    if ($.cookie('easyuiThemeName')) {
        switchTheme($.cookie('easyuiThemeName'));
    }
}

/**
 * 初始化工作台顶部菜单
 */
function initWorkbenchTopMenu() {
    initSwitchThemeMenubutton();
    initShutdownMenubutton();
}

/**
 * 初始化菜单栏
 */
function initWorkbenchLeftMenu() {
    var leftMenus = {
        "menus": [
            {
                "menuId": 1, "menuName": "系统管理", "iconCls": "icon-dannis-setting",
                "menus": [
                    {"menuId": 1, "menuName": "用户管理", "iconCls": "icon-dannis-user", "url": "page/userManagement.html"},
                    {"menuId": 1, "menuName": "操作日志", "iconCls": "icon-custom-log", "url": "page/systemSetting.html"}
                ]
            },
            {
                "menuId": 1, "menuName": "运营数据", "iconCls": "icon-dannis-storage",
                "menus": [
                    {
                        "menuId": 1,
                        "menuName": "手机号码",
                        "iconCls": "icon-custom-mobile-phone",
                        "url": "page/mobilePhoneNumberManagement.html"
                    },
                    {
                        "menuId": 1,
                        "menuName": "电话号码",
                        "iconCls": "icon-custom-telephone",
                        "url": "page/telephoneNumberManagement.html"
                    },
                    {
                        "menuId": 1,
                        "menuName": "汽车级别",
                        "iconCls": "icon-custom-car",
                        "url": "page/carLevelManagement.html"
                    },
                    {
                        "menuId": 1,
                        "menuName": "汽车品牌",
                        "iconCls": "icon-custom-car",
                        "url": "page/carBrandManagement.html"
                    },
                    {
                        "menuId": 1,
                        "menuName": "汽车信息",
                        "iconCls": "icon-custom-car",
                        "url": "page/carManagement.html"
                    }
                ]
            },
            {
                "menuId": 1, "menuName": "系统资源", "iconCls": "icon-dannis-report",
                "menus": [
                    {"menuId": 1, "menuName": "模板下载", "iconCls": "icon-custom-excel", "url": "page/default.html"}
                ]
            },
            {
                "menuId": 1, "menuName": "关于系统", "iconCls": "icon-dannis-about",
                "menus": [
                    {"menuId": 1, "menuName": "关于本系统", "iconCls": "icon-dannis-about", "url": "page/default.html"},
                    {"menuId": 1, "menuName": "使用手册", "iconCls": "icon-custom-manual", "url": "page/default.html"}
                ]
            }
        ]
    };

    $('#left-menu').accordion({
        animate: false
    });
    $.each(leftMenus.menus, function (index1, next1) {
        var menuList = '<ul>';
        $.each(next1.menus, function (index2, next2) {
            var menuItem = '<li>';
            menuItem += '<div class="menu-item-icon ' + next2.iconCls + '"></div>';
            menuItem += '<a ref="' + next2.menuId + '" iconCls="' + next2.iconCls + '" href="javascript:void(0)" rel="' + next2.url + '">' + next2.menuName + '</a>';
            menuItem += '</li>';
            menuList += menuItem;
        });
        menuList += '</ul>';

        $('#left-menu').accordion('add', {
            title: next1.menuName,
            content: menuList,
            iconCls: next1.iconCls
        });
    });
    $('.easyui-accordion ul li a').click(function () {
        var tabTitle = $(this).text();
        var url = $(this).attr('rel');
        var iconCls = $(this).attr('iconCls');
        addTab(tabTitle, url, iconCls);
        $('.easyui-accordion ul li a').removeClass('selected');
        $(this).addClass('selected');
    });
}

function initWorkbenchLeftMenuTree() {
    var menuData = [
        {
            "id": 1,
            "text": "系统管理",
            "iconCls": "icon-dannis-setting",
            "state": "closed",
            "children": [{
                "id": 11,
                "text": "用户管理",
                "iconCls": "icon-dannis-user",
                "attributes": {
                    "url": "page/userManagement.html"
                }
            }, {
                "id": 12,
                "text": "操作日志",
                "iconCls": "icon-custom-log",
                "attributes": {
                    "url": "page/userManagement.html"
                }
            }]
        },
        {
            "id": 2,
            "text": "运营数据",
            "iconCls": "icon-dannis-storage",
            "state": "closed",
            "children": [{
                "id": 21,
                "text": "手机号码",
                "iconCls": "icon-custom-mobile-phone",
                "attributes": {
                    "url": "page/mobilePhoneNumberManagement.html"
                }
            }, {
                "id": 22,
                "text": "电话号码",
                "iconCls": "icon-custom-telephone",
                "attributes": {
                    "url": "page/telephoneNumberManagement.html"
                }
            }, {
                "id": 23,
                "text": "汽车管理",
                "iconCls": "icon-custom-car",
                "state": "closed",
                "children": [{
                        "id": 231,
                        "text": "汽车级别",
                        "iconCls": "icon-custom-car",
                        "attributes": {
                            "url": "page/carLevelManagement.html"
                        }
                    }, {
                        "id": 232,
                        "text": "汽车品牌",
                        "iconCls": "icon-custom-car",
                        "attributes": {
                            "url": "page/carBrandManagement.html"
                        }
                    }, {
                        "id": 233,
                        "text": "汽车信息",
                        "iconCls": "icon-custom-car",
                        "attributes": {
                            "url": "page/carManagement.html"
                        }
                    }
                ]
            }]
        },
        {
            "id": 3,
            "text": "系统资源",
            "iconCls": "icon-dannis-report",
            "state": "closed",
            "children": [{
                "id": 31,
                "text": "模板下载",
                "iconCls": "icon-custom-excel",
                "attributes": {
                    "url": "page/default.html"
                }
            }
            ]
        },
        {
            "id": 4,
            "text": "关于系统",
            "iconCls": "icon-dannis-report",
            "state": "closed",
            "children": [{
                "id": 41,
                "text": "关于本系统",
                "iconCls": "icon-dannis-about",
                "attributes": {
                    "url": "page/default.html"
                }
            }, {
                "id": 42,
                "text": "使用手册",
                "iconCls": "icon-custom-manual",
                "attributes": {
                    "url": "page/default.html"
                }
            }
            ]
        }
    ];

    $('#leftMenuTree').tree({
        data: menuData,
        onClick: function (node) {
            if ($(node).tree('isLeaf', node.target)) {
                if (node.attributes) {
                    var tabTitle = node.text;
                    var url = node.attributes.url;
                    var iconCls = node.iconCls;
                    addTab(tabTitle, url, iconCls);
                }
            }
        }
    });
}

/**
 * 初始化Tab
 */
function initWorkspaceTabs() {
    $("div[id='workspaceTabs']").tabs({
        border: false,
        fit: true
    });
}

/**
 * 添加Tab，若当前Tab已经存在，则选择当前Tab，否则创建一个Tab
 * @param tabTitle Tab标题
 * @param url Tab内容URL
 * @param iconCls 图标
 */
function addTab(tabTitle, url, iconCls) {
    var $workspaceTabs = $('#workspaceTabs');
    if ($workspaceTabs) {
        if ($workspaceTabs.tabs('exists', tabTitle)) {//如果Tab已经存在，则选择标题为tabTitle参数值的Tab
            $workspaceTabs.tabs('select', tabTitle);
        } else {
            $.ajax({
                type: 'post',
                url: url,
                cache: false,
                async: false,
                success: function (content) {
                    if (content) {
                        $workspaceTabs.tabs('add', {
                            title: tabTitle,
                            iconCls: iconCls,
                            content: content,
                            fit: true,
                            closable: true,
                            height: $('#mainPanel').height() - 26
                        });
                    }
                },
                error: function () {
                    alert("Load page error!")
                }
            });
        }
    }
}
