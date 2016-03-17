<%-- 
    Document   : _OPTIONJEU
    Created on : 17 mars 2016, 10:43:31
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="fonctionjeu">
    <span class="bouton"><a class="bouton" href="echange"> echanger      </a></span>
    <span class="bouton"><a class="bouton" href="classement"> voir classement </a></span>

    <c:if test="${option == 'echange'}">
        <br>
        Choisissez ce que vous souhaitez echanger : 
        <br>
        <c:if test="${chevresNonEnceintes.size()>=tauxEchangeChevre || CarottesDispo.size()>=tauxEchangeCarotte || blesDispo.size()>=tauxEchangeBle}">
            <form method="post" action="echange">
                Echange :
                <c:if test="${chevresNonEnceintes.size()>=tauxEchangeChevre}">
                    <input type="radio" name="echange" value="chevre"> ${tauxEchangeChevre} chevre
                </c:if>
                <c:if test="${carottesDispo.size()>=tauxEchangeCarotte}">
                    <input type="radio" name="echange" value="carotte"> ${tauxEchangeCarotte} carottes
                </c:if>
                <c:if test="${blesDispo.size()>=tauxEchangeBle}">
                    <input type="radio" name="echange" value="ble"> ${tauxEchangeBle} bles
                </c:if>
                    <br>
                Contre :
                <input type="radio" name="contre" value="chevre"> ${tauxEchangeChevre} chevre
                <input type="radio" name="contre" value="carotte"> ${tauxEchangeCarotte} carottes
                <input type="radio" name="contre" value="ble"> ${tauxEchangeBle} bles
                <input type="submit" value="Echanger">
            </form>
        </c:if>

        <c:if test="${!option == 'echange'}">
            xxxxxxxxxxxxxxx
        </c:if>
    </c:if>
</div>