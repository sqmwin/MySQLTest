<%--
  Created by IntelliJ IDEA.
  User: sqm
  Date: 2017-11-29
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/loginservlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="code" id="code"></td>
        </tr>
        <tr>
            <td colspan="2"><a href="#" onclick="changeCode()">
                <img src="${pageContext.request.contextPath}/verificationservlet" alt="点击下一张" id="verificationcode"></a></td>
        </tr>
        <tr>
            <td><input type="submit" value="登陆"></td>
            <td>${requestScope["login.message"]}</td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    function changeCode() {
        var code = document.getElementById("verificationcode");
        code.src="${pageContext.request.contextPath}/verificationservlet?" + new Date().getTime();
    }
</script>
</html>
