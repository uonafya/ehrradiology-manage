package org.openmrs.module.radiology.web.controller.patientreport;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;
import org.openmrs.module.radiology.model.RadiologyTest;
import org.openmrs.module.radiology.util.RadiologyConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyShowPatientReportControllerController")
@RequestMapping("/module/radiology/showPatientReport.form")
public class ShowPatientReportController {

	@RequestMapping(method = RequestMethod.GET)
	public String showView(@RequestParam("testId") Integer testId, Model model) {
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		RadiologyTest test = rs.getRadiologyTestById(testId);
		String testName = test.getConcept().getName().getName();
		String patientIdentifier = test.getPatient().getPatientIdentifier().getIdentifier();
		RadiologyTemplate template = getTemplate(test.getConcept());
		
		model.addAttribute("testName", testName);
		model.addAttribute("patientIdentifier", patientIdentifier);
		model.addAttribute("orderId", test.getOrder().getOrderId());
		model.addAttribute("template", template);
		model.addAttribute("testId", testId);
		return "/module/radiology/patientreport/showPatientReport";
	}

	private RadiologyTemplate getTemplate(Concept concept) {
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		List<RadiologyTemplate> templates = rs.getRadiologyTemplates(concept);
		if (CollectionUtils.isEmpty(templates)) {
			Integer templateId = GlobalPropertyUtil.getInteger(RadiologyConstants.PROPERTY_TEMPLATE_DEFAULT, 3);
			return rs.getRadiologyTemplate(templateId);
		} else {
			return templates.get(0);
		}
	}
}
