package inflearn.springbootmvc1study.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    //서블릿이랑 똑같은 모양의 인터페이스 만듬
    //각 컨트롤러들은 해당 인터페이스를 구현하면 된다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
