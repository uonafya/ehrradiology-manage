<div style="border-bottom: 1px solid black; padding-bottom:5px; margin-bottom:10px;">
    <ul id="menu">
        <li class="first">
            <span style="font-weight:bold; font-size:large;">Radiology system</span>
        </li>
        <openmrs:hasPrivilege privilege="Manage Radiology Queue">
            <li id="QueueId">
				<a href="queue.form">Queue</a>
            </li>
        </openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Manage Radiology Worklist">
            <li id="WorkListId">
				<a href="worklist.form">Work List</a>
            </li>
        </openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Edit Radiology Result">
            <li id="EditResultId">
				<a href="editResult.form">Edit Result</a>
            </li>
        </openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Print Radiology Worklist">
            <li id="PrintWorkList">
				<a href="printWorklist.form">Print Work List</a>
            </li>
        </openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Manage Radiology Patient Report">
            <li id="PatientReportId">
				<a href="patientReport.form">Patient Report</a>
            </li>
        </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Radiology Functional Status">
            <li id="FunctionalStatusId">
				<a href="functionalStatus.form">Functional Status</a>
            </li>
        </openmrs:hasPrivilege>
    </ul>
</div>

<script type="text/javascript">
	// activate the <li> with @id by adding the css class named "active"
	function activate(id){
		$("#" + id).addClass("active");		
	}
	
	// return true whether the @str contains @searchText, otherwise return false
	function contain(str, searchText){
		return str.indexOf(searchText)>-1;
	}
	
	// choose which <li> will be activated using @url
	var url = location.href;
	if(contain(url, "queue.form")){
		activate("QueueId");
	} else if(contain(url, "worklist.form")){
		activate("WorkListId");
	} else if(contain(url, "editResult.form")){
		activate("EditResultId");
	} else if(contain(url, "printWorklist.form")){
		activate("PrintWorkList");
	} else if(contain(url, "patientReport.form")){
		activate("PatientReportId");
	} else if(contain(url, "functionalStatus.form")){
		activate("FunctionalStatusId");
	} 
	
	// get the context path
	function getContextPath() {
		pn = location.pathname;
		len = pn.indexOf("/", 1);
		cp = pn.substring(0, len);
		return cp;
	}
</script>