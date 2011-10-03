package org.openmrs.module.radiology.web.controller.department;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.radiology.RadiologyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyDeleteDepartmentController")
@RequestMapping("/module/radiology/deleteDepartment.form")
public class DeleteDepartmentController {

	@RequestMapping(method = RequestMethod.GET)
	public String deleteForm(@RequestParam("id") Integer id,
			Model model) {
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		RadiologyDepartment department = rs.getRadiologyDepartmentById(id);
		if(department!=null){
			rs.deleteRadiologyDepartment(department);
		}
		return "redirect:/module/radiology/listDepartment.form";
	}

}
