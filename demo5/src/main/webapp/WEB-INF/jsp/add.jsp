<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="script/node_modules/jquery/dist/jquery.min.js"></script>
<script src="script/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/node_modules/bootstrap/dist/css/bootstrap.min.css">
<script src="script/layer-3.1.1/dist/layer.js"></script>
<title>Insert title here</title>
<script>
	function doadd() {
		var updata = {
			mile : $("#mile").val(),
			cash : $("#cash").val(),
			price : $("#price").val(),
		};
		$.getJSON("./addedit", updata, function(result) {
		});
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	};
</script>
</head>
<body>

<form class="form-horizontal">
    <div class="form-group" >
        <label class="col-xs-offset-1 col-xs-3 control-label">mile</label>
        <div class="col-xs-7" >
            <input class="form-control" id="mile" type="text" name="mile" value="${requestScope.oil.mile }">
        </div>
    </div>
    
    <div class="form-group" >
        <label class="col-xs-offset-1 col-xs-3 control-label">cash</label>
        <div class="col-xs-7" >
            <input  class="form-control" id="cash" type="text" name="cash" value="${requestScope.oil.cash }">
        </div>
    </div>

    <div class="form-group" >
        <label class="col-xs-offset-1 col-xs-3 control-label">price</label>
        <div class="col-xs-7" >
            <input class="form-control" id="price" type="text" name="price" value="${requestScope.oil.price }">
        </div>
    </div>
        
    <div class="form-group">
        <div class="col-xs-offset-4 col-xs-1">
            <button class="btn btn-primary" onclick='doadd()'>添加</button>
        </div>
    </div>
</form>

</body>
</html>