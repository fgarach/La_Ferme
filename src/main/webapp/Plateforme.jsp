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
        nbCarotteDisponible : ${nbCarotte}<a href="planter?type=carotte"><input type="button" value="Planter"></a><br>
        nbBleDisponible : ${nbBle}<a href="planter?type=ble"><input type="button" value="Planter"></a><br>
        nbFromages: ${nbFromage}
        <br><br>
        <a href="actualisation"><input type="button" value="Actualiser"></a>


    </div>
    <div style="float: left;display: inline;border:1px solid black;width:1000px;height:700px">

        <c:if test="${!empty fermiers}">
            <c:forEach items="${fermiers}" var="fermier">
                1 fermier <br> 
                <a href="nourrir?type=fermier&idFermier=${fermier.getId()}"><input type="button" value="Nourrir"></a>
            </c:forEach>
        </c:if>

        
        <c:if test="${!empty chevresNonEnceintes}">
            <div>
                chevres non enceintes*******************<br>
                <c:forEach items="${chevresNonEnceintes}" var="chevre">
                    1 chevre <br> 
                </c:forEach>

                <c:if test="${chevresNonEnceintes.size()%2==0}">
                    <a href="accoupler_chevres"><input type="button" value="Accoupler 2 chevres"></a>
                    </c:if>
            </div>
        </c:if>    

        <c:if test="${!empty chevresEnceintes}">
            <div>
                chevres enceintes************************<br>
                <c:forEach items="${chevresEnceintes}" var="chevre">
                    1 chevre <br> 
                </c:forEach>
            </div>
        </c:if>


        <c:if test="${!empty carottes}">
            <c:forEach items="${carottes}" var="carotte">
                1 carotte <br> 
            </c:forEach>
        </c:if>
        <c:if test="${!empty bles}">
            <c:forEach items="${bles}" var="ble">
                1 ble <br> 
            </c:forEach>
        </c:if>        




    </div>
    <div style="float: right;display: inline;border:1px solid black;width:200px;height:700px">

    </div>



</div>
