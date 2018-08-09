package com.csomet.springboot.app.view.xml;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.csomet.springboot.app.model.DemoModelClass;

@XmlRootElement(name="root_demo")
public class DemoModelClassXmlWrapper {

	@XmlElement(name="item")
	private List<DemoModelClass> demoClasses;
	
	public DemoModelClassXmlWrapper() {}

	
	public DemoModelClassXmlWrapper(List<DemoModelClass> demoClasses) {
		this.demoClasses = demoClasses;
	}


	public List<DemoModelClass> getDemoClasses() {
		return demoClasses;
	}
	
	
	
	
}
