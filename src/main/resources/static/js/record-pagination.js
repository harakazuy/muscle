/**
 * 筋トレ履歴のページネーション
 * TODO:レコード取得とページネーションを切り出して共通化
 * TODO:最初に総ページ数取得するのを何とかしたい
 */
// ページネーション
function paginationToRecord(limit, upperDefer = $.Deferred()){
	$.ajax({
		type: "POST",
		url: restPath + "totalPages",
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
					url: restPath + "pagination",
					data: {
						limit : limit,
						page : page
					}
				}).done(function(data, textStatus, jqXHR){
					displayRecord(data)
					upperDefer.resolve()
				}).fail(function(jqXHR, textStatus, errorThrown){
					// 通信エラーの場合処理
				})
			}
		})
	}).fail(function(jqXHR, textStatus, errorThrown){
		// 通信エラーの場合処理
	})
};

// 筋トレ履歴描画
function displayRecord(data){
	var json = JSON.parse(data)
	var tableHtml = ""
	$(json).each(function(){
		tableHtml += `<div class="record-outer">
						<table border=1 class="col-sm-offset-1">
							<tr><th colspan=4><input type="date" value="${this['date']}" readonly>
							<button type="button" class="edit btn btn-primary">編集</button></th></tr>
							<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td></tr>`
		$(this["trainingRecords"]).each(function(){
			tableHtml += `<tr><td>${this["trainingName"]}</td><td>${this["weight"] ? this["weight"] : ""}</td>
							<td>${this["repetition"]}</td><td>${this["setCount"]}</td></tr>`
		})
		tableHtml += '</table></div><br>'
	})
	$('#records_content').html(tableHtml)
	editable()
}

// 編集する TODO:getPage使える？
function editable(){
	$('.edit').click(function(){
		$('.edit').prop("disabled", true)
		var date = $(this).parents('table').find('input[type="date"]').val()
		$.ajax(contextPath + "edit", {
			dataType: 'html',
			context: this
		}).done(function(data, textStatus, jqXHR){
			var pageHtml = $($.parseHTML(data)).filter('#edit')[0].outerHTML
			var record = $(this).parents('.record-outer')
			record.find('table').hide()
			record.append(pageHtml)
			var defer = $.Deferred()
			setTrainingForm('edit', defer)
			defer.done(function(){
				setEditForm(date)
			})
		})
	})
}
