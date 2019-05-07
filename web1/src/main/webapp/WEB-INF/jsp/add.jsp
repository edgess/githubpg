<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE>
<html lang="zh-CN">
<head><title>IT设备管理</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link rel="bookmark" href="images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="webjars/jquery-validation/1.17.0/jquery.validate.js"></script>

	<%--<link href="webjars/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" rel="stylesheet">--%>
	<%--<script src="webjars/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>--%>
	<%--<script src="webjars/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>--%>

	<script src="webjars/momentjs/2.22.1/moment.js"></script>
	<link href="script/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="script/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="script/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	
</head>
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
						},
						times : {
							digits : true,
							range : [ 0, 20 ]
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
						},
						times : {
							digits : "必须为数字",
							range : "20以内"
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
</script>
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


		<form class="form-horizontal" id="ff" action="./doadd" method="POST">
			
			<div class="form-group">
				<label class="col-sm-2 control-label">设备编号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p4" />
				</div>
				<label class="col-sm-2 control-label">使用人</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p5" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">出厂编号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p7" />
				</div>
				<label class="col-sm-2 control-label">财务编号</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="cwstr" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">归属部门</label>
				<div class="col-sm-3">
					<select class="form-control" name="p6">
						<c:forEach items="${page2}" var="dep" varStatus="status">
							<option value="${dep.id}">${dep.dept_name}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-2 control-label">设备归属</label>
				<div class="col-sm-3">
					<select class="form-control" name="p3">
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
					<select class="form-control" name="p1">
						<option value="A">正常</option>
						<option value="W">已报废</option>
						<option value="C">将过时</option>
					</select>
				</div>
				<label class="col-sm-2 control-label">设备类型</label>
				<div class="col-sm-3">
					<select class="form-control" name="p2">
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
					<input class="form-control input-sm" type="text" name="p8" />
				</div>
				<label class="col-sm-2 control-label">采购价格</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p9" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p10" />
				</div>
				<label class="col-sm-2 control-label">启用日期</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="p11"  id="dtpicker" readonly />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">提交数量</label>
				<div class="col-sm-3">
					<input class="form-control input-sm" type="text" name="times" placeholder="1" />
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
}).val(moment().format("YYYY-MM-DD"));//初始化input默认值;
</script>
</html>


