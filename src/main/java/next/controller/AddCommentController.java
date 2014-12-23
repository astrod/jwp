package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class AddCommentController extends AbstractController {
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		Answer answer = new Answer(writer, contents, questionId);
		
		answerDao.insert(answer);
		questionDao.changeCountOfComments(questionId);	
		
		
		ModelAndView mav = jstlView("redirect:/show.next?questionId=" + questionId);

		return mav;
	}

}
