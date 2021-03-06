/**
 * 宏恒Js工具类
 * 
 * **/
var hhutil = {
		
		/**
		 * Ajax 异步请求json数据
		 * @url ajax请求的地址
		 * @data ajax 请求的参数
		 * @callback 回调函数，用于处理请求后的业务逻辑
		 */
		ajax : function (url,data,callback){
			$.ajax({
					url: url,
					type: "post",
					dataType: 'json',
					data:this.getDataJson(data),
					timeout: 10000,
					contentType:'application/json;charset=UTF-8',
					beforeSend : function(XMLHttpRequest){
						//发送请求前
//						alert('before');
					},
					success: function(data) {
						if ("function" == typeof callback) callback(JSON.parse(data));
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
//						alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
						//TODO: 处理status， http status code，超时 408 
			        	// 注意：如果发生了错误，错误信息（第二个参数）除了得到null之外，还可能 
			            //是"timeout", "error", "notmodified" 和 "parsererror"。
						//_.request['error'](XMLHttpRequest, textStatus, errorThrown);
			            //if ("function" == typeof error) error(XMLHttpRequest, textStatus, errorThrown);
			            
			            //_.hideLoading();
			        }
				});
			
		},
		
		getDataJson : function(formBean){
			return JSON.stringify(formBean);
		},
		
		//自动封装form
		getFormBean : function(formID){
			var tempData = $('#'+formID).serializeArray();
			var item = new Object() ;
			if(null!=tempData&&''!=tempData&&typeof(tempData)!='undefined'){
				$.each(tempData, function(i, field){
					item[field.name]=field.value;
				});
			}
			return item;
		},
		
		//判断是否为空
		isEmpty : function(value){
			return (null === value || "" === value || undefined === value);
		},
		
		getRootPath : function(){
		    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
		    var curWwwPath=window.document.location.href;
		    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		    var pathName=window.document.location.pathname;
		    var pos=curWwwPath.indexOf(pathName);
		    //获取主机地址，如： http://localhost:8080
		    var localhostPath=curWwwPath.substring(0,pos);
		    //获取带"/"的项目名，如：/nnjssd
//		    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		    return localhostPath;
//		    return(localhostPath+projectName);
		},
		
		parseDate : function(datetime){
			if(!this.isEmpty(datetime)){
				var date = new Date(datetime);
				return date.format("YYYY-MM-DD");
			}
			return null ;
		},
		parseDateMinute : function(datetime){
			if(!this.isEmpty(datetime)){
				var date = new Date(datetime);
				return date.format("YYYY-MM-DD hh:mm");
			}
			return null ;
		},
		checkMobile : function(value){
			//验证手机号码
			var reg = /^(((13[0-9]{1})|(12[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(19[0-9]{1})|)+\d{8})$/;
            return value.length == 11 && reg.test(value);
		},
		checkEmail : function(value){
		    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		    return pattern.test(value);
		},
		getQueryString : function(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) return unescape(r[2]); return null;
		}
};

Date.prototype.format = function(fmt) { //author: meizz 
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"D+": this.getDate(), //日 
		"h+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(Y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}