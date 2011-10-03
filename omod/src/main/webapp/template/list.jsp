<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<br/>
<openmrs:require privilege="Manage Radiology" otherwise="/login.htm" redirect="/module/radiology/listTemplate.form" />
<%@ include file="../localHeader.jsp" %>
<script type="text/javascript">
	jQuery(document).ready(function() 
		{ 
			jQuery("#myTable").tablesorter({sortList: [[0,0]]}); 
		} 
	); 
</script>
<a href="editTemplate.form">Add new template</a>
<table id="myTable" class="tablesorter">
	<thead>
		<tr> 
			<th>No</th>
			<th>Name</th>
			<th>Description</th>			
			<th width="100px;"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="template" items="${templates}" varStatus="index">
			<c:choose>
				<c:when test="${index.count mod 2 == 0}">
					<c:set var="klass" value="odd"/>
				</c:when>					
				<c:otherwise>
					<c:set var="klass" value="even"/>
				</c:otherwise>
			</c:choose>
			<tr class="${klass}">
				<td>${index.count}</td>
				<td>
					<a href="editTemplate.form?id=${template.id}">${template.name}</a>
				</td>
				<td>
					${template.description}
				</td>
				<td>
					<center>
						<a href="deleteTemplate.form?id=${template.id}">
							Delete
						</a>
					</center>
				</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp" %>  