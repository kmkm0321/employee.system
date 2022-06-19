/**
 * 
 */
 
function beforeSubmit(msg){
    if(window.confirm(msg+'しますか？')) {
      	return true;
    } else {
		return false;
	}
}

var time = new Date();//引数はテスト用(2022,0,14,23,59,55)

function current_time(){
	time.setSeconds(time.getSeconds()+1);//テスト用
	document.getElementById("time").innerHTML = time.toLocaleTimeString();
	if(time.toLocaleTimeString() == "0:00:00"){
		var recode = document.getElementById("recode"); 
		recode.remove();
	}
}

setInterval("current_time()",1000);
