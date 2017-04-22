/** * 考试进行 * */

/*
<input type="hidden" name="curExamNum" id="curExamNum" value="${curExamLen}" />
<input type="hidden" name="isITMS" id="isITMS" value="${isITMS}" />
<input type="hidden" name="examTime" id="examTime" value="${examTime}" />
<script type="text/javascript" src="jsp/js/examing.js"></script>

*/

function basePath() {
	var location = (window.location+'').split('/'); 
	var ret = location[0]+'//'+location[2]+'/'+location[3]+'/';
	return ret;
}

function curExamLen() {
	return $("#curExamLen").val();
}

function examTime() {
	return $("#examTime").val();
}

var url_saveAnswer = "client/saveAnswers";
var url_judgeAnswer = basePath() + "client/judgeAnswers";

function hrefUrl(titNum, curOptid, curType, isAnswer) {
	var nxtOptId = 0;
	isAnswer = !!isAnswer;
	var parOptId = curOptid;
	var parType = curType;
	var parIsAnswer = isAnswer;
	if (!isAnswer) {
		curOptid = $("#curOptId").val();
		curType = $("#curOptType").val();
		if (curType == "7") {
			curType = $("#curOptGid").val();
		}
		isAnswer = true;
	}

	nxtOptId = $("#tymk_zt_div_" + curOptid).next().attr('npid');
	if (nxtOptId == null || nxtOptId == '' || nxtOptId == undefined) {
		nxtOptId = $("#tymk_zt_div_" + curOptid).next().next().attr('npid');
	}

	if (nxtOptId == null || nxtOptId == '' || nxtOptId == undefined) {
		nxtOptId = 0;
	}

	var datedaan = "";
	var type = parseInt(curType, 10);
	switch (type) {
	case 1:
		// 单选题答案
		$(".tymk_right_xx_radio").each(function() {
			if ($(this).is(":checked")) {
				datedaan = $(this).val();
			}
		});
		break;
	case 2:
		// 多选题答案
		$(".tymk_right_xx_checkbox").each(function() {
			if ($(this).is(":checked")) {
				datedaan += $(this).val() + ",";
			}
		});
		break;
	case 3:
		// 判断题答案
		$(".tymk_right_xx_panduan").each(function() {
			if ($(this).is(":checked")) {
				datedaan = $(this).val();
			}
		});
		break;
	case 4:
		// 填空题答案
		datedaan = $(".tiankongdaan").val();
		break;
	case 5:
		// 简答题答案
		datedaan = $(".jiandadaan").val();
		break;
	case 6:
		// 论述题答案
		datedaan = $(".jiandadaan").val();
		break;
	}
	;

	jQuery.post("client/optquestionDes", {
		'curOptId' : curOptid,
		'curType' : type,
		'titNum' : titNum,
		'nxtOptId' : nxtOptId,
		'isAnswer' : isAnswer,
		'curExamLen' : curExamLen(),
		'datedaan' : datedaan
	}, function(data) {
		if (parIsAnswer) {
			$("#tymk_right_cont_html").html(data);
			exceCallBack();
		} else {
			getRtCont(parOptId, parType, titNum, nxtOptId, parIsAnswer,
					datedaan);
		}
	});
};

function getRtCont(curOptid, curType, titNum, nxtOptId, isAnswer, datedaan) {
	var data = {
		'curOptId' : curOptid,
		'curType' : curType,
		'titNum' : titNum,
		'nxtOptId' : nxtOptId,
		'isAnswer' : isAnswer,
		'curExamLen' : curExamLen(),
		'datedaan' : datedaan
	};
	jQuery.post("client/optquestionDes", data, function(data) {
		$("#tymk_right_cont_html").html(data);
		exceCallBack();
	});
};

function exceCallBack() {
	var jqWrapLt = $("#left_question_titles");
	jqWrapLt.find("span[npid]").removeClass().addClass("tymk_list_ts_wd");
	for ( var key in mapAnswer) {
		var val = mapAnswer[key];
		var tit = jqWrapLt.find("span[npid='" + key + "']");
		if (val != "") {
			tit.removeClass().addClass("tymk_list_ts");
		} else {
			tit.removeClass().addClass("tymk_list_ts_dd");
		}
		;
	}
	;
	var curOptid = $("#curOptId").val();
	var curAnswer = mapAnswer[curOptid];
	var val = "";
	if (curAnswer != null) {
		var curType = $("#curOptType").val();
		if (curType == "7") {
			curType = $("#curOptGid").val();
		}
		curType = parseInt(curType, 10);
		switch (curType) {
		case 1:
			// 单选题答案
			$(".tymk_right_xx_radio").each(function() {
				if ($(this).val() == curAnswer) {
					$(this).attr("checked", true);
				}
			});
			break;
		case 2:
			// 多选题答案
			var arry = curAnswer.split(",");
			$(".tymk_right_xx_checkbox").each(function() {
				if (arry != null && arry.length > 0) {
					val = $(this).val();
					for ( var i = 0; i < arry.length; i++) {
						var en = arry[i];
						if (en == val) {
							$(this).attr("checked", true);
						}
					}
				}
			});
			break;
		case 3:
			// 判断题答案
			$(".tymk_right_xx_panduan").each(function() {
				if ($(this).val() == curAnswer) {
					$(this).attr("checked", true);
				}
			});
			break;
		case 4:
			// 填空题答案
			$(".tiankongdaan").val(curAnswer);
			break;
		case 5:
			// 简答题答案
			$(".jiandadaan").val(curAnswer);
			break;
		case 6:
			// 论述题答案
			$(".jiandadaan").val(curAnswer);
			break;
		}
		;
	}
};

/** * 考试时间倒计时 * */
var maxtime = 0; // 时间按秒 7200s
var tmpMaxTime = 0;
var timerId = -1;// 考试定时器ID
var minutes = 0;
var seconds = 0;

function SetTimeLabVal(){
	minutes = Math.floor(maxtime / 60);
	seconds = Math.floor(maxtime % 60);
	var msg = "还剩" + minutes + "分" + seconds + "秒";
	$("#timer_lab_val").html(msg);
}

function CountDown() {
	if (maxtime >= 0) {
		SetTimeLabVal();
		if (minutes == 5 && seconds == 0)
			alert('注意，还有5分钟!');
		else if (minutes == 0 && seconds == 30)
			alert('注意，还有30秒!');

		--maxtime;
	} else {
		clearInterval(timerId);
		if (tmpMaxTime > 0) {
			alert("时间到，结束!");
			judgeAnswers();
		}
	}
}

function judgeAnswers() {
	var usetime = (tmpMaxTime - maxtime);
	jQuery.post(url_judgeAnswer, {
		'curExamLen' : curExamLen(),
		"timediff" : maxtime
	}, function(back) {
		// console.info(back);
		if (back.status) {
			if (back.status == 1) {
				var url = '<%=basePath%>'
						+ url_saveAnswer
						+ "?curExamLen="+curExamLen()+"&timers="
						+ usetime;
				window.location.href = url;
			} else {
				var url = '<%=basePath%>' + "client/judgePage4Examing";
				var data = {
					"curExamLen" : curExamLen(),
					"timers" : usetime,
					"url" : url_saveAnswer
				};
				var callBack4Page = function(jugdeBack) {
					var jqAlert = $("#div_4_jugde4examing");
					if (jqAlert.length == 0) {
						jqAlert = $("<div id='div_4_jugde4examing'>");
						$(document.body).append(jqAlert);
					}
					;
					jqAlert.html(jugdeBack);
				};
				$.post(url, data, callBack4Page);
			}
		}
	}, "json");
}

$(document).ready(function() {
	var isITMS = $("#isITMS").val();
	isITMS = !!isITMS;
	if(isITMS){
		timerId = setInterval("CountDown()", 1000);
		url_saveAnswer = "client/saveAnswers4ITMS";
	}
	
	maxtime = examTime();
	maxtime = parseInt(maxtime, 10) * 60;
	tmpMaxTime = maxtime;
	
	SetTimeLabVal();
	
	exceCallBack();
	$("#submitAnswer").click(function() {
		judgeAnswers();
	});
});