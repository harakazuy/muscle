/**
 * 筋トレメニュー
 */
// バリデーション後にフォーム送信
function menuValidation(){
	var msg_required = "必須入力です。"
	
	$("[name='trainingName']").attr("data-validation", "required")
	$("[name='trainingName']").attr("data-validation-error-msg", 'トレーニング名は' + msg_required)
	
	$.validate({
		errorMessagePosition : 'top',
		language : {
			errorTitle: 'フォームの送信に失敗しました'
		},
		onSuccess : function(form) {
			var departure = $("input[type='submit']").attr('class')
			menuFormSubmit(form.serialize(), departure)
			return false; // ページ遷移しない
		}
	})
}

// フォーム送信 TODO:履歴と共通化
function menuFormSubmit(form, departure){
	$.ajax({
		type: "POST",
		url: restPath + "updateTraining",
		data: form
	}).done(function(result){
		var defer = $.Deferred()
		getPage('trainingMenu', defer)
		defer.done(function(){
			displayAlert(departure)
		})
	})
}

// 筋トレメニュー描画
function displayTrainingAjax(){
	$.ajax({
		type: "GET",
		url: restPath + "trainings"
	}).done(function(data, textStatus, jqXHR){
		displayTraining(data)
	})
}
function displayTraining(data){
	var json = JSON.parse(data)
	var tableHtml = ""
	$(json).each(function(){
		tableHtml += `<div class="training-outer">
						<table border=1 class="col-sm-offset-1">
							<input type="hidden" name="id" value="${this['id']}">
							<tr><th colspan=4><input type="text" value="${this['trainingName']}" readonly>
							<button type="button" class="edit btn btn-primary">編集</button></th></tr>
						</table>
					</div><br>`
	})
	$('#trainings_content').html(tableHtml)
	menuEditable()
}

// 筋トレメニューを編集する
function menuEditable(){
	$('.edit').click(function(){
		$('.edit').prop("disabled", true)
		$.ajax(contextPath + "menuEdit", {
			dataType: 'html',
			context: this
		}).done(function(data){
			var pageHtml = $($.parseHTML(data)).filter('#menuEdit')[0].outerHTML
			var id = $(this).parents('table').find('input[type="hidden"]').val()
			var trainingName = $(this).parents('table').find('input[type="text"]').val()
			var menu = $(this).parents('.training-outer')
			menu.find('table').hide()
			menu.append(pageHtml)
			$("[name='id']").val(id)
			$("[name='trainingName']").val(trainingName)
			menuValidation()
			cancelableEditMenu()
		})
	})
}

// 編集キャンセル TODO:履歴と共通化
function cancelableEditMenu(){
	$('#cancelEdit').click(function(){
		var menu = $(this).parents('.training-outer')
		menu.find('#menuEdit').remove()
		menu.find('table').show()
		$('.edit').prop("disabled", false)
	})
}

// ページネーション TODO:履歴の方と共通化出来る？
//function paginationToTraining(limit, upperDefer = $.Deferred()){
//	$.ajax({
//		type: "POST",
//		url: restPath + "totalMenuPages",
//		data: "limit=" + limit
//	}).done(function(data, textStatus, jqXHR){
//		$('#trainings_pagination').twbsPagination({
//			totalPages: data,
//			visiblePages: 5,
//			first: '最初',
//			prev: '前へ',
//			next: '次へ',
//			last: '最後',
//			onPageClick: function (event, page) {
//				$.ajax({
//					type: "POST",
//					url: restPath + "menuPagination",
//					data: {
//						limit : limit,
//						page : page
//					}
//				}).done(function(data, textStatus, jqXHR){
//					displayTraining(data)
//					upperDefer.resolve()
//				}).fail(function(jqXHR, textStatus, errorThrown){
//					// 通信エラーの場合処理
//				})
//			}
//		})
//	}).fail(function(jqXHR, textStatus, errorThrown){
//		// 通信エラーの場合処理
//	})
//}