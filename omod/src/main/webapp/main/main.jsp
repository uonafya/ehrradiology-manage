<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<br/>
<openmrs:require privilege="Manage Radiology" otherwise="/login.htm" redirect="/module/radiology/main.form" />
<%@ include file="../localHeader.jsp" %>
<%@ include file="/WEB-INF/template/footer.jsp" %>  
