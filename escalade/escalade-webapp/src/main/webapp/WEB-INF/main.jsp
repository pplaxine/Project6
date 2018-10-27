
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
            String attribut2 = (String) request.getAttribute("test2");
            String attribut = (String) request.getAttribute("test");
            out.println( attribut + " " + attribut2 );
            
            %>
        </p>
    </body>
</html>