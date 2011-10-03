package org.openmrs.module.radiology.web.controller.template;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("RadiologyListTemplateController")
@RequestMapping("/module/radiology/listTemplate.form")
public class ListTemplateController {
		
	@ModelAttribute("templates")
	public List<RadiologyTemplate> getTemplates(){
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		return rs.getAllRadiologyTemplates();
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public String listForms(
			Model model) {
		return "/module/radiology/template/list";
	}
}
