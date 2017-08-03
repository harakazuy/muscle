/**
 * 
 */

$("#header-outer").load("header #header");
$("#footer-outer").load("footer #footer");

function loadNavbar(pageId){
	$("#navbar-outer").load("navbar #navbar", function(){
		$(pageId).attr("class", "active");
	});
}

var form;

function setTrainingForm(page){
	$("#form-outer").load("form #trainingForm", function(){
		appendRepAndCount();
		form = $('#trainingForm').html();
		if(page == "index") appendForm(form);
		if(page == "edit"){
			optionSelected("#repetition_", $("#recordSize").val())
			optionSelected("#setCount_", $("#recordSize").val())
		}
	});
};

function optionSelected(selectElement, size){
	for(var i = 0; i < size; i++){
		var current = $(selectElement + i)
		current.val(current.next("input[type='hidden']").val());
	}
}

function appendRepAndCount(){
	for(var i = 1; i <= 50; i++){
		$("[name='repetition']").append('<option value=\"' + i + '\">' + i + '</option>')
	}
	for(var i = 1; i <= 20; i++){
		$("[name='setCount']").append('<option value=\"' + i + '\">' + i + '</option>')
	}
};

function formValidation(){
	var msg_required="必須入力です。";
	
	$("[name='date']").attr("data-validation", "required");
	$("[name='date']").attr("data-validation-error-msg", '日時は' + msg_required);
	
	$("[name='trainingId']").attr("data-validation", "required");
	$("[name='trainingId']").attr("data-validation-error-msg", 'メニューは' + msg_required);
	
	$("[name='repetition']").attr("data-validation", "required");
	$("[name='repetition']").attr("data-validation-error-msg", '回数は' + msg_required);
	
	$("[name='setCount']").attr("data-validation", "required");
	$("[name='setCount']").attr("data-validation-error-msg", 'セット数は' + msg_required);

	$.validate({
	    errorMessagePosition : 'top',
	    language : {
	    	errorTitle: 'フォームの送信に失敗しました'
	    }
	});
};

formValidation();

function removable(){
	$('.removeRow').click(function(){
		$(this).parent().parent().remove();
	})
};

removable();

function appendForm(formElement){
	$('#form').append(formElement);
	formValidation();
	removable();
};

$('#appendForm').click(function(){
	appendForm(form);
});

$('#removeForm').click(function(){
	$('#form div:last-child').remove();
});