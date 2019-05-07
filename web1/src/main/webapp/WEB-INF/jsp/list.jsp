<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
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

	<script>
	function getfile(){
		document.getElementById('search_form').setAttribute('action','./getfile');
		document.getElementById('search_form').submit();
		document.getElementById('search_form').setAttribute('action','./dolist');
	};
	function delone(pageNo,id){
		document.getElementById('search_form').setAttribute('action','./del');
		document.getElementById('pgset').setAttribute('value', pageNo);
		document.getElementById('pgdel').setAttribute('value', id);
		document.getElementById('search_form').submit(); 
	};	
</script>

</head>
<body>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">确认框</h4>
            </div>
            <div class="modal-body">
            <h5 class="modal-title" id="myModalrow"></h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="myModalbtnconf" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- 模态框（Modal） -->

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
        <ul class="nav navbar-nav">
            <li>
            		<a href="javascript:void(0)" onclick='$("#myModalrow").text("确定生成电子台账吗 ？");$("#myModalbtnconf").attr("onclick","getfile()");$("#myModal").modal();'>
            		<span class="glyphicon glyphicon-file"></span>&nbsp;生成电子台账</a>
            	</li>
            <li><a href="./tagpaper"><span class="glyphicon glyphicon-qrcode"></span>&nbsp;生成标签贴纸</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
        		<li><a  style="pointer-events: none">您好:${name}</a></li>
        		<li><a href="./logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;退出</a></li>
        </ul>
        
    </div>
    </div>
</nav>

	<form id="search_form"  class="form-horizontal" action="./dolist" method="POST">

		<!-- 隐藏input -->
		<input id="pgset" type="hidden" name="pageNo" value="" />
		<input id="pgdel" type="hidden" name="pagedel" value="" />

		<div class="form-group">
			<label class="col-sm-2 control-label">设备编号</label>
			<div class="col-sm-3">
			<input class="form-control input-sm" id="inputequip_no" type="text" name="p4"/>
			</div>
			<label class="col-sm-2 control-label">使用人</label>
			<div class="col-sm-3">
			<input class="form-control input-sm" id="inputusername" type="text" name="p5"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label">出厂编号</label>
			<div class="col-sm-3">
			<input class="form-control input-sm" id="inputsn" type="text" name="p7"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label">归属部门</label>
			<div class="col-sm-3" >
				<select class="form-control" id="selectdept" name="p6">
<%-- 					<option selected value="${page3.p6}">
						<!-- 回显只有部门号，遍历取名字 -->
						<c:forEach items="${page2}" var="dep" varStatus="status">
							<c:if test="${page3.p6 == dep.id}">${dep.dept_name}</c:if>
						</c:forEach>
						<c:if test="${page3.p6 == '999'}">管理部门</c:if>
						<c:if test="${page3.p6 == ''}">全部</c:if>
					</option> --%>
					<option value="">全部</option>
					<option value="999">管理部门</option>
					<c:forEach items="${page2}" var="dep" varStatus="status">
						<option value="${dep.id}">${dep.dept_name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-sm-2 control-label">设备归属</label>
			<div class="col-sm-3" >
				<select class="form-control" id="selectbelong" name="p3">
<%-- 					<option selected value="${page3.p3}">
						<c:choose>
							<c:when test="${page3.p3 == 'Y'}">国旅资产</c:when>
							<c:when test="${page3.p3 == 'G'}">挂靠设备</c:when>
							<c:when test="${page3.p3 == 'N'}">自有设备</c:when>
							<c:when test="${page3.p3 == 'Z'}">获赠资产</c:when>
							<c:otherwise>全部</c:otherwise>
						</c:choose>
					</option> --%>
					<option value="">全部</option>
					<option value="Y">国旅资产</option>
					<option value="G">挂靠设备</option>
					<option value="N">自有设备</option>
					<option value="Z">获赠资产</option>
				</select> 
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">设备状态</label>
			<div class="col-sm-3" >
				<select class="form-control" id="selectstatus" name="p1">
<%-- 					<option selected value="${page3.p1}">
						<c:choose>
							<c:when test="${page3.p1 == 'A'}">正常</c:when>
							<c:when test="${page3.p1 == 'W'}">已报废</c:when>
							<c:when test="${page3.p1 == 'C'}">将过时</c:when>
							<c:otherwise>全部</c:otherwise>
						</c:choose>
					</option> --%>
					<option value="">全部</option>
					<option value="A">正常</option>
					<option value="W">已报废</option>
					<option value="C">将过时</option>
				</select>
			</div>
			<label class="col-sm-2 control-label">设备类型</label> 
			<div class="col-sm-3" >
				<select class="form-control" id="selecttype" name="p2">
<%-- 					<option selected value="${page3.p2}">
						<c:choose>
							<c:when test="${page3.p2 == 'C'}">台式机</c:when>
							<c:when test="${page3.p2 == 'N'}">笔记本</c:when>
							<c:when test="${page3.p2 == 'S'}">服务器</c:when>
							<c:when test="${page3.p2 == 'L'}">激光打</c:when>
							<c:when test="${page3.p2 == 'P'}">针式打</c:when>
							<c:when test="${page3.p2 == 'M'}">喷墨打</c:when>
							<c:otherwise>全部</c:otherwise>
						</c:choose>
					</option> --%>
					<option value="">全部</option>
					<option value="C">台式机</option>
					<option value="N">笔记本</option>
					<option value="S">服务器</option>
					<option value="L">激光打</option>
					<option value="P">针式打</option>
					<option value="M">喷墨打</option>
				</select>
			</div>
		</div>
	</form>
	<div  class="row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-3">
			<button class="btn btn-primary btn-sm" onclick="document.getElementById('search_form').setAttribute('action','./dolist');document.getElementById('search_form').submit();"><span class="glyphicon glyphicon-search"></span>查询</button>
		</div>
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-3">
			<button class="btn btn-info btn-sm" onclick="javascript:window.location.href='./add'"><span class="glyphicon glyphicon-plus"></span>添加</button>
		</div>
	</div>		
	<div style="padding: 20px;"></div>
	<c:if test="${!empty page}">
		
		<!--a标签设定隐藏input的value，并且提交表单 -->
		<a href="javascript:void(0)"
			onclick="document.getElementById('pgset').setAttribute('value',${page3.pageNo-1==0 ? 1 : page3.pageNo-1});document.getElementById('search_form').submit();">&lt;上一页</a> 
		 	
		 	--共 ${page3.resultCount} 条记录-
		 	
		<c:forEach var="i" begin="1" end="${page3.pageCount}" step="1">
			<a href="javascript:void(0)"
				onclick="document.getElementById('pgset').setAttribute('value',${i});document.getElementById('search_form').submit();">
				${i}</a>
		</c:forEach> 
		 	
		 	-当前第${page3.pageNo } 页--
		 	
		<a href="javascript:void(0)" onclick="document.getElementById('pgset').setAttribute('value',${page3.pageNo==page3.pageCount ? page3.pageNo : page3.pageNo+1});document.getElementById('search_form').submit();">
			下一页&gt;</a>
	
		<table class="table table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th width="15%">设备编号</th>
					<th width="15%">使用人</th>
					<th width="15%">归属部门</th>
					<th width="15%">出厂编号</th>
					<th width="10%">设备状态</th>
					<th width="10%">设备类型</th>
					<th width="10%">设备归属</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="iteq" items="${page}">
				<tr>
					<td><c:out value="${iteq.equip_no}" /></td>
					<td><c:out value="${iteq.username}" /></td>
					<td><c:out value="${iteq.Dept_name}" /></td>
					<td><c:out value="${iteq.sn}" /></td>
					<td><c:choose>
							<c:when test="${iteq.status == 'A'}">正常</c:when>
							<c:when test="${iteq.status == 'W'}">已报废</c:when>
							<c:when test="${iteq.status == 'C'}">将过时</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${iteq.type == 'C'}">台式机</c:when>
							<c:when test="${iteq.type == 'N'}">笔记本</c:when>
							<c:when test="${iteq.type == 'S'}">服务器</c:when>
							<c:when test="${iteq.type == 'L'}">激光打</c:when>
							<c:when test="${iteq.type == 'P'}">针式打</c:when>
							<c:when test="${iteq.type == 'M'}">喷墨打</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${iteq.belong == 'Y'}">国旅资产</c:when>
							<c:when test="${iteq.belong == 'G'}">挂靠设备</c:when>
							<c:when test="${iteq.belong == 'N'}">自有设备</c:when>
							<c:when test="${iteq.belong == 'Z'}">获赠资产</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose></td>
					<td>
						<!-- 添加详细按钮 -->
				    		<a href="javascript:void(0)" class='btn btn-xs btn-success' data-html="true" data-toggle="tooltip" title="价格:${iteq.price}<br/>型号:${iteq.model}<br/>启用:<fmt:formatDate value="${iteq.startuse}" pattern="yyyy-MM-dd"/><br/>备注:${iteq.memo}"><span class="glyphicon glyphicon-search"></span></a>
						<!-- 添加修改按钮 -->
						<a href="./edit?id=${iteq.id}" class='btn btn-xs btn-primary' data-toggle="tooltip" title="修改"><span class="glyphicon glyphicon-pencil"></span></a>
						<!-- 添加删除按钮 -->
						<!-- 修改form的action，插入id和pageNo到隐藏input，提交到del，为了删除后的回现问题 -->
						<a href="javascript:void(0)" class='btn btn-xs btn-danger' onclick='$("#myModalrow").text("删除设备编号:${iteq.equip_no}");$("#myModalbtnconf").attr("onclick","delone(\"${page3.pageNo}\",\"${iteq.id}\")");$("#myModal").modal();'>
							<span data-toggle="tooltip" title="删除" class="glyphicon glyphicon-remove"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>
<script>
$(function () { 
	$("#inputequip_no").val("${page3.p4}");
	$("#inputusername").val("${page3.p5}");
	$("#inputsn").val("${page3.p7}");
	$("#selectdept").val("${page3.p6}");
	$("#selectbelong").val("${page3.p3}");
	$("#selectstatus").val("${page3.p1}");
	$("#selecttype").val("${page3.p2}");
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
});
</script>
</body>
</html>