package org.openmrs.module.radiology.web.controller.worklist;

import java.util.Date;
import java.util.UUID;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingConstants;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyForm;
import org.openmrs.module.radiology.model.RadiologyTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyGetDefaultFormController")
@RequestMapping("/module/radiology/getDefaultForm.form")
public class GetDefaultFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String enterResult(@RequestParam(value = "testId") Integer testId, Model model) {
		
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		RadiologyTest test = rs.getRadiologyTestById(testId);
		String encounterTypeStr = GlobalPropertyUtil.getString(
				BillingConstants.GLOBAL_PROPRETY_RADIOLOGY_ENCOUNTER_TYPE,
				"RADIOLOGYENCOUNTER");
		EncounterType encounterType = Context.getEncounterService()
				.getEncounterType(encounterTypeStr);
		Encounter enc = new Encounter();
		enc.setCreator(Context.getAuthenticatedUser());
		enc.setDateCreated(new Date());
		Location loc = Context.getLocationService().getLocation(1);
		enc.setLocation(loc);
		enc.setPatient(test.getPatient());
		enc.setPatientId(test.getPatient().getId());
		enc.setEncounterType(encounterType);
		enc.setVoided(false);
		enc.setProvider(Context.getAuthenticatedUser().getPerson());
		enc.setUuid(UUID.randomUUID().toString());
		enc.setEncounterDatetime(new Date());
		enc = Context.getEncounterService().saveEncounter(enc);
		test.setEncounter(enc);
		rs.saveRadiologyTest(test);
		RadiologyForm form = rs.getDefaultForm();
		model.addAttribute("form", form);
		model.addAttribute("encounterId", enc.getEncounterId());
		return "/module/radiology/worklist/getDefaultForm";
	}
}