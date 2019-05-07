<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

	<%--<link href="webjars/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.min.css" rel="stylesheet">--%>
	<%--<script src="webjars/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>--%>
	<%--<script src="webjars/bootstrap-datepicker/1.5.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>--%>

	<script src="webjars/momentjs/2.22.1/moment.js"></script>
	<link href="script/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="script/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="script/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script>
	$(function() {
		//让当前表单调用validate方法，实现表单验证功能
		$("#ff").validate(
				{
					debug : true, //调试模式，即使验证成功也不会跳转到目标页面
					rules : {
						p11 : {
							required : true,
							dateISO : true
						},
						p4 : "required",
						p9 : {
							required : true,
							digits : true,
							range : [ 0, 500000 ]
						}
					},
					messages : {
						p11 : {
							required : "不能为空",
							dateISO : "格式必须为日期型yyyy-mm-dd"
						},
						p4 : "不能为空",
						p9 : {
							required : "不能为空",
							digits : "必须为数字",
							range : "50万以内"
						}
					},
					errorClass : "error",
					success : 'valid',
					unhighlight : function(element, errorClass, validClass) { //验证通过
						$(element).tooltip('destroy').removeClass(errorClass);
					},
					//highlight: function (element, errorClass, validClass) { //未通过验证
					//TODO
					//},
					errorPlacement : function(error, element) {
						if ($(element).next("div").hasClass("tooltip")) {
							$(element).attr("data-original-title",
									$(error).text()).tooltip("show");
						} else {
							$(element).attr("title", $(error).text()).tooltip(
									"show");
						}
					},
					submitHandler : function(form) {
						form.submit();
					}
				})
	});
	
$(function () { 
	$("#selectdept").val("${page[0].dept_id}");
	$("#selectbelong").val("${page[0].belong}");
	$("#selectstatus").val("${page[0].status}");
	$("#selecttype").val("${page[0].type}");
});
</script>

</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#example-navbar-collapse">
						<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="./dolist">设备查询</a>
				</div>
				<div class="collapse navbar-collapse" id="example-navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="./logout"><span
								class="glyphicon glyphicon-log-out"></span>&nbsp;退出</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<form class="form-horizontal" id="ff" action="./doedit" method="POST">
			<input type="hidden" name="p0" value="${page[0].id}" />
			<div class="form-group">
				<label class="col-sm-2 control-label">设备编号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p4" value="${page[0].equip_no}" />
				</div>
				<label class="col-sm-2 control-label">使用人</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p5" value="${page[0].username}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">出厂编号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p7" value="${page[0].sn}" />
				</div>
				<label class="col-sm-2 control-label">财务编号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="cwstr" value="${page[0].cwstr}" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">归属部门</label>
				<div class="col-sm-3">
					<select class="form-control" id="selectdept" name="p6">
<%-- 						<option selected value="${page[0].dept_id}">${page[0].Dept_name}</option> --%>
						<c:forEach items="${page2}" var="dep" varStatus="status">
							<option value="${dep.id}">${dep.dept_name}</option>
						</c:forEach>
					</select>
				</div>

				<label class="col-sm-2 control-label">设备归属</label>
				<div class="col-sm-3">
					<select class="form-control" id="selectbelong" name="p3">
<%-- 						<option selected value="${page[0].belong}">
							<c:choose>
								<c:when test="${page[0].belong == 'Y'}">国旅资产</c:when>
								<c:when test="${page[0].belong == 'G'}">挂靠设备</c:when>
								<c:when test="${page[0].belong == 'N'}">自有设备</c:when>
								<c:when test="${page[0].belong == 'Z'}">获赠资产</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</option> --%>
						<option value="Y">国旅资产</option>
						<option value="G">挂靠设备</option>
						<option value="N">自有设备</option>
						<option value="Z">获赠资产</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">设备状态</label>
				<div class="col-sm-3">
					<select class="form-control" id="selectstatus" name="p1">
<%-- 						<option selected value="${page[0].status}">
							<c:choose>
								<c:when test="${page[0].status == 'A'}">正常</c:when>
								<c:when test="${page[0].status == 'W'}">已报废</c:when>
								<c:when test="${page[0].status == 'C'}">将过时</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</option> --%>
						<option value="A">正常</option>
						<option value="W">已报废</option>
						<option value="C">将过时</option>
					</select>
				</div>
				<label class="col-sm-2 control-label">设备类型</label>
				<div class="col-sm-3">
					<select class="form-control" id="selecttype" name="p2">
<%-- 						<option selected value="${page[0].type}">
							<c:choose>
								<c:when test="${page[0].type == 'C'}">台式机</c:when>
								<c:when test="${page[0].type == 'N'}">笔记本</c:when>
								<c:when test="${page[0].type == 'S'}">服务器</c:when>
								<c:when test="${page[0].type == 'L'}">激光打</c:when>
								<c:when test="${page[0].type == 'P'}">针式打</c:when>
								<c:when test="${page[0].type == 'M'}">喷墨打</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</option> --%>
						<option value="C">台式机</option>
						<option value="N">笔记本</option>
						<option value="S">服务器</option>
						<option value="L">激光打</option>
						<option value="P">针式打</option>
						<option value="M">喷墨打</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">型号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p8"
						value="${page[0].model}" />
				</div>
				<label class="col-sm-2 control-label">采购价格</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p9"
						value="${page[0].price}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p10"
						value="${page[0].memo}" />
				</div>
				<label class="col-sm-2 control-label">启用日期</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p11" id="dtpicker" readonly
						value="<fmt:formatDate value="${page[0].startuse}" pattern="yyyy-MM-dd"/>" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label"></label>
				<div class="col-sm-3">

				</div>
				<label class="col-sm-2 control-label"></label>
				<div class="col-sm-3">
					<button class="btn btn-sm btn-primary" type="submit"><span class="glyphicon glyphicon-ok"></span>提交</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script>
var myDate = new Date();
$("#dtpicker").datetimepicker({
    format: 'yyyy-mm-dd',  //设置时间展现格式
    weekStart: 1,          //设置起始周
    //startDate: myDate,     //开始时间
    endDate: myDate,         //结束时间
    autoclose: true,       //选完时间后是否自动关闭,默认值：false
    startView: 3,          //首先显示的视图,0小时,1天,2月,3年,4十年,默认值：2,
    minView: 2,            //最精确的时间,0小时,1天,2月,3年,4十年,默认值：0,
    //todayBtn: true,      //当天日期按钮,默认值：false
    language: "zh-CN",     //语言 默认值："en",中文："zh-CN"
    //forceParse: true,      //是否强制解析时间格式和类型
});
</script>
</html>


