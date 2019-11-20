<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--
Template Name: Colossus
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
<html>
<head>
<title>EZCOL</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="resources/css/layout.css" rel="stylesheet" type="text/css" media="all">

<!-- 개인 CSS -->
<style type="text/css">
    
</style>
</head>
<body id="top">
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->


<c:import url="header.jsp"></c:import>

<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper">
  <div id="slider" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="flexslider basicslider">
      <ul class="slides">
        <li><img src="resources/images/main3.jpg">
          <div class="txtoverlay">
            <div class="centralise">
              <div class="verticalwrap">
                <article>
                  <h2 class="heading uppercase" style="font-size: 60pt; font-weight:bold; text-shadow: 2px 2px 3px black;">
                   Lessons are brought to life through cases</h2>
                </article>
              </div>
            </div>
          </div>
        </li>
        <li><img src="resources/images/main1.jpg" alt="">
          <div class="txtoverlay">
            <div class="centralise">
              <div class="verticalwrap">
                <article>
                  <h2 class="heading uppercase" style="font-size: 60pt; font-weight:bold;  text-shadow: 2px 2px 3px black;">
                  			Engaging with your peers. </h2>
                </article>
              </div>
            </div>
          </div>
        </li>
        <li><img src="resources/images/main2.jpg" alt="">
          <div class="txtoverlay">
            <div class="centralise">
              <div class="verticalwrap">
                <article>
                  <h2 class="heading uppercase" style="font-size: 50pt; font-weight:bold; text-shadow: 2px 2px 3px black;">
                  HBS Online courses are nothing like a typical sit-back-and-listen lecture</h2>
                  
                </article>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <div id="services" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="group">
      <div class="one_third first">
        <article class="service"><i class="icon red circle fa fa-bell-o"></i>
          <h2 class="heading" style="font-family: fantasy">${ article1.title }</h2>
          <p class="btmspace-10">${ article1.head }</p>
          <p><a href="detailArticle.do?articleno=${ article1.articleno }">Read More &raquo;</a></p>
        </article>
      </div>
      <div class="one_third">
        <article class="service"><i class="icon orange circle fa fa-bicycle"></i>
          <h2 class="heading" style="font-family: fantasy">${ article2.title }</h2>
          <p class="btmspace-10">${ article2.head }</p>
          <p><a href="detailArticle.do?articleno=${ article2.articleno }">Read More &raquo;</a></p>
        </article>
      </div>
      <div class="one_third">
        <article class="service"><i class="icon green circle fa fa-mortar-board"></i>
          <h2 class="heading" style="font-family: fantasy">${ article3.title }</h2>
          <p class="btmspace-10">${ article3.head }</p>
          <p><a href="detailArticle.do?articleno=${ article3.articleno }">Read More &raquo;</a></p>
        </article>
      </div>
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- <div class="wrapper row6">
  <section id="cta" class="clear"> 
    ################################################################################################
    <div class="three_quarter first">
      <h2 class="heading" style="font-family: Georgia">2학기 수강 신청 기간 입니다 2019..20~ 2019.4.29 </h2>
      <p>학생 여러분들은 신청기간에 유의하여 수강신청 하시기 바랍니다.</p>

    </div>
    <div class="one_quarter"><a class="btn" href="#">수강 신청 화면으로  <span class="fa fa-arrow-right"></span></a></div>
    ################################################################################################
  </section>
</div> -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <div class="latest"> 
    <!-- ################################################################################################ -->
    
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->

<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<c:import url="footer.jsp"></c:import>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS -->
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/jquery.backtotop.js"></script>
<script src="resources/js/jquery.mobilemenu.js"></script>
<script src="resources/js/jquery.flexslider-min.js"></script>
</body>
</html>