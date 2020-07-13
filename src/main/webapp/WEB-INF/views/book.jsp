<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
</head>
<body>
<div align="center" class="container">
    <h1 class="display-1">Book Management</h1>
    <h2>
        <a href="/book/showAdd">Add new book</a>
    </h2>
    <h2>
        <a href="/category/showAdd/">Add new category</a>
    </h2>
    <h2>
        <a href="/author/showAdd">Add new author</a>
    </h2>
    <table cellpadding="4" border="1" class="table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Author</th>
                <th scope="col">Category</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listBook}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>
                        <c:forEach items="${book.getAuthors()}" var="author">
                            ${author.name} &nbsp;
                        </c:forEach>
                    </td>
                    <td>${book.getCategory().name}</td>
                    <td>
                        <a href="/book/showEdit/${book.id}">Edit</a>
                        &nbsp;
                        <a href="/book/remove/${book.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>