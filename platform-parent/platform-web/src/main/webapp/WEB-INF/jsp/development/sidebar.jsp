<%@ page import="com.iKMAK.model.Menu" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--@elvariable id="menu" type="com.infitecs.eyas.develop.model.Menu"--%>
<%--@elvariable id="level0List" type="java.util.List<com.infitecs.eyas.develop.model.Menu>"--%>
<%--@elvariable id="level1Map" type="java.util.Map<java.lang.String,java.util.List<com.infitecs.eyas.develop.model.Menu>>"--%>
<%--@elvariable id="level2Map" type="java.util.Map<java.lang.String,java.util.List<com.infitecs.eyas.develop.model.Menu>>"--%>
<%--@elvariable id="level1Menu" type="com.infitecs.eyas.develop.model.Menu"--%>
<%--@elvariable id="level2Menu" type="com.infitecs.eyas.develop.model.Menu"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu " data-auto-scroll="true" data-slide-speed="200">
            <li class="sidebar-toggler-wrapper">
                <div class="sidebar-toggler"></div>
            </li>
            <li class="sidebar-search-wrapper">
                <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                <div class="sidebar-search">
                    <a href="javascript:;" class="remove">
                        <i class="icon-close"></i>
                    </a>

                    <div class="input-group">
                        <input type="text" class="form-control" data-gautoflag="gAutoMenu"
                               placeholder="<spring:message code="menuSearch"/>" id="menuSearch" autocomplete="off">
                        <span class="input-group-btn">
                            <a href="javascript:;" class="btn submit" id="menuSearchBtn"><i class="icon-magnifier"></i></a>
                        </span>
                    </div>
                </div>
                <!-- END RESPONSIVE QUICK SEARCH FORM -->
            </li>
            <c:forEach var="menu" items="${sessionScope.level0List}">
                <li class="heading">
                    <h3 class="uppercase">${menu.menuName}</h3>
                </li>
                <c:forEach var="level1Menu" items="${sessionScope.level1Map[menu.menuId]}">
                    <li id="menu_${level1Menu.menuId}">
                        <a href="<c:choose><c:when test="${level1Menu.leaf}">${ctx}/${level1Menu.url}</c:when><c:otherwise>javascript:;</c:otherwise></c:choose>">
                            <c:if test="${not empty level1Menu.icon}"><i class="${level1Menu.icon}"></i></c:if>
                            <span class="title">${level1Menu.menuName}</span>
                            <span class="selected"></span>
                            <span class="arrow"></span>
                        </a>
                        <c:if test="${!level1Menu.leaf}">
                            <ul class="sub-menu">
                                <c:forEach var="level2Menu" items="${sessionScope.level2Map[level1Menu.menuId]}">
                                    <li id="menu_${level2Menu.menuId}">
                                        <a href="${ctx}/${level2Menu.url}"><c:if test="${not empty level2Menu.icon}"><i
                                                class="${level2Menu.icon}"></i></c:if> ${level2Menu.menuName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
<!-- END SIDEBAR -->
<c:set var="menu" value="${applicationScope.menuMap[param.menuId]}"/>
<input type="hidden" id="menuParentId" value="${menu.parentId}"/>
<input type="hidden" id="menuId" value="${menu.menuId}"/>