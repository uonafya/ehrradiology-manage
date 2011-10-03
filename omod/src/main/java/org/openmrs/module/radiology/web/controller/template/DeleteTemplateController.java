package org.openmrs.module.radiology.web.controller.template;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyDeleteTemplateController")
@RequestMapping("/module/radiology/deleteTemplate.form")
public class DeleteTemplateController {

	@RequestMapping(method = RequestMethod.GET)
	public String deleteForm(@RequestParam("id") Integer id,
			Model model) {
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		RadiologyTemplate template = rs.getRadiologyTemplate(id);
		if(template!=null){
			rs.deleteRadiologyTemplate(template);
		}
		return "redirect:/module/radiology/listTemplate.form";
	}

}
