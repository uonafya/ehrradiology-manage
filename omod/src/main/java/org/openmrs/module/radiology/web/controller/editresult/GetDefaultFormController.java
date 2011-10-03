package org.openmrs.module.radiology.web.controller.editresult;

import org.openmrs.Encounter;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyForm;
import org.openmrs.module.radiology.model.RadiologyTest;
import org.openmrs.module.radiology.web.util.RadiologyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyGetDefaultXRayFormController")
@RequestMapping("/module/radiology/getDefaultXRayForm.form")
public class GetDefaultFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String getDefaultXRayForm(@RequestParam(value = "testId") Integer testId,
			Model model) {
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		RadiologyTest test = rs.getRadiologyTestById(testId);
		Encounter encounter = test.getEncounter();
		RadiologyForm form = test.getForm();
		model.addAttribute("form", form);
		if (encounter != null)
			model.addAttribute("encounterId", encounter.getEncounterId());
		RadiologyUtil.generateDataFromEncounter(model, encounter, form);
		model.addAttribute("testId", testId);
		return "/module/radiology/editresult/getDefaultXRayForm";
	}
}