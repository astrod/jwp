package next.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class SaveController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String writer = (String) request.getParameter("writer");
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		Question q = new Question(writer, title, contents);
		questionDao.insert(q);
		
		ModelAndView mav = jstlView("list.jsp");
		mav.addObject("question", q);
		
		
		return mav;
	}

}
