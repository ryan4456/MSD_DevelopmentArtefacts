<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=request.getAttribute("title") %></title>
    <style>
    .content{
        max-width: 100%!important;
        margin-top: 15px;
    }
    .content * {
        max-width: 100%!important;
    }
    .date{
        color: #949494;
        font-size: 13px;
    }
    </style>
  </head>
  <body>
  <%-- <h4><%=request.getAttribute("title") %></h4>
  <span class="date"><%=request.getAttribute("addTime") %></span>
  <br /> --%>
  <div class="content">
  <%=request.getAttribute("content") %>
  </div>
  </body>
</html>