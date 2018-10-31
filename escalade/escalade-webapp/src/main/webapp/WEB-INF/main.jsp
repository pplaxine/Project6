
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            <% 
            
            String att1 = (String) request.getAttribute("test");
            String att2 = (String) request.getAttribute("nbreTest");
            String att3 = (String) request.getAttribute("nbreCompteUtilisateur");
            
            out.println( att1 + " "+ att2 + ". Nombre de compte utilisateur en BDD : " + att3 );
            
            
            
            %>
        </p>
    </body>
</html>