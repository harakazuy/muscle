/**
 * 筋トレ記録・更新フォーム
 */

// フォーム部品
var form;

// フォーム挿入
function setTrainingForm(page){
	$("#form-outer").load("form #trainingForm", function(){
		appendRepAndCount();
		formValidation();
		form = $('#trainingForm').html();
		if(page == "index") appendForm(form);
		if(page == "edit"){ // TODO:切り出す or 処理変更
			optionSelected("#repetition_", $("#recordSize").val())
			optionSelected("#setCount_", $("#recordSize").val())
		}
	});
};

//フォーム追加 TODO:イベントをcloneで引き継がせる
function appendForm(formElement){
	$('#form').append(formElement);
	formValidation();
	removable();
};

$('#appendForm').click(function(){
	appendForm(form);
});

// 回数とセット数のプルダウンの値を設定
function appendRepAndCount(){
	for(var i = 1; i <= 50; i++){
		$("[name='repetition']").append(`<option value="${i}">${i}</option>`)
	}
	for(var i = 1; i <= 20; i++){
		$("[name='setCount']").append(`<option value="${i}">${i}</option>`)
	}
};

// プルダウンを、HTMLに埋め込んだ値でselected
function optionSelected(selectElement, size){
	for(var i = 0; i < size; i++){
		var current = $(selectElement + i)
		current.val(current.next("input[type='hidden']").val());
	}
}

// フォーム削除
function removable(){
	$('.removeRow').click(function(){
		$(this).parent().parent().remove();
	})
};

$('#removeForm').click(function(){
	$('#form div:last-child').remove();
});

removable();

//レコード削除
$('.removeRecord').click(function(){
	var thisRecord = $(this).parent().parent();
	if(confirm("削除しますか？")){
    	$.ajax({
			type: "POST",
			url: contextPath + "delete",
			data: "id=" + $("[name='id']", thisRecord).val(),
			context: thisRecord
    	}).done(function(data, textStatus, jqXHR){
    		if(data == 1){
    			this.remove();
    		}else{
    			alert("削除できませんでした。")
    		}
    	}).fail(function(jqXHR, textStatus, errorThrown){
    		// 通信エラーの場合処理
    	})
	}
});

// バリデーション
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
