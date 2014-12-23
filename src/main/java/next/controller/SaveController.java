package next.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;

public class SaveController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;
	
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String writer = (String) request.getParameter("writer");
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		Question q = new Question(writer, title, contents);
		questionDao.insert(q);
		
		ModelAndView mav = jstlView("list.jsp");
		
		questions = questionDao.findAll();
		mav.addObject("questions", questions);
		
		return mav;
	}

}
