<%-- 
    Document   : _HEADER
    Created on : 15 mars 2016, 11:07:17
    Author     : admin
--%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="titre">
        <br>
    <c:if test="${sessionScope.email!=null}">
        <span style="float : left;"> ${sessionScope.email} </span>
        <span style="float : right;"> <div class="bouton"><a href="deconnexion">Se deconnecter</a></div></span>
    </c:if>
    <c:if test="${sessionScope.email==null}">
        <span style="float : left;"> <a href="login">Se connecter</a></span>
        <span style="float: right; color: white"><a href="inscription">inscription</a></span>
    </c:if> 

</div>