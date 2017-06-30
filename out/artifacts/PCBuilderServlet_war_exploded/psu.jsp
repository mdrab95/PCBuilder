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
    <h1 class="col-sm-12">PSU list:</h1>
    <form action="${pageContext.request.contextPath}/psu" method="post">
        <div class="form-group col-sm-11">
            <input type="text" class="form-control" name="psuSearchText">
        </div>
        <div class="col-sm-1">
            <button type="submit" class="btn btn-default" name="psuSearchButton" value="psuSearchButton">Search</button>
        </div>
    </form>
    <table class=table table-striped>
        <tr>
            <th>Brand</th>
            <th>Name</th>
            <th>Wattage</th>
            <th>Certificate 80+</th>
            <th>Specification</th>
            <th>Price</th>
        </tr>
        <c:forEach var="item" items="${psuList}">
            <tr>
                <td>${item.getBrand()}</td>
                <td>${item.getName()}</td>
                <td>${item.getWattage()} W</td>
                <td>${item.getCertificate80Plus()} </td>
                <td>${item.getSpecification()}</td>
                <td>${item.getPrice()} PLN</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>