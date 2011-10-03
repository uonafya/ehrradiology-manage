package org.openmrs.module.radiology.web.controller.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.concept.TestTree;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.util.RadiologyConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("RadiologyMainController")
@RequestMapping("/module/radiology/main.form")
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public String firstView(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		Map<Concept, Set<Concept>> testTreeMap = generateTestTreeMap();
		System.out.println("testTreeMap ===> " + testTreeMap);
		session.setAttribute(RadiologyConstants.SESSION_TEST_TREE_MAP, testTreeMap);	

		return "/module/radiology/main/main";
	}

	/*
	 * Generate test tree map. This map will be stored in session, so other features can reuse it and don't need to generate it again.
	 */
	private Map<Concept, Set<Concept>> generateTestTreeMap() {
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		RadiologyDepartment department = rs.getCurrentRadiologyDepartment();
		Map<Concept, Set<Concept>> investigationTests = new HashMap<Concept, Set<Concept>>();
		if (department != null) {
			Set<Concept> investigations = department.getInvestigations();
			for (Concept investigation : investigations) {
				TestTree tree = new TestTree(investigation);
				if (tree.getRootNode() != null) {
					investigationTests.put(tree.getRootNode().getConcept(),
							tree.getConceptSet());
				}
			}			
		}
		return investigationTests;
	}
}