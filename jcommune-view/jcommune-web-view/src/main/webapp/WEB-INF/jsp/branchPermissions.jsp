<%--

    Copyright (C) 2011  JTalks.org Team
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.
    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.
    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jtalks" uri="http://www.jtalks.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta name="description" content="<c:out value="${cmpDescription}"/>">
    <title>
        <c:out value="${cmpTitlePrefix}"/>
        <c:out value="${cmpDescription}"/>
    </title>
</head>
<body>
<div class="container">
    <div class="permissions">
        <c:forEach items="${permissions.permissions}" var="entry">
            <div class="permission-header"> ${entry.name}</div>
            <div class="permission-block">

                <div class="pull-left permission-type"> Allowed </div>
                <div class="permissions-container">
                    <ul class="permissions-list">
                        <c:forEach items="${permissions.getAllowed(entry)}" var="group">
                            <li> ${group.name}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="cleared"></div>

                <div class="pull-left permission-type"> Restricted </div>
                <div class="permissions-container">
                    <ul class="permissions-list">
                        <c:forEach items="${permissions.getRestricted(entry)}" var="group">
                            <li> ${group.name}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="cleared"></div>
            </div>
            <div class="cleared"></div>
        </c:forEach>
    <div/>
</div>
</body>