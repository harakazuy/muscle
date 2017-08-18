/**
 * 筋トレ履歴のページネーション
 * TODO:レコード取得とページネーションを切り出して共通化
 */
// ページネーション
function paginationToRecord(limit){
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