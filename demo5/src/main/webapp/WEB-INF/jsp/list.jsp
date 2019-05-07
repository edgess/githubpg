<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="script/node_modules/jquery/dist/jquery.min.js"></script>
<script src="script/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- <script src="script/dist/vendor.js"></script> -->

<link rel="stylesheet" href="script/node_modules/bootstrap/dist/css/bootstrap.min.css">
<script src="script/layer-3.1.1/dist/layer.js"></script>
<script type="text/javascript">
$(function() {
	//找到tr节点
	$(".withedit").click(function() {
		var url='./edit?id='+$(this).children('td').eq(0).text();
		var index = layer.open({
			  type: 2,
			  title: "修改", //标题
			  content: url,
			  area: ['350px', '300px'],
			  maxmin: true,
			  end: function () { //关闭后回调函数
				  window.location.reload();
	            }
			});
	});
});
function add(){
	var url='./add';
	var index = layer.open({
		  type: 2,
		  title: "添加", //标题
		  content: url,
		  area: ['350px', '300px'],
		  maxmin: true,
		  end: function () { //关闭后回调函数
			  window.location.reload();
          }
		});
};
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:if test="${page != null && page.numberOfElements > 0 }">
			<table class="table table-hover table-bordered">
				<tr>
					<th width="20%">ID</th>
					<th width="20%">mile</th>
					<th width="20%">cash</th>
					<th width="20%">price</th>
					<th width="20%">date</th>
				</tr>
				<c:forEach items="${page.content }" var="oil">
					<tr class="withedit">
						<td class="oilID">${oil.id }</td>
						<td>${oil.mile }</td>
						<td>${oil.cash }</td>
						<td>${oil.price }</td>
						<td><fmt:formatDate value="${oil.date }" pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="center"><a
						href="?pageNo=${page.number + 1 - 1 }" class="btn btn-info ">&lt;上一页</a>
						共 ${page.totalElements } 条记录 共${page.totalPages } 页
						当前${page.number + 1 } 页 <a href="?pageNo=${page.number + 1 + 1 }"
						class="btn btn-info "> 下一页&gt;</a> <a href="javascript:void(0)" onclick="add()"
						class="btn btn-primary ">ADD</a>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>