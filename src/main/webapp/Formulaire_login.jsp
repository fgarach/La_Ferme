<%-- 
    Document   : Formulaire_login
    Created on : 15 mars 2016, 12:10:12
    Author     : admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <h1>Connexion</h1>
    <form action="login" method="post">
        <table  class="table">
            <tr>
                <td> <label>Email</label></td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td> <label>Mot de passe</label></td>
                <td><input type="text" name="mdp"></td>
            </tr>
        </table>
        <c:if test="${info!=null}">
            <div>   ${info}</div>
        </c:if>
        <br><br><input type="submit"/>
    </form>
</div>
