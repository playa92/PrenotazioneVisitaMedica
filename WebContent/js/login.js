//REMEMBER ME
$(function() {
	
    if(localStorage.chkbox && localStorage.chkbox != '') {

        $('#rememberChkBox').attr('checked', 'checked');
        $('#signinId').val(localStorage.username);
        $('#signinPwd').val(localStorage.pass);
    } else {

        $('#rememberChkBox').removeAttr('checked');
        $('#signinId').val('');
        $('#signinPwd').val('');
    }

    $('#rememberChkBox').click(function() {

        if ($('#rememberChkBox').is(':checked')) {
            // save username and password
            localStorage.username = $('#signinId').val();
            localStorage.pass = $('#signinPwd').val();
            localStorage.chkbox = $('#rememberChkBox').val();
        } else {
            localStorage.username = '';
            localStorage.pass = '';
            localStorage.chkbox = '';
        }
    });
});
