<%-- 
    Document   : _OPTIONJEU
    Created on : 17 mars 2016, 10:43:31
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="fonctionjeu">
    
    Echange : 

 
        <br>
        Choisissez ce que vous souhaitez echanger : 
        <br>
        <c:if test="${chevresNonEnceintes.size()>=tauxEchangeChevre || CarottesDispo.size()>=tauxEchangeCarotte || blesDispo.size()>=tauxEchangeBle}">
            <form method="post" action="plateforme">
                Echange :
                <c:if test="${chevresNonEnceintes.size()>=tauxEchangeChevre}">
                    <input type="radio" name="ressechange" value="chevre" checked> ${tauxEchangeChevre} chevre
                </c:if>
                <c:if test="${carottesDispo.size()>=tauxEchangeCarotte}">
                    <input type="radio" name="ressechange" value="carotte"checked> ${tauxEchangeCarotte} carottes
                </c:if>
                <c:if test="${blesDispo.size()>=tauxEchangeBle}">
                    <input type="radio" name="ressechange" value="ble"checked> ${tauxEchangeBle} bles
                </c:if>
                <br>
                Contre :
                <input type="radio" name="resscontre" value="chevre"checked> ${tauxEchangeChevre} chevre
                <input type="radio" name="resscontre" value="carotte"> ${tauxEchangeCarotte} carottes
                <input type="radio" name="resscontre" value="ble"> ${tauxEchangeBle} bles
                <input type="submit" value="Echanger">
            </form>
        </c:if>


<!--    Classement : 
    
        <table>
            <tr>
                <td>classement</td><td>Utilisateur</td><td>score</td>
            </tr>
            <tr>
                <c:forEach items="${classements}" var="util" varStatus="loop" >
                    <td>loop.index</td> <td>util.email</td> <td>util.score</td>
                </c:forEach>
            </tr>
        </table>-->

</div>