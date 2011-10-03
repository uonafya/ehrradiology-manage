<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<br/>
<openmrs:require privilege="Edit Radiology Result" otherwise="/login.htm" redirect="/module/radiology/editResult.form" />
<%@ include file="../localHeader.jsp" %>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery('#date').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});
    });
	
	// get all tests
	function getTests(currentPage){
		var date = jQuery("#date").val();
		var phrase = jQuery("#phrase").val();
		var investigation = jQuery("#investigation").val();
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/radiology/searchCompletedTests.form",
			data : ({
				date			: date,
				phrase			: phrase,
				investigation	: investigation,
				currentPage		: currentPage
			}),
			success : function(data) {
				jQuery("#tests").html(data);	
				if(testNo>0){ 
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});
	}
	
	// complete a test
	function completeTest(testId) {
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/radiology/ajax/completeTest.htm",
			data : ({
				testId : testId
			}),
			success : function(data) {
				if (data == 'success') {
					getTests();
				} else {
					alert(data);
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});
	}
	
	//  reenter result for a test
	function reEnterResult(testId){
		tb_show("testing", "showForm.form?modal=true&height=600&width=800&mode=edit&radiologyTestId=" + testId);	
	}
	
	// show form for xray test
	function reEnterXRayResult(testId){
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/radiology/getDefaultXRayForm.form",
			data : ({
				testId : testId
			}),
			success : function(data) {
				jQuery("#row" + testId).show();
				jQuery("#contentForm" + testId).html(data);
				addValidations("#contentForm" + testId);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});
	}
	
	// Add required class
	function addValidations(formName){
		jQuery(formName + " input").each(function(index){
			input = jQuery(this);
			input.addClass("required");
			input.attr("title", "Required!");
		});
		
		jQuery(formName + " select").each(function(index){
			select = jQuery(this);			
			select.addClass("required");
			select.attr("title", "Required!");
		});
		
		jQuery(formName + " textarea").each(function(index){
			textarea = jQuery(this);			
			textarea.addClass("required");
			textarea.attr("title", "Required!");
		});
	}
	
	// submit xray form
	function submitXrayForm(testId){		
		validated = jQuery("#contentForm" + testId).valid();	
		if(validated){			
			var formContent = jQuery("#contentForm" + testId).formSerialize();
			jQuery.post(getContextPath() + "/module/radiology/showForm.form", formContent, function(data) {
				completeTest(testId);
			});
		} else {
			alert("Please complete the form");
		}
	}
</script> 

<div class="boxHeader"> 
	<strong>See patient List by choosing lab</strong>
</div>
<div class="box">
	Date:
	<input id="date" value="${currentDate}" style="text-align:right;"/>
	Patient ID/Name:
	<input id="phrase"/>
	Investigation:
	<select name="investigation" id="investigation">
		<option value="0">Select an investigation</option>
		<c:forEach var="investigation" items="${investigations}">
			<option value="${investigation.id}">${investigation.name.name}</option>
		</c:forEach>	
	</select>
	<br/>
	<input type="button" value="Get worklist" onClick="getTests();"/>
</div>

<div id="tests">
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>  