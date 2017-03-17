<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header navbar navbar-fixed-top">
    <div class="page-header-inner">
        <div class="page-logo">
            <a href="<c:url value="/"/>"><img src="<c:url value="/img/logo-big4.png"/>" alt="logo" class="logo-default" style="height:33px"/></a>
            <div class="menu-toggler sidebar-toggler hide"></div>
        </div>
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"></a>
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <span class="username username-hide-on-mobile"><spring:message code="welcome"/>: ${sessionScope.user.nickname}</span> <i class="fa fa-angle-down"></i>
                        <input type="hidden" id="loginUser" value="${sessionScope.user.nickname}">
                        <input type="hidden" id="loginUserName" value="${sessionScope.user.username}">
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:;" id="updatePass"><i class="icon-lock"></i><spring:message code="changePassword"/></a></li>
                        <li><a href="<c:url value="/logout"/>"><i class="icon-key"></i><spring:message code="logOff"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="modal fade bs-modal-lg" id="updatePasswordModal" tabindex="-1" data-keyboard="false" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" id="updatePasswordContent">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title"><spring:message code="resetPassword"/></h4>
            </div>
            <div class="modal-body form">
                <form id="updatePasswordDialog" class="form-horizontal">
                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-md-4 control-label"><spring:message code="oldPassword"/><span class="required"> * </span></label>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control input-medium" placeholder="<spring:message code="oldPassword"/>" id="oldPassword" name="oldPassword">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-md-4 control-label"><spring:message code="newPassword"/><span class="required"> * </span></label>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control input-medium" placeholder="<spring:message code="newPassword"/>" id="newPassword" name="newPassword">
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group last">
                                    <label class="col-md-4 control-label"><spring:message code="confirmPassword"/><span class="required"> * </span></label>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control input-medium" placeholder="<spring:message code="confirmPassword"/>" id="reNewPassword" name="reNewPassword">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn blue" id="updatePassword"><spring:message code="save"/></button>
                <button type="button" class="btn default" data-dismiss="modal"><spring:message code="close"/></button>
            </div>
        </div>
    </div>
</div>
<div class="clearfix"></div>