<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h3 class="page-title">${menu.menuName}
    <small>${menu.memo}</small>
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a href="<c:url value="/"/>"><spring:message code="home"/></a>
            <i class="fa fa-angle-right"></i>
        </li>
        <c:import url="/develop/menu/current?menuId=${param.menuId}"/>
    </ul>
</div>