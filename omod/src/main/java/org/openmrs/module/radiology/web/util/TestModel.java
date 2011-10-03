package org.openmrs.module.radiology.web.util;

public class TestModel {

	private String startDate;
	private String patientIdentifier;
	private String patientName;
	private String gender;
	private Integer age;
	private String testName;
	private Integer orderId;
	private String status;
	private Integer testId;
	private Integer givenFormId;
	private Integer notGivenFormId;
	private String acceptedDate;
	private String investigation;
	private Integer givenEncounterId;
	private Integer notGivenEncounterId;
	private Boolean xray;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPatientIdentifier() {
		return patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getGivenFormId() {
		return givenFormId;
	}

	public void setGivenFormId(Integer formId) {
		this.givenFormId = formId;
	}

	public String getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getInvestigation() {
		return investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}

	public Integer getGivenEncounterId() {
		return givenEncounterId;
	}

	public void setGivenEncounterId(Integer encounterId) {
		this.givenEncounterId = encounterId;
	}

	public Integer getNotGivenFormId() {
		return notGivenFormId;
	}

	public void setNotGivenFormId(Integer notGivenFormId) {
		this.notGivenFormId = notGivenFormId;
	}

	public Integer getNotGivenEncounterId() {
		return notGivenEncounterId;
	}

	public void setNotGivenEncounterId(Integer notGivenEncounterId) {
		this.notGivenEncounterId = notGivenEncounterId;
	}

	public Boolean getXray() {
		return xray;
	}

	public void setXray(Boolean xray) {
		this.xray = xray;
	}
}
