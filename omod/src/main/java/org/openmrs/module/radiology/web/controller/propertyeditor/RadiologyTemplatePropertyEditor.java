package org.openmrs.module.radiology.web.controller.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;

public class RadiologyTemplatePropertyEditor extends PropertyEditorSupport {
	private Log log = LogFactory.getLog(this.getClass());

	public RadiologyTemplatePropertyEditor() {
	}

	public void setAsText(String text) throws IllegalArgumentException {
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		if (text != null && text.trim().length() > 0) {
			try {
				setValue(rs.getRadiologyTemplate(NumberUtils.toInt(text)));
			} catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Radiology template not found: "
						+ ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}

	public String getAsText() {
		RadiologyTemplate template = (RadiologyTemplate) getValue();
		if (template == null) {
			return null;
		} else {
			return template.getId() + "";
		}
	}
}
