package inflearn.springbootmvc1study.web.frontcontroller.v2;

import inflearn.springbootmvc1study.web.frontcontroller.MyView;
import inflearn.springbootmvc1study.web.frontcontroller.v2.controller.MemberFormControllerV2;
import inflearn.springbootmvc1study.web.frontcontroller.v2.controller.MemberListControllerV2;
import inflearn.springbootmvc1study.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// * 이렇게 하면 하위의 어떤것이 들어와도 front컨트롤러에 접근한다.
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);//알맞은 uri에 따라 객체 인스턴스를 반환하도록 된다.

        if(controller == null) {//없을때 예외처리
           response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        //new MyView("/WEB-INF/views/new-form.jsp"); 회원가입폼은 여기에 들어온것이다. 이게 생긴것이다.
        MyView view = controller.process(request, response);//정상 호출한 경우
        view.render(request, response);//여기서 view의 인터페이스 각각의 컨트롤러에서 구현한 것들이 실행된다.
    }
}
