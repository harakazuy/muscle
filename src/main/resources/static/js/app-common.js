/**
 * アプリ全体での共通部分
 */

// ヘッダー、フッター、ナビバー
$("#header-outer").load("header #header");
$("#footer-outer").load("footer #footer");

function loadNavbar(pageId){
	$("#navbar-outer").load("navbar #navbar", function(){
		$(pageId).attr("class", "active");
	});
}

// コンテキストパス
var contextPath = $("#contextPath").val()
var restPath = contextPath + "rest"