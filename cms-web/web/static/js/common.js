var themeName = 'gray';
var customThemeHref = "static/easyui/themes/gray/easyui.css";

/**
 * 初始化切换主题菜单按钮
 */
function initSwitchThemeMenubutton() {
    var $switchThemeMenubutton = $('#switch-theme-menubutton').menubutton({
        menu: '#theme-menu',
        iconCls: 'icon-custom-theme'
    });
    $($switchThemeMenubutton.menubutton('options').menu).menu({
        onClick: function (item) {
            themeName = item.text.toLowerCase();
            switchTheme(themeName);
        }
    });
}

/**
 * 初始化关闭系统菜单按钮
 */
function initShutdownMenubutton() {
    var $exitMenuButton = $("#shutdown-menubutton").menubutton({
        menu: '#shutdown-menu',
        iconCls: 'icon-custom-lock'
    });
    $($exitMenuButton.menubutton('options').menu).menu({
        onClick: function (item) {
            if ("logout-button" == item.name) {
                $.messager.confirm('登出系统','请确认是否登出系统？', function (rs) {
                    if (rs) {
                        //登出系统;
                    }
                });
            } else if ("exit-button" == item.name) {
                $.messager.confirm('关闭系统','请确认是否关闭系统？', function (rs) {
                    if (rs) {
                        //关闭系统;
                    }
                });
            }
        }
    });
}

/**
 * 切换主题
 *
 * @param themeName 主题名称
 */
function switchTheme(themeName) {
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

/**
 * 初始化日历控件
 */
function initCalendar() {
    $('#cc').calendar({
        border: true,
        weeks: ['六', '一', '二', '三', '四', '五', '日'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        current: new Date()
    });
}

/**
 * 显示当前时间
 */
function showCurrentTime() {
    $("#current-time").text(getCurrentTime());
    setTimeout("showCurrentTime()", 1000);
}

/**
 * 获取当前时间字符串
 *
 * @returns {string}
 */
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