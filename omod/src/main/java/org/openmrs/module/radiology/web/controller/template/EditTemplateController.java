package org.openmrs.module.radiology.web.controller.template;

import java.util.Set;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;
import org.openmrs.module.radiology.web.controller.propertyeditor.RadiologyTemplatePropertyEditor;
import org.openmrs.module.radiology.web.util.RadiologyUtil;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyEditTemplateController")
@RequestMapping("/module/radiology/editTemplate.form")
public class EditTemplateController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(RadiologyDepartment.class,
				new RadiologyTemplatePropertyEditor());
		binder.registerCustomEditor(Set.class, "tests",
				new CustomCollectionEditor(Set.class) {

					protected Object convertElement(Object element) {
						String conceptName = null;
						if (element instanceof String)
							conceptName = (String) element;
						return conceptName != null ? RadiologyUtil.searchConcept(conceptName) : null;
					}
				});
	}

	@ModelAttribute("template")
	public RadiologyTemplate getTemplate(
			@RequestParam(value = "id", required = false) Integer id) {
		RadiologyTemplate template = new RadiologyTemplate();
		if (id != null) {
			RadiologyService rs = (RadiologyService) Context
					.getService(RadiologyService.class);
			template = rs.getRadiologyTemplate(id);
		}
		return template;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showDepartment(
			@RequestParam(value = "id", required = false) Integer id,
			Model model) {
		return "/module/radiology/template/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(
			@ModelAttribute("template") RadiologyTemplate template,
			Model model) {
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		rs.saveRadiologyTemplate(template);
		return "redirect:/module/radiology/listTemplate.form";
	}
}
