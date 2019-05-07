<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>IT设备管理</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link rel="bookmark" href="images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="webjars/jquery-validation/1.17.0/jquery.validate.js"></script>
</head>
<body>
<div class="container">
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#example-navbar-collapse">
            <span class="sr-only">切换导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="./dolist">设备查询</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
			<li><a href="./logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;退出</a></li>
        </ul>
    </div>
    </div>
</nav>
<h1>生成标签贴纸</h1>
<form class="form-horizontal" action="./gettagpaper" method="POST">
	<label class="control-label">标签起始：[默认&nbsp;1001-1024&nbsp;共24张]</label>
	<input  class="form-control" type="text" name="equpstart" />
	<label class="control-label">标签URL：[默认&nbsp;http://192.168.10.30:12380/it/getone?equipno=SATC]</label>
	<input  class="form-control" type="text" name="equpstr" />
	<div style="padding: 20px;"></div>
	<button class="btn btn-sm btn-primary" type="submit" onclick="javascript:return confirm('确定生成标签贴纸吗?')"><span class="glyphicon glyphicon-ok"></span>提交</button>
</form>
</div>
</body>
</html>


