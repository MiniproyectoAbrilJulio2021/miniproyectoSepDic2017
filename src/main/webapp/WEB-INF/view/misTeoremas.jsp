<%-- 
    Document   : misTeoremas
    Created on : Apr 1, 2017, 11:23:16 PM
    Author     : marylu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script type="text/x-mathjax-config">
    MathJax.Hub.Config({
      tex2jax: {
        inlineMath: [ ['$','$'], ["\\(","\\)"] ],
        processEscapes: true
      }
    });
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mathjax-MathJax-v2.3-248-g60e0a8c/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ClickOnAlias.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/buscarSoluciones.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-responsive.css" >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>David | Mis Teoremas</title>
    <tiles:insertDefinition name="style" />
  </head>
  <body>
    
    <tiles:insertDefinition name="header" />
    <h1>Mis Teoremas</h1>
    <div id="teoremas" style="float: left; width: 35%;">
    
    <ul>
      <c:forEach items="${categorias}" var="cat"> 
        <li style="list-style: none; color: #03A9F4"><h3>${cat.getNombre()}</h3>
          <ul>
            <c:forEach items="${teoremas}" var="teo">
              <c:choose>
                <c:when test="${teo.getCategoria().getId()==cat.getId()}">      
                  <c:choose>
                    <c:when test="${resuelveManager.getResuelveByUserAndTeorema(usuario.login,teo.getId()).isResuelto() == false}">
                      <li style="list-style: none;">
                        <p style="color: #000;">
                          <a onclick="expandMeta(${teo.getId().toString()})">
                             <i class="fa fa-plus-circle" aria-hidden="true"  style="margin-left: 10px; margin-right: 10px;"></i>
                          </a>
                             <i class="fa fa-lock" aria-hidden="true" style="margin-right: 10px;"></i>
                          $${teo.getTeoTerm().toStringInfFinal()}$
                          <span style="display: none;" id="${teo.getId()}">
                            <br><span  style="margin-left: 10px; margin-right: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;</span><i class="fa fa-lock" aria-hidden="true" style="margin-right: 5px;"></i>
                            $${teo.getMetateoTerm().toStringInfFinal()}$
                          </span>                       
                        </p> 
                      </li>
                      
                    </c:when>
                    <c:otherwise>
                      <li style="list-style: none;">
                        <p style="color: #000;">
                          <a onclick="expandMeta(${teo.getId().toString()})">
                              <i class="fa fa-plus-circle" aria-hidden="true"  style="margin-left: 10px; margin-right: 10px;"></i>
                          </a>
                              <i class="fa fa-unlock" aria-hidden="true" style="margin-right: 10px;"></i>
                          <a href="javascript:buscarSoluciones(${teo.getId()});"style="cursor:pointer;" title="Haga click para ver las soluciones del teorema">$${teo.getTeoTerm().toStringInfFinal()}$ </a>
                          <span style="display: none;" id="">

                              <br><span  style="margin-left: 10px; margin-right: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;</span><i class="fa fa-unlock" aria-hidden="true" style="margin-right: 5px;"></i>
                              $${teo.getMetateoTerm().toStringInfFinal()}$
                          </span>
                        </p>
                      </li>
                    </c:otherwise>
                  </c:choose>
                </c:when>
              </c:choose>
            </c:forEach>
          </ul>
        </li>
      </c:forEach> 
    </ul>
    
    <script>
      function expandMeta(id) {
        elem = document.getElementById(id);
        if (elem.style.display == "inline")
          elem.style.display = "none";
        else
          elem.style.display = "inline";
        
      }
    </script>
    </div>
    <div id="panelSoluciones" style="float:left; width: 48%; margin-left: 5%; margin-right: 2%; display:none">
        
        <div id="listaSoluciones" style="float:left; width: 20%;">
            <h3> Soluciones</h3>
            <ul id="lista">
            <li style="list-style: none;">
                uno
            </li>
            <li style="list-style: none;">
                dos
            </li>
            <li style="list-style: none;">
                tres
            </li>
            </ul>
        </div>
        <div style="float:right; width: 80%; height: 60%; overflow: scroll;">
            <h5 id="formula"></h5>
        </div>
    </div>
    <tiles:insertDefinition name="footer" />
  </body>
</html>
