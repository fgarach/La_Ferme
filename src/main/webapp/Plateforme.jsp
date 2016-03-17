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
        nbCarotteDisponible : ${carottesDispo.size()}<a href="planter?type=carotte"><input type="button" value="Planter"></a><br>
        nbBleDisponible : ${blesDispo.size()}<a href="planter?type=ble"><input type="button" value="Planter"></a><br>
        nbFromages: ${fromagesDispo.size()}
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
                <c:forEach items="${carottesPlantees}" var="carotte">
                    1 carotte <br> 
                </c:forEach>
            </c:if>
            <c:if test="${!empty blesPlantes}">
                <c:forEach items="${blesPlantes}" var="ble">
                    1 ble <br> 
                </c:forEach>
            </c:if>        
        </div>



        <div style="float:right">
            <c:if test="${!empty chevresNonEnceintes}">
                <table>
                    ******chevres non enceintes******<br>
                    <tr>
                        <c:forEach items="${chevresNonEnceintes}" var="chevre">
                            <td>
                                1 chevre <br> 
                                <c:if test="${blesDispo.size()>=nourrirChevreBle || carottesDispo.size()>=nourrirChevreCarotte}">
                                    <form method="post" action="nourrir">
                                        <c:if test="${blesDispo.size()>=nourrirChevreBle}">
                                            <input type="radio" name="nourriture" value="ble" checked>1 ble<br>
                                        </c:if>
                                        <c:if test="${carottesDispo.size()>=nourrirChevreCarotte}">
                                            <input type="radio" name="nourriture" value="carotte" checked>1 carotte
                                        </c:if>
                                        <input type="hidden" name="type" value="chevre">
                                        <input type="hidden" name="idChevre" value="${chevre.getId()}"><br>
                                        <input type="submit" value="Nourrir">

                                    </form>
                                </c:if>
                            <td>
                            </c:forEach>
                    <tr>
                </table>
                <c:if test="${chevresNonEnceintes.size()%2==0}">
                    <a href="accoupler_chevres"><input type="button" value="Accoupler 2 chevres"></a>
                    </c:if>

            </c:if>    

            <c:if test="${!empty chevresEnceintes}">
                <table>
                    ********chevres enceintes********<br>
                    <tr>
                        <c:forEach items="${chevresEnceintes}" var="chevre">
                            <td>
                                1 chevre <br> 
                                <c:if test="${blesDispo.size()>=nourrirChevreBle || CarottesDispo.size()>=nourrirChevreCarotte}">
                                    <form method="post" action="nourrir">
                                        <c:if test="${blesDispo.size()>=nourrirChevreBle}">
                                            <input type="radio" name="nourriture" value="ble" checked>1 ble
                                        </c:if>
                                        <c:if test="${carottesDispo.size()>=nourrirChevreCarotte}">
                                            <input type="radio" name="nourriture" value="carotte" checked> 1 carotte
                                        </c:if>
                                        <input type="hidden" name="type" value="chevre">
                                        <input type="hidden" name="idChevre" value="${chevre.getId()}"><br>
                                        <input type="submit" value="Nourrir">

                                    </form>
                                </c:if>
                            <td>
                            </c:forEach>
                    <tr>
                </table>
            </c:if>
        </div>
    </div>
    <div style="float: right;display: inline;border:1px solid black;width:200px;height:700px">

    </div>



</div>
