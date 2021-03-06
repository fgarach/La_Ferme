<%-- 
    Document   : Plateforme
    Created on : 15 mars 2016, 14:14:34
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <div style="float: left;display: inline;background-color: #336600;width:300px;height:700px;color:white;text-align: left;opacity: 0.8">
        nbCarotteDisponible : ${carottesDispo.size()}<a href="planter?type=carotte"><input type="button" value="Planter"></a><br>
        nbBleDisponible : ${blesDispo.size()}<a href="planter?type=ble"><input type="button" value="Planter"></a><br>
        nbFromages: ${fromagesDispo.size()}<br><br>
        Lune = ${lune}<br><br>
        Vie du fermier : ${vieFermier}
 
        <br><br>
        <a href="actualisation"><input type="button" value="Actualiser"></a>
    </div>
        
    <div style="float: left;display: inline;border:1px solid black;width:1000px;height:700px">
        <div style="text-align: center">
           
            <c:if test="${!empty fermiers}">
                <c:forEach items="${fermiers}" var="fermier">
                    1 fermier<br>
                    <c:if test="${blesDispo.size()>=nourrirFermierBle || carottesDispo.size()>=nourrirFermierCarotte || fromagesDispo.size()>=nourrirFermierFromage || chevresNonEnceintes.size()>=nourrirFermierChevre}">
                        <form method="post" action="nourrir">
                            <c:if test="${blesDispo.size()>=nourrirFermierBle}">
                                <input type="radio" name="nourriture" value="ble" checked>3 bles
                            </c:if>
                            <c:if test="${carottesDispo.size()>=nourrirFermierCarotte}">
                                <input type="radio" name="nourriture" value="carotte"checked> 2 carottes
                            </c:if>
                            <c:if test="${fromagesDispo.size()>=nourrirFermierFromage}">
                                <input type="radio" name="nourriture" value="fromage"checked> 2 fromages
                            </c:if>
                            <c:if test="${chevresNonEnceintes.size()>=nourrirFermierChevre}">
                                <input type="radio" name="nourriture" value="chevre"checked> 1 chevre
                            </c:if>
                            <br>
                            <input type="hidden" name="type" value="fermier">
                            <input type="hidden" name="idFermier" value="${fermier.getId()}">
                            <input type="submit" value="Nourrir">
                        </form>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>


        <div style="float:left">
            <c:if test="${!empty carottesPlantees}">
                Nombre de carottes plantees : ${carottesPlantees.size()} <br> 
            </c:if>
            <c:if test="${!empty blesPlantes}">

                Nombre de bles plantes : ${blesPlantes.size()} <br> 

            </c:if>        
        </div>



        <div style="float:right">
            <c:if test="${!empty chevresNonEnceintes}">

                ******chevres non enceintes******<br>

                    Nombre de chevres : ${chevresNonEnceintes.size()}<br> 

                <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresNonEnceintes.size()) || carottesDispo.size()>=(nourrirChevreCarotte*chevresNonEnceintes.size())}">
                    <form method="post" action="nourrir">
                        <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresNonEnceintes.size())}">
                            <input type="radio" name="nourriture" value="ble" checked>1 ble
                        </c:if>
                        <c:if test="${carottesDispo.size()>=(nourrirChevreCarotte*chevresNonEnceintes.size())}">
                            <input type="radio" name="nourriture" value="carotte" checked>1 carotte
                        </c:if>
                        <input type="hidden" name="type" value="chevre">
                        <input type="hidden" name="enceinte" value="faux">
                        <br>
                        <input type="submit" value="Nourrir">

                    </form>
                </c:if>

                    <c:if test="${chevresNonEnceintes.size()>=2}">
                    <a href="accoupler_chevres"><input type="button" value="Accoupler 2 chevres"></a>
                    </c:if>

            </c:if>    

            <c:if test="${!empty chevresEnceintes}">
                ******chevres enceintes******<br>

                Nombre de couples : ${Math.round(chevresEnceintes.size()/2)}<br> 

                <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresEnceintes.size()) || carottesDispo.size()>=(nourrirChevreCarotte*chevresEnceintes.size())}">
                    <form method="post" action="nourrir">
                        <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresEnceintes.size())}">
                            <input type="radio" name="nourriture" value="ble" checked>1 ble
                        </c:if>
                        <c:if test="${carottesDispo.size()>=(nourrirChevreCarotte*chevresEnceintes.size())}">
                            <input type="radio" name="nourriture" value="carotte" checked>1 carotte
                        </c:if>
                        <input type="hidden" name="type" value="chevre">
                        <input type="hidden" name="enceinte" value="vrai">
                        <br>
                        <input type="submit" value="Nourrir">
                    </form>
                </c:if>
            </c:if>
    </div>
</div>
<div style="float: right;display: inline;border:1px solid black;width:200px;height:700px">

</div>



</div>
