package com.csomet.springboot.app.view.json;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.csomet.springboot.app.model.DemoModelClass;

@Component("list.json")
@SuppressWarnings("unchecked")
public class DemoModelClassJsonView extends MappingJackson2JsonView{

	@Override
	protected Object filterModel(Map<String, Object> model) {
		
		model.remove("title");
		
		List<DemoModelClass> demoClasess =  (List<DemoModelClass>) model.get("demoData");
		model.remove("demoData");
		model.put("DemoModelClassList", demoClasess);
		
		return super.filterModel(model);
	}
}
