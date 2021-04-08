/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Radiology module.
 *
 *  Radiology module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Radiology module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Radiology module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.radiology;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class RadiologyActivator implements ModuleActivator {
	private Log log = LogFactory.getLog(this.getClass());
	
	public void contextRefreshed() {
		log.info("Radiology core module context refreshed");
	}

	public void started() {						
		//Maintainer.maintain();
		log.info("Radiology module started");
	}

	public void stopped() {
		log.info("Radiology module stopped");
		
	}

	public void willRefreshContext() {
		log.info("Radiology module will refresh context");
		
	}

	public void willStart() {
		log.info("Radiology module will start");
		
	}

	public void willStop() {
		log.info("Radiology module will stop");
	}
}
