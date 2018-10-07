<%--
  Created by IntelliJ IDEA.
  User: thk
  Date: 2018/10/6
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <!--<link href="css/layui.css" rel="stylesheet" type="text/css">-->
    <link href="/static/css/bootstrap.min.css" rel=" stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-6 col-lg-9 col-sm-6">
            <form action="${pageContext.request.contextPath}/user/login/" method="post">
                名字： <input class="form-control" type="text" name="name" required/>
                密码： <input class="form-control" type="password" name="password" required/>
                <button type="submit"> 提交</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script src="/static/js/jquery-3.3.1.min.js">
</script>
<script src="/static/js/bootstrap.js"></script>
