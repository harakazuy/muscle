/**
 * 
 */

// バリデーション後にログインフォーム送信
function loginValidation(){
	var msg_required = "を入力してください。";
	
	$("[name='username']").attr("data-validation", "required");
	$("[name='username']").attr("data-validation-error-msg", 'ユーザー名' + msg_required);
	
	$("[name='password']").attr("data-validation", "required");
	$("[name='password']").attr("data-validation-error-msg", 'パスワード' + msg_required);
	
	$.validate({
		errorMessagePosition : 'top',
		language : {
			errorTitle: 'ログインに失敗しました'
		},
		onSuccess : function(form) {
			login(form.serialize())
			return false // ページ遷移しない
		}
	})
}

// ログイン処理
function login(form){
	$.ajax({
		type: "POST",
		url: contextPath + "loginPerform",
		data: form
	}).done(function(result){
		var json = JSON.parse(result)
		if(json["status"] == true){
			window.location.href = contextPath + 'common'
		}else{
			window.location.href = contextPath + 'common'
		}
	})
}