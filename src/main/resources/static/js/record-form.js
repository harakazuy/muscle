/**
 * 
 */

// ヘッダー、フッター、ナビバー
$("#header-outer").load("header #header");
$("#footer-outer").load("footer #footer");

function loadNavbar(pageId){
	$("#navbar-outer").load("navbar #navbar", function(){
		$(pageId).attr("class", "active");
		if(pageId == "#record") paginationToRecord();
	});
}

// フォーム
var form;
var contextPath = $("#contextPath").val();

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

function setTrainingForm(page){
	$("#form-outer").load("form #trainingForm", function(){
		appendRepAndCount();
		formValidation();
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
		$("[name='repetition']").append(`<option value="${i}">${i}</option>`)
	}
	for(var i = 1; i <= 20; i++){
		$("[name='setCount']").append(`<option value="${i}">${i}</option>`)
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
var restPath = contextPath + "rest"
// ページネーション
var limit = 3 // 1ページあたりのレコード数
function paginationToRecord(){
	$.ajax({
		type: "POST",
		url: restPath + "/totalPages",
		data: "limit=" + limit
	}).done(function(data, textStatus, jqXHR){
		$('#records_pagination').twbsPagination({
			totalPages: data,
			visiblePages: 5,
			first: '最初',
			prev: '前へ',
			next: '次へ',
			last: '最後',
			onPageClick: function (event, page) {
				$.ajax({
					type: "POST",
					url: restPath + "/pagination",
					data: {
						limit : limit,
						page : page
					}
				}).done(function(data, textStatus, jqXHR){
					var json = JSON.parse(data);
					var tableHtml = "";
					$(json).each(function(){
						tableHtml += `<table border=1>
										<tr><th colspan=4>${this["date"]}
										<a href="${contextPath}/toEdit?date=${this["date"]}">
										<input type="button" value="編集"></a></th></tr>
										<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td></tr>`
						$(this["trainingRecords"]).each(function(){
							tableHtml += `<tr><td>${this["trainingName"]}</td><td>${this["weight"] ? this["weight"] : ""}</td>
											<td>${this["repetition"]}</td><td>${this["setCount"]}</td></tr>`
						})
						tableHtml += '</table><br>'
					})
					$('#records_content').html(tableHtml);
				}).fail(function(jqXHR, textStatus, errorThrown){
					// 通信エラーの場合処理
				})
			}
		})
	}).fail(function(jqXHR, textStatus, errorThrown){
		// 通信エラーの場合処理
	})
};