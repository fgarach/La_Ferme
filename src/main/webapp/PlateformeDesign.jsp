<%-- 
    Document   : Plateforme
    Created on : 15 mars 2016, 14:14:34
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Ferme</title>
        <script type="text/javascript">
            var task = function () {
                document.location.reload();
            }
            window.setTimeout(task, 1000);
        </script>


        <c:import url="_CSS.jsp"/>
    </head>
    <body>
        <c:import url="_HEADER.jsp"/>
        <c:import url="_OPTIONJEU.jsp"/>
        <div>
            <div class="ressources">
                <img src=Style/Image/ferme/carotte.png alt="Carotte" border=3 align=middle> x ${carottesDispo.size()}<div class="boutonplanter"><a href="planter?type=carotte">Planter</a></div><br>
                <img src=Style/Image/ferme/ble.png alt="Ble" border=3 align=middle> x ${blesDispo.size()}<span><div class="boutonplanter"><a href="planter?type=ble">Planter</a></div></span><br><br>
                <img src=Style/Image/ferme/fromage.png alt="Fromage" border=3 align=middle> x ${fromagesDispo.size()}<br><br>
                Lune = ${lune}<br><br>
                Vie du fermier : ${vieFermier}
                <br><br>
                <div class="bouton"><a href="actualisation?id=${sessionScope.login}">Actualiser</a></div>
            </div>

            <!--     <div class="fonctionjeu">
                     <div class="bouton"><a href=""> echanger </a></div>
                     <div class="bouton"><a href=""> voir classement </a></div>
            
                </div>    
            -->

            <div style="float: left;display: inline;border:1px solid black;width:1000px;height:700px">

                <div style="height:230px;">
                    <c:if test="${!empty fermiers}">
                        <c:forEach items="${fermiers}" var="fermier" >
                            <img src=Style/Image/ferme/fermier.png alt="Fermier" border=3 align=middle>
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

                <div class="main">
                    <div class="potager">
                        <c:if test="${!empty carottesPlantees}">
                            <c:if test="${carottesPlantees.size()<=4}">
                                <c:forEach items="${carottesPlantees}" var="carotte">
                                    <img src=Style/Image/ferme/petitecarotte.png alt="Carotte" border=3 align=middle>
                                </c:forEach>
                            </c:if>
                            <c:if test="${carottesPlantees.size()>4}">
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
                            <c:if test="${blesPlantes.size()<=4}">
                                <c:forEach items="${blesPlantes}" var="ble">
                                    <img src=Style/Image/ferme/petitble.png alt="Ble" border=3 align=middle>
                                </c:forEach>
                            </c:if>
                            <c:if test="${blesPlantes.size()>4}">
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

                                <img src=Style/Image/ferme/chevre.png alt="Chevre" border=3 align=middle>
                                (${chevresNonEnceintes.size()})

                                <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresNonEnceintes.size()) || carottesDispo.size()>=(nourrirChevreCarotte*chevresNonEnceintes.size())}">
                                    <form method="post" action="nourrir">
                                        <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresNonEnceintes.size())}">
                                            <input type="radio" name="nourriture" value="ble" checked> ${chevresNonEnceintes.size()} ble
                                        </c:if>
                                        <c:if test="${carottesDispo.size()>=(nourrirChevreCarotte*chevresNonEnceintes.size())}">
                                            <input type="radio" name="nourriture" value="carotte" checked> ${chevresNonEnceintes.size()} carotte
                                        </c:if>
                                        <input type="hidden" name="type" value="chevre">
                                        <input type="hidden" name="enceinte" value="faux">
                                        <br>
                                        <input type="submit" value="Nourrir">

                                    </form>
                                </c:if>

                                <c:if test="${chevresNonEnceintes.size()>=2}">
                                    <br><a href="accoupler_chevres"><input type="button" value="Accoupler 2 chevres"></a>
                                    </c:if>
                            </div>
                        </c:if>    

                        <c:if test="${!empty chevresEnceintes}">
                            <div>

                                <img src=Style/Image/ferme/chevreamour.png alt="ChevreCouple" border=3 align=middle>
                                (${Math.round(chevresEnceintes.size()/2)})
                                <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresEnceintes.size()) || carottesDispo.size()>=(nourrirChevreCarotte*chevresEnceintes.size())}">
                                    <form method="post" action="nourrir">
                                        <c:if test="${blesDispo.size()>=(nourrirChevreBle*chevresEnceintes.size())}">
                                            <input type="radio" name="nourriture" value="ble" checked> ${chevresEnceintes.size()} ble
                                        </c:if>
                                        <c:if test="${carottesDispo.size()>=(nourrirChevreCarotte*chevresEnceintes.size())}">
                                            <input type="radio" name="nourriture" value="carotte" checked> ${chevresEnceintes.size()} carotte
                                        </c:if>
                                        <input type="hidden" name="type" value="chevre">
                                        <input type="hidden" name="enceinte" value="vrai">
                                        <br>
                                        <input type="submit" value="Nourrir">
                                    </form>
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="_FOOTER.jsp"/>

    </body>
</html>
