
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link id="css" rel="stylesheet" type="text/css" href="/style.css"/>
    <title>登录</title>
</head>

<body  background="timg.png" >

<div id="bg"   >
<div class='div_logo'>
    <p >用户登录</p>
</div>

<div class='div_form'>

    <form name='login' action='/admin/login' method='post'>
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
            <input id='login_submit'  type='submit'  value='登录' style="color: #0096e6"></input>
        </div>
    </form>

</div>

</div>
</body>
</html>