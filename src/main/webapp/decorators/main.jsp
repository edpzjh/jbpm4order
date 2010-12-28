<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<html>
	<head>
		<link href="<s:url value='/public/stylesheets/globel.css'/>" rel="stylesheet" type="text/css"/>
		<script src="<s:url value='/public/jquery/js/jquery-1.4.2.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/javascripts/public.js'/>" type="text/javascript"></script>
		<title><decorator:title default="sitemesh" /></title>
		<decorator:head />
<style type="text/css">
	input[type='text']:focus, input[type='password']:focus, textarea:focus, select:focus { border: 1px solid #f00; background: #fcc; }
	.ie_hover { background: #ffc; }
	.ie_focus { border: 1px solid #f00; background: #fcc; } 
</style>
<script type="text/javascript">
// <![CDATA[
$(document).ready(function() {
	$("#loading").ajaxStart(function(evt, request, settings){
    	$(this).show('normal');
    });
    $("#loading").ajaxStop(function(){
   		$(this).hide('normal');
 	});
    if ($.browser.msie){
     	$("input[type='text'], input[type='password'], textarea, select")
     		.focus(function(){$(this).addClass("ie_focus");})
     		.blur(function(){$(this).removeClass("ie_focus");});
    }
});
// ]]></script>
	</head>
	<body>
		<center><div id="loading"><div>Loading...</div></div></center>
		<script type="text/javascript">
			/*$("#loading div").animate({width:"60px"});*/
		</script>
		<center>Head <span><a href="?request_locale=en_US">English</a>|<a href="?request_locale=zh_CN">Chinese</a></span>
		<s:url id="url" namespace="/authenticate" action="logout" /><a href="<s:property value="#url"/>"><s:text name="action.logout"/></a>
		</center>
		<hr/>
		<div>Menu</div>
		<div><decorator:body /></div>
		<hr/>
		<center>Foot</center>
		<script type="text/javascript">/*setTimeout(function(){$("#loading").hide(500)},300);*/</script>
		<script type="text/javascript">$("#loading").hide()</script>
	</body>
</html>