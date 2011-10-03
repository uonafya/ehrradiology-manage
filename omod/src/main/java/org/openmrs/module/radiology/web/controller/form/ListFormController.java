package org.openmrs.module.radiology.web.controller.form;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("RadiologyListFormController")
@RequestMapping("/module/radiology/listForm.form")
public class ListFormController {
	
	@ModelAttribute("forms")
	public List<RadiologyForm> getAllForms(){
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		return rs.getAllRadiologyForms();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listForms(
			Model model) {
		return "/module/radiology/form/list";
	}

}
