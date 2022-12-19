package inflearn.springbootmvc1study.web.frontcontroller.v1;

import inflearn.springbootmvc1study.web.frontcontroller.v1.controller.MemberFormControllerV1;
import inflearn.springbootmvc1study.web.frontcontroller.v1.controller.MemberListControllerV1;
import inflearn.springbootmvc1study.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// * 이렇게 하면 하위의 어떤것이 들어와도 front컨트롤러에 접근한다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    //다형성을 이용한 방법이다.. 대박스
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        ControllerV1 controller = controllerMap.get(requestURI);//알맞은 uri에 따라 객체 인스턴스를 반환하도록 된다.

        if(controller == null) {//없을때 예외처리
           response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        controller.process(request, response);//정상 호출한 경우
    }
}
