package com.csomet.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csomet.springboot.app.services.IDemoServices;
import com.csomet.springboot.app.view.xml.DemoModelClassXmlWrapper;

@RestController
@RequestMapping("/api")
public class DemoAppRestController {

	@Autowired
	IDemoServices demoService;
	
	@GetMapping("/list")
	public DemoModelClassXmlWrapper getDemoData() {
		
		return new DemoModelClassXmlWrapper(demoService.getDemoData());
	}
	
}
