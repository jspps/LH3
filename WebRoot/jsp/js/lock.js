/** * 左右键锁定 * */
// disable select-text script (ie4+, ns6+)- by small
function disableselect(e) {
	return false;
};

function reenable() {
	return true;
};

// if ie4+
document.onselectstart = function() {
	return false;
};

// if ns6
if (window.sidebar) {
	document.onmousedown = disableselect;
	document.onclick = reenable;
};

function retFlase() {
	return false;
};

function setBody() {
	document.oncontextmenu = retFlase;
	document.onselectstart = retFlase;
	document.body.onselectstart = retFlase;
	document.body.onselect = retFlase;
	document.body.oncopy = retFlase;
	document.body.onbeforecopy = retFlase;
	document.body.oncontextmenu = retFlase;
	document.body.onmouseup = retFlase;
};

setInterval(setBody, 200);

// 判断是否跳转
var isCanShowConfirm = false;
window.onbeforeunload = function() {
	if(isCanShowConfirm){
		if(confirm("确定要离开?")){
			return true;
		}
		return false;
	}
};