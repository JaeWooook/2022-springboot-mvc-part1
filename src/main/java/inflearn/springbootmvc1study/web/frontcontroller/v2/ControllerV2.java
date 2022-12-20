package inflearn.springbootmvc1study.web.frontcontroller.v2;

import inflearn.springbootmvc1study.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    //MyView를 타입으로 하는 메서드를 만든 것이다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
