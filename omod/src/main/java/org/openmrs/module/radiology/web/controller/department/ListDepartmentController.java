package org.openmrs.module.radiology.web.controller.department;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.RadiologyCoreService;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("RadiologyListDepartmentController")
@RequestMapping("/module/radiology/listDepartment.form")
public class ListDepartmentController {
	
	@ModelAttribute("departments")
	public List<RadiologyDepartment> getDepartments(){
		RadiologyCoreService rs = (RadiologyCoreService) Context.getService(RadiologyCoreService.class);
		return rs.getAllRadiologyDepartments();
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public String listForms(
			Model model) {
		return "/module/radiology/department/list";
	}
}
