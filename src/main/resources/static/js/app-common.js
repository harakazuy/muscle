/**
 * アプリ全体での共通部分
 */

// コンテキストパス
var contextPath = $("#contextPath").val()
var restPath = contextPath + "rest/"


// TODO:ログインをいい感じに非同期にする
// ヘッダー、フッター、ログインページ
function getLoginPage(){
	getPage('header')
	getPage('footer')
//	getPage('loginForm')
	loginValidation()
}
//ヘッダー、フッター、ナビバー、初期表示ページ
function getTopPage(){
	getPage('header')
	getPage('footer')
	getPage('navbar')
	getPage('index')
}

// ページ取得 TODO:共通処理をいい感じに纏める
function getPage(pageName, upperDefer = $.Deferred()){
	$.ajax(contextPath + pageName, {
		dataType: 'html',
		context:{
			pageName : pageName
		}
	}).then(function(data){
		var pageHtml = $($.parseHTML(data)).filter('#' + pageName)[0].outerHTML
		var limit = 3
		switch(pageName){
			case "navbar":
				$("#navbar-outer").empty().append(pageHtml)
				pageSwitchable()
				break
			case "header":
				$("#header-outer").empty().append(pageHtml)
				break
			case "footer":
				$("#footer-outer").empty().append(pageHtml)
				break
			case "index":
				$('#navbar li').removeAttr("class", "active")
				$(".index").parent("li").attr("class", "active")
				$('#page-outer').empty().append(pageHtml)
				setTrainingForm(pageName)
				break
			case "record":
				$('#navbar li').removeAttr("class", "active")
				$(".record").parent("li").attr("class", "active")
				$('#page-outer').empty().append(pageHtml)
				paginationToRecord(limit, upperDefer)
				break
			case "chart":
				$('#navbar li').removeAttr("class", "active")
				$(".chart").parent("li").attr("class", "active")
				$('#page-outer').empty().append(pageHtml)
				setChart()
				break
			case "loginForm":
				$('#page-outer').empty().append(pageHtml)
				break
			default:
				break
		}
	})
}

// ナビバーのページ切り替え
function pageSwitchable(){
	$('#navbar a').click(function(){
		getPage($(this).attr("class"))
	})
}