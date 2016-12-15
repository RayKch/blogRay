<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="un" uri="http://jakarta.apache.org/taglibs/unstandard-1.0" %>
<% pageContext.setAttribute("season", request.getAttribute("season")); %>
<% pageContext.setAttribute("imgIdx", request.getAttribute("imgIdx")); %>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" id="headDescription" content="">
	<meta name="author" content="김찬퐁">
	<meta name="robots" content="index, follow">
	<meta property="og:type" content="article">
	<meta property="og:title" id="ogTitle" content="">
	<meta property="og:description" id="ogDescription" content="">
	<meta property="og:image" content="${pageContext.request.contextPath}/image/profile/profile.jpg">
	<meta property="og:url" id="ogUrl" content="">
	<link href="${pageContext.request.contextPath}/styles/plugin/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
	<link href="${pageContext.request.contextPath}/styles/plugin/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/styles/plugin/clean-blog.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/styles/plugin/comment-form.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/styles/common/common.css" rel="stylesheet">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/image/profile/profile.jpg">
	<title id="headTitle"></title>
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>