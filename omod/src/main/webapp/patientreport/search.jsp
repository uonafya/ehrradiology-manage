<%@ include file="/WEB-INF/template/include.jsp" %>
<hr/>
<div id="patientReportTestInfo" style='display:none;'></div>

<c:forEach var="test" items="${tests}">
	<c:if test="${test.level eq 'LEVEL_INVESTIGATION'}">
		<div style='margin-left:30px;'>
			<b>${test.investigation}</b>
		</div>
	</c:if>
	<c:if test="${test.level eq 'LEVEL_SET'}">
		<div style='margin-left:60px;'>			
			<a href='showPatientReport.form?testId=${test.radiologyTestId}' target='_blank'>
				${test.set}
			</a> 
		</div>	
	</c:if>		
</c:forEach>