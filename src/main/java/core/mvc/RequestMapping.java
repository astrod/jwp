package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.ListController;
import next.controller.SaveController;
import next.controller.ShowController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	//주소 세 개의 값과 객체의 쌍을 맵에 넣는다.
	public void initMapping() {
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		mappings.put("/save.next", new SaveController());
		
		logger.info("Initialized Mapping Completed!");
	}

	//url을 던지면 해당하는 객체를 리턴한다.
	public Controller findController(String url) {
		return mappings.get(url);
	}

	//map에 객체 값을 집어 넣는다.
	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
