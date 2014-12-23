package core.mvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ModelAndView {
	private View view;
	private Map<String, Object> model = new HashMap<String, Object>();
	
	public ModelAndView() {
	}

	public ModelAndView(View view) {
		this.view = view;
	}
	
	//hashmap에 값을 집어 넣고 그에 접근할 수 있는 객체를 리턴한다.
	public ModelAndView addObject(String attributeName, Object attributeValue) {
		model.put(attributeName, attributeValue);
		return this;
	}
	
	public Map<String, Object> getModel() {
		return Collections.unmodifiableMap(model);
	}
	
	
	public View getView() {
		return view;
	}
}
