<%-- 
    Document   : Plateforme
    Created on : 15 mars 2016, 14:14:34
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <div style="float: left;display: inline;border:1px solid black;width:300px;height:700px">
        nbCarotteDisponible : ${nbCarotte}<a href="planter"><input type="button" value="Planter"></a><br>
        nbBleDisponible : ${nbBle}<input type="button" value="Planter"><br><br>
        nbChevres: ${nbChevre}<br>
        nbFromages: 0

        
    </div>
    <div style="float: left;display: inline;border:1px solid black;width:1000px;height:700px">
        
        <c:if test="${!empty carottes}">
            <c:forEach items="${carottes}" var="carotte">
                1 carotte <br> 
            </c:forEach>
        </c:if>

    </div>
    <div style="float: right;display: inline;border:1px solid black;width:200px;height:700px">

    </div>



</div>
