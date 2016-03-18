<%-- 
    Document   : Formulaire_login
    Created on : 15 mars 2016, 12:10:12
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <body class="body2">
    <h1>Inscription</h1>
    <form action="inscription" method="post">
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
        <br>
        <input type="submit"/>
    </form>
</div>
