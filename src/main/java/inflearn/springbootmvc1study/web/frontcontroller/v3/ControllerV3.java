package inflearn.springbootmvc1study.web.frontcontroller.v3;

import inflearn.springbootmvc1study.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
