<%-- 
    Document   : exibe_resultado
    Created on : 17 de nov. de 2024, 22:33:43
    Author     : Jean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="2; URL=CarroController?acao=2">
        <title>Resultado</title>
    </head>
    <body>
    <center>
        <h1>
            <%

                int resultado = Integer.parseInt(request.getParameter("result"));
                if (resultado == 1) {
                    out.print("<font color=\"blue\">SUCESSO</font>");
                } else {
                    out.print("<font color=\"red\">ERRO</font>");
                }

            %>
        </h1>
    </center>
</body>
</html>
