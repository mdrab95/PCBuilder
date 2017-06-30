<%--
  Created by IntelliJ IDEA.
  User: Ace
  Date: 30.06.2017
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <c:if test="${empty lastVisit}">
      <div class="col-sm-12">
        <h4>Your last visit: never</h4>
      </div>
    </c:if>
    <c:if test="${not empty lastVisit}">
      <div class="col-sm-12">
        <h4>Your last visit: ${lastVisit}</h4>
      </div>
    </c:if>
  </div>
  </body>
</html>
