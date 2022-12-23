package inflearn.springbootmvc1study.web.frontcontroller.v3;

import inflearn.springbootmvc1study.web.frontcontroller.ModelView;
import inflearn.springbootmvc1study.web.frontcontroller.MyView;
import inflearn.springbootmvc1study.web.frontcontroller.v3.controller.MemberFormControllerV3;
import inflearn.springbootmvc1study.web.frontcontroller.v3.controller.MemberListControllerV3;
import inflearn.springbootmvc1study.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// * 이렇게 하면 하위의 어떤것이 들어와도 front컨트롤러에 접근한다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);//알맞은 uri에 따라 객체 인스턴스를 반환하도록 된다.

        if(controller == null) {//없을때 예외처리
           response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); //논리이름 new-form이런것이다.
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        //paramMap을 넘겨줘야한다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
