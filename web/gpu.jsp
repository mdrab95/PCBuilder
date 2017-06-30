<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PCBuilder - servlets</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
    </style>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index">PCBuilder Component Lists:</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="cpu">CPU</a></li>
                <li><a href="cpucooler">CPU Cooler</a></li>
                <li><a href="mobo">MOBO</a></li>
                <li><a href="gpu">GPU</a></li>
                <li><a href="ram">RAM</a></li>
                <li><a href="ssd">SSD</a></li>
                <li><a href="hdd">HDD</a></li>
                <li><a href="psu">PSU</a></li>
                <li><a href="case">Case</a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="container">
    <h1 class="col-sm-12">GPU list:</h1>
    <form action="${pageContext.request.contextPath}/gpu" method="post">
        <div class="form-group col-sm-11">
            <input type="text" class="form-control" name="gpuSearchText">
        </div>
        <div class="col-sm-1">
            <button type="submit" class="btn btn-default" name="gpuSearchButton" value="gpuSearchButton">Search</button>
        </div>
    </form>
    <table class=table table-striped>
        <tr>
            <th>Manufacturer</th>
            <th>Series</th>
            <th>Name</th>
            <th>MemorySize</th>
            <th>MemoryType</th>
            <th>Price</th>
        </tr>
        <c:forEach var="item" items="${requestScope.gpuList}">
            <tr>
                <td>${item.getManufacturer()}</td>
                <td>${item.getseries()}</td>
                <td>${item.getName()}</td>
                <td>${item.getmemorySize()} GB</td>
                <td>${item.getMemoryType()}</td>
                <td>${item.getPrice()} PLN</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>