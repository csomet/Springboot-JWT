package com.csomet.springboot.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csomet.springboot.app.model.DemoModelClass;

@Service
public class DemoServiceClassImpl implements IDemoServices{

	@Override
	public List<DemoModelClass> getDemoData() {
	
		List<DemoModelClass> demoClasses = new ArrayList<>();
		
		//add demo dummy data
		DemoModelClass demo = new DemoModelClass();
		
		demo.setAttribute1("Hello world!");
		demo.setAttribute2(2);
		demo.setId(1L);
		demo.setAttribute3("This is another data");
		
		demoClasses.add(demo);
		
		demo = new DemoModelClass();
		
		demo.setAttribute1("Hello to you!");
		demo.setAttribute2(31);
		demo.setId(2L);
		demo.setAttribute3("This is another example");
		
		demoClasses.add(demo);
		
		demo = new DemoModelClass();
		
		demo.setAttribute1("Hello, how are you doing?");
		demo.setAttribute2(322);
		demo.setId(3L);
		demo.setAttribute3("This is the final example");
		
		demoClasses.add(demo);
		
		
		return demoClasses;
	}

	@Override
	public void saveData(DemoModelClass demoClass) {
		
		
	}

}
