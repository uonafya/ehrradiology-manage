<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<br/>
<openmrs:require privilege="Manage Radiology Worklist" otherwise="/login.htm" redirect="/module/radiology/worklist.form" />
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
			url : getContextPath() + "/module/radiology/searchWorklist.form",
			data : ({
				date			: date,
				phrase			: phrase,
				investigation	: investigation,
				currentPage		: currentPage
			}),
			success : function(data) {
				jQuery("#tests").html(data);	
				if(testNo>0){					
					tb_init("a.thickbox"); // init to show thickbox
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});
	}
	
	// reschedule a test
	function rescheduleTest(orderId, rescheduledDate) {
		validateRescheduleDate(orderId, rescheduledDate);
	}
	
	// validate reschedule date
	function validateRescheduleDate(orderId, rescheduledDate){			
		validateRescheduleDateResult = false;
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/radiology/ajax/validateRescheduleDate.htm",
			data : ({				
				rescheduleDate : rescheduledDate
			}),
			success : function(data) {
				
				if (data == 'success') {						
					jQuery.ajax({
						type : "POST",
						url : getContextPath() + "/module/radiology/rescheduleTest.form",
						data : ({
							orderId : orderId,
							rescheduledDate : rescheduledDate
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
					tb_remove();
				} else {
					alert('Invalid reorder date! It must be after the current date');
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				
			}
		});		
	}
	
	// complete a test
	function completeTest(testId) {		
		tests = jQuery("input[name=test_" + testId + "]");
		
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
					
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});
	}
	
	//  show form in popup to enter result for a test. This function is for all tests except xray tests.
	function enterResult(testId, type){
		type = escape(type);
		tb_show("testing", "enterResult.form?modal=true&height=600&width=800&testId=" + testId + "&type=" + type + "&script=completeTest('" + testId + "');");
	}
	
	// show form for xray test
	function enterXrayResult(testId){
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/radiology/getDefaultForm.form",
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