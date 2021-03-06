var Login = function () {

    function login(){
        var $loginForm = $('.login-form');
        if ($loginForm.validate().form()) {
            var $password = $("#password");
            $password.val($password.val().md5());
            $loginForm.submit();
        }
    }


    var handleLogin = function() {
        $('.login-form').validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: $.i18n.prop().name
                },
                password: {
                    required: $.i18n.prop().name
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.alert-danger', $('.login-form')).show();
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                form.submit();
            }
        });

        $('#username').keypress(function (e) {
            if (e.which == 13) {
                $("#password").focus();
                return false;
            }
        }).focus();

        $('#password').keypress(function (e) {
            if (e.which == 13) {
                login();
                return false;
            }
        });

        $("#loginBtn").on("click",function(){
            login();
        });

        $.backstretch([
            "img/login/1.jpg",
            "img/login/2.jpg",
            "img/login/3.jpg",
            "img/login/4.jpg"
        ], {
            fade: 1000,
            duration: 8000
        });
    };

    return {
        //main function to initiate the module
        init: function () {
            handleLogin();
        }

    };

}();