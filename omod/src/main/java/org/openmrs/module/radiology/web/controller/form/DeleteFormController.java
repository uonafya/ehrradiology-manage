package org.openmrs.module.radiology.web.controller.form;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyDeleteFormController")
@RequestMapping("/module/radiology/deleteForm.form")
public class DeleteFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String deleteForm(@RequestParam("id") Integer id,
			Model model) {
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		RadiologyForm form = rs.getRadiologyFormById(id);
		if(form!=null){
			rs.deleteRadiologyForm(form);
		}
		return "redirect:/module/radiology/listForm.form";
	}

}
