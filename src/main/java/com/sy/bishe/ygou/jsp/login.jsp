<%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录界面</title>
    <link rel="stylesheet" href="css/inti.css" type="text/css" />
    <link rel="stylesheet" href="css/login.css" type="text/css" />
</head>

<body>

<div id="bg">
<div class='div_logo'>
    <p >用户登录</p>
</div>

<div class='div_form'>

    <form name='login' action='Login' οnsubmit='return validation()' method='post'>
        <div class='div_login_input' id='user'>
            <div id='icon_user'></div>
            <input class='login' id='username' type='text' name='username' placeholder='用户名'></input>
            <span class='hint' id='hint_user'></span>
        </div>

        <div class='div_login_input' id='pwd' >
            <div id='icon_pwd'></div>
            <input class='login' id='password' type='password' name='password' placeholder='请输入密码'></input>
            <span class='hint' id='hint_pwd'></span>
        </div>

        <div class='div_btn'>
            <input id='login_submit'  type='submit'  value='登录'></input>
        </div>
    </form>

</div>

<script src='js/login.js'></script>

</div>
</body>
</html>