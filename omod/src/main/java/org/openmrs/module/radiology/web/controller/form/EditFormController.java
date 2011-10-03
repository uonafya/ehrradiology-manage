package org.openmrs.module.radiology.web.controller.form;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyEditFormController")
@RequestMapping("/module/radiology/editForm.form")
public class EditFormController {

	@ModelAttribute("form")
	public RadiologyForm getForm(
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			RadiologyService rs = (RadiologyService) Context
					.getService(RadiologyService.class);
			RadiologyForm form = rs.getRadiologyFormById(id);
			if (form != null)
				return form;
		}
		return new RadiologyForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return "/module/radiology/form/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("form") RadiologyForm form,
			Model model) {
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		rs.saveRadiologyForm(form);
		return "redirect:/module/radiology/listForm.form";
	}
}
