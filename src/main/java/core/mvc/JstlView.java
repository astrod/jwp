package core.mvc;

import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JstlView implements View {
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
	
	private String viewName;

	public JstlView(String viewName) {
		this.viewName = viewName;
	}

	//모델과 리퀘스트, 리스펀스를 받아서 리다이렉트를 해 준다.
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//만약 시작값이 redirect: 로 시작한다
		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}
		
		Set<String> keys = model.keySet();
		for (String key : keys) {
			request.setAttribute(key, model.get(key));
		}
		
		//그렇지 않다면 리퀘스트에 필요한 걸 매핑해주고 forward해 준다.
		RequestDispatcher rd = request.getRequestDispatcher(viewName);
		rd.forward(request, response);
	}
}
