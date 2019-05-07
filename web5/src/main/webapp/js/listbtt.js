var myDate = new Date();
$("#myModalstartuse").datetimepicker({
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
var validatorInit = function(){
	$("#editform").bootstrapValidator({
	    message: '输入值不合法',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	        p4: {
	        	container: '#errlableequipno',
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
	                stringLength: {
	                    min: 3,
	                    max: 30,
	                    message: '请输入3到30个字符'
	                }
	            }
	        },
	        p9: {
	        	container: '#errlableprice',
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
	                between: {
	                    min: 1,
	                    max: 500000,
	                    message: '请输50万以内数字'
	                }
	            }
	        },
	        p11: {
	        		container: '#errlabledate',
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
	                date: {//验证指定的日期格式
	                    format: 'YYYY-MM-DD',
	                    message: '格式必须为日期型yyyy-mm-dd'
	                }
	            }
	        }
	    }
	});
};
//$(document).ready(function() { }
$(function() {
	//判断是否登入
	$.getJSON("logincheck",function(result){
		if(result.status=="101"){
			window.location.href='login.html'
		} 
	})
	
	$.getJSON("getdept",function(data){
		var optionstring = "";
		for (i=0;i<data.length;i++){
			optionstring += "<option value=\""+  data[i].id +"\" >"+ data[i].dept_name +"</option>";
		}; 
		$("#p6").append(optionstring);
		
		//添加模态框内的option
  		$("#myModaldept").append(optionstring); 
        $("#myModalbelong")
        		.append("<option value=\"Y\">国旅资产</option><option value=\"G\">挂靠设备</option><option value=\"N\">自有设备</option><option value=\"Z\">获赠资产</option>"); 
       
        $("#myModalstatus")
        		.append("<option value=\"A\">正常</option><option value=\"W\">已报废</option><option value=\"C\">将过时</option>"); 
       
        $("#myModaltype")
        		.append("<option value=\"C\">台式机</option><option value=\"N\">笔记本</option><option value=\"S\">服务器</option><option value=\"L\">激光打</option><option value=\"P\">针式打</option><option value=\"M\">喷墨打</option>"); 

	});	
//	$.ajax({
//		url : "getdept",
//		type : "post",
//		dataType: "json",
//		async: false, 
//		success : function(data) {
//
//			var optionstring = "";
//			for (i=0;i<data.length;i++){
//				optionstring += "<option value=\""+  data[i].id +"\" >"+ data[i].dept_name +"</option>";
//			}; 
//			$("#p6").append(optionstring);
//			
//			//添加模态框内的option
//	  		$("#myModaldept").append(optionstring); 
//	        $("#myModalbelong")
//	        		.append("<option value=\"Y\">国旅资产</option><option value=\"G\">挂靠设备</option><option value=\"N\">自有设备</option><option value=\"Z\">获赠资产</option>"); 
//	       
//	        $("#myModalstatus")
//	        		.append("<option value=\"A\">正常</option><option value=\"W\">已报废</option><option value=\"C\">将过时</option>"); 
//	       
//	        $("#myModaltype")
//        			.append("<option value=\"C\">台式机</option><option value=\"N\">笔记本</option><option value=\"S\">服务器</option><option value=\"L\">激光打</option><option value=\"P\">针式打</option><option value=\"M\">喷墨打</option>"); 
//
//		}
//	}); 
	
	$("#btn_add").click(function() {
		//有选择就倒入数据，方便填写相同数据
         var arrselections = $("#table").bootstrapTable('getSelections');
	     if (arrselections.length == 1) {
		     $("#myModalid").val(arrselections[0].id);
		     $("#myModalequip_no").val(arrselections[0].equip_no);
		     $("#myModalusername").val(arrselections[0].username);
		     $("#myModalsn").val(arrselections[0].sn);
		     $("#myModalcwstr").val(arrselections[0].cwstr);
		     $("#myModalprice").val(arrselections[0].price);
		     $("#myModalmodel").val(arrselections[0].model);
		     $("#myModalstartuse").val(arrselections[0].startuse);
		     $("#myModalmemo").val(arrselections[0].memo);
		     $("#myModaldept").val(arrselections[0].dept_id);
		     $("#myModalbelong").val(arrselections[0].belong);
		     $("#myModalstatus").val(arrselections[0].status);
		     $("#myModaltype").val(arrselections[0].type);
	     };
	     $("#myModalLabel").text("新增");
	     $("#myModalbtn").attr("onclick","addone()");
	     $('#myModal').modal('show');
	});
	
	$("#btn_edit").click(function() {
	    var arrselections = $("#table").bootstrapTable('getSelections');
	//      if (arrselections.length > 1) {
	//          toastr.warning('只能选择一行进行编辑');
	//          return;
	//      }
	     if (arrselections.length <= 0) {
	         toastr.warning('请选择有效数据');
	         return;
	     };
	     $("#myModalLabel").text("修改");
	     $("#myModalid").val(arrselections[0].id);
	     $("#myModalequip_no").val(arrselections[0].equip_no);
	     $("#myModalusername").val(arrselections[0].username);
	     $("#myModalsn").val(arrselections[0].sn);
	     $("#myModalcwstr").val(arrselections[0].cwstr);
	     $("#myModalprice").val(arrselections[0].price);
	     $("#myModalmodel").val(arrselections[0].model);
	     $("#myModalstartuse").val(arrselections[0].startuse);
	     $("#myModalmemo").val(arrselections[0].memo);
	     $("#myModaldept").val(arrselections[0].dept_id);
	     $("#myModalbelong").val(arrselections[0].belong);
	     $("#myModalstatus").val(arrselections[0].status);
	     $("#myModaltype").val(arrselections[0].type);
	     $("#myModalbtn").attr("onclick","editone()");
	     $('#myModal').modal('show');
	});
	
	$('#myModal').on('hidden.bs.modal', function () {
		 //移除上次的校验配置
		 $("#editform").data('bootstrapValidator').destroy();
		 $("#editform").data('bootstrapValidator',null);
		//移除颜色
		 $("div.form-group").removeClass("has-feedback has-success has-error");
	     $("#myModalid").val("");
	     $("#myModalequip_no").val("");
	     $("#myModalusername").val("");
	     $("#myModalsn").val("");
	     $("#myModalcwstr").val("");
	     $("#myModalprice").val("");
	     $("#myModalmodel").val("");
	     $("#myModalstartuse").val("");
	     $("#myModalmemo").val("");
	     $("#myModaldept").val("");
	     $("#myModalbelong").val("");
	     $("#myModalstatus").val("");
	     $("#myModaltype").val("");
		 //重新添加校验
		 var onValidatorInit = new validatorInit();
	});
	
	$("#btn_delete").click(function() {
        var arrselections = $("#table").bootstrapTable('getSelections');
//      if (arrselections.length > 1) {
//          toastr.warning('只能选择一行进行编辑');
//          return;
//      }
		if (arrselections.length <= 0) {
		    toastr.warning('请选择有效数据');
		    return;
		};
		 $("#myModalconfLabel").text("删除");
		 $("#myModalconfbody").text("删除设备编号:"+arrselections[0].equip_no);
		 $("#myModalconfbtn").attr("onclick","delone("+arrselections[0].id+")");
		 $("#myModalconf").modal();
	});
	
	//初始化验证器
	var onValidatorInit = new validatorInit();
}); 

function searchall(){
	$("#p1").val("");
	$("#p2").val("");
	$("#p3").val("");
	$("#p4").val("");
	$("#p5").val("");
	$("#p6").val("");
	$("#p7").val("");
	$('#table').bootstrapTable('refresh');
};

function getfile(){
	document.getElementById('search_form').setAttribute('action','./getfile');
	document.getElementById('search_form').submit();
};

function delone(id){
	$.getJSON("deljson",{delID:id},function(data){
  		if(data.status=="200"){
  			$('#table').bootstrapTable('refresh'); 
  		}else{
  			alert("服务端错误");
  		};		
	});
};

function addone(){
    //alert($("#editform").serialize());
	var updata = {
            p1:$("#myModalstatus").val(),
            p2:$("#myModaltype").val(),
            p3:$("#myModalbelong").val(),
            p4:$("#myModalequip_no").val(),
            p5:$("#myModalusername").val(),
            p6:$("#myModaldept").val(),
            p7:$("#myModalsn").val(),
            p8:$("#myModalmodel").val(),
            p9:$("#myModalprice").val(),
            p10:$("#myModalmemo").val(),
            p11:$("#myModalstartuse").val(),
            cwstr:$("#myModalcwstr").val()
        };
	
	var bv = $("#editform").data('bootstrapValidator');
    bv.validate();
    if (bv.isValid()) {
        //发送ajax请求
	  	$.getJSON("addjson",updata,function(data){
	  		if(data.status=="200"){
	  			$('#myModal').modal('hide');
	  			$('#table').bootstrapTable('refresh'); 
	  		}else{
	  			alert("服务端错误1");
	  		};
	  	});
    }else{
    		$("#returnMessage").hide().html('<label class="label label-danger">请正确填写!</label>').show();
    		setTimeout("$('#returnMessage').hide().html('<label class=\"label label-danger\">请正确填写!</label>').hide()",2000);
    };
};

function editone(){
	var updata = {
            p0:$("#myModalid").val(),
            p1:$("#myModalstatus").val(),
            p2:$("#myModaltype").val(),
            p3:$("#myModalbelong").val(),
            p4:$("#myModalequip_no").val(),
            p5:$("#myModalusername").val(),
            p6:$("#myModaldept").val(),
            p7:$("#myModalsn").val(),
            p8:$("#myModalmodel").val(),
            p9:$("#myModalprice").val(),
            p10:$("#myModalmemo").val(),
            p11:$("#myModalstartuse").val(),
            cwstr:$("#myModalcwstr").val()
        };
	
	var bv = $("#editform").data('bootstrapValidator');
    bv.validate();
    if (bv.isValid()) {
        //发送ajax请求
	  	$.getJSON("editjson",updata,function(data){
	  		if(data.status=="200"){
		    		$('#myModal').modal('hide');
		    		$('#table').bootstrapTable('refresh'); 
	  		}else{
	  			alert("服务端错误");
	  		};	  		
	  	});
    }else{
    		$("#returnMessage").hide().html('<label class="label label-danger">请正确填写!</label>').show();
    		setTimeout("$('#returnMessage').hide().html('<label class=\"label label-danger\">请正确填写!</label>').hide()",2000);
    };
};

$('#table').bootstrapTable({
    url: 'listjson',
    toolbar: '#toolbar',             //工具按钮用哪个容器
    dataField: 'rows',				//这是返回的json数组的key.默认好像是"rows".这里只有前后端约定好就行
    pageNumber: 1, 					//初始化加载第一页，默认第一页
    pagination: true,				//是否分页
    sidePagination:'server',			//指定服务器端分页
    pageSize: 10,                    //每页的记录行数（*）
    pageList: [10,20,50],			//可供选择的每页的行数（*）
    showRefresh: true,               //是否显示刷新按钮
    showToggle:true,                 //是否显示详细视图和列表视图的切换按钮
    showColumns: true,               //是否显示所有的列
    toolbar : "#toolbar",			//工具按钮用哪个容器
    //uniqueId: "id",                  //每一行的唯一标识，一般为主键列
    singleSelect: true, 				//设置True 将禁止多选
    //clickToSelect: true, 			//设置true 将在点击行时，自动选择rediobox 和 checkbox
    queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求

        return {
			pageNo:params.offset/params.limit+1,
			pageSize: params.limit, // 每页要显示的数据条数
            //offset: params.offset, // 每页显示数据的开始行号
            //sort: params.sort, // 要排序的字段
            //sortOrder: params.order, // 排序规则
            p1: $("#p1").val(), // 额外添加的参数
            p2: $("#p2").val(), // 额外添加的参数
            p3: $("#p3").val(), // 额外添加的参数
            p4: $("#p4").val(), // 额外添加的参数
            p5: $("#p5").val(), // 额外添加的参数
            p6: $("#p6").val(), // 额外添加的参数
            p7: $("#p7").val(), // 额外添加的参数
        }
    },
    columns: [{
		checkbox: true
    },{
        field: 'equip_no',
        title: '设备编号',
        	width : '15%'
    }, {
        field: 'username',
        title: '使用人',
    		width : '15%'
    }, {
        field: 'Dept_name',
        title: '归属部门',
    		width : '15%'
    }, {
        field: 'sn',
        title: '出厂编号',
    		width : '15%'
    }, {
        field: 'status',
        title: '设备状态',
    		width : '10%',
        formatter: function (value, row, index){ // 单元格格式化函数
            var text = '-';
            if (value == 'A') {
                text = "正常";
            } else if (value == 'W') {
                text = "已报废";
            } else if (value == 'C') {
                text = "将过时";
            }else {
                text = "未知";
            }
            return text;
        }
    }, {
        field: 'type',
        title: '设备类型',
  	  	width : '10%',
        formatter: function (value, row, index){ // 单元格格式化函数
            var text = '-';
            if (value == 'C') {
                text = "台式机";
            } else if (value == 'N') {
                text = "笔记本";
            } else if (value == 'S') {
                text = "服务器";
            }else if (value == 'L') {
                text = "激光打";
            }else if (value == 'P') {
                text = "针式打";
            }else if (value == 'M') {
                text = "喷墨打";
            }else {
                text = "未知";
            }
            return text;
        }
    }, {
        field: 'belong',
        title: '设备归属',
        width : '10%',
        formatter: function (value, row, index){ // 单元格格式化函数
            var text = '-';
            if (value == 'Y') {
                text = "国旅资产";
            } else if (value == 'G') {
                text = "挂靠设备";
            } else if (value == 'N') {
                text = "自有设备";
            }else if (value == 'Z') {
                text = "获赠资产";
            }else {
                text = "未知";
            }
            return text;
        }
    }
//     , {
//         field: 'id',
//         title: '操作',
//         formatter: function (value, row, index){ // 单元格格式化函数
// 	        	var id = value;
// 			var result = "";
// 			result += "<a href='javascript:;' class='btn btn-xs btn-success' onclick=\"EditViewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
// 			result += "<a href='javascript:;' class='btn btn-xs btn-primary' onclick=\"EditViewById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
// 			result += "<a href='javascript:;' class='btn btn-xs btn-danger'  onclick=\"\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
// 			//delone('" + id + "','" + row.equip_no + "')
// 			return result;
//         }
//     }
    ],
//     onDblClickRow: function (row, $element) {
//     		$("#btn_edit").click();
//     },
});
