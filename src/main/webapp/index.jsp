<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

</head>
<body>
<form action="/hello/login" method="post">
    <%
        Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
        if(errors != null && errors.get("login") != null){
            out.println("<h1>" + errors.get("login") +"</h1>");
        }
    %>
    <input type="text" name="login"/>
    <%
    if(errors != null && errors.get("password") != null){
        out.println("<h1>" + errors.get("password") +"</h1>");
    }
    %>
    <input type="password" name="password"/>
    <input type="submit" name="Login"/>
</form>
</h1>
<br/>
<a href="products">do Products things</a>
<c:forEach var="element" items="${names}" >
    <c:if test="${element.length()>3}">
        <h1>
            <c:out value="${element}"></c:out>
        </h1>
    </c:if>
    <c:if test="${element.length()<=3}">
        <h1>
            <c:out value="${element}">Tom is less than 4</h1></c:out>

    </c:if>
<%--    <c:if test="${errors['password'] != null}">--%>
<%--        <c:redirect url="success.jsp"></c:redirect>--%>
<%--    </c:if>--%>
    <c:choose>
        <c:when test="${element == 'Pasha'}"><h1>This is pasha</h1></c:when>
        <c:when test="${element == 'Misha'}"><h1>This is misha></h1></c:when>
    </c:choose>
        </c:forEach>

</body>
</html>