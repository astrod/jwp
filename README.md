2014년 개발 경험 프로젝트
=========

1. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.

* 

131077 채종운
1. 서버가 실행된 상태에서 localhost:8080으로 접근하면 서버는 web.xml로 접근합니다. web.xml로 접근하면 welcome-file-list에 있는 jsp파일을 참조하여 index.jsp를 로드한다. index.jsp는 내부에서 /list.next로 redirect를 시켜준다. 어노테이션으로 인해서, *.next로 접근하는 모든 주소는 FrontController로 들어가게 된다.

2. FrontController는 init()과 service()를 override했다. init()에서는 servletContext에서 RequestMapping 객체를 가져온다. 그 후에 *.next로 요청이 들어오면 service()메서드가 실행된다.

3. service()에서는 요청을 한 url을 가져온다. 가져온 url로 findController를 시행한다.(이 때 시작부터 ? 앞까지의 값을 가져와서 findController의 인자로 넘긴다)

4. 그 후에 컨트롤러에서 execute를 시행하여 mav를 리턴받는다. RequestMapping은 내부구조가 hashmap이기 때문에, 인자로 주소를
받으면 해당하는 Controller객체를 리턴한다. /list.next로 요청이 왔기 때문에, 이 경우에는 ListController객체를 리턴하게 된다.

5. 그 후에 controller에서 execute를 시행하면, ListController의 execute가 시행된다. 그 내부에서는 Dao에 접근하여 필요한 데이터를 DB에서 받아오게 된다. 그리고, jstlView에서 list.jsp를 인자로 넘겨서 mav를 받아오고, mav에 DB에서 받아온 데이터를 question이라는 키로 매핑한 후에 mav를 리턴한다.

6. mav에서 view를 받아오고, render를 수행한다. render에서는 생성자에서 넘긴 주소값 앞에 redirect가 있으면 redirect를 수행하고, 그렇지 않다면 model에서 키를 받아오고 request에 매핑한 후에 forward를 시킨다.
이 경우에는 redirect를 시키므로 list.jsp로 redirect를 시키게 된다.

7. list.jsp가 서블릿으로 변환되어 화면에 뿌려지게 된다.

