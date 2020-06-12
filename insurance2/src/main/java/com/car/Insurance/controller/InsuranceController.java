package com.car.Insurance.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.Insurance.Service.InsuranceService;
import com.car.Insurance.model.CarDetails;
import com.car.Insurance.model.InsuranceDetail;

@Controller
public class InsuranceController {
	InsuranceService service;

	@Autowired
	public InsuranceController(InsuranceService service) {
		super();
		this.service = service;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringtrimmereditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringtrimmereditor);
	}

	@GetMapping("/")
	public String showhome(Model model) {
		CarDetails carDetails = new CarDetails();
		carDetails.setName("ram");
		model.addAttribute(carDetails);
		return "detail";
	}
	
	@GetMapping("/process")
	public String processForm(@Valid @ModelAttribute("carDetails") CarDetails carDetails,
			@RequestParam("modelName") String modelName, @RequestParam("fuelType") String fuelType,
			@RequestParam("year") String year, Model model) throws ParseException {

		int no_month = service.getmonth(carDetails.getYear());
		int no_year = service.getyear(year);
		List<InsuranceDetail> insuranceDetails=service.getPlans(modelName, fuelType, no_month, no_year);
		model.addAttribute("insuranceDetails", insuranceDetails);
		return "display";
	}
}
