package com.ritam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ritam.model.SemesterDetails;
import com.ritam.service.ISemesterService;

@Controller
public class SemesterController {
	
	@Autowired
	private ISemesterService service;
	
	@GetMapping("/")
	public String loadIndexPage(Model model) {
		model.addAttribute("semdata", new SemesterDetails());
		return "index";
	}

	@PostMapping("/save")
	public String saveDetails(SemesterDetails details) {
		service.saveDetails(details);
		return "save";
	}
	
	@GetMapping("/detailed-report")
	public String getDetailsReport(Model model) {
		model.addAttribute("semesters", service.fetchAllDetails());
		return "details";
	}
	
	@GetMapping("/show-result")
	public String showResult(Model model) {
		Float cgpa = service.calculateCGPA(); 
	    model.addAttribute("cgpa", cgpa);
		return "show";
	}
	
	
	@GetMapping("/update/{semNo}")
	public String showUpdateForm(@PathVariable Integer semNo, Model model) {
	    SemesterDetails details = service.getSemesterById(semNo);
	    model.addAttribute("semdata", details);
	    return "update-form";
	}


	@PostMapping("/update/{semNo}")
	public String updateSemester(@PathVariable Integer semNo, @ModelAttribute SemesterDetails details) {
	    service.updateSemester(semNo, details);
	    return "redirect:/detailed-report";
	}



	
}
