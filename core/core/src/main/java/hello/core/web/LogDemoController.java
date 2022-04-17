package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //이러면 MyLogger 주입 받는게 아니라 MyLogger를 찾을 수 있는걸 주입
   // private final ObjectProvider<MyLogger>  myLoggerProvider;
    private final MyLogger  myLogger;
    @RequestMapping("log-demo")
    @ResponseBody
    //view없고 문자 바로 응답 나가게
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //필요한 시점에 꺼내
       // MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
