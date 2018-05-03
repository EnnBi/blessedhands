<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">

<meta charset="utf-8">
<tiles:insertAttribute name="header" />




<body> 
<div class="all-wrapper menu-side with-side-panel">
		<div class="layout-w">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		
	</div>
	</div>	
</body>

		<tiles:insertAttribute name="footer" />


</html>