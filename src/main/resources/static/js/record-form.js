/**
 * 
 */
		$("#header-outer").load("header #header");
		$("#navbar-outer").load("navbar #navbar");
		$("#footer-outer").load("footer #footer");
		var form;
		function setTraininForm(page){
			$("#form-outer").load("form #trainingForm", function(){
				appendOption();
				form = $('#trainingForm').html();
				if(page == "index") appendForm(form);
			});
		};
		function appendOption(){
			for(var i = 1; i <= 50; i++){
				$("[name='repetition']").append('<option value=\"' + i + '\">' + i + '</option>')
			}
			for(var i = 1; i <= 20; i++){
				$("[name='setCount']").append('<option value=\"' + i + '\">' + i + '</option>')
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
		formValidation();
		function removeCurrent(){
			$('.removeCurrent').click(function(){
				$(this).parent().parent().remove();
			})
		};
		removeCurrent();
		function appendForm(fm){
			$('#form').append(fm);
			formValidation();
			removeCurrent();
		};
		$('#appendForm').click(function(){
			appendForm(form);
		});
		$('#removeForm').click(function(){
			$('#form div:last-child').remove();
		});