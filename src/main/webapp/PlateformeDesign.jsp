<%-- 
    Document   : Plateforme
    Created on : 15 mars 2016, 14:14:34
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <div class="ressources">
        <img src=Style/Image/ferme/carotte.png alt="Carotte" border=3 align=middle> x ${carottesDispo.size()}<div class="boutonplanter"><a href="planter?type=carotte">Planter</a></div><br>
        <img src=Style/Image/ferme/ble.png alt="Ble" border=3 align=middle> x ${blesDispo.size()}<span><div class="boutonplanter"><a href="planter?type=ble">Planter</a></div></span><br><br>
        <img src=Style/Image/ferme/fromage.png alt="Fromage" border=3 align=middle> x ${fromagesDispo.size()}
        <br><br>
        <div class="bouton"><a href="actualisation">Actualiser</a></div>
    </div>
        
<!--     <div class="fonctionjeu">
         <div class="bouton"><a href=""> echanger </a></div>
         <div class="bouton"><a href=""> voir classement </a></div>

    </div>    
        -->
        
    <div style="float: left;display: inline;border:1px solid black;width:1000px;height:700px">

        <c:if test="${!empty fermiers}">
            <c:forEach items="${fermiers}" var="fermier" >
                <img src=Style/Image/ferme/fermier.png alt="Fermier" border=3 align=middle>
                <a href="nourrir?type=fermier&idFermier=${fermier.getId()}"><input type="button" value="Nourrir"></a>
                </c:forEach>

            <br>
        </c:if>
        <div class="main">
            <div class="potager">
                <c:if test="${!empty carottesPlantees}">
                    <c:if test="${carottesPlantees.size()<=5}">
                        <c:forEach items="${carottesPlantees}" var="carotte">
                            <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                        </c:forEach>
                    </c:if>
                    <c:if test="${carottesPlantees.size()>5}">
                        <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                        <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                        <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                        <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                        <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                        ....
                    </c:if>
                    (${carottesPlantees.size()})
                    <br>
                </c:if>
                <c:if test="${!empty blesPlantes}">
                    <c:if test="${blesPlantes.size()<=5}">
                        <c:forEach items="${blesPlantes}" var="ble">
                            <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                        </c:forEach>
                    </c:if>
                    <c:if test="${blesPlantes.size()>5}">
                        <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                        <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                        <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                        <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                        <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                        ....
                    </c:if>
                    (${blesPlantes.size()})
                    <br>
                </c:if>
            </div>
            <div class="enclos">
                
                <c:if test="${empty chevresNonEnceintes}">
                </c:if>

                <c:if test="${!empty chevresNonEnceintes}">
                    <div>
                        <c:forEach items="${chevresNonEnceintes}" var="chevre">
                            1chevre
                            <img src=Style/Image/ferme/chevre.png alt="Chevre" border=3 align=middle>
                        </c:forEach>

                        <c:if test="${chevresNonEnceintes.size()%2==0}">
                            <a href="accoupler_chevres"><input type="button" value="Accoupler 2 chevres"></a>
                            </c:if>
                    </div>
                </c:if>    

                <c:if test="${!empty chevresEnceintes}">
                    <div>
                        <c:forEach items="${chevresEnceintes}" var="chevre" varStatus="loop">
                            <c:if test="${loop.index%2==0}">
                                <img src=Style/Image/ferme/chevreamour.png alt="ChevreCouple" border=3 align=middle>
                            </c:if>
                            <c:if test="${blesDispo.size()>=nourrirChevreBle || CarottesDispo.size()>=nourrirChevreCarotte}">
                                <form method="post" action="nourrir">
                                    <c:if test="${blesDispo.size()>=nourrirChevreBle}">
                                        <input type="radio" name="nourriture" value="ble">1 ble
                                    </c:if>
                                    <c:if test="${carottesDispo.size()>=nourrirChevreCarotte}">
                                        <input type="radio" name="nourriture" value="carotte"> 1 carotte
                                    </c:if>
                                    <input type="hidden" name="type" value="chevre">
                                    <input type="hidden" name="idChevre" value="${chevre.getId()}"><br>
                                    <input type="submit" value="Nourrir">
                                </form>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
        <!--    <div style="float: right;display: inline;border:1px solid black;width:200px;height:700px">
        
            </div>-->



    </div>
