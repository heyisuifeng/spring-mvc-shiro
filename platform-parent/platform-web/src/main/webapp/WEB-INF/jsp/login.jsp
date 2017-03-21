<%--@elvariable id="message" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <title>InfiTecs Expense</title>
    <%@include file="includes/head.jsp"%>
    <link href="<c:url value="/assets/admin/pages/css/login-soft.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="logo">
    <a href="<c:url value="/login"/>">
        <img src="<c:url value="/img/logo-big.png"/>" alt="" style="height:50px"/>
    </a>
</div>
<div class="menu-toggler sidebar-toggler">
</div>
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="/login" method="post">
        <input type="hidden" name="locale" value="en"/>
        <h3 class="form-title"><spring:message code="loginToYourAccount"/></h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span><spring:message code="enterAnyUsernameAndPassword"/></span>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9"><spring:message code="username"/></label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
                       placeholder="<spring:message code="username"/>" name="username" id="username" value="admin"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9"><spring:message code="password"/></label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input id="password" class="form-control placeholder-no-fix" type="password" autocomplete="off"
                       placeholder="<spring:message code="password"/>" name="password" value="admin"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/><spring:message code="rememberMe"/></label>
            <button type="button" id="loginBtn" class="btn blue pull-right"><spring:message code="login"/><i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END LOGIN FORM -->
</div>
<div class="copyright">
    Copyright © 2014-2015 InfiTecs Technology Co., Ltd
</div>
<%@include file="includes/bottomscript.jsp"%>
<script src="<c:url value="/js/bower_components/jquery-backstretch/jquery.backstretch.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/login.js"/>" type="text/javascript"></script>
<script>
    var languageType = "<%=session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)%>";
    $(function(){
        $.i18n.properties({
            name:'ViewMessages_en',
            path: '../i18n/',
            mode:'map',
            language:'en'
        });
        Login.init();
        <c:if test="${message!=null}">
        toast.error($.i18n.prop());
        </c:if>
    })
</script>
</body>
</html>