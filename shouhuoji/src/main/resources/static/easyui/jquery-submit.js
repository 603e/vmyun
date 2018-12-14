(function($) {
	// 扩张Array的push()方法，使数组内的数据不重复。
	Array.prototype.pushStrEx = function(obj) {
		var a = true;
		for ( var i = 0; i < this.length; i++) {
			if (this[i] == obj) {
				this[i] = obj;
				a = false;
				break;
			}
		}
		if (a) {
			this.push(obj);
		}
		return this.length;
	};
	
	var escapeable = /["\\\x00-\x1f\x7f-\x9f]/g, meta = {
		'\b' : '\\b',
		'\t' : '\\t',
		'\n' : '\\n',
		'\f' : '\\f',
		'\r' : '\\r',
		'"' : '\\"',
		'\\' : '\\\\'
	};

	var replaceEscape = function(string) {
		if (string.match(escapeable)) {
			return '"'
					+ string.replace(escapeable, function(a) {
						var c = meta[a];
						if (typeof c === 'string') {
							return c;
						}
						c = a.charCodeAt();
						return '\\u00' + Math.floor(c / 16).toString(16)
								+ (c % 16).toString(16);
					}) + '"';
		}
		return string;
	};
	
	$.getJson = function(dataDomainName){
		return getJson(dataDomainName);
	};
	
	function getJson(dataDomainName){
		var jsonStr = "{";
		var passwdInputArray = [];
		$("#"+dataDomainName+" input[type='password']").each(function(i){
			passwdInputArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<passwdInputArray.length; i++){
			var passwdJsonStr = '';
			var passwdInputObjs = $("#"+dataDomainName+" input[name='"+passwdInputArray[i]+"']");
			if(passwdInputObjs.length <= 1){
				passwdInputObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).val()) + '",';
						}
						else{
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
			}
			else{
				passwdJsonStr = '"' + passwdInputArray[i] + '":[';
				passwdInputObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							passwdJsonStr += '"' + replaceEscape($(this).val()) + '",';
						}
						else{
							passwdJsonStr += '"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
				jsonStr += passwdJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + '],';
			}
		}
		var textInputArray = [];
		$("#"+dataDomainName+" input[type='text']").each(function(i){
			textInputArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<textInputArray.length; i++){
			var textJsonStr = '';
			var textInputObjs = $("#"+dataDomainName+" input[name='"+textInputArray[i]+"']");
			if(textInputObjs.length <= 1){
				textInputObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).val()) + '",';
						}
						else{
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
			}
			else{
				textJsonStr = '"' + textInputArray[i] + '":[';
				textInputObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							textJsonStr += '"' + replaceEscape($(this).val()) + '",';
						}
						else{
							textJsonStr += '"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
				jsonStr += textJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + '],';
			}
		}
		var textareaInputArray = [];
		$("#"+dataDomainName+" textarea").each(function(i){
			textareaInputArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<textareaInputArray.length; i++){
			var textareaJsonStr = '';
			var textareaInputObjs = $("#"+dataDomainName+" textarea[name='"+textareaInputArray[i]+"']");
			if(textareaInputObjs.attr("isSumit") == "false"){
				continue;
			}
			if(textareaInputObjs.length <= 1){
				textareaInputObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).val()) + '",';
						}
						else{
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
			}
			else{
				textareaJsonStr = '"' + textareaInputArray[i] + '":[';
				textareaInputObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							textareaJsonStr += '"' + replaceEscape($(this).val()) + '",';
						}
						else{
							textareaJsonStr += '"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
				jsonStr += textareaJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + '],';
			}
		}
		var hiddenInputArray = [];
		$("#"+dataDomainName+" input[type='hidden']").each(function(i){
			hiddenInputArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<hiddenInputArray.length; i++){
			var hiddenJsonStr = '';
			var hiddenObjs = $("#"+dataDomainName+" input[name='"+hiddenInputArray[i]+"']");
			if(hiddenObjs.length <= 1){
				hiddenObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).val()) + '",';
						}
						else{
							jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
			}
			else{
				hiddenJsonStr = '"' + hiddenInputArray[i] + '":[';
				hiddenObjs.each(function(j){
					if($(this).attr('name') != null && $(this).attr('name') != ""){
						if($(this).attr('hiddenVal') == null){
							hiddenJsonStr += '"' + replaceEscape($(this).val()) + '",';
						}
						else{
							hiddenJsonStr += '"' + replaceEscape($(this).attr('hiddenVal')) + '",';
						}
					}
				});
				jsonStr += hiddenJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + '],';
			}
		}
		var checkboxArray = [];
		$("#"+dataDomainName+" input[type='checkbox']").each(function(i){
			checkboxArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<checkboxArray.length; i++){
			var chkJsonStr = "";
			var ckeckObjs = $("#"+dataDomainName+" input[name='"+checkboxArray[i]+"'][checked]");
			if(ckeckObjs.length <= 1){
				ckeckObjs.each(function(j){
					jsonStr += '"' + $(this).attr('name') + '":"' + chkJsonStr + '",';
				});
			}
			else{
				chkJsonStr = '"' + checkboxArray[i] + '":[';
				ckeckObjs.each(function(j){
					chkJsonStr += replaceEscape($(this).val()) + ',';
				});
				jsonStr += chkJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + "],";
			}
		}
		var radioArray = [];
		$("#"+dataDomainName+" input[type='radio']").each(function(i){
			radioArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<radioArray.length; i++){
			var radioJsonStr = "";
			var radioObjs = $("#"+dataDomainName+" input[name='" + radioArray[i] + "'][checked]");
			if(radioObjs.length <= 1){
				radioObjs.each(function(j){
					jsonStr += '"' + $(this).attr('name') + '":"' + replaceEscape($(this).val()) + '",';
				});
			}
			else{
				radioJsonStr = '"' + checkboxArray[i] + '":[';
				radioObjs.each(function(j){
					radioJsonStr += replaceEscape($(this).val()) + ',';
				});
				jsonStr += radioJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + "],";
			}
		}
		var selectArray = [];
		$("#"+dataDomainName+" select").each(function(i){
			selectArray.pushStrEx($(this).attr('name'));
		});
		for(var i=0; i<selectArray.length; i++){
			var selectJsonStr = "";
			var selectObjs = $("#"+dataDomainName+" select[name='" + selectArray[i] + "']");
			if(selectObjs.length <= 1){
				selectObjs.each(function(j){
					var selectOptions = $(this).find(":selected");
					if(selectOptions.length <= 1){
						selectOptions.each(function(h){
							jsonStr += '"' + selectArray[i] + '":"' + replaceEscape($(this).val()) + '",';
						});
					}
					else{
						var selectOptionJsonStr = '"' + selectArray[i] + '":"';
						selectOptions.each(function(h){
							selectOptionJsonStr += replaceEscape($(this).val()) + ',';
						});
						jsonStr += selectOptionJsonStr + '",';
					}
				});
			}
			else{
				selectJsonStr = '"' + selectArray[i] + '":[';
				selectObjs.each(function(j){
					var selectOptions = $(this).find(":selected");
					if(!$(this).attr('multiple')){
						selectOptions.each(function(h){
							selectJsonStr += '"' + replaceEscape($(this).val()) + '",';
						});
					}
					else{
						selectJsonStr += '"';
						selectOptions.each(function(h){
							selectJsonStr += replaceEscape($(this).val()) + ',';
						});
						selectJsonStr += '",';
					}
				});
				jsonStr += selectJsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + ']';
			}
		}
		//alert("(" + jsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + "})");
		return eval("(" + jsonStr.replace( /^\s+|\s+$/g, "" ).replace(/,+$/, "") + "})");
	}
	
	function pageCallback(dataDomainName, resData, tBody){
		
	}
	
	function queryCallback(dataDomainName, rootData, resData){
		settingData(dataDomainName, rootData, resData);
	}
	
	function paramsCallback(rootData, resData){
		
	}
	
	function submitCallback(dataDomainName, rootData, resData){
		
	}
	
	$.submitParams = function(actionUrl, params, callback_){
		submitParams(actionUrl, params, callback_);
	};
	
	function submitParams(actionUrl, params, callback_){
		if(callback_ == null){
			callback_ = paramsCallback;
		}
		var submitParams = {};
		$.extend(submitParams, params);
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : submitParams,
			dataType : "json",
			success : function(result) {
				var rootData = result;
				var resData = result;
				callback_(rootData, resData);
				//$.hideProgressBar();
			},
			beforeSend : function(XMLHttpRequest, textStatus){
				//$.showProgressBar();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交请求失败,请重试...");
				//$.hideProgressBar();
			}
		});
	};
	
	function showLoadMsg(options){
		$('<div class=\"datagrid-mask\" style=\"display:block;\"></div>'+
			'<div class=\"datagrid-mask-msg\" style=\"display:block;left:50%;padding-botoom:50%\">'+options.loadMsg+'</div>').appendTo('body');
	}

	function hideLoading(){
		 $('.datagrid-mask').hide();
		 $('.datagrid-mask-msg').hide();
	}
	
	$.submitForm = function(dataDomainName, actionUrl, callback_, isUpdateData) {
		submitForm(dataDomainName, actionUrl, callback_, true, isUpdateData);
	};
	
	function submitForm(dataDomainName, actionUrl, callback_, isValidator, isUpdateData){
		showLoading({loadMsg:'处理中,请稍后...'});
		if(isValidator == true){
			var isValidate = $('#' + dataDomainName).form('validate'); //jQuery('#' + dataDomainName).validationEngine('validate');
			if(isValidate == false){
				hideLoading();
				return false;
			}
		}
		if(callback_ == null){
			callback_ = submitCallback;
		}
		var submitParams = {};
		var params = getJson(dataDomainName);
		$.extend(submitParams, params);
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : submitParams,
			dataType : "json",
			success : function(result) {
				var rootData = result;
				var resData = result;
				if(isUpdateData){
					settingData(dataDomainName, rootData, resData);
				}
				callback_(dataDomainName, rootData, resData);
				//$.hideProgressBar();
				hideLoading();
			},
			beforeSend : function(XMLHttpRequest, textStatus){
				//$.showProgressBar();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交请求失败,请重试...");
				//$.hideProgressBar();
				hideLoading();
			}
		});
	};
	
	$.pageQuery = function(dataDomainName, tableName, actionUrl, dataKey, pageSize, callback_, params_) {
		if(callback_ == null){
			callback_ = pageCallback;
		}
		var submitParams = {};
		var params = getJson(dataDomainName);
		$.extend(submitParams, params_, params);
		$("#"+tableName+"").pageQuery({ajaxData :{url :actionUrl,params :submitParams,
								dataKey : dataKey,
								batch :true
							},
							pageSize :pageSize,
							callback :function(tBody, subData) {
								tBody.empty();
								//delete subData;
								callback_(dataDomainName, subData, tBody);
							}
						});
	};
	
	$.pageQueryParam = function(tableName, actionUrl, dataKey, pageSize, callback_, params_) {
		if(callback_ == null){
			callback_ = pageCallback;
		}
		var submitParams = params_;
		$("#"+tableName+"").pageQuery({ajaxData :{url :actionUrl,params :submitParams,
								dataKey : dataKey,
								batch :true
							},
							pageSize :pageSize,
							callback :function(tBody, subData) {
								tBody.empty();
								//delete subData;
								callback_(subData, tBody);
							}
						});
	};
	
	$.signQuery = function(dataDomainName, actionUrl, callback_) {
		if(callback_ == null){
			callback_ = queryCallback;
		}
		submitForm(dataDomainName, actionUrl, callback_, false, true);
	};
	
	$.signQueryParam = function(actionUrl, params, callback_) {
		if(callback_ == null){
			callback_ = queryCallback;
		}
		submitParams(actionUrl, params, callback_);
	};

	function settingData(dataDomainName, rootData, resData){
		for(var key in resData){
			if(key == "OLE_SID" || key == "_ServletRequest" || key == "ServletResponse"){
				continue;
			}
			var dataVal = resData[key] == null ? "" : resData[key];
			$("#"+dataDomainName+" input[type='text'][name='" + key + "']").each(function(i){
				if($(this).attr('convert') != null){
					$(this).val(eval($(this).attr('convert') + '("' + dataVal + '")'));
				}
				else{
					$(this).val(dataVal);
				}
			});
			$("#"+dataDomainName+" input[type='hidden'][name='" + key + "']").each(function(i){
				$(this).val(dataVal);
			});
			$("#"+dataDomainName+" input[type='checkbox'][name='" + key + "']").each(function(i){
				if($(this).val() == dataVal){
					$(this).attr("checked", "checked");
				}
			});
			$("#"+dataDomainName+" input[type='radio'][name='" + key + "']").each(function(i){
				if($(this).val() == dataVal){
					$(this).attr("checked", "checked");
				}
			});
			$("#"+dataDomainName+" textarea[name='" + key + "']").each(function(i){
				if($(this).attr('convert') != null){
					$(this).val(eval($(this).attr('convert') + '("' + dataVal + '")'));
				}
				else{
					$(this).val(dataVal);
				}
			});
			$("#"+dataDomainName+" select[name='" + key + "']").each(function(i){
				var count=$(this)[0].options.length;
				for(var i=0; i<count; i++){
					if($(this)[0].options[i].value == dataVal){
						$(this)[0].options[i].selected = true; 
						break;
					}
				}
			});
			var obj = $('#' + key);
			if(obj != null && obj.attr("tagName") != null){
				if(obj.attr("tagName") == 'label'){
					if(obj.attr('convert') != null){
						obj.attr("innerText", eval(obj.attr('convert') + '("' + dataVal + '")'));
					}
					else{
						obj.attr("innerText", dataVal);
					}
				}
			}
	    }
	}

	$.clearData = function(dataDomainName){
		$("#"+dataDomainName+" input[type='text']").each(function(i){
			if($(this).attr('clear') == null || $(this).attr('clear') == 'true'){
				$(this).val("");
				if($(this).attr('hiddenVal') != null){
					$(this).attr('hiddenVal', '');
				}
			}
		});
		$("#"+dataDomainName+" input[type='hidden']").each(function(i){
			if($(this).attr('clear') == null || $(this).attr('clear') == 'true'){
				if($(this).name != "OLE_SID"){
					$(this).val("");
				}
			}
		});
		$("#"+dataDomainName+" input[type='checkbox']").each(function(i){
			if($(this).attr('clear') == null || $(this).attr('clear') == 'true'){
				$(this).attr("checked", "");
			}
		});
		$("#"+dataDomainName+" input[type='radio']").each(function(i){
			if($(this).attr('clear') == null || $(this).attr('clear') == 'true'){
				$(this).attr("checked", "");
			}
		});
		$("#"+dataDomainName+" textarea").each(function(i){
			if($(this).attr('clear') == null || $(this).attr('clear') == 'true'){
				$(this).val("");
				if($(this).attr('hiddenVal') != null){
					$(this).attr('hiddenVal', '');
				}
			}
		});
		$("#"+dataDomainName+" select").each(function(i){
			if($(this).attr('clear') == null || $(this).attr('clear') == 'true'){
				$(this)[0].selectedIndex = 0;
			}
		});
	};

	$.setReadonly = function(dataDomainName, isReadonly){
		disabled = "";
		readonly = "";
		if(isReadonly == null || isReadonly == true){
			readonly = "readonly";
			disabled = "disabled";
		}
		$("#"+dataDomainName+" input[type='text']").each(function(i){
			if($(this).attr('rw') == 'r'){
				$(this).attr('readonly', 'readonly');
			}
			else{
				$(this).attr('readonly', readonly);
			}
		});
		$("#"+dataDomainName+" input[type='checkbox']").each(function(i){
			if($(this).attr('rw') == 'r'){
				$(this).attr('disabled', 'disabled');
			}
			else{
				$(this).attr('disabled', disabled);
			}
		});
		$("#"+dataDomainName+" input[type='radio']").each(function(i){
			if($(this).attr('rw') == 'r'){
				$(this).attr('disabled', 'disabled');
			}
			else{
				$(this).attr('disabled', disabled);
			}
		});
		$("#"+dataDomainName+" textarea").each(function(i){
			if($(this).attr('rw') == 'r'){
				$(this).attr('readonly', 'readonly');
			}
			else{
				$(this).attr('readonly', readonly);
			}
		});
		$("#"+dataDomainName+" select").each(function(i){
			if($(this).attr('rw') == 'r'){
				$(this).attr('disabled', 'disabled');
			}
			else{
				$(this).attr('disabled', disabled);
			}
		});
	};
	
	function validator(obj) {
		if (obj.attr('validator') != null) {
			var validators = obj.attr('validator').split(";");
			if($('#' + obj.attr("name") + 'Lab') != null){
				$('#' + obj.attr("name") + 'Lab').html("");
			}
			for ( var i = 0; i < validators.length; i++) {
				var methodName = toFirstUpperCase(validators[i]);
				if(validators[i] == 'nvl'){
					if(obj.val() == ""){
						var labObj = $('#' + obj.attr("name") + 'Lab');
						if(labObj[0]){
							labObj.css("color", "red").html("不能为空");
						}
						else{
							obj.val(obj.val() + "不能为空");
						}
						return false;
					}
				}
				else if (validators[i] == "mustlen"){
					if(obj.attr('maxlength') != null && obj.val().length != obj.attr('maxlength')) {
						var labObj = $('#' + obj.attr("name") + 'Lab');
						if(labObj[0]){
							labObj.css("color", "red").html("长度不足");
						}
						else{
							obj.val(obj.val() + "长度不足");
						}
						return false;
					}
				} else if (eval('is' + methodName + '("' + obj.val() + '")') == false) {
					var labObj = $('#' + obj.attr("name") + 'Lab');
					if(labObj[0]){
						labObj.css("color", "red").html(getDisplay(validators[i]));
					}
					else{
						obj.val(obj.val() + getDisplay(validators[i]));
					}
					return false;
				}
			}
		}
		return true;
	}

	function getDisplay(validator){
		if(validator == 'digitals'){
			return "必须由由数字组成";
		}
		else if(validator == 'int'){
			return "必须为整数";
		}
		else if(validator == 'num'){
			return "必须为大于0的整数";
		}
		else if(validator == 'minus'){
			return "负整数";
		}
		else if(validator == 'float'){
			return "必须为浮点型";
		}
		else if(validator == 'tel'){
			return "电话号码格式有误";
		}
		else if(validator == 'mobel'){
			return "手机号码格式有误";
		}
		else if(validator == 'chinese'){
			return "必须为中文";
		}
		else if(validator == 'qq'){
			return "QQ号码格式有误";
		}
		else if(validator == 'postId'){
			return "邮政编码格式有误";
		}
		else if(validator == 'email'){
			return "邮箱格式有误";
		}
		else if(validator == 'ip'){
			return "IP地址格式有误";
		}
		else if(validator == 'idNum'){
			return "身份证号码格式有误";
		}
		else if(validator == 'time'){
			return "时间格式有误";
		}
		else if(validator == 'date'){
			return "日期格式有误";
		}
		else if(validator == 'dateTime'){
			return "日期时间格式有误";
		}
		else if(validator == 'letters'){
			return "必须由a-Z或者是A-Z的字字母组成";
		}
		else if(validator == 'letterNum'){
			return "必须由字母和数字组成";
		}
		else if(validator == 'userName'){
			return "必须由字母和数字，下划线,点号组成.且开头的只能是下划线和字母";
		}
		else if(validator == 'url'){
			return "URL格式有误";
		}
	}

	function toFirstUpperCase(str) {
		return str.replace(/\s[a-z]/g, function($1) {
			return $1.toLocaleUpperCase();
		}).replace(/^[a-z]/, function($1) {
			return $1.toLocaleUpperCase();
		});
	}

	// 去除左侧空格
	function lTrim(str) {
		return str.replace(/^\s*/g, "");
	}

	// 去右空格
	function rTrim(str) {
		return str.replace(/\s*$/g, "");
	}

	// 去掉字符串两端的空格
	function trim(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	// 去除字符串中间空格
	function cTim(str) {
		return str.replace(/\s/g, '');
	}

	// 是否为由数字组成的字符串
	function isDigitals(str) {
		var reg = /^[0-9]*$/;// 匹配整数
		return reg.test(str);
	}

	// 验证是否为整数，包括正负数；
	function isInt(str) {
		var reg = /^(-|\+)?\d+$/;
		return reg.test(str);
	}

	// 是大于0的整数
	function isNum(str) {
		var reg = /^\d+$/;
		return reg.test(str);
	}

	// 负整数的验证
	function isMinus(str) {
		var reg = /^-\d+$/;
		return reg.test(str);
	}

	// 验证是否为浮点数（正数）
	function isFloat(str) {
		var check_float = new RegExp("^[1-9][0-9]*\.[0-9]+$");// 匹配浮点数
		return check_float.exec(str);
	}

	// 是否为固定电话，区号3到4位，号码7到8位,区号和号码用"－"分割开，转接号码为1到6位，用小括号括起来紧跟在号码后面
	function isTel(str) {
		var reg = /^[0-9]{3,4}\-\d{7,8}(\(\d{1,6}\))?$/;

		if (reg.test(str))
			return true;
		else
			return false;
	}

	// 手机号码验证，验证13系列和158，159几种号码，长度11位
	function isMobel(str) {
		var reg0 = /^13\d{9}$/;
		var reg1 = /^158\d{8}$/;
		var reg2 = /^159\d{8}$/;

		return (reg0.test(str) || reg1.test(str) || reg2.test(str));
	}

	// 验证是否为中文
	function isChinese(str) {
		var reg = /^[\u0391-\uFFE5]+$/;
		return reg.test(str);
	}

	// 验证是否为qq号码，长度为5－10位
	function isQq(str) {
		var reg = /^[1-9]\d{4,9}$/;
		return reg.test(str);
	}

	// 验证邮编
	function isPostId(str) {
		var reg = /^\d{6}$/;
		return reg.test(str);
	}

	// 验证是否未email
	function isEmail(str) {
		var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		return reg.test(str);
	}

	// 验证IP地址
	function isIp(str) {
		var check = function(v) {
			try {
				return (v <= 255 && v >= 0);
			} catch (x) {
				return false;
			}
		};
		var re = str.split(".");
		return (re.length == 4) ? (check(re[0]) && check(re[1]) && check(re[2]) && check(re[3]))
				: false;
	}

	// 身份证验证
	function isIdNum(str) {
		var City = {
			11 : "北京",
			12 : "天津",
			13 : "河北",
			14 : "山西",
			15 : "内蒙古",
			21 : "辽宁",
			22 : "吉林",
			23 : "黑龙江 ",
			31 : "上海",
			32 : "江苏",
			33 : "浙江",
			34 : "安徽",
			35 : "福建",
			36 : "江西",
			37 : "山东",
			41 : "河南",
			42 : "湖北 ",
			43 : "湖南",
			44 : "广东",
			45 : "广西",
			46 : "海南",
			50 : "重庆",
			51 : "四川",
			52 : "贵州",
			53 : "云南",
			54 : "西藏 ",
			61 : "陕西",
			62 : "甘肃",
			63 : "青海",
			64 : "宁夏",
			65 : "新疆",
			71 : "台湾",
			81 : "香港",
			82 : "澳门",
			91 : "国外 "
		};
		var iSum = 0;
		if (!/^\d{17}(\d|x)$/i.test(str))
			return false;
		str = str.replace(/x$/i, "a");
		if (City[parseInt(str.substr(0, 2))] == null) {
			alert("Error:非法地区");
			return false;
		}
		sBirthday = str.substr(6, 4) + "-" + Number(str.substr(10, 2)) + "-"
				+ Number(str.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d
				.getDate())) {
			alert("Error:非法生日");
			return false;
		}
		for ( var i = 17; i >= 0; i--)
			iSum += (Math.pow(2, i) % 11) * parseInt(str.charAt(17 - i), 11);
		if (iSum % 11 != 1) {
			alert("Error:非法证号");
			return false;
		}
		return City[parseInt(str.substr(0, 2))] + "," + sBirthday + ","
				+ (str.substr(16, 1) % 2 ? "男" : "女");
	}

	// 判断是否短时间，形如 (13:04:06)
	function isTime(str) {
		var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
		if (a == null) {
			alert('输入的参数不是时间格式');
			return false;
		}
		if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
			alert("时间格式不对");
			return false;
		}
		return true;
	}

	// 短日期，形如 (2003-12-05)
	function isDate(str) {
		var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if (r == null)
			return false;
		var d = new Date(r[1], r[3] - 1, r[4]);
		return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d
				.getDate() == r[4]);
	}

	// 长时间，形如 (2003-12-05 13:04:06)
	function isDateTime(str) {
		var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		var r = str.match(reg);
		if (r == null)
			return false;
		var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
		return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
				&& d.getDate() == r[4] && d.getHours() == r[5]
				&& d.getMinutes() == r[6] && d.getSeconds() == r[7]);
	}

	// 判断字符全部由a-Z或者是A-Z的字字母组成
	function isLetters(str) {
		var reg = /[^a-zA-Z]/g;
		return reg.test(str);
	}

	// 判断字符由字母和数字组成。
	function isLetterNum(str) {
		var reg = /[^0-9a-zA-Z]/g;
		return reg.test(str);
	}

	// 判断字符由字母和数字，下划线,点号组成.且开头的只能是下划线和字母
	function isUserName(str) {
		var reg = /^([a-zA-z_]{1})([\w]*)$/g;
		return reg.test(str);
	}

	// 判断用户名是否为数字字母下滑线
	function isNotChinese(str) {
		var reg = /[^A-Za-z0-9_]/g;
		if (reg.test(str)) {
			return (false);
		} else {
			return (true);
		}
	}

	// 验证url
	function isUrl(str) {
		var reg = /^(http\:\/\/)?([a-z0-9][a-z0-9\-]+\.)?[a-z0-9][a-z0-9\-]+[a-z0-9](\.[a-z]{2,4})+(\/[a-z0-9\.\,\-\_\%\?\=\&]?)?$/i;
		return reg.test(str);
	}

	// 判断是否含有汉字
	function isChinese1(str) {
		if (escape(str).indexOf("%u") != -1)
			return true;
		else
			return false;
	}
})(jQuery);