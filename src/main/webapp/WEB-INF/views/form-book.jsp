<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Book Management</title>
</head>
<body>
<div align="center">
    <h1>
        <c:if test="${edit == false}">Add</c:if>
        <c:if test="${edit == true}">Update</c:if>
        &nbsp; Book
    </h1>
    <c:if test="${edit == false}">
        <form action="/book/add" method="post">
        </c:if>
    <c:if test="${edit == true}">
        <form action="/book/edit?id=<c:out value='${book.id}' />" method="post">
    </c:if>
        <div class="form-group">
            <label for="name">Name:</label>
            <input size="50" type="text" name="name" id="name"
                   value="${book.name}"/>
        </div>
        <div class="form-group">
            <label>Author:</label>
            <form:select path="book.authors" id="id" items="${authors}"
                         itemLabel="name" itemValue="id" multiple="true"/>

        </div>
        <div class="form-group">
            <label>Category:</label>
            <form:select path="book.category.id" id="id" items="${categories}" itemLabel="name" itemValue="id" />
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        </form>
</div>

</body>
</html>