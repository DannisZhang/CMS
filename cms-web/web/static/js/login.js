/**
 * @author Dannis
 * @date 2015-05-14
 */
$(function () {
    initLoginPage();
});

/**
 * 初始化登陆页面
 */
function initLoginPage() {
    initLoginPageTopMenu();
}

/**
 * 初始化登陆页面顶部菜单
 */
function initLoginPageTopMenu() {
    initSwitchThemeMenubutton();
}

function login() {
    var $loginForm = $("#login-form");
    $loginForm.form("submit", {
        url: "auth/login.ajax",
        onSubmit: function () {
            var username = $loginForm.find("input[name='username']").val();
            if (!username) {
                $.messager.alert("错误提示", "请输入用户名", "warning");
                return false;
            }
            var password = $loginForm.find("input[name='password']").val();
            if (!password) {
                $.messager.alert("错误提示", "请输入密码", "warning");
                return false;
            }
        },
        success:function(result) {
            var jsonResult = $.parseJSON(result);
            if (jsonResult.success) {
                window.location.href = jsonResult.message;
            }
        }
    });
}
