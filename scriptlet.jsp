<html>
<head>
    <title>JSP Scriptlet</title>
</head>
<body>

    <h1>JSP Scriptlet</h1>

    <%
        java.util.Date date = new java.util.Date();
        String host = request.getRemoteHost();
    %>

    <p>Current time: <%= date %></p>

    <p>Server's remote host: <%= host %></p>

</body>
</html>
