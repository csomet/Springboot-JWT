package com.csomet.springboot.app.view.xml;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.csomet.springboot.app.model.DemoModelClass;

@Component("list")
@SuppressWarnings("unchecked")
public class DemoModelClassXMLView extends MarshallingView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//model.remove("title");
		List<DemoModelClass> demoClasess =  (List<DemoModelClass>) model.get("demoData");
		model.remove("demoData");
		model.put("DemoModelClassList", new DemoModelClassXmlWrapper(demoClasess));
		
		super.renderMergedOutputModel(model, request, response);
	}
	
	public DemoModelClassXMLView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

}
