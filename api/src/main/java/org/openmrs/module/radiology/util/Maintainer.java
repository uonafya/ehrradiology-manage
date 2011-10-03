package org.openmrs.module.radiology.util;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;

public class Maintainer {

	/**
	 * Maintain the module
	 */
	public static void maintain() {

		if (Context.getAuthenticatedUser() != null) {
			if (Context.getAuthenticatedUser().isSuperUser()) {

				String maintainCode = GlobalPropertyUtil.getString(
						RadiologyConstants.PROPERTY_MAINTAINCODE, "");
				// create necessary concepts for default xray form
				if (!maintainCode.contains("{1}")) {
					maintainCode += "{1}";
					MaintainUtil.createConceptsForXrayDefaultForm();
					GlobalPropertyUtil.setString(
							RadiologyConstants.PROPERTY_MAINTAINCODE,
							maintainCode);
				}

				// create default form for xray tests
				if (!maintainCode.contains("{2}")) {
					maintainCode += "{2}";
					MaintainUtil.createXRayDefaultForm();
					GlobalPropertyUtil.setString(
							RadiologyConstants.PROPERTY_MAINTAINCODE,
							maintainCode);
				}

				// create default templates
				if (!maintainCode.contains("{3}")) {
					maintainCode += "{3}";
					MaintainUtil.createTemplates();
					GlobalPropertyUtil.setString(
							RadiologyConstants.PROPERTY_MAINTAINCODE,
							maintainCode);
				}

				// Update select elements in radiology form for validation
				if (!maintainCode.contains("{4}")) {
					maintainCode += "{4}";
					MaintainUtil.updateFormSelects();
					GlobalPropertyUtil.setString(
							RadiologyConstants.PROPERTY_MAINTAINCODE,
							maintainCode);
				}
			}
		}

	}

}
