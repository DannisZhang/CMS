var themeName = 'gray';
var customThemeHref = "static/easyui/themes/gray/easyui.css";
$(function () {
    init();
});

function init() {
    initTopMenu();
    initLeftMenu();
    initWorkspaceTabs();
    $('body').layout();
    showCurrentTime();
    initCalendar();
    loadWorkbenches();
    if ($.cookie('easyuiThemeName')) {
        changeTheme($.cookie('easyuiThemeName'));
    }
}

function initTopMenu() {
    var $changeThemeMenuButton = $("#change-theme-menubutton").menubutton({
        menu: '#theme-menu',
        iconCls: 'icon-custom-theme'
    });
    var $exitMenuButton = $("#exit-menubutton").menubutton({
        menu: '#system-menu',
        iconCls: 'icon-custom-lock'
    });
    $($changeThemeMenuButton.menubutton('options').menu).menu({
        onClick: function (item) {
            themeName = item.text.toLowerCase();
            changeTheme(themeName);
        }
    });
    $($exitMenuButton.menubutton('options').menu).menu({
        onClick: function (item) {
            if ("logout-button" == item.name) {
                $('#logout-dialog').dialog('open');
            } else if ("exit-button" == item.name) {
                $("#exit-dialog").dialog('open');
            }
        }
    });
}

/**
 * 初始化菜单栏
 */
function initLeftMenu() {
    var leftMenus = {
        "menus": [
            {
                "menuId": 1, "menuName": "系统管理", "iconCls": "icon-dannis-setting",
                "menus": [
                    {"menuId": 1, "menuName": "用户管理", "iconCls": "icon-dannis-user", "url": "page/userManagement.html"},
                    {"menuId": 1, "menuName": "系统日志", "iconCls": "icon-custom-log", "url": "page/systemSetting.html"}
                ]
            },
            {
                "menuId": 1, "menuName": "运营数据", "iconCls": "icon-dannis-storage",
                "menus": [
                    {"menuId": 1, "menuName": "手机号码", "iconCls": "icon-custom-mobile-phone", "url": "page/mobilePhoneNumberManagement.html"},
                    {"menuId": 1, "menuName": "电话号码", "iconCls": "icon-custom-telephone", "url": "page/telephoneNumberManagement.html"},
                    {"menuId": 1, "menuName": "二手车", "iconCls": "icon-custom-car", "url": "page/default.html"}
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
 * 加载工作面板
 */
function loadWorkbenches() {
    var $workbenches = $("#workbenches");
    $workbenches.find("#userManagementWorkbench").load("page/userManagement.html");
}

/* Initialize calendar */
function initCalendar() {
    $('#cc').calendar({
        border: true,
        weeks: ['六', '一', '二', '三', '四', '五', '日'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        current: new Date()
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

/* 显示当前时间 */
function showCurrentTime() {
    $("#current-time").text(getCurrentTime());
    setTimeout("showCurrentTime()", 1000);
}

/* 获取当前时间字符串 */
function getCurrentTime() {
    var today = new Date();
    var day;
    var date;
    var time;

    if (today.getDay() == 0) {
        day = "星期日";
    } else if (today.getDay() == 1) {
        day = "星期一";
    } else if (today.getDay() == 2) {
        day = "星期二";
    } else if (today.getDay() == 3) {
        day = "星期三";
    } else if (today.getDay() == 4) {
        day = "星期四";
    } else if (today.getDay() == 5) {
        day = "星期五";
    } else if (today.getDay() == 6) {
        day = "星期六";
    }

    var month = today.getMonth();
    month++;
    if (month < '10') {
        month = "0" + month;
    }

    var d = today.getDate();
    if (d < '10') {
        d = "0" + d;
    }

    date = today.getFullYear() + "年" + month + "月" + d + "日";
    var hours = today.getHours();
    var minutes = today.getMinutes();
    var seconds = today.getSeconds();
    if (hours < '10') {
        hours = "0" + hours;
    }
    if (minutes < '10') {
        minutes = "0" + minutes;
    }
    if (seconds < '10') {
        seconds = "0" + seconds;
    }

    time = hours + "时" + minutes + "分" + seconds + "秒";

    return date + " " + day + " " + time;
}

/* 修改主题 */
function changeTheme(themeName) {
    var $easyuiTheme = $("link[id='easyuiTheme']");
    var oldHref = $easyuiTheme.attr("href");
    var newHref = oldHref.substring(0, oldHref.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr("href", newHref);
    customThemeHref = newHref;

    //子页面iframe
    var $iframes = $('iframe');
    if ($iframes.length > 0) {
        var iframeHref = '../' + newHref;
        for (var i = 0; i < $iframes.length; i++) {
            $($iframes[i]).contents().find('link[id="easyuiTheme"]').attr('href', iframeHref);
        }
    }
    $.cookie('easyuiThemeName', themeName, {
        expires: 7
    });
}
function refreshTheme() {
    //子页面iframe
    var $iframes = $('iframe');
    if ($iframes.length > 0) {
        var iframeHref = '../' + customThemeHref;
        for (var i = 0; i < $iframes.length; i++) {
            $($iframes[i]).contents().find('link[id="easyuiTheme"]').attr('href', iframeHref);
        }
    }
}
/**
 * 检查新添加的Tab的主题是否跟系统一致，若不一致则切换至跟系统一样
 * @param title
 */
function checkTheme(title) {
    var $iframes = $('iframe');
    if ($iframes.length > 0) {
        var iframeId = 'iframe' + title;
        for (var i = 0; i < $iframes.length; i++) {
            if (iframeId == $($iframes[i]).attr('id')) {
                $($iframes[i]).contents().find('link[id="easyuiTheme"]').attr('href', '../' + customThemeHref);
            }
        }
    }
}

/**
 * DateBox格式化器
 * @param date 日期对象
 * @returns {string} 格式化日期字符串，如：2014-12-22
 */
function dateBoxFormatter(date) {
    return date ? date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() : "";
}

/**
 * DateBox解析器
 * @param dateString 日期字符串，格式：yyyy-MM-dd
 * @returns {Date} 日期对象
 */
function dateBoxParser(dateString) {
    if (dateString || dateString.trim() != "") {
        return new Date(dateString.replaceAll("-", ","));
    } else {
        return new Date();
    }
}