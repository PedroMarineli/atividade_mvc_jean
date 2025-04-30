<%-- 
    Document   : exibe_pessoas
    Created on : 29 de abr. de 2025, 15:01:55
    Author     : PEDRO_TI
--%>

<%@page import="java.util.List"%>
<%@page import="vo.CarroVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Listagem</title>
        
    </head>
    <body>

        <%
            List veiculos = (List) request.getAttribute("lista");
            if (veiculos != null) {
                out.print("<center>Achados: " + veiculos.size() + "</center><br><br><br>");
                out.print("<table width=\"50%\" border=\"1\" cellspacing=\"0\" align=\"center\">");
                for (int cont = 0; cont < veiculos.size(); cont++) {
                    CarroVO p = new CarroVO();
                    p = (CarroVO) veiculos.get(cont);
                    out.print("<tr>");
                    out.print("<td>" + p.getId() + "</td>");
                    out.print("<td>" + p.getMarca() + "</td>");
                    out.print("<td>" + p.getModelo() + "</td>");
                    out.print("<td>" + p.getAno() + "</td>");
                    out.print("<td><a href=\"CarroController?acao=3&id=" + p.getId() + "\">Excluir</a></td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            }
        %>
        
    </body>
</html>
