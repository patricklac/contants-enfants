<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Liste contacts</title>
  </head>
  <body>
    <h2>Liste contacts</h2>
    <c:if test="${empty number}">
    <ul>
      <c:forEach items="${contacts}" var="contact" >
        <li>${contact.nom} : ${contact.telephone}</li>
      </c:forEach>
    </ul>
    </c:if>
    <c:if test="${not empty number}">
    <ul>
      <c:forEach items="${contacts}" var="contact" end="${number-1}" >
        <li>${contact.nom} : ${contact.telephone}</li>
      </c:forEach>
        <li><em>${contacts[number].nom} : ${contacts[number].telephone}</em></li>
      <c:forEach items="${contacts}" var="contact" begin="${number+1}" >
        <li>${contact.nom} : ${contact.telephone}</li>
      </c:forEach>
    </ul>
    </c:if>
  </body>
</html>
