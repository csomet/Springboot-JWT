package com.csomet.springboot.app.services;

import java.util.List;

import com.csomet.springboot.app.model.DemoModelClass;

public interface IDemoServices {
	
	public List<DemoModelClass> getDemoData();
	public void saveData(DemoModelClass demoClass);

}
