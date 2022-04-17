package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request" , proxyMode = ScopedProxyMode.TARGET_CLASS)
//HTTP 요청당 하나씩 생성 - 생성시점 스프링 컨테이너에 요청하는 시점
//이 빈 생성 시점 uuid를 생성해서 저장 - 다른 HTTP 요청과 구분
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid +"]" +"[" + requestURL +"]" + message );
    }

    @PostConstruct
    public void init(){
         uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid +"] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("");
        System.out.println("[" + uuid +"] request scope bean close:" + this);
    }
}
