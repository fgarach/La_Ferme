<%-- 
    Document   : _HOME
    Created on : 15 mars 2016, 11:07:40
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="body2">
        <h1>Bienvenue sur le site de jeu de La Ferme ! </h1>
    <c:if test="${sessionScope.email==null}">
        Veuillez vous inscrire ou vous connecter pour acceder au jeu.
    </c:if>
    <c:if test="${sessionScope.email!=null}">
        <h2><div class="bouton"> <a href="start">Commencer une partie</a></div></h2>
    </c:if>
</body>
</html>
