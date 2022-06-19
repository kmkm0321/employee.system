
window.onload = function createOption() {
	let today = new Date();
	let year = today.getFullYear();
	let list_year = ["", year, year-1, year-2];
	const selectYear = document.getElementById('year');
	for(i = 0; i <= 3; i++){
		let option = document.createElement('option');
		option.value = list_year[i];
		option.textContent = list_year[i] + "年";
		if(i == 0){
			option.disabled = true;
			option.selected = true;
		}
		selectYear.appendChild(option)
	}
}

function output(){
	window.open('http://localhost:8080/service/recode-user/output');
}

function check(){
	let select_year = document.getElementById('year');
	let select_month = document.getElementById('month');
	if(!select_year.value || !select_month.value){
		return false;
	}else{
		return true;
	}
}