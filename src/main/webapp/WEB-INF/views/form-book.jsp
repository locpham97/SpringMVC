<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Book Management</title>
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
<div class="container text-center">
    <h1>
        <c:if test="${book==null}">Add</c:if>
        <c:if test="${book!=null}">Update</c:if>
        &nbsp;Book
    </h1>
    <c:if test="${book==null}">
    <form action="/books/add" method="post">
        </c:if>
        <c:if test="${book!=null}">
        <form action="/books/edit/${book.id}" method="post">
            </c:if>

            <table class="table table-bordered">
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="${book.name}"/></td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td>
                        <select class="custom-select" name="authors" id="authors" size="4" multiple required>
                            <c:forEach items="${listOfAuthors}" var="author">
                                <c:choose>
                                    <c:when test="${book.getAuthors().contains(author) == true}">
                                        <option value="${author.id}" selected>${author.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${author.id}">${author.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>

                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="category" id="category" required>
                            <c:forEach items="${listOfCategories}" var="category">
                                <option value="${category.id}"
                                        selected="${book.category.id.equals(category.id)}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </td>

                </tr>
            </table>
            <input type="submit" value="Save">
        </form>
</div>

</body>
</html>