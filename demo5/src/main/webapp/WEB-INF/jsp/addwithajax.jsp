<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="script/node_modules/jquery/dist/jquery.min.js"></script>
<script src="script/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/node_modules/bootstrap/dist/css/bootstrap.min.css">
<script src="script/layer-3.1.1/dist/layer.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("./getwithAjax", {}, function(data) {
			$(".mile").val(data.mile);
			$(".cash").val(data.cash);
			$(".price").val(data.price);
		});

		$("#btn").click(function(event) {
			var mile = $(".mile").val();
			var cash = $(".cash").val();
			var price = $(".price").val();
			var args = {
				"mile" : mile,
				"cash" : cash,
				"price" : price
			};
			var url = "./savewithAjax";
			$("#btn").attr("disabled", true);
			$.post(url, args, function(data) {
				if (data == 1) {
					layer.msg('success', {
						time : 1500
					});
					setTimeout(function(){
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭   
					}, 1000);
				} else {
					layer.msg('error', {
						time : 1500
					});
					setTimeout(function(){
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭   
					}, 1000);
				}
			});

		});

	})
</script>

</head>
<body>

	<div class="form-group">
		<label>mile:</label> <input class="mile" class="form-control" />
	</div>
	<div class="form-group">
		<label>cash:</label> <input class="cash" class="form-control" />
	</div>
	<div class="form-group">
		<label>price:</label> <input class="price" class="form-control" />
	</div>
	<div class="form-group">
		<button id="btn" type="submit" class="btn btn-default">Submit</button>
	</div>
</body>

</html>