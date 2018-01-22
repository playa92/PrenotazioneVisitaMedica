$(document).on(ready, function() {

  fillByMemory()
  $('input#sign').on('click', function() {

    if ($('#rememberChkBox').val()) {
      rememberMe();
    }
    
    doLogin();
  	});
 
});

function rememberMe() {
  $.cookie('id', $('#signinId').val());
  $.cookie('pass', $('#signinPwd').val());
}

function fillByMemory() {
  if (!!$.cookie('id'))
    $('#signinId').val($.cookie('id'));

  if (!!$.cookie('pass'))
    $('#signinPwd').val($.cookie('pass'));
}
