/**
 * 筋トレ履歴のチャート
 */
// メニュー取得 TODO:フォームとかと共通化
// チャート表示・制御
function setChart(){
	displayChart(1); // 最初に腹筋を表示
	// メニュー変更用プルダウン
	$.ajax({
		type: "GET",
		url: restPath + "trainings"
	}).done(function(data, textStatus, jqXHR){
		var json = JSON.parse(data);
		$(json).each(function(){
			$("[name='trainingId']").append(`<option value="${this['id']}">${this['trainingName']}</option>`)
		})
	}).fail(function(jqXHR, textStatus, errorThrown){
		// 通信エラーの場合処理
	})
	// メニュー変更
	$("[name='trainingId']").change(function(){
		displayChart($(this).val())
	})
}

// チャート描画
function displayChart(trainingId){
	$.ajax({
		type: "POST",
		url: restPath + "chart",
		data: {
			trainingId : trainingId
		}
	}).done(function(data, textStatus, jqXHR){
		var json = JSON.parse(data);
		var dates = new Array();
		var weight = new Array();
		$(json).each(function(){
			dates.push(this["date"])
			weight.push(this["weight"])
		})
		var ctx = $('#trainingChart')[0].getContext('2d') // 仮
		var myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: dates,
				datasets: [{
					label: "weight / kg",
					data: weight,
					backgroundColor: "rgba(153,255,51,0.4)"
				}]
			}
		});
	}).fail(function(jqXHR, textStatus, errorThrown){
		// 通信エラーの場合処理
	})
}
