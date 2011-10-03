package org.openmrs.module.radiology.web.controller.queue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Order;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.web.util.RadiologyUtil;
import org.openmrs.module.radiology.web.util.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologyRescheduleTestController")
@RequestMapping("/module/radiology/rescheduleTest.form")
public class RescheduleTestController {

	@ModelAttribute("order")
	public Order getOrder(@RequestParam("orderId") Integer orderId) {
		return Context.getOrderService().getOrder(orderId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showRescheduleForm(Model model, @RequestParam(value="type", required=false) String type,
			@ModelAttribute("order") Order order) {
		if (order != null) {
			TestModel tm = RadiologyUtil.generateModel(order, null);
			model.addAttribute("test", tm);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			model.addAttribute("currentDate", RadiologyUtil.formatDate(c.getTime()));
		}
		if(!StringUtils.isBlank(type)){
			if(type.equalsIgnoreCase("reorder")){
				return "/module/radiology/worklist/reOrder";
			}				
		}			
		return "/module/radiology/queue/rescheduleForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String rescheduleTest(Model model,
			@ModelAttribute("order") Order order, @RequestParam("rescheduledDate") String rescheduledDateStr) {
		if (order != null) {
			RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
			Date rescheduledDate;
			try {
				rescheduledDate = RadiologyUtil.parseDate(rescheduledDateStr);
				String status = rs.rescheduleTest(order, rescheduledDate);
				model.addAttribute("status", status);
			} catch (ParseException e) {
				e.printStackTrace();
				model.addAttribute("status", "Invalid date!");
			}
		}
		return "/module/radiology/queue/rescheduleResponse";
	}

}
