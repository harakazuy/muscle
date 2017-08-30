/**
 * 筋トレ記録・更新フォーム
 */

// バリデーション後にフォーム送信
function formValidation(){
	var msg_required = "必須入力です。"
	
	$("[name='date']").attr("data-validation", "required")
	$("[name='date']").attr("data-validation-error-msg", '日時は' + msg_required)
	
	$("[name='trainingId']").attr("data-validation", "required")
	$("[name='trainingId']").attr("data-validation-error-msg", 'メニューは' + msg_required)
	
	$("[name='weight']").attr("data-validation", "required")
	$("[name='weight']").attr("data-validation-error-msg", '重さは' + msg_required)
	
	$("[name='repetition']").attr("data-validation", "required")
	$("[name='repetition']").attr("data-validation-error-msg", '回数は' + msg_required)
	
	$("[name='setCount']").attr("data-validation", "required")
	$("[name='setCount']").attr("data-validation-error-msg", 'セット数は' + msg_required)
	
	$.validate({
		errorMessagePosition : 'top',
		language : {
			errorTitle: 'フォームの送信に失敗しました'
		},
		onSuccess : function(form) {
			var departure = $("input[type='submit']").attr('class')
			formSubmit(form.serialize(), departure)
			return false; // ページ遷移しない
		}
	})
}

// フォーム送信 TODO:登録と更新で動作を変える
function formSubmit(form, departure){
	$.ajax({
		type: "POST",
		url: restPath + "upsert",
		data: form
	}).done(function(result){
		// TODO:エラー判定をgetPageの前にする
		var defer = $.Deferred()
		getPage('record', defer)
		defer.done(function(){
//			decision(result) エラー判定
			displayAlert(departure)
		})
	})
}

// 登録・更新のエラー判定 TODO:作る
function decision(result){
	var json = JSON.parse(result)
	$(json).each(function(){
		for(var record in this){
			if(this[record] != 1){
				// エラーがある場合
			}
		}
	})
}

// アラート表示 TODO:失敗の場合を追加
function displayAlert(departure){
	var action;
	switch(departure){
		case "index": action = "記録"
			break
		case "edit": action = "更新"
			break
		case "delete": action = "削除"
			break
		case "menuEdit": action = "更新" // TODO:履歴とメニュー区別する
			break
		default:
			break
	}
	var alert = $(".alert")
	alert.addClass("alert-success").append(
			`トレーニングを${action}しました。`
	).show()
	setTimeout(function(){
		alert.fadeOut().queue(function(){
			$(this).empty().removeClass("alert-success").stop(true)
		})
	}, 1000)
}

// フォーム挿入
function setTrainingForm(page, upperDefer = $.Deferred()){
	$.ajax(contextPath + 'form', {
		dataType: 'html'
	}).done(function(data){
		var pageHtml = $($.parseHTML(data)).filter('#formRow')[0].innerHTML
		$("#form-outer").empty().append(pageHtml)
		appendRepAndCount()
		formValidation()
		var defer = $.Deferred()
		appendMenu(defer)
		defer.done(function(){
			var form = $('#form-outer').html()
			$('#appendForm').click(function(){
				appendForm(form)
			})
			$('#removeForm').click(function(){
				$('#form div:last-child').remove()
			})
			if(page == "index") appendForm(form)
			upperDefer.resolve()
		})
	})
}

// メニュー取得 TODO: チャートと共通化
function appendMenu(upperDefer){
	$.ajax({
		type: "GET",
		url: restPath + "trainings"
	}).done(function(data, textStatus, jqXHR){
		var json = JSON.parse(data)
		$(json).each(function(){
			$("[name='trainingId']").append(`<option value="${this['id']}">${this['trainingName']}</option>`)
		})
		upperDefer.resolve()
	}).fail(function(jqXHR, textStatus, errorThrown){
		// 通信エラーの場合処理
	})
}

// 編集用の列挿入
function setEditForm(date){
	$.ajax({
		type: "POST",
		url: restPath + "records",
		data: "date=" + date
	}).done(function(data){
		$("#edit input[name='date']").val(date)
		var json = JSON.parse(data)
		$(json).each(function(){
			var form = $($('#form-outer')[0].innerHTML)
			form.find("input[name='id']").val(this['id'])
			form.find("select[name='trainingId']").val(this['trainingId'])
			form.find("input[name='weight']").val(this['weight'])
			form.find("select[name='repetition']").val(this['repetition'])
			form.find("select[name='setCount']").val(this['setCount'])
			form.find(".removeRow").attr('class', 'removeRecord')
			appendForm(form)
		})
		removableRecord()
		cancelableEdit()
	})
}

// 列追加 TODO:イベントをcloneで引き継がせる
function appendForm(formElement){
	$('#form').append(formElement)
	formValidation()
	removableRow()
};

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
		current.val(current.next("input[type='hidden']").val())
	}
}

// 列削除
function removableRow(){
	$('.removeRow').click(function(){
		$(this).parent().parent().remove() // TODO:セレクタを綺麗に
	})
}

// レコード削除
function removableRecord(){
	$('.removeRecord').click(function(){
		var action = "delete"
		var thisRecord = $(this).parent().parent() // TODO:セレクタを綺麗に
		if(confirm("削除しますか？")){
	    	$.ajax({
				type: "POST",
				url: restPath + action,
				data: "id=" + $("[name='id']", thisRecord).val(),
				context: thisRecord
	    	}).done(function(data, textStatus, jqXHR){
	    		if(data == 1){
	    			this.remove();
	    			displayAlert(action)
	    		}else{
	    			alert("削除できませんでした。")
	    		}
	    	}).fail(function(jqXHR, textStatus, errorThrown){
	    		// 通信エラーの場合処理
	    	})
		}
	})
}

// 編集キャンセル
function cancelableEdit(){
	$('#cancelEdit').click(function(){
		var record = $(this).parents('.record-outer')
		record.find('#edit').remove()
		record.find('table').show()
		$('.edit').prop("disabled", false)
	})
}